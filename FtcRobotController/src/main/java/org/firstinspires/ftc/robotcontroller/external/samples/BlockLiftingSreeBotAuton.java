//WE ARE ON THE RED TEAM

package org.firstinspires.ftc.robotcontroller.external.samples;

/**
 * Created by Sethu Senthil on 11/9/17.
 */

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


public class BlockLiftingSreeBotAuton extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor leftLift = null;
    private DcMotor rightLift = null;
    private ColorSensor colorSensor = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftLift = hardwareMap.get(DcMotor.class, "left_lift");
        rightLift = hardwareMap.get(DcMotor.class, "right_lift");
        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftLift.setDirection(DcMotor.Direction.FORWARD);
        rightLift.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftDrivePower = 0;
            double rightDrivePower = 0;
            double leftLiftPower = 0;
            double rightLiftPower = 0;

            driveFor(-1, 2000);    //drives for time at speed
            driveStop();                       //stops driving

            //WE ARE ON THE RED TEAM
            if (colorSensor.blue() > colorSensor.red()) {

            }












            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Drive Motors", "left (%.2f), right (%.2f)", leftDrivePower, rightDrivePower);
            telemetry.addData("Lift Motors", "left (%.2f), right (%.2f)", leftLiftPower, rightLiftPower);
            telemetry.addData("Color", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.update();
        }
    }

    public void setDrivePower(int x, int y) {   //give a speed for x and y (left and right)
        leftDrive.setPower(x);
        rightDrive.setPower(y);
    }
    public void driveFor(int speed, int time) {    //give a speed and time (in milliseconds), the speed goes for both motors
        setDrivePower(speed, speed);
        sleep(time);
        driveStop();
    }
    public void driveStop() {
        setDrivePower(0, 0);
    }
    public void turn(String dir) { //90 degree turns for left and right, and 180 degree for around
        if (dir.equals("right")) {   //change the timings to make it exactly 90 degrees and 180 degrees
            setDrivePower(1, -1);
            sleep(2000);
        }
        else if (dir.equals("left")) {
            setDrivePower(-1, 1);
            sleep(2000);
        }
        else if (dir.equals("around")) {
            setDrivePower(1, -1);
            sleep(4000);
        }
    }
}

