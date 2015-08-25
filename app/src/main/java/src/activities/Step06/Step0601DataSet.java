package src.activities.Step06;
import cdmst.smartsilver.R;

public class Step0601DataSet {

    public int img;
    public String Discription;
    public String txtBtn1;
    public String txtBtn2;
    public String strAns;

    public void setData(int iSeed) {
        Discription = DiscriptionList[iSeed];
        img = imgList[iSeed];
        txtBtn1 = txtBtn1List[iSeed];
        txtBtn2 = txtbtn2List[iSeed];
        strAns = strAnsList[iSeed];
    }

    private static final int imgList[] = {
            R.drawable.calendar_6_1,
            R.drawable.circle_graph_6_1,
            R.drawable.circle_graph_6_1_2,
            R.drawable.circle_graph_6_1_2,
            R.drawable.circle_graph_6_1_3
    };

    private static final String DiscriptionList[] = {
            " 다음 자료는 1주일이 모두 며칠로 이루어져 있는지 나타내는 자료입니다.\n 다음 자료에서 1주일은 며칠입니까.",
            " 다음 자료는 1년이 모두 몇 개월로 이루어져 있는 지 나타내고 있습니다.\n 다음 자료에서 봄은 모두 몇 개월 인가요.",
            " 다음 자료는 1년이 모두 몇 개월로 이루어져 있는 지 나타내고 있습니다.\n 다음 자료에서 1년은 모두 몇 개월 인가요.",
            " 1년의 계절은 모두 몇 개절 인가요.",
            " 오전이란 자정(밤12시)부터 정오(낮12시)까지의 시간이라고 합니다.n 다음 자료에서 하루 중 오전은 모두 몇 시간인가요."
       };

    private static final String txtBtn1List[] = {
            "7일",
            "1개월",
            "12개월",
            "2계절",
            "12시간"
    };
    private static final String txtbtn2List[] = {
            "30일",
            "3개월",
            "24개월",
            "4계절",
            "24시간"
    };

    private static final String strAnsList[] = {
            "7일",
            "3개월",
            "12개월",
            "4계절",
            "12시간"
    };
}