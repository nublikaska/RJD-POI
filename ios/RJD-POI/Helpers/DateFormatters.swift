//
//  DateFormatters.swift
//  Tochka
//
//  Created by Veaceslav Makarov on 05/04/2019.
//  Copyright © 2019 AO Tochka. All rights reserved.
//

import UIKit

protocol DateFormatterProtocol {
    var dateFormatter: DateFormatter { get }
}

enum DateformatterType: String, DateFormatterProtocol {
    
    /// полный форматтер с датой, временем, таймзоной
    /// - yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ 2019-03-25T15:51:30 + 05.00
    case `default` = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ"
    /// HH:mm 12:20
    case hhMM = "HH:mm"
    /// d MMMM 5 Марта
    case dMMMM = "d MMMM"
    /// MM.yyyy Май.2019
    case mmYYYY = "MM.yyyy"
    /// dd.MM.yyyy 5.03.2019
    case ddMMyyyy = "dd.MM.yyyy"
    
    var dateFormatter: DateFormatter {
        let formatter = DateFormatter.init(withFormat: self.rawValue, locale: "ru_RU")
        formatter.timeZone = .current
        return formatter
    }
}

extension Date {
    
    static func fromString(_ string: String, _ type: DateformatterType) -> Date? {
        return type.dateFormatter.date(from: string)
    }
}

extension String {
    
    static func fromDate(_ date: Date, _ type: DateformatterType) -> String {
        return type.dateFormatter.string(from: date)
    }
}
