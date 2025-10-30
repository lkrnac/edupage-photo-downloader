# Edupage Photo Downloader

A simple tool to automatically download new photos from your Edupage account.

## 📥 Download

**Latest Version:** [Download edupage-photo-downloader-1.0.0.jar](https://github.com/lkrnac/edupage-photo-downloader/releases/latest/download/edupage-photo-downloader-1.0.0.jar)

## 📋 Prerequisites

Before you can run this application, you need:

1. **Java 21 or higher** - [Download Java](https://www.oracle.com/java/technologies/downloads/#java21)
   - To check if Java is installed, open Terminal/Command Prompt and type: `java -version`

2. **Firefox Browser** - [Download Firefox](https://www.mozilla.org/firefox/new/)
   - This tool uses Firefox to access Edupage

## 🚀 How to Use

### Step 1: Download the Application
Click the download link above to get the JAR file.

### Step 2: Prepare Your Settings
You need to provide:
- Your Edupage URL (e.g., `yourschool.edupage.org`)
- Your Edupage username
- Your Edupage password
- Location where photos should be saved

### Step 3: Run the Application

Open Terminal (Mac/Linux) or Command Prompt (Windows) and run:

```bash
java -Dedupage.url=yourschool.edupage.org \
     -Dedupage.username=your_username \
     -Dedupage.password=your_password \
     -Dphotos.location=/path/to/save/photos \
     -jar edupage-photo-downloader-1.0.0.jar
```