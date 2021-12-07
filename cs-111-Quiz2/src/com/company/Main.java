package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static class cryBaby {
        String name,
        nickName;
        int age;
        ArrayList<String> reasons;

        public cryBaby(String name, int age, ArrayList<String> reasons){
            this.name = name;
            this.age = age;
            this.reasons = reasons;

        }

        public void printReasons(){
            this.reasons.forEach(reason -> System.out.println(reason));
            System.out.println("\n");
        }

        public void printName(){
            System.out.println("I am " + this.name);
        }

        public void setNickName(String nickName){
            this.nickName = nickName;
        }

        public String getNickName(){
            return this.nickName;
        }

        public String getName(){
            return this.name;
        }

        public void printAge(){
            System.out.println("I am" + this.age);
        }

        public void cryAboutIt(){
            System.out.println("***Squelching noises and sniffling begins***");
            this.reasons.forEach(reason -> System.out.println("Waaaaaaaaaaah " + reason));
            System.out.println("\n");
        }

    }
    public static void main(String[] args) {
        ArrayList<String> reasons1 = new ArrayList<String>(),
                reasons2 = new ArrayList<String>();
        reasons1.add("I am hungry");
        reasons1.add("I am tired");
        reasons1.add("I have too many assingments for finals week");

        reasons2.add("I am a dog");
        reasons2.add("I need attention");
        reasons2.add("I want a treat");
        cryBaby cb1 = new cryBaby("Omar", 10, reasons1);
        cryBaby cb2 = new cryBaby("Theia", 5, reasons2 );

        System.out.println("I am " + cb1.getName());
        cb1.setNickName("The Code Sourcer");
        System.out.println(cb1.getNickName());
        cb1.printAge();
        cb1.printReasons();
        cb1.cryAboutIt();


        cb2.printName();
        cb2.printAge();
        cb2.printReasons();
        cb2.cryAboutIt();
    }
}
