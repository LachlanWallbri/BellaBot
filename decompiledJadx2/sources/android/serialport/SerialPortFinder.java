package android.serialport;

import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0943d;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SerialPortFinder {
    private static final String TAG = "Lidar";
    private Vector<Driver> mDrivers = null;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public class Driver {
        private String mDeviceRoot;
        Vector<File> mDevices = null;
        private String mDriverName;

        public Driver(String str, String str2) {
            this.mDriverName = str;
            this.mDeviceRoot = str2;
        }

        public Vector<File> getDevices() {
            if (this.mDevices == null) {
                this.mDevices = new Vector<>();
                File[] listFiles = new File(C0943d.f793a).listFiles();
                if (listFiles != null) {
                    for (int i = 0; i < listFiles.length; i++) {
                        if (listFiles[i].getAbsolutePath().startsWith(this.mDeviceRoot)) {
                            Pdlog.m3273d("Lidar", "Found new device: " + listFiles[i]);
                            this.mDevices.add(listFiles[i]);
                        }
                    }
                }
            }
            return this.mDevices;
        }

        public String getName() {
            return this.mDriverName;
        }
    }

    Vector<Driver> getDrivers() throws IOException {
        if (this.mDrivers == null) {
            this.mDrivers = new Vector<>();
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("/proc/tty/drivers"));
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                String trim = readLine.substring(0, 21).trim();
                String[] split = readLine.split(" +");
                if (split.length >= 5 && split[split.length - 1].equals("serial")) {
                    Pdlog.m3273d("Lidar", "Found new driver " + trim + " on " + split[split.length - 4]);
                    this.mDrivers.add(new Driver(trim, split[split.length + (-4)]));
                }
            }
            lineNumberReader.close();
        }
        return this.mDrivers;
    }

    public String[] getAllDevices() {
        Vector vector = new Vector();
        try {
            Iterator<Driver> it = getDrivers().iterator();
            while (it.hasNext()) {
                Driver next = it.next();
                Iterator<File> it2 = next.getDevices().iterator();
                while (it2.hasNext()) {
                    vector.add(String.format("%s (%s)", it2.next().getName(), next.getName()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }

    public String[] getAllDevicesPath() {
        Vector vector = new Vector();
        try {
            Iterator<Driver> it = getDrivers().iterator();
            while (it.hasNext()) {
                Iterator<File> it2 = it.next().getDevices().iterator();
                while (it2.hasNext()) {
                    vector.add(it2.next().getAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }
}
