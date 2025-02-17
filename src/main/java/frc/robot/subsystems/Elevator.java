package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.ControlMode;  // For controlling the motor
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import static frc.robot.Constants.*;

public class Elevator extends SubsystemBase {
    private final TalonFX m_elevatorMotor;  // Using TalonFX motor controller
    private final PIDController pidController;
    private final CommandXboxController joystick;

    // Setpoints for the PID controller
    public static double setPointA = 1000.0;  // Example setpoint 1
    public static double setPointB = 2000.0;  // Example setpoint 2
    public static double setPointX = 3000.0;  // Example setpoint 3

    // Current setpoint
    private double currentSetPoint = setPointA;

    public Elevator(CommandXboxController joystick) {
        m_elevatorMotor = new TalonFX(elevatorMotorID);  // Initialize TalonFX on CAN ID 1, adjust according to your setup
        this.joystick = joystick;

        // Initialize the PID controller (Proportional, Integral, Derivative constants)
        pidController = new PIDController(0.1, 0.0, 0.0);
        pidController.setSetpoint(currentSetPoint);
        pidController.setTolerance(10.0);  // Allowable error for the PID controller (can be adjusted)

        // Optionally, reset the motor's encoder to a known starting position
        m_elevatorMotor.getConfigurator().apply(new TalonFXConfiguration());
    }

    @Override
    public void periodic() {
        // Read joystick button states to switch between setpoints
        if (joystick.a().getAsBoolean()) { // Button A
            setSetPoint(setPointA);
        }
        if (joystick.b().getAsBoolean()) { // Button B
            setSetPoint(setPointB);
        }
        if (joystick.x().getAsBoolean()) { // Button X
            setSetPoint(setPointX);
        }

        // Get the current motor position from the encoder
        double currentPosition = m_elevatorMotor.getPosition().getValueAsDouble();

        // Get the PID output based on the current position
        double output = pidController.calculate(currentPosition);

        // Apply the PID output to the motor
        m_elevatorMotor.set(output); // Control motor speed using PID output
    }

    // Update the current setpoint and reset the PID controller to use the new setpoint
    public void setSetPoint(double setPoint) {
        currentSetPoint = setPoint;
        pidController.setSetpoint(setPoint);
    }

    // Optionally, you can add a method to reset the encoder to zero if needed
    public void resetEncoder() {
        m_elevatorMotor.setPosition(0); // Reset encoder position to 0
    }
}
