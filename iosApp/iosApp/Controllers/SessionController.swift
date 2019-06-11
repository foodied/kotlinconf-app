import Foundation
import UIKit
import youtube_ios_player_helper

class SessionController : UIViewController {
    @IBOutlet weak var startButton: UIButton!
    @IBOutlet weak var videoBox: YTPlayerView!

    @IBOutlet weak var speakerLabel: TouchableLabel!
    @IBOutlet weak var roomLabel: UILabel!
    @IBOutlet weak var slidesLabel: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()

        startButton.setImage(UIImage(named: "favoriteEmpty.png"), for: .selected)
        startButton.setImage(UIImage(named: "favorite.png"), for: .selected)
        videoBox.load(withVideoId: "wZZ7oFKsKzY")

        speakerLabel.onTouchUp = {
            let sessionBoard = UIStoryboard(name: "Main", bundle: nil)
            let session = sessionBoard.instantiateViewController(withIdentifier: "Speaker")
            self.navigationController?.pushViewController(session, animated: true)
        }
    }

    @IBAction func backButtonTouch(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }

    @IBAction func favoriteClick(_ sender: Any) {
        startButton.isSelected = !startButton.isSelected
    }

}
