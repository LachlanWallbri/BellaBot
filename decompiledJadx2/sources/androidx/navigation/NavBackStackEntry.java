package androidx.navigation;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class NavBackStackEntry implements LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner {
    private Bundle mArgs;
    private final Context mContext;
    private ViewModelProvider.Factory mDefaultFactory;
    private final NavDestination mDestination;
    private Lifecycle.State mHostLifecycle;
    final UUID mId;
    private final LifecycleRegistry mLifecycle;
    private Lifecycle.State mMaxLifecycle;
    private NavControllerViewModel mNavControllerViewModel;
    private SavedStateHandle mSavedStateHandle;
    private final SavedStateRegistryController mSavedStateRegistryController;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavBackStackEntry(Context context, NavDestination navDestination, Bundle bundle, LifecycleOwner lifecycleOwner, NavControllerViewModel navControllerViewModel) {
        this(context, navDestination, bundle, lifecycleOwner, navControllerViewModel, UUID.randomUUID(), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavBackStackEntry(Context context, NavDestination navDestination, Bundle bundle, LifecycleOwner lifecycleOwner, NavControllerViewModel navControllerViewModel, UUID uuid, Bundle bundle2) {
        this.mLifecycle = new LifecycleRegistry(this);
        this.mSavedStateRegistryController = SavedStateRegistryController.create(this);
        this.mHostLifecycle = Lifecycle.State.CREATED;
        this.mMaxLifecycle = Lifecycle.State.RESUMED;
        this.mContext = context;
        this.mId = uuid;
        this.mDestination = navDestination;
        this.mArgs = bundle;
        this.mNavControllerViewModel = navControllerViewModel;
        this.mSavedStateRegistryController.performRestore(bundle2);
        if (lifecycleOwner != null) {
            this.mHostLifecycle = lifecycleOwner.getLifecycle().getCurrentState();
        }
    }

    public NavDestination getDestination() {
        return this.mDestination;
    }

    public Bundle getArguments() {
        return this.mArgs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceArguments(Bundle bundle) {
        this.mArgs = bundle;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMaxLifecycle(Lifecycle.State state) {
        this.mMaxLifecycle = state;
        updateState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lifecycle.State getMaxLifecycle() {
        return this.mMaxLifecycle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleLifecycleEvent(Lifecycle.Event event) {
        this.mHostLifecycle = getStateAfter(event);
        updateState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateState() {
        if (this.mHostLifecycle.ordinal() < this.mMaxLifecycle.ordinal()) {
            this.mLifecycle.setCurrentState(this.mHostLifecycle);
        } else {
            this.mLifecycle.setCurrentState(this.mMaxLifecycle);
        }
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        NavControllerViewModel navControllerViewModel = this.mNavControllerViewModel;
        if (navControllerViewModel == null) {
            throw new IllegalStateException("You must call setViewModelStore() on your NavHostController before accessing the ViewModelStore of a navigation graph.");
        }
        return navControllerViewModel.getViewModelStore(this.mId);
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        if (this.mDefaultFactory == null) {
            this.mDefaultFactory = new SavedStateViewModelFactory((Application) this.mContext.getApplicationContext(), this, this.mArgs);
        }
        return this.mDefaultFactory;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveState(Bundle bundle) {
        this.mSavedStateRegistryController.performSave(bundle);
    }

    public SavedStateHandle getSavedStateHandle() {
        if (this.mSavedStateHandle == null) {
            this.mSavedStateHandle = ((SavedStateViewModel) new ViewModelProvider(this, new NavResultSavedStateFactory(this, null)).get(SavedStateViewModel.class)).getHandle();
        }
        return this.mSavedStateHandle;
    }

    private static Lifecycle.State getStateAfter(Lifecycle.Event event) {
        switch (event) {
            case ON_CREATE:
            case ON_STOP:
                return Lifecycle.State.CREATED;
            case ON_START:
            case ON_PAUSE:
                return Lifecycle.State.STARTED;
            case ON_RESUME:
                return Lifecycle.State.RESUMED;
            case ON_DESTROY:
                return Lifecycle.State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class NavResultSavedStateFactory extends AbstractSavedStateViewModelFactory {
        NavResultSavedStateFactory(SavedStateRegistryOwner savedStateRegistryOwner, Bundle bundle) {
            super(savedStateRegistryOwner, bundle);
        }

        @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
        protected <T extends ViewModel> T create(String str, Class<T> cls, SavedStateHandle savedStateHandle) {
            return new SavedStateViewModel(savedStateHandle);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class SavedStateViewModel extends ViewModel {
        private SavedStateHandle mHandle;

        SavedStateViewModel(SavedStateHandle savedStateHandle) {
            this.mHandle = savedStateHandle;
        }

        public SavedStateHandle getHandle() {
            return this.mHandle;
        }
    }
}
