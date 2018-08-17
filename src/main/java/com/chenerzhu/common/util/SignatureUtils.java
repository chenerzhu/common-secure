package com.chenerzhu.common.util;

import com.chenerzhu.common.common.EncType;
import com.chenerzhu.common.common.SecureType;
import com.chenerzhu.common.secure.RSAPrivateSecure;
import com.chenerzhu.common.secure.RSAPublicSecure;
import com.chenerzhu.common.secure.SecureFactory;

public class SignatureUtils {
    /**
     * md5 默认HEX
     * @param value
     * @return
     */
    public static String getSignMD5(String value){
        return getSignMD5(value,EncType.HEX);
    }

    /**
     * md5 指定编码
     * @param value
     * @param encType
     * @return
     */
    public static String getSignMD5(String value, EncType encType){
        return SecureUtils.getMD5(value,encType);
    }
    /**
     * sha 默认HEX
     * @param value
     * @return
     */
    public static String getSignSHA(String value){
        return getSignSHA(value,EncType.HEX);
    }

    /**
     * sha 指定编码
     * @param value
     * @param encType
     * @return
     */
    public static String getSignSHA(String value, EncType encType){
        return SecureUtils.getSHA(value,encType);
    }

    /**
     * DES 默认base64
     * @param value
     * @return
     */
    public static String getSignDES(String value, String key){
        return getSignDES(value,key,EncType.BASE64,EncType.BASE64);
    }

    /**
     * DES 指定编码
     * @param value
     * @param contentEncType
     * @param keyEncType
     * @return
     */
    public static String getSignDES(String value, String key, EncType contentEncType, EncType keyEncType){
        return SecureUtils.encryptDES(value,key,contentEncType,keyEncType);
    }

    /**
     * 3DES 默认base64
     * @param value
     * @return
     */
    public static String getSign3DES(String value, String key){
        return getSign3DES(value,key,EncType.BASE64,EncType.BASE64);
    }

    /**
     * 3DES 指定编码
     * @param value
     * @param contentEncType
     * @param keyEncType
     * @return
     */
    public static String getSign3DES(String value, String key, EncType contentEncType, EncType keyEncType){
        return SecureUtils.encrypt3DES(value,key,contentEncType,keyEncType);
    }

    /**
     * AES 默认base64
     * @param value
     * @return
     */
    public static String getSignAES(String value, String key){
        return getSign3DES(value,key,EncType.BASE64,EncType.BASE64);
    }

    /**
     * AES 指定编码
     * @param value
     * @param contentEncType
     * @param keyEncType
     * @return
     */
    public static String getSignAES(String value, String key, EncType contentEncType, EncType keyEncType){
        return SecureUtils.encryptAES(value,key,contentEncType,keyEncType);
    }

    /**
     * 使用指定方法，指定加密key,默认编码,默认加签算法
     *
     * @param value
     * @param privateKey
     * @return
     */
    public static String getSignRSA(String value, String privateKey) {
        return getSignRSA(value, privateKey, SecureType.SignAlgorithm.SHA256WithRSA, EncType.BASE64, EncType.BASE64);
    }

    /**
     * 使用指定方法，指定加密key,默认编码，指定加签算法
     *
     * @param value
     * @param privateKey
     * @param type
     * @return
     */
    public static String getSignRSA(String value, String privateKey, SecureType.SignAlgorithm type) {
        return getSignRSA(value, privateKey, type, EncType.BASE64, EncType.BASE64);
    }

    /**
     * 使用指定方法，指定加密key,指定编码(结果内容编码，key编码),默认加签算法
     *
     * @param value
     * @param privateKey
     * @return
     */
    public static String getSignRSA(String value, String privateKey, EncType contentEncType, EncType keyEncType) {
        return getSignRSA(value, privateKey, SecureType.SignAlgorithm.SHA256WithRSA, contentEncType, keyEncType);
    }

    /**
     * 使用指定方法，指定加密key,指定编码(结果内容编码，key编码),加签算法
     *
     * @param value
     * @param privateKey
     * @param type
     * @return
     */
    public static String getSignRSA(String value, String privateKey, SecureType.SignAlgorithm type, EncType contentEncType, EncType keyEncType) {
        byte[] data = value.getBytes();
        RSAPrivateSecure secure = (RSAPrivateSecure) SecureFactory.getSecure(SecureType.RSA_PRIVATE, privateKey);
        secure.setKeyEncType(keyEncType);
        secure.setContentEncType(contentEncType);
        return secure.sign(data, type);
    }

    /**
     * RSA  使用指定方法，指定加密key,默认编码,默认加签算法
     * 验证
     *
     * @param value
     * @param publicKey
     * @return
     */
    public static boolean verify(String value, String publicKey, String sign) {
        return verify(value, publicKey, sign, SecureType.SignAlgorithm.SHA256WithRSA, EncType.BASE64, EncType.BASE64);
    }

    /**
     * RSA 使用指定方法，指定加密key,默认编码，指定加签算法
     * 验证
     *
     * @param value
     * @param publicKey
     * @param type
     * @return
     */
    public static boolean verify(String value, String publicKey, String sign, SecureType.SignAlgorithm type) {
        return verify(value, publicKey, sign, type, EncType.BASE64, EncType.BASE64);

    }

    /**
     * RSA 使用指定方法，指定加密key,指定编码(结果内容编码，key编码),默认加签算法
     * 验证
     *
     * @param value
     * @param publicKey
     * @return
     */
    public static boolean verify(String value, String publicKey, String sign, EncType contentEncType, EncType keyEncType) {
        return verify(value, publicKey, sign, SecureType.SignAlgorithm.SHA256WithRSA, contentEncType, keyEncType);
    }

    /**
     * RSA 使用指定方法，指定加密key,指定编码(结果内容编码，key编码),加签算法
     * 验证
     *
     * @param value
     * @param sign
     * @param type
     * @return
     */
    public static boolean verify(String value, String publicKey, String sign, SecureType.SignAlgorithm type, EncType contentEncType, EncType keyEncType) {
        byte[] data = value.getBytes();
        RSAPublicSecure secure = (RSAPublicSecure) SecureFactory.getSecure(SecureType.RSA_PUBLIC, publicKey);
        secure.setKeyEncType(keyEncType);
        secure.setContentEncType(contentEncType);
        return secure.verify(data, sign, type);
    }

}
