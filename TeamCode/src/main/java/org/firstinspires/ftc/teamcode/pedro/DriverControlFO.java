package org.firstinspires.ftc.teamcode.pedro;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.hardware.motors.GoBILDA5201Series;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Light;
import com.qualcomm.robotcore.hardware.LightBlinker;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptRevLED;
import org.firstinspires.ftc.teamcode.pedro.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.pedro.Subsystems.RGBIndicator;
import org.firstinspires.ftc.teamcode.pedro.Subsystems.Shooter;

@TeleOp(name = "Driver Control Field Oriented")
public class DriverControlFO extends OpMode {
    DriveTrain driveTrain;
    private enum DriveState{
        MANUAL,
        AUTO
    }
    private DriveState currentState = DriveState.MANUAL;
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();
    private Pose currentPose = poseFactory.of(0.0,0.0,0.0);
    private Pose shootingPose = poseFactory.of(0.0,0.0,0.0);
    private RGBIndicator rgb;

    private Shooter shooter;


    @Override
    public void init() {
        rgb = new RGBIndicator(hardwareMap);
        rgb.setZERO();
        driveTrain = new DriveTrain(hardwareMap, follower);
        follower = Constants.create(hardwareMap);
        follower.setPose(currentPose);
        follower.update();
        shooter = new Shooter(hardwareMap);
//        driveTrain.resetIMU();
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        rgb.setRAINBOW();
        follower.update();
        switch (currentState) {
            case MANUAL:
                driveTrain.drive(gamepad1);
                if (gamepad1.aWasPressed()){
                    if (gamepad1.left_trigger_pressed){
                        shootingPose = follower.pose();
                    }
                    else{
                        currentPose = follower.pose();
                        Path pathToShooter = line(currentPose,shootingPose).linear(currentPose,shootingPose);
                        follower.follow(pathToShooter);
                        currentState = DriveState.AUTO;
                    }
                }
                if (gamepad2.aWasPressed()){
                    shooter.setVelocity(2000);
                }
                if (gamepad2.bWasPressed()){
                    shooter.stop();
                }
                break;
            case AUTO:
                gamepad1.rumble(25);
                rgb.setGREEN();
                boolean driverInterrupt = Math.abs(gamepad1.left_stick_y) > 0.1 ||
                        Math.abs(gamepad1.left_stick_x) > 0.1 ||
                        Math.abs(gamepad1.right_stick_x) > 0.1 ||
                        Math.abs(gamepad1.right_stick_y) > 0.1;
                if (!follower.isBusy() || driverInterrupt){
                    follower.stop();
                    currentState = DriveState.MANUAL;
                    rgb.setZERO();
                }
                break;
        }

        telemetry.addData("Drive State:", currentState);
        telemetry.addData("yawAngle:",driveTrain.sendYawAngle());
        telemetry.addData("UpPressed?:", driveTrain.sendDpadUpWasPressed());
        telemetry.update();

    }
}