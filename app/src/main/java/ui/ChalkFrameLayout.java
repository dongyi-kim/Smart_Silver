package ui;

import android.content.Context;
import android.util.AttributeSet;

import cdmst.smartsilver.R;
import src.viewes.FrameView;

/**
 * Created by waps12b on 15. 5. 6..
 */
public class ChalkFrameLayout extends FrameView {
    public ChalkFrameLayout(Context context) {
        this(context, null);
    }

    public ChalkFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChalkFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBackgroundImage(R.drawable.img_chalkboard_2);
        //setLayout(R.layout.view_chalk_board);
        setPadding(40,40,40,40);
    }

    @Override
    protected void init() {

    }
}
