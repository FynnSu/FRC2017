package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToHeadingCommand extends Command {
	
	private final double MAX_ROTATE_SPEED = 0.4;
	
	private double heading;

    public RotateToHeadingCommand(double heading) {
    	requires(Robot.control.drive);
    	this.heading = heading;
    }

    protected void execute() {
    	double angleError = Robot.control.gyro.getAngleError(heading);
    	if (Math.abs(angleError) < 30.0) {
    		Robot.control.drive.setSpeedAndTurn(0, MAX_ROTATE_SPEED * (angleError / 30.0));
    	} else {
    		Robot.control.drive.setSpeedAndTurn(0, MAX_ROTATE_SPEED * (angleError / Math.abs(angleError)));
    	}
    }
    protected boolean isFinished() {
        return (Math.abs(Robot.control.gyro.getAngleError(heading)) < 2.0);
    }
}
