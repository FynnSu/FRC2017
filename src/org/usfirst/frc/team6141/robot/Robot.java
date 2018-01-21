
package org.usfirst.frc.team6141.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team6141.robot.commands.AutonomousCommand;
import org.usfirst.frc.team6141.robot.subsystems.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6141.robot.misc.AutoMode;
import org.usfirst.frc.team6141.robot.misc.MatchDetails.BoilerPosition;;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	Command autonomousCommand;
	public static RobotControl control = new RobotControl();
	SendableChooser<AutoMode> autoSelector;
	SendableChooser<BoilerPosition> boilerPosition;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		autoSelector = new SendableChooser<AutoMode>();
		autoSelector.addDefault("Cross Line Backward", AutoMode.BACKWARD_CROSS_LINE);
		autoSelector.addObject("Cross Line Forward", AutoMode.FORWARD_CROSS_LINE);
		autoSelector.addObject("Center Gear", AutoMode.CENTER_GEAR);
		autoSelector.addObject("Left Gear", AutoMode.LEFT_GEAR);
		autoSelector.addObject("Right Gear", AutoMode.RIGHT_GEAR);
		autoSelector.addObject("Left Shoot", AutoMode.LEFT_SHOOT);
		autoSelector.addObject("Left Gear and Shoot", AutoMode.LEFT_GEAR_SHOOT);
		autoSelector.addObject("Right Shoot", AutoMode.RIGHT_SHOOT);
		autoSelector.addObject("Right Gear and Shoot", AutoMode.RIGHT_GEAR_SHOOT);
		
		boilerPosition = new SendableChooser<BoilerPosition>();
		boilerPosition.addDefault("Left", BoilerPosition.LEFT);
		boilerPosition.addObject("Right", BoilerPosition.RIGHT);
		
		SmartDashboard.putData("Auto Mode", autoSelector);
		SmartDashboard.putData("Boiler Position", boilerPosition);
		update();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		update();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = new AutonomousCommand(autoSelector.getSelected(), boilerPosition.getSelected());
		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		update();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		update();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		update();
	}
	
	private void update() {
		oi.update();
		control.update();
	}
}
