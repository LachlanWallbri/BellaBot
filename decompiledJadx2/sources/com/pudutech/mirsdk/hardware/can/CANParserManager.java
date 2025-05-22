package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: CANParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\rH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J \u0010\u0018\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\rø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJP\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000726\u0010\u001d\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0018\u0010 \u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u008d\u0001\u0010\u0005\u001a~\u0012\u0004\u0012\u00020\u0007\u00124\u00122\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\b0\u0006j>\u0012\u0004\u0012\u00020\u0007\u00124\u00122\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\b`\u0010X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R!\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0012j\b\u0012\u0004\u0012\u00020\u0007`\u0013X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/can/CANParserManager;", "", "()V", "TAG", "", "canParsers", "Ljava/util/HashMap;", "Lkotlin/UByte;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "id", "Lkotlin/UByteArray;", "byteArray", "", "Lkotlin/collections/HashMap;", "checkValideBlackList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "checkValid", "", "checkValid-GBYM_sE", "([B)Z", "parse", "parse-eUNIVaU", "(I[B)V", "regCanParser", "protocol", "parser", "regCanParser-eLRuwBU", "(BLkotlin/jvm/functions/Function2;)V", "unRegCanParser", "unRegCanParser-7apg3OU", "(B)V", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CANParserManager {
    private final String TAG = "CAN";
    private final HashMap<UByte, Function2<Integer, UByteArray, Unit>> canParsers = new HashMap<>();
    private final ArrayList<UByte> checkValideBlackList = CollectionsKt.arrayListOf(UByte.m4522boximpl((byte) 96), UByte.m4522boximpl((byte) 97), UByte.m4522boximpl((byte) 98), UByte.m4522boximpl((byte) 99), UByte.m4522boximpl((byte) 100), UByte.m4522boximpl((byte) 101));

    /* renamed from: checkValid-GBYM_sE, reason: not valid java name */
    private final boolean m4426checkValidGBYM_sE(byte[] byteArray) {
        return true;
    }

    /* renamed from: regCanParser-eLRuwBU, reason: not valid java name */
    public final void m4428regCanParsereLRuwBU(byte protocol, Function2<? super Integer, ? super UByteArray, Unit> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        this.canParsers.put(UByte.m4522boximpl(protocol), parser);
    }

    /* renamed from: unRegCanParser-7apg3OU, reason: not valid java name */
    public final void m4429unRegCanParser7apg3OU(byte protocol) {
        this.canParsers.remove(UByte.m4522boximpl(protocol));
    }

    /* renamed from: parse-eUNIVaU, reason: not valid java name */
    public final void m4427parseeUNIVaU(int id, byte[] byteArray) {
        Function2<Integer, UByteArray, Unit> function2;
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        if (UByteArray.m4578getSizeimpl(byteArray) != 8) {
            Pdlog.m3277w(this.TAG, "parse can data size error:" + UByteArray.m4578getSizeimpl(byteArray));
            return;
        }
        if ((this.checkValideBlackList.contains(UByte.m4522boximpl(UByteArray.m4577getimpl(byteArray, 0))) || m4426checkValidGBYM_sE(byteArray)) && (function2 = this.canParsers.get(UByte.m4522boximpl(UByteArray.m4577getimpl(byteArray, 0)))) != null) {
            function2.invoke(Integer.valueOf(id), UByteArray.m4570boximpl(byteArray));
        }
    }
}
