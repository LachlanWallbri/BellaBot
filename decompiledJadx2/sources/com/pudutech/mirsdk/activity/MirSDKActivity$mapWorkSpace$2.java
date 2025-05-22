package com.pudutech.mirsdk.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$2;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.MapListConfig;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$mapWorkSpace$2 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$mapWorkSpace$2(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$2$1 */
    /* loaded from: classes5.dex */
    public static final class C48411 extends Lambda implements Function0<Unit> {
        C48411() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            MapPackageConfig pdmapNameList;
            ArrayList arrayList = new ArrayList();
            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
            if ((sDKInterface != null ? sDKInterface.getPdmapNameList() : null) != null) {
                SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                List<MapListConfig> list = (sDKInterface2 == null || (pdmapNameList = sDKInterface2.getPdmapNameList()) == null) ? null : pdmapNameList.getList();
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                Iterator<MapListConfig> it = list.iterator();
                while (it.hasNext()) {
                    MapListConfig next = it.next();
                    Map<String, String> list2 = next != null ? next.getList() : null;
                    if (list2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Iterator<Map.Entry<String, String>> it2 = list2.entrySet().iterator();
                    while (it2.hasNext()) {
                        arrayList.add(it2.next().getKey());
                    }
                }
            }
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(arrayList, null), 2, null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: MirSDKActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$2$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$2$1$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ List $maps;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f5652p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(List list, Continuation continuation) {
                super(2, continuation);
                this.$maps = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$maps, completion);
                anonymousClass1.f5652p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5652p$;
                    if (this.$maps == null) {
                        Toast.makeText(MirSDKActivity$mapWorkSpace$2.this.this$0, "no map", 0).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MirSDKActivity$mapWorkSpace$2.this.this$0);
                        Object[] array = this.$maps.toArray(new String[0]);
                        if (array != null) {
                            AlertDialog create = builder.setSingleChoiceItems((CharSequence[]) array, -1, new DialogInterface.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$2$1$1$dialog$1

                                /* JADX WARN: Classes with same name are omitted:
                                  classes4.dex
                                 */
                                /* compiled from: MirSDKActivity.kt */
                                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                                @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$2$1$1$dialog$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                                /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$2$1$1$dialog$1$1 */
                                /* loaded from: classes5.dex */
                                static final class C48421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                                    /* renamed from: $i */
                                    final /* synthetic */ int f5653$i;
                                    int label;

                                    /* renamed from: p$ */
                                    private CoroutineScope f5654p$;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C48421(int i, Continuation continuation) {
                                        super(2, continuation);
                                        this.f5653$i = i;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                                        C48421 c48421 = new C48421(this.f5653$i, completion);
                                        c48421.f5654p$ = (CoroutineScope) obj;
                                        return c48421;
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C48421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        String findFloorId;
                                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        if (this.label != 0) {
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        ResultKt.throwOnFailure(obj);
                                        CoroutineScope coroutineScope = this.f5654p$;
                                        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                                        if (sDKInterface != null) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("{\"floor\":\"");
                                            findFloorId = MirSDKActivity$mapWorkSpace$2.this.this$0.findFloorId((String) MirSDKActivity$mapWorkSpace$2.C48411.AnonymousClass1.this.$maps.get(this.f5653$i));
                                            sb.append(findFloorId);
                                            sb.append("\",\"name\":\"");
                                            sb.append((String) MirSDKActivity$mapWorkSpace$2.C48411.AnonymousClass1.this.$maps.get(this.f5653$i));
                                            sb.append("\"}");
                                            sDKInterface.switchUsingPdmap(sb.toString());
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }

                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48421(i, null), 3, null);
                                }
                            }).create();
                            Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(this…               }.create()");
                            create.show();
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.this$0.checkAuth("swmap", new C48411());
    }
}
