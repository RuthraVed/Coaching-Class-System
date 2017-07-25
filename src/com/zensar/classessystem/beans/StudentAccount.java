package com.zensar.classessystem.beans;
import java.util.ArrayList;
public class StudentAccount {
	private int enrollmentNo;
	private Course course;
	private String modeOfPayment;
	private int feesAfterDiscount;
	private int feesRemaining;
	private ArrayList<InstallmentDetail> installmentDetails;
	
	public StudentAccount(int enrollmentNo, Course course,
			String modeOfPayment, int feesAfterDiscount, int feesRemaining,
			ArrayList<InstallmentDetail> installmentDetails) {
		super();
		this.enrollmentNo = enrollmentNo;
		this.course = course;
		this.modeOfPayment = modeOfPayment;
		this.feesAfterDiscount = feesAfterDiscount;
		this.feesRemaining = feesRemaining;
		this.installmentDetails = installmentDetails;
	}
	
	
	public StudentAccount(int enrollmentNo, String modeOfPayment,
			int feesAfterDiscount, int feesRemaining) {
		super();
		this.enrollmentNo = enrollmentNo;
		this.modeOfPayment = modeOfPayment;
		this.feesAfterDiscount = feesAfterDiscount;
		this.feesRemaining = feesRemaining;
	}


	public StudentAccount(Course course, String modeOfPayment,
			int feesAfterDiscount, int feesRemaining) {
		super();
		this.course = course;
		this.modeOfPayment = modeOfPayment;
		this.feesAfterDiscount = feesAfterDiscount;
		this.feesRemaining = feesRemaining;
	}


	public StudentAccount(String modeOfPayment, int feesAfterDiscount,
			int feesRemaining) {
		super();
		this.modeOfPayment = modeOfPayment;
		this.feesAfterDiscount = feesAfterDiscount;
		this.feesRemaining = feesRemaining;
	}


	public StudentAccount(String modeOfPayment) {
		super();
		this.modeOfPayment = modeOfPayment;
	}

	public int getEnrollmentNo() {
		return enrollmentNo;
	}
	public void setEnrollmentNo(int enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public int getFeesAfterDiscount() {
		return feesAfterDiscount;
	}
	public void setFeesAfterDiscount(int feesAfterDiscount) {
		this.feesAfterDiscount = feesAfterDiscount;
	}
	public int getFeesRemaining() {
		return feesRemaining;
	}
	public void setFeesRemaining(int feesRemaining) {
		this.feesRemaining = feesRemaining;
	}
	public ArrayList<InstallmentDetail> getInstallmentDetails() {
		return installmentDetails;
	}
	public void setInstallmentDetails(
			ArrayList<InstallmentDetail> installmentDetails) {
		this.installmentDetails = installmentDetails;
	}
	@Override
	public String toString() {
		return "StudentAccount [enrollmentNo=" + enrollmentNo + ", course="
				+ course + ", modeOfPayment=" + modeOfPayment
				+ ", feesAfterDiscount=" + feesAfterDiscount
				+ ", feesRemaining=" + feesRemaining + ", installmentDetails="
				+ installmentDetails + "]";
	}
	
}
