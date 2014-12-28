# jscrot #
`jscrot` is a lightweight and simple tool for screenshot taking. After JTakeScrot's distribution rights were
ceased to TwentyPeople International, their dev team stopped working on it. jscrot is a fork of JTakeScrot that is
actively developed and therefore all the legacy bugs have been fixed.

## Dependencies ##
If you want to compile jscrot, you'll need the JDK8 and run this command:

    $ javac -cp jscrot/src/com.jscrot jscrot.java ImgurUploader.java

Then run the generated class:

    $ java jscrot

## Usage ##
If you don't want to compile it by yourself, a jar artifact is provided under `out/artifacts`. Simply download that
file and run it. You can start it by simply double-clicking the jar, but command line is recommended since you'll
be notified in case jscrot doesn't support your system tray:

    $ java -jar jscrot.jar

Afterward, an icon will appear in your system tray. To take a screenshot, just double click the icon and save
the file (jpeg, png and gif files are supported). After saving the file, the image will be anonymously uploaded
to imgur. A message box will appear, showing you the link. Afterwards, if supported by your platform, the link will
be saved in your clipboard.

## Compatibility ##
jscrot uses the Java built-in `SystemTray` and `TrayIcon` objects, which are supposed to work in every platform
featuring a system tray. In my tests, however, it didn't work for Ubuntu's Unity desktop environment since it
doesn't use a system tray but *indicators.*

## TODO ##
* ~~Auto upload screenshots to imgur.~~ (**completed** as of v2))
* ~~Switch from Swing to SWT to make everything look native.~~ (**discarded** as of v2, modified swing to adapt a more native look & feel)
* Constant bug spotting & fixing.