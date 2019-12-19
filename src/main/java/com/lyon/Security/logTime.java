package com.lyon.Security;

import java.text.SimpleDateFormat;
import java.util.Date;

public class logTime {
    public static String now() {
        return new SimpleDateFormat(" yy-MM-dd HH:mm:ss").format(new Date());
    }
}
