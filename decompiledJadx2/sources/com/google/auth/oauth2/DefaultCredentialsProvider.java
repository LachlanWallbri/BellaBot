package com.google.auth.oauth2;

import com.google.auth.http.HttpTransportFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.util.Collections;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class DefaultCredentialsProvider {
    static final String APP_ENGINE_SIGNAL_CLASS = "com.google.appengine.api.utils.SystemProperty";
    static final String CLOUDSDK_CLIENT_ID = "764086051850-6qr4p6gpi6hn506pt8ejuq83di341hur.apps.googleusercontent.com";
    static final String CLOUDSDK_CONFIG_DIRECTORY = "gcloud";
    static final String CLOUDSDK_CREDENTIALS_WARNING = "Your application has authenticated using end user credentials from Google Cloud SDK. We recommend that most server applications use service accounts instead. If your application continues to use end user credentials from Cloud SDK, you might receive a \"quota exceeded\" or \"API not enabled\" error. For more information about service accounts, see https://cloud.google.com/docs/authentication/.";
    static final String CLOUD_SHELL_ENV_VAR = "DEVSHELL_CLIENT_PORT";
    static final String CREDENTIAL_ENV_VAR = "GOOGLE_APPLICATION_CREDENTIALS";
    static final String GCE_METADATA_HOST_ENV_VAR = "GCE_METADATA_HOST";
    static final String HELP_PERMALINK = "https://developers.google.com/accounts/docs/application-default-credentials";
    static final String NO_GCE_CHECK_ENV_VAR = "NO_GCE_CHECK";
    static final String SKIP_APP_ENGINE_ENV_VAR = "GOOGLE_APPLICATION_CREDENTIALS_SKIP_APP_ENGINE";
    public static final String SUPPRESS_GCLOUD_CREDS_WARNING_ENV_VAR = "SUPPRESS_GCLOUD_CREDS_WARNING";
    static final String WELL_KNOWN_CREDENTIALS_FILE = "application_default_credentials.json";
    private GoogleCredentials cachedCredentials = null;
    private boolean checkedAppEngine = false;
    private boolean checkedComputeEngine = false;
    static final DefaultCredentialsProvider DEFAULT = new DefaultCredentialsProvider();
    static final String SPECIFICATION_VERSION = System.getProperty("java.specification.version");
    static final String GAE_RUNTIME_VERSION = System.getProperty("com.google.appengine.runtime.version");
    static final String RUNTIME_JETTY_LOGGER = System.getProperty("org.eclipse.jetty.util.log.class");
    static final Logger LOGGER = Logger.getLogger(DefaultCredentialsProvider.class.getName());

    /* JADX INFO: Access modifiers changed from: package-private */
    public final GoogleCredentials getDefaultCredentials(HttpTransportFactory httpTransportFactory) throws IOException {
        synchronized (this) {
            if (this.cachedCredentials == null) {
                this.cachedCredentials = getDefaultCredentialsUnsynchronized(httpTransportFactory);
            }
            if (this.cachedCredentials != null) {
                return this.cachedCredentials;
            }
            throw new IOException(String.format("The Application Default Credentials are not available. They are available if running in Google Compute Engine. Otherwise, the environment variable %s must be defined pointing to a file defining the credentials. See %s for more information.", CREDENTIAL_ENV_VAR, HELP_PERMALINK));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x009d, code lost:
    
        if (r3 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x009f, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a8, code lost:
    
        warnAboutProblematicCredentials(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a5, code lost:
    
        if (r3 != null) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final GoogleCredentials getDefaultCredentialsUnsynchronized(HttpTransportFactory httpTransportFactory) throws IOException {
        GoogleCredentials googleCredentials;
        InputStream inputStream;
        File file;
        String env = getEnv(CREDENTIAL_ENV_VAR);
        InputStream inputStream2 = null;
        if (env != null && env.length() > 0) {
            LOGGER.log(Level.FINE, String.format("Attempting to load credentials from file: %s", env));
            try {
                try {
                    file = new File(env);
                } catch (IOException e) {
                    e = e;
                } catch (AccessControlException unused) {
                    inputStream = null;
                }
                if (!isFile(file)) {
                    throw new IOException("File does not exist.");
                }
                inputStream = readStream(file);
                try {
                    googleCredentials = GoogleCredentials.fromStream(inputStream, httpTransportFactory);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e2) {
                    e = e2;
                    throw new IOException(String.format("Error reading credential file from environment variable %s, value '%s': %s", CREDENTIAL_ENV_VAR, env, e.getMessage()), e);
                } catch (AccessControlException unused2) {
                    googleCredentials = null;
                    if (googleCredentials == null) {
                    }
                    if (googleCredentials == null) {
                    }
                    if (googleCredentials == null) {
                    }
                    if (googleCredentials != null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = inputStream;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    throw th;
                }
                if (googleCredentials == null) {
                    File wellKnownCredentialsFile = getWellKnownCredentialsFile();
                    try {
                        try {
                            if (isFile(wellKnownCredentialsFile)) {
                                LOGGER.log(Level.FINE, String.format("Attempting to load credentials from well known file: %s", wellKnownCredentialsFile.getCanonicalPath()));
                                inputStream2 = readStream(wellKnownCredentialsFile);
                                googleCredentials = GoogleCredentials.fromStream(inputStream2, httpTransportFactory);
                            }
                        } catch (IOException e3) {
                            throw new IOException(String.format("Error reading credential file from location %s: %s", wellKnownCredentialsFile, e3.getMessage()));
                        } catch (AccessControlException unused3) {
                        }
                    } finally {
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                    }
                }
                if (googleCredentials == null && isOnGAEStandard7() && !skipAppEngineCredentialsCheck()) {
                    LOGGER.log(Level.FINE, "Attempting to load credentials from GAE 7 Standard");
                    googleCredentials = tryGetAppEngineCredential();
                }
                if (googleCredentials == null) {
                    LOGGER.log(Level.FINE, "Attempting to load credentials from Cloud Shell");
                    googleCredentials = tryGetCloudShellCredentials();
                }
                if (googleCredentials != null) {
                    return googleCredentials;
                }
                LOGGER.log(Level.FINE, "Attempting to load credentials from GCE");
                return tryGetComputeCredentials(httpTransportFactory);
            } catch (Throwable th2) {
                th = th2;
            }
        }
        googleCredentials = null;
        if (googleCredentials == null) {
        }
        if (googleCredentials == null) {
            LOGGER.log(Level.FINE, "Attempting to load credentials from GAE 7 Standard");
            googleCredentials = tryGetAppEngineCredential();
        }
        if (googleCredentials == null) {
        }
        if (googleCredentials != null) {
        }
    }

    private void warnAboutProblematicCredentials(GoogleCredentials googleCredentials) {
        if ((googleCredentials instanceof UserCredentials) && ((UserCredentials) googleCredentials).getClientId().equals(CLOUDSDK_CLIENT_ID) && !Boolean.parseBoolean(getEnv(SUPPRESS_GCLOUD_CREDS_WARNING_ENV_VAR))) {
            LOGGER.log(Level.WARNING, CLOUDSDK_CREDENTIALS_WARNING);
        }
    }

    private final File getWellKnownCredentialsFile() {
        File file;
        String lowerCase = getProperty("os.name", "").toLowerCase(Locale.US);
        String env = getEnv("CLOUDSDK_CONFIG");
        if (env != null) {
            file = new File(env);
        } else if (lowerCase.indexOf("windows") >= 0) {
            file = new File(new File(getEnv("APPDATA")), CLOUDSDK_CONFIG_DIRECTORY);
        } else {
            file = new File(new File(getProperty("user.home", ""), ".config"), CLOUDSDK_CONFIG_DIRECTORY);
        }
        return new File(file, WELL_KNOWN_CREDENTIALS_FILE);
    }

    private boolean runningOnAppEngine() {
        try {
            try {
                Field field = forName(APP_ENGINE_SIGNAL_CLASS).getField("environment");
                return field.getType().getMethod(ES6Iterator.VALUE_PROPERTY, new Class[0]).invoke(field.get(null), new Object[0]) != null;
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                throw new RuntimeException(String.format("Unexpected error trying to determine if runnning on Google App Engine: %s", e.getMessage()), e);
            }
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private GoogleCredentials tryGetCloudShellCredentials() {
        String env = getEnv(CLOUD_SHELL_ENV_VAR);
        if (env != null) {
            return CloudShellCredentials.create(Integer.parseInt(env));
        }
        return null;
    }

    private GoogleCredentials tryGetAppEngineCredential() throws IOException {
        if (this.checkedAppEngine) {
            return null;
        }
        boolean runningOnAppEngine = runningOnAppEngine();
        this.checkedAppEngine = true;
        if (runningOnAppEngine) {
            return new AppEngineCredentials(Collections.emptyList(), Collections.emptyList());
        }
        return null;
    }

    private final GoogleCredentials tryGetComputeCredentials(HttpTransportFactory httpTransportFactory) {
        if (this.checkedComputeEngine) {
            return null;
        }
        boolean runningOnComputeEngine = ComputeEngineCredentials.runningOnComputeEngine(httpTransportFactory, this);
        this.checkedComputeEngine = true;
        if (runningOnComputeEngine) {
            return ComputeEngineCredentials.newBuilder().setHttpTransportFactory(httpTransportFactory).build();
        }
        return null;
    }

    private boolean skipAppEngineCredentialsCheck() {
        String env = getEnv(SKIP_APP_ENGINE_ENV_VAR);
        if (env != null) {
            return env.equalsIgnoreCase("true") || env.equals("1");
        }
        return false;
    }

    protected boolean isOnGAEStandard7() {
        return GAE_RUNTIME_VERSION != null && (SPECIFICATION_VERSION.equals("1.7") || RUNTIME_JETTY_LOGGER == null);
    }

    Class<?> forName(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getEnv(String str) {
        return System.getenv(str);
    }

    String getProperty(String str, String str2) {
        return System.getProperty(str, str2);
    }

    boolean isFile(File file) {
        return file.isFile();
    }

    InputStream readStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }
}
