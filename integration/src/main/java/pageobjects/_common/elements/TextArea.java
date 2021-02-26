package pageobjects._common.elements;

public class TextArea extends InputBox {

    public TextArea(String elementName) {
        super(elementName);
        input = element.$("textarea");
    }
}
