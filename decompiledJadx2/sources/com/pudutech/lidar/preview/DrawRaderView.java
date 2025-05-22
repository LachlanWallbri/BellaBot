package com.pudutech.lidar.preview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import java.util.List;

/* loaded from: classes5.dex */
public class DrawRaderView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int MSG_INVALIDATE = 0;
    private static final String TAG = "Lidar";

    /* renamed from: dx */
    float f5461dx;

    /* renamed from: dy */
    float f5462dy;
    float lastx;
    float lasty;
    private DrawThread mDrawThread;
    private Handler mHandler;
    SurfaceHolder mHolder;
    private final RectF mLidarRect;
    Paint mLinePaint;
    private Looper mLooper;
    int mRectRange;
    List<LidarNode> mlsDatas;
    boolean move;
    int nHeight;
    double nLenStart2Touch;
    int nWidth;
    float oldx;
    float oly;
    double scale;

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public DrawRaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.nHeight = 0;
        this.nWidth = 0;
        this.scale = 0.3d;
        this.mRectRange = 5;
        this.f5461dx = -3.0f;
        this.f5462dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.lastx = 0.0f;
        this.lasty = 0.0f;
        this.mLidarRect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mlsDatas = ListLidarNodePool.obtain();
        this.nLenStart2Touch = 0.0d;
        init();
    }

    public DrawRaderView(Context context) {
        super(context);
        this.nHeight = 0;
        this.nWidth = 0;
        this.scale = 0.3d;
        this.mRectRange = 5;
        this.f5461dx = -3.0f;
        this.f5462dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.lastx = 0.0f;
        this.lasty = 0.0f;
        this.mLidarRect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mlsDatas = ListLidarNodePool.obtain();
        this.nLenStart2Touch = 0.0d;
        init();
    }

    public DrawRaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.nHeight = 0;
        this.nWidth = 0;
        this.scale = 0.3d;
        this.mRectRange = 5;
        this.f5461dx = -3.0f;
        this.f5462dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.lastx = 0.0f;
        this.lasty = 0.0f;
        this.mLidarRect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mlsDatas = ListLidarNodePool.obtain();
        this.nLenStart2Touch = 0.0d;
        init();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.mDrawThread == null) {
            this.mDrawThread = new DrawThread();
            this.mDrawThread.start();
            Canvas lockCanvas = surfaceHolder.lockCanvas();
            drawUseSurface(lockCanvas);
            surfaceHolder.unlockCanvasAndPost(lockCanvas);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        DrawThread drawThread = this.mDrawThread;
        if (drawThread != null) {
            drawThread.exit();
            this.mDrawThread = null;
        }
    }

    public void init() {
        this.mHolder = getHolder();
        this.mHolder.addCallback(this);
        this.mLinePaint = new Paint();
        this.mLinePaint.setAntiAlias(true);
        this.mLinePaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mLinePaint.setStrokeWidth(1.0f);
    }

    /* loaded from: classes5.dex */
    class DrawThread extends Thread {
        DrawThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            DrawRaderView.this.mLooper = Looper.myLooper();
            DrawRaderView drawRaderView = DrawRaderView.this;
            drawRaderView.mHandler = new Handler(drawRaderView.mLooper) { // from class: com.pudutech.lidar.preview.DrawRaderView.DrawThread.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what == 0) {
                        Canvas canvas = null;
                        try {
                            try {
                                if (DrawRaderView.this.mHolder != null) {
                                    canvas = DrawRaderView.this.mHolder.lockCanvas();
                                    DrawRaderView.this.drawUseSurface(canvas);
                                }
                                if (DrawRaderView.this.mHolder != null) {
                                    try {
                                        DrawRaderView.this.mHolder.unlockCanvasAndPost(canvas);
                                    } catch (Exception e) {
                                        Pdlog.m3273d("Lidar", "" + e.getMessage());
                                    }
                                }
                            } catch (Exception e2) {
                                Pdlog.m3273d("Lidar", "" + e2.getMessage());
                                if (DrawRaderView.this.mHolder != null) {
                                    try {
                                        DrawRaderView.this.mHolder.unlockCanvasAndPost(canvas);
                                    } catch (Exception e3) {
                                        Pdlog.m3273d("Lidar", "" + e3.getMessage());
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            if (DrawRaderView.this.mHolder != null) {
                                try {
                                    DrawRaderView.this.mHolder.unlockCanvasAndPost(canvas);
                                } catch (Exception e4) {
                                    Pdlog.m3273d("Lidar", "" + e4.getMessage());
                                }
                            }
                            throw th;
                        }
                    }
                }
            };
            Looper.loop();
        }

        public void exit() {
            DrawRaderView.this.mHandler = null;
            if (DrawRaderView.this.mLooper != null) {
                DrawRaderView.this.mLooper.quit();
                DrawRaderView.this.mLooper = null;
            }
        }
    }

    public void drawUseSurface(Canvas canvas) {
        Log.i("TAG", "scale:" + this.scale);
        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.save();
        canvas.translate(this.f5461dx, this.f5462dy);
        drawCoordinates(canvas);
        drawRegion(canvas);
        drawAllMeasureData(canvas);
        canvas.restore();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        this.nWidth = View.MeasureSpec.getSize(i);
        this.nHeight = View.MeasureSpec.getSize(i2);
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.nHeight = i2;
        this.nWidth = i;
    }

    public void drawRegion(Canvas canvas) {
        canvas.drawCircle(this.nWidth / 2.0f, this.nHeight / 2.0f, (float) (this.scale * 50.0d), this.mLinePaint);
        canvas.drawCircle(this.nWidth / 2.0f, this.nHeight / 2.0f, (float) (this.scale * 150.0d), this.mLinePaint);
        canvas.drawCircle(this.nWidth / 2.0f, this.nHeight / 2.0f, (float) (this.scale * 300.0d), this.mLinePaint);
    }

    public void drawCoordinates(Canvas canvas) {
        int i = this.nHeight;
        canvas.drawLine(0.0f, i / 2.0f, this.nWidth, i / 2.0f, this.mLinePaint);
        int i2 = this.nWidth;
        canvas.drawLine(i2 / 2.0f, 0.0f, i2 / 2.0f, this.nHeight, this.mLinePaint);
    }

    public void showData(List<LidarNode> list) {
        try {
            this.mlsDatas.clear();
            this.mlsDatas.addAll(list);
        } catch (Exception e) {
            Pdlog.m3273d("Lidar", "" + e.getMessage());
        }
        Handler handler = this.mHandler;
        if (handler == null || this.move) {
            return;
        }
        handler.sendEmptyMessage(0);
    }

    public void drawAllMeasureData(Canvas canvas) {
        if (this.mlsDatas == null) {
            return;
        }
        for (int i = 0; i < this.mlsDatas.size(); i++) {
            try {
                LidarNode lidarNode = this.mlsDatas.get(i);
                float f = (int) ((this.nWidth / 2.0f) + (lidarNode.ptX * this.scale));
                float f2 = (int) ((this.nHeight / 2.0f) - (lidarNode.ptY * this.scale));
                this.mLidarRect.set(f - (this.mRectRange / 2.0f), f2 - (this.mRectRange / 2.0f), f + (this.mRectRange / 2.0f), f2 + (this.mRectRange / 2.0f));
                canvas.drawRect(this.mLidarRect, this.mLinePaint);
                lidarNode.recycle();
            } catch (Exception e) {
                Pdlog.m3273d("Lidar", "" + e.getMessage());
                return;
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        if ((motionEvent.getAction() & 255) == 5 && 2 == pointerCount) {
            handlePointDown(motionEvent);
        } else if ((motionEvent.getAction() & 255) == 6 && 2 == pointerCount) {
            if (this.nLenStart2Touch == 0.0d) {
                return true;
            }
            handlePointUp(motionEvent);
            this.nLenStart2Touch = 0.0d;
        } else if ((motionEvent.getAction() & 255) != 2 || 2 != pointerCount) {
            this.nLenStart2Touch = 0.0d;
        } else {
            if (this.nLenStart2Touch == 0.0d) {
                return true;
            }
            this.nLenStart2Touch = handleMove(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.lastx = motionEvent.getX();
            this.lasty = motionEvent.getY();
            Log.i("Lidar", "down dx" + this.f5461dx + " dy:" + this.f5462dy);
        } else if (action == 1) {
            this.oldx = this.f5461dx;
            this.oly = this.f5462dy;
            this.move = false;
        } else if (action == 2) {
            this.f5461dx = (motionEvent.getX() - this.lastx) + this.oldx;
            this.f5462dy = (motionEvent.getY() - this.lasty) + this.oly;
            this.move = true;
            Log.i("Lidar", "move dx" + this.f5461dx + " dy:" + this.f5462dy);
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendEmptyMessage(0);
            }
        }
        return true;
    }

    private double handleMove(MotionEvent motionEvent) {
        double abs = Math.abs(((int) motionEvent.getX(0)) - ((int) motionEvent.getX(1)));
        double abs2 = Math.abs(((int) motionEvent.getY(0)) - ((int) motionEvent.getY(1)));
        double sqrt = Math.sqrt((abs * abs) + (abs2 * abs2));
        double d = this.nLenStart2Touch;
        if (sqrt > d) {
            this.scale += Math.abs(Math.sqrt(sqrt / d) - 1.0d);
            if (this.scale <= 0.0d) {
                this.scale = 1.0E-4d;
            }
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendEmptyMessage(0);
            }
        } else {
            this.scale -= Math.abs(Math.sqrt(sqrt / d) - 1.0d);
            if (this.scale <= 0.0d) {
                this.scale = 1.0E-4d;
            }
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.sendEmptyMessage(0);
            }
        }
        return sqrt;
    }

    private void handlePointUp(MotionEvent motionEvent) {
        double abs = Math.abs(((int) motionEvent.getX(0)) - ((int) motionEvent.getX(1)));
        double abs2 = Math.abs(((int) motionEvent.getY(0)) - ((int) motionEvent.getY(1)));
        double sqrt = Math.sqrt((abs * abs) + (abs2 * abs2));
        double d = this.nLenStart2Touch;
        if (sqrt > d) {
            this.scale += Math.abs(Math.sqrt(sqrt / d) - 1.0d);
            if (this.scale <= 0.0d) {
                this.scale = 1.0E-4d;
            }
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendEmptyMessage(0);
                return;
            }
            return;
        }
        this.scale -= Math.abs(Math.sqrt(sqrt / d) - 1.0d);
        if (this.scale <= 0.0d) {
            this.scale = 1.0E-4d;
        }
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.sendEmptyMessage(0);
        }
    }

    private void handlePointDown(MotionEvent motionEvent) {
        double abs = Math.abs(((int) motionEvent.getX(0)) - ((int) motionEvent.getX(1)));
        double abs2 = Math.abs(((int) motionEvent.getY(0)) - ((int) motionEvent.getY(1)));
        this.nLenStart2Touch = Math.sqrt((abs * abs) + (abs2 * abs2));
    }
}
