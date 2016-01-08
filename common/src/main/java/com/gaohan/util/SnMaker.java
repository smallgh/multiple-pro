package com.gaohan.util;

import org.apache.commons.lang3.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 */
public class SnMaker {

    /**
     * 生成OrderSn
     * @param prefix 订单前缀
     * @param now 生成时间
     * @return
     */
    public static String createOrderSn(String prefix, Date now) {
        StringBuffer orderSn = new StringBuffer(prefix);

        //单据中间时间字符，精确到分钟
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String orderTime = sdf.format(now);
        orderSn.append(orderTime);

        //单据末尾6位随机数
        int random = (int) (Math.random() * 100000);
        orderSn.append(random);

        return orderSn.toString();
    }

    // * 生成第三方支付15位交易号 规则：WX/AL+orderId+(年份，2014年是A，以此类推)+(16进制月)+（日补0）（随机数4位 前面补0）
    public static String getTradeNo(Long orderId, String payCode) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 年份 2014为A，2015为B以此类推
        char yearCode = (char) (year - 2014 + 65);
        // 4位随机数，不足补0
        int i = RandomUtils.nextInt(0, 9999);
        String result = payCode + orderId + yearCode + Integer.toHexString(month) + String.format("%02d", day)
                         + String.format("%04d", i);
        return result;
    }
}
