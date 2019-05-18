//
//  RouteTimelineViewController.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 18/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import SnapKit
import RxCocoa
import RxSwift
import Firebase
import JGProgressHUD

class RouteTimelineViewController: UIViewController {
    
    var route: RoutesModel?
    var startStation: StationModel?
    var finishStation: StationModel?
    
    let timelineTableView = UITableView()
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    init(route: RoutesModel, startStation: StationModel, finishStation: StationModel) {
        self.route = route
        self.startStation = startStation
        self.finishStation = finishStation
        super.init(nibName: nil, bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        
        if let route = route {
            title = "Маршрут поезда №" + route.number
        }
        
        timelineTableView.dataSource = self
        timelineTableView.delegate = self
        timelineTableView.register(TimelineTableViewCell.self, forCellReuseIdentifier: "timelineCellId")
        timelineTableView.separatorStyle = .none
        timelineTableView.rowHeight = UITableView.automaticDimension
        timelineTableView.estimatedRowHeight = 120
        view.addSubview(timelineTableView)
        
        timelineTableView.snp.makeConstraints { maker in
            maker.edges.equalToSuperview()
        }
        
        self.view.endEditing(true)
    }
}

extension RouteTimelineViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return route?.stations.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "timelineCellId", for: indexPath) as! TimelineTableViewCell
        if let route = route {
            let station = route.stations[indexPath.row]
            cell.arrivalTimeLabel.text = station.arrivalTime
            cell.stationNameLabel.text = station.stopsName
            cell.areaNameLabel.text = station.areaName
            cell.stopTimeLabel.text = " \(station.stopTime) мин. "
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 120
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        if let route = route {
            let station = route.stations[indexPath.row]
            
            let hud = JGProgressHUD(style: .dark)
            hud.textLabel.text = "Получение детальной информации по маршруту..."
            hud.show(in: self.view)
            
            let api = APIManager()
            api.getStationDetailedInfoWith(stationId: station.detailId) { [weak self] details in
                
                var detailsModel = details
                detailsModel.stationName = station.stopsName
                detailsModel.areaName = station.areaName
                detailsModel.stopTime = station.stopTime
                
                let controller = StationDetailsViewController(details: detailsModel)
                self?.navigationController?.pushViewController(controller, animated: true)
                hud.dismiss(animated: true)
            }
        }
    }
}
