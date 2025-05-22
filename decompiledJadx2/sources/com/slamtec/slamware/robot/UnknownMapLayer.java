package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class UnknownMapLayer extends MapLayer {
    private byte[] rawBody_;

    public byte[] getRawBody() {
        return this.rawBody_;
    }

    public void setRawBody(byte[] bArr) {
        this.rawBody_ = bArr;
    }

    @Override // com.slamtec.slamware.robot.MapLayer
    public void clear() {
        this.rawBody_ = null;
        super.clear();
    }
}
