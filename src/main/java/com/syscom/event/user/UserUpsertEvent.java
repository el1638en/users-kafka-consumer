package com.syscom.event.user;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = { "password" })
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "password" })
public class UserUpsertEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String firstName;

	private String login;

	private String password;

	private LocalDate birthDay;

}
