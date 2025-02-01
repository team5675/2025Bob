// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Algae;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Algae extends SubsystemBase {
  private static Algae AlgaeManipulator;
  
  private SparkMax wheelsMotor;
  private SparkMax axisMotor;
  private SparkClosedLoopController wheelsPID;
  private SparkClosedLoopController axisPID;
  private DigitalInput proxSensor;

  public Algae() {
  //  wheelsMotor = new SparkMax(AlgaeConstants.wheelsID, MotorType.kBrushless);
  // axisMotor = new SparkMax(AlgaeConstants.axisID, MotorType.kBrushless);
  //  SparkMaxConfig wheelsConfig = new SparkMaxConfig();
  //  wheelsConfig.closedLoop
  //     .p(AlgaeConstants.wheelsP)
  //     .i(AlgaeConstants.wheelsI)
  //     .d(AlgaeConstants.wheelsD);
     // .outputRange(kMinOutput, kMaxOutput);

    // SparkMaxConfig axisConfig = new SparkMaxConfig();
    // axisConfig.closedLoop
    //  .p(AlgaeConstants.axisP)
    //  .i(AlgaeConstants.axisI)
    //  .d(AlgaeConstants.axisD);
  
    // wheelsPID = wheelsMotor.getClosedLoopController();
 
    // axisPID = axisMotor.getClosedLoopController();

    // wheelsMotor.configure(wheelsConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    // axisMotor.configure(axisConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    // proxSensor = new DigitalInput(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // public void AxisOut(){
  //   axisMotor.set(0.2);
  // }

  // public void AxisIn(){
  //   axisMotor.set(-0.2);
  // }

  // public void flywheelSpin(double speed){
  //   wheelsMotor.set(speed);
  // }

  public static Algae getInstance(){
    if (AlgaeManipulator == null){
      AlgaeManipulator = new Algae();
    }
    return AlgaeManipulator;
  }

}

