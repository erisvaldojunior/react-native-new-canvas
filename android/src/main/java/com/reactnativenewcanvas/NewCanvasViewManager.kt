package com.reactnativenewcanvas

import android.util.Log
import android.view.View
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext

class NewCanvasViewManager : SimpleViewManager<View>() {

  companion object {
    lateinit var newCanvasView: NewCanvasView

    const val CANVAS_SET_STYLE = 91

    const val CONTEXT_BEGIN_PATH = 101
    const val CONTEXT_CLOSE_PATH = 102
    const val CONTEXT_LINE_TO = 103
    const val CONTEXT_MOVE_TO = 104
    const val CONTEXT_STROKE = 105
  }

  override fun getName() = "NewCanvasView"

  override fun createViewInstance(reactContext: ThemedReactContext): View {
    newCanvasView = NewCanvasView(reactContext);
    return newCanvasView
  }

  override fun getCommandsMap(): MutableMap<String, Int> {
    Log.d("NewCanvasViewManager", "getCommandsMap()")
    return mutableMapOf(
      "canvasSetStyle" to CANVAS_SET_STYLE,

      "contextBeginPath" to CONTEXT_BEGIN_PATH,
      "contextClosePath" to CONTEXT_CLOSE_PATH,
      "contextLineTo" to CONTEXT_LINE_TO,
      "contextMoveTo" to CONTEXT_MOVE_TO,
      "contextStroke" to CONTEXT_STROKE
    );
  }

  override fun receiveCommand(root: View, commandId: Int, args: ReadableArray?) {
    when (commandId) {
      CANVAS_SET_STYLE -> {
        if (args != null && root is NewCanvasView) {
          Log.d("NewCanvasViewManager", args.toString());
          root.canvasSetStyle(args.getInt(0), args.getInt(1))
        }
      }
      CONTEXT_BEGIN_PATH -> {
        if (args != null && root is NewCanvasView) {
          root.contextBeginPath()
        }
      }
      CONTEXT_CLOSE_PATH -> {
        if (args != null && root is NewCanvasView) {
          root.contextClosePath()
        }
      }
      CONTEXT_LINE_TO -> {
        if (args != null && !args.isNull(0)  && !args.isNull(1) && root is NewCanvasView) {
          root.contextLineTo(args.getDouble(0), args.getDouble(1))
        }
      }
      CONTEXT_MOVE_TO -> {
        if (args != null && !args.isNull(0)  && !args.isNull(1) && root is NewCanvasView) {
          root.contextMoveTo(args.getDouble(0), args.getDouble(1))
        }
      }
      CONTEXT_STROKE -> {
        if (args != null && !args.isNull(0) && root is NewCanvasView) {
          root.contextStroke(args.getString(0)!!)
        }
      }
    }
  }
}
