package frc.robot.Subsystems;

import static edu.wpi.first.units.Units.Rotation;

import java.util.Optional;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {
    private TalonFX shooter;
    private TalonFXConfiguration shooterConfig;

    private SparkMax hood;
    private SparkMaxConfig hoodConfig;
    private SparkClosedLoopController hoodPID;

    private SparkMax turret;
    private SparkMaxConfig turretConfig;
    private SparkClosedLoopController turretPID;

    private DutyCycleEncoder turretZeroEncoder;

    public TurretSubsystem() {
        shooter = new TalonFX(Constants.SHOOTER_CAN_ID);
        shooterConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        shooterConfig.CurrentLimits.StatorCurrentLimit = Constants.SHOOTER_CURRENT_LIMIT;
        shooter.getConfigurator().apply(shooterConfig);

        hood = new SparkMax(Constants.HOOD_CAN_ID, MotorType.kBrushless);
        
        hoodConfig = new SparkMaxConfig();
        hoodConfig.idleMode(IdleMode.kBrake);
        hoodConfig.smartCurrentLimit(Constants.HOOD_CURRENT_LIMIT);
        hoodConfig.encoder.positionConversionFactor(1/Constants.HOOD_GEAR_RATIO);
        hoodConfig.closedLoop.pid(Constants.HOOD_P, Constants.HOOD_I, Constants.HOOD_D);
        hoodConfig.closedLoop.allowedClosedLoopError(Constants.HOOD_ALLOWED_ERROR.in(Rotation), ClosedLoopSlot.kSlot0);
        hood.configure(hoodConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        hoodPID = hood.getClosedLoopController();

        turretZeroEncoder = new DutyCycleEncoder(Constants.TURRET_ZERO_ENCODER_PORT);
        turretZeroEncoder.setDutyCycleRange(3.884/1000, 998.06/1000);

        turret = new SparkMax(Constants.TURRET_CAN_ID, MotorType.kBrushless);
        
        turretConfig = new SparkMaxConfig();
        turretConfig.idleMode(IdleMode.kBrake);
        turretConfig.smartCurrentLimit(Constants.TURRET_CURRENT_LIMIT);
        turretConfig.encoder.positionConversionFactor(1/Constants.TURRET_GEAR_RATIO);
        turretConfig.closedLoop.pid(Constants.TURRET_P, Constants.TURRET_I, Constants.TURRET_D);
        turretConfig.closedLoop.allowedClosedLoopError(Constants.TURRET_ALLOWED_ERROR.in(Rotation), ClosedLoopSlot.kSlot0);
        turret.configure(turretConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        turret.getEncoder().setPosition(0.5 + ((((turretZeroEncoder.get() + Constants.TURRET_ZERO_ENCODER_OFFSET) % 1) - 0.5) / Constants.TURRET_ZERO_ENCODER_GEAR_RATIO));

        turretPID = turret.getClosedLoopController();
    }

    public void setTurretAngle(Angle angle) {
        turretPID.setSetpoint(angle.in(Rotation), ControlType.kPosition);
    }

    public void setHoodAngle(Angle angle) {
        hoodPID.setSetpoint(angle.in(Rotation), ControlType.kPosition);
    }

    public void setShooterSpeed(double speed) {
        shooter.set(speed);
    }

    public void stopShooter() {
        shooter.set(0);
    }

    public boolean isHubActive() {
        Optional<Alliance> alliance = DriverStation.getAlliance();
        if (alliance.isEmpty()) {
            return false;
        }
        if (DriverStation.isAutonomousEnabled()) {
            return true;
        }
        if (!DriverStation.isTeleopEnabled()) {
            return false;
        }

        double matchTime = DriverStation.getMatchTime();
        String gameData = DriverStation.getGameSpecificMessage();
        if (gameData.isEmpty()) {
            return true;
        }

        boolean blueActiveFirst = gameData.charAt(0) != 'B';

        boolean shift1Active = switch (alliance.get()) {
            case Red -> !blueActiveFirst;
            case Blue -> blueActiveFirst;
        };

        if (matchTime > 130 || matchTime <= 30) {
            return true;
        } else if ((matchTime - 30) % 25 <= Constants.FUEL_FLIGHT_TIME) {
            return true;
        } else {
            return ((matchTime - 30) % 50 > 25 && shift1Active) || ((matchTime - 30) % 50 < 25 && !shift1Active);
        }
    }

    public boolean isLocked() {
        return turretPID.isAtSetpoint() && hoodPID.isAtSetpoint();
    }
}