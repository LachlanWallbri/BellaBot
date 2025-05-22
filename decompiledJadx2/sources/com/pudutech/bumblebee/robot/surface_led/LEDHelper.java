package com.pudutech.bumblebee.robot.surface_led;

import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LEDHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/surface_led/LEDHelper;", "", "()V", "getAllHolabot", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/SurfaceLED;", "getAllPallets", "getDisinfection", "getEars", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LEDHelper {
    public static final LEDHelper INSTANCE = new LEDHelper();

    private LEDHelper() {
    }

    public final List<SurfaceLED> getAllHolabot() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(SurfaceLED.Bottom);
        SurfaceLED[] values = SurfaceLED.values();
        ArrayList arrayList2 = new ArrayList();
        for (SurfaceLED surfaceLED : values) {
            if (StringsKt.contains$default((CharSequence) surfaceLED.name(), (CharSequence) "Hola", false, 2, (Object) null)) {
                arrayList2.add(surfaceLED);
            }
        }
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    public final List<SurfaceLED> getDisinfection() {
        SurfaceLED[] values = SurfaceLED.values();
        ArrayList arrayList = new ArrayList();
        for (SurfaceLED surfaceLED : values) {
            if (StringsKt.contains$default((CharSequence) surfaceLED.name(), (CharSequence) "Disinfection", false, 2, (Object) null)) {
                arrayList.add(surfaceLED);
            }
        }
        return arrayList;
    }

    public final List<SurfaceLED> getEars() {
        SurfaceLED[] values = SurfaceLED.values();
        ArrayList arrayList = new ArrayList();
        for (SurfaceLED surfaceLED : values) {
            if (StringsKt.contains$default((CharSequence) surfaceLED.name(), (CharSequence) "Ear", false, 2, (Object) null)) {
                arrayList.add(surfaceLED);
            }
        }
        return arrayList;
    }

    public final List<SurfaceLED> getAllPallets() {
        SurfaceLED[] values = SurfaceLED.values();
        ArrayList arrayList = new ArrayList();
        for (SurfaceLED surfaceLED : values) {
            if (StringsKt.contains$default((CharSequence) surfaceLED.name(), (CharSequence) "Pallet", false, 2, (Object) null)) {
                arrayList.add(surfaceLED);
            }
        }
        return arrayList;
    }
}
