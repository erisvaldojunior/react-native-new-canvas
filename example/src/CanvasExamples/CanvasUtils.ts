import type Canvas from 'src/api/Canvas';

export interface Position {
  x?: number;
  y?: number;
}

export const getCoordinates = (canvas: any, event: any) => {
  if (!canvas || !event) {
    return {};
  }

  let positionX;
  let positionY;

  // First check if it is a touch event.
  // Tricky because Mobile uses event.nativeEvent object,
  // while web canvas uses event.changedTouches (array, position 0).
  if (event.changedTouches) {
    // Web Canvas touch
    positionX = event.changedTouches[0]?.pageX;
    positionY = event.changedTouches[0]?.pageY;
  } else if (event.nativeEvent?.pageX && event.nativeEvent?.pageY) {
    // Either Mobile touch or Web Canvas mouse click
    positionX = event.nativeEvent?.pageX;
    positionY = event.nativeEvent?.pageY;
  } else {
    // Shouldn't happen (being super safe to catch a Web Canvas mouse click)
    positionX = event.pageX;
    positionY = event.pageY;
  }

  const rect = canvas.getBoundingClientRect();

  return {
    x: positionX - rect.left,
    y: positionY - rect.top,
  };
};

export const drawLine = (
  canvas: Canvas,
  originalPosition: any,
  newPosition: any,
  color: any,
  size: any
) => {
  if (!canvas) {
    return;
  }
  const context = canvas.getContext('2d');
  if (context) {
    context.strokeStyle = color || '#FF0000';
    context.lineJoin = 'round';
    context.lineWidth = size || 30;
    context.beginPath();
    context.moveTo(originalPosition.x, originalPosition.y);
    context.lineTo(newPosition.x, newPosition.y);
    context.closePath();
    context.stroke();
  }
};
