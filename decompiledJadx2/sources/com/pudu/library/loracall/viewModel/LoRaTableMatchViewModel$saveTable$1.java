package com.pudu.library.loracall.viewModel;

import androidx.room.RoomDatabaseKt;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.dao.AppDatabase;
import com.pudu.library.loracall.dao.TableBindDao;
import com.pudu.library.loracall.dao.TableMatchBean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoRaTableMatchViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$saveTable$1", m3970f = "LoRaTableMatchViewModel.kt", m3971i = {0}, m3972l = {130}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class LoRaTableMatchViewModel$saveTable$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $dev;
    final /* synthetic */ TableMatchBean $table;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4393p$;
    final /* synthetic */ LoRaTableMatchViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoRaTableMatchViewModel$saveTable$1(LoRaTableMatchViewModel loRaTableMatchViewModel, TableMatchBean tableMatchBean, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = loRaTableMatchViewModel;
        this.$table = tableMatchBean;
        this.$dev = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoRaTableMatchViewModel$saveTable$1 loRaTableMatchViewModel$saveTable$1 = new LoRaTableMatchViewModel$saveTable$1(this.this$0, this.$table, this.$dev, completion);
        loRaTableMatchViewModel$saveTable$1.f4393p$ = (CoroutineScope) obj;
        return loRaTableMatchViewModel$saveTable$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoRaTableMatchViewModel$saveTable$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4393p$;
            KotlinUtilsKt.log$default(coroutineScope, null, new Function0<String>() { // from class: com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$saveTable$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "保存桌号: table:" + LoRaTableMatchViewModel$saveTable$1.this.$table.getTableName() + " dev:" + LoRaTableMatchViewModel$saveTable$1.this.$dev;
                }
            }, 1, null);
            this.this$0.changeAllData(this.$dev);
            this.$table.setDevAdder(this.$dev);
            AppDatabase companion = AppDatabase.INSTANCE.getInstance();
            C39612 c39612 = new C39612(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (RoomDatabaseKt.withTransaction(companion, c39612, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LoRaTableMatchViewModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, m3961d2 = {"<anonymous>", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$saveTable$1$2", m3970f = "LoRaTableMatchViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel$saveTable$1$2 */
    /* loaded from: classes4.dex */
    public static final class C39612 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        C39612(Continuation continuation) {
            super(1, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            return new C39612(completion);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C39612) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            TableBindDao dao;
            TableBindDao dao2;
            TableBindDao dao3;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            dao = LoRaTableMatchViewModel$saveTable$1.this.this$0.getDao();
            for (TableMatchBean tableMatchBean : dao.getTableNameList(LoRaTableMatchViewModel$saveTable$1.this.$table.getTableName())) {
                dao3 = LoRaTableMatchViewModel$saveTable$1.this.this$0.getDao();
                dao3.delete(tableMatchBean);
            }
            dao2 = LoRaTableMatchViewModel$saveTable$1.this.this$0.getDao();
            dao2.insert(LoRaTableMatchViewModel$saveTable$1.this.$table);
            return Unit.INSTANCE;
        }
    }
}
