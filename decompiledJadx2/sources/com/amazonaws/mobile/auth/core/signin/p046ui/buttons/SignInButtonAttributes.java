package com.amazonaws.mobile.auth.core.signin.p046ui.buttons;

/* loaded from: classes.dex */
public class SignInButtonAttributes {
    private int backgroundColor;
    private int backgroundColorPressed;
    private int bottomShadowColor;
    private int bottomShadowThickness;
    private int cornerRadius;
    private int defaultTextResourceId;
    private int imageIconResourceId;
    private int textColor;
    private int topShadowColor;
    private int topShadowThickness;

    public int getCornerRadius() {
        return this.cornerRadius;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getBackgroundColorPressed() {
        return this.backgroundColorPressed;
    }

    public int getTopShadowColor() {
        return this.topShadowColor;
    }

    public int getBottomShadowColor() {
        return this.bottomShadowColor;
    }

    public int getTopShadowThickness() {
        return this.topShadowThickness;
    }

    public int getBottomShadowThickness() {
        return this.bottomShadowThickness;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public int getDefaultTextResourceId() {
        return this.defaultTextResourceId;
    }

    public int getImageIconResourceId() {
        return this.imageIconResourceId;
    }

    public SignInButtonAttributes withCornerRadius(int i) {
        this.cornerRadius = i;
        return this;
    }

    public SignInButtonAttributes withBackgroundColor(int i) {
        this.backgroundColor = i;
        return this;
    }

    public SignInButtonAttributes withBackgroundColorPressed(int i) {
        this.backgroundColorPressed = i;
        return this;
    }

    public SignInButtonAttributes withTopShadowColor(int i) {
        this.topShadowColor = i;
        return this;
    }

    public SignInButtonAttributes withBottomShadowColor(int i) {
        this.bottomShadowColor = i;
        return this;
    }

    public SignInButtonAttributes withTopShadowThickness(int i) {
        this.topShadowThickness = i;
        return this;
    }

    public SignInButtonAttributes withBottomShadowThickness(int i) {
        this.bottomShadowThickness = i;
        return this;
    }

    public SignInButtonAttributes withTextColor(int i) {
        this.textColor = i;
        return this;
    }

    public SignInButtonAttributes withDefaultTextResourceId(int i) {
        this.defaultTextResourceId = i;
        return this;
    }

    public SignInButtonAttributes withImageIconResourceId(int i) {
        this.imageIconResourceId = i;
        return this;
    }
}
