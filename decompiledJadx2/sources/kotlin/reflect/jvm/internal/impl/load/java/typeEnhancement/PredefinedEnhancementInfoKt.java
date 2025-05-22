package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementBuilder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: predefinedEnhancementInfo.kt */
/* loaded from: classes2.dex */
public final class PredefinedEnhancementInfoKt {
    private static final Map<String, PredefinedFunctionEnhancementInfo> PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;
    private static final JavaTypeQualifiers NULLABLE = new JavaTypeQualifiers(NullabilityQualifier.NULLABLE, null, false, false, 8, null);
    private static final JavaTypeQualifiers NOT_PLATFORM = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, false, false, 8, null);
    private static final JavaTypeQualifiers NOT_NULLABLE = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, true, false, 8, null);

    static {
        final SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        final String javaLang = signatureBuildingComponents.javaLang("Object");
        final String javaFunction = signatureBuildingComponents.javaFunction("Predicate");
        final String javaFunction2 = signatureBuildingComponents.javaFunction("Function");
        final String javaFunction3 = signatureBuildingComponents.javaFunction("Consumer");
        final String javaFunction4 = signatureBuildingComponents.javaFunction("BiFunction");
        final String javaFunction5 = signatureBuildingComponents.javaFunction("BiConsumer");
        final String javaFunction6 = signatureBuildingComponents.javaFunction("UnaryOperator");
        final String javaUtil = signatureBuildingComponents.javaUtil("stream/Stream");
        final String javaUtil2 = signatureBuildingComponents.javaUtil("Optional");
        SignatureEnhancementBuilder signatureEnhancementBuilder = new SignatureEnhancementBuilder();
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("Iterator")).function("forEachRemaining", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaFunction3;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers, javaTypeQualifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaLang("Iterable")).function("spliterator", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String javaUtil3 = SignatureBuildingComponents.this.javaUtil("Spliterator");
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.returns(javaUtil3, javaTypeQualifiers, javaTypeQualifiers2);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder classEnhancementBuilder = new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("Collection"));
        classEnhancementBuilder.function("removeIf", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaFunction;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers, javaTypeQualifiers2);
                receiver.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        classEnhancementBuilder.function("stream", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaUtil;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.returns(str, javaTypeQualifiers, javaTypeQualifiers2);
            }
        });
        classEnhancementBuilder.function("parallelStream", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaUtil;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.returns(str, javaTypeQualifiers, javaTypeQualifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("List")).function("replaceAll", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$6
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaFunction6;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers, javaTypeQualifiers2);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder classEnhancementBuilder2 = new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("Map"));
        classEnhancementBuilder2.function("forEach", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$7
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaFunction5;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers, javaTypeQualifiers2, javaTypeQualifiers3);
            }
        });
        classEnhancementBuilder2.function("putIfAbsent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$8
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaLang;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str2, javaTypeQualifiers2);
                String str3 = javaLang;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.returns(str3, javaTypeQualifiers3);
            }
        });
        classEnhancementBuilder2.function("replace", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$9
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaLang;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str2, javaTypeQualifiers2);
                String str3 = javaLang;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.returns(str3, javaTypeQualifiers3);
            }
        });
        classEnhancementBuilder2.function("replace", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$10
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaLang;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str2, javaTypeQualifiers2);
                String str3 = javaLang;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str3, javaTypeQualifiers3);
                receiver.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        classEnhancementBuilder2.function("replaceAll", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$11
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                JavaTypeQualifiers javaTypeQualifiers4;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaFunction4;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers4 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers, javaTypeQualifiers2, javaTypeQualifiers3, javaTypeQualifiers4);
            }
        });
        classEnhancementBuilder2.function("compute", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$12
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                JavaTypeQualifiers javaTypeQualifiers4;
                JavaTypeQualifiers javaTypeQualifiers5;
                JavaTypeQualifiers javaTypeQualifiers6;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaFunction4;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers4 = PredefinedEnhancementInfoKt.NULLABLE;
                javaTypeQualifiers5 = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.parameter(str2, javaTypeQualifiers2, javaTypeQualifiers3, javaTypeQualifiers4, javaTypeQualifiers5);
                String str3 = javaLang;
                javaTypeQualifiers6 = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.returns(str3, javaTypeQualifiers6);
            }
        });
        classEnhancementBuilder2.function("computeIfAbsent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$13
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                JavaTypeQualifiers javaTypeQualifiers4;
                JavaTypeQualifiers javaTypeQualifiers5;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaFunction2;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers4 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str2, javaTypeQualifiers2, javaTypeQualifiers3, javaTypeQualifiers4);
                String str3 = javaLang;
                javaTypeQualifiers5 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.returns(str3, javaTypeQualifiers5);
            }
        });
        classEnhancementBuilder2.function("computeIfPresent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$14
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                JavaTypeQualifiers javaTypeQualifiers4;
                JavaTypeQualifiers javaTypeQualifiers5;
                JavaTypeQualifiers javaTypeQualifiers6;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaFunction4;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers4 = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                javaTypeQualifiers5 = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.parameter(str2, javaTypeQualifiers2, javaTypeQualifiers3, javaTypeQualifiers4, javaTypeQualifiers5);
                String str3 = javaLang;
                javaTypeQualifiers6 = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.returns(str3, javaTypeQualifiers6);
            }
        });
        classEnhancementBuilder2.function("merge", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$15
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                JavaTypeQualifiers javaTypeQualifiers4;
                JavaTypeQualifiers javaTypeQualifiers5;
                JavaTypeQualifiers javaTypeQualifiers6;
                JavaTypeQualifiers javaTypeQualifiers7;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaLang;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                receiver.parameter(str2, javaTypeQualifiers2);
                String str3 = javaFunction4;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers4 = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                javaTypeQualifiers5 = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                javaTypeQualifiers6 = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.parameter(str3, javaTypeQualifiers3, javaTypeQualifiers4, javaTypeQualifiers5, javaTypeQualifiers6);
                String str4 = javaLang;
                javaTypeQualifiers7 = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.returns(str4, javaTypeQualifiers7);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder classEnhancementBuilder3 = new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaUtil2);
        classEnhancementBuilder3.function("empty", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$16
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaUtil2;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                receiver.returns(str, javaTypeQualifiers, javaTypeQualifiers2);
            }
        });
        classEnhancementBuilder3.function("of", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$17
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaUtil2;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                receiver.returns(str2, javaTypeQualifiers2, javaTypeQualifiers3);
            }
        });
        classEnhancementBuilder3.function("ofNullable", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$18
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaUtil2;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                receiver.returns(str2, javaTypeQualifiers2, javaTypeQualifiers3);
            }
        });
        classEnhancementBuilder3.function(TmpConstant.PROPERTY_IDENTIFIER_GET, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$19
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                receiver.returns(str, javaTypeQualifiers);
            }
        });
        classEnhancementBuilder3.function("ifPresent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$20
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaFunction3;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_NULLABLE;
                receiver.parameter(str, javaTypeQualifiers, javaTypeQualifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaLang("ref/Reference")).function(TmpConstant.PROPERTY_IDENTIFIER_GET, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$21
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NULLABLE;
                receiver.returns(str, javaTypeQualifiers);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction).function("test", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$22
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                receiver.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaFunction("BiPredicate")).function("test", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$23
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaLang;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str2, javaTypeQualifiers2);
                receiver.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction3).function("accept", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$24
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction5).function("accept", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$25
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaLang;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str2, javaTypeQualifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction2).function("apply", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$26
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaLang;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.returns(str2, javaTypeQualifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction4).function("apply", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$27
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                JavaTypeQualifiers javaTypeQualifiers2;
                JavaTypeQualifiers javaTypeQualifiers3;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str, javaTypeQualifiers);
                String str2 = javaLang;
                javaTypeQualifiers2 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.parameter(str2, javaTypeQualifiers2);
                String str3 = javaLang;
                javaTypeQualifiers3 = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.returns(str3, javaTypeQualifiers3);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaFunction("Supplier")).function(TmpConstant.PROPERTY_IDENTIFIER_GET, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$$special$$inlined$enhancement$lambda$28
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder receiver) {
                JavaTypeQualifiers javaTypeQualifiers;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                String str = javaLang;
                javaTypeQualifiers = PredefinedEnhancementInfoKt.NOT_PLATFORM;
                receiver.returns(str, javaTypeQualifiers);
            }
        });
        PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE = signatureEnhancementBuilder.build();
    }

    public static final Map<String, PredefinedFunctionEnhancementInfo> getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE() {
        return PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;
    }
}
