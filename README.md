# How to develop
1. Open folder with the IntelliJ IDEA
2. Set (or first download) a JDK (tested with 19) under File -> Project Structure (hotkey Ctrl+Shift+Alt+S) and there Project Settings -> Project -> SDK
3. Define Gradle JVM under File | Settings (hotkey Ctrl+Shift+Alt+S) | Build, Execution, Deployment | Build Tools | Gradle
4. Click edit next to the SDK combobox to see the "JDK home path" and copy it. Then create a new entry for the "path" environment variable pointing to the "JDK home path"/bin (e.g. C:\Users\maxim\.jdks\openjdk-21\bin)
5. Click the Play Icon next to the Main class/method or any test~~~~ class/method to run/debug