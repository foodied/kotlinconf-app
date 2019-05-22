import Foundation
import UIKit

class SpeakerController : UIViewController {
    @IBAction func backButtonTouch(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
}
