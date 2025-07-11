package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class TypeParameterDescriptorImpl extends AbstractTypeParameterDescriptor {
    private boolean initialized;
    private final Function1<KotlinType, Void> reportCycleError;
    private final List<KotlinType> upperBounds;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 24) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 24) ? 2 : 3];
        switch (i) {
            case 1:
            case 6:
            case 11:
            case 17:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
            case 12:
            case 18:
                objArr[0] = "variance";
                break;
            case 3:
            case 8:
            case 13:
            case 19:
                objArr[0] = "name";
                break;
            case 4:
            case 24:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
                break;
            case 5:
            case 10:
            case 16:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 9:
            case 14:
            case 20:
                objArr[0] = MapElement.Source.SOURCE;
                break;
            case 15:
                objArr[0] = "supertypeLoopsResolver";
                break;
            case 21:
                objArr[0] = "supertypeLoopsChecker";
                break;
            case 22:
                objArr[0] = "bound";
                break;
            case 23:
                objArr[0] = "type";
                break;
        }
        if (i == 4) {
            objArr[1] = "createWithDefaultBound";
        } else if (i != 24) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
        } else {
            objArr[1] = "resolveUpperBounds";
        }
        switch (i) {
            case 4:
            case 24:
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[2] = "createForFurtherModification";
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                objArr[2] = "<init>";
                break;
            case 22:
                objArr[2] = "addUpperBound";
                break;
            case 23:
                objArr[2] = "reportSupertypeLoopError";
                break;
            default:
                objArr[2] = "createWithDefaultBound";
                break;
        }
        String format = String.format(str, objArr);
        if (i != 4 && i != 24) {
            throw new IllegalArgumentException(format);
        }
        throw new IllegalStateException(format);
    }

    public static TypeParameterDescriptor createWithDefaultBound(DeclarationDescriptor declarationDescriptor, Annotations annotations, boolean z, Variance variance, Name name, int i) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        if (variance == null) {
            $$$reportNull$$$0(2);
        }
        if (name == null) {
            $$$reportNull$$$0(3);
        }
        TypeParameterDescriptorImpl createForFurtherModification = createForFurtherModification(declarationDescriptor, annotations, z, variance, name, i, SourceElement.NO_SOURCE);
        createForFurtherModification.addUpperBound(DescriptorUtilsKt.getBuiltIns(declarationDescriptor).getDefaultBound());
        createForFurtherModification.setInitialized();
        if (createForFurtherModification == null) {
            $$$reportNull$$$0(4);
        }
        return createForFurtherModification;
    }

    public static TypeParameterDescriptorImpl createForFurtherModification(DeclarationDescriptor declarationDescriptor, Annotations annotations, boolean z, Variance variance, Name name, int i, SourceElement sourceElement) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(5);
        }
        if (annotations == null) {
            $$$reportNull$$$0(6);
        }
        if (variance == null) {
            $$$reportNull$$$0(7);
        }
        if (name == null) {
            $$$reportNull$$$0(8);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(9);
        }
        return createForFurtherModification(declarationDescriptor, annotations, z, variance, name, i, sourceElement, null, SupertypeLoopChecker.EMPTY.INSTANCE);
    }

    public static TypeParameterDescriptorImpl createForFurtherModification(DeclarationDescriptor declarationDescriptor, Annotations annotations, boolean z, Variance variance, Name name, int i, SourceElement sourceElement, Function1<KotlinType, Void> function1, SupertypeLoopChecker supertypeLoopChecker) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(10);
        }
        if (annotations == null) {
            $$$reportNull$$$0(11);
        }
        if (variance == null) {
            $$$reportNull$$$0(12);
        }
        if (name == null) {
            $$$reportNull$$$0(13);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(14);
        }
        if (supertypeLoopChecker == null) {
            $$$reportNull$$$0(15);
        }
        return new TypeParameterDescriptorImpl(declarationDescriptor, annotations, z, variance, name, i, sourceElement, function1, supertypeLoopChecker);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private TypeParameterDescriptorImpl(DeclarationDescriptor declarationDescriptor, Annotations annotations, boolean z, Variance variance, Name name, int i, SourceElement sourceElement, Function1<KotlinType, Void> function1, SupertypeLoopChecker supertypeLoopChecker) {
        super(LockBasedStorageManager.NO_LOCKS, declarationDescriptor, annotations, name, variance, z, i, sourceElement, supertypeLoopChecker);
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(16);
        }
        if (annotations == null) {
            $$$reportNull$$$0(17);
        }
        if (variance == null) {
            $$$reportNull$$$0(18);
        }
        if (name == null) {
            $$$reportNull$$$0(19);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(20);
        }
        if (supertypeLoopChecker == null) {
            $$$reportNull$$$0(21);
        }
        this.upperBounds = new ArrayList(1);
        this.initialized = false;
        this.reportCycleError = function1;
    }

    private void checkInitialized() {
        if (this.initialized) {
            return;
        }
        throw new IllegalStateException("Type parameter descriptor is not initialized: " + nameForAssertions());
    }

    private void checkUninitialized() {
        if (this.initialized) {
            throw new IllegalStateException("Type parameter descriptor is already initialized: " + nameForAssertions());
        }
    }

    private String nameForAssertions() {
        return getName() + " declared in " + DescriptorUtils.getFqName(getContainingDeclaration());
    }

    public void setInitialized() {
        checkUninitialized();
        this.initialized = true;
    }

    public void addUpperBound(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(22);
        }
        checkUninitialized();
        doAddUpperBound(kotlinType);
    }

    private void doAddUpperBound(KotlinType kotlinType) {
        if (KotlinTypeKt.isError(kotlinType)) {
            return;
        }
        this.upperBounds.add(kotlinType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    /* renamed from: reportSupertypeLoopError */
    protected void mo5463reportSupertypeLoopError(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(23);
        }
        Function1<KotlinType, Void> function1 = this.reportCycleError;
        if (function1 == null) {
            return;
        }
        function1.invoke(kotlinType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    protected List<KotlinType> resolveUpperBounds() {
        checkInitialized();
        List<KotlinType> list = this.upperBounds;
        if (list == null) {
            $$$reportNull$$$0(24);
        }
        return list;
    }
}
