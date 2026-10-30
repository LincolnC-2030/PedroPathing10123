package org.firstinspires.ftc.teamcode.pedro.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Shooter {
    private DcMotorEx shooterMotorLeft;
    private DcMotorEx shooterMotorRight;
    public static double P = 10.0;
    public static double I = 3.0;
    public static double D = 0.0;
    public static double F = 12.0;

    public Shooter(HardwareMap hardwareMap){
        shooterMotorLeft = hardwareMap.get(DcMotorEx.class, "shooterMotorLeft");
        shooterMotorRight = hardwareMap.get(DcMotorEx.class, "shooterMotorRight");

        shooterMotorLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotorRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotorLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        shooterMotorRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        shooterMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterMotorRight.setDirection(DcMotorSimple.Direction.FORWARD);


        shooterMotorLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        shooterMotorRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);


        setPIDF(P, I, D, F);

        }

    public void setVelocity(double targetTicksPerSec){
        shooterMotorLeft.setVelocity(targetTicksPerSec);
//        shooterMotorRight.setVelocity(targetTicksPerSec);
}
    public void stop(){
        shooterMotorLeft.setVelocity(0);
        shooterMotorRight.setVelocity(0);

    }

    public double getVelocityLeft(){
        return shooterMotorLeft.getVelocity();
    }
    public double getVelocityRight(){
        return shooterMotorRight.getVelocity();
    }

    public void setPIDF(double P, double I, double D, double F){
        shooterMotorLeft.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(P, I, D, F)
        );
        shooterMotorRight.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(P, I, D, F)
        );
    }
}
