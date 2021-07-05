package com.xc.mapper;

import com.xc.po.DictValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典值
 *
 * @author 肖超
 * @date: 2019年5月2日
 */
public interface DictValueMapper {


	/**
	 * 增量增加
	 *
	 * @author: 肖超
	 * @date: 2019年5月2日
	 * @param record
	 * @return
	 */
	int insertSelective(DictValue record);


	/**
	 * 在增量修改
	 *
	 * @author: 肖超
	 * @date: 2019年5月2日
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(DictValue record);


	/**
	 * 类型id查找
	 * 
	 * @author: 肖超
	 * @date: 2019年4月17日
	 * @param typeId
	 * @return
	 */
	List<DictValue> selectByTypeCode(@Param("code") String code);



}