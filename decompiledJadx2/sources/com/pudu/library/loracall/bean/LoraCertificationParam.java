package com.pudu.library.loracall.bean;

import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.loracall.library.C3965R;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;

/* compiled from: LoraCertificationParam.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u0004\u0018\u00010-J\b\u0010.\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR&\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR&\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR\u001a\u0010$\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR&\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001b\"\u0004\b)\u0010\u001d¨\u0006/"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoraCertificationParam;", "", "()V", "fixedFrequency", "", "getFixedFrequency", "()Ljava/lang/String;", "setFixedFrequency", "(Ljava/lang/String;)V", "joinMode", "", "getJoinMode", "()I", "setJoinMode", "(I)V", "receiveFrequency", "getReceiveFrequency", "setReceiveFrequency", "rfModule", "getRfModule", "setRfModule", "sendCycle", "getSendCycle", "setSendCycle", "sendModel", "Lkotlin/Pair;", "getSendModel", "()Lkotlin/Pair;", "setSendModel", "(Lkotlin/Pair;)V", "sendPower", "getSendPower", "setSendPower", "sendType", "getSendType", "setSendType", "silentValue", "getSilentValue", "setSilentValue", "wave", "getWave", "setWave", "checkData", "", "codingData", "", "toString", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoraCertificationParam {
    private String rfModule = "0";
    private int joinMode = 1;
    private Pair<String, Integer> sendType = new Pair<>("定频发射", 1);
    private Pair<String, Integer> wave = new Pair<>("调制波", 0);
    private String sendPower = "10";
    private String fixedFrequency = "24820";
    private String silentValue = "90";
    private Pair<String, Integer> sendModel = new Pair<>("收发可同时", 0);
    private String sendCycle = ErrorCode.UNKNOWN_SUCCESS_CODE;
    private String receiveFrequency = "24820";

    public final String getRfModule() {
        return this.rfModule;
    }

    public final void setRfModule(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.rfModule = str;
    }

    public final int getJoinMode() {
        return this.joinMode;
    }

    public final void setJoinMode(int i) {
        this.joinMode = i;
    }

    public final Pair<String, Integer> getSendType() {
        return this.sendType;
    }

    public final void setSendType(Pair<String, Integer> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "<set-?>");
        this.sendType = pair;
    }

    public final Pair<String, Integer> getWave() {
        return this.wave;
    }

    public final void setWave(Pair<String, Integer> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "<set-?>");
        this.wave = pair;
    }

    public final String getSendPower() {
        return this.sendPower;
    }

    public final void setSendPower(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.sendPower = str;
    }

    public final String getFixedFrequency() {
        return this.fixedFrequency;
    }

    public final void setFixedFrequency(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.fixedFrequency = str;
    }

    public final String getSilentValue() {
        return this.silentValue;
    }

    public final void setSilentValue(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.silentValue = str;
    }

    public final Pair<String, Integer> getSendModel() {
        return this.sendModel;
    }

    public final void setSendModel(Pair<String, Integer> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "<set-?>");
        this.sendModel = pair;
    }

    public final String getSendCycle() {
        return this.sendCycle;
    }

    public final void setSendCycle(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.sendCycle = str;
    }

    public final String getReceiveFrequency() {
        return this.receiveFrequency;
    }

    public final void setReceiveFrequency(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.receiveFrequency = str;
    }

    public final byte[] codingData() {
        try {
            if (!checkData()) {
                return null;
            }
            final byte[] bArr = new byte[17];
            bArr[0] = Byte.parseByte(this.rfModule);
            bArr[1] = (byte) this.joinMode;
            bArr[2] = (byte) this.sendType.getSecond().intValue();
            bArr[3] = (byte) this.wave.getSecond().intValue();
            bArr[4] = (byte) Integer.parseInt(this.sendPower);
            byte[] bArr2 = this.sendType.getSecond().intValue() == 1 ? KotlinUtilsKt.tolBytes(Long.parseLong(this.fixedFrequency) * BZip2Constants.BASEBLOCKSIZE) : new byte[]{0, 0, 0, 0};
            bArr[5] = bArr2[0];
            bArr[6] = bArr2[1];
            bArr[7] = bArr2[2];
            bArr[8] = bArr2[3];
            bArr[9] = Byte.parseByte(this.silentValue);
            bArr[10] = (byte) this.sendModel.getSecond().intValue();
            byte[] bArr3 = KotlinUtilsKt.tolBytes(Integer.parseInt(this.sendCycle));
            bArr[11] = bArr3[0];
            bArr[12] = bArr3[1];
            byte[] bArr4 = KotlinUtilsKt.tolBytes(Long.parseLong(this.receiveFrequency) * BZip2Constants.BASEBLOCKSIZE);
            bArr[13] = bArr4[0];
            bArr[14] = bArr4[1];
            bArr[15] = bArr4[2];
            bArr[16] = bArr4[3];
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.bean.LoraCertificationParam$codingData$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "codingData" + ArraysKt.joinToString$default(bArr, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, String>() { // from class: com.pudu.library.loracall.bean.LoraCertificationParam$codingData$1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ String invoke(Byte b) {
                            return invoke(b.byteValue());
                        }

                        public final String invoke(byte b) {
                            return KotlinUtilsKt.toHexString(b);
                        }
                    }, 31, (Object) null);
                }
            }, 1, null);
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.bean.LoraCertificationParam$codingData$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "LoraCertificationParam--" + LoraCertificationParam.this;
                }
            }, 1, null);
            return bArr;
        } catch (Exception e) {
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.bean.LoraCertificationParam$codingData$3
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
        if (StringsKt.isBlank(this.rfModule)) {
            KotlinUtilsKt.showToast("请填写射频模块数");
            return false;
        }
        if (StringsKt.isBlank(this.sendPower)) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_emission_power));
            return false;
        }
        if (StringsKt.isBlank(this.receiveFrequency)) {
            KotlinUtilsKt.showToast("请填写接收频率");
            return false;
        }
        int parseInt = Integer.parseInt(this.rfModule);
        if (parseInt < 0 || parseInt > 255) {
            KotlinUtilsKt.showToast("请在正确范围填写射频模块数");
            return false;
        }
        int parseInt2 = Integer.parseInt(this.sendPower);
        if (parseInt2 < -18 || parseInt2 > 13) {
            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_transmit_power_range));
            return false;
        }
        if (this.sendType.getSecond().intValue() == 1) {
            if (StringsKt.isBlank(this.fixedFrequency)) {
                KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_write_fixed_frequency));
                return false;
            }
            long parseLong = Long.parseLong(this.fixedFrequency);
            if (parseLong > 24835 || parseLong < 24000) {
                KotlinUtilsKt.showToast(KotlinUtilsKt.getString(this, C3965R.string.lora_fixed_frequency_range));
                return false;
            }
        }
        int parseInt3 = Integer.parseInt(this.silentValue);
        if (parseInt3 < 0 || parseInt3 > 100) {
            KotlinUtilsKt.showToast("发射静默阀值0~100");
            return false;
        }
        int parseInt4 = Integer.parseInt(this.sendCycle);
        if (parseInt4 < 100 || parseInt4 > 5000) {
            KotlinUtilsKt.showToast("发射周期100~5000");
            return false;
        }
        long parseLong2 = Long.parseLong(this.receiveFrequency);
        if (parseLong2 <= 24835 && parseLong2 >= 24000) {
            return true;
        }
        KotlinUtilsKt.showToast("接收频率范围24000~24835");
        return false;
    }

    public String toString() {
        return "{\nrfModule:" + this.rfModule + "\njoinMode:" + this.joinMode + "\nsendType:" + this.sendType + "\nwave:" + this.wave + "\nsendPower:" + this.sendPower + "\nfixedFrequency:" + this.fixedFrequency + "\nsilentValue:" + this.silentValue + "\nsendModel:" + this.sendModel + "\nsendCycle:" + this.sendCycle + "\nreceiveFrequency:" + this.receiveFrequency + "\n}";
    }
}
