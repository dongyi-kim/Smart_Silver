package src.activities.Step06;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-06-22.
 */
public class Step0602DataSet {
    public int img;
    public String Description;
    public String btnTxt[];
    public String strAns;

    public void setData(int iSeed){
        int rand = (int)(Math.random() * 3.0); // 0 ~ 2
        Description = DescriptionList[iSeed][rand];
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

    private static final String DescriptionList[][] = {
            {"다음 그림에서 김 할머니는 하루에 아침 운동을 시작하는 시간은 몇 시인가요?","다음 그림에서 김 할머니의 점심식사를 시작하는 시간은 몇 시 인가요?","다음 그림에서 김 할머니의 저녁식사를 시작하는 시간은 몇 시 인가요?"},
            {"예쁜이 할머니가 하루 중 가장 많이 하는 일은 무엇인가요?","다음 그림에서 예쁜이 할머니가 하루 중 두 번째로 많은 시간을 보내는 일은 무엇인가요?", "다음 그림에서 예쁜이 할머니가 하루 중 세 번째로 많이 하는 일은 무엇인가요.\n"},
            {"다음 그림에서 예쁜이 할머니가 평생학교에서 보내는 시간은 몇 시간인가요? ","다음 그림에서 예쁜이 할머니는 보통 집안일을 시작하는 시간은 몇 시입니까?","다음 그림에서 예쁜이 할머니의 기상시간은 몇 시 입니까?"},
            {"다음 그림은 예쁜이 할머니의 하루 일과를 나타낸 것입니다.\n예쁜이 할머니가 하루 중 가장 많은 시간을 보내는 것은 무엇인가요?","다음 그림은 예쁜이 할머니의 하루 일과를 나타낸 것입니다.\n예쁜이 할머니가 하루 중 가장 많은 시간을 보내는 것은 무엇인가요?","다음 그림은 예쁜이 할머니의 하루 일과를 나타낸 것입니다.\n예쁜이 할머니가 하루 중 가장 많은 시간을 보내는 것은 무엇인가요?",},
            {"다음 그림은 예쁜이 할머니의 하루 일과를 나타낸 것입니다.\n예쁜이 할머니가 하루 중 가장 적은 시간을 보내는 것은 무엇인가요?","다음 그림은 예쁜이 할머니의 하루 일과를 나타낸 것입니다.\n예쁜이 할머니가 하루 중 가장 적은 시간을 보내는 것은 무엇인가요?","다음 그림은 예쁜이 할머니의 하루 일과를 나타낸 것입니다.\n예쁜이 할머니가 하루 중 가장 적은 시간을 보내는 것은 무엇인가요?",},
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