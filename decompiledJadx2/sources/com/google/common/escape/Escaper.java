package com.google.common.escape;

import com.google.common.base.Function;
import com.google.errorprone.annotations.DoNotMock;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use Escapers.nullEscaper() or another methods from the *Escapers classes")
/* loaded from: classes3.dex */
public abstract class Escaper {
    private final Function<String, String> asFunction = new Function<String, String>() { // from class: com.google.common.escape.Escaper.1
        @Override // com.google.common.base.Function, java.util.function.Function
        public String apply(String str) {
            return Escaper.this.escape(str);
        }
    };

    public abstract String escape(String str);

    public final Function<String, String> asFunction() {
        return this.asFunction;
    }
}
