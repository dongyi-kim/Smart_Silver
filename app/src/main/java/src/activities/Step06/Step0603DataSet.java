package src.activities.Step06;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-07-05.
 */

public class Step0603DataSet {
    public int img;
    public String Discription;
    public String btnTxt[];
    public String strAns;

    public void setData(int iSeed){
//        Discription = DiscriptionList[iSeed];
  //      img = imgList[iSeed];
    //    btnTxt = txtBtnList[iSeed];
      //  strAns = strAnsList[iSeed];
    }

    private static final int imgList[][] ={
            {R.drawable.gam_6_3, R.drawable.apple_6_3},
            {R.drawable.gam_6_3, R.drawable.bam_6_3},
            {R.drawable.semo_6_3, R.drawable.nemo_6_3}
    };

    private static final String DiscriptionList[] = {
    };

    private static final String strAnsList[] = {"2?떆媛?", "?옞", "4?떆媛?", "?룊?깮?븰援?", "?븘移⑥슫?룞"};

    private static final String txtBtnList[][] = {
            {"2?떆媛?", "5?떆媛?"},
            {"?븘移⑥슫?룞", "?옞", "?룊?깮?븰援?"},
            {"3?떆媛?", "4?떆媛?", "5?떆媛?"},
            {"?옞", "吏묒븞?씪", "?룊?깮?븰援?"},
            {"?븘移⑥슫?룞", "吏묒븞?씪", "?븰援? 怨쇱젣"}
    };
}