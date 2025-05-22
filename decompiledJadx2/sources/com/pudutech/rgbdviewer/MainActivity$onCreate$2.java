package com.pudutech.rgbdviewer;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.recycle.robot.p064ui.widget.CFullScreenConfirmDialogFragment;
import com.pudutech.rgbdviewer.RGBDView;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: MainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "resultData", "", SpeechUtility.TAG_RESOURCE_RESULT, "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class MainActivity$onCreate$2 extends Lambda implements Function2<byte[], Boolean, Unit> {
    final /* synthetic */ Ref.ObjectRef $ctx;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onCreate$2(MainActivity mainActivity, Ref.ObjectRef objectRef) {
        super(2);
        this.this$0 = mainActivity;
        this.$ctx = objectRef;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr, Boolean bool) {
        invoke(bArr, bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(final byte[] resultData, boolean z) {
        String str;
        Intrinsics.checkParameterIsNotNull(resultData, "resultData");
        try {
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$2.1
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.access$getDownRGBDCheckBitmap$p(MainActivity$onCreate$2.this.this$0).copyPixelsFromBuffer(ByteBuffer.wrap(resultData).rewind());
                    ((ImageView) MainActivity$onCreate$2.this.this$0.findViewById(2131230869)).setImageBitmap(MainActivity.access$getDownRGBDCheckBitmap$p(MainActivity$onCreate$2.this.this$0));
                    View findViewById = MainActivity$onCreate$2.this.this$0.findViewById(2131230868);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<Button>(R.id.downRGBDCheckOK)");
                    ((Button) findViewById).setVisibility(0);
                }
            });
            if (z && this.this$0.downRgbdConfigExist()) {
                MainActivity.access$getRgbdSensor$p(this.this$0).setDownRgbdCheck(false);
                this.this$0.runOnUiThread(new RunnableC56872());
            }
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3274e(str, "exception: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.rgbdviewer.MainActivity$onCreate$2$2 */
    /* loaded from: classes.dex */
    public static final class RunnableC56872 implements Runnable {
        RunnableC56872() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            CFullScreenConfirmDialogFragment.Builder builder = new CFullScreenConfirmDialogFragment.Builder();
            String string = MainActivity$onCreate$2.this.this$0.getResources().getString(2131558436);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…ng.calibration_completed)");
            CFullScreenConfirmDialogFragment.Builder tips = builder.setTips(string);
            String string2 = MainActivity$onCreate$2.this.this$0.getResources().getString(2131558457);
            Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.string.main_ok)");
            CFullScreenConfirmDialogFragment.Builder positiveButton = tips.setPositiveButton(string2, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$2$2$dialogFragment$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity$onCreate$2.this.this$0.jump2Robot((MainActivity) MainActivity$onCreate$2.this.$ctx.element);
                }
            });
            String string3 = MainActivity$onCreate$2.this.this$0.getResources().getString(2131558452);
            Intrinsics.checkExpressionValueIsNotNull(string3, "resources.getString(R.string.main_cancel)");
            positiveButton.setNegativeButton(string3, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$2$2$dialogFragment$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.access$getRgbdSensor$p(MainActivity$onCreate$2.this.this$0).stopDownRGBDCheck();
                    MainActivity$onCreate$2.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$2$2$dialogFragment$2.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ((ImageView) MainActivity$onCreate$2.this.this$0.findViewById(2131230869)).setImageDrawable(null);
                            View findViewById = MainActivity$onCreate$2.this.this$0.findViewById(2131230867);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<LinearLayou…R.id.downRGBDCheckLayout)");
                            ((LinearLayout) findViewById).setVisibility(8);
                            View findViewById2 = MainActivity$onCreate$2.this.this$0.findViewById(2131230923);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById<LinearLayout>(R.id.mainLayout)");
                            ((LinearLayout) findViewById2).setVisibility(0);
                            View findViewById3 = MainActivity$onCreate$2.this.this$0.findViewById(2131230868);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById<Button>(R.id.downRGBDCheckOK)");
                            ((Button) findViewById3).setVisibility(8);
                            MainActivity.access$getCenterView$p(MainActivity$onCreate$2.this.this$0).setState(MainActivity$onCreate$2.this.this$0.downRgbdConfigExist() ? RGBDView.ConfigState.Configured : RGBDView.ConfigState.NotConfigured);
                        }
                    });
                }
            }).setBackgroundColor(Color.parseColor("#30000000")).build().show(MainActivity$onCreate$2.this.this$0.getSupportFragmentManager(), MainActivity.class.getSimpleName());
        }
    }
}
