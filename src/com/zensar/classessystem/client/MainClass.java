package com.zensar.classessystem.client;

import com.zensar.classessystem.exceptions.ClassesServicesNotFoundException;
import com.zensar.classessystem.exceptions.CourseDetailsNotFoundException;
import com.zensar.classessystem.exceptions.InvalidEnrollMentNoException;
import com.zensar.classessystem.exceptions.InvalidModeOfPaymentTypeException;
import com.zensar.classessystem.exceptions.InvalidStudentIdException;
import com.zensar.classessystem.exceptions.LessPaymentAmountException;
import com.zensar.classessystem.exceptions.StudentDetailsNotFoundException;
import com.zensar.classessystem.exceptions.StudentEnrollMentDetailsNotFoundException;
import com.zensar.classessystem.serviceprovider.CoachingDAOServiceProvider;
import com.zensar.classessystem.serviceprovider.CoachingServiceProvider;
import com.zensar.classessystem.services.ClassesServices;

public class MainClass {
	public static void main(String[] args) {
		ClassesServices services;
		try {
			services=CoachingServiceProvider.getClassService();
			services.getAllCoursesDetails();
			int studentId=services.acceptStudentDetails("Ravi", "98572654", "ravi.gupta@gmail.com", "Pune", "Maha", "India", 452001);
			services.enrollStudentForCourse(studentId, "100", "lumsum");
			services.enrollStudentForCourse(studentId, "200", "installment");
			services.enrollStudentForCourse(studentId, "300", "lumsum");
			services.acceptPayment(studentId, 1001, "100", 25000);
			services.getRemainingFeesAmount(studentId);
			services.getStudentAccountDetails(studentId, 1001);
			services.getStudentAllAccountDetails(studentId);
			services.getStudentDetails(studentId);
			
			} catch (ClassesServicesNotFoundException e) {
			e.printStackTrace();
		} catch (CourseDetailsNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidStudentIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StudentDetailsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidModeOfPaymentTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidEnrollMentNoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StudentEnrollMentDetailsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LessPaymentAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
