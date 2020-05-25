package com.hnk.account.core.model;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * @author 懒猴子CG
 * @date 2020/05/25 16:04
 */
@Data
public class PageData<T> implements Serializable {

    // 目标页
    private int page;

    // 一页多少行
    private int capacity;

    // 总记录数
    private long total;

    // 当前的数据
    private List<T> records;

    public PageData(int page, int capacity) {
        this.page = page - 1; // JPA分页从0开始
        this.capacity = capacity;
    }
  
    public static PageData from(PageInfo pageInfo) {
        PageData pageData = new PageData(pageInfo.getPageNum(), pageInfo.getPageSize());
        pageData.total = pageInfo.getTotal();
        pageData.records = pageInfo.getList();
        return pageData;
    }

    /**
     * 计算总页码
     * @author Caesar Liu
     * @date 2020-03-29 15:10
     */
    public long getPageCount(){
        if(this.getTotal() % this.getCapacity() == 0){
            long pc = this.getTotal()/this.getCapacity();
            return pc == 0 ? 1 : pc;
        }
        return this.getTotal()/this.getCapacity() + 1;
    }

}