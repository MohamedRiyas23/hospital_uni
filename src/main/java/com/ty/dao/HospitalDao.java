package com.ty.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.ty.controller.*;

import com.ty.dto.Branch;
import com.ty.dto.Hospital;

public class HospitalDao {
	static Scanner sc = new Scanner(System.in);
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction entityTransaction = null;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	static Hospital hospital = null;

	public void saveHospitalDetails() {
		System.out.println("Enter the how many hospitals do you want to add: ");
		int count = sc.nextInt();
		int i=0;
		for ( i = 0; i < count; i++) {

			
				hospital = new Hospital();
				
				System.out.println("Enter the Hospital id:");
				int id = sc.nextInt();
				System.out.println("Enter the hospital name: ");
				sc.nextLine();
				String name = sc.nextLine();
				System.out.println("Enter the hospital website: ");
				String website = sc.next();
				System.out.println("Enter the hospital location: ");
				String location = sc.next();
				hospital.setId(id);
				hospital.setName(name);
				hospital.setHospWebsite(website);
				hospital.setLocation(location);
				entityTransaction.begin();
				entityManager.persist(hospital);
				entityTransaction.commit();
				System.out.println("Data added successfully!!");
				if(i>=count)
					break;
		}
		
	}
	
	public static void getHospitalDetails(){
	Query q=entityManager.createQuery("select s from Hospital s");
	if(q!=null) {
	List<Hospital> hospitals=q.getResultList();
	for(Hospital h:hospitals) {
		int i=1;
		System.out.println(h.getId()+"."+h.getName());
	}
	System.out.println("Enter the hospital id that you to know the details: ");
	int id=sc.nextInt();
	Hospital hosp=entityManager.find(Hospital.class, id);
	System.out.println("Hospital name: "+hosp.getName());
	System.out.println("Hospital website: "+hosp.getHospWebsite());
	System.out.println("Hospital Location: "+hosp.getLocation());
	List<Branch> branches=hosp.getBranch();
	System.out.println("-------------BRANCH DETAILS---------------");
	for(Branch b:branches) {
		System.out.println("Branch id: "+b.getBranchId());
		System.out.println("Branch Name: "+b.getBranchName());
		System.out.println("Branch email: "+b.getEmail());
		System.out.println("Branch phone number: "+b.getPhoneNumber());
	}
	System.out.println("Enter the branch id that you want to get address of it: ");
	int bid=sc.nextInt();
	Branch b=entityManager.find(Branch.class, bid);
	if(b!=null) {
		System.out.println("------------ADDRESS OF THE BRANCH---------------");
		System.out.println(b.getAddress());
	}
	else {
		System.out.println("Entered id is wrong!!!");
	}
	
	}
	else {
		System.out.println("there is no data!!!");
	}
	
//	public static void hospitals() {
//		System.out.println("\n1.save hospital\n2.find hospital\n3.exit");
//		System.out.println("Enter the choice: ");
//		int choice=sc.nextInt();
//		boolean loop=true;
//		while(loop) {
//			switch(choice) {
//			case 1:{
//				saveHospitalDetails();
//				break;
//			}
//			case 2:{
//				getHospitalDetails();
//				break;
//			}
//			case 3:{
//				loop=false;
//				break;
//			}
//			default:{
//				System.out.println("Invalid input!!!!!");
//				break;
//			}
//			
//			}
//		}
//		return;
//	}
}
}
