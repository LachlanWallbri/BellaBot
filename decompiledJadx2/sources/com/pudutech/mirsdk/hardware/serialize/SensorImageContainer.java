package com.pudutech.mirsdk.hardware.serialize;

import java.io.FileDescriptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: SensorImageContainer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/SensorImageContainer;", "", "rows", "", "cols", "memorySize", "elementSize", "fileDescriptor", "Ljava/io/FileDescriptor;", "(IIIILjava/io/FileDescriptor;)V", "getCols", "()I", "setCols", "(I)V", "getElementSize", "setElementSize", "getFileDescriptor", "()Ljava/io/FileDescriptor;", "setFileDescriptor", "(Ljava/io/FileDescriptor;)V", "getMemorySize", "setMemorySize", "getRows", "setRows", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SensorImageContainer {
    private int cols;
    private int elementSize;
    private FileDescriptor fileDescriptor;
    private int memorySize;
    private int rows;

    public SensorImageContainer(int i, int i2, int i3, int i4, FileDescriptor fileDescriptor) {
        Intrinsics.checkParameterIsNotNull(fileDescriptor, "fileDescriptor");
        this.rows = i;
        this.cols = i2;
        this.memorySize = i3;
        this.elementSize = i4;
        this.fileDescriptor = fileDescriptor;
    }

    public final int getCols() {
        return this.cols;
    }

    public final int getElementSize() {
        return this.elementSize;
    }

    public final FileDescriptor getFileDescriptor() {
        return this.fileDescriptor;
    }

    public final int getMemorySize() {
        return this.memorySize;
    }

    public final int getRows() {
        return this.rows;
    }

    public final void setCols(int i) {
        this.cols = i;
    }

    public final void setElementSize(int i) {
        this.elementSize = i;
    }

    public final void setFileDescriptor(FileDescriptor fileDescriptor) {
        Intrinsics.checkParameterIsNotNull(fileDescriptor, "<set-?>");
        this.fileDescriptor = fileDescriptor;
    }

    public final void setMemorySize(int i) {
        this.memorySize = i;
    }

    public final void setRows(int i) {
        this.rows = i;
    }
}
