package org.jetbrains.anko.support.p094v4;

import android.app.Activity;
import android.content.Context;
import android.view.ViewManager;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTabHost;
import androidx.legacy.widget.Space;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Views.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\nH\u0086\b\u001a+\u0010\b\u001a\u00020\t*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\fH\u0086\b\u001a+\u0010\b\u001a\u00020\t*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0002H\u0086\b\u001a+\u0010\b\u001a\u00020\t*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\nH\u0086\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\fH\u0086\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0002H\u0086\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\nH\u0086\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\fH\u0086\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0002H\u0086\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\nH\u0086\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\fH\u0086\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\u0086\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\nH\u0086\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\fH\u0086\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0002H\u0086\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\nH\u0086\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\fH\u0086\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0002H\u0086\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u001a\u001a\u00020\u001b*\u00020\u0002H\u0086\b\u001a+\u0010\u001a\u001a\u00020\u001b*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\nH\u0086\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\fH\u0086\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\u0002H\u0086\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010!\u001a\u00020\t*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010!\u001a\u00020\t*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010!\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010'\u001a\u00020\u001b*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010'\u001a\u00020\u001b*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010)\u001a\u00020**\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010)\u001a\u00020**\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010)\u001a\u00020**\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010)\u001a\u00020**\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\u0017\u0010)\u001a\u00020**\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086\b\u001a5\u0010)\u001a\u00020**\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010,\u001a\u00020**\u00020\nH\u0086\b\u001a+\u0010,\u001a\u00020**\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010,\u001a\u00020**\u00020\fH\u0086\b\u001a+\u0010,\u001a\u00020**\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b\u001a\r\u0010,\u001a\u00020**\u00020\u0002H\u0086\b\u001a+\u0010,\u001a\u00020**\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\b¨\u0006-"}, m3961d2 = {"contentLoadingProgressBar", "Landroidx/core/widget/ContentLoadingProgressBar;", "Landroid/view/ViewManager;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/AnkoViewDslMarker;", "", "Lkotlin/ExtensionFunctionType;", "drawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "Landroid/app/Activity;", "Lorg/jetbrains/anko/support/v4/_DrawerLayout;", "Landroid/content/Context;", "fragmentTabHost", "Landroidx/fragment/app/FragmentTabHost;", "Lorg/jetbrains/anko/support/v4/_FragmentTabHost;", "nestedScrollView", "Landroidx/core/widget/NestedScrollView;", "Lorg/jetbrains/anko/support/v4/_NestedScrollView;", "pagerTabStrip", "Landroidx/viewpager/widget/PagerTabStrip;", "pagerTitleStrip", "Landroidx/viewpager/widget/PagerTitleStrip;", "slidingPaneLayout", "Landroidx/slidingpanelayout/widget/SlidingPaneLayout;", "Lorg/jetbrains/anko/support/v4/_SlidingPaneLayout;", "space", "Landroidx/legacy/widget/Space;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "themedContentLoadingProgressBar", "theme", "", "themedDrawerLayout", "themedFragmentTabHost", "themedNestedScrollView", "themedPagerTabStrip", "themedPagerTitleStrip", "themedSlidingPaneLayout", "themedSpace", "themedSwipeRefreshLayout", "themedViewPager", "Landroidx/viewpager/widget/ViewPager;", "Lorg/jetbrains/anko/support/v4/_ViewPager;", "viewPager", "anko-support-v4_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SupportV4ViewsKt {
    public static final PagerTabStrip pagerTabStrip(ViewManager receiver$0, Function1<? super PagerTabStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(ViewManager receiver$0, int i, Function1<? super PagerTabStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip pagerTabStrip(Context receiver$0, Function1<? super PagerTabStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(Context receiver$0, int i, Function1<? super PagerTabStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip pagerTabStrip(Activity receiver$0, Function1<? super PagerTabStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(Activity receiver$0, int i, Function1<? super PagerTabStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTabStrip pagerTabStrip = invoke;
        init.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTabStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(ViewManager receiver$0, Function1<? super PagerTitleStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(ViewManager receiver$0, int i, Function1<? super PagerTitleStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(Context receiver$0, Function1<? super PagerTitleStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(Context receiver$0, int i, Function1<? super PagerTitleStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(Activity receiver$0, Function1<? super PagerTitleStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(Activity receiver$0, int i, Function1<? super PagerTitleStrip, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTitleStrip pagerTitleStrip = invoke;
        init.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTitleStrip;
    }

    public static final ContentLoadingProgressBar contentLoadingProgressBar(ViewManager receiver$0, Function1<? super ContentLoadingProgressBar, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ContentLoadingProgressBar invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        ContentLoadingProgressBar contentLoadingProgressBar = invoke;
        init.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return contentLoadingProgressBar;
    }

    public static /* synthetic */ ContentLoadingProgressBar themedContentLoadingProgressBar$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ContentLoadingProgressBar invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        ContentLoadingProgressBar contentLoadingProgressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return contentLoadingProgressBar;
    }

    public static /* synthetic */ ContentLoadingProgressBar themedContentLoadingProgressBar$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ContentLoadingProgressBar invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        ContentLoadingProgressBar contentLoadingProgressBar = invoke;
        init.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return contentLoadingProgressBar;
    }

    public static final ContentLoadingProgressBar themedContentLoadingProgressBar(ViewManager receiver$0, int i, Function1<? super ContentLoadingProgressBar, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ContentLoadingProgressBar invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        ContentLoadingProgressBar contentLoadingProgressBar = invoke;
        init.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return contentLoadingProgressBar;
    }

    public static final Space space(ViewManager receiver$0, Function1<? super Space, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        Space invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        Space space = invoke;
        init.invoke(space);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return space;
    }

    public static /* synthetic */ Space themedSpace$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Space invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        Space space = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return space;
    }

    public static /* synthetic */ Space themedSpace$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        Space invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        Space space = invoke;
        init.invoke(space);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return space;
    }

    public static final Space themedSpace(ViewManager receiver$0, int i, Function1<? super Space, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        Space invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        Space space = invoke;
        init.invoke(space);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return space;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(ViewManager receiver$0, Function1<? super SwipeRefreshLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(ViewManager receiver$0, int i, Function1<? super SwipeRefreshLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(Context receiver$0, Function1<? super SwipeRefreshLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(Context receiver$0, int i, Function1<? super SwipeRefreshLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(Activity receiver$0, Function1<? super SwipeRefreshLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(Activity receiver$0, int i, Function1<? super SwipeRefreshLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        init.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return swipeRefreshLayout;
    }

    public static final FragmentTabHost fragmentTabHost(ViewManager receiver$0, Function1<? super _FragmentTabHost, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(ViewManager receiver$0, int i, Function1<? super _FragmentTabHost, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final FragmentTabHost fragmentTabHost(Context receiver$0, Function1<? super _FragmentTabHost, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(Context receiver$0, int i, Function1<? super _FragmentTabHost, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final FragmentTabHost fragmentTabHost(Activity receiver$0, Function1<? super _FragmentTabHost, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(Activity receiver$0, int i, Function1<? super _FragmentTabHost, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final ViewPager viewPager(ViewManager receiver$0, Function1<? super _ViewPager, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final ViewPager themedViewPager(ViewManager receiver$0, int i, Function1<? super _ViewPager, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final ViewPager viewPager(Context receiver$0, Function1<? super _ViewPager, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final ViewPager themedViewPager(Context receiver$0, int i, Function1<? super _ViewPager, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final ViewPager viewPager(Activity receiver$0, Function1<? super _ViewPager, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final ViewPager themedViewPager(Activity receiver$0, int i, Function1<? super _ViewPager, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final DrawerLayout drawerLayout(ViewManager receiver$0, Function1<? super _DrawerLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final DrawerLayout themedDrawerLayout(ViewManager receiver$0, int i, Function1<? super _DrawerLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final DrawerLayout drawerLayout(Context receiver$0, Function1<? super _DrawerLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final DrawerLayout themedDrawerLayout(Context receiver$0, int i, Function1<? super _DrawerLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final DrawerLayout drawerLayout(Activity receiver$0, Function1<? super _DrawerLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final DrawerLayout themedDrawerLayout(Activity receiver$0, int i, Function1<? super _DrawerLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final NestedScrollView nestedScrollView(ViewManager receiver$0, Function1<? super _NestedScrollView, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final NestedScrollView themedNestedScrollView(ViewManager receiver$0, int i, Function1<? super _NestedScrollView, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final NestedScrollView nestedScrollView(Context receiver$0, Function1<? super _NestedScrollView, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final NestedScrollView themedNestedScrollView(Context receiver$0, int i, Function1<? super _NestedScrollView, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final NestedScrollView nestedScrollView(Activity receiver$0, Function1<? super _NestedScrollView, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final NestedScrollView themedNestedScrollView(Activity receiver$0, int i, Function1<? super _NestedScrollView, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(ViewManager receiver$0, Function1<? super _SlidingPaneLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(ViewManager receiver$0, int i, Function1<? super _SlidingPaneLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(Context receiver$0, Function1<? super _SlidingPaneLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(Context receiver$0, int i, Function1<? super _SlidingPaneLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(Activity receiver$0, Function1<? super _SlidingPaneLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(Activity receiver$0, int i, Function1<? super _SlidingPaneLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final PagerTabStrip pagerTabStrip(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip pagerTabStrip(Context receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip pagerTabStrip(Activity receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTabStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTabStrip pagerTabStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTabStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(Context receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(Activity receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        PagerTitleStrip invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        PagerTitleStrip pagerTitleStrip = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return pagerTitleStrip;
    }

    public static final ContentLoadingProgressBar contentLoadingProgressBar(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ContentLoadingProgressBar invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        ContentLoadingProgressBar contentLoadingProgressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return contentLoadingProgressBar;
    }

    public static final ContentLoadingProgressBar themedContentLoadingProgressBar(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ContentLoadingProgressBar invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        ContentLoadingProgressBar contentLoadingProgressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return contentLoadingProgressBar;
    }

    public static final Space space(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Space invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        Space space = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return space;
    }

    public static final Space themedSpace(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Space invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        Space space = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return space;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(Context receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(Activity receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SwipeRefreshLayout invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        SwipeRefreshLayout swipeRefreshLayout = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return swipeRefreshLayout;
    }

    public static final FragmentTabHost fragmentTabHost(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final FragmentTabHost fragmentTabHost(Context receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final FragmentTabHost fragmentTabHost(Activity receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _FragmentTabHost invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final ViewPager viewPager(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final ViewPager themedViewPager(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final ViewPager viewPager(Context receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final ViewPager themedViewPager(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final ViewPager viewPager(Activity receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final ViewPager themedViewPager(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _ViewPager invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final DrawerLayout drawerLayout(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final DrawerLayout themedDrawerLayout(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final DrawerLayout drawerLayout(Context receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final DrawerLayout themedDrawerLayout(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final DrawerLayout drawerLayout(Activity receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final DrawerLayout themedDrawerLayout(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _DrawerLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final NestedScrollView nestedScrollView(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final NestedScrollView themedNestedScrollView(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final NestedScrollView nestedScrollView(Context receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final NestedScrollView themedNestedScrollView(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final NestedScrollView nestedScrollView(Activity receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final NestedScrollView themedNestedScrollView(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _NestedScrollView invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(ViewManager receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(Context receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(Activity receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _SlidingPaneLayout invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }
}
