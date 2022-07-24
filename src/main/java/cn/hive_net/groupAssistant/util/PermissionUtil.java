package cn.hive_net.groupAssistant.util;

import cn.hive_net.groupAssistant.config.Config;

import java.util.List;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: PermissionUtil
 * Description: 权限检测工具
 *
 * @author: 王晓文
 * @date: 2022/6/2 23:00
 */
public class PermissionUtil {

    public static boolean isHost(Long id) {
        for (Long host:Config.INSTANCE.getHosts()) {
            if (host.equals(id)) return true;
        }
        return false;
    }

    public static boolean isGroupEnable(Long id) {
        for (Long group:Config.INSTANCE.getGroups()) {
            if (group.equals(id)) return true;
        }
        return false;
    }
}
