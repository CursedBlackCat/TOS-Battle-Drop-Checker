# TOS Battle Drop Checker

The TOS Battle Drop Checker takes the com.madhead.tos.en.v2.playerprefs.xml file from the English verison of Madhead's popular mobile game *Tower of Saviors* as an input, and outputs what the player will receive as loot for each wave of enemies in that battle. This could be useful for determining whether a Power Release material will drop from an Ultimate stage, or for determining whether a biweekly card will drop, etc.

This program only works with the Android version of the game.

For now, this program is a desktop GUI program that is run on the computer. The code will be implemented into other more convenient ways of input/output in the future, such as through a Discord bot or an Android app.

**IMPORTANT NOTE: This program currently only checks for card drops. It does not display dragonary craft core, soul, treasure chest, or other drops. Support for other drop types will be added in a future release.**

If you encounter any issues while using the program, please open an issue on this repository so that I may investigate.

## How can I use this?

### Prerequisites
* TOS on a rooted Android device
* JRE (Java Runtime Environment) installed on your computer
* Latest release of TOS Battle Drop Checker (download from [here](https://github.com/CursedBlackCat/TOS-Battle-Drop-Checker/releases))

### Steps
1. Start a battle in TOS, then force close TOS.
2. Using a root browser of your choice, navigate to /data/data/com.madhead.tos.en/files/shared_prefs and copy the file com.madhead.tos.en.v2.playerprefs.xml to the /sdcard/ directory.
3. Via USB, Google Drive, or any method of your choice, copy the XML file from /sdcard/ on your phone to any folder on your computer.
4. Double click on the Battle Drop Checker JAR and click on "Load an XML file".
5. Navigate to the location where you saved the XML file from step 3, and select the XML file.
6. The drops for each wave should be displayed.

## Want to contribute?

This repository is in the format of an Eclipse Java project; if you already use Eclipse, you can clone or download the repository and import the whole repository into your workspace as an Eclipse Java project.

If you see any flaws in the code that you are able to fix, please do so and open a pull request. As an independent programmer, any contribution to fixing bugs is greatly appreciated.

If you have a suggestion or a feature request, please open an issue on this repository.
