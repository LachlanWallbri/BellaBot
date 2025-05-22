package com.pudu.library.loracall.bean;

import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.dao.AppDatabase;
import com.pudu.library.loracall.dao.TableMatchBean;
import com.pudu.loracall.library.C3965R;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoraTableParam.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012:\b\u0002\u0010\u0007\u001a4\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\bj\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\tj\b\u0012\u0004\u0012\u00020\u0003`\u000b`\n¢\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J;\u0010\u0017\u001a4\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\bj\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\tj\b\u0012\u0004\u0012\u00020\u0003`\u000b`\nHÆ\u0003Jo\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052:\b\u0002\u0010\u0007\u001a4\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\bj\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\tj\b\u0012\u0004\u0012\u00020\u0003`\u000b`\nHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J.\u0010\u001c\u001a*\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00050\bj\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u0005`\nJ\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eRC\u0010\u0007\u001a4\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\bj\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\tj\b\u0012\u0004\u0012\u00020\u0003`\u000b`\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoraTableParam;", "", "mapName", "", "allList", "", "diningOutList", "groupList", "Ljava/util/LinkedHashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/LinkedHashMap;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/LinkedHashMap;)V", "getAllList", "()Ljava/util/List;", "getDiningOutList", "getGroupList", "()Ljava/util/LinkedHashMap;", "getMapName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "getTableList", "Lcom/pudu/library/loracall/dao/TableMatchBean;", "hashCode", "", "toString", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoraTableParam {
    private final List<String> allList;
    private final List<String> diningOutList;
    private final LinkedHashMap<String, ArrayList<String>> groupList;
    private final String mapName;

    public LoraTableParam() {
        this(null, null, null, null, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LoraTableParam copy$default(LoraTableParam loraTableParam, String str, List list, List list2, LinkedHashMap linkedHashMap, int i, Object obj) {
        if ((i & 1) != 0) {
            str = loraTableParam.mapName;
        }
        if ((i & 2) != 0) {
            list = loraTableParam.allList;
        }
        if ((i & 4) != 0) {
            list2 = loraTableParam.diningOutList;
        }
        if ((i & 8) != 0) {
            linkedHashMap = loraTableParam.groupList;
        }
        return loraTableParam.copy(str, list, list2, linkedHashMap);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMapName() {
        return this.mapName;
    }

    public final List<String> component2() {
        return this.allList;
    }

    public final List<String> component3() {
        return this.diningOutList;
    }

    public final LinkedHashMap<String, ArrayList<String>> component4() {
        return this.groupList;
    }

    public final LoraTableParam copy(String mapName, List<String> allList, List<String> diningOutList, LinkedHashMap<String, ArrayList<String>> groupList) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(allList, "allList");
        Intrinsics.checkParameterIsNotNull(diningOutList, "diningOutList");
        Intrinsics.checkParameterIsNotNull(groupList, "groupList");
        return new LoraTableParam(mapName, allList, diningOutList, groupList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoraTableParam)) {
            return false;
        }
        LoraTableParam loraTableParam = (LoraTableParam) other;
        return Intrinsics.areEqual(this.mapName, loraTableParam.mapName) && Intrinsics.areEqual(this.allList, loraTableParam.allList) && Intrinsics.areEqual(this.diningOutList, loraTableParam.diningOutList) && Intrinsics.areEqual(this.groupList, loraTableParam.groupList);
    }

    public int hashCode() {
        String str = this.mapName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<String> list = this.allList;
        int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
        List<String> list2 = this.diningOutList;
        int hashCode3 = (hashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31;
        LinkedHashMap<String, ArrayList<String>> linkedHashMap = this.groupList;
        return hashCode3 + (linkedHashMap != null ? linkedHashMap.hashCode() : 0);
    }

    public String toString() {
        return "LoraTableParam(mapName=" + this.mapName + ", allList=" + this.allList + ", diningOutList=" + this.diningOutList + ", groupList=" + this.groupList + ")";
    }

    public LoraTableParam(String mapName, List<String> allList, List<String> diningOutList, LinkedHashMap<String, ArrayList<String>> groupList) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(allList, "allList");
        Intrinsics.checkParameterIsNotNull(diningOutList, "diningOutList");
        Intrinsics.checkParameterIsNotNull(groupList, "groupList");
        this.mapName = mapName;
        this.allList = allList;
        this.diningOutList = diningOutList;
        this.groupList = groupList;
    }

    public final String getMapName() {
        return this.mapName;
    }

    public /* synthetic */ LoraTableParam(String str, ArrayList arrayList, ArrayList arrayList2, LinkedHashMap linkedHashMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? new ArrayList() : arrayList, (i & 4) != 0 ? new ArrayList() : arrayList2, (i & 8) != 0 ? new LinkedHashMap() : linkedHashMap);
    }

    public final List<String> getAllList() {
        return this.allList;
    }

    public final List<String> getDiningOutList() {
        return this.diningOutList;
    }

    public final LinkedHashMap<String, ArrayList<String>> getGroupList() {
        return this.groupList;
    }

    public final LinkedHashMap<String, List<TableMatchBean>> getTableList() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!this.allList.isEmpty()) {
            String string = LoRaClient.INSTANCE.getInstance().getMContext$library_loracall_release().getString(C3965R.string.lora_all);
            Intrinsics.checkExpressionValueIsNotNull(string, "LoRaClient.instance.mCon…String(R.string.lora_all)");
            linkedHashMap.put(string, this.allList);
        }
        if (!this.diningOutList.isEmpty()) {
            String string2 = LoRaClient.INSTANCE.getInstance().getMContext$library_loracall_release().getString(C3965R.string.lora_dining_out);
            Intrinsics.checkExpressionValueIsNotNull(string2, "LoRaClient.instance.mCon…R.string.lora_dining_out)");
            linkedHashMap.put(string2, this.diningOutList);
        }
        linkedHashMap.putAll(this.groupList);
        List<TableMatchBean> tableList = AppDatabase.INSTANCE.getInstance().getTableDao().getTableList();
        LinkedHashMap<String, List<TableMatchBean>> linkedHashMap2 = new LinkedHashMap<>();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            LinkedHashMap<String, List<TableMatchBean>> linkedHashMap3 = linkedHashMap2;
            Object key = entry.getKey();
            Iterable<String> iterable = (Iterable) entry.getValue();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (String str : iterable) {
                TableMatchBean tableMatchBean = new TableMatchBean(str);
                for (TableMatchBean tableMatchBean2 : tableList) {
                    if (Intrinsics.areEqual(tableMatchBean2.getTableName(), str)) {
                        tableMatchBean = tableMatchBean2;
                    }
                }
                arrayList.add(tableMatchBean);
            }
            linkedHashMap3.put(key, arrayList);
        }
        return linkedHashMap2;
    }
}
