package org.jetbrains.anko.p093db;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: sqlTypes.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018\u001aC\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\u001a2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00182\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001f\"\u00020\u0001¢\u0006\u0002\u0010 \u001a\u000e\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#\u001a\u000e\u0010$\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#\u001a\u000e\u0010\u0014\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007\"\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007\"\u0011\u0010\u000e\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003\"\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007\"\u0011\u0010\u0014\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0003¨\u0006'"}, m3961d2 = {"AUTOINCREMENT", "Lorg/jetbrains/anko/db/SqlTypeModifier;", "getAUTOINCREMENT", "()Lorg/jetbrains/anko/db/SqlTypeModifier;", "BLOB", "Lorg/jetbrains/anko/db/SqlType;", "getBLOB", "()Lorg/jetbrains/anko/db/SqlType;", "INTEGER", "getINTEGER", "NOT_NULL", "getNOT_NULL", "NULL", "getNULL", "PRIMARY_KEY", "getPRIMARY_KEY", "REAL", "getREAL", "TEXT", "getTEXT", "UNIQUE", "getUNIQUE", "DEFAULT", ES6Iterator.VALUE_PROPERTY, "", "FOREIGN_KEY", "Lkotlin/Pair;", "columnName", "referenceTable", "referenceColumn", "actions", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lorg/jetbrains/anko/db/SqlTypeModifier;)Lkotlin/Pair;", "ON_DELETE", "constraintActions", "Lorg/jetbrains/anko/db/ConstraintActions;", "ON_UPDATE", "conflictClause", "Lorg/jetbrains/anko/db/ConflictClause;", "sqlite-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SqlTypesKt {
    private static final SqlType NULL = new SqlTypeImpl("NULL", null, 2, null);
    private static final SqlType INTEGER = new SqlTypeImpl("INTEGER", null, 2, null);
    private static final SqlType REAL = new SqlTypeImpl("REAL", null, 2, null);
    private static final SqlType TEXT = new SqlTypeImpl("TEXT", null, 2, null);
    private static final SqlType BLOB = new SqlTypeImpl("BLOB", null, 2, null);
    private static final SqlTypeModifier PRIMARY_KEY = new SqlTypeModifierImpl("PRIMARY KEY");
    private static final SqlTypeModifier NOT_NULL = new SqlTypeModifierImpl("NOT NULL");
    private static final SqlTypeModifier AUTOINCREMENT = new SqlTypeModifierImpl("AUTOINCREMENT");
    private static final SqlTypeModifier UNIQUE = new SqlTypeModifierImpl("UNIQUE");

    public static final SqlType getNULL() {
        return NULL;
    }

    public static final SqlType getINTEGER() {
        return INTEGER;
    }

    public static final SqlType getREAL() {
        return REAL;
    }

    public static final SqlType getTEXT() {
        return TEXT;
    }

    public static final SqlType getBLOB() {
        return BLOB;
    }

    public static final SqlTypeModifier ON_UPDATE(ConstraintActions constraintActions) {
        Intrinsics.checkParameterIsNotNull(constraintActions, "constraintActions");
        return new SqlTypeModifierImpl("ON UPDATE " + constraintActions);
    }

    public static final SqlTypeModifier ON_DELETE(ConstraintActions constraintActions) {
        Intrinsics.checkParameterIsNotNull(constraintActions, "constraintActions");
        return new SqlTypeModifierImpl("ON DELETE " + constraintActions);
    }

    public static final Pair<String, SqlType> FOREIGN_KEY(String columnName, String referenceTable, String referenceColumn, SqlTypeModifier... actions) {
        Intrinsics.checkParameterIsNotNull(columnName, "columnName");
        Intrinsics.checkParameterIsNotNull(referenceTable, "referenceTable");
        Intrinsics.checkParameterIsNotNull(referenceColumn, "referenceColumn");
        Intrinsics.checkParameterIsNotNull(actions, "actions");
        StringBuilder sb = new StringBuilder();
        sb.append("FOREIGN KEY(");
        sb.append(columnName);
        sb.append(") REFERENCES ");
        sb.append(referenceTable);
        sb.append('(');
        sb.append(referenceColumn);
        sb.append(')');
        ArrayList arrayList = new ArrayList(actions.length);
        for (SqlTypeModifier sqlTypeModifier : actions) {
            arrayList.add(sqlTypeModifier.getModifier());
        }
        sb.append(CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, new Function1<String, String>() { // from class: org.jetbrains.anko.db.SqlTypesKt$FOREIGN_KEY$2
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return ' ' + it;
            }
        }, 30, null));
        return TuplesKt.m3968to("", new SqlTypeImpl(sb.toString(), null, 2, null));
    }

    public static final SqlTypeModifier getPRIMARY_KEY() {
        return PRIMARY_KEY;
    }

    public static final SqlTypeModifier getNOT_NULL() {
        return NOT_NULL;
    }

    public static final SqlTypeModifier getAUTOINCREMENT() {
        return AUTOINCREMENT;
    }

    public static final SqlTypeModifier getUNIQUE() {
        return UNIQUE;
    }

    public static final SqlTypeModifier UNIQUE(ConflictClause conflictClause) {
        Intrinsics.checkParameterIsNotNull(conflictClause, "conflictClause");
        return new SqlTypeModifierImpl("UNIQUE ON CONFLICT " + conflictClause);
    }

    public static final SqlTypeModifier DEFAULT(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return new SqlTypeModifierImpl("DEFAULT " + value);
    }
}
