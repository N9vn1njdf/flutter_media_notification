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
  String active = "";

  @override
  void initState() {
    super.initState();

    getIsActive();
  }

  Future<void> getIsActive() async {
    try {
      String result = await MediaNotification.active;
      setState(() {
        active = result;
      });
    } on PlatformException {
      setState(() {
        active = 'error';
      });
    }
  }

  Future<void> hide() async {
    try {
      await MediaNotification.hide();
    } on PlatformException {

    }
    getIsActive();
  }

  Future<void> show() async {
    try {
      await MediaNotification.show();
    } on PlatformException {

    }
    getIsActive();
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          title: const Text('Plugin example app'),
        ),
        body: new Center(
          child: Column(
            children: <Widget>[
              FlatButton(
                child: Text('Показать уведомление'),
                onPressed: show,
              ),
              FlatButton(
                child: Text('Скрыть уведомление'),
                onPressed: hide,
              ),
              Text('Статус: ' + active)
            ],
          )
        ),
      ),
    );
  }
}
