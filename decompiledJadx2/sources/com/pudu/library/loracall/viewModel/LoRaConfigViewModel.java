package com.pudu.library.loracall.viewModel;

import androidx.lifecycle.MutableLiveData;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.base.BaseViewModel;
import com.pudu.library.loracall.bean.LoRaConfigParam;
import com.pudu.library.loracall.bean.LoRaVersionParam;
import com.pudu.loracall.library.C3965R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoRaConfigViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0015J\u0006\u0010\r\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\u001a\u0010\u0017\u001a\u00020\u00152\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00110\u0010R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R9\u0010\u000e\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00110\u00100\u000fj\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00110\u0010`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, m3961d2 = {"Lcom/pudu/library/loracall/viewModel/LoRaConfigViewModel;", "Lcom/pudu/library/loracall/base/BaseViewModel;", "()V", "loRaConfig", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudu/library/loracall/bean/LoRaConfigParam;", "getLoRaConfig", "()Landroidx/lifecycle/MutableLiveData;", "loRaMac", "", "getLoRaMac", "loRaVersion", "Lcom/pudu/library/loracall/bean/LoRaVersionParam;", "getLoRaVersion", "popList", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "", "Lkotlin/collections/ArrayList;", "getPopList", "()Ljava/util/ArrayList;", "", "saveLoRaConfig", "setExpandData", "item", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaConfigViewModel extends BaseViewModel {
    private final ArrayList<Pair<String, Byte>> popList = CollectionsKt.arrayListOf(TuplesKt.m3968to("LORA_SF5", Byte.valueOf((byte) 80)), TuplesKt.m3968to("LORA_SF6", Byte.valueOf((byte) 96)), TuplesKt.m3968to("LORA_SF7", Byte.valueOf((byte) 112)), TuplesKt.m3968to("LORA_SF8", Byte.valueOf((byte) 128)), TuplesKt.m3968to("LORA_SF9", Byte.valueOf((byte) 144)), TuplesKt.m3968to("LORA_SF10", Byte.valueOf((byte) 160)), TuplesKt.m3968to("LORA_SF11", Byte.valueOf((byte) 176)), TuplesKt.m3968to("LORA_SF12", Byte.valueOf((byte) 192)));
    private final MutableLiveData<LoRaVersionParam> loRaVersion = new MutableLiveData<>();
    private final MutableLiveData<LoRaConfigParam> loRaConfig = new MutableLiveData<>();
    private final MutableLiveData<String> loRaMac = new MutableLiveData<>();

    public final ArrayList<Pair<String, Byte>> getPopList() {
        return this.popList;
    }

    public final MutableLiveData<LoRaVersionParam> getLoRaVersion() {
        return this.loRaVersion;
    }

    public final MutableLiveData<LoRaConfigParam> getLoRaConfig() {
        return this.loRaConfig;
    }

    public final MutableLiveData<String> getLoRaMac() {
        return this.loRaMac;
    }

    /* renamed from: getLoRaVersion, reason: collision with other method in class */
    public final void m4291getLoRaVersion() {
        LoRaClient.INSTANCE.getInstance().getLoRaVersion(new Function1<LoRaVersionParam, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoRaConfigViewModel$getLoRaVersion$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoRaVersionParam loRaVersionParam) {
                invoke2(loRaVersionParam);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final LoRaVersionParam it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                KotlinUtilsKt.log$default(LoRaConfigViewModel.this, null, new Function0<String>() { // from class: com.pudu.library.loracall.viewModel.LoRaConfigViewModel$getLoRaVersion$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "版本----------" + LoRaVersionParam.this;
                    }
                }, 1, null);
                LoRaConfigViewModel.this.getLoRaVersion().postValue(it);
            }
        });
        LoRaClient.INSTANCE.getInstance().getLoRaMac(new Function1<String, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoRaConfigViewModel$getLoRaVersion$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                LoRaConfigViewModel.this.getLoRaMac().postValue(it);
            }
        });
    }

    /* renamed from: getLoRaConfig, reason: collision with other method in class */
    public final void m4290getLoRaConfig() {
        LoRaClient.INSTANCE.getInstance().getLoRaConfig(new Function1<LoRaConfigParam, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoRaConfigViewModel$getLoRaConfig$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoRaConfigParam loRaConfigParam) {
                invoke2(loRaConfigParam);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final LoRaConfigParam it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                KotlinUtilsKt.log$default(LoRaConfigViewModel.this, null, new Function0<String>() { // from class: com.pudu.library.loracall.viewModel.LoRaConfigViewModel$getLoRaConfig$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "配置：" + LoRaConfigParam.this;
                    }
                }, 1, null);
                LoRaConfigViewModel.this.getLoRaConfig().postValue(it);
            }
        });
    }

    public final void setExpandData(Pair<String, Byte> item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        LoRaConfigParam value = this.loRaConfig.getValue();
        if (value != null) {
            value.setExpand(item.getFirst());
        }
    }

    public final void saveLoRaConfig() {
        final LoRaConfigParam value = this.loRaConfig.getValue();
        if (value != null) {
            Intrinsics.checkExpressionValueIsNotNull(value, "loRaConfig.value ?: return");
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.viewModel.LoRaConfigViewModel$saveLoRaConfig$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "saveLoRaConfig" + LoRaConfigParam.this;
                }
            }, 1, null);
            byte[] codingData = value.codingData();
            if (codingData != null) {
                LoRaClient.INSTANCE.getInstance().setLoRaConfig(codingData, new Function1<Boolean, Unit>() { // from class: com.pudu.library.loracall.viewModel.LoRaConfigViewModel$saveLoRaConfig$$inlined$also$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        if (z) {
                            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(LoRaConfigViewModel.this, C3965R.string.lora_set_success));
                        } else {
                            KotlinUtilsKt.showToast(KotlinUtilsKt.getString(LoRaConfigViewModel.this, C3965R.string.lora_set_fail));
                        }
                    }
                });
            }
        }
    }
}
