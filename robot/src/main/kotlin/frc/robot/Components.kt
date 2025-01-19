package frc.robot

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import frc.robot.parts.DefaultLeftConfig
import frc.robot.parts.DefaultRightConfig
import frc.robot.parts.DefaultSwerveConfig
import frc.robot.parts.MotorSet

/**
 * The [Components] singleton can be used to configure and hold reference to hardware parts
 * used by the [Robot].
 *
 * The only gain here is organizational, as it avoids cluttering in the [Robot] class scope.
 */
object Components {
    object Propulsion {
        val LeftMotorSet = MotorSet(
            lead = SparkMax(2, SparkLowLevel.MotorType.kBrushless),
            follower0 = SparkMax(6, SparkLowLevel.MotorType.kBrushless),
            baseConfig = DefaultLeftConfig,
        )
        val RightMotorSet = MotorSet(
            lead = SparkMax(4, SparkLowLevel.MotorType.kBrushless),
            follower0 = SparkMax(7, SparkLowLevel.MotorType.kBrushless),
            baseConfig = DefaultRightConfig,
        )
        val frontLeftSwerve = SwerveModule(
            drive = SparkMax(_, SparkLowLevel.MotorType.kBrushless),
            turning = SparkMax(_, SparkLowLevel.MotorType.kBrushless),
            baseConfig = DefaultSwerveConfig,
        )
        val frontRightSwerve = SwerveModule(
            drive = SparkMax(_, SparkLowLevel.MotorType.kBrushless),
            turning = SparkMax(_, SparkLowLevel.MotorType.kBrushless),
            baseConfig = DefaultSwerveConfig,
        )
        val backLeftSwerve = SwerveModule(
            drive = SparkMax(_, SparkLowLevel.MotorType.kBrushless),
            turning = SparkMax(_, SparkLowLevel.MotorType.kBrushless),
            baseConfig = DefaultSwerveConfig,
        )
        val backRightSwerve = SwerveModule(
            drive = SparkMax(_, SparkLowLevel.MotorType.kBrushless),
            turning = SparkMax(_, SparkLowLevel.MotorType.kBrushless),
            baseConfig = DefaultSwerveConfig,
        )
    }
}
