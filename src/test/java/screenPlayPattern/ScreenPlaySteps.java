package screenPlayPattern;

import net.thucydides.core.annotations.Step;

public class ScreenPlaySteps {

    String actor;

    @Step("#actor entered valid credentials")
    public void test1(){
        System.out.println("some codes go here");
    }

    @Step("#actor landed on Bank of America Home Page")
    public void test2(){
        System.out.println("some more codes go here");
    }

    @Step("#actor logged out from Bank of America successfully")
    public void test3(){
        System.out.println("and some more codes go here");
    }
}
