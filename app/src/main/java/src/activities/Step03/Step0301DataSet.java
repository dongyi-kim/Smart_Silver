package src.activities.Step03;

/**
 * Created by Acka on 2015-03-28.
 */
public class Step0301DataSet {
    private static final int MAX_SET_NUMBER = 8;

    /*If file I/O
    private static final int arrStartNumber[] = new int[MAX_SET_NUMBER];
    private static final int[] arrProcessSet[] = new int[MAX_SET_NUMBER][];
    */

    //else
    private static final int arrStartNumber[] = {200, 400, 150, 380, 290, 590, 199, 699, 500, 400, 700, 500, 790, 101, 602, 602};
    private static final int[] arrProcessSet[] = {{100, 10, 1},
                                                        {100, 100, 100},
                                                        {100, 100, 100},
                                                        {100, 100, 100},
                                                        {10, 10, 10},
                                                        {10, 10, 10},
                                                        {1, 1, 1},
                                                        {1, 1, 1},
                                                        {-100, -10, -1},
                                                        {-100, -100, -100},
                                                        {-100, -100, -100},
                                                        {-10, -10, -10},
                                                        {-10, -10, -10},
                                                        {-1, -1, -1},
                                                        {-1, -1, -1},
                                                        {-1, -1, -1}};
    public int startNumber;
    public int arrProcess[] = new int[4];

    public Step0301DataSet(){
        //If file I/O, read file and set data
    }

    public void setData(int iSeed){
        startNumber = arrStartNumber[iSeed];
        for(int i = 0; i < 3; i++)
            arrProcess[i] = arrProcessSet[iSeed][i];
    }
}
