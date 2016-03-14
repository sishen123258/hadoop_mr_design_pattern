package designpattern.Command.executor;

import Command.Command;

/**
 * Created by Yue on 2015/9/28.
 */
public class LightOnCommand implements Command{

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
