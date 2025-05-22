package com.pudutech.factory_test.single_test;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.aiuicae.CAEwrapper;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.MicrophoneTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: MicrophoneTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001@B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u00020\u000fJ\u0006\u00103\u001a\u000201J\u0012\u00104\u001a\u0002012\b\u00105\u001a\u0004\u0018\u000106H\u0014J\b\u00107\u001a\u000201H\u0014J\u000e\u00108\u001a\u0002012\u0006\u00109\u001a\u00020\u0004J\u0006\u0010:\u001a\u000201J\u0006\u0010;\u001a\u000201J\u0006\u0010<\u001a\u000201J\u0006\u0010=\u001a\u000201J\u0006\u0010>\u001a\u000201J\u0006\u0010?\u001a\u000201R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u0006A"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/MicrophoneTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "all", "", "getAll", "()I", "setAll", "(I)V", "cnt", "getCnt", "setCnt", "hasRecord", "", "getHasRecord", "()Z", "setHasRecord", "(Z)V", "isInitDone", "setInitDone", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "mcae", "Lcom/pudutech/aiuicae/CAEwrapper;", "getMcae", "()Lcom/pudutech/aiuicae/CAEwrapper;", "setMcae", "(Lcom/pudutech/aiuicae/CAEwrapper;)V", "mediaPlayer", "Landroid/media/MediaPlayer;", "step", "Lcom/pudutech/factory_test/single_test/MicrophoneTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/MicrophoneTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/MicrophoneTestActivity$Step;)V", "FSM", "", "initMcae", "layoutRecording", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "play", "fileName", "runStepFail", "runStepIDLE", "runStepPlaying", "runStepRecording", "runStepSuccess", "stopPlayer", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MicrophoneTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int cnt;
    private boolean hasRecord;
    private boolean isInitDone;
    private Job job;
    public TestItem mTestItem;
    private MediaPlayer mediaPlayer;
    private final String TAG = "MicrophoneTestActivity";
    private CAEwrapper mcae = new CAEwrapper();
    private Step step = Step.IDLE;
    private int all = 6;

    /* compiled from: MicrophoneTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/MicrophoneTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "RECORDING", "PLAYING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        RECORDING,
        PLAYING,
        FAIL,
        SUCCESS
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Step.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Step.IDLE.ordinal()] = 1;
            $EnumSwitchMapping$0[Step.RECORDING.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.PLAYING.ordinal()] = 3;
            $EnumSwitchMapping$0[Step.SUCCESS.ordinal()] = 4;
            $EnumSwitchMapping$0[Step.FAIL.ordinal()] = 5;
        }
    }

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

    public static final /* synthetic */ MediaPlayer access$getMediaPlayer$p(MicrophoneTestActivity microphoneTestActivity) {
        MediaPlayer mediaPlayer = microphoneTestActivity.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        return mediaPlayer;
    }

    public final TestItem getMTestItem() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        return testItem;
    }

    public final void setMTestItem(TestItem testItem) {
        Intrinsics.checkParameterIsNotNull(testItem, "<set-?>");
        this.mTestItem = testItem;
    }

    public final CAEwrapper getMcae() {
        return this.mcae;
    }

    public final void setMcae(CAEwrapper cAEwrapper) {
        Intrinsics.checkParameterIsNotNull(cAEwrapper, "<set-?>");
        this.mcae = cAEwrapper;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        TestItem testItem;
        super.onCreate(savedInstanceState);
        setContentView(2131427397);
        if (getIntent() != null) {
            TestStage.Companion companion = TestStage.INSTANCE;
            String stringExtra = getIntent().getStringExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY());
            Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(Al…tem.EXTER_TEST_STAGE_KEY)");
            TestStage fromValue = companion.fromValue(stringExtra);
            Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
            while (true) {
                if (!it.hasNext()) {
                    testItem = null;
                    break;
                }
                testItem = it.next();
                TestItem testItem2 = testItem;
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.MICROPHONE) && testItem2.getStage() == fromValue) {
                    break;
                }
            }
            if (testItem == null) {
                Intrinsics.throwNpe();
            }
            this.mTestItem = testItem;
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod(ScrollingMovementMethod.getInstance());
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = MicrophoneTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                MicrophoneTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = MicrophoneTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                MicrophoneTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                MicrophoneTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = MicrophoneTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                MicrophoneTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                MicrophoneTestActivity.this.setStep(MicrophoneTestActivity.Step.IDLE);
                MicrophoneTestActivity.this.FSM();
            }
        });
        this.mediaPlayer = new MediaPlayer();
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setLegacyStreamType(3);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer.setAudioAttributes(builder.build());
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer2.setVolume(0.8f, 0.8f);
        FSM();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.mcae.releaseCAE();
        stopPlayer();
    }

    public final Step getStep() {
        return this.step;
    }

    public final void setStep(Step step) {
        Intrinsics.checkParameterIsNotNull(step, "<set-?>");
        this.step = step;
    }

    public final void FSM() {
        Pdlog.m3273d(this.TAG, "FSM. now=" + this.step);
        int i = WhenMappings.$EnumSwitchMapping$0[this.step.ordinal()];
        if (i == 1) {
            runStepIDLE();
            return;
        }
        if (i == 2) {
            runStepRecording();
            return;
        }
        if (i == 3) {
            runStepPlaying();
        } else if (i == 4) {
            runStepSuccess();
        } else {
            if (i != 5) {
                return;
            }
            runStepFail();
        }
    }

    public final Job getJob() {
        return this.job;
    }

    public final void setJob(Job job) {
        this.job = job;
    }

    public final void runStepIDLE() {
        Job launch$default;
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.cnt = 0;
        this.hasRecord = false;
        stopPlayer();
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("麦阵初始化中，请稍等");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new MicrophoneTestActivity$runStepIDLE$1(this, null), 2, null);
        this.job = launch$default;
    }

    public final boolean getHasRecord() {
        return this.hasRecord;
    }

    public final void setHasRecord(boolean z) {
        this.hasRecord = z;
    }

    public final void runStepRecording() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("麦阵测试：录音阶段");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("开始录音", "开始测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$runStepRecording$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MicrophoneTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                MicrophoneTestActivity.this.getMcae().startRecording();
                MicrophoneTestActivity.this.layoutRecording();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$runStepRecording$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MicrophoneTestActivity.this.getHasRecord()) {
                    MicrophoneTestActivity.this.setStep(MicrophoneTestActivity.Step.PLAYING);
                    MicrophoneTestActivity.this.FSM();
                } else {
                    Toast.makeText(MicrophoneTestActivity.this, "请先录音", 1).show();
                }
            }
        });
    }

    public final void layoutRecording() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("麦阵测试：录音中");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("结束录音"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$layoutRecording$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MicrophoneTestActivity.this.getMcae().stopRecording();
                MicrophoneTestActivity.this.setHasRecord(true);
                MicrophoneTestActivity.this.FSM();
            }
        });
    }

    public final int getCnt() {
        return this.cnt;
    }

    public final void setCnt(int i) {
        this.cnt = i;
    }

    public final int getAll() {
        return this.all;
    }

    public final void setAll(int i) {
        this.all = i;
    }

    public final void runStepPlaying() {
        if (this.cnt == this.all) {
            this.step = Step.SUCCESS;
            FSM();
            return;
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("麦阵测试 ( " + (this.cnt + 1) + " | " + this.all + " )：\n请确认录音是否清晰无杂音");
        StringBuilder sb = new StringBuilder();
        sb.append("/sdcard/CAEChannelAudio/channel_");
        sb.append(this.cnt);
        sb.append(".wav");
        play(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("有异常", "正常"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$runStepPlaying$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MicrophoneTestActivity.access$getMediaPlayer$p(MicrophoneTestActivity.this).pause();
                MicrophoneTestActivity.this.setStep(MicrophoneTestActivity.Step.FAIL);
                MicrophoneTestActivity.this.getMTestItem().setFailDescription("人工判定有杂音");
                MicrophoneTestActivity.this.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$runStepPlaying$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MicrophoneTestActivity.access$getMediaPlayer$p(MicrophoneTestActivity.this).pause();
                MicrophoneTestActivity microphoneTestActivity = MicrophoneTestActivity.this;
                microphoneTestActivity.setCnt(microphoneTestActivity.getCnt() + 1);
                MicrophoneTestActivity.this.FSM();
            }
        });
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("麦阵测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MicrophoneTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(MicrophoneTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                MicrophoneTestActivity.this.finish();
            }
        });
    }

    public final void runStepFail() {
        Pdlog.m3274e(this.TAG, "test fail");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.FAIL);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        StringBuilder sb = new StringBuilder();
        sb.append("麦阵测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MicrophoneTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) MicrophoneTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    /* renamed from: isInitDone, reason: from getter */
    public final boolean getIsInitDone() {
        return this.isInitDone;
    }

    public final void setInitDone(boolean z) {
        this.isInitDone = z;
    }

    public final boolean initMcae() {
        boolean initCAEEngine;
        if (this.isInitDone) {
            return true;
        }
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        if (hdInterface.getMachineInfo().getProductMachineType().getModel() == MachineModel.Peanut) {
            initCAEEngine = this.mcae.initCAEEngine(this, true, false, 2);
        } else {
            initCAEEngine = this.mcae.initCAEEngine(this, true, false, 1);
        }
        this.isInitDone = initCAEEngine;
        this.mcae.setOnStateChangedListener(new CAEwrapper.OnStateChangedListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$initMcae$1
            @Override // com.pudutech.aiuicae.CAEwrapper.OnStateChangedListener
            public void onWakeChanged(String p0) {
                String str;
                str = MicrophoneTestActivity.this.TAG;
                Pdlog.m3273d(str, "onWakeChanged p0=" + p0);
            }

            @Override // com.pudutech.aiuicae.CAEwrapper.OnStateChangedListener
            public void onVadChanged(int p0) {
                String str;
                str = MicrophoneTestActivity.this.TAG;
                Pdlog.m3273d(str, "onVadChanged p0=" + p0);
            }

            @Override // com.pudutech.aiuicae.CAEwrapper.OnStateChangedListener
            public void onResultChanged(String p0) {
                String str;
                str = MicrophoneTestActivity.this.TAG;
                Pdlog.m3273d(str, "onResultChanged p0=" + p0);
            }

            @Override // com.pudutech.aiuicae.CAEwrapper.OnStateChangedListener
            public void onStateChanged(int p0) {
                String str;
                str = MicrophoneTestActivity.this.TAG;
                Pdlog.m3273d(str, "onStateChanged p0=" + p0);
            }

            @Override // com.pudutech.aiuicae.CAEwrapper.OnStateChangedListener
            public void onTextChanged(String p0) {
                String str;
                str = MicrophoneTestActivity.this.TAG;
                Pdlog.m3273d(str, "onTextChanged p0=" + p0);
            }
        });
        return this.isInitDone;
    }

    public final void play(final String fileName) {
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        Pdlog.m3273d(this.TAG, "play name=" + fileName);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$play$1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) {
                MicrophoneTestActivity.this.play(fileName);
            }
        });
        try {
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer2.reset();
            MediaPlayer mediaPlayer3 = this.mediaPlayer;
            if (mediaPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer3.setDataSource(fileName);
            MediaPlayer mediaPlayer4 = this.mediaPlayer;
            if (mediaPlayer4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer4.prepare();
            MediaPlayer mediaPlayer5 = this.mediaPlayer;
            if (mediaPlayer5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer5.start();
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e);
        }
    }

    public final void stopPlayer() {
        Pdlog.m3273d(this.TAG, "stopPlayer ");
        try {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.factory_test.single_test.MicrophoneTestActivity$stopPlayer$1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                }
            });
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer2.stop();
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e);
        }
    }
}
