package com.pudutech.bumblebee.business.peripherals_task.led_task;

import android.content.Context;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.resources.led.LEDItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LEDAnimationParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDAnimationParser;", "", "()V", TmpConstant.PROPERTY_IDENTIFIER_GET, "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Animation;", "item", "Lcom/pudutech/resources/led/LEDItem;", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LEDAnimationParser {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String path = path;
    private static final String path = path;
    private static final LinkedHashMap<String, Animation> all = new LinkedHashMap<>();
    private static final Gson gson = new Gson();

    /* compiled from: LEDAnimationParser.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDAnimationParser$Companion;", "", "()V", "TAG", "", "all", "Ljava/util/LinkedHashMap;", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Animation;", "Lkotlin/collections/LinkedHashMap;", "gson", "Lcom/google/gson/Gson;", "path", "fromJson", "string", "loadFromFiles", "", "appContext", "Landroid/content/Context;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Animation fromJson(String string) {
            String str = string;
            if (str == null || str.length() == 0) {
                Pdlog.m3274e(LEDAnimationParser.TAG, "wrong json=" + string);
                return null;
            }
            Animation animation = (Animation) null;
            try {
                return (Animation) LEDAnimationParser.gson.fromJson(string, Animation.class);
            } catch (Exception e) {
                Pdlog.m3274e(LEDAnimationParser.TAG, "wrong json=" + string + " e=" + e);
                return animation;
            }
        }

        public final void loadFromFiles(Context appContext) {
            ArrayList<Frame> arrayList;
            ArrayList<Frame> arrayList2;
            Intrinsics.checkParameterIsNotNull(appContext, "appContext");
            Animation animation = new Animation();
            animation.frames.add(new Frame());
            Pdlog.m3273d(LEDAnimationParser.TAG, "json style: " + LEDAnimationParser.gson.toJson(animation));
            String[] list = appContext.getAssets().list(LEDAnimationParser.path);
            if (list != null) {
                for (String it : list) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(appContext.getAssets().open(LEDAnimationParser.path + File.separator + it)));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else {
                            sb.append(readLine);
                        }
                    }
                    String sb2 = sb.toString();
                    Pdlog.m3273d(LEDAnimationParser.TAG, "load file " + it + ":  " + sb2);
                    Animation fromJson = LEDAnimationParser.INSTANCE.fromJson(sb2);
                    if (fromJson != null && (arrayList2 = fromJson.frames) != null) {
                        arrayList2.removeIf(new Predicate<Frame>() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_task.LEDAnimationParser$Companion$loadFromFiles$1$1
                            @Override // java.util.function.Predicate
                            public final boolean test(Frame frame) {
                                return frame == null;
                            }
                        });
                    }
                    if (fromJson != null) {
                        LinkedHashMap linkedHashMap = LEDAnimationParser.all;
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        linkedHashMap.put(StringsKt.replace$default(it, ".json", "", false, 4, (Object) null), fromJson);
                    }
                }
            }
            String str = LEDAnimationParser.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb3 = new StringBuilder();
            sb3.append("after load asset. size=");
            sb3.append(LEDAnimationParser.all.size());
            sb3.append("  fileSize=");
            sb3.append(list != null ? Integer.valueOf(list.length) : null);
            objArr[0] = sb3.toString();
            Pdlog.m3275i(str, objArr);
            File file = new File("sdcard" + File.separator + LEDAnimationParser.path);
            if (!file.exists()) {
                Pdlog.m3275i(LEDAnimationParser.TAG, LEDAnimationParser.path + " not exist in sdcard");
                return;
            }
            File[] listFiles = file.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "sdcardFile.listFiles()");
            for (File it2 : listFiles) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                if (it2.isFile()) {
                    String readText = TextStreamsKt.readText(new FileReader(it2));
                    Pdlog.m3275i(LEDAnimationParser.TAG, "read from sdcard. " + it2.getName() + " : " + readText);
                    Animation fromJson2 = LEDAnimationParser.INSTANCE.fromJson(readText);
                    if (fromJson2 != null && (arrayList = fromJson2.frames) != null) {
                        arrayList.removeIf(new Predicate<Frame>() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_task.LEDAnimationParser$Companion$loadFromFiles$2$1
                            @Override // java.util.function.Predicate
                            public final boolean test(Frame frame) {
                                return frame == null;
                            }
                        });
                    }
                    if (fromJson2 != null) {
                        LinkedHashMap linkedHashMap2 = LEDAnimationParser.all;
                        String name = it2.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                        linkedHashMap2.put(StringsKt.replace$default(name, ".json", "", false, 4, (Object) null), fromJson2);
                    }
                }
            }
            Pdlog.m3275i(LEDAnimationParser.TAG, "after load asset. size=" + LEDAnimationParser.all.size() + "  fileSize=" + file.list().length);
            Iterator it3 = LEDAnimationParser.all.entrySet().iterator();
            while (it3.hasNext()) {
                Pdlog.m3275i(LEDAnimationParser.TAG, ((String) ((Map.Entry) it3.next()).getKey()) + ',');
            }
        }
    }

    public final Animation get(LEDItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (all.size() == 0) {
            Pdlog.m3274e(TAG, "not init yet or no animation file");
        }
        return all.get(item.name());
    }
}
