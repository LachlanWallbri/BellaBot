package org.jetbrains.anko.appcompat.p092v7.coroutines;

import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.FitWindowsFrameLayout;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001aO\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\r\u001aE\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\b\b\u0002\u0010\u0003\u001a\u00020\u00042'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\\\u0010\u0011\u001a\u00020\u0001*\u00020\u00122\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0013¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001as\u0010\u0019\u001a\u00020\u0001*\u00020\u001a2\b\b\u0002\u0010\u0003\u001a\u00020\u00042U\u0010\u0007\u001aQ\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001c\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u001b¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001af\u0010 \u001a\u00020\u0001*\u00020!2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\"¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0013¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010$\u001af\u0010 \u001a\u00020\u0001*\u00020%2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\"¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(#\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0013¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010&\u001ao\u0010'\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042Q\u0010\u0007\u001aM\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b()\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u001b¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010*\u001a-\u0010+\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0002\b\f\u001a\\\u0010/\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b((\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0013¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u00100\u001a-\u00101\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0002\b\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, m3961d2 = {"onClose", "", "Landroidx/appcompat/widget/SearchView;", "context", "Lkotlin/coroutines/CoroutineContext;", "returnValue", "", "handler", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/support/v7/widget/SearchView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function2;)V", "onDismiss", "Landroidx/appcompat/widget/ActivityChooserView;", "(Landroid/support/v7/widget/ActivityChooserView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)V", "onFitSystemWindows", "Landroidx/appcompat/widget/FitWindowsFrameLayout;", "Lkotlin/Function3;", "Landroid/graphics/Rect;", "Lkotlin/ParameterName;", "name", "insets", "(Landroid/support/v7/widget/FitWindowsFrameLayout;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onInflate", "Landroidx/appcompat/widget/ViewStubCompat;", "Lkotlin/Function4;", "stub", "Landroid/view/View;", "inflated", "(Landroid/support/v7/widget/ViewStubCompat;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "onMenuItemClick", "Landroidx/appcompat/widget/ActionMenuView;", "Landroid/view/MenuItem;", "item", "(Landroid/support/v7/widget/ActionMenuView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function3;)V", "Landroidx/appcompat/widget/Toolbar;", "(Landroid/support/v7/widget/Toolbar;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function3;)V", "onQueryTextFocusChange", "v", "hasFocus", "(Landroid/support/v7/widget/SearchView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "onQueryTextListener", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/appcompat/v7/coroutines/__SearchView_OnQueryTextListener;", "onSearchClick", "(Landroid/support/v7/widget/SearchView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onSuggestionListener", "Lorg/jetbrains/anko/appcompat/v7/coroutines/__SearchView_OnSuggestionListener;", "anko-appcompat-v7-coroutines_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class AppcompatV7CoroutinesListenersWithCoroutinesKt {
    public static /* synthetic */ void onMenuItemClick$default(ActionMenuView actionMenuView, CoroutineContext coroutineContext, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onMenuItemClick(actionMenuView, coroutineContext, z, (Function3<? super CoroutineScope, ? super MenuItem, ? super Continuation<? super Unit>, ? extends Object>) function3);
    }

    public static final void onMenuItemClick(ActionMenuView receiver$0, final CoroutineContext context, final boolean z, final Function3<? super CoroutineScope, ? super MenuItem, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() { // from class: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onMenuItemClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/appcompat/v7/coroutines/AppcompatV7CoroutinesListenersWithCoroutinesKt$onMenuItemClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {18, 20}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onMenuItemClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C87571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MenuItem $item;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10077p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87571(MenuItem menuItem, Continuation continuation) {
                    super(2, continuation);
                    this.$item = menuItem;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87571 c87571 = new C87571(this.$item, completion);
                    c87571.f10077p$ = (CoroutineScope) obj;
                    return c87571;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10077p$;
                        Function3 function3 = handler;
                        MenuItem menuItem = this.$item;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, menuItem, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // androidx.appcompat.widget.ActionMenuView.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87571(menuItem, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onDismiss$default(ActivityChooserView activityChooserView, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onDismiss(activityChooserView, coroutineContext, function2);
    }

    public static final void onDismiss(ActivityChooserView receiver$0, final CoroutineContext context, final Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onDismiss$1
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, handler);
            }
        });
    }

    public static /* synthetic */ void onFitSystemWindows$default(FitWindowsFrameLayout fitWindowsFrameLayout, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onFitSystemWindows(fitWindowsFrameLayout, coroutineContext, function3);
    }

    public static final void onFitSystemWindows(FitWindowsFrameLayout receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super Rect, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener() { // from class: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onFitSystemWindows$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/appcompat/v7/coroutines/AppcompatV7CoroutinesListenersWithCoroutinesKt$onFitSystemWindows$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {39, 41}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onFitSystemWindows$1$1, reason: invalid class name */
            /* loaded from: classes9.dex */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Rect $insets;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10075p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Rect rect, Continuation continuation) {
                    super(2, continuation);
                    this.$insets = rect;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$insets, completion);
                    anonymousClass1.f10075p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10075p$;
                        Function3 function3 = handler;
                        Rect rect = this.$insets;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, rect, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // androidx.appcompat.widget.FitWindowsViewGroup.OnFitSystemWindowsListener
            public final void onFitSystemWindows(Rect rect) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new AnonymousClass1(rect, null));
            }
        });
    }

    public static /* synthetic */ void onClose$default(SearchView searchView, CoroutineContext coroutineContext, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onClose(searchView, coroutineContext, z, function2);
    }

    public static final void onClose(SearchView receiver$0, final CoroutineContext context, final boolean z, final Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnCloseListener(new SearchView.OnCloseListener() { // from class: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onClose$1
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, handler);
                return z;
            }
        });
    }

    public static /* synthetic */ void onQueryTextFocusChange$default(SearchView searchView, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onQueryTextFocusChange(searchView, coroutineContext, function4);
    }

    public static final void onQueryTextFocusChange(SearchView receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super View, ? super Boolean, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() { // from class: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onQueryTextFocusChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/appcompat/v7/coroutines/AppcompatV7CoroutinesListenersWithCoroutinesKt$onQueryTextFocusChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {61, 63}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onQueryTextFocusChange$1$1, reason: invalid class name */
            /* loaded from: classes9.dex */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $hasFocus;

                /* renamed from: $v */
                final /* synthetic */ View f10079$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10080p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(View view, boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.f10079$v = view;
                    this.$hasFocus = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f10079$v, this.$hasFocus, completion);
                    anonymousClass1.f10080p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10080p$;
                        Function4 function4 = handler;
                        View v = this.f10079$v;
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        Boolean boxBoolean = Boxing.boxBoolean(this.$hasFocus);
                        this.label = 1;
                        if (function4.invoke(coroutineScope, v, boxBoolean, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new AnonymousClass1(view, z, null));
            }
        });
    }

    public static /* synthetic */ void onQueryTextListener$default(SearchView searchView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onQueryTextListener(searchView, coroutineContext, function1);
    }

    public static final void onQueryTextListener(SearchView receiver$0, CoroutineContext context, Function1<? super __SearchView_OnQueryTextListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __SearchView_OnQueryTextListener __searchview_onquerytextlistener = new __SearchView_OnQueryTextListener(context);
        init.invoke(__searchview_onquerytextlistener);
        receiver$0.setOnQueryTextListener(__searchview_onquerytextlistener);
    }

    public static /* synthetic */ void onSearchClick$default(SearchView searchView, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onSearchClick(searchView, coroutineContext, function3);
    }

    public static final void onSearchClick(SearchView receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnSearchClickListener(new View.OnClickListener() { // from class: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onSearchClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/appcompat/v7/coroutines/AppcompatV7CoroutinesListenersWithCoroutinesKt$onSearchClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {123, 125}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onSearchClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C87601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $v */
                final /* synthetic */ View f10081$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10082p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87601(View view, Continuation continuation) {
                    super(2, continuation);
                    this.f10081$v = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87601 c87601 = new C87601(this.f10081$v, completion);
                    c87601.f10082p$ = (CoroutineScope) obj;
                    return c87601;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10082p$;
                        Function3 function3 = handler;
                        View view = this.f10081$v;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87601(view, null));
            }
        });
    }

    public static /* synthetic */ void onSuggestionListener$default(SearchView searchView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onSuggestionListener(searchView, coroutineContext, function1);
    }

    public static final void onSuggestionListener(SearchView receiver$0, CoroutineContext context, Function1<? super __SearchView_OnSuggestionListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __SearchView_OnSuggestionListener __searchview_onsuggestionlistener = new __SearchView_OnSuggestionListener(context);
        init.invoke(__searchview_onsuggestionlistener);
        receiver$0.setOnSuggestionListener(__searchview_onsuggestionlistener);
    }

    public static /* synthetic */ void onMenuItemClick$default(Toolbar toolbar, CoroutineContext coroutineContext, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onMenuItemClick(toolbar, coroutineContext, z, (Function3<? super CoroutineScope, ? super MenuItem, ? super Continuation<? super Unit>, ? extends Object>) function3);
    }

    public static final void onMenuItemClick(Toolbar receiver$0, final CoroutineContext context, final boolean z, final Function3<? super CoroutineScope, ? super MenuItem, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onMenuItemClick$2

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/appcompat/v7/coroutines/AppcompatV7CoroutinesListenersWithCoroutinesKt$onMenuItemClick$2$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {186, 188}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onMenuItemClick$2$1 */
            /* loaded from: classes9.dex */
            static final class C87581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MenuItem $item;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10078p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87581(MenuItem menuItem, Continuation continuation) {
                    super(2, continuation);
                    this.$item = menuItem;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87581 c87581 = new C87581(this.$item, completion);
                    c87581.f10078p$ = (CoroutineScope) obj;
                    return c87581;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10078p$;
                        Function3 function3 = handler;
                        MenuItem menuItem = this.$item;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, menuItem, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87581(menuItem, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onInflate$default(ViewStubCompat viewStubCompat, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onInflate(viewStubCompat, coroutineContext, function4);
    }

    public static final void onInflate(ViewStubCompat receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super ViewStubCompat, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnInflateListener(new ViewStubCompat.OnInflateListener() { // from class: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onInflate$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/appcompat/v7/coroutines/AppcompatV7CoroutinesListenersWithCoroutinesKt$onInflate$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {198, 200}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.appcompat.v7.coroutines.AppcompatV7CoroutinesListenersWithCoroutinesKt$onInflate$1$1 */
            /* loaded from: classes9.dex */
            static final class C87561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ View $inflated;
                final /* synthetic */ ViewStubCompat $stub;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10076p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87561(ViewStubCompat viewStubCompat, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$stub = viewStubCompat;
                    this.$inflated = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87561 c87561 = new C87561(this.$stub, this.$inflated, completion);
                    c87561.f10076p$ = (CoroutineScope) obj;
                    return c87561;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10076p$;
                        Function4 function4 = handler;
                        ViewStubCompat viewStubCompat = this.$stub;
                        View view = this.$inflated;
                        this.label = 1;
                        if (function4.invoke(coroutineScope, viewStubCompat, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // androidx.appcompat.widget.ViewStubCompat.OnInflateListener
            public final void onInflate(ViewStubCompat viewStubCompat, View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87561(viewStubCompat, view, null));
            }
        });
    }
}
