package com.ty.dao;

import com.ty.dto.Address;
import com.ty.dto.Branch;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dao.BranchDao;
import com.ty.dao.HospitalDao;

public class AddressDao {
	static Scanner sc=new Scanner(System.in);
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction entityTransaction = null;
	static {
		entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	static Address address=null;
	public static void addAddress() {
		if(address!=null) {
			System.out.println("Enter the Address id: ");
			String id=sc.next();
			System.out.println("Enter the street: ");
			sc.nextLine();
			String street = sc.nextLine();
			System.out.println("Enter the area: ");
			String area=sc.next();
			System.out.println("Enter the city: ");
			String city=sc.next();
			System.out.println("Enter the state: ");
			String state=sc.next();
			System.out.println("Enter the pincode: ");
			long pincode=sc.nextLong();
			
			address.setId(id);
			address.setStreet(street);
			address.setArea(area);
			address.setCity(city);
			address.setState(state);
			address.setPincode(pincode);
			
			System.out.println("Enter the branch id that you to add this address: ");
			int bid=sc.nextInt();
			Branch branch=entityManager.find(Branch.class, bid);
			branch.setAddress(address);
			
			entityTransaction.begin();
			entityManager.persist(address);
			entityManager.merge(branch);
			entityTransaction.commit();
			System.out.println("Address added successfully!!!");
		}
		else {
			address=new Address();
			System.out.println("Enter the Address id: ");
			String id=sc.next();
			System.out.println("Enter the street: ");
			sc.nextLine();
			String street = sc.nextLine();
			System.out.println("Enter the area: ");
			String area=sc.next();
			System.out.println("Enter the city: ");
			String city=sc.next();
			System.out.println("Enter the state: ");
			String state=sc.next();
			System.out.println("Enter the pincode: ");
			long pincode=sc.nextLong();
			
			address.setId(id);
			address.setStreet(street);
			address.setArea(area);
			address.setCity(city);
			address.setState(state);
			address.setPincode(pincode);
			
			System.out.println("Enter the branch id that you to add this address: ");
			int bid=sc.nextInt();
			Branch branch=entityManager.find(Branch.class, bid);
			branch.setAddress(address);
			
			entityTransaction.begin();
			entityManager.persist(address);
			entityManager.merge(branch);
			entityTransaction.commit();
			System.out.println("Address added successfully!!");
		}
	}
}
