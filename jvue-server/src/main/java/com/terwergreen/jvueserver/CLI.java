package com.terwergreen.jvueserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;

public class CLI {
    private static final Log logger = LogFactory.getLog(CLI.class);

    /**
     * 运行命令
     * mvn -v && mvn compile && mvn exec:java -Dexec.args="arg0 arg1 arg2"
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        logger.info("Hello World=>" + Arrays.toString(args));
        System.out.println("Hello World=>" + Arrays.toString(args));
    }
}
