package com.pudutech.bumblebee.presenter.robot_voices_task;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceDataSource;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayerHelper;
import com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoiceContract;
import com.pudutech.bumblebee.presenter.utils.FileUtil;
import com.pudutech.disinfect.baselib.network.response.TtsVoiceInfo;
import com.pudutech.resources.voice.VoiceItem;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: AppointVoicePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J \u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J(\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J \u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J)\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u001b\"\u00020\u0013H\u0016¢\u0006\u0002\u0010\u001cR\u0014\u0010\u0005\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoicePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoiceContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoiceContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "TEMP_VOICE_FILE_PATH", "helper", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper;", "mmr", "Landroid/media/MediaMetadataRetriever;", "loadCustom", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoice;", "Lkotlin/collections/ArrayList;", "item", "Lcom/pudutech/resources/voice/VoiceItem;", "loadDefault", "context", "Landroid/content/Context;", "loadMerchantTts", "syncList", "", "items", "", "(Landroid/content/Context;[Lcom/pudutech/resources/voice/VoiceItem;)V", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AppointVoicePresenter extends BaseOneViewPresenter<AppointVoiceContract.ViewInterface> implements AppointVoiceContract.PresenterInterface {
    private final String TEMP_VOICE_FILE_PATH = FileUtil.INSTANCE.getDECORATION_PATH() + File.separator + "tempvoicefile";
    private final VoicePlayerHelper helper = new VoicePlayerHelper();
    private final MediaMetadataRetriever mmr = new MediaMetadataRetriever();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return "AppointVoicePresenter";
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoiceContract.PresenterInterface
    public void syncList(final Context context, final VoiceItem... items) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(items, "items");
        final ArrayList arrayList = new ArrayList();
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoicePresenter$syncList$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                VoicePlayerHelper voicePlayerHelper;
                ArrayList loadCustom;
                ArrayList loadDefault;
                ArrayList loadMerchantTts;
                for (VoiceItem voiceItem : items) {
                    voicePlayerHelper = AppointVoicePresenter.this.helper;
                    VoicePlayerHelper.VoiceType checkCustom = voicePlayerHelper.checkCustom();
                    if (Intrinsics.areEqual(checkCustom, VoicePlayerHelper.VoiceType.MerchantTtsVoice.INSTANCE)) {
                        ArrayList arrayList2 = arrayList;
                        loadMerchantTts = AppointVoicePresenter.this.loadMerchantTts(voiceItem);
                        arrayList2.addAll(loadMerchantTts);
                    } else if (Intrinsics.areEqual(checkCustom, VoicePlayerHelper.VoiceType.DefaultVoice.INSTANCE)) {
                        ArrayList arrayList3 = arrayList;
                        loadDefault = AppointVoicePresenter.this.loadDefault(context, voiceItem);
                        arrayList3.addAll(loadDefault);
                    } else if (Intrinsics.areEqual(checkCustom, VoicePlayerHelper.VoiceType.CustomVoice.INSTANCE)) {
                        ArrayList arrayList4 = arrayList;
                        loadCustom = AppointVoicePresenter.this.loadCustom(voiceItem);
                        arrayList4.addAll(loadCustom);
                    }
                }
                AppointVoicePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoicePresenter$syncList$1.2
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        AppointVoiceContract.ViewInterface theView;
                        theView = AppointVoicePresenter.this.getTheView();
                        if (theView != null) {
                            theView.showList(arrayList);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<AppointVoice> loadCustom(VoiceItem item) {
        VoiceItem voiceItem = item;
        List<VoiceDataSource> customAll = this.helper.getCustomAll(item.name());
        int i = 1;
        char c = 0;
        Pdlog.m3273d(getTAG(), "loadCustom " + voiceItem + ' ' + customAll);
        ArrayList<AppointVoice> arrayList = new ArrayList<>();
        if (customAll != null) {
            try {
                for (VoiceDataSource voiceDataSource : customAll) {
                    Integer intOrNull = StringsKt.toIntOrNull((String) CollectionsKt.last(StringsKt.split$default((CharSequence) StringsKt.replace$default(voiceDataSource.getName(), ".mp3", "", false, 4, (Object) null), new String[]{"_"}, false, 0, 6, (Object) null)));
                    if (intOrNull != null) {
                        AppointVoice appointVoice = new AppointVoice();
                        appointVoice.setVoiceItem(voiceItem);
                        String str = this.TEMP_VOICE_FILE_PATH + File.separator + voiceDataSource.getName();
                        String str2 = File.separator;
                        Intrinsics.checkExpressionValueIsNotNull(str2, "File.separator");
                        String substringBeforeLast$default = StringsKt.substringBeforeLast$default(str, str2, (String) null, 2, (Object) null);
                        String[] strArr = new String[i];
                        String str3 = File.separator;
                        Intrinsics.checkExpressionValueIsNotNull(str3, "File.separator");
                        strArr[c] = str3;
                        String str4 = (String) CollectionsKt.last(StringsKt.split$default((CharSequence) str, strArr, false, 0, 6, (Object) null));
                        Pdlog.m3273d(getTAG(), "loadCustom path:" + str + ",folderPath:" + substringBeforeLast$default + ",fileName:" + str4);
                        FileUtil fileUtil = FileUtil.INSTANCE;
                        byte[] mBuffer = voiceDataSource.getMBuffer();
                        if (mBuffer == null) {
                            mBuffer = new byte[0];
                        }
                        fileUtil.saveByteToFile(substringBeforeLast$default, str4, mBuffer);
                        this.mmr.setDataSource(str);
                        String extractMetadata = this.mmr.extractMetadata(7);
                        if (extractMetadata != null) {
                            appointVoice.setInfo(extractMetadata);
                            Charset charset = Charsets.ISO_8859_1;
                            if (extractMetadata == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            byte[] bytes = extractMetadata.getBytes(charset);
                            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                            String str5 = new String(bytes, Charsets.ISO_8859_1);
                            Charset charset2 = Charsets.ISO_8859_1;
                            if (extractMetadata == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            byte[] bytes2 = extractMetadata.getBytes(charset2);
                            Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                            Charset forName = Charset.forName("gb2312");
                            Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
                            String str6 = new String(bytes2, forName);
                            if (Intrinsics.areEqual(str5, extractMetadata)) {
                                appointVoice.setInfo(str6);
                            }
                        }
                        appointVoice.setIndex(intOrNull.intValue());
                        arrayList.add(appointVoice);
                    }
                    voiceItem = item;
                    i = 1;
                    c = 0;
                }
            } catch (Exception e) {
                Pdlog.m3274e(getTAG(), "loadCustom " + Log.getStackTraceString(e));
            }
        }
        FileUtil.INSTANCE.deleteFolder(this.TEMP_VOICE_FILE_PATH);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<AppointVoice> loadMerchantTts(VoiceItem item) {
        List<TtsVoiceInfo> merchantTts = VoicePackageHelper.INSTANCE.getMerchantTts();
        ArrayList<TtsVoiceInfo> arrayList = null;
        if (merchantTts != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : merchantTts) {
                if (StringsKt.contains$default((CharSequence) ((TtsVoiceInfo) obj).getId(), (CharSequence) String.valueOf(item.name()), false, 2, (Object) null)) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        }
        ArrayList<AppointVoice> arrayList3 = new ArrayList<>();
        if (arrayList != null) {
            for (TtsVoiceInfo ttsVoiceInfo : arrayList) {
                Integer intOrNull = StringsKt.toIntOrNull((String) CollectionsKt.last(StringsKt.split$default((CharSequence) ttsVoiceInfo.getId(), new String[]{"_"}, false, 0, 6, (Object) null)));
                int intValue = intOrNull != null ? intOrNull.intValue() : 1;
                AppointVoice appointVoice = new AppointVoice();
                appointVoice.setVoiceItem(item);
                appointVoice.setInfo(ttsVoiceInfo.getText());
                appointVoice.setIndex(intValue);
                arrayList3.add(appointVoice);
            }
        }
        Pdlog.m3273d(getTAG(), "loadMerchantTts() list =" + arrayList3);
        return arrayList3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<AppointVoice> loadDefault(Context context, VoiceItem item) {
        List<String> defaultAll = this.helper.getDefaultAll(item.name());
        Pdlog.m3273d(getTAG(), "loadDefault " + item + ' ' + defaultAll);
        String defaultFilePath = VoicePlayerHelper.INSTANCE.getDefaultFilePath();
        ArrayList<AppointVoice> arrayList = new ArrayList<>();
        for (String str : defaultAll) {
            Integer intOrNull = StringsKt.toIntOrNull((String) CollectionsKt.last(StringsKt.split$default((CharSequence) StringsKt.replace$default(str, ".mp3", "", false, 4, (Object) null), new String[]{"_"}, false, 0, 6, (Object) null)));
            if (intOrNull != null) {
                AssetFileDescriptor fd = context.getAssets().openFd(defaultFilePath + File.separator + str);
                MediaMetadataRetriever mediaMetadataRetriever = this.mmr;
                Intrinsics.checkExpressionValueIsNotNull(fd, "fd");
                mediaMetadataRetriever.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                AppointVoice appointVoice = new AppointVoice();
                appointVoice.setVoiceItem(item);
                String extractMetadata = this.mmr.extractMetadata(7);
                if (extractMetadata != null) {
                    appointVoice.setInfo(extractMetadata);
                    Charset charset = Charsets.ISO_8859_1;
                    if (extractMetadata == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    byte[] bytes = extractMetadata.getBytes(charset);
                    Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                    String str2 = new String(bytes, Charsets.ISO_8859_1);
                    Charset charset2 = Charsets.ISO_8859_1;
                    if (extractMetadata == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    byte[] bytes2 = extractMetadata.getBytes(charset2);
                    Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                    Charset forName = Charset.forName("gb2312");
                    Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
                    String str3 = new String(bytes2, forName);
                    if (Intrinsics.areEqual(str2, extractMetadata)) {
                        appointVoice.setInfo(str3);
                    }
                }
                appointVoice.setIndex(intOrNull.intValue());
                arrayList.add(appointVoice);
            }
        }
        return arrayList;
    }
}
