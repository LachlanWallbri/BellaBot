package org.checkerframework.checker.signedness;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/* loaded from: classes9.dex */
public class SignednessUtilExtra {
    private SignednessUtilExtra() {
        throw new Error("Do not instantiate");
    }

    public static int dimensionUnsignedWidth(Dimension dimension) {
        return dimension.width;
    }

    public static int dimensionUnsignedHeight(Dimension dimension) {
        return dimension.height;
    }

    public static void setUnsignedRGB(BufferedImage bufferedImage, int i, int i2, int i3, int i4, int[] iArr, int i5, int i6) {
        bufferedImage.setRGB(i, i2, i3, i4, iArr, i5, i6);
    }

    public static int[] getUnsignedRGB(BufferedImage bufferedImage, int i, int i2, int i3, int i4, int[] iArr, int i5, int i6) {
        return bufferedImage.getRGB(i, i2, i3, i4, iArr, i5, i6);
    }
}
