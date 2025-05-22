package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.core.view.ViewCompat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CustomAttribute {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    /* loaded from: classes.dex */
    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    private static int clamp(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public static int hsvToRgb(float f, float f2, float f3) {
        float f4 = f * 6.0f;
        int i = (int) f4;
        float f5 = f4 - i;
        float f6 = f3 * 255.0f;
        int i2 = (int) (((1.0f - f2) * f6) + 0.5f);
        int i3 = (int) (((1.0f - (f5 * f2)) * f6) + 0.5f);
        int i4 = (int) (((1.0f - ((1.0f - f5) * f2)) * f6) + 0.5f);
        int i5 = (int) (f6 + 0.5f);
        if (i == 0) {
            return ((i5 << 16) + (i4 << 8) + i2) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i == 1) {
            return ((i3 << 16) + (i5 << 8) + i2) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i == 2) {
            return ((i2 << 16) + (i5 << 8) + i4) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i == 3) {
            return ((i2 << 16) + (i3 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i == 4) {
            return ((i4 << 16) + (i2 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i != 5) {
            return 0;
        }
        return ((i5 << 16) + (i2 << 8) + i3) | ViewCompat.MEASURED_STATE_MASK;
    }

    public AttributeType getType() {
        return this.mType;
    }

    public boolean isContinuous() {
        int i = C01521.f14xbc2a0812[this.mType.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? false : true;
    }

    public void setFloatValue(float f) {
        this.mFloatValue = f;
    }

    public void setColorValue(int i) {
        this.mColorValue = i;
    }

    public void setIntValue(int i) {
        this.mIntegerValue = i;
    }

    public void setStringValue(String str) {
        this.mStringValue = str;
    }

    public int numberOfInterpolatedValues() {
        int i = C01521.f14xbc2a0812[this.mType.ordinal()];
        return (i == 4 || i == 5) ? 4 : 1;
    }

    public float getValueToInterpolate() {
        switch (this.mType) {
            case BOOLEAN_TYPE:
                return this.mBooleanValue ? 1.0f : 0.0f;
            case STRING_TYPE:
                throw new RuntimeException("Cannot interpolate String");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case INT_TYPE:
                return this.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue;
            case DIMENSION_TYPE:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (this.mType) {
            case BOOLEAN_TYPE:
                fArr[0] = this.mBooleanValue ? 1.0f : 0.0f;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int i = (this.mColorValue >> 24) & 255;
                float pow = (float) Math.pow(((r0 >> 16) & 255) / 255.0f, 2.2d);
                float pow2 = (float) Math.pow(((r0 >> 8) & 255) / 255.0f, 2.2d);
                float pow3 = (float) Math.pow((r0 & 255) / 255.0f, 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = pow3;
                fArr[3] = i / 255.0f;
                return;
            case INT_TYPE:
                fArr[0] = this.mIntegerValue;
                return;
            case FLOAT_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            case DIMENSION_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public void setValue(float[] fArr) {
        switch (this.mType) {
            case REFERENCE_TYPE:
            case INT_TYPE:
                this.mIntegerValue = (int) fArr[0];
                return;
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((double) fArr[0]) > 0.5d;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = hsvToRgb(fArr[0], fArr[1], fArr[2]);
                this.mColorValue = (clamp((int) (fArr[3] * 255.0f)) << 24) | (this.mColorValue & 16777215);
                return;
            case FLOAT_TYPE:
                this.mFloatValue = fArr[0];
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = fArr[0];
                return;
            default:
                return;
        }
    }

    public boolean diff(CustomAttribute customAttribute) {
        if (customAttribute == null || this.mType != customAttribute.mType) {
            return false;
        }
        switch (this.mType) {
            case REFERENCE_TYPE:
            case INT_TYPE:
                return this.mIntegerValue == customAttribute.mIntegerValue;
            case BOOLEAN_TYPE:
                return this.mBooleanValue == customAttribute.mBooleanValue;
            case STRING_TYPE:
                return this.mIntegerValue == customAttribute.mIntegerValue;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                return this.mColorValue == customAttribute.mColorValue;
            case FLOAT_TYPE:
                return this.mFloatValue == customAttribute.mFloatValue;
            case DIMENSION_TYPE:
                return this.mFloatValue == customAttribute.mFloatValue;
            default:
                return false;
        }
    }

    public CustomAttribute(String str, AttributeType attributeType) {
        this.mMethod = false;
        this.mName = str;
        this.mType = attributeType;
    }

    public CustomAttribute(String str, AttributeType attributeType, Object obj, boolean z) {
        this.mMethod = false;
        this.mName = str;
        this.mType = attributeType;
        this.mMethod = z;
        setValue(obj);
    }

    public CustomAttribute(CustomAttribute customAttribute, Object obj) {
        this.mMethod = false;
        this.mName = customAttribute.mName;
        this.mType = customAttribute.mType;
        setValue(obj);
    }

    public void setValue(Object obj) {
        switch (this.mType) {
            case REFERENCE_TYPE:
            case INT_TYPE:
                this.mIntegerValue = ((Integer) obj).intValue();
                return;
            case BOOLEAN_TYPE:
                this.mBooleanValue = ((Boolean) obj).booleanValue();
                return;
            case STRING_TYPE:
                this.mStringValue = (String) obj;
                return;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.mColorValue = ((Integer) obj).intValue();
                return;
            case FLOAT_TYPE:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            case DIMENSION_TYPE:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }

    public static HashMap<String, CustomAttribute> extractAttributes(HashMap<String, CustomAttribute> hashMap, Object obj) {
        HashMap<String, CustomAttribute> hashMap2 = new HashMap<>();
        Class<?> cls = obj.getClass();
        for (String str : hashMap.keySet()) {
            try {
                hashMap2.put(str, new CustomAttribute(hashMap.get(str), cls.getMethod("getMap" + str, new Class[0]).invoke(obj, new Object[0])));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return hashMap2;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0047. Please report as an issue. */
    public static void setAttributes(Object obj, HashMap<String, CustomAttribute> hashMap) {
        Class<?> cls = obj.getClass();
        for (String str : hashMap.keySet()) {
            CustomAttribute customAttribute = hashMap.get(str);
            String str2 = customAttribute.mMethod ? str : TmpConstant.PROPERTY_IDENTIFIER_SET + str;
            try {
                switch (customAttribute.mType) {
                    case REFERENCE_TYPE:
                        cls.getMethod(str2, Integer.TYPE).invoke(obj, Integer.valueOf(customAttribute.mIntegerValue));
                        break;
                    case BOOLEAN_TYPE:
                        cls.getMethod(str2, Boolean.TYPE).invoke(obj, Boolean.valueOf(customAttribute.mBooleanValue));
                        break;
                    case STRING_TYPE:
                        cls.getMethod(str2, CharSequence.class).invoke(obj, customAttribute.mStringValue);
                        break;
                    case COLOR_TYPE:
                        cls.getMethod(str2, Integer.TYPE).invoke(obj, Integer.valueOf(customAttribute.mColorValue));
                        break;
                    case INT_TYPE:
                        cls.getMethod(str2, Integer.TYPE).invoke(obj, Integer.valueOf(customAttribute.mIntegerValue));
                        break;
                    case FLOAT_TYPE:
                        cls.getMethod(str2, Float.TYPE).invoke(obj, Float.valueOf(customAttribute.mFloatValue));
                        break;
                    case DIMENSION_TYPE:
                        cls.getMethod(str2, Float.TYPE).invoke(obj, Float.valueOf(customAttribute.mFloatValue));
                        break;
                }
            } catch (IllegalAccessException e) {
                Utils.loge(TAG, " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                Utils.loge(TAG, e2.getMessage());
                Utils.loge(TAG, " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                StringBuilder sb = new StringBuilder();
                sb.append(cls.getName());
                sb.append(" must have a method ");
                sb.append(str2);
                Utils.loge(TAG, sb.toString());
            } catch (InvocationTargetException e3) {
                Utils.loge(TAG, " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                e3.printStackTrace();
            }
        }
    }

    public void applyCustom(Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        String str2 = this.mName;
        if (this.mMethod) {
            str = str2;
        } else {
            str = TmpConstant.PROPERTY_IDENTIFIER_SET + str2;
        }
        try {
            switch (this.mType) {
                case REFERENCE_TYPE:
                case INT_TYPE:
                    cls.getMethod(str, Integer.TYPE).invoke(obj, Integer.valueOf(this.mIntegerValue));
                    return;
                case BOOLEAN_TYPE:
                    cls.getMethod(str, Boolean.TYPE).invoke(obj, Boolean.valueOf(this.mBooleanValue));
                    return;
                case STRING_TYPE:
                    cls.getMethod(str, CharSequence.class).invoke(obj, this.mStringValue);
                    return;
                case COLOR_TYPE:
                    cls.getMethod(str, Integer.TYPE).invoke(obj, Integer.valueOf(this.mColorValue));
                    return;
                case COLOR_DRAWABLE_TYPE:
                default:
                    return;
                case FLOAT_TYPE:
                    cls.getMethod(str, Float.TYPE).invoke(obj, Float.valueOf(this.mFloatValue));
                    return;
                case DIMENSION_TYPE:
                    cls.getMethod(str, Float.TYPE).invoke(obj, Float.valueOf(this.mFloatValue));
                    return;
            }
        } catch (IllegalAccessException e) {
            Utils.loge(TAG, " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            Utils.loge(TAG, e2.getMessage());
            Utils.loge(TAG, " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            sb.append(" must have a method ");
            sb.append(str);
            Utils.loge(TAG, sb.toString());
        } catch (InvocationTargetException e3) {
            Utils.loge(TAG, " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
            e3.printStackTrace();
        }
    }

    public void setInterpolatedValue(Object obj, float[] fArr) {
        Class<?> cls = obj.getClass();
        String str = TmpConstant.PROPERTY_IDENTIFIER_SET + this.mName;
        try {
            int i = C01521.f14xbc2a0812[this.mType.ordinal()];
            boolean z = true;
            if (i == 2) {
                Method method = cls.getMethod(str, Boolean.TYPE);
                Object[] objArr = new Object[1];
                if (fArr[0] <= 0.5f) {
                    z = false;
                }
                objArr[0] = Boolean.valueOf(z);
                method.invoke(obj, objArr);
                return;
            }
            if (i == 3) {
                throw new RuntimeException("unable to interpolate strings " + this.mName);
            }
            if (i == 4) {
                cls.getMethod(str, Integer.TYPE).invoke(obj, Integer.valueOf((clamp((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | (clamp((int) (fArr[3] * 255.0f)) << 24) | (clamp((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f)) << 16) | clamp((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f))));
                return;
            }
            if (i == 6) {
                cls.getMethod(str, Integer.TYPE).invoke(obj, Integer.valueOf((int) fArr[0]));
            } else if (i == 7) {
                cls.getMethod(str, Float.TYPE).invoke(obj, Float.valueOf(fArr[0]));
            } else {
                if (i != 8) {
                    return;
                }
                cls.getMethod(str, Float.TYPE).invoke(obj, Float.valueOf(fArr[0]));
            }
        } catch (IllegalAccessException e) {
            Utils.loge(TAG, "cannot access method " + str + " on View \"" + obj.getClass().getName() + "\"");
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            Utils.loge(TAG, "no method " + str + " on View \"" + obj.getClass().getName() + "\"");
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }
}
