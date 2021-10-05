import { UIManager } from 'react-native';
import { _handle } from 'react-native-new-canvas';
import Context2d from './Context2d';

class Canvas {
  private context2d?: Context2d;

  private boundingClientRect = {
    left: 0,
    top: 0,
  };

  constructor(style?: any) {
    if (style.width && style.height) {
      UIManager.dispatchViewManagerCommand(
        _handle,
        UIManager.getViewManagerConfig('NewCanvasView').Commands.canvasSetStyle,
        [style.width, style.height]
      );
      /*
      UIManager.dispatchViewManagerCommand(
        _handle,
        UIManager.getViewManagerConfig('NewCanvasView').Commands.test,
        []
      );
      */
    }
  }

  getBoundingClientRect = () => {
    return this.boundingClientRect;
  };

  getContext = (contextType: string) => {
    if (contextType.toLowerCase() === '2d') {
      if (!this.context2d) {
        this.context2d = new Context2d();
      }
      return this.context2d;
    }
    return null;
  };

  setBoundingClientRect = (rect: any) => {
    this.boundingClientRect = rect;
  };
}

export default Canvas;
