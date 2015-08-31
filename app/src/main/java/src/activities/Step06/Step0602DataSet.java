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
        int rand = (int)(Math.random() * 3.0); // 0 ~ 2
        Discription = DiscriptionList[iSeed][rand];
        img = imgList[iSeed][rand];
        btnTxt = txtBtnList[iSeed][rand];
        strAns = strAnsList[iSeed][rand];
    }

    private static final int imgList[][] ={
            {R.drawable.circle_graph_6_2, R.drawable.circle_graph_6_2, R.drawable.circle_graph_6_2, },
            {R.drawable.circle_graph_6_2, R.drawable.circle_graph_6_2_3,  R.drawable.circle_graph_6_2_3,  },
            {R.drawable.circle_graph_6_2,R.drawable.circle_graph_6_2,R.drawable.circle_graph_6_2,},
            {R.drawable.circle_graph_6_2_4,R.drawable.circle_graph_6_2_6,R.drawable.circle_graph_6_2_6},
            {R.drawable.circle_graph_6_2_5,R.drawable.circle_graph_6_2_6,R.drawable.circle_graph_6_2_6}
    };

    private static final String DiscriptionList[][] = {
            {" 다음 자료는 김할머니의 하루 일과를 나타낸 일과표입니다.\n 김 할머니는 하루에 아침운동을 몇 시간씩 하나요."," 다음 자료는 김 할머니의 하루 일과를 나타낸 일과표입니다.\n 김 할머니의 점심식사 시간은 몇 시 인가요."," 다음 자료는 김 할머니의 하루 일과를 나타낸 일과표입니다.\n김 할머니의 저녁식사 시간은 몇 시 인가요."},
            {" 다음 자료는 예쁜이 할머니의 하루를 나타낸 일과표입니다.\n 예쁜이 할머니가 하루 중 가장 많이 하는 일은 무엇인가요."," 다음 자료는 예쁜이 할머니의 하루를 나타낸 일과표입니다.\n 예쁜이 할머니가 하루 중 두 번째로 많이 하는 일은 무엇인가요.", " 다음 자료는 예쁜이 할머니의 하루를 나타낸 일과표입니다.\n 예쁜이 할머니가 하루 중 세 번째로 많이 하는 일은 무엇인가요."},
            {" 다음 자료는 예쁜이 할머니의 하루를 나타낸 일과표입니다.\n 예쁜이 할머니는 평생학교에 몇 시간동안 있나요."," 다음 자료는 예쁜이 할머니의 하루를 나타낸 일과표입니다.\n 예쁜이 할머니는 보통 집안일을 몇 시간 동안 합니까", " 다음 자료는 예쁜이 할머니의 하루를 나타낸 일과표입니다.\n 예쁜이 할머니는 보통 아침운동을 몇 시간동안 합니까."},
            {" 다음 자료는 예쁜이 할머니가 하루에 하는 일을 원 그래프로 나타낸 것입니다.\n 예쁜이 할머니가 하루 중 가장 많이 하는 일은 무엇인가요.", " 다음 자료는 예쁜이 할머니가 하루에 하는 일은 원 그래프로 나타낸 것입니다.\n 예쁜이 할머니가 하루 중 가장 많이 하는 일은 무엇인가요.", " 다음 자료는 예쁜이 할머니가 하루에 하는 일은 원 그래프로 나타낸 것입니다.\n 예쁜이 할머니가 하루 중 가장 많이 하는 일은 무엇인가요."},
            {" 다음 자료는 예쁜이 할머니가 하루에 하는 일의 양을 표로 나타낸 것입니다.\n 예쁜이 할머니가 하루 중 가장 적게 하는 일은 무엇인가요.", " 다음 자료는 예쁜이 할머니가 하루에 하는 일의 양을 표로 나타낸 것입니다.\n 예쁜이 할머니가 하루 중 가장 적게 하는 일은 무엇인가요.", " 다음 자료는 예쁜이 할머니가 하루에 하는 일의 양을 표로 나타낸 것입니다.\n 예쁜이 할머니가 하루 중 가장 적게 하는 일은 무엇인가요."}
    };

    private static final String strAnsList[][] = {
            {"오전 6시", "정오 12시", "오후 6시"},
            {"잠", "평생학교","TV 시청"},
            {"4시간", "오전 10시", "오전 5시"},
            {"평생학교", "수학게임", "수학게임"},
            {"아침운동","운동","운동"}
    };

    private static final String txtBtnList[][][] = {
            {{"오전 6시", "오전 10시"}, {"정오 12시","오후 1시"}, {"정오 12시","오후 6시"}},
            {{"아침운동", "잠", "평생학교"}, {"아침운동", "잠", "평생학교"}, {"아침운동", "잠", "TV 시청"}, },
            {{"3시간", "4시간", "5시간"},{"오전 8시", "오전 9시", "오전 10시"},{"오전 5시", "오전 7시", "오전 10시"},},
            {{"잠", "집안일", "평생학교"},{"운동","집안일","수학게임"},{"수학게임","집안일","TV시청"}},
            {{"아침운동","집안일","학교 과제"},{"운동","집안일","수학게임"},{"수학게임","집안일","운동"}},
    };
}