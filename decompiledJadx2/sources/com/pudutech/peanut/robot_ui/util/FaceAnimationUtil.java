package com.pudutech.peanut.robot_ui.util;

import android.graphics.Bitmap;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: FaceAnimationUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JE\u0010\u0013\u001a\u00020\u00122=\u0010\u0014\u001a9\u0012/\u0012-\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u000ej\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00120\rJ\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0016J\u0006\u0010\u001a\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000RG\u0010\f\u001a;\u0012/\u0012-\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u000ej\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/util/FaceAnimationUtil;", "", "()V", "TAG", "", "cacheImages", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Landroid/graphics/Bitmap;", "isLoadedRobotImage", "", "isLoadingRobotImage", "loadRobotImageCallBack", "Lkotlin/Function1;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "Lkotlin/ParameterName;", "name", "", "getCacheImage", "callBack", "getCustomerModeAnimation", "", "getDeliveryBackToHomeAnimation", "getGuideBackToHomeAnimation", "getRobotAnimation", "loadRobotAnimation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FaceAnimationUtil {
    private static boolean isLoadedRobotImage;
    private static boolean isLoadingRobotImage;
    private static Function1<? super HashMap<Integer, Bitmap>, Unit> loadRobotImageCallBack;
    public static final FaceAnimationUtil INSTANCE = new FaceAnimationUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ConcurrentHashMap<Integer, Bitmap> cacheImages = new ConcurrentHashMap<>();

    private FaceAnimationUtil() {
    }

    public final void loadRobotAnimation() {
        if (isLoadingRobotImage || isLoadedRobotImage) {
            return;
        }
        isLoadingRobotImage = true;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new FaceAnimationUtil$loadRobotAnimation$1(null), 2, null);
    }

    public final void getCacheImage(Function1<? super HashMap<Integer, Bitmap>, Unit> callBack) {
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        if (isLoadedRobotImage) {
            HashMap hashMap = new HashMap();
            hashMap.putAll(cacheImages);
            callBack.invoke(hashMap);
        } else if (isLoadingRobotImage) {
            loadRobotImageCallBack = callBack;
        } else {
            Pdlog.m3274e(TAG, "getCacheImage : not load img ");
        }
    }

    public final int[] getRobotAnimation() {
        return new int[]{C5508R.drawable.animation_deliverydode_00000, C5508R.drawable.animation_deliverydode_00001, C5508R.drawable.animation_deliverydode_00002, C5508R.drawable.animation_deliverydode_00003, C5508R.drawable.animation_deliverydode_00004, C5508R.drawable.animation_deliverydode_00005, C5508R.drawable.animation_deliverydode_00006, C5508R.drawable.animation_deliverydode_00007, C5508R.drawable.animation_deliverydode_00008, C5508R.drawable.animation_deliverydode_00009, C5508R.drawable.animation_deliverydode_00010, C5508R.drawable.animation_deliverydode_00011, C5508R.drawable.animation_deliverydode_00012, C5508R.drawable.animation_deliverydode_00013, C5508R.drawable.animation_deliverydode_00014, C5508R.drawable.animation_deliverydode_00015, C5508R.drawable.animation_deliverydode_00016, C5508R.drawable.animation_deliverydode_00017, C5508R.drawable.animation_deliverydode_00018, C5508R.drawable.animation_deliverydode_00019, C5508R.drawable.animation_deliverydode_00020, C5508R.drawable.animation_deliverydode_00021, C5508R.drawable.animation_deliverydode_00022, C5508R.drawable.animation_deliverydode_00023, C5508R.drawable.animation_deliverydode_00024, C5508R.drawable.animation_deliverydode_00025, C5508R.drawable.animation_deliverydode_00026, C5508R.drawable.animation_deliverydode_00027, C5508R.drawable.animation_deliverydode_00028, C5508R.drawable.animation_deliverydode_00029, C5508R.drawable.animation_deliverydode_00030, C5508R.drawable.animation_deliverydode_00031, C5508R.drawable.animation_deliverydode_00032, C5508R.drawable.animation_deliverydode_00033, C5508R.drawable.animation_deliverydode_00034, C5508R.drawable.animation_deliverydode_00035, C5508R.drawable.animation_deliverydode_00036, C5508R.drawable.animation_deliverydode_00037};
    }

    public final int[] getDeliveryBackToHomeAnimation() {
        return new int[]{C5508R.drawable.animation_deliverymode_backhome_00000, C5508R.drawable.animation_deliverymode_backhome_00001, C5508R.drawable.animation_deliverymode_backhome_00002, C5508R.drawable.animation_deliverymode_backhome_00003, C5508R.drawable.animation_deliverymode_backhome_00004, C5508R.drawable.animation_deliverymode_backhome_00005, C5508R.drawable.animation_deliverymode_backhome_00006, C5508R.drawable.animation_deliverymode_backhome_00007, C5508R.drawable.animation_deliverymode_backhome_00008, C5508R.drawable.animation_deliverymode_backhome_00009, C5508R.drawable.animation_deliverymode_backhome_00010, C5508R.drawable.animation_deliverymode_backhome_00011, C5508R.drawable.animation_deliverymode_backhome_00012, C5508R.drawable.animation_deliverymode_backhome_00013, C5508R.drawable.animation_deliverymode_backhome_00014, C5508R.drawable.animation_deliverymode_backhome_00015, C5508R.drawable.animation_deliverymode_backhome_00016, C5508R.drawable.animation_deliverymode_backhome_00017, C5508R.drawable.animation_deliverymode_backhome_00018, C5508R.drawable.animation_deliverymode_backhome_00019, C5508R.drawable.animation_deliverymode_backhome_00020, C5508R.drawable.animation_deliverymode_backhome_00021, C5508R.drawable.animation_deliverymode_backhome_00022, C5508R.drawable.animation_deliverymode_backhome_00023, C5508R.drawable.animation_deliverymode_backhome_00024, C5508R.drawable.animation_deliverymode_backhome_00025, C5508R.drawable.animation_deliverymode_backhome_00026, C5508R.drawable.animation_deliverymode_backhome_00027, C5508R.drawable.animation_deliverymode_backhome_00028, C5508R.drawable.animation_deliverymode_backhome_00029, C5508R.drawable.animation_deliverymode_backhome_00030, C5508R.drawable.animation_deliverymode_backhome_00031, C5508R.drawable.animation_deliverymode_backhome_00032, C5508R.drawable.animation_deliverymode_backhome_00033, C5508R.drawable.animation_deliverymode_backhome_00034, C5508R.drawable.animation_deliverymode_backhome_00035, C5508R.drawable.animation_deliverymode_backhome_00036, C5508R.drawable.animation_deliverymode_backhome_00037};
    }

    public final int[] getGuideBackToHomeAnimation() {
        return new int[]{C5508R.drawable.animation_guidemode_backhome_00000, C5508R.drawable.animation_guidemode_backhome_00001, C5508R.drawable.animation_guidemode_backhome_00002, C5508R.drawable.animation_guidemode_backhome_00003, C5508R.drawable.animation_guidemode_backhome_00004, C5508R.drawable.animation_guidemode_backhome_00005, C5508R.drawable.animation_guidemode_backhome_00006, C5508R.drawable.animation_guidemode_backhome_00007, C5508R.drawable.animation_guidemode_backhome_00008, C5508R.drawable.animation_guidemode_backhome_00009, C5508R.drawable.animation_guidemode_backhome_00010, C5508R.drawable.animation_guidemode_backhome_00011, C5508R.drawable.animation_guidemode_backhome_00012, C5508R.drawable.animation_guidemode_backhome_00013, C5508R.drawable.animation_guidemode_backhome_00014, C5508R.drawable.animation_guidemode_backhome_00015, C5508R.drawable.animation_guidemode_backhome_00016, C5508R.drawable.animation_guidemode_backhome_00017, C5508R.drawable.animation_guidemode_backhome_00018, C5508R.drawable.animation_guidemode_backhome_00019, C5508R.drawable.animation_guidemode_backhome_00020, C5508R.drawable.animation_guidemode_backhome_00021, C5508R.drawable.animation_guidemode_backhome_00022, C5508R.drawable.animation_guidemode_backhome_00023, C5508R.drawable.animation_guidemode_backhome_00024};
    }

    public final int[] getCustomerModeAnimation() {
        return new int[]{C5508R.drawable.animation_guide_00000, C5508R.drawable.animation_guide_00001, C5508R.drawable.animation_guide_00002, C5508R.drawable.animation_guide_00003, C5508R.drawable.animation_guide_00004, C5508R.drawable.animation_guide_00005, C5508R.drawable.animation_guide_00006, C5508R.drawable.animation_guide_00007, C5508R.drawable.animation_guide_00008, C5508R.drawable.animation_guide_00009, C5508R.drawable.animation_guide_00010, C5508R.drawable.animation_guide_00011, C5508R.drawable.animation_guide_00012, C5508R.drawable.animation_guide_00013, C5508R.drawable.animation_guide_00014, C5508R.drawable.animation_guide_00015, C5508R.drawable.animation_guide_00016, C5508R.drawable.animation_guide_00017, C5508R.drawable.animation_guide_00018, C5508R.drawable.animation_guide_00019, C5508R.drawable.animation_guide_00020, C5508R.drawable.animation_guide_00021, C5508R.drawable.animation_guide_00022, C5508R.drawable.animation_guide_00023, C5508R.drawable.animation_guide_00024};
    }
}
