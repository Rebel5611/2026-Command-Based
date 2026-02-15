package frc.robot;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;

public final class Constants {
    public static final LinearVelocity MAXIMUM_VELOCITY = Units.MetersPerSecond.of(4);
    public static final AngularVelocity MAXIMUM_ANGULAR_VELOCITY = Units.DegreesPerSecond.of(270);
    public static final LinearVelocity MAXIMUM_MODULE_VELOCITY = Units.MetersPerSecond.of(5);

    public static final int DRIVE_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;

    public static final int NEO_ENCODER_COUNTS_PER_REVOLUTION = 42;

    public static final int INTAKE_CAN_ID = 14;
    public static final double INTAKE_SPEED = 0.6;
    public static final int INTAKE_CURRENT_LIMIT = 20;

    public static final int INTAKE_ARM_CAN_ID = 15;
    public static final Angle INTAKE_ARM_EXTENSION_ANGLE = Units.Degrees.of(110);
    public static final double INTAKE_ARM_GEAR_RATIO = 20;
    public static final int INTAKE_ARM_CURRENT_LIMIT = 60;
    public static final double INTAKE_ARM_P = 0.0001;
    public static final double INTAKE_ARM_I = 0;
    public static final double INTAKE_ARM_D = 0;
}
