package com.home.client;

import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class RefreshEntityDataClientTest {

	public static void main(String[] args) {
		SessionFactory sf=HibernateUtil.getSessionFactory();
		refereshEmployeeById(sf);
	}

	private static void refereshEmployeeById(SessionFactory sf) {
		int empId=1;
		try(Session session=sf.openSession()){
			Employee employee = session.byId(Employee.class).load(empId);
			session.doWork(connection -> {
				try(Statement statement=connection.createStatement()){
					statement.executeUpdate("update employee_table set employee_Name=LOWER(employee_Name)");
				}
			});
			session.refresh(employee);
			System.out.println(employee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
