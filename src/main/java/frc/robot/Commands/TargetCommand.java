package frc.robot.Commands;

import static edu.wpi.first.units.Units.Meter;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Subsystems.SwerveSubsystem;
import frc.robot.Subsystems.TurretSubsystem;

public class TargetCommand extends Command {
    private SwerveSubsystem swerveSubsystem;
    private TurretSubsystem turretSubsystem;

    public TargetCommand(SwerveSubsystem swerveSubsystem, TurretSubsystem turretSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        this.turretSubsystem = turretSubsystem;

        addRequirements(turretSubsystem);
    }

    @Override
    public void execute() {
        if (turretSubsystem.isHubActive() && swerveSubsystem.isInAllianceZone()) {
            turretSubsystem.setShooterSpeed(Constants.SHOOTER_SPEED);

            Translation2d hubPosition;
            if (DriverStation.getAlliance().get() == Alliance.Red) {
                hubPosition = new Translation2d(Constants.FIELD_WIDTH.in(Meter) / 2, Constants.FIELD_LENGTH.in(Meter) - Constants.ALLIANCE_ZONE_WIDTH.in(Meter));
            } else {
                hubPosition = new Translation2d(Constants.FIELD_WIDTH.in(Meter) / 2, Constants.ALLIANCE_ZONE_WIDTH.in(Meter));
            }
            hubPosition = hubPosition.minus(swerveSubsystem.getFieldVelocity().times(Constants.FUEL_FLIGHT_TIME));

            Rotation2d angleToHub = hubPosition.minus(swerveSubsystem.getPose().getTranslation()).getAngle();
            angleToHub = angleToHub.minus(swerveSubsystem.getPose().getRotation()).unaryMinus();

            turretSubsystem.setTurretAngle(Units.Degree.of(angleToHub.getDegrees() % 360));
        } else {
            turretSubsystem.stopShooter();
        }
    }

    @Override
    public void end(boolean interrupted) {
        turretSubsystem.stopShooter();
    }
}
