package com.zensar.classessystem.daoservice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zensar.classessystem.beans.Address;
import com.zensar.classessystem.beans.Course;
import com.zensar.classessystem.beans.InstallmentDetail;
import com.zensar.classessystem.beans.Student;
import com.zensar.classessystem.beans.StudentAccount;
import com.zensar.classessystem.exceptions.ClassesServicesNotFoundException;
import com.zensar.classessystem.exceptions.CourseDetailsNotFoundException;
import com.zensar.classessystem.exceptions.InvalidEnrollMentNoException;
import com.zensar.classessystem.exceptions.InvalidModeOfPaymentTypeException;
import com.zensar.classessystem.exceptions.InvalidStudentIdException;
import com.zensar.classessystem.exceptions.StudentDetailsNotFoundException;
import com.zensar.classessystem.exceptions.StudentEnrollMentDetailsNotFoundException;
import com.zensar.classessystem.serviceprovider.DBConnectionProvider;

public class CoachingClassDaoServicesImpl implements CoachingClassDaoServices {
	Connection con;	PreparedStatement pstmt;ResultSet rs;int custId;int enrollno;

	public CoachingClassDaoServicesImpl() throws ClassesServicesNotFoundException, SQLException {
		con=DBConnectionProvider.getDBConnecton();
		System.out.println(this.insertStudentDetails(new Student("Ravi", "98574838", "kjwerj", new Address("dfds", "sdsd", "g", 3434))));	
	}
	@Override
	public ArrayList<Course> getAllCoursesDetails()
			throws ClassesServicesNotFoundException, SQLException {
		try {
			pstmt=con.prepareStatement("select * from Coach_Course");
			rs=pstmt.executeQuery();
			con.commit();
			ArrayList<Course>courses=new ArrayList<>();
			while(rs.next())
				courses.add(new Course(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			return courses;
		} catch (SQLException e) {throw e;}
	}
	@Override
	public int insertStudentDetails(Student student)
			throws ClassesServicesNotFoundException, SQLException {

		try{con.setAutoCommit(false);
		pstmt=con.prepareStatement("insert into Coach_Course (studentId,studentName,mobileNo, emailId) values(COACH_STUDENT_SEQ.nextval,?,?,?)");
		pstmt.setString(1, student.getStudentName());
		pstmt.setString(2, student.getMobileNo());
		pstmt.setString(3, student.getEmailId());
		pstmt.executeUpdate();

		pstmt=con.prepareStatement("select max(studentId) from Coach_Student");
		rs=pstmt.executeQuery();
		if(rs.next())custId=rs.getInt(1);
		pstmt=con.prepareStatement("insert into Coach_Address values(?,?,?,?)");

		pstmt.setString(1, student.getAddress().getCity());
		pstmt.setString(2, student.getAddress().getState());
		pstmt.setString(3, student.getAddress().getCountry());
		pstmt.setInt(4, student.getAddress().getPinCode());
		pstmt.setInt(5, custId );
		pstmt.executeUpdate();
		con.commit();
		return custId;
		}
		catch(SQLException e){con.rollback();throw e;}
		finally{con.setAutoCommit(true);}
	}
	@Override
	public int enrollStudentForCourse(int studentId,String courseCode, StudentAccount account)
			throws InvalidStudentIdException, CourseDetailsNotFoundException,
			StudentDetailsNotFoundException, InvalidModeOfPaymentTypeException,
			ClassesServicesNotFoundException, SQLException {
		try{con.setAutoCommit(false);
		if(this.getCourse(courseCode)==null) throw new CourseDetailsNotFoundException("Course Detail Not Found!!");
		if(account.getModeOfPayment().equalsIgnoreCase("lumsum")){
			pstmt=con.prepareStatement("insert into Coach_studentAccount(EnrollmentNo,modeofpayment,FEEAFTERDISCOUNT,FEEREMAINING,coursecode,studentId) values(COACH_STACCOUNT_SEQ.nextval,?,?,?,?,?)");
			pstmt.setString(1, account.getModeOfPayment());
			pstmt.setString(4, account.getCourse().getCourseCode());
			pstmt.setInt(2, account.getFeesAfterDiscount());
			pstmt.setInt(3, account.getFeesRemaining());
			pstmt.setInt(5, studentId);
			pstmt.executeUpdate();con.commit();}
		else {if(account.getModeOfPayment().equalsIgnoreCase("installment")){
			pstmt=con.prepareStatement("insert into Coach_studentAccount(EnrollmentNo,modeofpayment,FEEAFTERDISCOUNT,FEEREMAINING,coursecode,studentId) values(COACH_STACCOUNT_SEQ.nextval,?,?,?,?,?)");
			pstmt.setString(1, account.getModeOfPayment());
			pstmt.setString(4, account.getCourse().getCourseCode());
			pstmt.setInt(2, account.getFeesAfterDiscount());
			pstmt.setInt(3, account.getFeesRemaining());
			pstmt.setInt(5, studentId);
			pstmt.executeUpdate();}
		}
		pstmt=con.prepareStatement("select max(enrollmentNo) from Coach_studentAccount");
		rs=pstmt.executeQuery();
		if(rs.next())
			enrollno=rs.getInt(1);
		return enrollno;
		}
		catch(SQLException e){
			con.rollback();
			throw e;
		}
		finally{con.setAutoCommit(true);}
	}
	@Override
	public int insertPayment(int enrollmentno, int studentId,InstallmentDetail detail)
			throws InvalidStudentIdException, CourseDetailsNotFoundException,
			StudentDetailsNotFoundException, ClassesServicesNotFoundException, SQLException {
		try{con.setAutoCommit(false);
		pstmt=con.prepareStatement("insert into COACH_INSTALLMENTDETAIL(RECEIPTNO,INSTALLMENTAMT,INSTALLMENTDATE,ENROLLMENTNO) values(RECEIPTNO_SEQ.nextval,?,?,?)");
		pstmt.setInt(1,detail.getInstallmentAmt());
		pstmt.setDate(2,(Date) detail.getInstallmentDate());
		pstmt.setInt(3,enrollmentno);
		pstmt.executeUpdate();
		pstmt=con.prepareStatement("select max(RECEIPTNO) from COACH_INSTALLMENTDETAIL");
		rs=pstmt.executeQuery();
		if(rs.next())return rs.getInt(1);
		}
		catch(SQLException e){
			con.rollback();
			throw e;
		}
		finally{con.setAutoCommit(true);}
		return 0;
	}
	@Override
	public int getRemainingFeesAmount(int studentId)
			throws InvalidStudentIdException, StudentDetailsNotFoundException,
			ClassesServicesNotFoundException, SQLException {

		try {
			pstmt=con.prepareStatement("select FEEREMAINING from COACH_STUDENTACCOUNT where studentId=?");
			pstmt.setInt(1, studentId);
			rs=pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1);
		} catch (SQLException e) {throw e;}
		return 0;
	}
	@Override
	public StudentAccount getStudentAccountDetails(int studentId,
			int enrollmentNo) throws SQLException {
		try {
			pstmt=con.prepareStatement("select * from COACH_STUDENTACCOUNT where studentId=? and enrollmentno=?");
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, enrollmentNo);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return new StudentAccount(enrollmentNo, this.getCourse(rs.getString(6)), rs.getString(2), rs.getInt(3), rs.getInt(4), this.getInstallmentDetails(enrollmentNo));
			}
		} catch (SQLException e) {
			throw e;
		}
		return null;
	}
	@Override
	public ArrayList<StudentAccount> getStudentAllAccountDetails(int studentId)
			throws ClassesServicesNotFoundException, SQLException {
		try {
			pstmt=con.prepareStatement("select * from Coach_StudentAccount where studentId=?");
			pstmt.setInt(1, studentId);
			rs=pstmt.executeQuery();
			ArrayList<StudentAccount>accounts=new ArrayList<>();
			while(rs.next()){accounts.add(new StudentAccount(rs.getInt(1), this.getCourse(rs.getString(6)), rs.getString(2), rs.getInt(3), rs.getInt(4), this.getInstallmentDetails(rs.getInt(1))));}
			return accounts;
		} catch (SQLException e) {
			throw e;
		}
	}
	@Override
	public Student getStudentDetails(int studentId)
			throws ClassesServicesNotFoundException, SQLException {
		try {
			pstmt=con.prepareStatement("select * from Coach_Student where studentId=?");
			pstmt.setInt(1, studentId);
			rs=pstmt.executeQuery();
			if(rs.next()) return new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), this.getAddress(studentId), this.getStudentAllAccountDetails(studentId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}

		return null;
	}
	@Override
	public ArrayList<Student> getAllStudentsDetails()
			throws ClassesServicesNotFoundException, SQLException {
		try {
			pstmt=con.prepareStatement("select * from Coach_Student");
			rs=pstmt.executeQuery();
			ArrayList<Student>students=new ArrayList<>();
			while(rs.next()) students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), this.getAddress(rs.getInt(1)), this.getStudentAllAccountDetails(rs.getInt(1))));
			return students;
		} catch (SQLException e) {
			throw e;
		}
	}
	@Override
	public ArrayList<Student> getCourseWiseAllStudentDetails(String courseCode)
			throws ClassesServicesNotFoundException {
		
		return null;
	}
	public Course getCourse(String coursecode) throws SQLException{
		try {
			pstmt=con.prepareStatement("select * from Coach_Course where coursecode=?");
			pstmt.setString(1, coursecode);
			rs=pstmt.executeQuery();
			if(rs.next()) return new Course(coursecode, rs.getString(2), rs.getInt(3), rs.getInt(4));
		} catch (SQLException e) {
			throw e;
		}
		return null;	
	}
	ArrayList<InstallmentDetail> getInstallmentDetails(int enrollmentno){
		try {
			pstmt=con.prepareStatement("select * from COACH_INSTALLMENTDETAIL where enrollmentno=?");
			pstmt.setInt(1, enrollmentno);
			rs=pstmt.executeQuery();
			ArrayList<InstallmentDetail>details=new ArrayList<>();
			while(rs.next()){details.add(new InstallmentDetail(rs.getInt(1), rs.getInt(2), rs.getDate(3)));}
			return details;
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	Address getAddress(int studentId) throws SQLException{
		try {
			pstmt=con.prepareStatement("select * from Coach_Address where studentId=?");
			pstmt.setInt(1, studentId);
			rs=pstmt.executeQuery();
			return new Address(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		} catch (SQLException e) {throw e;}
	}
	public boolean updateStudentAccount(StudentAccount account) throws SQLException{
		try {
			pstmt=con.prepareStatement("update Coach_StudentAccount where enrollmentno=? set FEEAFTERDISCOUNT=?,FEEREMAINING=? ");
			pstmt.setInt(1,account.getEnrollmentNo());
			pstmt.setInt(2, account.getFeesAfterDiscount());
			pstmt.setInt(3, account.getFeesRemaining());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {throw e;}
	}
}
