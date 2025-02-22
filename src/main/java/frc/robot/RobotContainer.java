// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.LED.LED;
import frc.robot.subsystems.LED.Patterns.RainbowPattern;
import frc.robot.subsystems.LED.Patterns.SolidPattern;
//import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.Climber.Climber;
//import frc.robot.subsystems.Algae.Algae;
//import frc.robot.subsystems.Coral.Coral;
import frc.robot.subsystems.Coral.Coral;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

   private Joystick m_Ipac_2 = 
      new Joystick(OperatorConstants.CommandJoystick);
      JoystickButton print = new JoystickButton(m_Ipac_2, 1);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // driverController.povUp().onTrue(Elevator.getInstance().runOnce(() -> Elevator.getInstance().runElevator()));
    // driverController.povDown().onTrue(Elevator.getInstance().runOnce(() -> Elevator.getInstance().zero()));
    // driverController.rightBumper().whileTrue(Climber.getInstance().runOnce(() -> Climber.getInstance().runClaw()));
    // driverController.a().onTrue(Algae.getInstance().runOnce(() -> Algae.getInstance().AxisOut()));
    //driverController.a().onTrue(Climber.getInstance().runOnce(() -> Climber.getInstance().runClaw()));

    // Algae
    // driverController.y().whileTrue(Algae.getInstance().runOnce(() -> Algae.getInstance().flywheelSpin(-0.2)));
    // driverController.y().whileFalse(Algae.getInstance().runOnce(() -> Algae.getInstance().flywheelSpin(0)));
    // driverController.x().whileTrue(Algae.getInstance().runOnce(() -> Algae.getInstance().flywheelSpin(0.2)));
    // driverController.x().whileFalse(Algae.getInstance().runOnce(() -> Algae.getInstance().flywheelSpin(0)));

    //driverController.leftBumper().whileTrue(new IntakeCommand());
    print.onTrue(Commands.runOnce(() -> System.out.println("IPAC A BUTTON")));

    driverController.b().onTrue(Commands.runOnce(() -> LED.getInstance().setPattern(RainbowPattern.getScrollingRainbow())));
    driverController.x().onTrue(Commands.runOnce(() -> LED.getInstance().setPattern(SolidPattern.getSolidPattern(Color.kCrimson))));
    driverController.a().onTrue(Commands.runOnce(() -> System.out.println(Coral.getInstance().bb1Tripped)));
  }

}
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

