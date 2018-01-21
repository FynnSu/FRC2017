package org.usfirst.frc.team6141.robot;

import org.usfirst.frc.team6141.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * Driver Joystick
 * -----------------------------------------------------------
 * 
 * Axis
 * ---------------
 * Y 			Speed
 * X			
 * Z			Turn
 * 
 * Buttons
 * --------------
 * 1			Forward/Backward Drive Toggle
 * 2			Precision Drive Toggle
 * 3
 * 4
 * 5
 * 6
 * 7			Cancel Current Commands
 * 8			Straight Drive Toggle
 * 9			Drive Straight At Half Speed
 * 10			Drive Straight At Quarter Speed
 * 11			Determine Position
 * 12			PositionAimAndShootCommand
 * 
 * 
 * Operator Joystick
 * -----------------------------------------------------------
 * Axis
 * ----------------
 * L Y
 * L X			
 * R Y
 * R X
 * L Trigger
 * R Trigger
 * 
 * Buttons
 * ---------------
 * A			Shooter On
 * B			Shooter Off
 * X			Cancel Current Commands
 * Y			Reset Gyro
 * Start
 * Reset
 * L1			Intake On
 * R1			Intake Off
 * 
 * POV
 * --------------
 * Up			Rotate To 0 degrees
 * Down			Rotate To 180 degrees
 * Left			Rotate To 270 degrees
 * Right		Rotate To 90 degrees
 * 
 */
public class OI {
	
	//Joystick Ports
	private static final int JOYSTICK_DRIVER_PORT = 0;
	private static final int JOYSTICK_OPERATOR_PORT = 1;
	
	//Driver Joystick Toggle Ports
	private static final int TOGGLE_DRIVE_STRAIGHT_PORT = 8;
	private static final int TOGGLE_DRIVE_PRECISION_PORT = 2;
	private static final int TOGGLE_DRIVE_BACKWARD_PORT = 1;
	
	//Operator Joystick Button Ports
	private static final int BUTTON_SHOOTER_ON_PORT = 1;
	private static final int BUTTON_SHOOTER_OFF_PORT = 2;
	private static final int BUTTON_INTAKE_ON_PORT = 5;
	private static final int BUTTON_INTAKE_OFF_PORT = 6;
	private static final int BUTTON_OPERATOR_CANCEL_PORT = 3;
	private static final int BUTTON_RESET_PORT = 4;
	
	//Driver Joystick Button Ports
	private static final int BUTTON_DRIVER_CANCEL_PORT = 7;
	private static final int BUTTON_DRIVE_STRAIGHT_HALF_SPEED_PORT = 9;
	private static final int BUTTON_DRIVE_STRAIGHT_QUARTER_SPEED_PORT = 10;
	private static final int BUTTON_DETERMINE_POSITION_PORT = 11;
	private static final int BUTTON_POSITION_AIM_AND_SHOOT_PORT = 12;
	
	//Joysticks
	private Joystick driver 							= new Joystick(JOYSTICK_DRIVER_PORT);
	private Joystick operator 							= new Joystick(JOYSTICK_OPERATOR_PORT);
	
	//Toggles
	private Toggle driveStraight 						= new Toggle(driver, TOGGLE_DRIVE_STRAIGHT_PORT, false);
	private Toggle drivePrecision 						= new Toggle(driver, TOGGLE_DRIVE_PRECISION_PORT, false);
	private Toggle driveBackward 						= new Toggle(driver, TOGGLE_DRIVE_BACKWARD_PORT, false);
	
	//Operator Joystick Buttons
	private JoystickButton shooterOn 					= new JoystickButton(operator, BUTTON_SHOOTER_ON_PORT);
	private JoystickButton shooterOff 					= new JoystickButton(operator, BUTTON_SHOOTER_OFF_PORT);
	private JoystickButton intakeOn 					= new JoystickButton(operator, BUTTON_INTAKE_ON_PORT);
	private JoystickButton intakeOff 					= new JoystickButton(operator, BUTTON_INTAKE_OFF_PORT);
	private JoystickButton operatorCancel 				= new JoystickButton(operator, BUTTON_OPERATOR_CANCEL_PORT);
	private JoystickButton reset						= new JoystickButton(operator, BUTTON_RESET_PORT);
	
