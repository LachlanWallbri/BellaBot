package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", SpeechUtility.TAG_RESOURCE_RESULT, "Lcom/pudutech/mirsdk/aidl/serialize/SwitchMapResult;", "map_name", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$connectSDK$4 extends Lambda implements Function2<SwitchMapResult, String, Unit> {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$connectSDK$4(MirSDKActivity mirSDKActivity) {
        super(2);
        this.this$0 = mirSDKActivity;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(SwitchMapResult switchMapResult, String str) {
        invoke2(switchMapResult, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(SwitchMapResult result, String map_name) {
        String str;
        List list;
        List list2;
        List list3;
        MoveActionInterface moveActionInterface;
        Intrinsics.checkParameterIsNotNull(result, "result");
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "showing switch map result " + result.name() + " map name " + map_name);
        MirSDKActivity mirSDKActivity = this.this$0;
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        mirSDKActivity.destinations = (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) ? null : moveActionInterface.getDestinations();
        list = this.this$0.tableArray;
        list.clear();
        list2 = this.this$0.tableNameArray;
        list2.clear();
        list3 = this.this$0.starterGroupArray;
        list3.clear();
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48281(map_name, result, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48292(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$4$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$4$1 */
    /* loaded from: classes5.dex */
    public static final class C48281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $map_name;
        final /* synthetic */ SwitchMapResult $result;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5626p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C48281(String str, SwitchMapResult switchMapResult, Continuation continuation) {
            super(2, continuation);
            this.$map_name = str;
            this.$result = switchMapResult;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48281 c48281 = new C48281(this.$map_name, this.$result, completion);
            c48281.f5626p$ = (CoroutineScope) obj;
            return c48281;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String name;
            List list;
            List<Destination> list2;
            List list3;
            List list4;
            List list5;
            String str;
            List list6;
            List list7;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5626p$;
            TextView current_map = (TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.current_map);
            Intrinsics.checkExpressionValueIsNotNull(current_map, "current_map");
            current_map.setText(MirSDKActivity$connectSDK$4.this.this$0.getString(C4946R.string.current_map) + (char) 65306 + this.$map_name);
            TextView switch_result = (TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.switch_result);
            Intrinsics.checkExpressionValueIsNotNull(switch_result, "switch_result");
            if (this.$result == SwitchMapResult.Finish) {
                ((TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.switch_result)).setTextColor(InputDeviceCompat.SOURCE_ANY);
            } else {
                ((TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.switch_result)).setTextColor(SupportMenu.CATEGORY_MASK);
                name = this.$result.name();
            }
            switch_result.setText(name);
            list = MirSDKActivity$connectSDK$4.this.this$0.destinations;
            if (list != null) {
                list2 = MirSDKActivity$connectSDK$4.this.this$0.destinations;
                if (list2 == null) {
                    Intrinsics.throwNpe();
                }
                for (Destination destination : list2) {
                    if (Intrinsics.areEqual(destination.getMode(), "dining_outlet") || Intrinsics.areEqual(destination.getMode(), Constants.POINT_TYPE_DOOR)) {
                        list4 = MirSDKActivity$connectSDK$4.this.this$0.starterGroupArray;
                        if (!list4.contains(destination.getGroup())) {
                            list5 = MirSDKActivity$connectSDK$4.this.this$0.starterGroupArray;
                            list5.add(destination.getGroup());
                        }
                    } else {
                        list6 = MirSDKActivity$connectSDK$4.this.this$0.tableNameArray;
                        list6.add(destination.getName());
                        list7 = MirSDKActivity$connectSDK$4.this.this$0.tableArray;
                        list7.add("floor " + destination.getFloor() + ' ' + destination.getName() + '(' + destination.getGroup() + ")[" + destination.getMode() + ']');
                    }
                    str = MirSDKActivity$connectSDK$4.this.this$0.TAG;
                    Pdlog.m3275i(str, "find table " + destination.getName());
                }
                TextView textViewMapData = (TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.textViewMapData);
                Intrinsics.checkExpressionValueIsNotNull(textViewMapData, "textViewMapData");
                StringBuilder sb = new StringBuilder();
                sb.append("destination size:");
                list3 = MirSDKActivity$connectSDK$4.this.this$0.destinations;
                if (list3 == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(list3.size());
                textViewMapData.setText(sb.toString());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$4$2", m3970f = "MirSDKActivity.kt", m3971i = {0}, m3972l = {794}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$4$2 */
    /* loaded from: classes5.dex */
    public static final class C48292 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5627p$;

        C48292(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48292 c48292 = new C48292(completion);
            c48292.f5627p$ = (CoroutineScope) obj;
            return c48292;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48292) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5627p$;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            do {
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface == null || !sDKInterface.isLocalizationFinishInitialization()) {
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
                        this.L$0 = coroutineScope;
                        this.label = 1;
                    } else {
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass2(null), 2, null);
                    }
                }
                return Unit.INSTANCE;
            } while (DelayKt.delay(500L, this) != coroutine_suspended);
            return coroutine_suspended;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MirSDKActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$4$2$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$4$2$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            /* renamed from: p$ */
            private CoroutineScope f5628p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f5628p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f5628p$;
                TextView tx_state = (TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.tx_state);
                Intrinsics.checkExpressionValueIsNotNull(tx_state, "tx_state");
                tx_state.setText(MoveError.LEVEL_ERROR);
                TextView tx_desc = (TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.tx_desc);
                Intrinsics.checkExpressionValueIsNotNull(tx_desc, "tx_desc");
                tx_desc.setText("Locate Not Finish Init");
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MirSDKActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$4$2$2", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$4$2$2, reason: invalid class name */
        /* loaded from: classes5.dex */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            /* renamed from: p$ */
            private CoroutineScope f5629p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(completion);
                anonymousClass2.f5629p$ = (CoroutineScope) obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f5629p$;
                TextView tx_state = (TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.tx_state);
                Intrinsics.checkExpressionValueIsNotNull(tx_state, "tx_state");
                tx_state.setText("Finish");
                TextView tx_desc = (TextView) MirSDKActivity$connectSDK$4.this.this$0._$_findCachedViewById(C4946R.id.tx_desc);
                Intrinsics.checkExpressionValueIsNotNull(tx_desc, "tx_desc");
                tx_desc.setText("Locate Finish Init");
                return Unit.INSTANCE;
            }
        }
    }
}
