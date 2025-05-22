package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.pudutech.base.Pdlog;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

/* compiled from: LongDoublePressConstraintLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010 \u001a\u00020\u001bH\u0002J\u0012\u0010!\u001a\u00020\u00142\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010$\u001a\u00020\u00142\b\u0010%\u001a\u0004\u0018\u00010#H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006&"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/LongDoublePressConstraintLayout;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "countdownJob", "Lkotlinx/coroutines/Job;", "isLongDoublePress", "", "onLongDoublePressListener", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "v", "", "getOnLongDoublePressListener", "()Lkotlin/jvm/functions/Function1;", "setOnLongDoublePressListener", "(Lkotlin/jvm/functions/Function1;)V", "innerLongDoublePress", "onInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "onTouchEvent", "event", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LongDoublePressConstraintLayout extends ConstraintLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CoroutineScope coroutineScope;
    private Job countdownJob;
    private boolean isLongDoublePress;
    private Function1<? super View, Unit> onLongDoublePressListener;

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

    public final Function1<View, Unit> getOnLongDoublePressListener() {
        return this.onLongDoublePressListener;
    }

    public final void setOnLongDoublePressListener(Function1<? super View, Unit> function1) {
        this.onLongDoublePressListener = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LongDoublePressConstraintLayout(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LongDoublePressConstraintLayout";
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.coroutineExceptionHandler = new C4378xbc43a4cb(CoroutineExceptionHandler.INSTANCE, this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LongDoublePressConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        this.TAG = "LongDoublePressConstraintLayout";
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.coroutineExceptionHandler = new C4379xbc43a4cc(CoroutineExceptionHandler.INSTANCE, this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LongDoublePressConstraintLayout(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        this.TAG = "LongDoublePressConstraintLayout";
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.coroutineExceptionHandler = new C4380xbc43a4cd(CoroutineExceptionHandler.INSTANCE, this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0050, code lost:
    
        if (r0 != 6) goto L28;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onInterceptTouchEvent ev=");
        sb.append(ev != null ? Integer.valueOf(ev.getActionMasked()) : null);
        sb.append(',');
        sb.append(ev != null ? Integer.valueOf(ev.getAction()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (this.onLongDoublePressListener != null && ev != null) {
            int actionMasked = ev.getActionMasked();
            if (actionMasked == 0) {
                this.isLongDoublePress = false;
            } else {
                if (actionMasked != 3) {
                    if (actionMasked == 5) {
                        innerLongDoublePress();
                    }
                }
                Job job = this.countdownJob;
                if (job != null) {
                    JobKt__JobKt.cancel$default(job, "actionMasked:" + ev.getActionMasked(), null, 2, null);
                }
                this.countdownJob = (Job) null;
            }
        }
        if (this.isLongDoublePress) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004e, code lost:
    
        if (r0 != 6) goto L25;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent event) {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onTouchEvent ev=");
        sb.append(event != null ? Integer.valueOf(event.getActionMasked()) : null);
        sb.append(',');
        sb.append(event != null ? Integer.valueOf(event.getAction()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (this.onLongDoublePressListener != null && event != null) {
            int actionMasked = event.getActionMasked();
            if (actionMasked != 3) {
                if (actionMasked == 5) {
                    innerLongDoublePress();
                    this.isLongDoublePress = true;
                }
            }
            Job job = this.countdownJob;
            if (job != null) {
                JobKt__JobKt.cancel$default(job, "actionMasked:" + event.getActionMasked(), null, 2, null);
            }
            this.countdownJob = (Job) null;
            this.isLongDoublePress = false;
        }
        return true;
    }

    private final void innerLongDoublePress() {
        Job launch$default;
        if (this.countdownJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.coroutineExceptionHandler, null, new LongDoublePressConstraintLayout$innerLongDoublePress$1(this, null), 2, null);
            this.countdownJob = launch$default;
        }
    }
}
