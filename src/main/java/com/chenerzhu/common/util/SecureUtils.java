package com.chenerzhu.common.util;

import com.chenerzhu.common.common.EncType;
import com.chenerzhu.common.common.SecureType;
import com.chenerzhu.common.secure.BaseSecure;
import com.chenerzhu.common.secure.SecureFactory;

import java.io.IOException;

/**
 * @author chenerzhu
 * @create 2018-08-06 10:55
 **/
public class SecureUtils {
    /**
     * 使用指定方法，指定加密key,指定编码(结果内容编码，key编码) 加密
     * @param value
     * @param key
     * @param type
     * @return
     */
    public static String encrypt(String value, String key, SecureType type, EncType contentEncType, EncType keyEncType) {
        byte[] data = value.getBytes();
        BaseSecure secure = SecureFactory.getSecure(type, key);
        secure.setKeyEncType(keyEncType);
        secure.setContentEncType(contentEncType);
        return secure.getEncrypt(data,contentEncType);
    }

    /**
     * 使用指定方法，指定解密key,指定编码(结果内容编码，key编码) 解密
     * @param value
     * @param key
     * @param type
     * @return
     */
    public static String decrypt(String value, String key, SecureType type, EncType contentEncType, EncType keyEncType) {
        byte[] data = value.getBytes();
        BaseSecure secure = SecureFactory.getSecure(type, key);
        secure.setKeyEncType(keyEncType);
        secure.setContentEncType(contentEncType);
        return secure.getDecrypt(data,contentEncType);
    }

    /**
     * md5加密
     * @param content
     * @return
     */
    public static String getMD5(String content) {
        return encrypt(content, null,SecureType.MD5,EncType.HEX,null);
    }

    /**
     * sha加密
     * @param content
     * @return
     */
    public static String getSHA(String content) {
        return encrypt(content, null,SecureType.SHA,EncType.HEX,null);
    }

    /**
     * md5加密
     * @param content
     * @return
     */
    public static String getMD5(String content, EncType contentEncType) {
        return encrypt(content, null,SecureType.MD5,contentEncType,null);
    }

    /**
     * sha加密
     * @param content
     * @return
     */
    public static String getSHA(String content, EncType contentEncType) {
        return encrypt(content, null,SecureType.SHA,contentEncType,null);
    }

    /**
     * RSA默认编码加密  私钥加密
     * @param value
     * @param privateKey
     * @return
     */
    public static String encryptRSAPrivate(String value, String privateKey) {
        return encrypt(value, privateKey,SecureType.RSA_PRIVATE,EncType.BASE64,EncType.BASE64);
    }
    /**
     * RSA默认编码加密 公钥加密
     * @param value
     * @param publicKey
     * @return
     */
    public static String encryptRSAPublic(String value, String publicKey) {
        return encrypt(value, publicKey,SecureType.RSA_PUBLIC,EncType.BASE64,EncType.BASE64);
    }

    /**
     * RSA默认编码解密 私钥解密
     * @param value
     * @param privateKey
     * @return
     */
    public static String decryptRSAPrivate(String value, String privateKey) {
        return decrypt(value, privateKey,SecureType.RSA_PRIVATE,EncType.BASE64,EncType.BASE64);
    }
    /**
     * RSA默认编码解密 公钥解密
     * @param value
     * @param publicKey
     * @return
     */
    public static String decryptRSAPublic(String value, String publicKey) {
        return decrypt(value, publicKey,SecureType.RSA_PUBLIC,EncType.BASE64,EncType.BASE64);
    }

    /**
     * RSA指定编码加密 私钥加密
     * @param value
     * @param privateKey
     * @return
     */
    public static String encryptRSAPrivate(String value, String privateKey, EncType contentEncType, EncType keyEncType) {
        return encrypt(value, privateKey,SecureType.RSA_PRIVATE,contentEncType,keyEncType);
    }

    /**
     * RSA指定编码加密 公钥加密
     * @param value
     * @param publicKey
     * @return
     */
    public static String encryptRSAPublic(String value, String publicKey, EncType contentEncType, EncType keyEncType) {
        return encrypt(value, publicKey,SecureType.RSA_PUBLIC,contentEncType,keyEncType);
    }

