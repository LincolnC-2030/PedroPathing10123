package org.firstinspires.ftc.teamcode.pedro.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class RGBIndicator {
    private Servo RGB;

    double RAINBOWvariable = 0.0;


    public RGBIndicator(HardwareMap hardwareMap){
        RGB = hardwareMap.get(Servo.class, "indLight");
        RGB.setDirection(Servo.Direction.FORWARD);
        RGB.setPosition(0.0);
    }
    public void setGREEN(){
        RGB.setPosition(0.444);
    }
    public void setRED(){
        RGB.setPosition(0.277);
    }
    public void setBLUE(){
        RGB.setPosition(0.611);
    }
    public void setYELLOW(){
        RGB.setPosition(0.388);
    }
    public void setRAINBOW(){
        RGB.setPosition(RAINBOWvariable);
        RAINBOWvariable += 0.025;
        if (RAINBOWvariable > 1.0){ RAINBOWvariable = 0.0;}
}}
