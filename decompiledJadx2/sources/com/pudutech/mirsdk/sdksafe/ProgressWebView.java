package com.pudutech.mirsdk.sdksafe;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes6.dex */
public class ProgressWebView extends WebView {
    private ProgressBar cicleProgress;
    private ProgressBar progressbar;

    public ProgressWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onProgressChanged(int i) {
        if (i == 100) {
            this.progressbar.setVisibility(8);
            this.cicleProgress.setVisibility(8);
        } else {
            if (this.progressbar.getVisibility() == 8) {
                this.progressbar.setVisibility(0);
                this.cicleProgress.setVisibility(0);
            }
            this.progressbar.setProgress(i);
        }
    }

    public void setProgressBar(ProgressBar progressBar, ProgressBar progressBar2) {
        this.progressbar = progressBar;
        this.cicleProgress = progressBar2;
    }

    public void dimissionProgressBar() {
        this.progressbar.setVisibility(8);
        this.cicleProgress.setVisibility(8);
    }
}
