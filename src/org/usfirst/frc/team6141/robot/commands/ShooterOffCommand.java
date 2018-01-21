package org.usfirst.frc.team6141.robot.commands;

import org.usfirst.frc.team6141.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShooterOffCommand extends InstantCommand {

    public ShooterOffCommand() {
        super();
        requires(Robot.control.shooter);
    }
    protected void initialize() {
    	Robot.control.shooter.stop();
    }

}
