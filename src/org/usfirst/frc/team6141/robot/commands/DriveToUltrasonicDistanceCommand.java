package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

/**
 *
 */
public class DriveToUltrasonicDistanceCommand extends DriveOnHeadingCommand {
	
	private double distanceInMeters;
	private final double MAX_SPEED = 0.4;

    public DriveToUltrasonicDistanceCommand(double heading, double speed, double distanceMeters) {
    	super(heading, speed);
    	requires(Robot.control.drive);
    	distanceInMeters = distanceMeters;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double displacement = Robot.control.ultrasonic.getDisplacement(distanceInMeters);
    	if (Math.abs(displacement) < 0.5) {
    		setSpeed(MAX_SPEED * (displacement / (-0.5)));
    	} else {
    		if (displacement < 0) {
        		setSpeed(MAX_SPEED);
        	} else {
        		setSpeed(-MAX_SPEED);
        	}
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double displacement = Robot.control.ultrasonic.getDisplacement(distanceInMeters);
        return (Math.abs(displacement) <= 0.1);
    }
}
