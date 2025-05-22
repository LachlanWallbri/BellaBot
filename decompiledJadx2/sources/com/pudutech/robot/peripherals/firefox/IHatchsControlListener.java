package com.pudutech.robot.peripherals.firefox;

import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: IHatchsControlListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;", "", "callbackState", "", "hatchs", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/peripherals/firefox/Hatch;", "Lkotlin/collections/ArrayList;", "state", "Lcom/pudutech/robot/peripherals/firefox/HatchesStatus;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IHatchsControlListener {
    void callbackState(ArrayList<Hatch> hatchs, HatchesStatus state);
}
