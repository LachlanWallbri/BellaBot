package org.jetbrains.anko.p093db;

import com.iflytek.cloud.SpeechConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: sqlTypes.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ\u0011\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH¦\u0002J\b\u0010\t\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000b"}, m3961d2 = {"Lorg/jetbrains/anko/db/SqlType;", "", "name", "", "getName", "()Ljava/lang/String;", SpeechConstant.MODE_PLUS, "m", "Lorg/jetbrains/anko/db/SqlTypeModifier;", "render", "Companion", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public interface SqlType {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    String getName();

    SqlType plus(SqlTypeModifier m);

    String render();

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* compiled from: sqlTypes.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lorg/jetbrains/anko/db/SqlType$Companion;", "", "()V", "create", "Lorg/jetbrains/anko/db/SqlType;", "name", "", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
    /* loaded from: classes9.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final SqlType create(String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new SqlTypeImpl(name, null, 2, null);
        }
    }
}
