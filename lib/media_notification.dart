import 'dart:async';

import 'package:flutter/services.dart';

class MediaNotification {
  static const MethodChannel _channel =
      const MethodChannel('media_notification');

  static Future show() async {
    await _channel.invokeMethod('getPlatformVersion');
  }
}
