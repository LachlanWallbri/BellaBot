package org.jetbrains.anko.p093db;

import com.iflytek.cloud.SpeechConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: sqlTypes.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\r"}, m3961d2 = {"Lorg/jetbrains/anko/db/SqlTypeImpl;", "Lorg/jetbrains/anko/db/SqlType;", "name", "", "modifiers", "(Ljava/lang/String;Ljava/lang/String;)V", "getModifiers", "()Ljava/lang/String;", "getName", SpeechConstant.MODE_PLUS, "m", "Lorg/jetbrains/anko/db/SqlTypeModifier;", "render", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
class SqlTypeImpl implements SqlType {
    private final String modifiers;
    private final String name;

    public SqlTypeImpl(String name, String str) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.modifiers = str;
    }

    public /* synthetic */ SqlTypeImpl(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? (String) null : str2);
    }

    public final String getModifiers() {
        return this.modifiers;
    }

    @Override // org.jetbrains.anko.p093db.SqlType
    public String getName() {
        return this.name;
    }

    @Override // org.jetbrains.anko.p093db.SqlType
    public String render() {
        if (this.modifiers == null) {
            return getName();
        }
        return getName() + ' ' + this.modifiers;
    }

    @Override // org.jetbrains.anko.p093db.SqlType
    public SqlType plus(SqlTypeModifier m) {
        String str;
        Intrinsics.checkParameterIsNotNull(m, "m");
        String name = getName();
        if (this.modifiers == null) {
            str = m.getModifier();
        } else {
            str = this.modifiers + ' ' + m.getModifier();
        }
        return new SqlTypeImpl(name, str);
    }
}
