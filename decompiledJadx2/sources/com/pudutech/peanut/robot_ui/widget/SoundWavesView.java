package com.pudutech.peanut.robot_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.pudutech.peanut.robot_ui.C5508R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class SoundWavesView extends View {
    private boolean isRun;
    private Handler mHandler;
    private int mHeight;
    private Runnable mInvalidateRun;
    private int mLineWidth;
    private int mMax;
    private int mMini;
    private Paint mPaint;
    private RectF mRectF;
    private List<SoundLine> mSoundList;
    private int mSoundNum;
    private int mSpac;
    private int mWidth;

    private void order() {
    }

    private void wave() {
    }

    public SoundWavesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSoundNum = 5;
        this.isRun = false;
        this.mSoundList = new ArrayList();
        this.mHandler = new Handler();
        this.mInvalidateRun = new Runnable() { // from class: com.pudutech.peanut.robot_ui.widget.SoundWavesView.1
            @Override // java.lang.Runnable
            public void run() {
                SoundWavesView.this.postInvalidate();
            }
        };
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(getResources().getColor(C5508R.color.white));
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mRectF = new RectF();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (i <= 0 || i2 <= 0) {
            return;
        }
        initParam();
    }

    private void initParam() {
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        int i = this.mHeight;
        this.mMini = (int) (i * 0.3f);
        this.mMax = i;
        initLines();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.mSoundNum; i++) {
            SoundLine soundLine = this.mSoundList.get(i);
            this.mRectF.left = soundLine.left;
            this.mRectF.right = soundLine.right;
            this.mRectF.top = soundLine.top;
            this.mRectF.bottom = soundLine.bottom;
            RectF rectF = this.mRectF;
            int i2 = this.mLineWidth;
            canvas.drawRoundRect(rectF, i2 / 2, i2 / 2, this.mPaint);
        }
        if (this.isRun) {
            this.mHandler.postDelayed(this.mInvalidateRun, 30L);
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.isRun) {
            if (i == 0) {
                if (this.mWidth == 0) {
                    initParam();
                }
                List<SoundLine> list = this.mSoundList;
                if (list == null || list.size() <= 0) {
                    return;
                }
                Iterator<SoundLine> it = this.mSoundList.iterator();
                while (it.hasNext()) {
                    it.next().start();
                }
                return;
            }
            List<SoundLine> list2 = this.mSoundList;
            if (list2 == null || list2.size() <= 0) {
                return;
            }
            Iterator<SoundLine> it2 = this.mSoundList.iterator();
            while (it2.hasNext()) {
                it2.next().stop();
            }
        }
    }

    public void start() {
        if (this.isRun) {
            return;
        }
        this.isRun = true;
        Iterator<SoundLine> it = this.mSoundList.iterator();
        while (it.hasNext()) {
            it.next().start();
        }
        postInvalidate();
    }

    public void stop() {
        if (this.isRun) {
            this.isRun = false;
            Iterator<SoundLine> it = this.mSoundList.iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
        }
    }

    private void initLines() {
        int i = this.mWidth;
        int i2 = this.mSoundNum;
        this.mLineWidth = (int) ((i / i2) * 0.7f);
        this.mSpac = i / (i2 - 1);
        this.mSoundList.clear();
        chaos();
    }

    private void chaos() {
        for (int i = 0; i < this.mSoundNum; i++) {
            int i2 = this.mSpac;
            int i3 = this.mLineWidth;
            SoundLine soundLine = new SoundLine((i * i2) - (i3 / 2), (i2 * i) + (i3 / 2), 0, this.mHeight);
            soundLine.setMode(0);
            soundLine.setBorder(this.mMini, this.mMax);
            this.mSoundList.add(soundLine);
        }
    }
}
