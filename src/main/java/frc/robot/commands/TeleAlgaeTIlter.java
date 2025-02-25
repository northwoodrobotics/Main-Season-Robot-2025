package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeTilter;
import edu.wpi.first.wpilibj.XboxController;
import java.util.Set;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class TeleAlgaeTilter extends Command {
    private final AlgaeTilter algaeTilter;

    private final Joystick controller;

    public TeleAlgaeTilter(AlgaeTilter algaeTilter, Joystick controller) {
        this.algaeTilter = algaeTilter;
        this.controller = controller;
    }

    

    @Override
    public void execute() {
        algaeTilter.controlWithJoystick(controller);
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(algaeTilter);
    }

    @Override
    public boolean isFinished() {
        return false; // Keeps running until interrupted
    }

    @Override
    public void end(boolean interrupted) {
        // Optional: Stop motor when command ends
    }
}