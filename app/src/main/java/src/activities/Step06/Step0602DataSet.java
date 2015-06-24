package src.activities.Step06;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-06-22.
 */
public class Step0602DataSet {
    public int img;
    public String Discription;
    public String btnTxt[];
    public String strAns;

    public void setData(int iSeed){
        Discription = DiscriptionList[iSeed];
        img = imgList[iSeed];
        btnTxt = txtBtnList[iSeed];
        strAns = strAnsList[iSeed];
    }

    private static final int imgList[] ={
            R.drawable.circle_graph_6_2,
            R.drawable.circle_graph_6_2,
            R.drawable.circle_graph_6_2,
            R.drawable.circle_graph_6_2_2,
            R.drawable.circle_graph_6_2_2
    };

    private static final String DiscriptionList[] = {
            "다음 자료는 김할머니의 하루 일과를 나타낸 일과표입니다. 김할머니는 하루에 아침운동을 몇 시간씩 하는지 아래 자료에서 찾아 단추를 누르세요!",
            "다음 자료는 예쁜이 할머니의 하루를 나타낸 일과표입니다. 다음 자료에서 예쁜이 할머니가 하루 중 가장 많은 일을 차지하는 것은 무엇입니까?",
            "다음 자료는 예쁜이 할머니의 하루를 나타낸 일과표입니다. 다음 자료에서 예쁜이 할머니는 평생학교에 몇 시간 동안 있나요? 오른쪽 단추를 누르세요!",
            "다음 자료는 예쁜이 할머니가 하루에 하는 일의 양을 표로 나타낸 것입니다. 아래 표에서 예쁜이 할머니가 하루 중 가장 많은 시간을 보내는 일은 무엇인지 찾아 단추를 누르세요!",
            "다음 자료는 예쁜이 할머니가 하루에 하는 일의 양을 표로 나타낸 것입니다. 아래 표에서 예쁜이 할머니가 하루 중 가장 적은 시간을 보내는 일은 무엇인지 찾아 단추를 누르세요!"
    };

    private static final String strAnsList[] = {"2시간", "잠", "4시간", "평생학교", "아침운동"};

    private static final String txtBtnList[][] = {
            {"2시간", "5시간"},
            {"아침운동", "잠", "평생학교"},
            {"3시간", "4시간", "5시간"},
            {"잠", "집안일", "평생학교"},
            {"아침운동", "집안일", "학교 과제"}
    };
}