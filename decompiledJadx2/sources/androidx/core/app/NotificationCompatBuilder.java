package androidx.core.app;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final NotificationCompat.Builder mBuilderCompat;
    private RemoteViews mContentView;
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;
    private final List<Bundle> mActionExtrasList = new ArrayList();
    private final Bundle mExtras = new Bundle();

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationCompatBuilder(NotificationCompat.Builder builder) {
        List list;
        List<String> combineLists;
        this.mBuilderCompat = builder;
        if (Build.VERSION.SDK_INT >= 26) {
            this.mBuilder = new Notification.Builder(builder.mContext, builder.mChannelId);
        } else {
            this.mBuilder = new Notification.Builder(builder.mContext);
        }
        Notification notification = builder.mNotification;
        this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, builder.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(builder.mContentTitle).setContentText(builder.mContentText).setContentInfo(builder.mContentInfo).setContentIntent(builder.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(builder.mFullScreenIntent, (notification.flags & 128) != 0).setLargeIcon(builder.mLargeIcon).setNumber(builder.mNumber).setProgress(builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate);
        if (Build.VERSION.SDK_INT < 21) {
            this.mBuilder.setSound(notification.sound, notification.audioStreamType);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.mBuilder.setSubText(builder.mSubText).setUsesChronometer(builder.mUseChronometer).setPriority(builder.mPriority);
            Iterator<NotificationCompat.Action> it = builder.mActions.iterator();
            while (it.hasNext()) {
                addAction(it.next());
            }
            if (builder.mExtras != null) {
                this.mExtras.putAll(builder.mExtras);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (builder.mLocalOnly) {
                    this.mExtras.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
                }
                if (builder.mGroupKey != null) {
                    this.mExtras.putString(NotificationCompatExtras.EXTRA_GROUP_KEY, builder.mGroupKey);
                    if (builder.mGroupSummary) {
                        this.mExtras.putBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY, true);
                    } else {
                        this.mExtras.putBoolean(NotificationManagerCompat.EXTRA_USE_SIDE_CHANNEL, true);
                    }
                }
                if (builder.mSortKey != null) {
                    this.mExtras.putString(NotificationCompatExtras.EXTRA_SORT_KEY, builder.mSortKey);
                }
            }
            this.mContentView = builder.mContentView;
            this.mBigContentView = builder.mBigContentView;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.mBuilder.setShowWhen(builder.mShowWhen);
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21 && (combineLists = combineLists(getPeople(builder.mPersonList), builder.mPeople)) != null && !combineLists.isEmpty()) {
            this.mExtras.putStringArray(NotificationCompat.EXTRA_PEOPLE, (String[]) combineLists.toArray(new String[combineLists.size()]));
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.mBuilder.setLocalOnly(builder.mLocalOnly).setGroup(builder.mGroupKey).setGroupSummary(builder.mGroupSummary).setSortKey(builder.mSortKey);
            this.mGroupAlertBehavior = builder.mGroupAlertBehavior;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.mBuilder.setCategory(builder.mCategory).setColor(builder.mColor).setVisibility(builder.mVisibility).setPublicVersion(builder.mPublicVersion).setSound(notification.sound, notification.audioAttributes);
            if (Build.VERSION.SDK_INT < 28) {
                list = combineLists(getPeople(builder.mPersonList), builder.mPeople);
            } else {
                list = builder.mPeople;
            }
            if (list != null && !list.isEmpty()) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    this.mBuilder.addPerson((String) it2.next());
                }
            }
            this.mHeadsUpContentView = builder.mHeadsUpContentView;
            if (builder.mInvisibleActions.size() > 0) {
                Bundle bundle = builder.getExtras().getBundle("android.car.EXTENSIONS");
                bundle = bundle == null ? new Bundle() : bundle;
                Bundle bundle2 = new Bundle(bundle);
                Bundle bundle3 = new Bundle();
                for (int i = 0; i < builder.mInvisibleActions.size(); i++) {
                    bundle3.putBundle(Integer.toString(i), NotificationCompatJellybean.getBundleForAction(builder.mInvisibleActions.get(i)));
                }
                bundle.putBundle("invisible_actions", bundle3);
                bundle2.putBundle("invisible_actions", bundle3);
                builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
                this.mExtras.putBundle("android.car.EXTENSIONS", bundle2);
            }
        }
        if (Build.VERSION.SDK_INT >= 23 && builder.mSmallIcon != null) {
            this.mBuilder.setSmallIcon(builder.mSmallIcon);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.mBuilder.setExtras(builder.mExtras).setRemoteInputHistory(builder.mRemoteInputHistory);
            if (builder.mContentView != null) {
                this.mBuilder.setCustomContentView(builder.mContentView);
            }
            if (builder.mBigContentView != null) {
                this.mBuilder.setCustomBigContentView(builder.mBigContentView);
            }
            if (builder.mHeadsUpContentView != null) {
                this.mBuilder.setCustomHeadsUpContentView(builder.mHeadsUpContentView);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.mBuilder.setBadgeIconType(builder.mBadgeIcon).setSettingsText(builder.mSettingsText).setShortcutId(builder.mShortcutId).setTimeoutAfter(builder.mTimeout).setGroupAlertBehavior(builder.mGroupAlertBehavior);
            if (builder.mColorizedSet) {
                this.mBuilder.setColorized(builder.mColorized);
            }
            if (!TextUtils.isEmpty(builder.mChannelId)) {
                this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (Build.VERSION.SDK_INT >= 28) {
            Iterator<Person> it3 = builder.mPersonList.iterator();
            while (it3.hasNext()) {
                this.mBuilder.addPerson(it3.next().toAndroidPerson());
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mBuilder.setAllowSystemGeneratedContextualActions(builder.mAllowSystemGeneratedContextualActions);
            this.mBuilder.setBubbleMetadata(NotificationCompat.BubbleMetadata.toPlatform(builder.mBubbleMetadata));
            if (builder.mLocusId != null) {
                this.mBuilder.setLocusId(builder.mLocusId.toLocusId());
            }
        }
        if (builder.mSilent) {
            if (this.mBuilderCompat.mGroupSummary) {
                this.mGroupAlertBehavior = 2;
            } else {
                this.mGroupAlertBehavior = 1;
            }
            this.mBuilder.setVibrate(null);
            this.mBuilder.setSound(null);
            notification.defaults &= -2;
            notification.defaults &= -3;
            this.mBuilder.setDefaults(notification.defaults);
            if (Build.VERSION.SDK_INT >= 26) {
                if (TextUtils.isEmpty(this.mBuilderCompat.mGroupKey)) {
                    this.mBuilder.setGroup(NotificationCompat.GROUP_KEY_SILENT);
                }
                this.mBuilder.setGroupAlertBehavior(this.mGroupAlertBehavior);
            }
        }
    }

    private static List<String> combineLists(List<String> list, List<String> list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        ArraySet arraySet = new ArraySet(list.size() + list2.size());
        arraySet.addAll(list);
        arraySet.addAll(list2);
        return new ArrayList(arraySet);
    }

    private static List<String> getPeople(List<Person> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Person> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().resolveToLegacyUri());
        }
        return arrayList;
    }

    @Override // androidx.core.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }

    public Notification build() {
        Bundle extras;
        RemoteViews makeHeadsUpContentView;
        RemoteViews makeBigContentView;
        NotificationCompat.Style style = this.mBuilderCompat.mStyle;
        if (style != null) {
            style.apply(this);
        }
        RemoteViews makeContentView = style != null ? style.makeContentView(this) : null;
        Notification buildInternal = buildInternal();
        if (makeContentView != null) {
            buildInternal.contentView = makeContentView;
        } else if (this.mBuilderCompat.mContentView != null) {
            buildInternal.contentView = this.mBuilderCompat.mContentView;
        }
        if (Build.VERSION.SDK_INT >= 16 && style != null && (makeBigContentView = style.makeBigContentView(this)) != null) {
            buildInternal.bigContentView = makeBigContentView;
        }
        if (Build.VERSION.SDK_INT >= 21 && style != null && (makeHeadsUpContentView = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this)) != null) {
            buildInternal.headsUpContentView = makeHeadsUpContentView;
        }
        if (Build.VERSION.SDK_INT >= 16 && style != null && (extras = NotificationCompat.getExtras(buildInternal)) != null) {
            style.addCompatExtras(extras);
        }
        return buildInternal;
    }

    private void addAction(NotificationCompat.Action action) {
        Notification.Action.Builder builder;
        Bundle bundle;
        if (Build.VERSION.SDK_INT >= 20) {
            IconCompat iconCompat = action.getIconCompat();
            if (Build.VERSION.SDK_INT >= 23) {
                builder = new Notification.Action.Builder(iconCompat != null ? iconCompat.toIcon() : null, action.getTitle(), action.getActionIntent());
            } else {
                builder = new Notification.Action.Builder(iconCompat != null ? iconCompat.getResId() : 0, action.getTitle(), action.getActionIntent());
            }
            if (action.getRemoteInputs() != null) {
                for (android.app.RemoteInput remoteInput : RemoteInput.fromCompat(action.getRemoteInputs())) {
                    builder.addRemoteInput(remoteInput);
                }
            }
            if (action.getExtras() != null) {
                bundle = new Bundle(action.getExtras());
            } else {
                bundle = new Bundle();
            }
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            bundle.putInt("android.support.action.semanticAction", action.getSemanticAction());
            if (Build.VERSION.SDK_INT >= 28) {
                builder.setSemanticAction(action.getSemanticAction());
            }
            if (Build.VERSION.SDK_INT >= 29) {
                builder.setContextual(action.isContextual());
            }
            bundle.putBoolean("android.support.action.showsUserInterface", action.getShowsUserInterface());
            builder.addExtras(bundle);
            this.mBuilder.addAction(builder.build());
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.mBuilder, action));
        }
    }

    protected Notification buildInternal() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mBuilder.build();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Notification build = this.mBuilder.build();
            if (this.mGroupAlertBehavior != 0) {
                if (build.getGroup() != null && (build.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                    removeSoundAndVibration(build);
                }
                if (build.getGroup() != null && (build.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                    removeSoundAndVibration(build);
                }
            }
            return build;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.mBuilder.setExtras(this.mExtras);
            Notification build2 = this.mBuilder.build();
            RemoteViews remoteViews = this.mContentView;
            if (remoteViews != null) {
                build2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.mBigContentView;
            if (remoteViews2 != null) {
                build2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.mHeadsUpContentView;
            if (remoteViews3 != null) {
                build2.headsUpContentView = remoteViews3;
            }
            if (this.mGroupAlertBehavior != 0) {
                if (build2.getGroup() != null && (build2.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                    removeSoundAndVibration(build2);
                }
                if (build2.getGroup() != null && (build2.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                    removeSoundAndVibration(build2);
                }
            }
            return build2;
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.mBuilder.setExtras(this.mExtras);
            Notification build3 = this.mBuilder.build();
            RemoteViews remoteViews4 = this.mContentView;
            if (remoteViews4 != null) {
                build3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.mBigContentView;
            if (remoteViews5 != null) {
                build3.bigContentView = remoteViews5;
            }
            if (this.mGroupAlertBehavior != 0) {
                if (build3.getGroup() != null && (build3.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                    removeSoundAndVibration(build3);
                }
                if (build3.getGroup() != null && (build3.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                    removeSoundAndVibration(build3);
                }
            }
            return build3;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            SparseArray<Bundle> buildActionExtrasMap = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (buildActionExtrasMap != null) {
                this.mExtras.putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, buildActionExtrasMap);
            }
            this.mBuilder.setExtras(this.mExtras);
            Notification build4 = this.mBuilder.build();
            RemoteViews remoteViews6 = this.mContentView;
            if (remoteViews6 != null) {
                build4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.mBigContentView;
            if (remoteViews7 != null) {
                build4.bigContentView = remoteViews7;
            }
            return build4;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            Notification build5 = this.mBuilder.build();
            Bundle extras = NotificationCompat.getExtras(build5);
            Bundle bundle = new Bundle(this.mExtras);
            for (String str : this.mExtras.keySet()) {
                if (extras.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            extras.putAll(bundle);
            SparseArray<Bundle> buildActionExtrasMap2 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (buildActionExtrasMap2 != null) {
                NotificationCompat.getExtras(build5).putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, buildActionExtrasMap2);
            }
            RemoteViews remoteViews8 = this.mContentView;
            if (remoteViews8 != null) {
                build5.contentView = remoteViews8;
            }
            RemoteViews remoteViews9 = this.mBigContentView;
            if (remoteViews9 != null) {
                build5.bigContentView = remoteViews9;
            }
            return build5;
        }
        return this.mBuilder.getNotification();
    }

    private void removeSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }
}
