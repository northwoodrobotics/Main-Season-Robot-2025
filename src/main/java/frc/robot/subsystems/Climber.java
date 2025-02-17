package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    private final TalonFX leftClimberMotor;
    private final TalonFX rightClimberMotor;

    public Climber(int leftMotorID, int rightMotorID) {
        leftClimberMotor = new TalonFX(leftMotorID);
        rightClimberMotor = new TalonFX(rightMotorID);
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