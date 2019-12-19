package com.lyon.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dept")
public class Dept implements Serializable {

    @Id
    @Column(name = "d_id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "info")
    private String info;

//    public long getD_id() {
//        return d_id;
//    }
//
//    public void setD_id(long d_id) {
//        this.d_id = d_id;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
