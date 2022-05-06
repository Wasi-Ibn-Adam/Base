package com.wasitech.basics.classes;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.FileProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wasitech.basics.Storage;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.content.Context.BLUETOOTH_SERVICE;

public class Basics {
    private static String data;
    private static final String TAG = "founder";

    public static void Log(String text) {
        Log.i(TAG, text);
    }

    public static class AudioVideo {
        public static final int AUD_STOPPED = 1;
        public static final int VID_STOPPED = 1;
        public static final int AUD_ERROR = -1;
        public static final int AUD_ALREADY = 0;
        public static final int AUD_PAUSE = 0;
        public static final int AUD_PLAY = 1;
        public static final int AUD_NEXT = 2;
        public static final int AUD_PREV = 3;
        public static final int AUD_STOP = 4;

        public static void Vibrator(Context context, Long sec) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(sec, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(sec);
            }
        }

        public static int stopAudio(Context context) {
            AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (am.isMusicActive()) {
                AudioManager.OnAudioFocusChangeListener focusChangeListener = i -> {
                };
                int result = am.requestAudioFocus(focusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                am.abandonAudioFocus(focusChangeListener);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                    return AUD_STOPPED;
                else {
                    return AUD_ERROR;
                }
            } else {
                return AUD_ALREADY;
            }
        }

        public static void audioManager(Context context, int modes) {
            boolean yes = Build.MANUFACTURER.toLowerCase().contains("samsung");
            if (yes) {
                Intent i = null;
                switch (modes) {
                    case AUD_PLAY: {
                        i = new Intent("com.sec.android.app.music.musicservicecommand.play");
                        break;
                    }
                    case AUD_NEXT: {
                        i = new Intent("com.sec.android.app.music.musicservicecommand.next");
                        break;
                    }
                    case AUD_PAUSE: {
                        i = new Intent("com.sec.android.app.music.musicservicecommand.pause");
                        break;
                    }
                    case AUD_PREV: {
                        i = new Intent("com.sec.android.app.music.musicservicecommand.previous");
                        break;
                    }
                    case AUD_STOP: {
                        i = new Intent("com.sec.android.app.music.musicservicecommand.stop");
                        break;
                    }
                }
                if (i != null)
                    context.getApplicationContext().sendBroadcast(i);
            } else {
                Intent i = new Intent("com.android.music.musicservicecommand");
                switch (modes) {
                    case AUD_PLAY: {
                        i.putExtra("command", "play");
                        break;
                    }
                    case AUD_NEXT: {
                        i.putExtra("command", "next");
                        break;
                    }
                    case AUD_PAUSE: {
                        i.putExtra("command", "pause");
                        break;
                    }
                    case AUD_PREV: {
                        i.putExtra("command", "previous");
                        break;
                    }
                    case AUD_STOP: {
                        i.putExtra("command", "stop");
                        break;
                    }
                }
                if (i.getStringExtra("command") != null)
                    context.getApplicationContext().sendBroadcast(i);

            }
        }

        public static int stopVideo(Context context) {
            MediaController controller = new MediaController(context);
            controller.setVisibility(View.GONE);
            controller.setMediaPlayer(null);
            //stopAudio(context);
            return VID_STOPPED;
        }
    }

    public static class BlueTooth {
        public static final int ENABLED = 0;
        public static final int CONNECTED = 0;
        public static final int ALREADY_ENABLED = 1;
        public static final int DISABLED = 2;
        public static final int DISCONNECTED = 2;
        public static final int ALREADY_DISABLED = 3;
        public static final int ERROR = -1;
        public static final int BLUETOOTH_CONNECTION_ERROR = -2;

        public static int set(boolean choice) {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            boolean isEnabled = bluetoothAdapter.isEnabled();
            if (isEnabled && choice) {
                return ALREADY_ENABLED;
            }
            if (choice) {
                if (bluetoothAdapter.enable())
                    return ENABLED;
                else
                    return ERROR;
            }
            if (!isEnabled) {
                return ALREADY_DISABLED;
            }
            if (bluetoothAdapter.disable())
                return DISABLED;
            else
                return ERROR;
        }

