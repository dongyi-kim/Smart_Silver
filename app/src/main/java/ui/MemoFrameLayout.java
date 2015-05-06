package ui;

import android.content.Context;
import android.util.AttributeSet;

import cdmst.smartsilver.R;
import src.viewes.FrameView;

/**
 * Created by waps12b on 15. 5. 6..
 */
public class MemoFrameLayout extends FrameView {
    public MemoFrameLayout(Context context) {
        this(context, null);
    }

    public MemoFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MemoFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBackgroundImage(R.drawable.img_memo_sticker);
        setPadding(12,12,12,12);
    }

    @Override
    protected void init() {

    }
}
