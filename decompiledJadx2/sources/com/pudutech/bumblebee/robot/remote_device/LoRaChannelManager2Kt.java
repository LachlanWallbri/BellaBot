package com.pudutech.bumblebee.robot.remote_device;

import com.pudutech.bumblebee.robot.aidl.serialize.Channel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoRaChannelManager2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u0019\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"calcChannelByte", "", "frequency", "", "calcChannelFrequency", "byte", "main", "", "args", "", "", "([Ljava/lang/String;)V", "Robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class LoRaChannelManager2Kt {
    public static final void main(String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        ArrayList arrayList = new ArrayList();
        BigDecimal startFrequency = LoRaChannelConfig.INSTANCE.getStartFrequency();
        do {
            BigDecimal add = startFrequency.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            BigDecimal multiply = LoRaChannelConfig.INSTANCE.getStepFrequency().multiply(new BigDecimal(String.valueOf(2.0f)));
            Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
            BigDecimal subtract = add.subtract(multiply);
            Channel channel = new Channel();
            channel.setSendFrequency(startFrequency.floatValue());
            channel.setReceiveFrequency(subtract.floatValue());
            arrayList.add(channel);
            startFrequency = startFrequency.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            Intrinsics.checkExpressionValueIsNotNull(startFrequency, "frequency.add(LoRaChanne…onfig.eachGroupFrequency)");
            if (startFrequency.compareTo(LoRaChannelConfig.INSTANCE.getDefaultFrequency()) >= 0) {
                break;
            }
        } while (startFrequency.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency()).compareTo(LoRaChannelConfig.INSTANCE.getDefaultFrequency()) < 0);
        BigDecimal add2 = LoRaChannelConfig.INSTANCE.getDefaultFrequency().add(new BigDecimal(String.valueOf(1.0f)));
        Intrinsics.checkExpressionValueIsNotNull(add2, "LoRaChannelConfig.defaul…Decimal(1.0f.toString()))");
        do {
            BigDecimal add3 = add2.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            BigDecimal multiply2 = LoRaChannelConfig.INSTANCE.getStepFrequency().multiply(new BigDecimal(String.valueOf(2.0f)));
            Intrinsics.checkExpressionValueIsNotNull(multiply2, "this.multiply(other)");
            BigDecimal subtract2 = add3.subtract(multiply2);
            Channel channel2 = new Channel();
            channel2.setSendFrequency(add2.floatValue());
            channel2.setReceiveFrequency(subtract2.floatValue());
            arrayList.add(channel2);
            add2 = add2.add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency());
            Intrinsics.checkExpressionValueIsNotNull(add2, "frequency.add(LoRaChanne…onfig.eachGroupFrequency)");
        } while (add2.floatValue() < LoRaChannelConfig.INSTANCE.getEndFrequency().add(LoRaChannelConfig.INSTANCE.getEachGroupFrequency()).floatValue());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            System.out.println((Channel) it.next());
        }
        System.out.println((Object) ("size = " + arrayList.size()));
        CollectionsKt.sort(arrayList);
        System.out.println((Object) "=====================");
        int size = arrayList.size();
        int i = 1;
        if (1 <= size) {
            while (true) {
                Object obj = arrayList.get(i - 1);
                Intrinsics.checkExpressionValueIsNotNull(obj, "channelList[i - 1]");
                Channel channel3 = (Channel) obj;
                channel3.setChannelId(i);
                System.out.println(channel3);
                if (i == size) {
                    break;
                } else {
                    i++;
                }
            }
        }
        byte calcChannelByte = calcChannelByte(476.4f);
        calcChannelByte(477.4f);
        System.out.println((Object) ("f = " + calcChannelFrequency(calcChannelByte)));
        Intrinsics.checkExpressionValueIsNotNull("82", "(this as java.lang.String).substring(startIndex)");
        System.out.println(Integer.parseInt("82", 16));
        int i2 = (int) 255.0f;
        System.out.println((Object) "频率\t\t\t信道下标\t\t\t值");
        int i3 = 0;
        if (i2 < 0) {
            return;
        }
        while (true) {
            System.out.println((Object) (((i3 * 0.2f) + 464.0f) + "\t\t\t" + i3 + "\t\t\t"));
            if (i3 == i2) {
                return;
            } else {
                i3++;
            }
        }
    }

    public static final byte calcChannelByte(float f) {
        int intValue = new BigDecimal(String.valueOf(f)).subtract(new BigDecimal(String.valueOf(464.0f))).divide(new BigDecimal(String.valueOf(0.2f))).intValue();
        System.out.println(intValue);
        String hexString = Integer.toHexString(intValue);
        Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(result)");
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (hexString == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = hexString.toUpperCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        System.out.println((Object) ("十进制：" + intValue + "\t\t十六进制：" + upperCase));
        int parseInt = Integer.parseInt(upperCase, 16);
        StringBuilder sb = new StringBuilder();
        sb.append("int16 = ");
        sb.append(parseInt);
        System.out.println((Object) sb.toString());
        byte b = (byte) parseInt;
        System.out.println((Object) ("byte = " + ((int) b)));
        return b;
    }

    public static final float calcChannelFrequency(byte b) {
        BigDecimal minFrequency = LoRaChannelConfig.INSTANCE.getMinFrequency();
        BigDecimal multiply = new BigDecimal(LoRaChannelConfig.INSTANCE.getStepFrequency().toString()).multiply(new BigDecimal(String.valueOf(b & 255)));
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return minFrequency.add(multiply).floatValue();
    }
}
