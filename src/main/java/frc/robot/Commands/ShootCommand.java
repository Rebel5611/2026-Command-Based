package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.TowerSubsystem;
import frc.robot.Subsystems.TurretSubsystem;

public class ShootCommand extends Command {
    private TowerSubsystem towerSubsystem;
    private TurretSubsystem turretSubsystem;

    public ShootCommand(TowerSubsystem towerSubsystem, TurretSubsystem turretSubsystem) {
        this.towerSubsystem = towerSubsystem;
        this.turretSubsystem = turretSubsystem;

        addRequirements(towerSubsystem);
    }

    @Override
    public void execute() {
        if (turretSubsystem.isLocked()) {
            towerSubsystem.runTower();
        }
    }

    @Override
    public void end(boolean interrupted) {
        towerSubsystem.stop();
    }
}
