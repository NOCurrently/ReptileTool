package com.xc.po;

import lombok.Data;

import java.util.Date;

/**
 * 字典值pojo
 *
 * @author 肖超
 * @date: 2019年5月2日
 */
@Data
public class DictValue{

	private Integer id;

    private String typeCode;

    private String name;
    private String valueStr;

    private Integer sort;

    private Integer status;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;

    private String remarks;

   
}