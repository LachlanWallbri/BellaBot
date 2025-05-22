package com.pudutech.pd_network.bean;

import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0005\u0006\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u0082\u0001\u0004\t\n\u000b\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/NetEnvironment;", "", "()V", "toString", "", "Dev", "PreTest", DataRecordKey.PRODUCT, "Test", "Lcom/pudutech/pd_network/bean/NetEnvironment$Dev;", "Lcom/pudutech/pd_network/bean/NetEnvironment$PreTest;", "Lcom/pudutech/pd_network/bean/NetEnvironment$Test;", "Lcom/pudutech/pd_network/bean/NetEnvironment$Product;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class NetEnvironment {

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/NetEnvironment$Dev;", "Lcom/pudutech/pd_network/bean/NetEnvironment;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Dev extends NetEnvironment {
        public static final Dev INSTANCE = new Dev();

        private Dev() {
            super(null);
        }
    }

    private NetEnvironment() {
    }

    public /* synthetic */ NetEnvironment(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/NetEnvironment$PreTest;", "Lcom/pudutech/pd_network/bean/NetEnvironment;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class PreTest extends NetEnvironment {
        public static final PreTest INSTANCE = new PreTest();

        private PreTest() {
            super(null);
        }
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/NetEnvironment$Test;", "Lcom/pudutech/pd_network/bean/NetEnvironment;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Test extends NetEnvironment {
        public static final Test INSTANCE = new Test();

        private Test() {
            super(null);
        }
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/NetEnvironment$Product;", "Lcom/pudutech/pd_network/bean/NetEnvironment;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Product extends NetEnvironment {
        public static final Product INSTANCE = new Product();

        private Product() {
            super(null);
        }
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        return simpleName;
    }
}
