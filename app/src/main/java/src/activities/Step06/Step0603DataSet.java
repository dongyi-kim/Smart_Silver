package src.activities.Step06;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-06-24.
 */
public class Step0603DataSet {
    public int[] img;
    public int[] imgGraph;
    public String Discription;
    public String btnTxt[];
    public String strAns;
    public String[] fruit;
    public int iAns[];

    public void setData(int iSeed) {
        int rand = (int)(Math.random() * 3.0); // 0 ~ 2
        Discription = DiscriptionList[iSeed];
        img = imgList[iSeed][rand];
        btnTxt = txtBtnList[iSeed][rand];
        strAns = strAnsList[iSeed][rand];
        iAns = iAnsList[iSeed][rand];
        btnTxt = txtBtnList[iSeed][rand];
        fruit = strFruitName[iSeed];
        imgGraph = iGraph[iSeed];
    }

    private static final int iGraph[][] ={
            {R.drawable.gam_6_3, R.drawable.apple_6_3},
            {R.drawable.gam_6_3, R.drawable.bam_6_3},
            {R.drawable.circle_6_3, R.drawable.rect_6_3},
            {0,0},
            {0,0}
    };

    private static final int imgList[][][] = {
            {{R.drawable.img_6_3_1_1_1,R.drawable.img_6_3_1_1_2},{R.drawable.img_6_3_1_2_1,R.drawable.img_6_3_1_2_2},{R.drawable.img_6_3_1_3_1,R.drawable.img_6_3_1_3_2}},
            {{R.drawable.img_6_3_2_1_1,R.drawable.img_6_3_2_1_2},{R.drawable.img_6_3_2_2_1,R.drawable.img_6_3_2_2_2},{R.drawable.img_6_3_2_3_1,R.drawable.img_6_3_2_3_2}},
            {{R.drawable.img_6_3_3_1_1,R.drawable.img_6_3_3_1_2},{R.drawable.img_6_3_3_2_1,R.drawable.img_6_3_3_2_2},{R.drawable.img_6_3_3_3_1,R.drawable.img_6_3_3_3_2}},
            {{R.drawable.graph_6_1_1,R.drawable.graph_6_1_1},{R.drawable.graph_6_1_2,R.drawable.graph_6_1_2,},{R.drawable.graph_6_1_3,R.drawable.graph_6_1_3}},
            {{R.drawable.graph_6_2_1,R.drawable.graph_6_2_1},{R.drawable.graph_6_2_2,R.drawable.graph_6_2_2},{R.drawable.graph_6_2_3,R.drawable.graph_6_2_3}}
    };

    private static final String DiscriptionList[] = {
            "아래 과일의 수만큼 오른쪽 표에 스티커를 붙여봅시다.\n감과 사과의 갯수만큼 화살표를 누르세요.",
            "아래 과일의 수만큼 오른쪽 표에 스티커를 붙여봅시다.\n감과 밤의 갯수만큼 화살표를 누르세요.",
            "아래 도형의 수만큼 오른쪽 표에 스티커를 붙여봅시다.\n동그라미, 네모의 갯수만큼 화살표를 누르세요.",
            "다음 표는 어떤 평생학교의 학생이 태어난 달을 표시한 것입니다.\n가장 많이 태어난 달은 몇 월인가요?",
            "다음 표는 어떤 평생학교 학생 집의 분리수거 하는 요일을 표시한 것입니다.\n분리수거를 가장 많이 하는 요일은 무엇인가요?"
    };

    private static final String strFruitName[][]={
            {"감","사과"},
            {"감","밤"},
            {"동그라미","네모"},
            {"",""},
            {"",""}
    };

    private static final int iAnsList[][][] = {
            {{4,3},{5,4},{2,6}},
            {{3,8},{5,6},{6,4}},
            {{5,3},{4,3},{6,2}},
            {{0,0},{0,0},{0,0}},
            {{0,0},{0,0},{0,0}},
    };

    private static final String strAnsList[][] = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"6월", "10월", "3월"},
            {"목", "수", "월"}
    };

    private static final String txtBtnList[][][] = {
            {{"","","","",},{"","","","",},{"","","",""}},
            {{"","","","",},{"","","","",},{"","","",""}},
            {{"","","","",},{"","","","",},{"","","",""}},
            {{"1월","3월","6월","12월"},{"1월","3월","6월","10월"},{"1월","3월","6월","10월"},},
            {{"월", "화", "수", "목"},{"월", "화", "수", "목"},{"월", "화", "수", "목"},},
    };
}