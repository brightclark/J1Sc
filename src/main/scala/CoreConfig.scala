/*
 * Author: Steffen Reith (steffen.reith@hs-rm.de)
 *
 * Creation Date:  Tue Nov 15 17:04:09 GMT+1 2016
 * Module Name:    BoardConfig - Holds the configuration of the used (development) board
 * Project Name:   J1Sc - A simple J1 implementation in Scala using Spinal HDL
 *
 */
import spinal.lib.com.uart._
import spinal.core._

// Configuration of a PMod-interface
case class GPIOConfig(width : Int)

object GPIOConfig {

  // Provide a default configuration
  def default : GPIOConfig = {

    // Default configuration values
    val config = GPIOConfig(width = 8)

    // Return the default configuration
    config

  }

  // Provide a configuration for Digilent's pmods
  def pmod : GPIOConfig = {

    // Create a configuration suitable for pmods
    val config = GPIOConfig(width = 8)

    // Return the configuration
    config

  }

}

// Information about controllable LEDs on the board
case class LEDArrayConfig(width : Int, lowActive : Boolean)

object LEDArrayConfig {

  // Provide a default configuration
  def default : LEDArrayConfig = {

    // Default configuration values
    val config = LEDArrayConfig(width     = 16,
                                lowActive = false)

    // Return the default configuration
    config

  }

  // Provide a configuration for Nexys4X boards
  def nexys4X : LEDArrayConfig = {

    // Create a configuration for a Nexys4 board
    val config = LEDArrayConfig(width     = 16,
                                lowActive = false)

    // Return the configuration
    config

  }

  // Provide a smaller number of LEDs for a PMod
  def pmodLEDs : LEDArrayConfig = {

    // Configure a bank of eight LEDs on a PMod
    val config = LEDArrayConfig(width = 8,
                                lowActive = false)

    // Return the configuration for a LED array on a Pmod
    config
  }

  // Provide a config for four LEDs of an IceBreaker board
  def iceLEDs : LEDArrayConfig = {

    // Configure a bank of eight LEDs on a PMod
    val config = LEDArrayConfig(width = 4,
                                lowActive = false)

    // Return the configuration for a LED array on a Pmod
    config
  }

}

// Configuration of the PWM component
case class PWMConfig (pwmFrequency    : HertzNumber,
                      numOfDutyCycles : Int,
                      numOfChannels   : Int)

// Some configurations used for the PWM
object PWMConfig {

  // Provide the default configuration
  def default : PWMConfig = {

    // Create a default PWMConfig instance
    val config = PWMConfig(pwmFrequency    = 200 Hz,
                           numOfDutyCycles = 256,
                           numOfChannels   = 6)

    // Return the default configuration
    config

  }

  // Provide the default configuration
  def nexys4X : PWMConfig = {

    // Create a PWMConfig instance
    val config = PWMConfig(pwmFrequency    = 200 Hz,
                           numOfDutyCycles = 256,
                           numOfChannels   = 6)

    // Return a configuration suitable for a Nexys4X
    config

  }


  // Provide a configuration for an IcoBoard
  def icoPWM: PWMConfig = {

    // Create a config for the three LEDs on an IcoBoard
    val config = PWMConfig(pwmFrequency    = 200 Hz,
                           numOfDutyCycles = 256,
                           numOfChannels   = 3)

    // Return the configuration
    config

  }

  // Provide a configuration for an IceBreaker board
  def icePWM : PWMConfig = {

    // Create a config for on LED (using pwm) on an IcoBreaker board
    val config = PWMConfig(pwmFrequency    = 200 Hz,
                           numOfDutyCycles = 256,
                           numOfChannels   = 1)

    // Return the configuration
    config

  }

}

// Configuration of the seven-segment display component
case class SSDConfig (mplxFrequency        : HertzNumber,
                      numOfDisplays        : Int,
                      invertSelector       : Boolean,
                      invertSegments       : Boolean,
                      displayDefaultActive : Boolean)

// Some standard configurations used for the seven-segment display component
object SSDConfig {

  // Provide a default configuration
  def default : SSDConfig = {

    // Create the default instance
    val config = SSDConfig(mplxFrequency  = 200 Hz,
                           numOfDisplays  = 8,
                           invertSelector = true,
                           invertSegments = true,
                           displayDefaultActive = true)

    // Return the default configuration
    config

  }

