package org.greenrobot.greendao.query;

import android.database.sqlite.SQLiteDatabase;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.p091rx.RxQuery;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class QueryBuilder<T> {
    public static boolean LOG_SQL;
    public static boolean LOG_VALUES;
    private final AbstractDao<T, ?> dao;
    private boolean distinct;
    private final List<Join<T, ?>> joins;
    private Integer limit;
    private Integer offset;
    private StringBuilder orderBuilder;
    private String stringOrderCollation;
    private final String tablePrefix;
    private final List<Object> values;
    private final WhereCollector<T> whereCollector;

    public static <T2> QueryBuilder<T2> internalCreate(AbstractDao<T2, ?> abstractDao) {
        return new QueryBuilder<>(abstractDao);
    }

    protected QueryBuilder(AbstractDao<T, ?> abstractDao) {
        this(abstractDao, ExifInterface.GPS_DIRECTION_TRUE);
    }

    protected QueryBuilder(AbstractDao<T, ?> abstractDao, String str) {
        this.dao = abstractDao;
        this.tablePrefix = str;
        this.values = new ArrayList();
        this.joins = new ArrayList();
        this.whereCollector = new WhereCollector<>(abstractDao, str);
        this.stringOrderCollation = " COLLATE NOCASE";
    }

    private void checkOrderBuilder() {
        StringBuilder sb = this.orderBuilder;
        if (sb == null) {
            this.orderBuilder = new StringBuilder();
        } else if (sb.length() > 0) {
            this.orderBuilder.append(",");
        }
    }

    public QueryBuilder<T> distinct() {
        this.distinct = true;
        return this;
    }

    public QueryBuilder<T> preferLocalizedStringOrder() {
        if (this.dao.getDatabase().getRawDatabase() instanceof SQLiteDatabase) {
            this.stringOrderCollation = " COLLATE LOCALIZED";
        }
        return this;
    }

    public QueryBuilder<T> stringOrderCollation(String str) {
        if (this.dao.getDatabase().getRawDatabase() instanceof SQLiteDatabase) {
            if (str != null && !str.startsWith(" ")) {
                str = " " + str;
            }
            this.stringOrderCollation = str;
        }
        return this;
    }

    public QueryBuilder<T> where(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        this.whereCollector.add(whereCondition, whereConditionArr);
        return this;
    }

    public QueryBuilder<T> whereOr(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        this.whereCollector.add(m4156or(whereCondition, whereCondition2, whereConditionArr), new WhereCondition[0]);
        return this;
    }

    /* renamed from: or */
    public WhereCondition m4156or(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.whereCollector.combineWhereConditions(" OR ", whereCondition, whereCondition2, whereConditionArr);
    }

    public WhereCondition and(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.whereCollector.combineWhereConditions(" AND ", whereCondition, whereCondition2, whereConditionArr);
    }

    public <J> Join<T, J> join(Class<J> cls, Property property) {
        return join(this.dao.getPkProperty(), cls, property);
    }

    public <J> Join<T, J> join(Property property, Class<J> cls) {
        AbstractDao<?, ?> dao = this.dao.getSession().getDao(cls);
        return addJoin(this.tablePrefix, property, dao, dao.getPkProperty());
    }

    public <J> Join<T, J> join(Property property, Class<J> cls, Property property2) {
        return addJoin(this.tablePrefix, property, this.dao.getSession().getDao(cls), property2);
    }

    public <J> Join<T, J> join(Join<?, T> join, Property property, Class<J> cls, Property property2) {
        return addJoin(join.tablePrefix, property, this.dao.getSession().getDao(cls), property2);
    }

    private <J> Join<T, J> addJoin(String str, Property property, AbstractDao<J, ?> abstractDao, Property property2) {
        Join<T, J> join = new Join<>(str, property, abstractDao, property2, "J" + (this.joins.size() + 1));
        this.joins.add(join);
        return join;
    }

    public QueryBuilder<T> orderAsc(Property... propertyArr) {
        orderAscOrDesc(" ASC", propertyArr);
        return this;
    }

    public QueryBuilder<T> orderDesc(Property... propertyArr) {
        orderAscOrDesc(" DESC", propertyArr);
        return this;
    }

    private void orderAscOrDesc(String str, Property... propertyArr) {
        String str2;
        for (Property property : propertyArr) {
            checkOrderBuilder();
            append(this.orderBuilder, property);
            if (String.class.equals(property.type) && (str2 = this.stringOrderCollation) != null) {
                this.orderBuilder.append(str2);
            }
            this.orderBuilder.append(str);
        }
    }

    public QueryBuilder<T> orderCustom(Property property, String str) {
        checkOrderBuilder();
        append(this.orderBuilder, property).append(' ');
        this.orderBuilder.append(str);
        return this;
    }

    public QueryBuilder<T> orderRaw(String str) {
        checkOrderBuilder();
        this.orderBuilder.append(str);
        return this;
    }

    protected StringBuilder append(StringBuilder sb, Property property) {
        this.whereCollector.checkProperty(property);
        sb.append(this.tablePrefix);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append('\'');
        sb.append(property.columnName);
        sb.append('\'');
        return sb;
    }

    public QueryBuilder<T> limit(int i) {
        this.limit = Integer.valueOf(i);
        return this;
    }

    public QueryBuilder<T> offset(int i) {
        this.offset = Integer.valueOf(i);
        return this;
    }

    public Query<T> build() {
        StringBuilder createSelectBuilder = createSelectBuilder();
        int checkAddLimit = checkAddLimit(createSelectBuilder);
        int checkAddOffset = checkAddOffset(createSelectBuilder);
        String sb = createSelectBuilder.toString();
        checkLog(sb);
        return Query.create(this.dao, sb, this.values.toArray(), checkAddLimit, checkAddOffset);
    }

    public CursorQuery buildCursor() {
        StringBuilder createSelectBuilder = createSelectBuilder();
        int checkAddLimit = checkAddLimit(createSelectBuilder);
        int checkAddOffset = checkAddOffset(createSelectBuilder);
        String sb = createSelectBuilder.toString();
        checkLog(sb);
        return CursorQuery.create(this.dao, sb, this.values.toArray(), checkAddLimit, checkAddOffset);
    }

    private StringBuilder createSelectBuilder() {
        StringBuilder sb = new StringBuilder(SqlUtils.createSqlSelect(this.dao.getTablename(), this.tablePrefix, this.dao.getAllColumns(), this.distinct));
        appendJoinsAndWheres(sb, this.tablePrefix);
        StringBuilder sb2 = this.orderBuilder;
        if (sb2 != null && sb2.length() > 0) {
            sb.append(" ORDER BY ");
            sb.append((CharSequence) this.orderBuilder);
        }
        return sb;
    }

    private int checkAddLimit(StringBuilder sb) {
        if (this.limit == null) {
            return -1;
        }
        sb.append(" LIMIT ?");
        this.values.add(this.limit);
        return this.values.size() - 1;
    }

    private int checkAddOffset(StringBuilder sb) {
        if (this.offset == null) {
            return -1;
        }
        if (this.limit == null) {
            throw new IllegalStateException("Offset cannot be set without limit");
        }
        sb.append(" OFFSET ?");
        this.values.add(this.offset);
        return this.values.size() - 1;
    }

    public DeleteQuery<T> buildDelete() {
        if (!this.joins.isEmpty()) {
            throw new DaoException("JOINs are not supported for DELETE queries");
        }
        String tablename = this.dao.getTablename();
        StringBuilder sb = new StringBuilder(SqlUtils.createSqlDelete(tablename, null));
        appendJoinsAndWheres(sb, this.tablePrefix);
        String replace = sb.toString().replace(this.tablePrefix + ".\"", '\"' + tablename + "\".\"");
        checkLog(replace);
        return DeleteQuery.create(this.dao, replace, this.values.toArray());
    }

    public CountQuery<T> buildCount() {
        StringBuilder sb = new StringBuilder(SqlUtils.createSqlSelectCountStar(this.dao.getTablename(), this.tablePrefix));
        appendJoinsAndWheres(sb, this.tablePrefix);
        String sb2 = sb.toString();
        checkLog(sb2);
        return CountQuery.create(this.dao, sb2, this.values.toArray());
    }

    private void checkLog(String str) {
        if (LOG_SQL) {
            DaoLog.m4136d("Built SQL for query: " + str);
        }
        if (LOG_VALUES) {
            DaoLog.m4136d("Values for query: " + this.values);
        }
    }

    private void appendJoinsAndWheres(StringBuilder sb, String str) {
        this.values.clear();
        for (Join<T, ?> join : this.joins) {
            sb.append(" JOIN ");
            sb.append(join.daoDestination.getTablename());
            sb.append(' ');
            sb.append(join.tablePrefix);
            sb.append(" ON ");
            SqlUtils.appendProperty(sb, join.sourceTablePrefix, join.joinPropertySource).append('=');
            SqlUtils.appendProperty(sb, join.tablePrefix, join.joinPropertyDestination);
        }
        boolean z = !this.whereCollector.isEmpty();
        if (z) {
            sb.append(" WHERE ");
            this.whereCollector.appendWhereClause(sb, str, this.values);
        }
        for (Join<T, ?> join2 : this.joins) {
            if (!join2.whereCollector.isEmpty()) {
                if (!z) {
                    sb.append(" WHERE ");
                    z = true;
                } else {
                    sb.append(" AND ");
                }
                join2.whereCollector.appendWhereClause(sb, join2.tablePrefix, this.values);
            }
        }
    }

    public List<T> list() {
        return build().list();
    }

    /* renamed from: rx */
    public RxQuery<T> m4157rx() {
        return build().__InternalRx();
    }

    public RxQuery<T> rxPlain() {
        return build().__internalRxPlain();
    }

    public LazyList<T> listLazy() {
        return build().listLazy();
    }

    public LazyList<T> listLazyUncached() {
        return build().listLazyUncached();
    }

    public CloseableListIterator<T> listIterator() {
        return build().listIterator();
    }

    public T unique() {
        return build().unique();
    }

    public T uniqueOrThrow() {
        return build().uniqueOrThrow();
    }

    public long count() {
        return buildCount().count();
    }
}
