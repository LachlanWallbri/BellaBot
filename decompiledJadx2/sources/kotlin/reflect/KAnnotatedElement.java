package kotlin.reflect;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: KAnnotatedElement.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lkotlin/reflect/KAnnotatedElement;", "", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface KAnnotatedElement {
    List<Annotation> getAnnotations();
}
