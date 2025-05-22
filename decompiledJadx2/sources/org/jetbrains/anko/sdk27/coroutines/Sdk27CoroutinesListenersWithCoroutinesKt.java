package org.jetbrains.anko.sdk27.coroutines;

import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.media.MediaPlayer;
import android.media.tv.TvView;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.widget.AbsListView;
import android.widget.ActionMenuView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toolbar;
import android.widget.VideoView;
import android.widget.ZoomControls;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.http.HttpStatus;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¶\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a{\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062U\u0010\u0007\u001aQ\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a-\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001a}\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172U\u0010\u0007\u001aQ\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001aq\u0010\u001c\u001a\u00020\u0001*\u00020\u001d2\b\b\u0002\u0010\u0003\u001a\u00020\u00042S\u0010\u0007\u001aO\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010 \u001aq\u0010\u001c\u001a\u00020\u0001*\u00020!2\b\b\u0002\u0010\u0003\u001a\u00020\u00042S\u0010\u0007\u001aO\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010!¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b($\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010%\u001a¾\u0001\u0010&\u001a\u00020\u0001*\u00020'2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172\u0095\u0001\u0010\u0007\u001a\u0090\u0001\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010'¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b()\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110,¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(-\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0(¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010.\u001a\\\u0010/\u001a\u00020\u0001*\u0002002\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u000100¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(2\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u00103\u001a\\\u00104\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u00105\u001aO\u00106\u001a\u00020\u0001*\u0002072\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f08¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u00109\u001a\\\u0010:\u001a\u00020\u0001*\u00020;2\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010<¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(=\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010>\u001af\u0010?\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010@\u001a\u008a\u0001\u0010A\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042l\u0010\u0007\u001ah\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010C¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(D\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010E¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(F\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0B¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010G\u001a\u009b\u0001\u0010H\u001a\u00020\u0001*\u00020I2\b\b\u0002\u0010\u0003\u001a\u00020\u00042}\u0010\u0007\u001ay\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010I¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(M\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0J¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010N\u001a\u009b\u0001\u0010O\u001a\u00020\u0001*\u00020P2\b\b\u0002\u0010\u0003\u001a\u00020\u00042}\u0010\u0007\u001ay\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010P¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(Q\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(M\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0J¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010R\u001aE\u0010S\u001a\u00020\u0001*\u00020T2\b\b\u0002\u0010\u0003\u001a\u00020\u00042'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f08¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010U\u001ay\u0010V\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172Q\u0010\u0007\u001aM\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110W¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001aE\u0010X\u001a\u00020\u0001*\u00020Y2\b\b\u0002\u0010\u0003\u001a\u00020\u00042'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f08¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010Z\u001aE\u0010[\u001a\u00020\u0001*\u00020Y2\b\b\u0002\u0010\u0003\u001a\u00020\u00042'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f08¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010Z\u001a-\u0010\\\u001a\u00020\u0001*\u00020Y2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020]\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001a\u0092\u0001\u0010^\u001a\u00020\u0001*\u00020_2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172j\u0010\u0007\u001af\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010_¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(`\u0012\u0015\u0012\u0013\u0018\u00010a¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0B¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010b\u001a\u0090\u0001\u0010c\u001a\u00020\u0001*\u00020;2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172h\u0010\u0007\u001ad\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010<¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(=\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(d\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0B¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010f\u001ao\u0010g\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042Q\u0010\u0007\u001aM\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(h\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010i\u001ay\u0010j\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172Q\u0010\u0007\u001aM\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a-\u0010k\u001a\u00020\u0001*\u00020l2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020m\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001as\u0010n\u001a\u00020\u0001*\u00020l2\b\b\u0002\u0010\u0003\u001a\u00020\u00042U\u0010\u0007\u001aQ\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010l¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(o\u0012\u0015\u0012\u0013\u0018\u00010p¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(q\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010r\u001a-\u0010s\u001a\u00020\u0001*\u00020l2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020t\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001a§\u0001\u0010u\u001a\u00020\u0001*\u00020'2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172\u007f\u0010\u0007\u001a{\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010'¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b()\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110,¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(-\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0J¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010v\u001aZ\u0010w\u001a\u00020\u0001*\u00020'2\b\b\u0002\u0010\u0003\u001a\u00020\u00042<\u0010\u0007\u001a8\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(*\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010x\u001aZ\u0010y\u001a\u00020\u0001*\u00020'2\b\b\u0002\u0010\u0003\u001a\u00020\u00042<\u0010\u0007\u001a8\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(*\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010x\u001a-\u0010z\u001a\u00020\u0001*\u00020{2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020|\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001ay\u0010}\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172Q\u0010\u0007\u001aM\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001av\u0010~\u001a\u00020\u0001*\u00020\u007f2\b\b\u0002\u0010\u0003\u001a\u00020\u00042W\u0010\u0007\u001aS\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0016\u0012\u0014\u0018\u00010\u007f¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0080\u0001\u0012\u0016\u0012\u0014\u0018\u00010\u0002¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0081\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010\u0082\u0001\u001a\u0091\u0001\u0010\u0083\u0001\u001a\u00020\u0001*\u00020;2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172h\u0010\u0007\u001ad\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010<¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(=\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(d\u0012\u0013\u0012\u00110#¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0B¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010f\u001a´\u0001\u0010\u0084\u0001\u001a\u00020\u0001*\f\u0012\u0007\b\u0001\u0012\u00030\u0086\u00010\u0085\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0089\u0001\u0010\u0007\u001a\u0084\u0001\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u001b\u0012\u0019\u0012\u0002\b\u0003\u0018\u00010\u0085\u0001¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0087\u0001\u0012\u0016\u0012\u0014\u0018\u00010\u0002¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0088\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0089\u0001\u0012\u0014\u0012\u00120,¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u008a\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0J¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010\u008b\u0001\u001a¾\u0001\u0010\u008c\u0001\u001a\u00020\u0001*\f\u0012\u0007\b\u0001\u0012\u00030\u0086\u00010\u0085\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172\u0089\u0001\u0010\u0007\u001a\u0084\u0001\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u001b\u0012\u0019\u0012\u0002\b\u0003\u0018\u00010\u0085\u0001¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0087\u0001\u0012\u0016\u0012\u0014\u0018\u00010\u0002¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0088\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0089\u0001\u0012\u0014\u0012\u00120,¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u008a\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0J¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010\u008d\u0001\u001a9\u0010\u008e\u0001\u001a\u00020\u0001*\f\u0012\u0007\b\u0001\u0012\u00030\u0086\u00010\u0085\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0013\u001a\u0014\u0012\u0005\u0012\u00030\u008f\u0001\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001a\u0093\u0001\u0010\u0090\u0001\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172i\u0010\u0007\u001ae\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0091\u0001\u0012\u0015\u0012\u0013\u0018\u00010a¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0B¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010\u0092\u0001\u001a\u0091\u0002\u0010\u0093\u0001\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042ð\u0001\u0010\u0007\u001aë\u0001\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0095\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0096\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0097\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0098\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u0099\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u009a\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u009b\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u009c\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0094\u0001¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010\u009d\u0001\u001ag\u0010\u009e\u0001\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010@\u001ak\u0010\u009f\u0001\u001a\u00020\u0001*\u00030 \u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172@\u0010\u0007\u001a<\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0017\u0012\u0015\u0018\u00010¡\u0001¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(¢\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010£\u0001\u001ak\u0010\u009f\u0001\u001a\u00020\u0001*\u00030¤\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172@\u0010\u0007\u001a<\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0017\u0012\u0015\u0018\u00010¡\u0001¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(¢\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010¥\u0001\u001a]\u0010¦\u0001\u001a\u00020\u0001*\u00020;2\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010<¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(=\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010>\u001aq\u0010§\u0001\u001a\u00020\u0001*\u0002072\b\b\u0002\u0010\u0003\u001a\u00020\u00042Q\u0010\u0007\u001aM\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(h\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010¨\u0001\u001a/\u0010©\u0001\u001a\u00020\u0001*\u0002072\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0013\u001a\u0014\u0012\u0005\u0012\u00030ª\u0001\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001a\u008e\u0001\u0010«\u0001\u001a\u00020\u0001*\u00030¬\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042m\u0010\u0007\u001ai\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0017\u0012\u0015\u0018\u00010¬\u0001¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(\u00ad\u0001\u0012\u0015\u0012\u00130®\u0001¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(¯\u0001\u0012\u0014\u0012\u00120\u0017¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(°\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0B¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010±\u0001\u001av\u0010²\u0001\u001a\u00020\u0001*\u00030³\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042U\u0010\u0007\u001aQ\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0016\u0012\u0014\u0018\u00010³\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0018\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(´\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010µ\u0001\u001a¸\u0001\u0010¶\u0001\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0097\u0001\u0010\u0007\u001a\u0092\u0001\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(·\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(¸\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(¹\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(º\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0(¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010»\u0001\u001a0\u0010¼\u0001\u001a\u00020\u0001*\u00030½\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0013\u001a\u0014\u0012\u0005\u0012\u00030¾\u0001\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001a^\u0010¿\u0001\u001a\u00020\u0001*\u0002072\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010À\u0001\u001a0\u0010Á\u0001\u001a\u00020\u0001*\u00030Â\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0013\u001a\u0014\u0012\u0005\u0012\u00030Ã\u0001\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001a/\u0010Ä\u0001\u001a\u00020\u0001*\u0002072\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0013\u001a\u0014\u0012\u0005\u0012\u00030Å\u0001\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u001a\\\u0010Æ\u0001\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042=\u0010\u0007\u001a9\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(Ç\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u00105\u001aa\u0010È\u0001\u001a\u00020\u0001*\u00030É\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042@\u0010\u0007\u001a<\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0017\u0012\u0015\u0018\u00010Ê\u0001¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(Ë\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010Ì\u0001\u001a\u008c\u0001\u0010Í\u0001\u001a\u00020\u0001*\u00030Î\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042k\u0010\u0007\u001ag\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0016\u0012\u0014\u0018\u00010Î\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0018\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(Ï\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(Ð\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0B¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010Ñ\u0001\u001az\u0010Ò\u0001\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172Q\u0010\u0007\u001aM\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\b¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001aj\u0010Ó\u0001\u001a\u00020\u0001*\u00030Ô\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00172?\u0010\u0007\u001a;\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0016\u0012\u0014\u0018\u00010Õ\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010Ö\u0001\u001a\u008d\u0001\u0010×\u0001\u001a\u00020\u0001*\u00030³\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042l\u0010\u0007\u001ah\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0017\u0012\u0015\u0018\u00010³\u0001¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(Ø\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(Ù\u0001\u0012\u0014\u0012\u00120#¢\u0006\r\b\n\u0012\t\b\u000b\u0012\u0005\b\b(Ú\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0B¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010Û\u0001\u001a_\u0010Ü\u0001\u001a\u00020\u0001*\u00030Ý\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010Þ\u0001\u001a_\u0010ß\u0001\u001a\u00020\u0001*\u00030Ý\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042>\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f01¢\u0006\u0002\b\u0010ø\u0001\u0000¢\u0006\u0003\u0010Þ\u0001\u001a/\u0010à\u0001\u001a\u00020\u0001*\u00020_2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0013\u001a\u0014\u0012\u0005\u0012\u00030á\u0001\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006â\u0001"}, m3961d2 = {"onApplyWindowInsets", "", "Landroid/view/View;", "context", "Lkotlin/coroutines/CoroutineContext;", "returnValue", "Landroid/view/WindowInsets;", "handler", "Lkotlin/Function4;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/ParameterName;", "name", "v", "insets", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;Landroid/view/WindowInsets;Lkotlin/jvm/functions/Function4;)V", "onAttachStateChangeListener", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/sdk27/coroutines/__View_OnAttachStateChangeListener;", "onCapturedPointer", "", "view", "Landroid/view/MotionEvent;", "event", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function4;)V", "onCheckedChange", "Landroid/widget/CompoundButton;", "buttonView", "isChecked", "(Landroid/widget/CompoundButton;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "Landroid/widget/RadioGroup;", MapElement.Key.GROUP, "", "checkedId", "(Landroid/widget/RadioGroup;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "onChildClick", "Landroid/widget/ExpandableListView;", "Lkotlin/Function7;", "parent", "groupPosition", "childPosition", "", "id", "(Landroid/widget/ExpandableListView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function7;)V", "onChronometerTick", "Landroid/widget/Chronometer;", "Lkotlin/Function3;", "chronometer", "(Landroid/widget/Chronometer;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onClick", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onClose", "Landroid/widget/SearchView;", "Lkotlin/Function2;", "(Landroid/widget/SearchView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function2;)V", "onCompletion", "Landroid/widget/VideoView;", "Landroid/media/MediaPlayer;", "mp", "(Landroid/widget/VideoView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onContextClick", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function3;)V", "onCreateContextMenu", "Lkotlin/Function5;", "Landroid/view/ContextMenu;", "menu", "Landroid/view/ContextMenu$ContextMenuInfo;", "menuInfo", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function5;)V", "onDateChange", "Landroid/widget/CalendarView;", "Lkotlin/Function6;", "year", "month", "dayOfMonth", "(Landroid/widget/CalendarView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function6;)V", "onDateChanged", "Landroid/widget/DatePicker;", "monthOfYear", "(Landroid/widget/DatePicker;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function6;)V", "onDismiss", "Landroid/widget/AutoCompleteTextView;", "(Landroid/widget/AutoCompleteTextView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)V", "onDrag", "Landroid/view/DragEvent;", "onDrawerClose", "Landroid/widget/SlidingDrawer;", "(Landroid/widget/SlidingDrawer;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)V", "onDrawerOpen", "onDrawerScrollListener", "Lorg/jetbrains/anko/sdk27/coroutines/__SlidingDrawer_OnDrawerScrollListener;", "onEditorAction", "Landroid/widget/TextView;", "actionId", "Landroid/view/KeyEvent;", "(Landroid/widget/TextView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function5;)V", "onError", "what", "extra", "(Landroid/widget/VideoView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function5;)V", "onFocusChange", "hasFocus", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "onGenericMotion", "onGestureListener", "Landroid/gesture/GestureOverlayView;", "Lorg/jetbrains/anko/sdk27/coroutines/__GestureOverlayView_OnGestureListener;", "onGesturePerformed", "overlay", "Landroid/gesture/Gesture;", "gesture", "(Landroid/gesture/GestureOverlayView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "onGesturingListener", "Lorg/jetbrains/anko/sdk27/coroutines/__GestureOverlayView_OnGesturingListener;", "onGroupClick", "(Landroid/widget/ExpandableListView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function6;)V", "onGroupCollapse", "(Landroid/widget/ExpandableListView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onGroupExpand", "onHierarchyChangeListener", "Landroid/view/ViewGroup;", "Lorg/jetbrains/anko/sdk27/coroutines/__ViewGroup_OnHierarchyChangeListener;", "onHover", "onInflate", "Landroid/view/ViewStub;", "stub", "inflated", "(Landroid/view/ViewStub;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "onInfo", "onItemClick", "Landroid/widget/AdapterView;", "Landroid/widget/Adapter;", "p0", "p1", "p2", "p3", "(Landroid/widget/AdapterView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function6;)V", "onItemLongClick", "(Landroid/widget/AdapterView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function6;)V", "onItemSelectedListener", "Lorg/jetbrains/anko/sdk27/coroutines/__AdapterView_OnItemSelectedListener;", "onKey", "keyCode", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function5;)V", "onLayoutChange", "Lkotlin/Function11;", "left", "top", "right", "bottom", "oldLeft", "oldTop", "oldRight", "oldBottom", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function11;)V", "onLongClick", "onMenuItemClick", "Landroid/widget/ActionMenuView;", "Landroid/view/MenuItem;", "item", "(Landroid/widget/ActionMenuView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function3;)V", "Landroid/widget/Toolbar;", "(Landroid/widget/Toolbar;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function3;)V", "onPrepared", "onQueryTextFocusChange", "(Landroid/widget/SearchView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "onQueryTextListener", "Lorg/jetbrains/anko/sdk27/coroutines/__SearchView_OnQueryTextListener;", "onRatingBarChange", "Landroid/widget/RatingBar;", "ratingBar", "", "rating", "fromUser", "(Landroid/widget/RatingBar;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function5;)V", "onScroll", "Landroid/widget/NumberPicker;", "scrollState", "(Landroid/widget/NumberPicker;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function4;)V", "onScrollChange", "scrollX", "scrollY", "oldScrollX", "oldScrollY", "(Landroid/view/View;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function7;)V", "onScrollListener", "Landroid/widget/AbsListView;", "Lorg/jetbrains/anko/sdk27/coroutines/__AbsListView_OnScrollListener;", "onSearchClick", "(Landroid/widget/SearchView;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onSeekBarChangeListener", "Landroid/widget/SeekBar;", "Lorg/jetbrains/anko/sdk27/coroutines/__SeekBar_OnSeekBarChangeListener;", "onSuggestionListener", "Lorg/jetbrains/anko/sdk27/coroutines/__SearchView_OnSuggestionListener;", "onSystemUiVisibilityChange", "visibility", "onTabChanged", "Landroid/widget/TabHost;", "", "tabId", "(Landroid/widget/TabHost;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onTimeChanged", "Landroid/widget/TimePicker;", "hourOfDay", "minute", "(Landroid/widget/TimePicker;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function5;)V", "onTouch", "onUnhandledInputEvent", "Landroid/media/tv/TvView;", "Landroid/view/InputEvent;", "(Landroid/media/tv/TvView;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function3;)V", "onValueChanged", "picker", "oldVal", "newVal", "(Landroid/widget/NumberPicker;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function5;)V", "onZoomInClick", "Landroid/widget/ZoomControls;", "(Landroid/widget/ZoomControls;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "onZoomOutClick", "textChangedListener", "Lorg/jetbrains/anko/sdk27/coroutines/__TextWatcher;", "anko-sdk27-coroutines_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class Sdk27CoroutinesListenersWithCoroutinesKt {
    public static /* synthetic */ void onLayoutChange$default(View view, CoroutineContext coroutineContext, Function11 function11, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onLayoutChange(view, coroutineContext, function11);
    }

    public static final void onLayoutChange(View receiver$0, final CoroutineContext context, final Function11<? super CoroutineScope, ? super View, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onLayoutChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onLayoutChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {18, 20}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onLayoutChange$1$1 */
            /* loaded from: classes9.dex */
            static final class C88021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $bottom;
                final /* synthetic */ int $left;
                final /* synthetic */ int $oldBottom;
                final /* synthetic */ int $oldLeft;
                final /* synthetic */ int $oldRight;
                final /* synthetic */ int $oldTop;
                final /* synthetic */ int $right;
                final /* synthetic */ int $top;

                /* renamed from: $v */
                final /* synthetic */ View f10138$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10139p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88021(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Continuation continuation) {
                    super(2, continuation);
                    this.f10138$v = view;
                    this.$left = i;
                    this.$top = i2;
                    this.$right = i3;
                    this.$bottom = i4;
                    this.$oldLeft = i5;
                    this.$oldTop = i6;
                    this.$oldRight = i7;
                    this.$oldBottom = i8;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88021 c88021 = new C88021(this.f10138$v, this.$left, this.$top, this.$right, this.$bottom, this.$oldLeft, this.$oldTop, this.$oldRight, this.$oldBottom, completion);
                    c88021.f10139p$ = (CoroutineScope) obj;
                    return c88021;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10139p$;
                        Function11 function11 = handler;
                        View view = this.f10138$v;
                        Integer boxInt = Boxing.boxInt(this.$left);
                        Integer boxInt2 = Boxing.boxInt(this.$top);
                        Integer boxInt3 = Boxing.boxInt(this.$right);
                        Integer boxInt4 = Boxing.boxInt(this.$bottom);
                        Integer boxInt5 = Boxing.boxInt(this.$oldLeft);
                        Integer boxInt6 = Boxing.boxInt(this.$oldTop);
                        Integer boxInt7 = Boxing.boxInt(this.$oldRight);
                        Integer boxInt8 = Boxing.boxInt(this.$oldBottom);
                        this.label = 1;
                        if (function11.invoke(coroutineScope, view, boxInt, boxInt2, boxInt3, boxInt4, boxInt5, boxInt6, boxInt7, boxInt8, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88021(view, i, i2, i3, i4, i5, i6, i7, i8, null));
            }
        });
    }

    public static /* synthetic */ void onAttachStateChangeListener$default(View view, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onAttachStateChangeListener(view, coroutineContext, function1);
    }

    public static final void onAttachStateChangeListener(View receiver$0, CoroutineContext context, Function1<? super __View_OnAttachStateChangeListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __View_OnAttachStateChangeListener __view_onattachstatechangelistener = new __View_OnAttachStateChangeListener(context);
        init.invoke(__view_onattachstatechangelistener);
        receiver$0.addOnAttachStateChangeListener(__view_onattachstatechangelistener);
    }

    public static /* synthetic */ void textChangedListener$default(TextView textView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        textChangedListener(textView, coroutineContext, function1);
    }

    public static final void textChangedListener(TextView receiver$0, CoroutineContext context, Function1<? super __TextWatcher, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __TextWatcher __textwatcher = new __TextWatcher(context);
        init.invoke(__textwatcher);
        receiver$0.addTextChangedListener(__textwatcher);
    }

    public static /* synthetic */ void onGestureListener$default(GestureOverlayView gestureOverlayView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onGestureListener(gestureOverlayView, coroutineContext, function1);
    }

    public static final void onGestureListener(GestureOverlayView receiver$0, CoroutineContext context, Function1<? super __GestureOverlayView_OnGestureListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __GestureOverlayView_OnGestureListener __gestureoverlayview_ongesturelistener = new __GestureOverlayView_OnGestureListener(context);
        init.invoke(__gestureoverlayview_ongesturelistener);
        receiver$0.addOnGestureListener(__gestureoverlayview_ongesturelistener);
    }

    public static /* synthetic */ void onGesturePerformed$default(GestureOverlayView gestureOverlayView, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onGesturePerformed(gestureOverlayView, coroutineContext, function4);
    }

    public static final void onGesturePerformed(GestureOverlayView receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super GestureOverlayView, ? super Gesture, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGesturePerformed$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onGesturePerformed$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {HttpStatus.SC_PARTIAL_CONTENT, 208}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGesturePerformed$1$1 */
            /* loaded from: classes9.dex */
            static final class C87921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Gesture $gesture;
                final /* synthetic */ GestureOverlayView $overlay;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10125p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87921(GestureOverlayView gestureOverlayView, Gesture gesture, Continuation continuation) {
                    super(2, continuation);
                    this.$overlay = gestureOverlayView;
                    this.$gesture = gesture;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87921 c87921 = new C87921(this.$overlay, this.$gesture, completion);
                    c87921.f10125p$ = (CoroutineScope) obj;
                    return c87921;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10125p$;
                        Function4 function4 = handler;
                        GestureOverlayView gestureOverlayView = this.$overlay;
                        Gesture gesture = this.$gesture;
                        this.label = 1;
                        if (function4.invoke(coroutineScope, gestureOverlayView, gesture, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.gesture.GestureOverlayView.OnGesturePerformedListener
            public final void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87921(gestureOverlayView, gesture, null));
            }
        });
    }

    public static /* synthetic */ void onGesturingListener$default(GestureOverlayView gestureOverlayView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onGesturingListener(gestureOverlayView, coroutineContext, function1);
    }

    public static final void onGesturingListener(GestureOverlayView receiver$0, CoroutineContext context, Function1<? super __GestureOverlayView_OnGesturingListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __GestureOverlayView_OnGesturingListener __gestureoverlayview_ongesturinglistener = new __GestureOverlayView_OnGesturingListener(context);
        init.invoke(__gestureoverlayview_ongesturinglistener);
        receiver$0.addOnGesturingListener(__gestureoverlayview_ongesturinglistener);
    }

    public static /* synthetic */ void onUnhandledInputEvent$default(TvView tvView, CoroutineContext coroutineContext, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onUnhandledInputEvent(tvView, coroutineContext, z, function3);
    }

    public static final void onUnhandledInputEvent(TvView receiver$0, final CoroutineContext context, final boolean z, final Function3<? super CoroutineScope, ? super InputEvent, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnUnhandledInputEventListener(new TvView.OnUnhandledInputEventListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onUnhandledInputEvent$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onUnhandledInputEvent$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {261, TarConstants.VERSION_OFFSET}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onUnhandledInputEvent$1$1 */
            /* loaded from: classes9.dex */
            static final class C88161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ InputEvent $event;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10158p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88161(InputEvent inputEvent, Continuation continuation) {
                    super(2, continuation);
                    this.$event = inputEvent;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88161 c88161 = new C88161(this.$event, completion);
                    c88161.f10158p$ = (CoroutineScope) obj;
                    return c88161;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10158p$;
                        Function3 function3 = handler;
                        InputEvent inputEvent = this.$event;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, inputEvent, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.media.tv.TvView.OnUnhandledInputEventListener
            public final boolean onUnhandledInputEvent(InputEvent inputEvent) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88161(inputEvent, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onApplyWindowInsets$default(View view, CoroutineContext coroutineContext, WindowInsets windowInsets, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onApplyWindowInsets(view, coroutineContext, windowInsets, function4);
    }

    public static final void onApplyWindowInsets(View receiver$0, final CoroutineContext context, final WindowInsets returnValue, final Function4<? super CoroutineScope, ? super View, ? super WindowInsets, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(returnValue, "returnValue");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onApplyWindowInsets$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onApplyWindowInsets$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {274, 276}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onApplyWindowInsets$1$1 */
            /* loaded from: classes9.dex */
            static final class C87751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ WindowInsets $insets;

                /* renamed from: $v */
                final /* synthetic */ View f10099$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10100p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87751(View view, WindowInsets windowInsets, Continuation continuation) {
                    super(2, continuation);
                    this.f10099$v = view;
                    this.$insets = windowInsets;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87751 c87751 = new C87751(this.f10099$v, this.$insets, completion);
                    c87751.f10100p$ = (CoroutineScope) obj;
                    return c87751;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10100p$;
                        Function4 function4 = handler;
                        View view = this.f10099$v;
                        WindowInsets windowInsets = this.$insets;
                        this.label = 1;
                        if (function4.invoke(coroutineScope, view, windowInsets, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87751(view, windowInsets, null));
                return returnValue;
            }
        });
    }

    public static /* synthetic */ void onCapturedPointer$default(View view, CoroutineContext coroutineContext, boolean z, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onCapturedPointer(view, coroutineContext, z, function4);
    }

    public static final void onCapturedPointer(View receiver$0, final CoroutineContext context, final boolean z, final Function4<? super CoroutineScope, ? super View, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnCapturedPointerListener(new View.OnCapturedPointerListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCapturedPointer$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onCapturedPointer$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {287, 289}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCapturedPointer$1$1 */
            /* loaded from: classes9.dex */
            static final class C87761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MotionEvent $event;
                final /* synthetic */ View $view;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10101p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87761(View view, MotionEvent motionEvent, Continuation continuation) {
                    super(2, continuation);
                    this.$view = view;
                    this.$event = motionEvent;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87761 c87761 = new C87761(this.$view, this.$event, completion);
                    c87761.f10101p$ = (CoroutineScope) obj;
                    return c87761;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10101p$;
                        Function4 function4 = handler;
                        View view = this.$view;
                        MotionEvent motionEvent = this.$event;
                        this.label = 1;
                        if (function4.invoke(coroutineScope, view, motionEvent, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnCapturedPointerListener
            public final boolean onCapturedPointer(View view, MotionEvent motionEvent) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87761(view, motionEvent, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onClick$default(View view, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onClick(view, coroutineContext, function3);
    }

    public static final void onClick(View receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnClickListener(new View.OnClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {299, 301}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C87811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $v */
                final /* synthetic */ View f10107$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10108p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87811(View view, Continuation continuation) {
                    super(2, continuation);
                    this.f10107$v = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87811 c87811 = new C87811(this.f10107$v, completion);
                    c87811.f10108p$ = (CoroutineScope) obj;
                    return c87811;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10108p$;
                        Function3 function3 = handler;
                        View view = this.f10107$v;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87811(view, null));
            }
        });
    }

    public static /* synthetic */ void onContextClick$default(View view, CoroutineContext coroutineContext, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onContextClick(view, coroutineContext, z, function3);
    }

    public static final void onContextClick(View receiver$0, final CoroutineContext context, final boolean z, final Function3<? super CoroutineScope, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnContextClickListener(new View.OnContextClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onContextClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onContextClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {311, 313}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onContextClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C87831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $v */
                final /* synthetic */ View f10110$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10111p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87831(View view, Continuation continuation) {
                    super(2, continuation);
                    this.f10110$v = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87831 c87831 = new C87831(this.f10110$v, completion);
                    c87831.f10111p$ = (CoroutineScope) obj;
                    return c87831;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10111p$;
                        Function3 function3 = handler;
                        View view = this.f10110$v;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnContextClickListener
            public final boolean onContextClick(View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87831(view, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onCreateContextMenu$default(View view, CoroutineContext coroutineContext, Function5 function5, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onCreateContextMenu(view, coroutineContext, function5);
    }

    public static final void onCreateContextMenu(View receiver$0, final CoroutineContext context, final Function5<? super CoroutineScope, ? super ContextMenu, ? super View, ? super ContextMenu.ContextMenuInfo, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCreateContextMenu$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onCreateContextMenu$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {323, 325}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCreateContextMenu$1$1 */
            /* loaded from: classes9.dex */
            static final class C87841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ ContextMenu $menu;
                final /* synthetic */ ContextMenu.ContextMenuInfo $menuInfo;

                /* renamed from: $v */
                final /* synthetic */ View f10112$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10113p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87841(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo, Continuation continuation) {
                    super(2, continuation);
                    this.$menu = contextMenu;
                    this.f10112$v = view;
                    this.$menuInfo = contextMenuInfo;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87841 c87841 = new C87841(this.$menu, this.f10112$v, this.$menuInfo, completion);
                    c87841.f10113p$ = (CoroutineScope) obj;
                    return c87841;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10113p$;
                        Function5 function5 = handler;
                        ContextMenu contextMenu = this.$menu;
                        View view = this.f10112$v;
                        ContextMenu.ContextMenuInfo contextMenuInfo = this.$menuInfo;
                        this.label = 1;
                        if (function5.invoke(coroutineScope, contextMenu, view, contextMenuInfo, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnCreateContextMenuListener
            public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87841(contextMenu, view, contextMenuInfo, null));
            }
        });
    }

    public static /* synthetic */ void onDrag$default(View view, CoroutineContext coroutineContext, boolean z, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onDrag(view, coroutineContext, z, function4);
    }

    public static final void onDrag(View receiver$0, final CoroutineContext context, final boolean z, final Function4<? super CoroutineScope, ? super View, ? super DragEvent, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnDragListener(new View.OnDragListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDrag$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onDrag$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {335, 337}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDrag$1$1 */
            /* loaded from: classes9.dex */
            static final class C87871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ DragEvent $event;

                /* renamed from: $v */
                final /* synthetic */ View f10116$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10117p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87871(View view, DragEvent dragEvent, Continuation continuation) {
                    super(2, continuation);
                    this.f10116$v = view;
                    this.$event = dragEvent;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87871 c87871 = new C87871(this.f10116$v, this.$event, completion);
                    c87871.f10117p$ = (CoroutineScope) obj;
                    return c87871;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10117p$;
                        Function4 function4 = handler;
                        View v = this.f10116$v;
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        DragEvent event = this.$event;
                        Intrinsics.checkExpressionValueIsNotNull(event, "event");
                        this.label = 1;
                        if (function4.invoke(coroutineScope, v, event, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnDragListener
            public final boolean onDrag(View view, DragEvent dragEvent) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87871(view, dragEvent, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onFocusChange$default(View view, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onFocusChange(view, coroutineContext, function4);
    }

    public static final void onFocusChange(View receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super View, ? super Boolean, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onFocusChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onFocusChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {347, 349}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onFocusChange$1$1 */
            /* loaded from: classes9.dex */
            static final class C87901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $hasFocus;

                /* renamed from: $v */
                final /* synthetic */ View f10121$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10122p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87901(View view, boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.f10121$v = view;
                    this.$hasFocus = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87901 c87901 = new C87901(this.f10121$v, this.$hasFocus, completion);
                    c87901.f10122p$ = (CoroutineScope) obj;
                    return c87901;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10122p$;
                        Function4 function4 = handler;
                        View v = this.f10121$v;
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        Boolean boxBoolean = Boxing.boxBoolean(this.$hasFocus);
                        this.label = 1;
                        if (function4.invoke(coroutineScope, v, boxBoolean, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87901(view, z, null));
            }
        });
    }

    public static /* synthetic */ void onGenericMotion$default(View view, CoroutineContext coroutineContext, boolean z, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onGenericMotion(view, coroutineContext, z, function4);
    }

    public static final void onGenericMotion(View receiver$0, final CoroutineContext context, final boolean z, final Function4<? super CoroutineScope, ? super View, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnGenericMotionListener(new View.OnGenericMotionListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGenericMotion$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onGenericMotion$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {359, 361}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGenericMotion$1$1 */
            /* loaded from: classes9.dex */
            static final class C87911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MotionEvent $event;

                /* renamed from: $v */
                final /* synthetic */ View f10123$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10124p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87911(View view, MotionEvent motionEvent, Continuation continuation) {
                    super(2, continuation);
                    this.f10123$v = view;
                    this.$event = motionEvent;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87911 c87911 = new C87911(this.f10123$v, this.$event, completion);
                    c87911.f10124p$ = (CoroutineScope) obj;
                    return c87911;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10124p$;
                        Function4 function4 = handler;
                        View v = this.f10123$v;
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        MotionEvent event = this.$event;
                        Intrinsics.checkExpressionValueIsNotNull(event, "event");
                        this.label = 1;
                        if (function4.invoke(coroutineScope, v, event, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnGenericMotionListener
            public final boolean onGenericMotion(View view, MotionEvent motionEvent) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87911(view, motionEvent, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onHover$default(View view, CoroutineContext coroutineContext, boolean z, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onHover(view, coroutineContext, z, function4);
    }

    public static final void onHover(View receiver$0, final CoroutineContext context, final boolean z, final Function4<? super CoroutineScope, ? super View, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnHoverListener(new View.OnHoverListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onHover$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onHover$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {372, 374}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onHover$1$1 */
            /* loaded from: classes9.dex */
            static final class C87961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MotionEvent $event;

                /* renamed from: $v */
                final /* synthetic */ View f10130$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10131p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87961(View view, MotionEvent motionEvent, Continuation continuation) {
                    super(2, continuation);
                    this.f10130$v = view;
                    this.$event = motionEvent;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87961 c87961 = new C87961(this.f10130$v, this.$event, completion);
                    c87961.f10131p$ = (CoroutineScope) obj;
                    return c87961;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10131p$;
                        Function4 function4 = handler;
                        View v = this.f10130$v;
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        MotionEvent event = this.$event;
                        Intrinsics.checkExpressionValueIsNotNull(event, "event");
                        this.label = 1;
                        if (function4.invoke(coroutineScope, v, event, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnHoverListener
            public final boolean onHover(View view, MotionEvent motionEvent) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87961(view, motionEvent, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onKey$default(View view, CoroutineContext coroutineContext, boolean z, Function5 function5, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onKey(view, coroutineContext, z, function5);
    }

    public static final void onKey(View receiver$0, final CoroutineContext context, final boolean z, final Function5<? super CoroutineScope, ? super View, ? super Integer, ? super KeyEvent, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnKeyListener(new View.OnKeyListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onKey$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onKey$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {385, 387}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onKey$1$1 */
            /* loaded from: classes9.dex */
            static final class C88011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ KeyEvent $event;
                final /* synthetic */ int $keyCode;

                /* renamed from: $v */
                final /* synthetic */ View f10136$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10137p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88011(View view, int i, KeyEvent keyEvent, Continuation continuation) {
                    super(2, continuation);
                    this.f10136$v = view;
                    this.$keyCode = i;
                    this.$event = keyEvent;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88011 c88011 = new C88011(this.f10136$v, this.$keyCode, this.$event, completion);
                    c88011.f10137p$ = (CoroutineScope) obj;
                    return c88011;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10137p$;
                        Function5 function5 = handler;
                        View v = this.f10136$v;
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        Integer boxInt = Boxing.boxInt(this.$keyCode);
                        KeyEvent keyEvent = this.$event;
                        this.label = 1;
                        if (function5.invoke(coroutineScope, v, boxInt, keyEvent, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88011(view, i, keyEvent, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onLongClick$default(View view, CoroutineContext coroutineContext, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onLongClick(view, coroutineContext, z, function3);
    }

    public static final void onLongClick(View receiver$0, final CoroutineContext context, final boolean z, final Function3<? super CoroutineScope, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onLongClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onLongClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {398, 400}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onLongClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C88031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $v */
                final /* synthetic */ View f10140$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10141p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88031(View view, Continuation continuation) {
                    super(2, continuation);
                    this.f10140$v = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88031 c88031 = new C88031(this.f10140$v, completion);
                    c88031.f10141p$ = (CoroutineScope) obj;
                    return c88031;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10141p$;
                        Function3 function3 = handler;
                        View view = this.f10140$v;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88031(view, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onScrollChange$default(View view, CoroutineContext coroutineContext, Function7 function7, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onScrollChange(view, coroutineContext, function7);
    }

    public static final void onScrollChange(View receiver$0, final CoroutineContext context, final Function7<? super CoroutineScope, ? super View, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onScrollChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onScrollChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {HttpStatus.SC_GONE, 412}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onScrollChange$1$1 */
            /* loaded from: classes9.dex */
            static final class C88101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $oldScrollX;
                final /* synthetic */ int $oldScrollY;
                final /* synthetic */ int $scrollX;
                final /* synthetic */ int $scrollY;

                /* renamed from: $v */
                final /* synthetic */ View f10149$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10150p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88101(View view, int i, int i2, int i3, int i4, Continuation continuation) {
                    super(2, continuation);
                    this.f10149$v = view;
                    this.$scrollX = i;
                    this.$scrollY = i2;
                    this.$oldScrollX = i3;
                    this.$oldScrollY = i4;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88101 c88101 = new C88101(this.f10149$v, this.$scrollX, this.$scrollY, this.$oldScrollX, this.$oldScrollY, completion);
                    c88101.f10150p$ = (CoroutineScope) obj;
                    return c88101;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10150p$;
                        Function7 function7 = handler;
                        View view = this.f10149$v;
                        Integer boxInt = Boxing.boxInt(this.$scrollX);
                        Integer boxInt2 = Boxing.boxInt(this.$scrollY);
                        Integer boxInt3 = Boxing.boxInt(this.$oldScrollX);
                        Integer boxInt4 = Boxing.boxInt(this.$oldScrollY);
                        this.label = 1;
                        if (function7.invoke(coroutineScope, view, boxInt, boxInt2, boxInt3, boxInt4, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88101(view, i, i2, i3, i4, null));
            }
        });
    }

    public static /* synthetic */ void onSystemUiVisibilityChange$default(View view, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onSystemUiVisibilityChange(view, coroutineContext, function3);
    }

    public static final void onSystemUiVisibilityChange(View receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onSystemUiVisibilityChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onSystemUiVisibilityChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {TypedValues.Cycle.TYPE_WAVE_SHAPE, 423}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onSystemUiVisibilityChange$1$1, reason: invalid class name */
            /* loaded from: classes9.dex */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $visibility;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10153p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(int i, Continuation continuation) {
                    super(2, continuation);
                    this.$visibility = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$visibility, completion);
                    anonymousClass1.f10153p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10153p$;
                        Function3 function3 = handler;
                        Integer boxInt = Boxing.boxInt(this.$visibility);
                        this.label = 1;
                        if (function3.invoke(coroutineScope, boxInt, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new AnonymousClass1(i, null));
            }
        });
    }

    public static /* synthetic */ void onTouch$default(View view, CoroutineContext coroutineContext, boolean z, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onTouch(view, coroutineContext, z, function4);
    }

    public static final void onTouch(View receiver$0, final CoroutineContext context, final boolean z, final Function4<? super CoroutineScope, ? super View, ? super MotionEvent, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnTouchListener(new View.OnTouchListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onTouch$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onTouch$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {433, 435}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onTouch$1$1 */
            /* loaded from: classes9.dex */
            static final class C88151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MotionEvent $event;

                /* renamed from: $v */
                final /* synthetic */ View f10156$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10157p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88151(View view, MotionEvent motionEvent, Continuation continuation) {
                    super(2, continuation);
                    this.f10156$v = view;
                    this.$event = motionEvent;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88151 c88151 = new C88151(this.f10156$v, this.$event, completion);
                    c88151.f10157p$ = (CoroutineScope) obj;
                    return c88151;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88151) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10157p$;
                        Function4 function4 = handler;
                        View v = this.f10156$v;
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        MotionEvent event = this.$event;
                        Intrinsics.checkExpressionValueIsNotNull(event, "event");
                        this.label = 1;
                        if (function4.invoke(coroutineScope, v, event, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88151(view, motionEvent, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onHierarchyChangeListener$default(ViewGroup viewGroup, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onHierarchyChangeListener(viewGroup, coroutineContext, function1);
    }

    public static final void onHierarchyChangeListener(ViewGroup receiver$0, CoroutineContext context, Function1<? super __ViewGroup_OnHierarchyChangeListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __ViewGroup_OnHierarchyChangeListener __viewgroup_onhierarchychangelistener = new __ViewGroup_OnHierarchyChangeListener(context);
        init.invoke(__viewgroup_onhierarchychangelistener);
        receiver$0.setOnHierarchyChangeListener(__viewgroup_onhierarchychangelistener);
    }

    public static /* synthetic */ void onInflate$default(ViewStub viewStub, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onInflate(viewStub, coroutineContext, function4);
    }

    public static final void onInflate(ViewStub receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super ViewStub, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnInflateListener(new ViewStub.OnInflateListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onInflate$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onInflate$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {488, 490}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onInflate$1$1 */
            /* loaded from: classes9.dex */
            static final class C87971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ View $inflated;
                final /* synthetic */ ViewStub $stub;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10132p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87971(ViewStub viewStub, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$stub = viewStub;
                    this.$inflated = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87971 c87971 = new C87971(this.$stub, this.$inflated, completion);
                    c87971.f10132p$ = (CoroutineScope) obj;
                    return c87971;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10132p$;
                        Function4 function4 = handler;
                        ViewStub viewStub = this.$stub;
                        View view = this.$inflated;
                        this.label = 1;
                        if (function4.invoke(coroutineScope, viewStub, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.ViewStub.OnInflateListener
            public final void onInflate(ViewStub viewStub, View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87971(viewStub, view, null));
            }
        });
    }

    public static /* synthetic */ void onScrollListener$default(AbsListView absListView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onScrollListener(absListView, coroutineContext, function1);
    }

    public static final void onScrollListener(AbsListView receiver$0, CoroutineContext context, Function1<? super __AbsListView_OnScrollListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __AbsListView_OnScrollListener __abslistview_onscrolllistener = new __AbsListView_OnScrollListener(context);
        init.invoke(__abslistview_onscrolllistener);
        receiver$0.setOnScrollListener(__abslistview_onscrolllistener);
    }

    public static /* synthetic */ void onMenuItemClick$default(ActionMenuView actionMenuView, CoroutineContext coroutineContext, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onMenuItemClick(actionMenuView, coroutineContext, z, (Function3<? super CoroutineScope, ? super MenuItem, ? super Continuation<? super Unit>, ? extends Object>) function3);
    }

    public static final void onMenuItemClick(ActionMenuView receiver$0, final CoroutineContext context, final boolean z, final Function3<? super CoroutineScope, ? super MenuItem, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onMenuItemClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onMenuItemClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {543, 545}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onMenuItemClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C88041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MenuItem $item;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10142p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88041(MenuItem menuItem, Continuation continuation) {
                    super(2, continuation);
                    this.$item = menuItem;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88041 c88041 = new C88041(this.$item, completion);
                    c88041.f10142p$ = (CoroutineScope) obj;
                    return c88041;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10142p$;
                        Function3 function3 = handler;
                        MenuItem menuItem = this.$item;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, menuItem, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.ActionMenuView.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88041(menuItem, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onItemClick$default(AdapterView adapterView, CoroutineContext coroutineContext, Function6 function6, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onItemClick(adapterView, coroutineContext, function6);
    }

    public static final void onItemClick(AdapterView<? extends Adapter> receiver$0, final CoroutineContext context, final Function6<? super CoroutineScope, ? super AdapterView<?>, ? super View, ? super Integer, ? super Long, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onItemClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onItemClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {555, 557}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onItemClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C87991 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ AdapterView $p0;
                final /* synthetic */ View $p1;
                final /* synthetic */ int $p2;
                final /* synthetic */ long $p3;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10134p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87991(AdapterView adapterView, View view, int i, long j, Continuation continuation) {
                    super(2, continuation);
                    this.$p0 = adapterView;
                    this.$p1 = view;
                    this.$p2 = i;
                    this.$p3 = j;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87991 c87991 = new C87991(this.$p0, this.$p1, this.$p2, this.$p3, completion);
                    c87991.f10134p$ = (CoroutineScope) obj;
                    return c87991;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87991) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10134p$;
                        Function6 function6 = handler;
                        AdapterView adapterView = this.$p0;
                        View view = this.$p1;
                        Integer boxInt = Boxing.boxInt(this.$p2);
                        Long boxLong = Boxing.boxLong(this.$p3);
                        this.label = 1;
                        if (function6.invoke(coroutineScope, adapterView, view, boxInt, boxLong, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87991(adapterView, view, i, j, null));
            }
        });
    }

    public static /* synthetic */ void onItemLongClick$default(AdapterView adapterView, CoroutineContext coroutineContext, boolean z, Function6 function6, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onItemLongClick(adapterView, coroutineContext, z, function6);
    }

    public static final void onItemLongClick(AdapterView<? extends Adapter> receiver$0, final CoroutineContext context, final boolean z, final Function6<? super CoroutineScope, ? super AdapterView<?>, ? super View, ? super Integer, ? super Long, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onItemLongClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onItemLongClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {567, 569}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onItemLongClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C88001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ AdapterView $p0;
                final /* synthetic */ View $p1;
                final /* synthetic */ int $p2;
                final /* synthetic */ long $p3;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10135p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88001(AdapterView adapterView, View view, int i, long j, Continuation continuation) {
                    super(2, continuation);
                    this.$p0 = adapterView;
                    this.$p1 = view;
                    this.$p2 = i;
                    this.$p3 = j;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88001 c88001 = new C88001(this.$p0, this.$p1, this.$p2, this.$p3, completion);
                    c88001.f10135p$ = (CoroutineScope) obj;
                    return c88001;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10135p$;
                        Function6 function6 = handler;
                        AdapterView adapterView = this.$p0;
                        View view = this.$p1;
                        Integer boxInt = Boxing.boxInt(this.$p2);
                        Long boxLong = Boxing.boxLong(this.$p3);
                        this.label = 1;
                        if (function6.invoke(coroutineScope, adapterView, view, boxInt, boxLong, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.AdapterView.OnItemLongClickListener
            public final boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88001(adapterView, view, i, j, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onItemSelectedListener$default(AdapterView adapterView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onItemSelectedListener(adapterView, coroutineContext, function1);
    }

    public static final void onItemSelectedListener(AdapterView<? extends Adapter> receiver$0, CoroutineContext context, Function1<? super __AdapterView_OnItemSelectedListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __AdapterView_OnItemSelectedListener __adapterview_onitemselectedlistener = new __AdapterView_OnItemSelectedListener(context);
        init.invoke(__adapterview_onitemselectedlistener);
        receiver$0.setOnItemSelectedListener(__adapterview_onitemselectedlistener);
    }

    public static /* synthetic */ void onDismiss$default(AutoCompleteTextView autoCompleteTextView, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onDismiss(autoCompleteTextView, coroutineContext, function2);
    }

    public static final void onDismiss(AutoCompleteTextView receiver$0, final CoroutineContext context, final Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDismiss$1
            @Override // android.widget.AutoCompleteTextView.OnDismissListener
            public final void onDismiss() {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, handler);
            }
        });
    }

    public static /* synthetic */ void onDateChange$default(CalendarView calendarView, CoroutineContext coroutineContext, Function6 function6, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onDateChange(calendarView, coroutineContext, function6);
    }

    public static final void onDateChange(CalendarView receiver$0, final CoroutineContext context, final Function6<? super CoroutineScope, ? super CalendarView, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnDateChangeListener(new CalendarView.OnDateChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDateChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onDateChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {631, 633}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDateChange$1$1 */
            /* loaded from: classes9.dex */
            static final class C87851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $dayOfMonth;
                final /* synthetic */ int $month;
                final /* synthetic */ CalendarView $view;
                final /* synthetic */ int $year;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10114p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87851(CalendarView calendarView, int i, int i2, int i3, Continuation continuation) {
                    super(2, continuation);
                    this.$view = calendarView;
                    this.$year = i;
                    this.$month = i2;
                    this.$dayOfMonth = i3;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87851 c87851 = new C87851(this.$view, this.$year, this.$month, this.$dayOfMonth, completion);
                    c87851.f10114p$ = (CoroutineScope) obj;
                    return c87851;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10114p$;
                        Function6 function6 = handler;
                        CalendarView calendarView = this.$view;
                        Integer boxInt = Boxing.boxInt(this.$year);
                        Integer boxInt2 = Boxing.boxInt(this.$month);
                        Integer boxInt3 = Boxing.boxInt(this.$dayOfMonth);
                        this.label = 1;
                        if (function6.invoke(coroutineScope, calendarView, boxInt, boxInt2, boxInt3, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.CalendarView.OnDateChangeListener
            public final void onSelectedDayChange(CalendarView calendarView, int i, int i2, int i3) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87851(calendarView, i, i2, i3, null));
            }
        });
    }

    public static /* synthetic */ void onChronometerTick$default(Chronometer chronometer, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onChronometerTick(chronometer, coroutineContext, function3);
    }

    public static final void onChronometerTick(Chronometer receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super Chronometer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onChronometerTick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onChronometerTick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {642, 644}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onChronometerTick$1$1 */
            /* loaded from: classes9.dex */
            static final class C87801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Chronometer $chronometer;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10106p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87801(Chronometer chronometer, Continuation continuation) {
                    super(2, continuation);
                    this.$chronometer = chronometer;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87801 c87801 = new C87801(this.$chronometer, completion);
                    c87801.f10106p$ = (CoroutineScope) obj;
                    return c87801;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10106p$;
                        Function3 function3 = handler;
                        Chronometer chronometer = this.$chronometer;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, chronometer, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.Chronometer.OnChronometerTickListener
            public final void onChronometerTick(Chronometer chronometer) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87801(chronometer, null));
            }
        });
    }

    public static /* synthetic */ void onCheckedChange$default(CompoundButton compoundButton, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onCheckedChange(compoundButton, coroutineContext, (Function4<? super CoroutineScope, ? super CompoundButton, ? super Boolean, ? super Continuation<? super Unit>, ? extends Object>) function4);
    }

    public static final void onCheckedChange(CompoundButton receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super CompoundButton, ? super Boolean, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCheckedChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onCheckedChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {653, 655}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCheckedChange$1$1 */
            /* loaded from: classes9.dex */
            static final class C87771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CompoundButton $buttonView;
                final /* synthetic */ boolean $isChecked;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10102p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87771(CompoundButton compoundButton, boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.$buttonView = compoundButton;
                    this.$isChecked = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87771 c87771 = new C87771(this.$buttonView, this.$isChecked, completion);
                    c87771.f10102p$ = (CoroutineScope) obj;
                    return c87771;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10102p$;
                        Function4 function4 = handler;
                        CompoundButton compoundButton = this.$buttonView;
                        Boolean boxBoolean = Boxing.boxBoolean(this.$isChecked);
                        this.label = 1;
                        if (function4.invoke(coroutineScope, compoundButton, boxBoolean, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87771(compoundButton, z, null));
            }
        });
    }

    public static /* synthetic */ void onDateChanged$default(DatePicker datePicker, CoroutineContext coroutineContext, Function6 function6, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onDateChanged(datePicker, coroutineContext, function6);
    }

    public static final void onDateChanged(DatePicker receiver$0, final CoroutineContext context, final Function6<? super CoroutineScope, ? super DatePicker, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnDateChangedListener(new DatePicker.OnDateChangedListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDateChanged$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onDateChanged$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {664, 666}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDateChanged$1$1 */
            /* loaded from: classes9.dex */
            static final class C87861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $dayOfMonth;
                final /* synthetic */ int $monthOfYear;
                final /* synthetic */ DatePicker $view;
                final /* synthetic */ int $year;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10115p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87861(DatePicker datePicker, int i, int i2, int i3, Continuation continuation) {
                    super(2, continuation);
                    this.$view = datePicker;
                    this.$year = i;
                    this.$monthOfYear = i2;
                    this.$dayOfMonth = i3;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87861 c87861 = new C87861(this.$view, this.$year, this.$monthOfYear, this.$dayOfMonth, completion);
                    c87861.f10115p$ = (CoroutineScope) obj;
                    return c87861;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10115p$;
                        Function6 function6 = handler;
                        DatePicker datePicker = this.$view;
                        Integer boxInt = Boxing.boxInt(this.$year);
                        Integer boxInt2 = Boxing.boxInt(this.$monthOfYear);
                        Integer boxInt3 = Boxing.boxInt(this.$dayOfMonth);
                        this.label = 1;
                        if (function6.invoke(coroutineScope, datePicker, boxInt, boxInt2, boxInt3, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.DatePicker.OnDateChangedListener
            public final void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87861(datePicker, i, i2, i3, null));
            }
        });
    }

    public static /* synthetic */ void onChildClick$default(ExpandableListView expandableListView, CoroutineContext coroutineContext, boolean z, Function7 function7, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onChildClick(expandableListView, coroutineContext, z, function7);
    }

    public static final void onChildClick(ExpandableListView receiver$0, final CoroutineContext context, final boolean z, final Function7<? super CoroutineScope, ? super ExpandableListView, ? super View, ? super Integer, ? super Integer, ? super Long, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onChildClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onChildClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {676, 678}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onChildClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C87791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $childPosition;
                final /* synthetic */ int $groupPosition;
                final /* synthetic */ long $id;
                final /* synthetic */ ExpandableListView $parent;

                /* renamed from: $v */
                final /* synthetic */ View f10104$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10105p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87791(ExpandableListView expandableListView, View view, int i, int i2, long j, Continuation continuation) {
                    super(2, continuation);
                    this.$parent = expandableListView;
                    this.f10104$v = view;
                    this.$groupPosition = i;
                    this.$childPosition = i2;
                    this.$id = j;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87791 c87791 = new C87791(this.$parent, this.f10104$v, this.$groupPosition, this.$childPosition, this.$id, completion);
                    c87791.f10105p$ = (CoroutineScope) obj;
                    return c87791;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10105p$;
                        Function7 function7 = handler;
                        ExpandableListView expandableListView = this.$parent;
                        View view = this.f10104$v;
                        Integer boxInt = Boxing.boxInt(this.$groupPosition);
                        Integer boxInt2 = Boxing.boxInt(this.$childPosition);
                        Long boxLong = Boxing.boxLong(this.$id);
                        this.label = 1;
                        if (function7.invoke(coroutineScope, expandableListView, view, boxInt, boxInt2, boxLong, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.ExpandableListView.OnChildClickListener
            public final boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87791(expandableListView, view, i, i2, j, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onGroupClick$default(ExpandableListView expandableListView, CoroutineContext coroutineContext, boolean z, Function6 function6, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onGroupClick(expandableListView, coroutineContext, z, function6);
    }

    public static final void onGroupClick(ExpandableListView receiver$0, final CoroutineContext context, final boolean z, final Function6<? super CoroutineScope, ? super ExpandableListView, ? super View, ? super Integer, ? super Long, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGroupClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onGroupClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {689, 691}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGroupClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C87931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $groupPosition;
                final /* synthetic */ long $id;
                final /* synthetic */ ExpandableListView $parent;

                /* renamed from: $v */
                final /* synthetic */ View f10126$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10127p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87931(ExpandableListView expandableListView, View view, int i, long j, Continuation continuation) {
                    super(2, continuation);
                    this.$parent = expandableListView;
                    this.f10126$v = view;
                    this.$groupPosition = i;
                    this.$id = j;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87931 c87931 = new C87931(this.$parent, this.f10126$v, this.$groupPosition, this.$id, completion);
                    c87931.f10127p$ = (CoroutineScope) obj;
                    return c87931;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10127p$;
                        Function6 function6 = handler;
                        ExpandableListView expandableListView = this.$parent;
                        View view = this.f10126$v;
                        Integer boxInt = Boxing.boxInt(this.$groupPosition);
                        Long boxLong = Boxing.boxLong(this.$id);
                        this.label = 1;
                        if (function6.invoke(coroutineScope, expandableListView, view, boxInt, boxLong, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public final boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87931(expandableListView, view, i, j, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onGroupCollapse$default(ExpandableListView expandableListView, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onGroupCollapse(expandableListView, coroutineContext, function3);
    }

    public static final void onGroupCollapse(ExpandableListView receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGroupCollapse$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onGroupCollapse$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {TypedValues.Transition.TYPE_FROM, 703}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGroupCollapse$1$1 */
            /* loaded from: classes9.dex */
            static final class C87941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $groupPosition;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10128p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87941(int i, Continuation continuation) {
                    super(2, continuation);
                    this.$groupPosition = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87941 c87941 = new C87941(this.$groupPosition, completion);
                    c87941.f10128p$ = (CoroutineScope) obj;
                    return c87941;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10128p$;
                        Function3 function3 = handler;
                        Integer boxInt = Boxing.boxInt(this.$groupPosition);
                        this.label = 1;
                        if (function3.invoke(coroutineScope, boxInt, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.ExpandableListView.OnGroupCollapseListener
            public final void onGroupCollapse(int i) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87941(i, null));
            }
        });
    }

    public static /* synthetic */ void onGroupExpand$default(ExpandableListView expandableListView, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onGroupExpand(expandableListView, coroutineContext, function3);
    }

    public static final void onGroupExpand(ExpandableListView receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGroupExpand$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onGroupExpand$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {712, 714}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onGroupExpand$1$1 */
            /* loaded from: classes9.dex */
            static final class C87951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $groupPosition;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10129p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87951(int i, Continuation continuation) {
                    super(2, continuation);
                    this.$groupPosition = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87951 c87951 = new C87951(this.$groupPosition, completion);
                    c87951.f10129p$ = (CoroutineScope) obj;
                    return c87951;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87951) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10129p$;
                        Function3 function3 = handler;
                        Integer boxInt = Boxing.boxInt(this.$groupPosition);
                        this.label = 1;
                        if (function3.invoke(coroutineScope, boxInt, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.ExpandableListView.OnGroupExpandListener
            public final void onGroupExpand(int i) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87951(i, null));
            }
        });
    }

    public static /* synthetic */ void onScroll$default(NumberPicker numberPicker, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onScroll(numberPicker, coroutineContext, function4);
    }

    public static final void onScroll(NumberPicker receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super NumberPicker, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnScrollListener(new NumberPicker.OnScrollListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onScroll$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onScroll$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {723, 725}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onScroll$1$1 */
            /* loaded from: classes9.dex */
            static final class C88091 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $scrollState;
                final /* synthetic */ NumberPicker $view;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10148p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88091(NumberPicker numberPicker, int i, Continuation continuation) {
                    super(2, continuation);
                    this.$view = numberPicker;
                    this.$scrollState = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88091 c88091 = new C88091(this.$view, this.$scrollState, completion);
                    c88091.f10148p$ = (CoroutineScope) obj;
                    return c88091;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88091) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10148p$;
                        Function4 function4 = handler;
                        NumberPicker numberPicker = this.$view;
                        Integer boxInt = Boxing.boxInt(this.$scrollState);
                        this.label = 1;
                        if (function4.invoke(coroutineScope, numberPicker, boxInt, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.NumberPicker.OnScrollListener
            public final void onScrollStateChange(NumberPicker numberPicker, int i) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88091(numberPicker, i, null));
            }
        });
    }

    public static /* synthetic */ void onValueChanged$default(NumberPicker numberPicker, CoroutineContext coroutineContext, Function5 function5, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onValueChanged(numberPicker, coroutineContext, function5);
    }

    public static final void onValueChanged(NumberPicker receiver$0, final CoroutineContext context, final Function5<? super CoroutineScope, ? super NumberPicker, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onValueChanged$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onValueChanged$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {734, 736}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onValueChanged$1$1 */
            /* loaded from: classes9.dex */
            static final class C88171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $newVal;
                final /* synthetic */ int $oldVal;
                final /* synthetic */ NumberPicker $picker;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10159p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88171(NumberPicker numberPicker, int i, int i2, Continuation continuation) {
                    super(2, continuation);
                    this.$picker = numberPicker;
                    this.$oldVal = i;
                    this.$newVal = i2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88171 c88171 = new C88171(this.$picker, this.$oldVal, this.$newVal, completion);
                    c88171.f10159p$ = (CoroutineScope) obj;
                    return c88171;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10159p$;
                        Function5 function5 = handler;
                        NumberPicker numberPicker = this.$picker;
                        Integer boxInt = Boxing.boxInt(this.$oldVal);
                        Integer boxInt2 = Boxing.boxInt(this.$newVal);
                        this.label = 1;
                        if (function5.invoke(coroutineScope, numberPicker, boxInt, boxInt2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.NumberPicker.OnValueChangeListener
            public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88171(numberPicker, i, i2, null));
            }
        });
    }

    public static /* synthetic */ void onCheckedChange$default(RadioGroup radioGroup, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onCheckedChange(radioGroup, coroutineContext, (Function4<? super CoroutineScope, ? super RadioGroup, ? super Integer, ? super Continuation<? super Unit>, ? extends Object>) function4);
    }

    public static final void onCheckedChange(RadioGroup receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super RadioGroup, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCheckedChange$2

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onCheckedChange$2$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {745, 747}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCheckedChange$2$1 */
            /* loaded from: classes9.dex */
            static final class C87781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $checkedId;
                final /* synthetic */ RadioGroup $group;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10103p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87781(RadioGroup radioGroup, int i, Continuation continuation) {
                    super(2, continuation);
                    this.$group = radioGroup;
                    this.$checkedId = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87781 c87781 = new C87781(this.$group, this.$checkedId, completion);
                    c87781.f10103p$ = (CoroutineScope) obj;
                    return c87781;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10103p$;
                        Function4 function4 = handler;
                        RadioGroup radioGroup = this.$group;
                        Integer boxInt = Boxing.boxInt(this.$checkedId);
                        this.label = 1;
                        if (function4.invoke(coroutineScope, radioGroup, boxInt, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87781(radioGroup, i, null));
            }
        });
    }

    public static /* synthetic */ void onRatingBarChange$default(RatingBar ratingBar, CoroutineContext coroutineContext, Function5 function5, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onRatingBarChange(ratingBar, coroutineContext, function5);
    }

    public static final void onRatingBarChange(RatingBar receiver$0, final CoroutineContext context, final Function5<? super CoroutineScope, ? super RatingBar, ? super Float, ? super Boolean, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onRatingBarChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onRatingBarChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {756, 758}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onRatingBarChange$1$1 */
            /* loaded from: classes9.dex */
            static final class C88081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $fromUser;
                final /* synthetic */ float $rating;
                final /* synthetic */ RatingBar $ratingBar;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10147p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88081(RatingBar ratingBar, float f, boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.$ratingBar = ratingBar;
                    this.$rating = f;
                    this.$fromUser = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88081 c88081 = new C88081(this.$ratingBar, this.$rating, this.$fromUser, completion);
                    c88081.f10147p$ = (CoroutineScope) obj;
                    return c88081;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10147p$;
                        Function5 function5 = handler;
                        RatingBar ratingBar = this.$ratingBar;
                        Float boxFloat = Boxing.boxFloat(this.$rating);
                        Boolean boxBoolean = Boxing.boxBoolean(this.$fromUser);
                        this.label = 1;
                        if (function5.invoke(coroutineScope, ratingBar, boxFloat, boxBoolean, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.RatingBar.OnRatingBarChangeListener
            public final void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88081(ratingBar, f, z, null));
            }
        });
    }

    public static /* synthetic */ void onClose$default(SearchView searchView, CoroutineContext coroutineContext, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onClose(searchView, coroutineContext, z, function2);
    }

    public static final void onClose(SearchView receiver$0, final CoroutineContext context, final boolean z, final Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnCloseListener(new SearchView.OnCloseListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onClose$1
            @Override // android.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, handler);
                return z;
            }
        });
    }

    public static /* synthetic */ void onQueryTextFocusChange$default(SearchView searchView, CoroutineContext coroutineContext, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onQueryTextFocusChange(searchView, coroutineContext, function4);
    }

    public static final void onQueryTextFocusChange(SearchView receiver$0, final CoroutineContext context, final Function4<? super CoroutineScope, ? super View, ? super Boolean, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onQueryTextFocusChange$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onQueryTextFocusChange$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {778, 780}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onQueryTextFocusChange$1$1, reason: invalid class name */
            /* loaded from: classes9.dex */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $hasFocus;

                /* renamed from: $v */
                final /* synthetic */ View f10145$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10146p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(View view, boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.f10145$v = view;
                    this.$hasFocus = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f10145$v, this.$hasFocus, completion);
                    anonymousClass1.f10146p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10146p$;
                        Function4 function4 = handler;
                        View v = this.f10145$v;
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        Boolean boxBoolean = Boxing.boxBoolean(this.$hasFocus);
                        this.label = 1;
                        if (function4.invoke(coroutineScope, v, boxBoolean, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new AnonymousClass1(view, z, null));
            }
        });
    }

    public static /* synthetic */ void onQueryTextListener$default(SearchView searchView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onQueryTextListener(searchView, coroutineContext, function1);
    }

    public static final void onQueryTextListener(SearchView receiver$0, CoroutineContext context, Function1<? super __SearchView_OnQueryTextListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __SearchView_OnQueryTextListener __searchview_onquerytextlistener = new __SearchView_OnQueryTextListener(context);
        init.invoke(__searchview_onquerytextlistener);
        receiver$0.setOnQueryTextListener(__searchview_onquerytextlistener);
    }

    public static /* synthetic */ void onSearchClick$default(SearchView searchView, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onSearchClick(searchView, coroutineContext, function3);
    }

    public static final void onSearchClick(SearchView receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnSearchClickListener(new View.OnClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onSearchClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onSearchClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {840, 842}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onSearchClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C88111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $v */
                final /* synthetic */ View f10151$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10152p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88111(View view, Continuation continuation) {
                    super(2, continuation);
                    this.f10151$v = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88111 c88111 = new C88111(this.f10151$v, completion);
                    c88111.f10152p$ = (CoroutineScope) obj;
                    return c88111;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10152p$;
                        Function3 function3 = handler;
                        View view = this.f10151$v;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88111(view, null));
            }
        });
    }

    public static /* synthetic */ void onSuggestionListener$default(SearchView searchView, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onSuggestionListener(searchView, coroutineContext, function1);
    }

    public static final void onSuggestionListener(SearchView receiver$0, CoroutineContext context, Function1<? super __SearchView_OnSuggestionListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __SearchView_OnSuggestionListener __searchview_onsuggestionlistener = new __SearchView_OnSuggestionListener(context);
        init.invoke(__searchview_onsuggestionlistener);
        receiver$0.setOnSuggestionListener(__searchview_onsuggestionlistener);
    }

    public static /* synthetic */ void onSeekBarChangeListener$default(SeekBar seekBar, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onSeekBarChangeListener(seekBar, coroutineContext, function1);
    }

    public static final void onSeekBarChangeListener(SeekBar receiver$0, CoroutineContext context, Function1<? super __SeekBar_OnSeekBarChangeListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __SeekBar_OnSeekBarChangeListener __seekbar_onseekbarchangelistener = new __SeekBar_OnSeekBarChangeListener(context);
        init.invoke(__seekbar_onseekbarchangelistener);
        receiver$0.setOnSeekBarChangeListener(__seekbar_onseekbarchangelistener);
    }

    public static /* synthetic */ void onDrawerClose$default(SlidingDrawer slidingDrawer, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onDrawerClose(slidingDrawer, coroutineContext, function2);
    }

    public static final void onDrawerClose(SlidingDrawer receiver$0, final CoroutineContext context, final Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDrawerClose$1
            @Override // android.widget.SlidingDrawer.OnDrawerCloseListener
            public final void onDrawerClosed() {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, handler);
            }
        });
    }

    public static /* synthetic */ void onDrawerOpen$default(SlidingDrawer slidingDrawer, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onDrawerOpen(slidingDrawer, coroutineContext, function2);
    }

    public static final void onDrawerOpen(SlidingDrawer receiver$0, final CoroutineContext context, final Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onDrawerOpen$1
            @Override // android.widget.SlidingDrawer.OnDrawerOpenListener
            public final void onDrawerOpened() {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, handler);
            }
        });
    }

    public static /* synthetic */ void onDrawerScrollListener$default(SlidingDrawer slidingDrawer, CoroutineContext coroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onDrawerScrollListener(slidingDrawer, coroutineContext, function1);
    }

    public static final void onDrawerScrollListener(SlidingDrawer receiver$0, CoroutineContext context, Function1<? super __SlidingDrawer_OnDrawerScrollListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __SlidingDrawer_OnDrawerScrollListener __slidingdrawer_ondrawerscrolllistener = new __SlidingDrawer_OnDrawerScrollListener(context);
        init.invoke(__slidingdrawer_ondrawerscrolllistener);
        receiver$0.setOnDrawerScrollListener(__slidingdrawer_ondrawerscrolllistener);
    }

    public static /* synthetic */ void onTabChanged$default(TabHost tabHost, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onTabChanged(tabHost, coroutineContext, function3);
    }

    public static final void onTabChanged(TabHost receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super String, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnTabChangedListener(new TabHost.OnTabChangeListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onTabChanged$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onTabChanged$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {PointerIconCompat.TYPE_ZOOM_IN, PointerIconCompat.TYPE_GRAB}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onTabChanged$1$1 */
            /* loaded from: classes9.dex */
            static final class C88131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $tabId;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10154p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88131(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$tabId = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88131 c88131 = new C88131(this.$tabId, completion);
                    c88131.f10154p$ = (CoroutineScope) obj;
                    return c88131;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10154p$;
                        Function3 function3 = handler;
                        String str = this.$tabId;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, str, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.TabHost.OnTabChangeListener
            public final void onTabChanged(String str) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88131(str, null));
            }
        });
    }

    public static /* synthetic */ void onEditorAction$default(TextView textView, CoroutineContext coroutineContext, boolean z, Function5 function5, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onEditorAction(textView, coroutineContext, z, function5);
    }

    public static final void onEditorAction(TextView receiver$0, final CoroutineContext context, final boolean z, final Function5<? super CoroutineScope, ? super TextView, ? super Integer, ? super KeyEvent, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onEditorAction$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onEditorAction$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1030, 1032}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onEditorAction$1$1 */
            /* loaded from: classes9.dex */
            static final class C87881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $actionId;
                final /* synthetic */ KeyEvent $event;

                /* renamed from: $v */
                final /* synthetic */ TextView f10118$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10119p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87881(TextView textView, int i, KeyEvent keyEvent, Continuation continuation) {
                    super(2, continuation);
                    this.f10118$v = textView;
                    this.$actionId = i;
                    this.$event = keyEvent;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87881 c87881 = new C87881(this.f10118$v, this.$actionId, this.$event, completion);
                    c87881.f10119p$ = (CoroutineScope) obj;
                    return c87881;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87881) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10119p$;
                        Function5 function5 = handler;
                        TextView textView = this.f10118$v;
                        Integer boxInt = Boxing.boxInt(this.$actionId);
                        KeyEvent keyEvent = this.$event;
                        this.label = 1;
                        if (function5.invoke(coroutineScope, textView, boxInt, keyEvent, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87881(textView, i, keyEvent, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onTimeChanged$default(TimePicker timePicker, CoroutineContext coroutineContext, Function5 function5, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onTimeChanged(timePicker, coroutineContext, function5);
    }

    public static final void onTimeChanged(TimePicker receiver$0, final CoroutineContext context, final Function5<? super CoroutineScope, ? super TimePicker, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onTimeChanged$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onTimeChanged$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1042, 1044}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onTimeChanged$1$1 */
            /* loaded from: classes9.dex */
            static final class C88141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $hourOfDay;
                final /* synthetic */ int $minute;
                final /* synthetic */ TimePicker $view;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10155p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88141(TimePicker timePicker, int i, int i2, Continuation continuation) {
                    super(2, continuation);
                    this.$view = timePicker;
                    this.$hourOfDay = i;
                    this.$minute = i2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88141 c88141 = new C88141(this.$view, this.$hourOfDay, this.$minute, completion);
                    c88141.f10155p$ = (CoroutineScope) obj;
                    return c88141;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10155p$;
                        Function5 function5 = handler;
                        TimePicker timePicker = this.$view;
                        Integer boxInt = Boxing.boxInt(this.$hourOfDay);
                        Integer boxInt2 = Boxing.boxInt(this.$minute);
                        this.label = 1;
                        if (function5.invoke(coroutineScope, timePicker, boxInt, boxInt2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.TimePicker.OnTimeChangedListener
            public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88141(timePicker, i, i2, null));
            }
        });
    }

    public static /* synthetic */ void onMenuItemClick$default(Toolbar toolbar, CoroutineContext coroutineContext, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onMenuItemClick(toolbar, coroutineContext, z, (Function3<? super CoroutineScope, ? super MenuItem, ? super Continuation<? super Unit>, ? extends Object>) function3);
    }

    public static final void onMenuItemClick(Toolbar receiver$0, final CoroutineContext context, final boolean z, final Function3<? super CoroutineScope, ? super MenuItem, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onMenuItemClick$2

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onMenuItemClick$2$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1054, 1056}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onMenuItemClick$2$1 */
            /* loaded from: classes9.dex */
            static final class C88051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MenuItem $item;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10143p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88051(MenuItem menuItem, Continuation continuation) {
                    super(2, continuation);
                    this.$item = menuItem;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88051 c88051 = new C88051(this.$item, completion);
                    c88051.f10143p$ = (CoroutineScope) obj;
                    return c88051;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10143p$;
                        Function3 function3 = handler;
                        MenuItem menuItem = this.$item;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, menuItem, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88051(menuItem, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onCompletion$default(VideoView videoView, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onCompletion(videoView, coroutineContext, function3);
    }

    public static final void onCompletion(VideoView receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super MediaPlayer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCompletion$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onCompletion$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1066, 1068}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onCompletion$1$1 */
            /* loaded from: classes9.dex */
            static final class C87821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MediaPlayer $mp;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10109p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87821(MediaPlayer mediaPlayer, Continuation continuation) {
                    super(2, continuation);
                    this.$mp = mediaPlayer;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87821 c87821 = new C87821(this.$mp, completion);
                    c87821.f10109p$ = (CoroutineScope) obj;
                    return c87821;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10109p$;
                        Function3 function3 = handler;
                        MediaPlayer mediaPlayer = this.$mp;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, mediaPlayer, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87821(mediaPlayer, null));
            }
        });
    }

    public static /* synthetic */ void onError$default(VideoView videoView, CoroutineContext coroutineContext, boolean z, Function5 function5, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onError(videoView, coroutineContext, z, function5);
    }

    public static final void onError(VideoView receiver$0, final CoroutineContext context, final boolean z, final Function5<? super CoroutineScope, ? super MediaPlayer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onError$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onError$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1078, 1080}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onError$1$1 */
            /* loaded from: classes9.dex */
            static final class C87891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $extra;
                final /* synthetic */ MediaPlayer $mp;
                final /* synthetic */ int $what;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10120p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87891(MediaPlayer mediaPlayer, int i, int i2, Continuation continuation) {
                    super(2, continuation);
                    this.$mp = mediaPlayer;
                    this.$what = i;
                    this.$extra = i2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87891 c87891 = new C87891(this.$mp, this.$what, this.$extra, completion);
                    c87891.f10120p$ = (CoroutineScope) obj;
                    return c87891;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10120p$;
                        Function5 function5 = handler;
                        MediaPlayer mediaPlayer = this.$mp;
                        Integer boxInt = Boxing.boxInt(this.$what);
                        Integer boxInt2 = Boxing.boxInt(this.$extra);
                        this.label = 1;
                        if (function5.invoke(coroutineScope, mediaPlayer, boxInt, boxInt2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87891(mediaPlayer, i, i2, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onInfo$default(VideoView videoView, CoroutineContext coroutineContext, boolean z, Function5 function5, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        onInfo(videoView, coroutineContext, z, function5);
    }

    public static final void onInfo(VideoView receiver$0, final CoroutineContext context, final boolean z, final Function5<? super CoroutineScope, ? super MediaPlayer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onInfo$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onInfo$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1091, 1093}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onInfo$1$1 */
            /* loaded from: classes9.dex */
            static final class C87981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ int $extra;
                final /* synthetic */ MediaPlayer $mp;
                final /* synthetic */ int $what;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10133p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C87981(MediaPlayer mediaPlayer, int i, int i2, Continuation continuation) {
                    super(2, continuation);
                    this.$mp = mediaPlayer;
                    this.$what = i;
                    this.$extra = i2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C87981 c87981 = new C87981(this.$mp, this.$what, this.$extra, completion);
                    c87981.f10133p$ = (CoroutineScope) obj;
                    return c87981;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C87981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10133p$;
                        Function5 function5 = handler;
                        MediaPlayer mediaPlayer = this.$mp;
                        Integer boxInt = Boxing.boxInt(this.$what);
                        Integer boxInt2 = Boxing.boxInt(this.$extra);
                        this.label = 1;
                        if (function5.invoke(coroutineScope, mediaPlayer, boxInt, boxInt2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.media.MediaPlayer.OnInfoListener
            public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C87981(mediaPlayer, i, i2, null));
                return z;
            }
        });
    }

    public static /* synthetic */ void onPrepared$default(VideoView videoView, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onPrepared(videoView, coroutineContext, function3);
    }

    public static final void onPrepared(VideoView receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super MediaPlayer, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onPrepared$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onPrepared$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1103, 1105}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onPrepared$1$1 */
            /* loaded from: classes9.dex */
            static final class C88061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MediaPlayer $mp;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10144p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88061(MediaPlayer mediaPlayer, Continuation continuation) {
                    super(2, continuation);
                    this.$mp = mediaPlayer;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88061 c88061 = new C88061(this.$mp, completion);
                    c88061.f10144p$ = (CoroutineScope) obj;
                    return c88061;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88061) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10144p$;
                        Function3 function3 = handler;
                        MediaPlayer mediaPlayer = this.$mp;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, mediaPlayer, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88061(mediaPlayer, null));
            }
        });
    }

    public static /* synthetic */ void onZoomInClick$default(ZoomControls zoomControls, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onZoomInClick(zoomControls, coroutineContext, function3);
    }

    public static final void onZoomInClick(ZoomControls receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnZoomInClickListener(new View.OnClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onZoomInClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onZoomInClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1114, 1116}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onZoomInClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C88181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $v */
                final /* synthetic */ View f10160$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10161p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88181(View view, Continuation continuation) {
                    super(2, continuation);
                    this.f10160$v = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88181 c88181 = new C88181(this.f10160$v, completion);
                    c88181.f10161p$ = (CoroutineScope) obj;
                    return c88181;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10161p$;
                        Function3 function3 = handler;
                        View view = this.f10160$v;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88181(view, null));
            }
        });
    }

    public static /* synthetic */ void onZoomOutClick$default(ZoomControls zoomControls, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getMain();
        }
        onZoomOutClick(zoomControls, coroutineContext, function3);
    }

    public static final void onZoomOutClick(ZoomControls receiver$0, final CoroutineContext context, final Function3<? super CoroutineScope, ? super View, ? super Continuation<? super Unit>, ? extends Object> handler) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver$0.setOnZoomOutClickListener(new View.OnClickListener() { // from class: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onZoomOutClick$1

            /* JADX WARN: Classes with same name are omitted:
              classes7.dex
             */
            /* compiled from: ListenersWithCoroutines.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
            @DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/Sdk27CoroutinesListenersWithCoroutinesKt$onZoomOutClick$1$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {1125, 1127}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt$onZoomOutClick$1$1 */
            /* loaded from: classes9.dex */
            static final class C88191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: $v */
                final /* synthetic */ View f10162$v;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f10163p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C88191(View view, Continuation continuation) {
                    super(2, continuation);
                    this.f10162$v = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C88191 c88191 = new C88191(this.f10162$v, completion);
                    c88191.f10163p$ = (CoroutineScope) obj;
                    return c88191;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C88191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        CoroutineScope coroutineScope = this.f10163p$;
                        Function3 function3 = handler;
                        View view = this.f10162$v;
                        this.label = 1;
                        if (function3.invoke(coroutineScope, view, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt.launch(GlobalScope.INSTANCE, CoroutineContext.this, CoroutineStart.DEFAULT, new C88191(view, null));
            }
        });
    }
}
