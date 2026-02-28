package frc.robot;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;

public final class Constants {
    public static final NetworkTableInstance NETWORK_TABLE = NetworkTableInstance.getDefault();

    public static final LinearVelocity MAXIMUM_VELOCITY = Units.MetersPerSecond.of(4);
    public static final AngularVelocity MAXIMUM_ANGULAR_VELOCITY = Units.DegreesPerSecond.of(270);
    public static final LinearVelocity MAXIMUM_MODULE_VELOCITY = Units.MetersPerSecond.of(5);

    public static final int FUEL_FLIGHT_TIME = 3;

    public static final int DRIVE_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;

    public static final int NEO_ENCODER_COUNTS_PER_REVOLUTION = 42;

    public static final int INTAKE_CAN_ID = 15;
    public static final double INTAKE_SPEED = 0.6;
    public static final int INTAKE_CURRENT_LIMIT = 20;

    public static final int INTAKE_ARM_CAN_ID = 50;
    public static final Angle INTAKE_ARM_EXTENSION_ANGLE = Units.Degrees.of(50);
    public static final double INTAKE_ARM_GEAR_RATIO = 20;
    public static final int INTAKE_ARM_CURRENT_LIMIT = 30;
    public static final Angle INTAKE_ARM_ALLOWED_ERROR = Units.Degrees.of(1);
    public static final double INTAKE_ARM_P = 0.0001;
    public static final double INTAKE_ARM_I = 0;
    public static final double INTAKE_ARM_D = 0;

    public static final int HOPPER_CAN_ID = 7;
    public static final double HOPPER_SPEED = 0.6;
    public static final int HOPPER_CURRENT_LIMIT = 20;

    public static final int TOWER_CAN_ID = 6;
    public static final double TOWER_SPEED = 0.6;
    public static final int TOWER_CURRENT_LIMIT = 20;

    public static final int SHOOTER_CAN_ID = 4;
    public static final double SHOOTER_SPEED = 1;
    public static final int SHOOTER_CURRENT_LIMIT = 60;

    public static final int HOOD_CAN_ID = 5;
    public static final double HOOD_GEAR_RATIO = 27;
    public static final int HOOD_CURRENT_LIMIT = 20;
    public static final Angle HOOD_ALLOWED_ERROR = Units.Degrees.of(1);
    public static final double HOOD_P = 0.0001;
    public static final double HOOD_I = 0;
    public static final double HOOD_D = 0;

    public static final int TURRET_CAN_ID = 16;
    public static final double TURRET_GEAR_RATIO = 800.0/21;
    public static final int TURRET_CURRENT_LIMIT = 30;
    public static final Angle TURRET_ALLOWED_ERROR = Units.Degrees.of(1);
    public static final double TURRET_P = 0.0001;
    public static final double TURRET_I = 0;
    public static final double TURRET_D = 0;

    public static final int TURRET_ZERO_ENCODER_PORT = 0;
    public static final double TURRET_ZERO_ENCODER_OFFSET = 0;
    public static final double TURRET_ZERO_ENCODER_GEAR_RATIO = 200.0/21;

    public static final AprilTagFieldLayout APRIL_TAG_LAYOUT = AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltAndymark);
    public static final Distance FIELD_LENGTH = Units.Inch.of(651.22);
    public static final Distance FIELD_WIDTH = Units.Inch.of(317.69);
    public static final Distance ALLIANCE_ZONE_WIDTH = Units.Inch.of(182.11);

    public static final String LEFT_LIMELIGHT_NAME = "left";
    public static final String RIGHT_LIMELIGHT_NAME = "right";
}
