package src.viewes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by waps12b on 15. 3. 1..
 */
public class FrameView extends LinearLayout
{

    

    protected FrameView(Context context)
    { // when create custom-view from include view
        super(context);
    }

    protected FrameView(Context context, int layoutID)
    {   //when create custom-view dynamically
        super(context);
        setLayout(layoutID);
    }

    protected void setLayout(int layoutID){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflat;
        inflat = (LayoutInflater) getContext().getSystemService(infService);
        inflat.inflate(layoutID, this, true);
    }
}

