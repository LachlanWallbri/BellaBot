package com.amazonaws.mobile.client;

import com.amazonaws.mobile.client.internal.ReturningRunnable;
import com.amazonaws.mobile.client.results.Device;
import com.amazonaws.mobile.client.results.ListDevicesResult;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.services.cognitoidentityprovider.AmazonCognitoIdentityProvider;
import com.amazonaws.services.cognitoidentityprovider.model.AttributeType;
import com.amazonaws.services.cognitoidentityprovider.model.DeviceRememberedStatusType;
import com.amazonaws.services.cognitoidentityprovider.model.DeviceType;
import com.amazonaws.services.cognitoidentityprovider.model.ForgetDeviceRequest;
import com.amazonaws.services.cognitoidentityprovider.model.GetDeviceRequest;
import com.amazonaws.services.cognitoidentityprovider.model.ListDevicesRequest;
import com.amazonaws.services.cognitoidentityprovider.model.UpdateDeviceStatusRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class DeviceOperations {
    private final AWSMobileClient mobileClient;
    private final AmazonCognitoIdentityProvider userpoolLL;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceOperations(AWSMobileClient aWSMobileClient, AmazonCognitoIdentityProvider amazonCognitoIdentityProvider) {
        this.mobileClient = aWSMobileClient;
        this.userpoolLL = amazonCognitoIdentityProvider;
    }

    public Device get() throws Exception {
        return _getDevice(null).await();
    }

    public void get(Callback<Device> callback) {
        _getDevice(null).async(callback);
    }

    public Device get(String str) throws Exception {
        return _getDevice(str).await();
    }

    public void get(String str, Callback<Device> callback) {
        _getDevice(str).async(callback);
    }

    private ReturningRunnable<Device> _getDevice(final String str) {
        return new ReturningRunnable<Device>() { // from class: com.amazonaws.mobile.client.DeviceOperations.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazonaws.mobile.client.internal.ReturningRunnable
            public Device run() throws Exception {
                CognitoDevice cognitoDevice = DeviceOperations.this.getCognitoDevice(str);
                GetDeviceRequest getDeviceRequest = new GetDeviceRequest();
                getDeviceRequest.setAccessToken(DeviceOperations.this.mobileClient.getTokens().getAccessToken().getTokenString());
                getDeviceRequest.setDeviceKey(cognitoDevice.getDeviceKey());
                return DeviceOperations.this.marshallDeviceTypeToDevice(DeviceOperations.this.userpoolLL.getDevice(getDeviceRequest).getDevice());
            }
        };
    }

    public ListDevicesResult list() throws Exception {
        return _listDevices(60, null).await();
    }

    public void list(Callback<ListDevicesResult> callback) {
        _listDevices(60, null).async(callback);
    }

    public ListDevicesResult list(Integer num, String str) throws Exception {
        return _listDevices(num, str).await();
    }

    public void list(Integer num, String str, Callback<ListDevicesResult> callback) {
        _listDevices(num, str).async(callback);
    }

    private ReturningRunnable<ListDevicesResult> _listDevices(final Integer num, final String str) {
        return new ReturningRunnable<ListDevicesResult>() { // from class: com.amazonaws.mobile.client.DeviceOperations.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazonaws.mobile.client.internal.ReturningRunnable
            public ListDevicesResult run() throws Exception {
                ListDevicesRequest listDevicesRequest = new ListDevicesRequest();
                listDevicesRequest.setAccessToken(DeviceOperations.this.mobileClient.getTokens().getAccessToken().getTokenString());
                listDevicesRequest.setLimit(num);
                listDevicesRequest.setPaginationToken(str);
                com.amazonaws.services.cognitoidentityprovider.model.ListDevicesResult listDevices = DeviceOperations.this.userpoolLL.listDevices(listDevicesRequest);
                ArrayList arrayList = new ArrayList(num.intValue());
                Iterator<DeviceType> it = listDevices.getDevices().iterator();
                while (it.hasNext()) {
                    arrayList.add(DeviceOperations.this.marshallDeviceTypeToDevice(it.next()));
                }
                return new ListDevicesResult(arrayList, listDevices.getPaginationToken());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Device marshallDeviceTypeToDevice(DeviceType deviceType) {
        HashMap hashMap = new HashMap();
        for (AttributeType attributeType : deviceType.getDeviceAttributes()) {
            hashMap.put(attributeType.getName(), attributeType.getValue());
        }
        return new Device(deviceType.getDeviceKey(), hashMap, deviceType.getDeviceCreateDate(), deviceType.getDeviceLastModifiedDate(), deviceType.getDeviceLastAuthenticatedDate());
    }

    public void updateStatus(boolean z) throws Exception {
        _rememberDevice(null, z).await();
    }

    public void updateStatus(boolean z, Callback<Void> callback) {
        _rememberDevice(null, z).async(callback);
    }

    public void updateStatus(String str, boolean z) throws Exception {
        _rememberDevice(str, z).await();
    }

    public void updateStatus(String str, boolean z, Callback<Void> callback) {
        _rememberDevice(str, z).async(callback);
    }

    private ReturningRunnable<Void> _rememberDevice(final String str, final boolean z) {
        return new ReturningRunnable<Void>() { // from class: com.amazonaws.mobile.client.DeviceOperations.3
            @Override // com.amazonaws.mobile.client.internal.ReturningRunnable
            public Void run() throws Exception {
                DeviceOperations.this.userpoolLL.updateDeviceStatus(new UpdateDeviceStatusRequest().withAccessToken(DeviceOperations.this.mobileClient.getTokens().getAccessToken().getTokenString()).withDeviceKey(DeviceOperations.this.getCognitoDevice(str).getDeviceKey()).withDeviceRememberedStatus(z ? DeviceRememberedStatusType.Remembered : DeviceRememberedStatusType.Not_remembered));
                return null;
            }
        };
    }

    public void forget() throws Exception {
        _forgetDevice(null).await();
    }

    public void forget(Callback<Void> callback) {
        _forgetDevice(null).async(callback);
    }

    public void forget(String str) throws Exception {
        _forgetDevice(str).await();
    }

    public void forget(String str, Callback<Void> callback) {
        _forgetDevice(str).async(callback);
    }

    private ReturningRunnable<Void> _forgetDevice(final String str) {
        return new ReturningRunnable<Void>() { // from class: com.amazonaws.mobile.client.DeviceOperations.4
            @Override // com.amazonaws.mobile.client.internal.ReturningRunnable
            public Void run() throws Exception {
                DeviceOperations.this.userpoolLL.forgetDevice(new ForgetDeviceRequest().withAccessToken(DeviceOperations.this.mobileClient.getTokens().getAccessToken().getTokenString()).withDeviceKey(DeviceOperations.this.getCognitoDevice(str).getDeviceKey()));
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CognitoDevice getCognitoDevice(String str) {
        if (str == null) {
            str = this.mobileClient.userpool.getCurrentUser().thisDevice().getDeviceKey();
        }
        return new CognitoDevice(str, null, null, null, null, this.mobileClient.userpool.getCurrentUser(), this.mobileClient.mContext);
    }
}
