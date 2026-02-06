package frc.robot;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;

public final class Constants {
    public static final LinearVelocity MAXIMUM_VELOCITY = Units.MetersPerSecond.of(4);
    public static final AngularVelocity MAXIMUM_ANGULAR_VELOCITY = Units.DegreesPerSecond.of(270);
    public static final LinearVelocity MAXIMUM_MODULE_VELOCITY = Units.MetersPerSecond.of(5);

    public static final int DriveControllerPort = 0;
    public static final int MechControllerPort = 1;
}
