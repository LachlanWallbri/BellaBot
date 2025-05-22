package org.jboss.netty.util;

/* loaded from: classes7.dex */
public interface ThreadNameDeterminer {
    public static final ThreadNameDeterminer PROPOSED = new ThreadNameDeterminer() { // from class: org.jboss.netty.util.ThreadNameDeterminer.1
        @Override // org.jboss.netty.util.ThreadNameDeterminer
        public String determineThreadName(String str, String str2) throws Exception {
            return str2;
        }
    };
    public static final ThreadNameDeterminer CURRENT = new ThreadNameDeterminer() { // from class: org.jboss.netty.util.ThreadNameDeterminer.2
        @Override // org.jboss.netty.util.ThreadNameDeterminer
        public String determineThreadName(String str, String str2) throws Exception {
            return null;
        }
    };

    String determineThreadName(String str, String str2) throws Exception;
}
