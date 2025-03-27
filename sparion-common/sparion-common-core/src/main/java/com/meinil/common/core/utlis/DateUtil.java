package com.meinil.common.core.utlis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description
 */
public class DateUtil {

    private DateUtil() {}

    public static String format(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(time);
    }
}
