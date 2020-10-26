package com.lyon.Security;


public class Decrypt {
    static final String SECRET_KEY = "";
    static final String TEMP = "";

    public static String decrypt(String raw) {
        StringBuffer sb = new StringBuffer(raw);
//        int sz = raw.length();
        for (int i=0; i<5; ++i)
            sb.setCharAt(i, SECRET_KEY.charAt(TEMP.indexOf(sb.charAt(i))));
        return sb.toString().substring(0,5);
    }
}
