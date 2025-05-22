package com.pudutech.mirsdk.hardware.mcuupdate;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: UpdateMCUTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class UpdateMCUTask$tryTo$2 extends Lambda implements Function1<Byte, String> {
    public static final UpdateMCUTask$tryTo$2 INSTANCE = new UpdateMCUTask$tryTo$2();

    UpdateMCUTask$tryTo$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ String invoke(Byte b) {
        return invoke(b.byteValue());
    }

    public final String invoke(byte b) {
        return String.valueOf(b & 255);
    }
}
