package com.pudutech.lidar.test_activity;

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
import com.pudutech.lidar.LidarNode;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes.dex */
public class DrawRaderView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int MSG_INVALIDATE = 0;
    private final String TAG;

    /* renamed from: dx */
    float f5474dx;

    /* renamed from: dy */
    float f5475dy;
    float lastx;
    float lasty;
    private DrawThread mDrawThread;
    private Handler mHandler;
    SurfaceHolder mHolder;
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
        this.TAG = "Lidar";
        this.nHeight = 0;
        this.nWidth = 0;
        this.scale = 0.3d;
        this.mRectRange = 5;
        this.f5474dx = -3.0f;
        this.f5475dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.lastx = 0.0f;
        this.lasty = 0.0f;
        this.mlsDatas = new ArrayList();
        this.nLenStart2Touch = 0.0d;
        init();
    }

    public DrawRaderView(Context context) {
        super(context);
        this.TAG = "Lidar";
        this.nHeight = 0;
        this.nWidth = 0;
        this.scale = 0.3d;
        this.mRectRange = 5;
        this.f5474dx = -3.0f;
        this.f5475dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.lastx = 0.0f;
        this.lasty = 0.0f;
        this.mlsDatas = new ArrayList();
        this.nLenStart2Touch = 0.0d;
        init();
    }

    public DrawRaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "Lidar";
        this.nHeight = 0;
        this.nWidth = 0;
        this.scale = 0.3d;
        this.mRectRange = 5;
        this.f5474dx = -3.0f;
        this.f5475dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.lastx = 0.0f;
        this.lasty = 0.0f;
        this.mlsDatas = new ArrayList();
        this.nLenStart2Touch = 0.0d;
        init();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.mDrawThread == null) {
            DrawThread drawThread = new DrawThread();
            this.mDrawThread = drawThread;
            drawThread.start();
            Canvas lockCanvas = surfaceHolder.lockCanvas();
            DrawUseSurface(lockCanvas);
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
        SurfaceHolder holder = getHolder();
        this.mHolder = holder;
        holder.addCallback(this);
        Paint paint = new Paint();
        this.mLinePaint = paint;
        paint.setAntiAlias(true);
        this.mLinePaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mLinePaint.setStrokeWidth(1.0f);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes.dex */
    class DrawThread extends Thread {
        DrawThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            DrawRaderView.this.mLooper = Looper.myLooper();
            DrawRaderView.this.mHandler = new Handler() { // from class: com.pudutech.lidar.test_activity.DrawRaderView.DrawThread.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what != 0) {
                        return;
                    }
                    Canvas canvas = null;
                    try {
                        try {
                            try {
                                if (DrawRaderView.this.mHolder != null) {
                                    canvas = DrawRaderView.this.mHolder.lockCanvas();
                                    DrawRaderView.this.DrawUseSurface(canvas);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (DrawRaderView.this.mHolder == null) {
                                    return;
                                } else {
                                    DrawRaderView.this.mHolder.unlockCanvasAndPost(canvas);
                                }
                            }
                            if (DrawRaderView.this.mHolder != null) {
                                DrawRaderView.this.mHolder.unlockCanvasAndPost(canvas);
                            }
                        } catch (Throwable th) {
                            if (DrawRaderView.this.mHolder != null) {
                                try {
                                    DrawRaderView.this.mHolder.unlockCanvasAndPost(canvas);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
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

    public void DrawUseSurface(Canvas canvas) {
        Log.i("TAG", "scale:" + this.scale);
        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.save();
        canvas.translate(this.f5474dx, this.f5475dy);
        DrawCoordinates(canvas);
        DrawRegion(canvas);
        DrawAllMeasureData(canvas);
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

    public void DrawRegion(Canvas canvas) {
        canvas.drawCircle(this.nWidth / 2, this.nHeight / 2, (float) (this.scale * 50.0d), this.mLinePaint);
        canvas.drawCircle(this.nWidth / 2, this.nHeight / 2, (float) (this.scale * 150.0d), this.mLinePaint);
        canvas.drawCircle(this.nWidth / 2, this.nHeight / 2, (float) (this.scale * 300.0d), this.mLinePaint);
    }

    public void DrawCoordinates(Canvas canvas) {
        int i = this.nHeight;
        canvas.drawLine(0.0f, i / 2, this.nWidth, i / 2, this.mLinePaint);
        int i2 = this.nWidth;
        canvas.drawLine(i2 / 2, 0.0f, i2 / 2, this.nHeight, this.mLinePaint);
    }

    public void showData(List<LidarNode> list) {
        try {
            this.mlsDatas.clear();
            this.mlsDatas.addAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Handler handler = this.mHandler;
        if (handler == null || this.move) {
            return;
        }
        handler.sendEmptyMessage(0);
    }

    public void DrawAllMeasureData(Canvas canvas) {
        if (this.mlsDatas == null) {
            return;
        }
        for (int i = 0; i < this.mlsDatas.size(); i++) {
            try {
                canvas.drawRect(getPointRect(this.mlsDatas.get(i)), this.mLinePaint);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private RectF getPointRect(LidarNode lidarNode) {
        int i = (int) ((this.nWidth / 2) + (lidarNode.ptX * this.scale));
        int i2 = (int) ((this.nHeight / 2) - (lidarNode.ptY * this.scale));
        int i3 = this.mRectRange;
        return new RectF(i - (i3 / 2), i2 - (i3 / 2), i + (i3 / 2), i2 + (i3 / 2));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        motionEvent.getAction();
        if ((motionEvent.getAction() & 255) == 5 && 2 == pointerCount) {
            double abs = Math.abs(((int) motionEvent.getX(0)) - ((int) motionEvent.getX(1)));
            double abs2 = Math.abs(((int) motionEvent.getY(0)) - ((int) motionEvent.getY(1)));
            this.nLenStart2Touch = Math.sqrt((abs * abs) + (abs2 * abs2));
        } else if ((motionEvent.getAction() & 255) == 6 && 2 == pointerCount) {
            int abs3 = Math.abs(((int) motionEvent.getX(0)) - ((int) motionEvent.getX(1)));
            int abs4 = Math.abs(((int) motionEvent.getY(0)) - ((int) motionEvent.getY(1)));
            int x = (((int) motionEvent.getX(0)) + ((int) motionEvent.getX(1))) / 2;
            int y = (((int) motionEvent.getY(0)) + ((int) motionEvent.getY(1))) / 2;
            double d = abs3;
            double d2 = abs4;
            double sqrt = Math.sqrt((d * d) + (d2 * d2));
            double d3 = this.nLenStart2Touch;
            if (d3 == 0.0d) {
                return true;
            }
            if (sqrt > d3) {
                double abs5 = this.scale + Math.abs(Math.sqrt(sqrt / d3) - 1.0d);
                this.scale = abs5;
                if (abs5 <= 0.0d) {
                    this.scale = 1.0E-4d;
                }
                Handler handler = this.mHandler;
                if (handler != null) {
                    handler.sendEmptyMessage(0);
                }
            } else {
                double abs6 = this.scale - Math.abs(Math.sqrt(sqrt / d3) - 1.0d);
                this.scale = abs6;
                if (abs6 <= 0.0d) {
                    this.scale = 1.0E-4d;
                }
                Handler handler2 = this.mHandler;
                if (handler2 != null) {
                    handler2.sendEmptyMessage(0);
                }
            }
            this.nLenStart2Touch = 0.0d;
        } else if ((motionEvent.getAction() & 255) == 2 && 2 == pointerCount) {
            int abs7 = Math.abs(((int) motionEvent.getX(0)) - ((int) motionEvent.getX(1)));
            int abs8 = Math.abs(((int) motionEvent.getY(0)) - ((int) motionEvent.getY(1)));
            int x2 = (((int) motionEvent.getX(0)) + ((int) motionEvent.getX(1))) / 2;
            int y2 = (((int) motionEvent.getY(0)) + ((int) motionEvent.getY(1))) / 2;
            double d4 = abs7;
            double d5 = abs8;
            double sqrt2 = Math.sqrt((d4 * d4) + (d5 * d5));
            double d6 = this.nLenStart2Touch;
            if (d6 == 0.0d) {
                return true;
            }
            if (sqrt2 > d6) {
                double abs9 = this.scale + Math.abs(Math.sqrt(sqrt2 / d6) - 1.0d);
                this.scale = abs9;
                if (abs9 <= 0.0d) {
                    this.scale = 1.0E-4d;
                }
                Handler handler3 = this.mHandler;
                if (handler3 != null) {
                    handler3.sendEmptyMessage(0);
                }
            } else {
                double abs10 = this.scale - Math.abs(Math.sqrt(sqrt2 / d6) - 1.0d);
                this.scale = abs10;
                if (abs10 <= 0.0d) {
                    this.scale = 1.0E-4d;
                }
                Handler handler4 = this.mHandler;
                if (handler4 != null) {
                    handler4.sendEmptyMessage(0);
                }
            }
            this.nLenStart2Touch = sqrt2;
        } else {
            this.nLenStart2Touch = 0.0d;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.lastx = motionEvent.getX();
            this.lasty = motionEvent.getY();
            Log.i("Lidar", "down dx" + this.f5474dx + " dy:" + this.f5475dy);
            return true;
        }
        if (action == 1) {
            this.oldx = this.f5474dx;
            this.oly = this.f5475dy;
            this.move = false;
            return true;
        }
        if (action != 2) {
            return true;
        }
        this.f5474dx = (motionEvent.getX() - this.lastx) + this.oldx;
        this.f5475dy = (motionEvent.getY() - this.lasty) + this.oly;
        this.move = true;
        Log.i("Lidar", "move dx" + this.f5474dx + " dy:" + this.f5475dy);
        Handler handler5 = this.mHandler;
        if (handler5 == null) {
            return true;
        }
        handler5.sendEmptyMessage(0);
        return true;
    }
}
