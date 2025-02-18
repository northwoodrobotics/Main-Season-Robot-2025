package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix6.signals.InvertedValue;


public class AlgaeShooter extends SubsystemBase {
    private final TalonFX topAlgaeShooterMotor = new TalonFX(1); // Set appropriate CAN IDs
    private final TalonFX bottomAlgaeShooterMotor = new TalonFX(2);
    private final XboxController controller;

    public AlgaeShooter(XboxController controller) {
        this.controller = controller;

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
        double finalSpeed = controller.getYButton() ? speed * 0.5 : speed;
        topAlgaeShooterMotor.set(finalSpeed);
        bottomAlgaeShooterMotor.set(finalSpeed);
    }

    public void stop() {
        topAlgaeShooterMotor.set(0);
        bottomAlgaeShooterMotor.set(0);
    }
}