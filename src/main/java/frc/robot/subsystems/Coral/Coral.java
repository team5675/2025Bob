// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Coral;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Coral extends SubsystemBase {
  public static Coral Coral;
  SparkMax motor;
  DigitalInput beamBreak1;
  DigitalInput beamBreak2;
  Trigger bb1Tripped;
  Trigger bb2Tripped;

  public Coral() {
    motor = new SparkMax(CoralConstants.motorID, MotorType.kBrushless);
    
    SparkMaxConfig motorConfig = new SparkMaxConfig();
    motorConfig.smartCurrentLimit(15);
    motor.configure(motorConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    beamBreak1 = new DigitalInput(0);
    beamBreak2 = new DigitalInput(1);

    bb1Tripped = new Trigger(beamBreak1::get);
    bb2Tripped = new Trigger(beamBreak2::get);
  }

public void Intake(){
  motor.set(1);

  bb1Tripped.onTrue(Commands.runOnce(()->motor.set(0.75)));
  bb1Tripped.onTrue(Commands.runOnce(()->motor.set(0.5)));
  bb2Tripped.onTrue(Commands.run(()->
  bb1Tripped.onFalse(Commands.runOnce(()->motor.set(0)))));
}



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static Coral getInstance(){
    if (Coral == null){
      Coral = new Coral();
    }
    return Coral;
  }

}
