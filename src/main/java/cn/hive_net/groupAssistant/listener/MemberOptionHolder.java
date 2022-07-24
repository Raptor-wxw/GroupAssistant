package cn.hive_net.groupAssistant.listener;

import cn.hive_net.groupAssistant.config.Constant;
import cn.hive_net.groupAssistant.util.MessageSplitUtil;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.contact.PermissionDeniedException;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;


/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: MuteHolder
 * Description: 管理群成员操作的类
 *
 * @author: 王晓文
 * @date: 2022/6/2 21:34
 */
public class MemberOptionHolder extends SimpleListenerHost implements Constant {

    public static final MemberOptionHolder INSTANCE = new MemberOptionHolder();

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        System.out.println("出现未捕获错误");
    }

    // 禁言操作
    @EventHandler
    public void onMute(@NotNull GroupMessageEvent event) throws Exception {
        Group group = event.getGroup();
        String[] muteArgs = MessageSplitUtil.groupMessageEventSplitter(event, MUTE_PREFIX);
        // 检查非空
        if (muteArgs == null)
            muteArgs = MessageSplitUtil.groupMessageEventSplitter(event, MUTE_PREFIX, 3);
        if (muteArgs == null) return;

        //禁言操作
        long id = Long.parseLong(muteArgs[1].substring(1));
        NormalMember member = group.getMembers().get(id);
        assert member != null;
        if (member.isMuted()) {
            group.sendMessage(String.format(MUTING_REPLY, member.getNick()));
            return;
        }
        try {
            if (muteArgs.length == 2) {
                member.mute(60);
            } else if (muteArgs.length == 3) {
                member.mute(60*Integer.parseInt(muteArgs[2]));
            }
        } catch (PermissionDeniedException exception) {
            group.sendMessage(PERMISSION_DENIED_REPLY);
        }
    }

    // 解除禁言操作
    @EventHandler
    public void onUnMute(@NotNull GroupMessageEvent event) throws Exception {
        Group group = event.getGroup();
        String[] unMuteArgs = MessageSplitUtil.groupMessageEventSplitter(event, UNMUTE_PREFIX);

        // 检查非空
        if (unMuteArgs == null) return;

        //禁言操作
        long id = Long.parseLong(unMuteArgs[1].substring(1));
        NormalMember member = group.getMembers().get(id);
        assert member != null;
        if (!member.isMuted()) {
            group.sendMessage(String.format(UNMUTE_REPLY, member.getNick()));
            return;
        }
        try{
            member.unmute();
        } catch (PermissionDeniedException exception) {
            group.sendMessage(PERMISSION_DENIED_REPLY);
        }
    }

    // 踢出群聊
    @EventHandler
    public void onKick(@NotNull GroupMessageEvent event) {
        Group group = event.getGroup();
        String[] kickArgs = MessageSplitUtil.groupMessageEventSplitter(event, KICK_PREFIX);

        // 检查非空
        if (kickArgs == null) return;

        long id = Long.parseLong(kickArgs[1].substring(1));
        NormalMember member = group.getMembers().get(id);
        assert member != null;
        try{
            member.kick(String.format(KICK_REPLY, member.getNick()));
        } catch (PermissionDeniedException exception) {
            group.sendMessage(PERMISSION_DENIED_REPLY);
        }
    }

    // 修改群昵称
    @EventHandler
    public void onAlterNick(@NotNull GroupMessageEvent event) {
        Group group = event.getGroup();
        String[] nickArgs = MessageSplitUtil.groupMessageEventSplitter(event, NICK_PREFIX, 3);

        // 检查非空
        if (nickArgs == null) return;

        long id = Long.parseLong(nickArgs[1].substring(1));
        NormalMember member = group.getMembers().get(id);
        assert member != null;
        try {
            member.setNameCard(nickArgs[2]);
        } catch (PermissionDeniedException exception) {
            group.sendMessage(PERMISSION_DENIED_REPLY);
            return;
        }
        group.sendMessage(ALTER_NICK_REPLY);
    }

    // 设为管理员
    @EventHandler
    public void onGrantAdmin(@NotNull GroupMessageEvent event) {
        Group group = event.getGroup();
        String[] adminArgs = MessageSplitUtil.groupMessageEventSplitter(event, GRANT_ADMIN_PREFIX);

        // 检查非空
        if (adminArgs == null) return;

        long id = Long.parseLong(adminArgs[1].substring(1));
        NormalMember member = group.getMembers().get(id);
        assert member != null;
        try {
            member.modifyAdmin(true);
        } catch (PermissionDeniedException exception) {
            group.sendMessage(PERMISSION_DENIED_REPLY);
            return;
        }
        group.sendMessage(String.format(GRANT_REPLY, member.getNick()));
    }

    // 撤销管理员
    @EventHandler
    public void onRevokeAdmin(@NotNull GroupMessageEvent event) {
        Group group = event.getGroup();
        String[] adminArgs = MessageSplitUtil.groupMessageEventSplitter(event, REVOKE_ADMIN_PREFIX);

        // 检查非空
        if (adminArgs == null) return;

        long id = Long.parseLong(adminArgs[1].substring(1));
        NormalMember member = group.getMembers().get(id);
        assert member != null;
        try{
            member.modifyAdmin(false);
        } catch (PermissionDeniedException exception) {
            group.sendMessage(PERMISSION_DENIED_REPLY);
            return;
        }
        group.sendMessage(String.format(REVOKE_REPLY, member.getNick()));
    }
}
