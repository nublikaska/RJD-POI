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
        arrivalTitleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 12, weight: .medium)
        arrivalTitleLabel.textColor = .lightGray
        
        stopTitleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 12, weight: .medium)
        stopTitleLabel.textColor = .lightGray
        
        departureTitleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 12, weight: .medium)
        departureTitleLabel.textColor = .lightGray
        
        arrivalLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 12, weight: .bold)
        arrivalLabel.textColor = .black
        
        stopLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 12, weight: .bold)
        stopLabel.textColor = .black
        
        departureLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 12, weight: .bold)
        departureLabel.textColor = .black
        
        horizontalLine.backgroundColor = .lightGray
        verticalLineLeft.backgroundColor = .lightGray
        verticalLineRight.backgroundColor = .lightGray
        
        arrivalTitleLabel.text = "Прибытие"
        stopTitleLabel.text = "Стоянка"
        departureTitleLabel.text = "Отправление"
        
        arrivalTitleLabel.textAlignment = .left
        stopTitleLabel.textAlignment = .center
        departureTitleLabel.textAlignment = .right
        
        arrivalLabel.textAlignment = .left
        stopLabel.textAlignment = .center
        departureLabel.textAlignment = .right
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        horizontalLine.snp.makeConstraints { maker in
            maker.left.top.equalToSuperview().offset(10)
            maker.right.equalToSuperview().offset(-10)
            maker.height.equalTo(4)
        }
        
        verticalLineLeft.snp.makeConstraints { maker in
            maker.left.equalToSuperview().offset(10)
            maker.width.equalTo(4)
            maker.height.equalTo(20)
        }
        
        verticalLineRight.snp.makeConstraints { maker in
            maker.right.equalToSuperview().offset(-10)
            maker.width.equalTo(4)
            maker.height.equalTo(20)
        }
        
        arrivalTitleLabel.snp.makeConstraints { maker in
            maker.height.equalTo(30)
            maker.left.equalToSuperview().offset(20)
            maker.width.equalTo(100)
            maker.top.equalTo(horizontalLine.snp.bottom).offset(10)
        }
        
        stopTitleLabel.snp.makeConstraints { maker in
            maker.height.equalTo(30)
            maker.centerX.equalToSuperview()
            maker.width.equalTo(100)
            maker.top.equalTo(horizontalLine.snp.bottom).offset(10)
        }
        
        departureTitleLabel.snp.makeConstraints { maker in
            maker.height.equalTo(30)
            maker.right.equalToSuperview().offset(-20)
            maker.width.equalTo(100)
            maker.top.equalTo(horizontalLine.snp.bottom).offset(10)
        }
//
        arrivalLabel.snp.makeConstraints { maker in
            maker.left.equalToSuperview().offset(20)
            maker.width.equalTo(100)
            maker.top.equalTo(arrivalTitleLabel.snp.bottom).offset(10)
            maker.bottom.equalToSuperview().offset(-10)
        }

        stopLabel.snp.makeConstraints { maker in
            maker.centerX.equalToSuperview()
            maker.width.equalTo(100)
            maker.top.equalTo(stopTitleLabel.snp.bottom).offset(10)
            maker.bottom.equalToSuperview().offset(-10)
        }

        departureLabel.snp.makeConstraints { maker in
            maker.right.equalToSuperview().offset(-20)
            maker.width.equalTo(100)
            maker.top.equalTo(departureTitleLabel.snp.bottom).offset(10)
            maker.bottom.equalToSuperview().offset(-10)
        }
    }
}
