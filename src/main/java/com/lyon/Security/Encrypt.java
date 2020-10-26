package com.lyon.Security;

public class Encrypt {
    static final String SECRET_KEY = "";
    static final String TEMP = "";

    public static String encrypt(String raw) {
        StringBuffer sb = new StringBuffer(raw);
        int sz = raw.length();
        for (int i=0; i<sz; ++i)
            sb.setCharAt(i, TEMP.charAt(SECRET_KEY.indexOf(sb.charAt(i))));
        System.out.println(raw+"->"+sb.toString());
        return sb.toString();
    }
}
