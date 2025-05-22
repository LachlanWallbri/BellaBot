package com.pudutech.mirsdk.hardware.mcuupdate;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* loaded from: classes2.dex */
public class IAPProtocol {
    private static final int[] CRC32_TABLE = {0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes2.dex */
    public enum CHECK_RESULT {
        WRONG_PROTOCOL,
        WRONG_TARGET,
        WRONG_VERIFY,
        UNEXPECTED,
        EXPECTED
    }

    private static boolean equal(byte b, int i) {
        return b == ((byte) i);
    }

    public static final byte[] ResetToIAP(byte b) {
        byte[] int2byte = ByteUtils.INSTANCE.int2byte((int) System.currentTimeMillis());
        byte[] bArr = {22, b, 1, int2byte[0], int2byte[1], int2byte[2], -23, 0};
        bArr[7] = addSum(bArr);
        return bArr;
    }

    public static CHECK_RESULT checkResetIAP(byte[] bArr, byte b) {
        if (equal(bArr[0], 22) && equal(bArr[6], -23)) {
            return !equal(bArr[7], addSum(bArr)) ? CHECK_RESULT.WRONG_VERIFY : !equal(bArr[1], b) ? CHECK_RESULT.WRONG_TARGET : !equal(bArr[2], 1) ? CHECK_RESULT.WRONG_PROTOCOL : CHECK_RESULT.EXPECTED;
        }
        return CHECK_RESULT.WRONG_PROTOCOL;
    }

    public static final byte[] ModeRequest(byte b) {
        byte[] bArr = {22, b, 2, 0, 0, 0, -23, 0};
        bArr[7] = addSum(bArr);
        return bArr;
    }

    public static CHECK_RESULT checkModeRequest(byte[] bArr, byte b, boolean z) {
        if (equal(bArr[0], 22) && equal(bArr[6], -23)) {
            return !equal(bArr[7], addSum(bArr)) ? CHECK_RESULT.WRONG_VERIFY : !equal(bArr[1], b) ? CHECK_RESULT.WRONG_TARGET : !equal(bArr[2], 2) ? CHECK_RESULT.WRONG_PROTOCOL : !equal(bArr[3], z ? 1 : 0) ? CHECK_RESULT.UNEXPECTED : CHECK_RESULT.EXPECTED;
        }
        return CHECK_RESULT.WRONG_PROTOCOL;
    }

    public static final byte[] ReadVersionRequest(byte b) {
        byte[] bArr = {22, b, 3, 0, 0, 0, -23, 0};
        bArr[7] = addSum(bArr);
        return bArr;
    }

    public static CHECK_RESULT checkVersionLatest(byte[] bArr, byte b, byte[] bArr2) {
        if (equal(bArr[0], 22) && equal(bArr[6], -23)) {
            if (!equal(bArr[7], addSum(bArr))) {
                return CHECK_RESULT.WRONG_VERIFY;
            }
            if (!equal(bArr[1], b)) {
                return CHECK_RESULT.WRONG_TARGET;
            }
            if (!equal(bArr[2], 3)) {
                return CHECK_RESULT.WRONG_PROTOCOL;
            }
            if (equal(bArr[3], bArr2[0]) && equal(bArr[4], bArr2[1]) && equal(bArr[5], bArr2[2])) {
                return CHECK_RESULT.EXPECTED;
            }
            return CHECK_RESULT.UNEXPECTED;
        }
        return CHECK_RESULT.WRONG_PROTOCOL;
    }

    public static final byte[] CANSwitch(byte b, boolean z) {
        byte[] bArr = {22, b, 4, z ? (byte) 1 : (byte) 0, 0, 0, -23, 0};
        bArr[7] = addSum(bArr);
        return bArr;
    }

    public static CHECK_RESULT checkCANSwitch(byte[] bArr, byte b, boolean z) {
        if (equal(bArr[0], 22) && equal(bArr[6], -23)) {
            return !equal(bArr[7], addSum(bArr)) ? CHECK_RESULT.WRONG_VERIFY : !equal(bArr[1], b) ? CHECK_RESULT.WRONG_TARGET : !equal(bArr[2], 4) ? CHECK_RESULT.WRONG_PROTOCOL : !equal(bArr[3], z ? 1 : 0) ? CHECK_RESULT.UNEXPECTED : CHECK_RESULT.EXPECTED;
        }
        return CHECK_RESULT.WRONG_PROTOCOL;
    }

    public static final byte[] FileInfo(byte b, int i) {
        byte[] bArr = {22, b, 5, 0, 0, 0, -23, 0};
        bArr[3] = (byte) ((i >> 16) & 255);
        bArr[4] = (byte) ((i >> 8) & 255);
        bArr[5] = (byte) ((i >> 0) & 255);
        bArr[7] = addSum(bArr);
        return bArr;
    }

    public static CHECK_RESULT checkFileInfo(byte[] bArr, byte b, int i) {
        if (equal(bArr[0], 22) && equal(bArr[6], -23)) {
            if (!equal(bArr[7], addSum(bArr))) {
                return CHECK_RESULT.WRONG_VERIFY;
            }
            if (!equal(bArr[1], b)) {
                return CHECK_RESULT.WRONG_TARGET;
            }
            if (!equal(bArr[2], 5)) {
                return CHECK_RESULT.WRONG_PROTOCOL;
            }
            if (equal(bArr[3], i >> 16) && equal(bArr[4], i >> 8) && equal(bArr[5], i)) {
                return CHECK_RESULT.EXPECTED;
            }
            return CHECK_RESULT.UNEXPECTED;
        }
        return CHECK_RESULT.WRONG_PROTOCOL;
    }

    public static final byte[] BlockInfo(byte b, int i, int i2) {
        byte[] bArr = {22, b, 6, 0, 0, 0, 0, 0};
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) ((i >> 0) & 255);
        bArr[5] = (byte) ((i2 >> 8) & 255);
        bArr[6] = (byte) ((i2 >> 0) & 255);
        bArr[7] = addSum(bArr);
        return bArr;
    }

