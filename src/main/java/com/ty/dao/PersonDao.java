package com.ty.dao;


import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Encounter;
import com.ty.dto.Person;

public class PersonDao {
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction entityTransaction = null;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	static Scanner sc = new Scanner(System.in);
	
	public static void addPersons() {
		Person person=new Person();
		System.out.println("Enter the person id: ");
		int id=sc.nextInt();
		System.out.println("Enter the person name: ");
		sc.nextLine();
		String name=sc.nextLine();
		System.out.println("Enter the person age: ");
		int age = sc.nextInt();
		System.out.println("Enter the phone number: ");
		long phonenumber=sc.nextLong();
		System.out.println("Enter the person gender: ");
		String gender=sc.next();
		
		person.setId(id);
		person.setName(name);
		person.setAge(age);
		person.setPhoneNumber(phonenumber);
		person.setGender(gender);
		
		System.out.println("Enter in how many encounters do want to add this:  ");
		int count=sc.nextInt();
		for(int i=0;i<count;i++) {
			System.out.println("Enter the encounter id: ");
			int enid=sc.nextInt();
			Encounter encounter=entityManager.find(Encounter.class,enid );
			encounter.setPerson(person);
			entityTransaction.begin();
			entityManager.persist(person);
			entityManager.persist(encounter);
			entityTransaction.commit();
			System.out.println("Added successfully!!!!");
		}
	}
	
	public static void findPerson() {
		System.out.println("Enter the person id that you want to find: ");
		int pid=sc.nextInt();
		Person person=entityManager.find(Person.class, pid);
		System.out.println("person name: "+person.getName());
		System.out.println("person age: "+person.getAge());
		System.out.println("person phone: "+person.getPhoneNumber());
		System.out.println("person gender: "+person.getGender());	
	}
	
	public static void updateAge() {
		System.out.println("Enter the person id : ");
		int pid=sc.nextInt();
		System.out.println("Enter the updated Age: ");
		int age=sc.nextInt();
		
		Person person=entityManager.find(Person.class, pid);
		person.setAge(age);
		entityTransaction.begin();
		entityManager.merge(person);
		entityTransaction.commit();
	}
	public static void updatePhone() {
		System.out.println("Enter the person id : ");
		int pid=sc.nextInt();
		System.out.println("Enter the updated Phone Number: ");
		long phno=sc.nextLong();
		
		Person person=entityManager.find(Person.class, pid);
		person.setPhoneNumber(phno);
		entityTransaction.begin();
		entityManager.merge(person);
		entityTransaction.commit();
		
	}
	
	public static void removeperson() {
		System.out.println("Enter the person id : ");
		int pid=sc.nextInt();
		Person person=entityManager.find(Person.class, pid);
		entityTransaction.begin();
		entityManager.remove(person);
		entityTransaction.commit();
	}
	public static void update() {
		System.out.println("\n1.Age\n2.phone number");
		System.out.println("Enter the choice: ");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:{
			updateAge();
			break;
		}
		case 2:{
			updatePhone();
			break;
		}
		default:{
			System.out.println("invalid input!!!");
			break;
		}
		}
	}
	
	public static void persons() {
		System.out.println("\n1.ADD PERSONS\n2.FIND PERSONS\n3.UPDATE PERSONS\n4.REMOVE PERSON\n");
		System.out.println("ENter the choice: ");
		int choice =sc.nextInt();
		switch(choice) {
		case 1:{
			addPersons();
			break;
		}
		case 2:{
			findPerson();
			break;
		}
		case 3:{
			update();
			break;
		}
		case 4:{
			removeperson();
			break;
		}
		default:{
			System.out.println("invalid input!!!");
			break;
		}
		}
	}
}
