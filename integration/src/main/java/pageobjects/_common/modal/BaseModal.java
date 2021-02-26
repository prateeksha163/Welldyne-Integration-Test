package pageobjects._common.modal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.annotations.ElementMeta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects._common.annotations.ElementMetaProcessor.getFieldFromVisibleName;
import static pageobjects._common.annotations.ElementMetaProcessor.getFieldLocator;

public class BaseModal {

    private final SelenideElement loader = $(".ui-heart-beat-preloader");
    protected SelenideElement modal;
    SelenideElement modalHead;
    SelenideElement saveButton;
    SelenideElement cancelButton;
    SelenideElement closeButton;
    SelenideElement nextButton;
    SelenideElement addSequenceButton;
    SelenideElement addRuleSetButton;
    private Class<?> subclass;

    public BaseModal() {
        initializeBaseModalElements();
    }

    public BaseModal(Class<?> subclass) {
        initializeBaseModalElements();
        this.subclass = subclass;
    }

    public void initializeBaseModalElements() {
        modal = $(".ui-overlay__content-pane");
        modalHead = modal.$("h1");
        saveButton = modal.$(byAttribute("data-action-name", "submit"));
        cancelButton = modal.$(byAttribute("data-action-name", "cancel"));
        closeButton = modal.$(byAttribute("data-action-name", "closeOverlay"));
        nextButton = modal.$(byAttribute("data-action-name", "proceed"));
        addSequenceButton = modal.$("button[class*='add-section']");
        addRuleSetButton = modal.$("button[class*='add-rules']");
        modal.shouldBe(Condition.visible);
    }

    public void clickOnActionButton(String action) {
        switch (action.toUpperCase()) {
            case "SUBMIT":
            case "SAVE":
            case "CONFIRM":
                saveButton.waitUntil(Condition.enabled, 10000).click();
                break;
            case "CANCEL":
                cancelButton.click();
                break;
            case "CLOSE":
                closeButton.click();
                break;
            case "NEXT":
                nextButton.click();
                break;
            case "ADD SEQUENCE":
                addSequenceButton.click();
                break;
            case "ADD RULE":
                addRuleSetButton.click();
                break; }
    }

    public void shouldBeVisible() {
        modal.shouldBe(Condition.visible);
    }

    public void shouldNotExist() {
        loader.waitUntil(Condition.disappear, 20000);
        modal.shouldNot(Condition.exist);
    }

    public void removeSection(String section) {
        modal.$(byAttribute("data-element-name", ""+section+"")).$("p[class*='remove']").click();
    }

    public SelenideElement getFormElement(String name) {
        return modal.$("*[data-element-name*='" + name + "']");
    }

    public SelenideElement getMultiFieldFormElement(String name) {
        name = name.replaceAll("_", " ");
        try {
            ElementsCollection e = $$("*[class*='item--multipleValueAddition']");
            return e.find(Condition.text(name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> getFormFields() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList<String> list = new ArrayList<>();
        Field[] fields = subclass.getDeclaredFields();
        for (Field f : fields) {
            String locator = f.getName();
            if (f.isAnnotationPresent(ElementMeta.class)) locator = getFieldLocator(f);
            if (!getFormElement(locator).exists() && !getMultiFieldFormElement(locator).exists()) {
                continue;
            }
            Method m = f.getType().getMethod("getName");
            String name = (String) m.invoke(f.getType()
                    .getDeclaredConstructor(String.class)
                    .newInstance(locator));
            list.add(name);
        }
        return list;
    }

    public void setValue(String visibleName, String value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("setValue", String.class);
        m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator), value);
    }

    public void setValue(String visibleName, List<String> values) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("setValue", List.class);
        m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator), values);
    }

    public void setValue(String visibleName, String label, Boolean value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("setValue", String.class, Boolean.class);
        m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator), label, value);
    }

    public String getValue(String visibleName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("getValue");
        return (String) m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator));
    }

    public List<String> getValues(String visibleName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("getValues");
        return (List<String>) m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator));
    }

    public List<String> getDropdownValues(String visibleName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("getDropdownValues");
        return (List<String>) m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator));
    }

    public List<String> getDropdownValues(String visibleName,String value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("getDropdownValues",String.class);
        return (List<String>) m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator),value);
    }

    public void clearValue(String visibleName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("clearField");
        m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator));
    }

    public void clearValue(String visibleName, String value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("clearField", String.class);
        m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator), value);
    }

    public void clearValue(List<String> visibleNames) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (String visibleName : visibleNames) {
            Field f = getFieldFromVisibleName(visibleName, subclass);
            String locator = getFieldLocator(f);
            Method m = f.getType().getMethod("clearField");
            m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator));
        }
    }

    public String getErrorText(String visibleName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field f = getFieldFromVisibleName(visibleName, subclass);
        String locator = getFieldLocator(f);
        Method m = f.getType().getMethod("getError");
        return (String) m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator));
    }

    public HashMap<String, String> getErrorText(List<String> visibleNames) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HashMap<String, String> errorTexts = new HashMap<>();
        for (String visibleName : visibleNames) {
            Field f = getFieldFromVisibleName(visibleName, subclass);
            String locator = getFieldLocator(f);
            Method m = f.getType().getMethod("getError");
            String error = (String) m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator));
            errorTexts.put(visibleName, error);
        }
        return errorTexts;
    }

    public Map<String, Boolean> getDisabledFields(List<String> visibleNames) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HashMap<String, Boolean> disabledFields = new HashMap<>();
        Boolean result;
        for (String visibleName : visibleNames) {
            Field f = getFieldFromVisibleName(visibleName, subclass);
            String locator = getFieldLocator(f);
            Method m = f.getType().getMethod("isFieldDisabled");
            result = (Boolean) m.invoke(f.getType().getDeclaredConstructor(String.class).newInstance(locator));
            disabledFields.put(visibleName, result);
        }
        return disabledFields;
    }
}