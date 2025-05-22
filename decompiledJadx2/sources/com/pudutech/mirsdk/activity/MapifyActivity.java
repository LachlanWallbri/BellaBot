package com.pudutech.mirsdk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.function.C4946R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: MapifyActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0014¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/MapifyActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "connectMapService", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapifyActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4946R.layout.activity_mapify);
        connectMapService();
        ((Button) _$_findCachedViewById(C4946R.id.bt_checklocal)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3273d("MapifyService", "MapifyService  MapServiceConnection bt_checklocal");
                Boolean checkflag = MapServiceConnection.INSTANCE.checkflag();
                if (checkflag == null) {
                    Intrinsics.throwNpe();
                }
                if (checkflag.booleanValue()) {
                    Pdlog.m3273d("MapifyService", "MapifyService  MapServiceConnection bt_checklocal true");
                    Toast.makeText(MapifyActivity.this, "check is true", 0).show();
                } else {
                    Pdlog.m3273d("MapifyService", "MapifyService  MapServiceConnection bt_checklocal false");
                    Toast.makeText(MapifyActivity.this, "check is false", 0).show();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_checkfinshlocal)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3273d("MapifyService", "MapifyService  MapServiceConnection bt_checklocal");
                Boolean checkFinshflag = MapServiceConnection.INSTANCE.checkFinshflag();
                if (checkFinshflag == null) {
                    Intrinsics.throwNpe();
                }
                if (checkFinshflag.booleanValue()) {
                    Toast.makeText(MapifyActivity.this, "check is true", 0).show();
                    Toast.makeText(MapifyActivity.this, "  MapServiceConnection.finishMapping()", 0).show();
                } else {
                    Toast.makeText(MapifyActivity.this, "check is false", 0).show();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_deletemap)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MapServiceConnection.INSTANCE.deletemap();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_startmaping)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Boolean checkflag = MapServiceConnection.INSTANCE.checkflag();
                if (checkflag == null) {
                    Intrinsics.throwNpe();
                }
                if (checkflag.booleanValue()) {
                    Toast.makeText(MapifyActivity.this, "check is true", 0).show();
                    MapServiceConnection.INSTANCE.startMapping();
                    Toast.makeText(MapifyActivity.this, "  MapServiceConnection.startMapping()", 0).show();
                    return;
                }
                Toast.makeText(MapifyActivity.this, "check is false", 0).show();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_finshmaping)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Boolean checkFinshflag = MapServiceConnection.INSTANCE.checkFinshflag();
                if (checkFinshflag == null) {
                    Intrinsics.throwNpe();
                }
                if (checkFinshflag.booleanValue()) {
                    Toast.makeText(MapifyActivity.this, "check is true", 0).show();
                    MapServiceConnection.INSTANCE.finishMapping();
                    Toast.makeText(MapifyActivity.this, "  MapServiceConnection.finishMapping()", 0).show();
                    return;
                }
                Toast.makeText(MapifyActivity.this, "check is false", 0).show();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_checkoptimzedMap)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Boolean optimezedMapFlag = MapServiceConnection.INSTANCE.optimezedMapFlag();
                if (optimezedMapFlag == null) {
                    Intrinsics.throwNpe();
                }
                if (optimezedMapFlag.booleanValue()) {
                    Toast.makeText(MapifyActivity.this, "optimezedMapFlag is finished", 0).show();
                } else {
                    Toast.makeText(MapifyActivity.this, "optimezedMapFlag is failed", 0).show();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_saveoptimzedMap)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MapServiceConnection.INSTANCE.saveoptimezedMapflag();
                SDKServiceConnection.INSTANCE.initLocalization();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_addTable)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MapServiceConnection.INSTANCE.addTable();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_addUsher)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MapServiceConnection.INSTANCE.addusher();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_recordCruise)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SDKServiceConnection.INSTANCE.setRecordFlag(true);
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_stoprecordCruise)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SDKServiceConnection.INSTANCE.setRecordFlag(false);
                MapServiceConnection.INSTANCE.createCruise();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_saveAtlas)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MapServiceConnection.INSTANCE.saveTopoMap();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_addListener)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MapServiceConnection.INSTANCE.addReinitmodulesListener();
                Toast.makeText(MapifyActivity.this, "addReinitmodulesListener success", 0).show();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.bt_reinitmodul)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MapifyActivity$onCreate$14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MapServiceConnection.INSTANCE.reinitModules();
                Toast.makeText(MapifyActivity.this, "reinitModules success", 0).show();
            }
        });
    }

    private final void connectMapService() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MapifyActivity$connectMapService$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        MapServiceConnection.INSTANCE.removeMapSensorListener();
        MapServiceConnection.INSTANCE.disconnectConnection(this);
        super.onDestroy();
    }
}