    /**
     * RSA指定编码解密 私钥解密
     * @param value
     * @param privateKey
     * @return
     */
    public static String decryptRSAPrivate(String value, String privateKey, EncType contentEncType, EncType keyEncType) {
        return decrypt(value, privateKey,SecureType.RSA_PRIVATE,contentEncType,keyEncType);
    }
    /**
     * RSA指定编码解密 公钥解密
     * @param value
     * @param publicKey
     * @return
     */
    public static String decryptRSAPublic(String value, String publicKey, EncType contentEncType, EncType keyEncType) {
        return decrypt(value, publicKey,SecureType.RSA_PUBLIC,contentEncType,keyEncType);
    }

    /**
     * DES默认编码加密
     * @param value
     * @param key
     * @return
     */
    public static String encryptDES(String value, String key) {
        return encrypt(value, key,SecureType.DES,EncType.BASE64,EncType.DEFAULT);
    }

    /**
     * DES默认编码解密
     * @param value
     * @param key
     * @return
     */
    public static String decryptDES(String value, String key) {
        return decrypt(value, key,SecureType.DES,EncType.BASE64,EncType.DEFAULT);
    }

    /**
     * DES指定编码加密
     * @param value
     * @param key
     * @return
     */
    public static String encryptDES(String value, String key, EncType contentEncType, EncType keyEncType) {
        return encrypt(value, key,SecureType.DES,contentEncType,keyEncType);
    }

    /**
     * DES指定编码解密
     * @param value
     * @param key
     * @return
     */
    public static String decryptDES(String value, String key, EncType contentEncType, EncType keyEncType) {
        return decrypt(value, key,SecureType.DES,contentEncType,keyEncType);
    }

    /**
     * 3DES默认编码加密
     * @param value
     * @param key
     * @return
     */
    public static String encrypt3DES(String value, String key) {
        return encrypt(value, key,SecureType.DES3,EncType.BASE64,EncType.DEFAULT);
    }

    /**
     * 3DES默认编码解密
     * @param value
     * @param key
     * @return
     */
    public static String decrypt3DES(String value, String key) {
        return decrypt(value, key,SecureType.DES3,EncType.BASE64,EncType.DEFAULT);
    }

    /**
     * 3DES指定编码加密
     * @param value
     * @param key
     * @return
     */
    public static String encrypt3DES(String value, String key, EncType contentEncType, EncType keyEncType) {
        return encrypt(value, key,SecureType.DES3,contentEncType,keyEncType);
    }

    /**
     * 3DES指定编码解密
     * @param value
     * @param key
     * @return
     */
    public static String decrypt3DES(String value, String key, EncType contentEncType, EncType keyEncType) {
        return decrypt(value, key,SecureType.DES3,contentEncType,keyEncType);
    }

    /**
     * AES默认编码加密
     * @param value
     * @param key
     * @return
     */
    public static String encryptAES(String value, String key) {
        return encrypt(value, key,SecureType.AES,EncType.BASE64,EncType.DEFAULT);
    }

    /**
     * AES默认编码解密
     * @param value
     * @param key
     * @return
     */
    public static String decryptAES(String value, String key) {
        return decrypt(value, key,SecureType.AES,EncType.BASE64,EncType.DEFAULT);
    }

    /**
     * AES指定编码加密
     * @param value
     * @param key
     * @return
     */
    public static String encryptAES(String value, String key, EncType contentEncType, EncType keyEncType) {
        return encrypt(value, key,SecureType.AES,contentEncType,keyEncType);
    }

    /**
     * AES指定编码解密
     * @param value
     * @param key
     * @return
     */
    public static String decryptAES(String value, String key, EncType contentEncType, EncType keyEncType) {
        return decrypt(value, key,SecureType.AES,contentEncType,keyEncType);
    }

    /**
     * 2进制数字转换为16进制字符串
     *
     * @param data
     * @return
     */
    public String encodeByteToHex(byte[] data) {
        if (data == null || data.length < 1) {
            return null;
        }

        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int h = data[i] & 0XFF;
            if (h < 16) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(h));
        }

        return hex.toString();
    }

    /**
     * 16进制字符串转换为2进制数字
     *
     * @param hex
     * @return
     */
    public byte[] decodeHex2Byte(String hex) {
        if (hex == null || "".equals(hex)) {
            return null;
        }

        int length = hex.length() >> 1;
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            int n = i << 1;
            int height = Integer.valueOf(hex.substring(n, n + 1), 16);
            int low = Integer.valueOf(hex.substring(n + 1, n + 2), 16);
            data[i] = (byte) (height * 16 + low);
        }

        return data;
    }

    public static String encodeByte2Base64(byte[] data){
        return CoderUtil.encodeBase64(data);
    }

    public static byte[] decodeBase642Byte(String data) throws IOException {
        return CoderUtil.decodeBase64(data);
    }
}