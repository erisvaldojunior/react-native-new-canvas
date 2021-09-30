import React, { useEffect } from 'react';
import ReactNative, {
  PanResponder,
  ViewStyle,
  requireNativeComponent,
} from 'react-native';
import Canvas from './api/Canvas';

type NewCanvasProps = {
  onCanvasCreate?: (canvas: Canvas) => void;
  onLayout?: (evt: any) => void;
  onTouchStart?: (evt: any) => void;
  onTouchMove?: (evt: any) => void;
  onTouchEnd?: (evt: any) => void;
  style: ViewStyle;
};

const NewCanvasView = requireNativeComponent<NewCanvasProps>('NewCanvasView');

export let _handle: any = null;

const NewCanvas: React.FC<NewCanvasProps> = ({
  onCanvasCreate,
  onTouchStart,
  onTouchMove,
  onTouchEnd,
  style,
}: NewCanvasProps) => {
  // Props parameters
  const props = {
    strokeWidth: 30,
    user: null,
  };

  let canvasRef = React.useRef<Canvas>();

  useEffect(() => {
    canvasRef.current = new Canvas(style);
    onCanvasCreate(canvasRef.current);
  }, []);

  const panResponder = React.useRef(
    PanResponder.create({
      // Ask to be the responder:
      onMoveShouldSetPanResponder: (evt, gestureState) => {
        return true;
      },
      onMoveShouldSetPanResponderCapture: (evt, gestureState) => {
        return true;
      },
      onPanResponderGrant: (evt, gestureState) => {
        if (onTouchStart) {
          onTouchStart(evt);
        }
      },
      onPanResponderMove: (evt, gestureState) => {
        if (onTouchMove) {
          onTouchMove(evt);
        }
      },
      onPanResponderRelease: (evt, gestureState) => {
        if (onTouchEnd) {
          onTouchEnd(evt);
        }
      },
      onShouldBlockNativeResponder: (evt, gestureState) => {
        return true;
      },
      onStartShouldSetPanResponder: (evt, gestureState) => {
        return true;
      },
      onStartShouldSetPanResponderCapture: (evt, gestureState) => {
        return true;
      },
    })
  ).current;

  const onLayout = (evt: any) => {
    const { x, y } = evt.nativeEvent.layout;
    const canvas = canvasRef.current;
    if (canvas) {
      canvas.setBoundingClientRect({
        left: x,
        top: y,
      });
    }
  };

  return (
    <NewCanvasView
      ref={(ref) => {
        _handle = ReactNative.findNodeHandle(ref);
      }}
      onLayout={onLayout}
      style={style}
      {...panResponder.panHandlers}
    />
  );
};

export default NewCanvas;
