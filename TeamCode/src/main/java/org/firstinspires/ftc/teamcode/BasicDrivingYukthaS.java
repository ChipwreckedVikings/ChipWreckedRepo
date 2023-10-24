package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;

/* importing the hardware, linearopmode and autonomuous  */

@Autonomous
public class BasicDrivingYukthaS extends LinearOpMode {

    private final ArrayList<DcMotor> dcMotors = new ArrayList<>();

    @Override
    public void runOpMode() {

        /*waitForStart();

        // adding motors so they move. rear motors are the back, fore motors move forward
        dcMotors.add(hardwareMap.get(DcMotor.class, "RightRearDcMotor0"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "LeftRearDcMotor1"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "LeftForeDcMotor2"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "RightForeDcMotor3"));

        // How much the wheels rotate when moving foward and rotating
        final int FORWARD_AMOUNT = 2000;
        final int TURN_LEFT_AMOUNT = 500;
        final int TURN_RIGHT_AMOUNT = 500;
        final int BACKWARD_AMOUNT = -2000;

        boolean isForwardPeriod = true;
        boolean isBackwardPeriod = false;
        boolean isRotate;
        
        // this is the default settings for the robot
        // since the right wheels turn the opposite way, this sets the right side of the
        // robot to turn counter-clockwise so the wheels dont go against each other
        // setting the power for the motors
        for (DcMotor motor : dcMotors) {
            motor.setPower(1.0 / 3.0);
            motor.setTargetPosition(-("0" + motor.getDeviceName()).indexOf("Right")*FORWARD_AMOUNT);
        }

        
        while(opModeIsActive()) {

            // Logs Status: Running if program is working correctly
            telemetry.addData("Status:", "Running");
            telemetry.update();

            if(isForwardPeriod) {
                // then tells the motor to run to position
                for (DcMotor motor : dcMotors) {
                    motor.setTargetPosition(FORWARD_AMOUNT);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            
                // if the robot is no longer moving, it will make a 3-point turn
                // the foward period is set to false and TURN_LEFT_AMOUNT is set to true
                if(!dcMotors.get(0).isBusy()) {
                    isForwardPeriod = false; 

                    // the robots speed is set to 1, and its direction is set to left
                    // so it turns left for the amount its set for
                    for (DcMotor motor : dcMotors) {

                        // turning left
                        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        motor.setTargetPosition(TURN_LEFT_AMOUNT);
                        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        
                        // the direction is set to reverse, so when the target posotion is set to 2000, the robot goes backwards
                        motor.setDirection(DcMotorSimple.Direction.valueOf("REVERSE"));
                        motor.setTargetPosition(BACKWARD_AMOUNT);
                        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        // turns left for the 3-point turn
                        motor.setTargetPosition(TURN_LEFT_AMOUNT); 
                        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        // goes foward after doing 3-point turn
                        motor.setDirection(DcMotorSimple.Direction.valueOf("FORWARD")); 
                        motor.setTargetPosition(FORWARD_AMOUNT);
                        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    }  
                }
            }

            // if the robot is not going forward, it will go backward
            else if(isBackwardPeriod) {
                isForwardPeriod = true;

                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motor.setDirection(DcMotorSimple.Direction.valueOf("REVERSE"));
                motor.setTargetPosition(BACKWARD_AMOUNT);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
              
                // turns right after moving backwards
                motor.setTargetPosition(TURN_RIGHT_AMOUNT); 
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // goes foward for set amount after turning right
                motor.setDirection(DcMotorSimple.Direction.valueOf("FORWARD")); 
                motor.setTargetPosition(FORWARD_AMOUNT);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } 
           
            else if(isRotate) {

                // robot is turning left
                isForwardPeriod = true;
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motor.setDirection(DcMotorSimple.Direction.valueOf("FORWARD"));
                motor.setTargetPosition(TURN_LEFT_AMOUNT);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                // turns left again for a u turn
                motor.setTargetPosition(TURN_LEFT_AMOUNT); 
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // goes foward after doing u turn
                motor.setDirection(DcMotorSimple.Direction.valueOf("FORWARD")); 
                motor.setTargetPosition(FORWARD_AMOUNT);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } 

            // robot stops if it runs for more than 30 seconds
            else {
                if(this.getRuntime() >= 30 ) {
                    this.requestOpModeStop();
                } */
    }
}
