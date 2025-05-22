package com.pudutech.rgbdlib.activity;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.pudutech.rgbdlib.C5657R;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

/* compiled from: CheckToolActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class CheckToolActivity$onCreate$4$onFrameDescriptor$1 implements Runnable {
    final /* synthetic */ Ref.ObjectRef $bmp;
    final /* synthetic */ ByteBuffer $byteBuffer;
    final /* synthetic */ CheckToolActivity$onCreate$4 this$0;

    CheckToolActivity$onCreate$4$onFrameDescriptor$1(CheckToolActivity$onCreate$4 checkToolActivity$onCreate$4, Ref.ObjectRef objectRef, ByteBuffer byteBuffer) {
        this.this$0 = checkToolActivity$onCreate$4;
        this.$bmp = objectRef;
        this.$byteBuffer = byteBuffer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ((Bitmap) this.$bmp.element).copyPixelsFromBuffer(this.$byteBuffer.rewind());
        ImageView imageView = (ImageView) this.this$0.this$0._$_findCachedViewById(C5657R.id.imageView);
        if (imageView != null) {
            imageView.setImageBitmap((Bitmap) this.$bmp.element);
        }
        ImageView imageView2 = (ImageView) this.this$0.this$0._$_findCachedViewById(C5657R.id.imageView);
        if (imageView2 != null) {
            imageView2.postInvalidate();
        }
    }
}
