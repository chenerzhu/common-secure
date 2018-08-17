package com.chenerzhu.common.secure;

import com.chenerzhu.common.common.SecureType;

import java.security.MessageDigest;

/**
 * MD5 单向加密
 *
 */
public class MD5Secure extends BaseSecure
{
	@Override
	public byte[] encrypt(byte[] data) throws Exception
	{
		MessageDigest messageDigest = MessageDigest.getInstance(SecureType.MD5.getType());
		return messageDigest.digest(data);
	}

    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        return null;
    }
}
