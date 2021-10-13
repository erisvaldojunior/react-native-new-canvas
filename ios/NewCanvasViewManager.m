#import "React/RCTViewManager.h"

@interface RCT_EXTERN_MODULE(NewCanvasViewManager, RCTViewManager)

RCT_EXTERN_METHOD(canvasSetStyle:(nonnull NSNumber *)node
    width:(nonnull NSNumber *)width
    height:(nonnull NSNumber *)height)

RCT_EXTERN_METHOD(contextBeginPath:(nonnull NSNumber *)node)

RCT_EXTERN_METHOD(contextClosePath:(nonnull NSNumber *)node)

RCT_EXTERN_METHOD(contextLineTo:(nonnull NSNumber *)node
    x:(nonnull NSNumber *)x
    y:(nonnull NSNumber *)y)

RCT_EXTERN_METHOD(contextMoveTo:(nonnull NSNumber *)node
    x:(nonnull NSNumber *)x
    y:(nonnull NSNumber *)y)

RCT_EXTERN_METHOD(contextStroke:(nonnull NSNumber *)node
    contextAttributes:(nonnull NSString *)contextAttributes)

RCT_EXPORT_VIEW_PROPERTY(color, NSString)


@end
