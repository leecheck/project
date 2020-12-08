package com.gytech.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 将Object类型转为集合工具类
 *
 * @author Lucky Luffy
 * @date 2019/10/31
 */
public class ObjectToListUtil {

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return new ArrayList<>();
    }
}
