package cn.hive_net.groupAssistant.util;

import cn.hive_net.groupAssistant.config.Constant;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: MessageSplitUtil
 * Description:
 *
 * @author: 王晓文
 * @date: 2022/7/24 19:42
 */
public class MessageSplitUtil implements Constant {

    public static String[] groupMessageEventSplitter(GroupMessageEvent event, String prefix) {
        String[] messageArgs = null;
        Group group = event.getGroup();
        String message = event.getMessage().contentToString();

        // 检查格式长度
        messageArgs = message.trim().split(" ");
        if (messageArgs.length != 2) return null;

        // 取出所有空格
        for (int i=0; i<messageArgs.length; i++) messageArgs[i] = messageArgs[i].trim();

        // 检查前缀
        if (!prefix.equals(messageArgs[0])) return null;

        return messageArgs;
    }

    public static String[] groupMessageEventSplitter(GroupMessageEvent event, String prefix, int nums) {
        String[] tempArgs = null;
        String message = event.getMessage().contentToString();


        tempArgs = message.trim().split(" ");
        ArrayList<String> messageList = new ArrayList<>();
        for (String arg:tempArgs) {
            if (StringUtils.isNotBlank(arg)) messageList.add(arg);
        }
        String[] messageArgs = messageList.toArray(new String[0]);

        // 检查格式长度
        if (messageArgs.length != nums) return null;

        // 取出所有空格
        for (int i=0; i<messageArgs.length; i++) messageArgs[i] = messageArgs[i].trim();

        // 检查前缀
        if (!prefix.equals(messageArgs[0])) return null;

        return messageArgs;
    }
}
