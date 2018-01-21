package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;
import org.usfirst.frc.team6141.robot.RobotConst;
import org.usfirst.frc.team6141.robot.misc.MatchDetails.BoilerPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DeterminePositionCommand extends CommandGroup {
	
	private double xValue, yValue;
	
    public DeterminePositionCommand() {
    	double xAngle;
    	if (Robot.control.matchDetails.getBoilerPosition() == BoilerPosition.RIGHT)
    		xAngle = 90;
    	else
    		xAngle = 270;
    	if (Math.abs(Robot.control.gyro.getAngleError(xAngle)) < Math.abs(Robot.control.gyro.getAngleError(180))) {
    		addSequential(new RotateToHeadingCommand(xAngle));
    		addSequential(new DelayCommand(0.5));
        	addSequential(new MeasureDistanceCommand(true));
        	addSequential(new RotateToHeadingCommand(180));
        	addSequential(new DelayCommand(0.5));
        	addSequential(new MeasureDistanceCommand(false));
    	} else {
    		addSequential(new RotateToHeadingCommand(180));
    		addSequential(new DelayCommand(0.5));
        	addSequential(new MeasureDistanceCommand(false));
        	addSequential(new RotateToHeadingCommand(xAngle));
        	addSequential(new DelayCommand(0.5));
        	addSequential(new MeasureDistanceCommand(true));
    	}
    	addSequential(new SetPositionCommand());
    }
    
    public class MeasureDistanceCommand extends InstantCommand {
    	
    	private boolean x;
    	
    	public MeasureDistanceCommand(boolean isX) {
    		this.x = isX;
    	}
    	
    	@Override
    	protected void initialize() {
    		//TODO check that extra distance doesnt't skew result too much after robot rotation
    		if (x) {
    			xValue = Robot.control.ultrasonic.getDistanceMeters();
    		} else {
    			yValue = Robot.control.ultrasonic.getDistanceMeters();
    		}
    	}
    }
    
    public class SetPositionCommand extends InstantCommand {
    	
    	@Override
    	protected void initialize() {
    		Robot.control.robotPos.setX(xValue);
    		Robot.control.robotPos.setY(yValue);
    	}
    }
}
