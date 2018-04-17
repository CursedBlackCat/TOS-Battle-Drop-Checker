# TOS Battle Drop Checker

The TOS Battle Drop Checker takes the com.madhead.tos.en.v2.playerprefs.xml file from the English verison of Madhead's popular mobile game *Tower of Saviors* as an input, and outputs what the player will receive as loot for each wave of enemies in that battle. This could be useful for determining whether a Power Release material will drop from an Ultimate stage, or for determining whether a biweekly card will drop, etc.

This program only works with the Android version of the game.

This is the core code for the Battle Drop Checker, reading from a file named com.madhead.tos.en.v2.playerprefs.xml in the project root and outputting to the standard Java console. **This code is not intended to be used as a standalone application.** The code will be implemented into more convenient ways of input/output, such as through a Discord bot, a GUI applcation, or an Android app.

If you encounter any issues while using the program, please open an issue on this repository so that I may investigate.

## How can I use this?
Again, this code is not intended to be used as a standalone application. It is functional, so theoretically you could download and use it as-is, but since this repository is in the format of an Eclipse project, it is not easy to do so. If you know how to download and import the project into Eclipse, you can simply place your com.madhead.tos.en.v2.playerprefs.xml file in the project root and then run the main class from within Eclipse. Otherwise, I suggest you wait until I release a version in a format that is easier to run.

**TL;DR: If you know how to import Eclipse projects into Eclipse, then clone or download the repository and do so. Otherwise, wait for a stable, more user-friendly release.**

## Want to contribute?

This repository is in the format of an Eclipse Java project; if you already use Eclipse, you can clone or download the repository and import the whole repository into your workspace as an Eclipse Java project.

If you see any flaws in the code that you are able to fix, please do so and open a pull request. As an independent programmer, any contribution to fixing bugs is greatly appreciated.

If you have a suggestion or a feature request, please open an issue on this repository.
