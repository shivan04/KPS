package sr.unasat.kpsfinetracker.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sr.unasat.kpsfinetracker.fragments.LicensePlateFragment;

public class DatabaseHelperSearch extends SQLiteOpenHelper {

    private static final String DB_NAME = "Users.db";
    private static final String DB_TABLE = "Users_Table";

    //columns
    private static final String ID = "ID";
    private static final String NAME = "NAME";

    public static final String CREATE_TABLE = " CREATE TABLE " + DB_TABLE + "( "+
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT " + ")";

    public DatabaseHelperSearch(Context databaseHelperSearch){
        super(databaseHelperSearch, DB_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + DB_TABLE);

        onCreate(sqLiteDatabase);

    }

    //create method to insert data
    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; //if result = -1 data doesn't insert
    }
    //create method to view Data
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " +DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    //if you want to search from query you can do something like this
    public Cursor searchUsers(String text){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE+" WHERE " +NAME+ " Like '%"+text+"%'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}
