package com.iflytek.cloud.p051ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.msc.util.Encrypter;
import com.iflytek.cloud.msc.util.FuncAdapter;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.resource.Resource;
import com.iflytek.cloud.thirdparty.C3694af;
import com.iflytek.cloud.thirdparty.C3706ar;
import com.iflytek.cloud.thirdparty.C3709au;
import com.iflytek.cloud.thirdparty.C3710av;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* renamed from: com.iflytek.cloud.ui.a */
/* loaded from: classes3.dex */
public final class ViewOnClickListenerC3748a extends C3709au implements View.OnClickListener {

    /* renamed from: a */
    public static int f3467a = 9;

    /* renamed from: d */
    private LinearLayout f3468d;

    /* renamed from: e */
    private C3710av f3469e;

    /* renamed from: f */
    private RotateAnimation f3470f;

    /* renamed from: g */
    private SpeechRecognizer f3471g;

    /* renamed from: h */
    private RecognizerDialogListener f3472h;

    /* renamed from: i */
    private long f3473i;

    /* renamed from: j */
    private RecognizerListener f3474j;

    /* renamed from: k */
    private volatile int f3475k;

    public ViewOnClickListenerC3748a(Context context, InitListener initListener) {
        super(context.getApplicationContext());
        this.f3469e = null;
        this.f3470f = null;
        this.f3473i = 0L;
        this.f3474j = new RecognizerListener() { // from class: com.iflytek.cloud.ui.a.2
            @Override // com.iflytek.cloud.RecognizerListener
            public void onBeginOfSpeech() {
            }

            @Override // com.iflytek.cloud.RecognizerListener
            public void onEvent(int i, int i2, int i3, Bundle bundle) {
            }

            @Override // com.iflytek.cloud.RecognizerListener
            public void onVolumeChanged(int i, byte[] bArr) {
                if (ViewOnClickListenerC3748a.this.f3475k != 1 || ViewOnClickListenerC3748a.this.f3469e == null) {
                    return;
                }
                ViewOnClickListenerC3748a.this.f3469e.setVolume((i + 2) / 5);
                ViewOnClickListenerC3748a.this.f3469e.invalidate();
            }

            @Override // com.iflytek.cloud.RecognizerListener
            public void onEndOfSpeech() {
                ViewOnClickListenerC3748a.this.m2293j();
            }

            @Override // com.iflytek.cloud.RecognizerListener
            public void onResult(RecognizerResult recognizerResult, boolean z) {
                if (z) {
                    ViewOnClickListenerC3748a.this.m1985f();
                }
                if (ViewOnClickListenerC3748a.this.f3472h != null) {
                    ViewOnClickListenerC3748a.this.f3472h.onResult(recognizerResult, z);
                }
            }

            @Override // com.iflytek.cloud.RecognizerListener
            public void onError(SpeechError speechError) {
                if (speechError != null && ViewOnClickListenerC3748a.this.f3087b) {
                    ViewOnClickListenerC3748a.this.m2280a(speechError);
                } else {
                    ViewOnClickListenerC3748a.this.m1985f();
                }
                if (ViewOnClickListenerC3748a.this.f3472h != null) {
                    ViewOnClickListenerC3748a.this.f3472h.onError(speechError);
                }
            }
        };
        this.f3471g = SpeechRecognizer.createRecognizer(context.getApplicationContext(), initListener);
        m2295a();
    }

