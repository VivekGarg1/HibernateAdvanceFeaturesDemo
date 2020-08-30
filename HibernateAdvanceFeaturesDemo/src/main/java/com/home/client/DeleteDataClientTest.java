package com.home.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class DeleteDataClientTest {

	public static void main(String[] args) {
		SessionFactory sf=HibernateUtil.getSessionFactory();
		deleteEmployeeById(sf);
	}

	private static void deleteEmployeeById(SessionFactory sf) {
		int empId=2;
		try(Session session=sf.openSession()){
			Employee employee = session.get(Employee.class, empId);
			if(employee !=null) {
				session.beginTransaction();
				session.delete(employee);
				session.getTransaction().commit();
			}
			else {
				System.out.println("Employee doesn't exist!!!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
