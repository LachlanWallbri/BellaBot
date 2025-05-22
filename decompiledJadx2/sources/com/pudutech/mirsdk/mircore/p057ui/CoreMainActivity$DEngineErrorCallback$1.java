package com.pudutech.mirsdk.mircore.p057ui;

import android.widget.TextView;
import com.pudutech.mirsdk.mircore.C5224R;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: CoreMainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class CoreMainActivity$DEngineErrorCallback$1 implements Runnable {
    final /* synthetic */ int $error_code;
    final /* synthetic */ CoreMainActivity this$0;

    CoreMainActivity$DEngineErrorCallback$1(CoreMainActivity coreMainActivity, int i) {
        this.this$0 = coreMainActivity;
        this.$error_code = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TextView face_result_txt = (TextView) this.this$0._$_findCachedViewById(C5224R.id.face_result_txt);
        Intrinsics.checkExpressionValueIsNotNull(face_result_txt, "face_result_txt");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(this.$error_code), Integer.valueOf(CoreMainActivity.INSTANCE.getError_count())};
        String format = String.format(" DengineError: %d count: %d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        face_result_txt.setText(format);
    }
}
