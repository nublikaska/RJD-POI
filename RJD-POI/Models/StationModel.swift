//
//  StopsModel.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 24/04/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import ObjectMapper

struct StationModel: Mappable {

    var id: String = ""
    var stopsName: String = ""
    var areaName: String = ""
    var stopTime: Int = 0
    var arrivalTime: String = ""
    var detailId: String = ""
    
    init?(map: Map) { }
    
    mutating func mapping(map: Map) {
        id <- map["id"]
        stopsName <- map["stopsName"]
        areaName <- map["areaName"]
        stopTime <- map["stopTime"]
        arrivalTime <- map["arrivalTime"]
        detailId <- map["detailId"]
    }
}
