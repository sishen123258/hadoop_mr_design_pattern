package designpattern.Command;

import Command.control.SimpleRemoteControl;
import Command.executor.Light;
import Command.executor.LightOnCommand;

/**
 * Created by Yue on 2015/9/28.
 */
public class Test {

    public static void main(String[] args) {
        SimpleRemoteControl simpleRemoteControl=new SimpleRemoteControl();
        LightOnCommand command=new LightOnCommand(new Light());
        simpleRemoteControl.setCommand(command);
        simpleRemoteControl.buttonWasPressed();
    }
}
