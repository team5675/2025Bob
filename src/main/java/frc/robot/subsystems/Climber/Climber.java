package frc.robot.subsystems.Climber;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
    SparkMax ClawMotor = new SparkMax(13, MotorType.kBrushless);
        SparkAbsoluteEncoder angleEncoder = ClawMotor.getAbsoluteEncoder();
    Climber(){
        
      ClawMotorConfig.idleMode(IdleMode.kBrake);
      SparkMaxConfig ClawmotorConfig = new SparkMaxConfig();
      ClawmotorConfig.smartCurrentLimit(15);
      ClawMotor.configure(ClawmotorConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
     //
     
      ClawMotorConfig.closedLoop
      .pid(ClimberConstants.ClawMotorP,ClimberConstants.ClawMotorI, ClimberConstants.ClawMotorD);
     
}
public  void runClaw(){
ClawMotor.set(0.5);
        }
}
        