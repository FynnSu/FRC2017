package org.usfirst.frc.team6141.robot.subsystems;

import org.usfirst.frc.team6141.robot.RobotConst;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	
	private VictorSP motor;
	
	public Shooter() {
		motor = new VictorSP(RobotConst.SHOOTER_MOTOR_PWM_PORT);
	}
	
	public void setSpeed(double speed) {
		motor.set(speed);
	}
	
	public void stop() {
		motor.stopMotor();
	}
	
	public void update() {
		SmartDashboard.putNumber("Shooter Power", motor.getSpeed());
		SmartDashboard.putData("Shooter", motor);
	}

	@Override
	protected void initDefaultCommand() {
	}

}
