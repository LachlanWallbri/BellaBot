package com.pudutech.bumblebee.robot_ui.viewmodel;

import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.callback.livedata.event.EventLiveData;
import com.pudutech.disinfect.baselib.network.req.FileUpdateReq;
import com.pudutech.disinfect.baselib.network.response.FileUpdateResponse;
import com.pudutech.disinfect.baselib.state.AppException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bR!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/VoiceViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "mFileUpdateResponse", "Lcom/pudutech/disinfect/baselib/callback/livedata/event/EventLiveData;", "Lcom/pudutech/disinfect/baselib/network/response/FileUpdateResponse;", "getMFileUpdateResponse", "()Lcom/pudutech/disinfect/baselib/callback/livedata/event/EventLiveData;", "mFileUpdateResponse$delegate", "Lkotlin/Lazy;", "getFileUpdate", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceViewModel extends BaseViewModel {

    /* renamed from: mFileUpdateResponse$delegate, reason: from kotlin metadata */
    private final Lazy mFileUpdateResponse = LazyKt.lazy(new Function0<EventLiveData<FileUpdateResponse>>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.VoiceViewModel$mFileUpdateResponse$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final EventLiveData<FileUpdateResponse> invoke() {
            return new EventLiveData<>();
        }
    });

    public final EventLiveData<FileUpdateResponse> getMFileUpdateResponse() {
        return (EventLiveData) this.mFileUpdateResponse.getValue();
    }

    public final void getFileUpdate() {
        FileUpdateReq fileUpdateReq = new FileUpdateReq();
        fileUpdateReq.setName("input_method_go");
        fileUpdateReq.setVersionCode(0);
        request(new VoiceViewModel$getFileUpdate$1(fileUpdateReq, null), new Function1<FileUpdateResponse, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.VoiceViewModel$getFileUpdate$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FileUpdateResponse fileUpdateResponse) {
                invoke2(fileUpdateResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FileUpdateResponse it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                VoiceViewModel.this.getMFileUpdateResponse().postValue(it);
            }
        }, new Function1<AppException, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.VoiceViewModel$getFileUpdate$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                invoke2(appException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AppException it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                VoiceViewModel.this.getMFileUpdateResponse().postValue(null);
            }
        });
    }
}
