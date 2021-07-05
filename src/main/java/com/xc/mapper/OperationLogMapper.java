package com.xc.mapper;

import com.xc.po.OperationLog;

public interface OperationLogMapper {

    /**
     * 根据参数添加日志数据
     * @auther: 张霞文
     * @date: 2019/7/24
     * @param: [record]
     * @return: int
     */
    int insertSelective(OperationLog record);
}