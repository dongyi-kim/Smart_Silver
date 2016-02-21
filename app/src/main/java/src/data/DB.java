package src.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.*;
import android.database.sqlite.*;

import java.util.ArrayList;

/**
 * Created by waps12b on 15. 5. 19..
 */
public class DB extends SQLiteOpenHelper {
    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    //destination path (location) of our database on device


    public static final String DB_PATH = "/data/data/senior_smart/databases";
    public static final String DB_NAME ="db_silver_math";// Database name

    public static final String TABLE_RESULT = "result";
    public static final String TABLE_STATISTICS = "statistics";

    public static final String SQL_TIME_NOW = " DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME') ";



    private final Context mContext;

    private static DB obj = null;
    static {

    }
    public static synchronized final void INIT(Context context)
    {
        if(obj!=null)
            return;
        obj = new DB(context, DB_NAME, null, 1);
    }



    public static void insertResult(ResultData data){
        String query = String.format("INSERT INTO %s VALUES %s ;", TABLE_RESULT, data.toString());
        DB.execSQL(query);
    }

    public static synchronized void insertStatistics(StatisticsData data){
        if(data == null)
            return;
        String query = String.format("INSERT INTO %s VALUES %s ;", TABLE_STATISTICS , data.toString() );
        DB.execSQL(query);
    }


    /* getResultCount()
     * get the number of records in TABLE_RESULT
     */
    public static int getResultCount(){
        return getResultCount(null);
    }
    public static int getResultCount(String where){
        synchronized (obj){
            String query = String.format("SELECT count(*) FROM %s ", TABLE_RESULT);
            if(where != null)
                query += String.format(" WHERE %s ", where);
            query += " ; ";

            Cursor cursor = null;
            SQLiteDatabase db = null;
            int result = 0;

            try{
                db = obj.getWritableDatabase();
                cursor = db.rawQuery(query, null);
                cursor.moveToFirst();

                if (cursor.getCount() > 0 && cursor.getColumnCount() > 0)
                    result = cursor.getInt(0);
            }catch (Exception ex){

            }finally {
                if(cursor != null)
                    cursor.close();
                if(db != null)
                    db.close();
            }
            return result;
        }
    }

    public static StatisticsData[] getStatisticsData(String query){
        synchronized ( obj ){
            Cursor cursor = null;
            SQLiteDatabase db = null;
            StatisticsData[] result = null;
            try{
                db = obj.getWritableDatabase();
                cursor = db.rawQuery(query, null);

                cursor.moveToFirst();

                ArrayList<StatisticsData> list = new ArrayList<>();
                while(!cursor.isAfterLast())
                {
                    int step    = cursor.getInt(1);
                    int success = cursor.getInt(2);
                    int fail    = cursor.getInt(3);
                    long seconds= cursor.getLong(4);
                    String userId = cursor.getString(6);

                    list.add(new StatisticsData(step,success,fail,seconds,userId));
                    cursor.moveToNext();
                }
                cursor.close();

                if( list.size() > 0 )
                {
                    result = new StatisticsData[list.size()];
                    for(int i=0; i<list.size(); i++)
                        result[i] = list.get(i);
                }
            }catch (Exception ex){
                result = null;
            }finally {
                if(cursor != null)
                    cursor.close();
                if(db != null)
                    db.close();
            }
            return result;
        }
    }


    public static ResultData[] getResultData(String query){
        synchronized ( obj ){
            Cursor cursor = null;
            SQLiteDatabase db = null;
            ResultData[] result = null;
            try{
                db = obj.getWritableDatabase();
                cursor = db.rawQuery(query, null);

                cursor.moveToFirst();

                ArrayList<ResultData> list = new ArrayList<>();
                while(!cursor.isAfterLast()){
                    //int id = cursor.getInt(0);
                    ResultData data= new ResultData( cursor.getInt(1), cursor.getInt(2), cursor.getInt(3) );
                    data.millisec = cursor.getInt(4);
                    data.isSuccess = ( Integer.parseInt(cursor.getString(5)) > 0 );
                    data.tryCount =  Math.abs(Integer.parseInt(cursor.getString(5)));
                    if(data.tryCount == 0){
                        data.tryCount = 1;
                    }
                    data.timestamp = ( cursor.getString(6) );
                    list.add(data);
                    cursor.moveToNext();
                }
                cursor.close();

                if( list.size() > 0 )
                {
                    result = new ResultData[list.size()];
                    for(int i=0; i<list.size(); i++)
                        result[i] = list.get(i);
                }
            }catch (Exception ex){
                result = null;
            }finally {
                if(cursor != null)
                    cursor.close();
                if(db != null)
                    db.close();
            }
            return result;
        }
    }



    // just execute query to DB
    private static synchronized void execSQL(String query){
        synchronized (obj){
            SQLiteDatabase db = null;
            try{
                db = obj.getWritableDatabase();
                db.execSQL(query);
                db.close();
            }catch (Exception ex){

            }finally {
                if(db!=null)
                    db.close();
            }
        }
    }





    //Common Meaningless
    private DB(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {//when DB doesn't exist.
        db.execSQL("CREATE TABLE " + TABLE_RESULT       +" ( id INTEGER PRIMARY KEY AUTOINCREMENT, step INTEGER, level INTEGER, stage INTEGER, millisecond INTEGER, success INTEGER, timestamp DATE, user_id TEXT);");
        db.execSQL("CREATE TABLE " + TABLE_STATISTICS   +" ( id INTEGER PRIMARY KEY AUTOINCREMENT, step INTEGER, success INTEGER, fail INTEGER, second INTEGER, timestamp DATE, user_id TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}

