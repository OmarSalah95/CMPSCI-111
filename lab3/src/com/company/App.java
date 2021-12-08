package com.company;

import java.io.*;
import java.net.*;
import java.util.*;



public class App {

    public static class Wallet {
        private String ticker;
        private double numberOfCoins = 0.0;

        public Wallet(String ticker, double coins) {
            this.ticker = ticker;
            this.numberOfCoins = coins;
        }

        public void buyCoin(double boughtCoins) {
            this.numberOfCoins += boughtCoins;
        }

        public void sellCoin(double soldCoin) {
            this.numberOfCoins -= soldCoin;
        }

        public void printWallet(double coinValue) {
            System.out.println("Your current number of " + this.ticker + " is " + numberOfCoins + " currently valued at: $"
                    + (numberOfCoins * coinValue));
        }

        public double getNumberOfCoins() {
            return this.numberOfCoins;
        }

        public String getTicker() {
            return this.ticker;
        }

        public void saveWallet(double cash) {
            try {
                File ledger = new File("BTCwallet.txt");
                if (ledger.createNewFile()) {
                    System.out.println("New BTCwallet created.");
                } else {
                    System.out.println("BTCwallet updated.");
                }
                try {
                    FileWriter ledgerWriter = new FileWriter("BTCwallet.txt");
                    ledgerWriter.write(cash + "\n" + ticker + "\n" + numberOfCoins);
                    ledgerWriter.close();
                } catch (IOException e) {
                    System.err.println("An error has occured in saving the BTCledger.");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.err.println("An error has occured in saving the BTCledger.");
                e.printStackTrace();
            }
        }

        public double loadWallet() {
            double cash;
            try {
                File wallet = new File("BTCwallet.txt");
                Scanner ledgerReader = new Scanner(wallet);
                cash = Double.parseDouble(ledgerReader.nextLine());
                this.ticker = ledgerReader.nextLine();
                this.numberOfCoins = Double.parseDouble(ledgerReader.nextLine());
                ledgerReader.close();

                return cash;
            } catch (FileNotFoundException e) {
                System.err.println("The BTCwallet does not exist. When you close the program, it will save a new one.");
                // e.printStackTrace();
            }
            return 100_000.00;
        }
    }


    public static class URLConnectionReader {
        private double price;

        public void fetchBTCPrice() throws Exception {
            String inputLine;
            URL apiURL = new URL("https://blockchain.info/ticker");
            URLConnection apiConnection = apiURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

        while( (inputLine = in.readLine()) != null && !inputLine.contains("USD") ){
            };
            inputLine = in.readLine()
                            .substring(
                                inputLine.indexOf(":")
                            )
                            .replace("m\":", "");


            this.price = Double.parseDouble(inputLine.replace(",", ""));
            in.close();
        }

        public double getBTCPrice() {
            return this.price;
        }

        public double getRandomBTCPrice() {
            return Math.random() * 2000 + 10_000;
        }
    }

    public static class MyDate {
        public String getTransactionTime() {
            return new Date().toString();
        }
    }


    public static class Ledger {
        private String header = String.format("|%4s|%6s|%11s|%11s|%11s|%13s|%29s|", "TYPE", "TICKER", "COIN AMOUNT",
                "COIN PRICE", "TOTAL PRICE", "CURRENT COINS", "TIMESTAMP");
        private ArrayList<String> transactionHistory = new ArrayList<String>();

        public void getHistory() {
            System.out.println(this.header);
            for (int i = 0; i < this.transactionHistory.size(); i++) {
                System.out.println(transactionHistory.get(i));
            }
        }

        public void getLastFiveHistory() {
            if (this.transactionHistory.size() >= 5) {
                System.out.println(this.header);
                for (int i = this.transactionHistory.size() - 5; i < this.transactionHistory.size(); i++) {
                    System.out.println(transactionHistory.get(i));
                }
            } else {
                this.getHistory();
            }
        }

        public void addToHistory(String date, Wallet wallet, String buySell, double BTCPrice, double coinAmount,
                                 double amountUSD, double cash) {
            String log = String.format("|%4s|%6s|%11.2f|%11.2f|%11.2f|%13.2f|%29s|", buySell, wallet.getTicker(),
                    coinAmount, BTCPrice, amountUSD, wallet.getNumberOfCoins(), date);
            this.transactionHistory.add(log);
            try {
                FileWriter fw = new FileWriter("BTCledger.txt", true);
                fw.write(log + "\n");
                fw.close();
            } catch (IOException e) {
                System.err.println("An error has occured in saving the BTCledger.");
                e.printStackTrace();
            }
            wallet.saveWallet(cash);
        }

