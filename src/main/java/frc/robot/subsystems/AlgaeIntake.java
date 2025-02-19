package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AlgaeIntake extends SubsystemBase {
    private final TalonSRX topIntakeMotor = new TalonSRX(topAlgaeIntakeID); // Set appropriate CAN ID
    private final TalonSRX bottomIntakeMotor = new TalonSRX(bottomAlgaeIntakeID); // Set appropriate CAN ID

    public AlgaeIntake() {
        // Configure motors
        topIntakeMotor.configFactoryDefault();
        bottomIntakeMotor.configFactoryDefault();
        
        topIntakeMotor.setInverted(InvertType.InvertMotorOutput); // Adjust inversion as needed
       //bottomIntakeMotor.setInverted(InvertType.InvertMotorOutput); // Adjust inversion as needed
    }

    public void setIntakeSpeed(double speed) {
        topIntakeMotor.set(ControlMode.PercentOutput, speed);
        bottomIntakeMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        topIntakeMotor.set(ControlMode.PercentOutput, 0);
        bottomIntakeMotor.set(ControlMode.PercentOutput, 0);
    }
}
