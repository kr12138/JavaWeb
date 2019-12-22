package com.lyon.Entity;

import java.io.Serializable;
import java.util.Objects;

public class TeachesPK implements Serializable {
    private long uid;
    private long cid;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Teaches that = (Teaches) o;
//        return uid == that.getUid() && cid == that.getCid() ;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(cid, uid);
//    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }
}