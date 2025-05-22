package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class DescriptorResolverUtils {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 15 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 15 ? 3 : 2];
        switch (i) {
            case 1:
            case 6:
            case 11:
                objArr[0] = "membersFromSupertypes";
                break;
            case 2:
            case 7:
            case 12:
                objArr[0] = "membersFromCurrent";
                break;
            case 3:
            case 8:
            case 13:
                objArr[0] = "classDescriptor";
                break;
            case 4:
            case 9:
            case 14:
                objArr[0] = "errorReporter";
                break;
            case 5:
            case 10:
            case 16:
            default:
                objArr[0] = "name";
                break;
            case 15:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
                break;
            case 17:
                objArr[0] = "annotationClass";
                break;
            case 18:
                objArr[0] = "member";
                break;
            case 19:
            case 20:
                objArr[0] = "method";
                break;
        }
        if (i != 15) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
        } else {
            objArr[1] = "resolveOverrides";
        }
        switch (i) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                objArr[2] = "resolveOverridesForStaticMembers";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                objArr[2] = "resolveOverrides";
                break;
            case 15:
                break;
            case 16:
            case 17:
                objArr[2] = "getAnnotationParameterByName";
                break;
            case 18:
                objArr[2] = "isObjectMethodInInterface";
                break;
            case 19:
                objArr[2] = "isObjectMethod";
                break;
            case 20:
                objArr[2] = "isMethodWithOneObjectParameter";
                break;
            default:
                objArr[2] = "resolveOverridesForNonStaticMembers";
                break;
        }
        String format = String.format(str, objArr);
        if (i == 15) {
            throw new IllegalStateException(format);
        }
    }

    public static <D extends CallableMemberDescriptor> Collection<D> resolveOverridesForNonStaticMembers(Name name, Collection<D> collection, Collection<D> collection2, ClassDescriptor classDescriptor, ErrorReporter errorReporter) {
        if (name == null) {
            $$$reportNull$$$0(0);
        }
        if (collection == null) {
            $$$reportNull$$$0(1);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(2);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(3);
        }
        if (errorReporter == null) {
            $$$reportNull$$$0(4);
        }
        return resolveOverrides(name, collection, collection2, classDescriptor, errorReporter, false);
    }

    public static <D extends CallableMemberDescriptor> Collection<D> resolveOverridesForStaticMembers(Name name, Collection<D> collection, Collection<D> collection2, ClassDescriptor classDescriptor, ErrorReporter errorReporter) {
        if (name == null) {
            $$$reportNull$$$0(5);
        }
        if (collection == null) {
            $$$reportNull$$$0(6);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(7);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(8);
        }
        if (errorReporter == null) {
            $$$reportNull$$$0(9);
        }
        return resolveOverrides(name, collection, collection2, classDescriptor, errorReporter, true);
    }

    private static <D extends CallableMemberDescriptor> Collection<D> resolveOverrides(Name name, Collection<D> collection, Collection<D> collection2, ClassDescriptor classDescriptor, final ErrorReporter errorReporter, final boolean z) {
        if (name == null) {
            $$$reportNull$$$0(10);
        }
        if (collection == null) {
            $$$reportNull$$$0(11);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(12);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(13);
        }
        if (errorReporter == null) {
            $$$reportNull$$$0(14);
        }
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        OverridingUtil.generateOverridesInFunctionGroup(name, collection, collection2, classDescriptor, new NonReportingOverrideStrategy() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i == 1) {
                    objArr[0] = "fromSuper";
                } else if (i == 2) {
                    objArr[0] = "fromCurrent";
                } else if (i == 3) {
                    objArr[0] = "member";
                } else if (i != 4) {
                    objArr[0] = "fakeOverride";
                } else {
                    objArr[0] = "overridden";
                }
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1";
                if (i == 1 || i == 2) {
                    objArr[2] = "conflict";
                } else if (i == 3 || i == 4) {
                    objArr[2] = "setOverriddenDescriptors";
                } else {
                    objArr[2] = "addFakeOverride";
                }
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
            public void conflict(CallableMemberDescriptor callableMemberDescriptor, CallableMemberDescriptor callableMemberDescriptor2) {
                if (callableMemberDescriptor == null) {
                    $$$reportNull$$$0(1);
                }
                if (callableMemberDescriptor2 == null) {
                    $$$reportNull$$$0(2);
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
            public void addFakeOverride(CallableMemberDescriptor callableMemberDescriptor) {
                if (callableMemberDescriptor == null) {
                    $$$reportNull$$$0(0);
                }
                OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor, new Function1<CallableMemberDescriptor, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.1
                    private static /* synthetic */ void $$$reportNull$$$0(int i) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1$1", "invoke"));
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public Unit invoke(CallableMemberDescriptor callableMemberDescriptor2) {
                        if (callableMemberDescriptor2 == null) {
                            $$$reportNull$$$0(0);
                        }
                        ErrorReporter.this.reportCannotInferVisibility(callableMemberDescriptor2);
                        return Unit.INSTANCE;
                    }
                });
                linkedHashSet.add(callableMemberDescriptor);
            }

            @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
            public void setOverriddenDescriptors(CallableMemberDescriptor callableMemberDescriptor, Collection<? extends CallableMemberDescriptor> collection3) {
                if (callableMemberDescriptor == null) {
                    $$$reportNull$$$0(3);
                }
                if (collection3 == null) {
                    $$$reportNull$$$0(4);
                }
                if (!z || callableMemberDescriptor.getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
                    super.setOverriddenDescriptors(callableMemberDescriptor, collection3);
                }
            }
        });
        return linkedHashSet;
    }

    public static ValueParameterDescriptor getAnnotationParameterByName(Name name, ClassDescriptor classDescriptor) {
        if (name == null) {
            $$$reportNull$$$0(16);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(17);
        }
        Collection<ClassConstructorDescriptor> constructors = classDescriptor.getConstructors();
        if (constructors.size() != 1) {
            return null;
        }
        for (ValueParameterDescriptor valueParameterDescriptor : constructors.iterator().next().getValueParameters()) {
            if (valueParameterDescriptor.getName().equals(name)) {
                return valueParameterDescriptor;
            }
        }
        return null;
    }

    public static boolean isObjectMethodInInterface(JavaMember javaMember) {
        if (javaMember == null) {
            $$$reportNull$$$0(18);
        }
        return javaMember.getContainingClass().isInterface() && (javaMember instanceof JavaMethod) && isObjectMethod((JavaMethod) javaMember);
    }

    private static boolean isObjectMethod(JavaMethod javaMethod) {
        if (javaMethod == null) {
            $$$reportNull$$$0(19);
        }
        String asString = javaMethod.getName().asString();
        if (asString.equals("toString") || asString.equals("hashCode")) {
            return javaMethod.getValueParameters().isEmpty();
        }
        if (asString.equals("equals")) {
            return isMethodWithOneObjectParameter(javaMethod);
        }
        return false;
    }

    private static boolean isMethodWithOneObjectParameter(JavaMethod javaMethod) {
        if (javaMethod == null) {
            $$$reportNull$$$0(20);
        }
        List<JavaValueParameter> valueParameters = javaMethod.getValueParameters();
        if (valueParameters.size() == 1) {
            JavaType type = valueParameters.get(0).getType();
            if (type instanceof JavaClassifierType) {
                JavaClassifier classifier = ((JavaClassifierType) type).getClassifier();
                if (classifier instanceof JavaClass) {
                    FqName fqName = ((JavaClass) classifier).getFqName();
                    return fqName != null && fqName.asString().equals("java.lang.Object");
                }
            }
        }
        return false;
    }
}
