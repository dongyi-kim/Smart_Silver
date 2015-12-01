package src;

import src.activities.Step01.*;
import src.activities.Step02.*;
import src.activities.Step03.*;
import src.activities.Step04.*;
import src.activities.Step05.*;
import src.activities.Step06.*;
import src.activities.Step07.*;
import src.activities.Step08.*;
import src.activities.Step09.*;
import src.activities.Step10.ActStep10;

/**
 * Created by waps12b on 15. 3. 25..
 */
public class Setting {
    public static final String CustomFont  = "NanumGothic.otf";


    public static final int[][] arrNumOfStage = {
            {5,5,5,5,5},
            {5,5,5,5,5},
            {5,5,4,5,4},
            {5,5,4,5,5},
            {5,5,4,5,5},
            {5,5,5,5,5},
            {5,5,5,5,5},
            {5,5,5,5,5},
            {4,5,5,5,5},
            {10},
    };

    public static final Class<?>[][] arrStepClass = {
            {ActStep0101.class, ActStep0102.class, ActStep0103.class, ActStep0104.class, ActStep0105.class  },//step1
            {ActStep0201.class, ActStep0202.class, ActStep0203.class, ActStep0204.class, ActStep0205.class },//step2
            {ActStep0301.class, ActStep0302.class, ActStep0303.class, ActStep0304.class, ActStep0305.class },//step3
            {ActStep0401.class, ActStep0402.class, ActStep0403.class, ActStep0404.class, ActStep0405.class },//step4
            {ActStep0501.class, ActStep0502.class, ActStep0503.class, ActStep0504.class, ActStep0505.class },//step5
            {ActStep0601.class, ActStep0602.class, ActStep0603.class, ActStep0604.class, ActStep0605.class },//step6
            {ActStep0701.class, ActStep0702.class, ActStep0703.class, ActStep0704.class, ActStep0705.class },//step7
            {ActStep0801.class, ActStep0802.class, ActStep0803.class, ActStep0804.class, ActStep0805.class },//step8
            {ActStep0901.class, ActStep0902.class, ActStep0903.class, ActStep0904.class, ActStep0905.class },//step9
            {ActStep10.class },//step10
    };

}
