package com.iflytek.aiui.data.audio.player;

import android.content.Context;
import android.media.AudioTrack;
import android.os.MemoryFile;
import com.iflytek.aiui.pro.C3586ap;
import com.iflytek.aiui.pro.C3589as;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class PcmBuffer {
    public static final int DEF_PROC_SCALE = 100;
    private ArrayList<C3533a> mAudioInfo;
    private Context mContext;
    private int mMaxFileSize;
    private volatile long mPercent;
    private int mProcScale;
    private int mRate;
    private volatile long mTotalSize;
    private final int DEF_BYTE = 2;
    private final int DEF_CHANNEL = 1;
    private final int DEF_RATE = 16000;
    private final int DEF_MIN_BUF_SEC = 60;
    private final int BLANK_BLOCK_MS = 500;
    private final int DEF_MIN_BUF_SIZE = 1920000;
    private MemoryFile memFile = null;
    private volatile int mReadOffset = 0;
    private C3533a mTempAudio = null;
    private String mFilepath = "";
    private byte[] mAudioBuf = null;
    private int mBufOffset = 0;
    private int mBufLen = 0;
    private final float MAX_PLAYABLE_PERCANT = 0.95f;
    private boolean mEndWithNull = true;
    private Object mSyncObj = new Object();
    private Object mFileSyncObj = new Object();

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.data.audio.player.PcmBuffer$a */
    /* loaded from: classes.dex */
    public class C3533a {

        /* renamed from: a */
        long f2142a;

        /* renamed from: b */
        long f2143b;

        /* renamed from: c */
        int f2144c;

        /* renamed from: d */
        int f2145d;

        public C3533a(long j, long j2, int i, int i2) {
            this.f2142a = j;
            this.f2143b = j2;
            this.f2144c = i;
            this.f2145d = i2;
        }
    }

    public PcmBuffer(Context context, int i, int i2, String str, int i3) {
        this.mMaxFileSize = 1920000;
        this.mAudioInfo = null;
        this.mContext = null;
        this.mRate = 16000;
        this.mPercent = 0L;
        this.mTotalSize = 0L;
        this.mProcScale = 100;
        this.mContext = context;
        this.mPercent = 0L;
        this.mAudioInfo = new ArrayList<>();
        this.mTotalSize = 0L;
        this.mRate = i;
        this.mProcScale = i3;
        this.mMaxFileSize = (i * 2 * 1 * i2) + 1920000;
        C3589as.m1058a("min audio seconds: " + i2 + ", max audio buf size: " + this.mMaxFileSize);
    }

    private String genFileName() {
        return C3586ap.m1053a(this.mContext) + System.currentTimeMillis() + "tts.pcm";
    }

    private void readAudio(int i) throws IOException {
        if (this.mAudioBuf == null) {
            this.mAudioBuf = new byte[i * 10];
        }
        synchronized (this.mSyncObj) {
            int length = this.mAudioBuf.length;
            int i2 = (int) (this.mTotalSize - this.mReadOffset);
            if (i2 < length) {
                length = i2;
            }
            synchronized (this.mFileSyncObj) {
                this.memFile.readBytes(this.mAudioBuf, this.mReadOffset, 0, length);
            }
            this.mReadOffset += length;
            this.mBufOffset = 0;
            this.mBufLen = length;
            C3589as.m1058a("readAudio leave, dataSize=" + length + ", bufLen=" + i2);
        }
    }

    private void writeToFile(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        synchronized (this.mFileSyncObj) {
            if (this.memFile == null) {
                this.mFilepath = genFileName();
                MemoryFile memoryFile = new MemoryFile(this.mFilepath, this.mMaxFileSize);
                this.memFile = memoryFile;
                memoryFile.allowPurging(false);
            } else if (this.mTotalSize + bArr.length > this.mMaxFileSize) {
                this.mMaxFileSize *= 2;
                String genFileName = genFileName();
                MemoryFile memoryFile2 = new MemoryFile(genFileName, this.mMaxFileSize);
                memoryFile2.allowPurging(false);
                int i = (int) this.mTotalSize;
                byte[] bArr2 = new byte[i];
                this.memFile.readBytes(bArr2, 0, 0, i);
                this.memFile.close();
                memoryFile2.writeBytes(bArr2, 0, 0, i);
                this.memFile = memoryFile2;
                this.mFilepath = genFileName;
            }
            this.memFile.writeBytes(bArr, 0, (int) this.mTotalSize, bArr.length);
            this.mTotalSize += bArr.length;
        }
    }

    public void beginRead() throws IOException {
        synchronized (this.mSyncObj) {
            this.mReadOffset = 0;
            this.mTempAudio = null;
            synchronized (this.mAudioInfo) {
                if (this.mAudioInfo.size() > 0) {
                    this.mTempAudio = this.mAudioInfo.get(0);
                }
            }
        }
    }

    public void deleteFile() {
        synchronized (this.mFileSyncObj) {
            C3589as.m1058a("deleteFile");
            try {
                if (this.memFile != null) {
                    this.memFile.close();
                    this.memFile = null;
                }
            } catch (Exception e) {
                C3589as.m1061a(e);
            }
        }
    }

    protected void finalize() throws Throwable {
        deleteFile();
        super.finalize();
    }

    public boolean getEndWithNull() {
        return this.mEndWithNull;
    }

    public int getMemFileLenth() {
        synchronized (this.mFileSyncObj) {
            if (this.memFile == null) {
                return 0;
            }
            return this.memFile.length();
        }
    }

    public C3533a getPalyAudioInfo() {
        if (this.mTempAudio == null) {
            return null;
        }
        long j = this.mReadOffset - (this.mBufLen - this.mBufOffset);
        if (j >= this.mTempAudio.f2142a && j <= this.mTempAudio.f2143b) {
            return this.mTempAudio;
        }
        synchronized (this.mAudioInfo) {
            Iterator<C3533a> it = this.mAudioInfo.iterator();
            while (it.hasNext()) {
                C3533a next = it.next();
                this.mTempAudio = next;
                if (j >= next.f2142a && j <= this.mTempAudio.f2143b) {
                    return this.mTempAudio;
                }
            }
            return null;
        }
    }

    public int getPlayPercent() {
        synchronized (this.mSyncObj) {
            if (this.mTotalSize <= 0) {
                return 0;
            }
            return (int) (((this.mReadOffset - (this.mBufLen - this.mBufOffset)) * this.mPercent) / this.mTotalSize);
        }
    }

    public int getRate() {
        return this.mRate;
    }

    public boolean hasMoreBuffer(int i) {
        boolean z;
        synchronized (this.mSyncObj) {
            z = ((long) i) <= ((this.mTotalSize - ((long) this.mReadOffset)) + ((long) this.mBufLen)) - ((long) this.mBufOffset);
        }
        return z;
    }

    public boolean isBufferingFinished() {
        return ((long) this.mProcScale) == this.mPercent;
    }

    public boolean isOver() {
        boolean z;
        synchronized (this.mSyncObj) {
            z = ((long) this.mProcScale) == this.mPercent && ((long) this.mReadOffset) >= this.mTotalSize && this.mBufOffset >= this.mBufLen;
        }
        return z;
    }

    public boolean playAble() {
        boolean z;
        synchronized (this.mSyncObj) {
            z = ((long) this.mReadOffset) < this.mTotalSize || this.mBufOffset < this.mBufLen;
        }
        return z;
    }

    public boolean readyToPlay(int i) {
        if (((float) this.mPercent) > this.mProcScale * 0.95f) {
            return true;
        }
        return this.mTotalSize / 32 >= ((long) i) && 0 < this.mTotalSize;
    }

    public void setEndWithNull(boolean z) {
        this.mEndWithNull = z;
    }

    public void setPercent(int i) {
        if (i < 0 || i > this.mProcScale) {
            return;
        }
        this.mPercent = i;
    }

    public void writeBuffer(ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue) throws IOException {
        if (concurrentLinkedQueue == null) {
            return;
        }
        Iterator<byte[]> it = concurrentLinkedQueue.iterator();
        while (it.hasNext()) {
            writeToFile(it.next());
        }
    }

    public void writeStream(ArrayList<byte[]> arrayList, int i, int i2, int i3) throws IOException {
        C3589as.m1058a("buffer percent = " + i + ", beg=" + i2 + ", end=" + i3);
        synchronized (this.mSyncObj) {
            C3533a c3533a = new C3533a(this.mTotalSize, this.mTotalSize, i2, i3);
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                writeToFile(arrayList.get(i4));
            }
            c3533a.f2143b = this.mTotalSize;
            this.mPercent = i;
            synchronized (this.mAudioInfo) {
                this.mAudioInfo.add(c3533a);
            }
        }
        C3589as.m1058a("allSize = " + this.mTotalSize + " maxSize=" + this.mMaxFileSize);
    }

    public void writeTrack(AudioTrack audioTrack, int i) throws IOException {
        System.currentTimeMillis();
        if (this.mBufOffset >= this.mBufLen) {
            readAudio(i);
        }
        int i2 = i * 2;
        int i3 = this.mBufLen;
        int i4 = this.mBufOffset;
        int i5 = i2 > i3 - i4 ? i3 - i4 : i;
        audioTrack.write(this.mAudioBuf, this.mBufOffset, i5);
        this.mBufOffset = i5 + this.mBufOffset;
        if (isOver() && getEndWithNull()) {
            writeTrackBlankBlock(audioTrack, i);
        }
    }

    public void writeTrackBlankBlock(AudioTrack audioTrack, int i) {
        int i2 = (((this.mRate * 500) * 2) * 1) / 1000;
        C3589as.m1058a("mBuffer.writeTrack writeTrackBlankBlock size: " + i2);
        audioTrack.write(new byte[i2], 0, i2);
    }
}
