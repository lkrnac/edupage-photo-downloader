# edupage-photo-downloader

Application for bulk downloading photos from EduPage. When executed repeatedly for same download directory, it dowloads only new photos.

## Usage

### Requirements
- Java 21
- Firefix browser

### Execution
    java -Dedupage.url=<edupage-uri>.edupage.org -Dedupage.username=<username> -Dedupage.password=<password> -Dphotos.location=<photos-location> -jar edupage-photo-downloader-<version>.jar

### Example execution
    java -Dedupage.url=zsboskovice-kv.edupage.org -Dedupage.username=user@gmail.com -Dedupage.password=password -Dphotos.location=/home/user/Pictures/edupage -jar edupage-photo-downloader-1.0.0.jar
