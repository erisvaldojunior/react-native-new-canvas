package com.reactnativenewcanvas

class NewCanvas(private val newCanvasView: NewCanvasView) {
  var height: Int? = null
  var width: Int? = null

  private var context2d: NewCanvasContext2d? = null

  fun getContext(contextType: String): NewCanvasContext2d? {
    if (contextType.toLowerCase() === "2d") {
      if (context2d === null) {
        context2d = NewCanvasContext2d(this, newCanvasView);
      }
      return context2d;
    }
    return null;
  }
}
