package view;

import controller.Controller;
import model.ProgramState;
import model.exceptions.MyException;

import java.io.*;
import java.util.Scanner;

public class Main {
    Controller controller;

    public Main(Controller ctrl) {
        controller = ctrl;
    }

    private void printMenu() {
        System.out.println("Options:");
        System.out.println(" - input <index>");
        //System.out.println(" - one step <index>");
        //System.out.println(" - complete");
        System.out.println(" - exit");
    }

    public void run() {
        TextMenu menu = new TextMenu();
        try {
            ProgramState program1 = controller.getProgramatIndex(0);
            ProgramState program2 = controller.getProgramatIndex(1);
            ProgramState program3 = controller.getProgramatIndex(2);
            ProgramState program4 = controller.getProgramatIndex(3);

            menu.addCommand(new exitCommand("0", "exit"));
            menu.addCommand(new runCommand("1", "example1", controller, program1));
            menu.addCommand(new runCommand("2", "example2", controller, program2));
            menu.addCommand(new runCommand("3", "example3", controller, program3));
            menu.addCommand(new runCommand("4", "example4", controller, program4));

            menu.show();
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }
}