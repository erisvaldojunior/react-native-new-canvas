import Foundation
import UIKit

class NewCanvasView : UIView {

  @objc var color: String = "" {
    didSet {
      self.backgroundColor = hexStringToUIColor(hexColor: color)
    }
  }

  func canvasSetStyle(withWidth width: Int, withHeight height: Int) {
    print("NewCanvasView.swift canvasSetStyle width \(width) height \(height)")
  }

  func contextBeginPath() {
    print("NewCanvasView.swift contextBeginPath()")
  }

  func contextClosePath() {
    print("NewCanvasView.swift contextClosePath()")
  }

  func contextLineTo(_ x: Float, _ y: Float) {
    print("NewCanvasView.swift contextLineTo x \(x) y \(y)")
  }

  func contextMoveTo(_ x: Float, _ y: Float) {
    print("NewCanvasView.swift contextMoveTo x \(x) y \(y)")
  }

  func contextStroke(withAttributes contextAttributes: String) {
    print("NewCanvasView.swift stroke contextAttributes \(contextAttributes)")
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
