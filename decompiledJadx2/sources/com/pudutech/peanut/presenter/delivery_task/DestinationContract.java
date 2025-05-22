package com.pudutech.peanut.presenter.delivery_task;

import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.peanut.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;

/* compiled from: DestinationContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract;", "", "PresenterInterface", "Type", "ViewInterface", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface DestinationContract {

    /* compiled from: DestinationContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract$PresenterInterface;", "Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "loadMap", "", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void loadMap();
    }

    /* compiled from: DestinationContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract$Type;", "", "(Ljava/lang/String;I)V", "TABLE", "DINING_OUTLET", "TRANSIT", "DISHWASHING_ROOM", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum Type {
        TABLE,
        DINING_OUTLET,
        TRANSIT,
        DISHWASHING_ROOM
    }

    /* compiled from: DestinationContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&JJ\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000528\u0010\n\u001a4\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bj\u001e\u0012\u0004\u0012\u00020\b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b0\fj\b\u0012\u0004\u0012\u00020\b`\u000e`\rH&¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseViewInterface;", "showAll", "", "type", "Lcom/pudutech/peanut/presenter/delivery_task/DestinationContract$Type;", "destinations", "", "", "showAllGroups", "groups", "Ljava/util/LinkedHashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/LinkedHashMap;", "Lkotlin/collections/ArrayList;", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showAll(Type type, List<String> destinations);

        void showAllGroups(Type type, LinkedHashMap<String, ArrayList<String>> groups);
    }
}
