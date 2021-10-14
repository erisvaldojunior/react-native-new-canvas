import Foundation
import UIKit

struct ContextAttributes: Codable {
  var lineCap: String?
  var lineJoin: String?
  var lineWidth: Float?
  var strokeStyle: String?
}

class NewCanvasView : UIView {

  private var newCanvas: NewCanvas!

  override init(frame: CGRect) {
    super.init(frame: frame)
    setupView()
  }
  
  //initWithCode to init view from xib or storyboard
  required init?(coder: NSCoder) {
    super.init(coder: coder)
    setupView()
  }
    
  private func setupView() {
    newCanvas = NewCanvas(newCanvasView: self)
  }
  
  func canvasSetStyle(withWidth width: Int, withHeight height: Int) {

  }

  func contextBeginPath() {
    let context = newCanvas.getContext("2d")!
    context.beginPath()
  }

  func contextClosePath() {
    let context = newCanvas.getContext("2d")!
    context.closePath()
  }

  func contextLineTo(_ x: Float, _ y: Float) {
    let context = newCanvas.getContext("2d")!
    context.lineTo(x, y)
  }

  func contextMoveTo(_ x: Float, _ y: Float) {
    let context = newCanvas.getContext("2d")!
    context.moveTo(x, y)
  }

  func contextStroke(withAttributes contextAttributes: String) {
    let context = newCanvas.getContext("2d")!
    context.stroke(withAttributes: contextAttributes)
  }

  override func draw(_ rect: CGRect) {
    super.draw(rect)

    let context = newCanvas.getContext("2d")

    let contextAttributes = context?.contextAttributes
    var contextAttributesObject: ContextAttributes?
    
    if let contextAttributesData = contextAttributes?.data(using: .utf8) {
      contextAttributesObject = try! JSONDecoder().decode(ContextAttributes.self, from: contextAttributesData)
    }

    let paths = context?.paths

    paths?.forEach { path in
      if (contextAttributesObject != nil) {
        let color = hexStringToUIColor(hex: contextAttributesObject?.strokeStyle ?? "#000000")
        color.setStroke()
        path.lineWidth = CGFloat(contextAttributesObject?.lineWidth ?? 1.0)
        path.lineCapStyle = contextAttributesObject?.lineCap == "round" ? .round : .square
        path.lineJoinStyle = contextAttributesObject?.lineJoin == "round" ? .round : .miter
      }
      path.stroke()
    }
  }

  private func hexStringToUIColor (hex:String) -> UIColor {
      var cString:String = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()

      if (cString.hasPrefix("#")) {
          cString.remove(at: cString.startIndex)
      }

      if ((cString.count) != 6) {
          return UIColor.gray
      }

      var rgbValue:UInt64 = 0
      Scanner(string: cString).scanHexInt64(&rgbValue)

      return UIColor(
          red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
          green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
          blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
          alpha: CGFloat(1.0)
      )
  }
}
