package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    private final TalonFX leftClimberMotor;
    private final TalonFX rightClimberMotor;

    public Climber(int leftClimberMotorID, int rightClimberMotorID) {
        leftClimberMotor = new TalonFX(leftClimberMotorID);
        rightClimberMotor = new TalonFX(rightClimberMotorID);

        TalonFXConfiguration leftConfig = new TalonFXConfiguration();
        TalonFXConfiguration rightConfig = new TalonFXConfiguration();

           // Set both motors to Brake mode
        leftConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        rightConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        rightConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // Set inversion here

        leftClimberMotor.getConfigurator().apply(leftConfig);
        rightClimberMotor.getConfigurator().apply(rightConfig);
    }

    public void setClimberSpeed(double speed) {
        leftClimberMotor.setControl(new DutyCycleOut(speed));
        rightClimberMotor.setControl(new DutyCycleOut(speed));
    }

    public void stopClimber() {
        leftClimberMotor.setControl(new DutyCycleOut(0));
        rightClimberMotor.setControl(new DutyCycleOut(0));
    }
}