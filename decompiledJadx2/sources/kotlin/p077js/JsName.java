package kotlin.p077js;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: JsAnnotationsH.kt */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\"\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u0013\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"Lkotlin/js/JsName;", "", "name", "", "()Ljava/lang/String;", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
@interface JsName {
    String name();
}
