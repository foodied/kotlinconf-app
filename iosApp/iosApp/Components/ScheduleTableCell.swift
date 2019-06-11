import Foundation
import UIKit
import kotlinconf

@IBDesignable
class ScheduleTableCell : UITableViewCell {
    @IBOutlet weak var favorite: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var subtitleLabel: UILabel!

    func configureLook(data: SessionModel) {
        titleLabel?.text = data.title
        subtitleLabel?.text = data.room + ", " + data.author

//        if (data.isFavorite) {
//            self.configureFavorite(data: data)
//            return
//        }

        if (data.startsAtStr == "8:00") {
            configureNow(data: data)
            return
        }

        configurePlain(data: data)
    }

    private func configureFavorite(data: SessionModel) {
        subtitleLabel?.text = data.startTime + "-" + data.endTime + ", " + data.location

        titleLabel.font = UIFont.headerListMed
        subtitleLabel.font = UIFont.noteList

        titleLabel.textColor = UIColor.darkGrey
        subtitleLabel.textColor = UIColor.lightGray

        titleLabel.lineBreakMode = .byWordWrapping

        separatorInset = UIEdgeInsets(top: 0, left: 100000, bottom: 0, right: 0)

        favorite.isHidden = false
    }

    private func configureNow(data: SessionModel) {
        self.backgroundColor = UIColor.redOrange

        titleLabel.font = UIFont.headerListSmall
        subtitleLabel.font = UIFont.noteList

        titleLabel.textColor = UIColor.white
        subtitleLabel.textColor = UIColor.white

        separatorInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)

        favorite.isHidden = true
    }

    private func configurePlain(data: SessionModel) {
        titleLabel.font = UIFont.headerListSmall
        subtitleLabel.font = UIFont.noteListSmall

        titleLabel.textColor = UIColor.darkGrey
        subtitleLabel.textColor = UIColor.darkGrey50

        favorite.isHidden = true
    }
}
