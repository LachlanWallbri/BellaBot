package com.pudutech.mpmodule.database;

import com.pudutech.mpmodule.database.greendao.GreenDaoDatabaseManager;
import com.pudutech.mpmodule.database.interf.IDatabaseInterface;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class DatabaseManagerFactory {
    public static IDatabaseInterface getDatabaseManager() {
        return GreenDaoDatabaseManager.getInstance();
    }
}
