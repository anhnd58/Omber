package app1.ducanh.ducanhvn.omber;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tajse on 5/11/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper{

    final static String TABLE_NAME = "user";
    final static String ID = "id";
    final static String NAME = "name";
    final static String TYPE = "type";
    final static String PHONE = "phone";
    final static String MAIL = "mail";
    final static String LAT = "lat";
    final static String LNG = "lng";
    final static String[] columns = { ID, NAME, TYPE, PHONE, MAIL, LAT, LNG };

    final private static String CREATE_CMD =

            "CREATE TABLE user (" + ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT NOT NULL" + TYPE + "TEXT NOT NULL" + PHONE + "TEXT NOT NULL"
                    + MAIL + "TEXT NOT NULL" + LAT + LNG;


    final private static String NAME1 = "artist_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseOpenHelper(Context context) {
        super(context, NAME1, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // N/A
    }

    void deleteDatabase() {
        mContext.deleteDatabase(NAME1);
    }
}
