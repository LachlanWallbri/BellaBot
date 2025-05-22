package com.acs.smartcard;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Reader {
    public static final int CARD_ABSENT = 1;
    public static final int CARD_COLD_RESET = 1;
    public static final int CARD_NEGOTIABLE = 5;
    public static final int CARD_POWERED = 4;
    public static final int CARD_POWER_DOWN = 0;
    public static final int CARD_PRESENT = 2;
    public static final int CARD_SPECIFIC = 6;
    public static final int CARD_SWALLOWED = 3;
    public static final int CARD_UNKNOWN = 0;
    public static final int CARD_WARM_RESET = 2;
    public static final int IOCTL_ACR128_READER_COMMAND = 2079;
    public static final int IOCTL_ACR83_DISPLAY_LCD_MESSAGE = 2079;
    public static final int IOCTL_ACR83_GET_FIRMWARE_VERSION = 2078;
    public static final int IOCTL_ACR83_READ_KEY = 2080;
    public static final int IOCTL_CCID_ESCAPE = 3500;
    public static final int IOCTL_GET_FEATURE_REQUEST = 3400;
    public static final int PROTOCOL_DEFAULT = Integer.MIN_VALUE;
    public static final int PROTOCOL_OPTIMAL = 0;
    public static final int PROTOCOL_RAW = 65536;
    public static final int PROTOCOL_T0 = 1;
    public static final int PROTOCOL_T1 = 2;
    public static final int PROTOCOL_TX = 3;
    public static final int PROTOCOL_UNDEFINED = 0;

    /* renamed from: a */
    private static final int[] f156a = {120566529, 120566532, 120566533, 120554240, 120554242, 120554247, 120554241, 120557568, 120557775, 120557772, 120557784, 120566016, 120566017, 120566018, 120566019, 120566020, 120566028, 120565760, 120557778, 120528913, 120555776, 120555777, 120555778, 120525317, 120525316, 120525318, 120529408, 120529454, 120529463, 120529457, 120529428, 120525440, 120529415, 120529451, 120529414, 120529445, 120529411, 120529434, 120529449, 120529503, 120529504, 120529432, 120529435, 120529458, 120529474, 120529464, 120529487, 120529467, 120529470, 120529476, 120529497, 120529471, 120529465, 120529425, 120529490, 120529152, 120529444, 120529423, 120529443, 120529416, 120523009, 120529418, 120529429, 120529440, 120529459, 120529460, 120529461, 120529462, 120529427, 120529452, 120529496, 120529482, 120553985, 120553990, 120557574, 120557787, 120566272, 120566022, 120566034};

    /* renamed from: b */
    private UsbManager f157b;

    /* renamed from: c */
    private UsbDevice f158c;

    /* renamed from: d */
    private int f159d;

    /* renamed from: e */
    private List<UsbInterface> f160e = new ArrayList();

    /* renamed from: f */
    private List<C0740d> f161f = new ArrayList();

    /* renamed from: g */
    private List<C0744h> f162g = new ArrayList();

    /* renamed from: h */
    private UsbDeviceConnection f163h;

    /* renamed from: i */
    private String f164i;

    /* renamed from: j */
    private Thread f165j;

    /* renamed from: k */
    private OnStateChangeListener f166k;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface OnStateChangeListener {
        void onStateChange(int i, int i2, int i3);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.acs.smartcard.Reader$b */
    /* loaded from: classes.dex */
    enum EnumC0736b {
        SUCCESS,
        FAILURE,
        PENDING;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static EnumC0736b[] valuesCustom() {
            EnumC0736b[] enumC0736bArr = new EnumC0736b[3];
            System.arraycopy(values(), 0, enumC0736bArr, 0, 3);
            return enumC0736bArr;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.acs.smartcard.Reader$a */
    /* loaded from: classes.dex */
    class RunnableC0735a implements Runnable {
        private RunnableC0735a() {
        }

        /* synthetic */ RunnableC0735a(Reader reader, byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i;
            int i2;
            int i3;
            int i4;
            for (int i5 = 0; i5 < Reader.this.f162g.size(); i5++) {
                C0744h c0744h = (C0744h) Reader.this.f162g.get(i5);
                C0743g c0743g = c0744h.f246c;
                synchronized (c0744h) {
                    i3 = c0743g.f237a;
                    if (c0744h.f245b) {
                        c0743g.f237a = 2;
                    } else {
                        try {
                            Reader.m31a(Reader.this, c0744h);
                        } catch (ReaderException unused) {
                        }
                    }
                    i4 = c0743g.f237a;
                }
                Reader.m30a(Reader.this, i5, i3, i4);
            }
            Iterator it = Reader.this.f161f.iterator();
            while (it.hasNext()) {
                ((C0740d) it.next()).m67b();
            }
            while (!Reader.this.f165j.isInterrupted()) {
                UsbRequest requestWait = Reader.this.f163h.requestWait();
                if (requestWait != null) {
                    C0740d c0740d = (C0740d) requestWait.getClientData();
                    byte[] m68c = c0740d.m68c();
                    int length = m68c.length;
                    c0740d.m67b();
                    if (length < 2) {
                        continue;
                    } else {
                        if ((m68c[0] & 255) == 1) {
                            m68c[0] = 80;
                            if ((m68c[1] & 255) == 192) {
                                m68c[1] = 2;
                            } else {
                                m68c[1] = 3;
                            }
                            length = 2;
                        }
                        if ((m68c[0] & 255) == 80) {
                            List<C0744h> m66a = c0740d.m66a();
                            for (int i6 = 0; i6 < m66a.size(); i6++) {
                                C0744h c0744h2 = m66a.get(i6);
                                if (!c0744h2.f245b) {
                                    C0743g c0743g2 = c0744h2.f246c;
                                    synchronized (c0744h2) {
                                        i = c0743g2.f237a;
                                        int i7 = (i6 % 4) * 2;
                                        int i8 = (i6 / 4) + 1;
                                        if (i8 < length) {
                                            if ((m68c[i8] & (1 << i7)) != 0) {
                                                if (c0743g2.f237a <= 1) {
                                                    c0743g2.f237a = 2;
                                                }
                                            } else {
                                                c0743g2.f237a = 1;
                                            }
                                        }
                                        i2 = c0743g2.f237a;
                                    }
                                    Reader.m30a(Reader.this, Reader.this.f162g.indexOf(c0744h2), i, i2);
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
    }

    public Reader(UsbManager usbManager) {
        if (usbManager == null) {
            throw new IllegalArgumentException("The manager is null.");
        }
        this.f157b = usbManager;
    }

    public boolean isSupported(UsbDevice usbDevice) {
        if (usbDevice == null) {
            return false;
        }
        int productId = usbDevice.getProductId() | (usbDevice.getVendorId() << 16);
        int i = 0;
        while (true) {
            int[] iArr = f156a;
            if (i >= 79) {
                return false;
            }
            if (iArr[i] == productId) {
                return true;
            }
            i++;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0473, code lost:
    
        if (r11 == r6) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0479, code lost:
    
        if (r11 == r6) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x04a3, code lost:
    
        if (r13.f244a == r6) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x04aa, code lost:
    
        if (r13.f244a > 0) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x04ef, code lost:
    
        if (r13.f244a > 0) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x03b6, code lost:
    
        if (r13.f244a == r6) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x03f4, code lost:
    
        if (r13.f244a > 2) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x03fc, code lost:
    
        if (r13.f244a > r6) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0404, code lost:
    
        if (r13.f244a > r6) goto L188;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x004d. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:74:0x03aa. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:186:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0332  */
    /* JADX WARN: Type inference failed for: r12v0, types: [android.hardware.usb.UsbDeviceConnection] */
    /* JADX WARN: Type inference failed for: r14v22 */
    /* JADX WARN: Type inference failed for: r14v29 */
    /* JADX WARN: Type inference failed for: r14v5, types: [int] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v12, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r6v15 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void open(UsbDevice usbDevice) {
        C0739c[] c0739cArr;
        char c;
        int interfaceCount;
        int i;
        int i2;
        byte[] bArr;
        String str;
        String str2;
        int i3;
        int i4;
        int i5;
        String str3;
        String str4;
        String str5;
        C0740d c0740d;
        int i6;
        C0739c c0739c;
        int i7;
        C0739c c0739c2;
        C0739c c0739c3;
        int i8;
        C0739c c0739c4;
        int i9;
        C0739c c0739c5;
        int i10;
        if (usbDevice == null) {
            throw new IllegalArgumentException("The device is null.");
        }
        UsbDevice usbDevice2 = this.f158c;
        if (usbDevice2 != null) {
            if (usbDevice.equals(usbDevice2)) {
                return;
            } else {
                close();
            }
        }
        if (!isSupported(usbDevice)) {
            throw new IllegalArgumentException("The device is not supported.");
        }
        int productId = usbDevice.getProductId() | (usbDevice.getVendorId() << 16);
        new StringBuilder("Reader ID: ").append(Integer.toHexString(productId));
        ?? openDevice = this.f157b.openDevice(usbDevice);
        if (openDevice == 0) {
            throw new IllegalArgumentException("Cannot open device.");
        }
        switch (productId) {
            case 120528913:
                c0739cArr = new C0739c[]{new C0739c()};
                c0739cArr[0].f195a = 4;
                c0739cArr[0].f196b = 3;
                c0739cArr[0].f197c = 3;
                c0739cArr[0].f198d = 3600;
                c0739cArr[0].f199e = 3600;
                c0739cArr[0].f200f = 9677;
                c0739cArr[0].f201g = 116129;
                c0739cArr[0].f202h = GateControllerMsg.ControlCode.Error;
                c0739cArr[0].f203i = 132282;
                c0739cArr[0].f204j = 271;
                c0739cArr[0].f205k = 2069;
                c = 0;
                interfaceCount = usbDevice.getInterfaceCount();
                if (interfaceCount > c0739cArr.length) {
                    interfaceCount = c0739cArr.length;
                }
                i = interfaceCount;
                i2 = 0;
                while (i2 < i) {
                    ?? r6 = 1;
                    boolean z = false;
                    if (c0739cArr[i2] != null) {
                        UsbInterface usbInterface = usbDevice.getInterface(i2);
                        int endpointCount = usbInterface.getEndpointCount();
                        int i11 = 0;
                        UsbEndpoint usbEndpoint = null;
                        UsbEndpoint usbEndpoint2 = null;
                        UsbEndpoint usbEndpoint3 = null;
                        while (i11 < endpointCount) {
                            UsbEndpoint endpoint = usbInterface.getEndpoint(i11);
                            int type = endpoint.getType();
                            if (type == 2) {
                                if (endpoint.getDirection() == 0) {
                                    if (usbEndpoint == null) {
                                        usbEndpoint = endpoint;
                                    }
                                } else if (usbEndpoint3 == null) {
                                    usbEndpoint3 = endpoint;
                                }
                            } else if (type == 3 && endpoint.getDirection() == 128 && usbEndpoint2 == null) {
                                usbEndpoint2 = endpoint;
                            }
                            i11++;
                            r6 = 1;
                            z = false;
                        }
                        if (usbEndpoint == null || usbEndpoint3 == null || usbEndpoint2 == null) {
                            openDevice.close();
                            throw new IllegalArgumentException("Cannot find endpoints.");
                        }
                        if (!openDevice.claimInterface(usbInterface, z) && !openDevice.claimInterface(usbInterface, r6)) {
                            openDevice.close();
                            throw new IllegalArgumentException("Cannot claim interface.");
                        }
                        this.f160e.add(usbInterface);
                        if (c == '&') {
                            c0740d = new C0737a(openDevice, usbEndpoint, usbEndpoint3, usbEndpoint2);
                        } else {
                            c0740d = new C0740d(openDevice, usbEndpoint, usbEndpoint3, usbEndpoint2);
                        }
                        C0740d c0740d2 = c0740d;
                        this.f161f.add(c0740d2);
                        if (productId == 120553985) {
                            c0740d2.m70d(10);
                        }
                        int i12 = 0;
                        ?? r14 = z;
                        while (i12 <= c0739cArr[i2].f195a) {
                            C0744h c0744h = new C0744h();
                            c0744h.f244a = i12;
                            c0744h.f248e = c0740d2;
                            c0744h.f249f = new C0739c(c0739cArr[i2]);
                            C0740d c0740d3 = c0744h.f248e;
                            switch (productId) {
                                case 120525440:
                                    if (c0744h.f244a != 2) {
                                        C0743g c0743g = c0744h.f246c;
                                        int i13 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g2 = c0744h.f246c;
                                        int i14 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    }
                                    c0744h.f245b = r6;
                                    C0743g c0743g3 = c0744h.f246c;
                                    int i132 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g22 = c0744h.f246c;
                                    int i142 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120528913:
                                    i6 = 9677;
                                    if (c0744h.f244a < 2) {
                                        c0744h.f249f.f201g = 116129;
                                        C0743g c0743g32 = c0744h.f246c;
                                        int i1322 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g222 = c0744h.f246c;
                                        int i1422 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    } else {
                                        c0739c = c0744h.f249f;
                                        c0739c.f201g = i6;
                                        c0744h.f245b = r6;
                                        C0743g c0743g322 = c0744h.f246c;
                                        int i13222 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g2222 = c0744h.f246c;
                                        int i14222 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    }
                                case 120529152:
                                    if (c0744h.f244a == 0) {
                                        c0744h.f249f.f203i = 132282;
                                        c0739c2 = c0744h.f249f;
                                        i7 = 116129;
                                    } else {
                                        i7 = 116129;
                                        if (c0744h.f244a == r6) {
                                            c0744h.f249f.f203i = 132218;
                                            c0739c2 = c0744h.f249f;
                                        } else {
                                            c0744h.f249f.f203i = 132282;
                                            c0739c = c0744h.f249f;
                                            i6 = 9677;
                                            c0739c.f201g = i6;
                                            c0744h.f245b = r6;
                                            C0743g c0743g3222 = c0744h.f246c;
                                            int i132222 = c0744h.f249f.f197c;
                                            c0744h.f246c.f238b = c0744h.f249f.f198d;
                                            C0743g c0743g22222 = c0744h.f246c;
                                            int i142222 = c0744h.f249f.f199e;
                                            c0744h.f246c.f239c = c0744h.f249f.f200f;
                                            c0744h.f246c.f240d = c0744h.f249f.f201g;
                                            c0744h.f246c.f241e = c0744h.f249f.f202h;
                                            c0744h.f246c.f237a = r6;
                                            this.f162g.add(c0744h);
                                            c0740d2.m66a().add(c0744h);
                                            i12++;
                                            r14 = 0;
                                        }
                                    }
                                    c0739c2.f201g = i7;
                                    C0743g c0743g32222 = c0744h.f246c;
                                    int i1322222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g222222 = c0744h.f246c;
                                    int i1422222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120529408:
                                    try {
                                        synchronized (c0740d3) {
                                            c0740d3.mo58b(c0744h.f244a);
                                        }
                                    } catch (ReaderException unused) {
                                    }
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException unused2) {
                                    }
                                    C0743g c0743g322222 = c0744h.f246c;
                                    int i13222222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g2222222 = c0744h.f246c;
                                    int i14222222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120529414:
                                    break;
                                case 120529425:
                                case 120529444:
                                case 120529474:
                                    if (c0744h.f244a == 0) {
                                        c0744h.f249f.f203i = 132282;
                                        c0744h.f249f.f201g = 344100;
                                        if (productId == 120529444 && m47f(c0744h) >= 526) {
                                            c0739c4 = c0744h.f249f;
                                            i9 = 263354;
                                            c0739c4.f203i = i9;
                                        }
                                        C0743g c0743g3222222 = c0744h.f246c;
                                        int i132222222 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g22222222 = c0744h.f246c;
                                        int i142222222 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    } else if (c0744h.f244a == r6) {
                                        c0744h.f249f.f203i = 263290;
                                        c0739c3 = c0744h.f249f;
                                        i8 = 344100;
                                        c0739c3.f201g = i8;
                                        C0743g c0743g32222222 = c0744h.f246c;
                                        int i1322222222 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g222222222 = c0744h.f246c;
                                        int i1422222222 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    } else {
                                        c0744h.f249f.f203i = 132282;
                                        c0744h.f249f.f201g = 125000;
                                        c0744h.f245b = r6;
                                        C0743g c0743g322222222 = c0744h.f246c;
                                        int i13222222222 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g2222222222 = c0744h.f246c;
                                        int i14222222222 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    }
                                    break;
                                case 120529427:
                                    break;
                                case 120529428:
                                    break;
                                case 120529429:
                                    if (c0744h.f244a == r6) {
                                        c0744h.f249f.f203i = 263290;
                                        c0744h.f249f.f201g = 344100;
                                        C0743g c0743g3222222222 = c0744h.f246c;
                                        int i132222222222 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g22222222222 = c0744h.f246c;
                                        int i142222222222 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    }
                                    c0744h.f249f.f203i = 132282;
                                    c0744h.f249f.f201g = 125000;
                                    c0744h.f245b = r6;
                                    C0743g c0743g32222222222 = c0744h.f246c;
                                    int i1322222222222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g222222222222 = c0744h.f246c;
                                    int i1422222222222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120529432:
                                case 120529434:
                                case 120529467:
                                case 120529504:
                                    break;
                                case 120529440:
                                    break;
                                case 120529454:
                                    if (c0744h.f244a > 0) {
                                        c0744h.f249f.f203i = 132282;
                                        c0744h.f249f.f201g = 125000;
                                        c0744h.f245b = r6;
                                    }
                                    C0743g c0743g322222222222 = c0744h.f246c;
                                    int i13222222222222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g2222222222222 = c0744h.f246c;
                                    int i14222222222222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120529457:
                                    if (c0744h.f244a > 0) {
                                        c0744h.f249f.f203i = 132282;
                                        c0739c5 = c0744h.f249f;
                                        i10 = 129032;
                                        c0739c5.f201g = i10;
                                        c0744h.f245b = r6;
                                    }
                                    C0743g c0743g3222222222222 = c0744h.f246c;
                                    int i132222222222222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g22222222222222 = c0744h.f246c;
                                    int i142222222222222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120529460:
                                case 120529461:
                                case 120529462:
                                    if (c0744h.f244a == r6) {
                                        c0744h.f249f.f203i = 132282;
                                        c0739c3 = c0744h.f249f;
                                        i8 = 600000;
                                        c0739c3.f201g = i8;
                                        C0743g c0743g32222222222222 = c0744h.f246c;
                                        int i1322222222222222 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g222222222222222 = c0744h.f246c;
                                        int i1422222222222222 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    } else {
                                        if (c0744h.f244a > r6) {
                                            c0744h.f249f.f203i = 132282;
                                            c0739c5 = c0744h.f249f;
                                            i10 = 172043;
                                            c0739c5.f201g = i10;
                                            c0744h.f245b = r6;
                                        }
                                        C0743g c0743g322222222222222 = c0744h.f246c;
                                        int i13222222222222222 = c0744h.f249f.f197c;
                                        c0744h.f246c.f238b = c0744h.f249f.f198d;
                                        C0743g c0743g2222222222222222 = c0744h.f246c;
                                        int i14222222222222222 = c0744h.f249f.f199e;
                                        c0744h.f246c.f239c = c0744h.f249f.f200f;
                                        c0744h.f246c.f240d = c0744h.f249f.f201g;
                                        c0744h.f246c.f241e = c0744h.f249f.f202h;
                                        c0744h.f246c.f237a = r6;
                                        this.f162g.add(c0744h);
                                        c0740d2.m66a().add(c0744h);
                                        i12++;
                                        r14 = 0;
                                    }
                                case 120553985:
                                    c0744h.f249f.f205k = DMErrorCode.ERROR_API_CLIENT_SEND_FAIL;
                                    c0744h.f249f.f203i &= -458753;
                                    c0739c4 = c0744h.f249f;
                                    i9 = c0739c4.f203i | 131072;
                                    c0739c4.f203i = i9;
                                    C0743g c0743g3222222222222222 = c0744h.f246c;
                                    int i132222222222222222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g22222222222222222 = c0744h.f246c;
                                    int i142222222222222222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120554240:
                                case 120554241:
                                case 120554242:
                                case 120554247:
                                    break;
                                case 120555776:
                                case 120555778:
                                    break;
                                case 120555777:
                                    break;
                                case 120557775:
                                    if (c0744h.f244a == r6) {
                                        c0744h.f249f.f201g = 10752;
                                        c0744h.f245b = r6;
                                    }
                                    C0743g c0743g32222222222222222 = c0744h.f246c;
                                    int i1322222222222222222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g222222222222222222 = c0744h.f246c;
                                    int i1422222222222222222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120557778:
                                    c0744h.f249f.f205k = DMErrorCode.ERROR_API_CLIENT_SEND_FAIL;
                                    if (m44e(c0744h) >= 17664) {
                                        c0744h.f249f.f203i &= -458753;
                                        c0744h.f249f.f203i |= 131072;
                                    } else {
                                        c0744h.f249f.f206l = r14;
                                    }
                                    C0743g c0743g322222222222222222 = c0744h.f246c;
                                    int i13222222222222222222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g2222222222222222222 = c0744h.f246c;
                                    int i14222222222222222222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                                case 120566020:
                                    break;
                                default:
                                    C0743g c0743g3222222222222222222 = c0744h.f246c;
                                    int i132222222222222222222 = c0744h.f249f.f197c;
                                    c0744h.f246c.f238b = c0744h.f249f.f198d;
                                    C0743g c0743g22222222222222222222 = c0744h.f246c;
                                    int i142222222222222222222 = c0744h.f249f.f199e;
                                    c0744h.f246c.f239c = c0744h.f249f.f200f;
                                    c0744h.f246c.f240d = c0744h.f249f.f201g;
                                    c0744h.f246c.f241e = c0744h.f249f.f202h;
                                    c0744h.f246c.f237a = r6;
                                    this.f162g.add(c0744h);
                                    c0740d2.m66a().add(c0744h);
                                    i12++;
                                    r14 = 0;
                            }
                        }
                    }
                    i2++;
                }
                bArr = new byte[255];
                byte b = 0;
                if (openDevice.controlTransfer(128, 6, 256, 0, bArr, 255, 2000) > 15 && (bArr[0] & 255) > 15 && (bArr[1] & 255) == 1) {
                    i3 = bArr[14] & 255;
                    i4 = bArr[15] & 255;
                    new StringBuilder("iManufacturer: ").append(i3);
                    new StringBuilder("iProduct: ").append(i4);
                    if (i3 != 0 && i4 != 0) {
                        if (openDevice.controlTransfer(128, 6, 768, 0, bArr, 255, 2000) > 3 && (bArr[0] & 255) > 3 && (bArr[1] & 255) == 3) {
                            i5 = (bArr[2] & 255) | ((bArr[3] & 255) << 8);
                            new StringBuilder("langId: ").append(i5);
                            if (openDevice.controlTransfer(128, 6, i3 | 768, i5, bArr, 255, 2000) <= 2 && (bArr[0] & 255) > 2) {
                                if ((bArr[1] & 255) == 3) {
                                    try {
                                        str5 = new String(bArr, 2, (bArr[0] & 255) - 2, "UTF-16LE");
                                        try {
                                            new StringBuilder("Manufacturer: ").append(str5);
                                        } catch (UnsupportedEncodingException unused3) {
                                        }
                                    } catch (UnsupportedEncodingException unused4) {
                                        str5 = null;
                                    }
                                    str3 = str5;
                                    if (openDevice.controlTransfer(128, 6, i4 | 768, i5, bArr, 255, 2000) > 2 || (bArr[0] & 255) <= 2 || (bArr[1] & 255) != 3) {
                                        str = str3;
                                        str2 = null;
                                        this.f164i = "";
                                        if (str != null) {
                                            this.f164i = String.valueOf("") + str.trim();
                                        }
                                        if (str2 != null) {
                                            if (this.f164i.length() > 0) {
                                                this.f164i = String.valueOf(this.f164i) + " ";
                                            }
                                            this.f164i = String.valueOf(this.f164i) + str2.trim();
                                        }
                                        new StringBuilder("Reader name: ").append(this.f164i);
                                    } else {
                                        try {
                                            String str6 = new String(bArr, 2, (bArr[0] & 255) - 2, "UTF-16LE");
                                            try {
                                                new StringBuilder("Product: ").append(str6);
                                                str2 = str6;
                                            } catch (UnsupportedEncodingException unused5) {
                                                str4 = str6;
                                                str2 = str4;
                                                str = str3;
                                                this.f164i = "";
                                                if (str != null) {
                                                }
                                                if (str2 != null) {
                                                }
                                                new StringBuilder("Reader name: ").append(this.f164i);
                                                this.f158c = usbDevice;
                                                this.f159d = productId;
                                                this.f163h = openDevice;
                                                Thread thread = new Thread(new RunnableC0735a(this, b));
                                                this.f165j = thread;
                                                thread.start();
                                                return;
                                            }
                                        } catch (UnsupportedEncodingException unused6) {
                                            str4 = null;
                                        }
                                        str = str3;
                                        this.f164i = "";
                                        if (str != null) {
                                        }
                                        if (str2 != null) {
                                        }
                                        new StringBuilder("Reader name: ").append(this.f164i);
                                    }
                                }
                            }
                            str3 = null;
                            if (openDevice.controlTransfer(128, 6, i4 | 768, i5, bArr, 255, 2000) > 2) {
                            }
                            str = str3;
                            str2 = null;
                            this.f164i = "";
                            if (str != null) {
                            }
                            if (str2 != null) {
                            }
                            new StringBuilder("Reader name: ").append(this.f164i);
                        }
                    }
                    this.f158c = usbDevice;
                    this.f159d = productId;
                    this.f163h = openDevice;
                    Thread thread2 = new Thread(new RunnableC0735a(this, b));
                    this.f165j = thread2;
                    thread2.start();
                    return;
                }
                str = null;
                str2 = null;
                this.f164i = "";
                if (str != null) {
                }
                if (str2 != null) {
                }
                new StringBuilder("Reader name: ").append(this.f164i);
                this.f158c = usbDevice;
                this.f159d = productId;
                this.f163h = openDevice;
                Thread thread22 = new Thread(new RunnableC0735a(this, b));
                this.f165j = thread22;
                thread22.start();
                return;
            case 120529152:
                c0739cArr = new C0739c[]{new C0739c()};
                c0739cArr[0].f195a = 2;
                c0739cArr[0].f196b = 3;
                c0739cArr[0].f197c = 3;
                c0739cArr[0].f198d = 3600;
                c0739cArr[0].f199e = 3600;
                c0739cArr[0].f200f = 9677;
                c0739cArr[0].f201g = 116129;
                c0739cArr[0].f202h = GateControllerMsg.ControlCode.Error;
                c0739cArr[0].f203i = 132282;
                c0739cArr[0].f204j = 271;
                c = 0;
                interfaceCount = usbDevice.getInterfaceCount();
                if (interfaceCount > c0739cArr.length) {
                }
                i = interfaceCount;
                i2 = 0;
                while (i2 < i) {
                }
                bArr = new byte[255];
                byte b2 = 0;
                if (openDevice.controlTransfer(128, 6, 256, 0, bArr, 255, 2000) > 15) {
                    i3 = bArr[14] & 255;
                    i4 = bArr[15] & 255;
                    new StringBuilder("iManufacturer: ").append(i3);
                    new StringBuilder("iProduct: ").append(i4);
                    if (i3 != 0) {
                        if (openDevice.controlTransfer(128, 6, 768, 0, bArr, 255, 2000) > 3) {
                            i5 = (bArr[2] & 255) | ((bArr[3] & 255) << 8);
                            new StringBuilder("langId: ").append(i5);
                            if (openDevice.controlTransfer(128, 6, i3 | 768, i5, bArr, 255, 2000) <= 2) {
                                break;
                            }
                            str3 = null;
                            if (openDevice.controlTransfer(128, 6, i4 | 768, i5, bArr, 255, 2000) > 2) {
                            }
                            str = str3;
                            str2 = null;
                            this.f164i = "";
                            if (str != null) {
                            }
                            if (str2 != null) {
                            }
                            new StringBuilder("Reader name: ").append(this.f164i);
                            break;
                        }
                    }
                    this.f158c = usbDevice;
                    this.f159d = productId;
                    this.f163h = openDevice;
                    Thread thread222 = new Thread(new RunnableC0735a(this, b2));
                    this.f165j = thread222;
                    thread222.start();
                    return;
                }
                str = null;
                str2 = null;
                this.f164i = "";
                if (str != null) {
                }
                if (str2 != null) {
                }
                new StringBuilder("Reader name: ").append(this.f164i);
                this.f158c = usbDevice;
                this.f159d = productId;
                this.f163h = openDevice;
                Thread thread2222 = new Thread(new RunnableC0735a(this, b2));
                this.f165j = thread2222;
                thread2222.start();
                return;
            case 120529425:
            case 120529429:
            case 120529444:
            case 120529474:
                c0739cArr = new C0739c[]{new C0739c()};
                c0739cArr[0].f195a = 2;
                c0739cArr[0].f196b = 3;
                c0739cArr[0].f197c = 3;
                c0739cArr[0].f198d = 4000;
                c0739cArr[0].f199e = 4000;
                c0739cArr[0].f200f = 10752;
                c0739cArr[0].f201g = 344100;
                c0739cArr[0].f202h = GateControllerMsg.ControlCode.Error;
                c0739cArr[0].f203i = 132282;
                c0739cArr[0].f204j = 271;
                c = 0;
                interfaceCount = usbDevice.getInterfaceCount();
                if (interfaceCount > c0739cArr.length) {
                }
                i = interfaceCount;
                i2 = 0;
                while (i2 < i) {
                }
                bArr = new byte[255];
                byte b22 = 0;
                if (openDevice.controlTransfer(128, 6, 256, 0, bArr, 255, 2000) > 15) {
                }
                str = null;
                str2 = null;
                this.f164i = "";
                if (str != null) {
                }
                if (str2 != null) {
                }
                new StringBuilder("Reader name: ").append(this.f164i);
                this.f158c = usbDevice;
                this.f159d = productId;
                this.f163h = openDevice;
                Thread thread22222 = new Thread(new RunnableC0735a(this, b22));
                this.f165j = thread22222;
                thread22222.start();
                return;
            case 120557568:
            case 120557574:
                c0739cArr = new C0739c[]{new C0739c()};
                c0739cArr[0].f195a = 0;
                c0739cArr[0].f196b = 7;
                c0739cArr[0].f197c = 3;
                c0739cArr[0].f198d = 4000;
                c0739cArr[0].f199e = 4000;
                c0739cArr[0].f200f = 10752;
                c0739cArr[0].f201g = 229390;
                c0739cArr[0].f202h = GateControllerMsg.ControlCode.Error;
                c0739cArr[0].f203i = 65584;
                c0739cArr[0].f204j = 271;
                c = Typography.amp;
                interfaceCount = usbDevice.getInterfaceCount();
                if (interfaceCount > c0739cArr.length) {
                }
                i = interfaceCount;
                i2 = 0;
                while (i2 < i) {
                }
                bArr = new byte[255];
                byte b222 = 0;
                if (openDevice.controlTransfer(128, 6, 256, 0, bArr, 255, 2000) > 15) {
                }
                str = null;
                str2 = null;
                this.f164i = "";
                if (str != null) {
                }
                if (str2 != null) {
                }
                new StringBuilder("Reader name: ").append(this.f164i);
                this.f158c = usbDevice;
                this.f159d = productId;
                this.f163h = openDevice;
                Thread thread222222 = new Thread(new RunnableC0735a(this, b222));
                this.f165j = thread222222;
                thread222222.start();
                return;
            case 120557775:
                c0739cArr = new C0739c[]{new C0739c()};
                c0739cArr[0].f195a = 1;
                c0739cArr[0].f196b = 7;
                c0739cArr[0].f197c = 3;
                c0739cArr[0].f198d = 4000;
                c0739cArr[0].f199e = 4000;
                c0739cArr[0].f200f = 10752;
                c0739cArr[0].f201g = 229390;
                c0739cArr[0].f202h = GateControllerMsg.ControlCode.Error;
                c0739cArr[0].f203i = 65584;
                c0739cArr[0].f204j = 271;
                c = Typography.amp;
                interfaceCount = usbDevice.getInterfaceCount();
                if (interfaceCount > c0739cArr.length) {
                }
                i = interfaceCount;
                i2 = 0;
                while (i2 < i) {
                }
                bArr = new byte[255];
                byte b2222 = 0;
                if (openDevice.controlTransfer(128, 6, 256, 0, bArr, 255, 2000) > 15) {
                }
                str = null;
                str2 = null;
                this.f164i = "";
                if (str != null) {
                }
                if (str2 != null) {
                }
                new StringBuilder("Reader name: ").append(this.f164i);
                this.f158c = usbDevice;
                this.f159d = productId;
                this.f163h = openDevice;
                Thread thread2222222 = new Thread(new RunnableC0735a(this, b2222));
                this.f165j = thread2222222;
                thread2222222.start();
                return;
            default:
                c0739cArr = new C0739c[usbDevice.getInterfaceCount()];
                if (m27a((UsbDeviceConnection) openDevice, c0739cArr) <= 0) {
                    openDevice.close();
                    throw new IllegalArgumentException("Cannot find CCID descriptor.");
                }
                switch (productId) {
                    case 120525440:
                        c0739cArr[0].f195a = 2;
                        break;
                    case 120529415:
                    case 120529428:
                        c0739cArr[0].f195a = 1;
                        break;
                    case 120566529:
                        c0739cArr[0].f195a = 0;
                        break;
                }
                c = 0;
                interfaceCount = usbDevice.getInterfaceCount();
                if (interfaceCount > c0739cArr.length) {
                }
                i = interfaceCount;
                i2 = 0;
                while (i2 < i) {
                }
                bArr = new byte[255];
                byte b22222 = 0;
                if (openDevice.controlTransfer(128, 6, 256, 0, bArr, 255, 2000) > 15) {
                }
                str = null;
                str2 = null;
                this.f164i = "";
                if (str != null) {
                }
                if (str2 != null) {
                }
                new StringBuilder("Reader name: ").append(this.f164i);
                this.f158c = usbDevice;
                this.f159d = productId;
                this.f163h = openDevice;
                Thread thread22222222 = new Thread(new RunnableC0735a(this, b22222));
                this.f165j = thread22222222;
                thread22222222.start();
                return;
        }
    }

    public void close() {
        if (this.f163h != null) {
            for (int i = 0; i < this.f162g.size(); i++) {
                try {
                    power(i, 0);
                } catch (ReaderException unused) {
                }
            }
            this.f165j.interrupt();
            Iterator<C0740d> it = this.f161f.iterator();
            while (it.hasNext()) {
                it.next().m69d();
            }
            Iterator<UsbInterface> it2 = this.f160e.iterator();
            while (it2.hasNext()) {
                this.f163h.releaseInterface(it2.next());
            }
            this.f163h.close();
            try {
                this.f165j.join();
            } catch (InterruptedException unused2) {
            }
            this.f165j = null;
            Iterator<C0740d> it3 = this.f161f.iterator();
            while (it3.hasNext()) {
                it3.next().m71e();
            }
            this.f162g.clear();
            this.f161f.clear();
            this.f160e.clear();
            this.f158c = null;
            this.f159d = 0;
            this.f163h = null;
            this.f164i = null;
        }
    }

    public boolean isOpened() {
        return this.f163h != null;
    }

    public UsbDevice getDevice() {
        return this.f158c;
    }

    public int getNumSlots() {
        return this.f162g.size();
    }

    public String getReaderName() {
        return this.f164i;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.f166k = onStateChangeListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00ba A[Catch: all -> 0x00ef, TryCatch #9 {, blocks: (B:9:0x0023, B:11:0x0028, B:13:0x0049, B:15:0x004f, B:16:0x0055, B:17:0x005b, B:23:0x007f, B:56:0x008d, B:62:0x0095, B:63:0x0096, B:30:0x00b1, B:32:0x00b4, B:34:0x00ba, B:36:0x00cf, B:40:0x00d5, B:46:0x00dc, B:47:0x00dd, B:51:0x00de, B:52:0x00e3, B:53:0x00e4, B:66:0x0098, B:68:0x009c, B:69:0x00a3, B:70:0x00a4, B:73:0x0088, B:74:0x0089, B:77:0x006d, B:78:0x0074, B:79:0x0075, B:85:0x007c, B:90:0x00a6, B:91:0x00a7, B:92:0x00a8, B:96:0x00ad, B:99:0x00e7, B:100:0x00e8, B:101:0x0058, B:102:0x00e9, B:103:0x00ee, B:81:0x0076, B:82:0x0079, B:94:0x00a9, B:95:0x00ac), top: B:8:0x0023, inners: #2, #4, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00de A[Catch: all -> 0x00ef, TRY_ENTER, TryCatch #9 {, blocks: (B:9:0x0023, B:11:0x0028, B:13:0x0049, B:15:0x004f, B:16:0x0055, B:17:0x005b, B:23:0x007f, B:56:0x008d, B:62:0x0095, B:63:0x0096, B:30:0x00b1, B:32:0x00b4, B:34:0x00ba, B:36:0x00cf, B:40:0x00d5, B:46:0x00dc, B:47:0x00dd, B:51:0x00de, B:52:0x00e3, B:53:0x00e4, B:66:0x0098, B:68:0x009c, B:69:0x00a3, B:70:0x00a4, B:73:0x0088, B:74:0x0089, B:77:0x006d, B:78:0x0074, B:79:0x0075, B:85:0x007c, B:90:0x00a6, B:91:0x00a7, B:92:0x00a8, B:96:0x00ad, B:99:0x00e7, B:100:0x00e8, B:101:0x0058, B:102:0x00e9, B:103:0x00ee, B:81:0x0076, B:82:0x0079, B:94:0x00a9, B:95:0x00ac), top: B:8:0x0023, inners: #2, #4, #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] power(int i, int i2) throws ReaderException {
        C0749m c0749m;
        byte[] bArr;
        boolean z;
        if (!isOpened()) {
            throw new IllegalArgumentException("The reader is not opened.");
        }
        if (i < 0 || i >= this.f162g.size()) {
            throw new IllegalArgumentException("The slot number is invalid.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i3 = c0744h.f244a;
        C0743g c0743g = c0744h.f246c;
        C0738b c0738b = c0744h.f247d;
        C0740d c0740d = c0744h.f248e;
        C0739c c0739c = c0744h.f249f;
        synchronized (c0744h) {
            if (c0743g.f237a <= 1) {
                throw new RemovedCardException();
            }
            c0744h.f247d.f190m = 65536;
            c0744h.f247d.f194q.f231a = 0;
            c0744h.f255l.f283n = 0;
            c0744h.f255l.f282m = 0;
            c0744h.f255l.f281l = 0;
            int i4 = 254;
            if (c0744h.f246c.f241e == 0 || c0744h.f246c.f241e >= 254) {
                c0749m = c0744h.f255l;
            } else {
                c0749m = c0744h.f255l;
                i4 = c0744h.f246c.f241e;
            }
            c0749m.f271b = i4;
            Arrays.fill(c0738b.f180c, (byte) 0);
            c0738b.f181d = 0;
            c0738b.f191n = 0;
            bArr = null;
            if (i2 != 0) {
                if (i2 == 1) {
                    synchronized (c0740d) {
                        c0740d.mo58b(i3);
                    }
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException unused) {
                    }
                } else if (i2 != 2) {
                    throw new IllegalArgumentException("The action is invalid.");
                }
                try {
                } catch (ReaderException unused2) {
                    z = true;
                }
                synchronized (c0740d) {
                    bArr = c0740d.mo56a(i3, 0);
                    z = false;
                    if (z) {
                        try {
                            synchronized (c0740d) {
                                bArr = c0740d.mo56a(i3, 0);
                            }
                        } catch (ReaderException e) {
                            if (c0744h.f245b) {
                                c0743g.f237a = 1;
                                throw new RemovedCardException();
                            }
                            throw e;
                        }
                    }
                    if (bArr != null && bArr.length > 0) {
                        if (bArr.length <= c0738b.f180c.length) {
                            throw new InsufficientBufferException();
                        }
                        System.arraycopy(bArr, 0, c0738b.f180c, 0, bArr.length);
                        c0738b.f181d = bArr.length;
                        C0744h.m72a(c0744h.m76a());
                        if (c0743g.f237a == 6 && (c0739c.f203i & 64) == 0) {
                            try {
                                synchronized (c0740d) {
                                    c0740d.mo54a(i3, c0738b);
                                }
                            } catch (ReaderException unused3) {
                            }
                        }
                    }
                }
            } else {
                synchronized (c0740d) {
                    c0740d.mo58b(i3);
                }
                c0743g.f237a = 2;
                if (bArr != null) {
                    if (bArr.length <= c0738b.f180c.length) {
                    }
                }
            }
        }
        return bArr;
    }

    public byte[] getAtr(int i) {
        if (!isOpened()) {
            throw new IllegalArgumentException("The reader is not opened.");
        }
        if (i < 0 || i >= this.f162g.size()) {
            throw new IllegalArgumentException("The slot number is invalid.");
        }
        C0744h c0744h = this.f162g.get(i);
        C0738b c0738b = c0744h.f247d;
        byte[] bArr = null;
        synchronized (c0744h) {
            int i2 = c0738b.f181d;
            if (i2 > 0) {
                bArr = new byte[i2];
                System.arraycopy(c0738b.f180c, 0, bArr, 0, i2);
            }
        }
        return bArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:114:0x008c, code lost:
    
        r14 = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014a A[Catch: all -> 0x018e, TRY_LEAVE, TryCatch #5 {, blocks: (B:9:0x0028, B:11:0x002d, B:13:0x0032, B:15:0x0037, B:18:0x016c, B:19:0x016e, B:22:0x003f, B:23:0x0044, B:24:0x0045, B:26:0x0049, B:28:0x0050, B:29:0x0059, B:31:0x0065, B:33:0x006a, B:36:0x007d, B:37:0x0088, B:61:0x0162, B:63:0x0166, B:64:0x016a, B:39:0x008f, B:41:0x0093, B:42:0x00a0, B:113:0x00a6, B:44:0x00a9, B:46:0x00af, B:47:0x0129, B:66:0x012d, B:70:0x0132, B:73:0x0140, B:74:0x0141, B:49:0x0144, B:51:0x014a, B:54:0x014f, B:58:0x0155, B:75:0x0142, B:76:0x00bb, B:78:0x00d3, B:95:0x0109, B:91:0x0114, B:99:0x0127, B:102:0x0119, B:106:0x011d, B:107:0x011e, B:115:0x009a, B:117:0x0072, B:119:0x0076, B:122:0x0170, B:123:0x0175, B:124:0x0176, B:125:0x017b, B:126:0x0055, B:127:0x017c, B:128:0x0181, B:129:0x0182, B:130:0x0187, B:131:0x0188, B:132:0x018d), top: B:8:0x0028, inners: #0, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0162 A[EDGE_INSN: B:60:0x0162->B:61:0x0162 BREAK  A[LOOP:0: B:37:0x0088->B:57:0x0157], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x012d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0114 A[Catch: ReaderException -> 0x0127, all -> 0x018e, LOOP:1: B:87:0x0103->B:91:0x0114, LOOP_END, TryCatch #1 {ReaderException -> 0x0127, blocks: (B:95:0x0109, B:91:0x0114, B:102:0x0119, B:106:0x011d, B:107:0x011e), top: B:94:0x0109 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0105 A[EDGE_INSN: B:92:0x0105->B:93:0x0105 BREAK  A[LOOP:1: B:87:0x0103->B:91:0x0114], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setProtocol(int i, int i2) throws ReaderException {
        char c;
        int i3;
        byte[] bArr;
        byte[] bArr2;
        int i4;
        if (!isOpened()) {
            throw new IllegalArgumentException("The reader is not opened.");
        }
        if (i < 0 || i >= this.f162g.size()) {
            throw new IllegalArgumentException("The slot number is invalid.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i5 = c0744h.f244a;
        C0743g c0743g = c0744h.f246c;
        C0738b c0738b = c0744h.f247d;
        C0740d c0740d = c0744h.f248e;
        C0739c c0739c = c0744h.f249f;
        synchronized (c0744h) {
            int i6 = 1;
            if (c0743g.f237a <= 1) {
                throw new RemovedCardException();
            }
            char c2 = 3;
            if (c0743g.f237a <= 3) {
                throw new UnpoweredCardException();
            }
            if (c0743g.f237a == 6) {
                if ((i2 & c0738b.f191n) == 0) {
                    throw new ProtocolMismatchException();
                }
            } else if ((i2 & 3) != 0) {
                char c3 = 0;
                if ((i2 & Integer.MIN_VALUE) != 0) {
                    c0738b.f194q.f231a = 0;
                } else {
                    c0738b.f194q.f231a = 1;
                }
                C0744h.m72a(c0744h.m76a());
                if (c0743g.f237a < 5) {
                    throw new InvalidDeviceStateException();
                }
                int i7 = 2;
                if ((i2 & 2) == 0 || (c0738b.f190m & 2) == 0) {
                    if ((i2 & 1) == 0 || (c0738b.f190m & 1) == 0) {
                        throw new ProtocolMismatchException();
                    }
                    c = 1;
                } else {
                    c = 2;
                }
                EnumC0736b enumC0736b = EnumC0736b.PENDING;
                int i8 = 4;
                byte[] bArr3 = new byte[4];
                byte[] bArr4 = new byte[4];
                bArr3[0] = -1;
                while (true) {
                    if (enumC0736b != EnumC0736b.PENDING) {
                        break;
                    }
                    if ((c & 2) != 0) {
                        bArr3[i6] = 17;
                        c0738b.f191n = i7;
                    } else {
                        bArr3[i6] = 16;
                        c0738b.f191n = i6;
                    }
                    if ((c0739c.f203i & 64) != 0) {
                        enumC0736b = EnumC0736b.SUCCESS;
                        break;
                    }
                    if ((c0739c.f203i & 128) != 0) {
                        enumC0736b = EnumC0736b.SUCCESS;
                        bArr = bArr4;
                        bArr2 = bArr3;
                        i3 = 0;
                    } else {
                        bArr3[i7] = (byte) (c0738b.f194q.f233c | (c0738b.f194q.f232b << i8));
                        bArr3[c2] = (byte) ((bArr3[c3] ^ bArr3[i6]) ^ bArr3[i7]);
                        try {
                            synchronized (c0740d) {
                                bArr = bArr4;
                                bArr2 = bArr3;
                                i3 = 0;
                                c0740d.mo55a(i5, bArr2, 0, 4, 0);
                                int mo52a = c0740d.mo52a(i5, bArr, 4, (int[]) null, 0);
                                boolean z = mo52a == 4;
                                if (z) {
                                    boolean z2 = z;
                                    for (int i9 = 0; i9 < mo52a; i9++) {
                                        if (z2) {
                                            try {
                                                if (bArr[i9] == bArr2[i9]) {
                                                    z2 = true;
                                                    if (z2) {
                                                        break;
                                                    }
                                                }
                                            } catch (ReaderException unused) {
                                                enumC0736b = EnumC0736b.FAILURE;
                                                if (enumC0736b == EnumC0736b.SUCCESS) {
                                                }
                                                if (c0738b.f194q.f231a != 0) {
                                                }
                                            }
                                        }
                                        z2 = false;
                                        if (z2) {
                                        }
                                    }
                                    z = z2;
                                }
                                if (z) {
                                    enumC0736b = EnumC0736b.SUCCESS;
                                }
                            }
                        } catch (ReaderException unused2) {
                            bArr = bArr4;
                            bArr2 = bArr3;
                            i3 = 0;
                        }
                    }
                    if (enumC0736b == EnumC0736b.SUCCESS) {
                        try {
                            synchronized (c0740d) {
                                c0740d.mo54a(i5, c0738b);
                                c0738b.f185h = c0738b.f194q.f233c;
                                c0738b.f184g = c0738b.f194q.f232b;
                                break;
                            }
                        } catch (ReaderException unused3) {
                            enumC0736b = EnumC0736b.FAILURE;
                        }
                    }
                    if (c0738b.f194q.f231a != 0) {
                        break;
                    }
                    c0738b.f194q.f231a = i3;
                    try {
                        power(i5, 1);
                        enumC0736b = EnumC0736b.PENDING;
                    } catch (ReaderException unused4) {
                        enumC0736b = EnumC0736b.FAILURE;
                    }
                    bArr4 = bArr;
                    bArr3 = bArr2;
                    i8 = 4;
                    i7 = 2;
                    c3 = 0;
                    i6 = 1;
                    c2 = 3;
                }
                if (enumC0736b == EnumC0736b.SUCCESS) {
                    c0743g.f237a = 6;
                } else {
                    c0738b.f191n = i3;
                }
            } else {
                throw new UnsupportedCardException();
            }
            i4 = c0738b.f191n;
        }
        return i4;
    }

    public int getProtocol(int i) {
        int i2;
        if (!isOpened()) {
            throw new IllegalArgumentException("The reader is not opened.");
        }
        if (i < 0 || i >= this.f162g.size()) {
            throw new IllegalArgumentException("The slot number is invalid.");
        }
        C0744h c0744h = this.f162g.get(i);
        C0738b c0738b = c0744h.f247d;
        synchronized (c0744h) {
            i2 = c0738b.f191n;
        }
        return i2;
    }

    public int getState(int i) {
        int i2;
        if (!isOpened()) {
            throw new IllegalArgumentException("The reader is not opened.");
        }
        if (i < 0 || i >= this.f162g.size()) {
            throw new IllegalArgumentException("The slot number is invalid.");
        }
        C0744h c0744h = this.f162g.get(i);
        C0743g c0743g = c0744h.f246c;
        synchronized (c0744h) {
            i2 = c0743g.f237a;
        }
        return i2;
    }

    public int transmit(int i, byte[] bArr, int i2, byte[] bArr2, int i3) throws ReaderException {
        int i4;
        if (!isOpened()) {
            throw new IllegalArgumentException("The reader is not opened.");
        }
        if (i < 0 || i >= this.f162g.size()) {
            throw new IllegalArgumentException("The slot number is invalid.");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("The send buffer is null.");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("The send buffer length is less than or equal to zero.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The send buffer length is greater than the send buffer size.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("The receive buffer is null.");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("The receive buffer length is less than or equal to zero.");
        }
        if (i3 > bArr2.length) {
            throw new IllegalArgumentException("The receive buffer length is greater than the receive buffer size.");
        }
        C0744h c0744h = this.f162g.get(i);
        C0743g c0743g = c0744h.f246c;
        C0738b c0738b = c0744h.f247d;
        C0739c c0739c = c0744h.f249f;
        synchronized (c0744h) {
            if (c0743g.f237a != 6) {
                throw new InvalidDeviceStateException("The current state is not equal to specific.");
            }
            int i5 = c0738b.f191n;
            if (i5 == 0) {
                throw new InvalidDeviceStateException("The active protocol is not equal to either T=0 or T=1");
            }
            c0744h.f254k.f227b = bArr;
            c0744h.f254k.f228c = i2;
            c0744h.f254k.f229d = bArr2;
            c0744h.f254k.f230e = i3;
            i4 = 0;
            c0744h.f254k.f226a = 0;
            if ((c0739c.f203i & 65536) != 0) {
                if (i5 == 1) {
                    i4 = m28a(c0744h);
                } else if (i5 == 2) {
                    i4 = m34b(c0744h);
                }
            } else if ((c0739c.f203i & 131072) != 0) {
                i4 = m38c(c0744h);
            } else if ((c0739c.f203i & 262144) != 0) {
                i4 = m41d(c0744h);
            } else {
                throw new CommunicationErrorException("The CCID exchange level is not supported.");
            }
        }
        C0744h.m72a(i4);
        return c0744h.f254k.f226a;
    }

    public int control(int i, int i2, byte[] bArr, int i3, byte[] bArr2, int i4) throws ReaderException {
        int i5;
        if (!isOpened()) {
            throw new IllegalArgumentException("The reader is not opened.");
        }
        if (i < 0 || i >= this.f162g.size()) {
            throw new IllegalArgumentException("The slot number is invalid.");
        }
        if (i2 == 2060) {
            int i6 = this.f159d;
            if (i6 == 120557568 || i6 == 120557775) {
                return m36c(i, bArr, i3);
            }
            throw new IllegalArgumentException("The control code is invalid.");
        }
        int i7 = 6;
        if (i2 == 3400) {
            if (bArr2 == null) {
                throw new IllegalArgumentException("The output buffer is null.");
            }
            C0744h c0744h = this.f162g.get(i);
            int i8 = (c0744h.f249f.f206l & 1) != 0 ? 30 : 24;
            if ((c0744h.f249f.f206l & 2) != 0) {
                i8 += 6;
            }
            if (this.f159d == 120553990) {
                i8 += 12;
            }
            if (i4 < i8) {
                throw new IllegalArgumentException("The output buffer length is less than " + i8 + ".");
            }
            if (i4 > bArr2.length) {
                throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
            }
            if ((c0744h.f249f.f206l & 1) != 0) {
                bArr2[0] = 6;
                bArr2[1] = 4;
                bArr2[2] = 0;
                bArr2[3] = 0;
                bArr2[4] = 13;
                bArr2[5] = 78;
            } else {
                i7 = 0;
            }
            if ((c0744h.f249f.f206l & 2) != 0) {
                int i9 = i7 + 1;
                bArr2[i7] = 7;
                int i10 = i9 + 1;
                bArr2[i9] = 4;
                int i11 = i10 + 1;
                bArr2[i10] = 0;
                int i12 = i11 + 1;
                bArr2[i11] = 0;
                int i13 = i12 + 1;
                bArr2[i12] = 13;
                i7 = i13 + 1;
                bArr2[i13] = 79;
            }
            int i14 = i7 + 1;
            bArr2[i7] = 10;
            int i15 = i14 + 1;
            bArr2[i14] = 4;
            int i16 = i15 + 1;
            bArr2[i15] = 0;
            int i17 = i16 + 1;
            bArr2[i16] = 0;
            int i18 = i17 + 1;
            bArr2[i17] = 13;
            int i19 = i18 + 1;
            bArr2[i18] = 82;
            if (this.f159d == 120553990) {
                int i20 = i19 + 1;
                bArr2[i19] = 15;
                int i21 = i20 + 1;
                bArr2[i20] = 4;
                int i22 = i21 + 1;
                bArr2[i21] = 0;
                int i23 = i22 + 1;
                bArr2[i22] = 0;
                int i24 = i23 + 1;
                bArr2[i23] = 13;
                i19 = i24 + 1;
                bArr2[i24] = 87;
            }
            if (this.f159d == 120553990) {
                int i25 = i19 + 1;
                bArr2[i19] = 16;
                int i26 = i25 + 1;
                bArr2[i25] = 4;
                int i27 = i26 + 1;
                bArr2[i26] = 0;
                int i28 = i27 + 1;
                bArr2[i27] = 0;
                int i29 = i28 + 1;
                bArr2[i28] = 13;
                i19 = i29 + 1;
                bArr2[i29] = TarConstants.LF_PAX_EXTENDED_HEADER_UC;
            }
            int i30 = i19 + 1;
            bArr2[i19] = 17;
            int i31 = i30 + 1;
            bArr2[i30] = 4;
            int i32 = i31 + 1;
            bArr2[i31] = 0;
            int i33 = i32 + 1;
            bArr2[i32] = 0;
            int i34 = i33 + 1;
            bArr2[i33] = 13;
            int i35 = i34 + 1;
            bArr2[i34] = ClassDefinitionUtils.OPS_dup;
            int i36 = i35 + 1;
            bArr2[i35] = 18;
            int i37 = i36 + 1;
            bArr2[i36] = 4;
            int i38 = i37 + 1;
            bArr2[i37] = 0;
            int i39 = i38 + 1;
            bArr2[i38] = 0;
            int i40 = i39 + 1;
            bArr2[i39] = 13;
            int i41 = i40 + 1;
            bArr2[i40] = 90;
            int i42 = i41 + 1;
            bArr2[i41] = 19;
            int i43 = i42 + 1;
            bArr2[i42] = 4;
            int i44 = i43 + 1;
            bArr2[i43] = 0;
            int i45 = i44 + 1;
            bArr2[i44] = 0;
            int i46 = i45 + 1;
            bArr2[i45] = 13;
            int i47 = i46 + 1;
            bArr2[i46] = -84;
            return i47;
        }
        if (i2 == 3410) {
            if (bArr2 == null) {
                throw new IllegalArgumentException("The output buffer is null.");
            }
            if (i4 < 4) {
                throw new IllegalArgumentException("The output buffer length is less than 4.");
            }
            if (i4 > bArr2.length) {
                throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
            }
            int i48 = this.f162g.get(i).f249f.f205k;
            bArr2[0] = (byte) i48;
            bArr2[1] = (byte) (i48 >> 8);
            bArr2[2] = 7;
            bArr2[3] = 0;
            return 4;
        }
        if (i2 == 3500) {
            return m26a(i, bArr, i3, bArr2, i4);
        }
        if (i2 == 3406) {
            return m43e(i, bArr, i3, bArr2, i4);
        }
        if (i2 != 3407) {
            switch (i2) {
                case IOCTL_ACR83_GET_FIRMWARE_VERSION /* 2078 */:
                    int i49 = this.f159d;
                    if (i49 == 120557778 || i49 == 120553985 || i49 == 120553990) {
                        return m25a(i, bArr2, i4);
                    }
                    throw new IllegalArgumentException("The control code is invalid.");
                case 2079:
                    int i50 = this.f159d;
                    if (i50 == 120557778 || i50 == 120553985 || i50 == 120553990) {
                        return m37c(i, bArr, i3, bArr2, i4);
                    }
                    return m33b(i, bArr, i3, bArr2, i4);
                case IOCTL_ACR83_READ_KEY /* 2080 */:
                    int i51 = this.f159d;
                    if (i51 == 120557778 || i51 == 120553985 || i51 == 120553990) {
                        return m40d(i, bArr, i3, bArr2, i4);
                    }
                    throw new IllegalArgumentException("The control code is invalid.");
                default:
                    switch (i2) {
                        case 3415:
                            if (this.f159d == 120553990) {
                                return m32b(i, bArr, i3);
                            }
                            throw new IllegalArgumentException("The control code is invalid.");
                        case 3416:
                            if (this.f159d == 120553990) {
                                return m48g(i, bArr, i3, bArr2, i4);
                            }
                            throw new IllegalArgumentException("The control code is invalid.");
                        case 3417:
                            if (bArr2 == null) {
                                throw new IllegalArgumentException("The output buffer is null.");
                            }
                            if (i4 < 4) {
                                throw new IllegalArgumentException("The output buffer length is less than 4.");
                            }
                            if (i4 > bArr2.length) {
                                throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
                            }
                            int i52 = this.f162g.get(i).f249f.f205k;
                            int i53 = i52 & 255;
                            bArr2[0] = (byte) i53;
                            bArr2[1] = (byte) (i53 >> 8);
                            int i54 = (i52 >> 8) & 255;
                            bArr2[2] = (byte) i54;
                            bArr2[3] = (byte) (i54 >> 8);
                            return 4;
                        case 3418:
                            if (bArr2 == null) {
                                throw new IllegalArgumentException("The output buffer is null.");
                            }
                            C0744h c0744h2 = this.f162g.get(i);
                            int i55 = c0744h2.f249f.f205k;
                            int i56 = i55 != 0 ? 32 : 24;
                            int i57 = this.f159d;
                            if (i57 == 120557778 || i57 == 120553985 || i57 == 120553990) {
                                i56 += 9;
                            }
                            if (i4 < i56) {
                                throw new IllegalArgumentException("The output buffer length is less than " + i56 + ".");
                            }
                            if (i4 > bArr2.length) {
                                throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
                            }
                            bArr2[0] = 1;
                            bArr2[1] = 2;
                            bArr2[2] = (byte) i55;
                            int i58 = i55 >> 8;
                            bArr2[3] = (byte) i58;
                            if (i55 != 0) {
                                int i59 = i55 & 255;
                                bArr2[4] = 4;
                                bArr2[5] = 2;
                                bArr2[6] = (byte) i59;
                                bArr2[7] = (byte) (i59 >> 8);
                                int i60 = i58 & 255;
                                bArr2[8] = 5;
                                bArr2[9] = 2;
                                bArr2[10] = (byte) i60;
                                bArr2[11] = (byte) (i60 >> 8);
                                i5 = 12;
                            } else {
                                i5 = 4;
                            }
                            int i61 = i5 + 1;
                            bArr2[i5] = 3;
                            int i62 = i61 + 1;
                            bArr2[i61] = 1;
                            int i63 = i62 + 1;
                            bArr2[i62] = 0;
                            int i64 = this.f159d;
                            if (i64 == 120557778 || i64 == 120553985 || i64 == 120553990) {
                                int i65 = i63 + 1;
                                bArr2[i63] = 6;
                                int i66 = i65 + 1;
                                bArr2[i65] = 1;
                                int i67 = i66 + 1;
                                bArr2[i66] = 1;
                                int i68 = i67 + 1;
                                bArr2[i67] = 7;
                                int i69 = i68 + 1;
                                bArr2[i68] = 1;
                                int i70 = i69 + 1;
                                bArr2[i69] = 16;
                                int i71 = i70 + 1;
                                bArr2[i70] = 2;
                                int i72 = i71 + 1;
                                bArr2[i71] = 1;
                                bArr2[i72] = 7;
                                i63 = i72 + 1;
                            }
                            int i73 = i63 + 1;
                            bArr2[i63] = 9;
                            int i74 = i73 + 1;
                            bArr2[i73] = 1;
                            int i75 = i74 + 1;
                            bArr2[i74] = 1;
                            int vendorId = this.f158c.getVendorId();
                            int i76 = i75 + 1;
                            bArr2[i75] = 11;
                            int i77 = i76 + 1;
                            bArr2[i76] = 2;
                            int i78 = i77 + 1;
                            bArr2[i77] = (byte) vendorId;
                            int i79 = i78 + 1;
                            bArr2[i78] = (byte) (vendorId >> 8);
                            int productId = this.f158c.getProductId();
                            int i80 = i79 + 1;
                            bArr2[i79] = 12;
                            int i81 = i80 + 1;
                            bArr2[i80] = 2;
                            int i82 = i81 + 1;
                            bArr2[i81] = (byte) productId;
                            int i83 = i82 + 1;
                            bArr2[i82] = (byte) (productId >> 8);
                            int i84 = ((c0744h2.f249f.f203i & 65536) == 0 && (c0744h2.f249f.f203i & 262144) == 0) ? 0 : 65536;
                            int i85 = i83 + 1;
                            bArr2[i83] = 10;
                            int i86 = i85 + 1;
                            bArr2[i85] = 4;
                            int i87 = i86 + 1;
                            bArr2[i86] = (byte) i84;
                            int i88 = i87 + 1;
                            bArr2[i87] = (byte) (i84 >> 8);
                            int i89 = i88 + 1;
                            bArr2[i88] = (byte) (i84 >> 16);
                            int i90 = i89 + 1;
                            bArr2[i89] = (byte) (i84 >>> 24);
                            return i90;
                        default:
                            throw new IllegalArgumentException("The control code is invalid.");
                    }
            }
        }
        return m46f(i, bArr, i3, bArr2, i4);
    }

    /* renamed from: a */
    private static int m27a(UsbDeviceConnection usbDeviceConnection, C0739c[] c0739cArr) {
        byte[] bArr = new byte[512];
        int controlTransfer = usbDeviceConnection.controlTransfer(128, 6, 512, 0, bArr, 512, 2000);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i + 1;
            if (i4 >= controlTransfer) {
                return i2;
            }
            int i5 = bArr[i] & 255;
            int i6 = bArr[i4] & 255;
            if (i5 == 9 && i6 == 4) {
                i3++;
            } else if (i5 == 54 && i6 == 33 && i3 <= c0739cArr.length && i + i5 < controlTransfer) {
                byte[] bArr2 = new byte[i5];
                System.arraycopy(bArr, i, bArr2, 0, i5);
                c0739cArr[i3 - 1] = new C0739c(bArr2);
                i2++;
            }
            i += i5;
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m31a(Reader reader, C0744h c0744h) throws ReaderException {
        int mo60c;
        C0740d c0740d = c0744h.f248e;
        synchronized (c0740d) {
            mo60c = c0740d.mo60c(c0744h.f244a);
        }
        C0743g c0743g = c0744h.f246c;
        synchronized (c0744h) {
            if (mo60c == 0 || mo60c == 1) {
                if (c0743g.f237a <= 1) {
                    c0743g.f237a = 2;
                }
            } else if (mo60c != 2) {
                c0743g.f237a = 0;
            } else {
                c0743g.f237a = 1;
            }
        }
    }

    /* renamed from: a */
    private static int m28a(C0744h c0744h) throws ReaderException {
        C0740d c0740d = c0744h.f248e;
        c0744h.f251h = 0;
        int m77b = c0744h.m77b();
        if (m77b != 0) {
            return m77b;
        }
        synchronized (c0740d) {
            c0740d.mo59b(c0744h.f244a, c0744h.f250g, 0, c0744h.f251h, 0);
            c0744h.f253j = c0744h.f252i.length;
            c0744h.f253j = c0740d.mo52a(c0744h.f244a, c0744h.f252i, c0744h.f253j, (int[]) null, 0);
        }
        return c0744h.m78c();
    }

    /* renamed from: b */
    private static int m34b(C0744h c0744h) throws ReaderException {
        int m79d;
        C0740d c0740d = c0744h.f248e;
        do {
            c0744h.f251h = 0;
            c0744h.f255l.f277h = 0;
            m79d = c0744h.m79d();
            if (m79d == 0) {
                synchronized (c0740d) {
                    c0740d.mo59b(c0744h.f244a, c0744h.f250g, 0, c0744h.f251h, 0);
                    c0744h.f253j = c0744h.f252i.length;
                    c0744h.f253j = c0740d.mo52a(c0744h.f244a, c0744h.f252i, c0744h.f253j, (int[]) null, 0);
                }
                m79d = c0744h.m80e();
            }
        } while (m79d == -1073741802);
        return m79d;
    }

    /* renamed from: c */
    private static int m38c(C0744h c0744h) throws ReaderException {
        C0740d c0740d = c0744h.f248e;
        if (c0744h.f254k.f228c > c0744h.f249f.f204j - 10) {
            return -2147483643;
        }
        synchronized (c0740d) {
            c0740d.mo59b(c0744h.f244a, c0744h.f254k.f227b, 0, c0744h.f254k.f228c, 0);
            c0744h.f254k.f226a = c0740d.mo52a(c0744h.f244a, c0744h.f254k.f229d, c0744h.f254k.f230e, (int[]) null, 0);
        }
        return 0;
    }

    /* renamed from: d */
    private static int m41d(C0744h c0744h) throws ReaderException {
        int i;
        int i2;
        int i3;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr = c0744h.f254k.f227b;
        int i4 = c0744h.f254k.f228c;
        byte[] bArr2 = c0744h.f254k.f229d;
        int i5 = c0744h.f254k.f230e;
        int i6 = c0744h.f249f.f204j - 10;
        int[] iArr = new int[1];
        if (i4 > i6) {
            i = i6;
            i2 = 1;
        } else {
            i = i4;
            i2 = 0;
        }
        synchronized (c0740d) {
            int i7 = i4;
            int i8 = i2;
            int i9 = i;
            int i10 = 0;
            while (i7 > 0) {
                int i11 = i8;
                c0740d.mo59b(c0744h.f244a, bArr, i10, i9, i11);
                int i12 = i7 - i9;
                i10 += i9;
                if (i11 == 0 || i11 == 2) {
                    break;
                }
                c0740d.mo52a(c0744h.f244a, (byte[]) null, 0, (int[]) null, 0);
                if (i12 > i6) {
                    i9 = i6;
                    i7 = i12;
                    i8 = 3;
                } else {
                    i7 = i12;
                    i9 = i7;
                    i8 = 2;
                }
            }
            boolean z = false;
            i3 = 0;
            while (!z) {
                int m64a = c0740d.m64a(c0744h.f244a, bArr2, i3, i5, iArr, 0);
                i5 -= m64a;
                i3 += m64a;
                int i13 = iArr[0];
                if (i13 != 0) {
                    if (i13 != 1) {
                        if (i13 != 2) {
                            if (i13 != 3 && i13 != 16) {
                                z = true;
                            }
                        }
                    }
                    c0740d.mo59b(c0744h.f244a, null, 0, 0, 16);
                }
                z = true;
            }
        }
        c0744h.f254k.f226a = i3;
        return 0;
    }

    /* renamed from: e */
    private static int m44e(C0744h c0744h) {
        int mo51a;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr = new byte[5];
        bArr[0] = 4;
        byte[] bArr2 = new byte[9];
        try {
            synchronized (c0740d) {
                mo51a = c0740d.mo51a(c0744h.f244a, bArr, 5, bArr2, 9, 2000);
            }
            if (mo51a < 7 || bArr2[0] != -124) {
                return 0;
            }
            return ((bArr2[5] & 255) << 8) | (bArr2[6] & 255);
        } catch (ReaderException unused) {
            return 0;
        }
    }

    /* renamed from: f */
    private static int m47f(C0744h c0744h) {
        int i;
        String str;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr = {-32, 0, 0, 24};
        byte[] bArr2 = new byte[35];
        try {
            synchronized (c0740d) {
                i = c0740d.mo51a(c0744h.f244a, bArr, 5, bArr2, 35, 2000);
            }
        } catch (ReaderException unused) {
            i = 0;
        }
        if (i > 5) {
            try {
                str = new String(bArr2, 5, i - 5, "US-ASCII");
            } catch (UnsupportedEncodingException unused2) {
                str = "";
            }
        } else {
            str = "";
        }
        new StringBuilder("Firmware Version: ").append(str);
        String[] strArr = {""};
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (i2 <= 0) {
                if (charAt == '.') {
                    i2++;
                } else if (charAt >= '0' && charAt <= '9') {
                    strArr[i2] = String.valueOf(strArr[i2]) + charAt;
                } else {
                    strArr[0] = "";
                    i2 = 0;
                }
            }
        }
        int parseInt = Integer.parseInt(strArr[0]);
        new StringBuilder("Major: ").append(parseInt);
        return parseInt;
    }

    /* renamed from: a */
    private int m26a(int i, byte[] bArr, int i2, byte[] bArr2, int i3) throws ReaderException {
        int mo51a;
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("The input buffer length is less than or equal to zero.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("The output buffer is null.");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("The output buffer length is less than or equal to zero.");
        }
        if (i3 > bArr2.length) {
            throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i4 = c0744h.f244a;
        C0740d c0740d = c0744h.f248e;
        synchronized (c0740d) {
            mo51a = c0740d.mo51a(i4, bArr, i2, bArr2, i3, 0);
        }
        return mo51a;
    }

    /* renamed from: b */
    private int m33b(int i, byte[] bArr, int i2, byte[] bArr2, int i3) throws ReaderException {
        int mo51a;
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("The input buffer length is less than or equal to zero.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("The output buffer is null.");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("The output buffer length is less than or equal to zero.");
        }
        if (i3 > bArr2.length) {
            throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i4 = c0744h.f244a;
        C0740d c0740d = c0744h.f248e;
        int i5 = i2 + 3;
        byte[] bArr3 = new byte[i5];
        bArr3[0] = -32;
        bArr3[1] = 0;
        bArr3[2] = 0;
        System.arraycopy(bArr, 0, bArr3, 3, i2);
        synchronized (c0740d) {
            mo51a = c0740d.mo51a(i4, bArr3, i5, bArr2, i3, 0);
        }
        return mo51a;
    }

    /* renamed from: a */
    private int m25a(int i, byte[] bArr, int i2) throws ReaderException {
        int mo51a;
        if (bArr == null) {
            throw new IllegalArgumentException("The output buffer is null.");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("The output buffer length is less than or equal to zero.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i3 = c0744h.f244a;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr2 = new byte[5];
        bArr2[0] = 4;
        byte[] bArr3 = new byte[9];
        synchronized (c0740d) {
            mo51a = c0740d.mo51a(i3, bArr2, 5, bArr3, 9, 2000);
        }
        if (mo51a <= 3 || bArr3[0] != -124) {
            throw new CommunicationErrorException("The response is invalid.");
        }
        int i4 = mo51a - 3;
        if (i2 < i4) {
            throw new InsufficientBufferException("Required output buffer length: " + i4);
        }
        System.arraycopy(bArr3, 3, bArr, 0, i4);
        return i4;
    }

    /* renamed from: c */
    private int m37c(int i, byte[] bArr, int i2, byte[] bArr2, int i3) throws ReaderException {
        int mo51a;
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 <= 0 || i2 > 32) {
            throw new IllegalArgumentException("The input buffer length is less than or equal to zero or greater than 32.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("The output buffer is null.");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("The output buffer length is less than or equal to zero.");
        }
        if (i3 > bArr2.length) {
            throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i4 = c0744h.f244a;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr3 = new byte[37];
        byte[] bArr4 = new byte[5];
        bArr3[0] = 5;
        bArr3[1] = 0;
        bArr3[2] = 32;
        bArr3[3] = 0;
        bArr3[4] = 0;
        Arrays.fill(bArr3, 5, 37, (byte) 32);
        System.arraycopy(bArr, 0, bArr3, 5, i2);
        synchronized (c0740d) {
            mo51a = c0740d.mo51a(i4, bArr3, 37, bArr4, 5, 2000);
        }
        if (mo51a <= 3 || bArr4[0] != -123) {
            throw new CommunicationErrorException("The response is invalid.");
        }
        int i5 = mo51a - 3;
        if (i3 < i5) {
            throw new InsufficientBufferException("Required output buffer length: " + i5);
        }
        System.arraycopy(bArr4, 3, bArr2, 0, i5);
        return i5;
    }

    /* renamed from: d */
    private int m40d(int i, byte[] bArr, int i2, byte[] bArr2, int i3) throws ReaderException {
        int mo51a;
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 != 6) {
            throw new IllegalArgumentException("The input buffer length is not equal to 6.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("The output buffer is null.");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("The output buffer length is less than or equal to zero.");
        }
        if (i3 > bArr2.length) {
            throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i4 = c0744h.f244a;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr3 = new byte[38];
        byte[] bArr4 = {6, 0, 6, 0, 0};
        System.arraycopy(bArr, 0, bArr4, 5, i2);
        synchronized (c0740d) {
            mo51a = c0740d.mo51a(i4, bArr4, 11, bArr3, 38, 0);
        }
        if (mo51a <= 3 || bArr3[0] != -122) {
            throw new CommunicationErrorException("The response is invalid.");
        }
        int i5 = mo51a - 3;
        if (i3 < i5) {
            throw new InsufficientBufferException("Required output buffer length: " + i5);
        }
        System.arraycopy(bArr3, 3, bArr2, 0, i5);
        return i5;
    }

    /* renamed from: e */
    private int m43e(int i, byte[] bArr, int i2, byte[] bArr2, int i3) throws ReaderException {
        int mo57b;
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 < 23) {
            throw new IllegalArgumentException("The input buffer length is less than 23.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("The output buffer is null.");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("The output buffer length is less than or equal to zero.");
        }
        if (i3 > bArr2.length) {
            throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
        }
        if (((bArr[15] & 255) | ((bArr[16] & 255) << 8) | ((bArr[17] & 255) << 16) | ((bArr[18] & 255) << 24)) + 19 != i2) {
            throw new IllegalArgumentException("The ulDataLength is invalid.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i4 = c0744h.f244a;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr3 = new byte[i2 + 1];
        bArr3[0] = 0;
        int i5 = 1;
        for (int i6 = 0; i6 < i2; i6++) {
            if (i6 != 1 && (i6 < 15 || i6 > 18)) {
                bArr3[i5] = bArr[i6];
                i5++;
            }
        }
        synchronized (c0740d) {
            mo57b = c0740d.mo57b(i4, bArr3, i5, bArr2, i3, 0);
        }
        return mo57b;
    }

    /* renamed from: f */
    private int m46f(int i, byte[] bArr, int i2, byte[] bArr2, int i3) throws ReaderException {
        int mo57b;
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 < 28) {
            throw new IllegalArgumentException("The input buffer length is less than 28.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("The output buffer is null.");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("The output buffer length is less than or equal to zero.");
        }
        if (i3 > bArr2.length) {
            throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
        }
        if (((bArr[20] & 255) | ((bArr[21] & 255) << 8) | ((bArr[22] & 255) << 16) | ((bArr[23] & 255) << 24)) + 24 != i2) {
            throw new IllegalArgumentException("The ulDataLength is invalid.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i4 = c0744h.f244a;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr3 = new byte[i2 + 1];
        bArr3[0] = 1;
        int i5 = 1;
        for (int i6 = 0; i6 < i2; i6++) {
            if (i6 != 1 && ((i6 != 15 || bArr[11] != 0) && ((i6 != 16 || bArr[11] >= 3) && (i6 < 20 || i6 > 23)))) {
                bArr3[i5] = bArr[i6];
                i5++;
            }
        }
        synchronized (c0740d) {
            mo57b = c0740d.mo57b(i4, bArr3, i5, bArr2, i3, 0);
        }
        return mo57b;
    }

    /* renamed from: b */
    private int m32b(int i, byte[] bArr, int i2) throws ReaderException {
        int mo51a;
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 < 8) {
            throw new IllegalArgumentException("The input buffer length is less than 8.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i3 = c0744h.f249f.f205k;
        int i4 = i3 & 255;
        int i5 = (i3 >> 8) & 255;
        int i6 = ((bArr[1] & 255) << 8) | (bArr[0] & 255);
        int i7 = bArr[2] & 255;
        int i8 = bArr[3] & 255;
        int i9 = bArr[6] & 255;
        int i10 = i6 / 1000;
        if (i10 > 255) {
            throw new IllegalArgumentException("The display time is greater than 255000.");
        }
        if (i7 >= i4) {
            throw new IllegalArgumentException("The column is greater than or equal to the maximum number of characters.");
        }
        if (i8 >= i5) {
            throw new IllegalArgumentException("The row is greater than or equal to the maximum number of lines.");
        }
        if (i9 != i2 - 7) {
            throw new IllegalArgumentException("The message length is invalid.");
        }
        try {
            byte[] bytes = new String(bArr, 7, i9, "UTF-8").getBytes("ISO-8859-2");
            int length = bytes.length + 2;
            int i11 = length + 5;
            byte[] bArr2 = new byte[i11];
            bArr2[0] = 7;
            bArr2[1] = (byte) (length >> 8);
            bArr2[2] = (byte) length;
            bArr2[3] = 0;
            bArr2[4] = 0;
            bArr2[5] = (byte) i10;
            bArr2[6] = (byte) ((i8 * i4) + i7);
            System.arraycopy(bytes, 0, bArr2, 7, bytes.length);
            int i12 = c0744h.f244a;
            C0740d c0740d = c0744h.f248e;
            byte[] bArr3 = new byte[5];
            synchronized (c0740d) {
                mo51a = c0740d.mo51a(i12, bArr2, i11, bArr3, 5, 10000);
            }
            if (mo51a > 4 && bArr3[0] == -121 && bArr3[3] == 0 && bArr3[4] == 0) {
                return 0;
            }
            throw new CommunicationErrorException("The response is invalid.");
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalArgumentException("The message contains invalid characters.");
        }
    }

    /* renamed from: g */
    private int m48g(int i, byte[] bArr, int i2, byte[] bArr2, int i3) throws ReaderException {
        int mo51a;
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 < 5) {
            throw new IllegalArgumentException("The input buffer length is less than 5.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("The output buffer is null.");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("The output buffer length is less than or equal to zero.");
        }
        if (i3 > bArr2.length) {
            throw new IllegalArgumentException("The output buffer length is greater than the output buffer size.");
        }
        C0744h c0744h = this.f162g.get(i);
        int i4 = c0744h.f249f.f205k;
        int i5 = i4 & 255;
        int i6 = (i4 >> 8) & 255;
        int i7 = (bArr[0] & 255) | ((bArr[1] & 255) << 8);
        int i8 = bArr[2] & 255;
        int i9 = bArr[3] & 255;
        int i10 = bArr[4] & 255;
        if (i7 == 0 || i7 > 255) {
            throw new IllegalArgumentException("The waiting time is equal to zero or greater than 255.");
        }
        if (i8 > 2) {
            throw new IllegalArgumentException("The display mode is greater than 2.");
        }
        if (i9 >= i5) {
            throw new IllegalArgumentException("The column is greater than or equal to the maximum number of characters.");
        }
        if (i10 >= i6) {
            throw new IllegalArgumentException("The row is greater than or equal to the maximum number of lines.");
        }
        byte[] bArr3 = {10, 0, 6, 0, 0, (byte) i7, 1, 1, 5, (byte) ((i10 * i5) + i9), (byte) i8};
        int i11 = c0744h.f244a;
        C0740d c0740d = c0744h.f248e;
        byte[] bArr4 = new byte[7];
        synchronized (c0740d) {
            mo51a = c0740d.mo51a(i11, bArr3, 11, bArr4, 7, (i7 + 10) * 1000);
        }
        if (mo51a <= 5 || bArr4[0] != -118 || bArr4[3] != 0 || bArr4[4] != 0) {
            throw new CommunicationErrorException("The response is invalid.");
        }
        if (mo51a <= 6) {
            return 0;
        }
        if (i3 <= 0) {
            throw new InsufficientBufferException(new StringBuilder("Required output buffer length: 1").toString());
        }
        bArr2[0] = bArr4[6];
        return 1;
    }

    /* renamed from: c */
    private int m36c(int i, byte[] bArr, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("The input buffer is null.");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("The input buffer length is less than 1.");
        }
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("The input buffer length is greater than the input buffer size.");
        }
        C0737a c0737a = (C0737a) this.f162g.get(i).f248e;
        int i3 = bArr[0] & 255;
        if (i3 != 12 && i3 != 13) {
            switch (i3) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    break;
                default:
                    throw new IllegalArgumentException("The card type is invalid.");
            }
        }
        synchronized (c0737a) {
            c0737a.m53a(i3);
        }
        return 0;
    }

    /* renamed from: a */
    static /* synthetic */ void m30a(Reader reader, final int i, final int i2, final int i3) {
        if ((i2 <= 1 || i3 > 1) && (i2 > 1 || i3 <= 1)) {
            return;
        }
        StringBuilder sb = new StringBuilder("run: slot ");
        sb.append(i);
        sb.append(": ");
        sb.append(i3 <= 1 ? "removed" : "inserted");
        if (reader.f166k != null) {
            new Thread(new Runnable() { // from class: com.acs.smartcard.Reader.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (Reader.this.f166k != null) {
                        Reader.this.f166k.onStateChange(i, i2, i3);
                    }
                }
            }).start();
        }
    }
}
