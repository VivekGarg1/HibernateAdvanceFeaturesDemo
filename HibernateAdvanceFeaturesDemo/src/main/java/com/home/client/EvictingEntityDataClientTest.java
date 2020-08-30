package com.home.client;

import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class EvictingEntityDataClientTest {

	public static void main(String[] args) {
		SessionFactory sf=HibernateUtil.getSessionFactory();
		evictingEmployeeById(sf);
	}

	private static void evictingEmployeeById(SessionFactory sf) {
		int empId=1;
		try(Session session=sf.openSession()){
			Employee employee = session.get(Employee.class,empId);
			System.out.println(employee);
			//session.evict(employee);
			session.clear();
			System.out.println("=================");
			employee = session.get(Employee.class,empId);
			System.out.println(employee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
