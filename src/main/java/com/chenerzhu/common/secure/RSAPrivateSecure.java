package com.chenerzhu.common.secure;

import com.chenerzhu.common.common.SecureType;
import com.chenerzhu.common.exception.CodeException;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * RSA 非对称加密，持有私钥的甲方
 */
public class RSAPrivateSecure extends BaseSecure {
    private String privateKey;
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
     /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public RSAPrivateSecure(String privateKey) {
        super();
        this.setPrivateKey(privateKey);
    }

    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        if (privateKey == null || "".equals(privateKey)) {
            throw new CodeException("RSA decrypt privateKey is null");
        }
        Cipher cipher = Cipher.getInstance(SecureType.RSA.getType());
        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(privateKey));
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        if (privateKey == null || "".equals(privateKey)) {
            throw new CodeException("RSA decrypt privateKey is null");
        }
        Cipher cipher = Cipher.getInstance(SecureType.RSA.getType());
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 使用默认签名算法 SHA256WithRSA
     *
     * @param data
     * @return
     * @throws Exception
     */
    public String sign(byte[] data) {
        return sign(data, SecureType.SignAlgorithm.SHA256WithRSA);
    }

    /**
     * 签名
     *
     * @param data
     * @param signAlgorithm
     * @return
     * @throws Exception
     */
    public String sign(byte[] data, SecureType.SignAlgorithm signAlgorithm) {
        try {
            /*if (privateKey == null || "".equals(privateKey)) {
                throw new CodeException("RSA decrypt privateKey is null");
            }*/
            PrivateKey key = getPrivateKey(privateKey);
            Signature signature = Signature.getInstance(signAlgorithm.getType());
            signature.initSign(key);
            signature.update(data);
            byte[] sign = signature.sign();
            return encode(sign,getContentEncType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PrivateKey getPrivateKey(String privateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode(privateKey,getKeyEncType()));
        KeyFactory keyFactory = KeyFactory.getInstance(SecureType.RSA.getType());
        return keyFactory.generatePrivate(keySpec);
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
