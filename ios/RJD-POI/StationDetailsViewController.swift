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
    
    let tableView = UITableView()
    
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
        
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(StationHeaderTableViewCell.self, forCellReuseIdentifier: "headerCellId")
        tableView.register(StationTimeTableViewCell.self, forCellReuseIdentifier: "timeCellId")
        tableView.separatorStyle = .none
        tableView.rowHeight = UITableView.automaticDimension
        tableView.estimatedRowHeight = 120
        view.addSubview(tableView)
        
        tableView.snp.makeConstraints { maker in
            maker.edges.equalToSuperview()
        }
    }
}

extension StationDetailsViewController: UITableViewDataSource, UITableViewDelegate {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let details = details else {
            return UITableViewCell()
        }
        
        switch indexPath.section {
        case 0:
            let cell = tableView.dequeueReusableCell(withIdentifier: "headerCellId", for: indexPath) as! StationHeaderTableViewCell
            cell.titleLabel.text = details.stationName
            cell.subtitleLabel.text = details.areaName
            cell.detailsLabel.text = details.detailInfo
            return cell
        case 1:
            let cell = tableView.dequeueReusableCell(withIdentifier: "timeCellId", for: indexPath) as! StationTimeTableViewCell
            return cell
        default:
            return UITableViewCell()
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 140
    }
}
