package org.danmo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class BeanUtil {

    public static String[] getNullPropertyNames(Object obj){
        Set<String> propertyNames = new HashSet<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(obj);
                if (field.get(obj) == null) {
                    propertyNames.add(field.getName());
                }
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        String[] result = new String[propertyNames.size()];
        return propertyNames.toArray(result);
    }

    public static void copyProperties(Object src , Object target) {
        BeanUtils.copyProperties(src, target);
    }

    public static void copyPropertiesIgnoreNull(Object src , Object target) {
        BeanUtils.copyProperties(src, target , getNullPropertyNames(src));
    }

}
