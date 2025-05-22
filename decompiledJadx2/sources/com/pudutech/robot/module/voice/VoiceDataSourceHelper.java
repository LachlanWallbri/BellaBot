package com.pudutech.robot.module.voice;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.module.voice.data.VoiceDataSource;
import com.pudutech.robot.module.voice.pkg.VoicePackageHelper;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.Soundex;

/* compiled from: VoiceDataSourceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004J\u0018\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u0019\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\u000fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\u0012J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004J\r\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017J\u0015\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0017J\u001d\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/robot/module/voice/VoiceDataSourceHelper;", "", "()V", "TAG", "", "checkCustom", "", "checkCustom$module_robot_voice_release", "checkFileExist", "itemName", "getCustomAll", "", "Lcom/pudutech/robot/module/voice/data/VoiceDataSource;", "name", "getCustomRandom", "getCustomRandom$module_robot_voice_release", "getDefaultAll", "getDefaultRandom", "getDefaultRandom$module_robot_voice_release", "getPathFromDefault", "path", "loadCustomVoiceToMemory", "", "loadCustomVoiceToMemory$module_robot_voice_release", "string", "loadDefaultVoice", "appContext", "Landroid/content/Context;", "locale", "Ljava/util/Locale;", "loadDefaultVoice$module_robot_voice_release", "Companion", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoiceDataSourceHelper {
    private static ArrayList<VoiceDataSource> customFiles;
    private final String TAG = "VoiceDataSourceHelper";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object lock = new Object();
    private static ArrayList<String> defaultFiles = new ArrayList<>();
    private static String defaultFilePath = "";

    /* compiled from: VoiceDataSourceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0004j\b\u0012\u0004\u0012\u00020\b`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/VoiceDataSourceHelper$Companion;", "", "()V", "customFiles", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/voice/data/VoiceDataSource;", "Lkotlin/collections/ArrayList;", "<set-?>", "", "defaultFilePath", "getDefaultFilePath", "()Ljava/lang/String;", "setDefaultFilePath", "(Ljava/lang/String;)V", "defaultFiles", "lock", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void setDefaultFilePath(String str) {
            VoiceDataSourceHelper.defaultFilePath = str;
        }

        public final String getDefaultFilePath() {
            return VoiceDataSourceHelper.defaultFilePath;
        }
    }

    public final void loadCustomVoiceToMemory$module_robot_voice_release() {
        String selectedIDRecord = new VoicePackageHelper().getSelectedIDRecord();
        if (selectedIDRecord != null) {
            loadCustomVoiceToMemory$module_robot_voice_release(selectedIDRecord);
        }
    }

    public final boolean loadCustomVoiceToMemory$module_robot_voice_release(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Pdlog.m3275i(this.TAG, "loadCustomVoiceToMemory. selected=" + string);
        File file = new File(VoicePackageHelper.INSTANCE.getPackagesPath(), string);
        ZipFile zipFile = new ZipFile(file);
        Pdlog.m3275i(this.TAG, "file=" + file + " size=" + zipFile.size());
        if (!file.exists()) {
            Pdlog.m3275i(this.TAG, "file not exist");
            return false;
        }
        synchronized (lock) {
            customFiles = new ArrayList<>();
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            Intrinsics.checkExpressionValueIsNotNull(entries, "zipFile.entries()");
            Iterator it = CollectionsKt.iterator(entries);
            while (it.hasNext()) {
                ZipEntry entry = (ZipEntry) it.next();
                String str = this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("load ");
                Intrinsics.checkExpressionValueIsNotNull(entry, "entry");
                sb.append(entry.getName());
                sb.append(" isFile=");
                sb.append(!entry.isDirectory());
                objArr[0] = sb.toString();
                Pdlog.m3275i(str, objArr);
                if (!entry.isDirectory()) {
                    VoiceDataSource voiceDataSource = new VoiceDataSource();
                    String name = entry.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "entry.name");
                    InputStream inputStream = zipFile.getInputStream(entry);
                    Intrinsics.checkExpressionValueIsNotNull(inputStream, "zipFile.getInputStream(entry)");
                    voiceDataSource.setSource(name, ByteStreamsKt.readBytes(inputStream));
                    ArrayList<VoiceDataSource> arrayList = customFiles;
                    if (arrayList == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(voiceDataSource);
                }
            }
            String str2 = this.TAG;
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("loadCustomVoiceToMemory all size=");
            ArrayList<VoiceDataSource> arrayList2 = customFiles;
            sb2.append(arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null);
            objArr2[0] = sb2.toString();
            Pdlog.m3275i(str2, objArr2);
        }
        return true;
    }

    public final boolean checkCustom$module_robot_voice_release() {
        String selectedIDRecord = new VoicePackageHelper().getSelectedIDRecord();
        return ((selectedIDRecord == null || selectedIDRecord.length() == 0) || customFiles == null) ? false : true;
    }

    public final VoiceDataSource getCustomRandom$module_robot_voice_release(String name) {
        VoiceDataSource voiceDataSource = null;
        if (name == null) {
            Pdlog.m3277w(this.TAG, "try random. name was null");
            return null;
        }
        synchronized (lock) {
            if (customFiles == null) {
                Pdlog.m3277w(this.TAG, "try random. list was null");
                return null;
            }
            ArrayList<VoiceDataSource> arrayList = customFiles;
            if (arrayList == null) {
                Intrinsics.throwNpe();
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (StringsKt.contains$default((CharSequence) ((VoiceDataSource) obj).getName(), (CharSequence) (name + '_'), false, 2, (Object) null)) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            Pdlog.m3273d(this.TAG, "random " + name + " list size=" + arrayList3.size());
            if (arrayList3.isEmpty()) {
                Pdlog.m3277w(this.TAG, "none contains name=" + name + ' ');
            } else if (arrayList3.size() == 1) {
                voiceDataSource = (VoiceDataSource) arrayList3.get(0);
            } else {
                voiceDataSource = (VoiceDataSource) arrayList3.get(Random.INSTANCE.nextInt(arrayList3.size()));
            }
            return voiceDataSource;
        }
    }

    public final String loadDefaultVoice$module_robot_voice_release(Context appContext, Locale locale) {
        int i;
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Pdlog.m3275i(this.TAG, "loadDefaultVoice");
        String[] list = appContext.getAssets().list("voices");
        Pdlog.m3275i(this.TAG, "language current=" + locale + " list=" + list);
        String str = "zh";
        if (list != null) {
            i = 0;
            String str2 = "zh";
            for (String it : list) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                String language = locale.getLanguage();
                Intrinsics.checkExpressionValueIsNotNull(language, "locale.language");
                if (StringsKt.contains$default((CharSequence) it, (CharSequence) language, false, 2, (Object) null)) {
                    i++;
                    str2 = it;
                }
            }
            str = str2;
        } else {
            i = 0;
        }
        if (i > 1 && list != null) {
            String str3 = str;
            for (String it2 : list) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                if (StringsKt.contains$default((CharSequence) it2, (CharSequence) (locale.getLanguage() + Soundex.SILENT_MARKER + locale.getCountry()), false, 2, (Object) null)) {
                    str3 = it2;
                }
            }
            str = str3;
        }
        defaultFilePath = ("voices" + File.separator) + str;
        String[] list2 = appContext.getAssets().list(defaultFilePath);
        String str4 = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("set filePath=");
        sb.append(defaultFilePath);
        sb.append(" sameCnt=");
        sb.append(i);
        sb.append(" fileSize=");
        sb.append(list2 != null ? Integer.valueOf(list2.length) : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str4, objArr);
        synchronized (lock) {
            defaultFiles.clear();
            if (list2 != null) {
                for (String str5 : list2) {
                    defaultFiles.add(str5);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return defaultFilePath;
    }

    public final String getDefaultRandom$module_robot_voice_release(String name) {
        String str = null;
        if (name == null) {
            Pdlog.m3277w(this.TAG, "try random. name was null");
            return null;
        }
        synchronized (lock) {
            ArrayList<String> arrayList = defaultFiles;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (StringsKt.contains$default((CharSequence) obj, (CharSequence) (name + '_'), false, 2, (Object) null)) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            Pdlog.m3273d(this.TAG, "random " + name + " list size=" + arrayList3.size());
            if (arrayList3.isEmpty()) {
                Pdlog.m3277w(this.TAG, "none contains name=" + name + ' ');
            } else if (arrayList3.size() == 1) {
                str = (defaultFilePath + File.separator) + ((String) arrayList3.get(0));
            } else {
                int nextInt = Random.INSTANCE.nextInt(arrayList3.size());
                str = (defaultFilePath + File.separator) + ((String) arrayList3.get(nextInt));
            }
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x008e, code lost:
    
        if (r4.isEmpty() != false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean checkFileExist(String itemName) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(itemName, "itemName");
        String str = itemName + '_';
        synchronized (lock) {
            boolean z2 = false;
            ArrayList arrayList = null;
            if (checkCustom$module_robot_voice_release()) {
                ArrayList<VoiceDataSource> arrayList2 = customFiles;
                if (arrayList2 != null) {
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj : arrayList2) {
                        if (StringsKt.contains$default((CharSequence) ((VoiceDataSource) obj).getName(), (CharSequence) str, false, 2, (Object) null)) {
                            arrayList3.add(obj);
                        }
                    }
                    arrayList = arrayList3;
                }
            } else {
                ArrayList<String> arrayList4 = defaultFiles;
                ArrayList arrayList5 = new ArrayList();
                for (Object obj2 : arrayList4) {
                    if (StringsKt.contains$default((CharSequence) obj2, (CharSequence) str, false, 2, (Object) null)) {
                        arrayList5.add(obj2);
                    }
                }
                arrayList = arrayList5;
            }
            ArrayList arrayList6 = arrayList;
            if (arrayList6 != null) {
            }
            z2 = true;
            z = true ^ z2;
        }
        return z;
    }

    public final List<VoiceDataSource> getCustomAll(String name) {
        ArrayList<VoiceDataSource> arrayList = customFiles;
        if (arrayList == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (StringsKt.contains$default((CharSequence) ((VoiceDataSource) obj).getName(), (CharSequence) (name + '_'), false, 2, (Object) null)) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public final List<String> getDefaultAll(String name) {
        ArrayList<String> arrayList = defaultFiles;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (StringsKt.contains$default((CharSequence) obj, (CharSequence) (name + '_'), false, 2, (Object) null)) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public final String getPathFromDefault(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        String str = defaultFilePath;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append('/');
        ArrayList<String> arrayList = defaultFiles;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (StringsKt.contains$default((CharSequence) obj, (CharSequence) path, false, 2, (Object) null)) {
                arrayList2.add(obj);
            }
        }
        sb.append((String) arrayList2.get(0));
        return sb.toString();
    }
}
