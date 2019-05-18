//
//  APIManager.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 18/05/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import Firebase
import ObjectMapper

class APIManager {
    
    let db = Firestore.firestore()
    
    func getAllRoutes(completion: @escaping ([RoutesModel]) -> ()) {
        
        db.collection("Route").getDocuments() { (querySnapshot, err) in
            var routes = [RoutesModel]()
            if let err = err {
                print("Error getting documents: \(err)")
            } else {
                if let querySnapshot = querySnapshot {
                    for document in querySnapshot.documents {
                        var routeModel = Mapper<RoutesModel>().map(JSON: document.data())
                        routeModel?.id = document.documentID
                        if let route = routeModel {
                            routes.append(route)
                        }
                    }
                }
                completion(routes)
            }
        }
    }
    
    func getStationsWith(ids: [String], completion: @escaping ([StationModel]) -> ()) {
        
        var stations = [StationModel]()
        getAllStations { allStations in
            for id in ids {
                for station in allStations {
                    if id == station.id {
                        stations.append(station)
                        break
                    }
                }
            }
            completion(stations)
        }
    }
    
    
    func getStopsFor(routeId: String, completion: @escaping ([StationModel]) -> ()) {
    
        let querry = db.collection("Station").whereField("id", isEqualTo: routeId)
        querry.getDocuments() { (querySnapshot, err) in
            var stations = [StationModel]()
            if let err = err {
                print("Error getting documents: \(err)")
            } else {
                if let querySnapshot = querySnapshot {
                    for document in querySnapshot.documents {
                        let stationModel = Mapper<StationModel>().map(JSON: document.data())
                        if let station = stationModel {
                            stations.append(station)
                        }
                    }
                }
                completion(stations)
            }
        }
    }
    
    func getAllStations(completion: @escaping ([StationModel]) -> ()) {
        
        db.collection("Station").getDocuments() { (querySnapshot, err) in
            var stations = [StationModel]()
            if let err = err {
                print("Error getting documents: \(err)")
            } else {
                for document in querySnapshot!.documents {
                    let stationModel = Mapper<StationModel>().map(JSON: document.data())
                    if let station = stationModel {
                        stations.append(station)
                    }
                }
            }
            completion(stations)
        }
    }
    
    func getStationDetailedInfoWith(stationId: String, completion: @escaping (StationDetailedModel) -> ()) {
        
        let querry = db.collection("StationDetailed").document(stationId)
        querry.getDocument() { (document, err) in
            if let document = document, document.exists, let data = document.data() {
                let stationModel = Mapper<StationDetailedModel>().map(JSON: data)
                if let station = stationModel {
                    completion(station)
                }
            }
        }
    }
}
