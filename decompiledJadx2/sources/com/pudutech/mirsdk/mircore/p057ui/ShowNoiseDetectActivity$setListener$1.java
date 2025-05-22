package com.pudutech.mirsdk.mircore.p057ui;

import android.os.SystemClock;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.mirperception.Costmap;
import com.pudutech.mirsdk.mircore.p057ui.ShowNoiseDetectActivity;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ShowNoiseDetectActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$setListener$1", m3970f = "ShowNoiseDetectActivity.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {236}, m3973m = "invokeSuspend", m3974n = {"$this$launch", AUserTrack.UTKEY_START_TIME, "noiseResult", "imgWidth", "imgHeight", LinkFormat.OBSERVABLE, "delta"}, m3975s = {"L$0", "J$0", "L$1", "I$0", "I$1", "L$2", "J$1"})
/* loaded from: classes6.dex */
public final class ShowNoiseDetectActivity$setListener$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.BooleanRef $alertFlag;
    int I$0;
    int I$1;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6276p$;
    final /* synthetic */ ShowNoiseDetectActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowNoiseDetectActivity$setListener$1(ShowNoiseDetectActivity showNoiseDetectActivity, Ref.BooleanRef booleanRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = showNoiseDetectActivity;
        this.$alertFlag = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ShowNoiseDetectActivity$setListener$1 showNoiseDetectActivity$setListener$1 = new ShowNoiseDetectActivity$setListener$1(this.this$0, this.$alertFlag, completion);
        showNoiseDetectActivity$setListener$1.f6276p$ = (CoroutineScope) obj;
        return showNoiseDetectActivity$setListener$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ShowNoiseDetectActivity$setListener$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x016a, code lost:
    
        if (com.pudutech.mirsdk.mircore.p057ui.ShowNoiseDetectActivity.access$getMediaPlayer$p(r19.this$0).isPlaying() != false) goto L40;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01bf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x003f A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v27 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Vector3d[] vector3dArr;
        String str;
        boolean z;
        boolean z2;
        long currentThreadTimeMillis;
        String str2;
        boolean z3;
        boolean z4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        int i2 = 1;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6276p$;
            Costmap costmap = Costmap.INSTANCE;
            vector3dArr = this.this$0.area;
            costmap.setNoiseDetectSwitch(true, vector3dArr);
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j = this.J$1;
            int i3 = this.I$1;
            int i4 = this.I$0;
            long j2 = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (true) {
            long currentThreadTimeMillis2 = SystemClock.currentThreadTimeMillis();
            int[] checkNoise = Costmap.INSTANCE.checkNoise();
            DrawNoiseDetectView noiseDetect = (DrawNoiseDetectView) this.this$0._$_findCachedViewById(C5224R.id.noiseDetect);
            Intrinsics.checkExpressionValueIsNotNull(noiseDetect, "noiseDetect");
            if (noiseDetect.getWidth() == 0) {
                return Unit.INSTANCE;
            }
            final int intValue = ArraysKt.takeLast(checkNoise, 2).get(0).intValue();
            final int intValue2 = ArraysKt.takeLast(checkNoise, 2).get(i2).intValue();
            final List<Integer> take = ArraysKt.take(checkNoise, checkNoise.length - 2);
            if (intValue * intValue2 != take.size()) {
                str = this.this$0.TAG;
                Object[] objArr = new Object[i2];
                objArr[0] = "w " + intValue + " h " + intValue2 + " but size " + take.size();
                Pdlog.m3274e(str, objArr);
                return Unit.INSTANCE;
            }
            Iterator<Integer> it = take.iterator();
            int i5 = i2;
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                int intValue3 = it.next().intValue();
                if (ShowNoiseDetectActivity.ObsType.INSTANCE.from(intValue3) == ShowNoiseDetectActivity.ObsType.OutsideObs || ShowNoiseDetectActivity.ObsType.INSTANCE.from(intValue3) == ShowNoiseDetectActivity.ObsType.Free) {
                    i5 = 1;
                } else {
                    str2 = this.this$0.TAG;
                    Object[] objArr2 = new Object[i5];
                    StringBuilder sb = new StringBuilder();
                    sb.append("alertFlag ");
                    sb.append(this.$alertFlag.element);
                    sb.append(" openVoice ");
                    z3 = this.this$0.openVoice;
                    sb.append(z3);
                    objArr2[0] = sb.toString();
                    Pdlog.m3273d(str2, objArr2);
                    if (!this.$alertFlag.element) {
                        z4 = this.this$0.openVoice;
                        if (z4) {
                            this.$alertFlag.element = i5;
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C52581(null), 3, null);
                        }
                    }
                    z = true;
                }
            }
            if (z || !ShowNoiseDetectActivity.access$getMediaPlayer$p(this.this$0).isPlaying()) {
                z2 = this.this$0.openVoice;
                if (!z2) {
                }
                this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$setListener$1.3
                    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                    @Override // java.lang.Runnable
                    public final void run() {
                        float f;
                        float f2;
                        ShowNoiseDetectActivity.DrawNoiseType drawNoiseType;
                        ShowNoiseDetectActivity.DrawNoiseType drawNoiseType2;
                        int i6;
                        ShowNoiseDetectActivity.DrawNoiseType drawNoiseType3;
                        int i7;
                        ShowNoiseDetectActivity.DrawNoiseType drawNoiseType4;
                        int i8;
                        DrawNoiseDetectView noiseDetect2 = (DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect);
                        Intrinsics.checkExpressionValueIsNotNull(noiseDetect2, "noiseDetect");
                        float width = noiseDetect2.getWidth() / 2.0f;
                        DrawNoiseDetectView noiseDetect3 = (DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect);
                        Intrinsics.checkExpressionValueIsNotNull(noiseDetect3, "noiseDetect");
                        float height = noiseDetect3.getHeight() / 4.0f;
                        ShowNoiseDetectActivity$setListener$1.this.this$0.drawAxis();
                        ShowNoiseDetectActivity$setListener$1.this.this$0.drawCheckArea();
                        int i9 = 0;
                        for (Object obj2 : take) {
                            int i10 = i9 + 1;
                            if (i9 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            ((Number) obj2).intValue();
                            float f3 = (i9 / intValue) - (intValue2 / 2);
                            f = ShowNoiseDetectActivity$setListener$1.this.this$0.scale;
                            float f4 = (f3 * f) + width;
                            int i11 = intValue;
                            float f5 = (i9 % i11) - (i11 / 2);
                            f2 = ShowNoiseDetectActivity$setListener$1.this.this$0.scale;
                            float f6 = (f5 * f2) + height;
                            boolean z5 = true;
                            switch (ShowNoiseDetectActivity.ObsType.INSTANCE.from(r5)) {
                                case Mix:
                                    drawNoiseType = ShowNoiseDetectActivity$setListener$1.this.this$0.obsToShow;
                                    if (drawNoiseType != null) {
                                        int i12 = ShowNoiseDetectActivity.WhenMappings.$EnumSwitchMapping$0[drawNoiseType.ordinal()];
                                        if (i12 != 1 && i12 != 2) {
                                            if (i12 != 3) {
                                                if (i12 == 4) {
                                                    ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(-16711936);
                                                    break;
                                                }
                                            } else {
                                                ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(SupportMenu.CATEGORY_MASK);
                                                break;
                                            }
                                        } else {
                                            ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(InputDeviceCompat.SOURCE_ANY);
                                            break;
                                        }
                                    }
                                    break;
                                case Lidar:
                                    drawNoiseType2 = ShowNoiseDetectActivity$setListener$1.this.this$0.obsToShow;
                                    if (drawNoiseType2 != null && ((i6 = ShowNoiseDetectActivity.WhenMappings.$EnumSwitchMapping$1[drawNoiseType2.ordinal()]) == 1 || i6 == 2)) {
                                        ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(SupportMenu.CATEGORY_MASK);
                                        break;
                                    }
                                    break;
                                case RGBD:
                                    drawNoiseType3 = ShowNoiseDetectActivity$setListener$1.this.this$0.obsToShow;
                                    if (drawNoiseType3 != null && ((i7 = ShowNoiseDetectActivity.WhenMappings.$EnumSwitchMapping$2[drawNoiseType3.ordinal()]) == 1 || i7 == 2)) {
                                        ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(-16711936);
                                        break;
                                    }
                                    break;
                                case LowObs:
                                    drawNoiseType4 = ShowNoiseDetectActivity$setListener$1.this.this$0.obsToShow;
                                    if (drawNoiseType4 != null && ((i8 = ShowNoiseDetectActivity.WhenMappings.$EnumSwitchMapping$3[drawNoiseType4.ordinal()]) == 1 || i8 == 2)) {
                                        ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(-16776961);
                                        break;
                                    }
                                    break;
                                case OutsideObs:
                                    ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(-1);
                                    break;
                            }
                            z5 = false;
                            if (z5) {
                                ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas().drawCircle(f4, f6, 3.0f, ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter());
                            }
                            i9 = i10;
                        }
                        ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).refreshScreen();
                    }
                });
                long j3 = 99;
                currentThreadTimeMillis = j3 - (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis2);
                if (0 <= currentThreadTimeMillis && j3 >= currentThreadTimeMillis) {
                    this.L$0 = coroutineScope;
                    this.J$0 = currentThreadTimeMillis2;
                    this.L$1 = checkNoise;
                    this.I$0 = intValue;
                    this.I$1 = intValue2;
                    this.L$2 = take;
                    this.J$1 = currentThreadTimeMillis;
                    i2 = 1;
                    this.label = 1;
                    if (DelayKt.delay(currentThreadTimeMillis, this) != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    i2 = 1;
                }
            }
            this.$alertFlag.element = false;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C52592(null), 3, null);
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$setListener$1.3
                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                @Override // java.lang.Runnable
                public final void run() {
                    float f;
                    float f2;
                    ShowNoiseDetectActivity.DrawNoiseType drawNoiseType;
                    ShowNoiseDetectActivity.DrawNoiseType drawNoiseType2;
                    int i6;
                    ShowNoiseDetectActivity.DrawNoiseType drawNoiseType3;
                    int i7;
                    ShowNoiseDetectActivity.DrawNoiseType drawNoiseType4;
                    int i8;
                    DrawNoiseDetectView noiseDetect2 = (DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect);
                    Intrinsics.checkExpressionValueIsNotNull(noiseDetect2, "noiseDetect");
                    float width = noiseDetect2.getWidth() / 2.0f;
                    DrawNoiseDetectView noiseDetect3 = (DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect);
                    Intrinsics.checkExpressionValueIsNotNull(noiseDetect3, "noiseDetect");
                    float height = noiseDetect3.getHeight() / 4.0f;
                    ShowNoiseDetectActivity$setListener$1.this.this$0.drawAxis();
                    ShowNoiseDetectActivity$setListener$1.this.this$0.drawCheckArea();
                    int i9 = 0;
                    for (Object obj2 : take) {
                        int i10 = i9 + 1;
                        if (i9 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        ((Number) obj2).intValue();
                        float f3 = (i9 / intValue) - (intValue2 / 2);
                        f = ShowNoiseDetectActivity$setListener$1.this.this$0.scale;
                        float f4 = (f3 * f) + width;
                        int i11 = intValue;
                        float f5 = (i9 % i11) - (i11 / 2);
                        f2 = ShowNoiseDetectActivity$setListener$1.this.this$0.scale;
                        float f6 = (f5 * f2) + height;
                        boolean z5 = true;
                        switch (ShowNoiseDetectActivity.ObsType.INSTANCE.from(r5)) {
                            case Mix:
                                drawNoiseType = ShowNoiseDetectActivity$setListener$1.this.this$0.obsToShow;
                                if (drawNoiseType != null) {
                                    int i12 = ShowNoiseDetectActivity.WhenMappings.$EnumSwitchMapping$0[drawNoiseType.ordinal()];
                                    if (i12 != 1 && i12 != 2) {
                                        if (i12 != 3) {
                                            if (i12 == 4) {
                                                ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(-16711936);
                                                break;
                                            }
                                        } else {
                                            ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(SupportMenu.CATEGORY_MASK);
                                            break;
                                        }
                                    } else {
                                        ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(InputDeviceCompat.SOURCE_ANY);
                                        break;
                                    }
                                }
                                break;
                            case Lidar:
                                drawNoiseType2 = ShowNoiseDetectActivity$setListener$1.this.this$0.obsToShow;
                                if (drawNoiseType2 != null && ((i6 = ShowNoiseDetectActivity.WhenMappings.$EnumSwitchMapping$1[drawNoiseType2.ordinal()]) == 1 || i6 == 2)) {
                                    ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(SupportMenu.CATEGORY_MASK);
                                    break;
                                }
                                break;
                            case RGBD:
                                drawNoiseType3 = ShowNoiseDetectActivity$setListener$1.this.this$0.obsToShow;
                                if (drawNoiseType3 != null && ((i7 = ShowNoiseDetectActivity.WhenMappings.$EnumSwitchMapping$2[drawNoiseType3.ordinal()]) == 1 || i7 == 2)) {
                                    ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(-16711936);
                                    break;
                                }
                                break;
                            case LowObs:
                                drawNoiseType4 = ShowNoiseDetectActivity$setListener$1.this.this$0.obsToShow;
                                if (drawNoiseType4 != null && ((i8 = ShowNoiseDetectActivity.WhenMappings.$EnumSwitchMapping$3[drawNoiseType4.ordinal()]) == 1 || i8 == 2)) {
                                    ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(-16776961);
                                    break;
                                }
                                break;
                            case OutsideObs:
                                ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setColor(-1);
                                break;
                        }
                        z5 = false;
                        if (z5) {
                            ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas().drawCircle(f4, f6, 3.0f, ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).getPainter());
                        }
                        i9 = i10;
                    }
                    ((DrawNoiseDetectView) ShowNoiseDetectActivity$setListener$1.this.this$0._$_findCachedViewById(C5224R.id.noiseDetect)).refreshScreen();
                }
            });
            long j32 = 99;
            currentThreadTimeMillis = j32 - (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis2);
            if (0 <= currentThreadTimeMillis) {
                this.L$0 = coroutineScope;
                this.J$0 = currentThreadTimeMillis2;
                this.L$1 = checkNoise;
                this.I$0 = intValue;
                this.I$1 = intValue2;
                this.L$2 = take;
                this.J$1 = currentThreadTimeMillis;
                i2 = 1;
                this.label = 1;
                if (DelayKt.delay(currentThreadTimeMillis, this) != coroutine_suspended) {
                }
            }
            i2 = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ShowNoiseDetectActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$setListener$1$1", m3970f = "ShowNoiseDetectActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$setListener$1$1 */
    /* loaded from: classes6.dex */
    public static final class C52581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6277p$;

        C52581(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52581 c52581 = new C52581(completion);
            c52581.f6277p$ = (CoroutineScope) obj;
            return c52581;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6277p$;
            ShowNoiseDetectActivity$setListener$1.this.this$0.playMusic("noise_alert1.mp3");
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ShowNoiseDetectActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$setListener$1$2", m3970f = "ShowNoiseDetectActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$setListener$1$2 */
    /* loaded from: classes6.dex */
    public static final class C52592 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6278p$;

        C52592(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52592 c52592 = new C52592(completion);
            c52592.f6278p$ = (CoroutineScope) obj;
            return c52592;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52592) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6278p$;
            str = ShowNoiseDetectActivity$setListener$1.this.this$0.TAG;
            Pdlog.m3273d(str, "stopPlayer");
            ShowNoiseDetectActivity$setListener$1.this.this$0.stopPlayer();
            return Unit.INSTANCE;
        }
    }
}
