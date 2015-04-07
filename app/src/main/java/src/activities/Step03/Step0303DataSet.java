package src.activities.Step03;

import android.media.Image;

import cdmst.smartsilver.R;

/**
 * Created by Acka on 2015-03-29.
 */
public class Step0303DataSet {
    private static final String sDiscriptionList[] = {" 할머니와 할아버지가 손주들에게 받은 송편의 합은 모두 몇 개인가요? 아래 숫자를 누르세요!",
            " 할머니 할아버지가 홍시 선물을 받았습니다. 모두 몇 개인지 아래 수를 누르세요!",
            " 할머니와 할아버지가 추석에 사용할 밤을 까려고 합니다. 모두 몇 개의 밤을 까야 할까요? 아래 숫자를 누르세요!",
            " 할머니와 할아버지가 제사준비를 하고 있습니다. 할머니와 할아버지가 전을 부칩니다. 얼만큼의 전을 부쳐야 할까요? 아래 숫자를 누르세요!"};
    private static final int iFoodType[] = {0, 1, 2, 0, 3};
    private static final int iGrandmaImageList[] = {R.drawable.grandma_with_songpyoen, R.drawable.grandma_with_hongshi, R.drawable.grandma_with_bam, R.drawable.grandma_with_jeon};
    private static final int iGrandfaImageList[] = {R.drawable.grandfa_with_songpyoen, R.drawable.grandfa_with_hongshi, R.drawable.grandfa_with_bam, R.drawable.grandfa_with_jeon};
    private static final String sGrandmaFoodList[] = {"깨송편5+콩송편10", "홍시 5개", "밤 24개", "깨송편5+콩송편10", "녹두전 20장\n호박전 40개\n동태전 30개"};
    private static final String sGrandfaFoodList[] = {"깨송편5+콩손편10", "홍시 7개", "밤 30개", "깨송편5+콩송편10", "녹두전 2장\n호박전 3개\n동태전 5개"};
    private static final int iAnswerList[] = {30, 12, 54, 30, 100};
    private static final int iBtnListSet[][] = {{10, 20, 30},
                                                {10, 12, 15},
                                                {54, 44, 64},
                                                {10, 20, 30},
                                                {90, 100, 110}};

    public String sDiscription;
    public int iGrandmaImage;
    public int iGrandfaImage;
    public String sGrandmaFood;
    public String sGrandfaFood;
    public int iAnswer;
    public int iBtnList[] = new int[3];

    public void setData(int iSeed){
        sDiscription = sDiscriptionList[iFoodType[iSeed]];
        iGrandmaImage = iGrandmaImageList[iFoodType[iSeed]];
        iGrandfaImage = iGrandfaImageList[iFoodType[iSeed]];
        sGrandmaFood = sGrandmaFoodList[iSeed];
        sGrandfaFood = sGrandfaFoodList[iSeed];
        iAnswer = iAnswerList[iSeed];

        for(int i = 0; i < 3; i++)
            iBtnList[i] = iBtnListSet[iSeed][i];
    }
}
