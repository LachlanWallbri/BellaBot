package com.pudu.library.loracall.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.loracall.library.BR;
import com.pudu.loracall.library.C3965R;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LoRaConfigParam.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u0004\u0018\u00010\u0003J\u0017\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010,J\u0010\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020*H\u0002J\u0010\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020!H\u0002J\b\u00101\u001a\u00020\u0006H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR&\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001a\u0010\u0011\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u001a\u0010\u0014\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\nR\u001a\u0010\u0017\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\u001a\u0010\u001a\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\u001a\u0010\u001d\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\nR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00062"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoRaConfigParam;", "Landroidx/databinding/BaseObservable;", "data", "", "([B)V", "broadCastTime", "", "getBroadCastTime", "()Ljava/lang/String;", "setBroadCastTime", "(Ljava/lang/String;)V", "getData", "()[B", ES6Iterator.VALUE_PROPERTY, "expand", "getExpand", "setExpand", "networkId", "getNetworkId", "setNetworkId", "networkP1", "getNetworkP1", "setNetworkP1", "networkP2", "getNetworkP2", "setNetworkP2", "receivePL", "getReceivePL", "setReceivePL", "sendPower", "getSendPower", "setSendPower", "type", "", "getType", "()I", "setType", "(I)V", "checkData", "", "codingData", "getExpandByte", "", "string", "(Ljava/lang/String;)Ljava/lang/Byte;", "getExpandString", "byte", "getShowType", "mType", "toString", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaConfigParam extends BaseObservable {
    private String broadCastTime;
    private final byte[] data;
    private String expand;
    private String networkId;
    private String networkP1;
    private String networkP2;
    private String receivePL;
    private String sendPower;
    private int type;

    private final String getShowType(int mType) {
        return mType != 0 ? mType != 1 ? mType != 2 ? mType != 3 ? mType != 4 ? "" : "凤凰" : "好拉" : "贝拉" : "欢乐送" : "未定义";
    }

    public LoRaConfigParam(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.data = data;
        this.sendPower = "13";
        this.receivePL = "";
        this.networkP1 = "";
        this.networkP2 = "";
        this.expand = "LORA_SF7";
        this.networkId = "";
        this.broadCastTime = "";
        byte[] bArr = this.data;
        if (bArr.length >= 11) {
            this.sendPower = String.valueOf((int) bArr[0]);
            this.receivePL = String.valueOf(KotlinUtilsKt.bytesToIntTwo(this.data, 1));
            this.networkP1 = String.valueOf(KotlinUtilsKt.bytesToIntTwo(this.data, 3));
            this.networkP2 = String.valueOf(KotlinUtilsKt.bytesToIntTwo(this.data, 5));
            setExpand(getExpandString(this.data[7]));
            this.networkId = String.valueOf(this.data[8] & 255);
            this.broadCastTime = String.valueOf(this.data[9] & 255);
            this.type = this.data[10];
        }
    }

    public final byte[] getData() {
        return this.data;
    }

    public final String getSendPower() {
        return this.sendPower;
    }

    public final void setSendPower(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.sendPower = str;
    }

    public final String getReceivePL() {
        return this.receivePL;
    }

    public final void setReceivePL(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.receivePL = str;
    }

    public final String getNetworkP1() {
        return this.networkP1;
    }

    public final void setNetworkP1(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.networkP1 = str;
    }

    public final String getNetworkP2() {
        return this.networkP2;
    }

    public final void setNetworkP2(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.networkP2 = str;
    }

    @Bindable
    public final String getExpand() {
        return this.expand;
    }

    public final void setExpand(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.expand = value;
        notifyPropertyChanged(BR.expand);
    }

    public final String getNetworkId() {
        return this.networkId;
    }

    public final void setNetworkId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.networkId = str;
    }

    public final String getBroadCastTime() {
        return this.broadCastTime;
    }

    public final void setBroadCastTime(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.broadCastTime = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final byte[] codingData() {
        try {
            if (!checkData()) {
                return null;
            }
            final byte[] bArr = new byte[11];
            bArr[0] = (byte) Integer.parseInt(this.sendPower);
            byte[] bArr2 = KotlinUtilsKt.tolBytes(Integer.parseInt(this.receivePL));
            bArr[1] = bArr2[0];
            bArr[2] = bArr2[1];
            byte[] bArr3 = KotlinUtilsKt.tolBytes(Integer.parseInt(this.networkP1));
            bArr[3] = bArr3[0];
            bArr[4] = bArr3[1];
            byte[] bArr4 = KotlinUtilsKt.tolBytes(Integer.parseInt(this.networkP2));
            bArr[5] = bArr4[0];
            bArr[6] = bArr4[1];
            Byte expandByte = getExpandByte(this.expand);
            if (expandByte == null) {
                KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_choose_spreading_factor));
                return null;
            }
            bArr[7] = expandByte.byteValue();
            bArr[8] = (byte) Integer.parseInt(this.networkId);
            bArr[9] = (byte) Integer.parseInt(this.broadCastTime);
            bArr[10] = (byte) LoRaClient.INSTANCE.getInstance().getDeviceType();
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.bean.LoRaConfigParam$codingData$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "codingData" + ArraysKt.joinToString$default(bArr, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, String>() { // from class: com.pudu.library.loracall.bean.LoRaConfigParam$codingData$1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ String invoke(Byte b) {
                            return invoke(b.byteValue());
                        }

                        public final String invoke(byte b) {
                            return String.valueOf((int) b);
                        }
                    }, 31, (Object) null);
                }
            }, 1, null);
            return bArr;
        } catch (Exception e) {
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.bean.LoRaConfigParam$codingData$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "编码数据出错：" + KotlinUtilsKt.stackTraceToString(e);
                }
            }, 1, null);
            return null;
        }
    }

    private final boolean checkData() {
        if (StringsKt.isBlank(this.sendPower)) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_emission_power));
            return false;
        }
        int parseInt = Integer.parseInt(this.sendPower);
        if (parseInt < -18 || parseInt > 13) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_transmit_power_range));
            return false;
        }
        if (StringsKt.isBlank(this.receivePL)) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_receive_frequency));
            return false;
        }
        int parseInt2 = Integer.parseInt(this.receivePL);
        if (parseInt2 < 24000 || parseInt2 > 24835) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_receive_frequency));
            return false;
        }
        if (StringsKt.isBlank(this.networkP1)) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_gateway_frequency_1));
            return false;
        }
        int parseInt3 = Integer.parseInt(this.networkP1);
        if (parseInt3 < 24000 || parseInt3 > 24835) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_gateway_frequency_1_rang));
            return false;
        }
        if (StringsKt.isBlank(this.networkP2)) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_gateway_frequency_2));
            return false;
        }
        int parseInt4 = Integer.parseInt(this.networkP2);
        if (parseInt4 < 24000 || parseInt4 > 24835) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_gateway_frequency_2_rang));
            return false;
        }
        if (StringsKt.isBlank(this.expand)) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_choose_spreading_factor));
            return false;
        }
        if (StringsKt.isBlank(this.networkId)) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_network_id));
            return false;
        }
        int parseInt5 = Integer.parseInt(this.networkId);
        if (parseInt5 > 255 || parseInt5 < 0) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_scope_write_network_id));
            return false;
        }
        if (!StringsKt.isBlank(this.broadCastTime)) {
            return true;
        }
        KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_airtime));
        return false;
    }

    private final String getExpandString(byte r3) {
        String hexString = KotlinUtilsKt.toHexString(r3);
        int hashCode = hexString.hashCode();
        if (hashCode != 1691) {
            if (hashCode != 1722) {
                if (hashCode != 1753) {
                    if (hashCode != 1784) {
                        if (hashCode != 1815) {
                            if (hashCode != 3055) {
                                if (hashCode == 3086) {
                                    if (hexString.equals("b0")) {
                                        return "LORA_SF11";
                                    }
                                } else if (hashCode == 3117 && hexString.equals("c0")) {
                                    return "LORA_SF12";
                                }
                            } else if (hexString.equals("a0")) {
                                return "LORA_SF10";
                            }
                        } else if (hexString.equals("90")) {
                            return "LORA_SF9";
                        }
                    } else if (hexString.equals("80")) {
                        return "LORA_SF8";
                    }
                } else if (hexString.equals("70")) {
                    return "LORA_SF7";
                }
            } else if (hexString.equals("60")) {
                return "LORA_SF6";
            }
        } else if (hexString.equals("50")) {
            return "LORA_SF5";
        }
        return "";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final Byte getExpandByte(String string) {
        Integer num;
        int hashCode = string.hashCode();
        switch (hashCode) {
            case -1181522571:
                if (string.equals("LORA_SF5")) {
                    num = 80;
                    break;
                }
                num = null;
                break;
            case -1181522570:
                if (string.equals("LORA_SF6")) {
                    num = 96;
                    break;
                }
                num = null;
                break;
            case -1181522569:
                if (string.equals("LORA_SF7")) {
                    num = 112;
                    break;
                }
                num = null;
                break;
            case -1181522568:
                if (string.equals("LORA_SF8")) {
                    num = 128;
                    break;
                }
                num = null;
                break;
            case -1181522567:
                if (string.equals("LORA_SF9")) {
                    num = 144;
                    break;
                }
                num = null;
                break;
            default:
                switch (hashCode) {
                    case 2027505887:
                        if (string.equals("LORA_SF10")) {
                            num = 160;
                            break;
                        }
                        num = null;
                        break;
                    case 2027505888:
                        if (string.equals("LORA_SF11")) {
                            num = 176;
                            break;
                        }
                        num = null;
                        break;
                    case 2027505889:
                        if (string.equals("LORA_SF12")) {
                            num = 192;
                            break;
                        }
                        num = null;
                        break;
                    default:
                        num = null;
                        break;
                }
        }
        if (num != null) {
            return Byte.valueOf((byte) num.intValue());
        }
        return null;
    }

    public String toString() {
        return "{\nsendPower:" + this.sendPower + "\nreceivePL:" + this.receivePL + "\nnetworkP1:" + this.networkP1 + "\nnetworkP2:" + this.networkP2 + "\nexpand:" + this.expand + "\nnetworkId:" + this.networkId + "\nbroadCastTime:" + this.broadCastTime + "\ntype:" + this.type + "\n\n}";
    }
}
