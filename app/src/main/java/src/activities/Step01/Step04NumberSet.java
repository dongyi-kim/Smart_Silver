package src.activities.Step01;

/**
 * Created by Acka on 2015-03-26.
 */
public class Step04NumberSet {
    private static final int MAX_SET_NUMBER = 8;

    /*If file I/O
    private static final boolean arrIsOdd[] = new boolean[MAX_SET_NUMBER];
    private static final int arrNumCount[] = new int[MAX_SET_NUMBER];
    private static final int[] arrNumSetList[] = new int[MAX_SET_NUMBER][];
    */

    //else
    private static final boolean arrIsOdd[] = {true, true, false, false, true, true, true, false};
    private static final int arrNumCount[] = {4, 2, 3, 2, 2, 3, 2, 3};
    private static final int[] arrNumSetList[] = {{1, 2, 6, 8},
                                                        {3, 2},
                                                        {6, 7, 9},
                                                        {39, 14},
                                                        {58, 97},
                                                        {48, 77, 52},
                                                        {121, 150},
                                                        {169, 126, 213}};

    public boolean isOdd;
    public int iNumCount;
    public int arrNumSet[] = new int[4];

    public Step04NumberSet(){
        //If file I/O, read file and set data
    }

    public void setData(int iSeed){
        isOdd = arrIsOdd[iSeed];
        iNumCount = arrNumCount[iSeed];
        for(int i = 0; i < iNumCount; i++)
            arrNumSet[i] = arrNumSetList[iSeed][i];
    }
}
