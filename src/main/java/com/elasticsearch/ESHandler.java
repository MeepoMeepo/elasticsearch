package com.elasticsearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class ESHandler {
    private static final String manual_index_name = "default";

    /**
     * 本周索引名
     *
     * @param indexPrefix    索引前缀
     * @param indexCreatedAt 元素创建时间
     */
    public static String getIndexName(String indexPrefix, Date indexCreatedAt) {
        if (!StringUtils.isEmpty(manual_index_name) && !"default".equalsIgnoreCase(manual_index_name)) {
            return manual_index_name;
        }
        SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy_MM_dd");
        //取星期一
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(indexCreatedAt);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return indexPrefix + yyyy_MM_dd.format(calendar.getTime());
    }

    /**
     * 时间点内所有索引名称
     *
     * @param indexPrefix         索引前缀
     * @param indexCreatedAtStart 起始元素创建时间
     * @param indexCreatedAtEnd   终止元素创建时间
     * @return
     */
    public static List<String> getIndexNames(String indexPrefix, Date indexCreatedAtStart, Date indexCreatedAtEnd) {
        SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy_MM_dd");
        List<String> indexs = new ArrayList<>();
        //取星期一
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(indexCreatedAtStart);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        while (calendar.getTime().before(indexCreatedAtEnd)) {
            indexs.add(indexPrefix + yyyy_MM_dd.format(calendar.getTime()));
            //下一周
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }
        return indexs;
    }
}
