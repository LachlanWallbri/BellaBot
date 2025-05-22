package com.pudutech.robotselfclean;

import android.content.Context;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.robotselfclean.filter.DecorationFilter;
import com.pudutech.robotselfclean.filter.HlsLegacyFilter;
import com.pudutech.robotselfclean.filter.IFilter;
import com.pudutech.robotselfclean.utils.FileUtil;
import com.pudutech.robotselfclean.utils.SystemTool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefCleanImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JX\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u001328\u0010\u0014\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015H\u0016J\u0018\u0010\u001b\u001a\u00020\u000f2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013H\u0016J\u0016\u0010\u001d\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013H\u0016J\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010 \u001a\u00020\u001a2\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013H\u0002J\u001a\u0010!\u001a\u00020\u001a2\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/robotselfclean/DefCleanImpl;", "Lcom/pudutech/robotselfclean/ICleanInterface;", "()V", "TAG", "", "defFilters", "", "Lcom/pudutech/robotselfclean/filter/IFilter;", "getDefFilters", "()Ljava/util/List;", "defFilters$delegate", "Lkotlin/Lazy;", "deleteList", "retainList", "clearAppData", "", "context", "Landroid/content/Context;", "appPkgNameList", "", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "pkg", "boolean", "", "clearFolderFilter", "filters", "clearFolderList", "cleanList", "clearSelfAppData", "createDefList", "createList", "RobotSelfClean_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DefCleanImpl implements ICleanInterface {
    private final String TAG = "CleanImpl";

    /* renamed from: defFilters$delegate, reason: from kotlin metadata */
    private final Lazy defFilters = LazyKt.lazy(new Function0<List<IFilter>>() { // from class: com.pudutech.robotselfclean.DefCleanImpl$defFilters$2
        @Override // kotlin.jvm.functions.Function0
        public final List<IFilter> invoke() {
            return CollectionsKt.mutableListOf(new DecorationFilter(), new HlsLegacyFilter());
        }
    });
    private final List<String> deleteList = new ArrayList();
    private final List<String> retainList = new ArrayList();

    private final List<IFilter> getDefFilters() {
        return (List) this.defFilters.getValue();
    }

    @Override // com.pudutech.robotselfclean.ICleanInterface
    public boolean clearFolderFilter(List<? extends IFilter> filters) {
        createDefList(filters);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.deleteList.iterator();
        while (it.hasNext()) {
            arrayList.addAll(FileUtil.INSTANCE.deleteFolder((String) it.next(), this.retainList));
        }
        return arrayList.size() == 0;
    }

    @Override // com.pudutech.robotselfclean.ICleanInterface
    public boolean clearFolderList(List<String> cleanList) {
        Intrinsics.checkParameterIsNotNull(cleanList, "cleanList");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = cleanList.iterator();
        while (it.hasNext()) {
            arrayList.addAll(FileUtil.deleteFolder$default(FileUtil.INSTANCE, (String) it.next(), null, 2, null));
        }
        return arrayList.size() == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void createDefList$default(DefCleanImpl defCleanImpl, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        defCleanImpl.createDefList(list);
    }

    private final void createDefList(List<? extends IFilter> filters) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getDefFilters());
        if (filters != null) {
            arrayList.addAll(filters);
        }
        createList(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void createList$default(DefCleanImpl defCleanImpl, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        defCleanImpl.createList(list);
    }

    private final void createList(List<? extends IFilter> filters) {
        this.deleteList.clear();
        this.retainList.clear();
        if (filters != null) {
            for (IFilter iFilter : filters) {
                this.deleteList.addAll(iFilter.onCreateDeleteList());
                this.retainList.addAll(iFilter.onCreateRetainList());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v8 */
    @Override // com.pudutech.robotselfclean.ICleanInterface
    public boolean clearAppData(Context context, List<String> appPkgNameList, Function2<? super String, ? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(appPkgNameList, "appPkgNameList");
        int i = 1;
        String str = "";
        boolean z = true;
        for (String str2 : appPkgNameList) {
            if (((Intrinsics.areEqual(context.getPackageName(), str2) ? 1 : 0) ^ i) != 0) {
                Pair<Integer, String> execCommand = SystemTool.INSTANCE.execCommand("pm clear " + str2, i);
                String str3 = this.TAG;
                Object[] objArr = new Object[i];
                objArr[0] = "pkg = " + str2 + " clearAppData p: " + ((Integer) execCommand.first) + ',' + ((String) execCommand.second);
                Pdlog.m3273d(str3, objArr);
                if (!Intrinsics.areEqual((String) execCommand.second, "Success")) {
                    if (callback != null) {
                        callback.invoke(str2, false);
                    }
                    z = false;
                } else if (callback != null) {
                    callback.invoke(str2, true);
                }
            } else {
                str = str2;
            }
            i = 1;
        }
        String str4 = str;
        if (str4 == null || str4.length() == 0) {
            return z;
        }
        Pair<Integer, String> execCommand2 = SystemTool.INSTANCE.execCommand("pm clear " + str, true);
        Pdlog.m3273d(this.TAG, "currentPkgName = " + str + " clearAppData p: " + ((Integer) execCommand2.first) + ',' + ((String) execCommand2.second));
        if (!Intrinsics.areEqual((String) execCommand2.second, "Success")) {
            if (callback != null) {
                callback.invoke(str, false);
            }
            return false;
        }
        if (callback == null) {
            return z;
        }
        callback.invoke(str, true);
        return z;
    }

    @Override // com.pudutech.robotselfclean.ICleanInterface
    public boolean clearSelfAppData(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return Intrinsics.areEqual((String) SystemTool.INSTANCE.execCommand("pm clear " + context.getPackageName(), true).second, "Success");
    }
}
