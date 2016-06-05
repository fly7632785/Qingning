package com.jafir.qingning.model.bean;

import java.io.Serializable;

/**
 * @author PanHoucheng
 * @time 2016年1月28日 下午3:27:19
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String lastUpStringTime;

    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastUpStringTime() {
        return lastUpStringTime;
    }

    public void setLastUpStringTime(String lastUpStringTime) {
        this.lastUpStringTime = lastUpStringTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "id=" + id +
                ", lastUpStringTime=" + lastUpStringTime +
                ", createTime=" + createTime +
                '}';
    }
}
