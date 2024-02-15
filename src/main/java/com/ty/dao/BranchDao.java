package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Branch;
import com.ty.dto.Hospital;

public class BranchDao {
	static Scanner sc = new Scanner(System.in);
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction entityTransaction = null;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	static Branch branch = null;
	static List<Branch> branches;

	public static void saveBranchDetails() {
		System.out.println("Enter how many branch you want to add: ");
		int count = sc.nextInt();
		for (int i = 0; i < count; i++) {
			if (branch != null && branches != null) {
				branches=new ArrayList<Branch>();
				System.out.println("Enter the branch id: ");
				int id = sc.nextInt();
				System.out.println("Enter the branch name: ");
				String name = sc.next();
				System.out.println("Enter the branch phone number: ");
				long phnumber = sc.nextLong();
				System.out.println("Enter the email of branch: ");
				String email = sc.next();
				branches.add(branch);
				branch.setBranchId(id);
				branch.setBranchName(name);
				branch.setPhoneNumber(phnumber);
				branch.setEmail(email);
				entityTransaction.begin();
				entityManager.persist(branch);
				entityTransaction.commit();

				

			} else {
				branches = new ArrayList<Branch>();
				branch = new Branch();
				System.out.println("Enter the branch id: ");
				int id = sc.nextInt();
				System.out.println("Enter the branch name: ");
				String name = sc.next();
				System.out.println("Enter the branch phone number: ");
				long phnumber = sc.nextLong();
				System.out.println("Enter the email of branch: ");
				String email = sc.next();

				branch.setBranchId(id);
				branch.setBranchName(name);
				branch.setPhoneNumber(phnumber);
				branch.setEmail(email);
				entityTransaction.begin();
				entityManager.persist(branch);
				entityTransaction.commit();
				branches.add(branch);
			}
			System.out.println("Enter the hospital id that you want to add these branches: ");

			int hid = sc.nextInt();
			Hospital hospital = entityManager.find(Hospital.class, hid);
			if (hospital != null) {
				List<Branch> branches = hospital.getBranch();
				branches.add(branch);
				hospital.setBranch(branches);
				entityTransaction.begin();
				entityManager.merge(hospital);
				entityTransaction.commit();
				System.out.println("data added successfully!!!");
			} else {
				System.out.println("Entered id is wrong!!!!");
			}

		}
		
	}

}
