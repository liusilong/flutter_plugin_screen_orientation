import 'dart:async';

import 'package:flutter/services.dart';

enum ScreenOrientation {
  /// 竖屏
  portrait,

  /// 横屏
  landscape
}

class PluginScreenOrientation {
  static const MethodChannel _channel =
      const MethodChannel('plugin_screen_orientation');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static void setScreenOrientation(ScreenOrientation orientation) {
    Map<String, String> args = {};
    if (orientation == ScreenOrientation.landscape) {
      args['orientation'] = 'landscape';
    } else {
      args['orientation'] = 'portrait';
    }
    _channel.invokeMethod('setScreenOrientation', args);
  }
}