        public void saveLedger() {
            try {
                File ledger = new File("BTCledger.txt");
                if (ledger.createNewFile()) {
                    System.out.println("New BTCledger created.");
                } else {
                    System.out.println("BTCledger updated.");
                }
                try {
                    FileWriter ledgerWriter = new FileWriter("BTCledger.txt");
                    for (String entry : this.transactionHistory) {
                        ledgerWriter.write(entry + "\n");
                    }
                    ledgerWriter.close();
                } catch (IOException e) {
                    System.err.println("An error has occured in saving the BTCledger.");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.err.println("An error has occured in saving the BTCledger.");
                e.printStackTrace();
            }
        }

        public void loadLedger() {
            try {
                File ledger = new File("BTCledger.txt");
                Scanner ledgerReader = new Scanner(ledger);
                while (ledgerReader.hasNextLine()) {
                    transactionHistory.add(ledgerReader.nextLine());
                }
                ledgerReader.close();

            } catch (FileNotFoundException e) {
                System.err.println("The BTCledger does not exist. When you close the program, it will save a new one.");
                // e.printStackTrace();
            }
        }
    }

        public static void main(String[] args) throws Exception {
            double BTCPrice;
            URLConnectionReader BTCReader = new URLConnectionReader();
            boolean cont = true;
            Scanner userInput = new Scanner(System.in);
            int choice;
            boolean choiceMade = false;
            double amountBTC;
            double amountUSD;
            Wallet BTCWallet = new Wallet("BTC", 0.0);
            Ledger BTCLedger = new Ledger();
            MyDate currentDate = new MyDate();
            double cash = BTCWallet.loadWallet();

            BTCLedger.loadLedger();
            System.out.println();

            do {
                // BTCPrice = BTCReader.getRandomBTCPrice();
                BTCReader.fetchBTCPrice();
                BTCPrice = BTCReader.getBTCPrice();
                do {
                    System.out.print(
                            "Please select from the following menu:\n" +
                                    "1) Buy Bitcoin\n" +
                                    "2) Sell Bitcoin\n" +
                                    "3) View price of Bitcoin\n" +
                                    "4) Check balance\n" +
                                    "5) Last five history\n" +
                                    "6) Full history\n" +
                                    "7) Exit\n" +
                                    "choice: ");
                    choice = userInput.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("How much Bitcoin would you like to buy: ");
                            amountBTC = userInput.nextDouble();
                            amountUSD = amountBTC * BTCPrice;
                            if (amountUSD <= cash) {
                                choiceMade = true;
                                cash -= amountUSD;
                                BTCWallet.buyCoin(amountBTC);
                                BTCWallet.printWallet(BTCPrice);
                                BTCLedger.addToHistory(currentDate.getTransactionTime(), BTCWallet, "BUY", BTCPrice,
                                        amountBTC, amountUSD, cash);
                            } else {
                                System.out.println("You cannot spend more money than you have.");
                            }
                            break;
                        case 2:
                            System.out.print("How much Bitcoin would you like to sell: ");
                            amountBTC = userInput.nextDouble();
                            amountUSD = amountBTC * BTCPrice;
                            if (amountBTC <= BTCWallet.getNumberOfCoins()) {
                                choiceMade = true;
                                cash += amountUSD;
                                BTCWallet.sellCoin(amountBTC);
                                BTCWallet.printWallet(BTCPrice);
                                BTCLedger.addToHistory(currentDate.getTransactionTime(), BTCWallet, "SELL", BTCPrice,
                                        amountBTC, amountUSD, cash);
                            } else {
                                System.out.println("You cannot sell more Bitcoin than you have.");
                            }
                            break;
                        case 3:
                            System.out.println("The current price of Bitcoin is " + BTCPrice);
                            break;
                        case 4:
                            System.out.println("Your current cash balance is $" + cash);
                            BTCWallet.printWallet(BTCPrice);
                            break;
                        case 5:
                            BTCLedger.getLastFiveHistory();
                            break;
                        case 6:
                            BTCLedger.getHistory();
                            break;
                        case 7:
                            cont = false;
                            choiceMade = true;
                            break;
                        case 8:
                            choiceMade = true;
                        default:
                            System.out.println("Please choose a valid option.");
                    }
                } while (!choiceMade);
            } while (cont);

            System.out.println();
            BTCLedger.saveLedger();
            BTCWallet.saveWallet(cash);

            userInput.close();
        }

}