  // Provide a configuration for a Nexys4X board
  def nexys4X: SSDConfig = {

    // Create the default instance
    val config = SSDConfig(mplxFrequency  = 200 Hz,
                           numOfDisplays  = 8,
                           invertSelector = true,
                           invertSegments = true,
                           displayDefaultActive = true)

    // Return the configuration for a Nexys4X board
    config

  }

}

// Configuration for an array of debounced pins
case class DBPinArrayConfig (waitTime  : TimeNumber,
                             numOfPins : Int)

// Come standard configurations used of debounced input pins
object DBPinArrayConfig {

  // Provide a default configuration
  def default: DBPinArrayConfig = {

    // Create the default instance
    val config = DBPinArrayConfig(waitTime  = 1 ms,
                                  numOfPins = 16)

    // Return the default configuration
    config

  }

  // A configuration for an array of slider switches
  def sliderSwitch16: DBPinArrayConfig = {

    // Debounce 16 switches with 1ms (e.g. Nexys4X)
    val config = DBPinArrayConfig(waitTime  = 1 ms,
                                  numOfPins = 16)

    // Return the configuration
    config

  }

  // A configuration for push buttons
  def pushButton5 : DBPinArrayConfig = {

    // Debounce 5 push butting with 5ms
    val config = DBPinArrayConfig(waitTime  = 5 ms,
                                  numOfPins = 5)

    // Return the configuration
    config

  }

  // A configuration for the push buttons of an IceBreaker board
  def iceButtons : DBPinArrayConfig = {

    // Debounce 3 push butting with 5ms (e.g. IceBreaker)
    val config = DBPinArrayConfig(waitTime  = 5 ms,
                                  numOfPins = 2)

    // Return the configuration
    config

  }

}

// Configuration of the UART
case class J1UARTConfig (clockDividerWidth : Int,
                         dataWidthMax : Int,
                         baudrate : Int,
                         dataLength : Int,
                         parity : UartParityType.E,
                         stop : UartStopType.E,
                         preSamplingSize : Int,
                         samplingSize : Int,
                         postSamplingSize : Int,
                         fifoDepth : Int)

object J1UARTConfig {

  // Provide a default configuration
  def default : J1UARTConfig = {

    val config = J1UARTConfig(clockDividerWidth = 20,
                              dataWidthMax = 8,
                              baudrate = 38400,
                              dataLength = 7,
                              parity = UartParityType.NONE,
                              stop = UartStopType.ONE,
                              preSamplingSize = 1,
                              samplingSize = 5,
                              postSamplingSize = 2,
                              fifoDepth = 8)

    // Return the configuration
    config

  }

  // Provide a fast configuration with 8N1 @ 921600
  def fastUartConfig : J1UARTConfig = {

    val config = J1UARTConfig(clockDividerWidth = 20,
                              dataWidthMax = 8,
                              baudrate = 8 * 115200,
                              dataLength = 7,
                              parity = UartParityType.NONE,
                              stop = UartStopType.ONE,
                              preSamplingSize = 1,
                              samplingSize = 5,
                              postSamplingSize = 2,
                              fifoDepth = 8)

    // Return the fast configuration
    config

  }

  // Provide a slow UART configuration 8N1 @ 11520
  def slowUartConfig : J1UARTConfig = {

    val config = J1UARTConfig(clockDividerWidth = 20,
                              dataWidthMax = 8,
                              baudrate = 115200,
                              dataLength = 7,
                              parity = UartParityType.NONE,
                              stop = UartStopType.ONE,
                              preSamplingSize = 1,
                              samplingSize = 5,
                              postSamplingSize = 2,
                              fifoDepth = 8)

    // Return the slow configuration
    config

  }

}

// Configuration of all IO components
case class CoreConfig(gpioConfig    : GPIOConfig,
                      ledBankConfig : LEDArrayConfig,
                      pwmConfig     : PWMConfig,
                      ssdConfig     : SSDConfig,
                      sSwitchConfig : DBPinArrayConfig,
                      pButtonConfig : DBPinArrayConfig,
                      uartConfig    : J1UARTConfig,
                      coreFrequency : IClockDomainFrequency,
                      ioWaitStates  : Int)

