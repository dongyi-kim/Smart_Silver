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
            "�Ʒ� �׸��� ������ ��� Ȯ���Դϴ�. �뱸������ ���� ���� ��� Ȯ���� ������ ������ ���߸� ��������!",
            "�Ʒ� �׸��� ������ ��� Ȯ���Դϴ�. û�������� ���� ���� ��� Ȯ���� ������ ������ ���߸� ��������!",
            "�Ʒ� �׸��� ������ ��� Ȯ���Դϴ�. ���������� ���� ���� ��� Ȯ���� ������ ������ ���߸� ��������!",
            "�Ʒ� �׸��� ������ ��� Ȯ���Դϴ�. ���������� ���� ���� ��� Ȯ���� ������ ������ ���߸� ��������!",
            "�Ʒ� �׸��� ������ ��� Ȯ���Դϴ�. ������ ��õ ��, �뱸 ���� �ִٰ� �մϴ�. ���������� ���� ���� ��� Ȯ���� ������ ������ ���߸� ��������!",
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