package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Violently jolts robot forward and backward in order to loosen stuck balls in storage compartment
 * Returns approximately to original position
 */
public class DislodgeBallsCommand extends CommandGroup {

    public DislodgeBallsCommand() {
    	double currentHeading = Robot.control.gyro.getAngle();
        addSequential(new DriveForSetTimeCommand(currentHeading, 1.0, 0.2));
        addSequential(new DriveForSetTimeCommand(currentHeading, -1.0, 0.2));
    }
}
