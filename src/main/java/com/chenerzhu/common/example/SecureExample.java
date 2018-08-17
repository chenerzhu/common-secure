package com.chenerzhu.common.example;

import com.chenerzhu.common.common.EncType;
import com.chenerzhu.common.util.SecureUtils;

/**
 * @author chenerzhu
 * @create 2018-08-06 11:32
 **/
public class SecureExample {
    private static final String INPUT_STR = "test secure test secure";
    public static String privateKeyString = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALeSN0E1LMs90jzm" +
            "+4RmZf5hMSXLVpWg9ESZF46Ljt0LQ1bf0kuDMZyAnzF+jiwNfoLbRMdBjxhlItHI" +
            "zI79fImeUPEYeXjh8cCHTWAj9NipX8kfwTF4AOjD99f4yNeIVwfeq9IJAUBmFTI0" +
            "JrvuMQzHTNRi6tXlcP5MMkKoNrGvAgMBAAECgYBWRkjjw6sOxjpV1zUkb7/Fw0YG" +
            "/j7uSdwjlVPl8Z8uMgnu/XhndvxNEoI/D7yf5aOsuoLjpuMq0vV/ZQEGvwnVFAt8" +
            "At8akr6yw53b/LeLqlFd/RNWKWoIieXEEoH0n57X+J9U7CTIkCLLQnVhc+63L9ha" +
            "we5EpVXibMcaKnrpgQJBAN/3V69b9pvqmqFBjFpsagrdso+evOqMmp6rPc0KmmNT" +
            "xneQ3Xq9AkSo0ntOeMuzYRDoWM+7/J14yRkG2CK2gu8CQQDR08jy7ytFWBjWuRtm" +
            "5Zu1sl3azTpf7DkJB5Fd5v0XjXpOoks9aEANGVTMerWr+hu6ptpKG9o0/XzQWctW" +
            "G71BAkAs19dksyMjgMvJMdiqWj65Qj54Zy4oQFLNJjhPj6nt7V41nnnaE3Ia0Tqj" +
            "mcix8I6k1gDCRz+DQCXzrt0jxitdAkEAkZYAt45614JouZN2D88AWvGHbWk4N5YP" +
            "fNRjaGP893qSgjzZN6I9ztjknXwG0WyYEMn0a7cnj9zR3T5wdy6IAQJAQjqeI/N+" +
            "2Eh0GCQNs8QuewIesbKX96MDZIivgCt/IVDRDcXK1/7UgcvVPsOmuYkzvnyCDte4" +
            "eKCQdMCEJw11Jw==";
    public static String privateKeyStringHex = "30820276020100300D06092A864886F70D0101010500048202603082025C02010002818100B7923741352CCB3DD23CE6FB846665FE613125CB5695A0F44499178E8B8EDD0B4356DFD24B83319C809F317E8E2C0D7E82DB44C7418F186522D1C8CC8EFD7C899E50F1187978E1F1C0874D6023F4D8A95FC91FC1317800E8C3F7D7F8C8D7885707DEABD20901406615323426BBEE310CC74CD462EAD5E570FE4C3242A836B1AF0203010001028180564648E3C3AB0EC63A55D735246FBFC5C34606FE3EEE49DC239553E5F19F2E3209EEFD786776FC4D12823F0FBC9FE5A3ACBA82E3A6E32AD2F57F650106BF09D5140B7C02DF1A92BEB2C39DDBFCB78BAA515DFD1356296A0889E5C41281F49F9ED7F89F54EC24C89022CB42756173EEB72FD85AC1EE44A555E26CC71A2A7AE981024100DFF757AF5BF69BEA9AA1418C5A6C6A0ADDB28F9EBCEA8C9A9EAB3DCD0A9A6353C67790DD7ABD0244A8D27B4E78CBB36110E858CFBBFC9D78C91906D822B682EF024100D1D3C8F2EF2B455818D6B91B66E59BB5B25DDACD3A5FEC390907915DE6FD178D7A4EA24B3D68400D1954CC7AB5ABFA1BBAA6DA4A1BDA34FD7CD059CB561BBD4102402CD7D764B3232380CBC931D8AA5A3EB9423E78672E284052CD26384F8FA9EDED5E359E79DA13721AD13AA399C8B1F08EA4D600C2473F834025F3AEDD23C62B5D024100919600B78E7AD78268B993760FCF005AF1876D693837960F7CD4636863FCF77A92823CD937A23DCED8E49D7C06D16C9810C9F46BB7278FDCD1DD3E70772E88010240423A9E23F37ED8487418240DB3C42E7B021EB1B297F7A3036488AF802B7F2150D10DC5CAD7FED481CBD53EC3A6B98933BE7C820ED7B878A09074C084270D7527";
    public static String publicKeyStringHex = "30819F300D06092A864886F70D010101050003818D0030818902818100B7923741352CCB3DD23CE6FB846665FE613125CB5695A0F44499178E8B8EDD0B4356DFD24B83319C809F317E8E2C0D7E82DB44C7418F186522D1C8CC8EFD7C899E50F1187978E1F1C0874D6023F4D8A95FC91FC1317800E8C3F7D7F8C8D7885707DEABD20901406615323426BBEE310CC74CD462EAD5E570FE4C3242A836B1AF0203010001";
    private static String key = "AAAAAAAAAAAAAAAA";
    private static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3kjdBNSzLPdI85vuEZmX+YTEl" +
            "y1aVoPREmReOi47dC0NW39JLgzGcgJ8xfo4sDX6C20THQY8YZSLRyMyO/XyJnlDx" +
            "GHl44fHAh01gI/TYqV/JH8ExeADow/fX+MjXiFcH3qvSCQFAZhUyNCa77jEMx0zU" +
            "YurV5XD+TDJCqDaxrwIDAQAB";

