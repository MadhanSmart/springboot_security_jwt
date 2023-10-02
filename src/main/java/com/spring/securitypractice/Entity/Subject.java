package com.spring.securitypractice.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subject_id")
	private int subId;
	private String subjectName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_department_id", referencedColumnName = "department_id")
	private Department department;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_teacher_id", referencedColumnName = "teacher_id")
	private Teacher teacher;

	public Subject() {

	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Subject [subId=" + subId + ", subjectName=" + subjectName + ", department=" + department + ", teacher="
				+ teacher + "]";
	}

	

}
