package com.wq.javashizhan.chapter12;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
/*
* java.time主要的几个类
* LocalDate LocalTime LocalDateTime Instant(机器用)
* Duration(细) Period(粗)
*
* */


public class DateTImeAPI {
    public static void main(String[] args) {
        System.out.println("=========LocalDate LocalTime LocalDateTime===========");
        // LocalDate 简单的日期 不含时间 不含时区
        LocalDate ld = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2021,9,20);
        System.out.println("年："+ld.getYear()+" 月："+ld.getMonth()+" 月日："+ld.getDayOfMonth()+
                " 年日："+ld.getDayOfYear() + " 星期：" + ld.getDayOfWeek() + " 月长" + ld.lengthOfMonth()
                + " 闰年："+ld.isLeapYear());
        // TemporalField 是一个接口 枚举 ChronoField 实现了它 可以使用get(ChronoField.XXX)获得LocalDate的所有信息
        System.out.println(ld2.get(ChronoField.DAY_OF_WEEK));

        // LocalTime    of(hour, min) of(hour, min, second)
        LocalTime lt = LocalTime.now();
        System.out.println(lt.getHour());

        // 都可以parse将字符串解析成日期 将字符串解析成时间
        LocalDate ld3 = LocalDate.parse("2001-08-10");
        System.out.println(ld3.getDayOfMonth());
        LocalTime lt2 = LocalTime.parse("13:13:13");
        System.out.println(lt2.getSecond());

    }
}
