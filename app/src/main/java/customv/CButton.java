package customv;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import java.lang.reflect.Type;

import cdmst.smartsilver.R;
import src.Setting;


/**
 * Created by waps12b on 15. 3. 29..
 */
public class CButton extends Button {
    public Context context;

    float rippleSpeed = 12f;
    int rippleSize = 3;
    Integer rippleColor;
    OnClickListener onClickListener;
    boolean clickAfterRipple = true;
    int backgroundColor = Color.parseColor("#1E88E5");
    final int disabledBackgroundColor = Color.parseColor("#E2E2E2");
    int beforeBackground;

    // Indicate if user touched this view the last time
    public boolean isLastTouch = false;


    public CButton(Context context)
    {
        super(context);
        this.context = context;

    }

    public CButton( Context context, AttributeSet attrs )
    {
        super(context, attrs);
        this.context = context;

    }

    public CButton( Context context, AttributeSet attrs, int defStyle )
    {
        super(context, attrs, defStyle);
        this.context = context;
    }


    @Override
    public void onFinishInflate()
    {
        super.onFinishInflate();
        this.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public void setFontStyle(int TypeFaceStyle)
    {
        this.setTypeface(Typeface.create(Setting.CustomFont, TypeFaceStyle));
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        onClickListener = l;
    }

    /**
     * Make a dark color to ripple effect
     * @return
     */

    protected int makePressColor(){
        return Color.parseColor("#88DDDDDD");
    }


    // ### RIPPLE EFFECT ###

    float x = -1, y = -1;
    float radius = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        if (isEnabled()) {
            isLastTouch = true;
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                radius = getHeight() / rippleSize;
                x = event.getX();
                y = event.getY();
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                radius = getHeight() / rippleSize;
                x = event.getX();
                y = event.getY();
                if (!((event.getX() <= getWidth() && event.getX() >= 0) && (event
                        .getY() <= getHeight() && event.getY() >= 0))) {
                    isLastTouch = false;
                    x = -1;
                    y = -1;
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if ((event.getX() <= getWidth() && event.getX() >= 0)
                        && (event.getY() <= getHeight() && event.getY() >= 0)) {
                    radius++;
                    if(!clickAfterRipple && onClickListener != null){
                        onClickListener.onClick(this);
                    }
                } else {
                    isLastTouch = false;
                    x = -1;
                    y = -1;
                }
            }else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                isLastTouch = false;
                x = -1;
                y = -1;
            }
        }
        return true;
    }
}
