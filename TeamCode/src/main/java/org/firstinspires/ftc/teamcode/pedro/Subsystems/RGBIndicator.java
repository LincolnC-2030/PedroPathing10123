package org.firstinspires.ftc.teamcode.pedro.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import kotlin.random.URandomKt;

public class RGBIndicator {
    private Servo RGB;

    double rainbowVariable = 0.0;


    public RGBIndicator(HardwareMap hardwareMap){
        RGB = hardwareMap.get(Servo.class, "indLight");
        RGB.setDirection(Servo.Direction.FORWARD);
        RGB.setPosition(0.0);
    }
    public void setGREEN(){
        RGB.setPosition(0.444);
    }
    public void setZERO(){
        RGB.setPosition(0.0);
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
        RGB.setPosition(rainbowVariable);
        rainbowVariable += 0.005;
        if (rainbowVariable > 0.722||rainbowVariable < 0.277){ rainbowVariable = Math.random();}
}}