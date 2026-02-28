package frc.robot.Subsystems;


import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TowerSubsystem extends SubsystemBase {
    private SparkMax tower;
    private SparkMaxConfig towerConfig;

    public TowerSubsystem() {
        tower = new SparkMax(Constants.TOWER_CAN_ID, MotorType.kBrushless);

        towerConfig = new SparkMaxConfig();
        towerConfig.idleMode(IdleMode.kBrake);
        towerConfig.smartCurrentLimit(Constants.TOWER_CURRENT_LIMIT);
        tower.configure(towerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runTower() {
        tower.set(Constants.TOWER_SPEED);
    }

    public void reverseTower() {
        tower.set(-Constants.TOWER_SPEED);
    }

    public void stop() {
        tower.stopMotor();
    }
}
