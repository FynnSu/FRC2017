package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class IntakeOnCommand extends Command {
	
	boolean firstCall;

    public IntakeOnCommand() {
    	super();
    	requires(Robot.control.intake);
    }

    protected void initialize() {
    	Robot.control.intake.setSpeed(-0.5);
    	firstCall = true;
    }

    protected void execute() {
    	if (firstCall) {firstCall = false;}
    	else {Robot.control.intake.setSpeed(1);}
    }

    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void interrupted() {
    	end();
    }
    
    @Override
    protected void end() {
    	Scheduler.getInstance().add(new IntakeOffCommand());
    }
}
