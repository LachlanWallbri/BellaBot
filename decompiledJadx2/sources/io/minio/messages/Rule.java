package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "Rule", strict = false)
/* loaded from: classes7.dex */
public class Rule {

    @ElementUnion({@Element(name = "Days", required = false, type = RetentionDurationDays.class), @Element(name = "Years", required = false, type = RetentionDurationYears.class)})
    @Path("DefaultRetention")
    private RetentionDuration duration;

    @Element(name = "Mode", required = false)
    @Path("DefaultRetention")
    private RetentionMode mode;

    public Rule(@Element(name = "Mode", required = false) RetentionMode retentionMode, @ElementUnion({@Element(name = "Days", required = false, type = RetentionDurationDays.class), @Element(name = "Years", required = false, type = RetentionDurationYears.class)}) RetentionDuration retentionDuration) {
        if (retentionMode != null && retentionDuration != null) {
            this.mode = retentionMode;
            this.duration = retentionDuration;
        } else {
            if (retentionMode == null && retentionDuration == null) {
                return;
            }
            if (retentionMode == null) {
                throw new IllegalArgumentException("mode is null");
            }
            throw new IllegalArgumentException("duration is null");
        }
    }

    public RetentionMode mode() {
        return this.mode;
    }

    public RetentionDuration duration() {
        return this.duration;
    }
}
