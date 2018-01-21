package org.usfirst.frc.team6141.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DelayCommand extends Command {

	private double timeLimit;
	
    public DelayCommand(double time) {
    	this.timeLimit = time;
    }

    protected void initialize() {
    	setTimeout(timeLimit);
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
}
