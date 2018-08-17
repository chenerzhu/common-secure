package com.chenerzhu.common.secure;

import com.chenerzhu.common.common.SecureType;
import com.chenerzhu.common.exception.CodeException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * DES 对称加密类
 */
public class DESSecure extends BaseSecure {

    /**
     * 当为密钥生产者（甲方）调用该构造方法
     *
     * @throws NoSuchAlgorithmException
     */
    public DESSecure() throws NoSuchAlgorithmException {
        super();
    }

    /**
     * 当为密钥接受者（乙方），调用该构造方法。
     *
     * @param secretKey 密钥
     */
    public DESSecure(String secretKey) {
        super.secretKey = secretKey;
    }


    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        if (secretKey == null || "".equals(secretKey)) {
            throw new CodeException("scretKey need to exists");
        }

        Key desKey = generateKey(secretKey);
        Cipher cipher = Cipher.getInstance(SecureType.DES.getType());
        cipher.init(Cipher.ENCRYPT_MODE, desKey);
        return cipher.doFinal(data);
    }

    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        if (secretKey == null || "".equals(secretKey)) {
            throw new Exception("scretKey need to exists");
        }

        Key desKey = generateKey(secretKey);
        Cipher cipher = Cipher.getInstance(SecureType.DES.getType());
        cipher.init(Cipher.DECRYPT_MODE, desKey);
        return cipher.doFinal(data);
    }


    /**
     * 根据传入的字符串的key生成key对象
     *
     * @param strKey
     */
    public Key generateKey(String strKey) throws Exception {
        try{
            DESKeySpec desKeySpec = new DESKeySpec(decode(strKey,getKeyEncType()));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SecureType.DES.getType());
            return keyFactory.generateSecret(desKeySpec);
        }catch(Exception e){
            throw new CodeException("generateKey exception",e);
        }

    }
}
