# J1Sc - A simple reimplementation of the J1 CPU in Scala using Spinal HDL

## How to build J1Sc

1. Clone the latest version of SpinalHDL 
   (see https://github.com/SpinalHDL/SpinalHDL)

2. Setup Spinal HDL
   (see http://spinalhdl.github.io/SpinalDoc/spinal_getting_started/)

3. Change directory to the new clone of SpinalHDL and run 
   "sbt publish-local" inside

4. Change directory to J1Sc 

5. Run "cd toolchain/forth && make && cd ../.." to build the forth core
   system

6. Build J1Sc (either using the VHDL or the Verilog version). The
   generated files can be found in "gen/src/vhdl/J1SoC.vhd" and
   "gen/src/verilog/J1SoC.v".
   The Xilinx Vivado project file "J1Sc.xpr" for the VHDL version can be
   found in "vprj/VHDL/J1Sc".
   Open the / create a project and add a PLL/MMCM IP using the
   ClockWizard. Use PLL as component name and create following ports:
       module PLL (

        // Clock out ports
        output        clkOut,

        // Status and control signals
        output        isLocked,

        // Clock in ports
        input         clkIn

       );
   Note that J1Sc runs fine with a 80Mhz Clock on a Nexys4 DDR from
   Digilent. Constraint files for the Nexys4 DDR can be found in
   "/src/main/xilinx/nexys4ddr"

7. Build J1Sc and send the .bit file to your FPGA

8. "cd toolchain/forth"

9. Become root and run "bin/confs" (note that you have to set the serial
   port appropriately)

10. Press the reset button (default is BTNC on the Nexys4 DDR). You should
    see something like:
    `Contacting... established`
    `Loaded 143 words`

11. Type "#include SwapForth.fs"

12. turn on the leds by the command "hex ffff leds!"

13. Have fun with a working forth system

