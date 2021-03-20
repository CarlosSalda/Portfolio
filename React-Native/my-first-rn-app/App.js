import { StatusBar } from 'expo-status-bar';
import React, {useState} from 'react';
import { Button, StyleSheet, Text, View } from 'react-native';

export default function App() {

  const [outPutText, setOutPutText] = useState('Open up App.js to start working on your app!');

  return (
    <View style={styles.container}>
      <Text>{outPutText}</Text>
      <Button title="Change Text" onPress={() => setOutPutText('The Text Changed')}/>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
