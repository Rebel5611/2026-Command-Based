// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.IntakeFuelCommand;
import frc.robot.Commands.RunHopperCommand;
import frc.robot.Commands.SwerveCommand;
import frc.robot.Commands.ToggleIntakeCommand;
import frc.robot.Subsystems.HopperSubsystem;
import frc.robot.Subsystems.IntakeSubsystem;
import frc.robot.Subsystems.SwerveSubsystem;

public class RobotContainer {
  private final CommandXboxController driveController = new CommandXboxController(Constants.DRIVE_CONTROLLER_PORT);
  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
  private final SwerveCommand swerveCommand = new SwerveCommand(swerveSubsystem, () -> driveController.getLeftX(), () -> -driveController.getLeftY(), () -> -driveController.getRightX(), () -> true);

  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final IntakeFuelCommand intakeFuel = new IntakeFuelCommand(intakeSubsystem);
  private final ToggleIntakeCommand toggleIntake = new ToggleIntakeCommand(intakeSubsystem); 

  private final HopperSubsystem hopperSubsystem = new HopperSubsystem();
  private final RunHopperCommand runHopper = new RunHopperCommand(hopperSubsystem);

  public RobotContainer() {
    hopperSubsystem.setDefaultCommand(runHopper);
    configureBindings();
  }

  private void configureBindings() {
    driveController.a().whileTrue(intakeFuel);
    driveController.x().onTrue(toggleIntake);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void onTeleopInit() {
    swerveSubsystem.setDefaultCommand(swerveCommand);
  }
}
