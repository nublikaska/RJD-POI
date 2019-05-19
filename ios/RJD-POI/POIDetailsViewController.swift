//
//  POIDetailsViewController.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 19/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit

class POIDetailsViewController: UIViewController {
    
    var poiDetails: POIModel?
    
    let tableView = UITableView()
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    init(poi: POIModel) {
        self.poiDetails = poi
        super.init(nibName: nil, bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        title = "Интересные места"
        
        let backImage = UIImage.init(named: "back_button_image")
        navigationItem.leftBarButtonItem = UIBarButtonItem(image: backImage, style: .plain, target: self, action: #selector(back))
        navigationItem.leftBarButtonItem?.tintColor = .rjdAction
        
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(StationHeaderTableViewCell.self, forCellReuseIdentifier: "headerCellId")
        tableView.separatorStyle = .none
        tableView.rowHeight = UITableView.automaticDimension
        tableView.estimatedRowHeight = 120
        view.addSubview(tableView)
        
        tableView.snp.makeConstraints { maker in
            maker.edges.equalToSuperview()
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        tableView.reloadData()
    }

    @objc func back() -> Void {
        navigationController?.popViewController(animated: true)
    }
}

extension POIDetailsViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "headerCellId", for: indexPath) as! StationHeaderTableViewCell
        if let poi = poiDetails {
            cell.titleLabel.text = poi.areaName
            cell.detailsLabel.text = poi.detailInfo
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 200
    }
}
