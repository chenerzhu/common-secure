package com.chenerzhu.common.secure;

import com.chenerzhu.common.common.SecureType;
import com.chenerzhu.common.exception.CodeException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * 3DES
 *
 * @author chenerzhu
 * @create 2018-08-07 19:47
 **/
public class DES3Secure extends BaseSecure {
    /**
     * 当为密钥生产者（甲方）调用该构造方法
     *
     * @throws NoSuchAlgorithmException
     */
    public DES3Secure() throws NoSuchAlgorithmException {
        super();
    }

    /**
     * 当为密钥接受者（乙方），调用该构造方法。
     *
     * @param secretKey 密钥
     */
    public DES3Secure(String secretKey) {
        super.secretKey = secretKey;
    }


    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        if (secretKey == null || "".equals(secretKey)) {
            throw new CodeException("scretKey need to exists");
        }

        SecretKey deskey = new SecretKeySpec(build3DesKey(secretKey), SecureType.DES3.getType());
        Cipher cipher = Cipher.getInstance(SecureType.DES3.getType());
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        return cipher.doFinal(data);
    }

    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        if (secretKey == null || "".equals(secretKey)) {
            throw new Exception("scretKey need to exists");
        }

        SecretKey deskey = new SecretKeySpec(build3DesKey(secretKey), SecureType.DES3.getType());
        Cipher cipher = Cipher.getInstance(SecureType.DES3.getType());
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        return cipher.doFinal(data);
    }

    public byte[] build3DesKey(String keyStr) throws Exception {
        //3des加密密钥只取前24位，不足24 有wrong key size，大于24位会自动截取，所以这里设置24位
        byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
        byte[] temp = decode(keyStr, getKeyEncType());

        /*
         * 执行数组拷贝
         * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
        if (key.length > temp.length) {
            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

}