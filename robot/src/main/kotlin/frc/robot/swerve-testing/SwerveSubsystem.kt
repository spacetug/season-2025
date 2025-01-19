package frc.robot.swerve-testing
// quick link: https://github.com/SeanSun6814/FRC0ToAutonomous
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.geometry.Pose2d
import edu.wpi.first.wpilibj.geometry.Rotation2d
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
//below needs to be changed once we actually move our experimental code out of this swerve-test directory.
import frc.robot.swerve-testing.Constants.DriveConstants
import frc.robot.swerve-testing.SwerveModule

class SwerveSubsystem(Thread{ 
        try {
            Thread.sleep(1000)
            zeroHeading()
        } catch (e: Exception) {
        }
    }.start()): SubsystemBase() {
            private val SwerveModule frontLeft = SwerveModule(
                DriveConstants.kFrontLeftDriveMotorPort,
                DriveConstants.kFrontLeftTurningMotorPort,
                DriveConstants.kFrontLeftDriveEncoderReversed,
                DriveConstants.kFrontLeftTurningEncoderReversed,
                DriveConstants.kFrontLeftDriveAbsoluteEncoderPort,
                DriveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad,
                DriveConstants.kFrontLeftDriveAbsoluteEncoderReversed
            )

            private val SwerveModule frontRight = SwerveModule(
                DriveConstants.kFrontRightDriveMotorPort,
                DriveConstants.kFrontRightTurningMotorPort,
                DriveConstants.kFrontRightDriveEncoderReversed,
                DriveConstants.kFrontRightTurningEncoderReversed,
                DriveConstants.kFrontRightDriveAbsoluteEncoderPort,
                DriveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad,
                DriveConstants.kFrontRightDriveAbsoluteEncoderReversed
            )

            private val SwerveModule backLeft = SwerveModule(
                DriveConstants.kBackLeftDriveMotorPort,
                DriveConstants.kBackLeftTurningMotorPort,
                DriveConstants.kBackLeftDriveEncoderReversed,
                DriveConstants.kBackLeftTurningEncoderReversed,
                DriveConstants.kBackLeftDriveAbsoluteEncoderPort,
                DriveConstants.kBackLeftDriveAbsoluteEncoderOffsetRad,
                DriveConstants.kBackLeftDriveAbsoluteEncoderReversed
            )

            private val SwerveModule backRight = SwerveModule(
                DriveConstants.kBackRightDriveMotorPort,
                DriveConstants.kBackRightTurningMotorPort,
                DriveConstants.kBackRightDriveEncoderReversed,
                DriveConstants.kBackRightTurningEncoderReversed,
                DriveConstants.kBackRightDriveAbsoluteEncoderPort,
                DriveConstants.kBackRightDriveAbsoluteEncoderOffsetRad,
                DriveConstants.kBackRightDriveAbsoluteEncoderReversed
            )

            private val AHRS gyro = AHRS(SPI.Port.xMXP)

            private val SwerveDriveOdometry odometer = SwerveDriveOdometry(DriveConstants.kDriveKinematics, Rotation2d(0))
        }