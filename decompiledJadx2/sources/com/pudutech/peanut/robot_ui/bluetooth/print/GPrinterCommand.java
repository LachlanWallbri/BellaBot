package com.pudutech.peanut.robot_ui.bluetooth.print;

import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;

/* loaded from: classes5.dex */
public class GPrinterCommand {
    public static final byte[] left = {27, 97, 0};
    public static final byte[] center = {27, 97, 1};
    public static final byte[] right = {27, 97, 2};
    public static final byte[] bold = {27, 69, 1};
    public static final byte[] bold_cancel = {27, 69, 0};
    public static final byte[] text_normal_size = {29, BinaryMemcacheOpcodes.SASL_AUTH, 0};
    public static final byte[] text_big_height = {27, BinaryMemcacheOpcodes.SASL_AUTH, 16};
    public static final byte[] text_big_size = {29, BinaryMemcacheOpcodes.SASL_AUTH, 17};
    public static final byte[] reset = {27, 64};
    public static final byte[] print = {10};
    public static final byte[] under_line = {27, 45, 2};
    public static final byte[] under_line_cancel = {27, 45, 0};

    public static byte[] move(byte b, byte b2) {
        return new byte[]{29, 80, b, b2};
    }

    public static byte[] walkPaper(byte b) {
        return new byte[]{27, 100, b};
    }
}
