package frc.robot.Subsystems;


import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    private SparkMax intake;
    private SparkMaxConfig intakeConfig;

    private SparkMax arm;
    private SparkClosedLoopController armPID;
    private SparkMaxConfig armConfig;

    private boolean extended = false;
    public IntakeSubsystem() {
        intake = new SparkMax(Constants.INTAKE_CAN_ID, MotorType.kBrushless);

        intakeConfig = new SparkMaxConfig();
        intakeConfig.smartCurrentLimit(Constants.INTAKE_CURRENT_LIMIT);
        intake.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        arm = new SparkMax(Constants.INTAKE_ARM_CAN_ID, MotorType.kBrushless);
        armPID = arm.getClosedLoopController();
        
        armConfig = new SparkMaxConfig();
        armConfig.smartCurrentLimit(Constants.INTAKE_ARM_CURRENT_LIMIT);
        armConfig.closedLoop.pid(Constants.INTAKE_ARM_P, Constants.INTAKE_ARM_I, Constants.INTAKE_ARM_D);
        arm.configure(armConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void toggleIntake() {
        if (extended) {
            setIntakeAngle(Units.Degrees.zero());
        } else {
            setIntakeAngle(Constants.INTAKE_ARM_EXTENSION_ANGLE);
        }
        extended = !extended;
    }

    public void setIntakeAngle(Angle angle) {
        armPID.setSetpoint(angle.in(Units.Revolution) *
                           Constants.INTAKE_ARM_GEAR_RATIO *
                           Constants.NEO_ENCODER_COUNTS_PER_REVOLUTION,
                           ControlType.kPosition);
    }

    public boolean getExtended() {
        return extended;
    }

    public void intakeFuel() {
        intake.set(Constants.INTAKE_SPEED);
    }

    public void spitFuel() {
        intake.set(-Constants.INTAKE_SPEED);
    }
    
    public void stop() {
        intake.stopMotor();
    }
}
