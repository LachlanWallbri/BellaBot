package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.NavArgs;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NavArgsLazy.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B!\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, m3961d2 = {"Landroidx/navigation/NavArgsLazy;", "Args", "Landroidx/navigation/NavArgs;", "Lkotlin/Lazy;", "navArgsClass", "Lkotlin/reflect/KClass;", "argumentProducer", "Lkotlin/Function0;", "Landroid/os/Bundle;", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)V", "cached", "Landroidx/navigation/NavArgs;", ES6Iterator.VALUE_PROPERTY, "getValue", "()Landroidx/navigation/NavArgs;", "isInitialized", "", "navigation-common-ktx_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class NavArgsLazy<Args extends NavArgs> implements Lazy<Args> {
    private final Function0<Bundle> argumentProducer;
    private Args cached;
    private final KClass<Args> navArgsClass;

    public NavArgsLazy(KClass<Args> navArgsClass, Function0<Bundle> argumentProducer) {
        Intrinsics.checkParameterIsNotNull(navArgsClass, "navArgsClass");
        Intrinsics.checkParameterIsNotNull(argumentProducer, "argumentProducer");
        this.navArgsClass = navArgsClass;
        this.argumentProducer = argumentProducer;
    }

    @Override // kotlin.Lazy
    public Args getValue() {
        Args args = this.cached;
        if (args != null) {
            return args;
        }
        Bundle invoke = this.argumentProducer.invoke();
        Method method = NavArgsLazyKt.getMethodMap().get(this.navArgsClass);
        if (method == null) {
            Class javaClass = JvmClassMappingKt.getJavaClass((KClass) this.navArgsClass);
            Class<Bundle>[] methodSignature = NavArgsLazyKt.getMethodSignature();
            method = javaClass.getMethod("fromBundle", (Class[]) Arrays.copyOf(methodSignature, methodSignature.length));
            NavArgsLazyKt.getMethodMap().put(this.navArgsClass, method);
            Intrinsics.checkExpressionValueIsNotNull(method, "navArgsClass.java.getMet…hod\n                    }");
        }
        Object invoke2 = method.invoke(null, invoke);
        if (invoke2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type Args");
        }
        Args args2 = (Args) invoke2;
        this.cached = args2;
        return args2;
    }

    @Override // kotlin.Lazy
    public boolean isInitialized() {
        return this.cached != null;
    }
}
