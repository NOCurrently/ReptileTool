package com.xc.mapper;

import com.xc.po.DictType;
import org.apache.ibatis.annotations.Param;

/**
 * 字典类型
 *
 * @author 肖超
 * @date: 2019年5月2日
 */
public interface DictTypeMapper {


	/**
	 * 增量添加
	 *
	 * @author: 肖超
	 * @date: 2019年5月2日
	 * @param record
	 * @return
	 */
	int insertSelective(DictType record);


	/**
	 * 增量修改
	 *
	 * @author: 肖超
	 * @date: 2019年5月2日
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(DictType record);



	/**
	 * code查询
	 * 
	 * @param code
	 * @return
	 * @author: 肖超
	 * @date: 2019年4月17日
	 */
	DictType selectByCode(@Param("code") String code);
}