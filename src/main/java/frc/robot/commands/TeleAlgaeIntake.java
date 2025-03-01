package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeIntake;
import frc.robot.subsystems.AlgaeShooter;

public class TeleAlgaeIntake extends Command {
    private final AlgaeIntake intake;
    private final AlgaeShooter shooter;
    private final double speed;

    public TeleAlgaeIntake(AlgaeIntake intake, AlgaeShooter shooter, double speed) {
        this.intake = intake;
        this.shooter = shooter;
        this.speed = speed;
        addRequirements(intake, shooter);
    }

    @Override
    public void initialize() {
        intake.setIntakeSpeed(speed);
        shooter.setShooterSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
        shooter.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // Runs until the button is released
    }
}