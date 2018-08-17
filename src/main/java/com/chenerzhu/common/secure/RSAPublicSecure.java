package com.chenerzhu.common.secure;

import com.chenerzhu.common.common.SecureType;
import com.chenerzhu.common.exception.CodeException;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 非对称加密，持有公钥的乙方
 */
public class RSAPublicSecure extends BaseSecure {
    private String publicKey;
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public RSAPublicSecure(String publicKey) {
        super();
        this.setPublicKey(publicKey);
    }


    public PublicKey getPublicKey(String publicKey) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode(publicKey,getKeyEncType()));
        KeyFactory keyFactory = KeyFactory.getInstance(SecureType.RSA.getType());
        return keyFactory.generatePublic(keySpec);
    }


    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        if (publicKey == null || "".equals(publicKey)) {
            throw new CodeException("RSA encrypt publicKey is null");
        }
        Cipher cipher = Cipher.getInstance(SecureType.RSA.getType());//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
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
        if (publicKey == null || "".equals(publicKey)) {
            throw new CodeException("RSA encrypt publicKey is null");
        }
        Cipher cipher = Cipher.getInstance(SecureType.RSA.getType());//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.DECRYPT_MODE, getPublicKey(publicKey));
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
     * 验证  使用默认签名算法SHA256WithRSA
     *
     * @param data
     * @param signData
     * @return
     * @throws Exception
     */
    public boolean verify(byte[] data, String signData) {
        return verify(data, signData, SecureType.SignAlgorithm.SHA256WithRSA);
    }

    /**
     * 验证签名
     *
     * @param data          未加密的值
     * @param signData      加密后的签名
     * @param signAlgorithm 签名算法
     * @return
     * @throws Exception
     */
    public boolean verify(byte[] data, String signData, SecureType.SignAlgorithm signAlgorithm) {
        try {
            PublicKey key = getPublicKey(publicKey);
            Signature signature = Signature.getInstance(signAlgorithm.getType());
            signature.initVerify(key);
            signature.update(data);
            return signature.verify(decode(signData,getContentEncType()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
