package cn.hive_net.groupAssistant;

import cn.hive_net.groupAssistant.listener.GlobalListener;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;


/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: GroupAssistant
 * Description: QQ群管理助手插件主类
 *
 * @author: 王晓文
 * @date: 2022/6/2 20:35
 */
public final class GroupAssistant extends JavaPlugin {
    public static final GroupAssistant INSTANCE = new GroupAssistant();
    private final GlobalListener globalListener = GlobalListener.INSTANCE;

    private GroupAssistant() {
        super(new JvmPluginDescriptionBuilder("cn.hive_net.groupAssistant.plugin", "1.0.0")
                .name("QQ群管理助手")
                .info("联系作者VX：Rem_wife\\n关注公众号：夜寒信息")
                .author("暮至夜寒")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("QQ群管理助手 by暮至夜寒 Plugin loaded!");
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        globalListener.listen(eventChannel);
    }
}