package com.xc.po;

import lombok.Data;

import java.util.Date;

/**
 * 字典pojo
 *
 * @author 肖超
 * @date: 2019年5月2日
 */
@Data
public class DictType{

	private Integer id;

	private Integer type;

	private String code;

	private String name;

	private Integer status;
	private String createBy;
	private String updateBy;
	private Date createTime;
	private Date updateTime;

	private String remarks;

	
}