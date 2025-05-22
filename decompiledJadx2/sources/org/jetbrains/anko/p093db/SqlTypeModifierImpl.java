package org.jetbrains.anko.p093db;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: sqlTypes.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0012\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lorg/jetbrains/anko/db/SqlTypeModifierImpl;", "Lorg/jetbrains/anko/db/SqlTypeModifier;", "modifier", "", "(Ljava/lang/String;)V", "getModifier", "()Ljava/lang/String;", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
class SqlTypeModifierImpl implements SqlTypeModifier {
    private final String modifier;

    public SqlTypeModifierImpl(String modifier) {
        Intrinsics.checkParameterIsNotNull(modifier, "modifier");
        this.modifier = modifier;
    }

    @Override // org.jetbrains.anko.p093db.SqlTypeModifier
    public String getModifier() {
        return this.modifier;
    }
}
