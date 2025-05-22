package com.pudutech.rgbdviewer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;

/* loaded from: classes.dex */
class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private MyThread myThread;

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public DrawView(Context context) {
        super(context);
        SurfaceHolder holder = getHolder();
        this.holder = holder;
        holder.addCallback(this);
        this.myThread = new MyThread(this.holder);
    }

    public DrawView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SurfaceHolder holder = getHolder();
        this.holder = holder;
        holder.addCallback(this);
        this.myThread = new MyThread(this.holder);
    }

    public DrawView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        SurfaceHolder holder = getHolder();
        this.holder = holder;
        holder.addCallback(this);
        this.myThread = new MyThread(this.holder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.myThread.isRun = true;
        this.myThread.start();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.myThread.isRun = false;
    }

    /* loaded from: classes.dex */
    class MyThread extends Thread {
        private SurfaceHolder holder;
        public boolean isRun = true;

        public MyThread(SurfaceHolder surfaceHolder) {
            this.holder = surfaceHolder;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i;
            int i2;
            Exception e;
            Throwable th;
            while (this.isRun) {
                Canvas canvas = null;
                try {
                    try {
                        synchronized (this.holder) {
                            try {
                                canvas = this.holder.lockCanvas();
                                canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
                                Paint paint = new Paint();
                                paint.setColor(-1);
                                canvas.drawRect(new Rect(100, 50, 300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), paint);
                                StringBuilder sb = new StringBuilder();
                                sb.append("这是第");
                                i2 = i + 1;
                                try {
                                    sb.append(i);
                                    sb.append("秒");
                                    canvas.drawText(sb.toString(), 100.0f, 310.0f, paint);
                                    Thread.sleep(1000L);
                                } catch (Throwable th2) {
                                    th = th2;
                                    try {
                                        throw th;
                                        break;
                                    } catch (Exception e2) {
                                        e = e2;
                                        e.printStackTrace();
                                    }
                                }
                            } catch (Throwable th3) {
                                i2 = i;
                                th = th3;
                            }
                        }
                    } finally {
                        if (0 != 0) {
                            this.holder.unlockCanvasAndPost(null);
                        }
                    }
                } catch (Exception e3) {
                    i2 = i;
                    e = e3;
                }
                if (canvas != null) {
                }
            }
        }
    }
}
