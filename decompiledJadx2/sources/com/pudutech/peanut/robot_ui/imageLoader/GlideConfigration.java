package com.pudutech.peanut.robot_ui.imageLoader;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.pudutech.peanut.robot_ui.config.PathConfig;

/* loaded from: classes5.dex */
public class GlideConfigration extends AppGlideModule {
    @Override // com.bumptech.glide.module.AppGlideModule
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(Context context, Glide glide, Registry registry) {
    }

    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        glideBuilder.setDiskCache(new DiskLruCacheFactory(getMyCacheLocation(), 314572800L));
    }

    private String getMyCacheLocation() {
        return PathConfig.getImagePath();
    }
}
