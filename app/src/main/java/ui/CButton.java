package ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import cdmst.smartsilver.R;
import src.viewes.FrameView;

/**
 * Created by waps12b on 15. 4. 7..
 */
public class CButton extends FrameView {

    public TextView vText;

    public CButton(Context context) {
        this(context, null);
    }

    public CButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // real work here
        setLayout(R.layout.ui_button);
        vText = (TextView)findViewById(R.id.text);

        vText.setText(context.obtainStyledAttributes( attrs, R.styleable.CButton ).getString( R.styleable.CButton_text));
        vText.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.obtainStyledAttributes( attrs, R.styleable.CButton ).getDimension( R.styleable.CButton_textSize, R.dimen.wp5));

    }

    @Override
    protected void init() {

//        View ripple = findViewById(R.id.ripple);
//        ripple.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                // handle me
//            }
//        });


    }
}


