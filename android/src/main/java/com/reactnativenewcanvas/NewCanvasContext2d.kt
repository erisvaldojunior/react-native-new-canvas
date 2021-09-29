package com.reactnativenewcanvas

import android.graphics.*
import android.util.Log
import org.json.JSONObject

class NewCanvasContext2d(private val newCanvas: NewCanvas, private val newCanvasView: NewCanvasView) {

  private var contextAttributes: JSONObject? = null
  private var paint: Paint? = null

  private lateinit var path: Path

  fun beginPath() {
    path = Path();
  }

  fun closePath() {

  }

  fun lineTo(x: Float, y: Float) {
    val convertedX = getConvertedX(x)
    val convertedY = getConvertedY(y)
    path.lineTo(convertedX, convertedY);
  }

  fun moveTo(x: Float, y: Float) {
    val convertedX = getConvertedX(x)
    val convertedY = getConvertedY(y)
    path.moveTo(convertedX, convertedY);
  }

  fun stroke(contextAttributes: JSONObject) {
    this.contextAttributes = contextAttributes
    this.newCanvasView!!.drawingCanvas!!.drawPath(path, getPaint()!!)
    newCanvasView.invalidate()
  }

  fun getPaint(): Paint? {
    if (paint == null) {
      paint = Paint()
    }
    if (this.contextAttributes != null) {
      val lineWidth = this.contextAttributes!!.getString("lineWidth")
      val strokeStyle = this.contextAttributes!!.getString("strokeStyle")
      paint!!.color = Color.parseColor(strokeStyle)
      paint!!.strokeWidth = lineWidth.toFloat()
      paint!!.style = Paint.Style.STROKE
      paint!!.strokeCap = Paint.Cap.ROUND
      paint!!.strokeJoin = Paint.Join.ROUND
      paint!!.isAntiAlias = true
      val isErase = false//Color.parseColor(strokeStyle) == Color.TRANSPARENT
      paint!!.xfermode = PorterDuffXfermode(if (isErase) PorterDuff.Mode.CLEAR else PorterDuff.Mode.SRC_OVER)
    }
    return paint
  }

  private fun getConvertedX(x: Float): Float {
    val originalCanvasWidth = newCanvas.width
    if (originalCanvasWidth != null) {
      val currentCanvasWidth = newCanvasView.width
      return currentCanvasWidth * x / originalCanvasWidth
    }
    return x
  }

  private fun getConvertedY(y: Float): Float {
    val originalCanvasHeight = newCanvas.height
    if (originalCanvasHeight != null) {
      val currentCanvasHeight = newCanvasView.height
      return currentCanvasHeight * y / originalCanvasHeight
    }
    return y
  }
}
