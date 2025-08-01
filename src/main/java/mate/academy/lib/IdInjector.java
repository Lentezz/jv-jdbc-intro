package mate.academy.lib;

import java.lang.reflect.Field;

public class IdInjector {
    //TODO add check fot id field data type
    public static <T extends Number> void injectIdFromDB(Object entity, T idValue) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ID.class)) {
                field.setAccessible(true);
                try {
                    field.set(entity, idValue);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject ID field into entity: "
                            + entity.getClass().getSimpleName(), e);
                }
            }
        }
    }
}
