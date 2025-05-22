package com.pudutech.bumblebee.robot_ui.p054ui;

import android.text.TextUtils;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LatticeInputDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: BirthdayModeMusicSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/BirthdayModeMusicSettingActivity$initListener$2", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BirthdayModeMusicSettingActivity$initListener$2 extends OnLazyVoiceClickListener {
    final /* synthetic */ BirthdayModeMusicSettingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BirthdayModeMusicSettingActivity$initListener$2(BirthdayModeMusicSettingActivity birthdayModeMusicSettingActivity) {
        super(null, 0, 3, null);
        this.this$0 = birthdayModeMusicSettingActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "tv_edit onSingleClick");
        LatticeInputDialog latticeInputDialog = new LatticeInputDialog();
        latticeInputDialog.setContent(Constans.INSTANCE.getBirthdayText());
        latticeInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.BirthdayModeMusicSettingActivity$initListener$2$onSingleClick$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Constans.INSTANCE.setBirthdayText(it);
                String str2 = it;
                if (TextUtils.isEmpty(str2)) {
                    TextView tv_birthday_text = (TextView) BirthdayModeMusicSettingActivity$initListener$2.this.this$0._$_findCachedViewById(C4188R.id.tv_birthday_text);
                    Intrinsics.checkExpressionValueIsNotNull(tv_birthday_text, "tv_birthday_text");
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = BirthdayModeMusicSettingActivity$initListener$2.this.this$0.getString(C4188R.string.pdStr9_2, new Object[]{"X"});
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr9_2, \"X\")");
                    Object[] objArr = new Object[0];
                    String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                    tv_birthday_text.setText(format);
                    ((TextView) BirthdayModeMusicSettingActivity$initListener$2.this.this$0._$_findCachedViewById(C4188R.id.tv_birthday_text)).setTextColor(BirthdayModeMusicSettingActivity$initListener$2.this.this$0.getColor(C4188R.color.black));
                    return;
                }
                TextView tv_birthday_text2 = (TextView) BirthdayModeMusicSettingActivity$initListener$2.this.this$0._$_findCachedViewById(C4188R.id.tv_birthday_text);
                Intrinsics.checkExpressionValueIsNotNull(tv_birthday_text2, "tv_birthday_text");
                tv_birthday_text2.setText(str2);
                ((TextView) BirthdayModeMusicSettingActivity$initListener$2.this.this$0._$_findCachedViewById(C4188R.id.tv_birthday_text)).setTextColor(BirthdayModeMusicSettingActivity$initListener$2.this.this$0.getColor(C4188R.color.switch_text_on));
            }
        });
        latticeInputDialog.show(this.this$0.getSupportFragmentManager(), "LatticeInputDialog");
    }
}
