package com.reactnativenewcanvas

import android.graphics.*
import android.util.Log
import android.view.View
import com.facebook.react.uimanager.ThemedReactContext
import java.util.*


class NewCanvasView : View {
  private val context: ThemedReactContext;
  private val pathsById = hashMapOf<Int, NewCanvasPath>()
  private val points: ArrayList<NewCanvasPoint> = ArrayList<NewCanvasPoint>()
  private var disableHardwareAccelerated = false
  private var drawingBitmap: Bitmap? = null
  private var drawingCanvas: Canvas? = null
  private val paint = Paint()

  constructor(themedReactContext: ThemedReactContext) : super(themedReactContext) {
    context = themedReactContext;
  }

  fun addPoint(pathId: Int, x: Double, y: Double) {
    Log.d("NewCanvasView pathId", pathId.toString());
    val path: NewCanvasPath = pathsById.get(pathId) ?: return
    val point: NewCanvasPoint? = path.addPoint(PointF(x.toFloat(), y.toFloat()))
    if (point != null) {
      points.add(point)
      Log.d("addPoint", point.toString());
      Log.d("addPoint", drawingCanvas.toString());
      val updateRect: Rect = drawingCanvas?.let { path.drawLastPoint(it) }!!
      Log.d("updateRect", updateRect.toString());
      invalidate()
    }
  }

  fun createNewPath(id: Int, strokeColor: Int, strokeWidth: Double) {
    Log.d("createNewPath", id.toString());
    val path = NewCanvasPath(id, strokeColor, strokeWidth)
    Log.d("createNewPath", path.toString());
    pathsById.put(id, path)
    val isErase = strokeColor == Color.TRANSPARENT

    Log.d("createNewPath", pathsById.toString());

    if (isErase && disableHardwareAccelerated === false) {
      disableHardwareAccelerated = true
      setLayerType(View.LAYER_TYPE_SOFTWARE, null)
      //FIXME: https://developer.android.com/guide/topics/graphics/hardware-accel This says to use it but all of a sudden it stopped working in RN 0.6
    }
    invalidate()
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    Log.d("NewCanvasView onDraw()", drawingCanvas.toString());

//    if (mNeedsFullRedraw && mDrawingCanvas != null) {
      drawingCanvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY)

    Log.d("onDraw() points", points.toString());
    Log.d("onDraw() pathsById", pathsById.toString());

      for (point in points) {
        Log.d("onDraw() point", point.pathId.toString());
        val path: NewCanvasPath? = pathsById.get(point.pathId)
        Log.d("onDraw() path", path.toString());
        if (path != null) {
          drawingCanvas?.let { path.draw(it, point.index) }
        }
      }
//      mNeedsFullRedraw = false
//    }
/*
    if (mBackgroundImage != null) {
      val dstRect = Rect()
      canvas!!.getClipBounds(dstRect)
      canvas!!.drawBitmap(mBackgroundImage, null, dstRect, null)
    }
*/
    Log.d("onDraw() drawingBitmap", drawingBitmap.toString());

    if (drawingBitmap != null) {
      canvas?.drawBitmap(drawingBitmap!!, 0.toFloat(), 0.toFloat(), paint)
    }

  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    drawingBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    val localBitmap = drawingBitmap;
    drawingCanvas = localBitmap?.let { Canvas(it) };
    Log.d("NewCanvasView onSize()", drawingCanvas.toString());
//    mNeedsFullRedraw = true;
    invalidate()
  }

}
