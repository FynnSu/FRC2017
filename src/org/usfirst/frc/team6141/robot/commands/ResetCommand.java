package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ResetCommand extends InstantCommand {
	
	private double newAngle;
	
	public ResetCommand(double angle) {
		super();
		this.newAngle = angle;
	}

    public ResetCommand() {
        super();
        newAngle = 0;
    }
    
    protected void initialize() {
    	Robot.control.gyro.setAngle(newAngle);
    }

}
