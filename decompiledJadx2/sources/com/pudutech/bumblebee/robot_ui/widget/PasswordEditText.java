package com.pudutech.bumblebee.robot_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatEditText;
import com.pudutech.bumblebee.robot_ui.C4188R;
import java.lang.reflect.Field;

/* loaded from: classes4.dex */
public class PasswordEditText extends AppCompatEditText {
    private static final String TAG = "MNPasswordEditText";
    private int backgroundColor;
    private int borderColor;
    private float borderRadius;
    private int borderSelectedColor;
    private float borderWidth;
    private Bitmap coverBitmap;
    private int coverBitmapID;
    private float coverBitmapWidth;
    private int coverCirclrColor;
    private float coverCirclrRadius;
    private String coverText;
    private int editTextStyle;
    private GradientDrawable gradientDrawable;
    private int inputMode;
    private float itemMargin;
    private Context mContext;
    private Paint mPaintLine;
    private Paint mPaintText;
    private int maxLength;
    private OnTextChangeListener onTextChangeListener;
    private int textColor;

    /* loaded from: classes4.dex */
    public interface OnTextChangeListener {
        void onTextChange(String str, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$init$0(View view) {
        return true;
    }

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PasswordEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.gradientDrawable = new GradientDrawable();
        this.mContext = context;
        initAttrs(attributeSet, i);
        init();
    }

