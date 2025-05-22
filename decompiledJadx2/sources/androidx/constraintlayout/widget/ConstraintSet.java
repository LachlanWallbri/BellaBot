package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.C0205R;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.os.EnvironmentCompat;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ConstraintSet {
    private static final int ALPHA = 43;
    private static final int BARRIER_ALLOWS_GONE_WIDGETS = 74;
    private static final int BARRIER_DIRECTION = 72;
    private static final int BARRIER_TYPE = 1;
    public static final int BASELINE = 5;
    private static final int BASELINE_TO_BASELINE = 1;
    public static final int BOTTOM = 4;
    private static final int BOTTOM_MARGIN = 2;
    private static final int BOTTOM_TO_BOTTOM = 3;
    private static final int BOTTOM_TO_TOP = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    private static final int CHAIN_USE_RTL = 71;
    private static final int CIRCLE = 61;
    private static final int CIRCLE_ANGLE = 63;
    private static final int CIRCLE_RADIUS = 62;
    private static final int CONSTRAINT_REFERENCED_IDS = 73;
    private static final boolean DEBUG = false;
    private static final int DIMENSION_RATIO = 5;
    private static final int EDITOR_ABSOLUTE_X = 6;
    private static final int EDITOR_ABSOLUTE_Y = 7;
    private static final int ELEVATION = 44;
    public static final int END = 7;
    private static final int END_MARGIN = 8;
    private static final int END_TO_END = 9;
    private static final int END_TO_START = 10;
    public static final int GONE = 8;
    private static final int GONE_BOTTOM_MARGIN = 11;
    private static final int GONE_END_MARGIN = 12;
    private static final int GONE_LEFT_MARGIN = 13;
    private static final int GONE_RIGHT_MARGIN = 14;
    private static final int GONE_START_MARGIN = 15;
    private static final int GONE_TOP_MARGIN = 16;
    private static final int GUIDE_BEGIN = 17;
    private static final int GUIDE_END = 18;
    private static final int GUIDE_PERCENT = 19;
    private static final int HEIGHT_DEFAULT = 55;
    private static final int HEIGHT_MAX = 57;
    private static final int HEIGHT_MIN = 59;
    private static final int HEIGHT_PERCENT = 70;
    public static final int HORIZONTAL = 0;
    private static final int HORIZONTAL_BIAS = 20;
    public static final int HORIZONTAL_GUIDELINE = 0;
    private static final int HORIZONTAL_STYLE = 41;
    private static final int HORIZONTAL_WEIGHT = 39;
    public static final int INVISIBLE = 4;
    private static final int LAYOUT_HEIGHT = 21;
    private static final int LAYOUT_VISIBILITY = 22;
    private static final int LAYOUT_WIDTH = 23;
    public static final int LEFT = 1;
    private static final int LEFT_MARGIN = 24;
    private static final int LEFT_TO_LEFT = 25;
    private static final int LEFT_TO_RIGHT = 26;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    private static final int ORIENTATION = 27;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    private static final int RIGHT_MARGIN = 28;
    private static final int RIGHT_TO_LEFT = 29;
    private static final int RIGHT_TO_RIGHT = 30;
    private static final int ROTATION = 60;
    private static final int ROTATION_X = 45;
    private static final int ROTATION_Y = 46;
    private static final int SCALE_X = 47;
    private static final int SCALE_Y = 48;
    public static final int START = 6;
    private static final int START_MARGIN = 31;
    private static final int START_TO_END = 32;
    private static final int START_TO_START = 33;
    private static final String TAG = "ConstraintSet";
    public static final int TOP = 3;
    private static final int TOP_MARGIN = 34;
    private static final int TOP_TO_BOTTOM = 35;
    private static final int TOP_TO_TOP = 36;
    private static final int TRANSFORM_PIVOT_X = 49;
    private static final int TRANSFORM_PIVOT_Y = 50;
    private static final int TRANSLATION_X = 51;
    private static final int TRANSLATION_Y = 52;
    private static final int TRANSLATION_Z = 53;
    public static final int UNSET = -1;
    private static final int UNUSED = 75;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_BIAS = 37;
    public static final int VERTICAL_GUIDELINE = 1;
    private static final int VERTICAL_STYLE = 42;
    private static final int VERTICAL_WEIGHT = 40;
    private static final int VIEW_ID = 38;
    private static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    public static final int VISIBLE = 0;
    private static final int WIDTH_DEFAULT = 54;
    private static final int WIDTH_MAX = 56;
    private static final int WIDTH_MIN = 58;
    private static final int WIDTH_PERCENT = 69;
    public static final int WRAP_CONTENT = -2;
    private static SparseIntArray mapToConstant;
    private HashMap<Integer, Constraint> mConstraints = new HashMap<>();

    private String sideToString(int i) {
        switch (i) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public void setBarrierType(int i, int i2) {
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        mapToConstant = sparseIntArray;
        sparseIntArray.append(C0205R.styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_editor_absoluteX, 6);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_editor_absoluteY, 7);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintGuide_begin, 17);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintGuide_end, 18);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintGuide_percent, 19);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_orientation, 27);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_goneMarginLeft, 13);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_goneMarginTop, 16);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_goneMarginRight, 14);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_goneMarginBottom, 11);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_goneMarginStart, 15);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_goneMarginEnd, 12);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintVertical_weight, 40);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintVertical_bias, 37);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintLeft_creator, 75);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintTop_creator, 75);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintRight_creator, 75);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintBottom_creator, 75);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintBaseline_creator, 75);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_layout_marginLeft, 24);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_layout_marginRight, 28);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_layout_marginStart, 31);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_layout_marginEnd, 8);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_layout_marginTop, 34);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_layout_marginBottom, 2);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_layout_width, 23);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_layout_height, 21);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_visibility, 22);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_alpha, 43);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_elevation, 44);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_rotationX, 45);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_rotationY, 46);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_rotation, 60);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_scaleX, 47);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_scaleY, 48);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_transformPivotX, 49);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_transformPivotY, 50);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_translationX, 51);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_translationY, 52);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_translationZ, 53);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintWidth_default, 54);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintHeight_default, 55);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintWidth_max, 56);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintHeight_max, 57);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintWidth_min, 58);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintHeight_min, 59);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintCircle, 61);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintCircleRadius, 62);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintCircleAngle, 63);
        mapToConstant.append(C0205R.styleable.ConstraintSet_android_id, 38);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintWidth_percent, 69);
        mapToConstant.append(C0205R.styleable.ConstraintSet_layout_constraintHeight_percent, 70);
        mapToConstant.append(C0205R.styleable.ConstraintSet_chainUseRtl, 71);
        mapToConstant.append(C0205R.styleable.ConstraintSet_barrierDirection, 72);
        mapToConstant.append(C0205R.styleable.ConstraintSet_constraint_referenced_ids, 73);
        mapToConstant.append(C0205R.styleable.ConstraintSet_barrierAllowsGoneWidgets, 74);
    }

    public Constraint getParameters(int i) {
        return get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Constraint {
        static final int UNSET = -1;
        public float alpha;
        public boolean applyElevation;
        public int baselineToBaseline;
        public int bottomMargin;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public float elevation;
        public int endMargin;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public int heightDefault;
        public int heightMax;
        public int heightMin;
        public float heightPercent;
        public float horizontalBias;
        public int horizontalChainStyle;
        public float horizontalWeight;
        public int leftMargin;
        public int leftToLeft;
        public int leftToRight;
        public boolean mBarrierAllowsGoneWidgets;
        public int mBarrierDirection;
        public int mHeight;
        public int mHelperType;
        boolean mIsGuideline;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        int mViewId;
        public int mWidth;
        public int orientation;
        public int rightMargin;
        public int rightToLeft;
        public int rightToRight;
        public float rotation;
        public float rotationX;
        public float rotationY;
        public float scaleX;
        public float scaleY;
        public int startMargin;
        public int startToEnd;
        public int startToStart;
        public int topMargin;
        public int topToBottom;
        public int topToTop;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;
        public float verticalBias;
        public int verticalChainStyle;
        public float verticalWeight;
        public int visibility;
        public int widthDefault;
        public int widthMax;
        public int widthMin;
        public float widthPercent;

        private Constraint() {
            this.mIsGuideline = false;
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.leftMargin = -1;
            this.rightMargin = -1;
            this.topMargin = -1;
            this.bottomMargin = -1;
            this.endMargin = -1;
            this.startMargin = -1;
            this.visibility = 0;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneEndMargin = -1;
            this.goneStartMargin = -1;
            this.verticalWeight = 0.0f;
            this.horizontalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = Float.NaN;
            this.transformPivotY = Float.NaN;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.widthDefault = 0;
            this.heightDefault = 0;
            this.widthMax = -1;
            this.heightMax = -1;
            this.widthMin = -1;
            this.heightMin = -1;
            this.widthPercent = 1.0f;
            this.heightPercent = 1.0f;
            this.mBarrierAllowsGoneWidgets = false;
            this.mBarrierDirection = -1;
            this.mHelperType = -1;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Constraint m4262clone() {
            Constraint constraint = new Constraint();
            constraint.mIsGuideline = this.mIsGuideline;
            constraint.mWidth = this.mWidth;
            constraint.mHeight = this.mHeight;
            constraint.guideBegin = this.guideBegin;
            constraint.guideEnd = this.guideEnd;
            constraint.guidePercent = this.guidePercent;
            constraint.leftToLeft = this.leftToLeft;
            constraint.leftToRight = this.leftToRight;
            constraint.rightToLeft = this.rightToLeft;
            constraint.rightToRight = this.rightToRight;
            constraint.topToTop = this.topToTop;
            constraint.topToBottom = this.topToBottom;
            constraint.bottomToTop = this.bottomToTop;
            constraint.bottomToBottom = this.bottomToBottom;
            constraint.baselineToBaseline = this.baselineToBaseline;
            constraint.startToEnd = this.startToEnd;
            constraint.startToStart = this.startToStart;
            constraint.endToStart = this.endToStart;
            constraint.endToEnd = this.endToEnd;
            constraint.horizontalBias = this.horizontalBias;
            constraint.verticalBias = this.verticalBias;
            constraint.dimensionRatio = this.dimensionRatio;
            constraint.editorAbsoluteX = this.editorAbsoluteX;
            constraint.editorAbsoluteY = this.editorAbsoluteY;
            constraint.horizontalBias = this.horizontalBias;
            constraint.horizontalBias = this.horizontalBias;
            constraint.horizontalBias = this.horizontalBias;
            constraint.horizontalBias = this.horizontalBias;
            constraint.horizontalBias = this.horizontalBias;
            constraint.orientation = this.orientation;
            constraint.leftMargin = this.leftMargin;
            constraint.rightMargin = this.rightMargin;
            constraint.topMargin = this.topMargin;
            constraint.bottomMargin = this.bottomMargin;
            constraint.endMargin = this.endMargin;
            constraint.startMargin = this.startMargin;
            constraint.visibility = this.visibility;
            constraint.goneLeftMargin = this.goneLeftMargin;
            constraint.goneTopMargin = this.goneTopMargin;
            constraint.goneRightMargin = this.goneRightMargin;
            constraint.goneBottomMargin = this.goneBottomMargin;
            constraint.goneEndMargin = this.goneEndMargin;
            constraint.goneStartMargin = this.goneStartMargin;
            constraint.verticalWeight = this.verticalWeight;
            constraint.horizontalWeight = this.horizontalWeight;
            constraint.horizontalChainStyle = this.horizontalChainStyle;
            constraint.verticalChainStyle = this.verticalChainStyle;
            constraint.alpha = this.alpha;
            constraint.applyElevation = this.applyElevation;
            constraint.elevation = this.elevation;
            constraint.rotation = this.rotation;
            constraint.rotationX = this.rotationX;
            constraint.rotationY = this.rotationY;
            constraint.scaleX = this.scaleX;
            constraint.scaleY = this.scaleY;
            constraint.transformPivotX = this.transformPivotX;
            constraint.transformPivotY = this.transformPivotY;
            constraint.translationX = this.translationX;
            constraint.translationY = this.translationY;
            constraint.translationZ = this.translationZ;
            constraint.constrainedWidth = this.constrainedWidth;
            constraint.constrainedHeight = this.constrainedHeight;
            constraint.widthDefault = this.widthDefault;
            constraint.heightDefault = this.heightDefault;
            constraint.widthMax = this.widthMax;
            constraint.heightMax = this.heightMax;
            constraint.widthMin = this.widthMin;
            constraint.heightMin = this.heightMin;
            constraint.widthPercent = this.widthPercent;
            constraint.heightPercent = this.heightPercent;
            constraint.mBarrierDirection = this.mBarrierDirection;
            constraint.mHelperType = this.mHelperType;
            int[] iArr = this.mReferenceIds;
            if (iArr != null) {
                constraint.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            }
            constraint.circleConstraint = this.circleConstraint;
            constraint.circleRadius = this.circleRadius;
            constraint.circleAngle = this.circleAngle;
            constraint.mBarrierAllowsGoneWidgets = this.mBarrierAllowsGoneWidgets;
            return constraint;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillFromConstraints(ConstraintHelper constraintHelper, int i, Constraints.LayoutParams layoutParams) {
            fillFromConstraints(i, layoutParams);
            if (constraintHelper instanceof Barrier) {
                this.mHelperType = 1;
                Barrier barrier = (Barrier) constraintHelper;
                this.mBarrierDirection = barrier.getType();
                this.mReferenceIds = barrier.getReferencedIds();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillFromConstraints(int i, Constraints.LayoutParams layoutParams) {
            fillFrom(i, layoutParams);
            this.alpha = layoutParams.alpha;
            this.rotation = layoutParams.rotation;
            this.rotationX = layoutParams.rotationX;
            this.rotationY = layoutParams.rotationY;
            this.scaleX = layoutParams.scaleX;
            this.scaleY = layoutParams.scaleY;
            this.transformPivotX = layoutParams.transformPivotX;
            this.transformPivotY = layoutParams.transformPivotY;
            this.translationX = layoutParams.translationX;
            this.translationY = layoutParams.translationY;
            this.translationZ = layoutParams.translationZ;
            this.elevation = layoutParams.elevation;
            this.applyElevation = layoutParams.applyElevation;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillFrom(int i, ConstraintLayout.LayoutParams layoutParams) {
            this.mViewId = i;
            this.leftToLeft = layoutParams.leftToLeft;
            this.leftToRight = layoutParams.leftToRight;
            this.rightToLeft = layoutParams.rightToLeft;
            this.rightToRight = layoutParams.rightToRight;
            this.topToTop = layoutParams.topToTop;
            this.topToBottom = layoutParams.topToBottom;
            this.bottomToTop = layoutParams.bottomToTop;
            this.bottomToBottom = layoutParams.bottomToBottom;
            this.baselineToBaseline = layoutParams.baselineToBaseline;
            this.startToEnd = layoutParams.startToEnd;
            this.startToStart = layoutParams.startToStart;
            this.endToStart = layoutParams.endToStart;
            this.endToEnd = layoutParams.endToEnd;
            this.horizontalBias = layoutParams.horizontalBias;
            this.verticalBias = layoutParams.verticalBias;
            this.dimensionRatio = layoutParams.dimensionRatio;
            this.circleConstraint = layoutParams.circleConstraint;
            this.circleRadius = layoutParams.circleRadius;
            this.circleAngle = layoutParams.circleAngle;
            this.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.orientation = layoutParams.orientation;
            this.guidePercent = layoutParams.guidePercent;
            this.guideBegin = layoutParams.guideBegin;
            this.guideEnd = layoutParams.guideEnd;
            this.mWidth = layoutParams.width;
            this.mHeight = layoutParams.height;
            this.leftMargin = layoutParams.leftMargin;
            this.rightMargin = layoutParams.rightMargin;
            this.topMargin = layoutParams.topMargin;
            this.bottomMargin = layoutParams.bottomMargin;
            this.verticalWeight = layoutParams.verticalWeight;
            this.horizontalWeight = layoutParams.horizontalWeight;
            this.verticalChainStyle = layoutParams.verticalChainStyle;
            this.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.constrainedHeight = layoutParams.constrainedHeight;
            this.widthDefault = layoutParams.matchConstraintDefaultWidth;
            this.heightDefault = layoutParams.matchConstraintDefaultHeight;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.widthMax = layoutParams.matchConstraintMaxWidth;
            this.heightMax = layoutParams.matchConstraintMaxHeight;
            this.widthMin = layoutParams.matchConstraintMinWidth;
            this.heightMin = layoutParams.matchConstraintMinHeight;
            this.widthPercent = layoutParams.matchConstraintPercentWidth;
            this.heightPercent = layoutParams.matchConstraintPercentHeight;
            if (Build.VERSION.SDK_INT >= 17) {
                this.endMargin = layoutParams.getMarginEnd();
                this.startMargin = layoutParams.getMarginStart();
            }
        }

        public void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            layoutParams.leftToLeft = this.leftToLeft;
            layoutParams.leftToRight = this.leftToRight;
            layoutParams.rightToLeft = this.rightToLeft;
            layoutParams.rightToRight = this.rightToRight;
            layoutParams.topToTop = this.topToTop;
            layoutParams.topToBottom = this.topToBottom;
            layoutParams.bottomToTop = this.bottomToTop;
            layoutParams.bottomToBottom = this.bottomToBottom;
            layoutParams.baselineToBaseline = this.baselineToBaseline;
            layoutParams.startToEnd = this.startToEnd;
            layoutParams.startToStart = this.startToStart;
            layoutParams.endToStart = this.endToStart;
            layoutParams.endToEnd = this.endToEnd;
            layoutParams.leftMargin = this.leftMargin;
            layoutParams.rightMargin = this.rightMargin;
            layoutParams.topMargin = this.topMargin;
            layoutParams.bottomMargin = this.bottomMargin;
            layoutParams.goneStartMargin = this.goneStartMargin;
            layoutParams.goneEndMargin = this.goneEndMargin;
            layoutParams.horizontalBias = this.horizontalBias;
            layoutParams.verticalBias = this.verticalBias;
            layoutParams.circleConstraint = this.circleConstraint;
            layoutParams.circleRadius = this.circleRadius;
            layoutParams.circleAngle = this.circleAngle;
            layoutParams.dimensionRatio = this.dimensionRatio;
            layoutParams.editorAbsoluteX = this.editorAbsoluteX;
            layoutParams.editorAbsoluteY = this.editorAbsoluteY;
            layoutParams.verticalWeight = this.verticalWeight;
            layoutParams.horizontalWeight = this.horizontalWeight;
            layoutParams.verticalChainStyle = this.verticalChainStyle;
            layoutParams.horizontalChainStyle = this.horizontalChainStyle;
            layoutParams.constrainedWidth = this.constrainedWidth;
            layoutParams.constrainedHeight = this.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = this.widthDefault;
            layoutParams.matchConstraintDefaultHeight = this.heightDefault;
            layoutParams.matchConstraintMaxWidth = this.widthMax;
            layoutParams.matchConstraintMaxHeight = this.heightMax;
            layoutParams.matchConstraintMinWidth = this.widthMin;
            layoutParams.matchConstraintMinHeight = this.heightMin;
            layoutParams.matchConstraintPercentWidth = this.widthPercent;
            layoutParams.matchConstraintPercentHeight = this.heightPercent;
            layoutParams.orientation = this.orientation;
            layoutParams.guidePercent = this.guidePercent;
            layoutParams.guideBegin = this.guideBegin;
            layoutParams.guideEnd = this.guideEnd;
            layoutParams.width = this.mWidth;
            layoutParams.height = this.mHeight;
            if (Build.VERSION.SDK_INT >= 17) {
                layoutParams.setMarginStart(this.startMargin);
                layoutParams.setMarginEnd(this.endMargin);
            }
            layoutParams.validate();
        }

        /* loaded from: classes.dex */
        static class Delta {
            private static final int INITIAL_BOOLEAN = 4;
            private static final int INITIAL_FLOAT = 10;
            private static final int INITIAL_INT = 10;
            private static final int INITIAL_STRING = 5;
            int[] mTypeInt = new int[10];
            int[] mValueInt = new int[10];
            int mCountInt = 0;
            int[] mTypeFloat = new int[10];
            float[] mValueFloat = new float[10];
            int mCountFloat = 0;
            int[] mTypeString = new int[5];
            String[] mValueString = new String[5];
            int mCountString = 0;
            int[] mTypeBoolean = new int[4];
            boolean[] mValueBoolean = new boolean[4];
            int mCountBoolean = 0;

            Delta() {
            }

            void add(int type, int value) {
                int i = this.mCountInt;
                int[] iArr = this.mTypeInt;
                if (i >= iArr.length) {
                    this.mTypeInt = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.mValueInt;
                    this.mValueInt = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.mTypeInt;
                int i2 = this.mCountInt;
                iArr3[i2] = type;
                int[] iArr4 = this.mValueInt;
                this.mCountInt = i2 + 1;
                iArr4[i2] = value;
            }

            void add(int type, float value) {
                int i = this.mCountFloat;
                int[] iArr = this.mTypeFloat;
                if (i >= iArr.length) {
                    this.mTypeFloat = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.mValueFloat;
                    this.mValueFloat = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.mTypeFloat;
                int i2 = this.mCountFloat;
                iArr2[i2] = type;
                float[] fArr2 = this.mValueFloat;
                this.mCountFloat = i2 + 1;
                fArr2[i2] = value;
            }

            void add(int type, String value) {
                int i = this.mCountString;
                int[] iArr = this.mTypeString;
                if (i >= iArr.length) {
                    this.mTypeString = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.mValueString;
                    this.mValueString = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.mTypeString;
                int i2 = this.mCountString;
                iArr2[i2] = type;
                String[] strArr2 = this.mValueString;
                this.mCountString = i2 + 1;
                strArr2[i2] = value;
            }

            void add(int type, boolean value) {
                int i = this.mCountBoolean;
                int[] iArr = this.mTypeBoolean;
                if (i >= iArr.length) {
                    this.mTypeBoolean = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.mValueBoolean;
                    this.mValueBoolean = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.mTypeBoolean;
                int i2 = this.mCountBoolean;
                iArr2[i2] = type;
                boolean[] zArr2 = this.mValueBoolean;
                this.mCountBoolean = i2 + 1;
                zArr2[i2] = value;
            }

            void applyDelta(Constraint c) {
                for (int i = 0; i < this.mCountInt; i++) {
                    ConstraintSet.access$300(c, this.mTypeInt[i], this.mValueInt[i]);
                }
                for (int i2 = 0; i2 < this.mCountFloat; i2++) {
                    ConstraintSet.access$400(c, this.mTypeFloat[i2], this.mValueFloat[i2]);
                }
                for (int i3 = 0; i3 < this.mCountString; i3++) {
                    ConstraintSet.access$500(c, this.mTypeString[i3], this.mValueString[i3]);
                }
                for (int i4 = 0; i4 < this.mCountBoolean; i4++) {
                    ConstraintSet.access$600(c, this.mTypeBoolean[i4], this.mValueBoolean[i4]);
                }
            }

            void printDelta(String tag) {
                Log.v(tag, "int");
                for (int i = 0; i < this.mCountInt; i++) {
                    int i2 = this.mTypeInt[i];
                    int i3 = this.mValueInt[i];
                    StringBuilder sb = new StringBuilder(25);
                    sb.append(i2);
                    sb.append(" = ");
                    sb.append(i3);
                    Log.v(tag, sb.toString());
                }
                Log.v(tag, "float");
                for (int i4 = 0; i4 < this.mCountFloat; i4++) {
                    int i5 = this.mTypeFloat[i4];
                    float f = this.mValueFloat[i4];
                    StringBuilder sb2 = new StringBuilder(29);
                    sb2.append(i5);
                    sb2.append(" = ");
                    sb2.append(f);
                    Log.v(tag, sb2.toString());
                }
                Log.v(tag, "strings");
                for (int i6 = 0; i6 < this.mCountString; i6++) {
                    int i7 = this.mTypeString[i6];
                    String str = this.mValueString[i6];
                    StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 14);
                    sb3.append(i7);
                    sb3.append(" = ");
                    sb3.append(str);
                    Log.v(tag, sb3.toString());
                }
                Log.v(tag, "boolean");
                for (int i8 = 0; i8 < this.mCountBoolean; i8++) {
                    int i9 = this.mTypeBoolean[i8];
                    boolean z = this.mValueBoolean[i8];
                    StringBuilder sb4 = new StringBuilder(19);
                    sb4.append(i9);
                    sb4.append(" = ");
                    sb4.append(z);
                    Log.v(tag, sb4.toString());
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Layout {
        private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
        private static final int BARRIER_DIRECTION = 72;
        private static final int BARRIER_MARGIN = 73;
        private static final int BASELINE_TO_BASELINE = 1;
        private static final int BOTTOM_MARGIN = 2;
        private static final int BOTTOM_TO_BOTTOM = 3;
        private static final int BOTTOM_TO_TOP = 4;
        private static final int CHAIN_USE_RTL = 71;
        private static final int CIRCLE = 61;
        private static final int CIRCLE_ANGLE = 63;
        private static final int CIRCLE_RADIUS = 62;
        private static final int CONSTRAINT_REFERENCED_IDS = 74;
        private static final int DIMENSION_RATIO = 5;
        private static final int EDITOR_ABSOLUTE_X = 6;
        private static final int EDITOR_ABSOLUTE_Y = 7;
        private static final int END_MARGIN = 8;
        private static final int END_TO_END = 9;
        private static final int END_TO_START = 10;
        private static final int GONE_BOTTOM_MARGIN = 11;
        private static final int GONE_END_MARGIN = 12;
        private static final int GONE_LEFT_MARGIN = 13;
        private static final int GONE_RIGHT_MARGIN = 14;
        private static final int GONE_START_MARGIN = 15;
        private static final int GONE_TOP_MARGIN = 16;
        private static final int GUIDE_BEGIN = 17;
        private static final int GUIDE_END = 18;
        private static final int GUIDE_PERCENT = 19;
        private static final int HEIGHT_PERCENT = 70;
        private static final int HORIZONTAL_BIAS = 20;
        private static final int HORIZONTAL_STYLE = 39;
        private static final int HORIZONTAL_WEIGHT = 37;
        private static final int LAYOUT_HEIGHT = 21;
        private static final int LAYOUT_WIDTH = 22;
        private static final int LEFT_MARGIN = 23;
        private static final int LEFT_TO_LEFT = 24;
        private static final int LEFT_TO_RIGHT = 25;
        private static final int ORIENTATION = 26;
        private static final int RIGHT_MARGIN = 27;
        private static final int RIGHT_TO_LEFT = 28;
        private static final int RIGHT_TO_RIGHT = 29;
        private static final int START_MARGIN = 30;
        private static final int START_TO_END = 31;
        private static final int START_TO_START = 32;
        private static final int TOP_MARGIN = 33;
        private static final int TOP_TO_BOTTOM = 34;
        private static final int TOP_TO_TOP = 35;
        public static final int UNSET = -1;
        private static final int UNUSED = 76;
        private static final int VERTICAL_BIAS = 36;
        private static final int VERTICAL_STYLE = 40;
        private static final int VERTICAL_WEIGHT = 38;
        private static final int WIDTH_PERCENT = 69;
        private static SparseIntArray mapToConstant;
        public String mConstraintTag;
        public int mHeight;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public boolean mIsGuideline = false;
        public boolean mApply = false;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int topToTop = -1;
        public int topToBottom = -1;
        public int bottomToTop = -1;
        public int bottomToBottom = -1;
        public int baselineToBaseline = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int endToStart = -1;
        public int endToEnd = -1;
        public float horizontalBias = 0.5f;
        public float verticalBias = 0.5f;
        public String dimensionRatio = null;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public float circleAngle = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int orientation = -1;
        public int leftMargin = -1;
        public int rightMargin = -1;
        public int topMargin = -1;
        public int bottomMargin = -1;
        public int endMargin = -1;
        public int startMargin = -1;
        public int goneLeftMargin = -1;
        public int goneTopMargin = -1;
        public int goneRightMargin = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneStartMargin = -1;
        public float verticalWeight = -1.0f;
        public float horizontalWeight = -1.0f;
        public int horizontalChainStyle = 0;
        public int verticalChainStyle = 0;
        public int widthDefault = 0;
        public int heightDefault = 0;
        public int widthMax = -1;
        public int heightMax = -1;
        public int widthMin = -1;
        public int heightMin = -1;
        public float widthPercent = 1.0f;
        public float heightPercent = 1.0f;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public int mHelperType = -1;
        public boolean constrainedWidth = false;
        public boolean constrainedHeight = false;
        public boolean mBarrierAllowsGoneWidgets = true;

        public void copyFrom(Layout layout) {
            this.mIsGuideline = layout.mIsGuideline;
            this.mWidth = layout.mWidth;
            this.mApply = layout.mApply;
            this.mHeight = layout.mHeight;
            this.guideBegin = layout.guideBegin;
            this.guideEnd = layout.guideEnd;
            this.guidePercent = layout.guidePercent;
            this.leftToLeft = layout.leftToLeft;
            this.leftToRight = layout.leftToRight;
            this.rightToLeft = layout.rightToLeft;
            this.rightToRight = layout.rightToRight;
            this.topToTop = layout.topToTop;
            this.topToBottom = layout.topToBottom;
            this.bottomToTop = layout.bottomToTop;
            this.bottomToBottom = layout.bottomToBottom;
            this.baselineToBaseline = layout.baselineToBaseline;
            this.startToEnd = layout.startToEnd;
            this.startToStart = layout.startToStart;
            this.endToStart = layout.endToStart;
            this.endToEnd = layout.endToEnd;
            this.horizontalBias = layout.horizontalBias;
            this.verticalBias = layout.verticalBias;
            this.dimensionRatio = layout.dimensionRatio;
            this.circleConstraint = layout.circleConstraint;
            this.circleRadius = layout.circleRadius;
            this.circleAngle = layout.circleAngle;
            this.editorAbsoluteX = layout.editorAbsoluteX;
            this.editorAbsoluteY = layout.editorAbsoluteY;
            this.orientation = layout.orientation;
            this.leftMargin = layout.leftMargin;
            this.rightMargin = layout.rightMargin;
            this.topMargin = layout.topMargin;
            this.bottomMargin = layout.bottomMargin;
            this.endMargin = layout.endMargin;
            this.startMargin = layout.startMargin;
            this.goneLeftMargin = layout.goneLeftMargin;
            this.goneTopMargin = layout.goneTopMargin;
            this.goneRightMargin = layout.goneRightMargin;
            this.goneBottomMargin = layout.goneBottomMargin;
            this.goneEndMargin = layout.goneEndMargin;
            this.goneStartMargin = layout.goneStartMargin;
            this.verticalWeight = layout.verticalWeight;
            this.horizontalWeight = layout.horizontalWeight;
            this.horizontalChainStyle = layout.horizontalChainStyle;
            this.verticalChainStyle = layout.verticalChainStyle;
            this.widthDefault = layout.widthDefault;
            this.heightDefault = layout.heightDefault;
            this.widthMax = layout.widthMax;
            this.heightMax = layout.heightMax;
            this.widthMin = layout.widthMin;
            this.heightMin = layout.heightMin;
            this.widthPercent = layout.widthPercent;
            this.heightPercent = layout.heightPercent;
            this.mBarrierDirection = layout.mBarrierDirection;
            this.mBarrierMargin = layout.mBarrierMargin;
            this.mHelperType = layout.mHelperType;
            this.mConstraintTag = layout.mConstraintTag;
            int[] iArr = layout.mReferenceIds;
            if (iArr != null) {
                this.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.mReferenceIds = null;
            }
            this.mReferenceIdString = layout.mReferenceIdString;
            this.constrainedWidth = layout.constrainedWidth;
            this.constrainedHeight = layout.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = layout.mBarrierAllowsGoneWidgets;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(C0205R.styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintLeft_toRightOf, 25);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintRight_toLeftOf, 28);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintRight_toRightOf, 29);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintTop_toTopOf, 35);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintTop_toBottomOf, 34);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintBottom_toTopOf, 4);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            mapToConstant.append(C0205R.styleable.Layout_layout_editor_absoluteX, 6);
            mapToConstant.append(C0205R.styleable.Layout_layout_editor_absoluteY, 7);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintGuide_begin, 17);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintGuide_end, 18);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintGuide_percent, 19);
            mapToConstant.append(C0205R.styleable.Layout_android_orientation, 26);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintStart_toEndOf, 31);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintStart_toStartOf, 32);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintEnd_toStartOf, 10);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintEnd_toEndOf, 9);
            mapToConstant.append(C0205R.styleable.Layout_layout_goneMarginLeft, 13);
            mapToConstant.append(C0205R.styleable.Layout_layout_goneMarginTop, 16);
            mapToConstant.append(C0205R.styleable.Layout_layout_goneMarginRight, 14);
            mapToConstant.append(C0205R.styleable.Layout_layout_goneMarginBottom, 11);
            mapToConstant.append(C0205R.styleable.Layout_layout_goneMarginStart, 15);
            mapToConstant.append(C0205R.styleable.Layout_layout_goneMarginEnd, 12);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintVertical_weight, 38);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintHorizontal_weight, 37);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintVertical_chainStyle, 40);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintHorizontal_bias, 20);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintVertical_bias, 36);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintDimensionRatio, 5);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintLeft_creator, 76);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintTop_creator, 76);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintRight_creator, 76);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintBottom_creator, 76);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintBaseline_creator, 76);
            mapToConstant.append(C0205R.styleable.Layout_android_layout_marginLeft, 23);
            mapToConstant.append(C0205R.styleable.Layout_android_layout_marginRight, 27);
            mapToConstant.append(C0205R.styleable.Layout_android_layout_marginStart, 30);
            mapToConstant.append(C0205R.styleable.Layout_android_layout_marginEnd, 8);
            mapToConstant.append(C0205R.styleable.Layout_android_layout_marginTop, 33);
            mapToConstant.append(C0205R.styleable.Layout_android_layout_marginBottom, 2);
            mapToConstant.append(C0205R.styleable.Layout_android_layout_width, 22);
            mapToConstant.append(C0205R.styleable.Layout_android_layout_height, 21);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintCircle, 61);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintCircleRadius, 62);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintCircleAngle, 63);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintWidth_percent, 69);
            mapToConstant.append(C0205R.styleable.Layout_layout_constraintHeight_percent, 70);
            mapToConstant.append(C0205R.styleable.Layout_chainUseRtl, 71);
            mapToConstant.append(C0205R.styleable.Layout_barrierDirection, 72);
            mapToConstant.append(C0205R.styleable.Layout_barrierMargin, 73);
            mapToConstant.append(C0205R.styleable.Layout_constraint_referenced_ids, 74);
            mapToConstant.append(C0205R.styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0205R.styleable.Layout);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                int i2 = mapToConstant.get(index);
                if (i2 == 80) {
                    this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                } else if (i2 != 81) {
                    switch (i2) {
                        case 1:
                            this.baselineToBaseline = ConstraintSet.access$100(obtainStyledAttributes, index, this.baselineToBaseline);
                            break;
                        case 2:
                            this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomMargin);
                            break;
                        case 3:
                            this.bottomToBottom = ConstraintSet.access$100(obtainStyledAttributes, index, this.bottomToBottom);
                            break;
                        case 4:
                            this.bottomToTop = ConstraintSet.access$100(obtainStyledAttributes, index, this.bottomToTop);
                            break;
                        case 5:
                            this.dimensionRatio = obtainStyledAttributes.getString(index);
                            break;
                        case 6:
                            this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                            break;
                        case 7:
                            this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                            break;
                        case 8:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.endMargin);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            this.endToEnd = ConstraintSet.access$100(obtainStyledAttributes, index, this.endToEnd);
                            break;
                        case 10:
                            this.endToStart = ConstraintSet.access$100(obtainStyledAttributes, index, this.endToStart);
                            break;
                        case 11:
                            this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                            break;
                        case 12:
                            this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                            break;
                        case 13:
                            this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                            break;
                        case 14:
                            this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                            break;
                        case 15:
                            this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                            break;
                        case 16:
                            this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                            break;
                        case 17:
                            this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                            break;
                        case 18:
                            this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                            break;
                        case 19:
                            this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                            break;
                        case 20:
                            this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                            break;
                        case 21:
                            this.mHeight = obtainStyledAttributes.getLayoutDimension(index, this.mHeight);
                            break;
                        case 22:
                            this.mWidth = obtainStyledAttributes.getLayoutDimension(index, this.mWidth);
                            break;
                        case 23:
                            this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.leftMargin);
                            break;
                        case 24:
                            this.leftToLeft = ConstraintSet.access$100(obtainStyledAttributes, index, this.leftToLeft);
                            break;
                        case 25:
                            this.leftToRight = ConstraintSet.access$100(obtainStyledAttributes, index, this.leftToRight);
                            break;
                        case 26:
                            this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                            break;
                        case 27:
                            this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.rightMargin);
                            break;
                        case 28:
                            this.rightToLeft = ConstraintSet.access$100(obtainStyledAttributes, index, this.rightToLeft);
                            break;
                        case 29:
                            this.rightToRight = ConstraintSet.access$100(obtainStyledAttributes, index, this.rightToRight);
                            break;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.startMargin);
                                break;
                            } else {
                                break;
                            }
                        case 31:
                            this.startToEnd = ConstraintSet.access$100(obtainStyledAttributes, index, this.startToEnd);
                            break;
                        case 32:
                            this.startToStart = ConstraintSet.access$100(obtainStyledAttributes, index, this.startToStart);
                            break;
                        case 33:
                            this.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.topMargin);
                            break;
                        case 34:
                            this.topToBottom = ConstraintSet.access$100(obtainStyledAttributes, index, this.topToBottom);
                            break;
                        case 35:
                            this.topToTop = ConstraintSet.access$100(obtainStyledAttributes, index, this.topToTop);
                            break;
                        case 36:
                            this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                            break;
                        case 37:
                            this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                            break;
                        case 38:
                            this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                            break;
                        case 39:
                            this.horizontalChainStyle = obtainStyledAttributes.getInt(index, this.horizontalChainStyle);
                            break;
                        case 40:
                            this.verticalChainStyle = obtainStyledAttributes.getInt(index, this.verticalChainStyle);
                            break;
                        default:
                            switch (i2) {
                                case 54:
                                    this.widthDefault = obtainStyledAttributes.getInt(index, this.widthDefault);
                                    break;
                                case 55:
                                    this.heightDefault = obtainStyledAttributes.getInt(index, this.heightDefault);
                                    break;
                                case 56:
                                    this.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMax);
                                    break;
                                case 57:
                                    this.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMax);
                                    break;
                                case 58:
                                    this.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMin);
                                    break;
                                case 59:
                                    this.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMin);
                                    break;
                                default:
                                    switch (i2) {
                                        case 61:
                                            this.circleConstraint = ConstraintSet.access$100(obtainStyledAttributes, index, this.circleConstraint);
                                            break;
                                        case 62:
                                            this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                                            break;
                                        case 63:
                                            this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle);
                                            break;
                                        default:
                                            switch (i2) {
                                                case 69:
                                                    this.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 70:
                                                    this.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 71:
                                                    Log.e(ConstraintSet.TAG, "CURRENTLY UNSUPPORTED");
                                                    break;
                                                case 72:
                                                    this.mBarrierDirection = obtainStyledAttributes.getInt(index, this.mBarrierDirection);
                                                    break;
                                                case 73:
                                                    this.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.mBarrierMargin);
                                                    break;
                                                case 74:
                                                    this.mReferenceIdString = obtainStyledAttributes.getString(index);
                                                    break;
                                                case 75:
                                                    this.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, this.mBarrierAllowsGoneWidgets);
                                                    break;
                                                case 76:
                                                    Log.w(ConstraintSet.TAG, "unused attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                                                    break;
                                                case 77:
                                                    this.mConstraintTag = obtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w(ConstraintSet.TAG, "Unknown attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                                                    break;
                                            }
                                    }
                            }
                    }
                } else {
                    this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void dump(MotionScene motionScene, StringBuilder sb) {
            Field[] declaredFields = getClass().getDeclaredFields();
            sb.append("\n");
            for (Field field : declaredFields) {
                String name = field.getName();
                if (!Modifier.isStatic(field.getModifiers())) {
                    try {
                        Object obj = field.get(this);
                        Class<?> type = field.getType();
                        if (type == Integer.TYPE) {
                            Integer num = (Integer) obj;
                            if (num.intValue() != -1) {
                                Object lookUpConstraintName = motionScene.lookUpConstraintName(num.intValue());
                                sb.append("    ");
                                sb.append(name);
                                sb.append(" = \"");
                                sb.append(lookUpConstraintName == null ? num : lookUpConstraintName);
                                sb.append("\"\n");
                            }
                        } else if (type == Float.TYPE) {
                            Float f = (Float) obj;
                            if (f.floatValue() != -1.0f) {
                                sb.append("    ");
                                sb.append(name);
                                sb.append(" = \"");
                                sb.append(f);
                                sb.append("\"\n");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void clone(Context context, int i) {
        clone((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public void clone(ConstraintSet constraintSet) {
        this.mConstraints.clear();
        for (Integer num : constraintSet.mConstraints.keySet()) {
            this.mConstraints.put(num, constraintSet.mConstraints.get(num).m4262clone());
        }
    }

    public void clone(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.mConstraints.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                this.mConstraints.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            constraint.fillFrom(id, layoutParams);
            constraint.visibility = childAt.getVisibility();
            if (Build.VERSION.SDK_INT >= 17) {
                constraint.alpha = childAt.getAlpha();
                constraint.rotation = childAt.getRotation();
                constraint.rotationX = childAt.getRotationX();
                constraint.rotationY = childAt.getRotationY();
                constraint.scaleX = childAt.getScaleX();
                constraint.scaleY = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    constraint.transformPivotX = pivotX;
                    constraint.transformPivotY = pivotY;
                }
                constraint.translationX = childAt.getTranslationX();
                constraint.translationY = childAt.getTranslationY();
                if (Build.VERSION.SDK_INT >= 21) {
                    constraint.translationZ = childAt.getTranslationZ();
                    if (constraint.applyElevation) {
                        constraint.elevation = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                constraint.mBarrierAllowsGoneWidgets = barrier.allowsGoneWidget();
                constraint.mReferenceIds = barrier.getReferencedIds();
                constraint.mBarrierDirection = barrier.getType();
            }
        }
    }

    public void clone(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.mConstraints.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraints.getChildAt(i);
            Constraints.LayoutParams layoutParams = (Constraints.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                this.mConstraints.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                constraint.fillFromConstraints((ConstraintHelper) childAt, id, layoutParams);
            }
            constraint.fillFromConstraints(id, layoutParams);
        }
    }

    public void applyTo(ConstraintLayout constraintLayout) {
        applyToInternal(constraintLayout);
        constraintLayout.setConstraintSet(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void applyToInternal(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.mConstraints.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (this.mConstraints.containsKey(Integer.valueOf(id))) {
                hashSet.remove(Integer.valueOf(id));
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                if (childAt instanceof Barrier) {
                    constraint.mHelperType = 1;
                }
                if (constraint.mHelperType != -1 && constraint.mHelperType == 1) {
                    Barrier barrier = (Barrier) childAt;
                    barrier.setId(id);
                    barrier.setType(constraint.mBarrierDirection);
                    barrier.setAllowsGoneWidget(constraint.mBarrierAllowsGoneWidgets);
                    if (constraint.mReferenceIds != null) {
                        barrier.setReferencedIds(constraint.mReferenceIds);
                    } else if (constraint.mReferenceIdString != null) {
                        constraint.mReferenceIds = convertReferenceString(barrier, constraint.mReferenceIdString);
                        barrier.setReferencedIds(constraint.mReferenceIds);
                    }
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                constraint.applyTo(layoutParams);
                childAt.setLayoutParams(layoutParams);
                childAt.setVisibility(constraint.visibility);
                if (Build.VERSION.SDK_INT >= 17) {
                    childAt.setAlpha(constraint.alpha);
                    childAt.setRotation(constraint.rotation);
                    childAt.setRotationX(constraint.rotationX);
                    childAt.setRotationY(constraint.rotationY);
                    childAt.setScaleX(constraint.scaleX);
                    childAt.setScaleY(constraint.scaleY);
                    if (!Float.isNaN(constraint.transformPivotX)) {
                        childAt.setPivotX(constraint.transformPivotX);
                    }
                    if (!Float.isNaN(constraint.transformPivotY)) {
                        childAt.setPivotY(constraint.transformPivotY);
                    }
                    childAt.setTranslationX(constraint.translationX);
                    childAt.setTranslationY(constraint.translationY);
                    if (Build.VERSION.SDK_INT >= 21) {
                        childAt.setTranslationZ(constraint.translationZ);
                        if (constraint.applyElevation) {
                            childAt.setElevation(constraint.elevation);
                        }
                    }
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Constraint constraint2 = this.mConstraints.get(num);
            if (constraint2.mHelperType != -1 && constraint2.mHelperType == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                if (constraint2.mReferenceIds != null) {
                    barrier2.setReferencedIds(constraint2.mReferenceIds);
                } else if (constraint2.mReferenceIdString != null) {
                    constraint2.mReferenceIds = convertReferenceString(barrier2, constraint2.mReferenceIdString);
                    barrier2.setReferencedIds(constraint2.mReferenceIds);
                }
                barrier2.setType(constraint2.mBarrierDirection);
                ConstraintLayout.LayoutParams generateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.validateParams();
                constraint2.applyTo(generateDefaultLayoutParams);
                constraintLayout.addView(barrier2, generateDefaultLayoutParams);
            }
            if (constraint2.mIsGuideline) {
                Guideline guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.LayoutParams generateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                constraint2.applyTo(generateDefaultLayoutParams2);
                constraintLayout.addView(guideline, generateDefaultLayoutParams2);
            }
        }
    }

    public void center(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        if (i4 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        if (i3 == 1 || i3 == 2) {
            connect(i, 1, i2, i3, i4);
            connect(i, 2, i5, i6, i7);
            this.mConstraints.get(Integer.valueOf(i)).horizontalBias = f;
        } else if (i3 == 6 || i3 == 7) {
            connect(i, 6, i2, i3, i4);
            connect(i, 7, i5, i6, i7);
            this.mConstraints.get(Integer.valueOf(i)).horizontalBias = f;
        } else {
            connect(i, 3, i2, i3, i4);
            connect(i, 4, i5, i6, i7);
            this.mConstraints.get(Integer.valueOf(i)).verticalBias = f;
        }
    }

    public void centerHorizontally(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        connect(i, 1, i2, i3, i4);
        connect(i, 2, i5, i6, i7);
        this.mConstraints.get(Integer.valueOf(i)).horizontalBias = f;
    }

    public void centerHorizontallyRtl(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        connect(i, 6, i2, i3, i4);
        connect(i, 7, i5, i6, i7);
        this.mConstraints.get(Integer.valueOf(i)).horizontalBias = f;
    }

    public void centerVertically(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        connect(i, 3, i2, i3, i4);
        connect(i, 4, i5, i6, i7);
        this.mConstraints.get(Integer.valueOf(i)).verticalBias = f;
    }

    public void createVerticalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        if (iArr.length < 2) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null && fArr.length != iArr.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null) {
            get(iArr[0]).verticalWeight = fArr[0];
        }
        get(iArr[0]).verticalChainStyle = i5;
        connect(iArr[0], 3, i, i2, 0);
        for (int i6 = 1; i6 < iArr.length; i6++) {
            int i7 = iArr[i6];
            int i8 = i6 - 1;
            connect(iArr[i6], 3, iArr[i8], 4, 0);
            connect(iArr[i8], 4, iArr[i6], 3, 0);
            if (fArr != null) {
                get(iArr[i6]).verticalWeight = fArr[i6];
            }
        }
        connect(iArr[iArr.length - 1], 4, i3, i4, 0);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Transform {
        private static final int ELEVATION = 11;
        private static final int ROTATION = 1;
        private static final int ROTATION_X = 2;
        private static final int ROTATION_Y = 3;
        private static final int SCALE_X = 4;
        private static final int SCALE_Y = 5;
        private static final int TRANSFORM_PIVOT_X = 6;
        private static final int TRANSFORM_PIVOT_Y = 7;
        private static final int TRANSLATION_X = 8;
        private static final int TRANSLATION_Y = 9;
        private static final int TRANSLATION_Z = 10;
        private static SparseIntArray mapToConstant;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;
        public boolean applyElevation = false;
        public float elevation = 0.0f;

        public void copyFrom(Transform transform) {
            this.mApply = transform.mApply;
            this.rotation = transform.rotation;
            this.rotationX = transform.rotationX;
            this.rotationY = transform.rotationY;
            this.scaleX = transform.scaleX;
            this.scaleY = transform.scaleY;
            this.transformPivotX = transform.transformPivotX;
            this.transformPivotY = transform.transformPivotY;
            this.translationX = transform.translationX;
            this.translationY = transform.translationY;
            this.translationZ = transform.translationZ;
            this.applyElevation = transform.applyElevation;
            this.elevation = transform.elevation;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(C0205R.styleable.Transform_android_rotation, 1);
            mapToConstant.append(C0205R.styleable.Transform_android_rotationX, 2);
            mapToConstant.append(C0205R.styleable.Transform_android_rotationY, 3);
            mapToConstant.append(C0205R.styleable.Transform_android_scaleX, 4);
            mapToConstant.append(C0205R.styleable.Transform_android_scaleY, 5);
            mapToConstant.append(C0205R.styleable.Transform_android_transformPivotX, 6);
            mapToConstant.append(C0205R.styleable.Transform_android_transformPivotY, 7);
            mapToConstant.append(C0205R.styleable.Transform_android_translationX, 8);
            mapToConstant.append(C0205R.styleable.Transform_android_translationY, 9);
            mapToConstant.append(C0205R.styleable.Transform_android_translationZ, 10);
            mapToConstant.append(C0205R.styleable.Transform_android_elevation, 11);
        }

        void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0205R.styleable.Transform);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                        break;
                    case 2:
                        this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                        break;
                    case 3:
                        this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = obtainStyledAttributes.getDimension(index, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = obtainStyledAttributes.getDimension(index, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = obtainStyledAttributes.getDimension(index, this.translationX);
                        break;
                    case 9:
                        this.translationY = obtainStyledAttributes.getDimension(index, this.translationY);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.translationZ = obtainStyledAttributes.getDimension(index, this.translationZ);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.applyElevation = true;
                            this.elevation = obtainStyledAttributes.getDimension(index, this.elevation);
                            break;
                        } else {
                            break;
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void createHorizontalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        createHorizontalChain(i, i2, i3, i4, iArr, fArr, i5, 1, 2);
    }

    public void createHorizontalChainRtl(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        createHorizontalChain(i, i2, i3, i4, iArr, fArr, i5, 6, 7);
    }

    private void createHorizontalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5, int i6, int i7) {
        if (iArr.length < 2) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null && fArr.length != iArr.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null) {
            get(iArr[0]).horizontalWeight = fArr[0];
        }
        get(iArr[0]).horizontalChainStyle = i5;
        connect(iArr[0], i6, i, i2, -1);
        for (int i8 = 1; i8 < iArr.length; i8++) {
            int i9 = iArr[i8];
            int i10 = i8 - 1;
            connect(iArr[i8], i6, iArr[i10], i7, -1);
            connect(iArr[i10], i7, iArr[i8], i6, -1);
            if (fArr != null) {
                get(iArr[i8]).horizontalWeight = fArr[i8];
            }
        }
        connect(iArr[iArr.length - 1], i7, i3, i4, -1);
    }

    public void connect(int i, int i2, int i3, int i4, int i5) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    constraint.leftToLeft = i3;
                    constraint.leftToRight = -1;
                } else if (i4 == 2) {
                    constraint.leftToRight = i3;
                    constraint.leftToLeft = -1;
                } else {
                    throw new IllegalArgumentException("Left to " + sideToString(i4) + " undefined");
                }
                constraint.leftMargin = i5;
                return;
            case 2:
                if (i4 == 1) {
                    constraint.rightToLeft = i3;
                    constraint.rightToRight = -1;
                } else if (i4 == 2) {
                    constraint.rightToRight = i3;
                    constraint.rightToLeft = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                constraint.rightMargin = i5;
                return;
            case 3:
                if (i4 == 3) {
                    constraint.topToTop = i3;
                    constraint.topToBottom = -1;
                    constraint.baselineToBaseline = -1;
                } else if (i4 == 4) {
                    constraint.topToBottom = i3;
                    constraint.topToTop = -1;
                    constraint.baselineToBaseline = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                constraint.topMargin = i5;
                return;
            case 4:
                if (i4 == 4) {
                    constraint.bottomToBottom = i3;
                    constraint.bottomToTop = -1;
                    constraint.baselineToBaseline = -1;
                } else if (i4 == 3) {
                    constraint.bottomToTop = i3;
                    constraint.bottomToBottom = -1;
                    constraint.baselineToBaseline = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                constraint.bottomMargin = i5;
                return;
            case 5:
                if (i4 == 5) {
                    constraint.baselineToBaseline = i3;
                    constraint.bottomToBottom = -1;
                    constraint.bottomToTop = -1;
                    constraint.topToTop = -1;
                    constraint.topToBottom = -1;
                    return;
                }
                throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
            case 6:
                if (i4 == 6) {
                    constraint.startToStart = i3;
                    constraint.startToEnd = -1;
                } else if (i4 == 7) {
                    constraint.startToEnd = i3;
                    constraint.startToStart = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                constraint.startMargin = i5;
                return;
            case 7:
                if (i4 == 7) {
                    constraint.endToEnd = i3;
                    constraint.endToStart = -1;
                } else if (i4 == 6) {
                    constraint.endToStart = i3;
                    constraint.endToEnd = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                constraint.endMargin = i5;
                return;
            default:
                throw new IllegalArgumentException(sideToString(i2) + " to " + sideToString(i4) + " unknown");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class PropertySet {
        public boolean mApply = false;
        public int visibility = 0;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;

        public void copyFrom(PropertySet propertySet) {
            this.mApply = propertySet.mApply;
            this.visibility = propertySet.visibility;
            this.alpha = propertySet.alpha;
            this.mProgress = propertySet.mProgress;
            this.mVisibilityMode = propertySet.mVisibilityMode;
        }

        void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0205R.styleable.PropertySet);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == C0205R.styleable.PropertySet_android_alpha) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == C0205R.styleable.PropertySet_android_visibility) {
                    this.visibility = obtainStyledAttributes.getInt(index, this.visibility);
                    this.visibility = ConstraintSet.access$200()[this.visibility];
                } else if (index == C0205R.styleable.PropertySet_visibilityMode) {
                    this.mVisibilityMode = obtainStyledAttributes.getInt(index, this.mVisibilityMode);
                } else if (index == C0205R.styleable.PropertySet_motionProgress) {
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Motion {
        private static final int ANIMATE_RELATIVE_TO = 5;
        private static final int MOTION_DRAW_PATH = 4;
        private static final int MOTION_STAGGER = 6;
        private static final int PATH_MOTION_ARC = 2;
        private static final int TRANSITION_EASING = 3;
        private static final int TRANSITION_PATH_ROTATE = 1;
        private static SparseIntArray mapToConstant;
        public boolean mApply = false;
        public int mAnimateRelativeTo = -1;
        public String mTransitionEasing = null;
        public int mPathMotionArc = -1;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public float mPathRotate = Float.NaN;

        public void copyFrom(Motion motion) {
            this.mApply = motion.mApply;
            this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
            this.mTransitionEasing = motion.mTransitionEasing;
            this.mPathMotionArc = motion.mPathMotionArc;
            this.mDrawPath = motion.mDrawPath;
            this.mPathRotate = motion.mPathRotate;
            this.mMotionStagger = motion.mMotionStagger;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(C0205R.styleable.Motion_motionPathRotate, 1);
            mapToConstant.append(C0205R.styleable.Motion_pathMotionArc, 2);
            mapToConstant.append(C0205R.styleable.Motion_transitionEasing, 3);
            mapToConstant.append(C0205R.styleable.Motion_drawPath, 4);
            mapToConstant.append(C0205R.styleable.Motion_animate_relativeTo, 5);
            mapToConstant.append(C0205R.styleable.Motion_motionStagger, 6);
        }

        void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0205R.styleable.Motion);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.mPathRotate = obtainStyledAttributes.getFloat(index, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                        break;
                    case 3:
                        if (obtainStyledAttributes.peekValue(index).type == 3) {
                            this.mTransitionEasing = obtainStyledAttributes.getString(index);
                            break;
                        } else {
                            this.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        this.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.mAnimateRelativeTo = ConstraintSet.access$100(obtainStyledAttributes, index, this.mAnimateRelativeTo);
                        break;
                    case 6:
                        this.mMotionStagger = obtainStyledAttributes.getFloat(index, this.mMotionStagger);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void connect(int i, int i2, int i3, int i4) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    constraint.leftToLeft = i3;
                    constraint.leftToRight = -1;
                    return;
                } else if (i4 == 2) {
                    constraint.leftToRight = i3;
                    constraint.leftToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("left to " + sideToString(i4) + " undefined");
                }
            case 2:
                if (i4 == 1) {
                    constraint.rightToLeft = i3;
                    constraint.rightToRight = -1;
                    return;
                } else if (i4 == 2) {
                    constraint.rightToRight = i3;
                    constraint.rightToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
            case 3:
                if (i4 == 3) {
                    constraint.topToTop = i3;
                    constraint.topToBottom = -1;
                    constraint.baselineToBaseline = -1;
                    return;
                } else if (i4 == 4) {
                    constraint.topToBottom = i3;
                    constraint.topToTop = -1;
                    constraint.baselineToBaseline = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
            case 4:
                if (i4 == 4) {
                    constraint.bottomToBottom = i3;
                    constraint.bottomToTop = -1;
                    constraint.baselineToBaseline = -1;
                    return;
                } else if (i4 == 3) {
                    constraint.bottomToTop = i3;
                    constraint.bottomToBottom = -1;
                    constraint.baselineToBaseline = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
            case 5:
                if (i4 == 5) {
                    constraint.baselineToBaseline = i3;
                    constraint.bottomToBottom = -1;
                    constraint.bottomToTop = -1;
                    constraint.topToTop = -1;
                    constraint.topToBottom = -1;
                    return;
                }
                throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
            case 6:
                if (i4 == 6) {
                    constraint.startToStart = i3;
                    constraint.startToEnd = -1;
                    return;
                } else if (i4 == 7) {
                    constraint.startToEnd = i3;
                    constraint.startToStart = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
            case 7:
                if (i4 == 7) {
                    constraint.endToEnd = i3;
                    constraint.endToStart = -1;
                    return;
                } else if (i4 == 6) {
                    constraint.endToStart = i3;
                    constraint.endToEnd = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
            default:
                throw new IllegalArgumentException(sideToString(i2) + " to " + sideToString(i4) + " unknown");
        }
    }

    public void centerHorizontally(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 1, 0, 0, 2, 0, 0.5f);
        } else {
            center(i, i2, 2, 0, i2, 1, 0, 0.5f);
        }
    }

    public void centerHorizontallyRtl(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 6, 0, 0, 7, 0, 0.5f);
        } else {
            center(i, i2, 7, 0, i2, 6, 0, 0.5f);
        }
    }

    public void centerVertically(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 3, 0, 0, 4, 0, 0.5f);
        } else {
            center(i, i2, 4, 0, i2, 3, 0, 0.5f);
        }
    }

    public void clear(int i) {
        this.mConstraints.remove(Integer.valueOf(i));
    }

    public void clear(int i, int i2) {
        if (this.mConstraints.containsKey(Integer.valueOf(i))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
            switch (i2) {
                case 1:
                    constraint.leftToRight = -1;
                    constraint.leftToLeft = -1;
                    constraint.leftMargin = -1;
                    constraint.goneLeftMargin = -1;
                    return;
                case 2:
                    constraint.rightToRight = -1;
                    constraint.rightToLeft = -1;
                    constraint.rightMargin = -1;
                    constraint.goneRightMargin = -1;
                    return;
                case 3:
                    constraint.topToBottom = -1;
                    constraint.topToTop = -1;
                    constraint.topMargin = -1;
                    constraint.goneTopMargin = -1;
                    return;
                case 4:
                    constraint.bottomToTop = -1;
                    constraint.bottomToBottom = -1;
                    constraint.bottomMargin = -1;
                    constraint.goneBottomMargin = -1;
                    return;
                case 5:
                    constraint.baselineToBaseline = -1;
                    return;
                case 6:
                    constraint.startToEnd = -1;
                    constraint.startToStart = -1;
                    constraint.startMargin = -1;
                    constraint.goneStartMargin = -1;
                    return;
                case 7:
                    constraint.endToStart = -1;
                    constraint.endToEnd = -1;
                    constraint.endMargin = -1;
                    constraint.goneEndMargin = -1;
                    return;
                default:
                    throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public void setMargin(int i, int i2, int i3) {
        Constraint constraint = get(i);
        switch (i2) {
            case 1:
                constraint.leftMargin = i3;
                return;
            case 2:
                constraint.rightMargin = i3;
                return;
            case 3:
                constraint.topMargin = i3;
                return;
            case 4:
                constraint.bottomMargin = i3;
                return;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                constraint.startMargin = i3;
                return;
            case 7:
                constraint.endMargin = i3;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setGoneMargin(int i, int i2, int i3) {
        Constraint constraint = get(i);
        switch (i2) {
            case 1:
                constraint.goneLeftMargin = i3;
                return;
            case 2:
                constraint.goneRightMargin = i3;
                return;
            case 3:
                constraint.goneTopMargin = i3;
                return;
            case 4:
                constraint.goneBottomMargin = i3;
                return;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                constraint.goneStartMargin = i3;
                return;
            case 7:
                constraint.goneEndMargin = i3;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setHorizontalBias(int i, float f) {
        get(i).horizontalBias = f;
    }

    public void setVerticalBias(int i, float f) {
        get(i).verticalBias = f;
    }

    public void setDimensionRatio(int i, String str) {
        get(i).dimensionRatio = str;
    }

    public void setVisibility(int i, int i2) {
        get(i).visibility = i2;
    }

    public void setAlpha(int i, float f) {
        get(i).alpha = f;
    }

    public boolean getApplyElevation(int i) {
        return get(i).applyElevation;
    }

    public void setApplyElevation(int i, boolean z) {
        get(i).applyElevation = z;
    }

    public void setElevation(int i, float f) {
        get(i).elevation = f;
        get(i).applyElevation = true;
    }

    public void setRotation(int i, float f) {
        get(i).rotation = f;
    }

    public void setRotationX(int i, float f) {
        get(i).rotationX = f;
    }

    public void setRotationY(int i, float f) {
        get(i).rotationY = f;
    }

    public void setScaleX(int i, float f) {
        get(i).scaleX = f;
    }

    public void setScaleY(int i, float f) {
        get(i).scaleY = f;
    }

    public void setTransformPivotX(int i, float f) {
        get(i).transformPivotX = f;
    }

    public void setTransformPivotY(int i, float f) {
        get(i).transformPivotY = f;
    }

    public void setTransformPivot(int i, float f, float f2) {
        Constraint constraint = get(i);
        constraint.transformPivotY = f2;
        constraint.transformPivotX = f;
    }

    public void setTranslationX(int i, float f) {
        get(i).translationX = f;
    }

    public void setTranslationY(int i, float f) {
        get(i).translationY = f;
    }

    public void setTranslation(int i, float f, float f2) {
        Constraint constraint = get(i);
        constraint.translationX = f;
        constraint.translationY = f2;
    }

    public void setTranslationZ(int i, float f) {
        get(i).translationZ = f;
    }

    public void constrainHeight(int i, int i2) {
        get(i).mHeight = i2;
    }

    public void constrainWidth(int i, int i2) {
        get(i).mWidth = i2;
    }

    public void constrainCircle(int i, int i2, int i3, float f) {
        Constraint constraint = get(i);
        constraint.circleConstraint = i2;
        constraint.circleRadius = i3;
        constraint.circleAngle = f;
    }

    public void constrainMaxHeight(int i, int i2) {
        get(i).heightMax = i2;
    }

    public void constrainMaxWidth(int i, int i2) {
        get(i).widthMax = i2;
    }

    public void constrainMinHeight(int i, int i2) {
        get(i).heightMin = i2;
    }

    public void constrainMinWidth(int i, int i2) {
        get(i).widthMin = i2;
    }

    public void constrainPercentWidth(int i, float f) {
        get(i).widthPercent = f;
    }

    public void constrainPercentHeight(int i, float f) {
        get(i).heightPercent = f;
    }

    public void constrainDefaultHeight(int i, int i2) {
        get(i).heightDefault = i2;
    }

    public void constrainDefaultWidth(int i, int i2) {
        get(i).widthDefault = i2;
    }

    public void setHorizontalWeight(int i, float f) {
        get(i).horizontalWeight = f;
    }

    public void setVerticalWeight(int i, float f) {
        get(i).verticalWeight = f;
    }

    public void setHorizontalChainStyle(int i, int i2) {
        get(i).horizontalChainStyle = i2;
    }

    public void setVerticalChainStyle(int i, int i2) {
        get(i).verticalChainStyle = i2;
    }

    public void addToHorizontalChain(int i, int i2, int i3) {
        connect(i, 1, i2, i2 == 0 ? 1 : 2, 0);
        connect(i, 2, i3, i3 == 0 ? 2 : 1, 0);
        if (i2 != 0) {
            connect(i2, 2, i, 1, 0);
        }
        if (i3 != 0) {
            connect(i3, 1, i, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int i, int i2, int i3) {
        connect(i, 6, i2, i2 == 0 ? 6 : 7, 0);
        connect(i, 7, i3, i3 == 0 ? 7 : 6, 0);
        if (i2 != 0) {
            connect(i2, 7, i, 6, 0);
        }
        if (i3 != 0) {
            connect(i3, 6, i, 7, 0);
        }
    }

    public void addToVerticalChain(int i, int i2, int i3) {
        connect(i, 3, i2, i2 == 0 ? 3 : 4, 0);
        connect(i, 4, i3, i3 == 0 ? 4 : 3, 0);
        if (i2 != 0) {
            connect(i2, 4, i, 3, 0);
        }
        if (i2 != 0) {
            connect(i3, 3, i, 4, 0);
        }
    }

    public void removeFromVerticalChain(int i) {
        if (this.mConstraints.containsKey(Integer.valueOf(i))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
            int i2 = constraint.topToBottom;
            int i3 = constraint.bottomToTop;
            if (i2 != -1 || i3 != -1) {
                if (i2 != -1 && i3 != -1) {
                    connect(i2, 4, i3, 3, 0);
                    connect(i3, 3, i2, 4, 0);
                } else if (i2 != -1 || i3 != -1) {
                    if (constraint.bottomToBottom != -1) {
                        connect(i2, 4, constraint.bottomToBottom, 4, 0);
                    } else if (constraint.topToTop != -1) {
                        connect(i3, 3, constraint.topToTop, 3, 0);
                    }
                }
            }
        }
        clear(i, 3);
        clear(i, 4);
    }

    public void removeFromHorizontalChain(int i) {
        if (this.mConstraints.containsKey(Integer.valueOf(i))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
            int i2 = constraint.leftToRight;
            int i3 = constraint.rightToLeft;
            if (i2 != -1 || i3 != -1) {
                if (i2 != -1 && i3 != -1) {
                    connect(i2, 2, i3, 1, 0);
                    connect(i3, 1, i2, 2, 0);
                } else if (i2 != -1 || i3 != -1) {
                    if (constraint.rightToRight != -1) {
                        connect(i2, 2, constraint.rightToRight, 2, 0);
                    } else if (constraint.leftToLeft != -1) {
                        connect(i3, 1, constraint.leftToLeft, 1, 0);
                    }
                }
                clear(i, 1);
                clear(i, 2);
                return;
            }
            int i4 = constraint.startToEnd;
            int i5 = constraint.endToStart;
            if (i4 != -1 || i5 != -1) {
                if (i4 != -1 && i5 != -1) {
                    connect(i4, 7, i5, 6, 0);
                    connect(i5, 6, i2, 7, 0);
                } else if (i2 != -1 || i5 != -1) {
                    if (constraint.rightToRight != -1) {
                        connect(i2, 7, constraint.rightToRight, 7, 0);
                    } else if (constraint.leftToLeft != -1) {
                        connect(i5, 6, constraint.leftToLeft, 6, 0);
                    }
                }
            }
            clear(i, 6);
            clear(i, 7);
        }
    }

    public void create(int i, int i2) {
        Constraint constraint = get(i);
        constraint.mIsGuideline = true;
        constraint.orientation = i2;
    }

    public void createBarrier(int i, int i2, int... iArr) {
        Constraint constraint = get(i);
        constraint.mHelperType = 1;
        constraint.mBarrierDirection = i2;
        constraint.mIsGuideline = false;
        constraint.mReferenceIds = iArr;
    }

    public void setGuidelineBegin(int i, int i2) {
        get(i).guideBegin = i2;
        get(i).guideEnd = -1;
        get(i).guidePercent = -1.0f;
    }

    public void setGuidelineEnd(int i, int i2) {
        get(i).guideEnd = i2;
        get(i).guideBegin = -1;
        get(i).guidePercent = -1.0f;
    }

    public void setGuidelinePercent(int i, float f) {
        get(i).guidePercent = f;
        get(i).guideEnd = -1;
        get(i).guideBegin = -1;
    }

    private Constraint get(int i) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        return this.mConstraints.get(Integer.valueOf(i));
    }

    public void load(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    Constraint fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        fillFromAttributeList.mIsGuideline = true;
                    }
                    this.mConstraints.put(Integer.valueOf(fillFromAttributeList.mViewId), fillFromAttributeList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    private static int lookupID(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    private Constraint fillFromAttributeList(Context context, AttributeSet attributeSet) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0205R.styleable.ConstraintSet);
        populateConstraint(constraint, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    private void populateConstraint(Constraint constraint, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            int i2 = mapToConstant.get(index);
            switch (i2) {
                case 1:
                    constraint.baselineToBaseline = lookupID(typedArray, index, constraint.baselineToBaseline);
                    break;
                case 2:
                    constraint.bottomMargin = typedArray.getDimensionPixelSize(index, constraint.bottomMargin);
                    break;
                case 3:
                    constraint.bottomToBottom = lookupID(typedArray, index, constraint.bottomToBottom);
                    break;
                case 4:
                    constraint.bottomToTop = lookupID(typedArray, index, constraint.bottomToTop);
                    break;
                case 5:
                    constraint.dimensionRatio = typedArray.getString(index);
                    break;
                case 6:
                    constraint.editorAbsoluteX = typedArray.getDimensionPixelOffset(index, constraint.editorAbsoluteX);
                    break;
                case 7:
                    constraint.editorAbsoluteY = typedArray.getDimensionPixelOffset(index, constraint.editorAbsoluteY);
                    break;
                case 8:
                    constraint.endMargin = typedArray.getDimensionPixelSize(index, constraint.endMargin);
                    break;
                case 9:
                    constraint.endToEnd = lookupID(typedArray, index, constraint.endToEnd);
                    break;
                case 10:
                    constraint.endToStart = lookupID(typedArray, index, constraint.endToStart);
                    break;
                case 11:
                    constraint.goneBottomMargin = typedArray.getDimensionPixelSize(index, constraint.goneBottomMargin);
                    break;
                case 12:
                    constraint.goneEndMargin = typedArray.getDimensionPixelSize(index, constraint.goneEndMargin);
                    break;
                case 13:
                    constraint.goneLeftMargin = typedArray.getDimensionPixelSize(index, constraint.goneLeftMargin);
                    break;
                case 14:
                    constraint.goneRightMargin = typedArray.getDimensionPixelSize(index, constraint.goneRightMargin);
                    break;
                case 15:
                    constraint.goneStartMargin = typedArray.getDimensionPixelSize(index, constraint.goneStartMargin);
                    break;
                case 16:
                    constraint.goneTopMargin = typedArray.getDimensionPixelSize(index, constraint.goneTopMargin);
                    break;
                case 17:
                    constraint.guideBegin = typedArray.getDimensionPixelOffset(index, constraint.guideBegin);
                    break;
                case 18:
                    constraint.guideEnd = typedArray.getDimensionPixelOffset(index, constraint.guideEnd);
                    break;
                case 19:
                    constraint.guidePercent = typedArray.getFloat(index, constraint.guidePercent);
                    break;
                case 20:
                    constraint.horizontalBias = typedArray.getFloat(index, constraint.horizontalBias);
                    break;
                case 21:
                    constraint.mHeight = typedArray.getLayoutDimension(index, constraint.mHeight);
                    break;
                case 22:
                    constraint.visibility = typedArray.getInt(index, constraint.visibility);
                    constraint.visibility = VISIBILITY_FLAGS[constraint.visibility];
                    break;
                case 23:
                    constraint.mWidth = typedArray.getLayoutDimension(index, constraint.mWidth);
                    break;
                case 24:
                    constraint.leftMargin = typedArray.getDimensionPixelSize(index, constraint.leftMargin);
                    break;
                case 25:
                    constraint.leftToLeft = lookupID(typedArray, index, constraint.leftToLeft);
                    break;
                case 26:
                    constraint.leftToRight = lookupID(typedArray, index, constraint.leftToRight);
                    break;
                case 27:
                    constraint.orientation = typedArray.getInt(index, constraint.orientation);
                    break;
                case 28:
                    constraint.rightMargin = typedArray.getDimensionPixelSize(index, constraint.rightMargin);
                    break;
                case 29:
                    constraint.rightToLeft = lookupID(typedArray, index, constraint.rightToLeft);
                    break;
                case 30:
                    constraint.rightToRight = lookupID(typedArray, index, constraint.rightToRight);
                    break;
                case 31:
                    constraint.startMargin = typedArray.getDimensionPixelSize(index, constraint.startMargin);
                    break;
                case 32:
                    constraint.startToEnd = lookupID(typedArray, index, constraint.startToEnd);
                    break;
                case 33:
                    constraint.startToStart = lookupID(typedArray, index, constraint.startToStart);
                    break;
                case 34:
                    constraint.topMargin = typedArray.getDimensionPixelSize(index, constraint.topMargin);
                    break;
                case 35:
                    constraint.topToBottom = lookupID(typedArray, index, constraint.topToBottom);
                    break;
                case 36:
                    constraint.topToTop = lookupID(typedArray, index, constraint.topToTop);
                    break;
                case 37:
                    constraint.verticalBias = typedArray.getFloat(index, constraint.verticalBias);
                    break;
                case 38:
                    constraint.mViewId = typedArray.getResourceId(index, constraint.mViewId);
                    break;
                case 39:
                    constraint.horizontalWeight = typedArray.getFloat(index, constraint.horizontalWeight);
                    break;
                case 40:
                    constraint.verticalWeight = typedArray.getFloat(index, constraint.verticalWeight);
                    break;
                case 41:
                    constraint.horizontalChainStyle = typedArray.getInt(index, constraint.horizontalChainStyle);
                    break;
                case 42:
                    constraint.verticalChainStyle = typedArray.getInt(index, constraint.verticalChainStyle);
                    break;
                case 43:
                    constraint.alpha = typedArray.getFloat(index, constraint.alpha);
                    break;
                case 44:
                    constraint.applyElevation = true;
                    constraint.elevation = typedArray.getDimension(index, constraint.elevation);
                    break;
                case 45:
                    constraint.rotationX = typedArray.getFloat(index, constraint.rotationX);
                    break;
                case 46:
                    constraint.rotationY = typedArray.getFloat(index, constraint.rotationY);
                    break;
                case 47:
                    constraint.scaleX = typedArray.getFloat(index, constraint.scaleX);
                    break;
                case 48:
                    constraint.scaleY = typedArray.getFloat(index, constraint.scaleY);
                    break;
                case 49:
                    constraint.transformPivotX = typedArray.getFloat(index, constraint.transformPivotX);
                    break;
                case 50:
                    constraint.transformPivotY = typedArray.getFloat(index, constraint.transformPivotY);
                    break;
                case 51:
                    constraint.translationX = typedArray.getDimension(index, constraint.translationX);
                    break;
                case 52:
                    constraint.translationY = typedArray.getDimension(index, constraint.translationY);
                    break;
                case 53:
                    constraint.translationZ = typedArray.getDimension(index, constraint.translationZ);
                    break;
                default:
                    switch (i2) {
                        case 60:
                            constraint.rotation = typedArray.getFloat(index, constraint.rotation);
                            break;
                        case 61:
                            constraint.circleConstraint = lookupID(typedArray, index, constraint.circleConstraint);
                            break;
                        case 62:
                            constraint.circleRadius = typedArray.getDimensionPixelSize(index, constraint.circleRadius);
                            break;
                        case 63:
                            constraint.circleAngle = typedArray.getFloat(index, constraint.circleAngle);
                            break;
                        default:
                            switch (i2) {
                                case 69:
                                    constraint.widthPercent = typedArray.getFloat(index, 1.0f);
                                    break;
                                case 70:
                                    constraint.heightPercent = typedArray.getFloat(index, 1.0f);
                                    break;
                                case 71:
                                    Log.e(TAG, "CURRENTLY UNSUPPORTED");
                                    break;
                                case 72:
                                    constraint.mBarrierDirection = typedArray.getInt(index, constraint.mBarrierDirection);
                                    break;
                                case 73:
                                    constraint.mReferenceIdString = typedArray.getString(index);
                                    break;
                                case 74:
                                    constraint.mBarrierAllowsGoneWidgets = typedArray.getBoolean(index, constraint.mBarrierAllowsGoneWidgets);
                                    break;
                                case 75:
                                    Log.w(TAG, "unused attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                                    break;
                                default:
                                    Log.w(TAG, "Unknown attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                                    break;
                            }
                    }
            }
        }
    }

    private int[] convertReferenceString(View view, String str) {
        int i;
        Object designInformation;
        String[] split = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < split.length) {
            String trim = split[i2].trim();
            try {
                i = C0205R.id.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i = 0;
            }
            if (i == 0) {
                i = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) view.getParent()).getDesignInformation(0, trim)) != null && (designInformation instanceof Integer)) {
                i = ((Integer) designInformation).intValue();
            }
            iArr[i3] = i;
            i2++;
            i3++;
        }
        return i3 != split.length ? Arrays.copyOf(iArr, i3) : iArr;
    }

    /* loaded from: classes.dex */
    class WriteXmlEngine {
        private static final String SPACE = "\n       ";
        Context context;
        int flags;
        ConstraintLayout layout;
        Writer writer;
        int unknownCount = 0;
        final String LEFT = "'left'";
        final String RIGHT = "'right'";
        final String BASELINE = "'baseline'";
        final String BOTTOM = "'bottom'";
        final String TOP = "'top'";
        final String START = "'start'";
        final String END = "'end'";
        HashMap<Integer, String> idMap = new HashMap<>();

        WriteXmlEngine(Writer writer, ConstraintLayout layout, int flags) throws IOException {
            this.writer = writer;
            this.layout = layout;
            this.context = layout.getContext();
            this.flags = flags;
        }

        void writeLayout() throws IOException {
            this.writer.write("\n<ConstraintSet>\n");
            for (Integer num : ConstraintSet.access$1300(ConstraintSet.this).keySet()) {
                Constraint constraint = (Constraint) ConstraintSet.access$1300(ConstraintSet.this).get(num);
                String name = getName(num.intValue());
                this.writer.write("  <Constraint");
                Writer writer = this.writer;
                StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 21);
                sb.append("\n       android:id=\"");
                sb.append(name);
                sb.append("\"");
                writer.write(sb.toString());
                Layout layout = constraint.layout;
                writeBaseDimension("android:layout_width", layout.mWidth, -5);
                writeBaseDimension("android:layout_height", layout.mHeight, -5);
                writeVariable("app:layout_constraintGuide_begin", layout.guideBegin, -1.0f);
                writeVariable("app:layout_constraintGuide_end", layout.guideEnd, -1.0f);
                writeVariable("app:layout_constraintGuide_percent", layout.guidePercent, -1.0f);
                writeVariable("app:layout_constraintHorizontal_bias", layout.horizontalBias, 0.5f);
                writeVariable("app:layout_constraintVertical_bias", layout.verticalBias, 0.5f);
                writeVariable("app:layout_constraintDimensionRatio", layout.dimensionRatio, (String) null);
                writeXmlConstraint("app:layout_constraintCircle", layout.circleConstraint);
                writeVariable("app:layout_constraintCircleRadius", layout.circleRadius, 0.0f);
                writeVariable("app:layout_constraintCircleAngle", layout.circleAngle, 0.0f);
                writeVariable("android:orientation", layout.orientation, -1.0f);
                writeVariable("app:layout_constraintVertical_weight", layout.verticalWeight, -1.0f);
                writeVariable("app:layout_constraintHorizontal_weight", layout.horizontalWeight, -1.0f);
                writeVariable("app:layout_constraintHorizontal_chainStyle", layout.horizontalChainStyle, 0.0f);
                writeVariable("app:layout_constraintVertical_chainStyle", layout.verticalChainStyle, 0.0f);
                writeVariable("app:barrierDirection", layout.mBarrierDirection, -1.0f);
                writeVariable("app:barrierMargin", layout.mBarrierMargin, 0.0f);
                writeDimension("app:layout_marginLeft", layout.leftMargin, 0);
                writeDimension("app:layout_goneMarginLeft", layout.goneLeftMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginRight", layout.rightMargin, 0);
                writeDimension("app:layout_goneMarginRight", layout.goneRightMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginStart", layout.startMargin, 0);
                writeDimension("app:layout_goneMarginStart", layout.goneStartMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginEnd", layout.endMargin, 0);
                writeDimension("app:layout_goneMarginEnd", layout.goneEndMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginTop", layout.topMargin, 0);
                writeDimension("app:layout_goneMarginTop", layout.goneTopMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginBottom", layout.bottomMargin, 0);
                writeDimension("app:layout_goneMarginBottom", layout.goneBottomMargin, Integer.MIN_VALUE);
                writeDimension("app:goneBaselineMargin", layout.goneBaselineMargin, Integer.MIN_VALUE);
                writeDimension("app:baselineMargin", layout.baselineMargin, 0);
                writeBoolen("app:layout_constrainedWidth", layout.constrainedWidth, false);
                writeBoolen("app:layout_constrainedHeight", layout.constrainedHeight, false);
                writeBoolen("app:barrierAllowsGoneWidgets", layout.mBarrierAllowsGoneWidgets, true);
                writeVariable("app:layout_wrapBehaviorInParent", layout.mWrapBehavior, 0.0f);
                writeXmlConstraint("app:baselineToBaseline", layout.baselineToBaseline);
                writeXmlConstraint("app:baselineToBottom", layout.baselineToBottom);
                writeXmlConstraint("app:baselineToTop", layout.baselineToTop);
                writeXmlConstraint("app:layout_constraintBottom_toBottomOf", layout.bottomToBottom);
                writeXmlConstraint("app:layout_constraintBottom_toTopOf", layout.bottomToTop);
                writeXmlConstraint("app:layout_constraintEnd_toEndOf", layout.endToEnd);
                writeXmlConstraint("app:layout_constraintEnd_toStartOf", layout.endToStart);
                writeXmlConstraint("app:layout_constraintLeft_toLeftOf", layout.leftToLeft);
                writeXmlConstraint("app:layout_constraintLeft_toRightOf", layout.leftToRight);
                writeXmlConstraint("app:layout_constraintRight_toLeftOf", layout.rightToLeft);
                writeXmlConstraint("app:layout_constraintRight_toRightOf", layout.rightToRight);
                writeXmlConstraint("app:layout_constraintStart_toEndOf", layout.startToEnd);
                writeXmlConstraint("app:layout_constraintStart_toStartOf", layout.startToStart);
                writeXmlConstraint("app:layout_constraintTop_toBottomOf", layout.topToBottom);
                writeXmlConstraint("app:layout_constraintTop_toTopOf", layout.topToTop);
                String[] strArr = {"spread", "wrap", "percent"};
                writeEnum("app:layout_constraintHeight_default", layout.heightDefault, strArr, 0);
                writeVariable("app:layout_constraintHeight_percent", layout.heightPercent, 1.0f);
                writeDimension("app:layout_constraintHeight_min", layout.heightMin, 0);
                writeDimension("app:layout_constraintHeight_max", layout.heightMax, 0);
                writeBoolen("android:layout_constrainedHeight", layout.constrainedHeight, false);
                writeEnum("app:layout_constraintWidth_default", layout.widthDefault, strArr, 0);
                writeVariable("app:layout_constraintWidth_percent", layout.widthPercent, 1.0f);
                writeDimension("app:layout_constraintWidth_min", layout.widthMin, 0);
                writeDimension("app:layout_constraintWidth_max", layout.widthMax, 0);
                writeBoolen("android:layout_constrainedWidth", layout.constrainedWidth, false);
                writeVariable("app:layout_constraintVertical_weight", layout.verticalWeight, -1.0f);
                writeVariable("app:layout_constraintHorizontal_weight", layout.horizontalWeight, -1.0f);
                writeVariable("app:layout_constraintHorizontal_chainStyle", layout.horizontalChainStyle);
                writeVariable("app:layout_constraintVertical_chainStyle", layout.verticalChainStyle);
                writeEnum("app:barrierDirection", layout.mBarrierDirection, new String[]{"left", "right", "top", "bottom", "start", "end"}, -1);
                writeVariable("app:layout_constraintTag", layout.mConstraintTag, (String) null);
                if (layout.mReferenceIds != null) {
                    writeVariable("'ReferenceIds'", layout.mReferenceIds);
                }
                this.writer.write(" />\n");
            }
            this.writer.write("</ConstraintSet>\n");
        }

        private void writeBoolen(String dimString, boolean val, boolean def) throws IOException {
            if (val != def) {
                Writer writer = this.writer;
                StringBuilder sb = new StringBuilder(String.valueOf(dimString).length() + 18);
                sb.append(SPACE);
                sb.append(dimString);
                sb.append("=\"");
                sb.append(val);
                sb.append("dp\"");
                writer.write(sb.toString());
            }
        }

        private void writeEnum(String dimString, int val, String[] types, int def) throws IOException {
            if (val != def) {
                Writer writer = this.writer;
                String str = types[val];
                StringBuilder sb = new StringBuilder(String.valueOf(dimString).length() + 11 + String.valueOf(str).length());
                sb.append(SPACE);
                sb.append(dimString);
                sb.append("=\"");
                sb.append(str);
                sb.append("\"");
                writer.write(sb.toString());
            }
        }

        private void writeDimension(String dimString, int dim, int def) throws IOException {
            if (dim != def) {
                Writer writer = this.writer;
                StringBuilder sb = new StringBuilder(String.valueOf(dimString).length() + 24);
                sb.append(SPACE);
                sb.append(dimString);
                sb.append("=\"");
                sb.append(dim);
                sb.append("dp\"");
                writer.write(sb.toString());
            }
        }

        private void writeBaseDimension(String dimString, int dim, int def) throws IOException {
            if (dim != def) {
                if (dim == -2) {
                    Writer writer = this.writer;
                    StringBuilder sb = new StringBuilder(String.valueOf(dimString).length() + 23);
                    sb.append(SPACE);
                    sb.append(dimString);
                    sb.append("=\"wrap_content\"");
                    writer.write(sb.toString());
                    return;
                }
                if (dim == -1) {
                    Writer writer2 = this.writer;
                    StringBuilder sb2 = new StringBuilder(String.valueOf(dimString).length() + 23);
                    sb2.append(SPACE);
                    sb2.append(dimString);
                    sb2.append("=\"match_parent\"");
                    writer2.write(sb2.toString());
                    return;
                }
                Writer writer3 = this.writer;
                StringBuilder sb3 = new StringBuilder(String.valueOf(dimString).length() + 24);
                sb3.append(SPACE);
                sb3.append(dimString);
                sb3.append("=\"");
                sb3.append(dim);
                sb3.append("dp\"");
                writer3.write(sb3.toString());
            }
        }

        String getName(int id) {
            if (this.idMap.containsKey(Integer.valueOf(id))) {
                String str = this.idMap.get(Integer.valueOf(id));
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 5);
                sb.append("@+id/");
                sb.append(str);
                return sb.toString();
            }
            if (id == 0) {
                return "parent";
            }
            String lookup = lookup(id);
            this.idMap.put(Integer.valueOf(id), lookup);
            StringBuilder sb2 = new StringBuilder(String.valueOf(lookup).length() + 5);
            sb2.append("@+id/");
            sb2.append(lookup);
            return sb2.toString();
        }

        String lookup(int id) {
            try {
                if (id != -1) {
                    return this.context.getResources().getResourceEntryName(id);
                }
                int i = this.unknownCount + 1;
                this.unknownCount = i;
                StringBuilder sb = new StringBuilder(18);
                sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                sb.append(i);
                return sb.toString();
            } catch (Exception unused) {
                int i2 = this.unknownCount + 1;
                this.unknownCount = i2;
                StringBuilder sb2 = new StringBuilder(18);
                sb2.append(EnvironmentCompat.MEDIA_UNKNOWN);
                sb2.append(i2);
                return sb2.toString();
            }
        }

        void writeXmlConstraint(String str, int leftToLeft) throws IOException {
            if (leftToLeft == -1) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(str);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            Writer writer2 = this.writer;
            String name = getName(leftToLeft);
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 3);
            sb.append("=\"");
            sb.append(name);
            sb.append("\"");
            writer2.write(sb.toString());
        }

        void writeConstraint(String my, int leftToLeft, String other, int margin, int goneMargin) throws IOException {
            if (leftToLeft == -1) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(my);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            this.writer.write(":[");
            this.writer.write(getName(leftToLeft));
            this.writer.write(" , ");
            this.writer.write(other);
            if (margin != 0) {
                Writer writer2 = this.writer;
                StringBuilder sb = new StringBuilder(14);
                sb.append(" , ");
                sb.append(margin);
                writer2.write(sb.toString());
            }
            this.writer.write("],\n");
        }

        void writeCircle(int circleConstraint, float circleAngle, int circleRadius) throws IOException {
            if (circleConstraint == -1) {
                return;
            }
            this.writer.write("circle");
            this.writer.write(":[");
            this.writer.write(getName(circleConstraint));
            Writer writer = this.writer;
            StringBuilder sb = new StringBuilder(17);
            sb.append(", ");
            sb.append(circleAngle);
            writer.write(sb.toString());
            Writer writer2 = this.writer;
            StringBuilder sb2 = new StringBuilder(12);
            sb2.append(circleRadius);
            sb2.append("]");
            writer2.write(sb2.toString());
        }

        void writeVariable(String name, int value) throws IOException {
            if (value == 0 || value == -1) {
                return;
            }
            Writer writer = this.writer;
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 23);
            sb.append(SPACE);
            sb.append(name);
            sb.append("=\"");
            sb.append(value);
            sb.append("\"\n");
            writer.write(sb.toString());
        }

        void writeVariable(String name, float value, float def) throws IOException {
            if (value == def) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            Writer writer2 = this.writer;
            StringBuilder sb = new StringBuilder(18);
            sb.append("=\"");
            sb.append(value);
            sb.append("\"");
            writer2.write(sb.toString());
        }

        void writeVariable(String name, String value, String def) throws IOException {
            if (value == null || value.equals(def)) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            Writer writer2 = this.writer;
            StringBuilder sb = new StringBuilder(String.valueOf(value).length() + 3);
            sb.append("=\"");
            sb.append(value);
            sb.append("\"");
            writer2.write(sb.toString());
        }

        void writeVariable(String name, int[] value) throws IOException {
            if (value == null) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            this.writer.write(":");
            int i = 0;
            while (i < value.length) {
                Writer writer2 = this.writer;
                String str = i == 0 ? "[" : ", ";
                String valueOf2 = String.valueOf(getName(value[i]));
                writer2.write(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                i++;
            }
            this.writer.write("],\n");
        }

        void writeVariable(String name, String value) throws IOException {
            if (value == null) {
                return;
            }
            this.writer.write(name);
            this.writer.write(":");
            Writer writer = this.writer;
            String valueOf = String.valueOf(value);
            writer.write(valueOf.length() != 0 ? ", ".concat(valueOf) : new String(", "));
            this.writer.write("\n");
        }
    }

    /* loaded from: classes.dex */
    class WriteJsonEngine {
        private static final String SPACE = "       ";
        Context context;
        int flags;
        ConstraintLayout layout;
        Writer writer;
        int unknownCount = 0;
        final String LEFT = "'left'";
        final String RIGHT = "'right'";
        final String BASELINE = "'baseline'";
        final String BOTTOM = "'bottom'";
        final String TOP = "'top'";
        final String START = "'start'";
        final String END = "'end'";
        HashMap<Integer, String> idMap = new HashMap<>();

        private void writeGuideline(int orientation, int guideBegin, int guideEnd, float guidePercent) {
        }

        WriteJsonEngine(Writer writer, ConstraintLayout layout, int flags) throws IOException {
            this.writer = writer;
            this.layout = layout;
            this.context = layout.getContext();
            this.flags = flags;
        }

        void writeLayout() throws IOException {
            this.writer.write("\n'ConstraintSet':{\n");
            for (Integer num : ConstraintSet.access$1300(ConstraintSet.this).keySet()) {
                Constraint constraint = (Constraint) ConstraintSet.access$1300(ConstraintSet.this).get(num);
                this.writer.write(String.valueOf(getName(num.intValue())).concat(":{\n"));
                Layout layout = constraint.layout;
                writeDimension("height", layout.mHeight, layout.heightDefault, layout.heightPercent, layout.heightMin, layout.heightMax, layout.constrainedHeight);
                writeDimension("width", layout.mWidth, layout.widthDefault, layout.widthPercent, layout.widthMin, layout.widthMax, layout.constrainedWidth);
                writeConstraint("'left'", layout.leftToLeft, "'left'", layout.leftMargin, layout.goneLeftMargin);
                writeConstraint("'left'", layout.leftToRight, "'right'", layout.leftMargin, layout.goneLeftMargin);
                writeConstraint("'right'", layout.rightToLeft, "'left'", layout.rightMargin, layout.goneRightMargin);
                writeConstraint("'right'", layout.rightToRight, "'right'", layout.rightMargin, layout.goneRightMargin);
                writeConstraint("'baseline'", layout.baselineToBaseline, "'baseline'", -1, layout.goneBaselineMargin);
                writeConstraint("'baseline'", layout.baselineToTop, "'top'", -1, layout.goneBaselineMargin);
                writeConstraint("'baseline'", layout.baselineToBottom, "'bottom'", -1, layout.goneBaselineMargin);
                writeConstraint("'top'", layout.topToBottom, "'bottom'", layout.topMargin, layout.goneTopMargin);
                writeConstraint("'top'", layout.topToTop, "'top'", layout.topMargin, layout.goneTopMargin);
                writeConstraint("'bottom'", layout.bottomToBottom, "'bottom'", layout.bottomMargin, layout.goneBottomMargin);
                writeConstraint("'bottom'", layout.bottomToTop, "'top'", layout.bottomMargin, layout.goneBottomMargin);
                writeConstraint("'start'", layout.startToStart, "'start'", layout.startMargin, layout.goneStartMargin);
                writeConstraint("'start'", layout.startToEnd, "'end'", layout.startMargin, layout.goneStartMargin);
                writeConstraint("'end'", layout.endToStart, "'start'", layout.endMargin, layout.goneEndMargin);
                writeConstraint("'end'", layout.endToEnd, "'end'", layout.endMargin, layout.goneEndMargin);
                writeVariable("'horizontalBias'", layout.horizontalBias, 0.5f);
                writeVariable("'verticalBias'", layout.verticalBias, 0.5f);
                writeCircle(layout.circleConstraint, layout.circleAngle, layout.circleRadius);
                writeGuideline(layout.orientation, layout.guideBegin, layout.guideEnd, layout.guidePercent);
                writeVariable("'dimensionRatio'", layout.dimensionRatio);
                writeVariable("'barrierMargin'", layout.mBarrierMargin);
                writeVariable("'type'", layout.mHelperType);
                writeVariable("'ReferenceId'", layout.mReferenceIdString);
                writeVariable("'mBarrierAllowsGoneWidgets'", layout.mBarrierAllowsGoneWidgets, true);
                writeVariable("'WrapBehavior'", layout.mWrapBehavior);
                writeVariable("'verticalWeight'", layout.verticalWeight);
                writeVariable("'horizontalWeight'", layout.horizontalWeight);
                writeVariable("'horizontalChainStyle'", layout.horizontalChainStyle);
                writeVariable("'verticalChainStyle'", layout.verticalChainStyle);
                writeVariable("'barrierDirection'", layout.mBarrierDirection);
                if (layout.mReferenceIds != null) {
                    writeVariable("'ReferenceIds'", layout.mReferenceIds);
                }
                this.writer.write("}\n");
            }
            this.writer.write("}\n");
        }

        private void writeDimension(String dimString, int dim, int dimDefault, float dimPercent, int dimMin, int dimMax, boolean constrainedDim) throws IOException {
            if (dim != 0) {
                if (dim == -2) {
                    Writer writer = this.writer;
                    StringBuilder sb = new StringBuilder(String.valueOf(dimString).length() + 16);
                    sb.append(SPACE);
                    sb.append(dimString);
                    sb.append(": 'wrap'\n");
                    writer.write(sb.toString());
                    return;
                }
                if (dim == -1) {
                    Writer writer2 = this.writer;
                    StringBuilder sb2 = new StringBuilder(String.valueOf(dimString).length() + 18);
                    sb2.append(SPACE);
                    sb2.append(dimString);
                    sb2.append(": 'parent'\n");
                    writer2.write(sb2.toString());
                    return;
                }
                Writer writer3 = this.writer;
                StringBuilder sb3 = new StringBuilder(String.valueOf(dimString).length() + 22);
                sb3.append(SPACE);
                sb3.append(dimString);
                sb3.append(": ");
                sb3.append(dim);
                sb3.append(",\n");
                writer3.write(sb3.toString());
                return;
            }
            if (dimMax == -1 && dimMin == -1) {
                if (dimDefault != 0) {
                    if (dimDefault == 1) {
                        Writer writer4 = this.writer;
                        StringBuilder sb4 = new StringBuilder(String.valueOf(dimString).length() + 24);
                        sb4.append(SPACE);
                        sb4.append(dimString);
                        sb4.append(": '???????????',\n");
                        writer4.write(sb4.toString());
                        return;
                    }
                    if (dimDefault != 2) {
                        return;
                    }
                    Writer writer5 = this.writer;
                    StringBuilder sb5 = new StringBuilder(String.valueOf(dimString).length() + 29);
                    sb5.append(SPACE);
                    sb5.append(dimString);
                    sb5.append(": '");
                    sb5.append(dimPercent);
                    sb5.append("%',\n");
                    writer5.write(sb5.toString());
                    return;
                }
                return;
            }
            if (dimDefault == 0) {
                Writer writer6 = this.writer;
                StringBuilder sb6 = new StringBuilder(String.valueOf(dimString).length() + 46);
                sb6.append(SPACE);
                sb6.append(dimString);
                sb6.append(": {'spread' ,");
                sb6.append(dimMin);
                sb6.append(", ");
                sb6.append(dimMax);
                sb6.append("}\n");
                writer6.write(sb6.toString());
                return;
            }
            if (dimDefault == 1) {
                Writer writer7 = this.writer;
                StringBuilder sb7 = new StringBuilder(String.valueOf(dimString).length() + 44);
                sb7.append(SPACE);
                sb7.append(dimString);
                sb7.append(": {'wrap' ,");
                sb7.append(dimMin);
                sb7.append(", ");
                sb7.append(dimMax);
                sb7.append("}\n");
                writer7.write(sb7.toString());
                return;
            }
            if (dimDefault != 2) {
                return;
            }
            Writer writer8 = this.writer;
            StringBuilder sb8 = new StringBuilder(String.valueOf(dimString).length() + 56);
            sb8.append(SPACE);
            sb8.append(dimString);
            sb8.append(": {'");
            sb8.append(dimPercent);
            sb8.append("'% ,");
            sb8.append(dimMin);
            sb8.append(", ");
            sb8.append(dimMax);
            sb8.append("}\n");
            writer8.write(sb8.toString());
        }

        String getName(int id) {
            if (this.idMap.containsKey(Integer.valueOf(id))) {
                String str = this.idMap.get(Integer.valueOf(id));
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2);
                sb.append("'");
                sb.append(str);
                sb.append("'");
                return sb.toString();
            }
            if (id == 0) {
                return "'parent'";
            }
            String lookup = lookup(id);
            this.idMap.put(Integer.valueOf(id), lookup);
            StringBuilder sb2 = new StringBuilder(String.valueOf(lookup).length() + 2);
            sb2.append("'");
            sb2.append(lookup);
            sb2.append("'");
            return sb2.toString();
        }

        String lookup(int id) {
            try {
                if (id != -1) {
                    return this.context.getResources().getResourceEntryName(id);
                }
                int i = this.unknownCount + 1;
                this.unknownCount = i;
                StringBuilder sb = new StringBuilder(18);
                sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                sb.append(i);
                return sb.toString();
            } catch (Exception unused) {
                int i2 = this.unknownCount + 1;
                this.unknownCount = i2;
                StringBuilder sb2 = new StringBuilder(18);
                sb2.append(EnvironmentCompat.MEDIA_UNKNOWN);
                sb2.append(i2);
                return sb2.toString();
            }
        }

        void writeConstraint(String my, int leftToLeft, String other, int margin, int goneMargin) throws IOException {
            if (leftToLeft == -1) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(my);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            this.writer.write(":[");
            this.writer.write(getName(leftToLeft));
            this.writer.write(" , ");
            this.writer.write(other);
            if (margin != 0) {
                Writer writer2 = this.writer;
                StringBuilder sb = new StringBuilder(14);
                sb.append(" , ");
                sb.append(margin);
                writer2.write(sb.toString());
            }
            this.writer.write("],\n");
        }

        void writeCircle(int circleConstraint, float circleAngle, int circleRadius) throws IOException {
            if (circleConstraint == -1) {
                return;
            }
            this.writer.write("       circle");
            this.writer.write(":[");
            this.writer.write(getName(circleConstraint));
            Writer writer = this.writer;
            StringBuilder sb = new StringBuilder(17);
            sb.append(", ");
            sb.append(circleAngle);
            writer.write(sb.toString());
            Writer writer2 = this.writer;
            StringBuilder sb2 = new StringBuilder(12);
            sb2.append(circleRadius);
            sb2.append("]");
            writer2.write(sb2.toString());
        }

        void writeVariable(String name, int value) throws IOException {
            if (value == 0 || value == -1) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            this.writer.write(":");
            Writer writer2 = this.writer;
            StringBuilder sb = new StringBuilder(13);
            sb.append(", ");
            sb.append(value);
            writer2.write(sb.toString());
            this.writer.write("\n");
        }

        void writeVariable(String name, float value) throws IOException {
            if (value == -1.0f) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            Writer writer2 = this.writer;
            StringBuilder sb = new StringBuilder(17);
            sb.append(": ");
            sb.append(value);
            writer2.write(sb.toString());
            this.writer.write(",\n");
        }

        void writeVariable(String name, float value, float def) throws IOException {
            if (value == def) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            Writer writer2 = this.writer;
            StringBuilder sb = new StringBuilder(17);
            sb.append(": ");
            sb.append(value);
            writer2.write(sb.toString());
            this.writer.write(",\n");
        }

        void writeVariable(String name, boolean value) throws IOException {
            if (value) {
                Writer writer = this.writer;
                String valueOf = String.valueOf(name);
                writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
                Writer writer2 = this.writer;
                StringBuilder sb = new StringBuilder(7);
                sb.append(": ");
                sb.append(value);
                writer2.write(sb.toString());
                this.writer.write(",\n");
            }
        }

        void writeVariable(String name, boolean value, boolean def) throws IOException {
            if (value == def) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            Writer writer2 = this.writer;
            StringBuilder sb = new StringBuilder(7);
            sb.append(": ");
            sb.append(value);
            writer2.write(sb.toString());
            this.writer.write(",\n");
        }

        void writeVariable(String name, int[] value) throws IOException {
            if (value == null) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            this.writer.write(": ");
            int i = 0;
            while (i < value.length) {
                Writer writer2 = this.writer;
                String str = i == 0 ? "[" : ", ";
                String valueOf2 = String.valueOf(getName(value[i]));
                writer2.write(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                i++;
            }
            this.writer.write("],\n");
        }

        void writeVariable(String name, String value) throws IOException {
            if (value == null) {
                return;
            }
            Writer writer = this.writer;
            String valueOf = String.valueOf(name);
            writer.write(valueOf.length() != 0 ? SPACE.concat(valueOf) : new String(SPACE));
            this.writer.write(":");
            Writer writer2 = this.writer;
            String valueOf2 = String.valueOf(value);
            writer2.write(valueOf2.length() != 0 ? ", ".concat(valueOf2) : new String(", "));
            this.writer.write("\n");
        }
    }
}
