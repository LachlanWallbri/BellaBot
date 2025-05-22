package com.pudutech.factory_test.test_pack;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TestItem.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0017\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR(\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R$\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018¨\u0006!"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/TestItem;", "", "testStage", "Lcom/pudutech/factory_test/test_pack/TestStage;", "name", "", "testStatus", "Lcom/pudutech/factory_test/test_pack/TestStatus;", "action", "Lkotlin/Function0;", "", "(Lcom/pudutech/factory_test/test_pack/TestStage;Ljava/lang/String;Lcom/pudutech/factory_test/test_pack/TestStatus;Lkotlin/jvm/functions/Function0;)V", "TAG", "getAction", "()Lkotlin/jvm/functions/Function0;", ES6Iterator.VALUE_PROPERTY, "failDescription", "getFailDescription", "()Ljava/lang/String;", "setFailDescription", "(Ljava/lang/String;)V", "getName", "stage", "getStage", "()Lcom/pudutech/factory_test/test_pack/TestStage;", "setStage", "(Lcom/pudutech/factory_test/test_pack/TestStage;)V", "status", "getStatus", "()Lcom/pudutech/factory_test/test_pack/TestStatus;", "setStatus", "(Lcom/pudutech/factory_test/test_pack/TestStatus;)V", "getTestStage", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class TestItem {
    private final String TAG;
    private final Function0<Unit> action;
    private String failDescription;
    private final String name;
    private TestStage stage;
    private TestStatus status;
    private final TestStage testStage;

    public TestItem(TestStage testStage, String name, TestStatus testStatus, Function0<Unit> action) {
        Intrinsics.checkParameterIsNotNull(testStage, "testStage");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(testStatus, "testStatus");
        Intrinsics.checkParameterIsNotNull(action, "action");
        this.testStage = testStage;
        this.name = name;
        this.action = action;
        this.TAG = "TestItem";
        this.status = testStatus;
        this.stage = testStage;
    }

    public final Function0<Unit> getAction() {
        return this.action;
    }

    public final String getName() {
        return this.name;
    }

    public final TestStage getTestStage() {
        return this.testStage;
    }

    public final TestStatus getStatus() {
        return this.status;
    }

    public final void setStatus(TestStatus value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.status = value;
        Pdlog.m3275i(this.TAG, "set stage:" + this.testStage + " status=" + value + " name:" + this.name + ' ');
        Recorder recorder = Recorder.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append(this.testStage);
        sb.append(this.name);
        recorder.save(sb.toString(), value);
    }

    public final String getFailDescription() {
        return this.failDescription;
    }

    public final void setFailDescription(String str) {
        this.failDescription = str;
        if (str != null) {
            Pdlog.m3274e(this.testStage + this.name, "失败描述：" + str);
        }
    }

    public final TestStage getStage() {
        return this.stage;
    }

    public final void setStage(TestStage value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.stage = value;
        Pdlog.m3275i(this.TAG, "set stages=" + value + " name:" + this.name + ' ');
    }
}
