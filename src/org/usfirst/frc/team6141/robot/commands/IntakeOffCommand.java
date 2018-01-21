package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakeOffCommand extends InstantCommand {

    public IntakeOffCommand() {
        super();
        requires(Robot.control.intake);
    }
    
    // Called once when the command executes
    protected void initialize() {
    	Robot.control.intake.stop();
    }

}
