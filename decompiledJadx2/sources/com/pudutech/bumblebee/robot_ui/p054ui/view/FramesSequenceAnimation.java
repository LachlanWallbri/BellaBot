package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.widget.ImageView;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class FramesSequenceAnimation {
    private Bitmap.Config bitmapConfig;
    private int bitmapH;
    private int bitmapW;
    private HashMap<Integer, Bitmap> cacheImages;
    private boolean isCache;
    private boolean isGoBack;
    private boolean isLoop;
    private Bitmap mBitmap;
    private BitmapFactory.Options mBitmapOptions;
    private long mDelayMillis;
    private int[] mFrames;
    private Handler mHandler;
    private int mIndex;
    private boolean mIsRunning;
    private OnAnimationListener mOnAnimationListener;
    private boolean mShouldRun;
    private SoftReference<ImageView> mSoftReferenceImageView;

    /* loaded from: classes4.dex */
    public interface OnAnimationListener {
        void onAnimationEnd(FramesSequenceAnimation framesSequenceAnimation);

        void onAnimationStart(FramesSequenceAnimation framesSequenceAnimation);

        void onAnimationStopOrCancel(FramesSequenceAnimation framesSequenceAnimation);
    }

    public FramesSequenceAnimation(ImageView imageView, int[] iArr, HashMap<Integer, Bitmap> hashMap) {
        this.mDelayMillis = 58L;
        this.isLoop = false;
        this.isGoBack = true;
        this.isCache = true;
        this.cacheImages = new HashMap<>();
        this.mBitmap = null;
        this.bitmapW = 0;
        this.bitmapH = 0;
        this.bitmapConfig = null;
        if (hashMap != null) {
            this.isCache = true;
            this.cacheImages.putAll(hashMap);
        }
        this.mHandler = new Handler();
        this.mFrames = iArr;
        this.mIndex = -1;
        this.mSoftReferenceImageView = new SoftReference<>(imageView);
        this.mShouldRun = false;
        this.mIsRunning = false;
        imageView.setImageResource(this.mFrames[0]);
        if (Build.VERSION.SDK_INT >= 11) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            this.bitmapW = bitmap.getWidth();
            this.bitmapH = bitmap.getHeight();
            this.bitmapConfig = bitmap.getConfig();
            this.mBitmap = Bitmap.createBitmap(this.bitmapW, this.bitmapH, this.bitmapConfig);
            this.mBitmapOptions = new BitmapFactory.Options();
            BitmapFactory.Options options = this.mBitmapOptions;
            options.inBitmap = this.mBitmap;
            options.inMutable = true;
            options.inSampleSize = 1;
        }
    }

    public FramesSequenceAnimation(ImageView imageView, int[] iArr) {
        this(imageView, iArr, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNext() {
        this.mIndex++;
        int i = this.mIndex;
        int[] iArr = this.mFrames;
        if (i < iArr.length) {
            return iArr[i];
        }
        if (i >= iArr.length && this.isLoop) {
            this.mIndex = 0;
            return iArr[0];
        }
        if (this.isGoBack) {
            end();
            int[] iArr2 = this.mFrames;
            this.mIndex = 0;
            return iArr2[0];
        }
        end();
        this.mIndex = -1;
        return -1;
    }

    public FramesSequenceAnimation setDuration(long j) {
        if (this.mFrames.length == 0) {
            throw new IllegalArgumentException("Animation frame length == 0");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.mDelayMillis = j / r0.length;
        return this;
    }

    public FramesSequenceAnimation setDelayMillis(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.mDelayMillis = j;
        return this;
    }

    public synchronized FramesSequenceAnimation setLoop(boolean z) {
        this.isLoop = z;
        return this;
    }

    public synchronized FramesSequenceAnimation setGoBack(boolean z) {
        this.isGoBack = z;
        return this;
    }

    public synchronized void start() {
        this.mShouldRun = true;
        if (this.mIsRunning) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.FramesSequenceAnimation.1
            /* JADX WARN: Removed duplicated region for block: B:19:0x00a1  */
            /* JADX WARN: Removed duplicated region for block: B:24:0x00ba  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                int next;
                Exception e;
                Bitmap bitmap;
                ImageView imageView = (ImageView) FramesSequenceAnimation.this.mSoftReferenceImageView.get();
                if (!FramesSequenceAnimation.this.mShouldRun || imageView == null) {
                    FramesSequenceAnimation.this.mIsRunning = false;
                    return;
                }
                FramesSequenceAnimation.this.mIsRunning = true;
                FramesSequenceAnimation.this.mHandler.postDelayed(this, FramesSequenceAnimation.this.mDelayMillis);
                if (!imageView.isShown() || (next = FramesSequenceAnimation.this.getNext()) == -1) {
                    return;
                }
                if (FramesSequenceAnimation.this.mBitmap != null) {
                    try {
                        bitmap = FramesSequenceAnimation.this.isCache ? (Bitmap) FramesSequenceAnimation.this.cacheImages.get(Integer.valueOf(next)) : null;
                        if (bitmap == null) {
                            try {
                                if (FramesSequenceAnimation.this.isCache) {
                                    FramesSequenceAnimation.this.mBitmapOptions.inBitmap = Bitmap.createBitmap(FramesSequenceAnimation.this.bitmapW, FramesSequenceAnimation.this.bitmapH, FramesSequenceAnimation.this.bitmapConfig);
                                }
                                bitmap = BitmapFactory.decodeResource(imageView.getResources(), next, FramesSequenceAnimation.this.mBitmapOptions);
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                if (bitmap == null) {
                                }
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        bitmap = null;
                    }
                    if (bitmap == null) {
                        if (FramesSequenceAnimation.this.isCache) {
                            FramesSequenceAnimation.this.cacheImages.put(Integer.valueOf(next), bitmap);
                        }
                        imageView.setImageBitmap(bitmap);
                        return;
                    } else {
                        imageView.setImageResource(next);
                        FramesSequenceAnimation.this.mBitmap.recycle();
                        FramesSequenceAnimation.this.mBitmap = null;
                        return;
                    }
                }
                imageView.setImageResource(next);
            }
        });
        if (this.mOnAnimationListener != null) {
            this.mOnAnimationListener.onAnimationStart(this);
        }
    }

    public synchronized void stop() {
        if (this.mShouldRun) {
            this.mShouldRun = false;
            if (this.mOnAnimationListener != null) {
                this.mOnAnimationListener.onAnimationStopOrCancel(this);
            }
        }
    }

    public synchronized void cancel() {
        if (this.mShouldRun) {
            this.mShouldRun = false;
            this.mIndex = 0;
            if (this.mOnAnimationListener != null) {
                this.mOnAnimationListener.onAnimationStopOrCancel(this);
            }
        }
    }

    private synchronized void end() {
        if (this.mShouldRun) {
            this.mShouldRun = false;
            if (this.mOnAnimationListener != null) {
                this.mOnAnimationListener.onAnimationEnd(this);
            }
        }
    }

    public synchronized void goBackStart() {
        if (this.mFrames.length == 0) {
            throw new IllegalArgumentException("Animation frame length == 0");
        }
        this.mIndex = 0;
        this.mSoftReferenceImageView.get().setImageResource(this.mFrames[this.mIndex]);
    }

    public FramesSequenceAnimation setOnAnimStopListener(OnAnimationListener onAnimationListener) {
        this.mOnAnimationListener = onAnimationListener;
        return this;
    }
}
