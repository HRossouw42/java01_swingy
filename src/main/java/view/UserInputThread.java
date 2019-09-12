package view;

import view.console.CViewChoices;

import java.util.Scanner;

//If you are not extending the Thread class,your class object would not be treated as a thread object.So you need to explicitely create Thread class object
public class UserInputThread implements Runnable {
    CViewChoices cViewChoices = null;

    //    https://alvinalexander.com/java/edu/pj/pj010005
    Scanner scanner = new Scanner(System.in);
    String input = null;
    boolean firstRun = true;

    //getter
    public UserInputThread(CViewChoices cViewChoices) {
        super();
        this.cViewChoices = cViewChoices;
    }

    //    First thread -> https://www.javatpoint.com/creating-thread
//    running input in a thread -> https://stackoverflow.com/questions/37135654/input-using-thread && https://caveofprogramming.com/java-multithreading/java-multithreading-wait-notify-part-8.html
    public void run() {
        System.out.println("Input thread is running");
        try {
            do {
//                    will continually run until valid input detected
                do {
                    while (!cViewChoices.isAwaitInput()){
                        Thread.sleep(100);
                    }

                    if (firstRun)
                        firstRun = false; //prevents first print of choice panel cause the game isn't running yet
                    else {
                        cViewChoices.printLegend();
                        //System.out.println("Input command ->");
                    }
                    input = scanner.nextLine();
                    System.out.println("Input of :" + input); //TODO remove
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Input thread interrupted"); // TODO remove
                        return;
                    }
                } while (!cViewChoices.validateInput(input));

                //if valid input found then redirect input
                firstRun = cViewChoices.redirectInput(input);
                //System.out.println(firstRun);

            } while (!Thread.currentThread().isInterrupted());
            System.out.println("Input thread interrupted"); //TODO remove
        } catch (Exception e) {
            System.out.println("Exception in run"); //TODO remove
            System.out.println(e);
//            e.printStackTrace();
        }
    }


}