        public static boolean isConnected(Context context) {
            BluetoothManager manager = (BluetoothManager) context.getSystemService(BLUETOOTH_SERVICE);
            List<BluetoothDevice> connected = manager.getConnectedDevices(BluetoothProfile.GATT_SERVER);
            return connected.size() > 0;
        }

        public static int connected(Context context) {
            if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                if (isConnected(context)) {
                    return CONNECTED;
                } else {
                    return BLUETOOTH_CONNECTION_ERROR;//"No Device is Connected with Bluetooth.";
                }
            } else {
                return DISCONNECTED;//"Bluetooth is Not Enabled."
            }
        }

        public static void connection(Context context) {
            set(true);
            context.startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    public static class WIFI {
        public static String set(Context context, boolean choice) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            boolean isEnabled = wifiManager.isWifiEnabled();
            if (isEnabled && choice) {
                return "Already Enabled.";
            }
            if (choice) {
                if (wifiManager.setWifiEnabled(true))
                    return "Enabled.";
                else
                    return "Unable to Enable.";
            }
            if (!isEnabled) {
                return "Already Disabled.";
            }
            if (wifiManager.setWifiEnabled(false))
                return "Disabled.";
            else
                return "Unable to disable.";
        }

        public static boolean isConnected(Context context) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            return wifiManager.isWifiEnabled();
        }
    }

    public static class Img {
        public static Bitmap parseBitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            Bitmap bitmap;
            if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }

        public static void saveBitmap(Bitmap bitmap) {
            try {
                OutputStream out = new FileOutputStream(Storage.CreateDataFile(Storage.IMG, ".jpg"));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (IOException e) {
                Issue.print(e, Img.class.getName());
            }
        }

        public static void saveBitmap(Bitmap bitmap, String fileName) {
            try {
                OutputStream out = new FileOutputStream(Storage.CreateDataFile(Storage.IMG, fileName, ".jpg"));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (IOException e) {
                Issue.print(e, Img.class.getName());
            }
        }

        public static Bitmap downloadBitmap(String url) {
            try {
                URL url1 = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                connection.setDoInput(true);
                connection.connect();
                return BitmapFactory.decodeStream(connection.getInputStream());
            } catch (IOException e) {
                Issue.print(e, Img.class.getName());
            }
            return null;
        }

        public static Bitmap parseBitmap(byte[] array) {
            if (array != null)
                return BitmapFactory.decodeByteArray(array, 0, array.length);
            return null;
        }

        public static Bitmap fromPath(String imgPath) {
            return BitmapFactory.decodeFile(imgPath);
        }

        public static Bitmap videoThumbNail(String path) {
            return ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MICRO_KIND);
        }

        public static void saveDrawable(Drawable drawable) {
            Bitmap bitmap = parseBitmap(drawable);
            if (bitmap != null) saveBitmap(bitmap);
        }

        public static File saveFile(byte[] bytes) {
            File photo = Storage.CreateDataFile(Storage.IMG, ".png");
            try {
                FileOutputStream fos = new FileOutputStream(photo);
                fos.write(bytes);
                fos.close();
            } catch (IOException e) {
                Issue.print(e, Img.class.getName());
            }
            return photo;
        }

        public static File parseFile(byte[] bytes) {
            File photo = Storage.CreateDataFile(Storage.IMG, ".png");
            try {
                FileOutputStream fos = new FileOutputStream(photo);
                fos.write(bytes);
                fos.close();
            } catch (IOException e) {
                Issue.print(e, Img.class.getName());
            }
            return photo;
        }

        public static byte[] parseBytes(Bitmap map) {
            if (map != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                map.compress(Bitmap.CompressFormat.PNG, 100, stream);
                return stream.toByteArray();
            }
            return null;
        }

        public static void saveBytes(byte[] array) {
            if (array != null)
                saveBitmap(BitmapFactory.decodeByteArray(array, 0, array.length));
        }

        public static void mediaScanner(Context context, String filePath) {
            MediaScannerConnection.scanFile(context.getApplicationContext(), new String[]{filePath}, null, (path, uri) -> {
            });
        }

        public static void mediaScanner(Context context, String filePath, String success, String failed) {
            MediaScannerConnection.scanFile(context.getApplicationContext(), new String[]{filePath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    if (path != null && new File(path).exists()) toasting(context, success);
                    else toasting(context, failed);
                }
            });
        }
    }

    public static class Internet {
        private static int number = 1;

        private static boolean isConnected(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo temp = connectivityManager.getActiveNetworkInfo();
            if (temp != null && temp.isConnected()) {
                temp.getDetailedState();
                return true;
            } else {
                toasting(context, "Internet Not Connected");
                return false;
            }
        }

        public static boolean isInternetJson(Context context) {
            if (Internet.number == 1) {
                Basics.data = " try run";
            }
            if (isConnected(context)) {
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/" + (Internet.number), null,
                        response -> {
                            try {
                                Basics.data = response.getString("title");
                            } catch (JSONException e) {
                                Issue.print(e, Internet.class.getName());
                            }
                        }, Issue::Internet));
                if (Internet.number == 200) {
                    Internet.number = 1;
                } else {
                    Internet.number++;
                }
                if (Basics.data != null) return true;
                else toasting(context, "Make Sure You Have An Active Internet Connection.");
            }
            return false;
        }

        public static Intent intentGPS() {
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            return poke;
        }

        public static boolean canToggleGPS(Context context) {
            PackageManager pacman = context.getPackageManager();
            PackageInfo pacInfo;
            try {
                pacInfo = pacman.getPackageInfo("com.android.settings", PackageManager.GET_RECEIVERS);
            } catch (PackageManager.NameNotFoundException e) {
                Issue.print(e, Internet.class.getName());
                return false;
            }
            if (pacInfo != null) {
                for (ActivityInfo actInfo : pacInfo.receivers) {
                    if (actInfo.name.equals("com.android.settings.widget.SettingsAppWidgetProvider") && actInfo.exported) {
                        return true;
                    }
                }
            }

            return false; //default
        }
    }

    public static class Send {
        public static void share(Context context, String path) {
            if (path == null) return;
            try {
                Uri uri = FileProvider.getUriForFile(context, "com.wasitech.basics.FileProvider", new File(path));
                if (uri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                    shareIntent.setDataAndType(uri, context.getContentResolver().getType(uri));
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    context.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
                }
            } catch (Exception e) {
                Issue.print(e, Basics.class.getName());
            }
        }
    }


    public static WindowManager.LayoutParams WindowServicesParams(int posX, int posY, int gravitySide) {
        WindowManager.LayoutParams params;
        {
            int LAYOUT_FLAG;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
            }
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    LAYOUT_FLAG,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
            params.gravity = Gravity.TOP | gravitySide;
            params.x = posX;
            params.y = posY;
        }
        return params;
    }

    public static String FlashLight(Context context, boolean enable) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            return "As far I know, Your phone don't have any.";
        }
        CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

        boolean done;
        try {
            manager.setTorchMode((manager.getCameraIdList())[0], enable);
            done = true;
        } catch (CameraAccessException e) {
            done = false;
            Issue.print(e, Basics.class.getName());
        }
        if (done) {
            if (enable)
                return "FlashLight On.";
            else
                return "FlashLight Off.";
        }
        return "Sorry, Something went wrong.";
    }

    public static String LockPhone(Context context, ComponentName cm, boolean lock) {
        DevicePolicyManager deviceManger = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        if (lock) {
            if (deviceManger.isAdminActive(cm)) {
                deviceManger.lockNow();
                return "Phone Locked.";
            } else {
                return "Error Found.";
            }
        } else {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP
                    | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
            wakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);
        }
        return "";
    }

    public static boolean isNLServiceRunning(Context context) {
        return NotificationManagerCompat.getEnabledListenerPackages(context.getApplicationContext()).contains(context.getPackageName());
    }

    public static void toasting(Context context, String text) {
        try {
            Toast toast1 = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast1.setGravity(80, 0, 500);
            toast1.show();
        } catch (Exception e) {
            Issue.print(e, Basics.class.getName());
        }
    }

    public static boolean isMyServiceRunning(Context c, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


}
