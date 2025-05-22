package com.pudu.library.loracall.utils;

import android.content.res.AssetManager;
import android.os.Environment;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.bean.LoraUpdateFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: UpdateUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u0006\u0010\u0013\u001a\u00020\rJ\"\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u00152\u0006\u0010\u0017\u001a\u00020\u0010H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0018"}, m3961d2 = {"Lcom/pudu/library/loracall/utils/UpdateUtils;", "", "()V", "comparator", "Ljava/util/Comparator;", "Lcom/pudu/library/loracall/bean/LoraUpdateFile;", "updateFilePath", "", "getUpdateFilePath", "()Ljava/lang/String;", "setUpdateFilePath", "(Ljava/lang/String;)V", "copyUpdateFile", "", "getUpdateList", "", "Ljava/io/File;", "getVersion", "file", "initUpdateFile", "sortUpdateFile", "", "", "dirFile", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UpdateUtils {
    public static final UpdateUtils INSTANCE = new UpdateUtils();
    private static String updateFilePath = "";
    private static final Comparator<LoraUpdateFile> comparator = new Comparator<LoraUpdateFile>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$comparator$1
        @Override // java.util.Comparator
        public final int compare(LoraUpdateFile loraUpdateFile, LoraUpdateFile loraUpdateFile2) {
            if (loraUpdateFile.getType() == -1 || loraUpdateFile2.getType() == -1) {
                return 0;
            }
            return (loraUpdateFile.getType() <= loraUpdateFile2.getType() && (loraUpdateFile.getType() != loraUpdateFile2.getType() || loraUpdateFile.getVersion() <= loraUpdateFile2.getVersion())) ? 1 : -1;
        }
    };

    private UpdateUtils() {
    }

    public final String getUpdateFilePath() {
        return updateFilePath;
    }

    public final void setUpdateFilePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        updateFilePath = str;
    }

    public final void initUpdateFile() {
        BuildersKt__Builders_commonKt.launch$default(KotlinUtilsKt.getMyScope(), null, null, new UpdateUtils$initUpdateFile$1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void copyUpdateFile() {
        try {
            AssetManager assets = LoRaClient.INSTANCE.getInstance().getMContext$library_loracall_release().getAssets();
            String[] list = assets.list("");
            if (list != null) {
                ArrayList<String> arrayList = new ArrayList();
                for (String it : list) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    if (StringsKt.contains$default((CharSequence) it, (CharSequence) "PJ0003_RobotRFM_Firmware_", false, 2, (Object) null)) {
                        arrayList.add(it);
                    }
                }
                for (String str : arrayList) {
                    StringBuilder sb = new StringBuilder();
                    File externalStorageDirectory = Environment.getExternalStorageDirectory();
                    Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
                    sb.append(externalStorageDirectory.getAbsolutePath());
                    sb.append(File.separator);
                    sb.append("pudu");
                    sb.append(File.separator);
                    sb.append("loracall");
                    sb.append(File.separator);
                    sb.append(str);
                    final File file = new File(sb.toString());
                    if (!file.exists()) {
                        InputStream open = assets.open(str);
                        Intrinsics.checkExpressionValueIsNotNull(open, "assetManager.open(fileName)");
                        KotlinUtilsKt.copyFile(open, new FileOutputStream(file));
                        KotlinUtilsKt.log$default(INSTANCE, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$copyUpdateFile$1$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "拷贝文件--" + file.getAbsolutePath();
                            }
                        }, 1, null);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<Integer, List<LoraUpdateFile>> sortUpdateFile(File dirFile) {
        File[] listFiles = dirFile.listFiles();
        Intrinsics.checkExpressionValueIsNotNull(listFiles, "dirFile.listFiles()");
        Sequence sortedWith = SequencesKt.sortedWith(SequencesKt.filter(SequencesKt.map(SequencesKt.filter(ArraysKt.asSequence(listFiles), new Function1<File, Boolean>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$sortUpdateFile$fileList$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(File file) {
                return Boolean.valueOf(invoke2(file));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(File it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (!it.isFile() || !StringsKt.contains$default((CharSequence) "bin", (CharSequence) FilesKt.getExtension(it), false, 2, (Object) null)) {
                    return false;
                }
                String name = it.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                return StringsKt.contains$default((CharSequence) name, (CharSequence) "PJ0003_RobotRFM_Firmware_", false, 2, (Object) null);
            }
        }), new Function1<File, LoraUpdateFile>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$sortUpdateFile$fileList$2
            @Override // kotlin.jvm.functions.Function1
            public final LoraUpdateFile invoke(File it) {
                LoraUpdateFile version;
                UpdateUtils updateUtils = UpdateUtils.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                version = updateUtils.getVersion(it);
                return version;
            }
        }), new Function1<LoraUpdateFile, Boolean>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$sortUpdateFile$fileList$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(LoraUpdateFile loraUpdateFile) {
                return Boolean.valueOf(invoke2(loraUpdateFile));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(LoraUpdateFile it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getFile() != null;
            }
        }), comparator);
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : sortedWith) {
            Integer valueOf = Integer.valueOf(((LoraUpdateFile) obj).getType());
            Object obj2 = linkedHashMap.get(valueOf);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(valueOf, obj2);
            }
            ((List) obj2).add(obj);
        }
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$sortUpdateFile$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "更新文件列表--" + linkedHashMap;
            }
        }, 1, null);
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LoraUpdateFile getVersion(File file) {
        LoraUpdateFile loraUpdateFile;
        try {
            Pattern compile = Pattern.compile("^PJ0003_RobotRFM_Firmware_T([0-9])+_V([0-9]+\\.[0-9]{2})$");
            Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(pattern)");
            Matcher matcher = compile.matcher(FilesKt.getNameWithoutExtension(file));
            Intrinsics.checkExpressionValueIsNotNull(matcher, "r.matcher(file.nameWithoutExtension)");
            if (matcher.matches()) {
                String group = matcher.group(1);
                int parseInt = group != null ? Integer.parseInt(group) : -1;
                String group2 = matcher.group(2);
                loraUpdateFile = new LoraUpdateFile(file, parseInt, group2 != null ? Float.parseFloat(group2) : -1.0f);
            } else {
                loraUpdateFile = new LoraUpdateFile(file, 0, Float.parseFloat((String) StringsKt.split$default((CharSequence) FilesKt.getNameWithoutExtension(file), new String[]{"_V"}, false, 0, 6, (Object) null).get(1)));
            }
            return loraUpdateFile;
        } catch (Exception e) {
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$getVersion$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "getVersion" + KotlinUtilsKt.stackTraceToString(e);
                }
            }, 1, null);
            return new LoraUpdateFile(null, 0, 0.0f, 7, null);
        }
    }

    public final List<File> getUpdateList() {
        List<File> emptyList = CollectionsKt.emptyList();
        try {
            StringBuilder sb = new StringBuilder();
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
            sb.append(externalStorageDirectory.getAbsolutePath());
            sb.append(File.separator);
            sb.append("pudu/loracall");
            File file = new File(sb.toString());
            if (!file.exists()) {
                return emptyList;
            }
            Map<Integer, List<LoraUpdateFile>> sortUpdateFile = sortUpdateFile(file);
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<Integer, List<LoraUpdateFile>>> it = sortUpdateFile.entrySet().iterator();
            while (it.hasNext()) {
                List<LoraUpdateFile> value = it.next().getValue();
                ArrayList arrayList2 = new ArrayList();
                Iterator<T> it2 = value.iterator();
                while (it2.hasNext()) {
                    File file2 = ((LoraUpdateFile) it2.next()).getFile();
                    if (file2 != null) {
                        arrayList2.add(file2);
                    }
                }
                CollectionsKt.addAll(arrayList, arrayList2);
            }
            return arrayList;
        } catch (Exception e) {
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.utils.UpdateUtils$getUpdateList$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return KotlinUtilsKt.stackTraceToString(e);
                }
            }, 1, null);
            return emptyList;
        }
    }
}
