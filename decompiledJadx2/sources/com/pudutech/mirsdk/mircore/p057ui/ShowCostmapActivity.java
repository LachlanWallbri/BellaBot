package com.pudutech.mirsdk.mircore.p057ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.mirperception.Costmap;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ShowCostmapActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0014J\b\u0010\u0011\u001a\u00020\fH\u0014J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/ShowCostmapActivity;", "Landroid/app/Activity;", "()V", "TAG", "", "costmapView", "Lcom/pudutech/mirsdk/mircore/ui/DrawCostmapView;", "showJob", "Lkotlinx/coroutines/Job;", "show_map", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "toast", "str", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ShowCostmapActivity extends Activity {
    private final String TAG = "ShowCostmap";
    private HashMap _$_findViewCache;
    private DrawCostmapView costmapView;
    private Job showJob;
    private boolean show_map;

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

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "decorView");
        decorView.setSystemUiVisibility(3846);
        setContentView(C5224R.layout.activity_show_costmap);
        ((Button) _$_findCachedViewById(C5224R.id.back_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.ShowCostmapActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowCostmapActivity.this.show_map = false;
                Costmap.INSTANCE.getCostmapListener().remove("show");
                ShowCostmapActivity.this.finish();
            }
        });
    }

    @Override // android.app.Activity
    protected void onResume() {
        Job launch$default;
        super.onResume();
        this.costmapView = (DrawCostmapView) findViewById(C5224R.id.costmap_view);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ShowCostmapActivity$onResume$1(this, null), 3, null);
        this.show_map = true;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ShowCostmapActivity$onResume$2(this, null), 3, null);
        this.showJob = launch$default;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.show_map = false;
        Job job = this.showJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Costmap.INSTANCE.getCostmapListener().remove("show");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    private final void toast(String str) {
        Toast.makeText(this, str, 1).show();
    }
}
