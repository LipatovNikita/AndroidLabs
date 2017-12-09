package android.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelperImpl extends SQLiteOpenHelper implements DataBaseHelper {

    public DataBaseHelperImpl(Context context) {
        super(context, "users", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Users ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username VARCHAR(45) NOT NULL, " +
                "password VARCHAR(20) NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    @Override
    public void addUser(User user) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        database.insert("Users", null, contentValues);
        database.close();
    }

    @Override
    public User getUserByUsername(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query("Users",
                new String[] {"id", "username", "password"}, "username =?",
                new String[] {username},
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        User user = new User(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return user;
    }

    @Override
    public void updateUser(User user) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        database.update("Users", contentValues, "id =?", new String[]{String.valueOf(user.getId())});
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM Users";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    @Override
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Users", null, null);
        db.close();
    }
}
