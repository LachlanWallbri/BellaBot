package com.alibaba.sdk.android.oss.common.utils;

import com.alibaba.sdk.android.oss.common.OSSLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class HttpdnsMini {
    private static final String ACCOUNT_ID = "181345";
    private static final int EMPTY_RESULT_HOST_TTL = 30;
    private static final int MAX_HOLD_HOST_NUM = 100;
    private static final int MAX_THREAD_NUM = 5;
    private static final int RESOLVE_TIMEOUT_IN_SEC = 10;
    private static final String SERVER_IP = "203.107.1.1";
    private static final String TAG = "HttpDnsMini";
    private static HttpdnsMini instance;
    private ConcurrentMap<String, HostObject> hostManager = new ConcurrentHashMap();
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public class HostObject {
        private String hostName;

        /* renamed from: ip */
        private String f320ip;
        private long queryTime;
        private long ttl;

        HostObject() {
        }

        public String toString() {
            return "[hostName=" + getHostName() + ", ip=" + this.f320ip + ", ttl=" + getTtl() + ", queryTime=" + this.queryTime + "]";
        }

        public boolean isExpired() {
            return getQueryTime() + this.ttl < System.currentTimeMillis() / 1000;
        }

        public boolean isStillAvailable() {
            return (getQueryTime() + this.ttl) + 600 > System.currentTimeMillis() / 1000;
        }

        public String getIp() {
            return this.f320ip;
        }

        public void setIp(String str) {
            this.f320ip = str;
        }

        public void setHostName(String str) {
            this.hostName = str;
        }

        public String getHostName() {
            return this.hostName;
        }

        public long getTtl() {
            return this.ttl;
        }

        public void setTtl(long j) {
            this.ttl = j;
        }

        public long getQueryTime() {
            return this.queryTime;
        }

        public void setQueryTime(long j) {
            this.queryTime = j;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    class QueryHostTask implements Callable<String> {
        private boolean hasRetryed = false;
        private String hostName;

        public QueryHostTask(String str) {
            this.hostName = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0163  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x016b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x0170 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public String call() {
            InputStream inputStream;
            InputStream inputStream2;
            String str = "http://" + HttpdnsMini.SERVER_IP + "/" + HttpdnsMini.ACCOUNT_ID + "/d?host=" + this.hostName;
            OSSLog.logDebug("[httpdnsmini] - buildUrl: " + str);
            InputStream inputStream3 = null;
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    if (httpURLConnection.getResponseCode() != 200) {
                        OSSLog.logError("[httpdnsmini] - responseCodeNot 200, but: " + httpURLConnection.getResponseCode());
                        inputStream2 = null;
                    } else {
                        inputStream2 = httpURLConnection.getInputStream();
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                sb.append(readLine);
                            }
                            JSONObject jSONObject = new JSONObject(sb.toString());
                            String string = jSONObject.getString("host");
                            long j = jSONObject.getLong("ttl");
                            JSONArray jSONArray = jSONObject.getJSONArray("ips");
                            OSSLog.logDebug("[httpdnsmini] - ips:" + jSONArray.toString());
                            if (string != null && jSONArray != null && jSONArray.length() > 0) {
                                if (j == 0) {
                                    j = 30;
                                }
                                HostObject hostObject = new HostObject();
                                String string2 = jSONArray == null ? null : jSONArray.getString(0);
                                hostObject.setHostName(string);
                                hostObject.setTtl(j);
                                hostObject.setIp(string2);
                                hostObject.setQueryTime(System.currentTimeMillis() / 1000);
                                OSSLog.logDebug("[httpdnsmini] - resolve result:" + hostObject.toString());
                                if (HttpdnsMini.this.hostManager.size() < 100) {
                                    HttpdnsMini.this.hostManager.put(this.hostName, hostObject);
                                }
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                return string2;
                            }
                        } catch (Exception e2) {
                            inputStream = inputStream2;
                            e = e2;
                            try {
                                if (OSSLog.isEnableLog()) {
                                    e.printStackTrace();
                                    OSSLog.logThrowable2Local(e);
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (!this.hasRetryed) {
                                }
                            } catch (Throwable th) {
                                th = th;
                                inputStream3 = inputStream;
                                if (inputStream3 != null) {
                                    try {
                                        inputStream3.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            inputStream3 = inputStream2;
                            th = th2;
                            if (inputStream3 != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    inputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            if (!this.hasRetryed) {
                return null;
            }
            this.hasRetryed = true;
            return call();
        }
    }

    private HttpdnsMini() {
    }

    public static HttpdnsMini getInstance() {
        if (instance == null) {
            synchronized (HttpdnsMini.class) {
                if (instance == null) {
                    instance = new HttpdnsMini();
                }
            }
        }
        return instance;
    }

    public String getIpByHostAsync(String str) {
        HostObject hostObject = this.hostManager.get(str);
        if (hostObject == null || hostObject.isExpired()) {
            OSSLog.logDebug("[httpdnsmini] - refresh host: " + str);
            this.pool.submit(new QueryHostTask(str));
        }
        if (hostObject == null || !hostObject.isStillAvailable()) {
            return null;
        }
        return hostObject.getIp();
    }
}
