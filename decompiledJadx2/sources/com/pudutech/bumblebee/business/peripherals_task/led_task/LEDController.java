package com.pudutech.bumblebee.business.peripherals_task.led_task;

import android.os.Handler;
import android.os.Looper;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.led_task.LEDController;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LEDController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001(B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001f\u001a\u00020\rH\u0002J\u0018\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#J\b\u0010$\u001a\u00020\rH\u0002J\u0006\u0010%\u001a\u00020\rJ\u0012\u0010&\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010!H\u0002J\u0012\u0010&\u001a\u00020\u00032\b\u0010'\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010&\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDController;", "", "name", "", "looper", "Landroid/os/Looper;", "(Ljava/lang/String;Landroid/os/Looper;)V", "TAG", "control", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Frame;", "Lkotlin/ParameterName;", TypedValues.Attributes.S_FRAME, "", "getControl", "()Lkotlin/jvm/functions/Function1;", "setControl", "(Lkotlin/jvm/functions/Function1;)V", "current", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDController$task;", "handler", "Landroid/os/Handler;", "index", "", "lastColor", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Color;", "mode", "stepHandler", "cloneColor", "src", "des", "play", "animation", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Animation;", "listener", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDListener;", "playNext", "stop", "toString", TypedValues.Custom.S_COLOR, "task", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LEDController {
    private String TAG;
    private Function1<? super Frame, Unit> control;
    private task current;
    private final Handler handler;
    private int index;
    private Color lastColor;
    private task mode;
    private final Handler stepHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LEDController.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDController$task;", "", "type", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Type;", "animation", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Animation;", "listener", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDListener;", "(Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Type;Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Animation;Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDListener;)V", "getAnimation", "()Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Animation;", "getListener", "()Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDListener;", "getType", "()Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Type;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class task {
        private final Animation animation;
        private final LEDListener listener;
        private final Type type;

        public static /* synthetic */ task copy$default(task taskVar, Type type, Animation animation, LEDListener lEDListener, int i, Object obj) {
            if ((i & 1) != 0) {
                type = taskVar.type;
            }
            if ((i & 2) != 0) {
                animation = taskVar.animation;
            }
            if ((i & 4) != 0) {
                lEDListener = taskVar.listener;
            }
            return taskVar.copy(type, animation, lEDListener);
        }

        /* renamed from: component1, reason: from getter */
        public final Type getType() {
            return this.type;
        }

        /* renamed from: component2, reason: from getter */
        public final Animation getAnimation() {
            return this.animation;
        }

        /* renamed from: component3, reason: from getter */
        public final LEDListener getListener() {
            return this.listener;
        }

        public final task copy(Type type, Animation animation, LEDListener listener) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            return new task(type, animation, listener);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof task)) {
                return false;
            }
            task taskVar = (task) other;
            return Intrinsics.areEqual(this.type, taskVar.type) && Intrinsics.areEqual(this.animation, taskVar.animation) && Intrinsics.areEqual(this.listener, taskVar.listener);
        }

        public int hashCode() {
            Type type = this.type;
            int hashCode = (type != null ? type.hashCode() : 0) * 31;
            Animation animation = this.animation;
            int hashCode2 = (hashCode + (animation != null ? animation.hashCode() : 0)) * 31;
            LEDListener lEDListener = this.listener;
            return hashCode2 + (lEDListener != null ? lEDListener.hashCode() : 0);
        }

        public String toString() {
            return "task(type=" + this.type + ", animation=" + this.animation + ", listener=" + this.listener + ")";
        }

        public task(Type type, Animation animation, LEDListener lEDListener) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.type = type;
            this.animation = animation;
            this.listener = lEDListener;
        }

        public final Animation getAnimation() {
            return this.animation;
        }

        public final LEDListener getListener() {
            return this.listener;
        }

        public final Type getType() {
            return this.type;
        }
    }

    public LEDController(String name, Looper looper) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(looper, "looper");
        this.TAG = "LedController";
        this.mode = new task(Type.MODE, null, null);
        this.current = this.mode;
        this.lastColor = new Color();
        this.TAG += '_' + name;
        this.handler = new Handler(looper);
        this.stepHandler = new Handler(looper);
    }

    public final void play(final Animation animation, final LEDListener listener) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        Pdlog.m3275i(this.TAG, "set play size=" + animation.frames.size() + ", listener=" + listener);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("new animation=");
        sb.append(toString(animation));
        Pdlog.m3275i(str, sb.toString());
        this.stepHandler.post(new Runnable() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_task.LEDController$play$1
            @Override // java.lang.Runnable
            public final void run() {
                Handler handler;
                String str2;
                Color color;
                LEDController.task taskVar;
                LEDController.task taskVar2;
                LEDController.task taskVar3;
                LEDController.task taskVar4;
                LEDController.task taskVar5;
                LEDController.task taskVar6;
                handler = LEDController.this.handler;
                handler.removeMessages(0);
                str2 = LEDController.this.TAG;
                Pdlog.m3275i(str2, "in step handler. play " + animation);
                Color color2 = new Color();
                LEDController lEDController = LEDController.this;
                color = lEDController.lastColor;
                lEDController.cloneColor(color, color2);
                ArrayList<Frame> arrayList = animation.frames;
                Intrinsics.checkExpressionValueIsNotNull(arrayList, "animation.frames");
                for (Frame frame : arrayList) {
                    if (frame.head.sameAsLast) {
                        LEDController lEDController2 = LEDController.this;
                        Color color3 = frame.head;
                        Intrinsics.checkExpressionValueIsNotNull(color3, "it.head");
                        lEDController2.cloneColor(color2, color3);
                    }
                    if (frame.end.sameAsLast) {
                        LEDController lEDController3 = LEDController.this;
                        Color color4 = frame.head;
                        Intrinsics.checkExpressionValueIsNotNull(color4, "it.head");
                        Color color5 = frame.end;
                        Intrinsics.checkExpressionValueIsNotNull(color5, "it.end");
                        lEDController3.cloneColor(color4, color5);
                    }
                    LEDController lEDController4 = LEDController.this;
                    Color color6 = frame.end;
                    Intrinsics.checkExpressionValueIsNotNull(color6, "it.end");
                    lEDController4.cloneColor(color2, color6);
                }
                String str3 = animation.type;
                Intrinsics.checkExpressionValueIsNotNull(str3, "animation.type");
                LEDController.task taskVar7 = new LEDController.task(Type.valueOf(str3), animation, listener);
                Type type = taskVar7.getType();
                taskVar = LEDController.this.mode;
                if (type == taskVar.getType()) {
                    LEDController.this.mode = taskVar7;
                    taskVar6 = LEDController.this.current;
                    LEDListener listener2 = taskVar6.getListener();
                    if (listener2 != null) {
                        listener2.onEvent(LEDAnimationEvent.STOP);
                    }
                } else {
                    taskVar2 = LEDController.this.current;
                    if (taskVar2.getType() == Type.MODE) {
                        taskVar5 = LEDController.this.current;
                        LEDListener listener3 = taskVar5.getListener();
                        if (listener3 != null) {
                            listener3.onEvent(LEDAnimationEvent.PAUSE);
                        }
                    } else {
                        taskVar3 = LEDController.this.current;
                        if (taskVar3.getType() == Type.TEMP) {
                            taskVar4 = LEDController.this.current;
                            LEDListener listener4 = taskVar4.getListener();
                            if (listener4 != null) {
                                listener4.onEvent(LEDAnimationEvent.STOP);
                            }
                        }
                    }
                }
                LEDController.this.current = taskVar7;
                LEDController.this.index = 0;
                LEDController.this.play();
            }
        });
    }

    public final void stop() {
        Pdlog.m3275i(this.TAG, "stop");
        this.stepHandler.post(new Runnable() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_task.LEDController$stop$1
            @Override // java.lang.Runnable
            public final void run() {
                Handler handler;
                LEDController.task taskVar;
                handler = LEDController.this.handler;
                handler.removeMessages(0);
                LEDController.this.mode = new LEDController.task(Type.MODE, null, null);
                LEDController lEDController = LEDController.this;
                taskVar = lEDController.mode;
                lEDController.current = taskVar;
                LEDController.this.index = 0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void play() {
        LEDListener listener;
        if (this.current.getAnimation() == null) {
            Pdlog.m3277w(this.TAG, "animation is null");
            Unit unit = Unit.INSTANCE;
        }
        Animation animation = this.current.getAnimation();
        if (animation != null) {
            if (animation.frames.size() == 0) {
                Pdlog.m3277w(this.TAG, "animation frames is null");
                return;
            }
            if (this.index == 0 && (listener = this.current.getListener()) != null) {
                listener.onEvent(LEDAnimationEvent.PLAYING);
            }
            Pdlog.m3275i(this.TAG, "play index=" + this.index + " type=" + this.current.getType());
            Frame frame = animation.frames.get(this.index);
            if (this.control == null) {
                Pdlog.m3277w(this.TAG, "no control.please check robot interface");
                Unit unit2 = Unit.INSTANCE;
            }
            Function1<? super Frame, Unit> function1 = this.control;
            if (function1 != null) {
                Intrinsics.checkExpressionValueIsNotNull(frame, "frame");
                function1.invoke(frame);
            }
            Color color = frame.end;
            Intrinsics.checkExpressionValueIsNotNull(color, "frame.end");
            cloneColor(color, this.lastColor);
            this.handler.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_task.LEDController$play$$inlined$let$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    LEDController.this.playNext();
                }
            }, frame.duration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playNext() {
        this.index++;
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("playNext index=");
        sb.append(this.index);
        sb.append(" frames.size=");
        Animation animation = this.current.getAnimation();
        if (animation == null) {
            Intrinsics.throwNpe();
        }
        sb.append(animation.frames.size());
        sb.append(" type=");
        sb.append(this.current.getType());
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        int i = this.index;
        Animation animation2 = this.current.getAnimation();
        if (animation2 == null) {
            Intrinsics.throwNpe();
        }
        if (i >= animation2.frames.size()) {
            this.index = 0;
            LEDListener listener = this.current.getListener();
            if (listener != null) {
                listener.onEvent(LEDAnimationEvent.COMPLETE_ONCE);
            }
            if (this.current.getType() == Type.TEMP) {
                this.current = this.mode;
                LEDListener listener2 = this.current.getListener();
                if (listener2 != null) {
                    listener2.onEvent(LEDAnimationEvent.RESUME);
                }
            }
        }
        play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cloneColor(Color src, Color des) {
        des.f4603R = src.f4603R;
        des.f4602G = src.f4602G;
        des.f4601B = src.f4601B;
    }

    public final Function1<Frame, Unit> getControl() {
        return this.control;
    }

    public final void setControl(Function1<? super Frame, Unit> function1) {
        this.control = function1;
    }

    private final String toString(Animation animation) {
        if (animation == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<Frame> arrayList = animation.frames;
        Intrinsics.checkExpressionValueIsNotNull(arrayList, "it.frames");
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append('{' + toString((Frame) it.next()) + '}');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "stringBuilder.toString()");
        return sb2;
    }

    private final String toString(Frame frame) {
        if (frame == null) {
            return "null";
        }
        return toString(frame.head) + " | " + toString(frame.end) + " | " + frame.duration;
    }

    private final String toString(Color color) {
        if (color == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(color.f4603R);
        sb.append(' ');
        sb.append(color.f4602G);
        sb.append(' ');
        sb.append(color.f4601B);
        sb.append(' ');
        sb.append(color.sameAsLast);
        return sb.toString();
    }
}