object CoreConfig {

  // Provide a default configuration
  def default : CoreConfig = {

    // Default configuration values
    val config = CoreConfig(gpioConfig     = GPIOConfig.default,
                             ledBankConfig = LEDArrayConfig.default,
                             pwmConfig     = PWMConfig.default,
                             ssdConfig     = SSDConfig.default,
                             sSwitchConfig = DBPinArrayConfig.default,
                             pButtonConfig = DBPinArrayConfig.default,
                             uartConfig    = J1UARTConfig.default,
                             coreFrequency = FixedFrequency(80 MHz),
                             1)

    // Return the default configuration
    config

  }

  // Provide a configuration for the Nexys4DDR board from Digilent
  def nexys4DDR : CoreConfig = {

    // Configuration values for a Nexys4DDR
    val config = CoreConfig(gpioConfig    = GPIOConfig.pmod,
                            ledBankConfig = LEDArrayConfig.nexys4X,
                            pwmConfig     = PWMConfig.nexys4X,
                            ssdConfig     = SSDConfig.nexys4X,
                            sSwitchConfig = DBPinArrayConfig.sliderSwitch16,
                            pButtonConfig = DBPinArrayConfig.pushButton5,
                            uartConfig    = J1UARTConfig.slowUartConfig,
                            coreFrequency = FixedFrequency(100 MHz),
                            1)

    // Return the configuration
    config

  }

  // Provide a configuration for the Nexys4 board from Digilent
  def nexys4 : CoreConfig = {

    // Configuration values for a Nexys4X
    val config = CoreConfig(gpioConfig    = GPIOConfig.pmod,
                            ledBankConfig = LEDArrayConfig.nexys4X,
                            pwmConfig     = PWMConfig.nexys4X,
                            ssdConfig     = SSDConfig.nexys4X,
                            sSwitchConfig = DBPinArrayConfig.sliderSwitch16,
                            pButtonConfig = DBPinArrayConfig.pushButton5,
                            uartConfig    = J1UARTConfig.fastUartConfig,
                            coreFrequency = FixedFrequency(100 MHz),
                            1)

    // Return the configuration
    config

  }

  // Provide a configuration for the IcoBoard
  def icoBoard : CoreConfig = {

    // Configuration values for an IcoBoard
    val config = CoreConfig(gpioConfig    = GPIOConfig.pmod,
                            ledBankConfig = LEDArrayConfig.pmodLEDs,
                            pwmConfig     = PWMConfig.icoPWM,
                            ssdConfig     = SSDConfig.default,
                            sSwitchConfig = DBPinArrayConfig.default,
                            pButtonConfig = DBPinArrayConfig.default,
                            uartConfig    = J1UARTConfig.slowUartConfig,
                            coreFrequency = FixedFrequency(40 MHz),
                            1)

    // Return the configuration
    config

  }

  // Provide a configuration for the IcoBreaker board
  def iceBoard : CoreConfig = {

    // Configuration values for an IceBreaker board
    val config = CoreConfig(gpioConfig    = GPIOConfig.pmod,
                            ledBankConfig = LEDArrayConfig.iceLEDs,
                            pwmConfig     = PWMConfig.icePWM,
                            ssdConfig     = null,
                            sSwitchConfig = null,
                            pButtonConfig = DBPinArrayConfig.iceButtons,
                            uartConfig    = J1UARTConfig.slowUartConfig,
                            coreFrequency = FixedFrequency(value = 18 MHz),
                            1)

    // Return the configuration
    config

  }

  // Provide a configuration for the simulation of an IcoBoard
  def boardSim : CoreConfig = {

    // Configuration values for an IcoBoard
    val config = CoreConfig(gpioConfig    = GPIOConfig.pmod,
                            ledBankConfig = LEDArrayConfig.pmodLEDs,
                            pwmConfig     = PWMConfig.icoPWM,
                            ssdConfig     = SSDConfig.default,
                            sSwitchConfig = DBPinArrayConfig.default,
                            pButtonConfig = DBPinArrayConfig.default,
                            uartConfig    = J1UARTConfig.default,
                            coreFrequency = FixedFrequency(value = 40 MHz),
                            1)

    // Return the configuration
    config

  }

}
