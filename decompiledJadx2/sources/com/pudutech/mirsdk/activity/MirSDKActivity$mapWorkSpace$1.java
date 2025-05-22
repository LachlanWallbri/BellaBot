package com.pudutech.mirsdk.activity;

import android.view.View;
import android.widget.Toast;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.MapListConfig;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$mapWorkSpace$1 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$mapWorkSpace$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.this$0.checkAuth("rswmap", new Function0<Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$1.1
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
                if (arrayList.size() > 0) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new AnonymousClass2((String) arrayList.get(Random.INSTANCE.nextInt(arrayList.size())), null), 3, null);
                }
            }

            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$1$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$1$1$1, reason: invalid class name */
            /* loaded from: classes5.dex */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5650p$;

                AnonymousClass1(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                    anonymousClass1.f5650p$ = (CoroutineScope) obj;
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
                    CoroutineScope coroutineScope = this.f5650p$;
                    Toast.makeText(MirSDKActivity$mapWorkSpace$1.this.this$0, "no map", 0).show();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Classes with same name are omitted:
              classes4.dex
             */
            /* compiled from: MirSDKActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$1$1$2", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$mapWorkSpace$1$1$2, reason: invalid class name */
            /* loaded from: classes5.dex */
            public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $goal_map;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5651p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass2(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$goal_map = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$goal_map, completion);
                    anonymousClass2.f5651p$ = (CoroutineScope) obj;
                    return anonymousClass2;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String findFloorId;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5651p$;
                    SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("{\"floor\":\"");
                        findFloorId = MirSDKActivity$mapWorkSpace$1.this.this$0.findFloorId(this.$goal_map);
                        sb.append(findFloorId);
                        sb.append("\",\"name\":\"");
                        sb.append(this.$goal_map);
                        sb.append("\"}");
                        sDKInterface.switchUsingPdmap(sb.toString());
                    }
                    return Unit.INSTANCE;
                }
            }
        });
    }
}
