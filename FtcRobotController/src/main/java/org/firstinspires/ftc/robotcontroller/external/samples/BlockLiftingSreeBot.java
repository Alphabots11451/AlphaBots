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

@TeleOp(name="TeleOpTank")//Names the OP mode
public class BlockLiftingSreeBot extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    //private DcMotor leftLift = null;
    //private DcMotor rightLift = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initializes the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        //leftLift = hardwareMap.get(DcMotor.class, "left_lift");
        //rightLift = hardwareMap.get(DcMotor.class, "right_lift");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        //leftLift.setDirection(DcMotor.Direction.FORWARD);
        //rightLift.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //rightLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftDrivePower;
            double rightDrivePower;
            //double leftLiftPower = 0;       //Disabled temporarily until lift is ready
            //double rightLiftPower = 0;

            //if (gamepad1.right_bumper) {
                //leftLiftPower = 1;
                //rightLiftPower = 1;
            //}
            //else if (gamepad1.left_bumper) {
                //leftLiftPower = -1;
                //rightLiftPower = -1;
            //}
            //else {
                //leftLiftPower = 0;
                //rightLiftPower = 0;
            //}

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            leftDrivePower = -gamepad1.left_stick_y;
            rightDrivePower  =  -gamepad1.right_stick_y;

            //leftDrivePower    = Range.clip(drive + turn, -1.0, 1.0) ;
            //rightDrivePower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Send calculated power to wheels
            leftDrive.setPower(leftDrivePower);
            rightDrive.setPower(rightDrivePower);
            //leftLift.setPower(leftLiftPower);
            //rightLift.setPower(rightLiftPower);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Drive Motors", "left (%.2f), right (%.2f)", leftDrivePower, rightDrivePower);
            //telemetry.addData("Lift Motors", "left (%.2f), right (%.2f)", leftLiftPower, rightLiftPower);
            telemetry.update();
        }
    }
}



