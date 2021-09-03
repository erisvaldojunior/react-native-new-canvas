import { requireNativeComponent, ViewStyle } from 'react-native';

type NewCanvasProps = {
  color: string;
  style: ViewStyle;
};

export const NewCanvasViewManager = requireNativeComponent<NewCanvasProps>(
'NewCanvasView'
);

export default NewCanvasViewManager;
