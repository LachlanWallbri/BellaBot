package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MaskParser {
    private MaskParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Mask parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        char c;
        jsonReader.beginObject();
        Mask.MaskMode maskMode = null;
        boolean z = false;
        AnimatableShapeValue animatableShapeValue = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            int hashCode = nextName.hashCode();
            char c2 = 65535;
            if (hashCode == 111) {
                if (nextName.equals("o")) {
                    c = 2;
                }
                c = 65535;
            } else if (hashCode == 3588) {
                if (nextName.equals("pt")) {
                    c = 1;
                }
                c = 65535;
            } else if (hashCode != 104433) {
                if (hashCode == 3357091 && nextName.equals("mode")) {
                    c = 0;
                }
                c = 65535;
            } else {
                if (nextName.equals("inv")) {
                    c = 3;
                }
                c = 65535;
            }
            if (c == 0) {
                String nextString = jsonReader.nextString();
                int hashCode2 = nextString.hashCode();
                if (hashCode2 != 97) {
                    if (hashCode2 != 105) {
                        if (hashCode2 != 110) {
                            if (hashCode2 == 115 && nextString.equals("s")) {
                                c2 = 1;
                            }
                        } else if (nextString.equals("n")) {
                            c2 = 2;
                        }
                    } else if (nextString.equals("i")) {
                        c2 = 3;
                    }
                } else if (nextString.equals("a")) {
                    c2 = 0;
                }
                if (c2 == 0) {
                    maskMode = Mask.MaskMode.MASK_MODE_ADD;
                } else if (c2 == 1) {
                    maskMode = Mask.MaskMode.MASK_MODE_SUBTRACT;
                } else if (c2 == 2) {
                    maskMode = Mask.MaskMode.MASK_MODE_NONE;
                } else if (c2 == 3) {
                    lottieComposition.addWarning("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                    maskMode = Mask.MaskMode.MASK_MODE_INTERSECT;
                } else {
                    Logger.warning("Unknown mask mode " + nextName + ". Defaulting to Add.");
                    maskMode = Mask.MaskMode.MASK_MODE_ADD;
                }
            } else if (c == 1) {
                animatableShapeValue = AnimatableValueParser.parseShapeData(jsonReader, lottieComposition);
            } else if (c == 2) {
                animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
            } else if (c == 3) {
                z = jsonReader.nextBoolean();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return new Mask(maskMode, animatableShapeValue, animatableIntegerValue, z);
    }
}
