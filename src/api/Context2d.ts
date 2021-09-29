import { UIManager } from 'react-native';
import { _handle } from 'react-native-new-canvas';

class Context2d {
  lineCap: string = 'round';
  lineJoin: string = 'round';
  lineWidth: number = 5;
  strokeStyle: string = '#000';

  beginPath() {
    UIManager.dispatchViewManagerCommand(
      _handle,
      UIManager.getViewManagerConfig('NewCanvasView').Commands.contextBeginPath,
      []
    );
  }

  closePath() {
    UIManager.dispatchViewManagerCommand(
      _handle,
      UIManager.getViewManagerConfig('NewCanvasView').Commands.contextClosePath,
      []
    );
  }

  lineTo(x: number, y: number) {
    UIManager.dispatchViewManagerCommand(
      _handle,
      UIManager.getViewManagerConfig('NewCanvasView').Commands.contextLineTo,
      [x, y]
    );
  }

  moveTo(x: number, y: number) {
    UIManager.dispatchViewManagerCommand(
      _handle,
      UIManager.getViewManagerConfig('NewCanvasView').Commands.contextMoveTo,
      [x, y]
    );
  }

  stroke() {
    const contextAttributes = this.getContextAttributes();
    UIManager.dispatchViewManagerCommand(
      _handle,
      UIManager.getViewManagerConfig('NewCanvasView').Commands.contextStroke,
      [JSON.stringify(contextAttributes)]
    );
  }

  private getContextAttributes = () => {
    const contextAttributes: any = {};
    contextAttributes.lineCap = this.lineCap;
    contextAttributes.lineJoin = this.lineJoin;
    contextAttributes.lineWidth = this.lineWidth;
    contextAttributes.strokeStyle = this.strokeStyle;
    return contextAttributes;
  };
}

export default Context2d;
