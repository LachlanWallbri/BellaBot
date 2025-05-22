package com.pudutech.bumblebee.robot_ui.module.escape;

import com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EscapeInteractor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeInteractor;", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IInteractor;", "service", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeService;", "(Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeService;)V", "delegate", "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapePresenter;", "getDelegate", "()Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapePresenter;", "setDelegate", "(Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapePresenter;)V", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "getService", "()Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeService;", "checkEscape", "", "checkMapHasNew", "checkNetwork", "uploadMap", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EscapeInteractor implements EscapeModule.IInteractor {
    public EscapePresenter delegate;
    private final CompositeDisposable disposables;
    private final EscapeService service;

    public EscapeInteractor(EscapeService service) {
        Intrinsics.checkParameterIsNotNull(service, "service");
        this.service = service;
        this.disposables = new CompositeDisposable();
        this.disposables.add(this.service.getNewMapObservable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.m3958io()).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeInteractor.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Boolean hasNew) {
                EscapePresenter delegate = EscapeInteractor.this.getDelegate();
                Intrinsics.checkExpressionValueIsNotNull(hasNew, "hasNew");
                delegate.didCheckMapHasNew(hasNew.booleanValue());
            }
        }));
        this.disposables.add(this.service.getEscapeObservable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.m3958io()).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeInteractor.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Boolean hasEscape) {
                EscapePresenter delegate = EscapeInteractor.this.getDelegate();
                Intrinsics.checkExpressionValueIsNotNull(hasEscape, "hasEscape");
                delegate.didCheckEscape(hasEscape.booleanValue());
            }
        }));
        this.disposables.add(this.service.getUploadObservable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.m3958io()).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeInteractor.5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Boolean needUpload) {
                EscapePresenter delegate = EscapeInteractor.this.getDelegate();
                Intrinsics.checkExpressionValueIsNotNull(needUpload, "needUpload");
                delegate.didCheckMapVersion(needUpload.booleanValue());
            }
        }));
        this.disposables.add(this.service.getNetworkObservable().subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.m3958io()).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeInteractor.7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Boolean available) {
                EscapePresenter delegate = EscapeInteractor.this.getDelegate();
                Intrinsics.checkExpressionValueIsNotNull(available, "available");
                delegate.didCheckNetwork(available.booleanValue());
            }
        }));
    }

    public final EscapeService getService() {
        return this.service;
    }

    public final EscapePresenter getDelegate() {
        EscapePresenter escapePresenter = this.delegate;
        if (escapePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delegate");
        }
        return escapePresenter;
    }

    public final void setDelegate(EscapePresenter escapePresenter) {
        Intrinsics.checkParameterIsNotNull(escapePresenter, "<set-?>");
        this.delegate = escapePresenter;
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IInteractor
    public void checkMapHasNew() {
        this.service.checkMapIsNew();
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IInteractor
    public void checkEscape() {
        this.service.checkEscape();
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IInteractor
    public void checkNetwork() {
        this.service.checkNetwork();
    }

    @Override // com.pudutech.bumblebee.robot_ui.module.escape.EscapeModule.IInteractor
    public void uploadMap() {
        this.service.uploadMap();
    }
}
