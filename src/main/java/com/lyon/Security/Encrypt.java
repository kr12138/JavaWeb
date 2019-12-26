package com.lyon.Security;

public class Encrypt {
    static String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String t = "qw5ertyui1opasdf3ghj6klzxcv0bnmMN8BVCXZLKJ2HGFD9SAP4OI7UYTREWQ";

    // 对字符串加密，暂定凯撒法
    static String encrypt(String raw) {
        StringBuffer sb = new StringBuffer(raw);
        int sz = raw.length();
        for (int i=0; i<sz; ++i)
            sb.setCharAt(i, t.charAt(s.indexOf(sb.charAt(i))));
        return sb.toString();
    }
}
