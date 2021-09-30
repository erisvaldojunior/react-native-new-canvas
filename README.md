# react-native-new-canvas

Modern iOS/Android Native Canvas component for React Native (uses SwiftUI Canvas and Kotlin android.graphics.Canvas under the hood).

It also has the same API of HTML ```<canvas>```, which is great for cross-platform projects (Android/iOS/Web) because they can use pretty much the same code just replacing ```<NewCanvas>``` for ```<canvas>``` on Web.

## Installation

```sh
npm install react-native-new-canvas
```

## Usage

```js
import NewCanvas from 'react-native-new-canvas';

export default function App() {
  const canvasRef: any = React.useRef(null);

  const initCanvas = (canvas: any) => {
    if (canvasRef?.current) {
      return;
    }
    canvasRef.current = canvas;

    // Example code (drawing a line after 3 seconds)
    setTimeout(() => {
      const context = canvas.getContext('2d');
      context.strokeStyle = '#FF0000';
      context.lineJoin = 'round';
      context.lineWidth = 30;
      context.beginPath();
      context.moveTo(20, 20);
      context.lineTo(100, 100);
      context.closePath();
      context.stroke();
    }, 3000);
  };

  const onTouchMove = (evt: any) => {
    // Your code here
  };

  const onTouchEnd = (evt: any) => {
    const context = canvasRef.current.getContext('2d');
    // Your code here
  };

  const onTouchStart = (evt: any) => {
    const context = canvasRef.current.getContext('2d');
    // Your code here
  };

  return (
    <View style={styles.container}>
      <NewCanvas
        onCanvasCreate={initCanvas}
        onTouchStart={(evt) => {
          onTouchStart(evt);
        }}
        onTouchMove={(evt) => {
          onTouchMove(evt);
        }}
        onTouchEnd={(evt) => {
          onTouchEnd(evt);
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
```

## API

1. Context

| API Name                 | API Type  | Status          |
| ------------------------ | --------- | --------------- |
| lineCap                  | Attribute | Implemented     |
| lineJoin                 | Attribute | Implemented     |
| lineWidth                | Attribute | Implemented     |
| strokeStyle              | Attribute | Implemented     |
| beginPath()              | Method    | Implemented     |
| closePath()              | Method    | Implemented     |
| lineTo()                 | Method    | Implemented     |
| moveTo()                 | Method    | Implemented     |
| stroke()                 | Method    | Implemented     |
| fillStyle                | Attribute | Not Implemented |
| font                     | Attribute | Not Implemented |
| globalAlpha              | Attribute | Not Implemented |
| globalCompositeOperation | Attribute | Not Implemented |
| miterLimit               | Attribute | Not Implemented |
| shadowColor              | Attribute | Not Implemented |
| shadowBlur               | Attribute | Not Implemented |
| shadowOffsetX            | Attribute | Not Implemented |
| shadowOffsetY            | Attribute | Not Implemented |
| textAlign                | Attribute | Not Implemented |
| textBaseline             | Attribute | Not Implemented |
| addColorStop()           | Method    | Not Implemented |
| createEvent()            | Method    | Not Implemented |
| createLinearGradient()   | Method    | Not Implemented |
| createPattern()          | Method    | Not Implemented |
| createRadialGradient()   | Method    | Not Implemented |
| isPointInPath()          | Method    | Not Implemented |
| toDataURL()              | Method    | Not Implemented |
| arc()                    | Method    | Not Implemented |
| arcTo()                  | Method    | Not Implemented |
| bezierCurveTo()          | Method    | Not Implemented |
| capture()                | Method    | Not Implemented |
| clearRect()              | Method    | Not Implemented |
| clip()                   | Method    | Not Implemented |
| createImageData()        | Method    | Not Implemented |
| drawImage()              | Method    | Not Implemented |
| fill()                   | Method    | Not Implemented |
| fillRect()               | Method    | Not Implemented |
| getContext()             | Method    | Not Implemented |
| getImageData()           | Method    | Not Implemented |
| loadTexture()            | Method    | Not Implemented |
| measureText()            | Method    | Not Implemented |
| rect()                   | Method    | Not Implemented |
| strokeRect()             | Method    | Not Implemented |
| quadraticCurveTo()       | Method    | Not Implemented |
| scale()                  | Method    | Not Implemented |
| rotate()                 | Method    | Not Implemented |
| translate()              | Method    | Not Implemented |
| transform()              | Method    | Not Implemented |
| setTransform()           | Method    | Not Implemented |
| fillText()               | Method    | Not Implemented |
| strokeText()             | Method    | Not Implemented |
| putImageData()           | Method    | Not Implemented |
| save()                   | Method    | Not Implemented |
| restore()                | Method    | Not Implemented |
| unloadTexture()          | Method    | Not Implemented |
| resetTransform()         | Method    | Not Implemented |
| render()                 | Method    | Not Implemented |
| resetClip()              | Method    | Not Implemented |

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

# react-native-new-canvas
