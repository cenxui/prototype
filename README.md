# prototype
Android proxy prototype for 8.0 

## Step
- Start a background service when OS booting completed
- Run background service 
- Keep the background service runing 

## Technical
- When OS booting completed it will broadcast event "booting_completed"
- Broadcast recevicer is a component of Android which has ability to run "intent". "intent" is a starting process of Android.
- *Chanllenge* Android 8.0 only allow the foreground process to create background process(background service), so original method "startService" is not working anymore.
- *Solution* use "startForegroundService" instead, the After starting a service, we must call a method "startForground" in 5 seconds or the service will be stop. The startForeground method required a notification params to send a notification to the user that there is a service is runing in background.
  
