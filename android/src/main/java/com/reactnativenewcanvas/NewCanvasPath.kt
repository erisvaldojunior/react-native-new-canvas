package com.reactnativenewcanvas

import android.graphics.*
import java.util.*

class NewCanvasPath(private val id: Int, private val strokeColor: Int, private val strokeWidth: Double) {
  private val points: ArrayList<NewCanvasPoint> = ArrayList<NewCanvasPoint>()

  private var paint: Paint? = null

  fun midPoint(p1: PointF, p2: PointF): PointF {
    return PointF((p1.x + p2.x) * 0.5f, (p1.y + p2.y) * 0.5f)
  }

  fun addPoint(p: PointF?): NewCanvasPoint? {
    if (p != null) {
      val point = NewCanvasPoint(id, p, points.size)
      points.add(point)
      return point
    }
    return null;
  }

  fun drawLastPoint(canvas: Canvas): Rect? {
    val pointsCount = points.size
    if (pointsCount < 1) {
      return Rect()
    }
    val index = pointsCount - 1
    draw(canvas, index)
    val p: NewCanvasPoint = points[index]
    return getUpdateRect(p.point)
  }

  fun draw(canvas: Canvas, pointIndex: Int) {
    val pointsCount = points.size
    if (pointIndex >= pointsCount) {
      return
    }
    if (pointsCount >= 3 && pointIndex >= 2) {
      val a: PointF = points[pointIndex - 2].point
      val b: PointF = points[pointIndex - 1].point
      val c: PointF = points[pointIndex].point
      val prevMid = midPoint(a, b)
      val currentMid = midPoint(b, c)

      // Draw a curve
      drawQuadCurve(canvas, prevMid, b, currentMid)
    } else if (pointsCount >= 2 && pointIndex >= 1) {
      val a: PointF = points[pointIndex - 1].point
      val b: PointF = points[pointIndex].point
      val mid = midPoint(a, b)

      // Draw a line to the middle of points a and b
      // This is so the next draw which uses a curve looks correct and continues from there
      drawLine(canvas, a, mid)
    } else if (pointsCount >= 1) {
      val a: PointF = points[pointIndex].point

      // Draw a single point
      drawPoint(canvas, a)
    }
  }

  private fun getUpdateRect(p: PointF): Rect? {
    val updateRect: RectF
    val pointsCount = points.size
    if (pointsCount >= 3) {
      val a: PointF = points[pointsCount - 3].point
      val b: PointF = points[pointsCount - 2].point
      val prevMid = midPoint(a, b)
      val currentMid = midPoint(b, p)
      updateRect = RectF(prevMid.x, prevMid.y, prevMid.x, prevMid.y)
      updateRect.union(b.x, b.y)
      updateRect.union(currentMid.x, currentMid.y)
    } else if (pointsCount >= 2) {
      val a: PointF = points[pointsCount - 2].point
      val mid = midPoint(a, p)
      updateRect = RectF(a.x, a.y, a.x, a.y)
      updateRect.union(mid.x, mid.y)
    } else {
      updateRect = RectF(p.x, p.y, p.x, p.y)
    }
    updateRect.inset((-strokeWidth * 2).toFloat(), (-strokeWidth * 2).toFloat())
    val integralRect = Rect()
    updateRect.roundOut(integralRect)
    return integralRect
  }

  private fun getPaint(): Paint? {
    if (paint == null) {
      val isErase = strokeColor == Color.TRANSPARENT
      paint = Paint()
      paint!!.color = strokeColor
      paint!!.strokeWidth = strokeWidth.toFloat()
      paint!!.style = Paint.Style.STROKE
      paint!!.strokeCap = Paint.Cap.ROUND
      paint!!.strokeJoin = Paint.Join.ROUND
      paint!!.isAntiAlias = true
      paint!!.xfermode = PorterDuffXfermode(if (isErase) PorterDuff.Mode.CLEAR else PorterDuff.Mode.SRC_OVER)
    }
    return paint
  }

  private fun drawPoint(canvas: Canvas, point: PointF) {
    canvas.drawPoint(point.x, point.y, getPaint()!!)
  }

  private fun drawLine(canvas: Canvas, fromPoint: PointF, toPoint: PointF) {
    canvas.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y, getPaint()!!)
  }

  private fun drawQuadCurve(canvas: Canvas, startPoint: PointF, controlPoint: PointF, endPoint: PointF) {
    val path = Path()
    path.moveTo(startPoint.x, startPoint.y)
    path.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y)
    canvas.drawPath(path, getPaint()!!)
  }
}
