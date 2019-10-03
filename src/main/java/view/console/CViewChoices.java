package view.console;

import controller.Game;
import controller.Instance;
import lombok.Getter;
import model.heroes.AHero;
import view.UserInputThread;

public class CViewChoices extends Thread {

    //instance
    Instance instance = Instance.getInstance();

    @Getter
    boolean awaitInput = false;
    public boolean isAwaitInput() {
        return awaitInput;
    }

    boolean inputConsole = true;

    boolean selectHero = true;
    boolean createNewHero = false;
    boolean needConfirm = false;
    boolean canToggle = false;
    String[] selectionLabels = null;
    boolean firstRun = true;


    //     to support concurrent programming -> Threads
//     A thread is a thread of execution in a program.
//     The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
//     in this case we can run the choicepanel concurrently with other functions
    Thread inputThread = null;

    CViewChoices() {
        inputThread = new Thread(new UserInputThread(this)); //class -> UserInputThread(CViewChoices cViewChoices)
        inputThread.start();
    }

    //printing function
    public void printLegend() {
        String string = "";

        if (selectHero) {
            if (needConfirm) {
                string = "Type ";
                string += (createNewHero) ? "your heroic name," : "anything";
                string += " to confirm, empty line to change";
            } else {
                for (int i = 0; i < selectionLabels.length; i++) {
                    if (i != 0) {
                        string += ", ";
                    }
                    string += (i + 1) + " for '" + selectionLabels[i] + "'";
                }

                if (!createNewHero) {
                    string += ", 0 to Create New Hero";
                } else if (canToggle) {
                    string += ", 0 to Load a Saved Hero";
                }
            }
        } else if (instance.getGame() != null) {
            switch (instance.getGame().getGameState()) {
                case WaitChoiceMap:
                    string = "Move: ";
                    for (int i = 0; i < Game.directionsArray.length; i++) {
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

        if (!string.equals("")) {
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
        if (!inputConsole) {
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
        if (!inputConsole) {
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
        if (!inputConsole) {
            System.out.println(input);
        }
        System.out.println("---");
    }

    public void startDeathInput() {
        printLegend();
        awaitInput = true;
        inputConsole = false;
    }

    public void stopDeathInput() {
        awaitInput = false;
        if (!inputConsole) {
            System.out.println();
        }
        System.out.println("---");
    }

    public void dump() {
        inputThread.interrupt();
        if (!inputConsole) {
            System.out.println("0");
        }
        System.out.println("---");
    }
    // ---------------------------- \\

    // ~ Hero creation functions ~

    public void refreshSelection(String[] combinedLabels, boolean createNewHero, AHero hero, boolean canToggle) {
        if (firstRun){
            createNewHero = true;
            firstRun = false;
        }
        System.out.println(createNewHero);
        selectionLabels = combinedLabels;
        this.createNewHero = createNewHero;
        System.out.println("OOOO");


        if (!inputConsole) {
            System.out.println("0");
        }
        System.out.println();
        if (createNewHero) {
            System.out.println("Create a New Hero");
        } else {
            System.out.println("Load a Saved Hero");
        }
        needConfirm = false;
        this.canToggle = canToggle;
        printLegend();
        awaitInput = true; //TODO
        inputConsole = false;
    }

    public void refreshHeroSelection(int index, AHero hero) {
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
        if (createNewHero) {
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

    //    redirects input to different methods in classes
    public boolean redirectInput(String input) {
        if (!awaitInput || input == null) {
            System.out.println("Not awaiting input or input null");
            return false;
        }

        System.out.println("Redirect input of: " + input); //TODO remove
        inputConsole = true;
        System.out.println(selectHero + "SELECT HERO"); //TODO remove
        if (selectHero) {
            if (needConfirm) {
                if (input.equals("")) {
                    needConfirm = false;
                    inputConsole = false;
                    return false;
                } else
                    instance.getHeroSelect().confirmSelection(input);
            } else {
                if (input.equals("0"))
                    instance.getHeroSelect().continueHeroSelect();
                else {
                    instance.getHeroSelect().changeSelection(Integer.parseInt(input) - 1);
                }
            }
        } else {
//            redirect input depending on game state
            System.out.println(instance.getGame().getGameState().toString()); //TODO remove
            switch (instance.getGame().getGameState()) {
                case WaitChoiceCombat:
                case WaitChoiceItem:
                    //TODO Item input redirect
                case Dead:
                    if (input.equals("2"))
                        instance.gameOver();
                    else
                        instance.restartGame();
                    System.out.println("Input invalid! Please input a number between '1' and '2'");
                    return false;
                case WaitChoiceMap:
                    if (input.equals("0"))
                        instance.gameOver();
                    else
                        instance.getGame().directionInput(Integer.parseInt(input) - 1);
                    break;

                default:
                    return false;
            }
        }
        return true;
    }

    public boolean validateInput(String input) {
        if (!awaitInput || input == null) {
            return false;
        }

        if (selectHero) {
            if (needConfirm) {
                return true;
            } else {
                if (!canToggle && input.equals("0")) {
                    System.out.println("Input invalid! Please input a number between '1' and '" + selectionLabels.length + "'");
                    return false;
                }
                for (int i = 0; i <= selectionLabels.length; i++) {
                    if (input.equals(i + ""))
                        return true;
                }
            }
            System.out.println("Input invalid! Please input a number between '1' and '" + selectionLabels.length + "'");
            return false;
        } else {
//           Input validation based on game state ->
            switch (instance.getGame().getGameState()) {
                case WaitChoiceCombat:
                case WaitChoiceItem:
                case Dead:
                    if (input.equals("1") || input.equals("2"))
                        return true;
                    System.out.println("Input invalid! Please input a number between '1' and '2'");
                    return false;
                case WaitChoiceMap:
                    //TODO dynamically check directions array
                    if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4"))
                        return true;
                    System.out.println("Input invalid! Please input a number between '1' and '4'");
                default:
                    return false;
            }
        }
    }

}
