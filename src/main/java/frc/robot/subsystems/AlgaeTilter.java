package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;

public class AlgaeTilter extends SubsystemBase {
    private final TalonFX algaeTilterMotor;
    private final PositionVoltage positionRequest = new PositionVoltage(0);

    // Setpoints for the tilter mechanism
    private static final double SETPOINT_A = 0.0;
    private static final double SETPOINT_B = 50.0;
    private static final double SETPOINT_X = 100.0;

    public AlgaeTilter(int algaeTilterMotorID) {
        algaeTilterMotor = new TalonFX(algaeTilterMotorID);
        algaeTilterMotor.setNeutralMode(NeutralModeValue.Brake);

        // Configure PID values (adjust as needed)
        algaeTilterMotor.getConfigurator().apply(new com.ctre.phoenix6.configs.TalonFXConfiguration() {{
            Slot0.kP = 0.1;
            Slot0.kI = 0.0;
            Slot0.kD = 0.0;
            Slot0.kV = 0.0;
        }});
    }

    public void setPosition(double position) {
        algaeTilterMotor.setControl(positionRequest.withPosition(position));
    }

    public void controlWithJoystick(XboxController controller) {
        if (controller.getAButtonPressed()) {
            setPosition(SETPOINT_A);
        } else if (controller.getBButtonPressed()) {
            setPosition(SETPOINT_B);
        } else if (controller.getXButtonPressed()) {
            setPosition(SETPOINT_X);
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}