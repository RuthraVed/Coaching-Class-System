package com.zensar.classessystem.beans;
import java.util.Date;
public class InstallmentDetail {
	private int receiptNo, installmentAmt;
	private Date installmentDate;
	public InstallmentDetail(int receiptNo, int installmentAmt,
			Date installmentDate) {
		super();
		this.receiptNo = receiptNo;
		this.installmentAmt = installmentAmt;
		this.installmentDate = installmentDate;
	}
	
	public InstallmentDetail(int installmentAmt, Date installmentDate) {
		super();
		this.installmentAmt = installmentAmt;
		this.installmentDate = installmentDate;
	}

	public int getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}
	public int getInstallmentAmt() {
		return installmentAmt;
	}
	public void setInstallmentAmt(int installmentAmt) {
		this.installmentAmt = installmentAmt;
	}
	public Date getInstallmentDate() {
		return installmentDate;
	}
	public void setInstallmentDate(Date installmentDate) {
		this.installmentDate = installmentDate;
	}
	@Override
	public String toString() {
		return "InstallmentDetail [receiptNo=" + receiptNo
				+ ", installmentAmt=" + installmentAmt + ", installmentDate="
				+ installmentDate + "]";
	}
	
}
