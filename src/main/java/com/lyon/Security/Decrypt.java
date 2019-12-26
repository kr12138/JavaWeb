package com.lyon.Security;


public class Decrypt {
    static String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String t = "qw5ertyui1opasdf3ghj6klzxcv0bnmMN8BVCXZLKJ2HGFD9SAP4OI7UYTREWQ";

    // 对字符串解密，返回用户权限，暂定为凯撒法
    static String decrypt(String raw) {
        StringBuffer sb = new StringBuffer(raw);
//        int sz = raw.length();
        for (int i=0; i<5; ++i)
            sb.setCharAt(i, s.charAt(t.indexOf(sb.charAt(i))));
        return sb.toString().substring(0,5);
    }
}
