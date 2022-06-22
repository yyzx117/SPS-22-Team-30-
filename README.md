CS 2200 Linux Environment Setup Script
===============

Introduction
-----------
Hello!

To avoid compatability issues for this class' projects and for use in future
classes, CS 2200 now features a virtual machine!

Reading this means you've either installed a virtual machine hypervisor and have
*successfully* installed the custom disk image provided, or you have downloaded
the included scripts for your native distribution.

*Note to TAs: Find discussion of code and future work below*

Requirements
-----------

The following bash scripts use Debian's and Ubuntu's package manager.
Our testing OS version was **Ubuntu 20.04 LTS**. These scripts currently work
with our custom disk image; however, *more work will be needed for other native
Linux distros*.

Included Files
-----------

* `cs2200init.sh`: main script (installs several packages and extensions)
* `run_circuitsim.sh`: script to run CircuitSim's .jar file

Using the Scripts
-----------

### Opening the Command Line

Ubuntu's shortcut for opening the terminal is `CTRL+ALT+t`
You will use this extensively, so it will shortly become muscle memory.

### General Info & Debugging

Scripts unpacked from the custom disk image are located in `/home/scripts`.

Scripts downloaded from Canvas will be in your user Downloads folder like so:
`/home/gpburdell/Downloads`

To run the scripts, open up Terminal and type either:

    cd /home/scripts

    cd /home/gpburdell/Downloads

You may move these scripts anywhere you'd like, but they must be located within
the **same directory**. `cd` into this directory and follow the usage guidelines.

These scripts install all necessary packages and dependecies for CS2200. You may
encounter an error such as:

    bash: ./cs2200init.sh: Permission denied

If so, the current script is not listed as an executable. To make a file
executable, type the following command:

    chmod +rx cs2200init.sh

You do not need to change the permissions of the `run_circuitsim.sh`.
`cs2200init.sh` should handle this case.

You may run the scripts in user mode. There are several commands that will
require root permissions, however. Please type in your password and press
`Enter` to accept and continue execution.

### Using cs2200init.sh

**Note:** `cs2200init.sh` requires `run_circuitsim.sh` to share a working
directory. If `run_circuitsim.sh` is not found, it will create a dummy copy.

Example usage of `cs2200init.sh`. Type the following command in terminal:

    ./cs2200init.sh

For debugging purposes, type the following command in terminal:

    ./cs2200init.sh 2>&1 | tee cs2200init.log

The following script will run approxiamtely 2-3 minutes. It will display the
following when complete:

    Script Done
    >   Successfully installed "example-package"
    ...

After completing, several things have been done:

    1) All necessary packages for CS 2200 projects and homeworks will be installed
    2) Text editors Sublime Text and VSCode will be installed
    3) Folders cs2200, cs2200-projects, and cs2200-homeworks will be created
    4) CircuitSim's .jar file will be installed into cs2200
    5) run_circuitsim.sh will be moved into cs2200

Note: The folder `cs2200` is not required and is used at **your discretion**.
You may use the contents of the folder (including sub-folders) as you wish. The
created file structure is simply a foundation to help your productivity. We
recommend the following:

    Projects should go into `cs2200/cs2200-projects`.
    Homeworks (mainly HW2) should go into `cs2200/cs22-homeworks`.
    CircuitSim and `run_circuitsim.sh` should be kept together.

### Using srun_circuitsim.sh

**Note:** `run_circuitsim.sh` requires CircuitSim's .jar file to be located in
the same directory. If it is not found, it will create a new file and load the
terminal command. This is not ideal

Example usage of `run_circuitsim.sh`. Type the following command in terminal:

    ./run_circuitsim.sh

For debugging purposes, type the following command in terminal:

    ./run_circuitsim.sh 2>&1 | tee run_circuitsim.sh.log

The following script will attached all needed modules and run the .jar file.
Use CircuitSim as usual.

TAs: Code Overview, Maintenance, and Future Work
-----------

Hello (again)!

This section is for future TAs. I do not expect many students to read from here,
but if you're eager to learn, **be my guest**. This section will discuss the
choices I've made, how to maintain this product, and future work to be completed.

### Code Overview and Maintenance

The script `cs2200init.sh` is split into three sections: variables, code, and
diagnostics.

The diagnostics are for the students. It tells them what has been installed
at run-time.

The code is fairly tight... (I may regret these words later, but so be it).
There are several conventions I have ignored; I am not a BASH programmer.
Certain segments were adapted or simply stolen from the internet. However, the
functionality is solid and is detailed clearly through documentation. Functions
are given parameters and have basic error detection.

The variables are your job, future TAs. This is how you maintain these scripts.
Add or remove needed packages from `PKG_LIST`; similarly, add or remove VSCode
extensions from `CODE_EXT`. Should CircuitSim be updated from 1.8.2, update the
file name from `CIRCUITSIM_VER`. And if `run_circuitsim.sh` is somehow
disconnected from the project, it serves to only run the following line of code:

    java -jar --module-path /usr/share/openjfx/lib
    --add-modules=javafx.base,javafx.controls,javafx.fxml CircuitSim1.8.2

The rest of that script was simple error handling. This may need to change if
the required version of Java changes.

### Future Work

Aside from updating variables, there are clear areas needing improvement. I may
work on this, or I may leave it for future TAs. Who knows...

Regardless, below is a list of possible future work for this product.

####1) **Fix error handling of missing script**:
I first opted to echo a simple error message saying "Come to office hours,
dummy." Then, I decided to simply write the need terminal command (found above)
to a new file also called `run_circuitsim.sh`. Both seem *meh*.

Future work may include different ways to handle this error, such as fetching
files using `wget` or writing a whole new script from within `cs2200init.sh`
itself.

####2) **Include instructions for other Linux Distros**:
As stated above, the included scripts use Debian and Ubuntu package managers.
If a student uses a native distribution such as Arch or RedHat distro, these
commands will be useless. If a student uses these distros, it is my hope they
themselves have enough *Linux knowledge* to adjust the commands accordingly;
however, this may be unfair.

Future work may include commented out lines for different package managers.

####3) **Update according to standard conventions**:
This is by far the easiest. I wrote these command from online examples, so I'm
certain I missed many BASH conventions (looking at you *if* and *for* statements).

Future work may include updating scripts for style and clarity.

### Final Remarks

####To the TAs:

Use this README for guidance, but feel free to adjust scripts as needed. All I
ask is that you keep my name as original author (*please*).

This class is always extremely rewarding to teach. There are difficult days, but
enjoy this time. It does not last forever. Make friends with you fellow TAs and
**GO TO GAME NIGHTS**.

####To the Students:
Wow.

If you've read down here, you either scrolled too far or you're already doing
better than I was as a student. Take it easy on yourself. Classes aren't
everything, but try your hardest in all your work. Enjoy the semester and
friends, and go kill it!

Best,
Jack :)
"# SPS-22-Team-30-" 
