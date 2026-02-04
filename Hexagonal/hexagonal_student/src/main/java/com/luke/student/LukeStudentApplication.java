package com.luke.student;

import com.luke.course.LukeCourseApplication;

public class LukeStudentApplication {

	public static void main(String[] args) {
//		SpringApplication.run(LukeStudentApplication.class, args);

		LukeCourseApplication course = new LukeCourseApplication();
		String s = course.miLukeCourseApplication();

		System.out.println("Traza --->" + s);
	}

}
