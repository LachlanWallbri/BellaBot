package com.pudutech.factory_test.test_pack;

import kotlin.Metadata;

/* compiled from: TestStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/TestStatus;", "", "str", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getStr", "()Ljava/lang/String;", "UNTESTED", "TESTING", "SUCCESS", "FAIL", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public enum TestStatus {
    UNTESTED("未测试"),
    TESTING("测试中"),
    SUCCESS("测试成功"),
    FAIL("测试失败");

    private final String str;

    TestStatus(String str) {
        this.str = str;
    }

    public final String getStr() {
        return this.str;
    }
}
