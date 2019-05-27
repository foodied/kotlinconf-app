import UIKit
import kotlinconf

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    public static let service: KotlinConfDataRepository = KotlinConfDataRepository(
        endPoint: "https://api.kotlinconf.com/",
        uid: generateUuid(),
        settings: PlatformSettings()
    )

    private static func generateUuid() -> String {
        return "ios-" + (UIDevice.current.identifierForVendor ?? UUID()).uuidString
    }
}
