package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.provider.FontsContractCompat;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TypefaceCompat {
    private static final LruCache<String, Typeface> sTypefaceCache;
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            sTypefaceCompatImpl = new TypefaceCompatApi29Impl();
        } else if (Build.VERSION.SDK_INT >= 28) {
            sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        } else if (Build.VERSION.SDK_INT >= 26) {
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        } else if (Build.VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable()) {
            sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
        } else if (Build.VERSION.SDK_INT >= 21) {
            sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        } else {
            sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
        }
        sTypefaceCache = new LruCache<>(16);
    }

    private TypefaceCompat() {
    }

    public static Typeface findFromCache(Resources resources, int i, int i2) {
        return sTypefaceCache.get(createResourceUid(resources, i, i2));
    }

    private static String createResourceUid(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface createFromResourcesFamilyXml(Context context, FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, Resources resources, int i, int i2, ResourcesCompat.FontCallback fontCallback, Handler handler, boolean z) {
        Typeface createFromFontFamilyFilesResourceEntry;
        if (familyResourceEntry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
            FontResourcesParserCompat.ProviderResourceEntry providerResourceEntry = (FontResourcesParserCompat.ProviderResourceEntry) familyResourceEntry;
            boolean z2 = false;
            if (!z ? fontCallback == null : providerResourceEntry.getFetchStrategy() == 0) {
                z2 = true;
            }
            createFromFontFamilyFilesResourceEntry = FontsContractCompat.getFontSync(context, providerResourceEntry.getRequest(), fontCallback, handler, z2, z ? providerResourceEntry.getTimeout() : -1, i2);
        } else {
            createFromFontFamilyFilesResourceEntry = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (FontResourcesParserCompat.FontFamilyFilesResourceEntry) familyResourceEntry, resources, i2);
            if (fontCallback != null) {
                if (createFromFontFamilyFilesResourceEntry != null) {
                    fontCallback.callbackSuccessAsync(createFromFontFamilyFilesResourceEntry, handler);
                } else {
                    fontCallback.callbackFailAsync(-3, handler);
                }
            }
        }
        if (createFromFontFamilyFilesResourceEntry != null) {
            sTypefaceCache.put(createResourceUid(resources, i, i2), createFromFontFamilyFilesResourceEntry);
        }
        return createFromFontFamilyFilesResourceEntry;
    }

    public static Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        Typeface createFromResourcesFontFile = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i, str, i2);
        if (createFromResourcesFontFile != null) {
            sTypefaceCache.put(createResourceUid(resources, i, i2), createFromResourcesFontFile);
        }
        return createFromResourcesFontFile;
    }

    public static Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fontInfoArr, i);
    }

    private static Typeface getBestFontFromFamily(Context context, Typeface typeface, int i) {
        FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamily = sTypefaceCompatImpl.getFontFamily(typeface);
        if (fontFamily == null) {
            return null;
        }
        return sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, fontFamily, context.getResources(), i);
    }

    public static Typeface create(Context context, Typeface typeface, int i) {
        Typeface bestFontFromFamily;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (bestFontFromFamily = getBestFontFromFamily(context, typeface, i)) == null) ? Typeface.create(typeface, i) : bestFontFromFamily;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    /* loaded from: classes.dex */
    public static class ResourcesCallbackAdapter extends FontsContractCompat.FontRequestCallback {
        private ResourcesCompat.FontCallback mFontCallback;

        public ResourcesCallbackAdapter(ResourcesCompat.FontCallback fontCallback) {
            this.mFontCallback = fontCallback;
        }

        @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
        public void onTypefaceRetrieved(Typeface typeface) {
            ResourcesCompat.FontCallback fontCallback = this.mFontCallback;
            if (fontCallback != null) {
                fontCallback.onFontRetrieved(typeface);
            }
        }

        @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
        public void onTypefaceRequestFailed(int i) {
            ResourcesCompat.FontCallback fontCallback = this.mFontCallback;
            if (fontCallback != null) {
                fontCallback.onFontRetrievalFailed(i);
            }
        }
    }
}
