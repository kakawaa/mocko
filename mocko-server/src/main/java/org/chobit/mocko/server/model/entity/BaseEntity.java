package org.chobit.mocko.server.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * entity基础类
 *
 * @author robin
 */
@Data
abstract class BaseEntity {


	/**
	 * 记录ID
	 */
	private Integer id;


	/**
	 * 操作人code
	 */
	private String operatorCode;


	/**
	 * 删除标记
	 */
	private Integer deleted;


	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;


	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;


}
