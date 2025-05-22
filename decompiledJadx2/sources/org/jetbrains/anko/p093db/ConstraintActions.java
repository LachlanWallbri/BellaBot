package org.jetbrains.anko.p093db;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: sqlTypes.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m3961d2 = {"Lorg/jetbrains/anko/db/ConstraintActions;", "", "(Ljava/lang/String;I)V", "toString", "", "SET_NULL", "SET_DEFAULT", "SET_RESTRICT", "CASCADE", "NO_ACTION", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public enum ConstraintActions {
    SET_NULL,
    SET_DEFAULT,
    SET_RESTRICT,
    CASCADE,
    NO_ACTION;

    @Override // java.lang.Enum
    public String toString() {
        return StringsKt.replace$default(super.toString(), "_", " ", false, 4, (Object) null);
    }
}
