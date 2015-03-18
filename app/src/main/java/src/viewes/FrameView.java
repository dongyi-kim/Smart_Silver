package src.viewes;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by waps12b on 15. 3. 1..
 */
public class FrameView extends LinearLayout{

    protected FrameView(Context context, int layoutID){
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

