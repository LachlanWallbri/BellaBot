package org.bouncycastle.crypto;

import org.apache.http.protocol.HTTP;

/* loaded from: classes9.dex */
public enum PasswordConverter implements CharToByteConverter {
    ASCII { // from class: org.bouncycastle.crypto.PasswordConverter.1
        @Override // org.bouncycastle.crypto.CharToByteConverter
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS5PasswordToBytes(cArr);
        }

        @Override // org.bouncycastle.crypto.CharToByteConverter
        public String getType() {
            return HTTP.ASCII;
        }
    },
    UTF8 { // from class: org.bouncycastle.crypto.PasswordConverter.2
        @Override // org.bouncycastle.crypto.CharToByteConverter
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(cArr);
        }

        @Override // org.bouncycastle.crypto.CharToByteConverter
        public String getType() {
            return "UTF8";
        }
    },
    PKCS12 { // from class: org.bouncycastle.crypto.PasswordConverter.3
        @Override // org.bouncycastle.crypto.CharToByteConverter
        public byte[] convert(char[] cArr) {
            return PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
        }

        @Override // org.bouncycastle.crypto.CharToByteConverter
        public String getType() {
            return "PKCS12";
        }
    }
}
