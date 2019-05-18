//
//  SelectStationViewController.swift
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

protocol SelectStationProtocol: class {
    func stationStartSelected(station: StationModel)
    func stationFinishSelected(station: StationModel)
}

class SelectStationViewController: UIViewController {
    
    let stationsTableView = UITableView()
    var stations = [StationModel]()
    var start: Bool = false
    
    weak var delegate: SelectStationProtocol?

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    init(stations: [StationModel]?, start: Bool) {
        self.stations = stations ?? [StationModel]()
        self.start = start
        super.init(nibName: nil, bundle: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .white

        stationsTableView.dataSource = self
        stationsTableView.delegate = self
        stationsTableView.register(StopTableViewCell.self, forCellReuseIdentifier: "stopCellId")
        stationsTableView.separatorStyle = .none
        view.addSubview(stationsTableView)
        
        stationsTableView.snp.makeConstraints { maker in
            maker.edges.equalToSuperview()
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.view.endEditing(true)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        view.endEditing(true)
    }
}

extension SelectStationViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return stations.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "stopCellId", for: indexPath) as! StopTableViewCell
        let station = stations[indexPath.row]
        cell.titleLabel.text = station.stopsName
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let station = stations[indexPath.row]
        if start {
            delegate?.stationStartSelected(station: station)
        } else {
            delegate?.stationFinishSelected(station: station)
        }
        navigationController?.popViewController(animated: true)
    }
    
}
