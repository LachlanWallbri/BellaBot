package com.pudutech.bumblebee.presenter.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Pattern;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CharacterHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/CharacterHandler;", "", "()V", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CharacterHandler {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final InputFilter EMOJI_FILTER = new InputFilter() { // from class: com.pudutech.bumblebee.presenter.utils.CharacterHandler$Companion$EMOJI_FILTER$1
        private Pattern emoji = Pattern.compile("[🀀-🏿]|[🐀-\u1f7ff]|[☀-⟿]", 66);

        public final Pattern getEmoji() {
            return this.emoji;
        }

        public final void setEmoji(Pattern pattern) {
            this.emoji = pattern;
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            Intrinsics.checkParameterIsNotNull(dest, "dest");
            if (this.emoji.matcher(source).find()) {
                return "";
            }
            return null;
        }
    };

    @JvmStatic
    public static final String jsonFormat(String str) {
        return INSTANCE.jsonFormat(str);
    }

    @JvmStatic
    public static final String xmlFormat(String str) {
        return INSTANCE.xmlFormat(str);
    }

    private CharacterHandler() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /* compiled from: CharacterHandler.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0014\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/CharacterHandler$Companion;", "", "()V", "EMOJI_FILTER", "Landroid/text/InputFilter;", "getEMOJI_FILTER", "()Landroid/text/InputFilter;", "jsonFormat", "", "json", "xmlFormat", "xml", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InputFilter getEMOJI_FILTER() {
            return CharacterHandler.EMOJI_FILTER;
        }

        @JvmStatic
        public final String jsonFormat(String json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            if (TextUtils.isEmpty(json)) {
                return "Empty/Null json content";
            }
            try {
                String str = json;
                int length = str.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = str.charAt(!z ? i : length) <= ' ';
                    if (z) {
                        if (!z2) {
                            break;
                        }
                        length--;
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                String obj = str.subSequence(i, length + 1).toString();
                if (StringsKt.startsWith$default(obj, "{", false, 2, (Object) null)) {
                    String jSONObject = new JSONObject(obj).toString(4);
                    Intrinsics.checkExpressionValueIsNotNull(jSONObject, "jsonObject.toString(4)");
                    return jSONObject;
                }
                String jSONArray = StringsKt.startsWith$default(obj, "[", false, 2, (Object) null) ? new JSONArray(obj).toString(4) : obj;
                Intrinsics.checkExpressionValueIsNotNull(jSONArray, "if (json.startsWith(\"[\")…   json\n                }");
                return jSONArray;
            } catch (OutOfMemoryError unused) {
                return "Output omitted because of object size";
            } catch (JSONException unused2) {
                return json;
            }
        }

        @JvmStatic
        public final String xmlFormat(String xml) {
            if (TextUtils.isEmpty(xml)) {
                return "Empty/Null xml content";
            }
            try {
                StreamSource streamSource = new StreamSource(new StringReader(xml));
                StreamResult streamResult = new StreamResult(new StringWriter());
                Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
                newTransformer.setOutputProperty("indent", "yes");
                newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amout", "2");
                newTransformer.transform(streamSource, streamResult);
                return new Regex(">").replaceFirst(streamResult.getWriter().toString(), ">\n");
            } catch (TransformerException unused) {
                return xml;
            }
        }
    }
}
