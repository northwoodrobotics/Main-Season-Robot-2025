package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CoralShooter extends SubsystemBase {
  private final TalonFX m_shooterMotor = new TalonFX(coralShooterID);

  /** Creates a new Tilter. */
  public CoralShooter() {
    
    TalonFXConfiguration shooterConfig = new TalonFXConfiguration();
    shooterConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // Set inversion here
    m_shooterMotor.getConfigurator().apply(shooterConfig);

   
    
  }

  
  public void move(double pwr) {
   
    m_shooterMotor.set(pwr);

  }


 
  public void stop() {
    m_shooterMotor.stopMotor();;
  }

 
}