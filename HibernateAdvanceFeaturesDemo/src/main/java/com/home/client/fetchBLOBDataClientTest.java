package com.home.client;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class fetchBLOBDataClientTest {

	public static void main(String[] args) {
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			Employee employee = session.get(Employee.class, 1);
			System.out.println(employee);
			Blob image=employee.getProfilePic();
			InputStream inputStream=image.getBinaryStream();
			Files.copy(inputStream, Paths.get("outputProfilePics/"+employee.getEmployeeName()+".JPG"),StandardCopyOption.REPLACE_EXISTING);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
