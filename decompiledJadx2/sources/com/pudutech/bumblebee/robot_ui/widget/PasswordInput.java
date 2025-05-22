package com.pudutech.bumblebee.robot_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.pudutech.bumblebee.robot_ui.C4188R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt;

/* compiled from: PasswordInput.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/PasswordInput;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "editText", "Landroid/widget/EditText;", "getEditText", "()Landroid/widget/EditText;", "root", "Landroid/widget/LinearLayout;", "getRoot", "()Landroid/widget/LinearLayout;", "toggle", "Landroid/widget/CheckBox;", "getToggle", "()Landroid/widget/CheckBox;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PasswordInput extends FrameLayout {
    private HashMap _$_findViewCache;
    private final EditText editText;
    private final LinearLayout root;
    private final CheckBox toggle;

    public PasswordInput(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ PasswordInput(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (AttributeSet) null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PasswordInput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        FrameLayout.inflate(context, C4188R.layout.layout_password_input, this);
        View findViewById = findViewById(C4188R.id.editText);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.editText)");
        this.editText = (EditText) findViewById;
        View findViewById2 = findViewById(C4188R.id.show_content);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.show_content)");
        this.toggle = (CheckBox) findViewById2;
        View findViewById3 = findViewById(C4188R.id.root);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById(R.id.root)");
        this.root = (LinearLayout) findViewById3;
        Sdk27CoroutinesListenersWithCoroutinesKt.onCheckedChange$default(this.toggle, (CoroutineContext) null, new C44221(null), 1, (Object) null);
    }

    public final EditText getEditText() {
        return this.editText;
    }

    public final CheckBox getToggle() {
        return this.toggle;
    }

    public final LinearLayout getRoot() {
        return this.root;
    }

    /* compiled from: PasswordInput.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.widget.PasswordInput$1", m3970f = "PasswordInput.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.widget.PasswordInput$1 */
    /* loaded from: classes4.dex */
    static final class C44221 extends SuspendLambda implements Function4<CoroutineScope, CompoundButton, Boolean, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4994p$;
        private CompoundButton p$0;
        private boolean p$1;

        C44221(Continuation continuation) {
            super(4, continuation);
        }

        public final Continuation<Unit> create(CoroutineScope create, CompoundButton compoundButton, boolean z, Continuation<? super Unit> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            C44221 c44221 = new C44221(continuation);
            c44221.f4994p$ = create;
            c44221.p$0 = compoundButton;
            c44221.p$1 = z;
            return c44221;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(CoroutineScope coroutineScope, CompoundButton compoundButton, Boolean bool, Continuation<? super Unit> continuation) {
            return ((C44221) create(coroutineScope, compoundButton, bool.booleanValue(), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4994p$;
            CompoundButton compoundButton = this.p$0;
            if (this.p$1) {
                PasswordInput.this.getEditText().setInputType(145);
            } else {
                PasswordInput.this.getEditText().setInputType(129);
            }
            PasswordInput.this.getEditText().setSelection(PasswordInput.this.getEditText().getText().length());
            return Unit.INSTANCE;
        }
    }
}
