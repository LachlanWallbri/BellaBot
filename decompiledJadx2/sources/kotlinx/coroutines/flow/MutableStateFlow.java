package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: StateFlow.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002R\u0018\u0010\u0003\u001a\u00028\u0000X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m3961d2 = {"Lkotlinx/coroutines/flow/MutableStateFlow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/StateFlow;", ES6Iterator.VALUE_PROPERTY, "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface MutableStateFlow<T> extends StateFlow<T> {
    @Override // kotlinx.coroutines.flow.StateFlow
    T getValue();

    void setValue(T t);
}
