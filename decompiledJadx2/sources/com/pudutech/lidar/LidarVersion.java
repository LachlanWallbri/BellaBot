package com.pudutech.lidar;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public enum LidarVersion {
    HLS(1),
    EAI_X4(2),
    RPLIDAR_A3(3),
    EAI_G4(4),
    EAI_G6(5),
    RPLIDAR_S1(6),
    EAI_G6_UART(7),
    RPLIDAR_S1_UART(8),
    RPLIDAR_A2M7(9),
    RPLIDAR_A2M7_UART(10),
    EAI_T05(11),
    EAI_TG30(12),
    EAI_TG30_UART(13),
    EAI_G7(14),
    EAI_G7_UART(15),
    EAI_TG30_SLAMWARE(16),
    LTME_02A(17),
    LD_06(18),
    LDS_50C(19),
    LDS_15D(20);

    private int value;

    LidarVersion(int i) {
        this.value = 0;
        this.value = i;
    }

    public static LidarVersion valueOf(int i) {
        for (LidarVersion lidarVersion : values()) {
            if (lidarVersion.value == i) {
                return lidarVersion;
            }
        }
        return EAI_G4;
    }

    public static int getValue(LidarVersion lidarVersion) {
        for (LidarVersion lidarVersion2 : values()) {
            if (lidarVersion2 == lidarVersion) {
                return lidarVersion2.value;
            }
        }
        return 4;
    }
}
