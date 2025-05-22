package com.pudutech.mpmodule.database.interf;

import com.pudutech.mpmodule.bean.PreviousPlayBean;

/* loaded from: classes6.dex */
public interface PreviousDbInterface {
    void deleteAllPreviousPlay();

    void deletePreviousPlayByModeId(String str);

    void insertOrReplacePreviousPlay(PreviousPlayBean previousPlayBean);

    PreviousPlayBean queryPreviousPlayByModeId(String str);
}
