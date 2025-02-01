// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.LimitSwitchConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

public class Elevator extends SubsystemBase {
  private static Elevator instance;

  public static Elevator getInstance() {
    if (instance == null) {
      instance = new Elevator();
    }

    return instance;
  }

  public SparkMax motor;
  public SparkMaxConfig motorConfig;
  public SparkFlexConfig flexConfig;
  private SparkClosedLoopController sparkPidController;
  private DigitalInput limitSwitch;

  SparkAbsoluteEncoder angleEncoder;
  RelativeEncoder ticksEncoder;

  public Elevator() {
    motor = new SparkMax(9, MotorType.kBrushless);
    motorConfig = new SparkMaxConfig();
    motorConfig.idleMode(IdleMode.kBrake);
    
    angleEncoder = motor.getAbsoluteEncoder();
    ticksEncoder = motor.getEncoder();
    
    sparkPidController = motor.getClosedLoopController();
    motorConfig.closedLoop
      .pid(ElevatorConstants.motorP,ElevatorConstants.motorI, ElevatorConstants.motorD)
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder);

    motorConfig
      .voltageCompensation(12)
      .idleMode(IdleMode.kBrake);
      
    //pidController = new ProfiledPIDController(ElevatorConstants.motorP, ElevatorConstants.motorI, ElevatorConstants.motorD, null);    

    motor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

    limitSwitch = new DigitalInput(0);
  }

	public void runElevator() {
		this.setTarget(ElevatorConstants.L4_HEIGHT);
	}

	public void setTarget(double ticks) {
    //ticksEncoder.setPosition(ticks);
    sparkPidController.setReference(ticks, ControlType.kPosition);
    SmartDashboard.putNumber("Target in ticks", ticks);
	}

	public void zero() {
    //ticksEncoder.setPosition(0)
    sparkPidController.setReference(1, ControlType.kPosition);
		//sparkPidController.setReference(zeroingSpeed, ControlType.kVelocity);
	}

  @Override
  public void periodic(){
    SmartDashboard.putNumber("Ticks", ticksEncoder.getPosition());
    SmartDashboard.putNumber("Wrist Counts", angleEncoder.getPosition());
    //SmartDashboard.putNumber("Motor vel", sparkPidController.kVelocity);
  }
}
