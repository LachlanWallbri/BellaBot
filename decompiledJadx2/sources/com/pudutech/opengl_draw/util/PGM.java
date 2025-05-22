package com.pudutech.opengl_draw.util;

import androidx.core.view.ViewCompat;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes5.dex */
public class PGM {
    char ch0;
    char ch1;
    int height;

    /* renamed from: in */
    DataInputStream f6833in;
    int maxpix;
    int width;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x006d, code lost:
    
        if (r8 != '\r') goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0075, code lost:
    
        r8 = (char) r7.f6833in.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x007d, code lost:
    
        if (r8 < '0') goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x007f, code lost:
    
        if (r8 <= '9') goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0089, code lost:
    
        r6 = r8;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008b, code lost:
    
        r8 = ((r8 * 10) + r6) - 48;
        r6 = (char) r7.f6833in.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0095, code lost:
    
        if (r6 < '0') goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0097, code lost:
    
        if (r6 <= '9') goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0099, code lost:
    
        r7.width = r8;
        r8 = (char) r7.f6833in.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a2, code lost:
    
        if (r8 < '0') goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a4, code lost:
    
        if (r8 <= '9') goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ae, code lost:
    
        r6 = r8;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b0, code lost:
    
        r8 = ((r8 * 10) + r6) - 48;
        r6 = (char) r7.f6833in.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ba, code lost:
    
        if (r6 < '0') goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00bc, code lost:
    
        if (r6 <= '9') goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00be, code lost:
    
        r7.height = r8;
        r8 = (char) r7.f6833in.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c7, code lost:
    
        if (r8 < '0') goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c9, code lost:
    
        if (r8 <= '9') goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d3, code lost:
    
        r1 = ((r1 * 10) + r8) - 48;
        r8 = (char) r7.f6833in.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00dd, code lost:
    
        if (r8 < '0') goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00df, code lost:
    
        if (r8 <= '9') goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e1, code lost:
    
        r7.maxpix = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cb, code lost:
    
        java.lang.System.out.print("Errow!");
        java.lang.System.exit(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a6, code lost:
    
        java.lang.System.out.print("Errow!");
        java.lang.System.exit(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0081, code lost:
    
        java.lang.System.out.print("Errow!");
        java.lang.System.exit(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0060, code lost:
    
        if (r8 == '#') goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0062, code lost:
    
        r8 = (char) r7.f6833in.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0069, code lost:
    
        if (r8 == '\n') goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void readPGMHeader(String str) {
        try {
            this.f6833in = new DataInputStream(new FileInputStream(str));
            this.ch0 = (char) this.f6833in.readByte();
            this.ch1 = (char) this.f6833in.readByte();
            int i = 0;
            if (this.ch0 != 'P' || this.ch1 != '5') {
                System.out.print("Not a pgm image! [0]=" + this.ch0 + ", [1]=" + this.ch1);
                System.exit(0);
            }
            this.f6833in.readByte();
            char readByte = (char) this.f6833in.readByte();
        } catch (IOException unused) {
            System.out.println("Exception!");
        }
    }

    public void readPPMHeader(String str) {
        try {
            this.f6833in = new DataInputStream(new FileInputStream(str));
            this.ch0 = (char) this.f6833in.readByte();
            this.ch1 = (char) this.f6833in.readByte();
            int i = 0;
            if (this.ch0 != 'P' || this.ch1 != '6') {
                System.out.print("Not a pgm image! [0]=" + this.ch0 + ", [1]=" + this.ch1);
                System.exit(0);
            }
            this.f6833in.readByte();
            char readByte = (char) this.f6833in.readByte();
            if (readByte < '0' || readByte > '9') {
                System.out.print("Errow!");
                System.exit(1);
            }
            char c = readByte;
            int i2 = 0;
            do {
                i2 = ((i2 * 10) + c) - 48;
                c = (char) this.f6833in.readByte();
                if (c < '0') {
                    break;
                }
            } while (c <= '9');
            this.width = i2;
            char readByte2 = (char) this.f6833in.readByte();
            if (readByte2 < '0' || readByte2 > '9') {
                System.out.print("Errow!");
                System.exit(1);
            }
            char c2 = readByte2;
            int i3 = 0;
            do {
                i3 = ((i3 * 10) + c2) - 48;
                c2 = (char) this.f6833in.readByte();
                if (c2 < '0') {
                    break;
                }
            } while (c2 <= '9');
            this.height = i3;
            char readByte3 = (char) this.f6833in.readByte();
            if (readByte3 < '0' || readByte3 > '9') {
                System.out.print("Errow!");
                System.exit(1);
            }
            do {
                i = ((i * 10) + readByte3) - 48;
                readByte3 = (char) this.f6833in.readByte();
                if (readByte3 < '0') {
                    break;
                }
            } while (readByte3 <= '9');
            this.maxpix = i;
        } catch (IOException unused) {
            System.out.println("Exception!");
        }
    }

    public int[] readData(int i, int i2, int i3) {
        int i4 = i * i2;
        int[] iArr = new int[i4];
        int i5 = 0;
        try {
            if (i3 == 5) {
                while (i5 < i4) {
                    int readByte = this.f6833in.readByte();
                    if (readByte < 0) {
                        readByte += 256;
                    }
                    iArr[i5] = readByte | (readByte << 16) | ViewCompat.MEASURED_STATE_MASK | (readByte << 8);
                    i5++;
                }
            } else if (i3 == 6) {
                while (i5 < i4) {
                    int readByte2 = this.f6833in.readByte();
                    if (readByte2 < 0) {
                        readByte2 += 256;
                    }
                    int readByte3 = this.f6833in.readByte();
                    if (readByte3 < 0) {
                        readByte3 += 256;
                    }
                    int readByte4 = this.f6833in.readByte();
                    if (readByte4 < 0) {
                        readByte4 += 256;
                    }
                    iArr[i5] = (readByte2 << 16) | ViewCompat.MEASURED_STATE_MASK | (readByte3 << 8) | readByte4;
                    i5++;
                }
            }
        } catch (IOException unused) {
            System.out.println("Exception!");
        }
        return iArr;
    }

    public char getCh0() {
        return this.ch0;
    }

    public char getCh1() {
        return this.ch1;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMaxpix() {
        return this.maxpix;
    }
}
