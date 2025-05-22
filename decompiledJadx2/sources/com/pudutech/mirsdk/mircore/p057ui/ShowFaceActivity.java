package com.pudutech.mirsdk.mircore.p057ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* compiled from: ShowFaceActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020(H\u0014J\b\u0010,\u001a\u00020(H\u0014J\b\u0010-\u001a\u00020(H\u0014J\u0018\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u00020\u0006H\u0002J\u0010\u00101\u001a\u00020(2\u0006\u00102\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/ShowFaceActivity;", "Landroid/app/Activity;", "()V", "TAG", "", "TIME", "", "bitmapRe1", "Landroid/graphics/Bitmap;", "getBitmapRe1", "()Landroid/graphics/Bitmap;", "setBitmapRe1", "(Landroid/graphics/Bitmap;)V", "bitmapRe2", "getBitmapRe2", "setBitmapRe2", "costmapView", "Lcom/pudutech/mirsdk/mircore/ui/DrawCostmapView;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "setHandler", "(Landroid/os/Handler;)V", "isinit", "", "getIsinit", "()Z", "setIsinit", "(Z)V", "runnable", "Ljava/lang/Runnable;", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "showJob", "Lkotlinx/coroutines/Job;", "show_map", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "showImg", "bitmap", "idx", "toast", "str", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ShowFaceActivity extends Activity {
    private HashMap _$_findViewCache;
    public Bitmap bitmapRe1;
    public Bitmap bitmapRe2;
    private DrawCostmapView costmapView;
    private boolean isinit;
    private Job showJob;
    private boolean show_map;
    private final String TAG = "ShowDet";
    private final int TIME = 80;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.ShowFaceActivity$runnable$1
        @Override // java.lang.Runnable
        public void run() {
            int i;
            try {
                i = ShowFaceActivity.this.TIME;
                ShowFaceActivity.this.getHandler().postDelayed(this, i);
                ShowFaceActivity.this.showImg(ShowFaceActivity.this.getBitmapRe1(), 0);
                ShowFaceActivity.this.showImg(ShowFaceActivity.this.getBitmapRe2(), 1);
                System.out.println((Object) "do...");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println((Object) "exception...");
            }
        }
    };

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final Bitmap getBitmapRe1() {
        Bitmap bitmap = this.bitmapRe1;
        if (bitmap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bitmapRe1");
        }
        return bitmap;
    }

    public final void setBitmapRe1(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "<set-?>");
        this.bitmapRe1 = bitmap;
    }

    public final Bitmap getBitmapRe2() {
        Bitmap bitmap = this.bitmapRe2;
        if (bitmap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bitmapRe2");
        }
        return bitmap;
    }

    public final void setBitmapRe2(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "<set-?>");
        this.bitmapRe2 = bitmap;
    }

    public final boolean getIsinit() {
        return this.isinit;
    }

    public final void setIsinit(boolean z) {
        this.isinit = z;
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public final void setHandler(Handler handler) {
        Intrinsics.checkParameterIsNotNull(handler, "<set-?>");
        this.handler = handler;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C5224R.layout.activity_show_mat);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), C5224R.drawable.blank, options);
        Intrinsics.checkExpressionValueIsNotNull(decodeResource, "BitmapFactory.decodeReso…, R.drawable.blank, opts)");
        this.bitmapRe1 = decodeResource;
        Bitmap decodeResource2 = BitmapFactory.decodeResource(getResources(), C5224R.drawable.blank, options);
        Intrinsics.checkExpressionValueIsNotNull(decodeResource2, "BitmapFactory.decodeReso…, R.drawable.blank, opts)");
        this.bitmapRe2 = decodeResource2;
        this.show_map = true;
        ((Button) _$_findCachedViewById(C5224R.id.back_btn_start)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.ShowFaceActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                ((ImageView) ShowFaceActivity.this._$_findCachedViewById(C5224R.id.imageView1)).setImageBitmap(ShowFaceActivity.this.getBitmapRe1());
                ((ImageView) ShowFaceActivity.this._$_findCachedViewById(C5224R.id.imageView2)).setImageBitmap(ShowFaceActivity.this.getBitmapRe2());
                ShowFaceActivity.this.setIsinit(true);
                ShowFaceActivity.this.toast("开始face view!!");
                Handler handler = ShowFaceActivity.this.getHandler();
                Runnable runnable = ShowFaceActivity.this.getRunnable();
                i = ShowFaceActivity.this.TIME;
                handler.postDelayed(runnable, i);
            }
        });
        ((Button) _$_findCachedViewById(C5224R.id.back_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.ShowFaceActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowFaceActivity.this.show_map = false;
                ShowFaceActivity.this.finish();
            }
        });
    }

    public final Runnable getRunnable() {
        return this.runnable;
    }

    public final void setRunnable(Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "<set-?>");
        this.runnable = runnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showImg(Bitmap bitmap, int idx) {
        Log.d("mot", "show image!!");
        if (this.isinit) {
            if (idx == 0) {
                Log.d("face", "bmap size = " + bitmap.getWidth() + "+_" + bitmap.getWidth() + "_bmp:" + bitmap.getByteCount());
                Perception.INSTANCE.getFaceDetView(bitmap);
                ((ImageView) _$_findCachedViewById(C5224R.id.imageView1)).setImageBitmap(bitmap);
                return;
            }
            if (idx == 1) {
                Log.d("yolo3", "bmap size = " + bitmap.getWidth() + "+_" + bitmap.getWidth() + "_bmp:" + bitmap.getByteCount());
                Perception.INSTANCE.getObjectDetView(bitmap);
                ((ImageView) _$_findCachedViewById(C5224R.id.imageView2)).setImageBitmap(bitmap);
            }
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.show_map = false;
        Job job = this.showJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toast(String str) {
        Toast.makeText(this, str, 1).show();
    }
}
