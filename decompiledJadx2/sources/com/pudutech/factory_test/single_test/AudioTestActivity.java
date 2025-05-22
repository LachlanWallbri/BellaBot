package com.pudutech.factory_test.single_test;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.single_test.AudioTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;

/* compiled from: AudioTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010 \u001a\u00020\bJ\u0012\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020\bH\u0014J\u000e\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u0004J\u0006\u0010'\u001a\u00020\bJ\u0006\u0010(\u001a\u00020\bJ\u0006\u0010)\u001a\u00020\bJ\u0006\u0010*\u001a\u00020\bJ\u0006\u0010+\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006-"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/AudioTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allCases", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getAllCases", "()Ljava/util/ArrayList;", "caseNum", "", "getCaseNum", "()I", "setCaseNum", "(I)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "mediaPlayer", "Landroid/media/MediaPlayer;", "step", "Lcom/pudutech/factory_test/single_test/AudioTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/AudioTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/AudioTestActivity$Step;)V", "FSM", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "play", "name", "runStepFail", "runStepPlaying", "runStepSuccess", "setTypeCases", "stopPlayer", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AudioTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseNum;
    public TestItem mTestItem;
    private MediaPlayer mediaPlayer;
    private final String TAG = "AudioTestActivity";
    private final ArrayList<Function0<Unit>> allCases = new ArrayList<>();
    private Step step = Step.IDLE;

    /* compiled from: AudioTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/AudioTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "PLAYING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
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
            $EnumSwitchMapping$0[Step.PLAYING.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.SUCCESS.ordinal()] = 3;
            $EnumSwitchMapping$0[Step.FAIL.ordinal()] = 4;
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

    public static final /* synthetic */ MediaPlayer access$getMediaPlayer$p(AudioTestActivity audioTestActivity) {
        MediaPlayer mediaPlayer = audioTestActivity.mediaPlayer;
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        TestItem testItem;
        super.onCreate(savedInstanceState);
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.AUDIO) && testItem2.getStage() == fromValue) {
                    break;
                }
            }
            if (testItem == null) {
                Intrinsics.throwNpe();
            }
            this.mTestItem = testItem;
        }
        setContentView(2131427397);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod(ScrollingMovementMethod.getInstance());
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AudioTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                AudioTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AudioTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                AudioTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                AudioTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AudioTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                AudioTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                AudioTestActivity.this.setStep(AudioTestActivity.Step.IDLE);
                AudioTestActivity.this.FSM();
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
        mediaPlayer2.setVolume(0.5f, 0.5f);
        FSM();
    }

    public final ArrayList<Function0<Unit>> getAllCases() {
        return this.allCases;
    }

    public final int getCaseNum() {
        return this.caseNum;
    }

    public final void setCaseNum(int i) {
        this.caseNum = i;
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
            setTypeCases();
            this.step = Step.PLAYING;
            FSM();
        } else if (i == 2) {
            runStepPlaying();
        } else if (i == 3) {
            runStepSuccess();
        } else {
            if (i != 4) {
                return;
            }
            runStepFail();
        }
    }

    public final void runStepPlaying() {
        Pdlog.m3273d(this.TAG, "runStepPlaying left size=" + this.allCases.size());
        if (this.allCases.size() == 0) {
            this.step = Step.SUCCESS;
            FSM();
            return;
        }
        int nextInt = Random.INSTANCE.nextInt(this.allCases.size());
        Pdlog.m3273d(this.TAG, "random next. size=" + this.allCases.size() + " next=" + nextInt);
        Function0<Unit> function0 = this.allCases.get(nextInt);
        Intrinsics.checkExpressionValueIsNotNull(function0, "allCases[num]");
        this.allCases.remove(nextInt);
        function0.invoke();
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("音响测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(AudioTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                AudioTestActivity.this.finish();
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
        sb.append("音响测试结束\n测试结果是：失败\n详细：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) AudioTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final void setTypeCases() {
        final int i = 0;
        Pdlog.m3273d(this.TAG, "setTypeCases ");
        this.allCases.clear();
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        for (Object obj : LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("语音", "音乐", "没有声音"), 0.0f, 4, null)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((Button) obj).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$setTypeCases$$inlined$forEachIndexed$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str;
                    str = this.TAG;
                    Pdlog.m3275i(str, "click option. index=" + i + " when rightAnswerIndex=" + intRef.element);
                    this.getMTestItem().setStatus(TestStatus.TESTING);
                    this.getMTestItem().setFailDescription((String) null);
                    AudioTestActivity.access$getMediaPlayer$p(this).setVolume(0.0f, 0.0f);
                    AudioTestActivity.access$getMediaPlayer$p(this).pause();
                    if (intRef.element != i) {
                        this.setStep(AudioTestActivity.Step.FAIL);
                        this.getMTestItem().setFailDescription("选择选项错误，可能是音频线其中一条未连接好");
                    }
                    this.FSM();
                }
            });
            i = i2;
        }
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$setTypeCases$updateGuide$1
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
                int caseNum = AudioTestActivity.this.getCaseNum() - AudioTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) AudioTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("连通性测试 ( " + caseNum + " | " + AudioTestActivity.this.getCaseNum() + " )：\n音响播放的内容是？");
            }
        };
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$setTypeCases$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                intRef.element = 0;
                function0.invoke();
                AudioTestActivity.access$getMediaPlayer$p(AudioTestActivity.this).setVolume(0.7f, 0.7f);
                AudioTestActivity.this.play("voice.mp3");
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$setTypeCases$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                intRef.element = 1;
                function0.invoke();
                AudioTestActivity.access$getMediaPlayer$p(AudioTestActivity.this).setVolume(0.7f, 0.7f);
                AudioTestActivity.this.play("music.mp3");
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$setTypeCases$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                intRef.element = 0;
                function0.invoke();
                AudioTestActivity.access$getMediaPlayer$p(AudioTestActivity.this).setVolume(0.0f, 0.9f);
                AudioTestActivity.this.play("voice.mp3");
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$setTypeCases$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                intRef.element = 1;
                function0.invoke();
                AudioTestActivity.access$getMediaPlayer$p(AudioTestActivity.this).setVolume(0.9f, 0.0f);
                AudioTestActivity.this.play("music.mp3");
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$setTypeCases$6
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                intRef.element = 2;
                AudioTestActivity.access$getMediaPlayer$p(AudioTestActivity.this).setVolume(0.0f, 0.0f);
                AudioTestActivity.access$getMediaPlayer$p(AudioTestActivity.this).pause();
                function0.invoke();
            }
        });
        this.caseNum = this.allCases.size();
    }

    public final void play(final String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(this.TAG, "play name=" + name);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.factory_test.single_test.AudioTestActivity$play$1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) {
                String str;
                str = AudioTestActivity.this.TAG;
                Pdlog.m3273d(str, "OnCompletion ");
                AudioTestActivity.this.play(name);
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
            mediaPlayer3.setDataSource(getAssets().openFd(name));
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
            mediaPlayer.setVolume(0.0f, 0.0f);
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer2.stop();
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        stopPlayer();
    }
}
