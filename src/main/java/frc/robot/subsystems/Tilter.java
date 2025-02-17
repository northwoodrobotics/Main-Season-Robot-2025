package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Tilter extends SubsystemBase {
  private final TalonFX m_tilterMotor = new TalonFX(indexMotorID);

  /** Creates a new Tilter. */
  public Tilter() {
    

    m_tilterMotor.getConfigurator().apply(new TalonFXConfiguration());

    m_tilterMotor.setInverted(true);
    
  }

  
  public void move(double pwr) {
   
    m_tilterMotor.set(pwr);

  }


 
  public void stop() {
    m_tilterMotor.stopMotor();;
  }

 
}