package com.pudutech.disinfect.baselib.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlideUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/GlideUtils;", "", "()V", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GlideUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static RequestOptions defaultOptions;

    /* compiled from: GlideUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\fJ\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\fJ\u0016\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\fJ4\u0010\u0019\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/GlideUtils$Companion;", "", "()V", "defaultOptions", "Lcom/bumptech/glide/request/RequestOptions;", "guideClearDiskCache", "", "mContext", "Landroid/content/Context;", "guideClearMemory", "loadGif", "imageView", "Landroid/widget/ImageView;", "id", "", "loadImageView", "path", "", "mImageView", "loadImageViewFormFile", "file", "Ljava/io/File;", "loadImageViewFormUri", "uri", "Landroid/net/Uri;", "preloadImage", "context", "preWidth", "preHeight", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void loadImageView(String path, ImageView mImageView) {
            Intrinsics.checkParameterIsNotNull(mImageView, "mImageView");
            Glide.with(mImageView).load(path).apply((BaseRequestOptions<?>) GlideUtils.defaultOptions).into(mImageView);
        }

        public final void loadImageViewFormUri(Uri uri, ImageView mImageView) {
            Intrinsics.checkParameterIsNotNull(uri, "uri");
            Intrinsics.checkParameterIsNotNull(mImageView, "mImageView");
            Glide.with(mImageView).load(uri).apply((BaseRequestOptions<?>) GlideUtils.defaultOptions).into(mImageView);
        }

        public final void loadImageViewFormFile(File file, ImageView mImageView) {
            Intrinsics.checkParameterIsNotNull(file, "file");
            Intrinsics.checkParameterIsNotNull(mImageView, "mImageView");
            Glide.with(mImageView).load(file).into(mImageView);
        }

        public final void preloadImage(Context context, String path, ImageView imageView, int preWidth, int preHeight) {
            if (imageView == null) {
                Intrinsics.throwNpe();
            }
            Glide.with(imageView).load(path).apply((BaseRequestOptions<?>) new RequestOptions().centerCrop().override(preWidth, preHeight).diskCacheStrategy(DiskCacheStrategy.RESOURCE)).preload(preWidth, preHeight);
        }

        public final void guideClearDiskCache(Context mContext) {
            if (mContext == null) {
                Intrinsics.throwNpe();
            }
            Glide.get(mContext).clearDiskCache();
        }

        public final void guideClearMemory(Context mContext) {
            if (mContext == null) {
                Intrinsics.throwNpe();
            }
            Glide.get(mContext).clearMemory();
        }

        public final void loadGif(ImageView imageView, int id) {
            Intrinsics.checkParameterIsNotNull(imageView, "imageView");
            Glide.with(imageView.getContext()).asGif().load(Integer.valueOf(id)).apply((BaseRequestOptions<?>) GlideUtils.defaultOptions).into(imageView);
        }
    }

    static {
        RequestOptions diskCacheStrategy = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Intrinsics.checkExpressionValueIsNotNull(diskCacheStrategy, "RequestOptions()\n       …skCacheStrategy.RESOURCE)");
        defaultOptions = diskCacheStrategy;
    }
}
