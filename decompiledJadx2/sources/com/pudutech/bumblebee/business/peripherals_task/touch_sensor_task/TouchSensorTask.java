package com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.HashMap;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;

/* compiled from: TouchSensorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032:\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004j\u0002`\f:\u0001.B\u0005¢\u0006\u0002\u0010\rJ\u0010\u0010$\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u0011H\u0002J\u001d\u0010&\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010\u00052\b\u0010(\u001a\u0004\u0018\u00010\tH\u0096\u0002J\u0010\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u0012H\u0002J\u0018\u0010+\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u00112\u0006\u0010'\u001a\u00020,H\u0016J\u0006\u0010-\u001a\u00020\u000bR\u000e\u0010\u000e\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchSensorTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchSensorListener;", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/TouchListener;", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "p0", "", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "()V", "TAG", "all", "Ljava/util/HashMap;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchPlace;", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchSensorTask$Checker;", "Lkotlin/collections/HashMap;", "<set-?>", "", "angryCnt", "getAngryCnt", "()I", "earChecker", "headCheker", "isAngry", "", "isCountAllowed", "isIgnore", "()Z", "setIgnore", "(Z)V", "lastAngryTimestamp", "", "FSM", "place", "invoke", "state", "description", "modify", "checker", "onTouch", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "reset", "Checker", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TouchSensorTask extends BaseMultiListenerImpl<TouchSensorListener> implements TouchListener, Function2<RobotState, String, Unit> {
    private int angryCnt;
    private Checker earChecker;
    private Checker headCheker;
    private boolean isAngry;
    private long lastAngryTimestamp;
    private final String TAG = "TouchSensorTask";
    private boolean isIgnore = true;
    private boolean isCountAllowed = true;
    private final HashMap<TouchPlace, Checker> all = new HashMap<>();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TouchPlace.values().length];

        static {
            $EnumSwitchMapping$0[TouchPlace.Head.ordinal()] = 1;
            $EnumSwitchMapping$0[TouchPlace.FunctionButton.ordinal()] = 2;
            $EnumSwitchMapping$0[TouchPlace.LeftEar.ordinal()] = 3;
            $EnumSwitchMapping$0[TouchPlace.RightEar.ordinal()] = 4;
        }
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
        invoke2(robotState, str);
        return Unit.INSTANCE;
    }

    public TouchSensorTask() {
        for (TouchPlace touchPlace : TouchPlace.values()) {
            this.all.put(touchPlace, new Checker(0, 0L));
        }
        this.earChecker = new Checker(0, 0L);
        this.headCheker = new Checker(0, 0L);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(RobotState state, String description) {
        this.isCountAllowed = state == RobotState.Pause || state == RobotState.Arrive || state == RobotState.Idle || state == RobotState.Moving || state == RobotState.Stuck || state == RobotState.Approaching || state == RobotState.Avoid;
        Pdlog.m3273d(this.TAG, "state=" + state + " change isCountAllowed to " + this.isCountAllowed);
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener
    public void onTouch(TouchPlace place, TouchState state) {
        Intrinsics.checkParameterIsNotNull(place, "place");
        Intrinsics.checkParameterIsNotNull(state, "state");
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onTouch=");
        sb.append(place);
        sb.append(" state=");
        sb.append(state);
        sb.append(" when movementTask=");
        BaseTaskInterface movementTask = Behavior.INSTANCE.getMovementTask();
        sb.append(movementTask != null ? movementTask.toString() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (state != TouchState.DOWN) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[place.ordinal()];
        if (i == 1 || i == 2 || i != 3) {
        }
        Pdlog.m3273d(this.TAG, "isIgnore=" + this.isIgnore + " isCountAllowed=" + this.isCountAllowed);
        if (this.isIgnore || !this.isCountAllowed) {
            return;
        }
        Checker checker = this.all.get(place);
        if (checker == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(checker, "all.get(it)!!");
        modify(checker);
        FSM(place);
        Pdlog.m3275i(this.TAG, "after FSM. isAngry=" + this.isAngry + " ear:" + this.earChecker + " head:" + this.headCheker + " angry=" + this.angryCnt);
    }

    /* renamed from: isIgnore, reason: from getter */
    public final boolean getIsIgnore() {
        return this.isIgnore;
    }

    public final void setIgnore(boolean z) {
        this.isIgnore = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TouchSensorTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchSensorTask$Checker;", "", "cnt", "", "lastTouchTimestamp", "", "(IJ)V", "getCnt", "()I", "setCnt", "(I)V", "getLastTouchTimestamp", "()J", "setLastTouchTimestamp", "(J)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class Checker {
        private int cnt;
        private long lastTouchTimestamp;

        public static /* synthetic */ Checker copy$default(Checker checker, int i, long j, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = checker.cnt;
            }
            if ((i2 & 2) != 0) {
                j = checker.lastTouchTimestamp;
            }
            return checker.copy(i, j);
        }

        /* renamed from: component1, reason: from getter */
        public final int getCnt() {
            return this.cnt;
        }

        /* renamed from: component2, reason: from getter */
        public final long getLastTouchTimestamp() {
            return this.lastTouchTimestamp;
        }

        public final Checker copy(int cnt, long lastTouchTimestamp) {
            return new Checker(cnt, lastTouchTimestamp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Checker)) {
                return false;
            }
            Checker checker = (Checker) other;
            return this.cnt == checker.cnt && this.lastTouchTimestamp == checker.lastTouchTimestamp;
        }

        public int hashCode() {
            int i = this.cnt * 31;
            long j = this.lastTouchTimestamp;
            return i + ((int) (j ^ (j >>> 32)));
        }

        public Checker(int i, long j) {
            this.cnt = i;
            this.lastTouchTimestamp = j;
        }

        public final int getCnt() {
            return this.cnt;
        }

        public final long getLastTouchTimestamp() {
            return this.lastTouchTimestamp;
        }

        public final void setCnt(int i) {
            this.cnt = i;
        }

        public final void setLastTouchTimestamp(long j) {
            this.lastTouchTimestamp = j;
        }

        public String toString() {
            return " cnt=" + this.cnt + "  lastTouchTimestamp=" + this.lastTouchTimestamp;
        }
    }

    public final int getAngryCnt() {
        return this.angryCnt;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorPlace, T] */
    /* JADX WARN: Type inference failed for: r8v1, types: [com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorPlace, T] */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorPlace, T] */
    /* JADX WARN: Type inference failed for: r8v23, types: [com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorPlace, T] */
    private final void FSM(TouchPlace place) {
        if (place == TouchPlace.LeftEar || place == TouchPlace.RightEar || place == TouchPlace.Head) {
            Pdlog.m3275i(this.TAG, "ear " + this.earChecker + "   head " + this.headCheker + "  angryCnt=" + this.angryCnt + " isAngry=" + this.isAngry);
            if (SystemClock.elapsedRealtime() - this.lastAngryTimestamp > 10000) {
                Pdlog.m3275i(this.TAG, "angry clear");
                if (this.isAngry) {
                    this.earChecker.setCnt(0);
                    this.headCheker.setCnt(0);
                }
                this.isAngry = false;
            }
            final Checker checker = place == TouchPlace.Head ? this.headCheker : this.earChecker;
            modify(checker);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = TouchSensorPlace.HEAD;
            if (place == TouchPlace.Head) {
                objectRef.element = TouchSensorPlace.HEAD;
            } else if (place == TouchPlace.RightEar) {
                objectRef.element = TouchSensorPlace.RIGHT_EAR;
            } else if (place == TouchPlace.LeftEar) {
                objectRef.element = TouchSensorPlace.LEFT_EAR;
            }
            if (this.earChecker.getCnt() <= 3 && this.headCheker.getCnt() <= 3) {
                this.isAngry = false;
                this.angryCnt = 0;
                getListeners().forEach(new Function1<TouchSensorListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorTask$FSM$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TouchSensorListener touchSensorListener) {
                        invoke2(touchSensorListener);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TouchSensorListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        TouchSensorPlace touchSensorPlace = (TouchSensorPlace) Ref.ObjectRef.this.element;
                        for (TouchEvent touchEvent : TouchEvent.values()) {
                            if (touchEvent.getValue() == checker.getCnt()) {
                                it.onTouchEvent(touchSensorPlace, touchEvent);
                                return;
                            }
                        }
                        throw new NoSuchElementException("Array contains no element matching the predicate.");
                    }
                });
                return;
            }
            if (this.isAngry) {
                this.angryCnt++;
                this.lastAngryTimestamp = SystemClock.elapsedRealtime();
                if (this.angryCnt > 3) {
                    getListeners().forEach(new Function1<TouchSensorListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorTask$FSM$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TouchSensorListener touchSensorListener) {
                            invoke2(touchSensorListener);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TouchSensorListener it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            it.onTouchEvent((TouchSensorPlace) Ref.ObjectRef.this.element, TouchEvent.ANGER_LEVEL2);
                        }
                    });
                    return;
                } else {
                    getListeners().forEach(new Function1<TouchSensorListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorTask$FSM$3
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TouchSensorListener touchSensorListener) {
                            invoke2(touchSensorListener);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TouchSensorListener it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            it.onTouchEvent((TouchSensorPlace) Ref.ObjectRef.this.element, TouchEvent.ANGER);
                        }
                    });
                    return;
                }
            }
            Pdlog.m3273d(this.TAG, "50% will be angry");
            if (Random.INSTANCE.nextInt(2) == 1) {
                Pdlog.m3273d(this.TAG, "still happy");
                getListeners().forEach(new Function1<TouchSensorListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorTask$FSM$4
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TouchSensorListener touchSensorListener) {
                        invoke2(touchSensorListener);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TouchSensorListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.onTouchEvent((TouchSensorPlace) Ref.ObjectRef.this.element, TouchEvent.HAPPY_LEVEL3);
                    }
                });
                return;
            }
            Pdlog.m3273d(this.TAG, "angry");
            this.isAngry = true;
            this.lastAngryTimestamp = SystemClock.elapsedRealtime();
            this.angryCnt++;
            getListeners().forEach(new Function1<TouchSensorListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorTask$FSM$5
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TouchSensorListener touchSensorListener) {
                    invoke2(touchSensorListener);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TouchSensorListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onTouchEvent((TouchSensorPlace) Ref.ObjectRef.this.element, TouchEvent.ANGER);
                }
            });
        }
    }

    public final void reset() {
        this.earChecker.setCnt(0);
        this.headCheker.setCnt(0);
        this.isAngry = false;
    }

    private final void modify(Checker checker) {
        if (checker.getLastTouchTimestamp() == 0) {
            checker.setLastTouchTimestamp(SystemClock.elapsedRealtime());
        }
        if (SystemClock.elapsedRealtime() - checker.getLastTouchTimestamp() > 10000 && !this.isAngry) {
            checker.setCnt(0);
        }
        checker.setCnt(checker.getCnt() + 1);
        checker.setLastTouchTimestamp(SystemClock.elapsedRealtime());
    }
}
