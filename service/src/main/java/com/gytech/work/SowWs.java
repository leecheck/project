package com.gytech.work;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseLogger;
import com.gytech.Configuration.token.JwtUtil;
import com.gytech.Security.entity.UserInfo;
import com.gytech.service.ISysUserService;
import com.gytech.work.entity.Affair;
import com.gytech.work.entity.Key;
import com.gytech.work.entity.StationDuty;
import com.gytech.work.entity.WsRes;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.security.Principal;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by LQ on 2018/10/9.
 * com.gytech.station
 */
@ServerEndpoint(value = "/SowWs")
@Component
public class SowWs extends BaseLogger {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<SowWs> webSocketSet = new CopyOnWriteArraySet<SowWs>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private static ApplicationContext applicationContext;

    private ISysUserService userService;

    private UserInfo userInfo;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SowWs.applicationContext = applicationContext;
    }
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        userService = applicationContext.getBean(ISysUserService.class);
        webSocketSet.add(this);
        logger.info("新连接加入，当前在线客户端数量：" + webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {

    }

    /**
     * 收到客户端消息后调用的方法
     * 客户端发送的message统一按WsRes格式进行封装
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        WsRes msg = JSON.parseObject(message,WsRes.class);
        String msType = msg.getType();
        Object data = msg.getData();
        String target = msg.getTarget();
        switch (msType){
            case Key.BASE_USERTOKEN:
                String token = String.valueOf(data);
                setUser(token,msg);
                break;
            case Key.DUTY_ORG_INFO:
                Long oid = Long.valueOf(target);
                break;

            default:
                    break;
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        Principal principal = session.getUserPrincipal();
        logger.info(principal == null ? "" : session.getUserPrincipal().getName() + "发生错误", error.getMessage());
        error.printStackTrace();
    }

    /**
     * 过滤操作 1、是否订阅 2、是否有权限
     *
     * @param res 返回true表示放行
     */
    public boolean filterMessage(WsRes res) {

        return false;
    }

    /**
     * 发送信息
     * @param res
     */
    public void sendMessage(WsRes res) {
        if (session != null) {
            if(true){
                synchronized(session){
                    try {
                        session.getBasicRemote().sendText(JSON.toJSONString(res));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                try {
                    session.getBasicRemote().sendText(JSON.toJSONString(res));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(WsRes res) {
        for (SowWs item : webSocketSet) {
            synchronized(item){
                item.sendMessage(res);
            }
        }
    }

    private void setUser(String token,WsRes req){
        UserInfo user = JwtUtil.extrack(token);
        if (user!=null){
            this.userInfo = user;
            sendMessage(req.success());
        }
        sendMessage(req);
    }



}
