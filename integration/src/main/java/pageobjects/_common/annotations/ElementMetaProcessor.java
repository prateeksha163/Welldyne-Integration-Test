package pageobjects._common.annotations;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ElementMetaProcessor {

    public static Field getFieldFromVisibleName(String visibleFieldName, Class<?> classReference) {
        return Arrays.stream(classReference.getDeclaredFields())
                .filter(field -> field.getAnnotation(ElementMeta.class).fieldName().toLowerCase()
                        .contains(visibleFieldName.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public static String getFieldLocator(Field field) {
        return field.getAnnotation(ElementMeta.class).locator();
    }
}
