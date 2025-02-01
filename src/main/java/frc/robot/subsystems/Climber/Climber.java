package frc.robot.subsystems.Climber;

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
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class Climber extends SubsystemBase{
    private static Climber instance;

    public static Climber getInstance(){
        if (instance == null){
            instance = new Climber();
        }
        return instance;
    }

    public SparkMaxConfig ClawMotorConfig;
    private SparkClosedLoopController ClawMotorPID;
    
    Climber(){
        SparkMax ClawMotor = new SparkMax(13, MotorType.kBrushless);
        SparkAbsoluteEncoder angleEncoder = ClawMotor.getAbsoluteEncoder();
      ClawMotorConfig.idleMode(IdleMode.kBrake);
      
     //
     
      ClawMotorConfig.closedLoop
      .pid(ClimberConstants.ClawMotorP,ClimberConstants.ClawMotorI, ClimberConstants.ClawMotorD);
     //public  void runClaw(){
//driverController.a.whileTrue(new InstantCommand(( -> ClawMotor.runMotor(0.5) ClawMotor)));
       // }
}
    }
        
