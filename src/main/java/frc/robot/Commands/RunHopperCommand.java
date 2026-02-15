package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.HopperSubsystem;

public class RunHopperCommand extends Command {
    private HopperSubsystem hopperSubsystem;

    public RunHopperCommand(HopperSubsystem hopperSubsystem) {
        this.hopperSubsystem = hopperSubsystem;

        addRequirements(hopperSubsystem);
    }

    @Override
    public void execute() {
        hopperSubsystem.runHopper();
    }

    @Override
    public void end(boolean interrupted) {
        hopperSubsystem.stop();
    }
}
