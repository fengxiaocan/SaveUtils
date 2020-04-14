package com.app.save;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.app.save.SaveDatabase.BLOB_TABLE;
import static com.app.save.SaveDatabase.BOOLEAN_TABLE;
import static com.app.save.SaveDatabase.BYTE_TABLE;
import static com.app.save.SaveDatabase.DOUBLE_TABLE;
import static com.app.save.SaveDatabase.FLOAT_TABLE;
import static com.app.save.SaveDatabase.INT_TABLE;
import static com.app.save.SaveDatabase.KEY_NAME;
import static com.app.save.SaveDatabase.LONG_TABLE;
import static com.app.save.SaveDatabase.SHORT_TABLE;
import static com.app.save.SaveDatabase.STRING_TABLE;
import static com.app.save.SaveDatabase.VALUE_NAME;

/**
 * 快速保存工具类,使用数据库实现,可以保存基本类型数据
 */
public class FastSaveUtils{
    private static FastSaveUtils sInstance;

    private SaveDatabase databaseHelper;
    private SQLiteDatabase writableDatabase;

    public synchronized FastSaveUtils editStart(Context context){
        if(databaseHelper==null){
            databaseHelper=new SaveDatabase(context);
        }
        if(writableDatabase==null||!writableDatabase.isOpen()||writableDatabase.isReadOnly()){
            writableDatabase=databaseHelper.getWritableDatabase();
        }
        return this;
    }

    public synchronized FastSaveUtils editEnd(){
        if(writableDatabase!=null){
            writableDatabase.close();
        }
        if(databaseHelper!=null){
            databaseHelper.close();
        }
        sInstance=null;
        return this;
    }

    public static synchronized FastSaveUtils get(){
        if(sInstance==null){
            sInstance=new FastSaveUtils();
        }
        return sInstance;
    }

    private static boolean isEquals(Object o1,Object o2){
        if(o1==o2){
            return true;
        }
        if(o1==null||o2==null){
            return false;
        }
        return o1.equals(o2);
    }

