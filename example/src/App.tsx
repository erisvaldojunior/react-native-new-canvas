import React from 'react';
import { StyleSheet, View } from 'react-native';
import NewCanvas from 'react-native-new-canvas';
import { useSimplePencil } from './CanvasExamples/SimplePencil';

export default function App() {
  const canvasRef: any = React.useRef(null);
  const simplePencil = useSimplePencil();

  const initCanvas = (canvas: any) => {
    if (canvasRef?.current) {
      return;
    }
    canvasRef.current = canvas;
  };

  const draw = (evt: any) => {
    simplePencil.draw(canvasRef.current, evt);
  };

  const exitDraw = (evt: any) => {
    simplePencil.exitDraw();
  };

  const startDraw = (evt: any) => {
    simplePencil.startDraw(canvasRef.current, evt);
  };

  return (
    <View style={styles.container}>
      <NewCanvas
        onCanvasCreate={initCanvas}
        onTouchStart={(evt) => {
          startDraw(evt);
        }}
        onTouchMove={(evt) => {
          draw(evt);
        }}
        onTouchEnd={(evt) => {
          exitDraw(evt);
        }}
        style={styles.newCanvasStyle}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  canvasContainer: {
    flex: 1,
  },
  container: {
    alignItems: 'center',
    backgroundColor: 'gray',
    flex: 1,
    justifyContent: 'center',
  },
  newCanvasStyle: {
    backgroundColor: '#fff',
    height: 750,
    width: 350,
  },
});
