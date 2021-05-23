package com.tranjt.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tranjt.hibernate.demo.entity.Instructor;
import com.tranjt.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		try {
			/*
			// create the objects
			Instructor tempInstructor = 
					new Instructor("Joe", "Doe", "doe@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.youtube.com/johndoe",
							"code 101");
			*/
			
			// create the objects
			Instructor tempInstructor = 
					new Instructor("Jane", "Dill", "Jane@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.youtube.com/JaneDill",
							"code 101");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
					
			// start a transaction
			session.beginTransaction();
		
			// save the instructor
			//
			// Note: this will ALSO save the details object 
			// because of CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
