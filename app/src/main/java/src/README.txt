앞으로 개발할 때 통일해야할 규칙들은 이 문서에 작성해서 통일하도록 합시다.

step > level > stage

[명칭통일 통일]
    기본적으로는 헝가리안 표기법에 베이스를 두고 좀 개량하여 작성할 생각이니 아래 문서 대강 읽어보삼
        - 헝가리안 표기법 : http://jikime.tistory.com/305
    요약하면 타입과 의미를 조합하는 거임
        예 : 카운트용 정수 변수는 iCount
    이름에 의미가 다른 단어가 여러게 들어가면 단어로 구분
        예 : 동물의 수를 새는 정수 변수 iAnimalCount

    조금 길어서 답답할수도 있으나 Android Studio가 워낙 자동완성이 강려크하므로
    차라리 이렇게 변수명을 길게 하는게 탐색도 쉽고 더 편함

    [요약] 넓은 범위의 의미를 앞으로 내보내서 이름을 Top-down식으로 작명
        xml 파일명    : 소문자만 사용, 의미마다 '_'로 구분
            예 : act_main.xml
                dlg_alert.xml


        layout id    : 소문자만 사용, 의미마다 '_'로 구분
            예 : btn_user_login
                btn_user_logout


        Class 명     : '_'없이 단어의 첫 글자를 대문자로하여 구분, 맨 앞글자는 무조건 대문자
                        클래스 명은 자주 사용하지 않으므로 instance이름 보다는 조금 더 길게해두는게 좋음

            예 : ActMain,    ViewNameCard


        Instance 명  : '_'없이 의미의 첫 글자를 대문자로하여 구분, 맨 앞글자는 무조건 소문자
                       보통 가장 앞 단어는 Instance의 Class의 이름을 줄여서 사용함
                       ** instance명의 경우 자기만 사용하는 경우가 대부분이므로 타입명시만 제대로하고 이름자체는 프리하게
                                    (그래도 만일의 경우에도 대비해서 기본적인 틀을 지킬 것)
            예 : ViewNameCard vMyCard = new ViewNameCard();
                int iCount = 0;
                string strName = "dy.kim";



        상수          : 대문자를 사용하고 '_'를 사용하여 단어마다 구분, Top-down식으로 네이밍
            예 : public static final int ERROR_NOT_FOUND = 404;
                public static final string PROJECT_NAME = "Smart Silver";


        **** instance명은 워낙 사람마다 작명기준이 다르니까 기본틀만 지키고 조금 자유롭게 지어도 될 듯
            하지만 그 이외의 요소는 모두 공통으로 사용하는 경우가 많으므로 길더라도 규칙대로 할 것


    1. 클래스명
        클래스들의 이름은 보통 자바들 처럼 영문만 사용해서 작성하면서, 의마마다 대문자로 구분하는 것으로 함
        위에 변수명 표기볍과 동일하게 타입+의미식으로 표기하는데 아래에 표따라 맞추기를 추천
        클래스명은 공통적으로 사용하므로 의미가 명확하게하고 타입기호는 통일할 것

        (타입)       (타입기호)
        Activity    Act
        Dialog      Dlg
        View        View

    2. 변수명(Instance)
        클래스명과 동일하나 차이점은 맨 앞글자는 무조건 소문자를 사용함
        타입 기호만 지키면 작명은 의미만 명확하게 자유롭게 할 것

        (타입)    (타입기호)
        int         i
        double      d
        char        c
        bool        b
        Array       a, arr
        ArrayList   list
        string      str
        Object      obj
        View        v, view
        Button      btn
        ImageButton ibtn
        Image       img
        Dialog      dlg
        Context     ctx

        대충 이런식

        3. xml
            xml파일은 소문자만 사용하고 '_'로 의미구분을 하고 작명법은 class와 유사

        4. Layout ID
            layout id도 물론 '_'로 의미를 구분하며 소문자만 사용
            작명법은 변수명과 유사






http://www.flaticon.com/packs/design-app-ui

