package org.usfirst.frc.team6141.robot.subsystems;

import org.usfirst.frc.team6141.robot.RobotConst;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem{
	
	private Spark motor;
	
	public Intake() {
		motor = new Spark(RobotConst.INTAKE_MOTOR_PWM_PORT);
	}
	
	public void setSpeed(double speed) {
		motor.set(speed);
	}
	
	public void stop() {
		motor.stopMotor();
	}
	
	public void update() {
		SmartDashboard.putNumber("Intake Power", motor.getSpeed());
		SmartDashboard.putData("Intake", motor);
	}

	@Override
	protected void initDefaultCommand() {
	}

}
