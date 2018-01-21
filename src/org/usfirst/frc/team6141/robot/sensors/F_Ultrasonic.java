package org.usfirst.frc.team6141.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class F_Ultrasonic extends AnalogInput{
	
	private double v40;
	private double m1, m2;
	private double b1, b2;
	
	/**
	 * Ultrasonic sensor
	 * @param analogInputPort the analog port the ultrasonic
	 * sensor is connected to.
	 */
	public F_Ultrasonic(int port) {
		super(port);
	}
	
	/**
	 * The calibrate routine takes the voltage measurement at 
	 * 20in, 40in and 80in and calculates two linear equations
	 * that describe the distance <-> voltage relationship.
	 * <p>
	 * There are two line segments because one segment does not
	 * adequately handle near and far distances.
	 * <p>
	 * The process for calibration is to set the sensor at a 
	 * distance to an object of 20, 40 and 80 inches and then
	 * input those voltage measurements into the calibration routine
	 * 
	 * @param v20 the voltage measured at 20in distance
	 * @param v40 the voltage measured at 40in distance
	 * @param v80 the voltage measured at 80in distance
	 */
	public void calibrate(double v20, double v40, double v80) {
		this.v40 = v40;
		m1 = (40.0 - 20.0) / (v40 - v20);
		b1 = 20.0 - (m1 * v20);
		
		m2 = (80.0 - 40.0) / (v80- v40);
		b2 = 80.0 - (m2 * v80);
	}
	
	public double getDistanceInches() {
		double distance = 0;
		double voltage = super.getVoltage();
		
		if (voltage <= v40) {
			distance = m1 * voltage + b1;
		} else {
			distance = m2 * voltage + b2;
		}
		
		return distance;
	}
	
	public double getDisplacement(double distanceInMeters) {
		return (distanceInMeters - getDistanceMeters());
	}
	
	public double getDistanceMeters() {
		return getDistanceInches() * 0.0254;
	}
	
	public void update() {
		SmartDashboard.putNumber("Ultrasonic Distance Inches", getDistanceInches());
		SmartDashboard.putNumber("Ultrasonic Distance Meters", getDistanceMeters());
		SmartDashboard.putData("Ultrasonic", this);
	}

}
