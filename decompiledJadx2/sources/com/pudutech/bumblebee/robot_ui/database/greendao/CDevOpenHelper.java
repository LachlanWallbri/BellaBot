package com.pudutech.bumblebee.robot_ui.database.greendao;

import android.content.Context;
import com.pudutech.bumblebee.robot_ui.database.greendao.DaoMaster;
import kotlin.Metadata;
import org.greenrobot.greendao.database.Database;

/* compiled from: CDevOpenHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/database/greendao/CDevOpenHelper;", "Lcom/pudutech/bumblebee/robot_ui/database/greendao/DaoMaster$OpenHelper;", "context", "Landroid/content/Context;", "name", "", "(Landroid/content/Context;Ljava/lang/String;)V", "onUpgrade", "", "db", "Lorg/greenrobot/greendao/database/Database;", "oldVersion", "", "newVersion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CDevOpenHelper extends DaoMaster.OpenHelper {
    public CDevOpenHelper(Context context, String str) {
        super(context, str);
    }

    @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        DaoMaster.dropAllTables(db, true);
        onCreate(db);
    }
}
