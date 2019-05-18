//
//  InputWithTitle.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 26/04/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import SnapKit

class InputWithTitle: UIView {
    
    let titleLabel = UILabel()
    let inputTextField = UITextField()
    let bottomLine = UIView()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubviews()
        configure()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        addSubviews()
        configure()
    }
    
    func addSubviews() {
        addSubview(titleLabel)
        addSubview(inputTextField)
        addSubview(bottomLine)
    }
    
    func configure() {
        titleLabel.font = UIFont.monospacedDigitSystemFont(ofSize: 14, weight: .light)
        titleLabel.textColor = .lightGray
        inputTextField.font = UIFont.monospacedDigitSystemFont(ofSize: 18, weight: .semibold)
        inputTextField.placeholder = "751А"
        bottomLine.backgroundColor = .lightGray
    }
    
    override func layoutSubviews() {
        titleLabel.snp.makeConstraints { maker in
            maker.left.right.top.equalToSuperview()
            maker.height.equalTo(20)
        }
        
        inputTextField.snp.makeConstraints { maker in
            maker.left.right.equalToSuperview()
            maker.top.equalTo(titleLabel.snp.bottom)
            maker.bottom.equalToSuperview().offset(-1)
        }
        
        bottomLine.snp.makeConstraints { maker in
            maker.left.right.equalToSuperview()
            maker.top.equalTo(inputTextField.snp.bottom)
            maker.bottom.equalToSuperview()
        }
    }
}
