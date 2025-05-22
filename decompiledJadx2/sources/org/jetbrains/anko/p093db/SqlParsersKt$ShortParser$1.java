package org.jetbrains.anko.p093db;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParsers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "p1", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
final class SqlParsersKt$ShortParser$1 extends FunctionReference implements Function1<Long, Short> {
    public static final SqlParsersKt$ShortParser$1 INSTANCE = new SqlParsersKt$ShortParser$1();

    SqlParsersKt$ShortParser$1() {
        super(1);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "toShort";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(Long.TYPE);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "shortValue()S";
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Short invoke(Long l) {
        return Short.valueOf(invoke(l.longValue()));
    }

    public final short invoke(long j) {
        return (short) j;
    }
}
