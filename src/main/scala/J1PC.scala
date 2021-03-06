/*
 * Author: Steffen Reith (Steffen.Reith@hs-rm.de)
 *
 * Create Date:    Fr 10. Apr 12:02:44 CEST 2020
 * Module Name:    J1PCNext - Update logic for the program counter
 * Project Name:   J1Sc - A simple J1 implementation in Scala using Spinal HDL
 *
 */
import spinal.core._

case class J1PC(cfg : J1Config) {

  // Program counter (note that the MSB is used to control dstack and rstack, hence make is one bit larger)
  val pc         = Reg(UInt(cfg.adrWidth + 1 bits)) init(cfg.startAddress)
  val pcPlusOne  = UInt(cfg.adrWidth + 1 bits)

  def apply(pcN : UInt, clrActive : Bool, irq : Bool) : (UInt, Bits) = {

    // Update the PC if no synchronous reset is active
    when(!clrActive) {pc := pcN}
    pcPlusOne := pc + 1

    // Check for interrupt mode, because afterwards the current instruction has to be executed
    val returnPC = Mux(irq, pc.asBits, pcPlusOne.asBits)

    // Return the new program counter
    (pc, returnPC)

  }

  def updatePC(stall : Bool, clrActive : Bool, instr : Bits, dtos : Bits, rtos : Bits) : UInt = {

    // The signal for the next pc value
    val pcN = UInt(cfg.adrWidth + 1 bits)

    // Handle the PC (remember cfg.adrWidth - 1 is the high indicator and instr(7) is the R -> PC field)
    switch(stall ##                                         // CPU stalled
           clrActive ##                                     // Check for reset state
           pc.msb ##                                        // Used to check for high jumps
           instr(instr.high downto (instr.high - 3) + 1) ## // Holds information about jumps and relevant ALU instructions
           instr(7) ##                                      // The R -> PC field in ALU instructions
           dtos.orR) {                                      // Jump if dtos is zero

      // Don't change the PC in stall mode
      is (M"1_-_-_---_-_-") {
        pcN := pc
      }

      // Check if we are in reset state
      is (M"0_1_-_---_-_-") {
        pcN := cfg.startAddress
      }

      // Check for jump, call instruction or conditional jump
      is (M"0_0_0_000_-_-", M"0_0_0_010_-_-", M"0_0_0_001_-_0") {
        pcN := instr(cfg.adrWidth downto 0).asUInt
      }

      // Check either for a high call or R -> PC field of an ALU instruction and load PC from return stack
      is (M"0_0_1_---_-_-", M"0_0_0_011_1_-") {
        pcN := rtos(cfg.adrWidth + 1 downto 1).asUInt
      }

      // By default goto next instruction
      default {
        pcN := pcPlusOne
      }

    }

    // The program counter for the next clock
    pcN

  }

}
