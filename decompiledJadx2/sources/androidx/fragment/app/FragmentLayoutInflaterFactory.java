package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.C0351R;
import org.simpleframework.xml.strategy.Name;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    private static final String TAG = "FragmentManager";
    private final FragmentManager mFragmentManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentLayoutInflaterFactory(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        FragmentStateManager createOrGetFragmentStateManager;
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.mFragmentManager);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, Name.LABEL);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0351R.styleable.Fragment);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(C0351R.styleable.Fragment_android_name);
        }
        int resourceId = obtainStyledAttributes.getResourceId(C0351R.styleable.Fragment_android_id, -1);
        String string = obtainStyledAttributes.getString(C0351R.styleable.Fragment_android_tag);
        obtainStyledAttributes.recycle();
        if (attributeValue == null || !FragmentFactory.isFragmentClass(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment findFragmentById = resourceId != -1 ? this.mFragmentManager.findFragmentById(resourceId) : null;
        if (findFragmentById == null && string != null) {
            findFragmentById = this.mFragmentManager.findFragmentByTag(string);
        }
        if (findFragmentById == null && id != -1) {
            findFragmentById = this.mFragmentManager.findFragmentById(id);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + findFragmentById);
        }
        if (findFragmentById == null) {
            findFragmentById = this.mFragmentManager.getFragmentFactory().instantiate(context.getClassLoader(), attributeValue);
            findFragmentById.mFromLayout = true;
            findFragmentById.mFragmentId = resourceId != 0 ? resourceId : id;
            findFragmentById.mContainerId = id;
            findFragmentById.mTag = string;
            findFragmentById.mInLayout = true;
            findFragmentById.mFragmentManager = this.mFragmentManager;
            findFragmentById.mHost = this.mFragmentManager.getHost();
            findFragmentById.onInflate(this.mFragmentManager.getHost().getContext(), attributeSet, findFragmentById.mSavedFragmentState);
            createOrGetFragmentStateManager = this.mFragmentManager.createOrGetFragmentStateManager(findFragmentById);
            this.mFragmentManager.addFragment(findFragmentById);
        } else {
            if (findFragmentById.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
            }
            findFragmentById.mInLayout = true;
            findFragmentById.mFragmentManager = this.mFragmentManager;
            findFragmentById.mHost = this.mFragmentManager.getHost();
            findFragmentById.onInflate(this.mFragmentManager.getHost().getContext(), attributeSet, findFragmentById.mSavedFragmentState);
            createOrGetFragmentStateManager = this.mFragmentManager.createOrGetFragmentStateManager(findFragmentById);
        }
        createOrGetFragmentStateManager.moveToExpectedState();
        createOrGetFragmentStateManager.ensureInflatedView();
        if (findFragmentById.mView == null) {
            throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
        }
        if (resourceId != 0) {
            findFragmentById.mView.setId(resourceId);
        }
        if (findFragmentById.mView.getTag() == null) {
            findFragmentById.mView.setTag(string);
        }
        return findFragmentById.mView;
    }

    /* renamed from: androidx.fragment.app.FragmentLayoutInflaterFactory$1 */
    /* loaded from: classes.dex */
    class ViewOnAttachStateChangeListenerC03841 implements View.OnAttachStateChangeListener {
        final /* synthetic */ FragmentStateManager val$fragmentStateManager;

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }

        ViewOnAttachStateChangeListenerC03841(FragmentStateManager fragmentStateManager) {
            this.val$fragmentStateManager = fragmentStateManager;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            Fragment fragment = this.val$fragmentStateManager.getFragment();
            this.val$fragmentStateManager.moveToExpectedState();
            SpecialEffectsController.getOrCreateController((ViewGroup) fragment.mView.getParent(), FragmentLayoutInflaterFactory.this.mFragmentManager).forceCompleteAllOperations();
        }
    }
}
