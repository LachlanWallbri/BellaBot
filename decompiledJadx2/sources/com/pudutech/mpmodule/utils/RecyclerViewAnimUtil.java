package com.pudutech.mpmodule.utils;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class RecyclerViewAnimUtil {
    private static final RecyclerViewAnimUtil ourInstance = new RecyclerViewAnimUtil();

    public static RecyclerViewAnimUtil getInstance() {
        return ourInstance;
    }

    private RecyclerViewAnimUtil() {
    }

    public void closeDefaultAnimator(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return;
        }
        recyclerView.getItemAnimator().setAddDuration(0L);
        recyclerView.getItemAnimator().setChangeDuration(0L);
        recyclerView.getItemAnimator().setMoveDuration(0L);
        recyclerView.getItemAnimator().setRemoveDuration(0L);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }
}
