package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;
import org.usfirst.frc.team6141.robot.RobotConst;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ShooterOnCommand extends Command {
	
	private boolean firstCall;
	private double speed;
	private double timeLimit = 0;
	private final double G_ACC = -9.8;

    public ShooterOnCommand() {
        super();
        requires(Robot.control.shooter);
        this.speed = -1;
    }
    
    public ShooterOnCommand(double time, double distance) {
    	super();
    	requires(Robot.control.shooter);
    	this.speed = calculateSpeed(distance);
    	this.timeLimit = time;
    }
    
    public ShooterOnCommand(double time) {
    	super();
    	requires(Robot.control.shooter);
    	this.speed = -1;
    	this.timeLimit = time;
    }
    
    protected void initialize() {
    	if (this.timeLimit != 0) {
    		setTimeout(this.timeLimit);
    	}
    	Robot.control.shooter.setSpeed(0.5);
    	firstCall = true;
    }
    
    @Override
    protected void execute() {
    	if (firstCall) {firstCall = false;}
    	else {Robot.control.shooter.setSpeed(speed);}
    }

	@Override
	protected boolean isFinished() {
		if (this.timeLimit == 0)
			return false;
		return isTimedOut();
	}
	@Override
	protected void interrupted() {
		end();
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new ShooterOffCommand());
	}
	
	private double calculateSpeed(double distance) {
		double temp = G_ACC * Math.pow(distance, 2);
		temp = temp / (2 * Math.cos(RobotConst.SHOOTER_RELEASE_ANGLE) * (Math.cos(RobotConst.SHOOTER_RELEASE_ANGLE) 
				* (RobotConst.BALL_VERTICAL_DISPLACEMENT - distance * Math.tan(RobotConst.SHOOTER_RELEASE_ANGLE))));
		// negative because shooter motor is backwards
		return - (Math.sqrt(temp) / RobotConst.SHOOTER_MAX_SPEED);
	}
	

}
