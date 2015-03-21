package src.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by waps12b on 15. 3. 1..
 */
public class FrameDialog extends Dialog{

    protected final int iLayoytID;

    protected FrameDialog(Context context, int iLayoutID){
        super(context);
        this.iLayoytID = iLayoutID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams WindowParams = new WindowManager.LayoutParams();
        WindowParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        WindowParams.dimAmount = 0.8f;
        getWindow().setAttributes(WindowParams);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);
        setContentView(iLayoytID);
    }


}
