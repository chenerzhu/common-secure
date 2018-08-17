package com.chenerzhu.common.secure;


import com.chenerzhu.common.common.SecureType;

import java.security.NoSuchAlgorithmException;

/**
 * @author chenerzhu
 * @create 2018-08-02 10:55
 **/
public class SecureFactory {

    public static BaseSecure getSecure(SecureType type, String key){
        BaseSecure secure = null;
        switch (type) {
            case MD5:
                secure = new MD5Secure();
                break;
            case SHA:
                secure = new SHASecure();
                break;
            case DES:
                if (key != null && !"".equals(key)) {
                    secure = new DESSecure(key);
                } else {
                    try {
                        secure = new DESSecure();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case DES3:
                if (key != null && !"".equals(key)) {
                    secure = new DES3Secure(key);
                } else {
                    try {
                        secure = new DES3Secure();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case AES:
                if (key != null && !"".equals(key)) {
                    secure = new AESSecure(key);
                } else {
                    try {
                        secure = new AESSecure();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case RSA_PRIVATE:
                secure = new RSAPrivateSecure(key);
                break;
            case RSA_PUBLIC:
                secure = new RSAPublicSecure(key);
                break;
            default:
                throw new IllegalArgumentException("SecureType not be supported.");
        }
        return secure;
    }
}
