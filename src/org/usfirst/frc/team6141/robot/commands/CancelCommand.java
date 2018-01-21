package org.usfirst.frc.team6141.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class CancelCommand extends InstantCommand {

    public CancelCommand() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	Scheduler.getInstance().removeAll();
    }

}
