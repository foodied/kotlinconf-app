import Foundation
import UIKit

class SessionController : UIViewController {
    @IBOutlet weak var startButton: UIButton!

    override func viewDidLoad() {
        startButton.setImage(UIImage(named: "favouriteEmpty.png"), for: .selected)
        startButton.setImage(UIImage(named: "favourite.png"), for: .selected)
    }

    @IBAction func backButtonTouch(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }

    @IBAction func favouriteClick(_ sender: Any) {
        startButton.isSelected = !startButton.isSelected
    }
}
