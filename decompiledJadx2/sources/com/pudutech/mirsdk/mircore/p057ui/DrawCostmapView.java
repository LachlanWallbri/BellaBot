package com.pudutech.mirsdk.mircore.p057ui;

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
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.coreparcel.MapData;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DrawCostmapView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0006\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 |2\u00020\u00012\u00020\u0002:\u0002|}B\u001b\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bB#\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010]\u001a\u00020^2\b\u0010_\u001a\u0004\u0018\u00010`J\u0010\u0010a\u001a\u00020^2\b\u0010_\u001a\u0004\u0018\u00010`J\u0010\u0010b\u001a\u00020^2\b\u0010_\u001a\u0004\u0018\u00010`J\u0018\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020\u00172\u0006\u0010f\u001a\u00020\u0017H\u0002J\u0006\u0010g\u001a\u00020^J\u0018\u0010h\u001a\u00020^2\u0006\u0010i\u001a\u00020\n2\u0006\u0010j\u001a\u00020\nH\u0014J(\u0010k\u001a\u00020^2\u0006\u0010l\u001a\u00020\n2\u0006\u0010m\u001a\u00020\n2\u0006\u0010n\u001a\u00020\n2\u0006\u0010o\u001a\u00020\nH\u0014J\u0010\u0010p\u001a\u00020\u00152\u0006\u0010q\u001a\u00020rH\u0016J\u000e\u0010s\u001a\u00020^2\u0006\u0010t\u001a\u00020\u000fJ(\u0010u\u001a\u00020^2\u0006\u0010v\u001a\u00020*2\u0006\u0010w\u001a\u00020\n2\u0006\u0010x\u001a\u00020\n2\u0006\u0010y\u001a\u00020\nH\u0016J\u0010\u0010z\u001a\u00020^2\u0006\u0010v\u001a\u00020*H\u0016J\u0010\u0010{\u001a\u00020^2\u0006\u0010v\u001a\u00020*H\u0016R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u001a\u0010\u001f\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR\u001a\u0010\"\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bR\u0014\u0010%\u001a\b\u0018\u00010&R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00107\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00102\"\u0004\b9\u00104R\u001c\u0010:\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00102\"\u0004\b<\u00104R\u001a\u0010=\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001a\u0010B\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010G\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010?\"\u0004\bI\u0010AR\u001a\u0010J\u001a\u00020KX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010P\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010?\"\u0004\bR\u0010AR\u001a\u0010S\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\u0019\"\u0004\bU\u0010\u001bR\u001a\u0010V\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010\u0019\"\u0004\bX\u0010\u001bR\u001a\u0010Y\u001a\u00020KX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010M\"\u0004\b[\u0010OR\u000e\u0010\\\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006~"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/DrawCostmapView;", "Landroid/view/SurfaceView;", "Landroid/view/SurfaceHolder$Callback;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "costmap", "Lcom/pudutech/mirsdk/mircore/coreparcel/MapData;", "getCostmap", "()Lcom/pudutech/mirsdk/mircore/coreparcel/MapData;", "setCostmap", "(Lcom/pudutech/mirsdk/mircore/coreparcel/MapData;)V", "drawing", "", "dx", "", "getDx", "()F", "setDx", "(F)V", "dy", "getDy", "setDy", "lastx", "getLastx", "setLastx", "lasty", "getLasty", "setLasty", "mDrawThread", "Lcom/pudutech/mirsdk/mircore/ui/DrawCostmapView$DrawThread;", "mHandler", "Landroid/os/Handler;", "mHolder", "Landroid/view/SurfaceHolder;", "getMHolder", "()Landroid/view/SurfaceHolder;", "setMHolder", "(Landroid/view/SurfaceHolder;)V", "mLinePaint", "Landroid/graphics/Paint;", "getMLinePaint", "()Landroid/graphics/Paint;", "setMLinePaint", "(Landroid/graphics/Paint;)V", "mLooper", "Landroid/os/Looper;", "mMyPaintText", "getMMyPaintText", "setMMyPaintText", "mPaintText", "getMPaintText", "setMPaintText", "mRectRange", "getMRectRange", "()I", "setMRectRange", "(I)V", "move", "getMove", "()Z", "setMove", "(Z)V", "nHeight", "getNHeight", "setNHeight", "nLenStart2Touch", "", "getNLenStart2Touch", "()D", "setNLenStart2Touch", "(D)V", "nWidth", "getNWidth", "setNWidth", "oldx", "getOldx", "setOldx", "oly", "getOly", "setOly", "scale", "getScale", "setScale", "syn_map", "DrawAllMeasureData", "", "canvas", "Landroid/graphics/Canvas;", "DrawRobotPose", "DrawUseSurface", "getPointRect", "Landroid/graphics/RectF;", "px", "py", "init", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "showData", MapElement.Key.MAP, "surfaceChanged", "holder", "format", "width", "height", "surfaceCreated", "surfaceDestroyed", "Companion", "DrawThread", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DrawCostmapView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int MSG_INVALIDATE = 0;
    private final String TAG;
    private HashMap _$_findViewCache;
    private MapData costmap;
    private boolean drawing;
    private float dx;
    private float dy;
    private float lastx;
    private float lasty;
    private DrawThread mDrawThread;
    private Handler mHandler;
    private SurfaceHolder mHolder;
    private Paint mLinePaint;
    private Looper mLooper;
    private Paint mMyPaintText;
    private Paint mPaintText;
    private int mRectRange;
    private boolean move;
    private int nHeight;
    private double nLenStart2Touch;
    private int nWidth;
    private float oldx;
    private float oly;
    private double scale;
    private boolean syn_map;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
    }

    public DrawCostmapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "DrawCostmap";
        this.scale = 0.3d;
        this.mRectRange = 2;
        this.dx = -3.0f;
        this.dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.costmap = new MapData(0.0d, 0.0d, 0.0d, 0, 0, null, 63, null);
        init();
    }

    public DrawCostmapView(Context context) {
        super(context);
        this.TAG = "DrawCostmap";
        this.scale = 0.3d;
        this.mRectRange = 2;
        this.dx = -3.0f;
        this.dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.costmap = new MapData(0.0d, 0.0d, 0.0d, 0, 0, null, 63, null);
        init();
    }

    public DrawCostmapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "DrawCostmap";
        this.scale = 0.3d;
        this.mRectRange = 2;
        this.dx = -3.0f;
        this.dy = -180.0f;
        this.oldx = -3.0f;
        this.oly = -180.0f;
        this.costmap = new MapData(0.0d, 0.0d, 0.0d, 0, 0, null, 63, null);
        init();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        if (this.mDrawThread == null) {
            this.mDrawThread = new DrawThread();
            DrawThread drawThread = this.mDrawThread;
            if (drawThread == null) {
                Intrinsics.throwNpe();
            }
            drawThread.start();
            Canvas lockCanvas = holder.lockCanvas();
            DrawUseSurface(lockCanvas);
            holder.unlockCanvasAndPost(lockCanvas);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        DrawThread drawThread = this.mDrawThread;
        if (drawThread != null) {
            if (drawThread == null) {
                Intrinsics.throwNpe();
            }
            drawThread.exit();
            this.mDrawThread = (DrawThread) null;
        }
    }

    public final void init() {
        this.mHolder = getHolder();
        SurfaceHolder surfaceHolder = this.mHolder;
        if (surfaceHolder != null) {
            surfaceHolder.addCallback(this);
        }
        this.mLinePaint = new Paint();
        Paint paint = this.mLinePaint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.setAntiAlias(true);
        Paint paint2 = this.mLinePaint;
        if (paint2 == null) {
            Intrinsics.throwNpe();
        }
        paint2.setColor(-16776961);
        Paint paint3 = this.mLinePaint;
        if (paint3 == null) {
            Intrinsics.throwNpe();
        }
        paint3.setStyle(Paint.Style.STROKE);
        Paint paint4 = this.mLinePaint;
        if (paint4 == null) {
            Intrinsics.throwNpe();
        }
        paint4.setStrokeWidth(5.0f);
        this.mPaintText = new Paint();
        Paint paint5 = this.mPaintText;
        if (paint5 == null) {
            Intrinsics.throwNpe();
        }
        paint5.setStrokeWidth(1.0f);
        Paint paint6 = this.mPaintText;
        if (paint6 == null) {
            Intrinsics.throwNpe();
        }
        paint6.setTextSize(20.0f);
        Paint paint7 = this.mPaintText;
        if (paint7 == null) {
            Intrinsics.throwNpe();
        }
        paint7.setColor(SupportMenu.CATEGORY_MASK);
        Paint paint8 = this.mPaintText;
        if (paint8 == null) {
            Intrinsics.throwNpe();
        }
        paint8.setTextAlign(Paint.Align.LEFT);
        this.mMyPaintText = new Paint();
        Paint paint9 = this.mMyPaintText;
        if (paint9 == null) {
            Intrinsics.throwNpe();
        }
        paint9.setStrokeWidth(1.0f);
        Paint paint10 = this.mMyPaintText;
        if (paint10 == null) {
            Intrinsics.throwNpe();
        }
        paint10.setTextSize(20.0f);
        Paint paint11 = this.mMyPaintText;
        if (paint11 == null) {
            Intrinsics.throwNpe();
        }
        paint11.setColor(-1);
        Paint paint12 = this.mMyPaintText;
        if (paint12 == null) {
            Intrinsics.throwNpe();
        }
        paint12.setTextAlign(Paint.Align.LEFT);
    }

    public final SurfaceHolder getMHolder() {
        return this.mHolder;
    }

    public final void setMHolder(SurfaceHolder surfaceHolder) {
        this.mHolder = surfaceHolder;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: DrawCostmapView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/DrawCostmapView$DrawThread;", "Ljava/lang/Thread;", "(Lcom/pudutech/mirsdk/mircore/ui/DrawCostmapView;)V", "exit", "", "run", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class DrawThread extends Thread {
        public DrawThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            DrawCostmapView.this.mLooper = Looper.myLooper();
            DrawCostmapView.this.mHandler = new Handler() { // from class: com.pudutech.mirsdk.mircore.ui.DrawCostmapView$DrawThread$run$1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkParameterIsNotNull(msg, "msg");
                    if (msg.what != 0) {
                        return;
                    }
                    Canvas canvas = (Canvas) null;
                    try {
                        try {
                            try {
                                if (DrawCostmapView.this.getMHolder() != null) {
                                    SurfaceHolder mHolder = DrawCostmapView.this.getMHolder();
                                    if (mHolder == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    canvas = mHolder.lockCanvas();
                                    DrawCostmapView.this.DrawUseSurface(canvas);
                                }
                                if (DrawCostmapView.this.getMHolder() != null) {
                                    SurfaceHolder mHolder2 = DrawCostmapView.this.getMHolder();
                                    if (mHolder2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    mHolder2.unlockCanvasAndPost(canvas);
                                }
                            } catch (Throwable th) {
                                if (DrawCostmapView.this.getMHolder() != null) {
                                    try {
                                        SurfaceHolder mHolder3 = DrawCostmapView.this.getMHolder();
                                        if (mHolder3 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        mHolder3.unlockCanvasAndPost(canvas);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            if (DrawCostmapView.this.getMHolder() != null) {
                                SurfaceHolder mHolder4 = DrawCostmapView.this.getMHolder();
                                if (mHolder4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                mHolder4.unlockCanvasAndPost(canvas);
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            };
            Looper.loop();
        }

        public final void exit() {
            DrawCostmapView.this.mHandler = (Handler) null;
            if (DrawCostmapView.this.mLooper != null) {
                Looper looper = DrawCostmapView.this.mLooper;
                if (looper == null) {
                    Intrinsics.throwNpe();
                }
                looper.quit();
                DrawCostmapView.this.mLooper = (Looper) null;
            }
        }
    }

    public final void DrawUseSurface(Canvas canvas) {
        Log.i("TAG", "scale:" + this.scale);
        if (canvas == null) {
            Intrinsics.throwNpe();
        }
        canvas.drawColor(-1);
        canvas.save();
        canvas.translate(this.dx, this.dy);
        DrawRobotPose(canvas);
        DrawAllMeasureData(canvas);
        canvas.restore();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.nWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        this.nHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.nHeight = h;
        this.nWidth = w;
    }

    public final int getNHeight() {
        return this.nHeight;
    }

    public final void setNHeight(int i) {
        this.nHeight = i;
    }

    public final int getNWidth() {
        return this.nWidth;
    }

    public final void setNWidth(int i) {
        this.nWidth = i;
    }

    public final Paint getMLinePaint() {
        return this.mLinePaint;
    }

    public final void setMLinePaint(Paint paint) {
        this.mLinePaint = paint;
    }

    public final Paint getMPaintText() {
        return this.mPaintText;
    }

    public final void setMPaintText(Paint paint) {
        this.mPaintText = paint;
    }

    public final Paint getMMyPaintText() {
        return this.mMyPaintText;
    }

    public final void setMMyPaintText(Paint paint) {
        this.mMyPaintText = paint;
    }

    public final double getScale() {
        return this.scale;
    }

    public final void setScale(double d) {
        this.scale = d;
    }

    public final int getMRectRange() {
        return this.mRectRange;
    }

    public final void setMRectRange(int i) {
        this.mRectRange = i;
    }

    public final float getDx() {
        return this.dx;
    }

    public final void setDx(float f) {
        this.dx = f;
    }

    public final float getDy() {
        return this.dy;
    }

    public final void setDy(float f) {
        this.dy = f;
    }

    public final float getOldx() {
        return this.oldx;
    }

    public final void setOldx(float f) {
        this.oldx = f;
    }

    public final float getOly() {
        return this.oly;
    }

    public final void setOly(float f) {
        this.oly = f;
    }

    public final float getLastx() {
        return this.lastx;
    }

    public final void setLastx(float f) {
        this.lastx = f;
    }

    public final float getLasty() {
        return this.lasty;
    }

    public final void setLasty(float f) {
        this.lasty = f;
    }

    public final boolean getMove() {
        return this.move;
    }

    public final void setMove(boolean z) {
        this.move = z;
    }

    public final void DrawRobotPose(Canvas canvas) {
        if (this.costmap.getSize_x() == 0 || this.costmap.getSize_y() == 0) {
            return;
        }
        Vector3d vector3d = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        vector3d.setX(MirCoreImpl.INSTANCE.getPosition$mircore_packRelease().getX());
        vector3d.setY(MirCoreImpl.INSTANCE.getPosition$mircore_packRelease().getY());
        vector3d.setZ(MirCoreImpl.INSTANCE.getPosition$mircore_packRelease().getZ());
        int i = this.nWidth - 200;
        int i2 = this.nHeight - 100;
        if (i > i2) {
            i = i2;
        }
        double d = i;
        double x = (((vector3d.getX() - this.costmap.getOrigin_x()) / this.costmap.getScale()) * (d / this.costmap.getSize_x())) + ((this.nWidth - i) / 2);
        double y = (((vector3d.getY() - this.costmap.getOrigin_y()) / this.costmap.getScale()) * (d / this.costmap.getSize_y())) + ((this.nHeight - i) / 2);
        if (canvas == null) {
            Intrinsics.throwNpe();
        }
        float f = (float) x;
        float f2 = (float) y;
        canvas.drawCircle(f, f2, 30 * ((float) this.scale), this.mLinePaint);
        double d2 = 18.0f;
        canvas.drawLine(f, f2, (float) (x + (Math.cos(vector3d.getZ()) * d2)), (float) (y + (Math.sin(vector3d.getZ()) * d2)), this.mLinePaint);
    }

    public final MapData getCostmap() {
        return this.costmap;
    }

    public final void setCostmap(MapData mapData) {
        Intrinsics.checkParameterIsNotNull(mapData, "<set-?>");
        this.costmap = mapData;
    }

    public final void showData(MapData map) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (Boolean.valueOf(this.drawing)) {
            if (this.drawing) {
                return;
            }
            Unit unit = Unit.INSTANCE;
            synchronized (Boolean.valueOf(this.syn_map)) {
                this.syn_map = true;
                Unit unit2 = Unit.INSTANCE;
            }
            this.costmap.setScale(map.getScale());
            this.costmap.setOrigin_x(map.getOrigin_x());
            this.costmap.setOrigin_y(map.getOrigin_y());
            this.costmap.setSize_x(map.getSize_x());
            this.costmap.setSize_y(map.getSize_y());
            MapData mapData = this.costmap;
            byte[] copyOf = Arrays.copyOf(map.getData(), map.getData().length);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "Arrays.copyOf(map.data, map.data.size)");
            mapData.setData(copyOf);
            synchronized (Boolean.valueOf(this.syn_map)) {
                this.syn_map = false;
                Unit unit3 = Unit.INSTANCE;
            }
            Handler handler = this.mHandler;
            if (handler == null || this.move) {
                return;
            }
            if (handler == null) {
                Intrinsics.throwNpe();
            }
            handler.sendEmptyMessage(0);
        }
    }

    public final void DrawAllMeasureData(Canvas canvas) {
        try {
            synchronized (Boolean.valueOf(this.syn_map)) {
                if (this.syn_map) {
                    return;
                }
                Unit unit = Unit.INSTANCE;
                synchronized (Boolean.valueOf(this.drawing)) {
                    this.drawing = true;
                    Unit unit2 = Unit.INSTANCE;
                }
                int size_x = this.costmap.getSize_x() * this.costmap.getSize_y();
                int i = this.nWidth - 200;
                int i2 = this.nHeight - 100;
                if (i > i2) {
                    i = i2;
                }
                for (int i3 = 0; i3 < size_x; i3++) {
                    RectF pointRect = getPointRect((((i3 % this.costmap.getSize_x()) * i) / this.costmap.getSize_x()) + ((this.nWidth - i) / 2), (((i3 / this.costmap.getSize_x()) * i) / this.costmap.getSize_y()) + ((this.nHeight - i) / 2));
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setStrokeWidth(3.0f);
                    MapData mapData = this.costmap;
                    if (mapData == null) {
                        Intrinsics.throwNpe();
                    }
                    if (UByte.m4528constructorimpl(mapData.getData()[i3]) != UByte.m4528constructorimpl((byte) 0)) {
                        MapData mapData2 = this.costmap;
                        if (mapData2 == null) {
                            Intrinsics.throwNpe();
                        }
                        int m4528constructorimpl = UByte.m4528constructorimpl(mapData2.getData()[i3]) & 255;
                        paint.setARGB(255, m4528constructorimpl, 255 - m4528constructorimpl, 0);
                    } else {
                        paint.setColor(-1);
                    }
                    if (canvas == null) {
                        Intrinsics.throwNpe();
                    }
                    canvas.drawRect(pointRect, paint);
                }
                synchronized (Boolean.valueOf(this.drawing)) {
                    this.drawing = false;
                    Unit unit3 = Unit.INSTANCE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final RectF getPointRect(float px, float py) {
        int i = this.mRectRange;
        return new RectF(px - (i / 2), py - (i / 2), px + (i / 2), py + (i / 2));
    }

    public final double getNLenStart2Touch() {
        return this.nLenStart2Touch;
    }

    public final void setNLenStart2Touch(double d) {
        this.nLenStart2Touch = d;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int pointerCount = event.getPointerCount();
        event.getAction();
        if ((event.getAction() & 255) == 5 && 2 == pointerCount) {
            double abs = Math.abs(((int) event.getX(0)) - ((int) event.getX(1)));
            double abs2 = Math.abs(((int) event.getY(0)) - ((int) event.getY(1)));
            this.nLenStart2Touch = Math.sqrt((abs * abs) + (abs2 * abs2));
        } else if ((event.getAction() & 255) == 6 && 2 == pointerCount) {
            int abs3 = Math.abs(((int) event.getX(0)) - ((int) event.getX(1)));
            int abs4 = Math.abs(((int) event.getY(0)) - ((int) event.getY(1)));
            int x = (((int) event.getX(0)) + ((int) event.getX(1))) / 2;
            int y = (((int) event.getY(0)) + ((int) event.getY(1))) / 2;
            double d = abs3;
            double d2 = abs4;
            double sqrt = Math.sqrt((d * d) + (d2 * d2));
            double d3 = this.nLenStart2Touch;
            if (d3 == 0.0d) {
                return true;
            }
            if (sqrt > d3) {
                this.scale += Math.abs(Math.sqrt(sqrt / d3) - 1);
                Handler handler = this.mHandler;
                if (handler != null) {
                    if (handler == null) {
                        Intrinsics.throwNpe();
                    }
                    handler.sendEmptyMessage(0);
                }
            } else {
                this.scale -= Math.abs(Math.sqrt(sqrt / d3) - 1);
                Handler handler2 = this.mHandler;
                if (handler2 != null) {
                    if (handler2 == null) {
                        Intrinsics.throwNpe();
                    }
                    handler2.sendEmptyMessage(0);
                }
            }
            this.nLenStart2Touch = 0.0d;
        } else if ((event.getAction() & 255) == 2 && 2 == pointerCount) {
            int abs5 = Math.abs(((int) event.getX(0)) - ((int) event.getX(1)));
            int abs6 = Math.abs(((int) event.getY(0)) - ((int) event.getY(1)));
            int x2 = (((int) event.getX(0)) + ((int) event.getX(1))) / 2;
            int y2 = (((int) event.getY(0)) + ((int) event.getY(1))) / 2;
            double d4 = abs5;
            double d5 = abs6;
            double sqrt2 = Math.sqrt((d4 * d4) + (d5 * d5));
            double d6 = this.nLenStart2Touch;
            if (d6 == 0.0d) {
                return true;
            }
            if (sqrt2 > d6) {
                this.scale += Math.abs(Math.sqrt(sqrt2 / d6) - 1);
                Handler handler3 = this.mHandler;
                if (handler3 != null) {
                    if (handler3 == null) {
                        Intrinsics.throwNpe();
                    }
                    handler3.sendEmptyMessage(0);
                }
            } else {
                this.scale -= Math.abs(Math.sqrt(sqrt2 / d6) - 1);
                Handler handler4 = this.mHandler;
                if (handler4 != null) {
                    if (handler4 == null) {
                        Intrinsics.throwNpe();
                    }
                    handler4.sendEmptyMessage(0);
                }
            }
            this.nLenStart2Touch = sqrt2;
        } else {
            this.nLenStart2Touch = 0.0d;
        }
        int action = event.getAction();
        if (action == 0) {
            this.lastx = event.getX();
            this.lasty = event.getY();
            Log.i(this.TAG, "down dx" + this.dx + " dy:" + this.dy);
        } else if (action == 1) {
            this.oldx = this.dx;
            this.oly = this.dy;
            this.move = false;
        } else if (action == 2) {
            this.dx = (event.getX() - this.lastx) + this.oldx;
            this.dy = (event.getY() - this.lasty) + this.oly;
            this.move = true;
            Log.i(this.TAG, "move dx" + this.dx + " dy:" + this.dy);
            Handler handler5 = this.mHandler;
            if (handler5 != null) {
                if (handler5 == null) {
                    Intrinsics.throwNpe();
                }
                handler5.sendEmptyMessage(0);
            }
        }
        return true;
    }
}
