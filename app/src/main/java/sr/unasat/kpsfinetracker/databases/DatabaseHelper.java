package sr.unasat.kpsfinetracker.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.kpsfinetracker.entities.User;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "financial.db";
    private static final int DATABASE_VERSION = 1;

    public static final String ID = "id";

    public static final String USER_TABLE = "user";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";

    private static final String SQL_USER_TABLE_QUERY = "create table user(id INTEGER PRIMARY KEY, username STRING NOT NULL UNIQUE, password STRING NOT NULL)";
   // private static final String SQL_TRANSACTION_TABLE_QUERY = "create table transactionT (id INTEGER PRIMARY KEY, amount REAL NOT NULL, type STRING NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setDefaultCredentials();
    }

    private void setDefaultCredentials() {
        User user = findOneRecordByUsername("admin");
        if (user != null) {
            return;
        }
        //Set default username and password
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_USERNAME, "admin");
        contentValues.put(USER_PASSWORD, "admin");
        insertOneRecord(USER_TABLE, contentValues);
    }

    ;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_USER_TABLE_QUERY);
       // db.execSQL(SQL_TRANSACTION_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertOneRecord(String tableName, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(tableName, null, contentValues);
        db.close();
        //return the row ID of the newly inserted row, or -1 if an error occurred
        return rowId;
    }

    public boolean insertMultipleRecord(String tableName, List<ContentValues> contentValuesList) {
        SQLiteDatabase db = getWritableDatabase();
        long countOnSucces = 0;
        long rowId = 0;
        for (ContentValues contentValues : contentValuesList) {
            rowId = db.insert(tableName, null, contentValues);
            countOnSucces = (rowId == 1 ? countOnSucces++ : countOnSucces);
        }
        boolean isSuccess = (countOnSucces > 0 && contentValuesList.size() == countOnSucces);
        db.close();
        //return the true id all inserts where succesfull
        return isSuccess;
    }

    public User findOneRecordByUsername(String username) {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s'", USER_TABLE, USER_USERNAME, username);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return user;
    }

    public List<User> findAllRecords(String table) {
        List<User> users = new ArrayList<> ();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s", table);
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            users.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }
        db.close();
        return users;
    }

    public Boolean CheckUsername(String username){
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=?", new String[]{username});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }

    }

    public Boolean CheckLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=? AND password=?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}