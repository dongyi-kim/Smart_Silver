package src.activities.Step06;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-07-05.
 */
public class Step0605DataSet {
    public int img;
    public String Discription;
    public String btnTxt[];
    public String strAns;

    public void setData(int iSeed) {
        int rand = (int)(Math.random() * 3.0); // 0 ~ 2
        Discription = DiscriptionList[iSeed][rand];
        img = imgList[iSeed][rand];
        btnTxt = txtBtnList[iSeed][rand];
        strAns = strAnsList[iSeed][rand];
    }

    private static final int imgList[][] = {
            {R.drawable.map_weather_5_1,R.drawable.map_weather_5_1,R.drawable.map_weather_5_1,},
            {R.drawable.map_weather_5_1,R.drawable.map_weather_5_1,R.drawable.map_weather_5_1,},
            {R.drawable.map_weather_5_1,R.drawable.map_weather_5_1,R.drawable.map_weather_5_1,},
            {R.drawable.map_weather_5_1,R.drawable.map_weather_5_1,R.drawable.map_weather_5_1,},
            {R.drawable.map_weather_5_2,R.drawable.map_weather_5_3,R.drawable.map_weather_5_4,}
    };

    private static final String DiscriptionList[][] = {
            {"아래 그림은 내일 비가 올 확률입니다.\n내일 오후 대구에서 비가 올 확률은 얼마인가요?","아래 그림은 내일 비가 올 확률입니다.\n내일 오후 대전에서 비가 올 확률은 얼마인가요?","아래 그림은 내일 비가 올 확률입니다.\n내일 오전 청주에서 비가 올 확률은 얼마인가요?",},
            {"아래 그림은 내일 비가 올 확률입니다.\n내일 오후 청주에서 비가 올 확률은 얼마인가요?","아래 그림은 내일 비가 올 확률입니다.\n내일 오후 전주에서 비가 올 확률은 얼마인가요?","아래 그림은 내일 비가 올 확률입니다.\n내일 오후 부산에서 비가 올 확률은 얼마인가요?",},
            {"아래 그림은 내일 비가 올 확률입니다.\n내일 오전 광주에서 비가 올 확률은 얼마인가요?","아래 그림은 내일 비가 올 확률입니다.\n내일 오전 서울에서 비가 올 확률은 얼마인가요?","아래 그림은 내일 비가 올 확률입니다.\n내일 오전 서울에서 비가 올 확률은 얼마인가요?",},
            {"아래 그림은 내일 비가 올 확률입니다.\n내일 오후 제주에서 비가 올 확률은 얼마인가요?","아래 그림은 내일 비가 올 확률입니다.\n내일 오전 제주에서 비가 올 확률은 얼마인가요?","아래 그림은 내일 비가 올 확률입니다. \n내일 오전 독도에서 비가 올 확률은 얼마인가요?",},
            {"아래 그림은 내일 비가 올 확률입니다.\n내일 오전에 비올 확률이 가장 높은 지역은 어디인가요?","아래 그림은 내일 비가 올 확률입니다.\n내일 오전에 비올 확률이 가장 높은 지역은 어디인가요?", "아래 그림은 내일 비가 올 확률입니다.\n내일 오전에 비올 확률이 가장 높은 지역은 어디인가요?"}
    };

    private static final String strAnsList[][] = {
            {"0","0","0"},
            {"10","0","10"},
            {"30","10","5"},
            {"10","30","10"},
            {"제주","광주","부산"}
    };

    private static final String txtBtnList[][][] = {
            {{"0", "10"},{"0","10"},{"0","10"}},
            {{"10", "30"},{"0","20"},{"10","30"}},
            {{"0", "30"},{"10","30"},{"5","10"}},
            {{"10", "30"},{"10","30"},{"10","30"}},
            {{"제주","광주"},{"제주","광주"},{"제주","부산"}}
    };
}