package com.pudutech.event_tracking;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.event_tracking.click.ClickArgs;
import com.pudutech.event_tracking.component.LocationSource;
import com.pudutech.event_tracking.custom.CustomArgs;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PuduEventTrackingManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011J-\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u001b\u0010\u0012\u001a\u0017\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00050\u0013j\u0002`\u0015¢\u0006\u0002\b\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\bH\u0016J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\bH\u0016J\u0010\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u0005H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/event_tracking/PuduEventTrackingManager;", "Lcom/pudutech/event_tracking/IEventTrackingManager;", "()V", "mUserActionManagerImpl", "addHeaderCustom", "", "pair", "Lkotlin/Pair;", "", "", "customEvent", "event", "Lcom/pudutech/event_tracking/custom/CustomArgs;", "hardwareVersion", "version", "init", "context", "Landroid/content/Context;", "block", "Lkotlin/Function1;", "Lcom/pudutech/event_tracking/_EventTrackingManagerBuilder;", "Lcom/pudutech/event_tracking/EventTrackingManagerBuilder;", "Lkotlin/ExtensionFunctionType;", "onClick", "v", "Landroid/view/View;", "params", "Lcom/pudutech/event_tracking/click/ClickArgs;", "onPageStart", "name", "onPageStop", "refreshUserId", "id", "registerLocationSource", "mLocationSource", "Lcom/pudutech/event_tracking/component/LocationSource;", "removeHeaderCustom", TransferTable.COLUMN_KEY, "switchReportData", "open", "", "unRegisterLocationSource", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PuduEventTrackingManager implements IEventTrackingManager {
    public static final PuduEventTrackingManager INSTANCE = new PuduEventTrackingManager();
    private static IEventTrackingManager mUserActionManagerImpl;

    private PuduEventTrackingManager() {
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context, new Function1<_EventTrackingManagerBuilder, Unit>() { // from class: com.pudutech.event_tracking.PuduEventTrackingManager$init$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(_EventTrackingManagerBuilder receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(_EventTrackingManagerBuilder _eventtrackingmanagerbuilder) {
                invoke2(_eventtrackingmanagerbuilder);
                return Unit.INSTANCE;
            }
        });
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public synchronized void init(Context context, Function1<? super _EventTrackingManagerBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Log.i("PuduUserActionManager", "init " + mUserActionManagerImpl);
        if (mUserActionManagerImpl == null) {
            PuduEventTrackingManagerImpl puduEventTrackingManagerImpl = new PuduEventTrackingManagerImpl();
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
            puduEventTrackingManagerImpl.init(applicationContext, block);
            mUserActionManagerImpl = puduEventTrackingManagerImpl;
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void onPageStart(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.onPageStart(name);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void onPageStop(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.onPageStop(name);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void onClick(View v, ClickArgs params) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(params, "params");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.onClick(v, params);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void refreshUserId(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.refreshUserId(id);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void hardwareVersion(String version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.hardwareVersion(version);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void customEvent(CustomArgs event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.customEvent(event);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void registerLocationSource(LocationSource mLocationSource) {
        Intrinsics.checkParameterIsNotNull(mLocationSource, "mLocationSource");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.registerLocationSource(mLocationSource);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void unRegisterLocationSource() {
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.unRegisterLocationSource();
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void addHeaderCustom(Pair<String, ? extends Object> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.addHeaderCustom(pair);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void removeHeaderCustom(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.removeHeaderCustom(key);
        }
    }

    @Override // com.pudutech.event_tracking.IEventTrackingManager
    public void switchReportData(boolean open) {
        IEventTrackingManager iEventTrackingManager = mUserActionManagerImpl;
        if (iEventTrackingManager != null) {
            iEventTrackingManager.switchReportData(open);
        }
    }
}
