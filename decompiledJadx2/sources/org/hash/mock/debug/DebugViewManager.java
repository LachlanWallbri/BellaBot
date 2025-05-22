package org.hash.mock.debug;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;
import org.hash.mock.debug.view.sheet.sweetpick.DimEffect;
import org.hash.mock.debug.view.sheet.sweetpick.SweetSheet;
import org.hash.mock.debug.view.sheet.sweetpick.ViewPagerDelegate;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class DebugViewManager {
    private SweetSheet mSweetSheet;

    public DebugViewManager setupRecyclerView(ViewGroup viewGroup, SparseArray<ArrayList<SimpleEntity>> sparseArray) {
        this.mSweetSheet = new SweetSheet(viewGroup);
        this.mSweetSheet.setMenuList(sparseArray);
        ViewPagerDelegate viewPagerDelegate = new ViewPagerDelegate();
        viewPagerDelegate.setContentHeight(Utils.getScreenHeight() / 2);
        this.mSweetSheet.setDelegate(viewPagerDelegate);
        this.mSweetSheet.setBackgroundEffect(new DimEffect(8.0f));
        this.mSweetSheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() { // from class: org.hash.mock.debug.-$$Lambda$DebugViewManager$8aIcNykje3aPwKTiP7RgJ9ywRFY
            @Override // org.hash.mock.debug.view.sheet.sweetpick.SweetSheet.OnMenuItemClickListener
            public final boolean onItemClick(View view, SimpleEntity simpleEntity) {
                return DebugViewManager.lambda$setupRecyclerView$0(view, simpleEntity);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$setupRecyclerView$0(View view, SimpleEntity simpleEntity) {
        if (simpleEntity.getOnClickListener() == null) {
            return false;
        }
        simpleEntity.getOnClickListener().onClick(view);
        return true;
    }

    public void toggle() {
        SweetSheet sweetSheet = this.mSweetSheet;
        if (sweetSheet == null) {
            return;
        }
        sweetSheet.toggle();
    }

    public boolean dismiss() {
        SweetSheet sweetSheet = this.mSweetSheet;
        if (sweetSheet == null || !sweetSheet.isShow()) {
            return false;
        }
        this.mSweetSheet.dismiss();
        return true;
    }
}
