package src.activities.Step06;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-06-24.
 */
public class Step0604DataSet {
    public int img;
    public String Discription;
    public String btnTxt[];
    public String strAns;
    public int iAns;

    public void setData(int iSeed) {
        int rand = (int)(Math.random() * 3.0); // 0 ~ 2
        Discription = DiscriptionList[iSeed][rand];
        img = imgList[iSeed];
        btnTxt = txtBtnList[iSeed][rand];
        strAns = strAnsList[iSeed][rand];
        iAns = iAnsList[iSeed][rand];
        btnTxt = txtBtnList[iSeed][rand];
    }

    private static final int imgList[] = {
            R.drawable.map_weather_4_1,
            R.drawable.map_weather_4_2,
            R.drawable.map_weather_4_3,
            R.drawable.map_weather_4_4,
            R.drawable.map_weather_4_5
    };

    private static final String DiscriptionList[][] = {
            {"아래 그림은 날씨 예보입니다. 제주의 날씨는 어떻습니까.","아래 그림은 날씨 예보입니다. 춘천의 날씨는 어떻습니까.","아래 그림은 날씨 예보입니다. 광주의 날씨는 어떻습니까.",},
            {"아래 그림은 날씨 예보입니다. 청주의 날씨는 어떻습니까.","아래 그림은 날씨 예보입니다. 대전의 날씨는 어떻습니까.","아래 그림은 날씨 예보입니다. 전주의 날씨는 어떻습니까.",},
            {"아래 그림은 날씨 예보입니다. 부산의 날씨는 어떻습니까.","아래 그림은 날씨 예보입니다. 청주의 날씨는 어떻습니까.","아래 그림은 날씨 예보입니다. 제주의 날씨는 어떻습니까.",},
            {"아래 그림은 날씨 예보입니다. 맑은 지역은 모두 몇 곳인가요.","아래 그림은 날씨 예보입니다. 비가 오는 지역은 모두 몇 곳인가요.","아래 그림은 날씨 예보입니다. 날씨가 흐린 지역은 모두 몇 곳인가요."},
            {"아래 그림은 날씨 예보입니다. 비가 오는 곳은 모두 몇 군데인가요.", "아래 그림은 날씨 예보입니다. 맑은 지역은 모두 몇 곳인가요.","아래 그림은 날씨 예보입니다. 날씨가 흐린 지역은 모두 몇 곳인가요."}
    };

    private static final int iAnsList[][] = {{1,0,1},{2,1,1},{1,2,1},{0,0,0},{0,0,0}};

    private static final String strAnsList[][] = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"4", "4", "3"},
            {"3", "5", "3"}
    };

    private static final String txtBtnList[][][] = {
            {{},{},{}},
            {{},{},{}},
            {{},{},{}},
            {{"2", "4"},{"2", "4"},{"3", "4"}},
            {{"3", "5"},{"3", "5"},{"3", "5"}}
    };
}