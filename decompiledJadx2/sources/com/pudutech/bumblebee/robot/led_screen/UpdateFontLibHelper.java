package com.pudutech.bumblebee.robot.led_screen;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.IUpdateListener;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateObject;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import java.io.File;
import java.io.InputStream;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: UpdateFontLibHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/led_screen/UpdateFontLibHelper;", "", "()V", "updateFontLib", "Lcom/pudutech/bumblebee/robot/led_screen/UpdateFontLib;", "getUpdateFontLib", "()Lcom/pudutech/bumblebee/robot/led_screen/UpdateFontLib;", "updateFontLib$delegate", "Lkotlin/Lazy;", "update", "", "context", "Landroid/content/Context;", "inf", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "listener", "Lcom/pudutech/bumblebee/robot/aidl/IUpdateListener;", "isForce", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class UpdateFontLibHelper {
    public static final UpdateFontLibHelper INSTANCE = new UpdateFontLibHelper();

    /* renamed from: updateFontLib$delegate, reason: from kotlin metadata */
    private static final Lazy updateFontLib = LazyKt.lazy(new Function0<UpdateFontLib>() { // from class: com.pudutech.bumblebee.robot.led_screen.UpdateFontLibHelper$updateFontLib$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final UpdateFontLib invoke() {
            return new UpdateFontLib();
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final UpdateFontLib getUpdateFontLib() {
        return (UpdateFontLib) updateFontLib.getValue();
    }

    private UpdateFontLibHelper() {
    }

    public static /* synthetic */ void update$default(UpdateFontLibHelper updateFontLibHelper, Context context, HardwareInterface hardwareInterface, IUpdateListener iUpdateListener, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            iUpdateListener = (IUpdateListener) null;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        updateFontLibHelper.update(context, hardwareInterface, iUpdateListener, z);
    }

    public final void update(final Context context, final HardwareInterface inf, final IUpdateListener listener, final boolean isForce) {
        String str;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(inf, "inf");
        if (getUpdateFontLib().getNeedUpdate() || isForce) {
            try {
                String[] list = context.getAssets().list("ledscreen_fontlib");
                if (list != null) {
                    int length = list.length;
                    int i = 0;
                    while (true) {
                        str = null;
                        if (i >= length) {
                            break;
                        }
                        String it = list[i];
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        if (StringsKt.contains$default((CharSequence) it, (CharSequence) "font_V", false, 2, (Object) null)) {
                            str = it;
                            break;
                        }
                        i++;
                    }
                    if (str != null) {
                        InputStream open = context.getAssets().open(("ledscreen_fontlib" + File.separator) + str);
                        Intrinsics.checkExpressionValueIsNotNull(open, "context.assets.open(filePath)");
                        byte[] readBytes = ByteStreamsKt.readBytes(open);
                        String str2 = str;
                        StringBuilder sb = new StringBuilder();
                        int length2 = str2.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            char charAt = str2.charAt(i2);
                            if (Character.isDigit(charAt)) {
                                sb.append(charAt);
                            }
                        }
                        String sb2 = sb.toString();
                        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterTo(StringBuilder(), predicate).toString()");
                        int parseInt = Integer.parseInt(sb2);
                        INSTANCE.getUpdateFontLib().setOnUpdateEvent(new Function1<UpdateEvent, Unit>() { // from class: com.pudutech.bumblebee.robot.led_screen.UpdateFontLibHelper$update$$inlined$let$lambda$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(UpdateEvent updateEvent) {
                                invoke2(updateEvent);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(UpdateEvent event) {
                                Intrinsics.checkParameterIsNotNull(event, "event");
                                IUpdateListener iUpdateListener = listener;
                                if (iUpdateListener != null) {
                                    iUpdateListener.onUpdateEvent(UpdateObject.LED_SCREEN_FONT_LIB, event);
                                }
                            }
                        });
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UpdateFontLibHelper$update$$inlined$let$lambda$2(parseInt, readBytes, null, context, listener, inf, isForce), 3, null);
                    }
                }
            } catch (Exception e) {
                Pdlog.m3277w("UpdateFontLibHelper", String.valueOf(e));
            }
        }
    }
}
