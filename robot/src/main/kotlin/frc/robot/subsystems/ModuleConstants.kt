package frc.robot.subsystems

import edu.wpi.first.math.util.Units

class Constants {
    class ModuleConstants {
        // NOTE: MEASURE AND UPDATE BEFORE DEPLOYING
        val kWheelDiameterMeters: Double = Units.inchesToMeters(4.0)
        val kDriveMotorGearRatio: Double = 1 / 5.8462
        val kTurningMotorGearRatio: Double = 1 / 18.0
        val kDriveEncoderRot2Meter: Double = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters
        val kTurningEncoderRot2Rad: Double = kTurningMotorGearRatio * 2 * Math.PI
        val kDriveEncoderRPM2MeterPerSec: Double = kDriveEncoderRot2Meter / 60
        val kTurningEncoderRPM2RadPerSec: Double = kTurningEncoderRot2Rad / 60
        val kPTurning: Double = 0.5;
    }
}