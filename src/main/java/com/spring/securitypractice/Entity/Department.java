package com.spring.securitypractice.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.lang.NonNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "departmentCode" }))
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_id")
	private int departId;
	private String departname;
	@NonNull
	private String departmentCode;
	private String hodName;
	private String departmentPhoneNumber;
	private String address;

	public Department() {

	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getHodName() {
		return hodName;
	}

	public void setHodName(String hodName) {
		this.hodName = hodName;
	}

	public String getDepartmentPhoneNumber() {
		return departmentPhoneNumber;
	}

	public void setDepartmentPhoneNumber(String departmentPhoneNumber) {
		this.departmentPhoneNumber = departmentPhoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Department [departId=" + departId + ", departname=" + departname + ", departmentCode=" + departmentCode
				+ ", hodName=" + hodName + ", departmentPhoneNumber=" + departmentPhoneNumber + ", address=" + address
				+ "]";
	}

}
