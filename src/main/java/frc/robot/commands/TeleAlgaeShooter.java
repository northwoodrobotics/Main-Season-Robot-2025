package frc.robot.commands;

import frc.robot.subsystems.AlgaeShooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class TeleAlgaeShooter extends Command {
    private final AlgaeShooter algaeShooter;
    private final CommandXboxController controller;

    public TeleAlgaeShooter(AlgaeShooter algaeShooter, CommandXboxController controller) {
        this.algaeShooter = algaeShooter;
        this.controller = controller;
        addRequirements(algaeShooter); // Declare subsystem dependencies
    }

    @Override
    public void initialize() {
        // You can add any initialization code if needed
    }

    @Override
    public void execute() {
        // Get the trigger values (range from 0.0 to 1.0)
        double leftTrigger = controller.getLeftTriggerAxis();
        double rightTrigger = controller.getRightTriggerAxis();

        // Combine the trigger inputs to determine the speed (right trigger increases speed, left decreases)
        double speed = rightTrigger - leftTrigger; // Right trigger will give positive, left negative

            // Check if the Y button is pressed to limit speed
        if (controller.y().getAsBoolean()) {
        speed *= 0.25; // Reduce speed by 50%
        }

        // Set the speed to the algae shooter
        algaeShooter.setShooterSpeed(speed);


        // Set the speed to the algae shooter
        algaeShooter.setShooterSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the shooter when the command ends or is interrupted
        algaeShooter.stop();
    }

    @Override
    public boolean isFinished() {
        // Keep the command running until it's explicitly interrupted
        return false;
    }
}