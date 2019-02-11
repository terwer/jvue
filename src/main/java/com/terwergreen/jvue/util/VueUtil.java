package com.terwergreen.jvue.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Vue工具类
 *
 * @author Terwer
 * @version 1.0
 * 2019/1/14 11:36
 **/
public class VueUtil {
    private static final Log logger = LogFactory.getLog(VueUtil.class);
    /**
     * Vue资源文件目录
     */
    private static final String VUE_RESOURCE_PATH = "/templates/";

    /**
     * 读取脚本
     *
     * @param resourceName 脚本名称
     * @return 脚本字符
     */
    public static String readVueResourceString(final String resourceName) {
        String result = null;
        try {
            URL resourcePath = VueUtil.class.getResource(VUE_RESOURCE_PATH + resourceName);
            logger.info("resourcePath:" + resourcePath);
            result = Resources.toString(resourcePath, Charsets.UTF_8);
        } catch (Exception e) {
            logger.error("文件读取失败:" + resourceName + ",", e);
        }
        return result;
    }

    /**
     * 读取资源文件
     *
     * @param fileName 文件名称
     * @return Reader
     */
    public static Reader readVueFileReader(final String fileName) {
        InputStream in = null;
        try {
            String appFilename = getVueFileResource(fileName);
            in = new FileInputStream(appFilename);
        } catch (FileNotFoundException e) {
            logger.error("Vue资源文件不存在", e);
        }
        return new InputStreamReader(in);
    }

    /**
     * 读取资源文件
     *
     * @param fileName 文件名称
     * @return File
     */
    public static File readVueFile(final String fileName) {
        File file = null;
        String appFilename = getVueFileResource(fileName);
        file = new File(appFilename);
        return file;
    }

    /**
     * 根据正则名称获取Vue资源文件
     *
     * @param fileName 文件名称
     * @return 匹配的第一个文件
     */
    private static String getVueFileResource(final String fileName) {
        List<String> filenameList = new ArrayList<>();
        try {
            // 获取JS路径
            URL resourcePath = VueUtil.class.getResource(VUE_RESOURCE_PATH);
            logger.info("resourcePath = " + resourcePath.toURI());
            File path = new File(resourcePath.toURI());
            String[] list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(fileName);

                @Override
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });
            if (list.length > 0) {
                // Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
                for (String dirItem : list) {
                    String absFilename = FilenameUtils.concat(path.getPath(), dirItem);
                    filenameList.add(absFilename);
                }
                logger.info("filenameList:" + JSON.toJSONString(filenameList));
            }
        } catch (URISyntaxException e) {
            logger.error("Vue文件路径错误", e);
        }

        if (filenameList.size() > 0) {
            return filenameList.get(0);
        }
        return null;
    }
}
