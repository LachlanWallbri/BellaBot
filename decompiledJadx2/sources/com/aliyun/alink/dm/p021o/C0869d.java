package com.aliyun.alink.dm.p021o;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.aliyun.alink.dm.p017k.C0859a;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NetworkUtils.java */
/* renamed from: com.aliyun.alink.dm.o.d */
/* loaded from: classes.dex */
public class C0869d {

    /* renamed from: a */
    private static HashMap<String, String> f478a = new HashMap<>();

    /* renamed from: a */
    public static String m186a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return null;
            }
            if (activeNetworkInfo.getType() == 0) {
                try {
                    try {
                        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                        while (networkInterfaces.hasMoreElements()) {
                            Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                            while (inetAddresses.hasMoreElements()) {
                                InetAddress nextElement = inetAddresses.nextElement();
                                if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                                    return nextElement.getHostAddress();
                                }
                            }
                        }
                        return null;
                    } catch (SocketException e) {
                        e.printStackTrace();
                        return null;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            if (activeNetworkInfo.getType() == 1) {
                return m185a(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
            }
            if (activeNetworkInfo.getType() == 9) {
                return m184a();
            }
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m185a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    /* renamed from: a */
    private static String m184a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress();
                    }
                }
            }
            return "0.0.0.0";
        } catch (SocketException unused) {
            return "0.0.0.0";
        }
    }

    /* renamed from: b */
    private static String m188b(Context context) {
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return macAddress != null ? macAddress : macAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m187a(String str, Context context) {
        NetworkInterface byName;
        if (f478a != null && !TextUtils.isEmpty(str) && f478a.containsKey(str) && TextUtils.isEmpty(f478a.get(str))) {
            return f478a.get(str);
        }
        String str2 = null;
        try {
            byName = NetworkInterface.getByName(str);
        } catch (Exception e) {
            C0859a.m135d("NetworkUtils", "getMacAddress: error:" + e.toString());
        }
        if (byName == null) {
            C0859a.m135d("NetworkUtils", "getMacAddress: NIC == null");
            return null;
        }
        byte[] hardwareAddress = byName.getHardwareAddress();
        if (hardwareAddress == null) {
            C0859a.m135d("NetworkUtils", "getMacAddress: b == null");
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : hardwareAddress) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = 0 + hexString;
            }
            stringBuffer.append(hexString);
        }
        str2 = stringBuffer.toString().toUpperCase();
        if (TextUtils.isEmpty(str2) && "wlan0".equals(str) && context != null) {
            str2 = m188b(context);
        }
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            f478a.put(str, str2);
        }
        C0859a.m131a("NetworkUtils", "getMacAddress Mac Address : " + str2 + ",tag=" + str);
        return str2;
    }
}
