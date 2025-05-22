package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

@Root(name = "Filter")
/* loaded from: classes7.dex */
public class RuleFilter {

    @Element(name = "And", required = false)
    private AndOperator andOperator;

    @Element(name = "Prefix", required = false)
    @Convert(PrefixConverter.class)
    private String prefix;

    @Element(name = "Tag", required = false)
    private Tag tag;

    public RuleFilter(@Element(name = "And", required = false) @Nullable AndOperator andOperator, @Element(name = "Prefix", required = false) @Nullable String str, @Element(name = "Tag", required = false) @Nullable Tag tag) {
        if ((tag != null) ^ ((andOperator != null) ^ (str != null))) {
            this.andOperator = andOperator;
            this.prefix = str;
            this.tag = tag;
            return;
        }
        throw new IllegalArgumentException("Only one of And, Prefix or Tag must be set");
    }

    public RuleFilter(@Nonnull AndOperator andOperator) {
        this.andOperator = (AndOperator) Objects.requireNonNull(andOperator, "And operator must not be null");
    }

    public RuleFilter(@Nonnull String str) {
        this.prefix = (String) Objects.requireNonNull(str, "Prefix must not be null");
    }

    public RuleFilter(@Nonnull Tag tag) {
        this.tag = (Tag) Objects.requireNonNull(tag, "Tag must not be null");
    }

    public AndOperator andOperator() {
        return this.andOperator;
    }

    public String prefix() {
        return this.prefix;
    }

    public Tag tag() {
        return this.tag;
    }
}
