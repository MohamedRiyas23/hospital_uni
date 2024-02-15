package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.Branch;
import com.ty.dto.Encounter;
import com.ty.dto.Encounter;
public class EncounterDao {
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction entityTransaction = null;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	static Scanner sc = new Scanner(System.in);
	static Encounter encounter=null;
	static List<Encounter> encounters;
	public static void addEncounters() {
		

		System.out.println("Enter how many encounters do you want to add: ");
		int count = sc.nextInt();
		for(int i=0;i<count;i++) {
			if(encounter!=null&&encounters!=null) {
				List<Encounter> encounters = new ArrayList<Encounter>();
				System.out.println("Enter Encounter id: ");
				int id = sc.nextInt();
				System.out.println("Enter the Bed Number: ");
				String bed = sc.next();
				System.out.println("Enter the ward type: ");
				String ward = sc.next();
				System.out.println("Enter the date: ");
				String date = sc.next();
				
				encounter.setId(id);
				encounter.setBedNumber(bed);			
				encounter.setWard(ward);
				encounter.setDate(date);
				encounters.add(encounter);
				
				try {
				entityTransaction.begin();
				entityManager.persist(encounter);
				
				entityTransaction.commit();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		
				
			System.out.println("Enter the branch id that you want to add these encounters: ");
			int branid = sc.nextInt();
			Branch b = entityManager.find(Branch.class, branid);
			b.setEncounters(encounters);
			entityTransaction.begin();
			entityManager.merge(encounters);
			entityTransaction.commit();
			System.out.println("Added successfully!!!!");
			}
			else {
				encounter=new Encounter();
				List<Encounter> encounters = new ArrayList<Encounter>();
				System.out.println("Enter Encounter id: ");
				int id = sc.nextInt();
				System.out.println("Enter the Bed Number: ");
				String bed = sc.next();
				System.out.println("Enter the ward type: ");
				String ward = sc.next();
				System.out.println("Enter the date: ");
				String date = sc.next();
				
				encounter.setId(id);
				encounter.setBedNumber(bed);			
				encounter.setWard(ward);
				encounter.setDate(date);
				encounters.add(encounter);
				
				try {
				entityTransaction.begin();
				entityManager.persist(encounter);
				
				entityTransaction.commit();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		
				
			System.out.println("Enter the branch id that you want to add these encounters: ");
			int branid = sc.nextInt();
			Branch b = entityManager.find(Branch.class, branid);
			b.setEncounters(encounters);
			entityTransaction.begin();
			entityManager.persist(encounter);
			entityTransaction.commit();
			System.out.println("Added successfully!!!!");
			}
		}
	
		
	}
	

	public static void findEncounters() {

		System.out.println("Enter the encounter id that you want to find: ");
		int eid = sc.nextInt();
		Encounter encounter = entityManager.find(Encounter.class, eid);
		System.out.println("Encounter Bed: " + encounter.getBedNumber());
		System.out.println("Encounter Ward: " + encounter.getWard());
		System.out.println("Encounter Date: " + encounter.getDate());
		System.out.println("person id: "+encounter.getPerson());
	}

	public static void updateBed() {
		System.out.println("\nEnter the Encounter id that you want to update: ");
		int eid = sc.nextInt();
		System.out.println("Enter the updated bed number: ");
		String bed = sc.next();
		Encounter e = entityManager.find(Encounter.class, eid);
		e.setBedNumber(bed);

		entityTransaction.begin();
		entityManager.merge(e);
		entityTransaction.commit();
		System.out.println("updated successfully!!!");
	}

	public static void updateWard() {
		
		System.out.println("\nEnter the Encounter id that you want to update: ");
		int eid = sc.nextInt();
		System.out.println("Enter the updated ward: ");
		String ward = sc.next();
		Encounter e = entityManager.find(Encounter.class, eid);
		e.setBedNumber(ward);

		entityTransaction.begin();
		entityManager.merge(e);
		entityTransaction.commit();
		
		System.out.println("updated successfully!!!");

	}

	public static void removeEncounter() {
		
		System.out.println("\nEnter the Encounter id that you want to delete: ");
		int eid = sc.nextInt();
		Encounter e = entityManager.find(Encounter.class, eid);
		
		entityTransaction.begin();
		entityManager.remove(e);
		entityTransaction.commit();
		
		System.out.println("removed successfully!!");
	}
	public static void update() {
		System.out.println("1.BED\n2.WARD\n");
		System.out.println("Enter the choice: ");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:{
			updateBed();
			break;
		}
		case 2:{
			updateWard();
			break;
		}
		default:{
			System.out.println("invalid input!!");
			break;
		}
		}
	}
	
	public static void encounters() {
		System.out.println("1.ADD ENCOUNTERS\n2.FIND ENCOUNTERS\n3.UPDATE ENCOUNTERS\n4.DELETE ENCOUNTERS");
		System.out.println("\nEnter the choice: ");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:{
			addEncounters();
			break;
		}
		case 2:{
			findEncounters();
			break;
		}
		case 3:{
			update();
			break;
		}
		case 4:{
			removeEncounter();
			break;
		}
		default:{
			System.out.println("Invalid input!!!!");
		}
		}
	}
	

}
