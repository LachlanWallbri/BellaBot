package com.amazonaws.services.p048s3.model;

@Deprecated
/* loaded from: classes.dex */
public interface EncryptionMaterialsProvider extends EncryptionMaterialsAccessor {
    EncryptionMaterials getEncryptionMaterials();

    void refresh();
}
