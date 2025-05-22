package androidx.room.migration;

import androidx.sqlite.p003db.SupportSQLiteDatabase;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class Migration {
    public final int endVersion;
    public final int startVersion;

    public abstract void migrate(SupportSQLiteDatabase supportSQLiteDatabase);

    public Migration(int i, int i2) {
        this.startVersion = i;
        this.endVersion = i2;
    }
}
