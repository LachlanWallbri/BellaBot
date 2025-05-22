package com.pudutech.freeinstall_ui.activity;

import androidx.lifecycle.Observer;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddChargePileActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "", "onChanged"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AddChargePileActivity$createObserver$1<T> implements Observer<String> {
    final /* synthetic */ AddChargePileActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AddChargePileActivity$createObserver$1(AddChargePileActivity addChargePileActivity) {
        this.this$0 = addChargePileActivity;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(String str) {
        OccupancyGridLayer occupancyGridLayer;
        Utils.Companion companion = Utils.INSTANCE;
        occupancyGridLayer = this.this$0.mapLayer;
        companion.updateMap(occupancyGridLayer, str, new OccupancyGridLayer.OccupancyOneListener() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$createObserver$1.1
            @Override // com.pudutech.opengl_draw.layer.OccupancyGridLayer.OccupancyOneListener
            public final void onSuccess() {
                String str2;
                str2 = AddChargePileActivity$createObserver$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "地图绘制成功");
                AddChargePileActivity$createObserver$1.this.this$0.locate();
                AddChargePileActivity$createObserver$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity.createObserver.1.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AddChargePileActivity$createObserver$1.this.this$0.setRestoreData();
                    }
                });
            }
        }, new Function2<Integer, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$createObserver$1.2
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                invoke(num.intValue(), num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, int i2) {
                AddChargePileActivity$createObserver$1.this.this$0.mapWith = i;
                AddChargePileActivity$createObserver$1.this.this$0.mapHeight = i2;
            }
        });
    }
}
