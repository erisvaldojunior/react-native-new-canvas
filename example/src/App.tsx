import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import NewCanvas from 'react-native-new-canvas';

export default function App() {
  const initCanvas = (canvas: any) => {
    console.log('initCanvas canvas object');
    console.log(canvas);
  };

  return (
    <View style={styles.container}>
      <NewCanvas
        color="yellow"
        onCanvasCreate={initCanvas}
        style={styles.box}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  canvasContainer: {
    flex: 1,
  },
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    height: '100%',
    width: '100%',
  },
});
