# Edupage Photo Downloader - User Guide

This guide will walk you through downloading and using the Edupage Photo Downloader, even if you're not familiar with technical tools.

## Table of Contents
1. [What This Tool Does](#what-this-tool-does)
2. [What You Need](#what-you-need)
3. [Step-by-Step Instructions](#step-by-step-instructions)
4. [Troubleshooting](#troubleshooting)

---

## What This Tool Does

This application automatically downloads new photos from your Edupage account to a folder on your computer. It's useful for parents who want to keep a backup of their children's school photos.

---

## What You Need

Before starting, make sure you have:

### ✅ Java 21 or Higher

**How to check if you have Java:**
1. Open Terminal (Mac) or Command Prompt (Windows)
   - **Windows:** Press `Win + R`, type `cmd`, press Enter
   - **Mac:** Press `Cmd + Space`, type `Terminal`, press Enter
2. Type: `java -version` and press Enter
3. If you see version 21 or higher, you're good to go!

**If you don't have Java or have an older version:**
1. Go to: https://www.oracle.com/java/technologies/downloads/#java21
2. Download the installer for your operating system
3. Run the installer and follow the instructions

### ✅ Firefox Browser

This tool uses Firefox to access Edupage.

**Download Firefox:** https://www.mozilla.org/firefox/new/

---

## Step-by-Step Instructions

### Step 1: Download the Application

1. Click this link: [Download edupage-photo-downloader-1.0.0.jar](https://github.com/YOUR_USERNAME/edupage-photo-downloader/releases/latest)
2. Save the file to a location you can find easily (like your Desktop or Downloads folder)

### Step 2: Create a Folder for Photos

1. Create a new folder where you want the photos to be saved
   - **Example (Windows):** `C:\Users\YourName\Pictures\EdupagePhotos`
   - **Example (Mac):** `/Users/YourName/Pictures/EdupagePhotos`
2. Remember this location - you'll need it in Step 4

### Step 3: Gather Your Information

You'll need:
- ✏️ **Edupage URL:** The web address of your school's Edupage (e.g., `yourschool.edupage.org`)
- ✏️ **Username:** Your Edupage login username
- ✏️ **Password:** Your Edupage login password
- ✏️ **Photos Location:** The folder path you created in Step 2

### Step 4: Run the Application

#### On Windows:

1. Press `Win + R` to open the Run dialog
2. Type `cmd` and press Enter to open Command Prompt
3. Type `cd` followed by the location where you saved the JAR file
   - Example: `cd C:\Users\YourName\Downloads`
4. Copy this command and replace the highlighted parts with your information:

```cmd
java -Dedupage.url=yourschool.edupage.org -Dedupage.username=your_username -Dedupage.password=your_password -Dphotos.location=C:\Users\YourName\Pictures\EdupagePhotos -jar edupage-photo-downloader-1.0.0.jar
```

#### On Mac:

1. Press `Cmd + Space` to open Spotlight Search
2. Type `Terminal` and press Enter to open Terminal
3. Type `cd` followed by the location where you saved the JAR file
   - Example: `cd ~/Downloads`
4. Copy this command and replace the highlighted parts with your information:

```bash
java -Dedupage.url=yourschool.edupage.org -Dedupage.username=your_username -Dedupage.password=your_password -Dphotos.location=/Users/YourName/Pictures/EdupagePhotos -jar edupage-photo-downloader-1.0.0.jar
```

## Troubleshooting
In case of any issues, please:
- Ask for help in the [Issues](https://github.com/lkrnac/edupage-photo-downloader/issues) section
- Write email to lubos.krnac@gmail.com