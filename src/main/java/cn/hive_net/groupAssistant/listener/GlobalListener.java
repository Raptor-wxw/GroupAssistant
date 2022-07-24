package cn.hive_net.groupAssistant.listener;

import cn.hive_net.groupAssistant.util.PermissionUtil;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import cn.hive_net.groupAssistant.config.Config;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.apache.commons.lang3.StringUtils;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: GlobalListener
 * Description: 全局Event监听器的加载类
 *
 * @author: 王晓文
 * @date: 2022/6/2 21:00
 */
public class GlobalListener {

    public static final GlobalListener INSTANCE = new GlobalListener();
    private final Config config = Config.INSTANCE;
    private final MemberOptionHolder memberOptionHolder = MemberOptionHolder.INSTANCE;

    public void listen(EventChannel<Event> eventChannel) {
        if(!config.isEnable()) return;

        /*
         * 载入用户操作(memberOptionHolder)监听器
         * 过滤所需权限：  1.是机器人的host
         *              2.群聊为有权限群聊
         *              3.消息内容不为空
         */
        eventChannel.filter(event ->
                PermissionUtil.isHost(((GroupMessageEvent) event).getSender().getId()) &&
                PermissionUtil.isGroupEnable(((GroupMessageEvent) event).getGroup().getId()) &&
                StringUtils.isNotBlank(((GroupMessageEvent) event).getMessage().contentToString()))
                .registerListenerHost(memberOptionHolder);
    }
}
