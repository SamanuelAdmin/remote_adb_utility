To use this utility you need to start in via startservice command.
1) Connect phone to the PC and enable USB-debugging (in developer settings)
2) Open terminal on the PC and enter:
adb shell am startservice -n com.example.remoteadbutility/.LoaderService
