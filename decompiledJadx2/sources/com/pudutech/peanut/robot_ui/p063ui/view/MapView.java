package com.pudutech.peanut.robot_ui.p063ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdkwrap.lib.map.MapLine;
import com.pudutech.peanut.robot_ui.C5508R;
import com.warkiz.widget.SizeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ \u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0014H\u0002J\b\u0010(\u001a\u00020\fH\u0002J\b\u0010)\u001a\u00020\fH\u0002J\u0010\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\tH\u0002J\u0010\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\tH\u0002J\b\u0010.\u001a\u00020#H\u0002J\u0012\u0010/\u001a\u00020#2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J>\u00100\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015J\u001e\u00102\u001a\u00020#2\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/MapView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "PANDDING", "", "TAG", "", "kotlin.jvm.PlatformType", "linePaint", "Landroid/graphics/Paint;", "mapLines", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "Lkotlin/collections/ArrayList;", "mapPaint", "mapPath", "Landroid/graphics/Path;", "mapSelectLines", "maxX", "maxY", "minX", "minY", "offsetX", "offsetY", "ratioX", "ratioY", "drawLines", "", "paint", "canvas", "Landroid/graphics/Canvas;", "lineModel", "getRealH", "getRealW", "getTransformX", "mapX", "getTransformY", "mapY", "initView", "onDraw", "setMapData", MapElement.Key.MAP, "setSelectLinesData", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapView extends AppCompatImageView {
    private final float PANDDING;
    private final String TAG;
    private HashMap _$_findViewCache;
    private final Paint linePaint;
    private final ArrayList<MapLine> mapLines;
    private final Paint mapPaint;
    private final Path mapPath;
    private final ArrayList<MapLine> mapSelectLines;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;
    private float offsetX;
    private float offsetY;
    private float ratioX;
    private float ratioY;

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapView(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.maxX = 2;
        this.maxY = 2;
        this.minX = -2;
        this.minY = -2;
        this.mapLines = new ArrayList<>();
        this.mapSelectLines = new ArrayList<>();
        this.mapPaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.mapPath = new Path();
        this.ratioX = 1.0f;
        this.ratioY = 1.0f;
        this.PANDDING = 5.0f;
        initView();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.maxX = 2;
        this.maxY = 2;
        this.minX = -2;
        this.minY = -2;
        this.mapLines = new ArrayList<>();
        this.mapSelectLines = new ArrayList<>();
        this.mapPaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.mapPath = new Path();
        this.ratioX = 1.0f;
        this.ratioY = 1.0f;
        this.PANDDING = 5.0f;
        initView();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.maxX = 2;
        this.maxY = 2;
        this.minX = -2;
        this.minY = -2;
        this.mapLines = new ArrayList<>();
        this.mapSelectLines = new ArrayList<>();
        this.mapPaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.mapPath = new Path();
        this.ratioX = 1.0f;
        this.ratioY = 1.0f;
        this.PANDDING = 5.0f;
        initView();
    }

    private final void initView() {
        this.mapPaint.setColor(getContext().getColor(C5508R.color.cruise_map_line_color));
        this.mapPaint.setStrokeWidth(SizeUtils.dp2px(getContext(), 7.0f));
        this.mapPaint.setStrokeCap(Paint.Cap.ROUND);
        this.linePaint.setColor(getContext().getColor(C5508R.color.theme_main_color));
        this.linePaint.setStrokeWidth(SizeUtils.dp2px(getContext(), 7.0f));
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas == null) {
            return;
        }
        Iterator<T> it = this.mapLines.iterator();
        while (it.hasNext()) {
            drawLines(this.mapPaint, canvas, (MapLine) it.next());
        }
        Iterator<T> it2 = this.mapSelectLines.iterator();
        while (it2.hasNext()) {
            drawLines(this.linePaint, canvas, (MapLine) it2.next());
        }
    }

    private final void drawLines(Paint paint, Canvas canvas, MapLine lineModel) {
        canvas.drawLine(getTransformX(lineModel.getStartX() - this.minX) + this.offsetX, getTransformY(lineModel.getStartY() - this.minY) + this.offsetY, getTransformX(lineModel.getEndX() - this.minX) + this.offsetX, getTransformY(lineModel.getEndY() - this.minY) + this.offsetY, paint);
    }

    private final float getRealW() {
        return getWidth() - this.PANDDING;
    }

    private final float getRealH() {
        return getHeight() - this.PANDDING;
    }

    public final void setMapData(int maxX, int maxY, int minX, int minY, ArrayList<MapLine> map) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
        float f = maxX - minX;
        float f2 = maxY - minY;
        float f3 = f / f2;
        if (f3 < getRealW() / getRealH()) {
            this.ratioX = getRealH() / f2;
            this.ratioY = getRealH() / f2;
            this.offsetX = (getRealW() - (getRealH() * f3)) / 2;
            this.offsetY = this.PANDDING;
        } else {
            this.ratioX = getRealW() / f;
            this.ratioY = getRealW() / f;
            this.offsetX = this.PANDDING;
            this.offsetY = (getRealH() - (getRealW() / f3)) / 2;
        }
        Pdlog.m3273d(this.TAG, "setMapData : width = " + getWidth() + " , height = " + getHeight());
        this.mapLines.clear();
        this.mapLines.addAll(map);
        invalidate();
    }

    public final void setSelectLinesData(ArrayList<MapLine> map) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        this.mapSelectLines.clear();
        this.mapSelectLines.addAll(map);
        invalidate();
    }

    private final float getTransformX(int mapX) {
        return this.ratioX * mapX;
    }

    private final float getTransformY(int mapY) {
        return this.ratioY * mapY;
    }
}
