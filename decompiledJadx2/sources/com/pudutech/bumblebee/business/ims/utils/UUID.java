package com.pudutech.bumblebee.business.ims.utils;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: UUID.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0005R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/utils/UUID;", "", "()V", "CHARS", "", "", "[Ljava/lang/String;", "generateShortUuid", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UUID {
    public static final UUID INSTANCE = new UUID();
    private static final String[] CHARS = {"a", "b", "c", LinkFormat.DOMAIN, C3898x.f4338g, C3898x.f4339h, C3898x.f4336e, "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", CompressorStreamFactory.f8930Z, "0", "1", "2", "3", TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD, "5", "6", "7", "8", "9", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z"};

    private UUID() {
    }

    public final String generateShortUuid() {
        StringBuffer stringBuffer = new StringBuffer();
        String uuid = java.util.UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        String replace$default = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
        for (int i = 0; i <= 7; i++) {
            int i2 = i * 4;
            int i3 = i2 + 4;
            if (replace$default != null) {
                String substring = replace$default.substring(i2, i3);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                stringBuffer.append(CHARS[Integer.parseInt(substring, 16) % 62]);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "shortBuffer.toString()");
        return stringBuffer2;
    }
}