    /* renamed from: a */
    public void m2295a() {
        try {
            final Context applicationContext = getContext().getApplicationContext();
            View m1971a = C3706ar.m1971a(applicationContext, "recognize", this);
            m1971a.setBackgroundDrawable(C3706ar.m1970a(applicationContext.getApplicationContext(), "voice_bg.9"));
            TextView textView = (TextView) m1971a.findViewWithTag("textlink");
            textView.getPaint().setFlags(8);
            textView.setText("语音识别能力由讯飞输入法提供");
            textView.setLinksClickable(true);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.iflytek.cloud.ui.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        Uri parse = Uri.parse("http://www.xunfei.cn/?appid=" + Encrypter.cut16MD5(C3694af.m1836a()));
                        DebugLog.LogD(parse.toString());
                        Intent intent = new Intent("android.intent.action.VIEW", parse);
                        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
                        ViewOnClickListenerC3748a.this.f3475k = 1;
                        ViewOnClickListenerC3748a.this.m2294k();
                        ViewOnClickListenerC3748a.this.f3471g.stopListening();
                        ViewOnClickListenerC3748a.this.m2292i();
                        applicationContext.getApplicationContext().startActivity(intent);
                    } catch (Exception unused) {
                        DebugLog.LogW("failed");
                    }
                }
            });
            this.f3468d = (LinearLayout) m1971a.findViewWithTag("container");
            FuncAdapter.CloseHardWareAccelerate(this);
            this.f3469e = new C3710av(applicationContext.getApplicationContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
            layoutParams.bottomMargin = 20;
            this.f3468d.addView(this.f3469e, 1, layoutParams);
            ((FrameLayout) this.f3468d.findViewWithTag("waiting")).findViewWithTag("control").setBackgroundDrawable(C3706ar.m1970a(getContext(), "waiting"));
            this.f3470f = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            this.f3470f.setRepeatCount(-1);
            this.f3470f.setInterpolator(new LinearInterpolator());
            this.f3470f.setDuration(700L);
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
    }

    public void setResultListener(RecognizerDialogListener recognizerDialogListener) {
        this.f3472h = recognizerDialogListener;
        setOnClickListener(this);
    }

    /* renamed from: a */
    public void m2297a(String str, String str2) {
        this.f3471g.setParameter(str, str2);
    }

    /* renamed from: g */
    private void m2289g() {
        DebugLog.LogD("startRecognizing");
        long j = this.f3473i;
        this.f3473i = SystemClock.elapsedRealtime();
        if (this.f3473i - j < 300) {
            return;
        }
        this.f3471g.setParameter("msc.skin", "default");
        int startListening = this.f3471g.startListening(this.f3474j);
        if (startListening != 0) {
            m2280a(new SpeechError(startListening));
        } else {
            m2292i();
        }
    }

    /* renamed from: h */
    private void m2290h() {
        LinearLayout linearLayout = this.f3468d;
        if (linearLayout != null) {
            linearLayout.destroyDrawingCache();
            this.f3468d = null;
        }
        this.f3469e = null;
        System.gc();
    }

    public void setTitle(CharSequence charSequence) {
        ((TextView) this.f3468d.findViewWithTag("title")).setText(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m2292i() {
        if (this.f3469e == null) {
            this.f3469e = new C3710av(getContext().getApplicationContext());
        }
        this.f3475k = 1;
        m2294k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m2293j() {
        try {
            ((FrameLayout) this.f3468d.findViewWithTag("waiting")).findViewWithTag("control").startAnimation(this.f3470f);
            this.f3475k = 2;
            m2294k();
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2280a(SpeechError speechError) {
        try {
            LinearLayout linearLayout = (LinearLayout) this.f3468d.findViewWithTag("error");
            m2296a((TextView) linearLayout.findViewWithTag("errtxt"), speechError);
            linearLayout.findViewWithTag("errview").setBackgroundDrawable(C3706ar.m1970a(getContext(), "warning"));
            setTag(speechError);
            this.f3475k = 3;
            m2294k();
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
    }

    /* renamed from: a */
    public void m2296a(TextView textView, SpeechError speechError) {
        String parameter = this.f3471g.getParameter("view_tips_plain");
        boolean z = parameter == null || !(parameter.equalsIgnoreCase("false") || parameter.equalsIgnoreCase("0"));
        textView.setText(Html.fromHtml(speechError.getHtmlDescription(z)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.bringToFront();
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            int length = text.length();
            Spannable spannable = (Spannable) textView.getText();
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            spannableStringBuilder.clearSpans();
            for (URLSpan uRLSpan : uRLSpanArr) {
                spannableStringBuilder.setSpan(new a(uRLSpan.getURL()), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 34);
            }
            int length2 = speechError.getHtmlDescription(false).length();
            int length3 = speechError.getHtmlDescription(true).length() - 4;
            spannableStringBuilder.setSpan(new ForegroundColorSpan(C3706ar.m1972a()[0]), 0, length2, 34);
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(C3706ar.m1974b()[0], true), 0, length2, 33);
            if (z) {
                int i = length2 + 1;
                spannableStringBuilder.setSpan(new ForegroundColorSpan(C3706ar.m1972a()[1]), i, length3 + 1, 34);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(C3706ar.m1974b()[1], true), i, length, 34);
            }
            textView.setText(spannableStringBuilder);
        }
    }

    /* renamed from: com.iflytek.cloud.ui.a$a */
    /* loaded from: classes3.dex */
    public class a extends ClickableSpan {

        /* renamed from: b */
        private String f3480b;

        public a(String str) {
            this.f3480b = str;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            try {
                Context context = view.getContext();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.f3480b));
                intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
                intent.putExtra("com.android.browser.application_id", context.getPackageName());
                context.startActivity(intent);
            } catch (Exception e) {
                DebugLog.LogE(e);
            }
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m2294k() {
        FrameLayout frameLayout = (FrameLayout) this.f3468d.findViewWithTag("waiting");
        TextView textView = (TextView) this.f3468d.findViewWithTag("title");
        LinearLayout linearLayout = (LinearLayout) this.f3468d.findViewWithTag("error");
        TextView textView2 = (TextView) frameLayout.findViewWithTag("tips");
        if (this.f3475k == 1) {
            linearLayout.setVisibility(8);
            textView.setVisibility(0);
            frameLayout.setVisibility(8);
            textView.setText(Resource.getTitle(2));
            this.f3469e.setVolume(0);
            this.f3469e.invalidate();
            this.f3469e.setVisibility(0);
            return;
        }
        if (this.f3475k == 2) {
            textView.setVisibility(8);
            this.f3469e.setVisibility(8);
            frameLayout.setVisibility(0);
            textView2.setVisibility(0);
            textView2.setText(Resource.getTitle(3));
            return;
        }
        if (this.f3475k == 3) {
            textView.setVisibility(8);
            this.f3469e.setVisibility(8);
            frameLayout.setVisibility(8);
            linearLayout.setVisibility(0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i = this.f3475k;
        if (i == 1) {
            this.f3471g.stopListening();
            m2293j();
        } else {
            if (i != 3) {
                return;
            }
            if (view.getTag() != null && ((SpeechError) view.getTag()).getErrorCode() == 20001) {
                m1984e();
            } else {
                m2289g();
            }
        }
    }

    @Override // com.iflytek.cloud.thirdparty.C3709au
    /* renamed from: b */
    public void mo1981b() {
        super.mo1981b();
        m2289g();
    }

    @Override // com.iflytek.cloud.thirdparty.C3709au
    /* renamed from: c */
    public void mo1982c() {
        if (this.f3471g.isListening()) {
            this.f3471g.cancel();
        }
        super.mo1982c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.C3709au
    /* renamed from: d */
    public boolean mo1983d() {
        if (!super.mo1983d()) {
            return false;
        }
        m2290h();
        return this.f3471g.destroy();
    }
}
