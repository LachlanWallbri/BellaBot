package com.amazonaws.mobileconnectors.p047s3.transferutility;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.google.gson.annotations.SerializedName;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ANY' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public abstract class TransferNetworkConnectionType {
    private static final /* synthetic */ TransferNetworkConnectionType[] $VALUES;

    @SerializedName("ANY")
    public static final TransferNetworkConnectionType ANY;
    private static final Log LOGGER;

    @SerializedName(TestConstantKt.WIFI)
    public static final TransferNetworkConnectionType WIFI = new TransferNetworkConnectionType(TestConstantKt.WIFI, 1) { // from class: com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.2
        @Override // com.amazonaws.mobileconnectors.p047s3.transferutility.TransferNetworkConnectionType
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 1;
        }
    };

    @SerializedName("MOBILE")
    public static final TransferNetworkConnectionType MOBILE = new TransferNetworkConnectionType("MOBILE", 2) { // from class: com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.3
        @Override // com.amazonaws.mobileconnectors.p047s3.transferutility.TransferNetworkConnectionType
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 0;
        }
    };
    private static final Map<String, TransferNetworkConnectionType> MAP = new HashMap();

    protected abstract boolean verify(NetworkInfo networkInfo);

    private TransferNetworkConnectionType(String str, int i) {
    }

    public static TransferNetworkConnectionType valueOf(String str) {
        return (TransferNetworkConnectionType) Enum.valueOf(TransferNetworkConnectionType.class, str);
    }

    public static TransferNetworkConnectionType[] values() {
        return (TransferNetworkConnectionType[]) $VALUES.clone();
    }

    static {
        int i = 0;
        ANY = new TransferNetworkConnectionType("ANY", i) { // from class: com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.1
            @Override // com.amazonaws.mobileconnectors.p047s3.transferutility.TransferNetworkConnectionType
            protected boolean verify(NetworkInfo networkInfo) {
                return networkInfo != null && networkInfo.isConnected();
            }
        };
        $VALUES = new TransferNetworkConnectionType[]{ANY, WIFI, MOBILE};
        TransferNetworkConnectionType[] values = values();
        int length = values.length;
        while (i < length) {
            TransferNetworkConnectionType transferNetworkConnectionType = values[i];
            MAP.put(transferNetworkConnectionType.toString(), transferNetworkConnectionType);
            i++;
        }
        LOGGER = LogFactory.getLog((Class<?>) TransferNetworkConnectionType.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConnected(ConnectivityManager connectivityManager) {
        return verify(connectivityManager.getActiveNetworkInfo());
    }

    public static TransferNetworkConnectionType getConnectionType(String str) {
        if (MAP.containsKey(str)) {
            return MAP.get(str);
        }
        LOGGER.error("Unknown connection type " + str + " transfer will have connection type set to ANY.");
        return ANY;
    }
}
