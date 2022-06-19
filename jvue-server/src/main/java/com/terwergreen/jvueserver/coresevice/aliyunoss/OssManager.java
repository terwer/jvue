package com.terwergreen.jvueserver.coresevice.aliyunoss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * endpoint
 * accessKeyId  阿里云自己申请的
 * accessKeySecret 阿里云自己申请的
 * token 后台生成
 */
public class OssManager {
    private static final Logger logger = LoggerFactory.getLogger(OssManager.class);

    private static final String ENDPOINT = "${ALIYUN_OSS_ENDPOINT}";
    private static final String ACCESS_KEY = "${ALIYUN_OSS_ACCESS_KEY}";
    private static final String ACCESS_SECRET = "${ALIYUN_OSS_ACCESS_SECRET}";
    private static final String BUCKET_NAME = "${ALIYUN_OSS_BUCKET_NAME}";

    private static OSS client = null;

    public static OssManager getInstance() {
        return OssInstance.instance;
    }

    private static class OssInstance {
        private static final OssManager instance = new OssManager();
    }

    private OssManager() {
    }

    public void upload(String name, byte[] bytes) {
        try {
            client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY, ACCESS_SECRET);

            /*
             * Create an empty folder without request body, note that the key must be
             * suffixed with a slash
             */
            client.putObject(BUCKET_NAME, name, new ByteArrayInputStream(bytes));

            /*
             * Verify whether the size of the empty folder is zero
             */
            OSSObject object = client.getObject(BUCKET_NAME, name);
            logger.info("Size of the file '" + object.getKey() + "' is " +
                    object.getObjectMetadata().getContentLength());
            object.getObjectContent().close();

        } catch (OSSException oe) {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            logger.error("Error Message: " + oe.getErrorMessage());
            logger.error("Error Code:       " + oe.getErrorCode());
            logger.error("Request ID:      " + oe.getRequestId());
            logger.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            logger.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            logger.error("Error Message: " + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }
}
