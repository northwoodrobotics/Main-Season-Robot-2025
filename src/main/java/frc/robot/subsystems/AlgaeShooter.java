package frc.robot.subsystems;
import static frc.robot.Constants.*;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.RobotContainer;

public class AlgaeShooter extends SubsystemBase {
    private final TalonFX topAlgaeShooterMotor = new TalonFX(topAlgaeShooterID); // Set appropriate CAN IDs
    private final TalonFX bottomAlgaeShooterMotor = new TalonFX(bottomAlgaeShooterID);
    private final GenericHID xbox;

    public AlgaeShooter() {
        xbox = RobotContainer.codriver.getHID();
        // Create configuration for TalonFX
        TalonFXConfiguration topConfig = new TalonFXConfiguration();
        TalonFXConfiguration bottomConfig = new TalonFXConfiguration();

        // Set inversion for bottom motor
        bottomConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // Set inversion here

        // Apply configurations
        topAlgaeShooterMotor.getConfigurator().apply(topConfig);
        bottomAlgaeShooterMotor.getConfigurator().apply(bottomConfig);
    }

    public void setShooterSpeed(double speed) {
        double finalSpeed = xbox.getRawButton(4) ? speed * 0.5 : speed;
        topAlgaeShooterMotor.set(finalSpeed);
        bottomAlgaeShooterMotor.set(finalSpeed);
    }

    public void stop() {
        topAlgaeShooterMotor.set(0);
        bottomAlgaeShooterMotor.set(0);
    }
}