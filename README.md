# H11AJobSeeker

This repository contains the decompilation and development of applications related to the Bellabot, a Pudutech food delivery robot. We sought to gain more granular control over the bot, and this is the result of our work.

## Main Developed Code

### BellaBotApp
- **Frontend:** 
  - Code: `BellaBotApp/App.tsx`, `BellaBotApp/src`
  - Testing: `BellaBotApp/cypress`
- **Backend:** 
  - Code: `BellaBotApp/android/src/java/com/unsw/bellabot`
  - Testing: `BellaBotApp/android/src/androidTest/java/com/unsw/bellabot`

### ROS
- **Files of Interest:** 
  - `ros-bellabot/android_tutorial_pubsub/src/org/ros/android/android_tutorial_pubsub`
  - Notable files: `DoublePub.java`, `IntPub.java`, `MainActivity.java`
- **Custom ROS Messages:**
  - Directory: `ros-bellabot/customROSMsgs`

## Applications

### BellaBotApp
BellaBotApp is our main application that combines a Java API backend with a React Native user-friendly frontend.

### JobeeekerApp
JobeeekerApp is a thin native Java/Kotlin reference implementation of our Java API. It serves as a reference for future developers.

### ros-bellabot
ros-bellabot is the ROS wrapper for our API. It allows a development device connected to the bot via a shared network connection to subscribe to the bot's ROS topics. The directory `ros-bellabot/customROSMsgs` contains a Python script to run on a developer machine connected to the bot via ROS, polling the bot's statistics.

## Decompilation Output

### DecompiledJadx2
- Directory: `decompiledJadx2/sources`
- Contains the most complete decompilation output of the original proprietary Pudutech applications.

### DecompiledAssets
- Directory: `decompiledAssets`
- Contains decompiled assets from the bot and decompiled Pudutech `.so` files in C/C++.
