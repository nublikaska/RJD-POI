//
//  RoutesModel.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 25/04/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import ObjectMapper

struct RoutesModel: Mappable {

    var id: String = ""
    var number: String = ""
    var departure: String = ""
    var arrival: String = ""
    var departurePlace: String = ""
    var arrivalPlace: String = ""
    var travelTime: Int = 0
    var stopsIds = [String]()
    
    var stations = [StationModel]()
    
    init?(map: Map) { }
    
    mutating func mapping(map: Map) {
        id <- map["id"]
        number <- map["number"]
        departure <- map["departure"]
        arrival <- map["arrival"]
        departurePlace <- map["departurePlace"]
        arrivalPlace <- map["arrivalPlace"]
        travelTime <- map["travelTime"]
        stopsIds <- map["stopsIds"]
    }
}
