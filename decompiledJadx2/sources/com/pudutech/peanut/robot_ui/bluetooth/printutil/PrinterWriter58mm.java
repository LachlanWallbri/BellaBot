package com.pudutech.peanut.robot_ui.bluetooth.printutil;

import java.io.IOException;

/* loaded from: classes5.dex */
public class PrinterWriter58mm extends PrinterWriter {
    public static final int TYPE_58 = 58;
    public int width;

    @Override // com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterWriter
    protected int getLineStringWidth(int i) {
        return i != 1 ? 31 : 15;
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterWriter
    protected int getLineWidth() {
        return 16;
    }

    public PrinterWriter58mm() throws IOException {
        this.width = 380;
    }

    public PrinterWriter58mm(int i) throws IOException {
        super(i);
        this.width = 380;
    }

    public PrinterWriter58mm(int i, int i2) throws IOException {
        super(i);
        this.width = 380;
        this.width = i2;
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterWriter
    protected int getDrawableMaxWidth() {
        return this.width;
    }
}
