package com.lyon.Entity;

import javax.persistence.*;

@Entity
@Table(name = "questionauth")
@IdClass(QuestionAuthPK.class)
public class QuestionAuth {
    @Id
    @Column(name = "t_id")
    private long tid;
    @Id
    @Column(name = "s_id")
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
