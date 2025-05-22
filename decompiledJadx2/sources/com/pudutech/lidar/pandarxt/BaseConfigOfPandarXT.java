package com.pudutech.lidar.pandarxt;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UByte;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseConfigOfPandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/BaseConfigOfPandarXT;", "", "()V", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BaseConfigOfPandarXT {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte[] dataHeadVerify = {UByte.m4528constructorimpl((byte) 238), UByte.m4528constructorimpl((byte) 255)};
    private static final Map<Integer, Double> verticalDegreeMap = MapsKt.mutableMapOf(new Pair(1, Double.valueOf(15.0d)), new Pair(2, Double.valueOf(13.0d)), new Pair(3, Double.valueOf(11.0d)), new Pair(4, Double.valueOf(9.0d)), new Pair(5, Double.valueOf(7.0d)), new Pair(6, Double.valueOf(5.0d)), new Pair(7, Double.valueOf(3.0d)), new Pair(8, Double.valueOf(1.0d)), new Pair(9, Double.valueOf(-1.0d)), new Pair(10, Double.valueOf(-3.0d)), new Pair(11, Double.valueOf(-5.0d)), new Pair(12, Double.valueOf(-7.0d)), new Pair(13, Double.valueOf(-9.0d)), new Pair(14, Double.valueOf(-11.0d)), new Pair(15, Double.valueOf(-13.0d)), new Pair(16, Double.valueOf(-15.0d)));

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: BaseConfigOfPandarXT.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/BaseConfigOfPandarXT$Companion;", "", "()V", "dataHeadVerify", "Lkotlin/UByteArray;", "getDataHeadVerify", "()[B", "[B", "verticalDegreeMap", "", "", "", "getVerticalDegreeMap", "()Ljava/util/Map;", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte[] getDataHeadVerify() {
            return BaseConfigOfPandarXT.dataHeadVerify;
        }

        public final Map<Integer, Double> getVerticalDegreeMap() {
            return BaseConfigOfPandarXT.verticalDegreeMap;
        }
    }
}
