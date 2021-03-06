package com.changan.icar.comm.po;

import java.io.Serializable;

public class IcarAdmin implements Serializable {
	@Override
	public String toString() {
		return "IcarAdmin [username=" + username + ", password=" + password + ", nickName=" + nickName + ", type="
				+ type + ", headImg=" + headImg + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4787858487168507162L;

	private String username;

	private String password;

	private String nickName;

	private Integer type;

	private String headImg;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg == null ? null : headImg.trim();
	}
}