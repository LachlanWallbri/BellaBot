package org.hash.mock.debug.manager;

import android.app.Activity;
import android.util.SparseArray;
import android.widget.FrameLayout;
import java.util.ArrayList;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface IDebugToolsManager {
    DebugTools add();

    DebugTools attach(FrameLayout frameLayout);

    IDebugToolsManager attach(Activity activity);

    DebugTools detach(Activity activity);

    DebugTools detach(FrameLayout frameLayout);

    boolean dismissMenu();

    DebugTools fillMenuData(SparseArray<ArrayList<SimpleEntity>> sparseArray);

    boolean isMemoryInfoShow();

    DebugTools remove();

    DebugTools updateInfo(CharSequence charSequence, float f);
}
