package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.loc.C3898x;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ShapeStrokeParser {
    private static JsonReader.Options NAMES = JsonReader.Options.m82of("nm", "c", "w", "o", "lc", "lj", "ml", "hd", LinkFormat.DOMAIN);
    private static final JsonReader.Options DASH_PATTERN_NAMES = JsonReader.Options.m82of("n", "v");

    private ShapeStrokeParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ShapeStroke parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        char c;
        int i;
        ArrayList arrayList = new ArrayList();
        float f = 0.0f;
        String str = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableColorValue animatableColorValue = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int i2 = 1;
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    str = jsonReader.nextString();
                    break;
                case 1:
                    animatableColorValue = AnimatableValueParser.parseColor(jsonReader, lottieComposition);
                    break;
                case 2:
                    animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                    break;
                case 3:
                    animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
                    break;
                case 4:
                    lineCapType = ShapeStroke.LineCapType.values()[jsonReader.nextInt() - 1];
                    break;
                case 5:
                    lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.nextInt() - 1];
                    break;
                case 6:
                    f = (float) jsonReader.nextDouble();
                    break;
                case 7:
                    z = jsonReader.nextBoolean();
                    break;
                case 8:
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        String str2 = null;
                        AnimatableFloatValue animatableFloatValue3 = null;
                        while (jsonReader.hasNext()) {
                            int selectName = jsonReader.selectName(DASH_PATTERN_NAMES);
                            if (selectName == 0) {
                                str2 = jsonReader.nextString();
                            } else if (selectName == i2) {
                                animatableFloatValue3 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                            } else {
                                jsonReader.skipName();
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        int hashCode = str2.hashCode();
                        if (hashCode == 100) {
                            if (str2.equals(LinkFormat.DOMAIN)) {
                                c = 1;
                            }
                            c = 65535;
                        } else if (hashCode != 103) {
                            if (hashCode == 111 && str2.equals("o")) {
                                c = 0;
                            }
                            c = 65535;
                        } else {
                            if (str2.equals(C3898x.f4336e)) {
                                c = 2;
                            }
                            c = 65535;
                        }
                        if (c != 0) {
                            i = 1;
                            if (c == 1 || c == 2) {
                                lottieComposition.setHasDashPattern(true);
                                arrayList.add(animatableFloatValue3);
                            }
                        } else {
                            i = 1;
                            animatableFloatValue = animatableFloatValue3;
                        }
                        i2 = i;
                    }
                    int i3 = i2;
                    jsonReader.endArray();
                    if (arrayList.size() != i3) {
                        break;
                    } else {
                        arrayList.add(arrayList.get(0));
                        break;
                    }
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        return new ShapeStroke(str, animatableFloatValue, arrayList, animatableColorValue, animatableIntegerValue, animatableFloatValue2, lineCapType, lineJoinType, f, z);
    }
}
