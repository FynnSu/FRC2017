package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;
import org.usfirst.frc.team6141.robot.RobotConst;
import org.usfirst.frc.team6141.robot.misc.AutoMode;
import org.usfirst.frc.team6141.robot.misc.MatchDetails.BoilerPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	
	//TODO test all coded autonomous modes
    public AutonomousCommand(AutoMode auto, BoilerPosition boiler) {
    	
    	if (auto != AutoMode.FORWARD_CROSS_LINE)
    		addSequential(new ResetCommand(180));
    	
    	double inchesToMetersMultiplier = 0.0254;
    	
    	switch(auto) {
    	case FORWARD_CROSS_LINE:
    		addSequential(new DriveForSetTimeCommand(0, 0.5, 2.5));
    		break;
    	case BACKWARD_CROSS_LINE:
    		addSequential(new DriveForSetTimeCommand(180, -0.5, 2.5));
    		break;
    	case LEFT_GEAR:
    		addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 75 
					* inchesToMetersMultiplier));
			addSequential(new RotateToHeadingCommand(239));
			addSequential(new DriveForSetTimeCommand(239, -0.25, 28 
					* inchesToMetersMultiplier 
					* RobotConst.DISTANCE_METERS_TO_TIME_QUARTER_SPEED_MULTIPLIER));
			addSequential(new DelayCommand(3));
			addSequential(new DriveForSetTimeCommand(239, 0.5, 30 
					* inchesToMetersMultiplier 
					* RobotConst.DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER));
			break;
    	case LEFT_GEAR_SHOOT:
    		addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
					* inchesToMetersMultiplier));
			addSequential(new RotateToHeadingCommand(239));
			addSequential(new DriveForSetTimeCommand(239, -0.25, 28 
					* inchesToMetersMultiplier 
					* RobotConst.DISTANCE_METERS_TO_TIME_QUARTER_SPEED_MULTIPLIER));
			addSequential(new DelayCommand(3));
			addSequential(new DriveForSetTimeCommand(239, 0.5, 30 
					* inchesToMetersMultiplier 
					* RobotConst.DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER));
			addSequential(new PositionAimAndShootCommand());
			addSequential(new DislodgeBallsCommand());
			addSequential(new PositionAimAndShootCommand());
			break;
    	case LEFT_SHOOT:
    		addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
					* inchesToMetersMultiplier));
			addSequential(new PositionAimAndShootCommand());
			addSequential(new DislodgeBallsCommand());
			addSequential(new PositionAimAndShootCommand());
			addSequential(new RotateToHeadingCommand(0));
			break;
    	case CENTER_GEAR:
    		addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5,  84 
					* inchesToMetersMultiplier));
    		break;
    	case RIGHT_GEAR:
    		addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
					* inchesToMetersMultiplier));
			addSequential(new RotateToHeadingCommand(121));
			addSequential(new DriveForSetTimeCommand(121, -0.25, 28 
					* inchesToMetersMultiplier 
					* RobotConst.DISTANCE_METERS_TO_TIME_QUARTER_SPEED_MULTIPLIER));
			addSequential(new DelayCommand(3));
			addSequential(new DriveForSetTimeCommand(121, 0.5, 30 
					* inchesToMetersMultiplier 
					* RobotConst.DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER));
			break;
    	case RIGHT_GEAR_SHOOT:
    		addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
					* inchesToMetersMultiplier));
			addSequential(new RotateToHeadingCommand(121));
			addSequential(new DriveForSetTimeCommand(121, -0.25, 28 
					* inchesToMetersMultiplier 
					* RobotConst.DISTANCE_METERS_TO_TIME_QUARTER_SPEED_MULTIPLIER));
			addSequential(new DelayCommand(3));
			addSequential(new DriveForSetTimeCommand(121, 0.5, 30 
					* inchesToMetersMultiplier 
					* RobotConst.DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER));
			addSequential(new PositionAimAndShootCommand());
			addSequential(new DislodgeBallsCommand());
			addSequential(new PositionAimAndShootCommand());
			break;
    	case RIGHT_SHOOT:
    		addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
					* inchesToMetersMultiplier));
			addSequential(new PositionAimAndShootCommand());
			addSequential(new DislodgeBallsCommand());
			addSequential(new PositionAimAndShootCommand());
			addSequential(new RotateToHeadingCommand(0));
			break;
    	}
    	
