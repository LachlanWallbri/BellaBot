package com.pudutech.mirsdk.hardware.serialize;

import java.io.FileDescriptor;

import kotlin.jvm.internal.Intrinsics;

public final class SensorImageContainer {
    private int cols;
    private int elementSize;
    private FileDescriptor fileDescriptor;
    private int memorySize;
    private int rows;

    public SensorImageContainer(int rows, int cols, int memSize, int elementSize, FileDescriptor fileDescriptor) {
        Intrinsics.checkParameterIsNotNull(fileDescriptor, "fileDescriptor");
        this.rows = rows;
        this.cols = cols;
        this.memorySize = memSize;
        this.elementSize = elementSize;
        this.fileDescriptor = fileDescriptor;
    }

    public int getCols() {
        return this.cols;
    }

    public int getElementSize() {
        return this.elementSize;
    }

    public FileDescriptor getFileDescriptor() {
        return this.fileDescriptor;
    }

    public int getMemorySize() {
        return this.memorySize;
    }

    public int getRows() {
        return this.rows;
    }

    public void setCols(int i) {
        this.cols = i;
    }

    public void setElementSize(int i) {
        this.elementSize = i;
    }

    public void setFileDescriptor(FileDescriptor fileDescriptor) {
        Intrinsics.checkParameterIsNotNull(fileDescriptor, "<set-?>");
        this.fileDescriptor = fileDescriptor;
    }

    public void setMemorySize(int i) {
        this.memorySize = i;
    }

    public void setRows(int i) {
        this.rows = i;
    }
}
