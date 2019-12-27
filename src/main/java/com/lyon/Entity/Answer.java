package com.lyon.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "answer")
public class Answer implements Serializable {
    @Id
    @Column(name = "a_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;    // 解答id
    @Column(name = "q_id")
    private long qid;   // 关联提问id
    @Column(name = "teacher_id")
    private long tid;   // 发出回答的用户id
    @Column(name = "content")
    private String content; // 回答内容
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "img", columnDefinition = "MEDIUMTEXT")
    private String img; // 配图
    @Column(name = "date")
    private String date;    // 回答时间
    @Column(name = "read_by_student")
    private boolean read;   // 是否被学生读过

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQid() {
        return qid;
    }

    public void setQid(long qid) {
        this.qid = qid;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
