package src;

/**
 * Created by waps12b on 15. 3. 18..
 */
public class Utility {

    public static boolean isNumber(String strNumber){
        try
        {
            long lNum = Long.parseLong(strNumber);
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }

    public static void swap(Object a, Object b)
    {
        Object t = a;
        a = b;
        b = t;
    }

}
