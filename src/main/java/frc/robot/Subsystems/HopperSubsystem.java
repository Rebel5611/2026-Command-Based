package frc.robot.Subsystems;


import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperSubsystem extends SubsystemBase {
    private SparkMax hopper;
    private SparkMaxConfig hopperConfig;

    public HopperSubsystem() {
        hopper = new SparkMax(Constants.HOPPER_CAN_ID, MotorType.kBrushless);

        hopperConfig = new SparkMaxConfig();
        hopperConfig.idleMode(IdleMode.kCoast);
        hopperConfig.smartCurrentLimit(Constants.HOPPER_CURRENT_LIMIT);
        hopper.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runHopper() {
        hopper.set(Constants.HOPPER_SPEED);
    }

    public void reverseHopper() {
        hopper.set(-Constants.HOPPER_SPEED);
    }

    public void stop() {
        hopper.stopMotor();
    }
}
