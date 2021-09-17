package com.reactnativenewcanvas

import android.graphics.Color
import android.util.Log
import android.view.View
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class NewCanvasViewManager : SimpleViewManager<View>() {

  companion object {
    lateinit var newCanvasView: NewCanvasView
    const val COMMAND_ADD_POINT = 1
    const val COMMAND_CREATE_NEW_PATH = 2
  }

  override fun getName() = "NewCanvasView"

  override fun createViewInstance(reactContext: ThemedReactContext): View {
    newCanvasView = NewCanvasView(reactContext);
    return newCanvasView
  }

  @ReactProp(name = "color")
  fun setColor(view: View, color: String) {
    view.setBackgroundColor(Color.parseColor("blue"))
  }

  override fun getCommandsMap(): MutableMap<String, Int> {
    return mutableMapOf(
      "addPoint" to COMMAND_ADD_POINT,
      "createNewPath" to COMMAND_CREATE_NEW_PATH
    );
  }

  override fun receiveCommand(root: View, commandId: Int, args: ReadableArray?) {
    when (commandId) {
      COMMAND_ADD_POINT -> {
        Log.d("NewCanvasViewManager", "COMMAND_ADD_POINT")
        if (args != null && root is NewCanvasView) {
          Log.d("NewCanvasViewManager", args.toString());
          root.addPoint(args.getInt(0), args.getDouble(1), args.getDouble(2))
        }
      }
      COMMAND_CREATE_NEW_PATH -> {
        Log.d("NewCanvasViewManager", "COMMAND_CREATE_NEW_PATH")
        if (args != null && root is NewCanvasView) {
          Log.d("NewCanvasViewManager", args.toString());
          root.createNewPath(args.getInt(0), args.getInt(1), args.getDouble(2))
        }
      }
    }
  }
}
