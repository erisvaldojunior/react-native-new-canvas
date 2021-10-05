#import "React/RCTViewManager.h"

@interface RCT_EXTERN_MODULE(NewCanvasViewManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(color, NSString)

RCT_EXTERN_METHOD(test:(nonnull NSNumber *)node)

@end
