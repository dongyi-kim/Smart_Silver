package src.activities.Step06;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-07-05.
 */
public class Step0603DataSet {
    public int img;
    public String Discription;
    public String btnTxt[];
    public String strAns;

    public void setData(int iSeed){
        Discription = DiscriptionList[iSeed];
        img = imgList[iSeed];
        btnTxt = txtBtnList[iSeed];
        strAns = strAnsList[iSeed];
    }

    private static final int imgList[][] ={
            {R.drawable.gam_6_3, R.drawable.apple_6_3},
            {R.drawable.gam_6_3, R.drawable.bam_6_3},
            {R.drawable.semo_6_3, R.drawable.nemo_6_3}
    };

    private static final String DiscriptionList[] = {
            "�Ʒ� ������ ����ŭ ������ ǥ�� ��ƼĿ�� �ٿ����ô�! ���� �Ʒ� ���߸� ���� ����� ������ŭ ��������!",
            "�Ʒ� ������ ����ŭ ������ ǥ�� ��ƼĿ�� �ٿ����ô�! ���� �Ʒ� ���߸� ���� ���� ������ŭ ��������!",
            "�Ʒ� ������ ����ŭ ������ ǥ�� ��ƼĿ�� �ٿ����ô�! ���� �Ʒ� ���߸� ����, �׸��� ������ŭ ��������! ",
            "����б� ������ �л��� �¾ ���Դϴ�. ���� ���� ���� �¾���ϱ�? �Ʒ� ���߸� ��������!",
            "����б� ������ ��Ӵϵ��� �и����� �ϴ� ���� ������ ���ҽ��ϴ�. ��� ���Ͽ� �и����Ÿ� ���� ���� �ϴ��� �Ʒ� ���߸� ��������!"
    };

    private static final String strAnsList[] = {"2�ð�", "��", "4�ð�", "����б�", "��ħ�"};

    private static final String txtBtnList[][] = {
            {"2�ð�", "5�ð�"},
            {"��ħ�", "��", "����б�"},
            {"3�ð�", "4�ð�", "5�ð�"},
            {"��", "������", "����б�"},
            {"��ħ�", "������", "�б� ����"}
    };
}
