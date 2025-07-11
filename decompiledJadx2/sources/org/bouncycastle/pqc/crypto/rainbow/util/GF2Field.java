package org.bouncycastle.pqc.crypto.rainbow.util;

import io.netty.handler.codec.memcache.binary.BinaryMemcacheResponseStatus;
import org.bouncycastle.crypto.tls.AlertDescription;

/* loaded from: classes9.dex */
public class GF2Field {
    public static final int MASK = 255;
    static final short[] exps = {1, 2, 4, 8, 16, 32, 64, 128, 77, 154, 121, 242, 169, 31, 62, 124, 248, 189, 55, AlertDescription.unsupported_extension, 220, 245, 167, 3, 6, 12, 24, 48, 96, 192, 205, 215, 227, 139, 91, 182, 33, 66, 132, 69, 138, 89, 178, 41, 82, 164, 5, 10, 20, 40, 80, 160, 13, 26, 52, 104, 208, 237, 151, 99, 198, 193, 207, 211, 235, 155, 123, 246, 161, 15, 30, 60, 120, 240, 173, 23, 46, 92, 184, 61, 122, 244, 165, 7, 14, 28, 56, AlertDescription.unrecognized_name, 224, 141, 87, 174, 17, 34, 68, 136, 93, 186, 57, AlertDescription.bad_certificate_hash_value, 228, 133, 71, 142, 81, 162, 9, 18, 36, 72, 144, 109, 218, 249, 191, 51, 102, 204, 213, 231, 131, 75, 150, 97, 194, 201, 223, 243, 171, 27, 54, 108, 216, 253, 183, 35, 70, 140, 85, 170, 25, 50, 100, 200, 221, 247, 163, 11, 22, 44, 88, 176, 45, 90, 180, 37, 74, 148, 101, 202, 217, 255, 179, 43, 86, 172, 21, 42, 84, 168, 29, 58, 116, 232, 157, 119, 238, 145, AlertDescription.certificate_unobtainable, 222, 241, 175, 19, 38, 76, 152, 125, 250, 185, 63, 126, 252, 181, 39, 78, 156, 117, 234, 153, 127, 254, 177, 47, 94, 188, 53, 106, 212, 229, 135, 67, 134, 65, BinaryMemcacheResponseStatus.ENOMEM, 73, 146, 105, 210, 233, 159, AlertDescription.unknown_psk_identity, 230, BinaryMemcacheResponseStatus.UNKNOWN_COMMAND, 79, 158, AlertDescription.bad_certificate_status_response, 226, 137, 95, 190, 49, 98, 196, 197, 199, 195, 203, 219, 251, 187, 59, 118, 236, 149, 103, 206, 209, 239, 147, 107, 214, 225, 143, 83, 166, 1};
    static final short[] logs = {0, 0, 1, 23, 2, 46, 24, 83, 3, 106, 47, 147, 25, 52, 84, 69, 4, 92, 107, 182, 48, 166, 148, 75, 26, 140, 53, BinaryMemcacheResponseStatus.UNKNOWN_COMMAND, 85, 170, 70, 13, 5, 36, 93, 135, 108, 155, 183, 193, 49, 43, 167, 163, 149, 152, 76, 202, 27, 230, 141, AlertDescription.unknown_psk_identity, 54, 205, BinaryMemcacheResponseStatus.ENOMEM, 18, 86, 98, 171, 240, 71, 79, 14, 189, 6, 212, 37, 210, 94, 39, 136, 102, 109, 214, 156, 121, 184, 8, 194, 223, 50, 104, 44, 253, 168, 138, 164, 90, 150, 41, 153, 34, 77, 96, 203, 228, 28, 123, 231, 59, 142, 158, 116, 244, 55, 216, 206, 249, 131, AlertDescription.certificate_unobtainable, 19, 178, 87, 225, 99, 220, 172, 196, 241, 175, 72, 10, 80, 66, 15, 186, 190, 199, 7, 222, 213, 120, 38, 101, 211, 209, 95, 227, 40, 33, 137, 89, 103, 252, AlertDescription.unsupported_extension, 177, 215, 248, 157, 243, 122, 58, 185, 198, 9, 65, 195, 174, 224, 219, 51, 68, 105, 146, 45, 82, 254, 22, 169, 12, 139, 128, 165, 74, 91, 181, 151, 201, 42, 162, 154, 192, 35, 134, 78, 188, 97, 239, 204, 17, 229, AlertDescription.bad_certificate_hash_value, 29, 61, 124, 235, 232, 233, 60, 234, 143, 125, 159, 236, 117, 30, 245, 62, 56, 246, 217, 63, 207, 118, 250, 31, 132, 160, AlertDescription.unrecognized_name, 237, 20, 144, 179, 126, 88, 251, 226, 32, 100, 208, 221, 119, 173, 218, 197, 64, 242, 57, 176, 247, 73, 180, 11, 127, 81, 21, 67, 145, 16, AlertDescription.bad_certificate_status_response, 187, 238, 191, 133, 200, 161};

    public static short addElem(short s, short s2) {
        return (short) (s ^ s2);
    }

    public static short getExp(short s) {
        return exps[s];
    }

    public static short getLog(short s) {
        return logs[s];
    }

    public static short invElem(short s) {
        if (s == 0) {
            return (short) 0;
        }
        return exps[255 - logs[s]];
    }

    public static short multElem(short s, short s2) {
        if (s == 0 || s2 == 0) {
            return (short) 0;
        }
        short[] sArr = exps;
        short[] sArr2 = logs;
        return sArr[(sArr2[s] + sArr2[s2]) % 255];
    }
}
