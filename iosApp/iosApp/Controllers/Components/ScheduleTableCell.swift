import Foundation
import UIKit

@IBDesignable
class ScheduleTableCell : UITableViewCell {
    @IBOutlet weak var favourite: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var subtitleLabel: UILabel!

    func configureLook(data: SessionData) {
        titleLabel?.text = data.title
        subtitleLabel?.text = data.location + ", " + data.author

        if (data.isFavourite) {
            self.configureFavourite(data: data)
            return
        }

        if (data.startTime == "8:00") {
            configureNow(data: data)
            return
        }

        configurePlain(data: data)
    }

    private func configureFavourite(data: SessionData) {
        subtitleLabel?.text = data.startTime + "-" + data.endTime + ", " + data.location

        titleLabel.font = UIFont.headerListMed
        subtitleLabel.font = UIFont.noteList

        titleLabel.textColor = UIColor.darkGrey
        subtitleLabel.textColor = UIColor.white

        titleLabel.lineBreakMode = .byWordWrapping

        separatorInset = UIEdgeInsets(top: 0, left: 100000, bottom: 0, right: 0)

        favourite.isHidden = false
    }

    private func configureNow(data: SessionData) {
        self.backgroundColor = UIColor.redOrange

        titleLabel.font = UIFont.headerListSmall
        subtitleLabel.font = UIFont.noteList

        titleLabel.textColor = UIColor.white
        subtitleLabel.textColor = UIColor.white

        favourite.isHidden = true
    }

    private func configurePlain(data: SessionData) {
        titleLabel.font = UIFont.headerListSmall
        subtitleLabel.font = UIFont.noteListSmall

        titleLabel.textColor = UIColor.darkGrey
        subtitleLabel.textColor = UIColor.darkGrey50

        favourite.isHidden = true
    }
}
