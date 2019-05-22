import Foundation
import UIKit
import youtube_ios_player_helper

class SessionController : UIViewController {
    @IBOutlet weak var startButton: UIButton!
    @IBOutlet weak var videoBox: YTPlayerView!

    @IBOutlet weak var speakerLabel: UILabel!
    @IBOutlet weak var roomLabel: UILabel!
    @IBOutlet weak var slidesLabel: UILabel!

    override func viewDidLoad() {
        startButton.setImage(UIImage(named: "favouriteEmpty.png"), for: .selected)
        startButton.setImage(UIImage(named: "favourite.png"), for: .selected)
        videoBox.load(withVideoId: "wZZ7oFKsKzY")

        let tapHandler = UIGestureRecognizer(target: <#T##Any?#>, action: <#T##Selector?#>)
    }

    @IBAction func backButtonTouch(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }

    @IBAction func favouriteClick(_ sender: Any) {
        startButton.isSelected = !startButton.isSelected
    }
}
