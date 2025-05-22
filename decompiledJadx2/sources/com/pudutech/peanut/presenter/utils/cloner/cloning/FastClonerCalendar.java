package com.pudutech.peanut.presenter.utils.cloner.cloning;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class FastClonerCalendar implements IFastCloner {
    @Override // com.pudutech.peanut.presenter.utils.cloner.cloning.IFastCloner
    public Object clone(Object obj, IDeepCloner iDeepCloner, Map<Object, Object> map) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Calendar calendar = (Calendar) obj;
        gregorianCalendar.setTimeInMillis(calendar.getTimeInMillis());
        gregorianCalendar.setTimeZone((TimeZone) calendar.getTimeZone().clone());
        return gregorianCalendar;
    }
}
