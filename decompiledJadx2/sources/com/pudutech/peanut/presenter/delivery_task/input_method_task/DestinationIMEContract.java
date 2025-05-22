package com.pudutech.peanut.presenter.delivery_task.input_method_task;

import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.peanut.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: DestinationIMEContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationIMEContract;", "", "PresenterInterface", "ViewInterface", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface DestinationIMEContract {

    /* compiled from: DestinationIMEContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationIMEContract$PresenterInterface;", "Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "backspace", "", "clear", "input", "", "string", "", "loadMap", "refreshAfterBackspace", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void backspace();

        void clear();

        boolean input(String string);

        void loadMap();

        void refreshAfterBackspace();
    }

    /* compiled from: DestinationIMEContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH&J \u0010\u000b\u001a\u00020\u00032\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\f0\bj\b\u0012\u0004\u0012\u00020\f`\nH&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationIMEContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseViewInterface;", "showInputted", "", "string", "", "showResults", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationModel;", "Lkotlin/collections/ArrayList;", "showWordModels", "Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/WordModel;", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showInputted(String string);

        void showResults(ArrayList<DestinationModel> list);

        void showWordModels(ArrayList<WordModel> list);
    }
}
