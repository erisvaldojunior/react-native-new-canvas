@objc(NewCanvasViewManager)
class NewCanvasViewManager: RCTViewManager {

  override func view() -> (NewCanvasView) {
    return NewCanvasView()
  }

  @objc func canvasSetStyle(_ node:NSNumber, width:NSNumber, height:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class canvasSetStyle func, width \(width) height \(height)")
  }

  @objc func contextBeginPath(_ node:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextBeginPath func")
  }

  @objc func contextClosePath(_ node:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextClosePath func")
  }

  @objc func contextLineTo(_ node:NSNumber, x:NSNumber, y:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextLineTo func, x \(x) y \(y)")
  }

  @objc func contextMoveTo(_ node:NSNumber, x:NSNumber, y:NSNumber) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextMoveTo func, x \(x) y \(y)")
  }

  @objc func contextStroke(_ node:NSNumber, contextAttributes:NSString) {
    print("NewCanvasViewManager.swift NewCanvasViewManager class contextStroke func, contextAttributes \(contextAttributes)")
  }
}

class NewCanvasView : UIView {

  @objc var color: String = "" {
    didSet {
      self.backgroundColor = hexStringToUIColor(hexColor: color)
    }
  }

  func hexStringToUIColor(hexColor: String) -> UIColor {
    let stringScanner = Scanner(string: hexColor)

    if(hexColor.hasPrefix("#")) {
      stringScanner.scanLocation = 1
    }
    var color: UInt32 = 0
    stringScanner.scanHexInt32(&color)

    let r = CGFloat(Int(color >> 16) & 0x000000FF)
    let g = CGFloat(Int(color >> 8) & 0x000000FF)
    let b = CGFloat(Int(color) & 0x000000FF)

    return UIColor(red: r / 255.0, green: g / 255.0, blue: b / 255.0, alpha: 1)
  }
}
