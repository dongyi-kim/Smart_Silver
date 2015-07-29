package src.activities.Step06;
import cdmst.smartsilver.R;

public class Step0601DataSet {

    public int img;
    public String Discription;
    public String txtBtn1;
    public String txtBtn2;
    public String strAns;

    public void setData(int iSeed) {
        int rand = (int)(Math.random() * 3.0); // 0 ~ 2
        Discription = DiscriptionList[iSeed][rand];
        img = imgList[iSeed][rand];
        txtBtn1 = txtBtn1List[iSeed][rand];
        txtBtn2 = txtbtn2List[iSeed][rand];
        strAns = strAnsList[iSeed][rand];
    }

    private static final int imgList[][] = {
            { R.drawable.circle_graph_6_1_2,R.drawable.circle_graph_6_1_2,R.drawable.circle_graph_6_1_2 },
            { R.drawable.calendar_6_1, R.drawable.calendar_6_1, R.drawable.calendar_6_1, },
            { R.drawable.circle_graph_6_1_spring, R.drawable.circle_graph_6_1_autumn, R.drawable.circle_graph_6_1_summer, },
            { R.drawable.circle_graph_6_1_2, R.drawable.circle_graph_6_1_2, R.drawable.circle_graph_6_1_2, },
            { R.drawable.circle_graph_6_1_3, R.drawable.circle_graph_6_1_3, R.drawable.circle_graph_6_1_3, }
    };

    private static final String DiscriptionList[][] = {
            { "다음 자료는 1년이 모두 몇 개월로 이루어져 있는 지 나타내고 있습니다. 다음 자료에서 봄은 모두 몇 개월인지 누르세요!", "다음 자료는 1년이 모두 몇 개월로 이루어져 있는 지 나타내고 있습니다. 다음 자료에서 여름은 모두 몇 개월인지 누르세요!", "다음 자료는 1년이 모두 몇 개월로 이루어져 있는 지 나타내고 있습니다. 다음 자료에서 가을은 모두 몇 개월인지 누르세요!", },
            { "다음은 어느 해의 달력입니다. 목요일이 되는 날짜는 모두 몇 개 입니까?", "다음은 어느 해의 달력입니다. 월요일이 되는 날짜는 모두 몇 개 입니까?", "다음은 어느 해의 달력입니다. 일요일이 되는 날짜는 모두 몇 개 입니까?" },
            { "다음 표에서 3월부터 5월은 무슨 계절입니까?", "다음 표에서 9월부터 11월은 무슨 계절입니까?", "다음 표에서 6월부터 8월은 무슨 계절입니까?", },
            { "다음 표에서 3월부터 8월까지는 몇 개의 계절이 지났습니까?", "다음 표에서 6월부터 11월까지는 몇 개의 계절이 지났습니까?", "다음 표에서 3월부터 11월까지는 몇 개의 계절이 지났습니까?" },
            { "오전이란 자정(밤12시)부터 정오(낮12시)까지의 시간이라고 합니다. 다음 그림에서 하루 중 오전은 모두 몇 시간인가요?", "오후란 정오(12시)부터 자정(24시) 까지의 시간이라고 합니다. 다음 그림에서 하루 중 오후는 모두 몇 시간인가요?", "다음 그림에서 12시부터 24시까지는 어디에 해당되나요?" }
    };

    private static final String txtBtn1List[][] = {
            { "3개월","3개월","3개월" },
            { "4개","4개","4개", },
            { "봄","여름","여름" },
            { "2계절","2계절","2계절", },
            { "12시간","12시간","오전" }
    };
    private static final String txtbtn2List[][] = {
            { "4개월","4개월","4개월" },
            { "5개","5개","5개", },
            { "여름","가을","가을" },
            { "3계절","3계절","3계절", },
            { "24시간","24시간","오후" }
    };

    private static final String strAnsList[][] = {
            { "3개월","3개월","3개월" },
            { "4개", "5개", "5개", },
            { "봄", "가을", "여름" },
            { "2계절","2계절","3계절" },
            { "12시간","12시간","오후" }
    };
}
