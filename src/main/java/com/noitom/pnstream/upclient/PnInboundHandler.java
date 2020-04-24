package com.noitom.pnstream.upclient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PnInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private final Channel serverChannel;
    private static long time1=0;
    private static long count=0;
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



    public PnInboundHandler(Channel channel) {
        serverChannel = channel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        long curTime = System.currentTimeMillis();
        if(time1 == 0 || curTime-time1>=1000){
            time1 = curTime;
            System.out.println(dateFormat.format(new Date(curTime))+",fps="+count+",size="+byteBuf.readableBytes());

            count = 0;
        }
        count++;

        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        serverChannel.writeAndFlush(data);
    }
}
