package com.chenerzhu.common.secure;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA256Secure extends BaseSecure {

    public HmacSHA256Secure() {
        super();
    }

    public HmacSHA256Secure(String secretKey) {
        super.secretKey = secretKey;
    }

    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        if (secretKey == null || "".equals(secretKey)) {
            throw new Exception("scretKey need to exists");
        }
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        return mac.doFinal(data);
    }

    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        return null;
    }
}
