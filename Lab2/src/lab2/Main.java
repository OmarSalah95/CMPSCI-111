package lab2;

import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.*;

public class Main {
    static Scanner scn = new Scanner(System.in);

//    Part 1 Ticket discount
//    Tell the user that tickets cost 14 dollars. Then ask the user his age. If the user's age is <18 tell them that
//    they can get a 10 dollar admission for being a minor, If the user’s age is >= 65, tell them that they can get a
//    0 dollar admission for being a senior citizen, lastly let them know if they have to pay full price.
    static void ticketDiscount(){
        System.out.println("Tickets are on sale for $14, however we are offering a discount for certain age groups." +
                "\nPlease enter your age: ");
        int age = scn.nextInt();
        System.out.println((age < 18)
                ? "You can get a 10 dollar admission for being a minor!\n\n"
                    : (age >= 65)
                ? "You can get a 0 dollar admission for being a senior citizen\n\n"
                    : "Unfortunately you are not applicable for any discounts on your ticket which will cost $14.\n\n");
    };

//    Part 2 Did I fail?
//    Ask the user to input his score (1-100). If he is 90-100 tell him no you got an A!, if he is 80-89 tell him no you
//    got a B!, if he is 70- 79 tell him it’s a C!, 60-69 it’s a D, 1-59 tell him Yeah you did fail, I’m sorry.
//    Challenge: show you can do this with a switch-like statement too
    static void didiFail(){
        System.out.println("Please enter your score(1-100): ");
        int score = scn.nextInt();
// As always with code, there are a ton of ways to skin this cat. The most dry and least redundant is this way, because
// switch cases really dont do conditionals under the cases, and so it is much simpler to go this route, but below are
// examples on how to actually implement this same solution in a few different methods. I am fairly to the last however
// in which all the conditions and outputs are in a single print statement for breviety, simplicity, and time space
// efficiency
        char grade = (score >= 90)
                    ? 'A'
                : (score >= 80 && score < 90)
                    ? 'B'
                : (score >= 70 && score < 80)
                    ? 'C'
                : (score >= 60 && score < 70)
                    ? 'D'
                : 'F';
        String msg = (grade != 'F') ? ( "No, you got a " + String.valueOf(grade) + "!") : "Yeah, you failed!";
        System.out.println(msg +"\n\n");

//        (grade != 'F')
//                ? System.out.println( "No, you got a " + String.valueOf(grade) + "!")
//                : System.out.println("Yeah, you failed!");

//        switch (grade){
//            case 'A':
//                System.out.println(no + grade);
//                break;
//            case 'B':
//                System.out.println(no + grade);
//                break;
//            case 'C':
//                System.out.println(no + grade);
//                break;
//            case 'D':
//                System.out.println(no + grade);
//                break;
//            default:
//                System.out.println("Yeah, you failed!");
//                break;
//    };

//    System.out.println((score >= 90)
//                ? "Grade: A"
//            : (score >= 80 && score < 90)
//                ? "Grade: B"
//            : (score >= 70 && score < 80)
//                ? "Grade: C"
//            : (score >= 60 && score < 70)
//                ? "Grade: D"
//            : "Yeah you failed");
}

//    Part 3 How low? (loops)
//    Write a program that prints out
//    how low low low low low
//    can you go go go go go
//    You are going to use a for loop on the first line to print the low word 5 times and a while loop on the second
//    line the go word 5 times.
    static void howLow(){
        System.out.println("Please enter the number of times you would like to repeat low and go.");
        int n = scn.nextInt();
        int i;
        String msg1 ="How";
        String msg2 ="Can you";

        for(i = 0 ; i < n; i++){
            msg1 += " low";
        };
        System.out.println(msg1);
        // This is why I usually define I in my loops as needed so I don't accidently create bugs by forgetting to reset my
        // iterators lol
        i=0;
        while(i<n){
            msg2 += " go";
            i++;
        }
        msg2 += "\n\n";
        System.out.println(msg2);
    };

//    Part 4 Guessing game (random and absolute)
//    Write a program that does this: You (as programmer) are the dealer. Pick a random number for yourself (between 0
//    and 100). Ask the user to input a random number (between 0 and 100). Whoever is closer to 21 wins the game. If the
//    dealer wins, say “Dealer wins.” If the user wins, say “Player wins”.
//    Example output:
//    Player please pick a number
//45
//    Dealer has 25 Dealer wins.
//    Part 5 Guessing game loop (keeping a counter)
//    Write the same program as in part 4, but keep it going so that it keeps playing (dealing hands and saying who wins)
//    until the User enters the number 21 at which point you print out some stats and say goodbye, for example, your
//    goodbye might look like this:

//    Number of hands played: 5 Dealer won: 3
//    Player won: 2
//    You’re 2 for 5
//    Come back to the CS House of Games soon.

    static void guessingGame() {
        System.out.println("Welcome to the guessing games. Please select an option.\n1 : Single Guess\n2 : Play to win");
        int option = scn.nextInt(),
                sysWins = 0,
                plyrWins = 0,
                draws = 0,
                sysGuess,
                plyrGuess;
        boolean runLoop = option == 1 ? false : true;
        Random random = new Random();

        do {
            System.out.println("Please enter the guess you would like to play against our system. (1-100)\nYou can exit at anytime by entering 0");
            sysGuess = random.nextInt(99) + 1;
            plyrGuess = scn.nextInt();

            if (plyrGuess==21 || plyrGuess==0) {
                if (plyrGuess ==21) plyrWins++;
                runLoop = false;
            } else if (abs(plyrGuess-21) < abs(sysGuess -21 )) {
                plyrWins++;
            } else if(abs(sysGuess -21 ) < abs(plyrGuess-21)) {
                sysWins++;
            } else {
                draws++;
            }
        } while (runLoop);

        System.out.println(sysWins > 1 || plyrWins > 1
                ? "Hands Played: " + (sysWins + plyrWins + draws)
                    + "\nSystem wins: " + sysWins
                    + "\nPlayer wins: " + plyrWins
                    + "\nDraws: " + draws
                    + "\nYou're " + plyrWins + " for " + (sysWins + plyrWins + draws)
                    + "\nCome back to the CS house of games soon.\n\n"
                : sysWins > plyrWins
                    ? "Dealer wins! System had " + sysGuess
                    : "Congratulations you won!");

    }

    public static void main(String[] args) {
	// write your code here
        boolean running = true;

        while(running){
            System.out.println("Please choose an option number for which sub-application yout would like to run." +
                    "\n1 : Ticket Discount" +
                    "\n2 : Did I Fail" +
                    "\n3 : How Low" +
                    "\n4 : Guessing games" +
                    "\n0 : Exit " +
                    "\n\nPlease enter your command code:");

            int code = scn.nextInt();
            switch(code){
                case 1:
                    ticketDiscount();
                    break;
                case 2:
                    didiFail();
                    break;
                case 3:
                    howLow();
                    break;
                case 4:
                    guessingGame();
                    break;
                default:
                    System.out.println("Thanks for giving us a shot!");
                    running = false;
            }
        }
    }
}
