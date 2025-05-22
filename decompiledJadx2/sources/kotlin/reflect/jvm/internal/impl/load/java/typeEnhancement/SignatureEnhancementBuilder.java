package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: predefinedEnhancementInfo.kt */
/* loaded from: classes2.dex */
final class SignatureEnhancementBuilder {
    private final Map<String, PredefinedFunctionEnhancementInfo> signatures = new LinkedHashMap();

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: predefinedEnhancementInfo.kt */
    /* loaded from: classes2.dex */
    public final class ClassEnhancementBuilder {
        private final String className;
        final /* synthetic */ SignatureEnhancementBuilder this$0;

        public ClassEnhancementBuilder(SignatureEnhancementBuilder signatureEnhancementBuilder, String className) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            this.this$0 = signatureEnhancementBuilder;
            this.className = className;
        }

        public final String getClassName() {
            return this.className;
        }

        public final void function(String name, Function1<? super FunctionEnhancementBuilder, Unit> block) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(block, "block");
            Map map = this.this$0.signatures;
            FunctionEnhancementBuilder functionEnhancementBuilder = new FunctionEnhancementBuilder(this, name);
            block.invoke(functionEnhancementBuilder);
            Pair<String, PredefinedFunctionEnhancementInfo> build = functionEnhancementBuilder.build();
            map.put(build.getFirst(), build.getSecond());
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: predefinedEnhancementInfo.kt */
        /* loaded from: classes2.dex */
        public final class FunctionEnhancementBuilder {
            private final String functionName;
            private final List<Pair<String, TypeEnhancementInfo>> parameters;
            private Pair<String, TypeEnhancementInfo> returnType;
            final /* synthetic */ ClassEnhancementBuilder this$0;

            public FunctionEnhancementBuilder(ClassEnhancementBuilder classEnhancementBuilder, String functionName) {
                Intrinsics.checkParameterIsNotNull(functionName, "functionName");
                this.this$0 = classEnhancementBuilder;
                this.functionName = functionName;
                this.parameters = new ArrayList();
                this.returnType = TuplesKt.m3968to(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, null);
            }

            public final void parameter(String type, JavaTypeQualifiers... qualifiers) {
                TypeEnhancementInfo typeEnhancementInfo;
                Intrinsics.checkParameterIsNotNull(type, "type");
                Intrinsics.checkParameterIsNotNull(qualifiers, "qualifiers");
                List<Pair<String, TypeEnhancementInfo>> list = this.parameters;
                if (qualifiers.length == 0) {
                    typeEnhancementInfo = null;
                } else {
                    Iterable<IndexedValue> withIndex = ArraysKt.withIndex(qualifiers);
                    LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(withIndex, 10)), 16));
                    for (IndexedValue indexedValue : withIndex) {
                        linkedHashMap.put(Integer.valueOf(indexedValue.getIndex()), (JavaTypeQualifiers) indexedValue.getValue());
                    }
                    typeEnhancementInfo = new TypeEnhancementInfo(linkedHashMap);
                }
                list.add(TuplesKt.m3968to(type, typeEnhancementInfo));
            }

            public final void returns(String type, JavaTypeQualifiers... qualifiers) {
                Intrinsics.checkParameterIsNotNull(type, "type");
                Intrinsics.checkParameterIsNotNull(qualifiers, "qualifiers");
                Iterable<IndexedValue> withIndex = ArraysKt.withIndex(qualifiers);
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(withIndex, 10)), 16));
                for (IndexedValue indexedValue : withIndex) {
                    linkedHashMap.put(Integer.valueOf(indexedValue.getIndex()), (JavaTypeQualifiers) indexedValue.getValue());
                }
                this.returnType = TuplesKt.m3968to(type, new TypeEnhancementInfo(linkedHashMap));
            }

            public final void returns(JvmPrimitiveType type) {
                Intrinsics.checkParameterIsNotNull(type, "type");
                this.returnType = TuplesKt.m3968to(type.getDesc(), null);
            }

            public final Pair<String, PredefinedFunctionEnhancementInfo> build() {
                SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
                String className = this.this$0.getClassName();
                String str = this.functionName;
                List<Pair<String, TypeEnhancementInfo>> list = this.parameters;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add((String) ((Pair) it.next()).getFirst());
                }
                String signature = signatureBuildingComponents.signature(className, signatureBuildingComponents.jvmDescriptor(str, arrayList, this.returnType.getFirst()));
                TypeEnhancementInfo second = this.returnType.getSecond();
                List<Pair<String, TypeEnhancementInfo>> list2 = this.parameters;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add((TypeEnhancementInfo) ((Pair) it2.next()).getSecond());
                }
                return TuplesKt.m3968to(signature, new PredefinedFunctionEnhancementInfo(second, arrayList2));
            }
        }
    }

    public final Map<String, PredefinedFunctionEnhancementInfo> build() {
        return this.signatures;
    }
}
