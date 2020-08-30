package com.home.client;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class Get_Load_NaturalId_AndByIdDataClientTest {

	public static void main(String[] args) {
		SessionFactory sf=HibernateUtil.getSessionFactory();
			//getEmployeeById(sf);
		    //loadEmployeeById(sf);
		   //byIdGetEmployee(sf);
		  //getEmployeeBySimpleNaturalId(sf);
		  //getEmployeeByNaturalId(sf);
		getEmployeeByOptionalJava8NaturalId(sf);
	}
	
	private static void getEmployeeByOptionalJava8NaturalId(SessionFactory sf) {
		String naturalId="123-9876543210";
		try(Session session=sf.openSession()){
			Optional<Employee> optional = session.byNaturalId(Employee.class).using("naturalId", naturalId).loadOptional();
			if(optional.isPresent()) {
				Employee employee = optional.get();
				System.out.println(employee);
			}
			else
				System.out.println("Employee doesn't exist!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
	}
	}

	private static void getEmployeeByNaturalId(SessionFactory sf) {
		String naturalId="123-9876543210";
		try(Session session=sf.openSession()){
			Employee employee = session.byNaturalId(Employee.class).using("naturalId", naturalId).load();
			if(employee != null)
				System.out.println(employee);
			else
				System.out.println("Employee doesn't exist!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
	}
	}

	private static void getEmployeeBySimpleNaturalId(SessionFactory sf) {
		String naturalId="123-9876543210";
		try(Session session=sf.openSession()){
			Employee employee = session.bySimpleNaturalId(Employee.class).getReference(naturalId);
			if(employee != null)
				System.out.println(employee);
			else
				System.out.println("Employee doesn't exist!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
	}
	}

	private static void byIdGetEmployee(SessionFactory sf) {
		int empId=1;
		try(Session session=sf.openSession()){
			//Employee employee = session.byId(Employee.class).load(empId);
			//We can use Java 8 optional as well
			Optional<Employee> option = session.byId(Employee.class).loadOptional(empId);
			/*if(employee != null)
				System.out.println(employee);*/
			if(option.isPresent()) {
				Employee employee = option.get();
				System.out.println(employee);
			} else
				System.out.println("Employee doesn't exist!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadEmployeeById(SessionFactory sf) {
		//If not found it will return exception
		int empId=10;
		try(Session session=sf.openSession()){
			Employee employee = session.load(Employee.class,empId);
			if(employee != null)
				System.out.println(employee);
			else
				System.out.println("Employee doesn't exist!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getEmployeeById(SessionFactory sf) {
		int empId=1;
		try(Session session=sf.openSession()){
			Employee employee = session.get(Employee.class,empId);
			if(employee != null)
				System.out.println(employee);
			else
				System.out.println("Employee doesn't exist!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		}
}
