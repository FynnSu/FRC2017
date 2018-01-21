package org.usfirst.frc.team6141.robot.misc;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MatchDetails {
	
	public static enum BoilerPosition {
		LEFT,
		RIGHT
	}
	private BoilerPosition boilerPosition = BoilerPosition.LEFT;
	
	public void update() {
		SmartDashboard.putString("Boiler Position", boilerPosition.toString());
	}
	
	public MatchDetails() {
	}

	public BoilerPosition getBoilerPosition() {
		return boilerPosition;
	}

	public void setBoilerPosition(BoilerPosition boilerPosition) {
		this.boilerPosition = boilerPosition;
	}
}
