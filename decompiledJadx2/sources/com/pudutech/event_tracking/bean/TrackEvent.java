package com.pudutech.event_tracking.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: tracking_event.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0003\b£\u0001\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BÇ\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u0012\u0006\u0010\u001b\u001a\u00020\u0005\u0012\u0006\u0010\u001c\u001a\u00020\u0005\u0012\u0006\u0010\u001d\u001a\u00020\u0005\u0012\u0006\u0010\u001e\u001a\u00020\u0005\u0012\u0006\u0010\u001f\u001a\u00020\u0005\u0012\u0006\u0010 \u001a\u00020\u0005\u0012\u0006\u0010!\u001a\u00020\u0005\u0012\u0006\u0010\"\u001a\u00020\u0005\u0012\u0006\u0010#\u001a\u00020\u0005\u0012\u0006\u0010$\u001a\u00020\u0005\u0012\u0006\u0010%\u001a\u00020\u0003\u0012\u0006\u0010&\u001a\u00020\u0005\u0012\u0006\u0010'\u001a\u00020\u0005\u0012\u0006\u0010(\u001a\u00020\u0005\u0012\u0006\u0010)\u001a\u00020\u0005\u0012\u0006\u0010*\u001a\u00020\u0005\u0012\u0006\u0010+\u001a\u00020\n\u0012\u0006\u0010,\u001a\u00020\n¢\u0006\u0002\u0010-J\n\u0010\u0084\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0085\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0086\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0087\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0088\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0089\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008a\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008d\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008e\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008f\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0090\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0091\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0092\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0093\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0094\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0095\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0096\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0097\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0098\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0099\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u009a\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u009b\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u009c\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u009d\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u009e\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009f\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010 \u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010¡\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010¢\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010£\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010¤\u0001\u001a\u00020\nHÆ\u0003J\n\u0010¥\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010¦\u0001\u001a\u00020\nHÆ\u0003J\n\u0010§\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010¨\u0001\u001a\u00020\nHÆ\u0003J\n\u0010©\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010ª\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010«\u0001\u001a\u00020\u0005HÆ\u0003J\u009a\u0003\u0010¬\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00052\b\b\u0002\u0010'\u001a\u00020\u00052\b\b\u0002\u0010(\u001a\u00020\u00052\b\b\u0002\u0010)\u001a\u00020\u00052\b\b\u0002\u0010*\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020\n2\b\b\u0002\u0010,\u001a\u00020\nHÆ\u0001J\u0016\u0010\u00ad\u0001\u001a\u00030®\u00012\t\u0010¯\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\n\u0010°\u0001\u001a\u00020\nHÖ\u0001J\n\u0010±\u0001\u001a\u00020\u0005HÖ\u0001R\u001a\u0010(\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010'\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010/\"\u0004\b3\u00101R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010/\"\u0004\b5\u00101R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010/\"\u0004\b;\u00101R\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010/\"\u0004\b=\u00101R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010/\"\u0004\b?\u00101R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010/\"\u0004\bA\u00101R\u001a\u0010\f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010/\"\u0004\bC\u00101R\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010/\"\u0004\bE\u00101R\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010/\"\u0004\bG\u00101R\u001a\u0010\u0012\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010/\"\u0004\bI\u00101R\u001a\u0010\u0013\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010/\"\u0004\bK\u00101R\u001a\u0010\u0014\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010/\"\u0004\bM\u00101R\u001a\u0010\u000f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010/\"\u0004\bO\u00101R\u001a\u0010\u0011\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010/\"\u0004\bQ\u00101R\u001a\u0010\u0015\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010/\"\u0004\bS\u00101R\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010/\"\u0004\bU\u00101R\u001a\u0010*\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010/\"\u0004\bW\u00101R\u001a\u0010\u0017\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010/\"\u0004\bY\u00101R\u001a\u0010\u0018\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010/\"\u0004\b[\u00101R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u001a\u0010\u0019\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010/\"\u0004\ba\u00101R\u001a\u0010\u001a\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010/\"\u0004\bc\u00101R\u001a\u0010\u001b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010/\"\u0004\be\u00101R\u001a\u0010\u001c\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010/\"\u0004\bg\u00101R\u001a\u0010\u001d\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010/\"\u0004\bi\u00101R\u001a\u0010\u001e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010/\"\u0004\bk\u00101R\u001a\u0010\u001f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010/\"\u0004\bm\u00101R\u001a\u0010 \u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010/\"\u0004\bo\u00101R\u001a\u0010!\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010/\"\u0004\bq\u00101R\u001a\u0010,\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u00107\"\u0004\bs\u00109R\u001a\u0010\"\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010/\"\u0004\bu\u00101R\u001a\u0010#\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010/\"\u0004\bw\u00101R\u001a\u0010$\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010/\"\u0004\by\u00101R\u001a\u0010%\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010]\"\u0004\b{\u0010_R\u001a\u0010+\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u00107\"\u0004\b}\u00109R\u001a\u0010&\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010/\"\u0004\b\u007f\u00101R\u001c\u0010)\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010/\"\u0005\b\u0081\u0001\u00101R\u001c\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010/\"\u0005\b\u0083\u0001\u00101¨\u0006²\u0001"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/TrackEvent;", "", "id", "", "uuid", "", "appInstallId", "appPackage", "appChannel", "appId", "", "appName", "appVersion", "appLanguage", "customEvent", "deviceType", "deviceId", "deviceUniqueId", "deviceIp", "deviceModel", "deviceTimezone", "elementId", "event", "header", "headerCustom", "latitude", "locationCity", "locationProvince", "longitude", "networkType", "osVersion", "pageId", "pagePreId", "params", "screenDensity", "screenResolution", "sessionId", "time", "userId", "abtestId", "abtestGroup", "userUniqueId", "hardwareVersion", "upload", LogFactory.PRIORITY_KEY, "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getAbtestGroup", "()Ljava/lang/String;", "setAbtestGroup", "(Ljava/lang/String;)V", "getAbtestId", "setAbtestId", "getAppChannel", "setAppChannel", "getAppId", "()I", "setAppId", "(I)V", "getAppInstallId", "setAppInstallId", "getAppLanguage", "setAppLanguage", "getAppName", "setAppName", "getAppPackage", "setAppPackage", "getAppVersion", "setAppVersion", "getCustomEvent", "setCustomEvent", "getDeviceId", "setDeviceId", "getDeviceIp", "setDeviceIp", "getDeviceModel", "setDeviceModel", "getDeviceTimezone", "setDeviceTimezone", "getDeviceType", "setDeviceType", "getDeviceUniqueId", "setDeviceUniqueId", "getElementId", "setElementId", "getEvent", "setEvent", "getHardwareVersion", "setHardwareVersion", "getHeader", "setHeader", "getHeaderCustom", "setHeaderCustom", "getId", "()J", "setId", "(J)V", "getLatitude", "setLatitude", "getLocationCity", "setLocationCity", "getLocationProvince", "setLocationProvince", "getLongitude", "setLongitude", "getNetworkType", "setNetworkType", "getOsVersion", "setOsVersion", "getPageId", "setPageId", "getPagePreId", "setPagePreId", "getParams", "setParams", "getPriority", "setPriority", "getScreenDensity", "setScreenDensity", "getScreenResolution", "setScreenResolution", "getSessionId", "setSessionId", "getTime", "setTime", "getUpload", "setUpload", "getUserId", "setUserId", "getUserUniqueId", "setUserUniqueId", "getUuid", "setUuid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TrackEvent {
    private String abtestGroup;
    private String abtestId;
    private String appChannel;
    private int appId;
    private String appInstallId;
    private String appLanguage;
    private String appName;
    private String appPackage;
    private String appVersion;
    private String customEvent;
    private String deviceId;
    private String deviceIp;
    private String deviceModel;
    private String deviceTimezone;
    private String deviceType;
    private String deviceUniqueId;
    private String elementId;
    private String event;
    private String hardwareVersion;
    private String header;
    private String headerCustom;
    private long id;
    private String latitude;
    private String locationCity;
    private String locationProvince;
    private String longitude;
    private String networkType;
    private String osVersion;
    private String pageId;
    private String pagePreId;
    private String params;
    private int priority;
    private String screenDensity;
    private String screenResolution;
    private String sessionId;
    private long time;
    private int upload;
    private String userId;
    private String userUniqueId;
    private String uuid;

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getCustomEvent() {
        return this.customEvent;
    }

    /* renamed from: component11, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component12, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* renamed from: component13, reason: from getter */
    public final String getDeviceUniqueId() {
        return this.deviceUniqueId;
    }

    /* renamed from: component14, reason: from getter */
    public final String getDeviceIp() {
        return this.deviceIp;
    }

    /* renamed from: component15, reason: from getter */
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    /* renamed from: component16, reason: from getter */
    public final String getDeviceTimezone() {
        return this.deviceTimezone;
    }

    /* renamed from: component17, reason: from getter */
    public final String getElementId() {
        return this.elementId;
    }

    /* renamed from: component18, reason: from getter */
    public final String getEvent() {
        return this.event;
    }

    /* renamed from: component19, reason: from getter */
    public final String getHeader() {
        return this.header;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUuid() {
        return this.uuid;
    }

    /* renamed from: component20, reason: from getter */
    public final String getHeaderCustom() {
        return this.headerCustom;
    }

    /* renamed from: component21, reason: from getter */
    public final String getLatitude() {
        return this.latitude;
    }

    /* renamed from: component22, reason: from getter */
    public final String getLocationCity() {
        return this.locationCity;
    }

    /* renamed from: component23, reason: from getter */
    public final String getLocationProvince() {
        return this.locationProvince;
    }

    /* renamed from: component24, reason: from getter */
    public final String getLongitude() {
        return this.longitude;
    }

    /* renamed from: component25, reason: from getter */
    public final String getNetworkType() {
        return this.networkType;
    }

    /* renamed from: component26, reason: from getter */
    public final String getOsVersion() {
        return this.osVersion;
    }

    /* renamed from: component27, reason: from getter */
    public final String getPageId() {
        return this.pageId;
    }

    /* renamed from: component28, reason: from getter */
    public final String getPagePreId() {
        return this.pagePreId;
    }

    /* renamed from: component29, reason: from getter */
    public final String getParams() {
        return this.params;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAppInstallId() {
        return this.appInstallId;
    }

    /* renamed from: component30, reason: from getter */
    public final String getScreenDensity() {
        return this.screenDensity;
    }

    /* renamed from: component31, reason: from getter */
    public final String getScreenResolution() {
        return this.screenResolution;
    }

    /* renamed from: component32, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* renamed from: component33, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    /* renamed from: component34, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component35, reason: from getter */
    public final String getAbtestId() {
        return this.abtestId;
    }

    /* renamed from: component36, reason: from getter */
    public final String getAbtestGroup() {
        return this.abtestGroup;
    }

    /* renamed from: component37, reason: from getter */
    public final String getUserUniqueId() {
        return this.userUniqueId;
    }

    /* renamed from: component38, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component39, reason: from getter */
    public final int getUpload() {
        return this.upload;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAppPackage() {
        return this.appPackage;
    }

    /* renamed from: component40, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAppChannel() {
        return this.appChannel;
    }

    /* renamed from: component6, reason: from getter */
    public final int getAppId() {
        return this.appId;
    }

    /* renamed from: component7, reason: from getter */
    public final String getAppName() {
        return this.appName;
    }

    /* renamed from: component8, reason: from getter */
    public final String getAppVersion() {
        return this.appVersion;
    }

    /* renamed from: component9, reason: from getter */
    public final String getAppLanguage() {
        return this.appLanguage;
    }

    public final TrackEvent copy(long id, String uuid, String appInstallId, String appPackage, String appChannel, int appId, String appName, String appVersion, String appLanguage, String customEvent, String deviceType, String deviceId, String deviceUniqueId, String deviceIp, String deviceModel, String deviceTimezone, String elementId, String event, String header, String headerCustom, String latitude, String locationCity, String locationProvince, String longitude, String networkType, String osVersion, String pageId, String pagePreId, String params, String screenDensity, String screenResolution, String sessionId, long time, String userId, String abtestId, String abtestGroup, String userUniqueId, String hardwareVersion, int upload, int priority) {
        Intrinsics.checkParameterIsNotNull(uuid, "uuid");
        Intrinsics.checkParameterIsNotNull(appInstallId, "appInstallId");
        Intrinsics.checkParameterIsNotNull(appPackage, "appPackage");
        Intrinsics.checkParameterIsNotNull(appChannel, "appChannel");
        Intrinsics.checkParameterIsNotNull(appName, "appName");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(appLanguage, "appLanguage");
        Intrinsics.checkParameterIsNotNull(customEvent, "customEvent");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        Intrinsics.checkParameterIsNotNull(deviceId, "deviceId");
        Intrinsics.checkParameterIsNotNull(deviceUniqueId, "deviceUniqueId");
        Intrinsics.checkParameterIsNotNull(deviceIp, "deviceIp");
        Intrinsics.checkParameterIsNotNull(deviceModel, "deviceModel");
        Intrinsics.checkParameterIsNotNull(deviceTimezone, "deviceTimezone");
        Intrinsics.checkParameterIsNotNull(elementId, "elementId");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(header, "header");
        Intrinsics.checkParameterIsNotNull(headerCustom, "headerCustom");
        Intrinsics.checkParameterIsNotNull(latitude, "latitude");
        Intrinsics.checkParameterIsNotNull(locationCity, "locationCity");
        Intrinsics.checkParameterIsNotNull(locationProvince, "locationProvince");
        Intrinsics.checkParameterIsNotNull(longitude, "longitude");
        Intrinsics.checkParameterIsNotNull(networkType, "networkType");
        Intrinsics.checkParameterIsNotNull(osVersion, "osVersion");
        Intrinsics.checkParameterIsNotNull(pageId, "pageId");
        Intrinsics.checkParameterIsNotNull(pagePreId, "pagePreId");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(screenDensity, "screenDensity");
        Intrinsics.checkParameterIsNotNull(screenResolution, "screenResolution");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        Intrinsics.checkParameterIsNotNull(abtestId, "abtestId");
        Intrinsics.checkParameterIsNotNull(abtestGroup, "abtestGroup");
        Intrinsics.checkParameterIsNotNull(userUniqueId, "userUniqueId");
        Intrinsics.checkParameterIsNotNull(hardwareVersion, "hardwareVersion");
        return new TrackEvent(id, uuid, appInstallId, appPackage, appChannel, appId, appName, appVersion, appLanguage, customEvent, deviceType, deviceId, deviceUniqueId, deviceIp, deviceModel, deviceTimezone, elementId, event, header, headerCustom, latitude, locationCity, locationProvince, longitude, networkType, osVersion, pageId, pagePreId, params, screenDensity, screenResolution, sessionId, time, userId, abtestId, abtestGroup, userUniqueId, hardwareVersion, upload, priority);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackEvent)) {
            return false;
        }
        TrackEvent trackEvent = (TrackEvent) other;
        return this.id == trackEvent.id && Intrinsics.areEqual(this.uuid, trackEvent.uuid) && Intrinsics.areEqual(this.appInstallId, trackEvent.appInstallId) && Intrinsics.areEqual(this.appPackage, trackEvent.appPackage) && Intrinsics.areEqual(this.appChannel, trackEvent.appChannel) && this.appId == trackEvent.appId && Intrinsics.areEqual(this.appName, trackEvent.appName) && Intrinsics.areEqual(this.appVersion, trackEvent.appVersion) && Intrinsics.areEqual(this.appLanguage, trackEvent.appLanguage) && Intrinsics.areEqual(this.customEvent, trackEvent.customEvent) && Intrinsics.areEqual(this.deviceType, trackEvent.deviceType) && Intrinsics.areEqual(this.deviceId, trackEvent.deviceId) && Intrinsics.areEqual(this.deviceUniqueId, trackEvent.deviceUniqueId) && Intrinsics.areEqual(this.deviceIp, trackEvent.deviceIp) && Intrinsics.areEqual(this.deviceModel, trackEvent.deviceModel) && Intrinsics.areEqual(this.deviceTimezone, trackEvent.deviceTimezone) && Intrinsics.areEqual(this.elementId, trackEvent.elementId) && Intrinsics.areEqual(this.event, trackEvent.event) && Intrinsics.areEqual(this.header, trackEvent.header) && Intrinsics.areEqual(this.headerCustom, trackEvent.headerCustom) && Intrinsics.areEqual(this.latitude, trackEvent.latitude) && Intrinsics.areEqual(this.locationCity, trackEvent.locationCity) && Intrinsics.areEqual(this.locationProvince, trackEvent.locationProvince) && Intrinsics.areEqual(this.longitude, trackEvent.longitude) && Intrinsics.areEqual(this.networkType, trackEvent.networkType) && Intrinsics.areEqual(this.osVersion, trackEvent.osVersion) && Intrinsics.areEqual(this.pageId, trackEvent.pageId) && Intrinsics.areEqual(this.pagePreId, trackEvent.pagePreId) && Intrinsics.areEqual(this.params, trackEvent.params) && Intrinsics.areEqual(this.screenDensity, trackEvent.screenDensity) && Intrinsics.areEqual(this.screenResolution, trackEvent.screenResolution) && Intrinsics.areEqual(this.sessionId, trackEvent.sessionId) && this.time == trackEvent.time && Intrinsics.areEqual(this.userId, trackEvent.userId) && Intrinsics.areEqual(this.abtestId, trackEvent.abtestId) && Intrinsics.areEqual(this.abtestGroup, trackEvent.abtestGroup) && Intrinsics.areEqual(this.userUniqueId, trackEvent.userUniqueId) && Intrinsics.areEqual(this.hardwareVersion, trackEvent.hardwareVersion) && this.upload == trackEvent.upload && this.priority == trackEvent.priority;
    }

    public int hashCode() {
        int hashCode = Long.hashCode(this.id) * 31;
        String str = this.uuid;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.appInstallId;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.appPackage;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.appChannel;
        int hashCode5 = (((hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31) + Integer.hashCode(this.appId)) * 31;
        String str5 = this.appName;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.appVersion;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.appLanguage;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.customEvent;
        int hashCode9 = (hashCode8 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.deviceType;
        int hashCode10 = (hashCode9 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.deviceId;
        int hashCode11 = (hashCode10 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.deviceUniqueId;
        int hashCode12 = (hashCode11 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.deviceIp;
        int hashCode13 = (hashCode12 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.deviceModel;
        int hashCode14 = (hashCode13 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.deviceTimezone;
        int hashCode15 = (hashCode14 + (str14 != null ? str14.hashCode() : 0)) * 31;
        String str15 = this.elementId;
        int hashCode16 = (hashCode15 + (str15 != null ? str15.hashCode() : 0)) * 31;
        String str16 = this.event;
        int hashCode17 = (hashCode16 + (str16 != null ? str16.hashCode() : 0)) * 31;
        String str17 = this.header;
        int hashCode18 = (hashCode17 + (str17 != null ? str17.hashCode() : 0)) * 31;
        String str18 = this.headerCustom;
        int hashCode19 = (hashCode18 + (str18 != null ? str18.hashCode() : 0)) * 31;
        String str19 = this.latitude;
        int hashCode20 = (hashCode19 + (str19 != null ? str19.hashCode() : 0)) * 31;
        String str20 = this.locationCity;
        int hashCode21 = (hashCode20 + (str20 != null ? str20.hashCode() : 0)) * 31;
        String str21 = this.locationProvince;
        int hashCode22 = (hashCode21 + (str21 != null ? str21.hashCode() : 0)) * 31;
        String str22 = this.longitude;
        int hashCode23 = (hashCode22 + (str22 != null ? str22.hashCode() : 0)) * 31;
        String str23 = this.networkType;
        int hashCode24 = (hashCode23 + (str23 != null ? str23.hashCode() : 0)) * 31;
        String str24 = this.osVersion;
        int hashCode25 = (hashCode24 + (str24 != null ? str24.hashCode() : 0)) * 31;
        String str25 = this.pageId;
        int hashCode26 = (hashCode25 + (str25 != null ? str25.hashCode() : 0)) * 31;
        String str26 = this.pagePreId;
        int hashCode27 = (hashCode26 + (str26 != null ? str26.hashCode() : 0)) * 31;
        String str27 = this.params;
        int hashCode28 = (hashCode27 + (str27 != null ? str27.hashCode() : 0)) * 31;
        String str28 = this.screenDensity;
        int hashCode29 = (hashCode28 + (str28 != null ? str28.hashCode() : 0)) * 31;
        String str29 = this.screenResolution;
        int hashCode30 = (hashCode29 + (str29 != null ? str29.hashCode() : 0)) * 31;
        String str30 = this.sessionId;
        int hashCode31 = (((hashCode30 + (str30 != null ? str30.hashCode() : 0)) * 31) + Long.hashCode(this.time)) * 31;
        String str31 = this.userId;
        int hashCode32 = (hashCode31 + (str31 != null ? str31.hashCode() : 0)) * 31;
        String str32 = this.abtestId;
        int hashCode33 = (hashCode32 + (str32 != null ? str32.hashCode() : 0)) * 31;
        String str33 = this.abtestGroup;
        int hashCode34 = (hashCode33 + (str33 != null ? str33.hashCode() : 0)) * 31;
        String str34 = this.userUniqueId;
        int hashCode35 = (hashCode34 + (str34 != null ? str34.hashCode() : 0)) * 31;
        String str35 = this.hardwareVersion;
        return ((((hashCode35 + (str35 != null ? str35.hashCode() : 0)) * 31) + Integer.hashCode(this.upload)) * 31) + Integer.hashCode(this.priority);
    }

    public String toString() {
        return "TrackEvent(id=" + this.id + ", uuid=" + this.uuid + ", appInstallId=" + this.appInstallId + ", appPackage=" + this.appPackage + ", appChannel=" + this.appChannel + ", appId=" + this.appId + ", appName=" + this.appName + ", appVersion=" + this.appVersion + ", appLanguage=" + this.appLanguage + ", customEvent=" + this.customEvent + ", deviceType=" + this.deviceType + ", deviceId=" + this.deviceId + ", deviceUniqueId=" + this.deviceUniqueId + ", deviceIp=" + this.deviceIp + ", deviceModel=" + this.deviceModel + ", deviceTimezone=" + this.deviceTimezone + ", elementId=" + this.elementId + ", event=" + this.event + ", header=" + this.header + ", headerCustom=" + this.headerCustom + ", latitude=" + this.latitude + ", locationCity=" + this.locationCity + ", locationProvince=" + this.locationProvince + ", longitude=" + this.longitude + ", networkType=" + this.networkType + ", osVersion=" + this.osVersion + ", pageId=" + this.pageId + ", pagePreId=" + this.pagePreId + ", params=" + this.params + ", screenDensity=" + this.screenDensity + ", screenResolution=" + this.screenResolution + ", sessionId=" + this.sessionId + ", time=" + this.time + ", userId=" + this.userId + ", abtestId=" + this.abtestId + ", abtestGroup=" + this.abtestGroup + ", userUniqueId=" + this.userUniqueId + ", hardwareVersion=" + this.hardwareVersion + ", upload=" + this.upload + ", priority=" + this.priority + ")";
    }

    public TrackEvent(long j, String uuid, String appInstallId, String appPackage, String appChannel, int i, String appName, String appVersion, String appLanguage, String customEvent, String deviceType, String deviceId, String deviceUniqueId, String deviceIp, String deviceModel, String deviceTimezone, String elementId, String event, String header, String headerCustom, String latitude, String locationCity, String locationProvince, String longitude, String networkType, String osVersion, String pageId, String pagePreId, String params, String screenDensity, String screenResolution, String sessionId, long j2, String userId, String abtestId, String abtestGroup, String userUniqueId, String hardwareVersion, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(uuid, "uuid");
        Intrinsics.checkParameterIsNotNull(appInstallId, "appInstallId");
        Intrinsics.checkParameterIsNotNull(appPackage, "appPackage");
        Intrinsics.checkParameterIsNotNull(appChannel, "appChannel");
        Intrinsics.checkParameterIsNotNull(appName, "appName");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(appLanguage, "appLanguage");
        Intrinsics.checkParameterIsNotNull(customEvent, "customEvent");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        Intrinsics.checkParameterIsNotNull(deviceId, "deviceId");
        Intrinsics.checkParameterIsNotNull(deviceUniqueId, "deviceUniqueId");
        Intrinsics.checkParameterIsNotNull(deviceIp, "deviceIp");
        Intrinsics.checkParameterIsNotNull(deviceModel, "deviceModel");
        Intrinsics.checkParameterIsNotNull(deviceTimezone, "deviceTimezone");
        Intrinsics.checkParameterIsNotNull(elementId, "elementId");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(header, "header");
        Intrinsics.checkParameterIsNotNull(headerCustom, "headerCustom");
        Intrinsics.checkParameterIsNotNull(latitude, "latitude");
        Intrinsics.checkParameterIsNotNull(locationCity, "locationCity");
        Intrinsics.checkParameterIsNotNull(locationProvince, "locationProvince");
        Intrinsics.checkParameterIsNotNull(longitude, "longitude");
        Intrinsics.checkParameterIsNotNull(networkType, "networkType");
        Intrinsics.checkParameterIsNotNull(osVersion, "osVersion");
        Intrinsics.checkParameterIsNotNull(pageId, "pageId");
        Intrinsics.checkParameterIsNotNull(pagePreId, "pagePreId");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(screenDensity, "screenDensity");
        Intrinsics.checkParameterIsNotNull(screenResolution, "screenResolution");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        Intrinsics.checkParameterIsNotNull(abtestId, "abtestId");
        Intrinsics.checkParameterIsNotNull(abtestGroup, "abtestGroup");
        Intrinsics.checkParameterIsNotNull(userUniqueId, "userUniqueId");
        Intrinsics.checkParameterIsNotNull(hardwareVersion, "hardwareVersion");
        this.id = j;
        this.uuid = uuid;
        this.appInstallId = appInstallId;
        this.appPackage = appPackage;
        this.appChannel = appChannel;
        this.appId = i;
        this.appName = appName;
        this.appVersion = appVersion;
        this.appLanguage = appLanguage;
        this.customEvent = customEvent;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.deviceUniqueId = deviceUniqueId;
        this.deviceIp = deviceIp;
        this.deviceModel = deviceModel;
        this.deviceTimezone = deviceTimezone;
        this.elementId = elementId;
        this.event = event;
        this.header = header;
        this.headerCustom = headerCustom;
        this.latitude = latitude;
        this.locationCity = locationCity;
        this.locationProvince = locationProvince;
        this.longitude = longitude;
        this.networkType = networkType;
        this.osVersion = osVersion;
        this.pageId = pageId;
        this.pagePreId = pagePreId;
        this.params = params;
        this.screenDensity = screenDensity;
        this.screenResolution = screenResolution;
        this.sessionId = sessionId;
        this.time = j2;
        this.userId = userId;
        this.abtestId = abtestId;
        this.abtestGroup = abtestGroup;
        this.userUniqueId = userUniqueId;
        this.hardwareVersion = hardwareVersion;
        this.upload = i2;
        this.priority = i3;
    }

    public /* synthetic */ TrackEvent(long j, String str, String str2, String str3, String str4, int i, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, long j2, String str31, String str32, String str33, String str34, String str35, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0L : j, str, str2, str3, str4, i, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, str25, str26, str27, str28, str29, str30, j2, str31, str32, str33, str34, str35, i2, i3);
    }

    public final long getId() {
        return this.id;
    }

    public final void setId(long j) {
        this.id = j;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public final void setUuid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.uuid = str;
    }

    public final String getAppInstallId() {
        return this.appInstallId;
    }

    public final void setAppInstallId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appInstallId = str;
    }

    public final String getAppPackage() {
        return this.appPackage;
    }

    public final void setAppPackage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appPackage = str;
    }

    public final String getAppChannel() {
        return this.appChannel;
    }

    public final void setAppChannel(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appChannel = str;
    }

    public final int getAppId() {
        return this.appId;
    }

    public final void setAppId(int i) {
        this.appId = i;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final void setAppName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appName = str;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final void setAppVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appVersion = str;
    }

    public final String getAppLanguage() {
        return this.appLanguage;
    }

    public final void setAppLanguage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appLanguage = str;
    }

    public final String getCustomEvent() {
        return this.customEvent;
    }

    public final void setCustomEvent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.customEvent = str;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final void setDeviceType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceType = str;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final void setDeviceId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceId = str;
    }

    public final String getDeviceUniqueId() {
        return this.deviceUniqueId;
    }

    public final void setDeviceUniqueId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceUniqueId = str;
    }

    public final String getDeviceIp() {
        return this.deviceIp;
    }

    public final void setDeviceIp(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceIp = str;
    }

    public final String getDeviceModel() {
        return this.deviceModel;
    }

    public final void setDeviceModel(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceModel = str;
    }

    public final String getDeviceTimezone() {
        return this.deviceTimezone;
    }

    public final void setDeviceTimezone(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceTimezone = str;
    }

    public final String getElementId() {
        return this.elementId;
    }

    public final void setElementId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.elementId = str;
    }

    public final String getEvent() {
        return this.event;
    }

    public final void setEvent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.event = str;
    }

    public final String getHeader() {
        return this.header;
    }

    public final void setHeader(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.header = str;
    }

    public final String getHeaderCustom() {
        return this.headerCustom;
    }

    public final void setHeaderCustom(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.headerCustom = str;
    }

    public final String getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.latitude = str;
    }

    public final String getLocationCity() {
        return this.locationCity;
    }

    public final void setLocationCity(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.locationCity = str;
    }

    public final String getLocationProvince() {
        return this.locationProvince;
    }

    public final void setLocationProvince(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.locationProvince = str;
    }

    public final String getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.longitude = str;
    }

    public final String getNetworkType() {
        return this.networkType;
    }

    public final void setNetworkType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.networkType = str;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }

    public final void setOsVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.osVersion = str;
    }

    public final String getPageId() {
        return this.pageId;
    }

    public final void setPageId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.pageId = str;
    }

    public final String getPagePreId() {
        return this.pagePreId;
    }

    public final void setPagePreId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.pagePreId = str;
    }

    public final String getParams() {
        return this.params;
    }

    public final void setParams(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.params = str;
    }

    public final String getScreenDensity() {
        return this.screenDensity;
    }

    public final void setScreenDensity(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.screenDensity = str;
    }

    public final String getScreenResolution() {
        return this.screenResolution;
    }

    public final void setScreenResolution(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.screenResolution = str;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final void setSessionId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.sessionId = str;
    }

    public final long getTime() {
        return this.time;
    }

    public final void setTime(long j) {
        this.time = j;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.userId = str;
    }

    public final String getAbtestId() {
        return this.abtestId;
    }

    public final void setAbtestId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.abtestId = str;
    }

    public final String getAbtestGroup() {
        return this.abtestGroup;
    }

    public final void setAbtestGroup(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.abtestGroup = str;
    }

    public final String getUserUniqueId() {
        return this.userUniqueId;
    }

    public final void setUserUniqueId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.userUniqueId = str;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final int getUpload() {
        return this.upload;
    }

    public final void setUpload(int i) {
        this.upload = i;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final void setPriority(int i) {
        this.priority = i;
    }
}
