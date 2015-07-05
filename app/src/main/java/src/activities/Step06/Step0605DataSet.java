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
    public int iAns;

    public void setData(int iSeed) {
        Discription = DiscriptionList[iSeed];
        img = imgList[iSeed];
        btnTxt = txtBtnList[iSeed];
        strAns = strAnsList[iSeed];
        btnTxt = txtBtnList[iSeed];
    }

    private static final int imgList[] = {
            R.drawable.map_weather_5_1,
            R.drawable.map_weather_5_1,
            R.drawable.map_weather_5_1,
            R.drawable.map_weather_5_1,
            R.drawable.map_weather_5_2
    };

    private static final String DiscriptionList[] = {
            "아래 그림은 내일의 비올 확률입니다. 대구지역의 내일 오후 비올 확률은 얼마인지 오른쪽 단추를 누르세요!",
            "아래 그림은 내일의 비올 확률입니다. 청주지역의 내일 오후 비올 확률은 얼마인지 오른쪽 단추를 누르세요!",
            "아래 그림은 내일의 비올 확률입니다. 광주지역의 내일 오전 비올 확률은 얼마인지 오른쪽 단추를 누르세요!",
            "아래 그림은 내일의 비올 확률입니다. 제주지역의 내일 오후 비올 확률은 얼마인지 오른쪽 단추를 누르세요!",
            "아래 그림은 내일의 비올 확률입니다. 강릉은 춘천 옆, 대구 위에 있다고 합니다. 강릉지역의 내일 오후 비올 확률은 얼마인지 오른쪽 단추를 누르세요!",
    };

    private static final String strAnsList[] = {"0", "10", "30", "10", "0"};

    private static final String txtBtnList[][] = {
            {"0", "10"},
            {"10", "30"},
            {"0", "30"},
            {"10", "30"},
            {"0", "10"}
    };
}