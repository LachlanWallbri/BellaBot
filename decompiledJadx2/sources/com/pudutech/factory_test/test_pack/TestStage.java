package com.pudutech.factory_test.test_pack;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TestStage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/TestStage;", "", "str", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getStr", "()Ljava/lang/String;", "NULL", "INIT_TEST", "FINAL_TEST", "Companion", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public enum TestStage {
    NULL(""),
    INIT_TEST("初测"),
    FINAL_TEST("终测");


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String str;

    TestStage(String str) {
        this.str = str;
    }

    public final String getStr() {
        return this.str;
    }

    /* compiled from: TestStage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/TestStage$Companion;", "", "()V", "fromValue", "Lcom/pudutech/factory_test/test_pack/TestStage;", ES6Iterator.VALUE_PROPERTY, "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TestStage fromValue(String value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            for (TestStage testStage : TestStage.values()) {
                if (Intrinsics.areEqual(value, testStage.getStr())) {
                    return testStage;
                }
            }
            return TestStage.NULL;
        }
    }
}
