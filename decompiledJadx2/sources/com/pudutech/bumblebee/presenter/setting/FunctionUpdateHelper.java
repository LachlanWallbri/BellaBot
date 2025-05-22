package com.pudutech.bumblebee.presenter.setting;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* compiled from: FunctionUpdateHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\r\"\u00020\u0007H\u0002¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u0006\u0010\u0011\u001a\u00020\u000bJ\b\u0010\u0012\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/setting/FunctionUpdateHelper;", "", "()V", "TAG", "", "migrationList", "", "Lcom/pudutech/bumblebee/presenter/setting/IMigration;", "newVersion", "", "addMigration", "", "migration", "", "([Lcom/pudutech/bumblebee/presenter/setting/IMigration;)V", "findMigration", "version", "init", "update", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FunctionUpdateHelper {
    private static final String TAG = "FunctionUpdateTool";
    private static final int newVersion = 1;
    public static final FunctionUpdateHelper INSTANCE = new FunctionUpdateHelper();
    private static final List<IMigration> migrationList = new ArrayList();

    private FunctionUpdateHelper() {
    }

    public final void init() {
        addMigration(new ZeroToOneMigration());
        update();
    }

    private final void update() {
        int functionUpdateToolVersion = Constant.INSTANCE.getFunctionUpdateToolVersion();
        Pdlog.m3273d(TAG, "update localVersion:" + functionUpdateToolVersion + " targetVersion:1 ");
        if (functionUpdateToolVersion < 1) {
            Iterator<Integer> it = new IntRange(functionUpdateToolVersion, 1).iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                Pdlog.m3273d(TAG, "update version:" + nextInt);
                IMigration findMigration = INSTANCE.findMigration(nextInt);
                if (findMigration != null) {
                    findMigration.update(nextInt);
                }
            }
            Constant.INSTANCE.setFunctionUpdateToolVersion(1);
        }
    }

    private final IMigration findMigration(int version) {
        for (IMigration iMigration : migrationList) {
            if (version > iMigration.getStartVersion() && version <= iMigration.getEndVersion()) {
                return iMigration;
            }
        }
        return null;
    }

    private final void addMigration(IMigration... migration) {
        for (IMigration iMigration : migration) {
            if (iMigration.getStartVersion() >= iMigration.getEndVersion()) {
                Pdlog.m3273d(TAG, StringsKt.trimMargin$default("addMigration migration endVersion(" + iMigration.getEndVersion() + ") is not allowed to be smaller than \n                        |or equal to the startVersion(" + iMigration.getStartVersion() + ')', null, 1, null));
                throw new RuntimeException(StringsKt.trimMargin$default("addMigration migration endVersion(" + iMigration.getEndVersion() + ") is not allowed to be smaller than \n                        |or equal to the startVersion(" + iMigration.getStartVersion() + ')', null, 1, null));
            }
            if ((!migrationList.isEmpty()) && iMigration.getStartVersion() <= ((IMigration) CollectionsKt.last((List) migrationList)).getEndVersion()) {
                Pdlog.m3273d(TAG, StringsKt.trimMargin$default("addMigration last migration startVersion(" + iMigration.getStartVersion() + ") is not allowed to be smaller than \n                        |or equal to the previous migration endVersion(" + ((IMigration) CollectionsKt.last((List) migrationList)).getEndVersion() + ") ", null, 1, null));
                throw new RuntimeException(StringsKt.trimMargin$default("addMigration last migration startVersion(" + iMigration.getStartVersion() + ") is not allowed to be smaller than \n                        |or equal to the previous migration endVersion(" + ((IMigration) CollectionsKt.last((List) migrationList)).getEndVersion() + ") ", null, 1, null));
            }
            migrationList.add(iMigration);
        }
    }
}
