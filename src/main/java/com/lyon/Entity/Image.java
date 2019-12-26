package com.lyon.Entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "img")
public class Image implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "i_id")
    private long iid;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "img", columnDefinition = "MEDIUMTEXT")
    private String img;

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
