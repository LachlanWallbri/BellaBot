package com.pudutech.bumblebee.presenter.robot_voices;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.disinfect.baselib.network.response.TtsVoiceInfo;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.Option;
import com.pudutech.resources.voice.VoiceItem;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.Soundex;

/* compiled from: VoicePlayerHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper;", "", "()V", "TAG", "", "checkCustom", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType;", "checkFileExist", "", "item", "Lcom/pudutech/resources/voice/VoiceItem;", "getCustomAll", "", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceDataSource;", "name", "getCustomRandom", "appointIndex", "", "getDefaultAll", "getDefaultRandom", "getMerchantTtsRandom", "Lcom/pudutech/disinfect/baselib/network/response/TtsVoiceInfo;", "loadCustomVoiceToMemory", "", "string", "loadDefaultVoice", "appContext", "Landroid/content/Context;", "Companion", "VoiceType", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoicePlayerHelper {
    private static ArrayList<VoiceDataSource> customFiles;
    private final String TAG = "VoicePlayerHelper";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object lock = new Object();
    private static ArrayList<String> defaultFiles = new ArrayList<>();
    private static String defaultFilePath = "";

    /* compiled from: VoicePlayerHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0004j\b\u0012\u0004\u0012\u00020\b`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$Companion;", "", "()V", "customFiles", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceDataSource;", "Lkotlin/collections/ArrayList;", "<set-?>", "", "defaultFilePath", "getDefaultFilePath", "()Ljava/lang/String;", "setDefaultFilePath", "(Ljava/lang/String;)V", "defaultFiles", "lock", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void setDefaultFilePath(String str) {
            VoicePlayerHelper.defaultFilePath = str;
        }

        public final String getDefaultFilePath() {
            return VoicePlayerHelper.defaultFilePath;
        }
    }

    public final void loadCustomVoiceToMemory() {
        String selectedIDRecord = new VoicePackageHelper().getSelectedIDRecord();
        if (selectedIDRecord != null) {
            loadCustomVoiceToMemory(selectedIDRecord);
        }
    }

    public final boolean loadCustomVoiceToMemory(String string) {
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

    /* compiled from: VoicePlayerHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType;", "", "()V", "CustomVoice", "DefaultVoice", "MerchantTtsVoice", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType$DefaultVoice;", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType$CustomVoice;", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType$MerchantTtsVoice;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static abstract class VoiceType {

        /* compiled from: VoicePlayerHelper.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType$DefaultVoice;", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType;", "()V", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultVoice extends VoiceType {
            public static final DefaultVoice INSTANCE = new DefaultVoice();

            private DefaultVoice() {
                super(null);
            }
        }

        private VoiceType() {
        }

        public /* synthetic */ VoiceType(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: VoicePlayerHelper.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType$CustomVoice;", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType;", "()V", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class CustomVoice extends VoiceType {
            public static final CustomVoice INSTANCE = new CustomVoice();

            private CustomVoice() {
                super(null);
            }
        }

        /* compiled from: VoicePlayerHelper.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType$MerchantTtsVoice;", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType;", "()V", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class MerchantTtsVoice extends VoiceType {
            public static final MerchantTtsVoice INSTANCE = new MerchantTtsVoice();

            private MerchantTtsVoice() {
                super(null);
            }
        }
    }

    public final VoiceType checkCustom() {
        if (Constant.INSTANCE.isSelectMerchantTts$module_bumblebee_presenter_robotRelease()) {
            return VoiceType.MerchantTtsVoice.INSTANCE;
        }
        String selectedIDRecord = new VoicePackageHelper().getSelectedIDRecord();
        if (selectedIDRecord == null || selectedIDRecord.length() == 0) {
            return VoiceType.DefaultVoice.INSTANCE;
        }
        return VoiceType.CustomVoice.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005c A[Catch: all -> 0x0132, TRY_LEAVE, TryCatch #0 {, blocks: (B:14:0x0045, B:16:0x0050, B:21:0x005c, B:27:0x0074, B:28:0x0077, B:29:0x0084, B:31:0x008a, B:34:0x00b4, B:39:0x00b8, B:41:0x00ee, B:44:0x0112, B:46:0x0118, B:49:0x0120, B:52:0x006e), top: B:13:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final TtsVoiceInfo getMerchantTtsRandom(String name, int appointIndex) {
        boolean z;
        Pdlog.m3273d(this.TAG, "getMerchantTtsRandom() name =" + name + " appointIndex =" + appointIndex);
        String str = name;
        if (str == null || str.length() == 0) {
            Pdlog.m3277w(this.TAG, "try random. name was null");
            return null;
        }
        synchronized (lock) {
            List<TtsVoiceInfo> merchantTts = VoicePackageHelper.INSTANCE.getMerchantTts();
            List<TtsVoiceInfo> list = merchantTts;
            if (list != null && !list.isEmpty()) {
                z = false;
                if (!z) {
                    Pdlog.m3277w(this.TAG, "try random. list was null");
                    return null;
                }
                String valueOf = appointIndex < 0 ? "" : String.valueOf(appointIndex);
                if (merchantTts == null) {
                    Intrinsics.throwNpe();
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : merchantTts) {
                    if (StringsKt.contains$default((CharSequence) ((TtsVoiceInfo) obj).getId(), (CharSequence) (name + '_' + valueOf), false, 2, (Object) null)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = arrayList;
                Pdlog.m3273d(this.TAG, "random " + name + " list size=" + arrayList2.size() + " list =" + arrayList2);
                if (arrayList2.isEmpty()) {
                    Pdlog.m3277w(this.TAG, "none contains name=" + name + " appoint=" + valueOf);
                    return null;
                }
                if (arrayList2.size() == 1) {
                    return (TtsVoiceInfo) arrayList2.get(0);
                }
                return (TtsVoiceInfo) arrayList2.get(Random.INSTANCE.nextInt(arrayList2.size()));
            }
            z = true;
            if (!z) {
            }
        }
    }

    public final VoiceDataSource getCustomRandom(String name, int appointIndex) {
        if (name == null) {
            Pdlog.m3277w(this.TAG, "try random. name was null");
            return null;
        }
        synchronized (lock) {
            if (customFiles == null) {
                Pdlog.m3277w(this.TAG, "try random. list was null");
                return null;
            }
            String valueOf = appointIndex < 0 ? "" : String.valueOf(appointIndex);
            ArrayList<VoiceDataSource> arrayList = customFiles;
            if (arrayList == null) {
                Intrinsics.throwNpe();
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (StringsKt.contains$default((CharSequence) ((VoiceDataSource) obj).getName(), (CharSequence) (name + '_' + valueOf), false, 2, (Object) null)) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            Pdlog.m3273d(this.TAG, "random " + name + " list size=" + arrayList3.size());
            if (arrayList3.isEmpty()) {
                Pdlog.m3277w(this.TAG, "none contains name=" + name + " appoint=" + valueOf);
                return null;
            }
            if (arrayList3.size() == 1) {
                return (VoiceDataSource) arrayList3.get(0);
            }
            return (VoiceDataSource) arrayList3.get(Random.INSTANCE.nextInt(arrayList3.size()));
        }
    }

    public final String loadDefaultVoice(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Pdlog.m3275i(this.TAG, "loadDefaultVoice");
        Option current = new LanguageUtils(appContext).getCurrent();
        String[] list = appContext.getAssets().list("voices");
        Pdlog.m3275i(this.TAG, "language current=" + current + " list=" + list);
        Intrinsics.checkExpressionValueIsNotNull(list, "list");
        int length = list.length;
        int i = 0;
        String str = "zh";
        for (int i2 = 0; i2 < length; i2++) {
            String it = list[i2];
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            String language = current.getLocale().getLanguage();
            Intrinsics.checkExpressionValueIsNotNull(language, "option.locale.language");
            if (StringsKt.contains$default((CharSequence) it, (CharSequence) language, false, 2, (Object) null)) {
                i++;
                str = it;
            }
        }
        if (i > 1) {
            for (String it2 : list) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                if (StringsKt.contains$default((CharSequence) it2, (CharSequence) (current.getLocale().getLanguage() + Soundex.SILENT_MARKER + current.getLocale().getCountry()), false, 2, (Object) null)) {
                    str = it2;
                }
            }
        }
        defaultFilePath = ("voices" + File.separator) + str;
        String[] list2 = appContext.getAssets().list(defaultFilePath);
        Pdlog.m3275i(this.TAG, "set filePath=" + defaultFilePath + " sameCnt=" + i + " fileSize=" + list2.length);
        synchronized (lock) {
            defaultFiles.clear();
            if (list2 != null) {
                for (String str2 : list2) {
                    defaultFiles.add(str2);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return defaultFilePath;
    }

    public final String getDefaultRandom(String name, int appointIndex) {
        if (name == null) {
            Pdlog.m3277w(this.TAG, "try random. name was null");
            return null;
        }
        synchronized (lock) {
            String valueOf = appointIndex < 0 ? "" : String.valueOf(appointIndex);
            ArrayList<String> arrayList = defaultFiles;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (StringsKt.contains$default((CharSequence) obj, (CharSequence) (name + '_' + valueOf), false, 2, (Object) null)) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            Pdlog.m3273d(this.TAG, "random " + name + " list size=" + arrayList3.size());
            if (arrayList3.isEmpty()) {
                Pdlog.m3277w(this.TAG, "none contains name=" + name + " appoint=" + valueOf);
                return null;
            }
            if (arrayList3.size() == 1) {
                return (String) arrayList3.get(0);
            }
            return (String) arrayList3.get(Random.INSTANCE.nextInt(arrayList3.size()));
        }
    }

    public final boolean checkFileExist(VoiceItem item) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(item, "item");
        String str = item.name() + '_';
        synchronized (lock) {
            VoiceType checkCustom = checkCustom();
            ArrayList arrayList = null;
            if (Intrinsics.areEqual(checkCustom, VoiceType.MerchantTtsVoice.INSTANCE)) {
                List<TtsVoiceInfo> merchantTts = VoicePackageHelper.INSTANCE.getMerchantTts();
                if (merchantTts != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj : merchantTts) {
                        if (StringsKt.contains$default((CharSequence) ((TtsVoiceInfo) obj).getId(), (CharSequence) str, false, 2, (Object) null)) {
                            arrayList2.add(obj);
                        }
                    }
                    arrayList = arrayList2;
                }
            } else if (Intrinsics.areEqual(checkCustom, VoiceType.DefaultVoice.INSTANCE)) {
                ArrayList<String> arrayList3 = defaultFiles;
                ArrayList arrayList4 = new ArrayList();
                for (Object obj2 : arrayList3) {
                    if (StringsKt.contains$default((CharSequence) obj2, (CharSequence) str, false, 2, (Object) null)) {
                        arrayList4.add(obj2);
                    }
                }
                arrayList = arrayList4;
            } else if (Intrinsics.areEqual(checkCustom, VoiceType.CustomVoice.INSTANCE)) {
                ArrayList<VoiceDataSource> arrayList5 = customFiles;
                if (arrayList5 != null) {
                    ArrayList arrayList6 = new ArrayList();
                    for (Object obj3 : arrayList5) {
                        if (StringsKt.contains$default((CharSequence) ((VoiceDataSource) obj3).getName(), (CharSequence) str, false, 2, (Object) null)) {
                            arrayList6.add(obj3);
                        }
                    }
                    arrayList = arrayList6;
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
            Pdlog.m3273d(this.TAG, "checkFileExist() item =" + item + "  list =" + arrayList);
            ArrayList arrayList7 = arrayList;
            z = !(arrayList7 == null || arrayList7.isEmpty());
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
}
