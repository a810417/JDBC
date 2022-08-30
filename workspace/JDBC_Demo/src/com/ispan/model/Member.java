package com.ispan.model;

public class Member {
	
	// Java Bean

	// instance variable 不可以是 public
	private int memberId;
	private String memberName;
	private String memberAddress;
	private String phone;

	// 一定要有全空的 Constructor
	public Member() {
	}

	public Member(int memberId, String memberName, String memberAddress, String phone) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberAddress = memberAddress;
		this.phone = phone;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
