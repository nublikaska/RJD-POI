//
//  StationTimeTableViewCell.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 18/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import SnapKit

class StationTimeTableViewCell: UITableViewCell {

    let arrivalTitleLabel = UILabel()
    let arrivalLabel = UILabel()
    
    let stopTitleLabel = UILabel()
    let stopLabel = UILabel()
    
    let departureTitleLabel = UILabel()
    let departureLabel = UILabel()
    
    let horizontalLine = UIView()
    let verticalLineLeft = UIView()
    let verticalLineRight = UIView()
    
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
        addSubview(arrivalTitleLabel)
        addSubview(arrivalLabel)
        addSubview(stopTitleLabel)
        addSubview(stopLabel)
        addSubview(departureTitleLabel)
        addSubview(departureLabel)
        
        addSubview(horizontalLine)
        addSubview(verticalLineLeft)
        addSubview(verticalLineRight)
        
        configureSubviews()
    }
    
    func configureSubviews() {
        arrivalTitleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .medium)
        arrivalTitleLabel.textColor = .lightGray
        
        stopTitleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .medium)
        stopTitleLabel.textColor = .lightGray
        
        departureTitleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .medium)
        departureTitleLabel.textColor = .lightGray
        
        arrivalLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .bold)
        arrivalLabel.textColor = .black
        
        stopLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .bold)
        stopLabel.textColor = .black
        
        departureLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .bold)
        departureLabel.textColor = .black
        
        horizontalLine.backgroundColor = .lightGray
        verticalLineLeft.backgroundColor = .lightGray
        verticalLineRight.backgroundColor = .lightGray
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        horizontalLine.snp.makeConstraints { maker in
            maker.left.top.equalToSuperview().offset(10)
            maker.right.equalToSuperview().offset(-10)
            maker.height.equalTo(6)
        }
        
        verticalLineLeft.snp.makeConstraints { maker in
            maker.left.equalToSuperview().offset(10)
            maker.width.equalTo(6)
            maker.height.equalTo(20)
        }
        
        verticalLineRight.snp.makeConstraints { maker in
            maker.right.equalToSuperview().offset(-10)
            maker.width.equalTo(6)
            maker.height.equalTo(20)
        }
        
//        arr.snp.makeConstraints { maker in
//            maker.left.equalToSuperview().offset(20)
//            maker.right.equalToSuperview().offset(-20)
//            maker.top.equalToSuperview().offset(10)
//            maker.bottom.equalTo(subtitleLabel.snp.top).offset(10)
//        }
//
//        subtitleLabel.snp.makeConstraints { maker in
//            maker.left.equalToSuperview().offset(20)
//            maker.right.equalToSuperview().offset(-20)
//            maker.top.equalTo(titleLabel.snp.bottom).offset(10)
//            maker.bottom.equalTo(detailsLabel.snp.top).offset(10)
//        }
//
//        detailsLabel.snp.makeConstraints { maker in
//            maker.left.equalToSuperview().offset(20)
//            maker.right.equalToSuperview().offset(-20)
//            maker.top.equalTo(subtitleLabel.snp.bottom).offset(10)
//            maker.bottom.equalToSuperview().offset(-10)
//        }
    }
}
