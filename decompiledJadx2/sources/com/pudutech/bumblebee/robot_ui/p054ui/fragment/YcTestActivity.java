package com.pudutech.bumblebee.robot_ui.p054ui.fragment;

import android.os.Bundle;
import android.view.View;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: YcTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/fragment/YcTestActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mScope", "Lkotlinx/coroutines/CoroutineScope;", "getMScope", "()Lkotlinx/coroutines/CoroutineScope;", "setMScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class YcTestActivity extends MyBaseActivity {
    private HashMap _$_findViewCache;
    private final String TAG = "YcTestActivity";
    private CoroutineScope mScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
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

    public final String getTAG() {
        return this.TAG;
    }

    public final CoroutineScope getMScope() {
        return this.mScope;
    }

    public final void setMScope(CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "<set-?>");
        this.mScope = coroutineScope;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_test);
    }
}
