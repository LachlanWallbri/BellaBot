package com.pudutech.rgbdlib;

import kotlin.Metadata;

/* compiled from: RGBDSensor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/rgbdlib/RobotType;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "Hls", "Bellabot", "RecycleDog", "Ninetales", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public enum RobotType {
    Hls(1),
    Bellabot(2),
    RecycleDog(3),
    Ninetales(4);

    private final int id;

    RobotType(int i) {
        this.id = i;
    }

    public final int getId() {
        return this.id;
    }
}
