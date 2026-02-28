package frc.robot.Subsystems;


import static edu.wpi.first.units.Units.Rotation;
import static edu.wpi.first.units.Units.Degree;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    private SparkMax intake;
    private SparkMaxConfig intakeConfig;

    private SparkMax arm;
    private SparkMaxConfig armConfig;
    private SparkClosedLoopController armPID;

    private boolean extended = false;
    public IntakeSubsystem() {
        intake = new SparkMax(Constants.INTAKE_CAN_ID, MotorType.kBrushless);

        intakeConfig = new SparkMaxConfig();
        intakeConfig.idleMode(IdleMode.kCoast);
        intakeConfig.smartCurrentLimit(Constants.INTAKE_CURRENT_LIMIT);
        intake.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        arm = new SparkMax(Constants.INTAKE_ARM_CAN_ID, MotorType.kBrushless);
        
        armConfig = new SparkMaxConfig();
        armConfig.idleMode(IdleMode.kBrake);
        armConfig.smartCurrentLimit(Constants.INTAKE_ARM_CURRENT_LIMIT);
        armConfig.encoder.positionConversionFactor(1/Constants.INTAKE_ARM_GEAR_RATIO);
        armConfig.closedLoop.pid(Constants.INTAKE_ARM_P, Constants.INTAKE_ARM_I, Constants.INTAKE_ARM_D);
        armConfig.closedLoop.allowedClosedLoopError(Constants.INTAKE_ARM_ALLOWED_ERROR.in(Rotation), ClosedLoopSlot.kSlot0);
        arm.configure(armConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        armPID = arm.getClosedLoopController();
    }

    public void toggleIntake() {
        if (extended) {
            setIntakeAngle(Degree.zero());
        } else {
            setIntakeAngle(Constants.INTAKE_ARM_EXTENSION_ANGLE);
        }
        extended = !extended;
    }

    public void setIntakeAngle(Angle angle) {
        armPID.setSetpoint(-angle.in(Rotation), ControlType.kPosition);
    }

    public boolean getExtended() {
        return arm.getEncoder().getPosition() < -Constants.INTAKE_ARM_EXTENSION_ANGLE.in(Rotation)/2;
    }

    public void intakeFuel() {
        intake.set(-Constants.INTAKE_SPEED);
    }

    public void spitFuel() {
        intake.set(Constants.INTAKE_SPEED);
    }
    
    public void stop() {
        intake.stopMotor();
    }
}
