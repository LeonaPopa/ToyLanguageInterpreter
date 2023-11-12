package view;

import controller.Controller;
import model.ProgramState;

public class runCommand extends Command {
    private Controller controller;
    private ProgramState program;
    public runCommand(String key, String description, Controller ctrl, ProgramState prg) {
        super(key, description);
        controller = ctrl;
        program = prg;
    }

    public void execute() {
        try {
            controller.allStep(program);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}