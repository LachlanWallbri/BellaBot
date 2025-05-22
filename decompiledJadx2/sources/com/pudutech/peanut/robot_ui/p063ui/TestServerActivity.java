package com.pudutech.peanut.robot_ui.p063ui;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TestServerActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000bH\u0014J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/TestServerActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "tableName", "tableNumber", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setContent", "count", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TestServerActivity extends MyBaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int PLAY_VOICE_UP = 2;
    private HashMap _$_findViewCache;
    private final String TAG = getClass().getSimpleName();
    private String tableName = "";
    private String tableNumber = "";
    private Handler handler = new Companion.WithoutLeakHandler(this);

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C5508R.layout.activity_printl_table);
        this.tableNumber = getIntent().getStringExtra("number");
        this.tableName = getIntent().getStringExtra(Constants.POINT_TYPE_TABLE);
        String str = this.tableNumber;
        if (str != null) {
            setContent(str);
        }
        TextView tvTable = (TextView) _$_findCachedViewById(C5508R.id.tvTable);
        Intrinsics.checkExpressionValueIsNotNull(tvTable, "tvTable");
        tvTable.setText(this.tableName);
        TextView tvFinish = (TextView) _$_findCachedViewById(C5508R.id.tvFinish);
        Intrinsics.checkExpressionValueIsNotNull(tvFinish, "tvFinish");
        ViewExtKt.onSingleClick(tvFinish, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TestServerActivity$onCreate$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                TestServerActivity.this.finish();
            }
        });
        ((LottieAnimationView) _$_findCachedViewById(C5508R.id.print_float_anim)).addAnimatorListener(new Animator.AnimatorListener() { // from class: com.pudutech.peanut.robot_ui.ui.TestServerActivity$onCreate$3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator p0) {
                String str2;
                str2 = TestServerActivity.this.TAG;
                Pdlog.m3273d(str2, "onAnimationRepeat");
                LinearLayout llTable = (LinearLayout) TestServerActivity.this._$_findCachedViewById(C5508R.id.llTable);
                Intrinsics.checkExpressionValueIsNotNull(llTable, "llTable");
                llTable.setVisibility(0);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator p0) {
                String str2;
                str2 = TestServerActivity.this.TAG;
                Pdlog.m3273d(str2, "onAnimationEnd");
                LinearLayout llTable = (LinearLayout) TestServerActivity.this._$_findCachedViewById(C5508R.id.llTable);
                Intrinsics.checkExpressionValueIsNotNull(llTable, "llTable");
                llTable.setVisibility(0);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator p0) {
                String str2;
                str2 = TestServerActivity.this.TAG;
                Pdlog.m3273d(str2, "onAnimationCancel");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator p0) {
                String str2;
                str2 = TestServerActivity.this.TAG;
                Pdlog.m3273d(str2, "onAnimationStart");
            }
        });
        this.handler.sendEmptyMessageDelayed(2, 1000L);
    }

    private final void setContent(String count) {
        TextView tvContent = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
        Intrinsics.checkExpressionValueIsNotNull(tvContent, "tvContent");
        tvContent.setText(Html.fromHtml(getString(C5508R.string.print_table_content, new Object[]{count})));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    /* compiled from: TestServerActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/TestServerActivity$Companion;", "", "()V", "PLAY_VOICE_UP", "", "newInstance", "Lcom/pudutech/peanut/robot_ui/ui/TestServerActivity;", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TestServerActivity newInstance() {
            return new TestServerActivity();
        }

        /* compiled from: TestServerActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/TestServerActivity$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "activity", "Lcom/pudutech/peanut/robot_ui/ui/TestServerActivity;", "(Lcom/pudutech/peanut/robot_ui/ui/TestServerActivity;)V", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<TestServerActivity> activity;

            public WithoutLeakHandler(TestServerActivity activity) {
                Intrinsics.checkParameterIsNotNull(activity, "activity");
                this.activity = new WeakReference<>(activity);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                if (this.activity == null || msg.what != 2) {
                    return;
                }
                VoicePlayTasks.INSTANCE.playTakeNo();
            }
        }
    }
}
