package com.amazonaws.services.p048s3.model;

import java.util.Map;

/* loaded from: classes.dex */
public class StaticEncryptionMaterialsProvider implements EncryptionMaterialsProvider {
    private final EncryptionMaterials materials;

    @Override // com.amazonaws.services.p048s3.model.EncryptionMaterialsProvider
    public void refresh() {
    }

    public StaticEncryptionMaterialsProvider(EncryptionMaterials encryptionMaterials) {
        this.materials = encryptionMaterials;
    }

    @Override // com.amazonaws.services.p048s3.model.EncryptionMaterialsProvider
    public EncryptionMaterials getEncryptionMaterials() {
        return this.materials;
    }

    @Override // com.amazonaws.services.p048s3.model.EncryptionMaterialsAccessor
    public EncryptionMaterials getEncryptionMaterials(Map<String, String> map) {
        EncryptionMaterials encryptionMaterials;
        Map<String, String> materialsDescription = this.materials.getMaterialsDescription();
        if (map != null && map.equals(materialsDescription)) {
            return this.materials;
        }
        EncryptionMaterialsAccessor accessor = this.materials.getAccessor();
        if (accessor != null && (encryptionMaterials = accessor.getEncryptionMaterials(map)) != null) {
            return encryptionMaterials;
        }
        boolean z = map == null || map.size() == 0;
        boolean z2 = materialsDescription == null || materialsDescription.size() == 0;
        if (z && z2) {
            return this.materials;
        }
        return null;
    }
}
