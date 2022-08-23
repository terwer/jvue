package com.terwergreen.jvueserver.coresevice.xmlrpc;

import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * 自定义XmlrpcServlet处理类
 *
 * @name: MyXmlRpcServlet
 * @author: terwer
 * @date: 2022-08-23 15:41
 **/
public class MyXmlRpcServlet extends XmlRpcServlet {
    @Override
    public void init(ServletConfig pConfig) throws ServletException {
        super.init(pConfig);

        // enabledForExtensions: 指定是否启用 Apache 对 XML-RPC 的扩展。
        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) this.getXmlRpcServletServer().getConfig();
        serverConfig.setEnabledForExceptions(true);
        serverConfig.setEnabledForExtensions(true);
    }
}
