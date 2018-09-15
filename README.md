# common-secure
提供一些加密算法java代码封装 包括 RSA/AES/DES/3DES/MD5/SHA

代码封装了各类算法的加密解密。

##### 1、SecureTest 测试了所有算法封装的代码

src\main\java\com\chenerzhu\common\example\SecureTest.java

##### 2、SecureExample 列举了加密和解密的SecureUtils 使用

src\main\java\com\chenerzhu\common\util\SecureUtils.java    
src\main\java\com\chenerzhu\common\example\SecureExample.java

##### 3、SignatureExample 列举了SignatureUtils RSA签名的生成和验证 
  
src\main\java\com\chenerzhu\common\util\SignatureUtils.java   
src\main\java\com\chenerzhu\common\example\SignatureExample.java

## 举例
    final String INPUT_STR="hello world.";   
    String key="AAAAAAAAAAAAAAAA";  
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
    private static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3kjdBNSzLPdI85vuEZmX+YTEl" +
            "y1aVoPREmReOi47dC0NW39JLgzGcgJ8xfo4sDX6C20THQY8YZSLRyMyO/XyJnlDx" +
            "GHl44fHAh01gI/TYqV/JH8ExeADow/fX+MjXiFcH3qvSCQFAZhUyNCa77jEMx0zU" +
            "YurV5XD+TDJCqDaxrwIDAQAB";
   
#### MD5：  
       String strMD5=SecureUtils.getMD5(INPUT_STR);  
#### SHA：
       String strSHA=SecureUtils.getSHA(INPUT_STR); 
#### DES：  
     加密 String strDES=SecureUtils.encryptDES(INPUT_STR, key);
     解密 SecureUtils.decryptDES(strDES, key);
#### RSA：  
     加密-解密：
     String strRSAPrivate = SecureUtils.encryptRSAPrivate(INPUT_STR, privateKeyString);//私钥加密
     String strRSAPublic = SecureUtils.encryptRSAPublic(INPUT_STR, publicKeyString);//公钥加密
     System.out.println("RSA 公钥解密:" + SecureUtils.decryptRSAPublic(strRSAPrivate, publicKeyString));
     System.out.println("RSA 私钥解密:" + SecureUtils.decryptRSAPrivate(strRSAPublic, privateKeyString));  
       
     签名-验证：
     String sign= SignatureUtils.getSignRSA(INPUT_STR,privateKeyString);
     System.out.println("签名："+sign+"\n length:"+sign.length());
     System.out.println("验证："+ SignatureUtils.verify(INPUT_STR,publicKeyString,sign));
