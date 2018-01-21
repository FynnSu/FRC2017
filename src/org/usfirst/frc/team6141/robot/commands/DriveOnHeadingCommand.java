package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveOnHeadingCommand extends Command {
	
	private double heading;
	private double speed;
	private final double STRAIGHT_DRIVE_TURN_RATE = 0.005;
	
	//TODO Test this works
	public DriveOnHeadingCommand(double heading, double speed) {
		super();
		requires(Robot.control.drive);
		this.heading = heading;
		this.speed = speed;
	}
	
	protected void setSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	protected void execute() {
		double turn = Robot.control.gyro.getAngleError(heading) 
				* STRAIGHT_DRIVE_TURN_RATE;
		if (turn > 1) turn = 1;
		if (turn < -1) turn = -1;
		Robot.control.drive.setSpeedAndTurn(speed, turn);
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	
}
