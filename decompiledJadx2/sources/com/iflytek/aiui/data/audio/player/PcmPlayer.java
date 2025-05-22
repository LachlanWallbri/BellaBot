package com.iflytek.aiui.data.audio.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.aiui.data.audio.player.PcmBuffer;
import com.iflytek.aiui.error.AIUIError;
import com.iflytek.aiui.pro.C3587aq;
import com.iflytek.aiui.pro.C3589as;
import com.iflytek.aiui.pro.C3645v0;
import com.iflytek.aiui.pro.C3651y0;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class PcmPlayer {
    public static final int BUFFERING = 1;
    public static final int INIT = 0;
    private static final int MIN_SLEEP = 5;
    private static final int MSG_ERROR = 0;
    private static final int MSG_PAUSE = 1;
    private static final int MSG_PERCENT = 3;
    private static final int MSG_RESUME = 2;
    private static final int MSG_STOPED = 4;
    public static final int PAUSED = 3;
    public static final int PLAYING = 2;
    public static final int STOPED = 4;
    private static final String TAG = "PcmPlayer";
    private final int BYTES_OF_PER_SAMPLE;
    private final int FADE_PERIOD;
    private final int FADE_TIME;
    private final int FADING_SIZE;
    private final float MAX_VOL;
    private final float MIN_VOL;
    private final float PER;
    AudioManager.OnAudioFocusChangeListener afChangeListener;
    private boolean mAudioFocus;
    private Object mAudioLock;
    private AudioTrack mAudioTrack;
    private PcmBuffer mBuffer;
    private int mBufferSize;
    private boolean mBufferingFadingEnable;
    private boolean mChangeListenerFlag;
    private Context mContext;
    private int mCurEndPos;
    private float mCurFadingPeriod;
    private float mCurVol;
    private boolean mFading;
    private boolean mIsFadeOut;
    private PcmPlayerListener mListener;
    private int mPerPlaySize;
    private volatile int mPlaytate;
    private boolean mRequestFocus;
    private int mStreamType;
    private Object mSyncObj;
    private float mTargetVol;
    private C3536a mThread;
    private Handler mUihandler;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public interface PcmPlayerListener {
        void onError(AIUIError aIUIError);

        void onPaused();

        void onPercent(int i, int i2, int i3);

        void onResume();

        void onStoped(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.data.audio.player.PcmPlayer$a */
    /* loaded from: classes.dex */
    public class C3536a extends Thread {

        /* renamed from: b */
        private int f2150b;

        private C3536a() {
            this.f2150b = PcmPlayer.this.mStreamType;
        }

        /* renamed from: a */
        public int m798a() {
            return this.f2150b;
        }

        /* renamed from: a */
        public void m799a(int i) {
            this.f2150b = i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:150:0x033c, code lost:
        
            if (r9.f2149a.mAudioFocus == false) goto L83;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x0288, code lost:
        
            if (r9.f2149a.mAudioFocus != false) goto L82;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x028a, code lost:
        
            com.iflytek.aiui.pro.C3587aq.m1055b(r9.f2149a.mContext, java.lang.Boolean.valueOf(r9.f2149a.mRequestFocus), r9.f2149a.afChangeListener);
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x02b5, code lost:
        
            r9.f2149a.mThread = null;
            com.iflytek.aiui.pro.C3589as.m1059a(com.iflytek.aiui.data.audio.player.PcmPlayer.TAG, "player stopped");
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x02c1, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x02a2, code lost:
        
            com.iflytek.aiui.pro.C3587aq.m1055b(r9.f2149a.mContext, java.lang.Boolean.valueOf(r9.f2149a.mRequestFocus), null);
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            try {
                try {
                    C3589as.m1059a(PcmPlayer.TAG, "start player");
                    C3589as.m1059a(PcmPlayer.TAG, "audioFocus=" + PcmPlayer.this.mAudioFocus + ", requestFocus=" + PcmPlayer.this.mRequestFocus);
                    if (PcmPlayer.this.mAudioFocus) {
                        C3587aq.m1054a(PcmPlayer.this.mContext, Boolean.valueOf(PcmPlayer.this.mRequestFocus), PcmPlayer.this.afChangeListener);
                    } else {
                        C3587aq.m1054a(PcmPlayer.this.mContext, Boolean.valueOf(PcmPlayer.this.mRequestFocus), null);
                    }
                    PcmPlayer.this.mBuffer.beginRead();
                    synchronized (PcmPlayer.this.mSyncObj) {
                        if (PcmPlayer.this.mPlaytate != 4 && PcmPlayer.this.mPlaytate != 3) {
                            PcmPlayer.this.mPlaytate = 2;
                        }
                    }
                    PcmPlayer.this.startFadeIn();
                    while (true) {
                        PcmPlayer.this.prepAudioPlayer();
                        if (PcmPlayer.this.mPlaytate != 2 && PcmPlayer.this.mPlaytate != 1 && !PcmPlayer.this.mFading) {
                            if (PcmPlayer.this.mPlaytate == 3) {
                                if (2 != PcmPlayer.this.mAudioTrack.getPlayState()) {
                                    PcmPlayer.this.mAudioTrack.pause();
                                    if (PcmPlayer.this.mFading) {
                                        PcmPlayer.this.setSilence();
                                    }
                                }
                                sleep(5L);
                            } else if (4 == PcmPlayer.this.mPlaytate) {
                                Message.obtain(PcmPlayer.this.mUihandler, 4, Boolean.valueOf(PcmPlayer.this.mBuffer.isOver())).sendToTarget();
                                PcmPlayer.this.setSilence();
                                break;
                            }
                        }
                        if (PcmPlayer.this.mBuffer.playAble()) {
                            if (PcmPlayer.this.setState(1, 2)) {
                                Message.obtain(PcmPlayer.this.mUihandler, 2).sendToTarget();
                                C3589as.m1058a("BUFFERING to PLAYING  fading ");
                                PcmPlayer.this.startFadeIn();
                            }
                            int playPercent = PcmPlayer.this.mBuffer.getPlayPercent();
                            PcmBuffer.C3533a palyAudioInfo = PcmPlayer.this.mBuffer.getPalyAudioInfo();
                            if (palyAudioInfo != null) {
                                PcmPlayer.this.mCurEndPos = palyAudioInfo.f2145d;
                                Message.obtain(PcmPlayer.this.mUihandler, 3, playPercent, palyAudioInfo.f2144c).sendToTarget();
                            }
                            if (PcmPlayer.this.mAudioTrack.getPlayState() != 3) {
                                PcmPlayer.this.mAudioTrack.play();
                            }
                            if (PcmPlayer.this.mBufferingFadingEnable) {
                                if (!PcmPlayer.this.mBuffer.isBufferingFinished() && !PcmPlayer.this.mBuffer.hasMoreBuffer(PcmPlayer.this.FADING_SIZE) && Math.abs(PcmPlayer.this.mTargetVol - 0.0f) >= 0.1f) {
                                    C3589as.m1058a("no more size  fading ");
                                    PcmPlayer.this.startFadeOut();
                                } else if (2 == PcmPlayer.this.mPlaytate && ((PcmPlayer.this.mBuffer.isBufferingFinished() || PcmPlayer.this.mBuffer.hasMoreBuffer(PcmPlayer.this.FADING_SIZE)) && Math.abs(PcmPlayer.this.mTargetVol - 1.0f) >= 0.1f)) {
                                    C3589as.m1058a("has buffer  fading ");
                                    PcmPlayer.this.startFadeIn();
                                }
                            }
                            if (PcmPlayer.this.mFading) {
                                PcmPlayer.this.fading();
                            }
                            PcmPlayer.this.mBuffer.writeTrack(PcmPlayer.this.mAudioTrack, PcmPlayer.this.mPerPlaySize);
                        } else {
                            if (PcmPlayer.this.mBuffer.isOver()) {
                                C3589as.m1058a("play stoped");
                                PcmPlayer.this.mPlaytate = 4;
                                Message.obtain(PcmPlayer.this.mUihandler, 4, true).sendToTarget();
                                PcmPlayer.this.mFading = false;
                                break;
                            }
                            if (PcmPlayer.this.mFading) {
                                PcmPlayer.this.mFading = false;
                            } else {
                                if (PcmPlayer.this.setState(2, 1)) {
                                    C3589as.m1058a("play onpaused!");
                                    Message.obtain(PcmPlayer.this.mUihandler, 1).sendToTarget();
                                }
                                sleep(5L);
                            }
                        }
                    }
                    if (PcmPlayer.this.mAudioTrack != null) {
                        PcmPlayer.this.mAudioTrack.stop();
                    }
                    synchronized (PcmPlayer.this.mSyncObj) {
                        PcmPlayer.this.mPlaytate = 4;
                    }
                    if (PcmPlayer.this.mAudioTrack != null) {
                        PcmPlayer.this.mAudioTrack.release();
                        PcmPlayer.this.mAudioTrack = null;
                    }
                } catch (Exception e) {
                    C3589as.m1063b(PcmPlayer.TAG, e.getMessage());
                    Message.obtain(PcmPlayer.this.mUihandler, 0, new AIUIError(20011)).sendToTarget();
                    synchronized (PcmPlayer.this.mSyncObj) {
                        PcmPlayer.this.mPlaytate = 4;
                        if (PcmPlayer.this.mAudioTrack != null) {
                            PcmPlayer.this.mAudioTrack.release();
                            PcmPlayer.this.mAudioTrack = null;
                        }
                    }
                }
            } catch (Throwable th) {
                synchronized (PcmPlayer.this.mSyncObj) {
                    PcmPlayer.this.mPlaytate = 4;
                    if (PcmPlayer.this.mAudioTrack != null) {
                        PcmPlayer.this.mAudioTrack.release();
                        PcmPlayer.this.mAudioTrack = null;
                    }
                    if (PcmPlayer.this.mAudioFocus) {
                        C3587aq.m1055b(PcmPlayer.this.mContext, Boolean.valueOf(PcmPlayer.this.mRequestFocus), PcmPlayer.this.afChangeListener);
                    } else {
                        C3587aq.m1055b(PcmPlayer.this.mContext, Boolean.valueOf(PcmPlayer.this.mRequestFocus), null);
                    }
                    PcmPlayer.this.mThread = null;
                    C3589as.m1059a(PcmPlayer.TAG, "player stopped");
                    throw th;
                }
            }
        }
    }

    /* renamed from: com.iflytek.aiui.data.audio.player.PcmPlayer$b */
    /* loaded from: classes4.dex */
    class HandlerC3537b extends Handler {
        HandlerC3537b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onPaused();
                        return;
                    }
                    return;
                } else if (i == 2) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onResume();
                        return;
                    }
                    return;
                } else if (i == 3) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onPercent(message.arg1, message.arg2, PcmPlayer.this.mCurEndPos);
                        return;
                    }
                    return;
                } else {
                    if (i != 4 || PcmPlayer.this.mListener == null) {
                        return;
                    }
                    PcmPlayer.this.mListener.onStoped(((Boolean) message.obj).booleanValue());
                }
            } else if (PcmPlayer.this.mListener == null) {
                return;
            } else {
                PcmPlayer.this.mListener.onError((AIUIError) message.obj);
            }
            PcmPlayer.this.mListener = null;
        }
    }

    /* renamed from: com.iflytek.aiui.data.audio.player.PcmPlayer$c */
    /* loaded from: classes4.dex */
    private class C3538c extends Thread {

        /* renamed from: a */
        private int f2152a;

        private C3538c() {
            this.f2152a = PcmPlayer.this.mStreamType;
        }

        /* synthetic */ C3538c(PcmPlayer pcmPlayer, C3536a c3536a) {
            this();
        }

        /* renamed from: a */
        public int m800a() {
            return this.f2152a;
        }

        /* renamed from: b */
        public void m801b(int i) {
            this.f2152a = i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:132:0x0306, code lost:
        
            if (r9.f2153b.mAudioFocus == false) goto L110;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x0282, code lost:
        
            if (r9.f2153b.mAudioFocus != false) goto L109;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x0320, code lost:
        
            com.iflytek.aiui.pro.C3645v0.m1603b(r9.f2153b.mContext, java.lang.Boolean.valueOf(r9.f2153b.mRequestFocus), null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0333, code lost:
        
            com.iflytek.aiui.data.audio.player.PcmPlayer.access$1902(r9.f2153b, (com.iflytek.aiui.data.audio.player.PcmPlayer.C3538c) null);
            com.iflytek.aiui.pro.C3651y0.m1620b(com.iflytek.aiui.data.audio.player.PcmPlayer.TAG, "player stopped");
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x033f, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0308, code lost:
        
            com.iflytek.aiui.pro.C3645v0.m1603b(r9.f2153b.mContext, java.lang.Boolean.valueOf(r9.f2153b.mRequestFocus), r9.f2153b.afChangeListener);
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            try {
                try {
                    C3651y0.m1620b(PcmPlayer.TAG, "start player");
                    C3651y0.m1620b(PcmPlayer.TAG, "audioFocus=" + PcmPlayer.this.mAudioFocus + ", requestFocus=" + PcmPlayer.this.mRequestFocus);
                    if (PcmPlayer.this.mAudioFocus) {
                        C3645v0.m1602a(PcmPlayer.this.mContext, Boolean.valueOf(PcmPlayer.this.mRequestFocus), PcmPlayer.this.afChangeListener);
                    } else {
                        C3645v0.m1602a(PcmPlayer.this.mContext, Boolean.valueOf(PcmPlayer.this.mRequestFocus), null);
                    }
                    PcmPlayer.this.mBuffer.beginRead();
                    synchronized (PcmPlayer.this.mSyncObj) {
                        if (PcmPlayer.this.mPlaytate != 4 && PcmPlayer.this.mPlaytate != 3) {
                            PcmPlayer.this.mPlaytate = 2;
                        }
                    }
                    PcmPlayer.this.startFadeIn();
                    while (true) {
                        PcmPlayer.this.prepAudioPlayer();
                        if (PcmPlayer.this.mPlaytate != 2 && PcmPlayer.this.mPlaytate != 1 && !PcmPlayer.this.mFading) {
                            if (PcmPlayer.this.mPlaytate == 3) {
                                if (2 != PcmPlayer.this.mAudioTrack.getPlayState()) {
                                    PcmPlayer.this.mAudioTrack.pause();
                                    if (PcmPlayer.this.mFading) {
                                        PcmPlayer.this.setSilence();
                                    }
                                }
                                Thread.sleep(5L);
                            } else if (4 == PcmPlayer.this.mPlaytate) {
                                Message.obtain(PcmPlayer.this.mUihandler, 4, Boolean.valueOf(PcmPlayer.this.mBuffer.isOver())).sendToTarget();
                                PcmPlayer.this.setSilence();
                                break;
                            }
                        }
                        if (PcmPlayer.this.mBuffer.playAble()) {
                            if (PcmPlayer.this.setState(1, 2)) {
                                Message.obtain(PcmPlayer.this.mUihandler, 2).sendToTarget();
                                C3651y0.m1619a("BUFFERING to PLAYING  fading ");
                                PcmPlayer.this.startFadeIn();
                            }
                            int playPercent = PcmPlayer.this.mBuffer.getPlayPercent();
                            PcmBuffer.C3533a palyAudioInfo = PcmPlayer.this.mBuffer.getPalyAudioInfo();
                            if (palyAudioInfo != null) {
                                PcmPlayer.this.mCurEndPos = palyAudioInfo.f2145d;
                                Message.obtain(PcmPlayer.this.mUihandler, 3, playPercent, palyAudioInfo.f2144c).sendToTarget();
                            }
                            if (PcmPlayer.this.mAudioTrack.getPlayState() != 3) {
                                PcmPlayer.this.mAudioTrack.play();
                            }
                            if (PcmPlayer.this.mBufferingFadingEnable) {
                                if (!PcmPlayer.this.mBuffer.isBufferingFinished() && !PcmPlayer.this.mBuffer.hasMoreBuffer(PcmPlayer.this.FADING_SIZE) && Math.abs(PcmPlayer.this.mTargetVol - 0.0f) >= 0.1f) {
                                    C3651y0.m1619a("no more size  fading ");
                                    PcmPlayer.this.startFadeOut();
                                } else if (2 == PcmPlayer.this.mPlaytate && ((PcmPlayer.this.mBuffer.isBufferingFinished() || PcmPlayer.this.mBuffer.hasMoreBuffer(PcmPlayer.this.FADING_SIZE)) && Math.abs(PcmPlayer.this.mTargetVol - 1.0f) >= 0.1f)) {
                                    C3651y0.m1619a("has buffer  fading ");
                                    PcmPlayer.this.startFadeIn();
                                }
                            }
                            if (PcmPlayer.this.mFading) {
                                PcmPlayer.this.fading();
                            }
                            PcmPlayer.this.mBuffer.writeTrack(PcmPlayer.this.mAudioTrack, PcmPlayer.this.mPerPlaySize);
                        } else {
                            if (PcmPlayer.this.mBuffer.isOver()) {
                                C3651y0.m1619a("play stoped");
                                PcmPlayer.this.mPlaytate = 4;
                                Message.obtain(PcmPlayer.this.mUihandler, 4, Boolean.TRUE).sendToTarget();
                                PcmPlayer.this.mFading = false;
                                break;
                            }
                            if (PcmPlayer.this.mFading) {
                                PcmPlayer.this.mFading = false;
                            } else {
                                if (PcmPlayer.this.setState(2, 1)) {
                                    C3651y0.m1619a("play onpaused!");
                                    Message.obtain(PcmPlayer.this.mUihandler, 1).sendToTarget();
                                }
                                Thread.sleep(5L);
                            }
                        }
                    }
                    if (PcmPlayer.this.mAudioTrack != null) {
                        PcmPlayer.this.mAudioTrack.stop();
                    }
                    synchronized (PcmPlayer.this.mSyncObj) {
                        PcmPlayer.this.mPlaytate = 4;
                    }
                    if (PcmPlayer.this.mAudioTrack != null) {
                        PcmPlayer.this.mAudioTrack.release();
                        PcmPlayer.this.mAudioTrack = null;
                    }
                } catch (Exception e) {
                    C3651y0.m1622d(PcmPlayer.TAG, e.getMessage());
                    Message.obtain(PcmPlayer.this.mUihandler, 0, new AIUIError(20011)).sendToTarget();
                    synchronized (PcmPlayer.this.mSyncObj) {
                        PcmPlayer.this.mPlaytate = 4;
                        if (PcmPlayer.this.mAudioTrack != null) {
                            PcmPlayer.this.mAudioTrack.release();
                            PcmPlayer.this.mAudioTrack = null;
                        }
                    }
                }
            } catch (Throwable th) {
                synchronized (PcmPlayer.this.mSyncObj) {
                    PcmPlayer.this.mPlaytate = 4;
                    if (PcmPlayer.this.mAudioTrack != null) {
                        PcmPlayer.this.mAudioTrack.release();
                        PcmPlayer.this.mAudioTrack = null;
                    }
                    if (PcmPlayer.this.mAudioFocus) {
                        C3645v0.m1603b(PcmPlayer.this.mContext, Boolean.valueOf(PcmPlayer.this.mRequestFocus), PcmPlayer.this.afChangeListener);
                    } else {
                        C3645v0.m1603b(PcmPlayer.this.mContext, Boolean.valueOf(PcmPlayer.this.mRequestFocus), null);
                    }
                    PcmPlayer.access$1902(PcmPlayer.this, (C3538c) null);
                    C3651y0.m1620b(PcmPlayer.TAG, "player stopped");
                    throw th;
                }
            }
        }
    }

    public PcmPlayer(Context context) {
        this.mAudioTrack = null;
        this.mBuffer = null;
        this.mContext = null;
        this.mThread = null;
        this.mListener = null;
        this.mPlaytate = 0;
        this.mStreamType = 3;
        this.mAudioFocus = true;
        this.mRequestFocus = true;
        this.mChangeListenerFlag = false;
        this.mAudioLock = new Object();
        this.mSyncObj = this;
        this.mIsFadeOut = false;
        this.BYTES_OF_PER_SAMPLE = 2;
        this.FADE_TIME = 500;
        this.FADE_PERIOD = 50;
        this.mPerPlaySize = 1600;
        this.MAX_VOL = 1.0f;
        this.MIN_VOL = 0.0f;
        this.PER = 0.1f;
        this.FADING_SIZE = 1600 * 10;
        this.mCurVol = 0.0f;
        this.mTargetVol = 1.0f;
        this.mCurFadingPeriod = 0.1f;
        this.mFading = false;
        this.mBufferingFadingEnable = false;
        this.afChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.iflytek.aiui.data.audio.player.PcmPlayer.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (i == -2 || i == -3 || i == -1) {
                    C3589as.m1059a(PcmPlayer.TAG, "pause start");
                    if (PcmPlayer.this.pause()) {
                        C3589as.m1059a(PcmPlayer.TAG, "pause success");
                        PcmPlayer.this.mChangeListenerFlag = true;
                        if (PcmPlayer.this.mListener != null) {
                            PcmPlayer.this.mListener.onPaused();
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (i == 1) {
                    C3589as.m1059a(PcmPlayer.TAG, "resume start");
                    if (PcmPlayer.this.mChangeListenerFlag) {
                        PcmPlayer.this.mChangeListenerFlag = false;
                        if (PcmPlayer.this.resume()) {
                            C3589as.m1059a(PcmPlayer.TAG, "resume success");
                            if (PcmPlayer.this.mListener != null) {
                                PcmPlayer.this.mListener.onResume();
                            }
                        }
                    }
                }
            }
        };
        this.mCurEndPos = 0;
        this.mUihandler = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.aiui.data.audio.player.PcmPlayer.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onError((AIUIError) message.obj);
                        PcmPlayer.this.mListener = null;
                        return;
                    }
                    return;
                }
                if (i == 1) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onPaused();
                        return;
                    }
                    return;
                }
                if (i == 2) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onResume();
                    }
                } else if (i == 3) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onPercent(message.arg1, message.arg2, PcmPlayer.this.mCurEndPos);
                    }
                } else if (i == 4 && PcmPlayer.this.mListener != null) {
                    PcmPlayer.this.mListener.onStoped(((Boolean) message.obj).booleanValue());
                    PcmPlayer.this.mListener = null;
                }
            }
        };
        this.mContext = context;
    }

    public PcmPlayer(Context context, int i, boolean z, boolean z2) {
        this.mAudioTrack = null;
        this.mBuffer = null;
        this.mContext = null;
        this.mThread = null;
        this.mListener = null;
        this.mPlaytate = 0;
        this.mStreamType = 3;
        this.mAudioFocus = true;
        this.mRequestFocus = true;
        this.mChangeListenerFlag = false;
        this.mAudioLock = new Object();
        this.mSyncObj = this;
        this.mIsFadeOut = false;
        this.BYTES_OF_PER_SAMPLE = 2;
        this.FADE_TIME = 500;
        this.FADE_PERIOD = 50;
        this.mPerPlaySize = 1600;
        this.MAX_VOL = 1.0f;
        this.MIN_VOL = 0.0f;
        this.PER = 0.1f;
        this.FADING_SIZE = 1600 * 10;
        this.mCurVol = 0.0f;
        this.mTargetVol = 1.0f;
        this.mCurFadingPeriod = 0.1f;
        this.mFading = false;
        this.mBufferingFadingEnable = false;
        this.afChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.iflytek.aiui.data.audio.player.PcmPlayer.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i2) {
                if (i2 == -2 || i2 == -3 || i2 == -1) {
                    C3589as.m1059a(PcmPlayer.TAG, "pause start");
                    if (PcmPlayer.this.pause()) {
                        C3589as.m1059a(PcmPlayer.TAG, "pause success");
                        PcmPlayer.this.mChangeListenerFlag = true;
                        if (PcmPlayer.this.mListener != null) {
                            PcmPlayer.this.mListener.onPaused();
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (i2 == 1) {
                    C3589as.m1059a(PcmPlayer.TAG, "resume start");
                    if (PcmPlayer.this.mChangeListenerFlag) {
                        PcmPlayer.this.mChangeListenerFlag = false;
                        if (PcmPlayer.this.resume()) {
                            C3589as.m1059a(PcmPlayer.TAG, "resume success");
                            if (PcmPlayer.this.mListener != null) {
                                PcmPlayer.this.mListener.onResume();
                            }
                        }
                    }
                }
            }
        };
        this.mCurEndPos = 0;
        this.mUihandler = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.aiui.data.audio.player.PcmPlayer.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 0) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onError((AIUIError) message.obj);
                        PcmPlayer.this.mListener = null;
                        return;
                    }
                    return;
                }
                if (i2 == 1) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onPaused();
                        return;
                    }
                    return;
                }
                if (i2 == 2) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onResume();
                    }
                } else if (i2 == 3) {
                    if (PcmPlayer.this.mListener != null) {
                        PcmPlayer.this.mListener.onPercent(message.arg1, message.arg2, PcmPlayer.this.mCurEndPos);
                    }
                } else if (i2 == 4 && PcmPlayer.this.mListener != null) {
                    PcmPlayer.this.mListener.onStoped(((Boolean) message.obj).booleanValue());
                    PcmPlayer.this.mListener = null;
                }
            }
        };
        this.mContext = context;
        this.mStreamType = i;
        this.mRequestFocus = z;
        this.mBufferingFadingEnable = z2;
    }

    private void createAudio() throws Exception {
        C3589as.m1059a(TAG, "createAudio start, tid=" + Thread.currentThread().getId());
        int rate = this.mBuffer.getRate();
        this.mBufferSize = AudioTrack.getMinBufferSize(rate, 2, 2);
        this.mPerPlaySize = (rate / 1000) * 2 * 50;
        if (this.mAudioTrack != null) {
            release();
        }
        C3589as.m1059a(TAG, "createAudio || mStreamType = " + this.mStreamType + ", buffer size: " + this.mBufferSize);
        this.mAudioTrack = new AudioTrack(this.mStreamType, rate, 2, 2, this.mBufferSize * 2, 1);
        int i = this.mBufferSize;
        if (i == -2 || i == -1) {
            throw new Exception();
        }
        C3589as.m1059a(TAG, "createAudio end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepAudioPlayer() throws Exception {
        C3536a c3536a = this.mThread;
        if (this.mAudioTrack == null || !(c3536a == null || c3536a.m798a() == this.mStreamType)) {
            C3589as.m1059a(TAG, "prepAudioPlayer || audiotrack is null or stream type is change.");
            createAudio();
            if (c3536a != null) {
                c3536a.m799a(this.mStreamType);
            }
        }
    }

    private void setState(int i) {
        this.mPlaytate = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setState(int i, int i2) {
        boolean z;
        synchronized (this.mSyncObj) {
            if (i == this.mPlaytate) {
                this.mPlaytate = i2;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void fading() {
        synchronized (this.mSyncObj) {
            if (Math.abs(this.mTargetVol - this.mCurVol) < 0.1f) {
                this.mCurVol = this.mTargetVol;
                this.mFading = false;
                C3589as.m1058a("fading finish");
            } else {
                this.mCurVol += this.mCurFadingPeriod;
            }
        }
        AudioTrack audioTrack = this.mAudioTrack;
        float f = this.mCurVol;
        audioTrack.setStereoVolume(f, f);
    }

    public int getState() {
        return this.mPlaytate;
    }

    public boolean pause() {
        if (this.mPlaytate == 4 || this.mPlaytate == 3) {
            return false;
        }
        C3589as.m1058a("pause start fade out");
        startFadeOut();
        this.mPlaytate = 3;
        return true;
    }

    public boolean play(PcmBuffer pcmBuffer, PcmPlayerListener pcmPlayerListener) {
        boolean z;
        C3589as.m1059a(TAG, "play mPlaytate= " + this.mPlaytate + ",mAudioFocus= " + this.mAudioFocus);
        synchronized (this.mSyncObj) {
            if (this.mThread == null || this.mPlaytate == 4 || this.mPlaytate == 0 || this.mPlaytate == 3) {
                this.mBuffer = pcmBuffer;
                this.mListener = pcmPlayerListener;
                C3536a c3536a = new C3536a();
                this.mThread = c3536a;
                c3536a.start();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean rePlay(PcmBuffer pcmBuffer, PcmPlayerListener pcmPlayerListener) {
        setState(0);
        return play(pcmBuffer, pcmPlayerListener);
    }

    public void release() {
        synchronized (this.mAudioLock) {
            if (this.mAudioTrack != null) {
                if (this.mAudioTrack.getPlayState() == 3) {
                    this.mAudioTrack.stop();
                }
                this.mAudioTrack.release();
                this.mAudioTrack = null;
            }
            C3589as.m1059a(TAG, "mAudioTrack released");
        }
    }

    public boolean resume() {
        boolean state = setState(3, 2);
        if (state) {
            C3589as.m1058a("resume start fade in");
            startFadeIn();
        }
        return state;
    }

    public void setAudioFocus(boolean z) {
        C3589as.m1059a(TAG, "setAudioFocus " + z);
        this.mAudioFocus = z;
    }

    public void setIsFadeOut(boolean z) {
        this.mIsFadeOut = z;
    }

    public void setRequestFocus(boolean z) {
        C3589as.m1059a(TAG, "setRequestFocus " + z);
        this.mRequestFocus = z;
    }

    public void setSilence() {
        C3589as.m1058a("fading set silence");
        synchronized (this.mSyncObj) {
            if (Math.abs(0.0f - this.mTargetVol) < 0.1f) {
                this.mCurVol = 0.0f;
                this.mFading = false;
            }
        }
        AudioTrack audioTrack = this.mAudioTrack;
        float f = this.mCurVol;
        audioTrack.setStereoVolume(f, f);
    }

    public void setStreamType(int i) {
        C3589as.m1059a(TAG, "setmStreamType || streamType = " + i);
        this.mStreamType = i;
    }

    public void startFadeIn() {
        synchronized (this.mSyncObj) {
            C3589as.m1058a("start fade in");
            this.mFading = true;
            this.mTargetVol = 1.0f;
            this.mCurFadingPeriod = 0.1f;
        }
    }

    public void startFadeOut() {
        synchronized (this.mSyncObj) {
            C3589as.m1058a("start fade out");
            this.mFading = true;
            this.mTargetVol = 0.0f;
            this.mCurFadingPeriod = -0.1f;
        }
    }

    public void stop() {
        if (this.mIsFadeOut && 4 != this.mPlaytate) {
            C3589as.m1058a("stop start fade out");
            startFadeOut();
        }
        synchronized (this.mSyncObj) {
            this.mPlaytate = 4;
        }
    }
}
