//
//  CollectionViewTableViewCell.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 19/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit

class CollectionViewTableViewCell: UITableViewCell {

    var collectionView: UICollectionView!
    let layout = UICollectionViewFlowLayout()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        
        layout.scrollDirection = .horizontal
        layout.itemSize = CGSize.init(width: 150, height: 150)
        layout.sectionInset = .init(top: 10, left: 10, bottom: 10, right: 10)
        collectionView = UICollectionView.init(frame: .zero, collectionViewLayout: layout)
        collectionView.register(POICollectionViewCell.self, forCellWithReuseIdentifier: "collectionCellId")
        collectionView.backgroundColor = .clear
        
        addSubview(collectionView)
        
        configureSubviews()
    }
    
    func configureSubviews() {
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        collectionView.snp.makeConstraints { maker in
            maker.edges.equalToSuperview()
        }
    }

}
