package hu.uniobuda.nik.gondos_hidvegi;

/**
 * Created by Peter on 2015.04.21..
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rendszergazda on 2015.03.16..
 */
public class Db {
    //Adatbázisnév,verziószám,táblanév
    private final static String DB_NAME = "database";
    private final static int DB_VERSION = 1;
    private final static String TABLE_NAME = "mytable";
    //oszlopnevek...
    private final static String ID = "id";
    private final static String EBRESZTES_IDEJE = "ebresztesideje";
    private final static String UZENET = "uzenet";
    private final static String SZUNDI_SZAM = "szundiszam";


    //új tábla
    private final static String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
            "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            EBRESZTES_IDEJE + " TEXT NOT NULL," +
            UZENET + " TEXT NOT NULL," +
            SZUNDI_SZAM + " INTEGER NOT NULL)";

    //törlés
    private final static String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME;


    private OpenHelp dbHelper;

    public Db(Context context)
    {
        this.dbHelper = new OpenHelp(context);
    }


    public long addUser(String ebresztesideje, String uzenet, int szundiszam) {
        SQLiteDatabase dba = dbHelper.getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put(EBRESZTES_IDEJE, ebresztesideje);
        cvs.put(UZENET, uzenet);
        cvs.put(SZUNDI_SZAM, szundiszam);
        long id = dba.insert(TABLE_NAME, null, cvs);
        dba.close();
        return id;
    }

    public Cursor getAllUser() {
        SQLiteDatabase dba = dbHelper.getReadableDatabase();
        Cursor c = dba.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        dba.close();
        return c;
    }


    public boolean deleteTitle(long id)
    {
        SQLiteDatabase dba = dbHelper.getWritableDatabase();
        return dba.delete(TABLE_NAME, ID + "=" + id, null) > 0;
    }


    private class OpenHelp extends SQLiteOpenHelper {

        private OpenHelp(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

            sqLiteDatabase.execSQL(DROP_TABLE);

            onCreate(sqLiteDatabase);
        }
    }
}

