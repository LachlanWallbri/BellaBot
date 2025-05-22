package com.pudutech.importmusic;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ReplayLanguage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/importmusic/ReplayLanguage;", "", "()V", "languageStrMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "replayEn", "s", "ImportMusic_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ReplayLanguage {
    public static final ReplayLanguage INSTANCE = new ReplayLanguage();
    private static final HashMap<String, String> languageStrMap = new HashMap<>();

    static {
        languageStrMap.put("上传文件", "Upload file");
        languageStrMap.put("文件名称", "File name");
        languageStrMap.put("文件大小", "File size");
        languageStrMap.put("文件类型必须是音频文件，IOS用户无法使用此功能", "File type must be audio file; IOS users cannot use this function");
        languageStrMap.put("选择音乐文件", "Select music file");
        languageStrMap.put("上传音乐文件", "Upload music file");
        languageStrMap.put("请选择50M内的文件", "Please select a file less than 50m");
        languageStrMap.put("提示", "Tips");
        languageStrMap.put("文件格式不对，无法上传", "The file format is not correct and cannot be uploaded");
        languageStrMap.put("上传成功", "Upload succeeded");
        languageStrMap.put("请重试", "Please try again");
        languageStrMap.put("上传失败", "Upload failed");
        languageStrMap.put("请刷新网页或检查网络", "Please refresh the page or check the network");
        languageStrMap.put("IOS 用户无法使用此功能", "IOS users cannot use this feature");
        languageStrMap.put("还未选择文件", "File not selected yet");
        languageStrMap.put("文件超过50M，无法上传", "The file is larger than 50m and cannot be uploaded");
        languageStrMap.put("文件格式不对，无法上传", "The file format is not correct and cannot be uploaded");
        languageStrMap.put("对端拒绝或网络错误", "Peer reject or network error");
    }

    private ReplayLanguage() {
    }

    public final String replayEn(String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        String str = s;
        for (Map.Entry<String, String> entry : languageStrMap.entrySet()) {
            str = StringsKt.replace$default(str, entry.getKey(), entry.getValue(), false, 4, (Object) null);
        }
        return str;
    }
}
