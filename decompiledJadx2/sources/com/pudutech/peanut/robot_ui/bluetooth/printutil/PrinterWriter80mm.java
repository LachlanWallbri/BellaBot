package com.pudutech.peanut.robot_ui.bluetooth.printutil;

import java.io.IOException;

/* loaded from: classes5.dex */
public class PrinterWriter80mm extends PrinterWriter {
    public static final int TYPE_80 = 80;
    public int width;

    @Override // com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterWriter
    protected int getLineStringWidth(int i) {
        return i != 1 ? 47 : 23;
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterWriter
    protected int getLineWidth() {
        return 24;
    }

    public PrinterWriter80mm() throws IOException {
        this.width = 500;
    }

    public PrinterWriter80mm(int i) throws IOException {
        super(i);
        this.width = 500;
    }

    public PrinterWriter80mm(int i, int i2) throws IOException {
        super(i);
        this.width = 500;
        this.width = i2;
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterWriter
    protected int getDrawableMaxWidth() {
        return this.width;
    }
}
