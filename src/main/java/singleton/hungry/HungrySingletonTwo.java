package singleton.hungry;

public class HungrySingletonTwo {


    private static final  HungrySingletonTwo hungrySingletionTwo ;

    static{
        hungrySingletionTwo = new HungrySingletonTwo();
    }

    private HungrySingletonTwo (){}

    public static HungrySingletonTwo getInstance(){
        return hungrySingletionTwo;
    }
}
