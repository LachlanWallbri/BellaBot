package com.pudutech.bumblebee.robot_ui.database;

import com.pudutech.bumblebee.robot_ui.database.greendao.GreenDaoDatabaseManager;
import com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface;
import kotlin.Metadata;

/* compiled from: DatabaseManagerFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/database/DatabaseManagerFactory;", "", "()V", "getDatabaseManager", "Lcom/pudutech/bumblebee/robot_ui/database/interf/IDatabaseInterface;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DatabaseManagerFactory {
    public static final DatabaseManagerFactory INSTANCE = new DatabaseManagerFactory();

    private DatabaseManagerFactory() {
    }

    public final IDatabaseInterface getDatabaseManager() {
        return GreenDaoDatabaseManager.INSTANCE.getInstance();
    }
}
