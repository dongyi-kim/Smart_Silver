package src.activities.Step08;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-06-24.
 */
public class Step0803DataSet {
    public int img;
    public String Discription;
    public String btnTxt[];
    public String strAns;
    public int iAns;

    public void setData(int iSeed) {
        int rand = (int)(Math.random() * 3.0); // 0 ~ 2
        Discription = DiscriptionList[iSeed][rand];
        img = imgList[iSeed][rand];
        btnTxt = txtBtnList[iSeed][rand];
        strAns = strAnsList[iSeed][rand];
        iAns = iAnsList[iSeed][rand];
        btnTxt = txtBtnList[iSeed][rand];
    }

    private static final int imgList[][] = {
            {R.drawable.map_weather_4_1_1,R.drawable.map_weather_4_1_2,R.drawable.map_weather_4_1_3},
            {R.drawable.map_weather_4_2_1,R.drawable.map_weather_4_2_2,R.drawable.map_weather_4_2_3},
            {R.drawable.map_weather_4_3_1,R.drawable.map_weather_4_3_2,R.drawable.map_weather_4_3_3},
            {R.drawable.map_weather_4_4_1,R.drawable.map_weather_4_4_2,R.drawable.map_weather_4_4_3},
            {R.drawable.map_weather_4_5_1,R.drawable.map_weather_4_5_2,R.drawable.map_weather_4_5_3},
    };

    private static final String DiscriptionList[][] = {
            {"아래 그림은 날씨 예보입니다.\n제주의 날씨는 어떻습니까?","아래 그림은 날씨 예보입니다.\n춘천의 날씨는 어떻습니까?","아래 그림은 날씨 예보입니다.\n광주의 날씨는 어떻습니까?",},
            {"아래 그림은 날씨 예보입니다.\n청주의 날씨는 어떻습니까?","아래 그림은 날씨 예보입니다.\n대전의 날씨는 어떻습니까?","아래 그림은 날씨 예보입니다.\n전주의 날씨는 어떻습니까?",},
            {"아래 그림은 날씨 예보입니다.\n부산의 날씨는 어떻습니까?","아래 그림은 날씨 예보입니다.\n청주의 날씨는 어떻습니까?","아래 그림은 날씨 예보입니다.\n제주의 날씨는 어떻습니까?",},
            {"아래 그림은 날씨 예보입니다.\n맑은 지역은 모두 몇 곳인가요?","아래 그림은 날씨 예보입니다.\n비가 오는 지역은 모두 몇 곳인가요?","아래 그림은 날씨 예보입니다.\n흐린 지역은 모두 몇 곳인가요?"},
            {"아래 그림은 날씨 예보입니다.\n비가 오는 곳은 모두 몇 군데인가요?", "아래 그림은 날씨 예보입니다.\n맑은 지역은 모두 몇 곳인가요?","아래 그림은 날씨 예보입니다.\n흐린 지역은 모두 몇 곳인가요?"}
    };

    private static final int iAnsList[][] = {
            {1,0,1},
            {2,1,1},
            {1,2,1},
            {0,0,0},
            {0,0,0},
    };

    private static final String strAnsList[][] = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"4", "4", "3"},
            {"3", "5", "3"},
    };

    private static final String txtBtnList[][][] = {
            {{},{},{}},
            {{},{},{}},
            {{},{},{}},
            {{"2", "4"},{"2", "4"},{"3", "4"}},
            {{"3", "5"},{"3", "5"},{"3", "5"}},
    };
}