package com.pudutech.bumblebee.robot.pallet;

import com.iflytek.speech.UtilityConfig;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PalletProtocol.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0007J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J\u001e\u0010!\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0016ø\u0001\u0000¢\u0006\u0002\u0010#R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/pallet/PalletProtocol;", "", "()V", "TAG", "", "deviceMap", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDevice;", "Lkotlin/UByte;", "pallets", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "Lkotlin/collections/ArrayList;", "getPallets", "()Ljava/util/ArrayList;", "pallets$delegate", "Lkotlin/Lazy;", "requestCMD", "", "getRequestCMD", "()[B", "checkIsPallet", "", UtilityConfig.KEY_DEVICE_INFO, "getDevice", "palletId", "getDevice-7apg3OU", "(B)Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDevice;", "parse", "cmd", "Lkotlin/UByteArray;", "parse-GBYM_sE", "([B)Z", "powerCMD", "onOrOff", "(Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDevice;Z)[B", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PalletProtocol {

    /* renamed from: pallets$delegate, reason: from kotlin metadata */
    private static final Lazy pallets;
    private static final byte[] requestCMD;
    public static final PalletProtocol INSTANCE = new PalletProtocol();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final Map<PeripheralDevice, UByte> deviceMap = MapsKt.mapOf(TuplesKt.m3968to(PeripheralDevice.Pallet1, UByte.m4522boximpl((byte) 1)), TuplesKt.m3968to(PeripheralDevice.Pallet2, UByte.m4522boximpl((byte) 2)), TuplesKt.m3968to(PeripheralDevice.Pallet3, UByte.m4522boximpl((byte) 3)), TuplesKt.m3968to(PeripheralDevice.Pallet4, UByte.m4522boximpl((byte) 4)));

    public final ArrayList<Pallet> getPallets() {
        return (ArrayList) pallets.getValue();
    }

    static {
        byte[] copyOf = Arrays.copyOf(new byte[]{-111, 0, 0, 0, 0, 0, 0}, 7);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        requestCMD = copyOf;
        pallets = LazyKt.lazy(new Function0<ArrayList<Pallet>>() { // from class: com.pudutech.bumblebee.robot.pallet.PalletProtocol$pallets$2
            @Override // kotlin.jvm.functions.Function0
            public final ArrayList<Pallet> invoke() {
                ArrayList<Pallet> arrayList = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                    Pallet pallet = new Pallet();
                    pallet.setPalletId(i);
                    pallet.setInstalled(true);
                    pallet.setPlaced(false);
                    pallet.setPowerOn(true);
                    arrayList.add(pallet);
                }
                return arrayList;
            }
        });
    }

    private PalletProtocol() {
    }

    /* renamed from: getDevice-7apg3OU, reason: not valid java name */
    public final PeripheralDevice m4329getDevice7apg3OU(byte palletId) {
        for (Map.Entry<PeripheralDevice, UByte> entry : deviceMap.entrySet()) {
            if (entry.getValue().getData() == palletId) {
                return entry.getKey();
            }
        }
        return null;
    }

    public final boolean checkIsPallet(PeripheralDevice device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        return deviceMap.containsKey(device);
    }

    public final byte[] powerCMD(PeripheralDevice device, boolean onOrOff) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        return new byte[]{-111, 3, ((UByte) MapsKt.getValue(deviceMap, device)).getData(), onOrOff ? (byte) 1 : (byte) 0, 0, 0, 0};
    }

    public final byte[] getRequestCMD() {
        return requestCMD;
    }

    /* renamed from: parse-GBYM_sE, reason: not valid java name */
    public final boolean m4330parseGBYM_sE(byte[] cmd) {
        Intrinsics.checkParameterIsNotNull(cmd, "cmd");
        byte b = (byte) 2;
        if (UByteArray.m4577getimpl(cmd, 1) != UByte.m4528constructorimpl(b)) {
            return false;
        }
        byte m4577getimpl = UByteArray.m4577getimpl(cmd, 2);
        ArrayList<Pallet> pallets2 = getPallets();
        ArrayList<Pallet> arrayList = new ArrayList();
        for (Object obj : pallets2) {
            if (UByte.m4528constructorimpl((byte) ((Pallet) obj).getPalletId()) == m4577getimpl) {
                arrayList.add(obj);
            }
        }
        for (Pallet pallet : arrayList) {
            byte m4577getimpl2 = UByteArray.m4577getimpl(cmd, 3);
            byte b2 = (byte) 1;
            if (m4577getimpl2 == UByte.m4528constructorimpl(b2)) {
                pallet.setInstalled(UByteArray.m4577getimpl(cmd, 4) == UByte.m4528constructorimpl(b2));
            } else if (m4577getimpl2 == UByte.m4528constructorimpl(b)) {
                pallet.setPlaced(UByteArray.m4577getimpl(cmd, 4) == UByte.m4528constructorimpl(b2));
            } else if (m4577getimpl2 == UByte.m4528constructorimpl((byte) 3)) {
                pallet.setPowerOn(UByteArray.m4577getimpl(cmd, 4) == UByte.m4528constructorimpl(b2));
            }
        }
        return true;
    }
}
