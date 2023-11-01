# How to develop
1. Open folder with the IntelliJ IDEA
2. Set (or first download) a JDK (tested with 21) under File -> Project Structure (hotkey Ctrl+Shift+Alt+S) and there Project Settings -> Project -> SDK
3. Click edit next to the SDK combobox to see the "JDK home path" and copy it. Then create a new entry for the "path" environment variable pointing to the "JDK home path"/bin (e.g. C:\Users\maxim\.jdks\openjdk-21\bin)
4. Click the Play Icon next to the Main class/method or any test class/method to run/debug