package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter the number you would like to double: ");
        int input = scn.nextInt();
        System.out.println(2*input);
    }
}
