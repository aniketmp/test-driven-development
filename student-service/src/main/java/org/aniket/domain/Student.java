package org.aniket.domain;

public class Student {
	private String name;
	private int schoolId;

	public Student(String name,int schoolId) {
		super();
		this.name = name;
		this.setSchoolId(schoolId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	
	
	
}
