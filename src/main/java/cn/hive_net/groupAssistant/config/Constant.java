package cn.hive_net.groupAssistant.config;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: Constant
 * Description: GroupAssistant的关键词常量
 *
 * @author: 王晓文
 * @date: 2022/6/2 21:35
 */
public interface Constant {

    // ============================== 触发关键词 ============================== //

    /**
     * 禁言成员关键词
     */
    public final String MUTE_PREFIX = "禁言";

    /**
     * 解除禁言关键词
     */
    public final String UNMUTE_PREFIX = "解除禁言";

    /**
     * 踢出群关键词
     */
    public final String KICK_PREFIX = "踢";

    /**
     * 修改群名片关键词
     */
    public final String NICK_PREFIX = "修改群昵称";

    /**
     * 授予管理员权限关键词
     */
    public final String GRANT_ADMIN_PREFIX = "设为管理";

    /**
     * 收回管理员权限关键词
     */
    public final String REVOKE_ADMIN_PREFIX = "撤销管理";

    // ============================== 关键词的回复 ============================== //

    /**
     * 权限不足回复
     */
    public final String PERMISSION_DENIED_REPLY = "机器人的权限不足！";
    /**
     * 已禁言回复
     */
    public final String MUTING_REPLY = "%s 已经在禁言中了";

    /**
     * 不在禁言回复
     */
    public final String UNMUTE_REPLY = "%s 不在禁言中";

    /**
     * 踢出回复
     */
    public final String KICK_REPLY = "%s 已被踢出";

    /**
     * 修改群昵称回复
     */
    public final String ALTER_NICK_REPLY = "已修改";

    /**
     * 授予管理员回复
     */
    public final String GRANT_REPLY = "已设置%s为管理员";

    /**
     * 撤销管理员回复
     */
    public final String REVOKE_REPLY = "已撤销%s的管理员权限";
}
