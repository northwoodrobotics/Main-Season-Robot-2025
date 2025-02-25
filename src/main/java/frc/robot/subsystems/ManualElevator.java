package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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
   // Create (or get) a Shuffleboard tab, typically done during initialization
ShuffleboardTab elevatorTab = Shuffleboard.getTab("Elevator");

// Add widgets for encoder value and limit switch. The `withWidget` method lets you choose the widget type if desired.
elevatorTab.add("Elevator Encoder", m_elevatorMotor.getPosition().getValueAsDouble())
           .withWidget(BuiltInWidgets.kTextView)
           .withPosition(0, 0)
           .withSize(2, 1);

elevatorTab.add("Bottom Limit Switch", bottomLimitSwitch.get())
           .withWidget(BuiltInWidgets.kBooleanBox)
           .withPosition(0, 1)
           .withSize(2, 1);

    // Optionally, enforce a stop in periodic as well if the limit is reached.
    if (bottomLimitSwitch.get()) {
      stop();
      m_elevatorMotor.setPosition(0); // Reset encoder when limit is active.
    }
  }
 
}