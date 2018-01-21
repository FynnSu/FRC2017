package org.usfirst.frc.team6141.robot.sensors;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class F_Gyro extends ADXRS450_Gyro{

	double offset = 0.0;
	public F_Gyro() {
		super();
	}
	 
	 @Override 
	 public void reset() {
		setAngle(0.0d);
	 }
	 
	 /**
	  * Set the gyro to the supplied angle.  This routine will ensure the 
	  * output of the gyro is reset to the supplied angle. If angle is out of 
	  * range, nothing will be done.
	  * @param angle
	  */
	 public void setAngle(double angle) {
		 if (angle >= 0 && angle < 360) {
			super.reset();
		 	offset = angle;
		 }
	 }
	 
	 /** 
	  * Get the error in degrees from the current angle to the target angle.  This 
	  * routine calculates the minimum error, always returning an angle between
	  * -180 degrees and +180 degrees.
	  * @param targetAngle (0 to 360 degrees)
	  * @return angle difference from the current angle (-180 to +180 degrees).  A positive
	  * number indicates the target is clockwise from the current angle.  A negative 
	  * number indicates the target is counter-clockwise from the current angle.
	  */
	 public double getAngleError(double targetAngle) {
		 
		
		 double error = targetAngle - getAngle();
		 
		 if (error > 180)
			 error -= 360;
		 else if (error < -180)
			 error += 360;
		 
		 return error;
		 
	 }
	 
	 @Override
	 public double getAngle() {
		 
		 double angle = (super.getAngle() + offset) % 360.0;
		 if (angle < 0.0) {
			 angle = 360.0 + angle;
		 }
		 
		 return(angle);
	 }
	 
	 public void update() {
		 SmartDashboard.putNumber("Gyro Angle", getAngle());
		 SmartDashboard.putData("Gyro", this);
		 SmartDashboard.putNumber("Gyro Rate", getRate());
	 }
	 
}
