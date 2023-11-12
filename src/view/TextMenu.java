package view;

import model.program.symbolTable.MyDictionary;
import model.program.symbolTable.MyIDictionary;

import java.util.Scanner;

public class TextMenu {
    private MyIDictionary<String, Command> commands;
    public TextMenu() { commands = new MyDictionary<String, Command>(); }

    public void addCommand(Command command) { commands.add(command.getKey(), command); }

    private void printMenu() {
        System.out.println("Options are: ");
        for (Command command : commands.values()){
            String line = String.format("%4s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            Command command = commands.getElementByKey(key);
            if (command == null){
                System.out.println("Invalid option");
                continue;
            }
            command.execute();
        }
    }
}