package com.pudutech.mirsdk.sdksafe;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: LoginActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "loginTime", "", "node", "webView", "Landroid/webkit/WebView;", "getWebView$sdksafe_release", "()Landroid/webkit/WebView;", "setWebView$sdksafe_release", "(Landroid/webkit/WebView;)V", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "LoginResult", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class LoginActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int loginTime;
    public WebView webView;
    private final String TAG = "SDKSafe";
    private String node = "";

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes6.dex
     */
    /* compiled from: LoginActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/LoginActivity$LoginResult;", "", "(Ljava/lang/String;I)V", "MatchUrl", "LoginError", "AuthFailed", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum LoginResult {
        MatchUrl,
        LoginError,
        AuthFailed
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes6.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoginResult.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LoginResult.MatchUrl.ordinal()] = 1;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final WebView getWebView$sdksafe_release() {
        WebView webView = this.webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        }
        return webView;
    }

    public final void setWebView$sdksafe_release(WebView webView) {
        Intrinsics.checkParameterIsNotNull(webView, "<set-?>");
        this.webView = webView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Pdlog.m3273d(this.TAG, "onCreate");
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) {
            Window window = getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "window");
            window.setNavigationBarColor(getResources().getColor(C5309R.color.navigationBarColor));
        } else if (Build.VERSION.SDK_INT >= 23) {
            Window window2 = getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window2, "window");
            window2.setNavigationBarColor(getResources().getColor(C5309R.color.navigationBarColor, getTheme()));
        }
        setContentView(C5309R.layout.sdk_login_activity);
        String stringExtra = getIntent().getStringExtra("node");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.node = stringExtra;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        SDKSafe.INSTANCE.addLoginActivity$sdksafe_release(this);
        View findViewById = findViewById(C5309R.id.login_webview);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<WebView>(R.id.login_webview)");
        WebView webView = (WebView) findViewById;
        this.webView = webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        }
        WebSettings settings = webView.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings, "webView.settings");
        settings.setJavaScriptEnabled(true);
        WebView webView2 = this.webView;
        if (webView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        }
        webView2.setWebViewClient(new LoginActivity$onCreate$1(this));
        WebView webView3 = this.webView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        }
        webView3.setWebChromeClient(new WebChromeClient() { // from class: com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$2
            @Override // android.webkit.WebChromeClient
            public void onPermissionRequest(PermissionRequest request) {
                Intrinsics.checkParameterIsNotNull(request, "request");
                request.grant(request.getResources());
            }
        });
        WebView webView4 = this.webView;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        }
        WebSettings settings2 = webView4.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings2, "webView.settings");
        settings2.setMixedContentMode(0);
        WebView webView5 = this.webView;
        if (webView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        }
        WebSettings settings3 = webView5.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings3, "webView.settings");
        settings3.setDomStorageEnabled(true);
        WebView webView6 = this.webView;
        if (webView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        }
        webView6.loadUrl(SDKSafe.INSTANCE.getPage$sdksafe_release());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        Pdlog.m3273d(this.TAG, "onResume");
        if (this.loginTime == 1) {
            Pdlog.m3273d(this.TAG, "login time over 1 times");
            WebView webView = this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            }
            webView.destroy();
            SDKSafe.INSTANCE.finishActivity$sdksafe_release(this.node);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LoginActivity$onResume$1(this, null), 3, null);
        }
        super.onResume();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (item.getItemId() == 16908332) {
            SDKSafe.INSTANCE.finishActivity$sdksafe_release(this.node);
            WebView webView = this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            }
            webView.destroy();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        WebView webView = this.webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        }
        webView.destroy();
        SDKSafe.INSTANCE.finishActivity$sdksafe_release(this.node);
        super.onBackPressed();
    }
}
