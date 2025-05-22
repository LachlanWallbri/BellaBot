package com.pudutech.mirsdk.mapify;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class Robot {
    private static final String TAG = "RobotNAME";
    public static double WHEEL_TRACK = 0.3836d;
    public static double[] gyroAngleVel = {0.0d, 0.0d, 0.0d};
    public static double[] MINI_BELLABOT_EXTRINSIC = {1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.1925d, 0.0d, 0.0d, 0.0d, 1.0d};
    public static double[] BIG_BELLABOT_EXTRINSIC = {1.0d, 0.0d, 0.0d, 0.0305d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.26d, 0.0d, 0.0d, 0.0d, 1.0d};
    public static double[] GRAY_DOG_EXTRINSIC = {1.0d, 0.0d, 0.0d, 0.1059d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.22d, 0.0d, 0.0d, 0.0d, 1.0d};
    public static double[] PEANUT_EXTRINSIC = {1.0d, 0.0d, 0.0d, -0.03425d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.06419d, 0.0d, 0.0d, 0.0d, 1.0d};

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public enum RobotType {
        BellaBot,
        Peanut,
        Firefox,
        RecycleDog,
        Ninetales
    }

    public static double[] getRobotExtrinsic(ProductMachineType productMachineType) {
        Pdlog.m3273d(TAG, "getRobotExtrinsic = " + productMachineType.getModel().name());
        if (productMachineType == null) {
            return null;
        }
        if (productMachineType.getModel() == MachineModel.BellaBot && productMachineType.getMainVersion() == 0 && productMachineType.getMinorVersion() == 0) {
            return MINI_BELLABOT_EXTRINSIC;
        }
        if (productMachineType.getModel() == MachineModel.BellaBot) {
            return BIG_BELLABOT_EXTRINSIC;
        }
        if (productMachineType.getModel() == MachineModel.RecycleDog) {
            return GRAY_DOG_EXTRINSIC;
        }
        if (productMachineType.getModel() == MachineModel.Peanut) {
            return PEANUT_EXTRINSIC;
        }
        return null;
    }

    public static RobotType getRobotType(ProductMachineType productMachineType) {
        if (productMachineType == null) {
            return null;
        }
        if (productMachineType.getModel() == MachineModel.RecycleDog) {
            return RobotType.RecycleDog;
        }
        if (productMachineType.getModel() == MachineModel.BellaBot) {
            return RobotType.BellaBot;
        }
        if (productMachineType.getModel() == MachineModel.Peanut) {
            return RobotType.Peanut;
        }
        if (productMachineType.getModel() == MachineModel.Firefox) {
            return RobotType.Firefox;
        }
        if (productMachineType.getModel() == MachineModel.Ninetales) {
            return RobotType.Ninetales;
        }
        return null;
    }
}
