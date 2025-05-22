package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: specialBuiltinMembers.kt */
/* loaded from: classes2.dex */
public final class NameAndSignature {
    private final Name name;
    private final String signature;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NameAndSignature)) {
            return false;
        }
        NameAndSignature nameAndSignature = (NameAndSignature) obj;
        return Intrinsics.areEqual(this.name, nameAndSignature.name) && Intrinsics.areEqual(this.signature, nameAndSignature.signature);
    }

    public int hashCode() {
        Name name = this.name;
        int hashCode = (name != null ? name.hashCode() : 0) * 31;
        String str = this.signature;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "NameAndSignature(name=" + this.name + ", signature=" + this.signature + ")";
    }

    public NameAndSignature(Name name, String signature) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signature, "signature");
        this.name = name;
        this.signature = signature;
    }

    public final Name getName() {
        return this.name;
    }

    public final String getSignature() {
        return this.signature;
    }
}
