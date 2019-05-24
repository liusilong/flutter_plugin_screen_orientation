package com.lsl.plugin_screen_orientation;

import android.content.pm.ActivityInfo;
import android.text.TextUtils;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * PluginScreenOrientationPlugin
 */
public class PluginScreenOrientationPlugin implements MethodCallHandler {
    private Registrar registrar;
    private MethodChannel methodChannel;

    public PluginScreenOrientationPlugin(Registrar registrar, MethodChannel methodChannel) {
        this.registrar = registrar;
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "plugin_screen_orientation");
        channel.setMethodCallHandler(new PluginScreenOrientationPlugin(registrar, channel));
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("setScreenOrientation")) {
            String orientation = call.argument("orientation");
            if (null != orientation) {
                if (orientation.equals("landscape")) {
                    registrar.activity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else if (orientation.equals("portrait")) {
                    registrar.activity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        } else if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else {
            result.notImplemented();
        }
    }
}
