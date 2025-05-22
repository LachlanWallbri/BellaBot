package org.jetbrains.anko;

import android.app.MediaRouteButton;
import android.content.Context;
import android.gesture.GestureOverlayView;
import android.inputmethodservice.ExtractEditText;
import android.media.tv.TvView;
import android.opengl.GLSurfaceView;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.AdapterViewFlipper;
import android.widget.AnalogClock;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.DialerFilter;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.StackView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.TwoLineListItem;
import android.widget.VideoView;
import android.widget.ViewFlipper;
import android.widget.ZoomButton;
import android.widget.ZoomControls;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Views.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¢\u0003\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00160\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\bR\u001d\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00190\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u001d\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001c0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\bR\u001d\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u001d\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\"0\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\bR\u001d\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020%0\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\bR\u001d\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020(0\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\bR\u001d\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020+0\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\bR\u001d\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020.0\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\bR\u001d\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002010\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\bR\u001d\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002040\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\bR\u001d\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002070\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\bR\u001d\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020:0\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\bR\u001d\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020=0\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\bR\u001d\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020@0\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\bR\u001d\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020C0\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\bR\u001d\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020F0\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\bR\u001d\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020I0\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\bR\u001d\u0010K\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020L0\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\bR\u001d\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020O0\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\bR\u001d\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020R0\u0004¢\u0006\b\n\u0000\u001a\u0004\bS\u0010\bR\u001d\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020U0\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\bR\u001d\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020X0\u0004¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\bR\u001d\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020[0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\bR\u001d\u0010]\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020^0\u0004¢\u0006\b\n\u0000\u001a\u0004\b_\u0010\bR\u001d\u0010`\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020a0\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\bR\u001d\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020d0\u0004¢\u0006\b\n\u0000\u001a\u0004\be\u0010\bR\u001d\u0010f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020g0\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\bR\u001d\u0010i\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020j0\u0004¢\u0006\b\n\u0000\u001a\u0004\bk\u0010\bR\u001d\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020m0\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\bR\u001d\u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020p0\u0004¢\u0006\b\n\u0000\u001a\u0004\bq\u0010\bR\u001d\u0010r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020s0\u0004¢\u0006\b\n\u0000\u001a\u0004\bt\u0010\bR\u001d\u0010u\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020v0\u0004¢\u0006\b\n\u0000\u001a\u0004\bw\u0010\bR\u001d\u0010x\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020y0\u0004¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\bR\u001d\u0010{\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020|0\u0004¢\u0006\b\n\u0000\u001a\u0004\b}\u0010\bR\u001e\u0010~\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u007f0\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010\bR \u0010\u0081\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0082\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010\bR \u0010\u0084\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0085\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010\bR \u0010\u0087\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0088\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u0010\bR \u0010\u008a\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u008b\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u0010\bR \u0010\u008d\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u008e\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u0010\bR \u0010\u0090\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0091\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010\bR \u0010\u0093\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0094\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0095\u0001\u0010\bR \u0010\u0096\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0097\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u0010\bR \u0010\u0099\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u009a\u00010\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u0010\b¨\u0006\u009c\u0001"}, m3961d2 = {"org/jetbrains/anko/$$Anko$Factories$Sdk27View", "", "()V", "ADAPTER_VIEW_FLIPPER", "Lkotlin/Function1;", "Landroid/content/Context;", "Landroid/widget/AdapterViewFlipper;", "getADAPTER_VIEW_FLIPPER", "()Lkotlin/jvm/functions/Function1;", "ANALOG_CLOCK", "Landroid/widget/AnalogClock;", "getANALOG_CLOCK", "AUTO_COMPLETE_TEXT_VIEW", "Landroid/widget/AutoCompleteTextView;", "getAUTO_COMPLETE_TEXT_VIEW", "BUTTON", "Landroid/widget/Button;", "getBUTTON", "CALENDAR_VIEW", "Landroid/widget/CalendarView;", "getCALENDAR_VIEW", "CHECKED_TEXT_VIEW", "Landroid/widget/CheckedTextView;", "getCHECKED_TEXT_VIEW", "CHECK_BOX", "Landroid/widget/CheckBox;", "getCHECK_BOX", "CHRONOMETER", "Landroid/widget/Chronometer;", "getCHRONOMETER", "DATE_PICKER", "Landroid/widget/DatePicker;", "getDATE_PICKER", "DIALER_FILTER", "Landroid/widget/DialerFilter;", "getDIALER_FILTER", "DIGITAL_CLOCK", "Landroid/widget/DigitalClock;", "getDIGITAL_CLOCK", "EDIT_TEXT", "Landroid/widget/EditText;", "getEDIT_TEXT", "EXPANDABLE_LIST_VIEW", "Landroid/widget/ExpandableListView;", "getEXPANDABLE_LIST_VIEW", "EXTRACT_EDIT_TEXT", "Landroid/inputmethodservice/ExtractEditText;", "getEXTRACT_EDIT_TEXT", "GESTURE_OVERLAY_VIEW", "Landroid/gesture/GestureOverlayView;", "getGESTURE_OVERLAY_VIEW", "G_L_SURFACE_VIEW", "Landroid/opengl/GLSurfaceView;", "getG_L_SURFACE_VIEW", "IMAGE_BUTTON", "Landroid/widget/ImageButton;", "getIMAGE_BUTTON", "IMAGE_VIEW", "Landroid/widget/ImageView;", "getIMAGE_VIEW", "LIST_VIEW", "Landroid/widget/ListView;", "getLIST_VIEW", "MEDIA_ROUTE_BUTTON", "Landroid/app/MediaRouteButton;", "getMEDIA_ROUTE_BUTTON", "MULTI_AUTO_COMPLETE_TEXT_VIEW", "Landroid/widget/MultiAutoCompleteTextView;", "getMULTI_AUTO_COMPLETE_TEXT_VIEW", "NUMBER_PICKER", "Landroid/widget/NumberPicker;", "getNUMBER_PICKER", "PROGRESS_BAR", "Landroid/widget/ProgressBar;", "getPROGRESS_BAR", "QUICK_CONTACT_BADGE", "Landroid/widget/QuickContactBadge;", "getQUICK_CONTACT_BADGE", "RADIO_BUTTON", "Landroid/widget/RadioButton;", "getRADIO_BUTTON", "RATING_BAR", "Landroid/widget/RatingBar;", "getRATING_BAR", "SEARCH_VIEW", "Landroid/widget/SearchView;", "getSEARCH_VIEW", "SEEK_BAR", "Landroid/widget/SeekBar;", "getSEEK_BAR", "SLIDING_DRAWER", "Landroid/widget/SlidingDrawer;", "getSLIDING_DRAWER", "SPACE", "Landroid/widget/Space;", "getSPACE", "SPINNER", "Landroid/widget/Spinner;", "getSPINNER", "STACK_VIEW", "Landroid/widget/StackView;", "getSTACK_VIEW", "SURFACE_VIEW", "Landroid/view/SurfaceView;", "getSURFACE_VIEW", "SWITCH", "Landroid/widget/Switch;", "getSWITCH", "TAB_HOST", "Landroid/widget/TabHost;", "getTAB_HOST", "TAB_WIDGET", "Landroid/widget/TabWidget;", "getTAB_WIDGET", "TEXTURE_VIEW", "Landroid/view/TextureView;", "getTEXTURE_VIEW", "TEXT_CLOCK", "Landroid/widget/TextClock;", "getTEXT_CLOCK", "TEXT_VIEW", "Landroid/widget/TextView;", "getTEXT_VIEW", "TIME_PICKER", "Landroid/widget/TimePicker;", "getTIME_PICKER", "TOGGLE_BUTTON", "Landroid/widget/ToggleButton;", "getTOGGLE_BUTTON", "TV_VIEW", "Landroid/media/tv/TvView;", "getTV_VIEW", "TWO_LINE_LIST_ITEM", "Landroid/widget/TwoLineListItem;", "getTWO_LINE_LIST_ITEM", "VIDEO_VIEW", "Landroid/widget/VideoView;", "getVIDEO_VIEW", "VIEW", "Landroid/view/View;", "getVIEW", "VIEW_FLIPPER", "Landroid/widget/ViewFlipper;", "getVIEW_FLIPPER", "VIEW_STUB", "Landroid/view/ViewStub;", "getVIEW_STUB", "WEB_VIEW", "Landroid/webkit/WebView;", "getWEB_VIEW", "ZOOM_BUTTON", "Landroid/widget/ZoomButton;", "getZOOM_BUTTON", "ZOOM_CONTROLS", "Landroid/widget/ZoomControls;", "getZOOM_CONTROLS", "anko-sdk27_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* renamed from: org.jetbrains.anko.$$Anko$Factories$Sdk27View, reason: invalid class name */
/* loaded from: classes9.dex */
public final class C$$Anko$Factories$Sdk27View {
    public static final C$$Anko$Factories$Sdk27View INSTANCE = new C$$Anko$Factories$Sdk27View();
    private static final Function1<Context, MediaRouteButton> MEDIA_ROUTE_BUTTON = new Function1<Context, MediaRouteButton>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$MEDIA_ROUTE_BUTTON$1
        @Override // kotlin.jvm.functions.Function1
        public final MediaRouteButton invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new MediaRouteButton(ctx);
        }
    };
    private static final Function1<Context, GestureOverlayView> GESTURE_OVERLAY_VIEW = new Function1<Context, GestureOverlayView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$GESTURE_OVERLAY_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final GestureOverlayView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new GestureOverlayView(ctx);
        }
    };
    private static final Function1<Context, ExtractEditText> EXTRACT_EDIT_TEXT = new Function1<Context, ExtractEditText>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$EXTRACT_EDIT_TEXT$1
        @Override // kotlin.jvm.functions.Function1
        public final ExtractEditText invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ExtractEditText(ctx);
        }
    };
    private static final Function1<Context, TvView> TV_VIEW = new Function1<Context, TvView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TV_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final TvView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new TvView(ctx);
        }
    };
    private static final Function1<Context, GLSurfaceView> G_L_SURFACE_VIEW = new Function1<Context, GLSurfaceView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$G_L_SURFACE_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final GLSurfaceView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new GLSurfaceView(ctx);
        }
    };
    private static final Function1<Context, SurfaceView> SURFACE_VIEW = new Function1<Context, SurfaceView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$SURFACE_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final SurfaceView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new SurfaceView(ctx);
        }
    };
    private static final Function1<Context, TextureView> TEXTURE_VIEW = new Function1<Context, TextureView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TEXTURE_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final TextureView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new TextureView(ctx);
        }
    };
    private static final Function1<Context, View> VIEW = new Function1<Context, View>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final View invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new View(ctx);
        }
    };
    private static final Function1<Context, ViewStub> VIEW_STUB = new Function1<Context, ViewStub>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$VIEW_STUB$1
        @Override // kotlin.jvm.functions.Function1
        public final ViewStub invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ViewStub(ctx);
        }
    };
    private static final Function1<Context, WebView> WEB_VIEW = new Function1<Context, WebView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$WEB_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final WebView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new WebView(ctx);
        }
    };
    private static final Function1<Context, AdapterViewFlipper> ADAPTER_VIEW_FLIPPER = new Function1<Context, AdapterViewFlipper>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$ADAPTER_VIEW_FLIPPER$1
        @Override // kotlin.jvm.functions.Function1
        public final AdapterViewFlipper invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new AdapterViewFlipper(ctx);
        }
    };
    private static final Function1<Context, AnalogClock> ANALOG_CLOCK = new Function1<Context, AnalogClock>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$ANALOG_CLOCK$1
        @Override // kotlin.jvm.functions.Function1
        public final AnalogClock invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new AnalogClock(ctx);
        }
    };
    private static final Function1<Context, AutoCompleteTextView> AUTO_COMPLETE_TEXT_VIEW = new Function1<Context, AutoCompleteTextView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$AUTO_COMPLETE_TEXT_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final AutoCompleteTextView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new AutoCompleteTextView(ctx);
        }
    };
    private static final Function1<Context, Button> BUTTON = new Function1<Context, Button>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$BUTTON$1
        @Override // kotlin.jvm.functions.Function1
        public final Button invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new Button(ctx);
        }
    };
    private static final Function1<Context, CalendarView> CALENDAR_VIEW = new Function1<Context, CalendarView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$CALENDAR_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final CalendarView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new CalendarView(ctx);
        }
    };
    private static final Function1<Context, CheckBox> CHECK_BOX = new Function1<Context, CheckBox>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$CHECK_BOX$1
        @Override // kotlin.jvm.functions.Function1
        public final CheckBox invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new CheckBox(ctx);
        }
    };
    private static final Function1<Context, CheckedTextView> CHECKED_TEXT_VIEW = new Function1<Context, CheckedTextView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$CHECKED_TEXT_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final CheckedTextView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new CheckedTextView(ctx);
        }
    };
    private static final Function1<Context, Chronometer> CHRONOMETER = new Function1<Context, Chronometer>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$CHRONOMETER$1
        @Override // kotlin.jvm.functions.Function1
        public final Chronometer invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new Chronometer(ctx);
        }
    };
    private static final Function1<Context, DatePicker> DATE_PICKER = new Function1<Context, DatePicker>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$DATE_PICKER$1
        @Override // kotlin.jvm.functions.Function1
        public final DatePicker invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new DatePicker(ctx);
        }
    };
    private static final Function1<Context, DialerFilter> DIALER_FILTER = new Function1<Context, DialerFilter>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$DIALER_FILTER$1
        @Override // kotlin.jvm.functions.Function1
        public final DialerFilter invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new DialerFilter(ctx);
        }
    };
    private static final Function1<Context, DigitalClock> DIGITAL_CLOCK = new Function1<Context, DigitalClock>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$DIGITAL_CLOCK$1
        @Override // kotlin.jvm.functions.Function1
        public final DigitalClock invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new DigitalClock(ctx);
        }
    };
    private static final Function1<Context, EditText> EDIT_TEXT = new Function1<Context, EditText>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$EDIT_TEXT$1
        @Override // kotlin.jvm.functions.Function1
        public final EditText invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new EditText(ctx);
        }
    };
    private static final Function1<Context, ExpandableListView> EXPANDABLE_LIST_VIEW = new Function1<Context, ExpandableListView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$EXPANDABLE_LIST_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final ExpandableListView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ExpandableListView(ctx);
        }
    };
    private static final Function1<Context, ImageButton> IMAGE_BUTTON = new Function1<Context, ImageButton>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$IMAGE_BUTTON$1
        @Override // kotlin.jvm.functions.Function1
        public final ImageButton invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ImageButton(ctx);
        }
    };
    private static final Function1<Context, ImageView> IMAGE_VIEW = new Function1<Context, ImageView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$IMAGE_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final ImageView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ImageView(ctx);
        }
    };
    private static final Function1<Context, ListView> LIST_VIEW = new Function1<Context, ListView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$LIST_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final ListView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ListView(ctx);
        }
    };
    private static final Function1<Context, MultiAutoCompleteTextView> MULTI_AUTO_COMPLETE_TEXT_VIEW = new Function1<Context, MultiAutoCompleteTextView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$MULTI_AUTO_COMPLETE_TEXT_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final MultiAutoCompleteTextView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new MultiAutoCompleteTextView(ctx);
        }
    };
    private static final Function1<Context, NumberPicker> NUMBER_PICKER = new Function1<Context, NumberPicker>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$NUMBER_PICKER$1
        @Override // kotlin.jvm.functions.Function1
        public final NumberPicker invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new NumberPicker(ctx);
        }
    };
    private static final Function1<Context, ProgressBar> PROGRESS_BAR = new Function1<Context, ProgressBar>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$PROGRESS_BAR$1
        @Override // kotlin.jvm.functions.Function1
        public final ProgressBar invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ProgressBar(ctx);
        }
    };
    private static final Function1<Context, QuickContactBadge> QUICK_CONTACT_BADGE = new Function1<Context, QuickContactBadge>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$QUICK_CONTACT_BADGE$1
        @Override // kotlin.jvm.functions.Function1
        public final QuickContactBadge invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new QuickContactBadge(ctx);
        }
    };
    private static final Function1<Context, RadioButton> RADIO_BUTTON = new Function1<Context, RadioButton>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$RADIO_BUTTON$1
        @Override // kotlin.jvm.functions.Function1
        public final RadioButton invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new RadioButton(ctx);
        }
    };
    private static final Function1<Context, RatingBar> RATING_BAR = new Function1<Context, RatingBar>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$RATING_BAR$1
        @Override // kotlin.jvm.functions.Function1
        public final RatingBar invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new RatingBar(ctx);
        }
    };
    private static final Function1<Context, SearchView> SEARCH_VIEW = new Function1<Context, SearchView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$SEARCH_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final SearchView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new SearchView(ctx);
        }
    };
    private static final Function1<Context, SeekBar> SEEK_BAR = new Function1<Context, SeekBar>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$SEEK_BAR$1
        @Override // kotlin.jvm.functions.Function1
        public final SeekBar invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new SeekBar(ctx);
        }
    };
    private static final Function1<Context, SlidingDrawer> SLIDING_DRAWER = new Function1<Context, SlidingDrawer>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$SLIDING_DRAWER$1
        @Override // kotlin.jvm.functions.Function1
        public final SlidingDrawer invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new SlidingDrawer(ctx, null);
        }
    };
    private static final Function1<Context, Space> SPACE = new Function1<Context, Space>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$SPACE$1
        @Override // kotlin.jvm.functions.Function1
        public final Space invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new Space(ctx);
        }
    };
    private static final Function1<Context, Spinner> SPINNER = new Function1<Context, Spinner>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$SPINNER$1
        @Override // kotlin.jvm.functions.Function1
        public final Spinner invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new Spinner(ctx);
        }
    };
    private static final Function1<Context, StackView> STACK_VIEW = new Function1<Context, StackView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$STACK_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final StackView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new StackView(ctx);
        }
    };
    private static final Function1<Context, Switch> SWITCH = new Function1<Context, Switch>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$SWITCH$1
        @Override // kotlin.jvm.functions.Function1
        public final Switch invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new Switch(ctx);
        }
    };
    private static final Function1<Context, TabHost> TAB_HOST = new Function1<Context, TabHost>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TAB_HOST$1
        @Override // kotlin.jvm.functions.Function1
        public final TabHost invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new TabHost(ctx);
        }
    };
    private static final Function1<Context, TabWidget> TAB_WIDGET = new Function1<Context, TabWidget>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TAB_WIDGET$1
        @Override // kotlin.jvm.functions.Function1
        public final TabWidget invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new TabWidget(ctx);
        }
    };
    private static final Function1<Context, TextClock> TEXT_CLOCK = new Function1<Context, TextClock>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TEXT_CLOCK$1
        @Override // kotlin.jvm.functions.Function1
        public final TextClock invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new TextClock(ctx);
        }
    };
    private static final Function1<Context, TextView> TEXT_VIEW = new Function1<Context, TextView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TEXT_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final TextView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new TextView(ctx);
        }
    };
    private static final Function1<Context, TimePicker> TIME_PICKER = new Function1<Context, TimePicker>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TIME_PICKER$1
        @Override // kotlin.jvm.functions.Function1
        public final TimePicker invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new TimePicker(ctx);
        }
    };
    private static final Function1<Context, ToggleButton> TOGGLE_BUTTON = new Function1<Context, ToggleButton>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TOGGLE_BUTTON$1
        @Override // kotlin.jvm.functions.Function1
        public final ToggleButton invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ToggleButton(ctx);
        }
    };
    private static final Function1<Context, TwoLineListItem> TWO_LINE_LIST_ITEM = new Function1<Context, TwoLineListItem>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$TWO_LINE_LIST_ITEM$1
        @Override // kotlin.jvm.functions.Function1
        public final TwoLineListItem invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new TwoLineListItem(ctx);
        }
    };
    private static final Function1<Context, VideoView> VIDEO_VIEW = new Function1<Context, VideoView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$VIDEO_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final VideoView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new VideoView(ctx);
        }
    };
    private static final Function1<Context, ViewFlipper> VIEW_FLIPPER = new Function1<Context, ViewFlipper>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$VIEW_FLIPPER$1
        @Override // kotlin.jvm.functions.Function1
        public final ViewFlipper invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ViewFlipper(ctx);
        }
    };
    private static final Function1<Context, ZoomButton> ZOOM_BUTTON = new Function1<Context, ZoomButton>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$ZOOM_BUTTON$1
        @Override // kotlin.jvm.functions.Function1
        public final ZoomButton invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ZoomButton(ctx);
        }
    };
    private static final Function1<Context, ZoomControls> ZOOM_CONTROLS = new Function1<Context, ZoomControls>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27View$ZOOM_CONTROLS$1
        @Override // kotlin.jvm.functions.Function1
        public final ZoomControls invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ZoomControls(ctx);
        }
    };

    private C$$Anko$Factories$Sdk27View() {
    }

    public final Function1<Context, MediaRouteButton> getMEDIA_ROUTE_BUTTON() {
        return MEDIA_ROUTE_BUTTON;
    }

    public final Function1<Context, GestureOverlayView> getGESTURE_OVERLAY_VIEW() {
        return GESTURE_OVERLAY_VIEW;
    }

    public final Function1<Context, ExtractEditText> getEXTRACT_EDIT_TEXT() {
        return EXTRACT_EDIT_TEXT;
    }

    public final Function1<Context, TvView> getTV_VIEW() {
        return TV_VIEW;
    }

    public final Function1<Context, GLSurfaceView> getG_L_SURFACE_VIEW() {
        return G_L_SURFACE_VIEW;
    }

    public final Function1<Context, SurfaceView> getSURFACE_VIEW() {
        return SURFACE_VIEW;
    }

    public final Function1<Context, TextureView> getTEXTURE_VIEW() {
        return TEXTURE_VIEW;
    }

    public final Function1<Context, View> getVIEW() {
        return VIEW;
    }

    public final Function1<Context, ViewStub> getVIEW_STUB() {
        return VIEW_STUB;
    }

    public final Function1<Context, WebView> getWEB_VIEW() {
        return WEB_VIEW;
    }

    public final Function1<Context, AdapterViewFlipper> getADAPTER_VIEW_FLIPPER() {
        return ADAPTER_VIEW_FLIPPER;
    }

    public final Function1<Context, AnalogClock> getANALOG_CLOCK() {
        return ANALOG_CLOCK;
    }

    public final Function1<Context, AutoCompleteTextView> getAUTO_COMPLETE_TEXT_VIEW() {
        return AUTO_COMPLETE_TEXT_VIEW;
    }

    public final Function1<Context, Button> getBUTTON() {
        return BUTTON;
    }

    public final Function1<Context, CalendarView> getCALENDAR_VIEW() {
        return CALENDAR_VIEW;
    }

    public final Function1<Context, CheckBox> getCHECK_BOX() {
        return CHECK_BOX;
    }

    public final Function1<Context, CheckedTextView> getCHECKED_TEXT_VIEW() {
        return CHECKED_TEXT_VIEW;
    }

    public final Function1<Context, Chronometer> getCHRONOMETER() {
        return CHRONOMETER;
    }

    public final Function1<Context, DatePicker> getDATE_PICKER() {
        return DATE_PICKER;
    }

    public final Function1<Context, DialerFilter> getDIALER_FILTER() {
        return DIALER_FILTER;
    }

    public final Function1<Context, DigitalClock> getDIGITAL_CLOCK() {
        return DIGITAL_CLOCK;
    }

    public final Function1<Context, EditText> getEDIT_TEXT() {
        return EDIT_TEXT;
    }

    public final Function1<Context, ExpandableListView> getEXPANDABLE_LIST_VIEW() {
        return EXPANDABLE_LIST_VIEW;
    }

    public final Function1<Context, ImageButton> getIMAGE_BUTTON() {
        return IMAGE_BUTTON;
    }

    public final Function1<Context, ImageView> getIMAGE_VIEW() {
        return IMAGE_VIEW;
    }

    public final Function1<Context, ListView> getLIST_VIEW() {
        return LIST_VIEW;
    }

    public final Function1<Context, MultiAutoCompleteTextView> getMULTI_AUTO_COMPLETE_TEXT_VIEW() {
        return MULTI_AUTO_COMPLETE_TEXT_VIEW;
    }

    public final Function1<Context, NumberPicker> getNUMBER_PICKER() {
        return NUMBER_PICKER;
    }

    public final Function1<Context, ProgressBar> getPROGRESS_BAR() {
        return PROGRESS_BAR;
    }

    public final Function1<Context, QuickContactBadge> getQUICK_CONTACT_BADGE() {
        return QUICK_CONTACT_BADGE;
    }

    public final Function1<Context, RadioButton> getRADIO_BUTTON() {
        return RADIO_BUTTON;
    }

    public final Function1<Context, RatingBar> getRATING_BAR() {
        return RATING_BAR;
    }

    public final Function1<Context, SearchView> getSEARCH_VIEW() {
        return SEARCH_VIEW;
    }

    public final Function1<Context, SeekBar> getSEEK_BAR() {
        return SEEK_BAR;
    }

    public final Function1<Context, SlidingDrawer> getSLIDING_DRAWER() {
        return SLIDING_DRAWER;
    }

    public final Function1<Context, Space> getSPACE() {
        return SPACE;
    }

    public final Function1<Context, Spinner> getSPINNER() {
        return SPINNER;
    }

    public final Function1<Context, StackView> getSTACK_VIEW() {
        return STACK_VIEW;
    }

    public final Function1<Context, Switch> getSWITCH() {
        return SWITCH;
    }

    public final Function1<Context, TabHost> getTAB_HOST() {
        return TAB_HOST;
    }

    public final Function1<Context, TabWidget> getTAB_WIDGET() {
        return TAB_WIDGET;
    }

    public final Function1<Context, TextClock> getTEXT_CLOCK() {
        return TEXT_CLOCK;
    }

    public final Function1<Context, TextView> getTEXT_VIEW() {
        return TEXT_VIEW;
    }

    public final Function1<Context, TimePicker> getTIME_PICKER() {
        return TIME_PICKER;
    }

    public final Function1<Context, ToggleButton> getTOGGLE_BUTTON() {
        return TOGGLE_BUTTON;
    }

    public final Function1<Context, TwoLineListItem> getTWO_LINE_LIST_ITEM() {
        return TWO_LINE_LIST_ITEM;
    }

    public final Function1<Context, VideoView> getVIDEO_VIEW() {
        return VIDEO_VIEW;
    }

    public final Function1<Context, ViewFlipper> getVIEW_FLIPPER() {
        return VIEW_FLIPPER;
    }

    public final Function1<Context, ZoomButton> getZOOM_BUTTON() {
        return ZOOM_BUTTON;
    }

    public final Function1<Context, ZoomControls> getZOOM_CONTROLS() {
        return ZOOM_CONTROLS;
    }
}
