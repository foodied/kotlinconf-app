import Foundation
import UIKit

let ScheduleCellId = "ScheduleTableCell"
let ScheduleHeaderId = "ScheduleTableHeader"

@IBDesignable
class ScheduleController : UIViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet weak var scheduleTable: UITableView!


    private let tableData = [[
            SessionData(title: "Mathematical Modeling with Kotlin", startTime: "9:30", endTime: "10:15", location: "Room A", author: "Aaron Arnst", isFavourite: true),
            SessionData(title: "GraphQL powered by Kotlin", startTime: "9:30", endTime: "10:15", location: "Room A", author: "Aaron Arnst", isFavourite: true),
        ], [
            SessionData(title: "Keynote", startTime: "8:00", endTime: "10:15", location: "Room A", author: "Aaron Arnst", isFavourite: false),
        ], [
            SessionData(title: "GraphQL powered by Kotlin", startTime: "9:30", endTime: "10:15", location: "Room A", author: "Aaron Arnst", isFavourite: false),
            SessionData(title: "Safe(r) Kotlin Code - Static Analysis tools", startTime: "9:30", endTime: "10:15", location: "Room A", author: "Aaron Arnst", isFavourite: false),
            SessionData(title: "Android Suspenders", startTime: "9:30", endTime: "10:15", location: "Room A", author: "Aaron Arnst", isFavourite: false),
        ]
    ]

    override func viewDidLoad() {
        super.viewDidLoad()
        scheduleTable.register(UINib(nibName: ScheduleCellId, bundle: nil), forCellReuseIdentifier: ScheduleCellId)
        scheduleTable.register(UINib(nibName: ScheduleHeaderId, bundle: nil), forHeaderFooterViewReuseIdentifier: ScheduleHeaderId)
        scheduleTable.delegate = self
        scheduleTable.dataSource = self
    }

    func numberOfSections(in tableView: UITableView) -> Int {
        return tableData.count
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tableData[section].count
    }

    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        if (section == 0) {
            return nil
        }

        let result = tableView.dequeueReusableHeaderFooterView(withIdentifier: ScheduleHeaderId) as! ScheduleTableHeader

        let startTime = "8:00"
        let endTime = "9:00"
        let isNow: Bool = section == 1 // replace with now check
//
        result.configureLook(start: startTime, end: endTime, isNow: isNow)
        return result
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let section = indexPath.section
        let row = indexPath.row

        let result = tableView.dequeueReusableCell(withIdentifier: ScheduleCellId, for: indexPath) as! ScheduleTableCell
        result.configureLook(data: tableData[section][row])

        return result
    }
}

struct SessionData {
    let title: String
    let startTime: String
    let endTime: String
    let location: String
    let author: String
    let isFavourite: Bool
}
