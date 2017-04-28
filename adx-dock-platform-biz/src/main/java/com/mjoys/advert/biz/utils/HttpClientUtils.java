package com.mjoys.advert.biz.utils;

import com.mjoys.advert.common.constants.ErrorCode;
import com.mjoys.advert.common.exception.InnerException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 18:55.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class HttpClientUtils {
    private static final Log log = LogFactory.getLog(HttpClientUtils.class);

    // 读取超时
    private final static int SOCKET_TIMEOUT = 10000;
    // 连接超时
    private final static int CONNECTION_TIMEOUT = 10000;
    // 每个HOST的最大连接数量
    private final static int MAX_CONN_PRE_HOST = 20;
    // 连接池的最大连接数
    private final static int MAX_CONN = 60;
    // 连接池
    private final static HttpConnectionManager httpConnectionManager;

    static {
        httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(CONNECTION_TIMEOUT);
        params.setSoTimeout(SOCKET_TIMEOUT);
        params.setDefaultMaxConnectionsPerHost(MAX_CONN_PRE_HOST);
        params.setMaxTotalConnections(MAX_CONN);
    }

    /**
     * 远程http调用
     *
     * @param method
     * @return
     */
    public static String doHttpRequest(HttpMethod method) {

        return exectureMethod(getHttpClient(), method);

    }

    /**
     * 从http池中获取一个HttpClient
     *
     * @return
     */
    private static HttpClient getHttpClient() {
        HttpClient httpClient = new HttpClient(httpConnectionManager);
        // 设置读取超时时间(单位毫秒)
        httpClient.getParams().setParameter("http.socket.timeout", SOCKET_TIMEOUT);
        // 设置连接超时时间(单位毫秒)
        httpClient.getParams().setParameter("http.connection.timeout", CONNECTION_TIMEOUT);
        httpClient.getParams().setParameter("http.connection-manager.timeout", 100000000L);

        return httpClient;
    }

    /**
     * 执行远程调用
     *
     * @param httpClient
     * @param method
     * @return
     */
    private static String exectureMethod(HttpClient httpClient, HttpMethod method) {
        BufferedReader in = null;
        String resultString = "";
        try {
            int statusCode = httpClient.executeMethod(method);
            if (HttpStatus.SC_OK == statusCode) {
                return method.getResponseBodyAsString();
            }

        } catch (SocketTimeoutException e) {
            log.error("连接超时" + e.toString());
            throw new InnerException(ErrorCode.E00000, e);
        } catch (HttpException e) {
            log.error("读取外部服务器数据失败" + e.toString());
            throw new InnerException(ErrorCode.E00000, e);
        } catch (UnknownHostException e) {
            log.error("请求的主机地址无效" + e.toString());
            throw new InnerException(ErrorCode.E00000, e);
        } catch (IOException e) {
            log.error("向外部接口发送数据失败" + e.toString());
            throw new InnerException(ErrorCode.E00000, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("向外部接口发送数据失败" + e.toString());
                }
            }
            method.releaseConnection();
        }
        return resultString;
    }
}
