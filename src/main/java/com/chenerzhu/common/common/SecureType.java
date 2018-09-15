package com.chenerzhu.common.common;
/**
 * Created by chenerzhu on 2018/8/2.
 */
public enum SecureType {
    MD5("MD5"),
    RSA("RSA"),
    RSA_PRIVATE("RSA_PRIVATE"),
    RSA_PUBLIC("RSA_PUBLIC"),
    DES("DES"),
    AES("AES"),
    DES3("DESede"),
    SHA("SHA"),
    HmacSHA256("HmacSHA256");

    public enum SignAlgorithm{
        MD5withRSA("MD5withRSA"),
        SHA1WithRSA("SHA1WithRSA"),
        SHA224WithRSA("SHA224WithRSA"),
        SHA256WithRSA("SHA256WithRSA"),
        SHA384WithRSA("SHA384WithRSA"),
        SHA512WithRSA("SHA512WithRSA");
        private String type;

        SignAlgorithm(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }
    }

    private String type;

    SecureType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
