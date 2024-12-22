package manyToMany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateInheritanceExample {

	 public static void main(String[] args) {
	        // Load the configuration and build the SessionFactory
	        Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	        		.applySettings(configuration.getProperties())
	        		.build();
	        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	        // Create a session
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;
  
	        try {
	            transaction = session.beginTransaction();
	            
	            // Create Students
	            Student student1 = new Student();
	            student1.setName("John Doe");

	            Student student2 = new Student();
	            student2.setName("Jane Smith");

	            // Create Courses
	            Course course1 = new Course();
	            course1.setName("Mathematics");

	            Course course2 = new Course();
	            course2.setName("Science");

	            // Assign Courses to Students
	            Set<Course> courses1 = new HashSet();
	            courses1.add(course1);
	            courses1.add(course2);
	            student1.setCourses(courses1);

	            Set<Course> courses2 = new HashSet();
	            courses2.add(course1);
	            student2.setCourses(courses2);

	            // Assign Students to Courses
	            Set<Student> students1 = new HashSet();
	            students1.add(student1);
	            students1.add(student2);
	            course1.setStudents(students1);

	            Set<Student> students2 = new HashSet();
	            students2.add(student1);
	            course2.setStudents(students2);

	            // Save the data
	            session.save(student1);
	            session.save(student2);
	           
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            sessionFactory.close();
	        }
	    }	            
}