package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.ArrayList;

//  import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/*
*****************************************
 Ayan R. Kumbhani

    The program will:

    1. Move in reverse
    2. Rotate 90 degrees counter clockwise
    3. Move forward 
    
****************************************
*/
@Autonomous
public class BasicDrivingAyanK extends LinearOpMode {

    // Initializes ArrayList<DcMotor>
    // ArrayList of each DcMotor
    private final ArrayList<DcMotor> dcMotors = new ArrayList<>();

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
        
        
        final int REVERSE_AMOUNT = -2000;
        //I do not know how the robot is exactly structured so i'll assume that half of the 180 cc is 90 cc
        final int ROTATION_AMOUNT = 250;
        final int FORWARD_AMOUNT = 3000;
        
        // Ayan K Program:
        // Reverse
        // 90 degree 
        // Move
        boolean isReverse0 = true;
        boolean is90CCRotationPeriod = false;
        boolean isForwardPeriod3 = false;

        // reset encoder and set initial reverse target amount for all four wheels
        for (DcMotor motor: dcMotors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(("0" + motor.getDeviceName()).indexOf("Left")*REVERSE_AMOUNT);
        }  

        // Do this while the program is running
        while(opModeIsActive()) {

            // Logs Status: Running if program is working correctly
            telemetry.addData("Status:", "Running");
            telemetry.update();
           
            //AyanK Reverse Code
            if(isReverse0) {
               
                // Tell all motors that they should "run to position" (the distance was set by REVERSE_AMOUNT)
                for (DcMotor motor : dcMotors) {
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

                // Turns on power for all motors at 1/3 of the maximum power
                for (DcMotor motor : dcMotors) {
                    motor.setPower(1.0 / 3.0);
                }
                
                if(!dcMotors.get(0).isBusy()) {
                    isReverse0 = false;
                    is90CCRotationPeriod = true;                    
                    // turn off all motors just in case
                    for (DcMotor motor: dcMotors) {
                        motor.setPower(0.0);
                    }
                    for (DcMotor motor: dcMotors) {
                        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        // right motors are set to go forward
                        //    (negative amount which then flipped because motors are backwards)
                        // left motors are set to go backwards (negative amount)                        
                        motor.setTargetPosition(-ROTATION_AMOUNT);
                        
                    }
                }
            }
            //AyanK 90CCRotate Code
            else if(is90CCRotationPeriod) {

                // Tell all motors that they should "run to position" (the distance was set by -ROTATION_AMOUNT)
                for (DcMotor motor: dcMotors) {
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }                
                
                // Turns on power for all motors at 1/3 of the maximum power
                for (DcMotor motor : dcMotors) {
                    motor.setPower(1.0 / 3.0);
                }
                
                if(!dcMotors.get(0).isBusy()) {
                    is90CCRotationPeriod = false;
                    isForwardPeriod3 = true;
                    // turn off all motors just in case
                    for (DcMotor motor: dcMotors) {
                        motor.setPower(0.0);
                    }
                    for (DcMotor motor: dcMotors) {
                        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        motor.setTargetPosition(("0" + motor.getDeviceName()).indexOf("Left")*FORWARD_AMOUNT);                        
                    }
                }
            }
            //AyanK Forward Code
            else if(isForwardPeriod3) {
                
                // Sets all motor RunModes to make robot until the target position is met
                for (DcMotor motor : dcMotors) {
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

                // Turns on power for all motors at 1/3 of the maximum power
                for (DcMotor motor : dcMotors) {
                    motor.setPower(1.0 / 3.0);
                }
                
                if(!dcMotors.get(0).isBusy()) {
                    isForwardPeriod3 = false;
                    // turn off all motors and reset encoders for final step
                    for (DcMotor motor: dcMotors) {
                        motor.setPower(0.0);
                        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    }                    
                }
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

