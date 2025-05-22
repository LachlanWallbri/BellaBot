package com.p071xw.repo;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class BubbleConfigBuilder {
    boolean alwaysShowBubble;
    long alwaysShowBubbleDelay;
    long animDuration;
    boolean autoAdjustSectionMark;
    int bubbleColor;
    int bubbleTextColor;
    int bubbleTextSize;
    boolean floatType;
    boolean hideBubble;
    private BubbleSeekBar mBubbleSeekBar;
    float max;
    float min;
    float progress;
    boolean rtl;
    int secondTrackColor;
    int secondTrackSize;
    int sectionCount;
    int sectionTextColor;
    int sectionTextInterval;
    int sectionTextPosition;
    int sectionTextSize;
    boolean seekBySection;
    boolean seekStepSection;
    boolean showProgressInFloat;
    boolean showSectionMark;
    boolean showSectionText;
    boolean showThumbText;
    int thumbColor;
    int thumbRadius;
    int thumbRadiusOnDragging;
    int thumbTextColor;
    int thumbTextSize;
    boolean touchToSeek;
    int trackColor;
    int trackSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BubbleConfigBuilder(BubbleSeekBar bubbleSeekBar) {
        this.mBubbleSeekBar = bubbleSeekBar;
    }

    public void build() {
        this.mBubbleSeekBar.config(this);
    }

    public BubbleConfigBuilder min(float f) {
        this.min = f;
        this.progress = f;
        return this;
    }

    public BubbleConfigBuilder max(float f) {
        this.max = f;
        return this;
    }

    public BubbleConfigBuilder progress(float f) {
        this.progress = f;
        return this;
    }

    public BubbleConfigBuilder floatType() {
        this.floatType = true;
        return this;
    }

    public BubbleConfigBuilder trackSize(int i) {
        this.trackSize = BubbleUtils.dp2px(i);
        return this;
    }

    public BubbleConfigBuilder secondTrackSize(int i) {
        this.secondTrackSize = BubbleUtils.dp2px(i);
        return this;
    }

    public BubbleConfigBuilder thumbRadius(int i) {
        this.thumbRadius = BubbleUtils.dp2px(i);
        return this;
    }

    public BubbleConfigBuilder thumbRadiusOnDragging(int i) {
        this.thumbRadiusOnDragging = BubbleUtils.dp2px(i);
        return this;
    }

    public BubbleConfigBuilder trackColor(int i) {
        this.trackColor = i;
        this.sectionTextColor = i;
        return this;
    }

    public BubbleConfigBuilder secondTrackColor(int i) {
        this.secondTrackColor = i;
        this.thumbColor = i;
        this.thumbTextColor = i;
        this.bubbleColor = i;
        return this;
    }

    public BubbleConfigBuilder thumbColor(int i) {
        this.thumbColor = i;
        return this;
    }

    public BubbleConfigBuilder sectionCount(int i) {
        this.sectionCount = i;
        return this;
    }

    public BubbleConfigBuilder showSectionMark() {
        this.showSectionMark = true;
        return this;
    }

    public BubbleConfigBuilder autoAdjustSectionMark() {
        this.autoAdjustSectionMark = true;
        return this;
    }

    public BubbleConfigBuilder showSectionText() {
        this.showSectionText = true;
        return this;
    }

    public BubbleConfigBuilder sectionTextSize(int i) {
        this.sectionTextSize = BubbleUtils.sp2px(i);
        return this;
    }

    public BubbleConfigBuilder sectionTextColor(int i) {
        this.sectionTextColor = i;
        return this;
    }

    public BubbleConfigBuilder sectionTextPosition(int i) {
        this.sectionTextPosition = i;
        return this;
    }

    public BubbleConfigBuilder sectionTextInterval(int i) {
        this.sectionTextInterval = i;
        return this;
    }

    public BubbleConfigBuilder showThumbText() {
        this.showThumbText = true;
        return this;
    }

    public BubbleConfigBuilder thumbTextSize(int i) {
        this.thumbTextSize = BubbleUtils.sp2px(i);
        return this;
    }

    public BubbleConfigBuilder thumbTextColor(int i) {
        this.thumbTextColor = i;
        return this;
    }

    public BubbleConfigBuilder showProgressInFloat() {
        this.showProgressInFloat = true;
        return this;
    }

    public BubbleConfigBuilder animDuration(long j) {
        this.animDuration = j;
        return this;
    }

    public BubbleConfigBuilder touchToSeek() {
        this.touchToSeek = true;
        return this;
    }

    public BubbleConfigBuilder seekStepSection() {
        this.seekStepSection = true;
        return this;
    }

    public BubbleConfigBuilder seekBySection() {
        this.seekBySection = true;
        return this;
    }

    public BubbleConfigBuilder bubbleColor(int i) {
        this.bubbleColor = i;
        return this;
    }

    public BubbleConfigBuilder bubbleTextSize(int i) {
        this.bubbleTextSize = BubbleUtils.sp2px(i);
        return this;
    }

    public BubbleConfigBuilder bubbleTextColor(int i) {
        this.bubbleTextColor = i;
        return this;
    }

    public BubbleConfigBuilder alwaysShowBubble() {
        this.alwaysShowBubble = true;
        return this;
    }

    public BubbleConfigBuilder alwaysShowBubbleDelay(long j) {
        this.alwaysShowBubbleDelay = j;
        return this;
    }

    public BubbleConfigBuilder hideBubble() {
        this.hideBubble = true;
        return this;
    }

    public BubbleConfigBuilder rtl(boolean z) {
        this.rtl = z;
        return this;
    }

    public float getMin() {
        return this.min;
    }

    public float getMax() {
        return this.max;
    }

    public float getProgress() {
        return this.progress;
    }

    public boolean isFloatType() {
        return this.floatType;
    }

    public int getTrackSize() {
        return this.trackSize;
    }

    public int getSecondTrackSize() {
        return this.secondTrackSize;
    }

    public int getThumbRadius() {
        return this.thumbRadius;
    }

    public int getThumbRadiusOnDragging() {
        return this.thumbRadiusOnDragging;
    }

    public int getTrackColor() {
        return this.trackColor;
    }

    public int getSecondTrackColor() {
        return this.secondTrackColor;
    }

    public int getThumbColor() {
        return this.thumbColor;
    }

    public int getSectionCount() {
        return this.sectionCount;
    }

    public boolean isShowSectionMark() {
        return this.showSectionMark;
    }

    public boolean isAutoAdjustSectionMark() {
        return this.autoAdjustSectionMark;
    }

    public boolean isShowSectionText() {
        return this.showSectionText;
    }

    public int getSectionTextSize() {
        return this.sectionTextSize;
    }

    public int getSectionTextColor() {
        return this.sectionTextColor;
    }

    public int getSectionTextPosition() {
        return this.sectionTextPosition;
    }

    public int getSectionTextInterval() {
        return this.sectionTextInterval;
    }

    public boolean isShowThumbText() {
        return this.showThumbText;
    }

    public int getThumbTextSize() {
        return this.thumbTextSize;
    }

    public int getThumbTextColor() {
        return this.thumbTextColor;
    }

    public boolean isShowProgressInFloat() {
        return this.showProgressInFloat;
    }

    public long getAnimDuration() {
        return this.animDuration;
    }

    public boolean isTouchToSeek() {
        return this.touchToSeek;
    }

    public boolean isSeekStepSection() {
        return this.seekStepSection;
    }

    public boolean isSeekBySection() {
        return this.seekBySection;
    }

    public int getBubbleColor() {
        return this.bubbleColor;
    }

    public int getBubbleTextSize() {
        return this.bubbleTextSize;
    }

    public int getBubbleTextColor() {
        return this.bubbleTextColor;
    }

    public boolean isAlwaysShowBubble() {
        return this.alwaysShowBubble;
    }

    public long getAlwaysShowBubbleDelay() {
        return this.alwaysShowBubbleDelay;
    }

    public boolean isHideBubble() {
        return this.hideBubble;
    }

    public boolean isRtl() {
        return this.rtl;
    }
}
