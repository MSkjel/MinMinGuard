package tw.fatminmin.xposed.minminguard.orm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "APP_DATA".
 */
public class AppDataDao extends AbstractDao<AppData, String>
{

    public static final String TABLENAME = "APP_DATA";

    public AppDataDao(DaoConfig config)
    {
        super(config);
    }


    public AppDataDao(DaoConfig config, DaoSession daoSession)
    {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists)
    {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"APP_DATA\" (" + //
                "\"PKG_NAME\" TEXT PRIMARY KEY NOT NULL ," + // 0: pkgName
                "\"AD_NETWORKS\" TEXT," + // 1: adNetworks
                "\"BLOCK_NUM\" INTEGER);"); // 2: blockNum
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists)
    {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"APP_DATA\"";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, AppData entity)
    {
        stmt.clearBindings();

        String pkgName = entity.getPkgName();
        if (pkgName != null)
        {
            stmt.bindString(1, pkgName);
        }

        String adNetworks = entity.getAdNetworks();
        if (adNetworks != null)
        {
            stmt.bindString(2, adNetworks);
        }

        Integer blockNum = entity.getBlockNum();
        if (blockNum != null)
        {
            stmt.bindLong(3, blockNum);
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public String readKey(Cursor cursor, int offset)
    {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public AppData readEntity(Cursor cursor, int offset)
    {
        return new AppData( //
                cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // pkgName
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // adNetworks
                cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2) // blockNum
        );
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, AppData entity, int offset)
    {
        entity.setPkgName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setAdNetworks(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBlockNum(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected String updateKeyAfterInsert(AppData entity, long rowId)
    {
        return entity.getPkgName();
    }

    /**
     * @inheritdoc
     */
    @Override
    public String getKey(AppData entity)
    {
        if (entity != null)
        {
            return entity.getPkgName();
        }
        else
        {
            return null;
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable()
    {
        return true;
    }

    /**
     * Properties of entity AppData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties
    {
        public final static Property PkgName = new Property(0, String.class, "pkgName", true, "PKG_NAME");
        public final static Property AdNetworks = new Property(1, String.class, "adNetworks", false, "AD_NETWORKS");
        public final static Property BlockNum = new Property(2, Integer.class, "blockNum", false, "BLOCK_NUM");
    }
}
