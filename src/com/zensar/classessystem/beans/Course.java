package com.zensar.classessystem.beans;

public class Course {
	private String courseCode, courseTechnologyName;
	private int fees, durationHrs;
	public Course(String courseCode, String courseTechnologyName, int fees,
			int durationHrs) {
		super();
		this.courseCode = courseCode;
		this.courseTechnologyName = courseTechnologyName;
		this.fees = fees;
		this.durationHrs = durationHrs;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseTechnologyName() {
		return courseTechnologyName;
	}
	public void setCourseTechnologyName(String courseTechnologyName) {
		this.courseTechnologyName = courseTechnologyName;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public int getDurationHrs() {
		return durationHrs;
	}
	public void setDurationHrs(int durationHrs) {
		this.durationHrs = durationHrs;
	}
	@Override
	public String toString() {
		return "Course [courseCode=" + courseCode + ", courseTechnologyName="
				+ courseTechnologyName + ", fees=" + fees + ", durationHrs="
				+ durationHrs + "]";
	}
	
}
