package com.home.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class UpdateNaturalIdDataClientTest {

	public static void main(String[] args) {
		SessionFactory sf=HibernateUtil.getSessionFactory();
		int empId=1;
		try(Session session=sf.openSession()){
			Employee employee = session.get(Employee.class,empId);
			if(employee != null) {
               session.beginTransaction();
               employee.setNaturalId("901-1234569870");
               session.getTransaction().commit();
               System.out.println(employee);
			}
			else
				System.out.println("Employee doesn't exist!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
