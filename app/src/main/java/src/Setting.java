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
//    public static final String CustomFont  = "NanumGothic.otf";


    public static final int[][] arrNumOfStage = {
            {5,5,5,5,5},    //Step 01
            {5,5,5,5,5},    //Step 02
            {5,5,4,5,4},    //Step 03
            {5,5,4,5,5},    //Step 04
            {5,5,4,5,5},    //Step 05
            {5,5,5,5,5},    //Step 06
            {5,5,5,5,5},    //Step 07
            {5,5,5,5,5},    //Step 08
            {4,4,5,4,4},    //Step 09
            {10},           //Step 10
    };

    public static final Class<?>[][] arrStepClass = {
                            /* Level 01 */      /* Step 02 */       /* Step 03 */       /* Step 04 */       /* Step 05 */
            /* Step 01 */   {ActStep0101.class, ActStep0102.class,  ActStep0103.class,  ActStep0104.class,  ActStep0105.class },
            /* Step 02 */   {ActStep0201.class, ActStep0202.class,  ActStep0203.class,  ActStep0204.class,  ActStep0205.class },
            /* Step 03 */   {ActStep0301.class, ActStep0302.class,  ActStep0303.class,  ActStep0304.class,  ActStep0305.class },
            /* Step 04 */   {ActStep0401.class, ActStep0402.class,  ActStep0403.class,  ActStep0404.class,  ActStep0405.class },
            /* Step 05 */   {ActStep0501.class, ActStep0502.class,  ActStep0503.class,  ActStep0504.class,  ActStep0505.class },
            /* Step 06 */   {ActStep0601.class, ActStep0602.class,  ActStep0603.class,  ActStep0604.class,  ActStep0605.class },
            /* Step 07 */   {ActStep0701.class, ActStep0702.class,  ActStep0703.class,  ActStep0704.class,  ActStep0705.class },
            /* Step 08 */   {ActStep0801.class, ActStep0802.class,  ActStep0803.class,  ActStep0804.class,  ActStep0805.class },
            /* Step 09 */   {ActStep0901.class, ActStep0902.class,  ActStep0903.class,  ActStep0904.class,  ActStep0905.class },
            /* Step 10 */   {ActStep10.class },
    };

}
