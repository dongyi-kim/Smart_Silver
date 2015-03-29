package src.viewes;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by waps12b on 15. 3. 1..
 */
public abstract class FrameView extends LinearLayout
{

    public final Context context;

    protected FrameView(Context context)
    { // when create custom-view from include view
        super(context);
        this.context = context;
    }

    public FrameView( Context context, AttributeSet attrs )
    {
        super(context, attrs);
        this.context = context;
    }

    public FrameView( Context context, AttributeSet attrs, int defStyle )
    {
        super(context, attrs, defStyle);
        this.context = context;
    }

    protected abstract void init();

    protected void setLayout(int layoutID){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflat;
        inflat = (LayoutInflater) getContext().getSystemService(infService);
        inflat.inflate(layoutID, this, true);
    }





    @Override
    public void onFinishInflate()
    {
        super.onFinishInflate();
    }

    @Override
    public void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}

