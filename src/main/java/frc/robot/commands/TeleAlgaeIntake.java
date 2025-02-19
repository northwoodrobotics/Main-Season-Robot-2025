package frc.robot.commands;

import frc.robot.subsystems.AlgaeIntake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class TeleAlgaeIntake extends Command {
    private final AlgaeIntake algaeIntake;
    private final CommandXboxController codriver;

    public TeleAlgaeIntake(AlgaeIntake algaeIntake, CommandXboxController codriver) {
        this.algaeIntake = algaeIntake;
        this.codriver = codriver;
        addRequirements(algaeIntake);
    }

    @Override
    public void execute() {
        double intakeSpeed = codriver.getLeftY(); // Example control using left stick Y-axis
        algaeIntake.setIntakeSpeed(intakeSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        algaeIntake.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
