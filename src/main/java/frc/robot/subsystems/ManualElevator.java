package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ManualElevator extends SubsystemBase {
  private final TalonFX m_elevatorMotor = new TalonFX(elevatorMotorID);

  /** Creates a new Tilter. */
  public ManualElevator() {
    
    TalonFXConfiguration tilterConfig = new TalonFXConfiguration();
    tilterConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // Set inversion here
    m_elevatorMotor.getConfigurator().apply(tilterConfig);
    
  }

  
  public void move(double pwr) {
   
    m_elevatorMotor.set(pwr);

  }


 
  public void stop() {
    m_elevatorMotor.stopMotor();;
  }

 
}