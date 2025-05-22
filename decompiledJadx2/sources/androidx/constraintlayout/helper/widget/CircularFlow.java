package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.C0205R;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.VirtualLayout;
import java.util.Arrays;

/* loaded from: classes.dex */
public class CircularFlow extends VirtualLayout {
    private static float DEFAULT_ANGLE = 0.0f;
    private static int DEFAULT_RADIUS = 0;
    private static final String TAG = "CircularFlow";
    private float[] mAngles;
    ConstraintLayout mContainer;
    private int mCountAngle;
    private int mCountRadius;
    private int[] mRadius;
    private String mReferenceAngles;
    private Float mReferenceDefaultAngle;
    private Integer mReferenceDefaultRadius;
    private String mReferenceRadius;
    int mViewCenter;

    public CircularFlow(Context context) {
        super(context);
    }

    public CircularFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularFlow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int[] getRadius() {
        return Arrays.copyOf(this.mRadius, this.mCountRadius);
    }

    public float[] getAngles() {
        return Arrays.copyOf(this.mAngles, this.mCountAngle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet attrs) {
        super.init(attrs);
        if (attrs != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, C0205R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == C0205R.styleable.ConstraintLayout_Layout_circularflow_viewCenter) {
                    this.mViewCenter = obtainStyledAttributes.getResourceId(index, 0);
                } else if (index == C0205R.styleable.ConstraintLayout_Layout_circularflow_angles) {
                    this.mReferenceAngles = obtainStyledAttributes.getString(index);
                    setAngles(this.mReferenceAngles);
                } else if (index == C0205R.styleable.ConstraintLayout_Layout_circularflow_radiusInDP) {
                    this.mReferenceRadius = obtainStyledAttributes.getString(index);
                    setRadius(this.mReferenceRadius);
                } else if (index == C0205R.styleable.ConstraintLayout_Layout_circularflow_defaultAngle) {
                    this.mReferenceDefaultAngle = Float.valueOf(obtainStyledAttributes.getFloat(index, DEFAULT_ANGLE));
                    setDefaultAngle(this.mReferenceDefaultAngle.floatValue());
                } else if (index == C0205R.styleable.ConstraintLayout_Layout_circularflow_defaultRadius) {
                    this.mReferenceDefaultRadius = Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(index, DEFAULT_RADIUS));
                    setDefaultRadius(this.mReferenceDefaultRadius.intValue());
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.mReferenceAngles;
        if (str != null) {
            this.mAngles = new float[1];
            setAngles(str);
        }
        String str2 = this.mReferenceRadius;
        if (str2 != null) {
            this.mRadius = new int[1];
            setRadius(str2);
        }
        Float f = this.mReferenceDefaultAngle;
        if (f != null) {
            setDefaultAngle(f.floatValue());
        }
        Integer num = this.mReferenceDefaultRadius;
        if (num != null) {
            setDefaultRadius(num.intValue());
        }
        anchorReferences();
    }

    private void anchorReferences() {
        this.mContainer = (ConstraintLayout) getParent();
        for (int i = 0; i < this.mCount; i++) {
            View viewById = this.mContainer.getViewById(this.mIds[i]);
            if (viewById != null) {
                int i2 = DEFAULT_RADIUS;
                float f = DEFAULT_ANGLE;
                int[] iArr = this.mRadius;
                if (iArr != null && i < iArr.length) {
                    i2 = iArr[i];
                } else {
                    Integer num = this.mReferenceDefaultRadius;
                    if (num != null && num.intValue() != -1) {
                        this.mCountRadius++;
                        if (this.mRadius == null) {
                            this.mRadius = new int[1];
                        }
                        this.mRadius = getRadius();
                        this.mRadius[this.mCountRadius - 1] = i2;
                    } else {
                        String valueOf = String.valueOf((String) this.mMap.get(Integer.valueOf(viewById.getId())));
                        Log.e(TAG, valueOf.length() != 0 ? "Added radius to view with id: ".concat(valueOf) : new String("Added radius to view with id: "));
                    }
                }
                float[] fArr = this.mAngles;
                if (fArr != null && i < fArr.length) {
                    f = fArr[i];
                } else {
                    Float f2 = this.mReferenceDefaultAngle;
                    if (f2 != null && f2.floatValue() != -1.0f) {
                        this.mCountAngle++;
                        if (this.mAngles == null) {
                            this.mAngles = new float[1];
                        }
                        this.mAngles = getAngles();
                        this.mAngles[this.mCountAngle - 1] = f;
                    } else {
                        String valueOf2 = String.valueOf((String) this.mMap.get(Integer.valueOf(viewById.getId())));
                        Log.e(TAG, valueOf2.length() != 0 ? "Added angle to view with id: ".concat(valueOf2) : new String("Added angle to view with id: "));
                    }
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewById.getLayoutParams();
                layoutParams.circleAngle = f;
                layoutParams.circleConstraint = this.mViewCenter;
                layoutParams.circleRadius = i2;
                viewById.setLayoutParams(layoutParams);
            }
        }
        applyLayoutFeatures();
    }

    public void addViewToCircularFlow(View view, int radius, float angle) {
        if (containsId(view.getId())) {
            return;
        }
        addView(view);
        this.mCountAngle++;
        this.mAngles = getAngles();
        this.mAngles[this.mCountAngle - 1] = angle;
        this.mCountRadius++;
        this.mRadius = getRadius();
        this.mRadius[this.mCountRadius - 1] = (int) (radius * this.myContext.getResources().getDisplayMetrics().density);
        anchorReferences();
    }

    public void updateRadius(View view, int radius) {
        if (!isUpdatable(view)) {
            int id = view.getId();
            StringBuilder sb = new StringBuilder(65);
            sb.append("It was not possible to update radius to view with id: ");
            sb.append(id);
            Log.e(TAG, sb.toString());
            return;
        }
        int indexFromId = indexFromId(view.getId());
        if (indexFromId > this.mRadius.length) {
            return;
        }
        this.mRadius = getRadius();
        this.mRadius[indexFromId] = (int) (radius * this.myContext.getResources().getDisplayMetrics().density);
        anchorReferences();
    }

    public void updateAngle(View view, float angle) {
        if (!isUpdatable(view)) {
            int id = view.getId();
            StringBuilder sb = new StringBuilder(64);
            sb.append("It was not possible to update angle to view with id: ");
            sb.append(id);
            Log.e(TAG, sb.toString());
            return;
        }
        int indexFromId = indexFromId(view.getId());
        if (indexFromId > this.mAngles.length) {
            return;
        }
        this.mAngles = getAngles();
        this.mAngles[indexFromId] = angle;
        anchorReferences();
    }

    public void updateReference(View view, int radius, float angle) {
        if (!isUpdatable(view)) {
            int id = view.getId();
            StringBuilder sb = new StringBuilder(75);
            sb.append("It was not possible to update radius and angle to view with id: ");
            sb.append(id);
            Log.e(TAG, sb.toString());
            return;
        }
        int indexFromId = indexFromId(view.getId());
        if (getAngles().length > indexFromId) {
            this.mAngles = getAngles();
            this.mAngles[indexFromId] = angle;
        }
        if (getRadius().length > indexFromId) {
            this.mRadius = getRadius();
            this.mRadius[indexFromId] = (int) (radius * this.myContext.getResources().getDisplayMetrics().density);
        }
        anchorReferences();
    }

    public void setDefaultAngle(float angle) {
        DEFAULT_ANGLE = angle;
    }

    public void setDefaultRadius(int radius) {
        DEFAULT_RADIUS = radius;
    }

    public int removeView(View view) {
        int removeView = super.removeView(view);
        if (removeView == -1) {
            return removeView;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.mContainer);
        constraintSet.clear(view.getId(), 8);
        constraintSet.applyTo(this.mContainer);
        float[] fArr = this.mAngles;
        if (removeView < fArr.length) {
            this.mAngles = removeAngle(fArr, removeView);
            this.mCountAngle--;
        }
        int[] iArr = this.mRadius;
        if (removeView < iArr.length) {
            this.mRadius = removeRadius(iArr, removeView);
            this.mCountRadius--;
        }
        anchorReferences();
        return removeView;
    }

    private float[] removeAngle(float[] angles, int index) {
        return (angles == null || index < 0 || index >= this.mCountAngle) ? angles : removeElementFromArray(angles, index);
    }

    private int[] removeRadius(int[] radius, int index) {
        return (radius == null || index < 0 || index >= this.mCountRadius) ? radius : removeElementFromArray(radius, index);
    }

    private void setAngles(String idList) {
        if (idList == null) {
            return;
        }
        int i = 0;
        this.mCountAngle = 0;
        while (true) {
            int indexOf = idList.indexOf(44, i);
            if (indexOf == -1) {
                addAngle(idList.substring(i).trim());
                return;
            } else {
                addAngle(idList.substring(i, indexOf).trim());
                i = indexOf + 1;
            }
        }
    }

    private void setRadius(String idList) {
        if (idList == null) {
            return;
        }
        int i = 0;
        this.mCountRadius = 0;
        while (true) {
            int indexOf = idList.indexOf(44, i);
            if (indexOf == -1) {
                addRadius(idList.substring(i).trim());
                return;
            } else {
                addRadius(idList.substring(i, indexOf).trim());
                i = indexOf + 1;
            }
        }
    }

    private void addAngle(String angleString) {
        float[] fArr;
        if (angleString == null || angleString.length() == 0 || this.myContext == null || (fArr = this.mAngles) == null) {
            return;
        }
        if (this.mCountAngle + 1 > fArr.length) {
            this.mAngles = Arrays.copyOf(fArr, fArr.length + 1);
        }
        this.mAngles[this.mCountAngle] = Integer.parseInt(angleString);
        this.mCountAngle++;
    }

    private void addRadius(String radiusString) {
        int[] iArr;
        if (radiusString == null || radiusString.length() == 0 || this.myContext == null || (iArr = this.mRadius) == null) {
            return;
        }
        if (this.mCountRadius + 1 > iArr.length) {
            this.mRadius = Arrays.copyOf(iArr, iArr.length + 1);
        }
        this.mRadius[this.mCountRadius] = (int) (Integer.parseInt(radiusString) * this.myContext.getResources().getDisplayMetrics().density);
        this.mCountRadius++;
    }

    public static int[] removeElementFromArray(int[] array, int index) {
        int[] iArr = new int[array.length - 1];
        int i = 0;
        for (int i2 = 0; i2 < array.length; i2++) {
            if (i2 != index) {
                iArr[i] = array[i2];
                i++;
            }
        }
        return iArr;
    }

    public static float[] removeElementFromArray(float[] array, int index) {
        float[] fArr = new float[array.length - 1];
        int i = 0;
        for (int i2 = 0; i2 < array.length; i2++) {
            if (i2 != index) {
                fArr[i] = array[i2];
                i++;
            }
        }
        return fArr;
    }

    public boolean isUpdatable(View view) {
        return containsId(view.getId()) && indexFromId(view.getId()) != -1;
    }
}
