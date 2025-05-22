package org.conscrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;
import org.conscrypt.io.IoUtils;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class KeyManagerFactoryImpl extends KeyManagerFactorySpi {
    private KeyStore keyStore;
    private char[] pwd;

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected void engineInit(KeyStore keyStore, char[] cArr) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        if (keyStore != null) {
            this.keyStore = keyStore;
            if (cArr != null) {
                this.pwd = (char[]) cArr.clone();
                return;
            } else {
                this.pwd = EmptyArray.CHAR;
                return;
            }
        }
        this.keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        String property = System.getProperty(SSLSocketFactoryFactory.SYSKEYSTORE);
        FileInputStream fileInputStream = null;
        if (property == null || property.equalsIgnoreCase("NONE") || property.isEmpty()) {
            try {
                this.keyStore.load(null, null);
                return;
            } catch (IOException e) {
                throw new KeyStoreException(e);
            } catch (CertificateException e2) {
                throw new KeyStoreException(e2);
            }
        }
        String property2 = System.getProperty(SSLSocketFactoryFactory.SYSKEYSTOREPWD);
        if (property2 == null) {
            this.pwd = EmptyArray.CHAR;
        } else {
            this.pwd = property2.toCharArray();
        }
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(new File(property));
                try {
                    this.keyStore.load(fileInputStream2, this.pwd);
                    IoUtils.closeQuietly(fileInputStream2);
                } catch (FileNotFoundException e3) {
                    e = e3;
                    throw new KeyStoreException(e);
                } catch (IOException e4) {
                    e = e4;
                    throw new KeyStoreException(e);
                } catch (CertificateException e5) {
                    e = e5;
                    throw new KeyStoreException(e);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    IoUtils.closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
            } catch (IOException e7) {
                e = e7;
            } catch (CertificateException e8) {
                e = e8;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("ManagerFactoryParameters not supported");
    }

    @Override // javax.net.ssl.KeyManagerFactorySpi
    protected KeyManager[] engineGetKeyManagers() {
        KeyStore keyStore = this.keyStore;
        if (keyStore != null) {
            return new KeyManager[]{new KeyManagerImpl(keyStore, this.pwd)};
        }
        throw new IllegalStateException("KeyManagerFactory is not initialized");
    }
}
