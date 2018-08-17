package com.chenerzhu.common.example.RSA;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * @author chenerzhu
 * @create 2018-08-04 13:22
 **/
public class RSADemo3 {
    public static String data="hello world";
    public static String modulusString="95701876885335270857822974167577168764621211406341574477817778908798408856077334510496515211568839843884498881589280440763139683446418982307428928523091367233376499779842840789220784202847513854967218444344438545354682865713417516385450114501727182277555013890267914809715178404671863643421619292274848317157";
    public static String publicExponentString="65537";
    public static String privateExponentString="15118200884902819158506511612629910252530988627643229329521452996670429328272100404155979400725883072214721713247384231857130859555987849975263007110480563992945828011871526769689381461965107692102011772019212674436519765580328720044447875477151172925640047963361834004267745612848169871802590337012858580097";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        //由n和e获取公钥
        PublicKey publicKey=getPublicKey(modulusString, publicExponentString);

        //由n和d获取私钥
        PrivateKey privateKey=getPrivateKey(modulusString, privateExponentString);

        //公钥加密
        byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);
        System.out.println("加密后："+new String(encryptedBytes));

        //私钥解密
        byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);
        System.out.println("解密后："+new String(decryptedBytes));
    }

    //将base64编码后的公钥字符串转成PublicKey实例
    public static PublicKey getPublicKey(String modulusStr, String exponentStr) throws Exception {
        BigInteger modulus=new BigInteger(modulusStr);
        BigInteger exponent=new BigInteger(exponentStr);
        RSAPublicKeySpec publicKeySpec=new RSAPublicKeySpec(modulus, exponent);
        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicKeySpec);
    }

    //将base64编码后的私钥字符串转成PrivateKey实例
    public static PrivateKey getPrivateKey(String modulusStr, String exponentStr) throws Exception {
        BigInteger modulus=new BigInteger(modulusStr);
        BigInteger exponent=new BigInteger(exponentStr);
        RSAPrivateKeySpec privateKeySpec=new RSAPrivateKeySpec(modulus, exponent);
        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }

    //公钥加密
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher= Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    //私钥解密
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher= Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }
}