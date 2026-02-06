// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.Drive;
import frc.robot.Subsystems.Drivetrain;

public class RobotContainer {
  private final CommandXboxController driveController = new CommandXboxController(Constants.DriveControllerPort);
  private final Drivetrain drivetrain = new Drivetrain();
  private final Drive drive = new Drive(drivetrain, () -> driveController.getLeftX(), () -> -driveController.getLeftY(), () -> -driveController.getRightX(), () -> true);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void onTeleopInit() {
    drivetrain.setDefaultCommand(drive);
  }
}
