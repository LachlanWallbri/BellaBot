package com.pudutech.peanut.robot_ui.bluetooth.bt;

/* loaded from: classes5.dex */
public class BtMsgReadEvent {
    public byte[] buffer;
    public int bytes;
    public String message;

    public BtMsgReadEvent(int i, byte[] bArr) {
        this.buffer = bArr;
        this.bytes = i;
        this.message = new String(bArr, 0, i);
    }
}
