package ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.webkit.WebSettings;

import cdmst.smartsilver.R;

/**
 * Created by waps12b on 15. 5. 6..
 */
public class WhiteChalkFrameTextView extends ChalkTextView {
    public WhiteChalkFrameTextView(Context context) {
        this(context, null);
    }

    public WhiteChalkFrameTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public WhiteChalkFrameTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBackgroundImage(R.drawable.img_chalkboard_2_white);
        //setLayout(R.layout.view_chalk_board);
        setPadding(40,40,40,40);
        setTextColor(Color.BLACK);
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int fontSize = Math.min(0, Math.min(h-80,w-80));
        setTextSize(TypedValue.COMPLEX_UNIT_PX,fontSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected void setBackgroundImage(int drawable_id)
    {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackgroundDrawable( getResources().getDrawable(drawable_id) );
        } else {
            this.setBackground( getResources().getDrawable(drawable_id));
        }
    }
}
