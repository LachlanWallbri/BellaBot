package com.pudutech.robotselfclean;

import android.content.Context;
import com.pudutech.robotselfclean.filter.IFilter;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: ICleanInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001JZ\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072:\b\u0002\u0010\t\u001a4\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nH&J\u001a\u0010\u0010\u001a\u00020\u00032\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0007H&J\u0016\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robotselfclean/ICleanInterface;", "", "clearAppData", "", "context", "Landroid/content/Context;", "appPkgNameList", "", "", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "pkg", "boolean", "", "clearFolderFilter", "filters", "Lcom/pudutech/robotselfclean/filter/IFilter;", "clearFolderList", "cleanList", "clearSelfAppData", "RobotSelfClean_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface ICleanInterface {
    boolean clearAppData(Context context, List<String> appPkgNameList, Function2<? super String, ? super Boolean, Unit> callback);

    boolean clearFolderFilter(List<? extends IFilter> filters);

    boolean clearFolderList(List<String> cleanList);

    boolean clearSelfAppData(Context context);

    /* compiled from: ICleanInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ boolean clearFolderFilter$default(ICleanInterface iCleanInterface, List list, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clearFolderFilter");
            }
            if ((i & 1) != 0) {
                list = (List) null;
            }
            return iCleanInterface.clearFolderFilter(list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ boolean clearAppData$default(ICleanInterface iCleanInterface, Context context, List list, Function2 function2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clearAppData");
            }
            if ((i & 4) != 0) {
                function2 = (Function2) null;
            }
            return iCleanInterface.clearAppData(context, list, function2);
        }
    }
}
