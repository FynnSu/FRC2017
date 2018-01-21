package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;
import org.usfirst.frc.team6141.robot.RobotConst;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class PositionAimAndShootCommand extends CommandGroup {

    public PositionAimAndShootCommand() {
    	addSequential(new DeterminePositionCommand());
    	addSequential(new ManageResultCommand());
    }
    
    public class ManageResultCommand extends CommandGroup {

    	public ManageResultCommand() {
    		double heading = Robot.control.robotPos.getBoilerAngle();
    		double distance = Robot.control.robotPos.getDistanceToBoiler();
    		addSequential(new RotateToHeadingCommand(heading));
    		if (distance > RobotConst.MAX_SHOOT_DISTANCE_METERS + 0.05) {
    			double displacement = distance - RobotConst.MAX_SHOOT_DISTANCE_METERS;
    			addSequential(new DriveForSetTimeCommand(heading, 0.5, displacement * RobotConst.DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER));
    			addSequential(new DeterminePositionCommand());
    			addSequential(new ManageResultCommand());
    		} else if (distance < RobotConst.MIN_SHOOT_DISTANCE_METERS) {
    			double displacement = RobotConst.MIN_SHOOT_DISTANCE_METERS - distance;
    			addSequential(new DriveForSetTimeCommand(heading, -0.5, displacement * RobotConst.DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER));
    			addSequential(new DeterminePositionCommand());
    			addSequential(new ManageResultCommand());
    		} else {
    			if (distance > RobotConst.MAX_SHOOT_DISTANCE_METERS - 0.05) {
    				addSequential(new ShooterOnCommand(5));
    			} else {
    				addSequential(new ShooterOnCommand(5, distance));
    			}
    		}
    	}
    	
    }
}
