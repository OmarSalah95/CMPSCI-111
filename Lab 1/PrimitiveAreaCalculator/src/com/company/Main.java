package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Scanner scn = new Scanner(System.in);
        System.out.println("Which primitive would you like to calculate the area for? Please enter 1 for a Triangle or 2 for a Circle): ");
        int input;
        input = scn.nextInt();

           if (input == 2){
               System.out.println("Please enter the radius of the circle you would like to calculate: ");
               float rad = scn.nextFloat();
               System.out.println(3.1415926 * rad * rad);
           } else if(input == 1) {
               System.out.println("Please enter the base and height of the Triangle you would like to calculate: ");
               float base = scn.nextFloat();
               float height = scn.nextFloat();
               System.out.println((base*height)/2);
           } else{
               System.out.println("Invalid input, please run the application again");
           }

    }
}
