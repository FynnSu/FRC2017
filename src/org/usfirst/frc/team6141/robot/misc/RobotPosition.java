package org.usfirst.frc.team6141.robot.misc;

import org.usfirst.frc.team6141.robot.Robot;
import org.usfirst.frc.team6141.robot.misc.MatchDetails.BoilerPosition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotPosition {
	
	private double x;
	private double y;
	private double distanceToBoiler;
	private double boilerAngle;
	
	public RobotPosition() {
	}
	
	public RobotPosition(double xPos, double yPos) {
		setX(xPos);
		setY(yPos);
	}
	
	public void update() {
		SmartDashboard.putNumber("X", x);
		SmartDashboard.putNumber("Y", y);
		SmartDashboard.putNumber("Boiler Angle", boilerAngle);
		SmartDashboard.putNumber("Boiler Distance", distanceToBoiler);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		calculateDistanceToBoiler();
		calculateBoilerAngle();
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		calculateDistanceToBoiler();
		calculateBoilerAngle();
	}
	
	public double getDistanceToBoiler() {
		return distanceToBoiler;
	}
	
	public double getBoilerAngle() {
		return boilerAngle;
	}
	
	public void calculateDistanceToBoiler() {
		distanceToBoiler = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void calculateBoilerAngle() {
		if (Robot.control.matchDetails.getBoilerPosition() == BoilerPosition.LEFT) 
			boilerAngle = 270 - Math.toDegrees(Math.atan(y/x));
		else 
			boilerAngle = 90 + Math.toDegrees(Math.atan(y/x));
	}
}
