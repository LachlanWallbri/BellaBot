package com.tencent.bugly.proguard;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ai */
/* loaded from: classes7.dex */
public final class C5905ai implements InterfaceC5906aj {

    /* renamed from: a */
    private String f8026a = null;

    @Override // com.tencent.bugly.proguard.InterfaceC5906aj
    /* renamed from: a */
    public final byte[] mo3647a(byte[] bArr) throws Exception {
        if (this.f8026a == null || bArr == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f8026a.getBytes("UTF-8"))), new IvParameterSpec(this.f8026a.getBytes("UTF-8")));
        return cipher.doFinal(bArr);
    }

    @Override // com.tencent.bugly.proguard.InterfaceC5906aj
    /* renamed from: b */
    public final byte[] mo3648b(byte[] bArr) throws Exception, NoSuchAlgorithmException {
        if (this.f8026a == null || bArr == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(1, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f8026a.getBytes("UTF-8"))), new IvParameterSpec(this.f8026a.getBytes("UTF-8")));
        return cipher.doFinal(bArr);
    }

    @Override // com.tencent.bugly.proguard.InterfaceC5906aj
    /* renamed from: a */
    public final void mo3646a(String str) {
        if (str != null) {
            this.f8026a = str;
        }
    }
}
