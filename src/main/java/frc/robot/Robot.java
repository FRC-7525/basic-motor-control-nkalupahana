// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  TalonFX motor1 = new TalonFX(0);
  TalonFX motor2 = new TalonFX(1);
  Timer timer = new Timer();
  boolean autonomousFlipped = false;
  XboxController controller = new XboxController(0);
  boolean motor2Toggle = false;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
    autonomousFlipped = false;
  }

  @Override
  public void autonomousPeriodic() {
    if (timer.get() < 3) {
      double speed = autonomousFlipped ? -1 : 1;
      motor1.set(speed);
      motor2.set(speed);
    } else {
      timer.reset();
      autonomousFlipped = !autonomousFlipped;
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    if (controller.getAButton()) {
      motor1.set(1);
    } else {
      motor1.set(0);
    }

    if (motor2Toggle) {
      motor2.set(1);
    } else {
      motor2.set(0);
    }

    if (controller.getBButtonPressed() || (motor2Toggle && timer.get() > 10)) {
      motor2Toggle = !motor2Toggle;
      timer.reset();
      timer.start();
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
