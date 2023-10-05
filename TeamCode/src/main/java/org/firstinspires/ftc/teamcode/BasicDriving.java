package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.ArrayList;

//  import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/*
*****************************************
 Matthew A. Padilla, Chipwrecked Vikings Coding Team Captain: 9/30/23
    Program to Move Robot Forward to position 200(based by DcMotor Encoders)
    180 degree counter-clockwise rotation and moving forward a second time
    To be added by remaining members of the Coding Team
****************************************
*/
@Autonomous
public class BasicDriving extends LinearOpMode {

    // Initializes ArrayList<DcMotor>
    // ArrayList of each DcMotor
    private ArrayList<DcMotor> dcMotors = new ArrayList<DcMotor>();

    // What the robot does while running this OpMode
    @Override
    public void runOpMode() {

        waitForStart();

        // Initializes all 4 DcMotors; named by top-down view with front of robot facing away
        dcMotors.add(hardwareMap.get(DcMotor.class, "RightRearDcMotor0"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "LeftRearDcMotor1"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "LeftForeDcMotor2"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "RightForeDcMotor3"));

        // Angular Position encoders should count to for each movement Period
        final int FORWARD_AMOUNT0 = 2000;
        final int ROTATION_AMOUNT = 500;
        final int FORWARD_AMOUNT1 = 1000;

        // Resets angular position of DcMotor Encoders and sets target angular position
        for (int i = 0; i < 4; i++) {
            dcMotors.get(i).setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            if(i == 0 || i == 3)
                dcMotors.get(i).setTargetPosition(-FORWARD_AMOUNT0);
            else
                dcMotors.get(i).setTargetPosition(FORWARD_AMOUNT0);
        }

        // The three main movement Periods for this Program:
        // Moving forward,
        // 180 degree Counter-Clockwise(CC) rotation,
        // and moving forward again
        boolean isForwardPeriod0 = true,
                is180CCRotationPeriod = false,
                isForwardPeriod1 = false;

        // Do this while the program is running
        while(opModeIsActive()) {

            // Logs Status: Running if program is working correctly
            telemetry.addData("Status:", "Running");
            telemetry.update();

            // Moves Robot forward with an encoded Angular position of 2000
            if(isForwardPeriod0) {

                // Sets all motor RunModes to make robot until the target position is met
                for (DcMotor motor : dcMotors) {
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

                // Turns on power for all motors at 1/3 of the maximum power
                for (DcMotor motor : dcMotors) {
                    motor.setPower(1.0 / 3.0);
                }

                // If the motors are no longer moving then switches to 180CCRotationPeriod
                // Resets encoders and sets new target position
                if(!dcMotors.get(0).isBusy()) {
                    isForwardPeriod0 = false;
                    is180CCRotationPeriod = true;
                    for (DcMotor motor: dcMotors) {
                        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        motor.setTargetPosition(ROTATION_AMOUNT);
                    }
                }

            }

            // Rotates robot 180 degrees with an encoded Angular position of 500
            // Is this correct? Find out
            else if(is180CCRotationPeriod) {
                // Add Code Here
            }

            // Moves Robot forward with an encoded Angular position of 1000
            else if(isForwardPeriod1) {
                // Add Code Here
            }

            // Stops program if every other movement Period is finished
            else
                this.requestOpModeStop();


            // Stops Program if it has been running for 30 seconds or more
            if(this.getRuntime() >= 30 )
                this.requestOpModeStop();
        }

    }
}