	//Driver Joystick Buttons
	private JoystickButton driverCancel 				= new JoystickButton(driver, BUTTON_DRIVER_CANCEL_PORT);
	private JoystickButton determinePosition 			= new JoystickButton(driver, BUTTON_DETERMINE_POSITION_PORT);
	private JoystickButton driveStraightHalfSpeed 		= new JoystickButton(driver, BUTTON_DRIVE_STRAIGHT_HALF_SPEED_PORT);
	private JoystickButton driveStraightQuarterSpeed 	= new JoystickButton(driver, BUTTON_DRIVE_STRAIGHT_QUARTER_SPEED_PORT);
	private JoystickButton positionAimAndShoot 			= new JoystickButton(driver, BUTTON_POSITION_AIM_AND_SHOOT_PORT);
	
	/**
	 * Bind Buttons To Commands
	 */
	public OI() {
		//Operator Buttons
		shooterOn.whenPressed(new ShooterOnCommand());
		shooterOff.whenPressed(new ShooterOffCommand());
		intakeOn.whenPressed(new IntakeOnCommand());
		intakeOff.whenPressed(new IntakeOffCommand());
		operatorCancel.whenPressed(new CancelCommand());
		reset.whenPressed(new ResetCommand());
		
		//Driver Buttons
		driverCancel.whenPressed(new CancelCommand());
		
		//TODO test these commands
		determinePosition.whenPressed(new DeterminePositionCommand());
		positionAimAndShoot.whenPressed(new PositionAimAndShootCommand());
		
		//TODO use these for RobotConst calibration
		driveStraightHalfSpeed.whenPressed(new DriveForSetTimeCommand(Robot.control.gyro.getAngle(), 0.5, 1));
		driveStraightQuarterSpeed.whenPressed(new DriveForSetTimeCommand(Robot.control.gyro.getAngle(), 0.25, 1));
	}
	
	/**
	 * @return Speed value (-1 to 1)
	 */
	public double getSpeed() {
		return driver.getAxis(AxisType.kY);
	}
	
	/**
	 * @return Turn value (-1 to 1)
	 */
	public double getTurn() {
		return driver.getAxis(AxisType.kZ);
	}
	
	//Get Toggle Values
	public boolean isStraightDrive() {return driveStraight.getToggle();}
	public boolean isPrecisionDrive() {return drivePrecision.getToggle();}
	public boolean isBackwardDrive() {return driveBackward.getToggle();}
	
	/**
	 * Update the OI class.
	 * Called during the periodic components of robot sequence
	 * Displays data on SmartDashboard and checks the POV for a value
	 */
	public void update() {
		SmartDashboard.putString("Driver", driver.getName());
		SmartDashboard.putNumber("Driver Speed", getSpeed());
		SmartDashboard.putNumber("Driver Turn", getTurn());
		SmartDashboard.putBoolean("Straight Drive", isStraightDrive());
		SmartDashboard.putBoolean("Precision Drive", isPrecisionDrive());
		SmartDashboard.putBoolean("Backward Drive", isBackwardDrive());
	}
	
	public int getPOV() {
		return operator.getPOV();
	}
	
	
	/**
	 * Toggle Class that stores values for a toggle object and handles 
	 * button presses for desired joystick button
	 *
	 */
	public class Toggle {
		private boolean toggle;
		
		public Toggle(Joystick joystick, int port, boolean start) {
			JoystickButton button = new JoystickButton(joystick, port);
			button.whenPressed(new UpdateToggleCommand());
			toggle = start;
		}
		
		public boolean getToggle() {
			return toggle;
		}
		
		public void setToggle(boolean value) {
			toggle = value;
		}
		
		public class UpdateToggleCommand extends InstantCommand {
			@Override
			protected void initialize() {
				toggle = !toggle;
			}
		}		
	}
}
