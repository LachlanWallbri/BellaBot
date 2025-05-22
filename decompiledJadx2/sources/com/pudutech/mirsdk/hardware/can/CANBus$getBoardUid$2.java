package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$getBoardUid$2 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ Ref.IntRef $len;
    final /* synthetic */ Ref.ObjectRef $mainBoardSn;
    final /* synthetic */ List $snByteList;
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$getBoardUid$2(CANBus cANBus, Ref.IntRef intRef, List list, Ref.ObjectRef objectRef) {
        super(2);
        this.this$0 = cANBus;
        this.$len = intRef;
        this.$snByteList = list;
        this.$mainBoardSn = objectRef;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [T, java.lang.String] */
    public final void invoke(int i, byte[] bytes) {
        String str;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        int m4577getimpl = UByteArray.m4577getimpl(bytes, 1) & 255;
        if (m4577getimpl == 0 && UByteArray.m4577getimpl(bytes, 2) == UByte.m4528constructorimpl((byte) 0) && UByteArray.m4577getimpl(bytes, 3) == UByte.m4528constructorimpl((byte) 80)) {
            this.$len.element = UByteArray.m4577getimpl(bytes, 7) & 255;
        }
        if (m4577getimpl > 0) {
            this.$snByteList.add(Byte.valueOf(UByteArray.m4577getimpl(bytes, 2)));
            this.$snByteList.add(Byte.valueOf(UByteArray.m4577getimpl(bytes, 3)));
            this.$snByteList.add(Byte.valueOf(UByteArray.m4577getimpl(bytes, 4)));
            this.$snByteList.add(Byte.valueOf(UByteArray.m4577getimpl(bytes, 5)));
        }
        if (this.$snByteList.size() > this.$len.element) {
            List subList = this.$snByteList.subList(0, this.$len.element);
            this.$mainBoardSn.element = new String(CollectionsKt.toByteArray(subList), Charsets.UTF_8);
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "getBoardUid:" + CommonKt.toHexString(CollectionsKt.toByteArray(subList)));
        }
    }
}
