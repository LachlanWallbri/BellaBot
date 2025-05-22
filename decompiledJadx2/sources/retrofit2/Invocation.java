package retrofit2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public final class Invocation {
    private final List<?> arguments;
    private final Method method;

    /* renamed from: of */
    public static Invocation m4258of(Method method, List<?> list) {
        Utils.checkNotNull(method, "method == null");
        Utils.checkNotNull(list, "arguments == null");
        return new Invocation(method, new ArrayList(list));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Invocation(Method method, List<?> list) {
        this.method = method;
        this.arguments = Collections.unmodifiableList(list);
    }

    public Method method() {
        return this.method;
    }

    public List<?> arguments() {
        return this.arguments;
    }

    public String toString() {
        return String.format("%s.%s() %s", this.method.getDeclaringClass().getName(), this.method.getName(), this.arguments);
    }
}
