package src.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

import cdmst.smartsilver.R;

/**
 * Created by waps12b on 15. 3. 18..
 */
public class DlgResultMark extends FrameDialog {

    private boolean isOkay;
    private ImageButton ibtnOkay;
    private ImageButton ibtnFail;
    private final Dialog dlg;


    public DlgResultMark(Context context, boolean isOkay)
    {
        super(context, R.layout.dlg_result_mark);
        this.isOkay = isOkay;
        dlg = this;
    }

    public void setIsOkay(boolean isOkay)
    {
        this.isOkay = isOkay;
        if(isOkay)
        {
            ibtnOkay.setVisibility(ImageButton.VISIBLE);
            ibtnFail.setVisibility(ImageButton.INVISIBLE);
        }else
        {
            ibtnOkay.setVisibility(ImageButton.INVISIBLE);
            ibtnFail.setVisibility(ImageButton.VISIBLE);
        }
    }

    private Timer t;
    @Override
    public void show() {
        super.show();
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                dlg.dismiss();
                t.cancel();
            }
        },2000);
    }


    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        ibtnOkay = (ImageButton)findViewById(R.id.ibtn_mark_o);
        ibtnFail = (ImageButton)findViewById(R.id.ibtn_mark_x);

        ibtnOkay.setOnClickListener(eventOnClickButton);
        ibtnFail.setOnClickListener(eventOnClickButton);


        setIsOkay(isOkay);
    }

    public void show(boolean isOkay)
    {
        setIsOkay(isOkay);
        super.show();
    }

    //when click this dialog
    View.OnClickListener eventOnClickButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        dismiss();
        }
    };

}
