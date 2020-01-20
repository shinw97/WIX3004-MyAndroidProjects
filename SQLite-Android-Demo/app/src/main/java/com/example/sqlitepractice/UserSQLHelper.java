package com.example.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlitepractice.UserContract.User;

import java.util.ArrayList;
import java.util.List;

public class UserSQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.TABLE_NAME + "(" +
                    User.COLUMN_PHONE + " TEXT," +
                    User.COLUMN_NAME + " TEXT," +
                    User.COLUMN_EMAIL + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.TABLE_NAME;
    private String[] allColumn = {
            User.COLUMN_PHONE,
            User.COLUMN_NAME,
            User.COLUMN_EMAIL,
            "rowid",
    };

    public UserSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //Add a new record
    public void insertUser(UserRecord userRecord) {
        //Prepare record
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_PHONE, userRecord.getPhone());
        values.put(User.COLUMN_NAME, userRecord.getName());
        values.put(User.COLUMN_EMAIL, userRecord.getEmail());
        //Insert a row
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(User.TABLE_NAME, null, values);
        //Close database connection
        database.close();
    }

    public void deleteRecord(UserRecord userRecord) {
        String name = userRecord.getName();
        SQLiteDatabase database = this.getWritableDatabase();
        String selection = User.COLUMN_NAME + "=?";
        database.delete(User.TABLE_NAME, selection, new String[]{name});
        database.close();
    }

    public void updateRecord(UserRecord oldUserRecord, UserRecord newUserRecord) {
//        ContentValues contentValues = new ContentValues();
//        ContentValues values = new ContentValues();
//        values.put(User.COLUMN_NAME, "'" + newUserRecord.getName() + "'");
//        values.put(User.COLUMN_PHONE, "'" + newUserRecord.getPhone() + "'");
//        values.put(User.COLUMN_EMAIL, "'" + newUserRecord.getEmail() + "'");

        SQLiteDatabase database = this.getWritableDatabase();
        System.out.println(oldUserRecord.getName());
//        database.update(User.TABLE_NAME, contentValues,
//                User.COLUMN_ID + "=" + oldUserRecord.getId(), null);
        database.execSQL("UPDATE " + User.TABLE_NAME +
                "\nSET " +
                User.COLUMN_NAME + " = '" + newUserRecord.getName() + "'," +
                User.COLUMN_PHONE + " = '" + newUserRecord.getPhone() + "'," +
                User.COLUMN_EMAIL + " = '" + newUserRecord.getEmail() + "'" +
                "\nWHERE rowid = " + oldUserRecord.getId());
        database.close();
    }

    //Get all records
    public List<UserRecord> getAllUsers() {
        List<UserRecord> records = new ArrayList<UserRecord>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(User.TABLE_NAME, allColumn, null, null,
                null, null, null);
//        Cursor cursor = database.rawQuery("SELECT * FROM " + User.TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            UserRecord userRecord = new UserRecord();
//            System.out.println(cursor.getLong(cursor.getColumnIndex("id")));
            userRecord.setPhone(cursor.getString(0));
            userRecord.setName(cursor.getString(1));
            userRecord.setEmail(cursor.getString(2));
            userRecord.setId(cursor.getString(3));

            records.add(userRecord);
            cursor.moveToNext();
        }
        return records;
    }
}
