import Foundation

import UIKit

extension CGFloat {
    var dp: CGFloat {
        return (self / 320) * UIScreen.main.bounds.width
    }
}

// Color palette

extension UIColor {

    @nonobjc class var redOrange: UIColor {
        return UIColor(red: 1.0, green: 63.0 / 255.0, blue: 0.0, alpha: 1.0)
    }

    @nonobjc class var darkGrey: UIColor {
        return UIColor(red: 39.0 / 255.0, green: 40.0 / 255.0, blue: 44.0 / 255.0, alpha: 1.0)
    }

    @nonobjc class var darkGrey50: UIColor {
        return UIColor(red: 39.0 / 255.0, green: 40.0 / 255.0, blue: 44.0 / 255.0, alpha: 0.5)
    }

    @nonobjc class var lightGray: UIColor {
        return UIColor(red: 142.0 / 255.0, green: 142.0 / 255.0, blue: 147.0 / 255.0, alpha: 1.0)
    }

    @nonobjc class var deepSkyBlue: UIColor {
        return UIColor(red: 10.0 / 255.0, green: 122.0 / 255.0, blue: 247.0 / 255.0, alpha: 1.0)
    }

    @nonobjc class var white: UIColor {
        return UIColor(white: 1.0, alpha: 1.0)
    }

    @nonobjc class var darkGreyOpac: UIColor {
        return UIColor(white: 132.0 / 255.0, alpha: 0.3)
    }

    @nonobjc class var whiteOpac: UIColor {
        return UIColor(white: 1.0, alpha: 0.3)
    }

}

// Text styles

extension UIFont {

    class var textRegular: UIFont {
        return UIFont.systemFont(ofSize: 16.0, weight: .regular)
    }

    class var headerTextRegular: UIFont {
        return UIFont.systemFont(ofSize: 16.0, weight: .bold)
    }

    class var headerScreen: UIFont {
        return UIFont(name: "DrukCy-Bold", size: 64.0)!
    }

    class var headerListBig: UIFont {
        return UIFont(name: "DrukCy-Bold", size: 40.0)!
    }

    class var headerListMed: UIFont {
        return UIFont.systemFont(ofSize: 24.0, weight: .bold)
    }

    class var headerListSmall: UIFont {
        return UIFont.systemFont(ofSize: 16.0, weight: .bold)
    }

    class var noteList: UIFont {
        return UIFont.systemFont(ofSize: 16.0, weight: .regular)
    }

    class var noteListSmall: UIFont {
        return UIFont.systemFont(ofSize: 14.0, weight: .regular)
    }

    class var note: UIFont {
        return UIFont.systemFont(ofSize: 16.0, weight: .regular)
    }

    class var tag: UIFont {
        return UIFont.systemFont(ofSize: 16.0, weight: .bold)
    }

}
