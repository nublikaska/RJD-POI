//
//  StationNameTableViewCell.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 18/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import SnapKit

class StationHeaderTableViewCell: UITableViewCell {

    let titleLabel = UILabel()
    let subtitleLabel = UILabel()
    let detailsLabel = UILabel()
    
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
        addSubview(titleLabel)
        addSubview(subtitleLabel)
        addSubview(detailsLabel)
        
        configureSubviews()
    }
    
    func configureSubviews() {
        titleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .bold)
        titleLabel.textColor = .black
        
        subtitleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .light)
        subtitleLabel.textColor = .lightGray
//        subtitleLabel.textAlignment = .center
        
        detailsLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .medium)
        detailsLabel.textColor = .black
        detailsLabel.numberOfLines = 0
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        titleLabel.snp.makeConstraints { maker in
            maker.left.equalToSuperview().offset(20)
            maker.right.equalToSuperview().offset(-20)
            maker.top.equalToSuperview().offset(10)
//            maker.bottom.equalTo(subtitleLabel.snp.top).offset(-10)
            
            maker.height.equalTo(20)
        }
        
        subtitleLabel.snp.makeConstraints { maker in
            maker.left.equalToSuperview().offset(20)
            maker.right.equalToSuperview().offset(-20)
            maker.top.greaterThanOrEqualTo(titleLabel.snp.bottom).offset(20)
//            maker.bottom.equalTo(detailsLabel.snp.top).offset(-10)
            
            maker.height.equalTo(20)
        }
        
        detailsLabel.snp.makeConstraints { maker in
            maker.left.equalToSuperview().offset(20)
            maker.right.equalToSuperview().offset(-20)
            maker.top.greaterThanOrEqualTo(subtitleLabel.snp.bottom).offset(20)
            maker.bottom.equalToSuperview().offset(-10)
        }
    }
}
