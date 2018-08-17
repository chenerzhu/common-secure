package com.chenerzhu.common.secure;


import com.chenerzhu.common.common.SecureType;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class AESSecure extends BaseSecure {

    private static final int KEY_SIZE = 128;

    public AESSecure() throws NoSuchAlgorithmException {
        super();
    }

    public AESSecure(String secretKey) {
        super.secretKey = secretKey;
    }

    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        if (secretKey == null || "".equals(secretKey)) {
            throw new Exception("scretKey need to exists");
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), SecureType.AES.getType());
        Cipher cipher = Cipher.getInstance(SecureType.AES.getType());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        if (secretKey == null || "".equals(secretKey)) {
            throw new Exception("scretKey need to exists");
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), SecureType.AES.getType());
        Cipher cipher = Cipher.getInstance(SecureType.AES.getType());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }
}
