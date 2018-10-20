package com.viktor.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Viktor Angelutsa
 *
 */
@Entity
@JsonPropertyOrder({ "id", "firstName", "lastName", "nickName", "password", "email", "country" })
@ApiModel("User Model")
public class User {

	@Id
	@GeneratedValue
	@ApiModelProperty(value = "user id", required = false)
	private long id;

	@Column(nullable = false)
	@ApiModelProperty(value = "user first name", required = true)
	private String firstName;

	@Column(nullable = false)
	@ApiModelProperty(value = "user last name", required = true)
	private String lastName;

	@Column(nullable = false)
	@ApiModelProperty(value = "user nick name", required = true)
	private String nickName;

	@Column(nullable = false)
	@ApiModelProperty(value = "user password", required = true)
	private String password;

	@Column(nullable = false)
	@ApiModelProperty(value = "user email", required = true)
	private String email;

	@Column(nullable = false)
	@ApiModelProperty(value = "user country", required = true)
	private String country;

	protected User() {

	}

	@JsonCreator
	public User(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
			@JsonProperty("nickName") String nickName, @JsonProperty("password") String password,
			@JsonProperty("email") String email, @JsonProperty("country") String country) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
		this.country = country;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() {
		return "id: '" + this.id + "', firstName: '" + this.firstName + "', lastName: '" + this.lastName
				+ "', nickName: '" + this.nickName + "', email: '" + this.email + "', country: '" + this.country + "'";
	}
}