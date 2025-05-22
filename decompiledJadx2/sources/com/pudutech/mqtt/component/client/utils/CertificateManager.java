package com.pudutech.mqtt.component.client.utils;

import android.content.Context;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.google.gson.Gson;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.text.Charsets;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: CertificateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\b\u0010\f\u001a\u0004\u0018\u00010\tJ\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/utils/CertificateManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "gson", "Lcom/google/gson/Gson;", "params", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "getPassword", "getUserName", "hasCertificateFile", "", "readFileToParams", "", "Companion", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CertificateManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Context context;
    private final Gson gson;
    private LinkedHashMap<String, String> params;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: CertificateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/utils/CertificateManager$Companion;", "Lcom/pudutech/mqtt/component/client/utils/SingletonHolder;", "Lcom/pudutech/mqtt/component/client/utils/CertificateManager;", "Landroid/content/Context;", "()V", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<CertificateManager, Context> {

        /* JADX WARN: Classes with same name are omitted:
          classes5.dex
         */
        /* compiled from: CertificateManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/mqtt/component/client/utils/CertificateManager;", "p1", "Landroid/content/Context;", "Lkotlin/ParameterName;", "name", "context", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
        /* renamed from: com.pudutech.mqtt.component.client.utils.CertificateManager$Companion$1 */
        /* loaded from: classes6.dex */
        static final /* synthetic */ class C54621 extends FunctionReference implements Function1<Context, CertificateManager> {
            public static final C54621 INSTANCE = new C54621();

            C54621() {
                super(1);
            }

            @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "<init>";
            }

            @Override // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(CertificateManager.class);
            }

            @Override // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "<init>(Landroid/content/Context;)V";
            }

            @Override // kotlin.jvm.functions.Function1
            public final CertificateManager invoke(Context p1) {
                Intrinsics.checkParameterIsNotNull(p1, "p1");
                return new CertificateManager(p1, null);
            }
        }

        private Companion() {
            super(C54621.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private CertificateManager(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    public /* synthetic */ CertificateManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x001f A[Catch: Exception -> 0x0031, TryCatch #0 {Exception -> 0x0031, blocks: (B:3:0x0001, B:5:0x0010, B:14:0x001f, B:16:0x0023), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean hasCertificateFile() {
        String[] list;
        boolean z;
        try {
            list = this.context.getAssets().list("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null) {
            if (!(list.length == 0)) {
                z = false;
                if (!z) {
                    return false;
                }
                for (String str : list) {
                    if (Intrinsics.areEqual(str, "certificate")) {
                        return true;
                    }
                }
                return false;
            }
        }
        z = true;
        if (!z) {
        }
    }

    public final String getUserName() {
        if (!hasCertificateFile()) {
            return null;
        }
        readFileToParams();
        LinkedHashMap<String, String> linkedHashMap = this.params;
        if (linkedHashMap != null) {
            return linkedHashMap.get("userName");
        }
        return null;
    }

    public final String getPassword() {
        if (!hasCertificateFile()) {
            return null;
        }
        readFileToParams();
        LinkedHashMap<String, String> linkedHashMap = this.params;
        if (linkedHashMap != null) {
            return linkedHashMap.get(CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD);
        }
        return null;
    }

    private final void readFileToParams() {
        if (this.params != null) {
            return;
        }
        try {
            InputStream open = this.context.getAssets().open("certificate");
            Intrinsics.checkExpressionValueIsNotNull(open, "context.assets.open(\"certificate\")");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            this.params = (LinkedHashMap) this.gson.fromJson(AES.decrypt(new String(bArr, Charsets.UTF_8)), (Type) LinkedHashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
