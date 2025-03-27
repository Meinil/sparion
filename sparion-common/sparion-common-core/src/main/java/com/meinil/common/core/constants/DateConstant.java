package com.meinil.common.core.constants;

import java.time.format.DateTimeFormatter;

/**
 * @author Meinil
 * @date 2025/3/20
 * @description 时间常量
 */
public class DateConstant {

    private DateConstant() {}

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD);

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
}
