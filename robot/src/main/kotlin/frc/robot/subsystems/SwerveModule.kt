package frc.robot.subsystems

// import com.revrobotics.spark.CANEncoder // get encoder directly from sparkmax using getEncoder?
import com.revrobotics.AbsoluteEncoder
import com.revrobotics.AnalogInput
import com.revrobotics.RelativeEncoder
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.kinematics.SwerveModuleState
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.RobotController
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlin.math.abs


/*class SwerveModule(
    driveMotorId: Int,

){
    // int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed,
    // int absoluteEncoderId, double absoluteEncoderOffset, boolean absoluteEncoderReversed)
    private val driveMotor: SparkMax
    private val turningMotor: SparkMax

    // private val driveEncoder: CANEncoder
    // private val turningEncoder: CANEncoder

    private val turningPIDController: PIDController

    private val absoluteEncoder: AnalogInput
    private val absoluteEncoderReversed: Boolean
    private val absoluteEncoderOffsetRad: Double
} */

class SwerveModule(
    driveMotorId: Int,
    turningMotorId: Int,
    driveMotorReversed: Boolean,
    turningMotorReversed: Boolean,
    absoluteEncoderId: Int,
    absoluteEncoderOffsetRad: Double,
    absoluteEncoderReversed: Boolean
) {
    private val driveMotor: SparkMax
    private val turningMotor: SparkMax

    private val driveEncoder: Encoder
    private val turningEncoder: Encoder

    private val turningPidController: PIDController

    private val absoluteEncoder: AbsoluteEncoder


    init {
        absoluteEncoder = AnalogInput()

        driveMotor = SparkMax(driveMotorId, SparkLowLevel.MotorType.kBrushless)
        turningMotor = SparkMax(turningMotorId, SparkLowLevel.MotorType.kBrushless)

        driveMotor.setInverted(driveMotorReversed)
        turningMotor.setInverted(turningMotorReversed)

        driveEncoder = driveMotor.getEncoder()
        turningEncoder = turningMotor.getEncoder()

        driveEncoder.setPositionConversionFactor(ModuleConstants.kDriveEncoderRot2Meter)
        driveEncoder.setVelocityConversionFactor(ModuleConstants.kDriveEncoderRPM2MeterPerSec)
        turningEncoder.setPositionConversionFactor(ModuleConstants.kTurningEncoderRot2Rad)
        turningEncoder.setVelocityConversionFactor(ModuleConstants.kTurningEncoderRPM2RadPerSec)

        turningPidController = PIDController(ModuleConstants.kPTurning, 0.0, 0.0)
        turningPidController.enableContinuousInput(-Math.PI, Math.PI)

        resetEncoders()
    }

    val drivePosition: Double
        get() = driveEncoder.getPosition()

    val turningPosition: Double
        get() = turningEncoder.getPosition()

    val driveVelocity: Double
        get() = driveEncoder.getVelocity()

    val turningVelocity: Double
        get() = turningEncoder.getVelocity()

    val absoluteEncoderRad: Double
        get() {
            var angle = absoluteEncoder.voltage / RobotController.getVoltage5V()
            angle *= 2.0 * Math.PI
            angle -= absoluteEncoderOffsetRad
            return angle * (if (absoluteEncoderReversed) -1.0 else 1.0)
        }

    fun resetEncoders() {
        driveEncoder.setPosition(0)
        turningEncoder.setPosition(absoluteEncoderRad)
    }

    val state: SwerveModuleState
        get() = SwerveModuleState(driveVelocity, Rotation2d(turningPosition))

    fun setDesiredState(state: SwerveModuleState) {
        var state = state
        if (abs(state.speedMetersPerSecond) < 0.001) {
            stop()
            return
        }
        state = SwerveModuleState.optimize(state, this.state.angle)
        driveMotor.set(state.speedMetersPerSecond / DriveConstants.kPhysicalMaxSpeedMetersPerSecond)
        turningMotor.set(turningPidController.calculate(turningPosition, state.angle.radians))
        SmartDashboard.putString("Swerve[" + absoluteEncoder.getChannel() + "] state", state.toString())
    }

    fun stop() {
        driveMotor.set(0)
        turningMotor.set(0)
    }
}