    /**********STRING_TABLE**********/
    public String getString(String key,String defaultValue){
        Cursor query=query(STRING_TABLE,key);
        if(query.moveToNext()){
            String string=query.getString(2);
            query.close();
            return string;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public String getString(String key){
        return getString(key,null);
    }

    public FastSaveUtils save(String key,String value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value);
        saveContent(STRING_TABLE,key,values);
        return this;
    }


    public static String getString(Context context,String key,String defaultValue){
        try{
            return get().editStart(context).getString(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static String getString(Context context,String key){
        return getString(context,key,null);
    }

    public static void save(Context context,String key,String value){
        get().editStart(context).save(key,value).editEnd();
    }

    /**
     * 检查是否相同,不同就替换
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean checkAndReplace(Context context,String key,String value){
        FastSaveUtils utils=get();
        boolean isCheck;
        if(isEquals(value,utils.editStart(context).getString(key))){
            //如果相等的,返回true
            isCheck=true;
        } else{
            //不相等的保存下来返回false
            isCheck=false;
            utils.save(key,value);
        }
        utils.editEnd();
        return isCheck;
    }

    public FastSaveUtils clearString(String key){
        clearKey(STRING_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllString(){
        clearTable(STRING_TABLE);
        return this;
    }

    public static void clearString(Context context,String key){
        get().editStart(context).clearString(key).editEnd();
    }

    public static void clearAllString(Context context){
        get().editStart(context).clearAllString().editEnd();
    }

    /**********BOOLEAN_TABLE**********/

    public boolean getBoolean(String key,boolean defaultValue){
        Cursor query=query(BOOLEAN_TABLE,key);
        if(query.moveToNext()){
            int value=query.getInt(2);
            query.close();
            return value==1;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public boolean getBoolean(String key){
        return getBoolean(key,false);
    }

    public FastSaveUtils save(String key,boolean value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value ? 1 : 0);
        saveContent(BOOLEAN_TABLE,key,values);
        return this;
    }

    public static boolean getBoolean(Context context,String key,boolean defaultValue){
        try{
            return get().editStart(context).getBoolean(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static boolean getBoolean(Context context,String key){
        return getBoolean(context,key,false);
    }

    public static void save(Context context,String key,boolean value){
        get().editStart(context).save(key,value).editEnd();
    }

    public FastSaveUtils clearBoolean(String key){
        clearKey(BOOLEAN_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllBoolean(){
        clearTable(BOOLEAN_TABLE);
        return this;
    }

    public static void clearBoolean(Context context,String key){
        get().editStart(context).clearBoolean(key).editEnd();
    }

    public static void clearAllBoolean(Context context){
        get().editStart(context).clearAllBoolean().editEnd();
    }

    /**
     * 检查是否相同,不同就替换
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean checkAndReplace(Context context,String key,boolean value){
        FastSaveUtils utils=get();
        boolean isCheck;
        if(value==utils.editStart(context).getBoolean(key,!value)){
            //如果相等的,返回true
            isCheck=true;
        } else{
            //不相等的保存下来返回false
            isCheck=false;
            utils.save(key,value);
        }
        utils.editEnd();
        return isCheck;
    }


    /**********LONG_TABLE**********/

    public FastSaveUtils clearLong(String key){
        clearKey(LONG_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllLong(){
        clearTable(LONG_TABLE);
        return this;
    }

    public static void clearLong(Context context,String key){
        get().editStart(context).clearLong(key).editEnd();
    }

    public static void clearAllLong(Context context){
        get().editStart(context).clearAllLong().editEnd();
    }


    public long getLong(String key,long defaultValue){
        Cursor query=query(LONG_TABLE,key);
        if(query.moveToNext()){
            long value=query.getLong(2);
            query.close();
            return value;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public long getLong(String key){
        return getLong(key,0);
    }

    public FastSaveUtils save(String key,long value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value);
        saveContent(LONG_TABLE,key,values);
        return this;
    }

    public static long getLong(Context context,String key,long defaultValue){
        try{
            return get().editStart(context).getLong(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static long getLong(Context context,String key){
        return getLong(context,key,0);
    }

    public static void save(Context context,String key,long value){
        get().editStart(context).save(key,value).editEnd();
    }

    /**
     * 检查是否相同,不同就替换
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean checkAndReplace(Context context,String key,long value){
        FastSaveUtils utils=get();
        boolean isCheck;
        if(value==utils.editStart(context).getLong(key)){
            //如果相等的,返回true
            isCheck=true;
        } else{
            //不相等的保存下来返回false
            isCheck=false;
            utils.save(key,value);
        }
        utils.editEnd();
        return isCheck;
    }

    /**********INT_TABLE**********/
    public FastSaveUtils clearInt(String key){
        clearKey(INT_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllInt(){
        clearTable(INT_TABLE);
        return this;
    }

    public static void clearInt(Context context,String key){
        get().editStart(context).clearInt(key).editEnd();
    }

    public static void clearAllInt(Context context){
        get().editStart(context).clearAllInt().editEnd();
    }

    public int getInt(String key,int defaultValue){
        Cursor query=query(INT_TABLE,key);
        if(query.moveToNext()){
            int value=query.getInt(2);
            query.close();
            return value;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public int getInt(String key){
        return getInt(key,0);
    }

    public FastSaveUtils save(String key,int value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value);
        saveContent(INT_TABLE,key,values);
        return this;
    }

    public static int getInt(Context context,String key,int defaultValue){
        try{
            return get().editStart(context).getInt(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static int getInt(Context context,String key){
        return getInt(context,key,0);
    }

    public static void save(Context context,String key,int value){
        get().editStart(context).save(key,value).editEnd();
    }

    /**
     * 检查是否相同,不同就替换
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean checkAndReplace(Context context,String key,int value){
        FastSaveUtils utils=get();
        boolean isCheck;
        if(value==utils.editStart(context).getInt(key)){
            //如果相等的,返回true
            isCheck=true;
        } else{
            //不相等的保存下来返回false
            isCheck=false;
            utils.save(key,value);
        }
        utils.editEnd();
        return isCheck;
    }

    /**********FLOAT_TABLE**********/
    public FastSaveUtils clearFloat(String key){
        clearKey(FLOAT_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllFloat(){
        clearTable(FLOAT_TABLE);
        return this;
    }

    public static void clearFloat(Context context,String key){
        get().editStart(context).clearFloat(key).editEnd();
    }

    public static void clearAllFloat(Context context){
        get().editStart(context).clearAllFloat().editEnd();
    }

    public float getFloat(String key,float defaultValue){
        Cursor query=query(FLOAT_TABLE,key);
        if(query.moveToNext()){
            float value=query.getFloat(2);
            query.close();
            return value;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public float getFloat(String key){
        return getFloat(key,0);
    }

    public FastSaveUtils save(String key,float value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value);
        saveContent(FLOAT_TABLE,key,values);
        return this;
    }

    public static float getFloat(Context context,String key,float defaultValue){
        try{
            return get().editStart(context).getFloat(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static float getFloat(Context context,String key){
        return getFloat(context,key,0);
    }

    public static void save(Context context,String key,float value){
        get().editStart(context).save(key,value).editEnd();
    }

    /**
     * 检查是否相同,不同就替换
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean checkAndReplace(Context context,String key,float value){
        FastSaveUtils utils=get();
        boolean isCheck;
        if(value==utils.editStart(context).getFloat(key)){
            //如果相等的,返回true
            isCheck=true;
        } else{
            //不相等的保存下来返回false
            isCheck=false;
            utils.save(key,value);
        }
        utils.editEnd();
        return isCheck;
    }

    /**********BYTE_TABLE**********/
    public FastSaveUtils clearByte(String key){
        clearKey(BYTE_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllByte(){
        clearTable(BYTE_TABLE);
        return this;
    }

    public static void clearByte(Context context,String key){
        get().editStart(context).clearByte(key).editEnd();
    }

    public static void clearAllByte(Context context){
        get().editStart(context).clearAllByte().editEnd();
    }

    public byte getByte(String key,byte defaultValue){
        Cursor query=query(BYTE_TABLE,key);
        if(query.moveToNext()){
            byte value=(byte)query.getInt(2);
            query.close();
            return value;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public byte getByte(String key){
        return getByte(key,(byte)0);
    }

    public FastSaveUtils save(String key,byte value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value);
        saveContent(BYTE_TABLE,key,values);
        return this;
    }

    public static byte getByte(Context context,String key,byte defaultValue){
        try{
            return get().editStart(context).getByte(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static byte getByte(Context context,String key){
        return getByte(context,key,(byte)0);
    }

    public static void save(Context context,String key,byte value){
        get().editStart(context).save(key,value).editEnd();
    }

    /**
     * 检查是否相同,不同就替换
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean checkAndReplace(Context context,String key,byte value){
        FastSaveUtils utils=get();
        boolean isCheck;
        if(value==utils.editStart(context).getByte(key)){
            //如果相等的,返回true
            isCheck=true;
        } else{
            //不相等的保存下来返回false
            isCheck=false;
            utils.save(key,value);
        }
        utils.editEnd();
        return isCheck;
    }

    /**********BLOB_TABLE**********/
    public FastSaveUtils clearBlob(String key){
        clearKey(BLOB_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllBlob(){
        clearTable(BLOB_TABLE);
        return this;
    }

    public static void clearBlob(Context context,String key){
        get().editStart(context).clearBlob(key).editEnd();
    }

    public static void clearAllBlob(Context context){
        get().editStart(context).clearAllBlob().editEnd();
    }

    public byte[] getBlob(String key,byte[] defaultValue){
        Cursor query=query(BLOB_TABLE,key);
        if(query.moveToNext()){
            byte[] value=query.getBlob(2);
            query.close();
            return value;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public byte[] getBlob(String key){
        return getBlob(key,null);
    }

    public FastSaveUtils save(String key,byte[] value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value);
        saveContent(BLOB_TABLE,key,values);
        return this;
    }

    public static byte[] getBlob(Context context,String key,byte[] defaultValue){
        try{
            return get().editStart(context).getBlob(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static byte[] getBlob(Context context,String key){
        return getBlob(context,key,null);
    }

    public static void save(Context context,String key,byte[] value){
        get().editStart(context).save(key,value).editEnd();
    }

    /**********SHORT_TABLE**********/
    public FastSaveUtils clearShort(String key){
        clearKey(SHORT_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllShort(){
        clearTable(SHORT_TABLE);
        return this;
    }

    public static void clearShort(Context context,String key){
        get().editStart(context).clearShort(key).editEnd();
    }

    public static void clearAllShort(Context context){
        get().editStart(context).clearAllShort().editEnd();
    }

    public short getShort(String key,short defaultValue){
        Cursor query=query(SHORT_TABLE,key);
        if(query.moveToNext()){
            short value=query.getShort(2);
            query.close();
            return value;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public short getShort(String key){
        return getShort(key,(short)0);
    }

    public FastSaveUtils save(String key,short value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value);
        saveContent(SHORT_TABLE,key,values);
        return this;
    }

    public static short getShort(Context context,String key,short defaultValue){
        try{
            return get().editStart(context).getShort(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static short getShort(Context context,String key){
        return getShort(context,key,(short)0);
    }

    public static void save(Context context,String key,short value){
        get().editStart(context).save(key,value).editEnd();
    }

    /**
     * 检查是否相同,不同就替换
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean checkAndReplace(Context context,String key,short value){
        FastSaveUtils utils=get();
        boolean isCheck;
        if(value==utils.editStart(context).getShort(key)){
            //如果相等的,返回true
            isCheck=true;
        } else{
            //不相等的保存下来返回false
            isCheck=false;
            utils.save(key,value);
        }
        utils.editEnd();
        return isCheck;
    }

    /**********DOUBLE_TABLE**********/
    public FastSaveUtils clearDouble(String key){
        clearKey(DOUBLE_TABLE,key);
        return this;
    }

    public FastSaveUtils clearAllDouble(){
        clearTable(DOUBLE_TABLE);
        return this;
    }

    public static void clearDouble(Context context,String key){
        get().editStart(context).clearDouble(key).editEnd();
    }

    public static void clearAllDouble(Context context){
        get().editStart(context).clearAllDouble().editEnd();
    }

    public double getDouble(String key,double defaultValue){
        Cursor query=query(DOUBLE_TABLE,key);
        if(query.moveToNext()){
            double value=query.getDouble(2);
            query.close();
            return value;
        } else{
            query.close();
            return defaultValue;
        }
    }

    public double getDouble(String key){
        return getDouble(key,0);
    }

    public FastSaveUtils save(String key,double value){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,key);
        values.put(VALUE_NAME,value);
        saveContent(DOUBLE_TABLE,key,values);
        return this;
    }

    public static double getDouble(Context context,String key,double defaultValue){
        try{
            return get().editStart(context).getDouble(key,defaultValue);
        } finally{
            get().editEnd();
        }
    }

    public static double getDouble(Context context,String key){
        return getDouble(context,key,0d);
    }

    public static void save(Context context,String key,double value){
        get().editStart(context).save(key,value).editEnd();
    }

    /**
     * 检查是否相同,不同就替换
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean checkAndReplace(Context context,String key,double value){
        FastSaveUtils utils=get();
        boolean isCheck;
        if(value==utils.editStart(context).getDouble(key)){
            //如果相等的,返回true
            isCheck=true;
        } else{
            //不相等的保存下来返回false
            isCheck=false;
            utils.save(key,value);
        }
        utils.editEnd();
        return isCheck;
    }

    /**********DOUBLE_TABLE**********/
    private void saveContent(String table,String key,ContentValues value){
        writableDatabase.replace(table,KEY_NAME,value);
    }

    private boolean clearKey(String table,String key){
        writableDatabase.delete(table,KEY_NAME+" = ?",new String[]{key});
        return true;
    }

    private void clearTable(String table){
        writableDatabase.execSQL("DELETE FROM "+table);
    }

    private Cursor query(String table,String key){
        Cursor query=writableDatabase.query(table,null,KEY_NAME+" = ?",new String[]{key},null,null,"id desc");
        return query;
    }
}
