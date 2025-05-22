package com.pudutech.bumblebee.presenter.robot_open_task.interf;

import com.pudutech.bumblebee.presenter.robot_open_task.bean.ProductInfo;
import java.util.Set;
import kotlin.Metadata;

/* compiled from: IDataListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/interf/IDataListener;", "", "getProductInfo", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ProductInfo;", "getSubTopicSet", "", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface IDataListener {
    ProductInfo getProductInfo();

    Set<String> getSubTopicSet();
}
