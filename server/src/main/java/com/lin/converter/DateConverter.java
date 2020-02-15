package com.lin.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期转换器
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class DateConverter implements Converter<String, Date> {

    private static final List<String> DATE_FORMAT = new ArrayList<>(4);
    static {
        DATE_FORMAT.add("yyyy-MM");
        DATE_FORMAT.add("yyyy-MM-dd");
        DATE_FORMAT.add("yyyy-MM-dd HH:mm");
        DATE_FORMAT.add("yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 格式化日期
     *
     * @param dateString the date string
     * @param format     the format
     * @return the date
     * @author : yangjunqing / 2019-01-17
     */
    public Date parseDate(String dateString, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateString);
        } catch (Exception e) {

        }
        return date;
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if(source.matches("^\\d{4}-\\d{1,2}$")){
            return parseDate(source, DATE_FORMAT.get(0));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, DATE_FORMAT.get(1));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, DATE_FORMAT.get(2));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, DATE_FORMAT.get(3));
        }else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }
}
