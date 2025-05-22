package com.google.api.gax.rpc.mtls;

import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.SecurityUtils;
import com.google.api.core.BetaApi;
import com.google.common.collect.ImmutableList;
import com.slamtec.slamware.robot.SystemParameters;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public class MtlsProvider {
    private static final String DEFAULT_CONTEXT_AWARE_METADATA_PATH = System.getProperty("user.home") + "/.secureConnect/context_aware_metadata.json";
    private EnvironmentProvider envProvider;
    private String metadataPath;
    private ProcessProvider processProvider;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    interface EnvironmentProvider {
        String getenv(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum MtlsEndpointUsagePolicy {
        NEVER,
        AUTO,
        ALWAYS
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface ProcessProvider {
        Process createProcess(InputStream inputStream) throws IOException;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class SystemEnvironmentProvider implements EnvironmentProvider {
        SystemEnvironmentProvider() {
        }

        @Override // com.google.api.gax.rpc.mtls.MtlsProvider.EnvironmentProvider
        public String getenv(String str) {
            return System.getenv(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class DefaultProcessProvider implements ProcessProvider {
        DefaultProcessProvider() {
        }

        @Override // com.google.api.gax.rpc.mtls.MtlsProvider.ProcessProvider
        public Process createProcess(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            return new ProcessBuilder(MtlsProvider.extractCertificateProviderCommand(inputStream)).start();
        }
    }

    MtlsProvider(EnvironmentProvider environmentProvider, ProcessProvider processProvider, String str) {
        this.envProvider = environmentProvider;
        this.processProvider = processProvider;
        this.metadataPath = str;
    }

    public MtlsProvider() {
        this(new SystemEnvironmentProvider(), new DefaultProcessProvider(), DEFAULT_CONTEXT_AWARE_METADATA_PATH);
    }

    public boolean useMtlsClientCertificate() {
        return "true".equals(this.envProvider.getenv("GOOGLE_API_USE_CLIENT_CERTIFICATE"));
    }

    public MtlsEndpointUsagePolicy getMtlsEndpointUsagePolicy() {
        String str = this.envProvider.getenv("GOOGLE_API_USE_MTLS_ENDPOINT");
        if ("never".equals(str)) {
            return MtlsEndpointUsagePolicy.NEVER;
        }
        if (SystemParameters.SYSVAL_DOCKED_REGISTER_STRATEGY_ALWAYS.equals(str)) {
            return MtlsEndpointUsagePolicy.ALWAYS;
        }
        return MtlsEndpointUsagePolicy.AUTO;
    }

    public KeyStore getKeyStore() throws IOException {
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(this.metadataPath);
                try {
                    KeyStore keyStore = getKeyStore(fileInputStream, this.processProvider);
                    fileInputStream.close();
                    return keyStore;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            } catch (InterruptedException e) {
                throw new IOException("Interrupted executing certificate provider command", e);
            }
        } catch (FileNotFoundException | GeneralSecurityException unused) {
            return null;
        }
    }

    static KeyStore getKeyStore(InputStream inputStream, ProcessProvider processProvider) throws IOException, InterruptedException, GeneralSecurityException {
        Process createProcess = processProvider.createProcess(inputStream);
        int runCertificateProviderCommand = runCertificateProviderCommand(createProcess, 1000L);
        if (runCertificateProviderCommand != 0) {
            throw new IOException("Cert provider command failed with exit code: " + runCertificateProviderCommand);
        }
        return SecurityUtils.createMtlsKeyStore(createProcess.getInputStream());
    }

    static ImmutableList<String> extractCertificateProviderCommand(InputStream inputStream) throws IOException {
        return ((ContextAwareMetadataJson) new GsonFactory().createJsonParser(inputStream).parse(ContextAwareMetadataJson.class)).getCommands();
    }

    static int runCertificateProviderCommand(Process process, long j) throws IOException, InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        while (j > 0) {
            Thread.sleep(Math.min(1 + j, 100L));
            j -= System.currentTimeMillis() - currentTimeMillis;
            try {
                return process.exitValue();
            } catch (IllegalThreadStateException unused) {
            }
        }
        process.destroy();
        throw new IOException("cert provider command timed out");
    }
}
