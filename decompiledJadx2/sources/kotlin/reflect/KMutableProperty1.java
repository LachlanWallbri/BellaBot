package kotlin.reflect;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: KProperty.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004:\u0001\u000eJ\u001d\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\rR\u001e\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, m3961d2 = {"Lkotlin/reflect/KMutableProperty1;", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlin/reflect/KProperty1;", "Lkotlin/reflect/KMutableProperty;", "setter", "Lkotlin/reflect/KMutableProperty1$Setter;", "getSetter", "()Lkotlin/reflect/KMutableProperty1$Setter;", TmpConstant.PROPERTY_IDENTIFIER_SET, "", "receiver", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Ljava/lang/Object;)V", "Setter", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface KMutableProperty1<T, R> extends KProperty1<T, R>, KMutableProperty<R> {

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: KProperty.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, m3961d2 = {"Lkotlin/reflect/KMutableProperty1$Setter;", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlin/reflect/KMutableProperty$Setter;", "Lkotlin/Function2;", "", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface Setter<T, R> extends KMutableProperty.Setter<R>, Function2<T, R, Unit> {
    }

    @Override // kotlin.reflect.KMutableProperty
    Setter<T, R> getSetter();

    void set(T receiver, R value);
}
