@objc(NewCanvasViewManager)
class NewCanvasViewManager: RCTViewManager {

  var newCanvasView: NewCanvasView!

  override static func requiresMainQueueSetup() -> Bool {
    return true
  }

  override func view() -> (NewCanvasView) {
    newCanvasView = NewCanvasView()
    return newCanvasView
  }

  @objc func canvasSetStyle(_ node:NSNumber, width:NSNumber, height:NSNumber) {
    newCanvasView.canvasSetStyle(withWidth: width.intValue, withHeight: height.intValue)
  }

  @objc func contextBeginPath(_ node:NSNumber) {
    newCanvasView.contextBeginPath()
  }

  @objc func contextClosePath(_ node:NSNumber) {
    newCanvasView.contextClosePath()
  }

  @objc func contextLineTo(_ node:NSNumber, x:NSNumber, y:NSNumber) {
    newCanvasView.contextLineTo(x.floatValue, y.floatValue)
  }

  @objc func contextMoveTo(_ node:NSNumber, x:NSNumber, y:NSNumber) {
    newCanvasView.contextMoveTo(x.floatValue, y.floatValue)
  }

  @objc func contextStroke(_ node:NSNumber, contextAttributes:NSString) {
    newCanvasView.contextStroke(withAttributes: contextAttributes as String)
  }
}
