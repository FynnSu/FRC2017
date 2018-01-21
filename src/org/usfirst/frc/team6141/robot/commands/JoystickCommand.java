package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class JoystickCommand extends Command {
	
	private boolean isStraightDrive;
	private double straightDriveAngle;
	private final double STRAIGHT_DRIVE_TURN_RATE = 0.005;

    public JoystickCommand() {
    	requires(Robot.control.drive);
    	isStraightDrive = false;
    }

    protected void initialize() {
    }

    protected void execute() {
    	int POV = Robot.oi.getPOV();
    	if (POV != -1) {
    		Scheduler.getInstance().add(new RotateToHeadingCommand(POV));
    	}
    	
    	double speed = Robot.oi.getSpeed();
    	double turn = Robot.oi.getTurn();
    	
    	if (Robot.oi.isStraightDrive()) {
    		if (!isStraightDrive) {
    			isStraightDrive = true;
    			straightDriveAngle = Robot.control.gyro.getAngle();
    		}
    		turn = Robot.control.gyro.getAngleError(straightDriveAngle)
    				* STRAIGHT_DRIVE_TURN_RATE;
    	} else {
    		isStraightDrive = false;
    	}
    	if (Robot.oi.isBackwardDrive()) {
    		speed = -speed;
    	}
    	if (Robot.oi.isPrecisionDrive()) {
    		speed = 0.4 * speed;
    		turn = 0.4 * turn;
    	} else {
    		if (Math.abs(speed) < 0.02) {
    			speed = 0.0;
    		}
    		if (Math.abs(turn) < 0.02) {
    			turn = 0.0;
    		}
    	}
    	
    	Robot.control.drive.setSpeedAndTurn(speed, turn);
    }

    protected boolean isFinished() {
        return false;
    }
}
