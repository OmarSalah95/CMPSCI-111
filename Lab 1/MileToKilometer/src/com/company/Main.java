package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter the number of miles you would like to convert to Kilometers: ");
        float miles = scn.nextFloat();
        System.out.println( miles +" miles is: " + miles*1.60934 + " Kilometers");
    }
}
