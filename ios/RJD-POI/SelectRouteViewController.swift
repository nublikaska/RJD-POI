//
//  SelectRouteViewController.swift
//  RJD-POI
//
//  Created by Veaceslav Makarov on 26/04/2019.
//  Copyright © 2019 Veaceslav Makarov. All rights reserved.
//

import UIKit
import SnapKit
import RxCocoa
import RxSwift
import Firebase
import JGProgressHUD

class SelectRouteViewController: UIViewController {
    
    let trainNumberView: InputWithTitle = InputWithTitle()
    let departureView: InputWithTitle = InputWithTitle()
    let arrivalView: InputWithTitle = InputWithTitle()
    
    let bag = DisposeBag()
    var allRoutes = [RoutesModel]()
    var stations = [StationModel]()
    
    var route: RoutesModel?
    var stationStart: StationModel?
    var stationFinish: StationModel?
    
    var doneButton = UIButton()

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        title = "Поезд, города и дата"
        view.backgroundColor = .white
        
        doneButton.backgroundColor = .green
        doneButton.setTitle("Всё готово", for: .normal)
        doneButton.layer.cornerRadius = 10
        doneButton.addTarget(self, action: #selector(viewRoute), for: .touchUpInside)
        view.addSubview(doneButton)
        
        view.addSubview(trainNumberView)
        view.addSubview(departureView)
        view.addSubview(arrivalView)
        
        trainNumberView.titleLabel.text = "Номер поезда"
        departureView.titleLabel.text = "Пункт отправления"
        arrivalView.titleLabel.text = "Пункт назначения"
        
        departureView.inputTextField.isUserInteractionEnabled = false
        arrivalView.inputTextField.isUserInteractionEnabled = false
        
        trainNumberView.inputTextField.rx.controlEvent([.editingChanged]).asObservable().subscribe({ [weak self] _ in
            if let text = self?.trainNumberView.inputTextField.text {
                self?.checkRouteWith(number: text)
            }
        }).disposed(by: bag)
        
        departureView.inputTextField.rx.controlEvent([.editingDidBegin]).asObservable().subscribe({ [weak self] _ in
            let controller = SelectStationViewController(stations: self?.stations, start: true)
            controller.delegate = self
            self?.navigationController?.pushViewController(controller, animated: true)
            self?.view.endEditing(true)
        }).disposed(by: bag)
        
        arrivalView.inputTextField.rx.controlEvent([.editingDidBegin]).asObservable().subscribe({ [weak self] _ in
            let controller = SelectStationViewController(stations: self?.stations, start: false)
            controller.delegate = self
            self?.navigationController?.pushViewController(controller, animated: true)
            self?.view.endEditing(true)
        }).disposed(by: bag)
        
        
        let api = APIManager()
        
        let hud = JGProgressHUD(style: .dark)
        hud.textLabel.text = "Грузятся маршруты"
        hud.show(in: self.view)
        
        api.getAllRoutes { [weak self] routes in
            self?.allRoutes = routes
            hud.dismiss(animated: true)
        }
    }
    
    override func viewWillLayoutSubviews() {
        trainNumberView.snp.remakeConstraints { maker in
            maker.top.equalToSuperview().offset(100)
            maker.left.equalToSuperview().offset(20)
            maker.right.equalToSuperview().offset(-20)
            maker.height.equalTo(50)
        }
        
        departureView.snp.remakeConstraints { maker in
            maker.top.equalTo(trainNumberView.snp.bottom).offset(20)
            maker.left.equalToSuperview().offset(20)
            maker.right.equalToSuperview().offset(-20)
            maker.height.equalTo(50)
        }
        
        arrivalView.snp.remakeConstraints { maker in
            maker.top.equalTo(departureView.snp.bottom).offset(20)
            maker.left.equalToSuperview().offset(20)
            maker.right.equalToSuperview().offset(-20)
            maker.height.equalTo(50)
        }
        
        doneButton.snp.makeConstraints { maker in
            maker.top.equalTo(arrivalView.snp.bottom).offset(20)
            maker.left.equalToSuperview().offset(20)
            maker.right.equalToSuperview().offset(-20)
            maker.height.equalTo(40)
        }
    }
    
    func checkRouteWith(number: String) {
        for route in allRoutes {
            print(route.number.uppercased())
            print(number.uppercased())
            if route.number.uppercased() == number.uppercased() {

                self.route = route
                
                let api = APIManager()
                
                let hud = JGProgressHUD(style: .dark)
                hud.textLabel.text = "Грузятся остановки по маршруту"
                hud.show(in: self.view)
                
                let stationsIds = route.stopsIds
                api.getStationsWith(ids: stationsIds) { [weak self] stations in
                    self?.stations = stations
                    hud.dismiss(animated: true)
                    self?.departureView.inputTextField.isUserInteractionEnabled = true
                }
                
            }
        }
    }
    
    @objc func viewRoute() {
        if let route = route, let stationStart = stationStart, let stationFinish = stationFinish {
            var route = route
            route.stations = stations
            let controller = RouteTimelineViewController(route: route, startStation: stationStart, finishStation: stationFinish)
            navigationController?.pushViewController(controller, animated: true)
        } else {
            let hud = JGProgressHUD(style: .dark)
            hud.textLabel.text = "Введите маршрут, пункт отправления, и пункт назначения"
            hud.show(in: self.view)
            hud.dismiss(afterDelay: 2)
        }
    }
}

extension SelectRouteViewController: SelectStationProtocol {
    func stationStartSelected(station: StationModel) {
        stationStart = station
        departureView.inputTextField.text = stationStart?.stopsName
        arrivalView.inputTextField.isUserInteractionEnabled = true
    }
    
    func stationFinishSelected(station: StationModel) {
        stationFinish = station
        arrivalView.inputTextField.text = stationFinish?.stopsName
    }
}