    public static CHECK_RESULT checkBlockInfo(byte[] bArr, byte b, int i, int i2) {
        if (!equal(bArr[0], 22)) {
            return CHECK_RESULT.WRONG_PROTOCOL;
        }
        if (!equal(bArr[7], addSum(bArr))) {
            return CHECK_RESULT.WRONG_VERIFY;
        }
        if (!equal(bArr[1], b)) {
            return CHECK_RESULT.WRONG_TARGET;
        }
        if (!equal(bArr[2], 6)) {
            return CHECK_RESULT.WRONG_PROTOCOL;
        }
        if (equal(bArr[3], i >> 8) && equal(bArr[4], i >> 0) && equal(bArr[5], i2 >> 8) && equal(bArr[6], i2 >> 0)) {
            return CHECK_RESULT.EXPECTED;
        }
        return CHECK_RESULT.UNEXPECTED;
    }

    public static final byte[] BytesPackage(byte b, byte[] bArr) {
        byte[] bArr2 = {22, b, 7, 0, 0, 0, 0, 0};
        bArr2[3] = bArr[0];
        bArr2[4] = bArr[1];
        bArr2[5] = bArr[2];
        bArr2[6] = bArr[3];
        bArr2[7] = bArr[4];
        return bArr2;
    }

    public static final byte[] blockCRC32(byte b, byte[] bArr) {
        byte[] bArr2 = {22, b, 8, 0, 0, 0, 0, 0};
        bArr2[4] = bArr[0];
        bArr2[5] = bArr[1];
        bArr2[6] = bArr[2];
        bArr2[7] = bArr[3];
        return bArr2;
    }

    public static CHECK_RESULT checkBlockCRC32(byte[] bArr, byte b) {
        return !equal(bArr[0], 22) ? CHECK_RESULT.WRONG_PROTOCOL : !equal(bArr[7], addSum(bArr)) ? CHECK_RESULT.WRONG_VERIFY : !equal(bArr[1], b) ? CHECK_RESULT.WRONG_TARGET : !equal(bArr[2], 8) ? CHECK_RESULT.WRONG_PROTOCOL : equal(bArr[3], 0) ? CHECK_RESULT.UNEXPECTED : CHECK_RESULT.EXPECTED;
    }

    public static final byte[] ResetToAPP(byte b) {
        byte[] bArr = {22, b, 9, 0, 0, 0, -23, 0};
        bArr[7] = addSum(bArr);
        return bArr;
    }

    private static byte addSum(byte[] bArr) {
        if (bArr.length != 8) {
            bArr[10] = 0;
        }
        byte b = 0;
        for (int i = 0; i < 7; i++) {
            b = (byte) (b + bArr[i]);
        }
        return b;
    }

    public static int calculateCRC32(byte[] bArr) {
        int i = -1;
        for (byte b : bArr) {
            i ^= b & 255;
            for (int i2 = 0; i2 < 4; i2++) {
                i = (i << 8) ^ CRC32_TABLE[(i >> 24) & 255];
            }
        }
        return i;
    }
}
