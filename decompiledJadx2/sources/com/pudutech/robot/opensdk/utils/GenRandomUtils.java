package com.pudutech.robot.opensdk.utils;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: GenRandomUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/utils/GenRandomUtils;", "", "()V", "getRandom", "", "n", "", "onlyLowerCase", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class GenRandomUtils {
    public static final GenRandomUtils INSTANCE = new GenRandomUtils();

    private GenRandomUtils() {
    }

    public static /* synthetic */ String getRandom$default(int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        return getRandom(i, z);
    }

    @JvmStatic
    public static final String getRandom(int n, boolean onlyLowerCase) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        String str = !onlyLowerCase ? "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" : "0123456789abcdefghijklmnopqrstuvwxyz";
        int length = str.length();
        for (int i = 0; i < n; i++) {
            stringBuffer.append(str.charAt(random.nextInt(length)));
        }
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "valSb.toString()");
        return stringBuffer2;
    }
}
