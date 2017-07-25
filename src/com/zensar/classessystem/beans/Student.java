package com.zensar.classessystem.beans;

import java.util.ArrayList;

public class Student {
	private int studentId;
	private String  studentName, mobileNo, emailId;
	private Address address;
	private ArrayList<StudentAccount> studentAccounts;
	
	public Student(int studentId, String studentName, String mobileNo,
			String emailId, Address address,
			ArrayList<StudentAccount> studentAccounts) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.address = address;
		this.studentAccounts = studentAccounts;
	}
	
	public Student(String studentName, String mobileNo, String emailId,
			Address address) {
		super();
		this.studentName = studentName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.address = address;
	}

	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ArrayList<StudentAccount> getStudentAccounts() {
		return studentAccounts;
	}
	public void setStudentAccounts(ArrayList<StudentAccount> studentAccounts) {
		this.studentAccounts = studentAccounts;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName="
				+ studentName + ", mobileNo=" + mobileNo + ", emailId="
				+ emailId + ", address=" + address + ", studentAccounts="
				+ studentAccounts + "]";
	}
	

}
