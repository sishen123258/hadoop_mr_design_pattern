package designpattern.Command.control;

import Command.Command;

/**
 * Created by Yue on 2015/9/28.
 */
public class SimpleRemoteControl {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void buttonWasPressed(){
        command.execute();
    }
}
