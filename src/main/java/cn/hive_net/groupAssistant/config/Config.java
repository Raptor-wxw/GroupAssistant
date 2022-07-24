package cn.hive_net.groupAssistant.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: Config
 * Description: QQ群管理助手配置文件
 *
 * @author: 王晓文
 * @date: 2022/6/2 20:40
 */
public class Config {

    public static final Config INSTANCE = new Config();

    private boolean enable = true;
    private List<Long> hosts = new ArrayList<Long>(){{
        add(296854007L);
        add(2689969038L);
        add(168745806L);
    }};
    private List<Long> groups = new ArrayList<Long>(){{
        add(726925125L);
        add(103172845L);
    }};

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Long> getHosts() {
        return hosts;
    }

    public void setHosts(List<Long> hosts) {
        this.hosts = hosts;
    }

    public void addHost(Long host) {
        for (Long id : this.hosts)
            if (id.equals(host)) return;
        this.hosts.add(host);
    }

    public List<Long> getGroups() {
        return groups;
    }

    public void removeHost(Long host) {
        this.hosts.remove(host);
    }

    public void setGroups(List<Long> groups) {
        this.groups = groups;
    }

    public void addGroup(Long group) {
        for (Long id : this.groups)
            if (id.equals(group)) return;
        this.groups.add(group);
    }

    public void removeGroup(Long group) {
        this.groups.remove(group);
    }

    @Override
    public String toString() {
        return "Config{" +
                "enable=" + enable +
                ", hosts=" + hosts +
                ", groups=" + groups +
                '}';
    }
}
