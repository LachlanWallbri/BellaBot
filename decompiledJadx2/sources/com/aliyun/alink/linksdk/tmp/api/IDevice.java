package com.aliyun.alink.linksdk.tmp.api;

import com.aliyun.alink.linksdk.tmp.data.p044ut.ExtraData;
import com.aliyun.alink.linksdk.tmp.device.payload.KeyValuePair;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.Event;
import com.aliyun.alink.linksdk.tmp.devicemodel.Property;
import com.aliyun.alink.linksdk.tmp.devicemodel.Service;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevRawDataListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevStateChangeListener;
import com.aliyun.alink.linksdk.tmp.listener.IEventListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.ITRawDataRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDevice {
    boolean addDeviceStateChangeListener(IDevStateChangeListener iDevStateChangeListener);

    Map<String, ValueWrapper> getAllPropertyValue();

    String getDevId();

    String getDevName();

    TmpEnum.DeviceState getDeviceState();

    List<Event> getEvents();

    List<Property> getProperties();

    ValueWrapper getPropertyValue(String str);

    boolean getPropertyValue(List<String> list, Object obj, IDevListener iDevListener);

    List<Service> getServices();

    void init(Object obj, IDevListener iDevListener);

    boolean invokeService(String str, List<KeyValuePair> list, Object obj, IDevListener iDevListener);

    boolean regRawRes(boolean z, ITRawDataRequestHandler iTRawDataRequestHandler);

    String regRes(String str, boolean z, ITResRequestHandler iTResRequestHandler);

    boolean removeDeviceStateChangeListener(IDevStateChangeListener iDevStateChangeListener);

    boolean sendRawData(byte[] bArr, IDevRawDataListener iDevRawDataListener);

    boolean setPropertyValue(ExtraData extraData, List<KeyValuePair> list, Object obj, IDevListener iDevListener);

    boolean setPropertyValue(String str, ValueWrapper valueWrapper, Object obj, IDevListener iDevListener);

    boolean setPropertyValue(List<KeyValuePair> list, Object obj, IDevListener iDevListener);

    boolean setPropertyValue(Map<String, ValueWrapper> map, boolean z);

    boolean setPropertyValue(Map<String, ValueWrapper> map, boolean z, IPublishResourceListener iPublishResourceListener);

    boolean setup(Object obj, Object obj2, IDevListener iDevListener);

    boolean subAllEvents(Object obj, IEventListener iEventListener);

    boolean subscribeEvent(String str, Object obj, IEventListener iEventListener);

    boolean triggerRes(String str, OutputParams outputParams);

    boolean triggerRes(String str, OutputParams outputParams, IPublishResourceListener iPublishResourceListener);

    void unInit();

    boolean unRegRes(String str, ITResRequestHandler iTResRequestHandler);

    boolean unsubscribeEvent(String str, Object obj, IDevListener iDevListener);
}
