package com.lyon.Entity;

import java.io.Serializable;

public class QuestionAuthPK implements Serializable {
    private long tid;
    private long sid;

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }
}
