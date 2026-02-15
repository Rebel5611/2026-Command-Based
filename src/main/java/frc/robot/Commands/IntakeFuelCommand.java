package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.IntakeSubsystem;

public class IntakeFuelCommand extends Command {
    private IntakeSubsystem intakeSubsystem;

    public IntakeFuelCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        if (!intakeSubsystem.getExtended()) {
            intakeSubsystem.toggleIntake();
        }

        intakeSubsystem.intakeFuel();
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
    }
}
