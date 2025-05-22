package com.pudutech.bumblebee.presenter.robot_voices;

import android.media.MediaDataSource;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.cloud.SpeechEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceDataSource.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J*\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u0016\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceDataSource;", "Landroid/media/MediaDataSource;", "()V", "mBuffer", "", "<set-?>", "", "name", "getName", "()Ljava/lang/String;", "close", "", "getSize", "", "getSource", "readAt", "", RequestParameters.POSITION, SpeechEvent.KEY_EVENT_TTS_BUFFER, TypedValues.Cycle.S_WAVE_OFFSET, "size", "setSource", "bytes", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceDataSource extends MediaDataSource {
    private byte[] mBuffer;
    private String name = "";

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public final String getName() {
        return this.name;
    }

    public final void setSource(String name, byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        this.name = name;
        this.mBuffer = bytes;
    }

    /* renamed from: getSource, reason: from getter */
    public final byte[] getMBuffer() {
        return this.mBuffer;
    }

    @Override // android.media.MediaDataSource
    public int readAt(long position, byte[] buffer, int offset, int size) {
        byte[] bArr = this.mBuffer;
        if (bArr == null || position >= bArr.length) {
            return -1;
        }
        long j = size;
        long j2 = position + j;
        if (j2 > bArr.length) {
            size = (int) (j - (j2 - bArr.length));
        }
        System.arraycopy(bArr, (int) position, buffer, offset, size);
        return size;
    }

    @Override // android.media.MediaDataSource
    public long getSize() {
        if (this.mBuffer != null) {
            return r0.length;
        }
        return 0L;
    }
}
