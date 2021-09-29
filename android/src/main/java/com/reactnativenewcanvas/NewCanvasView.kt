package com.reactnativenewcanvas

import android.graphics.*
import android.view.View
import com.facebook.react.uimanager.ThemedReactContext
import org.json.JSONObject

class NewCanvasView : View {
  private val context: ThemedReactContext
  private val newCanvas: NewCanvas;

  private var drawingBitmap: Bitmap? = null

  var drawingCanvas: Canvas? = null

  constructor(themedReactContext: ThemedReactContext) : super(themedReactContext) {
    context = themedReactContext;
    newCanvas = NewCanvas(this);
  }

  fun canvasSetStyle(width: Int, height: Int) {
    newCanvas.width = width
    newCanvas.height = height
  }

  fun contextBeginPath() {
    val context = newCanvas.getContext("2d")
    context!!.beginPath()
  }

  fun contextClosePath() {
    val context = newCanvas.getContext("2d")
    context!!.closePath()
  }

  fun contextLineTo(x: Double, y: Double) {
    val context = newCanvas.getContext("2d")
    context!!.lineTo(x.toFloat(), y.toFloat())
  }

  fun contextMoveTo(x: Double, y: Double) {
    val context = newCanvas.getContext("2d")
    context!!.moveTo(x.toFloat(), y.toFloat())
  }

  fun contextStroke(contextAttributes: String) {
    val contextAttributesObject = JSONObject(contextAttributes);
    val context = newCanvas.getContext("2d")
    context!!.stroke(contextAttributesObject)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    if (drawingBitmap != null) {
      val paint = newCanvas.getContext("2d")!!.getPaint()
      canvas?.drawBitmap(drawingBitmap!!, 0.toFloat(), 0.toFloat(), paint)
    }
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    drawingBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888)
    val localBitmap = drawingBitmap
    drawingCanvas = localBitmap?.let { Canvas(it) }
    invalidate()
  }

}
