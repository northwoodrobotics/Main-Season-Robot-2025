package frc.robot.commands;

import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;

public class TeleElevator extends Command {

    private final Elevator elevator;
    private final Joystick joystick;

    public TeleElevator(Elevator subsystem, Joystick joystick) {
        this.elevator = subsystem;
        this.joystick = joystick;
        
        // Declare subsystem dependencies
        addRequirements(elevator);
    }

    @Override
    public void initialize() {
        // No initialization logic required for this command
    }

    @Override
    public void execute() {
        // Check joystick button presses to change setpoints
        if (joystick.getRawButtonPressed(1)) { // Button A
            elevator.setSetPoint(Elevator.setPointA);
        }
        if (joystick.getRawButtonPressed(2)) { // Button B
            elevator.setSetPoint(Elevator.setPointB);
        }
        if (joystick.getRawButtonPressed(3)) { // Button X
            elevator.setSetPoint(Elevator.setPointX);
        }
    }

    @Override
    public void end(boolean interrupted) {
        // Optionally stop the motor when the command ends
        elevator.setSetPoint(0); // This will stop the motor when the command is interrupted
    }

    @Override
    public boolean isFinished() {
        // The command is ongoing since it listens to joystick inputs to change the setpoint
        return false;
    }
}
