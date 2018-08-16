import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:media_notification/media_notification.dart';

void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String status = 'неактивно';

  @override
  void initState() {
    super.initState();

    MediaNotification.setListener('pause', () {
      setState(() => status = 'пауза');
    });

    MediaNotification.setListener('play', () {
      setState(() => status = 'воспроизведение');
    });
    
    MediaNotification.setListener('next', () {
      
    });

    MediaNotification.setListener('prev', () {
      
    });

    MediaNotification.setListener('select', () {
      
    });
  }

  Future<void> hide() async {
    try {
      await MediaNotification.hide();
    } on PlatformException {

    }
  }

  Future<void> show(title, author) async {
    try {
      await MediaNotification.show(title: title, author: author);
      setState(() => status = 'воспроизведение');
    } on PlatformException {

    }
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          title: const Text('Plugin example app'),
        ),
        body: new Center(
          child: Container(
            height: 200.0,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                FlatButton(
                  child: Text('Показать уведомление'),
                  onPressed: () => show('Заголовок', 'Автор песни'),
                ),
                FlatButton(
                  child: Text('Обновить уведомление'),
                  onPressed: () => show('Новый заголовок', 'другой автор песни'),
                ),
                FlatButton(
                  child: Text('Скрыть уведомление'),
                  onPressed: hide,
                ),
                Text(status)
              ],
            ),
          )
        ),
      ),
    );
  }
}
