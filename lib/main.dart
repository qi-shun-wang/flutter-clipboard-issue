import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: Material(
        child: Padding(
          padding: EdgeInsets.only(top: 50),
          child: SingleChildScrollView(
            child: TextField(
              decoration: InputDecoration.collapsed(hintText: 'Paste here!'),
              keyboardType: TextInputType.multiline,
              minLines: 1,
              maxLines: 20,
            ),
          ),
        ),
      ),
    );
  }
}
