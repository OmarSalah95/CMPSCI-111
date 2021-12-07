package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void printMenu() {
        System.out.println("Welcome to the Horse Races!\n\n" +
                "Please select from the following options 1-6\n" +
                "1) Place an exact bet           (Cost: $10  Return: $100)\n" +
                "2) Place an exactabox bet       (Cost: $5  Return: $50)\n" +
                "3) Place a trifectabet          (Cost: $25  Return: $200)\n" +
                "4) Please a trifectabox bet     (Cost: $20  Return: $150)\n" +
                "5) See your USD balance\n" +
                "6) Exit\n");
    }

    public static class Race {
        int[] array = {1, 2, 3, 4};

        void readySetGo() {
            int n = array.length;
            Random random = new Random();
            for (int i = 0; i < array.length; i++) {
                // Get a random index of the array past the current index.
                // ... The argument is an exclusive bound.
                // It will not go past the array's end.
                int randomValue = i + random.nextInt(n - i);
                // Swap the random element with the present element.
                int randomElement = array[randomValue];
                array[randomValue] = array[i];
                array[i] = randomElement;
            }
        } // end shuffle

        int first() {
            return array[0];
        }

        int second() {
            return array[1];
        }

        int third() {
            return array[2];
        }

        int fourth() {
            return array[3];
        }

        int[] getarray() {
            return array;
        }
        void printResults(){
            System.out.println("Race Results: "+
                                this.first() +" "+
                                this.second() +" "+
                                this.third() +" "+
                                this.fourth());
        }

    }

    static boolean confirmValid(int n){
        return (n >= 1 && n<=4);
    }

    public static void main(String[] args) {
        Race r = new Race();
        boolean running = true;
        Scanner scn = new Scanner(System.in);
        int wallet = 200,
                wins = 0,
                losses = 0,
                userInput1,
                userInput2,
                userInput3;

        int commandCode;

        while(running){
            r.readySetGo();
            printMenu();
            commandCode = scn.nextInt();
            r.printResults();


            switch (commandCode){
                case 1:
                    if(wallet<10){
                        System.out.println("You don't have the funds to place this bet!");
                        break;
                    }
                    System.out.println("Your selections must match the First and Second Place racers exactly to win.");
                    wallet -= 10;

                    do{
                        System.out.println("Enter your selection for first place: ");
                        userInput1 = scn.nextInt();
                        System.out.println("Enter your selection for second place: ");
                        userInput2 = scn.nextInt();

                        if (confirmValid(userInput1) && confirmValid(userInput2) && userInput2 != userInput1){
                            r.printResults();
                            if(userInput1 == r.first() && userInput2 == r.second()){
                                wallet+=100;
                                System.out.println("Congratulations you won! You now have: $" + wallet+ "\n\n");
                                wins +=1;
                            } else{
                                System.out.println("That's rough you lost.\n\n");
                                losses+=1;
                            }
                            continue;
                        }else {
                            System.out.println("Oops, invalid input, lets try that again.");
                        }
                    }
                    while(!confirmValid(userInput1) || !confirmValid(userInput2));
                    break;
                case 2:
                    if(wallet<5){
                        System.out.println("You don't have the funds to place this bet!");
                        break;
                    }
                    System.out.println("Your selections must match the first and second place racers in any order to win.");
                    wallet -= 5;

                    do{
                        System.out.println("Enter your selection for one of the top 2: ");
                        userInput1 = scn.nextInt();
                        System.out.println("Enter your selection the other in the top 2: ");
                        userInput2 = scn.nextInt();

                        if (confirmValid(userInput1) && confirmValid(userInput2) ){
                            r.printResults();
                            if(userInput1 == r.first() || userInput1 == r.second() && userInput2 == r.first() ||  userInput2 == r.second()){
                                wallet+=50;
                                System.out.println("Congratulations you won! You now have: $" + wallet + "\n\n");
                                wins +=1;
                            } else{
                                System.out.println("That's rough you lost.\n\n");
                                losses+=1;
                            }
                            continue;
                        } else {
                            System.out.println("Oops, invalid input, lets try that again.");
                        }
                    }
                    while(!confirmValid(userInput1) || !confirmValid(userInput2));
                    break;


                case 3:
                    if(wallet<25){
                        System.out.println("You don't have the funds to place this bet!");
                        break;
                    }
                    System.out.println("Your selections must match the first, second, and third place winners accurately to win.");
                    wallet -= 25;

                    do{
                        System.out.println("Enter your selection for the first place winner:  ");
                        userInput1 = scn.nextInt();
                        System.out.println("Enter your selection for the second place winner: ");
                        userInput2 = scn.nextInt();
                        System.out.println("Enter your selection for the third place winner: ");
                        userInput3 = scn.nextInt();

                        if (confirmValid(userInput1) && confirmValid(userInput2) && confirmValid(userInput3)){
                            r.printResults();
                            if(userInput1 == r.first() && userInput2 == r.second() && userInput3 == r.third()){
                                wallet+=200;
                                System.out.println("Congratulations you won! You now have: $" + wallet + "\n\n");
                                wins +=1;
                            } else{
                                System.out.println("That's rough you lost.\n\n");
                                losses+=1;
                            }
                            continue;
                        }else {
                            System.out.println("Oops, invalid input, lets try that again.");
                        }
                    }
                    while(!confirmValid(userInput1) || !confirmValid(userInput2) || !confirmValid(userInput3));
                    break;


                case 4:
                    if(wallet<20){
                        System.out.println("You don't have the funds to place this bet!");
                        break;
                    }
                    System.out.println("Your selections must match the first, second, and third place winners in any order to win.");
                    wallet -= 20;

                    do{
                        System.out.println("Enter your selection for one of the top 3:  ");
                        userInput1 = scn.nextInt();
                        System.out.println("Enter your selection for the second of the top 3: ");
                        userInput2 = scn.nextInt();
                        System.out.println("Enter your selection for the third of the top 3: ");
                        userInput3 = scn.nextInt();

                        if (confirmValid(userInput1) && confirmValid(userInput2) && confirmValid(userInput3) ){
                            r.printResults();
                            if(userInput1 == r.first() || userInput1 == r.second() || userInput1 == r.third()
                                    &&  userInput2 == r.first() || userInput2 == r.second() || userInput2 == r.third()
                                    && userInput3 == r.third()  || userInput3 == r.second() || userInput3 == r.third()
                            ){
                                wallet+=150;
                                System.out.println("Congratulations you won! You now have: $" + wallet + "\n\n");
                                wins +=1;
                            } else{
                                System.out.println("That's rough you lost. You now have: $" + wallet +" remaining\n\n");
                                losses+=1;
                            }
                            continue;
                        }else {
                            System.out.println("Oops, invalid input, lets try that again.");
                        }
                    }
                    while(!confirmValid(userInput1) || !confirmValid(userInput2) || !confirmValid(userInput3));
                    break;
                case 5:
                    System.out.println(wallet);
                    break;
                default:
                    System.out.println(
                            "You won "+ wins +" bets\n" +
                            "and lost "+ losses +" bets\n" +
                            "You are leaving with $" + wallet +
                            "\nThanks for coming to the horse races.");
                    running = false;
                    break;
            }

        }

    }

}

