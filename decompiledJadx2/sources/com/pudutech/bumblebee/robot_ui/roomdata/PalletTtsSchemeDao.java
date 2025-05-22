package com.pudutech.bumblebee.robot_ui.roomdata;

import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import java.util.List;
import kotlin.Metadata;

/* compiled from: PalletTtsSchemeDao.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH'J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/roomdata/PalletTtsSchemeDao;", "", "deletePalletTtsScheme", "", "palletTtsScheme", "Lcom/pudutech/bumblebee/robot_ui/bean/PalletTtsScheme;", "insertPalletTtsScheme", "", "queryPalletTtsSchemeByLocale", "", "locale", "", "updatePalletTtsScheme", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface PalletTtsSchemeDao {
    void deletePalletTtsScheme(PalletTtsScheme palletTtsScheme);

    long insertPalletTtsScheme(PalletTtsScheme palletTtsScheme);

    List<PalletTtsScheme> queryPalletTtsSchemeByLocale(String locale);

    void updatePalletTtsScheme(PalletTtsScheme palletTtsScheme);
}
