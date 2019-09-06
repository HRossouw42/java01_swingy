package view;

import view.console.CViewChoices;

import java.util.Scanner;

public class userInput {
    CViewChoices cViewChoices = null;

//    https://alvinalexander.com/java/edu/pj/pj010005
    Scanner scanner = new Scanner(System.in);
    String input = null;
    boolean firstRun = true;

    //getter
    public userInput (CViewChoices cViewChoices) {
        super();
        this.cViewChoices = cViewChoices;
    }

    public void catchInput() {
        try {

            if (firstRun)
                firstRun = false; //prevents first print of choice panel
            else {
                cViewChoices.printLegend();
            }
            input = scanner.nextLine();
            if (Thread.currentThread().isInterrupted())
                return;


        }
        catch (Exception e){
            System.out.println(e);
//            e.printStackTrace();
        }
    }


}
