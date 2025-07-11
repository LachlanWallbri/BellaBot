package kotlin.reflect.jvm.internal.impl.types.error;

import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ErrorSimpleFunctionDescriptorImpl extends SimpleFunctionDescriptorImpl {
    private final ErrorUtils.ErrorScope ownerScope;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "ownerScope";
                break;
            case 2:
                objArr[0] = "newOwner";
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = "annotations";
                break;
            case 5:
                objArr[0] = MapElement.Source.SOURCE;
                break;
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl";
                break;
            case 8:
                objArr[0] = "overriddenDescriptors";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i == 6) {
            objArr[1] = "createSubstitutedCopy";
        } else if (i != 7) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl";
        } else {
            objArr[1] = "copy";
        }
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 6:
            case 7:
                break;
            case 8:
                objArr[2] = "setOverriddenDescriptors";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i != 6 && i != 7) {
            throw new IllegalArgumentException(format);
        }
        throw new IllegalStateException(format);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public SimpleFunctionDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality, Visibility visibility, CallableMemberDescriptor.Kind kind, boolean z) {
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor, FunctionDescriptor functionDescriptor, CallableMemberDescriptor.Kind kind, Name name, Annotations annotations, SourceElement sourceElement) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(2);
        }
        if (kind == null) {
            $$$reportNull$$$0(3);
        }
        if (annotations == null) {
            $$$reportNull$$$0(4);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(5);
        }
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public <V> V getUserData(CallableDescriptor.UserDataKey<V> userDataKey) {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isSuspend() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(Collection<? extends CallableMemberDescriptor> collection) {
        if (collection == null) {
            $$$reportNull$$$0(8);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorSimpleFunctionDescriptorImpl(ClassDescriptor classDescriptor, ErrorUtils.ErrorScope errorScope) {
        super(classDescriptor, null, Annotations.Companion.getEMPTY(), Name.special("<ERROR FUNCTION>"), CallableMemberDescriptor.Kind.DECLARATION, SourceElement.NO_SOURCE);
        if (classDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (errorScope == null) {
            $$$reportNull$$$0(1);
        }
        this.ownerScope = errorScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder() {
        return new FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.types.error.ErrorSimpleFunctionDescriptorImpl.1
            /* JADX WARN: Removed duplicated region for block: B:100:0x0071  */
            /* JADX WARN: Removed duplicated region for block: B:101:0x0076  */
            /* JADX WARN: Removed duplicated region for block: B:102:0x007b  */
            /* JADX WARN: Removed duplicated region for block: B:103:0x0080  */
            /* JADX WARN: Removed duplicated region for block: B:104:0x0085  */
            /* JADX WARN: Removed duplicated region for block: B:105:0x008a  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x0058  */
            /* JADX WARN: Removed duplicated region for block: B:34:0x009e  */
            /* JADX WARN: Removed duplicated region for block: B:47:0x010e  */
            /* JADX WARN: Removed duplicated region for block: B:48:0x0111  */
            /* JADX WARN: Removed duplicated region for block: B:49:0x0116  */
            /* JADX WARN: Removed duplicated region for block: B:50:0x011b  */
            /* JADX WARN: Removed duplicated region for block: B:51:0x0120  */
            /* JADX WARN: Removed duplicated region for block: B:52:0x0123  */
            /* JADX WARN: Removed duplicated region for block: B:53:0x0126  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x0129  */
            /* JADX WARN: Removed duplicated region for block: B:55:0x012c  */
            /* JADX WARN: Removed duplicated region for block: B:56:0x012f  */
            /* JADX WARN: Removed duplicated region for block: B:57:0x0132  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x013a A[ADDED_TO_REGION] */
            /* JADX WARN: Removed duplicated region for block: B:71:0x0151  */
            /* JADX WARN: Removed duplicated region for block: B:95:0x0109  */
            /* JADX WARN: Removed duplicated region for block: B:96:0x005d  */
            /* JADX WARN: Removed duplicated region for block: B:97:0x0062  */
            /* JADX WARN: Removed duplicated region for block: B:98:0x0067  */
            /* JADX WARN: Removed duplicated region for block: B:99:0x006c  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str;
                int i2;
                if (i != 1 && i != 3 && i != 5 && i != 10 && i != 12 && i != 14 && i != 16 && i != 18 && i != 30 && i != 7 && i != 8) {
                    switch (i) {
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                            break;
                        default:
                            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                            break;
                    }
                    if (i != 1 && i != 3 && i != 5 && i != 10 && i != 12 && i != 14 && i != 16 && i != 18 && i != 30 && i != 7 && i != 8) {
                        switch (i) {
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                                break;
                            default:
                                i2 = 3;
                                break;
                        }
                        Object[] objArr = new Object[i2];
                        switch (i) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                            case 12:
                            case 14:
                            case 16:
                            case 18:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 30:
                                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl$1";
                                break;
                            case 2:
                                objArr[0] = "modality";
                                break;
                            case 4:
                                objArr[0] = "visibility";
                                break;
                            case 6:
                                objArr[0] = "kind";
                                break;
                            case 9:
                                objArr[0] = "name";
                                break;
                            case 11:
                            case 17:
                                objArr[0] = "parameters";
                                break;
                            case 13:
                                objArr[0] = "substitution";
                                break;
                            case 15:
                                objArr[0] = "userDataKey";
                                break;
                            case 19:
                                objArr[0] = "type";
                                break;
                            case 29:
                                objArr[0] = "additionalAnnotations";
                                break;
                            default:
                                objArr[0] = "owner";
                                break;
                        }
                        if (i != 1) {
                            objArr[1] = "setOwner";
                        } else if (i == 3) {
                            objArr[1] = "setModality";
                        } else if (i == 5) {
                            objArr[1] = "setVisibility";
                        } else if (i == 10) {
                            objArr[1] = "setName";
                        } else if (i == 12) {
                            objArr[1] = "setValueParameters";
                        } else if (i == 14) {
                            objArr[1] = "setSubstitution";
                        } else if (i == 16) {
                            objArr[1] = "putUserData";
                        } else if (i == 18) {
                            objArr[1] = "setTypeParameters";
                        } else if (i == 30) {
                            objArr[1] = "setAdditionalAnnotations";
                        } else if (i == 7) {
                            objArr[1] = "setKind";
                        } else if (i != 8) {
                            switch (i) {
                                case 20:
                                    objArr[1] = "setReturnType";
                                    break;
                                case 21:
                                    objArr[1] = "setExtensionReceiverParameter";
                                    break;
                                case 22:
                                    objArr[1] = "setDispatchReceiverParameter";
                                    break;
                                case 23:
                                    objArr[1] = "setOriginal";
                                    break;
                                case 24:
                                    objArr[1] = "setSignatureChange";
                                    break;
                                case 25:
                                    objArr[1] = "setPreserveSourceElement";
                                    break;
                                case 26:
                                    objArr[1] = "setDropOriginalInContainingParts";
                                    break;
                                case 27:
                                    objArr[1] = "setHiddenToOvercomeSignatureClash";
                                    break;
                                case 28:
                                    objArr[1] = "setHiddenForResolutionEverywhereBesideSupercalls";
                                    break;
                                default:
                                    objArr[1] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl$1";
                                    break;
                            }
                        } else {
                            objArr[1] = "setCopyOverrides";
                        }
                        switch (i) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                            case 12:
                            case 14:
                            case 16:
                            case 18:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 30:
                                break;
                            case 2:
                                objArr[2] = "setModality";
                                break;
                            case 4:
                                objArr[2] = "setVisibility";
                                break;
                            case 6:
                                objArr[2] = "setKind";
                                break;
                            case 9:
                                objArr[2] = "setName";
                                break;
                            case 11:
                                objArr[2] = "setValueParameters";
                                break;
                            case 13:
                                objArr[2] = "setSubstitution";
                                break;
                            case 15:
                                objArr[2] = "putUserData";
                                break;
                            case 17:
                                objArr[2] = "setTypeParameters";
                                break;
                            case 19:
                                objArr[2] = "setReturnType";
                                break;
                            case 29:
                                objArr[2] = "setAdditionalAnnotations";
                                break;
                            default:
                                objArr[2] = "setOwner";
                                break;
                        }
                        String format = String.format(str, objArr);
                        if (i != 1 && i != 3 && i != 5 && i != 10 && i != 12 && i != 14 && i != 16 && i != 18 && i != 30 && i != 7 && i != 8) {
                            switch (i) {
                                case 20:
                                case 21:
                                case 22:
                                case 23:
                                case 24:
                                case 25:
                                case 26:
                                case 27:
                                case 28:
                                    break;
                                default:
                                    throw new IllegalArgumentException(format);
                            }
                        }
                        throw new IllegalStateException(format);
                    }
                    i2 = 2;
                    Object[] objArr2 = new Object[i2];
                    switch (i) {
                    }
                    if (i != 1) {
                    }
                    switch (i) {
                    }
                    String format2 = String.format(str, objArr2);
                    if (i != 1) {
                        switch (i) {
                        }
                    }
                    throw new IllegalStateException(format2);
                }
                str = "@NotNull method %s.%s must not return null";
                if (i != 1) {
                    switch (i) {
                    }
                    Object[] objArr22 = new Object[i2];
                    switch (i) {
                    }
                    if (i != 1) {
                    }
                    switch (i) {
                    }
                    String format22 = String.format(str, objArr22);
                    if (i != 1) {
                    }
                    throw new IllegalStateException(format22);
                }
                i2 = 2;
                Object[] objArr222 = new Object[i2];
                switch (i) {
                }
                if (i != 1) {
                }
                switch (i) {
                }
                String format222 = String.format(str, objArr222);
                if (i != 1) {
                }
                throw new IllegalStateException(format222);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setAdditionalAnnotations(Annotations annotations) {
                if (annotations == null) {
                    $$$reportNull$$$0(29);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setCopyOverrides(boolean z) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setDispatchReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setDropOriginalInContainingParts() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setExtensionReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setHiddenForResolutionEverywhereBesideSupercalls() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setHiddenToOvercomeSignatureClash() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setKind(CallableMemberDescriptor.Kind kind) {
                if (kind == null) {
                    $$$reportNull$$$0(6);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setModality(Modality modality) {
                if (modality == null) {
                    $$$reportNull$$$0(2);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setName(Name name) {
                if (name == null) {
                    $$$reportNull$$$0(9);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setOriginal(CallableMemberDescriptor callableMemberDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setOwner(DeclarationDescriptor declarationDescriptor) {
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(0);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setPreserveSourceElement() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setReturnType(KotlinType kotlinType) {
                if (kotlinType == null) {
                    $$$reportNull$$$0(19);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setSignatureChange() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setSubstitution(TypeSubstitution typeSubstitution) {
                if (typeSubstitution == null) {
                    $$$reportNull$$$0(13);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setTypeParameters(List<TypeParameterDescriptor> list) {
                if (list == null) {
                    $$$reportNull$$$0(17);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setValueParameters(List<ValueParameterDescriptor> list) {
                if (list == null) {
                    $$$reportNull$$$0(11);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setVisibility(Visibility visibility) {
                if (visibility == null) {
                    $$$reportNull$$$0(4);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            public SimpleFunctionDescriptor build() {
                return ErrorSimpleFunctionDescriptorImpl.this;
            }
        };
    }
}
