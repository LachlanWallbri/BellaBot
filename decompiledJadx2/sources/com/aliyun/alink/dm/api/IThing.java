package com.aliyun.alink.dm.api;

import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.Event;
import com.aliyun.alink.linksdk.tmp.devicemodel.Property;
import com.aliyun.alink.linksdk.tmp.devicemodel.Service;
import com.aliyun.alink.linksdk.tmp.listener.IDevRawDataListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.ITRawDataRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IThing {
    Map<String, ValueWrapper> getAllPropertyValue();

    List<Event> getEvents();

    List<Property> getProperties();

    ValueWrapper getPropertyValue(String str);

    List<Service> getServices();

    boolean isThingInited();

    void setRawPropertyChangeListener(boolean z, ITRawDataRequestHandler iTRawDataRequestHandler);

    void setServiceHandler(String str, ITResRequestHandler iTResRequestHandler);

    void thingEventPost(String str, OutputParams outputParams, IPublishResourceListener iPublishResourceListener);

    void thingPropertyPost(Map<String, ValueWrapper> map, IPublishResourceListener iPublishResourceListener);

    void thingRawPropertiesPost(byte[] bArr, IDevRawDataListener iDevRawDataListener);

    void thingServiceRegister(String str, ITResRequestHandler iTResRequestHandler);

    void thingUnubscribe(String str, ITResRequestHandler iTResRequestHandler);

    void uninit();
}
