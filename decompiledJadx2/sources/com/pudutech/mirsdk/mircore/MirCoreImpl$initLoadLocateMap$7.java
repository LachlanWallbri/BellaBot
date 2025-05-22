package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreImpl$initLoadLocateMap$7 extends Lambda implements Function1<Vector2d, Unit> {
    public static final MirCoreImpl$initLoadLocateMap$7 INSTANCE = new MirCoreImpl$initLoadLocateMap$7();

    MirCoreImpl$initLoadLocateMap$7() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Vector2d vector2d) {
        invoke2(vector2d);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Vector2d it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        MirCoreImpl.INSTANCE.getNavigationer().updateSpeeds$mircore_packRelease(it);
    }
}
