package pageobjects;

import com.codeborne.selenide.Condition;

public abstract class BasePage {

    abstract void load();

    abstract void isLoaded();

    static Condition clickable = Condition.and("can be clicked", Condition.appear, Condition.visible, Condition.enabled);
    public void get() {
        try {
            isLoaded();
        }
        catch (Exception e) {
            load();
        }
        finally {
            isLoaded();
        }
    }
}