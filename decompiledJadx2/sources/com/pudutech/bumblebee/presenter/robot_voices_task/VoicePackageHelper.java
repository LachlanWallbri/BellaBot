package com.pudutech.bumblebee.presenter.robot_voices_task;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayerHelper;
import com.pudutech.bumblebee.presenter.robot_voices_task.protocol.Download;
import com.pudutech.bumblebee.presenter.robot_voices_task.protocol.Record;
import com.pudutech.disinfect.baselib.network.response.TtsVoiceInfo;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloud;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.resources.resource.ResUtil;
import java.io.File;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: VoicePackageHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0002J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\nJ\u0018\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\nH\u0002J\u0006\u0010 \u001a\u00020!J\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\"\u001a\u00020!2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\nJ\u0006\u0010%\u001a\u00020\nJ\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040'J\u0006\u0010(\u001a\u00020!J\u0016\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020,J\u0014\u0010-\u001a\u00020!2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00040'R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R*\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u00060"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageHelper;", "", "()V", "defaultPackage", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "getDefaultPackage", "()Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "defaultPackage$delegate", "Lkotlin/Lazy;", ES6Iterator.VALUE_PROPERTY, "", "selectedIDRecord", "getSelectedIDRecord", "()Ljava/lang/String;", "setSelectedIDRecord", "(Ljava/lang/String;)V", "bytesToHexString", "bytes", "", "calMD5", "file", "Ljava/io/File;", "calMD5Mask", "string", "checkMD5Same", "", "zipName", "md5", "checkPackageExistAndDeleteIllegal", "id", "", "md5Mask", "cleanDownloads", "", "deleteFile", "deletePackFile", "name", "getCurrentVoiceName", "getDownloadList", "", "loadRecord", "moveDownloadsToPackage", "fileName", "cloudPack", "Lcom/pudutech/disinfect/baselib/network/response/VoicePackCloud;", "syncRecordToSDCard", "list", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoicePackageHelper {
    private static Record record;
    private static SharedPreferences sharedPreferences;

    /* renamed from: defaultPackage$delegate, reason: from kotlin metadata */
    private final Lazy defaultPackage = LazyKt.lazy(new Function0<VoicePackageInfo>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper$defaultPackage$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VoicePackageInfo invoke() {
            VoicePackageInfo voicePackageInfo = new VoicePackageInfo();
            voicePackageInfo.setId(VoicePackageHelper.INSTANCE.getDefaultID());
            voicePackageInfo.setName(ResUtil.INSTANCE.getString("advance_battery_guard_level_default"));
            boolean z = true;
            voicePackageInfo.setExist(true);
            String selectedIDRecord = VoicePackageHelper.this.getSelectedIDRecord();
            if (selectedIDRecord != null && selectedIDRecord.length() != 0) {
                z = false;
            }
            voicePackageInfo.setSelected(z);
            return voicePackageInfo;
        }
    });
    private String selectedIDRecord;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String FILE_NAME = FILE_NAME;
    private static final String FILE_NAME = FILE_NAME;
    private static final String KEY_SELECTED = KEY_SELECTED;
    private static final String KEY_SELECTED = KEY_SELECTED;
    private static final String KEY_RECORD = KEY_RECORD;
    private static final String KEY_RECORD = KEY_RECORD;
    private static final String PackagesPath = "sdcard" + File.separator + "voice_package";
    private static final String DownloadPath = "sdcard" + File.separator + "voice_download";
    private static final long DefaultID = -1;
    private static final long DefaultBusinessVoice = -2;

    private final VoicePackageInfo getDefaultPackage() {
        return (VoicePackageInfo) this.defaultPackage.getValue();
    }

    /* compiled from: VoicePackageHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0012\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u000e\u0010\u0014\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR(\u0010\u001b\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageHelper$Companion;", "", "()V", "DefaultBusinessVoice", "", "getDefaultBusinessVoice", "()J", "DefaultID", "getDefaultID", "DownloadPath", "", "getDownloadPath", "()Ljava/lang/String;", "FILE_NAME", "KEY_RECORD", "getKEY_RECORD", "KEY_SELECTED", "getKEY_SELECTED", "PackagesPath", "getPackagesPath", "TAG", ES6Iterator.VALUE_PROPERTY, "", "isSelectMerchantTts", "()Z", "setSelectMerchantTts", "(Z)V", "merchantTtsData", "getMerchantTtsData", "setMerchantTtsData", "(Ljava/lang/String;)V", VoicePackageHelper.KEY_RECORD, "Lcom/pudutech/bumblebee/presenter/robot_voices_task/protocol/Record;", "sharedPreferences", "Landroid/content/SharedPreferences;", "getMerchantTts", "", "Lcom/pudutech/disinfect/baselib/network/response/TtsVoiceInfo;", "getVoiceType", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper$VoiceType;", "voicePackageInfo", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "init", "", "context", "Landroid/content/Context;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getKEY_SELECTED() {
            return VoicePackageHelper.KEY_SELECTED;
        }

        public final String getKEY_RECORD() {
            return VoicePackageHelper.KEY_RECORD;
        }

        public final String getPackagesPath() {
            return VoicePackageHelper.PackagesPath;
        }

        public final String getDownloadPath() {
            return VoicePackageHelper.DownloadPath;
        }

        public final long getDefaultID() {
            return VoicePackageHelper.DefaultID;
        }

        public final long getDefaultBusinessVoice() {
            return VoicePackageHelper.DefaultBusinessVoice;
        }

        public final void init(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(VoicePackageHelper.FILE_NAME, 0);
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…ODE_PRIVATE\n            )");
            VoicePackageHelper.sharedPreferences = sharedPreferences;
            new VoicePackageHelper().loadRecord();
        }

        public final void setSelectMerchantTts(boolean z) {
            Constant.INSTANCE.setSelectMerchantTts$module_bumblebee_presenter_robotRelease(z);
        }

        public final boolean isSelectMerchantTts() {
            return Constant.INSTANCE.isSelectMerchantTts$module_bumblebee_presenter_robotRelease();
        }

        public final void setMerchantTtsData(String str) {
            Constant.INSTANCE.setMerchantTtsData$module_bumblebee_presenter_robotRelease(str);
        }

        public final String getMerchantTtsData() {
            return Constant.INSTANCE.getMerchantTtsData$module_bumblebee_presenter_robotRelease();
        }

        public final VoicePlayerHelper.VoiceType getVoiceType(VoicePackageInfo voicePackageInfo) {
            Intrinsics.checkParameterIsNotNull(voicePackageInfo, "voicePackageInfo");
            long id = voicePackageInfo.getId();
            if (id == -1) {
                return VoicePlayerHelper.VoiceType.DefaultVoice.INSTANCE;
            }
            if (id == -2) {
                return VoicePlayerHelper.VoiceType.MerchantTtsVoice.INSTANCE;
            }
            return VoicePlayerHelper.VoiceType.CustomVoice.INSTANCE;
        }

        public final List<TtsVoiceInfo> getMerchantTts() {
            return GsonSingleton.INSTANCE.getINSTANCE().fromJsonArray(getMerchantTtsData(), TtsVoiceInfo.class);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0091 A[Catch: Exception -> 0x00b7, TryCatch #0 {Exception -> 0x00b7, blocks: (B:7:0x001f, B:9:0x0032, B:11:0x0036, B:12:0x0040, B:14:0x0046, B:16:0x004a, B:18:0x004e, B:19:0x005b, B:21:0x0061, B:24:0x0078, B:29:0x007c, B:30:0x0081, B:32:0x0085, B:37:0x0091, B:38:0x00b4), top: B:6:0x001f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void loadRecord() {
        ArrayList arrayList;
        boolean z;
        ArrayList<Download> arrayList2;
        ArrayList<Download> arrayList3;
        Pdlog.m3275i(TAG, "loadRecord");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        String string = sharedPreferences2.getString(KEY_RECORD, null);
        if (string != null) {
            try {
                record = (Record) new Gson().fromJson(string, Record.class);
                Record record2 = record;
                if (record2 != null && (arrayList3 = record2.downloads) != null) {
                    arrayList3.removeIf(new Predicate<Download>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper$loadRecord$$inlined$let$lambda$1
                        @Override // java.util.function.Predicate
                        public final boolean test(Download download) {
                            boolean checkPackageExistAndDeleteIllegal;
                            VoicePackageHelper voicePackageHelper = VoicePackageHelper.this;
                            Long l = download.f4717id;
                            Intrinsics.checkExpressionValueIsNotNull(l, "it.id");
                            long longValue = l.longValue();
                            String str = download.md5_mask;
                            Intrinsics.checkExpressionValueIsNotNull(str, "it.md5_mask");
                            checkPackageExistAndDeleteIllegal = voicePackageHelper.checkPackageExistAndDeleteIllegal(longValue, str);
                            return !checkPackageExistAndDeleteIllegal;
                        }
                    });
                }
                String selectedIDRecord = getSelectedIDRecord();
                if (selectedIDRecord != null) {
                    Record record3 = record;
                    if (record3 == null || (arrayList2 = record3.downloads) == null) {
                        arrayList = null;
                    } else {
                        ArrayList arrayList4 = new ArrayList();
                        for (Object obj : arrayList2) {
                            if (Intrinsics.areEqual(String.valueOf(((Download) obj).f4717id.longValue()), selectedIDRecord)) {
                                arrayList4.add(obj);
                            }
                        }
                        arrayList = arrayList4;
                    }
                    ArrayList arrayList5 = arrayList;
                    if (arrayList5 != null && !arrayList5.isEmpty()) {
                        z = false;
                        if (z) {
                            Pdlog.m3277w(TAG, getSelectedIDRecord() + " remove");
                            setSelectedIDRecord((String) null);
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    z = true;
                    if (z) {
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "loadRecord " + e);
                Unit unit3 = Unit.INSTANCE;
            }
        }
    }

    public final void setSelectedIDRecord(String str) {
        this.selectedIDRecord = str;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        sharedPreferences2.edit().putString(KEY_SELECTED, str).apply();
        Pdlog.m3275i(TAG, "selectedIDRecord set " + str);
    }

    public final String getSelectedIDRecord() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return sharedPreferences2.getString(KEY_SELECTED, null);
    }

    public final List<VoicePackageInfo> getDownloadList() {
        ArrayList<Download> arrayList;
        ArrayList arrayList2 = new ArrayList();
        String selectedIDRecord = getSelectedIDRecord();
        Pdlog.m3275i(TAG, "load record. selected=" + selectedIDRecord);
        Record record2 = record;
        if (record2 != null && (arrayList = record2.downloads) != null) {
            for (Download download : arrayList) {
                Pdlog.m3275i(TAG, "load record. download=" + download.name + ' ' + download.f4717id + ' ' + download.version_code);
                Long l = download.f4717id;
                Intrinsics.checkExpressionValueIsNotNull(l, "it.id");
                long longValue = l.longValue();
                String str = download.md5_mask;
                Intrinsics.checkExpressionValueIsNotNull(str, "it.md5_mask");
                if (checkPackageExistAndDeleteIllegal(longValue, str)) {
                    VoicePackageInfo voicePackageInfo = new VoicePackageInfo();
                    String str2 = download.name;
                    Intrinsics.checkExpressionValueIsNotNull(str2, "it.name");
                    voicePackageInfo.setName(str2);
                    voicePackageInfo.setExist(true);
                    Long l2 = download.f4717id;
                    Intrinsics.checkExpressionValueIsNotNull(l2, "it.id");
                    voicePackageInfo.setId(l2.longValue());
                    Long l3 = download.version_code;
                    Intrinsics.checkExpressionValueIsNotNull(l3, "it.version_code");
                    voicePackageInfo.setVersion_code(l3.longValue());
                    if (Intrinsics.areEqual(String.valueOf(voicePackageInfo.getId()), selectedIDRecord)) {
                        voicePackageInfo.setSelected(true);
                    }
                    arrayList2.add(voicePackageInfo);
                } else {
                    Pdlog.m3275i(TAG, download.name + ' ' + download.f4717id + " not exist");
                }
            }
        }
        return arrayList2;
    }

    public final VoicePackageInfo defaultPackage() {
        getDefaultPackage().setName(ResUtil.INSTANCE.getString("advance_battery_guard_level_default"));
        VoicePackageInfo defaultPackage = getDefaultPackage();
        String selectedIDRecord = getSelectedIDRecord();
        defaultPackage.setSelected(selectedIDRecord == null || selectedIDRecord.length() == 0);
        return getDefaultPackage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkPackageExistAndDeleteIllegal(long id, String md5Mask) {
        Pdlog.m3275i(TAG, "checkPackageExistAndDeleteIllegal " + id);
        File file = new File(PackagesPath, String.valueOf(id));
        if (file.exists()) {
            if (StringsKt.equals(calMD5Mask(calMD5(file)), md5Mask, true)) {
                return true;
            }
            deletePackFile(String.valueOf(id));
            Pdlog.m3277w(TAG, "checkPackageExistAndDeleteIllegal " + file.getName() + " md5 not legal");
        }
        return false;
    }

    public final void deletePackFile(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3275i(TAG, "deletePackFile " + name);
        File file = new File(PackagesPath, name);
        if (file.exists()) {
            deleteFile(file);
        }
    }

    public final void cleanDownloads() {
        Pdlog.m3275i(TAG, "cleanDownloads");
        File file = new File(DownloadPath);
        if (file.exists()) {
            deleteFile(file);
        }
        file.mkdirs();
    }

    private final void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                Intrinsics.throwNpe();
            }
            for (File f : listFiles) {
                Intrinsics.checkExpressionValueIsNotNull(f, "f");
                deleteFile(f);
            }
            file.delete();
            return;
        }
        if (file.exists()) {
            file.delete();
        }
    }

    public final void syncRecordToSDCard(List<VoicePackageInfo> list) {
        ArrayList<Download> arrayList;
        ArrayList<Download> arrayList2;
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3275i(TAG, "syncRecordToSDCard " + list);
        for (final VoicePackageInfo voicePackageInfo : list) {
            Record record2 = record;
            if (record2 != null && (arrayList2 = record2.downloads) != null) {
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList2) {
                    Long l = ((Download) obj).f4717id;
                    if (l != null && l.longValue() == voicePackageInfo.getId()) {
                        arrayList3.add(obj);
                    }
                }
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    ((Download) it.next()).name = voicePackageInfo.getName();
                }
            }
            Record record3 = record;
            if (record3 != null && (arrayList = record3.downloads) != null) {
                arrayList.removeIf(new Predicate<Download>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper$syncRecordToSDCard$1$3
                    @Override // java.util.function.Predicate
                    public final boolean test(Download download) {
                        Long l2 = download.f4717id;
                        return (l2 == null || l2.longValue() != VoicePackageInfo.this.getId() || VoicePackageInfo.this.getIsExist()) ? false : true;
                    }
                });
            }
        }
        String json = new Gson().toJson(record);
        if (json == null) {
            json = "";
        }
        Pdlog.m3275i(TAG, "save " + json);
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        sharedPreferences2.edit().putString(KEY_RECORD, json).apply();
    }

    public final boolean moveDownloadsToPackage(String fileName, VoicePackCloud cloudPack) {
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        Intrinsics.checkParameterIsNotNull(cloudPack, "cloudPack");
        Pdlog.m3275i(TAG, "moveDownloadsToPackage " + fileName + ' ' + cloudPack.getName() + " version=" + cloudPack.getVersion_code());
        String valueOf = String.valueOf(cloudPack.getId());
        File file = new File(PackagesPath, valueOf);
        if (file.exists()) {
            deleteFile(file);
        }
        File file2 = new File(DownloadPath, fileName);
        if (file2.exists()) {
            new File(PackagesPath).mkdirs();
            File file3 = new File(PackagesPath, valueOf);
            if (!file2.renameTo(file3)) {
                Pdlog.m3277w(TAG, "move pack fail. " + file2.getAbsolutePath());
                return false;
            }
            if (record == null) {
                record = new Record();
            }
            Record record2 = record;
            if (record2 == null) {
                Intrinsics.throwNpe();
            }
            ArrayList<Download> arrayList = record2.downloads;
            Intrinsics.checkExpressionValueIsNotNull(arrayList, "record!!.downloads");
            boolean z = false;
            for (Download download : arrayList) {
                Long l = download.f4717id;
                long id = cloudPack.getId();
                if (l != null && l.longValue() == id) {
                    download.version_code = Long.valueOf(cloudPack.getVersion_code());
                    download.md5_mask = calMD5Mask(calMD5(file3));
                    z = true;
                }
            }
            if (!z) {
                Download download2 = new Download();
                download2.f4717id = Long.valueOf(cloudPack.getId());
                download2.name = cloudPack.getName();
                download2.version_code = Long.valueOf(cloudPack.getVersion_code());
                download2.md5_mask = calMD5Mask(calMD5(file3));
                Record record3 = record;
                if (record3 == null) {
                    Intrinsics.throwNpe();
                }
                record3.downloads.add(download2);
            }
        }
        Pdlog.m3275i(TAG, "copy pack done.");
        return true;
    }

    public final boolean checkMD5Same(String zipName, String md5) {
        Intrinsics.checkParameterIsNotNull(zipName, "zipName");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        File file = new File(DownloadPath, zipName);
        if (!file.exists()) {
            Pdlog.m3274e(TAG, zipName + " not exist");
            return false;
        }
        String calMD5 = calMD5(file);
        if (StringsKt.equals(calMD5, md5, true)) {
            return true;
        }
        Pdlog.m3274e(TAG, "md5 not equals. " + calMD5 + ' ' + md5);
        return false;
    }

    private final String calMD5(File file) {
        byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(FilesKt.readBytes(file));
        Intrinsics.checkExpressionValueIsNotNull(digest, "MessageDigest.getInstanc….digest(file.readBytes())");
        return bytesToHexString(digest);
    }

    private final String bytesToHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "sb.toString()");
        return stringBuffer2;
    }

    private final String calMD5Mask(String string) {
        if (string == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = string.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        String str = upperCase + "pudutech.com.voice";
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String str2 = StringsKt.reversed((CharSequence) str).toString() + "pudutech.com.voice";
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        Charset charset = Charsets.UTF_8;
        if (str2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = str2.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] mask = messageDigest.digest(bytes);
        Intrinsics.checkExpressionValueIsNotNull(mask, "mask");
        return bytesToHexString(mask);
    }

    public final String getCurrentVoiceName() {
        ArrayList<Download> arrayList;
        String selectedIDRecord = getSelectedIDRecord();
        String name = defaultPackage().getName();
        Record record2 = record;
        if (record2 != null && (arrayList = record2.downloads) != null) {
            for (Download download : arrayList) {
                if (Intrinsics.areEqual(String.valueOf(download.f4717id.longValue()), selectedIDRecord)) {
                    name = download.name;
                    Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                }
            }
        }
        return name;
    }
}
