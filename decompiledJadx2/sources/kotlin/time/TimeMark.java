package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.iflytek.cloud.SpeechConstant;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: TimeSource.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u0004H&ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u001b\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m3961d2 = {"Lkotlin/time/TimeMark;", "", "()V", "elapsedNow", "Lkotlin/time/Duration;", "()D", "hasNotPassedNow", "", "hasPassedNow", "minus", TypedValues.Transition.S_DURATION, "minus-LRDsOJo", "(D)Lkotlin/time/TimeMark;", SpeechConstant.MODE_PLUS, "plus-LRDsOJo", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class TimeMark {
    public abstract double elapsedNow();

    /* renamed from: plus-LRDsOJo */
    public TimeMark mo5474plusLRDsOJo(double duration) {
        return new AdjustedTimeMark(this, duration, null);
    }

    /* renamed from: minus-LRDsOJo, reason: not valid java name */
    public TimeMark m5525minusLRDsOJo(double duration) {
        return mo5474plusLRDsOJo(Duration.m5518unaryMinusimpl(duration));
    }

    public final boolean hasPassedNow() {
        return !Duration.m5498isNegativeimpl(elapsedNow());
    }

    public final boolean hasNotPassedNow() {
        return Duration.m5498isNegativeimpl(elapsedNow());
    }
}
