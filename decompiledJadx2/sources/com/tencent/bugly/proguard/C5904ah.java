package com.tencent.bugly.proguard;

import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ah */
/* loaded from: classes7.dex */
public final class C5904ah implements InterfaceC5906aj {

    /* renamed from: a */
    private String f8025a = null;

    @Override // com.tencent.bugly.proguard.InterfaceC5906aj
    /* renamed from: a */
    public final byte[] mo3647a(byte[] bArr) throws Exception {
        if (this.f8025a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(((int) b) + " ");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.f8025a.getBytes(), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
        cipher.init(2, secretKeySpec, new IvParameterSpec(this.f8025a.getBytes()));
        byte[] doFinal = cipher.doFinal(bArr);
        StringBuffer stringBuffer2 = new StringBuffer();
        for (byte b2 : doFinal) {
            stringBuffer2.append(((int) b2) + " ");
        }
        return doFinal;
    }

    @Override // com.tencent.bugly.proguard.InterfaceC5906aj
    /* renamed from: b */
    public final byte[] mo3648b(byte[] bArr) throws Exception, NoSuchAlgorithmException {
        if (this.f8025a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(((int) b) + " ");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.f8025a.getBytes(), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
        cipher.init(1, secretKeySpec, new IvParameterSpec(this.f8025a.getBytes()));
        byte[] doFinal = cipher.doFinal(bArr);
        StringBuffer stringBuffer2 = new StringBuffer();
        for (byte b2 : doFinal) {
            stringBuffer2.append(((int) b2) + " ");
        }
        return doFinal;
    }

    @Override // com.tencent.bugly.proguard.InterfaceC5906aj
    /* renamed from: a */
    public final void mo3646a(String str) {
        if (str != null) {
            for (int length = str.length(); length < 16; length++) {
                str = str + "0";
            }
            this.f8025a = str.substring(0, 16);
        }
    }
}
