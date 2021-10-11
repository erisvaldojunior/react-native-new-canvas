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
    print("NewCanvasViewManager.swift NewCanvasViewManager class canvasSetStyle func, width \(width) height \(height)")
    newCanvasView.canvasSetStyle(withWidth: width.intValue, withHeight: height.intValue)
  }

  @objc func contextBeginPath(_ node:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextBeginPath func")
    newCanvasView.contextBeginPath()
    
  }

  @objc func contextClosePath(_ node:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextClosePath func")
    newCanvasView.contextClosePath()
  }

  @objc func contextLineTo(_ node:NSNumber, x:NSNumber, y:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextLineTo func, x \(x) y \(y)")
    newCanvasView.contextLineTo(x.floatValue, y.floatValue)
  }

  @objc func contextMoveTo(_ node:NSNumber, x:NSNumber, y:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextMoveTo func, x \(x) y \(y)")
    newCanvasView.contextMoveTo(x.floatValue, y.floatValue)
  }

  @objc func contextStroke(_ node:NSNumber, contextAttributes:NSString) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextStroke func, contextAttributes \(contextAttributes)")
      newCanvasView.contextStroke(withAttributes: contextAttributes as String)
  }
}
