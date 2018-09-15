package com.chenerzhu.common.example;


import com.chenerzhu.common.common.SecureType;
import com.chenerzhu.common.secure.*;
import com.chenerzhu.common.util.CoderUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * @author chenerzhu
 * @create 2018-08-06 10:31
 **/
public class SecureTest {
    private static final String INPUT_STR = "test secure test secure";
    public static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3kjdBNSzLPdI85vuEZmX+YTEl" +
            "y1aVoPREmReOi47dC0NW39JLgzGcgJ8xfo4sDX6C20THQY8YZSLRyMyO/XyJnlDx" +
            "GHl44fHAh01gI/TYqV/JH8ExeADow/fX+MjXiFcH3qvSCQFAZhUyNCa77jEMx0zU" +
            "YurV5XD+TDJCqDaxrwIDAQAB";
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

    @Test
    public void md5Test() {
        System.out.println("======= MD5 ========");
        try {
            byte[] data = INPUT_STR.getBytes();
            MD5Secure codec = (MD5Secure) SecureFactory.getSecure(SecureType.MD5, null);
            System.out.println("md5 hex:" + codec.getEncrypt2Hex(data));
            System.out.println("md5 base64:" + codec.getEncrypt2Base64(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shaTest() {
        System.out.println("======== SHA ========");
        try {
            byte[] data = INPUT_STR.getBytes();
            SHASecure codec = (SHASecure) SecureFactory.getSecure(SecureType.SHA, null);
            System.out.println("sha hex:" + codec.getEncrypt2Hex(data));
            System.out.println("sha base64:" + codec.getEncrypt2Base64(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void desTest() {
        System.out.println("========= DES ========");
        try {
            byte[] data = INPUT_STR.getBytes();
            DESSecure codecA = (DESSecure) SecureFactory.getSecure(SecureType.DES, "AAAAAAAAAAAAAAAA");
            String secretKey = codecA.getSecretKey();
            System.out.println("key DES:" + secretKey);
            byte[] encryptData = codecA.encrypt(data);
            System.out.println("encryptData hex DES:" + codecA.getEncrypt2Hex(data));
            System.out.println("encryptData base64 DES:" + codecA.getEncrypt2Base64(data));


            DESSecure codecB = (DESSecure) SecureFactory.getSecure(SecureType.DES, secretKey);
            System.out.println("decryptData hex DES:" + codecB.getDecrypt2Hex(codecA.getEncrypt2Hex(data).getBytes()));
            System.out.println("decryptData base64 DES:" + codecB.getDecrypt2Base64("LN4koqnzOrj70kYdmXeO34egve7MHE/T".getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void des3Test() {
        System.out.println("========= 3DES ========");
        try {
            byte[] data = INPUT_STR.getBytes();
            DES3Secure codecA = (DES3Secure) SecureFactory.getSecure(SecureType.DES3, "AAAAAAAAAAAAAAAA");
            String secretKey = codecA.getSecretKey();
            System.out.println("key 3DES:" + secretKey);
            byte[] encryptData = codecA.encrypt(data);
            System.out.println("encryptData hex 3DES:" + codecA.getEncrypt2Hex(data));
            System.out.println("encryptData base64 3DES:" + codecA.getEncrypt2Base64(data));


            DES3Secure codecB = (DES3Secure) SecureFactory.getSecure(SecureType.DES3, secretKey);
            System.out.println("decryptData hex 3DES:" + codecB.getDecrypt2Hex(codecA.getEncrypt2Hex(data).getBytes()));
            System.out.println("decryptData base64 3DES:" + codecB.getDecrypt2Base64(codecA.getEncrypt2Base64(data).getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aesTest() {
        System.out.println("=========== AES ===========");
        try {
            byte[] data = INPUT_STR.getBytes();
            AESSecure codecA = (AESSecure) SecureFactory.getSecure(SecureType.AES, "AAAAAAAAAAAAAAAA");
            String secretKey = codecA.getSecretKey();

            System.out.println("key AES:" + secretKey);
            byte[] encryptData = codecA.encrypt(data);
            System.out.println("encryptData hex AES:" + codecA.getEncrypt2Hex(data));
            System.out.println("encryptData base64 AES:" + codecA.getEncrypt2Base64(data));


            AESSecure codecB = (AESSecure) SecureFactory.getSecure(SecureType.AES, secretKey);
            System.out.println("decryptData hex AES:" + codecB.getDecrypt2Hex(codecA.getEncrypt2Hex(data).getBytes()));
            System.out.println("decryptData base64 AES:" + codecB.getDecrypt2Base64("T5leeW3QFl82ZxIo/j1KVnNt3wAf6FFETFudjl+LnJg=".getBytes()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    //使用hex或base64编码前，需要先确定公钥和私钥的编码  可以通过enctype.HEX或enctype.BASE64设置
    public void rsaTest() {
        System.out.println("=========== RSA ============");
        try {
            byte[] data = INPUT_STR.getBytes();


            RSAPublicSecure codecB = (RSAPublicSecure) SecureFactory.getSecure(SecureType.RSA_PUBLIC, publicKeyString);
            //codecB.encType = EncType.BASE64;
            System.out.println("publicKey:"+codecB.getPublicKey());
            System.out.println("encrypt hex RSA:"+ codecB.getEncrypt2Hex(data));
            System.out.println("encrypt base64 RSA:"+ codecB.getEncrypt2Base64(data));

            RSAPrivateSecure codecA = (RSAPrivateSecure) SecureFactory.getSecure(SecureType.RSA_PRIVATE, privateKeyString);
            //codecA.encType = EncType.BASE64;
            String privateKey=codecA.getPrivateKey();
            System.out.println("privateKey:"+privateKey);
            System.out.println("decrypt hex RSA:"+codecA.getDecrypt2Hex(codecB.getEncrypt2Hex(data).getBytes()));
            System.out.println("decrypt base64 RSA:"+codecA.getDecrypt2Base64(codecB.getEncrypt2Base64(data).getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBase64() throws IOException {
        //dGVzdCBzZWN1cmU=
        System.out.println(CoderUtil.encodeBase64(INPUT_STR.getBytes()));
        System.out.println(new String(CoderUtil.encodeBase64(INPUT_STR.getBytes())));

        System.out.println(new String(CoderUtil.decodeBase64("dGVzdCBzZWN1cmU=")));
        System.out.println(new String(CoderUtil.decodeBase64("dGVzdCBzZWN1cmU=")));
    }

}
