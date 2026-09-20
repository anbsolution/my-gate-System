# My Gate Android V1.0

Android WebView application for the My Gate Company Gate Management System.

## Backend
https://selfpayroll.site.je

The APK uses the existing InfinityFree + PHP + MySQL backend. No new database is created.

## GitHub APK build

1. Create a GitHub repository, for example `my-gate-android`.
2. Upload all files from this project.
3. Push to the `main` branch.
4. Open **Actions** → **Build My Gate APK**.
5. When the workflow finishes, open the run and download the artifact:
   `My-Gate-Android-V1.0`
6. Extract it and install `app-debug.apk` on Android.

The debug APK is signed with Android's debug key so it is directly installable for testing.

## Important
- This project does not contain the MySQL password.
- Do not import `database.sql` for this Android project.
- The app connects only to the existing HTTPS My Gate website.
- The current app URL includes `?v=182` to avoid serving an older cached web page.
