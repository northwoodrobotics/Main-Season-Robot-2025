package frc.robot.commands;



import frc.robot.subsystems.CoralShooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;


public class TeleCoralShooter extends Command {

      private final CoralShooter m_shooter;
      private DoubleSupplier pwr;

 


  public TeleCoralShooter(CoralShooter shooter, DoubleSupplier pwr) {

  
      m_shooter = shooter;
      this.pwr=pwr;
      addRequirements(m_shooter);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.move(pwr.getAsDouble());
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
    m_shooter.stop();
  }
}