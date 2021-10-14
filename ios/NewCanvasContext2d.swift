import UIKit

class NewCanvasContext2d {

  private var newCanvas: NewCanvas
  private var newCanvasView: NewCanvasView

  var contextAttributes: String?
  var paths: [UIBezierPath]

  init(newCanvas: NewCanvas, newCanvasView: NewCanvasView) {
    self.newCanvas = newCanvas
    self.newCanvasView = newCanvasView
    self.paths = []
  }

  func beginPath() {
    paths.append(UIBezierPath())
  }

  func closePath() {
    paths.last!.close()
  }

  func lineTo(_ x: Float, _ y: Float) {
    paths.last!.addLine(to: CGPoint(x: CGFloat(x), y: CGFloat(y)))
  }

  func moveTo(_ x: Float, _ y: Float) {
    paths.last!.move(to: CGPoint(x: CGFloat(x), y: CGFloat(y)))
  }

  func stroke(withAttributes contextAttributes: String) {
    self.contextAttributes = contextAttributes
    DispatchQueue.main.async {
      self.newCanvasView.setNeedsDisplay()
    }
  }

}