    private void initAttrs(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, C4188R.styleable.PasswordEditText, i, 0);
        this.backgroundColor = obtainStyledAttributes.getColor(C4188R.styleable.PasswordEditText_psw_background_color, Color.parseColor("#FFFFFF"));
        this.borderColor = obtainStyledAttributes.getColor(C4188R.styleable.PasswordEditText_psw_border_color, Color.parseColor("#FF0000"));
        this.borderSelectedColor = obtainStyledAttributes.getColor(C4188R.styleable.PasswordEditText_psw_border_selected_color, 0);
        this.textColor = obtainStyledAttributes.getColor(C4188R.styleable.PasswordEditText_psw_text_color, Color.parseColor("#000000"));
        this.borderRadius = obtainStyledAttributes.getDimension(C4188R.styleable.PasswordEditText_psw_border_radius, dip2px(6.0f));
        this.borderWidth = obtainStyledAttributes.getDimension(C4188R.styleable.PasswordEditText_psw_border_width, dip2px(1.0f));
        this.itemMargin = obtainStyledAttributes.getDimension(C4188R.styleable.PasswordEditText_psw_item_margin, dip2px(10.0f));
        this.inputMode = obtainStyledAttributes.getInt(C4188R.styleable.PasswordEditText_psw_mode, 1);
        this.editTextStyle = obtainStyledAttributes.getInt(C4188R.styleable.PasswordEditText_psw_style, 1);
        this.coverBitmapID = obtainStyledAttributes.getResourceId(C4188R.styleable.PasswordEditText_psw_cover_bitmap_id, -1);
        this.coverText = obtainStyledAttributes.getString(C4188R.styleable.PasswordEditText_psw_cover_text);
        if (TextUtils.isEmpty(this.coverText)) {
            this.coverText = "密";
        }
        this.coverCirclrColor = obtainStyledAttributes.getColor(C4188R.styleable.PasswordEditText_psw_cover_circle_color, Color.parseColor("#000000"));
        this.coverCirclrRadius = obtainStyledAttributes.getDimension(C4188R.styleable.PasswordEditText_psw_cover_circle_radius, 0.0f);
        this.coverBitmapWidth = obtainStyledAttributes.getDimension(C4188R.styleable.PasswordEditText_psw_cover_bitmap_width, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private void init() {
        this.maxLength = getMaxLength();
        setCursorVisible(false);
        setTextColor(0);
        setFocusableInTouchMode(true);
        setOnLongClickListener(new View.OnLongClickListener() { // from class: com.pudutech.bumblebee.robot_ui.widget.-$$Lambda$PasswordEditText$eS6BxBMhDTG4Y1j1_BMuHPvjg-M
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return PasswordEditText.lambda$init$0(view);
            }
        });
        this.mPaintText = new Paint(1);
        this.mPaintText.setStyle(Paint.Style.FILL);
        this.mPaintText.setColor(this.textColor);
        this.mPaintText.setTextSize(getTextSize());
        this.mPaintLine = new Paint(1);
        this.mPaintLine.setStyle(Paint.Style.STROKE);
        this.mPaintLine.setColor(this.borderColor);
        this.mPaintLine.setStrokeWidth(this.borderWidth);
        if (this.inputMode == 2) {
            if (this.coverBitmapID == -1) {
                throw new NullPointerException("遮盖图片为空");
            }
            this.coverBitmap = BitmapFactory.decodeResource(getContext().getResources(), this.coverBitmapID);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        float measuredHeight = getMeasuredHeight();
        float f = this.itemMargin;
        float f2 = (measuredWidth - ((r3 - 1) * f)) / this.maxLength;
        int i = this.editTextStyle;
        if (i == 1) {
            this.gradientDrawable.setStroke((int) this.borderWidth, this.borderColor);
            this.gradientDrawable.setCornerRadius(this.borderRadius);
            this.gradientDrawable.setColor(this.backgroundColor);
            if (Build.VERSION.SDK_INT >= 16) {
                setBackground(this.gradientDrawable);
            } else {
                setBackgroundDrawable(this.gradientDrawable);
            }
            f2 = measuredWidth / this.maxLength;
            for (int i2 = 1; i2 < this.maxLength; i2++) {
                float f3 = f2 * i2;
                canvas.drawLine(f3, 0.0f, f3, measuredHeight, this.mPaintLine);
            }
            f = 0.0f;
        } else if (i == 2) {
            this.gradientDrawable.setStroke((int) this.borderWidth, this.borderColor);
            this.gradientDrawable.setCornerRadius(this.borderRadius);
            this.gradientDrawable.setColor(this.backgroundColor);
            int i3 = (int) f2;
            int i4 = (int) measuredHeight;
            Bitmap drawableToBitmap = drawableToBitmap(this.gradientDrawable, i3, i4);
            Bitmap bitmap = null;
            int i5 = this.borderSelectedColor;
            if (i5 != 0) {
                this.gradientDrawable.setStroke((int) this.borderWidth, i5);
                bitmap = drawableToBitmap(this.gradientDrawable, i3, i4);
            }
            for (int i6 = 0; i6 < this.maxLength; i6++) {
                float f4 = i6;
                float f5 = (f2 * f4) + (f4 * f);
                if (bitmap == null) {
                    canvas.drawBitmap(drawableToBitmap, f5, 0.0f, this.mPaintLine);
                } else if (getText().length() == i6) {
                    canvas.drawBitmap(bitmap, f5, 0.0f, this.mPaintLine);
                } else {
                    canvas.drawBitmap(drawableToBitmap, f5, 0.0f, this.mPaintLine);
                }
            }
        } else if (i == 3) {
            for (int i7 = 0; i7 < this.maxLength; i7++) {
                if (this.borderSelectedColor != 0) {
                    if (getText().length() == i7) {
                        this.mPaintLine.setColor(this.borderSelectedColor);
                    } else {
                        this.mPaintLine.setColor(this.borderColor);
                    }
                } else {
                    this.mPaintLine.setColor(this.borderColor);
                }
                float f6 = i7;
                float f7 = (f2 * f6) + (this.itemMargin * f6);
                float f8 = measuredHeight - this.borderWidth;
                canvas.drawLine(f7, f8, f7 + f2, f8, this.mPaintLine);
            }
        }
        String obj = getText().toString();
        for (int i8 = 0; i8 < this.maxLength; i8++) {
            if (!TextUtils.isEmpty(obj) && i8 < obj.length()) {
                int i9 = this.inputMode;
                if (i9 == 1) {
                    float f9 = f2 * 0.5f * 0.5f;
                    float f10 = measuredHeight / 2.0f;
                    if (f9 > f10) {
                        f9 = measuredHeight * 0.5f * 0.5f;
                    }
                    float f11 = this.coverCirclrRadius;
                    if (f11 > 0.0f) {
                        f9 = f11;
                    }
                    float f12 = i8;
                    this.mPaintText.setColor(this.coverCirclrColor);
                    canvas.drawCircle((f2 / 2.0f) + (f2 * f12) + (f12 * f), f10, f9, this.mPaintText);
                } else if (i9 == 2) {
                    float f13 = 0.5f * f2;
                    float f14 = this.coverBitmapWidth;
                    if (f14 <= 0.0f) {
                        f14 = f13;
                    }
                    float f15 = i8;
                    float f16 = ((f2 - f14) / 2.0f) + (f2 * f15) + (f15 * f);
                    float f17 = (measuredHeight - f14) / 2.0f;
                    int i10 = (int) f14;
                    canvas.drawBitmap(Bitmap.createScaledBitmap(this.coverBitmap, i10, i10, true), f16, f17, this.mPaintText);
                } else if (i9 == 3) {
                    float fontWidth = getFontWidth(this.mPaintText, this.coverText);
                    float fontHeight = getFontHeight(this.mPaintText, this.coverText);
                    float f18 = i8;
                    this.mPaintText.setColor(this.textColor);
                    canvas.drawText(this.coverText, ((f2 - fontWidth) / 2.0f) + (f2 * f18) + (f18 * f), ((fontHeight + measuredHeight) / 2.0f) - 6.0f, this.mPaintText);
                } else {
                    String valueOf = String.valueOf(obj.charAt(i8));
                    float f19 = i8;
                    float fontWidth2 = ((f2 - getFontWidth(this.mPaintText, valueOf)) / 2.0f) + (f2 * f19) + (f19 * f);
                    float fontHeight2 = (getFontHeight(this.mPaintText, valueOf) + measuredHeight) / 2.0f;
                    this.mPaintText.setColor(this.textColor);
                    canvas.drawText(valueOf, fontWidth2, fontHeight2, this.mPaintText);
                }
            }
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i, i2);
        drawable.draw(canvas);
        return createBitmap;
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        invalidate();
        if (this.onTextChangeListener != null) {
            if (getText().toString().length() == getMaxLength()) {
                this.onTextChangeListener.onTextChange(getText().toString(), true);
            } else {
                this.onTextChangeListener.onTextChange(getText().toString(), false);
            }
        }
    }

    public float getFontWidth(Paint paint, String str) {
        paint.getTextBounds(str, 0, str.length(), new Rect());
        return r0.width();
    }

    public float getFontHeight(Paint paint, String str) {
        paint.getTextBounds(str, 0, str.length(), new Rect());
        return r0.height();
    }

    public int getMaxLength() {
        int i;
        Exception e;
        try {
            i = 0;
            for (InputFilter inputFilter : getFilters()) {
                try {
                    Class<?> cls = inputFilter.getClass();
                    if (cls.getName().equals("android.text.InputFilter$LengthFilter")) {
                        int i2 = i;
                        for (Field field : cls.getDeclaredFields()) {
                            try {
                                if (field.getName().equals("mMax")) {
                                    field.setAccessible(true);
                                    i2 = ((Integer) field.get(inputFilter)).intValue();
                                }
                            } catch (Exception e2) {
                                e = e2;
                                i = i2;
                                e.printStackTrace();
                                return i;
                            }
                        }
                        i = i2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        } catch (Exception e4) {
            i = 0;
            e = e4;
        }
        return i;
    }

    private int dip2px(float f) {
        return (int) ((f * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener) {
        this.onTextChangeListener = onTextChangeListener;
    }
}
