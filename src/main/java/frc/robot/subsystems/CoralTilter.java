package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CoralTilter extends SubsystemBase {
  private final TalonFX m_tilterMotor = new TalonFX(coralTilterMotorID);

  /** Creates a new Tilter. */
  public CoralTilter() {
    
    TalonFXConfiguration tilterConfig = new TalonFXConfiguration();
    tilterConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // Set inversion here
    m_tilterMotor.getConfigurator().apply(tilterConfig);
    
  }

  
  public void move(double pwr) {
   
    m_tilterMotor.set(pwr);

  }


 
  public void stop() {
    m_tilterMotor.stopMotor();;
  }

 
}