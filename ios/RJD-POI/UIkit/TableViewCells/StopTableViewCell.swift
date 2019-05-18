//
//  StopTableViewCell.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 07/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import SnapKit

class StopTableViewCell: UITableViewCell {
    
    let titleLabel = UILabel()

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
        
        configureSubviews()
    }
    
    func configureSubviews() {
        titleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .bold)
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
            maker.bottom.equalToSuperview().offset(-10)
        }
    }
}
