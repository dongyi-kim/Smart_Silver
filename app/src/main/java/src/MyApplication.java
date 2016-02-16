package src;

import android.app.Application;

/**
 * Created by waps12b on 16. 2. 17..
 */
public class MyApplication extends Application {
    public static final Application getInstance(){
        return instance;
    }
    private static MyApplication instance;
    public MyApplication()
    {
        instance = this;
    }
}
