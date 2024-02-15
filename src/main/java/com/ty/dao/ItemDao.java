package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import com.ty.dto.Item;
import com.ty.dto.MedOrder;

public class ItemDao {
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction entityTransaction = null;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	static Scanner sc = new Scanner(System.in);
	static List<Item> items;
	static Item item=null;
	public static void addItems() {
		System.out.println("Enter how many item you want to add: ");
		int count=sc.nextInt();
		for(int i=0;i<count;i++) {
			if(item!=null && items!=null) {
				System.out.println("Enter the item id: ");
				int id=sc.nextInt();
				System.out.println("Enter the item name: ");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println("Enter the item type: ");
				sc.nextLine();
				String itemType=sc.nextLine();
				System.out.println("Enter the item price: ");
				double itemPrice=sc.nextDouble();
				System.out.println("Enter the item expiry date: ");
				String date=sc.next();
				
				item.setId(id);
				item.setName(name);
				item.setItemType(itemType);
				item.setPrice(itemPrice);
				item.getExpiryDate();
				items.add(item);	
			}
			else {
				item=new Item();
				items=new ArrayList<Item>();
				System.out.println("Enter the item id: ");
				int id=sc.nextInt();
				System.out.println("Enter the item name: ");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println("Enter the item type: ");
				sc.nextLine();
				String itemType=sc.nextLine();
				System.out.println("Enter the item price: ");
				double itemPrice=sc.nextDouble();
				System.out.println("Enter the item expiry date: ");
				String date=sc.next();
				
				item.setId(id);
				item.setName(name);
				item.setItemType(itemType);
				item.setPrice(itemPrice);
				item.getExpiryDate();
				items.add(item);
				
			}
		}
		System.out.println("Enter in how many medorders do want to add this:  ");
		int count1=sc.nextInt();
		for(int i=0;i<count1;i++) {
			System.out.println("Enter the medorder id: ");
			int enid=sc.nextInt();
			MedOrder medorder=entityManager.find(MedOrder.class,enid );
			medorder.setItems(items);
			entityTransaction.begin();
			entityManager.persist(item);
			entityManager.merge(medorder);
			entityTransaction.commit();
			System.out.println("Added successfully!!!!");
		}
	}
	
	public static void findItems() {
		System.out.println("Enter the item id that you want to find: ");
		int id=sc.nextInt();
		Item item=entityManager.find(Item.class, id);
		System.out.println("Item name: "+item.getName());
		System.out.println("Item Type: "+item.getItemType());
		System.out.println("Item Cost: "+item.getPrice());
		System.out.println("Item Expiry date: "+item.getExpiryDate());
	}
	
	public static void removeItem() {
		System.out.println("Enter the item id that you want to find: ");
		int id=sc.nextInt();
		Item item=entityManager.find(Item.class, id);
		entityTransaction.begin();
		entityManager.remove(item);
		entityTransaction.commit();
	}
	
	public static void items() {
		System.out.println("\n1.ADD ITEM\n2.FIND ITEM\n3.DELETE ITEM\n");
		System.out.println("Enter the choice: ");
		int choice = sc.nextInt();
		switch(choice) {
		case 1:{
			addItems();
			break;
		}
		case 2:{
			findItems();
			break;
		}
		case 3:{
			removeItem();
			break;
		}
		default:{
			System.out.println("Invalid input!!!");
			break;
		}
		}
	}
}
