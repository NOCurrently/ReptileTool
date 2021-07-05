package com.xc.service;

import com.xc.mapper.OperationLogMapper;
import com.xc.po.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张霞文
 * @date 2019/7/23
 */
@Service
public class OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    /**
     * 新增日志数据
     * @auther: 张霞文
     * @date: 2019/7/24
     * @param: [reqBo]
     * @return: int
     */
    public int insertSelective(OperationLog operationLog) {
        return operationLogMapper.insertSelective(operationLog);
    }
}
