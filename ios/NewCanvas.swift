class NewCanvas {

    var height: Int?
    var width: Int?

    private var context2d: NewCanvasContext2d?
    private var newCanvasView: NewCanvasView

    init(newCanvasView: NewCanvasView) {
        self.newCanvasView = newCanvasView
    }

    func getContext(withType contextType: String) -> NewCanvasContext2d? {
        if (contextType == "2d") {
            if (context2d == nil) {
                context2d = NewCanvasContext2d(newCanvas: self, newCanvasView: newCanvasView)
            }
            return context2d
        }
        return nil
    }
}
