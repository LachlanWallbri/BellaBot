package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.Repeater;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RepeaterParser {
    private static JsonReader.Options NAMES = JsonReader.Options.m82of("nm", "c", "o", "tr", "hd");

    private RepeaterParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Repeater parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        boolean z = false;
        String str = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableTransform animatableTransform = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                str = jsonReader.nextString();
            } else if (selectName == 1) {
                animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, false);
            } else if (selectName == 2) {
                animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, false);
            } else if (selectName == 3) {
                animatableTransform = AnimatableTransformParser.parse(jsonReader, lottieComposition);
            } else if (selectName == 4) {
                z = jsonReader.nextBoolean();
            } else {
                jsonReader.skipValue();
            }
        }
        return new Repeater(str, animatableFloatValue, animatableFloatValue2, animatableTransform, z);
    }
}
