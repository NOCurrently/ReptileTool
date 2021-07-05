package com.xc.po;

import lombok.Data;

import java.util.Date;

@Data
public class OperationLog {
    private Integer id;

    private String type;

    private String createBy;

    private String url;

    private Date createTime;

    private String operationData;

}