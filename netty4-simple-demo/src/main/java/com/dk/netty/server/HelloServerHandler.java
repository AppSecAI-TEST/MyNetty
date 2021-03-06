package com.dk.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA
 * HelloServerHandler
 *
 * @author dk
 * @date 2017/6/22 11:45
 */
public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
       System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);

        // 返回客户端消息 - 我已经接收到了你的消息
       ctx.writeAndFlush("Received your message! msg:"+msg+"\n");
    }


    /**
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
        super.channelActive(ctx);
    }

    //会定时清除闲置会话
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}
