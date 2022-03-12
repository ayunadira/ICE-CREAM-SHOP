package main;

import java.util.ArrayList;
import java.awt.Color;

public class Main {
	static ArrayList<Customerclass> customers = new ArrayList<Customerclass>();
	static ArrayList<Itemsclass> listitems = new ArrayList<Itemsclass>();
	static ArrayList<Ordersclass> listorders = new ArrayList<Ordersclass>();
	static ArrayList<Paymentclass> listpayment = new ArrayList<Paymentclass>();
	
	//CONFIGURATION
	static private double discount = 0.20;
	static private String appname = "ICE CREAM SHOP";
	static private String contributor = "Modified";
	
	static public String getappname() {
		return appname;
	}
	
	static public String getcontributor() {
		return contributor;
	}
	
	static public double getdiscountvalue() {
		return discount;
	}

	public static ArrayList<Customerclass> getcustomer() {
		return customers;
	}

	public static ArrayList<Itemsclass> getitems() {
		return listitems;
	}
	
	public static ArrayList<Ordersclass> getorders() {
		return listorders;
	}
	
	public static ArrayList<Paymentclass> getpayment(){
		return listpayment;
	}

	public static void main(String[] args) {
		Welcomeframe welcomeframe;
		Cashierframe cashier;

		try {
			welcomeframe = new Welcomeframe();
			welcomeframe.getContentPane().setBackground(new Color(255, 182, 193));
			welcomeframe.getContentPane().setForeground(new Color(245, 222, 179));
			cashier = new Cashierframe();

			welcomeframe.setVisible(true);
			Thread.sleep(2000);
			welcomeframe.progressBar.setVisible(true);
			try {
				for (int i = 0; i <= 100; i += 4) {
					Thread.sleep(20);
					welcomeframe.progressBar.setValue(i);
					welcomeframe.lblNewLabel_3.setText("Welcome! Starting up " + i + "%");
				}
				Thread.sleep(1000);
				welcomeframe.setVisible(false);
				cashier.setVisible(true);
			} catch (Exception e) {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}