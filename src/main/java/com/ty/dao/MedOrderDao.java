package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Encounter;
import com.ty.dto.MedOrder;

public class MedOrderDao {
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction entityTransaction = null;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	static Scanner sc = new Scanner(System.in);
	static MedOrder medorder = null;
	static List<MedOrder> medorders=null;
	public static void addMedOrders() {
		medorders = new ArrayList<MedOrder>();

		medorder = new MedOrder();

		
			System.out.println("Enter the med id: ");
			String medId = sc.next();
			System.out.println("Enter the Quantity : ");
			int quantity = sc.nextInt();
			System.out.println("Enter the invoice code: ");
			String invoice = sc.next();
			System.out.println("Enter the payment method: ");
			String payment = sc.next();

			medorder.setId(medId);
			medorder.setQuantity(quantity);
			medorder.setInvoiceCode(invoice);
			medorder.setPaymentMethod(payment);
			
			
			entityTransaction.begin();
			entityManager.persist(medorder);
			entityTransaction.commit();

			medorders.add(medorder);
			System.out.println("Medorder added successfully!!!");

		

		System.out.println("Enter the Encouter id you want to add this medorder: ");
		int eid = sc.nextInt();
		Encounter encounter = entityManager.find(Encounter.class, eid);
		encounter.setOrders(medorders);
		entityTransaction.begin();
		entityManager.persist(encounter);
		
		entityTransaction.commit();
		System.out.println("Added successfully!!!");

	}
	
	public void findMedorder() {
		System.out.println("Enter the order id that you want to fetch: ");
		String id=sc.next();
		MedOrder med=entityManager.find(MedOrder.class, id);
		System.out.println("Medorder Quantity: "+med.getQuantity());
		System.out.println("Medorder invoice: "+med.getInvoiceCode());
		System.out.println("Medorder payment method: "+med.getPaymentMethod());
	}
	
	public void remove() {
		System.out.println("Enter the order id that you want to remove: ");
		String id=sc.next();
		MedOrder med=entityManager.find(MedOrder.class, id);
		entityTransaction.begin();
		entityManager.remove(med);
		entityTransaction.commit();
	}
	public void medOrders() {
		System.out.println("\n1.ADD MEDORDER\n2.FIND MEDORDER\n3.DELETE MEDORDER");
		System.out.println("Enter the choice: ");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:{
			addMedOrders();
			break;
		}
		case 2:{
			findMedorder();
			break;
		}
		case 3:{
			remove();
			break;
		}
		default:{
			System.out.println("invalid input!!!!");
			break;
		}
		}
	}

}
