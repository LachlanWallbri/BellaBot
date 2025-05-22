package com.pudutech.peanut.robot_ui.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;

/* loaded from: classes5.dex */
public class ImageLoader {
    private static volatile ImageLoader sImageLoader;

    private <T extends ImageView> String checkSaveFlow(T t, String str) {
        return str;
    }

    public static ImageLoader getInstance() {
        if (sImageLoader == null) {
            synchronized (ImageLoader.class) {
                sImageLoader = new ImageLoader();
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

    public <T extends ImageView> void display(T t, Uri uri, boolean z) {
        Context context = getContext(t);
        if (context != null) {
            Glide.with(context).load(uri).skipMemoryCache(true).diskCacheStrategy(z ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL).transition(new DrawableTransitionOptions().crossFade(1000)).into(t);
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

    private <T extends ImageView> RequestBuilder init_config(T t, String str) {
        Context context = getContext(t);
        return Glide.with(context).load(checkSaveFlow(t, str)).transition(new DrawableTransitionOptions().crossFade(1000));
    }

    private <T extends ImageView> RequestBuilder init_config4bitmap(T t, Bitmap bitmap) {
        return Glide.with(getContext(t)).load(bitmap).transition(new DrawableTransitionOptions().crossFade(1000));
    }
}
