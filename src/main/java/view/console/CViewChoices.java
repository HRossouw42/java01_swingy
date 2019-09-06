package view.console;

import controller.Game;
import controller.Instance;
import lombok.Getter;
import model.heroes.AHero;

public class CViewChoices extends Thread{

    //instance
    Instance instance = Instance.getInstance();

    @Getter boolean awaitInput = false;
    boolean inputConsole = true;

    boolean selectHero = true;
    boolean createNewHero = false;
    boolean needConfirm = false;
    boolean canToggle = false;
    String[] selectionLabels = null;

//     to support concurrent programming -> Threads
//     A thread is a thread of execution in a program.
//     The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
//     in this case we can run the choicepanel concurrently with other functions
    Thread choiceThread = null;

    CViewChoices() {
        //TODO Make thread
        //choiceThread = new Thread(new ChoiceRunnable(this));
        //choiceThread.start();
    }

    //printing function
    public void printLegend() {
        String string = "";

        if (selectHero){
            if (needConfirm) {
                string = "Type ";
                string += (createNewHero) ? "your name, hero" : "anything";
                string +=" to confirm, empty line to change";
            }
            else {
                for (int i = 0; i < selectionLabels.length; i++) {
//                    if (i != 0) {
//                        string += ", ";
//                    }
                    string += (i + 1) + " for '" + selectionLabels[i] + "'";
                }

                if (!createNewHero){
                    string +=", 0 to Create New Hero";
                }
                else if (canToggle){
                    string +=", 0 to Load a Saved Hero";
                }
            }
        }
        else if (instance.getGame() != null) {
            switch (instance.getGame().getGameState()) {
                case WaitChoiceMap:
                    string = "Move: ";
                    for (int i = 0; i < Game.directionsArray.length; i++){
//                        if (i != 0){
//                            string +=", ";
//                        }
                        //prints out direction on array
                        string += (i + 1) + " for " + Game.directionsArray[i];
                    }
                    string += ", 0 to Exit Game";
                    break;
                case WaitChoiceCombat:
                    string = "1 to fight, 2 to flee";
                    break;
                case WaitChoiceItem:
                    string = "1 to equip, 2 to discard";
                    break;
                case Dead:
                    string = "1 to retry, 2 to exit";
                    break;
                default:
                    string = "SEE CVIEWCHOICES CLASS";
                    break;
            }
        }

        if(!string.equals("")){
            System.out.println(string);
        }

    }

    // ---------------------------- \\
    // game's console methods
    public void mainGameStarted() {
        selectHero = false;
        selectionLabels = null;
    }

    public void startDirectionInput() {
        printLegend();
        awaitInput = true;
        inputConsole = false;
    }

    public void stopDirectionInput(int input) {
        awaitInput = false;
        if (!inputConsole){
            System.out.println(input + 1);
        }
        System.out.println("---");
    }

    public void startCombatInput() {
        printLegend();
        awaitInput = true;
        inputConsole = false;
    }

    public void stopFightInput(String input) {
        awaitInput = false;
        if (!inputConsole){
            System.out.println(input);
        }
        System.out.println("---");
    }

    public void startItemInput() {
        printLegend();
        awaitInput = true;
        inputConsole = false;
    }

    public void stopItemInput(String input) {
        awaitInput = false;
        if (!inputConsole){
            System.out.println(input);
        }
        System.out.println("---");
    }

    public void startDeathInput() {
        printLegend();
        awaitInput = true;
        inputConsole = false;
    }

    public void stopDeathInput(String input) {
        awaitInput = false;
        if (!inputConsole){
            System.out.println(input);
        }
        System.out.println("---");
    }

    public void dump() {
        choiceThread.interrupt();
        if(!inputConsole){
            System.out.println("0");
        }
        System.out.println("---");
    }
    // ---------------------------- \\

    // ~ Hero creation functions ~

    public void refreshSelection(String [] combinedLabels, boolean createNewHero, AHero hero) {
        selectionLabels = combinedLabels;
        this.createNewHero = createNewHero;

        if (!inputConsole){
            System.out.println("0");
        }
        System.out.println();
        if (createNewHero) {
            System.out.println("Create a New Hero");
        }
        else {
            System.out.println("Load a Saved Hero");
        }
        needConfirm = false;
        printLegend();
        awaitInput = true;
        inputConsole = false;
    }

    public void refreshHeroSelection(int index, AHero hero){
        if (needConfirm) {
            System.out.println();
            needConfirm = false;
            printLegend();
        }
        if (!inputConsole)
            System.out.println(index + 1);
        System.out.println();
        System.out.println("You've selected:");
        CViewHero cViewHero = new CViewHero(hero);
        if (createNewHero){
            System.out.println("Class description: \n" + hero.getClassDescription() + "\n");
        }

        needConfirm = true;
        printLegend();
        awaitInput = true;
        inputConsole = false;
    }

    public void printError(String error) {
        if (needConfirm)
            System.out.println(error);
    }

    // ~~ input functions ~~ \\

    public boolean redirectInput(String input){
        if (awaitInput || input == null )
            return false;

        inputConsole = true;


        return true;
    }

}
