package com.pudutech.disinfect.baselib.imageLoader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.pudutech.disinfect.baselib.base.BaseApp;
import java.io.File;

/* loaded from: classes4.dex */
public class ImageLoader {
    private static volatile ImageLoader sImageLoader;

    public static ImageLoader getInstance() {
        if (sImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (sImageLoader == null) {
                    sImageLoader = new ImageLoader();
                }
            }
        }
        return sImageLoader;
    }

    private ImageLoader() {
    }

    private Context getContext(View view) {
        if (view != null) {
            return view.getContext();
        }
        return null;
    }

    public <T extends ImageView> void display(T t, String str) {
        init_config(t, str).into(t);
    }

    public <T extends ImageView> void displayNormal(T t, String str) {
        Glide.with(getContext(t)).load(str).into(t);
    }

    public void displayImg(Activity activity, String str, ImageView imageView) {
        Glide.with(activity).load(str).into(imageView);
    }

    public void displayImg(Context context, String str, ImageView imageView) {
        Glide.with(context).load(str).into(imageView);
    }

    public <T extends ImageView> void display(T t, Uri uri, boolean z) {
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load(uri).diskCacheStrategy(z ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL).transition(new DrawableTransitionOptions().crossFade(1000)).into(t);
        }
    }

    public <T extends ImageView> void display(T t, String str, int i, int i2, RequestListener requestListener) {
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load(str).error(i).placeholder(i2).listener(requestListener).into(t);
        }
    }

    public <T extends ImageView> void display(T t, int i) {
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load(Integer.valueOf(i)).transition(new DrawableTransitionOptions().crossFade(1000)).into(t);
        }
    }

    public <T extends ImageView> void display(T t, int i, DisplayConfig displayConfig) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(displayConfig.getId_holder_image()).error(displayConfig.getId_err_image());
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) requestOptions).transition(new DrawableTransitionOptions().crossFade(1000)).into(t);
        }
    }

    public <T extends ImageView> void display(T t, File file) {
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load(file).transition(new DrawableTransitionOptions().crossFade(1000)).into(t);
        }
    }

    public <T extends ImageView> void display(T t, Byte[] bArr) {
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load((Object) bArr).transition(new DrawableTransitionOptions().crossFade(1000)).into(t);
        }
    }

    public <T extends ImageView> void display(T t, Uri uri, float f) {
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load(uri).thumbnail(f).transition(new DrawableTransitionOptions().crossFade(1000)).into(t);
        }
    }

    public <T extends ImageView> void display(T t, String str, float f) {
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load(str).thumbnail(f).transition(new DrawableTransitionOptions().crossFade(1000)).into(t);
        }
    }

    public <T extends ImageView> void display(T t, String str, DisplayConfig displayConfig) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(displayConfig.getId_holder_image()).error(displayConfig.getId_err_image());
        init_config(t, str).apply((BaseRequestOptions<?>) requestOptions).into(t);
    }

    public <T extends ImageView> void displayCircle(T t, String str, DisplayConfig displayConfig) {
        Context context = getContext(t);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(displayConfig.getId_holder_image()).error(displayConfig.getId_err_image());
        Glide.with(context).load(str).apply((BaseRequestOptions<?>) requestOptions).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new CircleCrop())).into(t);
    }

    public <T extends ImageView> void displayCircle(T t, String str) {
        Glide.with(getContext(t)).load(str).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new CircleCrop())).into(t);
    }

    public <T extends ImageView> void displayRounded(T t, String str, int i) {
        Glide.with(getContext(t)).load(str).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(i))).into(t);
    }

    private <T extends ImageView> RequestBuilder init_config(T t, String str) {
        return Glide.with(getContext(t)).load(str).transition(new DrawableTransitionOptions().crossFade(1000));
    }

    private <T extends ImageView> RequestBuilder init_config4bitmap(T t, Bitmap bitmap) {
        return Glide.with(getContext(t)).load(bitmap).transition(new DrawableTransitionOptions().crossFade(1000));
    }

    private void guideClearMemory() {
        Glide.get(BaseApp.INSTANCE.getINSTANCE()).clearMemory();
    }

    private void displayImg(ImageView imageView, String str) {
        Glide.with(BaseApp.INSTANCE.getINSTANCE()).load(str).into(imageView);
    }
}
