package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Drivetrain;

public class Drive extends Command {
    private Drivetrain drivetrain;
    private DoubleSupplier x;
    private DoubleSupplier y;
    private DoubleSupplier rot;
    private BooleanSupplier fieldOriented;

    public Drive(Drivetrain drivetrain, DoubleSupplier x, DoubleSupplier y, DoubleSupplier rot, BooleanSupplier fieldOriented) {
        this.drivetrain = drivetrain;
        this.x = x;
        this.y = y;
        this.rot = rot;
        this.fieldOriented = fieldOriented;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.drive(x.getAsDouble(), y.getAsDouble(), rot.getAsDouble(), fieldOriented.getAsBoolean());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}
