package org.simpleframework.xml.transform;

/* loaded from: classes9.dex */
class CharacterTransform implements Transform<Character> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Character read(String str) throws Exception {
        if (str.length() != 1) {
            throw new InvalidFormatException("Cannot convert '%s' to a character", str);
        }
        return Character.valueOf(str.charAt(0));
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Character ch) throws Exception {
        return ch.toString();
    }
}
