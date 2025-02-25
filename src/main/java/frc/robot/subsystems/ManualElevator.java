package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ManualElevator extends SubsystemBase {
  private final TalonFX m_elevatorMotor = new TalonFX(elevatorMotorID);
    private final DigitalInput bottomLimitSwitch = new DigitalInput(bottomLimitSwitchChannel);

  /** Creates a new Tilter. */
  public ManualElevator() {
    
    TalonFXConfiguration elevatorConfig = new TalonFXConfiguration();
    elevatorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // Set inversion here
    m_elevatorMotor.getConfigurator().apply(elevatorConfig);
    
  }

  
  public void move(double pwr) {
   
      // Check the limit switch state. 
    // Assuming that get() returns true when the switch is pressed.
    if (bottomLimitSwitch.get() && pwr < 0) { // pwr < 0 to indicate downward movement
        // Limit reached, so stop the motor and reset encoder.
        stop();
        m_elevatorMotor.setPosition(0); // Reset the encoder to zero
      } else {
        m_elevatorMotor.set(pwr);
      }

    }
 
  public void stop() {
    m_elevatorMotor.stopMotor();;
  }


  @Override
  public void periodic() {
    // Publish encoder value and limit switch state to the SmartDashboard.
    double encoderPosition = m_elevatorMotor.getPosition().getValueAsDouble();
    SmartDashboard.putNumber("Elevator Encoder", encoderPosition);
    SmartDashboard.putBoolean("Bottom Limit Switch", bottomLimitSwitch.get());

    // Optionally, enforce a stop in periodic as well if the limit is reached.
    if (bottomLimitSwitch.get()) {
      stop();
      m_elevatorMotor.setPosition(0); // Reset encoder when limit is active.
    }
  }
 
}