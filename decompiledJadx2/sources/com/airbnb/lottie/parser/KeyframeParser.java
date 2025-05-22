package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.collection.SparseArrayCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import com.loc.C3898x;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
class KeyframeParser {
    private static final float MAX_CP_VALUE = 100.0f;
    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    static JsonReader.Options NAMES = JsonReader.Options.m82of("t", "s", C3898x.f4338g, "o", "i", "h", TypedValues.Transition.S_TO, "ti");

    KeyframeParser() {
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache() {
        if (pathInterpolatorCache == null) {
            pathInterpolatorCache = new SparseArrayCompat<>();
        }
        return pathInterpolatorCache;
    }

    private static WeakReference<Interpolator> getInterpolator(int i) {
        WeakReference<Interpolator> weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = pathInterpolatorCache().get(i);
        }
        return weakReference;
    }

    private static void putInterpolator(int i, WeakReference<Interpolator> weakReference) {
        synchronized (KeyframeParser.class) {
            pathInterpolatorCache.put(i, weakReference);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Keyframe<T> parse(JsonReader jsonReader, LottieComposition lottieComposition, float f, ValueParser<T> valueParser, boolean z) throws IOException {
        if (z) {
            return parseKeyframe(lottieComposition, jsonReader, f, valueParser);
        }
        return parseStaticValue(jsonReader, f, valueParser);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0020. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:5:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static <T> Keyframe<T> parseKeyframe(LottieComposition lottieComposition, JsonReader jsonReader, float f, ValueParser<T> valueParser) throws IOException {
        Interpolator interpolator;
        T t;
        jsonReader.beginObject();
        PointF pointF = null;
        PointF pointF2 = null;
        T t2 = null;
        T t3 = null;
        PointF pointF3 = null;
        PointF pointF4 = null;
        float f2 = 0.0f;
        while (true) {
            boolean z = false;
            while (jsonReader.hasNext()) {
                switch (jsonReader.selectName(NAMES)) {
                    case 0:
                        f2 = (float) jsonReader.nextDouble();
                    case 1:
                        t3 = valueParser.parse(jsonReader, f);
                    case 2:
                        t2 = valueParser.parse(jsonReader, f);
                    case 3:
                        pointF = JsonUtils.jsonToPoint(jsonReader, f);
                    case 4:
                        pointF2 = JsonUtils.jsonToPoint(jsonReader, f);
                    case 5:
                        if (jsonReader.nextInt() == 1) {
                            z = true;
                        }
                        break;
                    case 6:
                        pointF3 = JsonUtils.jsonToPoint(jsonReader, f);
                    case 7:
                        pointF4 = JsonUtils.jsonToPoint(jsonReader, f);
                    default:
                        jsonReader.skipValue();
                }
                while (jsonReader.hasNext()) {
                }
            }
            jsonReader.endObject();
            if (z) {
                interpolator = LINEAR_INTERPOLATOR;
                t = t3;
            } else {
                if (pointF != null && pointF2 != null) {
                    float f3 = -f;
                    pointF.x = MiscUtils.clamp(pointF.x, f3, f);
                    pointF.y = MiscUtils.clamp(pointF.y, -100.0f, MAX_CP_VALUE);
                    pointF2.x = MiscUtils.clamp(pointF2.x, f3, f);
                    pointF2.y = MiscUtils.clamp(pointF2.y, -100.0f, MAX_CP_VALUE);
                    int hashFor = Utils.hashFor(pointF.x, pointF.y, pointF2.x, pointF2.y);
                    WeakReference<Interpolator> interpolator2 = getInterpolator(hashFor);
                    Interpolator interpolator3 = interpolator2 != null ? interpolator2.get() : null;
                    if (interpolator2 == null || interpolator3 == null) {
                        interpolator3 = PathInterpolatorCompat.create(pointF.x / f, pointF.y / f, pointF2.x / f, pointF2.y / f);
                        try {
                            putInterpolator(hashFor, new WeakReference(interpolator3));
                        } catch (ArrayIndexOutOfBoundsException unused) {
                        }
                    }
                    interpolator = interpolator3;
                } else {
                    interpolator = LINEAR_INTERPOLATOR;
                }
                t = t2;
            }
            Keyframe<T> keyframe = new Keyframe<>(lottieComposition, t3, t, interpolator, f2, null);
            keyframe.pathCp1 = pointF3;
            keyframe.pathCp2 = pointF4;
            return keyframe;
        }
    }

    private static <T> Keyframe<T> parseStaticValue(JsonReader jsonReader, float f, ValueParser<T> valueParser) throws IOException {
        return new Keyframe<>(valueParser.parse(jsonReader, f));
    }
}
