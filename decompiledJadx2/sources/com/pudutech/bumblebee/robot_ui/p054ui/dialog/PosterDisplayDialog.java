package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceManager;
import com.pudutech.disinfect.baselib.imageLoader.ImageLoader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PosterDisplayDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u0016\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/PosterDisplayDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mPic", "Landroid/widget/ImageView;", "onClose", "Lkotlin/Function0;", "", "getOnClose", "()Lkotlin/jvm/functions/Function0;", "setOnClose", "(Lkotlin/jvm/functions/Function0;)V", "getLayoutId", "", "initView", "view", "Landroid/view/View;", "isOpenReset", "", "setDisplayData", "imgUrl", "", "text", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PosterDisplayDialog extends BumbleBaseDialog {
    private ImageView mPic;
    private Function0<Unit> onClose;

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    protected boolean isOpenReset() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PosterDisplayDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final Function0<Unit> getOnClose() {
        return this.onClose;
    }

    public final void setOnClose(Function0<Unit> function0) {
        this.onClose = function0;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_poster_display;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initView(view);
        this.mPic = (ImageView) view.findViewById(C4188R.id.dialog_poster_pic);
    }

    public final void setDisplayData(String imgUrl, String text) {
        Intrinsics.checkParameterIsNotNull(imgUrl, "imgUrl");
        Intrinsics.checkParameterIsNotNull(text, "text");
        ImageLoader.getInstance().displayImg(getMContext(), imgUrl, this.mPic);
        TtsVoiceManager.playTtsVoice$default(TtsVoiceManager.INSTANCE, text, false, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PosterDisplayDialog$setDisplayData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ImageView imageView;
                imageView = PosterDisplayDialog.this.mPic;
                if (imageView != null) {
                    imageView.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PosterDisplayDialog$setDisplayData$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Function0<Unit> onClose = PosterDisplayDialog.this.getOnClose();
                            if (onClose != null) {
                                onClose.invoke();
                            }
                            PosterDisplayDialog.this.dismiss();
                            Pdlog.m3273d("PosterDisplayDialog", "setDisplayData() 5s delay close");
                        }
                    }, 5000L);
                }
            }
        }, 2, null);
    }
}
