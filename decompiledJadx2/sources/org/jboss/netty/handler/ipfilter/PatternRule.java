package org.jboss.netty.handler.ipfilter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class PatternRule implements IpFilterRule, Comparable<Object> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PatternRule.class);
    private Pattern ipPattern;
    private boolean isAllowRule;
    private boolean localhost;
    private Pattern namePattern;
    private final String pattern;

    public PatternRule(boolean z, String str) {
        this.isAllowRule = true;
        this.isAllowRule = z;
        this.pattern = str;
        parse(str);
    }

    public String getPattern() {
        return this.pattern;
    }

    @Override // org.jboss.netty.handler.ipfilter.IpFilterRule
    public boolean isAllowRule() {
        return this.isAllowRule;
    }

    @Override // org.jboss.netty.handler.ipfilter.IpFilterRule
    public boolean isDenyRule() {
        return !this.isAllowRule;
    }

    @Override // org.jboss.netty.handler.ipfilter.IpSet
    public boolean contains(InetAddress inetAddress) {
        if (this.localhost && isLocalhost(inetAddress)) {
            return true;
        }
        Pattern pattern = this.ipPattern;
        if (pattern != null && pattern.matcher(inetAddress.getHostAddress()).matches()) {
            return true;
        }
        Pattern pattern2 = this.namePattern;
        return pattern2 != null && pattern2.matcher(inetAddress.getHostName()).matches();
    }

    private void parse(String str) {
        if (str == null) {
            return;
        }
        String str2 = "";
        String str3 = "";
        for (String str4 : str.split(",")) {
            String trim = str4.trim();
            if (trim.equals("n:localhost")) {
                this.localhost = true;
            } else if (trim.startsWith("n:")) {
                str3 = addRule(str3, trim.substring(2));
            } else if (trim.startsWith("i:")) {
                str2 = addRule(str2, trim.substring(2));
            }
        }
        if (str2.length() != 0) {
            this.ipPattern = Pattern.compile(str2);
        }
        if (str3.length() != 0) {
            this.namePattern = Pattern.compile(str3);
        }
    }

    private static String addRule(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        if (str.length() != 0) {
            str = str + "|";
        }
        return str + "(" + str2.replaceAll("\\.", "\\\\.").replaceAll("\\*", ".*").replaceAll("\\?", ".") + ")";
    }

    private static boolean isLocalhost(InetAddress inetAddress) {
        try {
            if (inetAddress.equals(InetAddress.getLocalHost())) {
                return true;
            }
        } catch (UnknownHostException e) {
            if (logger.isInfoEnabled()) {
                logger.info("error getting ip of localhost", e);
            }
        }
        try {
            for (InetAddress inetAddress2 : InetAddress.getAllByName("127.0.0.1")) {
                if (inetAddress2.equals(inetAddress)) {
                    return true;
                }
            }
        } catch (UnknownHostException e2) {
            if (logger.isInfoEnabled()) {
                logger.info("error getting ip of localhost", e2);
            }
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == null || !(obj instanceof PatternRule)) {
            return -1;
        }
        PatternRule patternRule = (PatternRule) obj;
        if (patternRule.isAllowRule() && !this.isAllowRule) {
            return -1;
        }
        if (this.pattern == null && patternRule.pattern == null) {
            return 0;
        }
        String str = this.pattern;
        if (str != null) {
            return str.compareTo(patternRule.getPattern());
        }
        return -1;
    }
}
