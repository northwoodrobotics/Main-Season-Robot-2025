package frc.robot.commands;



import frc.robot.subsystems.CoralTilter;
import frc.robot.subsystems.ManualElevator;

import static frc.robot.Constants.elevatorMotorID;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;


public class HumanElevator extends Command {

      private final ManualElevator m_elevatorMotor;
      private DoubleSupplier pwr;

 


  public HumanElevator(ManualElevator manualElevator, DoubleSupplier pwr) {

  
      m_elevatorMotor = manualElevator;
      this.pwr=pwr;
      addRequirements(m_elevatorMotor);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevatorMotor.move(pwr.getAsDouble());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Always return false so the command never ends on it's own. In this project we use the
    // scheduler to end the command when the button is released.
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop the wheels when the command ends.
    m_elevatorMotor.stop();
  }
}