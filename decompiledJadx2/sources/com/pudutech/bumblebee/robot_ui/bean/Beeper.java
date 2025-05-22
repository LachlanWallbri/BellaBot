package com.pudutech.bumblebee.robot_ui.bean;

import java.util.Objects;

/* loaded from: classes2.dex */
public class Beeper {

    /* renamed from: id */
    private Long f4839id;
    private String mac;

    public Beeper(Long l, String str) {
        this.f4839id = l;
        this.mac = str;
    }

    public Beeper() {
    }

    public Long getId() {
        return this.f4839id;
    }

    public void setId(Long l) {
        this.f4839id = l;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.mac, ((Beeper) obj).mac);
    }

    public int hashCode() {
        return Objects.hash(this.mac);
    }

    public String toString() {
        return "Beeper{id=" + this.f4839id + ", mac='" + this.mac + "'}";
    }
}
