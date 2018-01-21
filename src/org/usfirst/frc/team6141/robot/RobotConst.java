package org.usfirst.frc.team6141.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotConst {
	
	// PWM Ports
	// TODO double check these
	public static final int LEFT_MOTOR_ONE_PWM_PORT = 0;
	public static final int LEFT_MOTOR_TWO_PWM_PORT = 1;
	public static final int RIGHT_MOTOR_ONE_PWM_PORT = 2;
	public static final int RIGHT_MOTOR_TWO_PWM_PORT = 3;
	public static final int SHOOTER_MOTOR_PWM_PORT = 4;
	public static final int INTAKE_MOTOR_PWM_PORT = 5;
	
	//Analog Input Ports
	public static final int ULTRASONIC_ANALOG_INPUT_PORT = 0;
	
	//Shooter
	//TODO calibrate these
	public static final double MAX_SHOOT_DISTANCE_METERS = 2.5;
	public static final double MIN_SHOOT_DISTANCE_METERS = 0.5;
	public static final double SHOOTER_RELEASE_ANGLE = 75;
	public static final double SHOOTER_MAX_SPEED = 8.0;
	public static final double BALL_VERTICAL_DISPLACEMENT = 2.0;
	
	//Ultrasonic
	public static final double ULTRASONIC_20_INCHES_VOLTAGE = 0.46;
	public static final double ULTRASONIC_40_INCHES_VOLTAGE = 0.945;
	public static final double ULTRASONIC_80_INCHES_VOLTAGE = 1.8;
	
	//Movement
	//TODO calibrate this
	public static final double DISTANCE_METERS_TO_TIME_HALF_SPEED_MULTIPLIER = 0.5;
	public static final double DISTANCE_METERS_TO_TIME_QUARTER_SPEED_MULTIPLIER = 0.25;
	
}
