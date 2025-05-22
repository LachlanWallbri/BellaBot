package com.pudutech.bumblebee.business.ims.utils;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.loc.C3898x;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: ShortUUID.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0005H\u0002J\u001b\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0002\u0010\rJ\u0006\u0010\u000e\u001a\u00020\u0005R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/utils/ShortUUID;", "", "()V", "CHARS", "", "", "[Ljava/lang/String;", "uuidCache", "Lcom/pudutech/bumblebee/business/ims/utils/LimitQueue;", "generateShortUuid", "main", "", "args", "([Ljava/lang/String;)V", "randomUUID", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ShortUUID {
    public static final ShortUUID INSTANCE = new ShortUUID();
    private static final String[] CHARS = {"a", "b", "c", LinkFormat.DOMAIN, C3898x.f4338g, C3898x.f4339h, C3898x.f4336e, "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", CompressorStreamFactory.f8930Z, "0", "1", "2", "3", TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD, "5", "6", "7", "8", "9", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z"};
    private static LimitQueue<String> uuidCache = new LimitQueue<>(1000);

    private ShortUUID() {
    }

    public final String randomUUID() {
        String str = (String) null;
        while (true) {
            if (str == null || uuidCache.contains(str)) {
                str = generateShortUuid();
            } else {
                uuidCache.offer(str);
                return str;
            }
        }
    }

    private final String generateShortUuid() {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 4) {
            sb.append(CHARS[RangesKt.random(RangesKt.until(0, 10), Random.INSTANCE)]);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    @JvmStatic
    public static final void main(String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        new Thread(new Runnable() { // from class: com.pudutech.bumblebee.business.ims.utils.ShortUUID$main$1
            @Override // java.lang.Runnable
            public final void run() {
                HashMap hashMap = new HashMap();
                int i = 0;
                while (true) {
                    i++;
                    long currentTimeMillis = System.currentTimeMillis();
                    String randomUUID = ShortUUID.INSTANCE.randomUUID();
                    System.out.println((Object) ("time = " + (System.currentTimeMillis() - currentTimeMillis)));
                    System.out.println((Object) ("uuid = " + randomUUID));
                    if (hashMap.containsKey(randomUUID)) {
                        System.out.println((Object) ("重复 index = " + i));
                    }
                    hashMap.put(randomUUID, Integer.valueOf(i));
                    Thread.sleep(10L);
                }
            }
        }).start();
    }
}
