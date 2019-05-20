import Foundation
import UIKit

@IBDesignable
class ScheduleTableHeader : UITableViewHeaderFooterView {
    @IBOutlet weak var backView: UIView!
    @IBOutlet weak var timeLabel: UILabel!
    @IBOutlet weak var nowLabel: UILabel!

    func configureLook(start: String, end: String, isNow: Bool) {
        timeLabel?.text = start + "  .  .  .  " + end

        if (isNow) {
            backView?.backgroundColor = UIColor.redOrange
            nowLabel?.isHidden = false
        } else {
            nowLabel?.isHidden = true
        }
    }
}
