package com.app.save;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class SaveDatabase extends SQLiteOpenHelper {
    static final String KEY_NAME = "KEY_NAME";
    static final String VALUE_NAME = "VALUE_NAME";

    static final String STRING_TABLE = "FAST_SAVE_STRING";
    static final String BOOLEAN_TABLE = "FAST_SAVE_BOOLEAN";
    static final String INT_TABLE = "FAST_SAVE_INT";
    static final String LONG_TABLE = "FAST_SAVE_LONG";
    static final String FLOAT_TABLE = "FAST_SAVE_FLOAT";
    static final String DOUBLE_TABLE = "FAST_SAVE_DOUBLE";
    static final String BYTE_TABLE = "FAST_SAVE_BYTE";
    static final String SHORT_TABLE = "FAST_SAVE_SHORT";
    static final String BLOB_TABLE = "FAST_SAVE_BLOB";

    public SaveDatabase(@Nullable Context context)
    {
        super(context, "sFastSave.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create(STRING_TABLE));
        db.execSQL(create(BOOLEAN_TABLE));
        db.execSQL(create(INT_TABLE));
        db.execSQL(create(LONG_TABLE));
        db.execSQL(create(FLOAT_TABLE));
        db.execSQL(create(DOUBLE_TABLE));
        db.execSQL(create(BYTE_TABLE));
        db.execSQL(create(SHORT_TABLE));
        db.execSQL(create(BLOB_TABLE));
    }

    private String create(final String table) {
        return "CREATE TABLE " + table + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME +
               " TEXT NOT NULL," + VALUE_NAME + " TEXT)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
