package org.usfirst.frc.team6141.robot.commands;

/**
 *
 */
public class DriveForSetTimeCommand extends DriveOnHeadingCommand {
	private double timeLimit;

    public DriveForSetTimeCommand(double heading, double speed, double time) {
    	super(heading, speed);
    	this.timeLimit = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeLimit);
    }
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
