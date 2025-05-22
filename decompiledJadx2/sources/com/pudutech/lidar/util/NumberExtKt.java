package com.pudutech.lidar.util;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NumberExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\f\"\u0019\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0003\u0010\u0004¨\u0006\r"}, m3961d2 = {"BYTE_CHARS", "", "", "getBYTE_CHARS", "()[[C", "[[C", "byteToInt", "", "", "shlByte", "toHexString", "", "", "LidarLib_mirRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class NumberExtKt {
    private static final char[][] BYTE_CHARS = {new char[]{'0', '0'}, new char[]{'0', '1'}, new char[]{'0', '2'}, new char[]{'0', '3'}, new char[]{'0', '4'}, new char[]{'0', '5'}, new char[]{'0', '6'}, new char[]{'0', '7'}, new char[]{'0', '8'}, new char[]{'0', '9'}, new char[]{'0', 'a'}, new char[]{'0', 'b'}, new char[]{'0', 'c'}, new char[]{'0', 'd'}, new char[]{'0', 'e'}, new char[]{'0', 'f'}, new char[]{'1', '0'}, new char[]{'1', '1'}, new char[]{'1', '2'}, new char[]{'1', '3'}, new char[]{'1', '4'}, new char[]{'1', '5'}, new char[]{'1', '6'}, new char[]{'1', '7'}, new char[]{'1', '8'}, new char[]{'1', '9'}, new char[]{'1', 'a'}, new char[]{'1', 'b'}, new char[]{'1', 'c'}, new char[]{'1', 'd'}, new char[]{'1', 'e'}, new char[]{'1', 'f'}, new char[]{'2', '0'}, new char[]{'2', '1'}, new char[]{'2', '2'}, new char[]{'2', '3'}, new char[]{'2', '4'}, new char[]{'2', '5'}, new char[]{'2', '6'}, new char[]{'2', '7'}, new char[]{'2', '8'}, new char[]{'2', '9'}, new char[]{'2', 'a'}, new char[]{'2', 'b'}, new char[]{'2', 'c'}, new char[]{'2', 'd'}, new char[]{'2', 'e'}, new char[]{'2', 'f'}, new char[]{'3', '0'}, new char[]{'3', '1'}, new char[]{'3', '2'}, new char[]{'3', '3'}, new char[]{'3', '4'}, new char[]{'3', '5'}, new char[]{'3', '6'}, new char[]{'3', '7'}, new char[]{'3', '8'}, new char[]{'3', '9'}, new char[]{'3', 'a'}, new char[]{'3', 'b'}, new char[]{'3', 'c'}, new char[]{'3', 'd'}, new char[]{'3', 'e'}, new char[]{'3', 'f'}, new char[]{'4', '0'}, new char[]{'4', '1'}, new char[]{'4', '2'}, new char[]{'4', '3'}, new char[]{'4', '4'}, new char[]{'4', '5'}, new char[]{'4', '6'}, new char[]{'4', '7'}, new char[]{'4', '8'}, new char[]{'4', '9'}, new char[]{'4', 'a'}, new char[]{'4', 'b'}, new char[]{'4', 'c'}, new char[]{'4', 'd'}, new char[]{'4', 'e'}, new char[]{'4', 'f'}, new char[]{'5', '0'}, new char[]{'5', '1'}, new char[]{'5', '2'}, new char[]{'5', '3'}, new char[]{'5', '4'}, new char[]{'5', '5'}, new char[]{'5', '6'}, new char[]{'5', '7'}, new char[]{'5', '8'}, new char[]{'5', '9'}, new char[]{'5', 'a'}, new char[]{'5', 'b'}, new char[]{'5', 'c'}, new char[]{'5', 'd'}, new char[]{'5', 'e'}, new char[]{'5', 'f'}, new char[]{'6', '0'}, new char[]{'6', '1'}, new char[]{'6', '2'}, new char[]{'6', '3'}, new char[]{'6', '4'}, new char[]{'6', '5'}, new char[]{'6', '6'}, new char[]{'6', '7'}, new char[]{'6', '8'}, new char[]{'6', '9'}, new char[]{'6', 'a'}, new char[]{'6', 'b'}, new char[]{'6', 'c'}, new char[]{'6', 'd'}, new char[]{'6', 'e'}, new char[]{'6', 'f'}, new char[]{'7', '0'}, new char[]{'7', '1'}, new char[]{'7', '2'}, new char[]{'7', '3'}, new char[]{'7', '4'}, new char[]{'7', '5'}, new char[]{'7', '6'}, new char[]{'7', '7'}, new char[]{'7', '8'}, new char[]{'7', '9'}, new char[]{'7', 'a'}, new char[]{'7', 'b'}, new char[]{'7', 'c'}, new char[]{'7', 'd'}, new char[]{'7', 'e'}, new char[]{'7', 'f'}, new char[]{'8', '0'}, new char[]{'8', '1'}, new char[]{'8', '2'}, new char[]{'8', '3'}, new char[]{'8', '4'}, new char[]{'8', '5'}, new char[]{'8', '6'}, new char[]{'8', '7'}, new char[]{'8', '8'}, new char[]{'8', '9'}, new char[]{'8', 'a'}, new char[]{'8', 'b'}, new char[]{'8', 'c'}, new char[]{'8', 'd'}, new char[]{'8', 'e'}, new char[]{'8', 'f'}, new char[]{'9', '0'}, new char[]{'9', '1'}, new char[]{'9', '2'}, new char[]{'9', '3'}, new char[]{'9', '4'}, new char[]{'9', '5'}, new char[]{'9', '6'}, new char[]{'9', '7'}, new char[]{'9', '8'}, new char[]{'9', '9'}, new char[]{'9', 'a'}, new char[]{'9', 'b'}, new char[]{'9', 'c'}, new char[]{'9', 'd'}, new char[]{'9', 'e'}, new char[]{'9', 'f'}, new char[]{'a', '0'}, new char[]{'a', '1'}, new char[]{'a', '2'}, new char[]{'a', '3'}, new char[]{'a', '4'}, new char[]{'a', '5'}, new char[]{'a', '6'}, new char[]{'a', '7'}, new char[]{'a', '8'}, new char[]{'a', '9'}, new char[]{'a', 'a'}, new char[]{'a', 'b'}, new char[]{'a', 'c'}, new char[]{'a', 'd'}, new char[]{'a', 'e'}, new char[]{'a', 'f'}, new char[]{'b', '0'}, new char[]{'b', '1'}, new char[]{'b', '2'}, new char[]{'b', '3'}, new char[]{'b', '4'}, new char[]{'b', '5'}, new char[]{'b', '6'}, new char[]{'b', '7'}, new char[]{'b', '8'}, new char[]{'b', '9'}, new char[]{'b', 'a'}, new char[]{'b', 'b'}, new char[]{'b', 'c'}, new char[]{'b', 'd'}, new char[]{'b', 'e'}, new char[]{'b', 'f'}, new char[]{'c', '0'}, new char[]{'c', '1'}, new char[]{'c', '2'}, new char[]{'c', '3'}, new char[]{'c', '4'}, new char[]{'c', '5'}, new char[]{'c', '6'}, new char[]{'c', '7'}, new char[]{'c', '8'}, new char[]{'c', '9'}, new char[]{'c', 'a'}, new char[]{'c', 'b'}, new char[]{'c', 'c'}, new char[]{'c', 'd'}, new char[]{'c', 'e'}, new char[]{'c', 'f'}, new char[]{'d', '0'}, new char[]{'d', '1'}, new char[]{'d', '2'}, new char[]{'d', '3'}, new char[]{'d', '4'}, new char[]{'d', '5'}, new char[]{'d', '6'}, new char[]{'d', '7'}, new char[]{'d', '8'}, new char[]{'d', '9'}, new char[]{'d', 'a'}, new char[]{'d', 'b'}, new char[]{'d', 'c'}, new char[]{'d', 'd'}, new char[]{'d', 'e'}, new char[]{'d', 'f'}, new char[]{'e', '0'}, new char[]{'e', '1'}, new char[]{'e', '2'}, new char[]{'e', '3'}, new char[]{'e', '4'}, new char[]{'e', '5'}, new char[]{'e', '6'}, new char[]{'e', '7'}, new char[]{'e', '8'}, new char[]{'e', '9'}, new char[]{'e', 'a'}, new char[]{'e', 'b'}, new char[]{'e', 'c'}, new char[]{'e', 'd'}, new char[]{'e', 'e'}, new char[]{'e', 'f'}, new char[]{'f', '0'}, new char[]{'f', '1'}, new char[]{'f', '2'}, new char[]{'f', '3'}, new char[]{'f', '4'}, new char[]{'f', '5'}, new char[]{'f', '6'}, new char[]{'f', '7'}, new char[]{'f', '8'}, new char[]{'f', '9'}, new char[]{'f', 'a'}, new char[]{'f', 'b'}, new char[]{'f', 'c'}, new char[]{'f', 'd'}, new char[]{'f', 'e'}, new char[]{'f', 'f'}};

    public static final int byteToInt(byte b, int i) {
        return (b & 255) << (i * 8);
    }

    public static final char[][] getBYTE_CHARS() {
        return BYTE_CHARS;
    }

    public static /* synthetic */ int byteToInt$default(byte b, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return byteToInt(b, i);
    }

    public static final String toHexString(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        char[] cArr = new char[toHexString.length * 3];
        int length = toHexString.length;
        for (int i = 0; i < length; i++) {
            char[] cArr2 = BYTE_CHARS[UByte.m4528constructorimpl(toHexString[i]) & 255];
            int i2 = i * 3;
            cArr[i2] = cArr2[0];
            cArr[i2 + 1] = cArr2[1];
            cArr[i2 + 2] = ' ';
        }
        return new String(cArr);
    }
}
