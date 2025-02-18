package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    public static final CommandXboxController codriver = new CommandXboxController(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kRightX.value;
    private final int rotationAxis = XboxController.Axis.kLeftX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    /* Co-Driver Buttons */
    private final Trigger elevatorA = codriver.button(XboxController.Button.kLeftBumper.value);
    private final Trigger elevatorB = codriver.button(XboxController.Button.kLeftBumper.value);
    private final Trigger elevatorC = codriver.button(XboxController.Button.kLeftBumper.value);



    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final CoralShooter m_shooter = new CoralShooter();
    private final Tilter m_tilter = new Tilter();
    private final Elevator m_elevator = new Elevator(codriver);
    private final Climber climber = new Climber(24, 25);


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();

 // Example: Binding left bumper to a command

        m_tilter.setDefaultCommand(new TeleTilter(m_tilter, () -> ((codriver.getRawAxis(5)))));
        m_shooter.setDefaultCommand(new TeleCoralShooter(m_shooter, () -> ((codriver.getRawAxis(1)))));
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        elevatorA.onTrue(new InstantCommand(() -> m_elevator.setSetPoint(0.0)));
        elevatorB.onTrue(new InstantCommand(() -> m_elevator.setSetPoint(0.0)));
        elevatorC.onTrue(new InstantCommand(() -> m_elevator.setSetPoint(0.0)));


        // Bind the right and left triggers to control the climber using TeleClimber
        // Right and Left Trigger axis values
        int leftTriggerAxis = XboxController.Axis.kLeftTrigger.value;
        int rightTriggerAxis = XboxController.Axis.kRightTrigger.value;

        // Bind the right and left triggers to control the climber using TeleClimber
        // These will continuously adjust based on the axis values of the triggers
        climber.setDefaultCommand(new TeleClimber(climber, driver, leftTriggerAxis, rightTriggerAxis));
    }
    

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }
}