    public static void main(String[] args) {
        System.out.println("==================加密开始===================");
        System.out.println("使用指定算法，默认编码:");
        System.out.println("MD5:" + SecureUtils.getMD5(INPUT_STR));
        System.out.println("SHA:" + SecureUtils.getSHA(INPUT_STR));
        System.out.println("============================");
        System.out.println("使用指定算法，指定编码:");
        System.out.println("MD5:" + SecureUtils.getMD5(INPUT_STR, EncType.HEX));
        System.out.println("SHA:" + SecureUtils.getSHA(INPUT_STR, EncType.HEX));
        System.out.println("============================");
        System.out.println("使用指定方法，指定加密key,默认编码:");
        String strDES = SecureUtils.encryptDES(INPUT_STR, key);
        String strDES3 = SecureUtils.encrypt3DES(INPUT_STR, key);
        String strAES = SecureUtils.encryptAES(INPUT_STR, key);
        String strRSAPrivate = SecureUtils.encryptRSAPrivate(INPUT_STR, privateKeyString);
        String strRSAPublic = SecureUtils.encryptRSAPublic(INPUT_STR, publicKeyString);
        System.out.println("DES:" + SecureUtils.encryptDES(INPUT_STR, key));
        System.out.println("DES3:" + SecureUtils.encrypt3DES(INPUT_STR, key));
        System.out.println("AES:" + SecureUtils.encryptAES(INPUT_STR, key));
        System.out.println("RSA 私钥加密:" + SecureUtils.encryptRSAPrivate(INPUT_STR, privateKeyString));//私钥加密公钥解密
        System.out.println("RSA 公钥加密:" + SecureUtils.encryptRSAPublic(INPUT_STR, publicKeyString));//公钥加密私钥解密
        System.out.println("============================");
        System.out.println("使用指定方法，指定加密key,指定编码:");
        System.out.println("DES HEX:" + SecureUtils.encryptDES(INPUT_STR, key, EncType.HEX, EncType.DEFAULT));
        System.out.println("DES BASE64:" + SecureUtils.encryptDES(INPUT_STR, key, EncType.BASE64, EncType.DEFAULT));
        //System.out.println("DES:" + SecureUtils.encryptDES(INPUT_STR, key, EncType.HEX,EncType.HEX));

        System.out.println("3DES HEX:" + SecureUtils.encrypt3DES(INPUT_STR, key, EncType.HEX, EncType.DEFAULT));
        System.out.println("3DES BASE64:" + SecureUtils.encrypt3DES(INPUT_STR, key, EncType.BASE64, EncType.DEFAULT));
        //System.out.println("DES3:" + SecureUtils.encrypt3DES(INPUT_STR, key, EncType.HEX,EncType.BASE64));
        System.out.println("AES:" + SecureUtils.encryptAES(INPUT_STR, key, EncType.HEX, EncType.HEX));
        System.out.println("RSA 私钥加密:" + SecureUtils.encryptRSAPrivate(INPUT_STR, privateKeyStringHex, EncType.HEX, EncType.HEX));
        System.out.println("RSA 公钥加密:" + SecureUtils.encryptRSAPublic(INPUT_STR, publicKeyStringHex, EncType.HEX, EncType.HEX));
        System.out.println("==================加密结束===================");


        System.out.println("==================解密开始===================");
        System.out.println("使用指定方法，指定解密key,默认编码:");
        System.out.println("DES:" + SecureUtils.decryptDES(strDES, key));
        System.out.println("DES3:" + SecureUtils.decrypt3DES(strDES3, key));
        System.out.println("AES:" + SecureUtils.decryptAES(strAES, key));
        System.out.println("RSA 公钥解密:" + SecureUtils.decryptRSAPublic(strRSAPrivate, publicKeyString));
        System.out.println("RSA 私钥解密:" + SecureUtils.decryptRSAPrivate(strRSAPublic, privateKeyString));//公钥加密私钥解密
        System.out.println("============================");
        System.out.println("使用指定方法，指定解密key,指定编码:");
        strDES = SecureUtils.encryptDES(INPUT_STR, key, EncType.HEX, EncType.DEFAULT);
        strDES3 = SecureUtils.encrypt3DES(INPUT_STR, key, EncType.HEX, EncType.DEFAULT);
        strAES = SecureUtils.encryptAES(INPUT_STR, key, EncType.HEX, EncType.HEX);
        strRSAPrivate = SecureUtils.encryptRSAPrivate(INPUT_STR, privateKeyStringHex, EncType.HEX, EncType.HEX);
        strRSAPublic = SecureUtils.encryptRSAPublic(INPUT_STR, publicKeyStringHex, EncType.HEX, EncType.HEX);
        System.out.println("DES:" + SecureUtils.decryptDES(strDES, key, EncType.HEX, EncType.DEFAULT));
        System.out.println("DES3:" + SecureUtils.decrypt3DES(strDES3, key, EncType.HEX, EncType.DEFAULT));
        System.out.println("AES:" + SecureUtils.decryptAES(strAES, key, EncType.HEX, EncType.HEX));
        System.out.println("RSA 公钥解密:" + SecureUtils.decryptRSAPublic(strRSAPrivate, publicKeyStringHex, EncType.HEX, EncType.HEX));//私钥加密公钥解密
        System.out.println("RSA 私钥解密:" + SecureUtils.decryptRSAPrivate(strRSAPublic, privateKeyStringHex, EncType.HEX, EncType.HEX));//公钥加密私钥解密

        System.out.println("==================解密结束===================");
    }
}