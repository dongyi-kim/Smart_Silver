package src;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.*;
import android.database.sqlite.SQLiteOpenHelper.*;
import android.database.sqlite.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by waps12b on 15. 5. 19..
 */
public class DB extends SQLiteOpenHelper {
    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    //destination path (location) of our database on device


    public static final String DB_PATH = "/data/data/senior_smart/databases";
    public static final String DB_NAME ="db";// Database name
    public static final String TABLE_RESULT = "RESULT_DATA";


    private final Context mContext;

    public static DB obj = null;
    public static final void INIT(Context context)
    {
        if(obj!=null)
            return;
        obj = new DB(context, DB_NAME, null, 1);
    }
    public static boolean QUERY(String query)
    {
        SQLiteDatabase db = obj.getWritableDatabase();
        db.execSQL(query);
        db.close();
        return true;
    }


    public static final String QUERY_GET_LAST = "SELECT * FROM " + TABLE_RESULT + " order by timestamp desc LIMIT 1;";
    public static ResultData[] SELECT(String query)
    {

        SQLiteDatabase db = obj.getWritableDatabase();

        Cursor result = db.rawQuery(query, null);
        if(! result.moveToFirst() )
            return null;

        ArrayList<ResultData> list = new ArrayList<ResultData>();
        while(!result.isAfterLast()){
            int id = result.getInt(0);
            ResultData data= new ResultData( result.getInt(1), result.getInt(2), result.getInt(3) );
            data.millisec = result.getInt(4);
            data.isSuccess = Boolean.parseBoolean( result.getString(5));
            list.add(data);
            result.moveToNext();
        }
        result.close();
        db.close();

        ResultData[] arr = new ResultData[list.size()];
        for(int i=0; i<list.size(); i++)
            arr[i] = list.get(i);
        return arr;
    }

    public static String values(Object obj[])
    {
        String val = "";
        for(int i=0; i<obj.length; i++)
        {
            if(i > 0)
                val += ",";

            if(obj[i].getClass() == String.class)
                val+= "'" + (String)obj[i] + "'";
            else if(obj[i].getClass() == Boolean.class)
                val += "'" + String.valueOf((boolean)obj[i]) + "'";
            else
                val+= obj[i];
        }
        return val;
    }

    public static boolean INSERT(ResultData data)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String val = values(new Object[]{data.iStep, data.iLevel, data.iStage, data.millisec, data.isSuccess});
        String query = "INSERT INTO " + TABLE_RESULT + " values(null, "+ val +", DATETIME('NOW') );";
        QUERY(query);
        return true;
    }

    private DB(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {//when DB doesn't exist.
        db.execSQL("CREATE TABLE " + TABLE_RESULT +"( _id INTEGER PRIMARY KEY AUTOINCREMENT, step INTEGER, level INTEGER, stage INTEGER, second INTEGER, pass TEXT, timestamp DATE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
