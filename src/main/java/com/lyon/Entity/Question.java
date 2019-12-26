package com.lyon.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "question")
public class Question implements Serializable {
    @Id
    @Column(name = "q_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;    // 问题id
    @Column(name = "c_id")
    private long cid;   // 关联课程id
    @Column(name = "student_id")
    private long sid;   // 提出问题的学生的用户id
    @Column(name = "teacher_id")
    private long tid;   // 教这门课的教师的用户id
    @Column(name = "title")
    private String title;   // 问题标题
    @Column(name = "content")
    private String content; // 问题内容
    @Column(name = "img_id")
    private long iid;   // 配图id
    @Column(name = "date")
    private String date;    // 提问时间
    @Column(name = "read_by_teacher")
    private boolean read;   // 是否被教师读过

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
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
