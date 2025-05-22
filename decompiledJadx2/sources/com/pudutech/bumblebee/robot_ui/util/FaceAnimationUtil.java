package com.pudutech.bumblebee.robot_ui.util;

import android.graphics.Bitmap;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JE\u0010\u0013\u001a\u00020\u00122=\u0010\u0014\u001a9\u0012/\u0012-\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u000ej\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00120\rJ\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000RG\u0010\f\u001a;\u0012/\u0012-\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u000ej\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/FaceAnimationUtil;", "", "()V", "TAG", "", "cacheImages", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Landroid/graphics/Bitmap;", "isLoadedRobotImage", "", "isLoadingRobotImage", "loadRobotImageCallBack", "Lkotlin/Function1;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "Lkotlin/ParameterName;", "name", "", "getCacheImage", "callBack", "getRobotAnimation", "", "loadRobotAnimation", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
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
        return new int[]{C4188R.drawable.home_animation_00000, C4188R.drawable.home_animation_00001, C4188R.drawable.home_animation_00002, C4188R.drawable.home_animation_00003, C4188R.drawable.home_animation_00004, C4188R.drawable.home_animation_00005, C4188R.drawable.home_animation_00006, C4188R.drawable.home_animation_00007, C4188R.drawable.home_animation_00008, C4188R.drawable.home_animation_00009, C4188R.drawable.home_animation_00010, C4188R.drawable.home_animation_00011, C4188R.drawable.home_animation_00012, C4188R.drawable.home_animation_00013, C4188R.drawable.home_animation_00014, C4188R.drawable.home_animation_00015, C4188R.drawable.home_animation_00016, C4188R.drawable.home_animation_00017, C4188R.drawable.home_animation_00018, C4188R.drawable.home_animation_00019, C4188R.drawable.home_animation_00020, C4188R.drawable.home_animation_00021, C4188R.drawable.home_animation_00022, C4188R.drawable.home_animation_00023, C4188R.drawable.home_animation_00024, C4188R.drawable.home_animation_00025, C4188R.drawable.home_animation_00026, C4188R.drawable.home_animation_00027, C4188R.drawable.home_animation_00028, C4188R.drawable.home_animation_00029};
    }
}
