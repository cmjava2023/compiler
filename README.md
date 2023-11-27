# How to develop
1. Open folder with the IntelliJ IDEA
2. Set (or first download) a JDK (tested with 19) under File -> Project Structure (hotkey Ctrl+Shift+Alt+S) and there Project Settings -> Project -> SDK
   ![resources\images\IntelliJ JDK Settings.png](resources\images\IntelliJ_JDK_Settings.png)
3. Click edit next to the SDK combobox to see the "JDK home path" and copy it. Then create a new entry for the "path" environment variable pointing to the "JDK home path"/bin (e.g. C:\Users\maxim\.jdks\openjdk-21\bin)
   ![resources\images\Path Environment Variable.png](resources\images\Path_Environment_Variable.png)   
4. Define Gradle JVM and that it is used to build and test under File | Settings (hotkey Ctrl+Alt+S) | Build, Execution, Deployment | Build Tools | Gradle
    ![resources\images\IntelliJ Gradle Settings.png](resources\images\IntelliJ_Gradle_Settings.png)
5. Click the Play Icon next to the Main class/method or any test class/method to run/debug