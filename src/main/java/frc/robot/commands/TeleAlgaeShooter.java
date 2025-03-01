package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.AlgaeShooter;
import frc.robot.subsystems.AlgaeIntake;
import edu.wpi.first.wpilibj.Joystick;

public class TeleAlgaeShooter extends Command {
    private final AlgaeShooter shooter;
    private final AlgaeIntake intake;
    private final Joystick driver;
    private final int highSpeedButton;
    private final int lowSpeedButton;
    private boolean isRunning = false;

    public TeleAlgaeShooter(AlgaeShooter shooter, AlgaeIntake intake, Joystick driver, int highSpeedButton, int lowSpeedButton) {
        this.shooter = shooter;
        this.intake = intake;
        this.driver = driver;
        this.highSpeedButton = highSpeedButton;
        this.lowSpeedButton = lowSpeedButton;
        addRequirements(shooter, intake);
    }

    @Override
    public void initialize() {
        isRunning = true;
        if (driver.getRawButton(highSpeedButton)) {
            new SequentialCommandGroup(
                new Command() {
                    @Override
                    public void initialize() {
                        shooter.setShooterSpeed(1.0);
                    }
                    @Override
                    public boolean isFinished() {
                        return true;
                    }
                },
                new WaitCommand(0.5),
                new Command() {
                    @Override
                    public void initialize() {
                        intake.setIntakeSpeed(1.0);
                    }
                    @Override
                    public boolean isFinished() {
                        return true;
                    }
                }
            ).schedule();
        } else if (driver.getRawButton(lowSpeedButton)) {
            new SequentialCommandGroup(
                new Command() {
                    @Override
                    public void initialize() {
                        shooter.setShooterSpeed(0.25);
                    }
                    @Override
                    public boolean isFinished() {
                        return true;
                    }
                },
                new WaitCommand(0.5),
                new Command() {
                    @Override
                    public void initialize() {
                        intake.setIntakeSpeed(0.25);
                    }
                    @Override
                    public boolean isFinished() {
                        return true;
                    }
                }
            ).schedule();
        }
    }

    @Override
    public void execute() {
        if (!driver.getRawButton(highSpeedButton) && !driver.getRawButton(lowSpeedButton)) {
            isRunning = false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return !isRunning;
    }
}
