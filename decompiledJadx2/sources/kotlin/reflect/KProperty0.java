package kotlin.reflect;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: KProperty.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\fJ\r\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tJ\n\u0010\n\u001a\u0004\u0018\u00010\u000bH'R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, m3961d2 = {"Lkotlin/reflect/KProperty0;", "R", "Lkotlin/reflect/KProperty;", "Lkotlin/Function0;", "getter", "Lkotlin/reflect/KProperty0$Getter;", "getGetter", "()Lkotlin/reflect/KProperty0$Getter;", TmpConstant.PROPERTY_IDENTIFIER_GET, "()Ljava/lang/Object;", "getDelegate", "", "Getter", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface KProperty0<R> extends KProperty<R>, Function0<R> {

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: KProperty.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003¨\u0006\u0004"}, m3961d2 = {"Lkotlin/reflect/KProperty0$Getter;", "R", "Lkotlin/reflect/KProperty$Getter;", "Lkotlin/Function0;", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface Getter<R> extends KProperty.Getter<R>, Function0<R> {
    }

    R get();

    Object getDelegate();

    @Override // kotlin.reflect.KProperty
    Getter<R> getGetter();
}
