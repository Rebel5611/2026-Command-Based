package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.TowerSubsystem;

public class ShootCommand extends Command {
    private TowerSubsystem towerSubsystem;

    public ShootCommand(TowerSubsystem towerSubsystem) {
        this.towerSubsystem = towerSubsystem;

        addRequirements(towerSubsystem);
    }

    @Override
    public void execute() {
        towerSubsystem.runTower();
    }

    @Override
    public void end(boolean interrupted) {
        towerSubsystem.stop();
    }
}
