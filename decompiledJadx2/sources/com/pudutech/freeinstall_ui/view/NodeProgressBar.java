package com.pudutech.freeinstall_ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NodeProgressBar extends View {
    private int mBottomTxtColor;
    private boolean mBottomTxtEnable;
    private int mBottomTxtGap;
    private List<Location> mBottomTxtLocationList;
    private int mBottomTxtSize;
    private int mBottomTxtStyle;
    private int mBottomWarnTxtColor;
    private int mBottomWarnTxtStyle;
    private int mHeight;
    private int mLineWidth;
    private int mNodeCount;
    private int mNodeFailed;
    private Bitmap mNodeFailedBitmap;
    private int mNodeFinished;
    private Bitmap mNodeFinishedBitmap;
    private int mNodeHeight;
    private List<Node> mNodeList;
    private List<Location> mNodeLocationList;
    private float mNodeRatio;
    private int mNodeReached;
    private Bitmap mNodeReachedBitmap;
    private int mNodeUnreached;
    private Bitmap mNodeUnreachedBitmap;
    private int mNodeWidth;
    private Paint mPaintBottomTxt;
    private Paint mPaintBottomWarnTxt;
    private Paint mPaintNode;
    private Paint mPaintReachedLine;
    private Paint mPaintTopTxt;
    private Paint mPaintUnreachedLine;
    private int mReachedLineColor;
    private int mRegionWidth;
    private int mTopTxtColor;
    private boolean mTopTxtEnable;
    private int mTopTxtGap;
    private List<Location> mTopTxtLocationList;
    private int mTopTxtSize;
    private int mTopTxtStyle;
    private int mUnreachedLineColor;
    private int mWidth;

    /* loaded from: classes2.dex */
    public static class Node {
        public String bottomTxt;
        public int nodeAfterLineState;
        public int nodeState;
        public String topTxt;

        /* loaded from: classes2.dex */
        public interface LineState {
            public static final int REACHED = 0;
            public static final int UNREACHED = 1;
        }

        /* loaded from: classes2.dex */
        public interface NodeState {
            public static final int FAILED = 4;
            public static final int FINISHED = 3;
            public static final int REACHED = 2;
            public static final int UNREACHED = 1;
        }
    }

    /* loaded from: classes2.dex */
    public interface TxtStyle {
        public static final int BOLD = 1;
        public static final int COMMON = 0;
        public static final int ITALIC = 2;
    }

    public NodeProgressBar(Context context) {
        this(context, null);
    }

    public NodeProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NodeProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mNodeList = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5362R.styleable.NodeProgressBar);
        this.mNodeCount = obtainStyledAttributes.getInt(C5362R.styleable.NodeProgressBar_nodeCount, 0);
        this.mNodeWidth = obtainStyledAttributes.getDimensionPixelSize(C5362R.styleable.NodeProgressBar_nodeWidth, 0);
        this.mNodeHeight = obtainStyledAttributes.getDimensionPixelSize(C5362R.styleable.NodeProgressBar_nodeHeight, 0);
        this.mNodeUnreached = obtainStyledAttributes.getResourceId(C5362R.styleable.NodeProgressBar_nodeUnreached, C5362R.drawable.icon_un_select);
        this.mNodeReached = obtainStyledAttributes.getResourceId(C5362R.styleable.NodeProgressBar_nodeReached, C5362R.drawable.icon_select);
        this.mNodeFinished = obtainStyledAttributes.getResourceId(C5362R.styleable.NodeProgressBar_nodeFinished, C5362R.drawable.icon_select);
        this.mNodeFailed = obtainStyledAttributes.getResourceId(C5362R.styleable.NodeProgressBar_nodeFailed, C5362R.drawable.icon_un_select);
        this.mNodeRatio = obtainStyledAttributes.getFloat(C5362R.styleable.NodeProgressBar_nodeRatio, 1.0f);
        this.mTopTxtEnable = obtainStyledAttributes.getBoolean(C5362R.styleable.NodeProgressBar_topTxtEnable, false);
        this.mTopTxtSize = obtainStyledAttributes.getDimensionPixelSize(C5362R.styleable.NodeProgressBar_topTxtSize, 0);
        this.mTopTxtColor = obtainStyledAttributes.getColor(C5362R.styleable.NodeProgressBar_topTxtColor, 0);
        this.mTopTxtGap = obtainStyledAttributes.getDimensionPixelSize(C5362R.styleable.NodeProgressBar_topTxtGap, 0);
        this.mTopTxtStyle = obtainStyledAttributes.getInteger(C5362R.styleable.NodeProgressBar_topTxtStyle, 1);
        this.mBottomTxtEnable = obtainStyledAttributes.getBoolean(C5362R.styleable.NodeProgressBar_bottomTxtEnable, false);
        this.mBottomTxtSize = obtainStyledAttributes.getDimensionPixelSize(C5362R.styleable.NodeProgressBar_bottomTxtSize, 0);
        this.mBottomTxtColor = obtainStyledAttributes.getColor(C5362R.styleable.NodeProgressBar_bottomTxtColor, 0);
        this.mBottomTxtGap = obtainStyledAttributes.getDimensionPixelSize(C5362R.styleable.NodeProgressBar_bottomTxtGap, 0);
        this.mBottomTxtStyle = obtainStyledAttributes.getInteger(C5362R.styleable.NodeProgressBar_bottomTxtStyle, 0);
        this.mBottomWarnTxtColor = obtainStyledAttributes.getColor(C5362R.styleable.NodeProgressBar_bottomWarnTxtColor, 0);
        this.mBottomWarnTxtStyle = obtainStyledAttributes.getInteger(C5362R.styleable.NodeProgressBar_bottomWarnTxtStyle, 0);
        this.mLineWidth = obtainStyledAttributes.getDimensionPixelSize(C5362R.styleable.NodeProgressBar_lineWidth, 0);
        this.mReachedLineColor = obtainStyledAttributes.getColor(C5362R.styleable.NodeProgressBar_reachedLineColor, 0);
        this.mUnreachedLineColor = obtainStyledAttributes.getColor(C5362R.styleable.NodeProgressBar_unreachedLineColor, 0);
        this.mRegionWidth = obtainStyledAttributes.getDimensionPixelSize(C5362R.styleable.NodeProgressBar_regionWidth, 0);
        obtainStyledAttributes.recycle();
        configBitmaps(context);
        configPaints();
    }

    private void configPaints() {
        configTopTxtPaint();
        configBottomTxtPaint();
        configBottomWarnTxtPaint();
        configNodePaint();
        configUnreachedLinePaint();
        configReachedLinePaint();
    }

    private void configReachedLinePaint() {
        this.mPaintReachedLine = new Paint();
        this.mPaintReachedLine.setColor(this.mReachedLineColor);
        this.mPaintReachedLine.setStrokeWidth(this.mLineWidth);
        this.mPaintReachedLine.setStyle(Paint.Style.FILL);
        this.mPaintReachedLine.setAntiAlias(true);
    }

    private void configUnreachedLinePaint() {
        this.mPaintUnreachedLine = new Paint();
        this.mPaintUnreachedLine.setColor(this.mUnreachedLineColor);
        this.mPaintUnreachedLine.setStrokeWidth(this.mLineWidth);
        this.mPaintUnreachedLine.setStyle(Paint.Style.FILL);
        this.mPaintUnreachedLine.setAntiAlias(true);
    }

    private void configNodePaint() {
        this.mPaintNode = new Paint();
        this.mPaintNode.setAntiAlias(true);
    }

    private void configBottomWarnTxtPaint() {
        this.mPaintBottomWarnTxt = new Paint();
        this.mPaintBottomWarnTxt.setTextSize(this.mBottomTxtSize);
        this.mPaintBottomWarnTxt.setColor(this.mBottomWarnTxtColor);
        this.mPaintBottomWarnTxt.setTextAlign(Paint.Align.CENTER);
        this.mPaintBottomWarnTxt.setAntiAlias(true);
        int i = this.mBottomWarnTxtStyle;
        if (i == 0) {
            this.mPaintBottomWarnTxt.setTypeface(Typeface.DEFAULT);
        } else if (1 == i) {
            this.mPaintBottomWarnTxt.setTypeface(Typeface.defaultFromStyle(1));
        } else if (2 == i) {
            this.mPaintBottomWarnTxt.setTypeface(Typeface.defaultFromStyle(2));
        }
    }

    private void configBottomTxtPaint() {
        this.mPaintBottomTxt = new Paint();
        this.mPaintBottomTxt.setTextSize(this.mBottomTxtSize);
        this.mPaintBottomTxt.setColor(this.mBottomTxtColor);
        this.mPaintBottomTxt.setTextAlign(Paint.Align.CENTER);
        this.mPaintBottomTxt.setAntiAlias(true);
        int i = this.mBottomTxtStyle;
        if (i == 0) {
            this.mPaintBottomTxt.setTypeface(Typeface.DEFAULT);
        } else if (1 == i) {
            this.mPaintBottomTxt.setTypeface(Typeface.defaultFromStyle(1));
        } else if (2 == i) {
            this.mPaintBottomTxt.setTypeface(Typeface.defaultFromStyle(2));
        }
    }

    private void configTopTxtPaint() {
        this.mPaintTopTxt = new Paint();
        this.mPaintTopTxt.setTextSize(this.mTopTxtSize);
        this.mPaintTopTxt.setColor(this.mTopTxtColor);
        this.mPaintTopTxt.setTextAlign(Paint.Align.CENTER);
        this.mPaintTopTxt.setAntiAlias(true);
        int i = this.mTopTxtStyle;
        if (i == 0) {
            this.mPaintTopTxt.setTypeface(Typeface.DEFAULT);
        } else if (1 == i) {
            this.mPaintTopTxt.setTypeface(Typeface.defaultFromStyle(1));
        } else if (2 == i) {
            this.mPaintTopTxt.setTypeface(Typeface.defaultFromStyle(2));
        }
    }

    private void configBitmaps(Context context) {
        Resources resources = context.getResources();
        this.mNodeUnreachedBitmap = BitmapFactory.decodeResource(resources, C5362R.drawable.icon_un_select);
        this.mNodeReachedBitmap = BitmapFactory.decodeResource(resources, C5362R.drawable.icon_select);
        this.mNodeFailedBitmap = BitmapFactory.decodeResource(resources, C5362R.drawable.icon_un_select);
        this.mNodeFinishedBitmap = BitmapFactory.decodeResource(resources, C5362R.drawable.icon_select);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWidth = View.MeasureSpec.getSize(i);
        this.mHeight = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(this.mWidth, this.mHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        List<Node> list;
        int i;
        super.onDraw(canvas);
        if (this.mNodeCount <= 0 || (list = this.mNodeList) == null || list.isEmpty() || this.mNodeList.size() != this.mNodeCount) {
            return;
        }
        initLocationLists();
        measureLocations();
        if (this.mTopTxtEnable) {
            for (int i2 = 0; i2 < this.mNodeCount; i2++) {
                Node node = this.mNodeList.get(i2);
                if (!TextUtils.isEmpty(node.topTxt)) {
                    this.mPaintTopTxt.getFontMetrics();
                    canvas.drawText(node.topTxt, this.mTopTxtLocationList.get(i2).f5249x, (int) (this.mTopTxtLocationList.get(i2).f5250y + Math.abs(this.mPaintTopTxt.ascent() + (this.mPaintTopTxt.descent() / 2.0f))), this.mPaintTopTxt);
                }
            }
        }
        int i3 = 0;
        while (true) {
            i = 1;
            if (i3 >= this.mNodeCount) {
                break;
            }
            Node node2 = this.mNodeList.get(i3);
            if (i3 == this.mNodeCount - 1) {
                break;
            }
            int i4 = this.mNodeLocationList.get(i3).f5249x;
            int i5 = this.mNodeLocationList.get(i3).f5250y;
            int i6 = i3 + 1;
            int i7 = this.mNodeLocationList.get(i6).f5249x;
            int i8 = this.mNodeLocationList.get(i6).f5250y;
            if (1 == node2.nodeAfterLineState) {
                canvas.drawLine(i4, i5, i7, i8, this.mPaintUnreachedLine);
            } else if (node2.nodeAfterLineState == 0) {
                canvas.drawLine(i4, i5, i7, i8, this.mPaintReachedLine);
            } else {
                canvas.drawLine(i4, i5, i7, i8, this.mPaintUnreachedLine);
            }
            i3 = i6;
        }
        int i9 = 0;
        while (i9 < this.mNodeCount) {
            Node node3 = this.mNodeList.get(i9);
            int i10 = this.mNodeLocationList.get(i9).f5249x;
            int i11 = this.mNodeLocationList.get(i9).f5250y;
            if (i == node3.nodeState) {
                Rect rect = new Rect(0, 0, this.mNodeUnreachedBitmap.getWidth(), this.mNodeUnreachedBitmap.getHeight());
                float f = i10;
                float f2 = this.mNodeRatio;
                int i12 = this.mNodeWidth;
                float f3 = i11;
                int i13 = this.mNodeHeight;
                canvas.drawBitmap(this.mNodeUnreachedBitmap, rect, new RectF(f - ((i12 * f2) / 2.0f), f3 - ((i13 * f2) / 2.0f), f + ((i12 * f2) / 2.0f), f3 + ((f2 * i13) / 2.0f)), this.mPaintNode);
            } else if (2 == node3.nodeState) {
                Rect rect2 = new Rect(0, 0, this.mNodeUnreachedBitmap.getWidth(), this.mNodeUnreachedBitmap.getHeight());
                float f4 = i10;
                float f5 = this.mNodeRatio;
                int i14 = this.mNodeWidth;
                float f6 = i11;
                int i15 = this.mNodeHeight;
                canvas.drawBitmap(this.mNodeReachedBitmap, rect2, new RectF(f4 - ((i14 * f5) / 2.0f), f6 - ((i15 * f5) / 2.0f), f4 + ((i14 * f5) / 2.0f), f6 + ((f5 * i15) / 2.0f)), this.mPaintNode);
            } else if (4 == node3.nodeState) {
                Rect rect3 = new Rect(0, 0, this.mNodeUnreachedBitmap.getWidth(), this.mNodeUnreachedBitmap.getHeight());
                float f7 = i10;
                int i16 = this.mNodeWidth;
                canvas.drawBitmap(this.mNodeFailedBitmap, rect3, new RectF(f7 - ((i16 * 1.0f) / 2.0f), i11 - ((this.mNodeHeight * 1.0f) / 2.0f), f7 + ((i16 * 1.0f) / 2.0f), i11 + (r15 / 2)), this.mPaintNode);
            } else if (3 == node3.nodeState) {
                Rect rect4 = new Rect(0, 0, this.mNodeUnreachedBitmap.getWidth(), this.mNodeUnreachedBitmap.getHeight());
                float f8 = i10;
                int i17 = this.mNodeWidth;
                float f9 = i11;
                int i18 = this.mNodeHeight;
                canvas.drawBitmap(this.mNodeFinishedBitmap, rect4, new RectF(f8 - ((i17 * 1.0f) / 2.0f), f9 - ((i18 * 1.0f) / 2.0f), f8 + ((i17 * 1.0f) / 2.0f), f9 + ((i18 * 1.0f) / 2.0f)), this.mPaintNode);
            }
            i9++;
            i = 1;
        }
        if (this.mBottomTxtEnable) {
            for (int i19 = 0; i19 < this.mNodeCount; i19++) {
                Node node4 = this.mNodeList.get(i19);
                if (!TextUtils.isEmpty(node4.bottomTxt)) {
                    int i20 = this.mBottomTxtLocationList.get(i19).f5249x;
                    int abs = (int) (this.mBottomTxtLocationList.get(i19).f5250y + Math.abs(this.mPaintBottomTxt.ascent() + (this.mPaintBottomTxt.descent() / 2.0f)));
                    if (1 == node4.nodeState) {
                        canvas.drawText(node4.bottomTxt, i20, abs, this.mPaintBottomTxt);
                    } else if (2 == node4.nodeState) {
                        canvas.drawText(node4.bottomTxt, i20, abs, this.mPaintBottomWarnTxt);
                    } else if (4 == node4.nodeState) {
                        canvas.drawText(node4.bottomTxt, i20, abs, this.mPaintBottomTxt);
                    } else {
                        canvas.drawText(node4.bottomTxt, i20, abs, this.mPaintBottomWarnTxt);
                    }
                }
            }
        }
    }

    private void initLocationLists() {
        List<Location> list = this.mTopTxtLocationList;
        if (list != null) {
            list.clear();
        } else {
            this.mTopTxtLocationList = new ArrayList();
        }
        List<Location> list2 = this.mNodeLocationList;
        if (list2 != null) {
            list2.clear();
        } else {
            this.mNodeLocationList = new ArrayList();
        }
        List<Location> list3 = this.mBottomTxtLocationList;
        if (list3 != null) {
            list3.clear();
        } else {
            this.mBottomTxtLocationList = new ArrayList();
        }
    }

    private void measureLocations() {
        int i = this.mNodeCount;
        if (i == 1) {
            if (this.mTopTxtEnable) {
                Location location = new Location();
                location.f5249x = this.mWidth / 2;
                location.f5250y = this.mTopTxtSize / 2;
                this.mTopTxtLocationList.add(location);
            }
            if (this.mTopTxtEnable) {
                Location location2 = new Location();
                location2.f5249x = this.mWidth / 2;
                location2.f5250y = this.mTopTxtSize + this.mTopTxtGap + (this.mNodeHeight / 2);
                this.mNodeLocationList.add(location2);
            } else {
                Location location3 = new Location();
                location3.f5249x = this.mWidth / 2;
                location3.f5250y = this.mNodeHeight / 2;
                this.mNodeLocationList.add(location3);
            }
            if (this.mTopTxtEnable && this.mBottomTxtEnable) {
                Location location4 = new Location();
                location4.f5249x = this.mWidth / 2;
                location4.f5250y = this.mTopTxtSize + this.mTopTxtGap + this.mNodeHeight + this.mBottomTxtGap + (this.mBottomTxtSize / 2);
                this.mBottomTxtLocationList.add(location4);
                return;
            }
            if (this.mBottomTxtEnable) {
                Location location5 = new Location();
                location5.f5249x = this.mWidth / 2;
                location5.f5250y = this.mNodeHeight + this.mBottomTxtGap + (this.mBottomTxtSize / 2);
                this.mBottomTxtLocationList.add(location5);
                return;
            }
            return;
        }
        int i2 = (this.mWidth - (this.mRegionWidth * i)) / (i - 1);
        for (int i3 = 0; i3 < this.mNodeCount; i3++) {
            if (this.mTopTxtEnable) {
                Location location6 = new Location();
                int i4 = this.mRegionWidth;
                location6.f5249x = (i4 / 2) + (i3 * i2) + (i4 * i3);
                location6.f5250y = this.mTopTxtSize / 2;
                this.mTopTxtLocationList.add(location6);
            }
            if (this.mTopTxtEnable) {
                Location location7 = new Location();
                int i5 = this.mRegionWidth;
                location7.f5249x = (i5 / 2) + (i3 * i2) + (i5 * i3);
                location7.f5250y = this.mTopTxtSize + this.mTopTxtGap + (this.mNodeHeight / 2);
                this.mNodeLocationList.add(location7);
            } else {
                Location location8 = new Location();
                int i6 = this.mRegionWidth;
                location8.f5249x = (i6 / 2) + (i3 * i2) + (i6 * i3);
                location8.f5250y = this.mNodeHeight / 2;
                this.mNodeLocationList.add(location8);
            }
            if (this.mTopTxtEnable && this.mBottomTxtEnable) {
                Location location9 = new Location();
                int i7 = this.mRegionWidth;
                location9.f5249x = (i7 / 2) + (i3 * i2) + (i7 * i3);
                location9.f5250y = this.mTopTxtSize + this.mTopTxtGap + this.mNodeHeight + this.mBottomTxtGap + (this.mBottomTxtSize / 2);
                this.mBottomTxtLocationList.add(location9);
            } else if (this.mBottomTxtEnable) {
                Location location10 = new Location();
                int i8 = this.mRegionWidth;
                location10.f5249x = (i8 / 2) + (i3 * i2) + (i8 * i3);
                location10.f5250y = this.mNodeHeight + this.mBottomTxtGap + (this.mBottomTxtSize / 2);
                this.mBottomTxtLocationList.add(location10);
            }
        }
    }

    public void setTopTxtEnable(boolean z) {
        this.mTopTxtEnable = z;
        invalidate();
    }

    public void setBottomTxtEnable(boolean z) {
        this.mBottomTxtEnable = z;
        invalidate();
    }

    public void setNodeList(List<Node> list) {
        this.mNodeList = list;
        this.mNodeCount = list.size();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Location {

        /* renamed from: x */
        int f5249x;

        /* renamed from: y */
        int f5250y;

        private Location() {
        }
    }
}
