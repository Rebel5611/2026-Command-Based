package frc.robot.Subsystems;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class SwerveSubsystem extends SubsystemBase {
    private SwerveDrive swerveDrive;
    public SwerveSubsystem() {
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        try {
            swerveDrive = new SwerveParser(new File(Filesystem.getDeployDirectory(), "swerve")).createSwerveDrive(Constants.MAXIMUM_MODULE_VELOCITY.in(MetersPerSecond));
        } catch (IOException e) {
            System.out.println("ERROR: Failed to load swerve configs");
        }

        swerveDrive.setCosineCompensator(RobotBase.isReal());
        swerveDrive.resetOdometry(new Pose2d(new Translation2d(5, 5), new Rotation2d()));
    }

    public void drive(double x, double y, double rot, boolean fieldOriented) {
        Translation2d translation = new Translation2d(x, y);
        
        //Apply deadzones
        translation = translation.getNorm() > 0.075 ? translation.times(Constants.MAXIMUM_VELOCITY.in(MetersPerSecond)) : Translation2d.kZero;
        rot = Math.abs(rot) > 0.075 ? rot * Constants.MAXIMUM_ANGULAR_VELOCITY.in(RadiansPerSecond) : 0;

        swerveDrive.drive(translation, rot, fieldOriented, false);
    }

    public void stop() {
        swerveDrive.drive(Translation2d.kZero, 0, false, false);
    }
}