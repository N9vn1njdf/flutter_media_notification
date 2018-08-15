import 'dart:async';

import 'package:flutter/services.dart';

class MediaNotification {
  static const MethodChannel _channel = const MethodChannel('media_notification');

  static Future<String> get active async {
    String result = await _channel.invokeMethod('isActive');
    return result;
  }

  static Future show() async {
    await _channel.invokeMethod('show');
  }

  static Future hide() async {
    await _channel.invokeMethod('hide');
  }
}
