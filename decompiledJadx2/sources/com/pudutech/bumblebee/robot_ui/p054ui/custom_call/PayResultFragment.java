package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallQrcodeBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.pudutech.importmusic.QRCodeUtils;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PayResultFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/PayResultFragment;", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BaseCustomCallFragment;", "()V", "customCallQrcode", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallQrcodeBean;", "completeCustomCall", "", "generateQRCodeBitmap", "Landroid/graphics/Bitmap;", "code", "", "getLayoutId", "", "initview", "view", "Landroid/view/View;", "setData", "updateCustomCallContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PayResultFragment extends BaseCustomCallFragment {
    private HashMap _$_findViewCache;
    private CustomCallQrcodeBean customCallQrcode;

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment, com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment, com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment, com.pudutech.bumblebee.robot_ui.base.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public int getLayoutId() {
        return C4188R.layout.fragment_pay_result;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void initview(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initview(view);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment
    public void updateCustomCallContent(ICustomCallBean content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.customCallQrcode = (CustomCallQrcodeBean) content;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment
    public void completeCustomCall() {
        Context it = getContext();
        if (it != null) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            PaySuccessDialog paySuccessDialog = new PaySuccessDialog(it);
            paySuccessDialog.setOnCloseListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.PayResultFragment$completeCustomCall$$inlined$let$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
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
                    Function2<CustomCallState, CustomCallOperationType, Unit> onActionState = PayResultFragment.this.getOnActionState();
                    if (onActionState != null) {
                        onActionState.invoke(CustomCallState.Complete, CustomCallOperationType.user);
                    }
                }
            });
            paySuccessDialog.show();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void setData() {
        super.setData();
        CustomCallQrcodeBean customCallQrcodeBean = this.customCallQrcode;
        if (customCallQrcodeBean != null) {
            TextView textView = (TextView) _$_findCachedViewById(C4188R.id.pay_table_number);
            if (textView != null) {
                textView.setText(customCallQrcodeBean.getDestination());
            }
            UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.pay_table_number), 400, customCallQrcodeBean.getDestination());
            String qrcode = customCallQrcodeBean.getQrcode();
            if (qrcode != null) {
                ((ImageView) _$_findCachedViewById(C4188R.id.pay_qr_code)).setImageBitmap(generateQRCodeBitmap(qrcode));
            }
            TextView pay_content = (TextView) _$_findCachedViewById(C4188R.id.pay_content);
            Intrinsics.checkExpressionValueIsNotNull(pay_content, "pay_content");
            pay_content.setText(Html.fromHtml(customCallQrcodeBean.getContent()));
        }
    }

    private final Bitmap generateQRCodeBitmap(String code) {
        return QRCodeUtils.createQRCodeBitmap(code, 360, 360);
    }
}
