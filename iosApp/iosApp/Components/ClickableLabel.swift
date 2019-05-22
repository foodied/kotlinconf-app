import Foundation
import UIKit

class TouchableLabel : UILabel {
    var onTouchUp: () -> Void = {}

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        onTouchUp()
    }
}
