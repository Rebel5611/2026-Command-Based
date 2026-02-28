// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.IntakeFuelCommand;
import frc.robot.Commands.RunHopperCommand;
import frc.robot.Commands.ShootCommand;
import frc.robot.Commands.SwerveCommand;
import frc.robot.Commands.TargetCommand;
import frc.robot.Commands.ToggleIntakeCommand;
import frc.robot.Subsystems.HopperSubsystem;
import frc.robot.Subsystems.IntakeSubsystem;
import frc.robot.Subsystems.SwerveSubsystem;
import frc.robot.Subsystems.TowerSubsystem;
import frc.robot.Subsystems.TurretSubsystem;
import frc.robot.Subsystems.VisionSubsystem;

public class RobotContainer {
  private final CommandXboxController driveController = new CommandXboxController(Constants.DRIVE_CONTROLLER_PORT);

  private final VisionSubsystem visionSubsystem = new VisionSubsystem();

  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem(visionSubsystem);
  private final SwerveCommand swerveCommand = new SwerveCommand(swerveSubsystem, () -> driveController.getLeftX(), () -> -driveController.getLeftY(), () -> -driveController.getRightX(), () -> true);

  // private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  // private final IntakeFuelCommand intakeFuel = new IntakeFuelCommand(intakeSubsystem);
  // private final ToggleIntakeCommand toggleIntake = new ToggleIntakeCommand(intakeSubsystem); 

  // private final HopperSubsystem hopperSubsystem = new HopperSubsystem();
  // private final RunHopperCommand runHopper = new RunHopperCommand(hopperSubsystem);

  // private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  // private final TargetCommand targetHub = new TargetCommand(swerveSubsystem, turretSubsystem);

  // private final TowerSubsystem towerSubsystem = new TowerSubsystem();
  // private final ShootCommand shoot = new ShootCommand(towerSubsystem, turretSubsystem);

  public RobotContainer() {
    // hopperSubsystem.setDefaultCommand(runHopper);
    // turretSubsystem.setDefaultCommand(targetHub);
    configureBindings();
  }

  private void configureBindings() {
    // driveController.a().whileTrue(intakeFuel);
    // driveController.x().onTrue(toggleIntake);
    // driveController.rightBumper().whileTrue(shoot);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void onTeleopInit() {
    swerveSubsystem.setDefaultCommand(swerveCommand);
  }
}
