package com.pudutech.voiceinteraction.component.utils;

import androidx.exifinterface.media.ExifInterface;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: Weak.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u0015\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005¢\u0006\u0002\u0010\u0006J&\u0010\t\u001a\u0004\u0018\u00018\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u00022\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0086\u0002¢\u0006\u0002\u0010\rJ.\u0010\u000e\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\b\u0010\u0010\u001a\u0004\u0018\u00018\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0011R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/Weak;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "initializer", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "weakReference", "Ljava/lang/ref/WeakReference;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Weak<T> {
    private WeakReference<T> weakReference;

    public Weak(Function0<? extends T> initializer) {
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        this.weakReference = new WeakReference<>(initializer.invoke());
    }

    public Weak() {
        this(new Function0() { // from class: com.pudutech.voiceinteraction.component.utils.Weak.1
            @Override // kotlin.jvm.functions.Function0
            public final Void invoke() {
                return null;
            }
        });
    }

    public final T getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkParameterIsNotNull(property, "property");
        return this.weakReference.get();
    }

    public final void setValue(Object thisRef, KProperty<?> property, T value) {
        Intrinsics.checkParameterIsNotNull(property, "property");
        this.weakReference = new WeakReference<>(value);
    }
}