//    	if (robotPosition == StartPosition.LEFT) {
//			
//			if (autoMode == AutonomousMode.CROSS_LINE) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					//Start In Left Loc And Drive Forward Across Line
//					addSequential(new DriveForSetTimeCommand(0, 0.5, 2.5));
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					//Start In Left Loc And Drive Backward Across Line
//					addSequential(new DriveForSetTimeCommand(180, -0.5, 2.5));
//				}
//			} else if (autoMode == AutonomousMode.GEAR || autoMode == AutonomousMode.GEAR_AND_SHOOT) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					//Impossible Auto (must fully rotate for gear slot on back)	
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					//Start In Left Loc And Drive Backward, Rotate to Angle, Drive Backward To Gear
//					addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
//							* inchesToMetersMultiplier));
//					addSequential(new RotateToHeadingCommand(239));
//					addSequential(new DriveForSetTimeCommand(239, -0.25, 28 
//							* inchesToMetersMultiplier 
//							* RobotConst.DISTANCE_METERS_TO_TIME_QUARTER_SPEED_MULTIPLIER));
//					addSequential(new DelayCommand(3));
//					addSequential(new DriveForSetTimeCommand(239, 0.5, 30 
//							* inchesToMetersMultiplier 
//							* RobotConst.DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER));
//					if (autoMode == AutonomousMode.GEAR_AND_SHOOT) {
//						if (boilerPosition == BoilerPosition.LEFT) {
//							addSequential(new PositionAimAndShootCommand());
//							addSequential(new DislodgeBallsCommand());
//							addSequential(new PositionAimAndShootCommand());
//				    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//				    		//Impossible Auto (must cross other robot paths)
//				    	}
//					}
//					addSequential(new RotateToHeadingCommand(0));
//					
//				}
//			} else if (autoMode == AutonomousMode.SHOOT) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					
//					if (boilerPosition == BoilerPosition.LEFT) {
//			    		//Impossible Auto (must rotate for shot)
//			    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//			    		//Impossible Auto (must cross other robot paths)
//			    	}
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					
//					if (boilerPosition == BoilerPosition.LEFT) {
//			    		//Start In Left Loc And Drive Backward, Run PositionAimAndShootCommand, jolt robot, repeat
//						addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
//								* inchesToMetersMultiplier));
//						addSequential(new PositionAimAndShootCommand());
//						addSequential(new DislodgeBallsCommand());
//						addSequential(new PositionAimAndShootCommand());
//						addSequential(new RotateToHeadingCommand(0));
//			    	}  else if (boilerPosition == BoilerPosition.RIGHT) {
//			    		//Impossible Auto (must cross other robot paths)
//			    	}
//				}
//			}
//		} else if (robotPosition == StartPosition.CENTER) {
//			
//			if (autoMode == AutonomousMode.CROSS_LINE) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					//Impossible Auto (line obstructed by airship)
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					//Impossible Auto (line obstructed by airship)
//				}
//			} else if (autoMode == AutonomousMode.GEAR) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					//Impossible Auto (must fully rotate for gear slot on back)	
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					//Start In Center Loc And Drive Backward To Gear
//					addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5,  84 
//							* inchesToMetersMultiplier));
//					}
//			} else if (autoMode == AutonomousMode.SHOOT) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					
//					if (boilerPosition == BoilerPosition.LEFT) {
//			    		//Impossible Auto (must rotate for shot)
//			    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//			    		//Impossible Auto (must rotate for shot)
//			    	}
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					
//					if (boilerPosition == BoilerPosition.LEFT) {
//			    		//Impossible Auto (must cross other robot paths)
//			    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//			    		//Impossible Auto (must cross other robot paths)
//			    	}
//				}
//			} else if (autoMode == AutonomousMode.GEAR_AND_SHOOT) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					
//					if (boilerPosition == BoilerPosition.LEFT) {	
//			    		//Impossible Auto (must cross other robot paths)
//			    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//			    		//Impossible Auto (must cross other robot paths)
//			    	}
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					
//					if (boilerPosition == BoilerPosition.LEFT) {
//			    		//Impossible Auto (must cross other robot paths)
//			    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//			    		//Impossible Auto (must cross other robot paths)
//			    	}
//				}
//			}
//			
//		} else if (robotPosition == StartPosition.RIGHT) {
//			if (autoMode == AutonomousMode.CROSS_LINE) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					//Start In Right Loc And Drive Forward Across Line
//					addSequential(new DriveForSetTimeCommand(0, 0.5, 2.5));
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					//Start In Right Loc And Drive Backward Across Line
//					addSequential(new DriveForSetTimeCommand(180, -0.5, 2.5));
//				}
//			} else if (autoMode == AutonomousMode.GEAR || autoMode == AutonomousMode.GEAR_AND_SHOOT) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					//Impossible Auto (must fully rotate for gear slot on back)	
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					//Start In Right Loc And Drive Backward, Rotate to Angle, Drive Backward To Gear
//					addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
//							* inchesToMetersMultiplier));
//					addSequential(new RotateToHeadingCommand(121));
//					addSequential(new DriveForSetTimeCommand(121, -0.25, 28 
//							* inchesToMetersMultiplier 
//							* RobotConst.DISTANCE_METERS_TO_TIME_QUARTER_SPEED_MULTIPLIER));
//					addSequential(new DelayCommand(3));
//					addSequential(new DriveForSetTimeCommand(121, 0.5, 30 
//							* inchesToMetersMultiplier 
//							* RobotConst.DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER));
//					if (autoMode == AutonomousMode.GEAR_AND_SHOOT) {
//						if (boilerPosition == BoilerPosition.RIGHT) {
//							addSequential(new PositionAimAndShootCommand());
//							addSequential(new DislodgeBallsCommand());
//							addSequential(new PositionAimAndShootCommand());
//				    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//				    		//Impossible Auto (must cross other robot paths)
//				    	}
//					}
//					addSequential(new RotateToHeadingCommand(0));
//				}
//			} else if (autoMode == AutonomousMode.SHOOT) {
//				
//				if (startOrientation == StartingOrientation.FORWARD) {
//					
//					if (boilerPosition == BoilerPosition.LEFT) {
//			    		//Impossible Auto (must rotate for shot)
//			    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//			    		//Impossible Auto (must rotate for shot))
//			    	}
//				} else if (startOrientation == StartingOrientation.BACKWARD) {
//					
//					if (boilerPosition == BoilerPosition.LEFT) {
//			    		//Impossible Auto (must cross other robot paths)
//			    	} else if (boilerPosition == BoilerPosition.RIGHT) {
//			    		//Start In Right Loc And Drive Backward, Run PositionAimAndShootCommand, jolt robot, repeat
//			    		addSequential(new DriveToUltrasonicDistanceCommand(180, -0.5, 94 
//								* inchesToMetersMultiplier));
//						addSequential(new PositionAimAndShootCommand());
//						addSequential(new DislodgeBallsCommand());
//						addSequential(new PositionAimAndShootCommand());
//						addSequential(new RotateToHeadingCommand(0));
//			    	}
//				}
//			}
//		}
//    	
    }
}
 