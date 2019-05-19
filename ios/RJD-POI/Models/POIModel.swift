//
//  POIModel.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 19/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import ObjectMapper

struct POIModel: Mappable {

    var id: String = ""
    var areaName: String = ""
    var detailInfo: String = ""
    
    init?(map: Map) { }
    
    mutating func mapping(map: Map) {
        areaName <- map["areaName"]
        detailInfo <- map["detailInfo"]
    }
}
