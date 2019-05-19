//
//  TimelineTableViewCell.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 18/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import SnapKit

class TimelineTableViewCell: UITableViewCell {

    let arrivalTimeLabel = UILabel()
    let stationNameLabel = UILabel()
    let areaNameLabel = UILabel()
    let stopTimeLabel = UILabel()
    
    let timelineView = UIView()
    let horizontalLine = UIView()
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        addSubview(arrivalTimeLabel)
        addSubview(stationNameLabel)
        addSubview(areaNameLabel)
        addSubview(stopTimeLabel)
        addSubview(timelineView)
        addSubview(horizontalLine)
        
        configureSubviews()
    }
    
    func configureSubviews() {
        arrivalTimeLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .bold)
        arrivalTimeLabel.textColor = .black
        
        stationNameLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .bold)
        stationNameLabel.textColor = .black
        
        areaNameLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .light)
        areaNameLabel.textColor = .lightGray
        
        stopTimeLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 12, weight: .light)
        stopTimeLabel.textColor = .gray
        
        stopTimeLabel.layer.cornerRadius = 4
        stopTimeLabel.clipsToBounds = true
        stopTimeLabel.backgroundColor = .groupTableViewBackground
        
        timelineView.backgroundColor = .groupTableViewBackground
        horizontalLine.backgroundColor = .groupTableViewBackground
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        arrivalTimeLabel.snp.makeConstraints { maker in
            maker.left.equalToSuperview().offset(20)
            maker.width.equalTo(60)
            maker.centerY.equalToSuperview()
        }
        
        timelineView.snp.makeConstraints { maker in
            maker.left.equalTo(arrivalTimeLabel.snp.right).offset(10)
            maker.width.equalTo(4)
            maker.top.bottom.equalToSuperview()
        }
        
        horizontalLine.snp.makeConstraints { maker in
            maker.left.equalTo(timelineView.snp.left)
            maker.centerY.equalToSuperview()
            maker.width.equalTo(12)
            maker.height.equalTo(4)
        }
        
        stationNameLabel.snp.makeConstraints { maker in
            maker.left.equalTo(timelineView.snp.right).offset(14)
            maker.right.lessThanOrEqualTo(stopTimeLabel.snp.left).offset(-20)
            maker.centerY.equalToSuperview().offset(-20)
            maker.bottom.equalTo(areaNameLabel.snp.top).offset(20)
        }
        
        areaNameLabel.snp.makeConstraints { maker in
            maker.left.equalTo(timelineView.snp.right).offset(14)
            maker.right.lessThanOrEqualTo(stopTimeLabel.snp.left).offset(-20)
            maker.centerY.equalToSuperview().offset(20)
            maker.bottom.equalToSuperview().offset(-20)
        }
        
        stopTimeLabel.snp.makeConstraints { maker in
            maker.right.equalToSuperview().offset(-20)
            maker.centerY.equalToSuperview()
            maker.height.equalTo(20)
        }
    }
}
