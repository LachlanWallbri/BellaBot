package com.pudutech.mirsdk.mircore;

import com.iflytek.cloud.SpeechUtility;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", SpeechUtility.TAG_RESOURCE_RESULT, "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreBinder$addInteractionListener$1 extends Lambda implements Function1<byte[], Unit> {
    public static final MirCoreBinder$addInteractionListener$1 INSTANCE = new MirCoreBinder$addInteractionListener$1();

    MirCoreBinder$addInteractionListener$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
        invoke2(bArr);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final byte[] result) {
        Intrinsics.checkParameterIsNotNull(result, "result");
        MirCoreImpl.INSTANCE.getInteractionListener$mircore_packRelease().notify(new Function2<InteractionListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addInteractionListener$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(InteractionListener interactionListener, String str) {
                invoke2(interactionListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InteractionListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onDetection(result);
            }
        });
    }
}
