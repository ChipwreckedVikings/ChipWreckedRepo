package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Movement extends LinearOpMode{
    // Array of DcMotors
    private DcMotor[] dcMotors;
    private final int TICKS_PER_REV = 560;
    private final double GEAR_RATIO = 2;
    private final double WHEEL_DIAMETER = 0.0889;
    private final double WHEEL_LENGTH = 0.04572;

    // motorSpeed is from [-1,1]
    private double motorSpeed;
    // Constructor for Movement class DcMotors must be created with hardwareMap.get(DcMotor, String)
    public Movement(DcMotor rightRear, DcMotor leftRear, DcMotor leftFore, DcMotor rightFore) {
        dcMotors[0] = rightRear;
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dcMotors[1] = leftRear;
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dcMotors[2] = leftFore;
        leftFore.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dcMotors[3] = rightFore;
        rightFore.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorSpeed = 0;
    }

    // assumed no motors
    public Movement() {
        dcMotors = null;
        motorSpeed = 0;
    }

    public double getSpeed() {
        return this.motorSpeed;
    }

    public void setSpeed(double speed) {
        if(Math.abs(speed) > 1.0)
            motorSpeed = speed/Math.abs(speed);
        else
            motorSpeed = speed;
    }

    // Moves the robot forward the specified distance in revolutions
    // if resetEncoders is true then stops and resets encoders
    // otherwise encoders keep position after moving forward
    // Issue: currently distance refers to rotations of the wheels not distance the robot moves
    // Measurements needed for implementation
    public void move(double revolutions, boolean resetEncoders) {

        double distance = Math.PI*WHEEL_DIAMETER*TICKS_PER_REV*GEAR_RATIO*revolutions;
        // Sets target position for each DcMotor
        // if the motor name does not include "Right" then String.indexOf(String) will return -1
        for(DcMotor motor: dcMotors)
            motor.setTargetPosition(-("0" + motor.getDeviceName()).indexOf("Right")*(int)Math.round(distance));

        // Sets all DcMotor RunModes to make the robot move until the target position is met
        for (DcMotor motor : dcMotors) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        // Turns motor on at required speed until target reached
        while(dcMotors[3].isBusy()) {
            // Turns on power for all motors goes at the speed identified by the motorSpeed variable
            for (DcMotor motor : dcMotors) {
                motor.setPower(motorSpeed);
            }
        }

        // when the DcMotors are done moving reset encoders if requested and stop moving
        for (DcMotor motor : dcMotors) {
            motor.setPower(0.0);
            if(resetEncoders)
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

    }

    // rotates the robot by the given angle in degrees
    // if resetEncoders is true the encoders are set to zero and stop
    // wheel Radius in meters
    public void rotate(double angle, boolean resetEncoders) {

        // The angle needed for the robot
        double rotation = (angle/360)*Math.PI*Math.PI*WHEEL_DIAMETER*WHEEL_LENGTH*TICKS_PER_REV*GEAR_RATIO;

        // Sets target position for rearRight DcMotor
        if(angle > 0) {
            dcMotors[0].setTargetPosition((int) Math.round(rotation));
        }

        // Sets target position for rearLeft DcMotor
        else if(angle < 0) {
            dcMotors[1].setTargetPosition((int) Math.round(rotation));
        }

        // Sets all DcMotor RunModes to make the robot move until the target position is met
        for (DcMotor motor : dcMotors) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        while(dcMotors[3].isBusy()) {
            // Turns on power for all motors goes at the speed identified by the motorSpeed variable
            for (DcMotor motor : dcMotors) {
                motor.setPower(motorSpeed);
            }
        }
        // when the DcMotors are done moving reset encoders if requested and stop moving
        for (DcMotor motor : dcMotors) {
            motor.setPower(0.0);
            if(resetEncoders)
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

    }

    @Override
    public void runOpMode() {

        waitForStart();

        // Declares & Initializes Movement object
        Movement chipWrecked = new Movement(
                               hardwareMap.get(DcMotor.class, "RightRearDcMotor0"),
                               hardwareMap.get(DcMotor.class, "LeftRearDcMotor1"),
                               hardwareMap.get(DcMotor.class, "LeftForeDcMotor2"),
                               hardwareMap.get(DcMotor.class, "RightForeDcMotor3")
        );

        // Example movement
        chipWrecked.move(10, true);
        chipWrecked.rotate(90, true);
        chipWrecked.move(5, true);
        chipWrecked.rotate(180, true);
        chipWrecked.move(10, true);

        // Stops Program if it has been running for 30 seconds or more
        if(this.getRuntime() >= 30 )
            this.requestOpModeStop();
    }

}
