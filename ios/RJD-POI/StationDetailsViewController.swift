//
//  StationDetailsViewController.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 18/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit

class StationDetailsViewController: UIViewController {
    
    var details: StationDetailedModel?
    var pois = [POIModel]()
    
    let tableView = UITableView()
    
    enum Sections: Int {
        case header = 0
        case time = 1
        case poi = 2
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    init(details: StationDetailedModel) {
        self.details = details
        super.init(nibName: nil, bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        title = "Станция"
        
        let backImage = UIImage.init(named: "back_button_image")
        navigationItem.leftBarButtonItem = UIBarButtonItem(image: backImage, style: .plain, target: self, action: #selector(back))
        navigationItem.leftBarButtonItem?.tintColor = .rjdAction
        
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(StationHeaderTableViewCell.self, forCellReuseIdentifier: "headerCellId")
        tableView.register(StationTimeTableViewCell.self, forCellReuseIdentifier: "timeCellId")
        tableView.register(CollectionViewTableViewCell.self, forCellReuseIdentifier: "collectionId")
        tableView.separatorStyle = .none
        tableView.rowHeight = UITableView.automaticDimension
        tableView.estimatedRowHeight = 120
        view.addSubview(tableView)
        
        tableView.snp.makeConstraints { maker in
            maker.edges.equalToSuperview()
        }
        
        if let details = details {
            let api = APIManager()
            api.getArrayOfPOIsWith(ids: details.poiIds) { [weak self] pois in
                self?.pois = pois
                self?.tableView.reloadData()
            }
        }
    }
    
    @objc func back() -> Void {
        navigationController?.popViewController(animated: true)
    }
}

extension StationDetailsViewController: UITableViewDataSource, UITableViewDelegate {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let details = details else {
            return UITableViewCell()
        }
        
        let section = Sections.init(rawValue: indexPath.section)
        
        switch section {
        case .header?:
            let cell = tableView.dequeueReusableCell(withIdentifier: "headerCellId", for: indexPath) as! StationHeaderTableViewCell
            cell.titleLabel.text = details.stationName
            cell.subtitleLabel.text = details.areaName
            cell.detailsLabel.text = details.detailInfo
            return cell
        case .time?:
            let cell = tableView.dequeueReusableCell(withIdentifier: "timeCellId", for: indexPath) as! StationTimeTableViewCell
            
            let departureTime = Date.fromString(details.arrival, .hhMM)
            let time = departureTime?.addingTimeInterval(TimeInterval(details.stopTime * 60))
            if let time = time {
                let departure = String.fromDate(time, .hhMM)
                cell.departureLabel.text = departure
            }

            cell.stopLabel.text = "\(details.stopTime) мин."
            cell.arrivalLabel.text = details.arrival
            return cell
        case .poi?:
            let cell = tableView.dequeueReusableCell(withIdentifier: "collectionId", for: indexPath) as! CollectionViewTableViewCell
            
            cell.collectionView.delegate = self
            cell.collectionView.dataSource = self
            cell.collectionView.showsHorizontalScrollIndicator = false
            cell.collectionView.reloadData()

            return cell
        default:
            return UITableViewCell()
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        switch indexPath.section {
        case 0:
            return 180
        case 2:
            return 160
        default:
            return 80
        }
    }
    
    func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }
}

extension StationDetailsViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return pois.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "collectionCellId", for: indexPath) as! POICollectionViewCell
        let poi = pois[indexPath.row]
        cell.titleLabel.text = poi.detailInfo
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didDeselectItemAt indexPath: IndexPath) {
//        let poi = pois[indexPath.row]
//        let controller = POIDetailsViewController(poi: poi)
//        navigationController?.pushViewController(controller, animated: true)
        collectionView.deselectItem(at: indexPath, animated: true)
    }
}
