package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;

public class TeleClimber extends Command {
    private final Climber climber;
    private final Joystick joystick;
    private final int leftTriggerAxis;
    private final int rightTriggerAxis;

    public TeleClimber(Climber climber, Joystick joystick, int leftTriggerAxis, int rightTriggerAxis) {
        this.climber = climber;
        this.joystick = joystick;
        this.leftTriggerAxis = leftTriggerAxis;
        this.rightTriggerAxis = rightTriggerAxis;
    }

    @Override
    public void execute() {
        // Get the axis values of the triggers (ranging from 0 to 1.0)
        double leftTrigger = joystick.getRawAxis(leftTriggerAxis);
        double rightTrigger = joystick.getRawAxis(rightTriggerAxis);

        // Calculate the climber speed based on the difference between the triggers
        double speed = rightTrigger - leftTrigger;
        climber.setClimberSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopClimber();
    }

    @Override
    public boolean isFinished() {
        return false; // This command will continuously execute
    }
}
