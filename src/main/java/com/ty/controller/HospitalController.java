package com.ty.controller;

import java.util.Scanner;
import com.ty.dao.*;

public class HospitalController {

	public static void main(String[] args) {
		
		HospitalDao hospitaldao=new HospitalDao();
		EncounterDao encounterdao=new EncounterDao();
		ItemDao itemdao=new ItemDao();
		MedOrderDao medorderdao=new MedOrderDao();
		PersonDao persondao=new PersonDao();
		BranchDao branchdao=new BranchDao();
		AddressDao addressdao=new AddressDao();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("\n1.Save Hospital Details\n2.save branch details\n3.save address\n4.find hospital details\n5.Encounters\n6.medorders\n7.items\n8.persons\n9.exit");
		System.out.println("\nEnter the choice:");
		int choice=sc.nextInt();
		
		boolean loop =true;
		
		while(loop) {
			switch(choice) {
			case 1:{
				hospitaldao.saveHospitalDetails();
				break;
			}
			case 2:{
				branchdao.saveBranchDetails();
				
				break;
			}
			case 3:{
				addressdao.addAddress();
				
				break;
			}
			case 4:{
				hospitaldao.getHospitalDetails();
				break;
			}
			case 5:{
				encounterdao.encounters();
				break;
			}
			case 6:{
				medorderdao.medOrders();
				break;
			}
			case 7:{
				itemdao.items();
				break;
			}
			case 8:{
				persondao.persons();
				break;
			}
			case 9:{
				loop=false;
				break;
			}
			default:{
				System.out.println("Invalid choice!!!!");
				break;
			}
			}
		}

	}

}
