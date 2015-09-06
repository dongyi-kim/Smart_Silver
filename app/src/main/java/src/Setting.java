package src;

import src.activities.Step01.ActStep0101;
import src.activities.Step01.ActStep0102;
import src.activities.Step01.ActStep0103;
import src.activities.Step01.ActStep0104;
import src.activities.Step01.ActStep0105;
import src.activities.Step02.ActStep0201;
import src.activities.Step02.ActStep0202;
import src.activities.Step02.ActStep0203;
import src.activities.Step02.ActStep0204;
import src.activities.Step02.ActStep0205;
import src.activities.Step03.ActStep0301;
import src.activities.Step03.ActStep0302;
import src.activities.Step03.ActStep0303;
import src.activities.Step03.ActStep0304;
import src.activities.Step03.ActStep0305;
import src.activities.Step04.ActStep0401;
import src.activities.Step04.ActStep0402;
import src.activities.Step04.ActStep0403;
import src.activities.Step04.ActStep0404;
import src.activities.Step04.ActStep0405;
import src.activities.Step05.ActStep0502;
import src.activities.Step05.ActStep0503;
import src.activities.Step05.ActStep0504;
import src.activities.Step06.ActStep0601;
import src.activities.Step06.ActStep0602;
import src.activities.Step06.ActStep0603;
import src.activities.Step06.ActStep0604;
import src.activities.Step06.ActStep0605;
import src.activities.Step07.ActStep0701;
import src.activities.Step07.ActStep0702;
import src.activities.Step07.ActStep0703;
import src.activities.Step07.ActStep0704;
import src.activities.Step07.ActStep0705;
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
            {5,5,5,5,5},
            {10},
    };

    public static final Class<?>[][] arrStepClass = {
            {ActStep0101.class, ActStep0102.class, ActStep0103.class, ActStep0104.class, ActStep0105.class  },//step1
            {ActStep0201.class, ActStep0202.class, ActStep0203.class, ActStep0204.class, ActStep0205.class },//step2
            {ActStep0301.class, ActStep0302.class, ActStep0303.class, ActStep0304.class, ActStep0305.class },//step3
            {ActStep0401.class, ActStep0402.class, ActStep0403.class, ActStep0404.class, ActStep0405.class },//step4
            {ActStep0502.class, ActStep0502.class, ActStep0503.class, ActStep0504.class, ActStep0605.class },//step5
            {ActStep0601.class, ActStep0602.class, ActStep0603.class, ActStep0604.class, ActStep0605.class },//step6
            {ActStep0701.class, ActStep0702.class, ActStep0703.class, ActStep0704.class, ActStep0705.class },//step7
            { },//step8
            { },//step9
            {ActStep10.class },//step10
    };

}
