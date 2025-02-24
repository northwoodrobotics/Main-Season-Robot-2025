package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeTilter;
import edu.wpi.first.wpilibj.XboxController;
import java.util.Set;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.GenericHID;

public class TeleAlgaeTilter extends Command {
    private final AlgaeTilter algaeTilter;

    private final GenericHID controller;

    public TeleAlgaeTilter(AlgaeTilter algaeTilter, GenericHID driver) {
        this.algaeTilter = algaeTilter;
        this.controller = driver;
    }

    

    @Override
    public void execute() {
        algaeTilter.controlWithJoystick(driver);
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