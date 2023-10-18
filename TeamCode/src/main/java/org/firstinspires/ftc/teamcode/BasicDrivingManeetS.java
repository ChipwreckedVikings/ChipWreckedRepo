package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.ArrayList;


@Autonomous
public class BasicDrivingManeetS extends LinearOpMode {
    private final ArrayList<DcMotor> dcMotors = new ArrayList<>(); // Creates an array list to store all the motor classes

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        dcMotors.add(hardwareMap.get(DcMotor.class, "RightRearDcMotor0"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "LeftRearDcMotor1"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "LeftForeDcMotor2"));
        dcMotors.add(hardwareMap.get(DcMotor.class, "RightForeDcMotor3"));

        int forward = 2000;
        for (DcMotor motor : dcMotors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(-("0" + motor.getDeviceName()).indexOf("Left") * forward); // Sets the 1st target position
        }

        for (DcMotor motor : dcMotors) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        for (DcMotor motor : dcMotors) {
            motor.setPower(1.0 / 3.0); // Starts the motor
        }

        if (this.getRuntime() >= 10) { // Designed to let the motors run to its previous positions before stopping and going in a different direction

            for (DcMotor motor : dcMotors) {
                motor.setPower(0.0);
            }

            if (this.getRuntime() >= 13) { // Creates a delay where the motor stops

                for (DcMotor motor : dcMotors) {
                    motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    motor.setTargetPosition(-("10" + motor.getDeviceName()).indexOf("Right") * forward); // Changes the target position
                }

                for (DcMotor motor : dcMotors) {
                    motor.setPower(1.0 / 3.0); // Turns the motors back on
                }

                for (DcMotor motor : dcMotors) {
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

                for (DcMotor motor : dcMotors) {
                    motor.setPower(1.0 / 3.0);
                }
                if (this.getRuntime() >= 30)
                    this.requestOpModeStop(); // Shuts down OpMode after 30 seconds.

            }

        }
    }
}

