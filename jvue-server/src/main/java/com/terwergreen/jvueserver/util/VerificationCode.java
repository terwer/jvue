package com.terwergreen.jvueserver.util;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 验证码工具类
 *
 * @name: VerificationCode
 * @author: terwer
 * @date: 2022-07-17 22:21
 **/
public class VerificationCode {
    private static final String[] randomStr = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "A", "B",
            "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 随机生成验证码
     *
     * @return
     */
    public static Map getVerificationCode() {
        return getVerificationCodeWithStr(null);
    }

    /**
     * 利用给定的字符串生成验证码
     *
     * @param str 指定的字符串
     * @return
     */
    public static Map getVerificationCodeWithStr(String str) {
        // 设置默认生成 4个 长度的验证码
        int strLength = 4;
        char[] strArr = null;
        if (StringUtils.isNotEmpty(str)) {
            strLength = str.length();
            strArr = str.toCharArray();
        }

        // 定义验证码图片大小
        int width = 20 * strLength + 5, height = 25;
        // 在内存中创建 图像
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 为内存中要创建的图像生成画布，
        Graphics2D graphics2D = bufferedImage.createGraphics();
        // 画一个白色矩形，作为验证码背景
        graphics2D.setColor(Color.WHITE);
        // 填充
        graphics2D.fillRect(0, 0, width, height);

        // 画 100 条 灰色的 随机干扰线
        if (StringUtils.isNotEmpty(str)) {
            graphics2D.setColor(Color.WHITE);
        } else {
            graphics2D.setColor(Color.GRAY);
        }
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            graphics2D.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }

        // 创建字体
        Font font = new Font("Times New Roman", Font.BOLD, 25);
        graphics2D.setFont(font);

        StringBuffer sb = new StringBuffer();
        // 取得 4 位数的 随机字符串
        for (int i = 0; i < strLength; i++) {
            // 返回一个 随机数，在 1 和 20 之间
            String randomNumber = randomStr[random.nextInt(36)];
            if (StringUtils.isNotEmpty(str)) {
                randomNumber = String.valueOf(strArr[i]);
            }

            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            //获得一个随机红蓝绿的配合颜色
            graphics2D.setColor(new Color(red, green, blue));
            //把该数字用画笔在画布画出，并指定数字的坐标
            graphics2D.drawString(randomNumber, 20 * i + 5, (height / 2) + 10);
            //把该数字加到缓存字符串中。用于等会生成验证码字符串set到session中用于校对
            sb.append(randomNumber);
        }
        // 清除内存的图片
        bufferedImage.flush();
        // 释放资源
        graphics2D.dispose();

        // 返回结果
        Map result = new HashMap();
        result.put("imgStream", bufferedImage);
        result.put("code", str);
        return result;
    }
}
