package com.pudutech.mpmodule.p060ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.pudutech.mpmodule.C5441R;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.mpmodule.utils.DensityUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CTopBar extends RelativeLayout {
    private Drawable backIcon;
    private boolean hasBack;
    private CImageButton mBackBtn;
    private Context mContext;
    private TextView mTitleTextView;
    private String title;
    private int titleTextColor;
    private int titleTextSize;

    public CTopBar(Context context) {
        this(context, null);
    }

    public CTopBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CTopBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5441R.styleable.CTopBar, i, 0);
        this.title = obtainStyledAttributes.getString(C5441R.styleable.CTopBar_ctb_title_text);
        this.titleTextSize = obtainStyledAttributes.getDimensionPixelSize(C5441R.styleable.CTopBar_ctb_title_textSize, DensityUtil.sp2px(22));
        this.titleTextColor = obtainStyledAttributes.getColor(C5441R.styleable.CTopBar_ctb_title_textColor, ViewCompat.MEASURED_STATE_MASK);
        this.hasBack = obtainStyledAttributes.getBoolean(C5441R.styleable.CTopBar_ctb_has_back, true);
        this.backIcon = obtainStyledAttributes.getDrawable(C5441R.styleable.CTopBar_ctb_back_icon);
        obtainStyledAttributes.recycle();
        setBackgroundColor(getResources().getColor(C5441R.color.activity_white_background));
        init();
    }

    private void init() {
        setId(C5441R.id.c_topBar);
        createBack();
        createTitle();
    }

    private void createTitle() {
        this.mTitleTextView = new TextView(this.mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        CImageButton cImageButton = this.mBackBtn;
        if (cImageButton != null) {
            layoutParams.addRule(17, cImageButton.getId());
            layoutParams.leftMargin = 15;
        } else {
            layoutParams.addRule(20);
            layoutParams.leftMargin = 35;
        }
        this.mTitleTextView.setLayoutParams(layoutParams);
        this.mTitleTextView.setText(this.title);
        this.mTitleTextView.setTextSize(0, this.titleTextSize);
        this.mTitleTextView.setTextColor(this.titleTextColor);
        this.mTitleTextView.getPaint().setFakeBoldText(true);
        addView(this.mTitleTextView);
    }

    private void createBack() {
        Drawable drawable;
        if (this.hasBack) {
            this.mBackBtn = new CImageButton(this.mContext);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(15);
            layoutParams.addRule(20);
            layoutParams.leftMargin = 15;
            this.mBackBtn.setLayoutParams(layoutParams);
            this.mBackBtn.setPadding(DensityUtil.dp2px(10), DensityUtil.dp2px(14), DensityUtil.dp2px(10), DensityUtil.dp2px(14));
            if (Build.VERSION.SDK_INT >= 19 && (drawable = this.backIcon) != null) {
                drawable.setAutoMirrored(true);
            }
            CImageButton cImageButton = this.mBackBtn;
            Drawable drawable2 = this.backIcon;
            if (drawable2 == null) {
                drawable2 = ContextCompat.getDrawable(this.mContext, C5441R.drawable.module_icon_back_default);
            }
            cImageButton.setImageDrawable(drawable2);
            this.mBackBtn.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.ui.widget.-$$Lambda$CTopBar$5EwoeewCK4S4_LpjU4VwcDlMxb8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlayerModuleActivityManager.getInstance().finishActivity();
                }
            });
            this.mBackBtn.setId(11);
            addView(this.mBackBtn);
        }
    }

    public void setTitle(String str) {
        this.title = str;
        this.mTitleTextView.setText(str);
    }
}
