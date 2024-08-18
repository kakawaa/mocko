package org.chobit.mocko.server.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author robin
 */
@Builder
@Data
public class UserItem {


	/**
	 * 用户名
	 */
	private String username;


	/**
	 * token
	 */
	private String token;


	/**
	 * 上次登录时间
	 */
	private LocalDateTime lastLoginTime;

}
