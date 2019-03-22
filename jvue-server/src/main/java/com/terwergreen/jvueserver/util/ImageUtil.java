package com.terwergreen.jvueserver.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片工具类
 */
public class ImageUtil {
    private static final Log logger = LogFactory.getLog(ReflectUtil.class);

    /**
     * 读取html中所有img标签的src值
     *
     * @param htmlStr html字符串
     * @return 图片链接数组
     */
    public static List<String> getImgSrc(String htmlStr) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<String>();
//       String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            img = img + "," + m_image.group();
            // Matcher m =
            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    /**
     * 检测图片是否存在
     *
     * @param source 图片链接
     * @return 是否存在
     */
    public static boolean checkRource(String source) {
        try {
            URL url = new URL(source);
            URLConnection uc = url.openConnection();
            InputStream in = uc.getInputStream();
            if (source.equalsIgnoreCase(uc.getURL().toString())) {
                in.close();
            }
            return true;
        } catch (Exception e) {
            logger.error("图片不存在：" + source);
            return false;
        }
    }

    public static void main(String[] args) {
        boolean exists = ImageUtil.checkRource("https://img2018.cnblogs.com/blog/236099/201903/236099-20190309195913811-558449509.png");
        System.out.println("exists = " + exists);
    }
}
