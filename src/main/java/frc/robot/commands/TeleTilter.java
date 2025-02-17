package frc.robot.commands;



import frc.robot.subsystems.Tilter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;


public class TeleTilter extends Command {

      private final Tilter m_tilter;
      private DoubleSupplier pwr;

 


  public TeleTilter(Tilter tilter, DoubleSupplier pwr) {

  
      m_tilter = tilter;
      this.pwr=pwr;
      addRequirements(m_tilter);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_tilter.move(pwr.getAsDouble());
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
    m_tilter.stop();
  }
}