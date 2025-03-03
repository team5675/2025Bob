// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Algae;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Elevator.ElevatorConstants;

public class Algae extends SubsystemBase {
  private static Algae AlgaeManipulator;
  
  private SparkMax wheelsMotor;
  private SparkMax axisMotor;
  private SparkClosedLoopController axisPID;
  private RelativeEncoder ticksEncoder;
  private DigitalInput axisHardStop;
  Trigger axisHardStopTripped;
 

  public Algae() {
    wheelsMotor = new SparkMax(AlgaeConstants.wheelsID, MotorType.kBrushless);
    SparkMaxConfig wheelsConfig = new SparkMaxConfig();
    wheelsConfig.smartCurrentLimit(15);
    
    // axisMotor = new SparkMax(AlgaeConstants.axisID, MotorType.kBrushless);
    // ticksEncoder = axisMotor.getEncoder();

    // SparkMaxConfig axisConfig = new SparkMaxConfig();
    // axisConfig.smartCurrentLimit(15);

    // axisPID = axisMotor.getClosedLoopController();
    // axisConfig.closedLoop
    // .pid(AlgaeConstants.axisP,AlgaeConstants.axisI,AlgaeConstants.axisD)
    // .feedbackSensor(FeedbackSensor.kPrimaryEncoder);
  
    // axisMotor.configure(axisConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    // axisHardStop = new DigitalInput(1);
    // axisHardStopTripped = new Trigger(axisHardStop::get);
    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("Axis Ticks", ticksEncoder.getPosition());
  }

  public void AxisOut(){
    System.out.println("Out");
    axisPID.setReference(200, ControlType.kPosition);
  }

  public void AxisIn(){
    System.out.println("in");
    axisPID.setReference(0, ControlType.kPosition);
    //axisHardStopTripped.onTrue(Commands.runOnce(()->ticksEncoder.setPosition(0)));
  
  }

  public void flywheelSpin(double speed){
    System.out.println("Spinning Wheel!");
    wheelsMotor.set(speed);
  }

  public static Algae getInstance(){
    if (AlgaeManipulator == null){
      AlgaeManipulator = new Algae();
    }
    return AlgaeManipulator;
  }

}

