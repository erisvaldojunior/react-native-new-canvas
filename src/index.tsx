import React from 'react';
import ReactNative, {
  PanResponder,
  PixelRatio,
  Platform,
  UIManager,
  ViewStyle,
  processColor,
  requireNativeComponent,
} from 'react-native';

type NewCanvasProps = {
  color: string;
  style: ViewStyle;
};

const NewCanvasView = requireNativeComponent<NewCanvasProps>('NewCanvasView');

const NewCanvas: React.FC<NewCanvasProps> = ({
  color,
  style,
}: NewCanvasProps) => {
  let _currentPath: any = null;
  let _handle: any = null;
  let _paths: any = [];
  let _pathsById: any = {};
  let _points: any = [];

  const _pathsToProcess = [];
  const _screenScale = Platform.OS === 'ios' ? 1 : PixelRatio.get();
  const _size = { height: 0, width: 0 };
  const _initialized = false;

  // Props parameters
  const props = {
    strokeWidth: 30,
    user: null,
  };

  const addPoint = (pathId: any, x: number, y: number) => {
    const pathData = _pathsById[pathId];
    if (!pathData) {
      return;
    }
    UIManager.dispatchViewManagerCommand(
      _handle,
      UIManager.getViewManagerConfig('NewCanvasView').Commands.addPoint,
      [
        pathId,
        parseFloat(x.toFixed(2) * _screenScale),
        parseFloat(y.toFixed(2) * _screenScale),
      ]
    );
    const point = `${x.toFixed(2)},${y.toFixed(2)}`;
    pathData.path.data.push(point);
    _points.push([pathId, point]);
  };

  const createNewPath = (strokeColor: any, strokeWidth: any) => {
    const path = {
      id: Number(Math.random() * 100000000),
      color: strokeColor,
      width: strokeWidth,
      data: [],
    };
    UIManager.dispatchViewManagerCommand(
      _handle,
      UIManager.getViewManagerConfig('NewCanvasView').Commands.createNewPath,
      [path.id, processColor(path.color), path.width * _screenScale]
    );
    const pathData = {
      path: path,
      size: _size,
      finished: false,
      drawer: props.user,
    };
    _pathsById[path.id] = pathData;
    _paths.push(pathData);
    return path;
  };

  const panResponder = React.useRef(
    PanResponder.create({
      // Ask to be the responder:
      onStartShouldSetPanResponder: (evt, gestureState) => {
        return true;
      },
      onStartShouldSetPanResponderCapture: (evt, gestureState) => {
        return true;
      },
      onMoveShouldSetPanResponder: (evt, gestureState) => {
        return true;
      },
      onMoveShouldSetPanResponderCapture: (evt, gestureState) => {
        return true;
      },
      onPanResponderGrant: (evt, gestureState) => {
        const nativeEvent = evt.nativeEvent;
        _currentPath = createNewPath(color, props.strokeWidth);
        addPoint(_currentPath.id, nativeEvent.locationX, nativeEvent.locationY);
        //        this.props.onStrokeStart({ path: this._currentPath, size: this._size, drawer: this.props.user })
        console.log('onPanResponderGrant RN');
      },
      onPanResponderMove: (evt, gestureState) => {
        console.log('onPanResponderMove RN');
      },
      onPanResponderRelease: (evt, gestureState) => {
        console.log('onPanResponderRelease RN');
      },
      onShouldBlockNativeResponder: (evt, gestureState) => {
        return true;
      },
    })
  ).current;

  console.log('Test NewCanvas 7');

  return (
    <NewCanvasView
      ref={(ref) => {
        _handle = ReactNative.findNodeHandle(ref);
      }}
      color={color}
      style={style}
      {...panResponder.panHandlers}
    />
  );
};

export default NewCanvas;
