package com.pudutech.gatecontrollerlib;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.simpleframework.xml.strategy.Name;

/* compiled from: GateControllerMsg.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002()B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010#\u001a\u00020\u0000J\u0018\u0010$\u001a\u00020%2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0006H\u0002J\b\u0010&\u001a\u00020'H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\u001a\u0010\u001a\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0004R\u001a\u0010\u001d\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\nR\u001a\u0010 \u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\n¨\u0006*"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/GateControllerMsg;", "", TypedValues.Attributes.S_FRAME, "", "([B)V", "CMD", "", "getCMD", "()I", "setCMD", "(I)V", "Ctrl", "getCtrl", "setCtrl", "ErrorCode", "Lcom/pudutech/gatecontrollerlib/GateControllerMsg$ErrorType;", "getErrorCode", "()Lcom/pudutech/gatecontrollerlib/GateControllerMsg$ErrorType;", "setErrorCode", "(Lcom/pudutech/gatecontrollerlib/GateControllerMsg$ErrorType;)V", "HEAD", "getFrame", "()[B", Name.LENGTH, "getLength", "setLength", "param", "getParam", "setParam", "seq", "getSeq", "setSeq", "sum", "getSum", "setSum", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "checkSum", "", "toString", "", "ControlCode", "ErrorType", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GateControllerMsg {
    private int CMD;
    private int Ctrl;
    private ErrorType ErrorCode;
    private final byte[] HEAD;
    private final byte[] frame;
    private int length;
    public byte[] param;
    private int seq;
    private int sum;

    /* compiled from: GateControllerMsg.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/GateControllerMsg$ErrorType;", "", "(Ljava/lang/String;I)V", "Success", "FrameHeadError", "CtrlCodeError", "SeqError", "CommandError", "ParamLengthError", "ParamError", "SumLack", "SumError", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum ErrorType {
        Success,
        FrameHeadError,
        CtrlCodeError,
        SeqError,
        CommandError,
        ParamLengthError,
        ParamError,
        SumLack,
        SumError
    }

    public GateControllerMsg(byte[] frame) {
        Intrinsics.checkParameterIsNotNull(frame, "frame");
        this.frame = frame;
        this.ErrorCode = ErrorType.Success;
        this.HEAD = new byte[]{80, 85, 68, 85, 71, 65, 84, 69};
    }

    public final byte[] getFrame() {
        return this.frame;
    }

    /* compiled from: GateControllerMsg.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/GateControllerMsg$ControlCode;", "", "()V", "Command", "", MoveError.LEVEL_ERROR, "Response", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class ControlCode {
        public static final int Command = 0;
        public static final int Error = 248;
        public static final ControlCode INSTANCE = new ControlCode();
        public static final int Response = 192;

        private ControlCode() {
        }
    }

    public final int getCtrl() {
        return this.Ctrl;
    }

    public final void setCtrl(int i) {
        this.Ctrl = i;
    }

    public final ErrorType getErrorCode() {
        return this.ErrorCode;
    }

    public final void setErrorCode(ErrorType errorType) {
        Intrinsics.checkParameterIsNotNull(errorType, "<set-?>");
        this.ErrorCode = errorType;
    }

    public final int getSeq() {
        return this.seq;
    }

    public final void setSeq(int i) {
        this.seq = i;
    }

    public final int getCMD() {
        return this.CMD;
    }

    public final void setCMD(int i) {
        this.CMD = i;
    }

    public final int getLength() {
        return this.length;
    }

    public final void setLength(int i) {
        this.length = i;
    }

    public final byte[] getParam() {
        byte[] bArr = this.param;
        if (bArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("param");
        }
        return bArr;
    }

    public final void setParam(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.param = bArr;
    }

    public final int getSum() {
        return this.sum;
    }

    public final void setSum(int i) {
        this.sum = i;
    }

    public final GateControllerMsg build() {
        byte[] bArr = this.frame;
        if (bArr.length < this.HEAD.length) {
            this.Ctrl = ControlCode.Error;
            this.ErrorCode = ErrorType.FrameHeadError;
            return this;
        }
        int i = 0;
        for (Object obj : ArraysKt.slice(bArr, new IntRange(0, 7))) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((Number) obj).byteValue() != this.HEAD[i]) {
                this.Ctrl = ControlCode.Error;
                this.ErrorCode = ErrorType.FrameHeadError;
                return this;
            }
            i = i2;
        }
        byte[] bArr2 = this.frame;
        if (bArr2.length < 9) {
            this.Ctrl = ControlCode.Error;
            this.ErrorCode = ErrorType.CtrlCodeError;
        } else {
            byte b = bArr2[8];
            if (b == -64) {
                this.Ctrl = 192;
            } else if (b == 0) {
                this.Ctrl = 0;
            } else {
                this.Ctrl = ControlCode.Error;
                this.ErrorCode = ErrorType.CtrlCodeError;
            }
        }
        byte[] bArr3 = this.frame;
        if (bArr3.length < 10) {
            this.Ctrl = ControlCode.Error;
            this.ErrorCode = ErrorType.SeqError;
        } else {
            this.seq = bArr3[9];
        }
        byte[] bArr4 = this.frame;
        if (bArr4.length < 11) {
            this.Ctrl = ControlCode.Error;
            this.ErrorCode = ErrorType.CommandError;
        } else {
            this.CMD = bArr4[10];
        }
        byte[] bArr5 = this.frame;
        if (bArr5.length < 12) {
            this.Ctrl = ControlCode.Error;
            this.ErrorCode = ErrorType.ParamLengthError;
        } else {
            this.length = bArr5[11];
        }
        byte[] bArr6 = this.frame;
        int length = bArr6.length;
        int i3 = this.length;
        if (length < i3 + 12) {
            this.Ctrl = ControlCode.Error;
            this.ErrorCode = ErrorType.ParamError;
        } else {
            this.param = new byte[0];
            Iterator<T> it = ArraysKt.slice(bArr6, new IntRange(12, i3 + 12)).iterator();
            while (it.hasNext()) {
                byte byteValue = ((Number) it.next()).byteValue();
                byte[] bArr7 = this.param;
                if (bArr7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("param");
                }
                ArraysKt.plus(bArr7, byteValue);
            }
        }
        byte[] bArr8 = this.frame;
        int length2 = bArr8.length;
        int i4 = this.length;
        if (length2 < i4 + 13) {
            this.Ctrl = ControlCode.Error;
            this.ErrorCode = ErrorType.SumLack;
        } else {
            this.sum = bArr8[i4 + 12];
            if (!checkSum(bArr8, this.sum)) {
                this.Ctrl = ControlCode.Error;
                this.ErrorCode = ErrorType.SumError;
            }
        }
        return this;
    }

    public String toString() {
        String str = "msg type " + this.Ctrl + "msg error code " + this.ErrorCode;
        Intrinsics.checkExpressionValueIsNotNull(str, "ret.toString()");
        return str;
    }

    private final boolean checkSum(byte[] frame, int sum) {
        int length = frame.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            byte b = frame[i];
            int i4 = i2 + 1;
            if (i2 != frame.length - 1) {
                i3 = Integer.remainderUnsigned(UInt.m4595constructorimpl(i3 + UInt.m4595constructorimpl(b)), 256);
            }
            i++;
            i2 = i4;
        }
        if (sum < 0) {
            sum += 256;
        }
        return i3 == UInt.m4595constructorimpl(sum);
    }
}
