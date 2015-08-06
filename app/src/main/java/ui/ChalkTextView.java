package ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import cdmst.smartsilver.R;


/**
 * Created by waps12b on 15. 5. 6..
 */
public class ChalkTextView extends TextView {

    public ChalkTextView(Context context) {
        this(context, null);
    }

    public ChalkTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChalkTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/nanum_pen.otf");
        setTypeface(typeface);
        setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
    }
}
