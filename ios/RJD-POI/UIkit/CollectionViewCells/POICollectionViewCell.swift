//
//  POICollectionViewCell.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 19/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit

class POICollectionViewCell: UICollectionViewCell {
    
    let containerView = UIView()
    let titleLabel = UILabel()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.addSubview(containerView)
        containerView.addSubview(titleLabel)
        
        configureSubviews()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func configureSubviews() {
        containerView.layer.cornerRadius = 10
        containerView.backgroundColor = .rjdBlue
        
        titleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 12, weight: .regular)
        titleLabel.textColor = .white
        titleLabel.numberOfLines = 0
        
        containerView.isUserInteractionEnabled = false
        titleLabel.isUserInteractionEnabled = false
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
       
        containerView.snp.makeConstraints { maker in
            maker.edges.equalToSuperview()
        }
        
        titleLabel.snp.makeConstraints { maker in
            maker.left.equalToSuperview().offset(10)
            maker.bottom.equalToSuperview().offset(-10)
            maker.right.equalToSuperview().offset(-10)
            maker.top.equalToSuperview().offset(20)
        }
    }
}
