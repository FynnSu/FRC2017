package org.usfirst.frc.team6141.robot.subsystems;

import org.usfirst.frc.team6141.robot.RobotConst;
import org.usfirst.frc.team6141.robot.sensors.*;
import org.usfirst.frc.team6141.robot.misc.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RobotControl extends Subsystem {
	
	public DriveTrain drive;
	public Shooter shooter;
	public Intake intake;
	public F_Gyro gyro;
	public F_Ultrasonic ultrasonic;
	public RobotPosition robotPos;
	public MatchDetails matchDetails;
	
	public RobotControl() {
		drive = new DriveTrain();
		shooter = new Shooter();
		intake = new Intake();
		gyro = new F_Gyro();
		ultrasonic = new F_Ultrasonic(RobotConst.ULTRASONIC_ANALOG_INPUT_PORT);
		ultrasonic.calibrate(RobotConst.ULTRASONIC_20_INCHES_VOLTAGE, RobotConst.ULTRASONIC_40_INCHES_VOLTAGE, RobotConst.ULTRASONIC_80_INCHES_VOLTAGE);
		robotPos = new RobotPosition();
		matchDetails = new MatchDetails();
	}
	
	public void update() {
		drive.update();
		shooter.update();
		intake.update();
		gyro.update();
		ultrasonic.update();
		matchDetails.update();
		robotPos.update();
		//TODO Test SmartDashboard Subsystem Display
		SmartDashboard.putData("Drive", drive);
		SmartDashboard.putData("Shooter", shooter);
		SmartDashboard.putData("Intake", intake);
	}

    public void initDefaultCommand() {
    }
        
}

